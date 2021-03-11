package org.sunt.sqlanalysis.model;

public enum LogicalOperator implements Operator{

    NOT("NOT"),
    XOR("XOR"),
    AND("AND"),
    OR("OR");

    private final String symbol;

    LogicalOperator(String symbol) {
        this.symbol = symbol;
    }

    @Override
    public String getSymbol() {
        return symbol;
    }
}
