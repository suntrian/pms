// Generated from C:/Users/suntr/Project/pms/module/module-formula/src/main/java/org/sunt/formula/function/parser\FunctionParser.g4 by ANTLR 4.8
package org.sunt.formula.function.parser;

import org.antlr.v4.runtime.tree.ParseTreeListener;

/**
 * This interface defines a complete listener for a parse tree produced by
 * {@link FunctionParser}.
 */
public interface FunctionParserListener extends ParseTreeListener {
    /**
     * Enter a parse tree produced by {@link FunctionParser#root}.
     *
     * @param ctx the parse tree
     */
    void enterRoot(FunctionParser.RootContext ctx);

    /**
     * Exit a parse tree produced by {@link FunctionParser#root}.
     *
     * @param ctx the parse tree
     */
    void exitRoot(FunctionParser.RootContext ctx);

    /**
     * Enter a parse tree produced by {@link FunctionParser#category}.
     *
     * @param ctx the parse tree
     */
    void enterCategory(FunctionParser.CategoryContext ctx);

    /**
     * Exit a parse tree produced by {@link FunctionParser#category}.
     *
     * @param ctx the parse tree
     */
    void exitCategory(FunctionParser.CategoryContext ctx);

    /**
     * Enter a parse tree produced by {@link FunctionParser#functionItem}.
     *
     * @param ctx the parse tree
     */
    void enterFunctionItem(FunctionParser.FunctionItemContext ctx);

    /**
     * Exit a parse tree produced by {@link FunctionParser#functionItem}.
     *
     * @param ctx the parse tree
     */
    void exitFunctionItem(FunctionParser.FunctionItemContext ctx);

    /**
     * Enter a parse tree produced by {@link FunctionParser#functionAlias}.
     *
     * @param ctx the parse tree
     */
    void enterFunctionAlias(FunctionParser.FunctionAliasContext ctx);

    /**
     * Exit a parse tree produced by {@link FunctionParser#functionAlias}.
     *
     * @param ctx the parse tree
     */
    void exitFunctionAlias(FunctionParser.FunctionAliasContext ctx);

    /**
     * Enter a parse tree produced by {@link FunctionParser#funcDefine}.
     *
     * @param ctx the parse tree
     */
    void enterFuncDefine(FunctionParser.FuncDefineContext ctx);

    /**
     * Exit a parse tree produced by {@link FunctionParser#funcDefine}.
     *
     * @param ctx the parse tree
     */
    void exitFuncDefine(FunctionParser.FuncDefineContext ctx);

    /**
     * Enter a parse tree produced by {@link FunctionParser#funcImplement}.
     *
     * @param ctx the parse tree
     */
    void enterFuncImplement(FunctionParser.FuncImplementContext ctx);

    /**
     * Exit a parse tree produced by {@link FunctionParser#funcImplement}.
     *
     * @param ctx the parse tree
     */
    void exitFuncImplement(FunctionParser.FuncImplementContext ctx);

    /**
     * Enter a parse tree produced by {@link FunctionParser#funcImplementBody}.
     *
     * @param ctx the parse tree
     */
    void enterFuncImplementBody(FunctionParser.FuncImplementBodyContext ctx);

    /**
     * Exit a parse tree produced by {@link FunctionParser#funcImplementBody}.
     *
     * @param ctx the parse tree
     */
    void exitFuncImplementBody(FunctionParser.FuncImplementBodyContext ctx);

    /**
     * Enter a parse tree produced by {@link FunctionParser#funcArgs}.
     *
     * @param ctx the parse tree
     */
    void enterFuncArgs(FunctionParser.FuncArgsContext ctx);

    /**
     * Exit a parse tree produced by {@link FunctionParser#funcArgs}.
     *
     * @param ctx the parse tree
     */
    void exitFuncArgs(FunctionParser.FuncArgsContext ctx);

    /**
     * Enter a parse tree produced by {@link FunctionParser#funcArg}.
     *
     * @param ctx the parse tree
     */
    void enterFuncArg(FunctionParser.FuncArgContext ctx);

    /**
     * Exit a parse tree produced by {@link FunctionParser#funcArg}.
     *
     * @param ctx the parse tree
     */
    void exitFuncArg(FunctionParser.FuncArgContext ctx);

    /**
     * Enter a parse tree produced by {@link FunctionParser#argName}.
     *
     * @param ctx the parse tree
     */
    void enterArgName(FunctionParser.ArgNameContext ctx);

    /**
     * Exit a parse tree produced by {@link FunctionParser#argName}.
     *
     * @param ctx the parse tree
     */
    void exitArgName(FunctionParser.ArgNameContext ctx);

    /**
     * Enter a parse tree produced by {@link FunctionParser#argRef}.
     *
     * @param ctx the parse tree
     */
    void enterArgRef(FunctionParser.ArgRefContext ctx);

    /**
     * Exit a parse tree produced by {@link FunctionParser#argRef}.
     *
     * @param ctx the parse tree
     */
    void exitArgRef(FunctionParser.ArgRefContext ctx);

    /**
     * Enter a parse tree produced by {@link FunctionParser#enumerations}.
     *
     * @param ctx the parse tree
     */
    void enterEnumerations(FunctionParser.EnumerationsContext ctx);

    /**
     * Exit a parse tree produced by {@link FunctionParser#enumerations}.
     *
     * @param ctx the parse tree
     */
    void exitEnumerations(FunctionParser.EnumerationsContext ctx);

    /**
     * Enter a parse tree produced by {@link FunctionParser#allSymbol}.
     *
     * @param ctx the parse tree
     */
    void enterAllSymbol(FunctionParser.AllSymbolContext ctx);

    /**
     * Exit a parse tree produced by {@link FunctionParser#allSymbol}.
     *
     * @param ctx the parse tree
     */
    void exitAllSymbol(FunctionParser.AllSymbolContext ctx);

    /**
     * Enter a parse tree produced by {@link FunctionParser#dataType}.
     *
     * @param ctx the parse tree
     */
    void enterDataType(FunctionParser.DataTypeContext ctx);

    /**
     * Exit a parse tree produced by {@link FunctionParser#dataType}.
     *
     * @param ctx the parse tree
     */
    void exitDataType(FunctionParser.DataTypeContext ctx);
}