package org.sunt.query.formula.exception;

import org.sunt.query.define.DataType;

public class ParamTypeMismatchException extends AbstractFormulaException {

    private static final long serialVersionUID = 3648211597650156219L;

    private DataType expected;

    private DataType actually;

    private String expression;

    public ParamTypeMismatchException(String message) {
        super(message);
    }

    public ParamTypeMismatchException(String expression, DataType expected, DataType actually) {
        super(String.format("表达式%s期待%s类型，实际%s类型", expression, expected.name(), actually.name()));
        this.expression = expression;
        this.expected = expected;
        this.actually = actually;
    }

    public DataType getExpected() {
        return expected;
    }

    public DataType getActually() {
        return actually;
    }

    public String getExpression() {
        return expression;
    }
}
