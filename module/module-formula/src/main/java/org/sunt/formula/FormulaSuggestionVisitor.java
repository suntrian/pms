package org.sunt.formula;

import org.sunt.formula.define.IColumn;
import org.sunt.formula.define.SqlProduct;
import org.sunt.formula.parser.FormulaParser;

import java.util.function.Function;

public class FormulaSuggestionVisitor extends AbstractFormulaVisitor {

    private final int cursor;

    public FormulaSuggestionVisitor(SqlProduct dialect, Function<String, IColumn> getColumnById, Function<String, IColumn> getColumnByName, int cursor) {
        super(dialect, getColumnById, getColumnByName);
        this.cursor = cursor;
    }

    @Override
    public StatementInfo visitFormula(FormulaParser.FormulaContext ctx) {
        return super.visitFormula(ctx);
    }

    @Override
    public StatementInfo visitParenthesesExpression(FormulaParser.ParenthesesExpressionContext ctx) {
        return super.visitParenthesesExpression(ctx);
    }

    @Override
    public StatementInfo visitConstantExpression(FormulaParser.ConstantExpressionContext ctx) {
        return super.visitConstantExpression(ctx);
    }

    @Override
    public StatementInfo visitComparePredicate(FormulaParser.ComparePredicateContext ctx) {
        return super.visitComparePredicate(ctx);
    }

    @Override
    public StatementInfo visitInPredicate(FormulaParser.InPredicateContext ctx) {
        return super.visitInPredicate(ctx);
    }

    @Override
    public StatementInfo visitCaseExpression(FormulaParser.CaseExpressionContext ctx) {
        return super.visitCaseExpression(ctx);
    }

    @Override
    public StatementInfo visitMathExpression(FormulaParser.MathExpressionContext ctx) {
        return super.visitMathExpression(ctx);
    }

    @Override
    public StatementInfo visitColumnExpression(FormulaParser.ColumnExpressionContext ctx) {
        return super.visitColumnExpression(ctx);
    }

    @Override
    public StatementInfo visitNotPredicate(FormulaParser.NotPredicateContext ctx) {
        return super.visitNotPredicate(ctx);
    }

    @Override
    public StatementInfo visitLogicalPredicate(FormulaParser.LogicalPredicateContext ctx) {
        return super.visitLogicalPredicate(ctx);
    }

    @Override
    public StatementInfo visitIfExpression(FormulaParser.IfExpressionContext ctx) {
        return super.visitIfExpression(ctx);
    }

    @Override
    public StatementInfo visitLikePredicate(FormulaParser.LikePredicateContext ctx) {
        return super.visitLikePredicate(ctx);
    }

    @Override
    public StatementInfo visitFunctionExpression(FormulaParser.FunctionExpressionContext ctx) {
        return super.visitFunctionExpression(ctx);
    }

    @Override
    public StatementInfo visitFunctionStatement(FormulaParser.FunctionStatementContext ctx) {
        return super.visitFunctionStatement(ctx);
    }

    @Override
    public StatementInfo visitFunctionParams(FormulaParser.FunctionParamsContext ctx) {
        return super.visitFunctionParams(ctx);
    }

    @Override
    public StatementInfo visitCaseStatement(FormulaParser.CaseStatementContext ctx) {
        return super.visitCaseStatement(ctx);
    }

    @Override
    public StatementInfo visitIfSpecial(FormulaParser.IfSpecialContext ctx) {
        return super.visitIfSpecial(ctx);
    }

    @Override
    public StatementInfo visitIfFunction(FormulaParser.IfFunctionContext ctx) {
        return super.visitIfFunction(ctx);
    }

    @Override
    public StatementInfo visitIfStatement(FormulaParser.IfStatementContext ctx) {
        return super.visitIfStatement(ctx);
    }

    @Override
    public StatementInfo visitElseIfStatement(FormulaParser.ElseIfStatementContext ctx) {
        return super.visitElseIfStatement(ctx);
    }

    @Override
    public StatementInfo visitElseStatement(FormulaParser.ElseStatementContext ctx) {
        return super.visitElseStatement(ctx);
    }

    @Override
    public StatementInfo visitPredictStatement(FormulaParser.PredictStatementContext ctx) {
        return super.visitPredictStatement(ctx);
    }

    @Override
    public StatementInfo visitColumnId(FormulaParser.ColumnIdContext ctx) {
        return super.visitColumnId(ctx);
    }

    @Override
    public StatementInfo visitColumnName(FormulaParser.ColumnNameContext ctx) {
        return super.visitColumnName(ctx);
    }

    @Override
    public StatementInfo visitIdentity(FormulaParser.IdentityContext ctx) {
        return super.visitIdentity(ctx);
    }

    @Override
    public StatementInfo visitComparisonOperator(FormulaParser.ComparisonOperatorContext ctx) {
        return super.visitComparisonOperator(ctx);
    }

    @Override
    public StatementInfo visitLogicalOperator(FormulaParser.LogicalOperatorContext ctx) {
        return super.visitLogicalOperator(ctx);
    }

}
