package org.sunt.formula

import org.antlr.v4.runtime.*
import org.antlr.v4.runtime.atn.ATNConfigSet
import org.antlr.v4.runtime.dfa.DFA
import org.antlr.v4.runtime.tree.ErrorNode
import org.antlr.v4.runtime.tree.TerminalNode
import org.slf4j.LoggerFactory
import org.sunt.formula.define.DataType
import org.sunt.formula.define.IColumn
import org.sunt.formula.define.SqlDialect
import org.sunt.formula.exception.ParamTypeMismatchException
import org.sunt.formula.exception.ParamsSizeMismatchException
import org.sunt.formula.exception.WillNeverHappenException
import org.sunt.formula.function.FunctionDefinition
import org.sunt.formula.function.FunctionDefinitionParser.loadFunctions
import org.sunt.formula.function.StatementInfo
import org.sunt.formula.function.TokenItem
import org.sunt.formula.function.TokenStatus
import org.sunt.formula.parser.FormulaBaseVisitor
import org.sunt.formula.parser.FormulaParser
import org.sunt.formula.parser.FormulaParser.*
import java.util.*
import kotlin.collections.ArrayList

internal fun TerminalNode.isValid(): Boolean = !isNotValid()

internal fun TerminalNode.isNotValid(): Boolean = this is ErrorNode

internal fun ParserRuleContext.isValid(): Boolean = this.exception == null

