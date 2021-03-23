package org.sunt.sqlanalysis.model;

public enum CalculateOperator implements Operator {

    MUL("*"),
    DIV("DIV"),   //整数
    MOD("%"),
    DIVIDE("/"), //小数
    PLUS("+"),
    MINUS("-"),
    MINUSMINUS("--"),
    CONCAT("||");

    private final String symbol;

    CalculateOperator(String symbol) {
        this.symbol = symbol;
    }

    @Override
    public String getSymbol() {
        return symbol;
    }

}
