package org.sunt.query.formula.function.parser;
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
     * Enter a parse tree produced by {@link FunctionParser#packageDeclare}.
     *
     * @param ctx the parse tree
     */
    void enterPackageDeclare(FunctionParser.PackageDeclareContext ctx);

    /**
     * Exit a parse tree produced by {@link FunctionParser#packageDeclare}.
     *
     * @param ctx the parse tree
     */
    void exitPackageDeclare(FunctionParser.PackageDeclareContext ctx);

    /**
     * Enter a parse tree produced by {@link FunctionParser#importDeclare}.
     *
     * @param ctx the parse tree
     */
    void enterImportDeclare(FunctionParser.ImportDeclareContext ctx);

    /**
     * Exit a parse tree produced by {@link FunctionParser#importDeclare}.
     *
     * @param ctx the parse tree
     */
    void exitImportDeclare(FunctionParser.ImportDeclareContext ctx);

    /**
     * Enter a parse tree produced by {@link FunctionParser#dialectInterface}.
     *
     * @param ctx the parse tree
     */
    void enterDialectInterface(FunctionParser.DialectInterfaceContext ctx);

    /**
     * Exit a parse tree produced by {@link FunctionParser#dialectInterface}.
     *
     * @param ctx the parse tree
     */
    void exitDialectInterface(FunctionParser.DialectInterfaceContext ctx);

    /**
     * Enter a parse tree produced by {@link FunctionParser#typeAliasDelare}.
     *
     * @param ctx the parse tree
     */
    void enterTypeAliasDelare(FunctionParser.TypeAliasDelareContext ctx);

    /**
     * Exit a parse tree produced by {@link FunctionParser#typeAliasDelare}.
     *
     * @param ctx the parse tree
     */
    void exitTypeAliasDelare(FunctionParser.TypeAliasDelareContext ctx);

    /**
     * Enter a parse tree produced by {@link FunctionParser#classDeclare}.
     *
     * @param ctx the parse tree
     */
    void enterClassDeclare(FunctionParser.ClassDeclareContext ctx);

    /**
     * Exit a parse tree produced by {@link FunctionParser#classDeclare}.
     *
     * @param ctx the parse tree
     */
    void exitClassDeclare(FunctionParser.ClassDeclareContext ctx);

    /**
     * Enter a parse tree produced by {@link FunctionParser#functionDefine}.
     *
     * @param ctx the parse tree
     */
    void enterFunctionDefine(FunctionParser.FunctionDefineContext ctx);

    /**
     * Exit a parse tree produced by {@link FunctionParser#functionDefine}.
     *
     * @param ctx the parse tree
     */
    void exitFunctionDefine(FunctionParser.FunctionDefineContext ctx);

    /**
     * Enter a parse tree produced by {@link FunctionParser#functionModifierList}.
     *
     * @param ctx the parse tree
     */
    void enterFunctionModifierList(FunctionParser.FunctionModifierListContext ctx);

    /**
     * Exit a parse tree produced by {@link FunctionParser#functionModifierList}.
     *
     * @param ctx the parse tree
     */
    void exitFunctionModifierList(FunctionParser.FunctionModifierListContext ctx);

    /**
     * Enter a parse tree produced by {@link FunctionParser#annotation}.
     *
     * @param ctx the parse tree
     */
    void enterAnnotation(FunctionParser.AnnotationContext ctx);

    /**
     * Exit a parse tree produced by {@link FunctionParser#annotation}.
     *
     * @param ctx the parse tree
     */
    void exitAnnotation(FunctionParser.AnnotationContext ctx);

    /**
     * Enter a parse tree produced by {@link FunctionParser#annotationNS}.
     *
     * @param ctx the parse tree
     */
    void enterAnnotationNS(FunctionParser.AnnotationNSContext ctx);

    /**
     * Exit a parse tree produced by {@link FunctionParser#annotationNS}.
     *
     * @param ctx the parse tree
     */
    void exitAnnotationNS(FunctionParser.AnnotationNSContext ctx);

    /**
     * Enter a parse tree produced by {@link FunctionParser#functionModifier}.
     *
     * @param ctx the parse tree
     */
    void enterFunctionModifier(FunctionParser.FunctionModifierContext ctx);

    /**
     * Exit a parse tree produced by {@link FunctionParser#functionModifier}.
     *
     * @param ctx the parse tree
     */
    void exitFunctionModifier(FunctionParser.FunctionModifierContext ctx);

    /**
     * Enter a parse tree produced by {@link FunctionParser#functionImplement}.
     *
     * @param ctx the parse tree
     */
    void enterFunctionImplement(FunctionParser.FunctionImplementContext ctx);

    /**
     * Exit a parse tree produced by {@link FunctionParser#functionImplement}.
     *
     * @param ctx the parse tree
     */
    void exitFunctionImplement(FunctionParser.FunctionImplementContext ctx);

    /**
     * Enter a parse tree produced by {@link FunctionParser#functionSimpleImpl}.
     *
     * @param ctx the parse tree
     */
    void enterFunctionSimpleImpl(FunctionParser.FunctionSimpleImplContext ctx);

    /**
     * Exit a parse tree produced by {@link FunctionParser#functionSimpleImpl}.
     *
     * @param ctx the parse tree
     */
    void exitFunctionSimpleImpl(FunctionParser.FunctionSimpleImplContext ctx);

    /**
     * Enter a parse tree produced by {@link FunctionParser#functionFullImpl}.
     *
     * @param ctx the parse tree
     */
    void enterFunctionFullImpl(FunctionParser.FunctionFullImplContext ctx);

    /**
     * Exit a parse tree produced by {@link FunctionParser#functionFullImpl}.
     *
     * @param ctx the parse tree
     */
    void exitFunctionFullImpl(FunctionParser.FunctionFullImplContext ctx);

    /**
     * Enter a parse tree produced by {@link FunctionParser#typeParameters}.
     *
     * @param ctx the parse tree
     */
    void enterTypeParameters(FunctionParser.TypeParametersContext ctx);

    /**
     * Exit a parse tree produced by {@link FunctionParser#typeParameters}.
     *
     * @param ctx the parse tree
     */
    void exitTypeParameters(FunctionParser.TypeParametersContext ctx);

    /**
     * Enter a parse tree produced by {@link FunctionParser#functionParamDefines}.
     *
     * @param ctx the parse tree
     */
    void enterFunctionParamDefines(FunctionParser.FunctionParamDefinesContext ctx);

    /**
     * Exit a parse tree produced by {@link FunctionParser#functionParamDefines}.
     *
     * @param ctx the parse tree
     */
    void exitFunctionParamDefines(FunctionParser.FunctionParamDefinesContext ctx);

    /**
     * Enter a parse tree produced by {@link FunctionParser#functionParamDefine}.
     *
     * @param ctx the parse tree
     */
    void enterFunctionParamDefine(FunctionParser.FunctionParamDefineContext ctx);

    /**
     * Exit a parse tree produced by {@link FunctionParser#functionParamDefine}.
     *
     * @param ctx the parse tree
     */
    void exitFunctionParamDefine(FunctionParser.FunctionParamDefineContext ctx);

    /**
     * Enter a parse tree produced by {@link FunctionParser#functionParamModifierList}.
     *
     * @param ctx the parse tree
     */
    void enterFunctionParamModifierList(FunctionParser.FunctionParamModifierListContext ctx);

    /**
     * Exit a parse tree produced by {@link FunctionParser#functionParamModifierList}.
     *
     * @param ctx the parse tree
     */
    void exitFunctionParamModifierList(FunctionParser.FunctionParamModifierListContext ctx);

    /**
     * Enter a parse tree produced by {@link FunctionParser#functionParamModifier}.
     *
     * @param ctx the parse tree
     */
    void enterFunctionParamModifier(FunctionParser.FunctionParamModifierContext ctx);

    /**
     * Exit a parse tree produced by {@link FunctionParser#functionParamModifier}.
     *
     * @param ctx the parse tree
     */
    void exitFunctionParamModifier(FunctionParser.FunctionParamModifierContext ctx);

    /**
     * Enter a parse tree produced by {@link FunctionParser#functionParams}.
     *
     * @param ctx the parse tree
     */
    void enterFunctionParams(FunctionParser.FunctionParamsContext ctx);

    /**
     * Exit a parse tree produced by {@link FunctionParser#functionParams}.
     *
     * @param ctx the parse tree
     */
    void exitFunctionParams(FunctionParser.FunctionParamsContext ctx);

    /**
     * Enter a parse tree produced by {@link FunctionParser#functionCall}.
     *
     * @param ctx the parse tree
     */
    void enterFunctionCall(FunctionParser.FunctionCallContext ctx);

    /**
     * Exit a parse tree produced by {@link FunctionParser#functionCall}.
     *
     * @param ctx the parse tree
     */
    void exitFunctionCall(FunctionParser.FunctionCallContext ctx);

    /**
     * Enter a parse tree produced by {@link FunctionParser#expression}.
     *
     * @param ctx the parse tree
     */
    void enterExpression(FunctionParser.ExpressionContext ctx);

    /**
     * Exit a parse tree produced by {@link FunctionParser#expression}.
     *
     * @param ctx the parse tree
     */
    void exitExpression(FunctionParser.ExpressionContext ctx);

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

    /**
     * Enter a parse tree produced by {@link FunctionParser#dataTypeNull}.
     *
     * @param ctx the parse tree
     */
    void enterDataTypeNull(FunctionParser.DataTypeNullContext ctx);

    /**
     * Exit a parse tree produced by {@link FunctionParser#dataTypeNull}.
     *
     * @param ctx the parse tree
     */
    void exitDataTypeNull(FunctionParser.DataTypeNullContext ctx);

    /**
     * Enter a parse tree produced by {@link FunctionParser#identifier}.
     *
     * @param ctx the parse tree
     */
    void enterIdentifier(FunctionParser.IdentifierContext ctx);

    /**
     * Exit a parse tree produced by {@link FunctionParser#identifier}.
     *
     * @param ctx the parse tree
     */
    void exitIdentifier(FunctionParser.IdentifierContext ctx);

    /**
     * Enter a parse tree produced by {@link FunctionParser#simpleIdentifier}.
     *
     * @param ctx the parse tree
     */
    void enterSimpleIdentifier(FunctionParser.SimpleIdentifierContext ctx);

    /**
     * Exit a parse tree produced by {@link FunctionParser#simpleIdentifier}.
     *
     * @param ctx the parse tree
     */
    void exitSimpleIdentifier(FunctionParser.SimpleIdentifierContext ctx);
}