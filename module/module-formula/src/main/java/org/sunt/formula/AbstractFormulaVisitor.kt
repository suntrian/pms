package org.sunt.formula

import org.antlr.v4.runtime.ParserRuleContext
import org.slf4j.LoggerFactory
import org.sunt.formula.define.DataType
import org.sunt.formula.define.IColumn
import org.sunt.formula.define.SqlDialect
import org.sunt.formula.exception.AbstractFormulaException
import org.sunt.formula.exception.DataTypeMismatchException
import org.sunt.formula.exception.ParamsSizeMismatchException
import org.sunt.formula.exception.WillNeverHappenException
import org.sunt.formula.function.FunctionDefinition
import org.sunt.formula.function.FunctionDefinitionParser.loadFunctions
import org.sunt.formula.parser.FormulaBaseVisitor
import org.sunt.formula.parser.FormulaParser.*
import org.sunt.formula.suggestion.SuggestionScope
import org.sunt.formula.suggestion.TokenStatus
import java.util.*
import java.util.function.Function
import kotlin.collections.ArrayList

abstract class AbstractFormulaVisitor(
    protected val dialect: SqlDialect,
    protected val getColumnById: Function<String, IColumn?>,
    protected val getColumnByName: Function<String, IColumn?>
) : FormulaBaseVisitor<Any?>() {

    protected val functionMap: Map<String, List<FunctionDefinition>> = loadFunctions(dialect)

    protected val aliasFunctionNameMap: Map<String, String>

    override fun visitConstantExpression(ctx: ConstantExpressionContext): StatementInfo {
        return visitConstant(ctx.constant())
    }

    override fun visitColumnExpression(ctx: ColumnExpressionContext): StatementInfo {
        return when (val columnCtx = ctx.column()) {
            is ColumnIdContext -> visitColumnId(columnCtx)
            is ColumnNameContext -> visitColumnName(columnCtx)
            is IdentityContext -> visitIdentity(columnCtx)
            else -> throw WillNeverHappenException("Not Expected Here")
        }
    }

    override fun visitConstant(ctx: ConstantContext): StatementInfo {
        val stmt = StatementInfo(ctx)
        if (ctx.BOOL() != null) {
            stmt.dataType = DataType.BOOLEAN
        } else if (ctx.INTEGER() != null) {
            stmt.dataType = DataType.INTEGER
        } else if (ctx.FLOAT() != null) {
            stmt.dataType = DataType.DECIMAL
        } else if (ctx.STRING() != null) {
            stmt.dataType = DataType.STRING
        } else if (ctx.NULL() != null) {
            stmt.dataType = DataType.ANY
        }
        stmt.expression = ctx.text
        stmt.scope = SuggestionScope.CONSTANT(stmt.expression)
        stmt.status = TokenStatus.NORMAL
        return stmt
    }

    override fun visitColumnId(ctx: ColumnIdContext): StatementInfo {
        return getColumnStmt(ctx, getColumn(ctx.text))
    }

    override fun visitColumnName(ctx: ColumnNameContext): StatementInfo {
        return getColumnStmt(ctx, getColumn(ctx.text))
    }

    override fun visitIdentity(ctx: IdentityContext): StatementInfo {
        var funcDefines: List<FunctionDefinition>? = emptyList()
        var column: IColumn? = null
        val stmt = StatementInfo(ctx)
        val identity = ctx.text
        if (functionMap[identity]?.also { funcDefines = it } != null) {
            stmt.scope = SuggestionScope.FUNCTION(identity)
            stmt.dataType = funcDefines!![0].dataType
            stmt.expression = identity
            stmt.status = TokenStatus.EXPECTED
        } else if (aliasFunctionNameMap[identity]?.also { funcDefines = functionMap[it] } != null) {
            stmt.scope = SuggestionScope.FUNCTION(identity)
            stmt.dataType = funcDefines!![0].dataType
            stmt.expression = identity
            stmt.status = TokenStatus.EXPECTED
        } else if (getColumn(ctx.text)?.also { column = it } != null) {
            stmt.status = TokenStatus.NORMAL
            stmt.scope = SuggestionScope.COLUMN(identity)
            stmt.expression = column!!.expression
            stmt.dataType = column!!.dataType
        } else {
            stmt.status = TokenStatus.UNKNOWN
            stmt.expression = ctx.text
            stmt.dataType = DataType.NONE
        }
        return stmt
    }

    private fun getColumn(columnIdOrName: String): IColumn? {
        return if (columnIdOrName.startsWith("#")) {
            getColumnById.apply(columnIdOrName.substring(1))
        } else if (columnIdOrName.startsWith("`") && columnIdOrName.endsWith("`")) {
            getColumnByName.apply(columnIdOrName.substring(1, columnIdOrName.length - 1))
        } else {
            getColumnByName.apply(columnIdOrName)
        }
    }

    private fun getColumnStmt(ctx: ParserRuleContext, column: IColumn?): StatementInfo {
        val colStmt = StatementInfo(ctx)
        colStmt.scope = SuggestionScope.COLUMN(ctx.text)
        if (column == null) {
            colStmt.status = TokenStatus.UNKNOWN
            colStmt.expression = ctx.text
            colStmt.dataType = DataType.NONE
        } else {
            colStmt.status = TokenStatus.NORMAL
            colStmt.dataType = column.dataType
            colStmt.expression = column.expression
        }
        return colStmt
    }

    protected fun figureFunctionDefine(
        functionDefines: List<FunctionDefinition>,
        params: List<StatementInfo>
    ): FunctionDefinition {
        val matched = ArrayList<FunctionDefinition>(functionDefines.size)
        val errorInfos = ArrayList<List<AbstractFormulaException>>(functionDefines.size)

        outer@ for (funcDefine in functionDefines) {
            val errors = LinkedList<AbstractFormulaException>()
            if (funcDefine.arguments.isEmpty() && params.isNotEmpty()) {
                errors.add(ParamsSizeMismatchException(funcDefine.funcName, funcDefine.arguments.size, params.size));
                //"函数${funcDefine.funcName}期待${funcDefine.args.size}个参数，实际为${params.size}"
                errorInfos.add(errors)
                continue
            }
            val paramIter: Iterator<StatementInfo> = params.listIterator()
            var i = 0
            for (arg in funcDefine.arguments) {
                i++
                if (!paramIter.hasNext()) {
                    errors.add(ParamsSizeMismatchException(funcDefine.funcName, funcDefine.arguments.size, params.size))
                    //"函数${funcDefine.funcName}期待${funcDefine.args.size}个参数，实际为${params.size}"
                    errorInfos.add(errors)
                    continue@outer
                }
                var paramStmt = paramIter.next()
                if (!arg.vararg) {
                    if (paramInvalid(arg.dataType, paramStmt.dataType)) {
                        errors.add(DataTypeMismatchException(paramStmt.expression, arg.dataType, paramStmt.dataType))
                        //"函数${funcDefine.funcName}第${i}个参数期待${arg.dataType}类型, 实际为${paramStmt.dataType}类型"
                        errorInfos.add(errors)
                        continue@outer
                    }
                } else {
                    while (paramIter.hasNext()) {
                        i++
                        paramStmt = paramIter.next()
                        if (paramInvalid(arg.dataType, paramStmt.dataType)) {
                            errors.add(DataTypeMismatchException(paramStmt.expression, arg.dataType, paramStmt.dataType))
                            //"函数${funcDefine.funcName}第${i}个参数期待${arg.dataType}类型, 实际为${paramStmt.dataType}类型"
                            errorInfos.add(errors)
                            continue@outer
                        }
                    }
                }
            }
            matched.add(funcDefine)
        }
        if (matched.isNotEmpty()) {
            return matched[0]
        }
        if (errorInfos.isNotEmpty()) {
            throw errorInfos[0][0]
        }
        throw WillNeverHappenException("不应该来这里")
    }

    protected fun paramInvalid(expectedType: DataType, actualType: DataType): Boolean {
        if (DataType.ANY == expectedType || DataType.ANY == actualType || expectedType == actualType) return false
        return true
    }

    companion object {

        val logger = LoggerFactory.getLogger(AbstractFormulaVisitor::class.java)!!

        @JvmStatic
        fun checkDataType(stmt: StatementInfo, expectedType: DataType): Boolean {
            if (expectedType === stmt.dataType) {
                return true
            }
            throw DataTypeMismatchException(stmt.expression, expectedType, stmt.dataType)
        }

        @JvmStatic
        fun checkArgSize(stmts: List<ParserRuleContext>, expectedSize: Int): Boolean {
            if (stmts.size == expectedSize) {
                return true
            }
            throw ParamsSizeMismatchException("", expectedSize, stmts.size)
        }

        public val operatorMap = mapOf(
                AND to " AND ",
                XOR to " XOR ",
                OR to " OR ",
                MUL to " * ",
                DIV to " / ",
                MOD to " % ",
                PLUS to " + ",
                MINUS to " - ",
                GREATER to " > ",
                GREATER_EQUAL to " >= ",
                LESS to " < ",
                LESS_EQUAL to " <= ",
                EQUAL to " = "
        )
    }

    init {
        aliasFunctionNameMap = functionMap.values.flatMap { l -> l.asIterable() }.flatMap { func -> func.alias.map { Pair(it, func.funcName) }.asIterable() }.toMap()
    }
}