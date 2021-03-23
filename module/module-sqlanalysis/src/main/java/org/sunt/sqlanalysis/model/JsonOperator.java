package org.sunt.sqlanalysis.model;

public enum JsonOperator implements Operator{

    PROP("->"),
    PROPP("->>")
    ;

    private final String symbol;

    JsonOperator(String symbol) {
        this.symbol = symbol;
    }

    public static JsonOperator of(String symbol) {
        switch (symbol) {
            case "->": return PROP;
            case "->>": return PROPP;
            default: return null;
        }
    }

    @Override
    public String getSymbol() {
        return symbol;
    }
}
