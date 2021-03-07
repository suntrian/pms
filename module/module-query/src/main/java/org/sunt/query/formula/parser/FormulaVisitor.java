// Generated from D:/projects/pms/module/module-formula/src/main/java/org/sunt/formula/parser\Formula.g4 by ANTLR 4.9.1
package org.sunt.query.formula.parser;
import org.antlr.v4.runtime.tree.ParseTreeVisitor;

/**
 * This interface defines a complete generic visitor for a parse tree produced
 * by {@link FormulaParser}.
 *
 * @param <T> The return type of the visit operation. Use {@link Void} for
 * operations with no return type.
 */
public interface FormulaVisitor<T> extends ParseTreeVisitor<T> {
	/**
	 * Visit a parse tree produced by {@link FormulaParser#formula}.
	 * @param ctx the parse tree
	 * @return the visitor result
	 */
	T visitFormula(FormulaParser.FormulaContext ctx);
	/**
	 * Visit a parse tree produced by the {@code constantExpression}
	 * labeled alternative in {@link FormulaParser#statement}.
	 * @param ctx the parse tree
	 * @return the visitor result
	 */
	T visitConstantExpression(FormulaParser.ConstantExpressionContext ctx);
	/**
	 * Visit a parse tree produced by the {@code inPredicate}
	 * labeled alternative in {@link FormulaParser#statement}.
	 * @param ctx the parse tree
	 * @return the visitor result
	 */
	T visitInPredicate(FormulaParser.InPredicateContext ctx);
	/**
	 * Visit a parse tree produced by the {@code mathExpression}
	 * labeled alternative in {@link FormulaParser#statement}.
	 * @param ctx the parse tree
	 * @return the visitor result
	 */
	T visitMathExpression(FormulaParser.MathExpressionContext ctx);
	/**
	 * Visit a parse tree produced by the {@code likePredicate}
	 * labeled alternative in {@link FormulaParser#statement}.
	 * @param ctx the parse tree
	 * @return the visitor result
	 */
	T visitLikePredicate(FormulaParser.LikePredicateContext ctx);
	/**
	 * Visit a parse tree produced by the {@code functionExpression}
	 * labeled alternative in {@link FormulaParser#statement}.
	 * @param ctx the parse tree
	 * @return the visitor result
	 */
	T visitFunctionExpression(FormulaParser.FunctionExpressionContext ctx);
	/**
     * Visit a parse tree produced by the {@code parenthesesExpression}
     * labeled alternative in {@link FormulaParser#statement}.
     * @param ctx the parse tree
     * @return the visitor result
     */
    T visitParenthesesExpression(FormulaParser.ParenthesesExpressionContext ctx);

    /**
     * Visit a parse tree produced by the {@code squareExpression}
     * labeled alternative in {@link FormulaParser#statement}.
     *
     * @param ctx the parse tree
     * @return the visitor result
     */
    T visitSquareExpression(FormulaParser.SquareExpressionContext ctx);

    /**
     * Visit a parse tree produced by the {@code comparePredicate}
     * labeled alternative in {@link FormulaParser#statement}.
     *
     * @param ctx the parse tree
     * @return the visitor result
     */
    T visitComparePredicate(FormulaParser.ComparePredicateContext ctx);

    /**
     * Visit a parse tree produced by the {@code ifnullExpression}
     * labeled alternative in {@link FormulaParser#statement}.
     *
     * @param ctx the parse tree
     * @return the visitor result
     */
    T visitIfnullExpression(FormulaParser.IfnullExpressionContext ctx);

    /**
     * Visit a parse tree produced by the {@code caseExpression}
     * labeled alternative in {@link FormulaParser#statement}.
     *
     * @param ctx the parse tree
     * @return the visitor result
     */
    T visitCaseExpression(FormulaParser.CaseExpressionContext ctx);

    /**
     * Visit a parse tree produced by the {@code columnExpression}
     * labeled alternative in {@link FormulaParser#statement}.
     *
     * @param ctx the parse tree
     * @return the visitor result
     */
    T visitColumnExpression(FormulaParser.ColumnExpressionContext ctx);

