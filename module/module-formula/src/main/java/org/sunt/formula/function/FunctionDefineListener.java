package org.sunt.formula.function;

import org.antlr.v4.runtime.BufferedTokenStream;
import org.antlr.v4.runtime.tree.ParseTree;
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

    private final List<FunctionArgDefine> current_function_args = new LinkedList<>();

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
        current_categories.addAll(ctx.IDENTIFIER().stream().map(ParseTree::getText).collect(Collectors.toList()));
    }

    @Override
    public void exitFunctionItem(FunctionParser.FunctionItemContext ctx) {
        this.current_function.setDescription(ctx.DESCRIPTION().getText());
        this.current_function.getCategories().addAll(this.current_categories);
        this.functions.add(this.current_function);
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
        final String funcName = ctx.IDENTIFIER().getText();
        if (ctx.dataType() != null) {
            final String dataType = ctx.dataType().getText();
            this.current_function = new FunctionDefine(funcName, DataType.of(dataType));
        } else if (ctx.argRef() != null) {
            this.current_function = new FunctionDefine(funcName);
            if (ctx.argRef().INTEGER() != null) {
                this.current_function.setTypeParamIndex(Integer.valueOf(ctx.argRef().INTEGER().getText()));
            } else if (ctx.argRef().argName() != null) {
                final String argName = ctx.argRef().argName().getText();
                for (int i = 0; i < this.current_function_args.size(); i++) {
                    if (argName.equalsIgnoreCase(this.current_function_args.get(i).getName())) {
                        this.current_function.setTypeParamIndex(i + 1);
                        break;
                    }
                }
            }
        }
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
        final String argName = ctx.argName().getText();
        final DataType dataType = DataType.of(ctx.dataType().getText());
        FunctionArgDefine argDefine = new FunctionArgDefine(argName, dataType);
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
