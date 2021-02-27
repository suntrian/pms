package org.sunt.formula

import org.antlr.v4.runtime.ParserRuleContext
import org.slf4j.LoggerFactory
import org.sunt.formula.define.DataType
import org.sunt.formula.define.IColumn
import org.sunt.formula.define.SqlDialect
import org.sunt.formula.exception.DataTypeMismatchException
import org.sunt.formula.exception.ParamsSizeMismatchException
import org.sunt.formula.exception.WillNeverHappenException
import org.sunt.formula.function.FunctionDefinition
import org.sunt.formula.function.FunctionDefinitionParser.loadFunctions
import org.sunt.formula.function.TokenItem
import org.sunt.formula.parser.FormulaBaseVisitor
import org.sunt.formula.parser.FormulaParser.*
import org.sunt.formula.suggestion.TokenStatus

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

    protected fun visitStatement(ctx: StatementContext?): StatementInfo {
        return when (ctx) {
            is FunctionExpressionContext -> visitFunctionExpression(ctx)
            is ConstantExpressionContext -> visitConstantExpression(ctx)
            is ColumnExpressionContext -> visitColumnExpression(ctx)
            is MathExpressionContext -> visitMathExpression(ctx)
            is ComparePredicateContext -> visitComparePredicate(ctx)
            is LogicalPredicateContext -> visitLogicalPredicate(ctx)
            is IfExpressionContext -> visitIfExpression(ctx)
            is CaseExpressionContext -> visitCaseExpression(ctx)
            is InPredicateContext -> visitInPredicate(ctx)
            is ParenthesesExpressionContext -> visitParenthesesExpression(ctx)
            is NotPredicateContext -> visitNotPredicate(ctx)
            is LikePredicateContext -> visitLikePredicate(ctx)
            is SquareExpressionContext -> visitSquareExpression(ctx)
            is IfnullExpressionContext -> visitIfnullExpression(ctx)
            else -> StatementInfo(ctx)
        }
    }

    abstract override fun visitFunctionExpression(ctx: FunctionExpressionContext): StatementInfo
    abstract override fun visitMathExpression(ctx: MathExpressionContext): StatementInfo
    abstract override fun visitComparePredicate(ctx: ComparePredicateContext): StatementInfo
    abstract override fun visitLogicalPredicate(ctx: LogicalPredicateContext): StatementInfo
    abstract override fun visitIfExpression(ctx: IfExpressionContext): StatementInfo
    abstract override fun visitCaseExpression(ctx: CaseExpressionContext): StatementInfo
    abstract override fun visitInPredicate(ctx: InPredicateContext): StatementInfo
    abstract override fun visitParenthesesExpression(ctx: ParenthesesExpressionContext): StatementInfo
    abstract override fun visitNotPredicate(ctx: NotPredicateContext): StatementInfo
    abstract override fun visitLikePredicate(ctx: LikePredicateContext): StatementInfo
    abstract override fun visitSquareExpression(ctx: SquareExpressionContext): StatementInfo
    abstract override fun visitIfnullExpression(ctx: IfnullExpressionContext): StatementInfo

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
        with(stmt) {
            when {
                ctx.BOOL() != null -> dataType = DataType.BOOLEAN
                ctx.INTEGER() != null -> dataType = DataType.INTEGER
                ctx.FLOAT() != null -> dataType = DataType.DECIMAL
                ctx.STRING() != null -> dataType = DataType.STRING
                ctx.NULL() != null -> dataType = DataType.NONE
            }
            expression = ctx.text
            token = TokenItem.CONSTANT(stmt.expression)
            status = TokenStatus.NORMAL
        }


        return stmt
    }

    override fun visitColumnId(ctx: ColumnIdContext): StatementInfo {
        return getColumnStmt(ctx, columnInterface.getColumnById(ctx.text.substring(1)))
    }

    override fun visitColumnName(ctx: ColumnNameContext): StatementInfo {
        return getColumnStmt(ctx, columnInterface.getColumnByName(ctx.text.trim('`')))
    }

    override fun visitIdentity(ctx: IdentityContext): StatementInfo {
        val identity = ctx.text
        var funcDefines: List<FunctionDefinition>? = emptyList()
        var column: IColumn? = null
        val stmt = StatementInfo(ctx)
        with(stmt) {
            if (functionMap[identity]?.also { funcDefines = it } != null) {
                token = TokenItem.FUNCTION(identity)
                dataType = funcDefines!![0].dataType
                expression = identity
                status = TokenStatus.EXPECTED
                payload = funcDefines!!
            } else if (aliasFunctionNameMap[identity]?.also { funcDefines = functionMap[it] } != null) {
                token = TokenItem.FUNCTION(identity)
                dataType = funcDefines!![0].dataType
                expression = identity
                status = TokenStatus.EXPECTED
                payload = funcDefines!!
            } else if (currentReservedIdentities.contains(identity.toUpperCase())) {
                token = TokenItem.RESERVED(identity)
                dataType = DataType.NONE
                expression = identity
                status = TokenStatus.NORMAL
            } else if (columnInterface.getColumnByName(identity)?.also { column = it } != null) {
                return getColumnStmt(ctx, column)
            } else {
                status = TokenStatus.UNKNOWN
                expression = ctx.text
                dataType = DataType.NONE
            }
        }
        return stmt
    }

    private fun getColumnStmt(ctx: ParserRuleContext, column: IColumn?): StatementInfo {
        val colStmt = StatementInfo(ctx)
        with(colStmt) {
            token = TokenItem.COLUMN(ctx.text)
            if (column == null) {
                status = TokenStatus.UNKNOWN
                expression = ctx.text
                dataType = DataType.NONE
            } else {
                status = TokenStatus.NORMAL
                dataType = column.dataType
                expression = column.expression
                payload = column
            }
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
            EQUAL to " = ",
            NOT_EQUAL to " != "
        )
    }

}