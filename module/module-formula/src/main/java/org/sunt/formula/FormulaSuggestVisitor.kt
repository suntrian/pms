package org.sunt.formula

import org.sunt.formula.define.SqlDialect
import org.sunt.formula.function.StatementInfo
import org.sunt.formula.parser.FormulaParser

class FormulaSuggestVisitor(
    product: SqlDialect,
    columnInterface: ColumnInterface
) : AbstractFormulaVisitor(product, columnInterface) {


    override fun visitFunctionExpression(ctx: FormulaParser.FunctionExpressionContext): StatementInfo {
        TODO("Not yet implemented")
    }

    override fun visitMathExpression(ctx: FormulaParser.MathExpressionContext): StatementInfo {
        TODO("Not yet implemented")
    }

    override fun visitComparePredicate(ctx: FormulaParser.ComparePredicateContext): StatementInfo {
        TODO("Not yet implemented")
    }

    override fun visitLogicalPredicate(ctx: FormulaParser.LogicalPredicateContext): StatementInfo {
        TODO("Not yet implemented")
    }

    override fun visitIfExpression(ctx: FormulaParser.IfExpressionContext): StatementInfo {
        TODO("Not yet implemented")
    }

    override fun visitCaseExpression(ctx: FormulaParser.CaseExpressionContext): StatementInfo {
        TODO("Not yet implemented")
    }

    override fun visitInPredicate(ctx: FormulaParser.InPredicateContext): StatementInfo {
        TODO("Not yet implemented")
    }

    override fun visitParenthesesExpression(ctx: FormulaParser.ParenthesesExpressionContext): StatementInfo {
        TODO("Not yet implemented")
    }

    override fun visitNotPredicate(ctx: FormulaParser.NotPredicateContext): StatementInfo {
        TODO("Not yet implemented")
    }

    override fun visitLikePredicate(ctx: FormulaParser.LikePredicateContext): StatementInfo {
        TODO("Not yet implemented")
    }

    override fun visitSquareExpression(ctx: FormulaParser.SquareExpressionContext): StatementInfo {
        TODO("Not yet implemented")
    }

    override fun visitIfnullExpression(ctx: FormulaParser.IfnullExpressionContext): StatementInfo {
        TODO("Not yet implemented")
    }

}