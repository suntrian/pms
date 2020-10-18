package org.sunt.formula

import org.antlr.v4.runtime.ParserRuleContext
import org.slf4j.LoggerFactory
import org.sunt.formula.define.DataType
import org.sunt.formula.define.IColumn
import org.sunt.formula.define.SqlProduct
import org.sunt.formula.exception.DataTypeMismatchException
import org.sunt.formula.exception.ParamsSizeMismatchException
import org.sunt.formula.function.FunctionDefine
import org.sunt.formula.function.FunctionDefineParser.getFunctionMapByProduct
import org.sunt.formula.parser.FormulaBaseVisitor
import org.sunt.formula.parser.FormulaParser.*
import org.sunt.formula.suggestion.SuggestionScope
import org.sunt.formula.suggestion.TokenStatus
import java.util.function.Function

abstract class AbstractFormulaVisitor(protected val dialect: SqlProduct, protected val getColumnById: Function<String, IColumn?>, protected val getColumnByName: Function<String, IColumn?>) : FormulaBaseVisitor<Any?>() {

    protected val functionMap: Map<String, List<FunctionDefine>> = getFunctionMapByProduct(dialect)

    protected val aliasFunctionNameMap: Map<String, String>

    override fun visitConstantExpression(ctx: ConstantExpressionContext): StatementInfo {
        return visitConstant(ctx.constant())
    }

    override fun visitColumnExpression(ctx: ColumnExpressionContext): StatementInfo {
        return when (val columnCtx = ctx.column()) {
            is ColumnIdContext -> visitColumnId(columnCtx)
            is ColumnNameContext -> visitColumnName(columnCtx)
            is IdentityContext -> visitIdentity(columnCtx)
            else -> throw IllegalStateException("Not Expected Here")
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
        var funcDefines: List<FunctionDefine> = emptyList()
        var column: IColumn? = null
        val stmt = StatementInfo(ctx)
        val identity = ctx.text
        if (functionMap[identity].also { funcDefines = it!! } != null) {
            stmt.scope = SuggestionScope.FUNCTION(identity)
            stmt.dataType = funcDefines[0].dataType
            stmt.expression = identity
            stmt.status = TokenStatus.EXPECTED
        } else if (getColumn(ctx.text).also { column = it } != null) {
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

    }

    init {
        aliasFunctionNameMap = functionMap.values.flatMap { l -> l.asIterable() }.flatMap { func -> func.alias.map { Pair(it, func.funcName) }.asIterable() }.toMap()
    }
}