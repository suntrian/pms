package org.sunt.formula.function;

import org.antlr.v4.runtime.BufferedTokenStream;
import org.antlr.v4.runtime.Token;
import org.antlr.v4.runtime.tree.TerminalNode;
import org.sunt.formula.define.DataType;
import org.sunt.formula.function.parser.FunctionBaseListener;
import org.sunt.formula.function.parser.FunctionParser;

import java.util.*;
import java.util.stream.Collectors;

public class FunctionDefineListener extends FunctionBaseListener {

    private final BufferedTokenStream tokens;

    private final Map<String, Set<String>> aliasMap = new TreeMap<>(String.CASE_INSENSITIVE_ORDER);

    private final List<FunctionDefine> functions = new LinkedList<>();

    private final Set<String> current_categories = new HashSet<>();

    private FunctionDefine current_function;

    private final List<FunctionDefine.FunctionArgDefine> current_function_args = new LinkedList<>();

    public FunctionDefineListener(BufferedTokenStream tokens) {
        this.tokens = tokens;
    }

    public List<FunctionDefine> getFunctions() {
        if (!aliasMap.isEmpty()) {
            for (FunctionDefine function : functions) {
                function.getAlias().addAll(this.aliasMap.getOrDefault(function.getFuncName(), Collections.emptySet()));
            }
        }
        return functions;
    }

    @Override
    public void exitCategory(FunctionParser.CategoryContext ctx) {
        current_categories.add(ctx.IDENTIFIER().getText());
    }

    @Override
    public void exitFunctionItem(FunctionParser.FunctionItemContext ctx) {
        current_function.getCategories().addAll(this.current_categories);
        this.functions.add(current_function);
        //清理现场
        this.current_function = null;
        this.current_categories.clear();
    }

    @Override
    public void exitFunctionAlias(FunctionParser.FunctionAliasContext ctx) {
        if (ctx.IDENTIFIER() == null || ctx.IDENTIFIER().size() <= 1) {
            throw new IllegalStateException("函数别名设置错误:" + ctx.getText());
        }
        String funcName = ctx.IDENTIFIER(0).getText();
        Set<String> aliasNames = new TreeSet<>(String.CASE_INSENSITIVE_ORDER);
        this.aliasMap.put(funcName, aliasNames);
        for (int i = 1; i < ctx.IDENTIFIER().size(); i++) {
            aliasNames.add(ctx.IDENTIFIER(i).getText());
        }
    }

    @Override
    public void exitFuncDefine(FunctionParser.FuncDefineContext ctx) {
        List<Token> descriptionTokens = tokens.getHiddenTokensToLeft(ctx.getStart().getTokenIndex());
        final String description = descriptionTokens.stream().map(Token::getText).collect(Collectors.joining(" "));

        final String funcName = ctx.IDENTIFIER().getText();
        final String dataType = ctx.dataType().getText();
        this.current_function = new FunctionDefine(funcName, DataType.of(dataType));
        this.current_function.setDescription(description);
        this.current_function.getArgs().addAll(this.current_function_args);

        this.current_function_args.clear();
    }

    @Override
    public void exitFuncImplement(FunctionParser.FuncImplementContext ctx) {
        if (ctx.D_BLOCK() != null || ctx.S_BLOCK() != null) {
            String implement = ctx.getText();
            implement = implement.substring(3, implement.length() - 3);
            this.current_function.setImplement(implement);
        } else {
            this.current_function.setImplement(ctx.getText());
        }
    }

    @Override
    public void exitFuncArgs(FunctionParser.FuncArgsContext ctx) {
        if (ctx.VARARG() != null) {
            this.current_function_args.get(this.current_function_args.size() - 1).setVararg(true);
        }
    }

    @Override
    public void exitFuncArg(FunctionParser.FuncArgContext ctx) {
        final String argName = ctx.IDENTIFIER().getText();
        final DataType dataType = DataType.of(ctx.IDENTIFIER().getText());
        FunctionDefine.FunctionArgDefine argDefine = new FunctionDefine.FunctionArgDefine(argName, dataType);
        if (ctx.enumerations() != null) {
            Set<String> enumValues = new HashSet<>();
            if (ctx.enumerations().NUMBER() != null) {
                for (TerminalNode numNode : ctx.enumerations().NUMBER()) {
                    enumValues.add(numNode.getText());
                }
            } else if (ctx.enumerations().STRING() != null) {
                for (TerminalNode stringNode : ctx.enumerations().STRING()) {
                    enumValues.add(stringNode.getText());
                }
            }
            argDefine.setEnumValues(enumValues);
        }
        this.current_function_args.add(argDefine);
    }
}
