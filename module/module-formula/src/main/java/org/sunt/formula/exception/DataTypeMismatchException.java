package org.sunt.formula.exception;

import org.sunt.formula.define.DataType;

public class DataTypeMismatchException extends AbstractFormulaException {

    private static final long serialVersionUID = 3648211597650156219L;

    private DataType expected;

    private DataType actually;

    private String expression;

    public DataTypeMismatchException(String message) {
        super(message);
    }

    public DataTypeMismatchException(String expression, DataType expected, DataType actually) {
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
