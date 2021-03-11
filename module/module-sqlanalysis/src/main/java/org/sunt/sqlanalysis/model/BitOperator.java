package org.sunt.sqlanalysis.model;

public enum BitOperator implements Operator{

    AND("&"),
    TILDE("~"),
    OR("|"),
    XOR("^");

    private final String symbol;

    @Override
    public String getSymbol() {
        return symbol;
    }

    BitOperator(String symbol) {
        this.symbol = symbol;
    }

    @Override
    public String toString() {
        return symbol;
    }

}
