package org.sunt.sqlanalysis.model;

public enum SetOperator implements Operator {

    UNION_ALL("UNION ALL"),
    UNION_DISTINCT("UNION DISTINCT"),
    INTERSECT_ALL("INTERSECT ALL"),
    INTERSECT_DISTINCT("INTERSECT DISTINCT"),
    EXCEPT_ALL("EXCEPT ALL"),
    EXCEPT_DISTINCT("EXCEPT DISTINCT"),
    MINUS_ALL("MINUS ALL"),
    MINUS_DISTINCT("MINUS DISTINCT");

    private final String symbol;

    SetOperator(String symbol) {
        this.symbol = symbol;
    }

    public static SetOperator of(String op) {
        op = op.trim().toUpperCase().replaceAll("\\s+", " ");
        for (SetOperator value : values()) {
            if (value.symbol.equals(op) || value.symbol.equals(op + " DISTINCT")) {
                return value;
            }
        }
        throw new IllegalArgumentException("未知的查询集合类型："+ op);
    }

    @Override
    public String getSymbol() {
        return symbol;
    }
}