    /**
     * Visit a parse tree produced by the {@code notPredicate}
     * labeled alternative in {@link FormulaParser#statement}.
     *
     * @param ctx the parse tree
     * @return the visitor result
     */
	T visitNotPredicate(FormulaParser.NotPredicateContext ctx);
	/**
	 * Visit a parse tree produced by the {@code logicalPredicate}
	 * labeled alternative in {@link FormulaParser#statement}.
	 * @param ctx the parse tree
	 * @return the visitor result
	 */
	T visitLogicalPredicate(FormulaParser.LogicalPredicateContext ctx);
	/**
	 * Visit a parse tree produced by the {@code ifExpression}
     * labeled alternative in {@link FormulaParser#statement}.
     * @param ctx the parse tree
     * @return the visitor result
     */
    T visitIfExpression(FormulaParser.IfExpressionContext ctx);

    /**
     * Visit a parse tree produced by {@link FormulaParser#functionStatement}.
     *
     * @param ctx the parse tree
     * @return the visitor result
     */
    T visitFunctionStatement(FormulaParser.FunctionStatementContext ctx);

    /**
     * Visit a parse tree produced by {@link FormulaParser#functionParams}.
     *
     * @param ctx the parse tree
     * @return the visitor result
     */
    T visitFunctionParams(FormulaParser.FunctionParamsContext ctx);

    /**
     * Visit a parse tree produced by {@link FormulaParser#functionParam}.
     *
     * @param ctx the parse tree
     * @return the visitor result
     */
    T visitFunctionParam(FormulaParser.FunctionParamContext ctx);

    /**
     * Visit a parse tree produced by {@link FormulaParser#statements}.
     *
     * @param ctx the parse tree
     * @return the visitor result
     */
    T visitStatements(FormulaParser.StatementsContext ctx);

    /**
     * Visit a parse tree produced by {@link FormulaParser#caseStatement}.
     *
     * @param ctx the parse tree
     * @return the visitor result
     */
    T visitCaseStatement(FormulaParser.CaseStatementContext ctx);

    /**
     * Visit a parse tree produced by {@link FormulaParser#ifSpecial}.
     *
     * @param ctx the parse tree
     * @return the visitor result
     */
    T visitIfSpecial(FormulaParser.IfSpecialContext ctx);

    /**
     * Visit a parse tree produced by {@link FormulaParser#ifFunction}.
	 * @param ctx the parse tree
	 * @return the visitor result
	 */
	T visitIfFunction(FormulaParser.IfFunctionContext ctx);
	/**
	 * Visit a parse tree produced by {@link FormulaParser#ifStatement}.
	 * @param ctx the parse tree
	 * @return the visitor result
	 */
	T visitIfStatement(FormulaParser.IfStatementContext ctx);
	/**
	 * Visit a parse tree produced by {@link FormulaParser#elseIfStatement}.
	 * @param ctx the parse tree
	 * @return the visitor result
	 */
	T visitElseIfStatement(FormulaParser.ElseIfStatementContext ctx);
	/**
	 * Visit a parse tree produced by {@link FormulaParser#elseStatement}.
	 * @param ctx the parse tree
	 * @return the visitor result
	 */
	T visitElseStatement(FormulaParser.ElseStatementContext ctx);
	/**
	 * Visit a parse tree produced by {@link FormulaParser#predictStatement}.
	 * @param ctx the parse tree
	 * @return the visitor result
	 */
	T visitPredictStatement(FormulaParser.PredictStatementContext ctx);
	/**
	 * Visit a parse tree produced by {@link FormulaParser#constant}.
	 * @param ctx the parse tree
	 * @return the visitor result
	 */
	T visitConstant(FormulaParser.ConstantContext ctx);
	/**
	 * Visit a parse tree produced by the {@code columnId}
     * labeled alternative in {@link FormulaParser#column}.
     * @param ctx the parse tree
     * @return the visitor result
     */
    T visitColumnId(FormulaParser.ColumnIdContext ctx);

    /**
     * Visit a parse tree produced by the {@code columnName}
     * labeled alternative in {@link FormulaParser#column}.
     *
     * @param ctx the parse tree
     * @return the visitor result
     */
    T visitColumnName(FormulaParser.ColumnNameContext ctx);

    /**
     * Visit a parse tree produced by the {@code columnIdentity}
     * labeled alternative in {@link FormulaParser#column}.
     *
     * @param ctx the parse tree
     * @return the visitor result
     */
    T visitColumnIdentity(FormulaParser.ColumnIdentityContext ctx);
	/**
	 * Visit a parse tree produced by {@link FormulaParser#identity}.
	 * @param ctx the parse tree
	 * @return the visitor result
	 */
	T visitIdentity(FormulaParser.IdentityContext ctx);
}