abstract class AbstractFormulaVisitor(
    protected val dialect: SqlDialect,
    protected val columnInterface: ColumnInterface,
    protected val rewriter: TokenStreamRewriter
) : FormulaBaseVisitor<Any?>(), ANTLRErrorListener {

    protected val functionMap: Map<String, List<FunctionDefinition>> = loadFunctions(dialect)

    protected val aliasFunctionNameMap: Map<String, String> =
        functionMap.values.flatMap { l -> l.asIterable() }
            .flatMap { func -> func.alias.map { Pair(it, func.funcName) }.asIterable() }
            .toMap(TreeMap(String.CASE_INSENSITIVE_ORDER))

    protected var currentFunctionCandidates: MutableList<FunctionDefinition> = ArrayList(0)
    protected var currentDataTypeCandidates: Set<DataType> = emptySet()
    protected var currentOptValueCandidates: Set<String> = emptySet()

    /**
     * 保存现场
     */
    protected fun <T> recordCurrent(
        currentFunctions: MutableList<FunctionDefinition>,
        currentExpectedOptions: Set<String>,
        block: () -> T
    ): T {
        val formerFunctions = this.currentFunctionCandidates
        val formerExpectedOptions = this.currentOptValueCandidates
        try {
            this.currentFunctionCandidates = currentFunctions
            this.currentOptValueCandidates = currentExpectedOptions
            return block()
        } finally {
            this.currentFunctionCandidates = formerFunctions
            this.currentOptValueCandidates = formerExpectedOptions
        }
    }

    protected fun <T> recordCurrent(
        currentDataTypes: Set<DataType>,
        currentOptions: Set<String>,
        block: () -> T
    ): T {
        val formerDataTypes = this.currentDataTypeCandidates
        val formerOptions = this.currentOptValueCandidates
        try {
            this.currentDataTypeCandidates = currentDataTypes
            this.currentOptValueCandidates = currentOptions
            return block()
        } finally {
            this.currentDataTypeCandidates = formerDataTypes
            this.currentOptValueCandidates = formerOptions
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
            else -> StatementInfo(ctx).apply {
                status = TokenStatus.ERROR
            }
        }
    }

    override fun visitFunctionExpression(ctx: FunctionExpressionContext): StatementInfo =
        visitFunctionStatement(ctx.functionStatement())

    abstract override fun visitFunctionStatement(ctx: FunctionStatementContext): StatementInfo
    abstract override fun visitMathExpression(ctx: MathExpressionContext): StatementInfo
    abstract override fun visitComparePredicate(ctx: ComparePredicateContext): StatementInfo
    abstract override fun visitLogicalPredicate(ctx: LogicalPredicateContext): StatementInfo
    override fun visitIfExpression(ctx: IfExpressionContext): StatementInfo = visitIfSpecial(ctx.ifSpecial())
    abstract override fun visitIfSpecial(ctx: IfSpecialContext): StatementInfo
    override fun visitCaseExpression(ctx: CaseExpressionContext): StatementInfo =
        visitCaseStatement(ctx.caseStatement())

    abstract override fun visitCaseStatement(ctx: CaseStatementContext): StatementInfo
    abstract override fun visitInPredicate(ctx: InPredicateContext): StatementInfo
    abstract override fun visitParenthesesExpression(ctx: ParenthesesExpressionContext): StatementInfo
    abstract override fun visitNotPredicate(ctx: NotPredicateContext): StatementInfo
    abstract override fun visitLikePredicate(ctx: LikePredicateContext): StatementInfo
    abstract override fun visitIfnullExpression(ctx: IfnullExpressionContext): StatementInfo
    override fun visitConstantExpression(ctx: ConstantExpressionContext): StatementInfo = visitConstant(ctx.constant())
    override fun visitColumnExpression(ctx: ColumnExpressionContext): StatementInfo {
        return when (val columnCtx = ctx.column()) {
            is ColumnIdContext -> visitColumnId(columnCtx)
            is ColumnNameContext -> visitColumnName(columnCtx)
            is ColumnIdentityContext -> visitIdentity(columnCtx.identity())
            else -> throw WillNeverHappenException("Not Expected Here")
        }
    }

    override fun visitStatements(ctx: StatementsContext): List<StatementInfo> =
        ctx.statement().map { visitStatement(it) }

    override fun visitSquareExpression(ctx: SquareExpressionContext): StatementInfo {
        val statements = ctx.statements()?.let { visitStatements(it) } ?: emptyList()
        val result = StatementInfo(ctx)
        with(result) {
            expression = statements.joinToString(", ") { it.expression }
            status = statements.map { it.status }.maxByOrNull { it.privilege } ?: TokenStatus.NORMAL
            val commonDataType = DataType.commonType(statements.map { it.dataType })
            dataType = DataType.of("List<${commonDataType}>")
        }
        return result
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
            } else if (currentOptValueCandidates.contains(identity.toUpperCase())) {
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
                rewriter.replace(ctx.start, ctx.stop, convertColumnExpression(column))
                status = TokenStatus.NORMAL
                dataType = column.dataType
                expression = column.expression
                payload = column
            }
        }
        return colStmt
    }

    protected fun convertColumnExpression(column: IColumn): String = "${column.name}@(${column.id})"

    protected val syntaxErrors: MutableList<SyntaxError> = mutableListOf()

    override fun syntaxError(
        recognizer: Recognizer<*, *>,
        offendingSymbol: Any?,
        line: Int,
        charPositionInLine: Int,
        msg: String?,
        e: RecognitionException?
    ) {
        syntaxErrors.add(
            SyntaxError(
                recognizer,
                offendingSymbol as Token,
                line,
                charPositionInLine,
                msg,
                e?.expectedTokens ?: (recognizer as FormulaParser).expectedTokens,
                e
            )
        )
    }

    override fun reportAmbiguity(
        recognizer: Parser?,
        dfa: DFA?,
        startIndex: Int,
        stopIndex: Int,
        exact: Boolean,
        ambigAlts: BitSet?,
        configs: ATNConfigSet?
    ) {
        log.error("reportAmbiguity:{}", startIndex)
    }

    override fun reportAttemptingFullContext(
        recognizer: Parser?,
        dfa: DFA?,
        startIndex: Int,
        stopIndex: Int,
        conflictingAlts: BitSet?,
        configs: ATNConfigSet?
    ) {
        log.error("reportAttemptingFullContext:{}", startIndex);
    }

    override fun reportContextSensitivity(
        recognizer: Parser?,
        dfa: DFA?,
        startIndex: Int,
        stopIndex: Int,
        prediction: Int,
        configs: ATNConfigSet?
    ) {
        log.error("reportContextSensitivity:{}", startIndex);
    }


    companion object {

        val log = LoggerFactory.getLogger(AbstractFormulaVisitor::class.java)!!

        @JvmStatic
        fun checkDataType(stmt: StatementInfo, vararg expectedType: DataType): Boolean {
            if (expectedType.any { it == stmt.dataType }) {
                return true
            }
            throw ParamTypeMismatchException(stmt.expression, expectedType[0], stmt.dataType)
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