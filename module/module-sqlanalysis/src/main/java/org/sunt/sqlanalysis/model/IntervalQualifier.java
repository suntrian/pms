package org.sunt.sqlanalysis.model;

public enum  IntervalQualifier {
    YEAR_MONTH("YEAR TO MONTH"),
    DAY_SECOND("DAY TO SECOND"),
    YEAR("YEAR"),
    MONTH("MONTH"),
    DAY("DAY"),
    HOUR("HOUR"),
    MINUTE("MINUTE"),
    SECOND("SECOND");

    private final String symbol;


    IntervalQualifier(String symbol) {
        this.symbol = symbol;
    }

    public String getSymbol() {
        return symbol;
    }


}
