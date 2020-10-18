package org.sunt.formula.exception;

public class ParamsSizeMismatchException extends RuntimeException {

    private Integer actually;
    private Integer expected;

    public ParamsSizeMismatchException(String expression, Integer expected, Integer actually) {
        super(String.format("%s期待%d个参数，实际参数个数为%d", expression, expected, actually));
        this.actually = actually;
        this.expected = expected;
    }

    public Integer getActually() {
        return actually;
    }

    public Integer getExpected() {
        return expected;
    }

}
