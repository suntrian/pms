// Generated from D:/projects/pms/module/module-query/src/main/java/org/sunt/query/parser\Keyword.g4 by ANTLR 4.9.1

package org.sunt.query.parser;

import org.antlr.v4.runtime.tree.ParseTreeVisitor;

/**
 * This interface defines a complete generic visitor for a parse tree produced
 * by {@link KeywordParser}.
 *
 * @param <T> The return type of the visit operation. Use {@link Void} for
 * operations with no return type.
 */
public interface KeywordVisitor<T> extends ParseTreeVisitor<T> {
	/**
	 * Visit a parse tree produced by {@link KeywordParser#keywords}.
	 * @param ctx the parse tree
	 * @return the visitor result
	 */
	T visitKeywords(KeywordParser.KeywordsContext ctx);
	/**
	 * Visit a parse tree produced by {@link KeywordParser#keyword}.
	 * @param ctx the parse tree
	 * @return the visitor result
	 */
	T visitKeyword(KeywordParser.KeywordContext ctx);
	/**
	 * Visit a parse tree produced by {@link KeywordParser#segment}.
	 * @param ctx the parse tree
	 * @return the visitor result
	 */
	T visitSegment(KeywordParser.SegmentContext ctx);
	/**
	 * Visit a parse tree produced by {@link KeywordParser#aggregate}.
	 * @param ctx the parse tree
	 * @return the visitor result
	 */
	T visitAggregate(KeywordParser.AggregateContext ctx);
	/**
	 * Visit a parse tree produced by {@link KeywordParser#group}.
	 * @param ctx the parse tree
	 * @return the visitor result
	 */
	T visitGroup(KeywordParser.GroupContext ctx);
	/**
	 * Visit a parse tree produced by the {@code mostFilter}
	 * labeled alternative in {@link KeywordParser#filter}.
	 * @param ctx the parse tree
	 * @return the visitor result
	 */
	T visitMostFilter(KeywordParser.MostFilterContext ctx);
	/**
	 * Visit a parse tree produced by the {@code compareFilter}
	 * labeled alternative in {@link KeywordParser#filter}.
	 * @param ctx the parse tree
	 * @return the visitor result
	 */
	T visitCompareFilter(KeywordParser.CompareFilterContext ctx);
	/**
	 * Visit a parse tree produced by the {@code betweenFilter}
	 * labeled alternative in {@link KeywordParser#filter}.
	 * @param ctx the parse tree
	 * @return the visitor result
	 */
	T visitBetweenFilter(KeywordParser.BetweenFilterContext ctx);
	/**
	 * Visit a parse tree produced by the {@code stringFilter}
	 * labeled alternative in {@link KeywordParser#filter}.
	 * @param ctx the parse tree
	 * @return the visitor result
	 */
	T visitStringFilter(KeywordParser.StringFilterContext ctx);
	/**
	 * Visit a parse tree produced by the {@code lastDateFilter}
	 * labeled alternative in {@link KeywordParser#filter}.
	 * @param ctx the parse tree
	 * @return the visitor result
	 */
	T visitLastDateFilter(KeywordParser.LastDateFilterContext ctx);
	/**
	 * Visit a parse tree produced by the {@code compareDateFilter}
	 * labeled alternative in {@link KeywordParser#filter}.
	 * @param ctx the parse tree
	 * @return the visitor result
	 */
	T visitCompareDateFilter(KeywordParser.CompareDateFilterContext ctx);
	/**
	 * Visit a parse tree produced by {@link KeywordParser#sort}.
	 * @param ctx the parse tree
	 * @return the visitor result
	 */
	T visitSort(KeywordParser.SortContext ctx);
	/**
	 * Visit a parse tree produced by {@link KeywordParser#limit}.
	 * @param ctx the parse tree
	 * @return the visitor result
	 */
	T visitLimit(KeywordParser.LimitContext ctx);
	/**
	 * Visit a parse tree produced by {@link KeywordParser#nonReserved}.
	 * @param ctx the parse tree
	 * @return the visitor result
	 */
	T visitNonReserved(KeywordParser.NonReservedContext ctx);
	/**
	 * Visit a parse tree produced by {@link KeywordParser#formula}.
	 * @param ctx the parse tree
	 * @return the visitor result
	 */
	T visitFormula(KeywordParser.FormulaContext ctx);
	/**
	 * Visit a parse tree produced by the {@code constantExpression}
	 * labeled alternative in {@link KeywordParser#statement}.
	 * @param ctx the parse tree
	 * @return the visitor result
	 */
	T visitConstantExpression(KeywordParser.ConstantExpressionContext ctx);
	/**
	 * Visit a parse tree produced by the {@code inPredicate}
	 * labeled alternative in {@link KeywordParser#statement}.
	 * @param ctx the parse tree
	 * @return the visitor result
	 */
	T visitInPredicate(KeywordParser.InPredicateContext ctx);
	/**
	 * Visit a parse tree produced by the {@code mathExpression}
	 * labeled alternative in {@link KeywordParser#statement}.
	 * @param ctx the parse tree
	 * @return the visitor result
	 */
	T visitMathExpression(KeywordParser.MathExpressionContext ctx);
	/**
	 * Visit a parse tree produced by the {@code likePredicate}
	 * labeled alternative in {@link KeywordParser#statement}.
	 * @param ctx the parse tree
	 * @return the visitor result
	 */
	T visitLikePredicate(KeywordParser.LikePredicateContext ctx);
	/**
	 * Visit a parse tree produced by the {@code functionExpression}
	 * labeled alternative in {@link KeywordParser#statement}.
	 * @param ctx the parse tree
	 * @return the visitor result
	 */
	T visitFunctionExpression(KeywordParser.FunctionExpressionContext ctx);
	/**
	 * Visit a parse tree produced by the {@code parenthesesExpression}
	 * labeled alternative in {@link KeywordParser#statement}.
	 * @param ctx the parse tree
	 * @return the visitor result
	 */
	T visitParenthesesExpression(KeywordParser.ParenthesesExpressionContext ctx);
	/**
	 * Visit a parse tree produced by the {@code squareExpression}
	 * labeled alternative in {@link KeywordParser#statement}.
	 * @param ctx the parse tree
	 * @return the visitor result
	 */
	T visitSquareExpression(KeywordParser.SquareExpressionContext ctx);
	/**
	 * Visit a parse tree produced by the {@code comparePredicate}
	 * labeled alternative in {@link KeywordParser#statement}.
	 * @param ctx the parse tree
	 * @return the visitor result
	 */
	T visitComparePredicate(KeywordParser.ComparePredicateContext ctx);
	/**
	 * Visit a parse tree produced by the {@code ifnullExpression}
	 * labeled alternative in {@link KeywordParser#statement}.
	 * @param ctx the parse tree
	 * @return the visitor result
	 */
	T visitIfnullExpression(KeywordParser.IfnullExpressionContext ctx);
	/**
	 * Visit a parse tree produced by the {@code caseExpression}
	 * labeled alternative in {@link KeywordParser#statement}.
	 * @param ctx the parse tree
	 * @return the visitor result
	 */
	T visitCaseExpression(KeywordParser.CaseExpressionContext ctx);
	/**
	 * Visit a parse tree produced by the {@code columnExpression}
	 * labeled alternative in {@link KeywordParser#statement}.
	 * @param ctx the parse tree
	 * @return the visitor result
	 */
	T visitColumnExpression(KeywordParser.ColumnExpressionContext ctx);
	/**
	 * Visit a parse tree produced by the {@code notPredicate}
	 * labeled alternative in {@link KeywordParser#statement}.
	 * @param ctx the parse tree
	 * @return the visitor result
	 */
	T visitNotPredicate(KeywordParser.NotPredicateContext ctx);
	/**
	 * Visit a parse tree produced by the {@code logicalPredicate}
	 * labeled alternative in {@link KeywordParser#statement}.
	 * @param ctx the parse tree
	 * @return the visitor result
	 */
	T visitLogicalPredicate(KeywordParser.LogicalPredicateContext ctx);
	/**
	 * Visit a parse tree produced by the {@code ifExpression}
	 * labeled alternative in {@link KeywordParser#statement}.
	 * @param ctx the parse tree
	 * @return the visitor result
	 */
	T visitIfExpression(KeywordParser.IfExpressionContext ctx);
	/**
	 * Visit a parse tree produced by {@link KeywordParser#functionStatement}.
	 * @param ctx the parse tree
	 * @return the visitor result
	 */
	T visitFunctionStatement(KeywordParser.FunctionStatementContext ctx);
	/**
	 * Visit a parse tree produced by {@link KeywordParser#functionParams}.
	 * @param ctx the parse tree
	 * @return the visitor result
	 */
	T visitFunctionParams(KeywordParser.FunctionParamsContext ctx);
	/**
	 * Visit a parse tree produced by {@link KeywordParser#functionParam}.
	 * @param ctx the parse tree
	 * @return the visitor result
	 */
	T visitFunctionParam(KeywordParser.FunctionParamContext ctx);
	/**
	 * Visit a parse tree produced by {@link KeywordParser#statements}.
	 * @param ctx the parse tree
	 * @return the visitor result
	 */
	T visitStatements(KeywordParser.StatementsContext ctx);
	/**
	 * Visit a parse tree produced by {@link KeywordParser#caseStatement}.
	 * @param ctx the parse tree
	 * @return the visitor result
	 */
	T visitCaseStatement(KeywordParser.CaseStatementContext ctx);
	/**
	 * Visit a parse tree produced by {@link KeywordParser#ifSpecial}.
	 * @param ctx the parse tree
	 * @return the visitor result
	 */
	T visitIfSpecial(KeywordParser.IfSpecialContext ctx);
	/**
	 * Visit a parse tree produced by {@link KeywordParser#ifFunction}.
	 * @param ctx the parse tree
	 * @return the visitor result
	 */
	T visitIfFunction(KeywordParser.IfFunctionContext ctx);
	/**
	 * Visit a parse tree produced by {@link KeywordParser#ifStatement}.
	 * @param ctx the parse tree
	 * @return the visitor result
	 */
	T visitIfStatement(KeywordParser.IfStatementContext ctx);
	/**
	 * Visit a parse tree produced by {@link KeywordParser#elseIfStatement}.
	 * @param ctx the parse tree
	 * @return the visitor result
	 */
	T visitElseIfStatement(KeywordParser.ElseIfStatementContext ctx);
	/**
	 * Visit a parse tree produced by {@link KeywordParser#elseStatement}.
	 * @param ctx the parse tree
	 * @return the visitor result
	 */
	T visitElseStatement(KeywordParser.ElseStatementContext ctx);
	/**
	 * Visit a parse tree produced by {@link KeywordParser#predictStatement}.
	 * @param ctx the parse tree
	 * @return the visitor result
	 */
	T visitPredictStatement(KeywordParser.PredictStatementContext ctx);
	/**
	 * Visit a parse tree produced by {@link KeywordParser#constant}.
	 * @param ctx the parse tree
	 * @return the visitor result
	 */
	T visitConstant(KeywordParser.ConstantContext ctx);
	/**
	 * Visit a parse tree produced by the {@code columnId}
	 * labeled alternative in {@link KeywordParser#column}.
	 * @param ctx the parse tree
	 * @return the visitor result
	 */
	T visitColumnId(KeywordParser.ColumnIdContext ctx);
	/**
	 * Visit a parse tree produced by the {@code columnName}
	 * labeled alternative in {@link KeywordParser#column}.
	 * @param ctx the parse tree
	 * @return the visitor result
	 */
	T visitColumnName(KeywordParser.ColumnNameContext ctx);
	/**
	 * Visit a parse tree produced by the {@code columnIdentity}
	 * labeled alternative in {@link KeywordParser#column}.
	 * @param ctx the parse tree
	 * @return the visitor result
	 */
	T visitColumnIdentity(KeywordParser.ColumnIdentityContext ctx);
	/**
	 * Visit a parse tree produced by {@link KeywordParser#identity}.
	 * @param ctx the parse tree
	 * @return the visitor result
	 */
	T visitIdentity(KeywordParser.IdentityContext ctx);
}