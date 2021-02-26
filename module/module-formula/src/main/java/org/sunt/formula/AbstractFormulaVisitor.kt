package org.sunt.formula

import org.antlr.v4.runtime.ParserRuleContext
import org.slf4j.LoggerFactory
import org.sunt.formula.define.DataType
import org.sunt.formula.define.IColumn
import org.sunt.formula.define.SqlDialect
import org.sunt.formula.exception.*
import org.sunt.formula.function.FunctionDefinition
import org.sunt.formula.function.FunctionDefinitionParser.loadFunctions
import org.sunt.formula.function.TokenItem
import org.sunt.formula.parser.FormulaBaseVisitor
import org.sunt.formula.parser.FormulaParser.*
import org.sunt.formula.suggestion.TokenStatus
import java.util.*
import kotlin.collections.ArrayList
import kotlin.collections.HashMap

abstract class AbstractFormulaVisitor(
    protected val dialect: SqlDialect,
    protected val columnInterface: ColumnInterface
) : FormulaBaseVisitor<Any?>() {

    protected val functionMap: Map<String, List<FunctionDefinition>> = loadFunctions(dialect)

    protected val aliasFunctionNameMap: Map<String, String> =
        functionMap.values.flatMap { l -> l.asIterable() }
            .flatMap { func -> func.alias.map { Pair(it, func.funcName) }.asIterable() }.toMap()

    protected var currentFunctions: List<FunctionDefinition> = emptyList()

    protected var currentReservedIdentities: Set<String> = emptySet()

    /**
     * 保存现场
     */
    protected fun <T> recordCurrent(
        currentFunctions: List<FunctionDefinition>,
        currentReservedIdentities: Set<String>,
        block: () -> T
    ): T {
        val formerFunctions = this.currentFunctions
        val formerReservedIdentities = this.currentReservedIdentities
        try {
            this.currentFunctions = currentFunctions
            this.currentReservedIdentities = currentReservedIdentities
            return block()
        } finally {
            this.currentFunctions = formerFunctions
            this.currentReservedIdentities = formerReservedIdentities
        }
    }

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
            stmt.dataType = DataType.NONE
        }
        stmt.expression = ctx.text
        stmt.token = TokenItem.CONSTANT(stmt.expression)
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
            stmt.token = TokenItem.FUNCTION(identity)
            stmt.dataType = funcDefines!![0].dataType
            stmt.expression = identity
            stmt.status = TokenStatus.EXPECTED
        } else if (aliasFunctionNameMap[identity]?.also { funcDefines = functionMap[it] } != null) {
            stmt.token = TokenItem.FUNCTION(identity)
            stmt.dataType = funcDefines!![0].dataType
            stmt.expression = identity
            stmt.status = TokenStatus.EXPECTED
        } else if (currentReservedIdentities.contains(identity.toUpperCase())) {
            stmt.token = TokenItem.RESERVED(identity)
            stmt.dataType = DataType.ANY
            stmt.expression = identity
            stmt.status = TokenStatus.NORMAL
        } else if (getColumn(identity)?.also { column = it } != null) {
            stmt.status = TokenStatus.NORMAL
            stmt.token = TokenItem.COLUMN(identity)
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
            columnInterface.getColumnById(columnIdOrName.substring(1))
        } else if (columnIdOrName.startsWith("`") && columnIdOrName.endsWith("`")) {
            columnInterface.getColumnByName(columnIdOrName.substring(1, columnIdOrName.length - 1))
        } else {
            columnInterface.getColumnByName(columnIdOrName)
        }
    }

    private fun getColumnStmt(ctx: ParserRuleContext, column: IColumn?): StatementInfo {
        val colStmt = StatementInfo(ctx)
        colStmt.token = TokenItem.COLUMN(ctx.text)
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
        actualArgs: MutableList<StatementInfo?>
    ): FunctionDefinition {
        val matched = ArrayList<FunctionDefinition>(functionDefines.size)
        val errorInfos = ArrayList<List<AbstractFormulaException>>(functionDefines.size)

        outer@ for (funcDefine in functionDefines) {
            val errors = LinkedList<AbstractFormulaException>()
            val expectArgSize = funcDefine.arguments.size
            val actualArgSize = actualArgs.size

            //处理泛型
            val genericTypeMap: MutableMap<String, DataType> = if (funcDefine.genericTypes.isNotEmpty()) {
                HashMap(2)
            } else mutableMapOf()

            //有参函数
            val expectIter = funcDefine.arguments.iterator()
            val actualIter = actualArgs.listIterator()

            var expectArg: FunctionDefinition.FunctionArgument? = null
            var actualArg: StatementInfo? = null
            var matchedArg = 0
            while (true) {
                //无实参了
                if (!actualIter.hasNext()) {
                    if (!expectIter.hasNext()) {
                        break
                    }
                    //还有形参
                    expectArg = expectIter.next()
                    //无缺省值
                    if (!expectArg.vararg && expectArg.defaultValue == null) {
                        errors.add(ParamsSizeMismatchException(funcDefine.funcName, expectArgSize, actualArgSize))
                        errorInfos.add(errors)
                        continue@outer
                    }
                    continue
                }
                //无形参了
                if (!expectIter.hasNext()) {
                    //此处必然还有实参，那么如果不是可变参数，则不匹配
                    if (expectArg == null || !expectArg.vararg) {
                        errors.add(ParamsSizeMismatchException(funcDefine.funcName, expectArgSize, actualArgSize))
                        errorInfos.add(errors)
                        continue@outer
                    }
                }

                if (expectArg == null) expectArg = expectIter.next()
                if (actualArg == null) actualArg = actualIter.next()

                //泛型实际类型
                val genericRealType: DataType? = expectArg.genericType?.let { genericTypeMap[it] }

                //参数匹配
                if (expectArg.match(actualArg!!.expression, actualArg.dataType, actualArg.token, genericRealType)) {
                    if (expectArg.genericType != null && genericRealType == null) {
                        genericTypeMap[expectArg.genericType!!] = actualArg.dataType
                    }
                    if (expectIter.hasNext()) expectArg = expectIter.next()
                    if (actualIter.hasNext()) actualArg = actualIter.next()
                    matchedArg++
                    continue
                }
                //参数不匹配
                //无缺省参数或无下一形参
                if (expectArg.defaultValue == null || !expectIter.hasNext()) {
                    errors.add(DataTypeMismatchException(actualArg.expression, expectArg.dataType, actualArg.dataType))
                    errorInfos.add(errors)
                    continue@outer
                }
                //有缺省参数
                else {
                    expectArg = expectIter.next()
                }
            }
            matched.add(funcDefine)
        }
        if (matched.isNotEmpty()) {
            if (matched.size > 1) {
                throw AmbiguousFunctionException(matched)
            }
            return matched[0]
        }
        if (errorInfos.isNotEmpty()) {
            throw errorInfos[0][0]
        }
        throw WillNeverHappenException("不应该来这里")
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

        val operatorMap = mapOf(
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

}