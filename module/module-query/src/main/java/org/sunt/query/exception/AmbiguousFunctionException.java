package org.sunt.query.exception;

import org.sunt.query.formula.function.FunctionDefinition;

import java.util.Collection;
import java.util.stream.Collectors;

public class AmbiguousFunctionException extends AbstractFormulaException {

    private static final long serialVersionUID = -6629216833943283878L;

    public AmbiguousFunctionException(String message) {
        super(message);
    }

    public AmbiguousFunctionException(String expression, Collection<FunctionDefinition> functionDefinitions) {
        this("[" + expression + "]同时满足以下函数定义：\n" + functionDefinitions.stream().map(FunctionDefinition::getDefinition).collect(Collectors.joining("\n", "\t", ";")));
    }

    public AmbiguousFunctionException(Collection<FunctionDefinition> functionDefinitions) {
        this("函数歧义调用，同时满足以下定义：\n" + functionDefinitions.stream().map(FunctionDefinition::getDefinition).collect(Collectors.joining("\n", "\t", ";")));
    }

}
