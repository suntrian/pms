package org.sunt.formula

import org.sunt.formula.define.DataType
import org.sunt.formula.define.IColumn
import org.sunt.formula.define.SqlProduct
import org.sunt.formula.parser.FormulaParser.*
import java.util.function.Function

class FormulaToSqlVisitor(product: SqlProduct, getColumnById: Function<String?, IColumn?>, getColumnByName: Function<String?, IColumn?>) : AbstractFormulaVisitor(product, getColumnById, getColumnByName) {

    override fun visitFormula(ctx: FormulaContext): StatementInfo {
        return visitStatement(ctx.statement())
    }

    private fun visitStatement(ctx: StatementContext?): StatementInfo {
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
            else -> StatementInfo(ctx)
        }
    }

    override fun visitParenthesesExpression(ctx: ParenthesesExpressionContext): StatementInfo {
        return visitStatement(ctx.statement())
    }

    override fun visitConstantExpression(ctx: ConstantExpressionContext): StatementInfo {
        return super.visitConstantExpression(ctx)!!
    }

    override fun visitComparePredicate(ctx: ComparePredicateContext): StatementInfo {
        return super.visitComparePredicate(ctx)!!
    }

    override fun visitInPredicate(ctx: InPredicateContext): StatementInfo {
        return super.visitInPredicate(ctx)!!
    }

    override fun visitCaseExpression(ctx: CaseExpressionContext): StatementInfo {
        return super.visitCaseExpression(ctx)!!
    }

    override fun visitMathExpression(ctx: MathExpressionContext): StatementInfo {
        return super.visitMathExpression(ctx)!!
    }

    override fun visitColumnExpression(ctx: ColumnExpressionContext): StatementInfo {
        return super.visitColumnExpression(ctx)!!
    }

    override fun visitNotPredicate(ctx: NotPredicateContext): StatementInfo {
        val pred = visitStatement(ctx.statement())
        checkDataType(pred, DataType.BOOLEAN)
        val stmt = StatementInfo(ctx)
        stmt.expression = " NOT " + pred.expression
        stmt.dataType = DataType.BOOLEAN
        return stmt
    }

    override fun visitLogicalPredicate(ctx: LogicalPredicateContext): StatementInfo {
        if (ctx.statement().size != 2) {
            throw java.lang.IllegalStateException("操作数个数不符")
        }
        val left = visitStatement(ctx.statement(0))
        checkDataType(left, DataType.BOOLEAN)
        val right = visitStatement(ctx.statement(1))
        checkDataType(right, DataType.BOOLEAN)
        val stmt = StatementInfo(ctx)
        stmt.dataType = DataType.BOOLEAN
        stmt.status = maxOf(left.status, right.status, Comparator.comparingInt { it.privilege })
        stmt.expression = left.expression + ctx.logicalOperator().text + right.expression
        return stmt
    }

    override fun visitIfExpression(ctx: IfExpressionContext): StatementInfo {
        return super.visitIfExpression(ctx)!!
    }

    override fun visitLikePredicate(ctx: LikePredicateContext): StatementInfo {
        val left = visitStatement(ctx.statement())
        val not = ctx.NOT() != null
        val right = ctx.STRING().text;
        val likeStmt = StatementInfo(ctx)
        likeStmt.expression = left.expression + if (not) {
            " NOT"
        } else {
            ""
        } + " LIKE " + right
        likeStmt.status = left.status
        likeStmt.dataType = DataType.BOOLEAN
        return likeStmt
    }

    override fun visitFunctionExpression(ctx: FunctionExpressionContext): StatementInfo {
        return visitFunctionStatement(ctx.functionStatement())
    }

    override fun visitFunctionStatement(ctx: FunctionStatementContext): StatementInfo {
        val funcName = ctx.IDENTITY().text
        val params = visitFunctionParams(ctx.functionParams())
        val funcStmt = StatementInfo(ctx)
        funcStmt.status = params.status
        funcStmt.dataType = DataType.NONE
        funcStmt.expression = ""
        return funcStmt
    }

    override fun visitFunctionParams(ctx: FunctionParamsContext): StatementInfo {
        val stmts: ArrayList<StatementInfo> = ArrayList(ctx.statement().size)
        for (statementContext in ctx.statement()) {
            stmts.add(visitStatement(statementContext))
        }
        val stmt = StatementInfo(ctx)
        stmt.expression = stmts.joinToString(",") { it.expression }
        stmt.status = stmts.map { it.status }.maxByOrNull { it.privilege }!!
        return stmt
    }

    override fun visitCaseStatement(ctx: CaseStatementContext): StatementInfo {
        return super.visitCaseStatement(ctx)!!
    }

    override fun visitIfSpecial(ctx: IfSpecialContext): StatementInfo {
        return when {
            ctx.ifFunction() != null -> visitIfFunction(ctx.ifFunction())
            ctx.ifStatement() != null -> visitIfStatement(ctx.ifStatement())
            else -> throw IllegalStateException("Not Expected Here")
        }
    }

    override fun visitIfFunction(ctx: IfFunctionContext): StatementInfo {
        return super.visitIfFunction(ctx)!!
    }

    override fun visitIfStatement(ctx: IfStatementContext): StatementInfo {
        return super.visitIfStatement(ctx)!!
    }

    override fun visitElseIfStatement(ctx: ElseIfStatementContext): StatementInfo {
        return super.visitElseIfStatement(ctx)!!
    }

    override fun visitElseStatement(ctx: ElseStatementContext): StatementInfo {
        return super.visitElseStatement(ctx)!!
    }

    override fun visitPredictStatement(ctx: PredictStatementContext): StatementInfo {
        return super.visitPredictStatement(ctx)!!
    }

    override fun visitColumnId(ctx: ColumnIdContext): StatementInfo {
        return super.visitColumnId(ctx)!!
    }

    override fun visitColumnName(ctx: ColumnNameContext): StatementInfo {
        return super.visitColumnName(ctx)!!
    }

    override fun visitIdentity(ctx: IdentityContext): StatementInfo {
        return super.visitIdentity(ctx)!!
    }

    override fun visitComparisonOperator(ctx: ComparisonOperatorContext): StatementInfo {
        return super.visitComparisonOperator(ctx)!!
    }

    override fun visitLogicalOperator(ctx: LogicalOperatorContext): StatementInfo {
        return super.visitLogicalOperator(ctx)!!
    }
}