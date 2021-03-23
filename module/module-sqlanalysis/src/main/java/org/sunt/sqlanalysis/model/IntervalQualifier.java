package org.sunt.sqlanalysis.model;

public enum  IntervalQualifier {
    YEAR_MONTH("YEAR TO MONTH"),
    DAY_SECOND("DAY TO SECOND"),
    YEAR("YEAR"),
    QUARTER("QUARTER"),
    MONTH("MONTH"),
    WEEK("WEEK"),
    DAY("DAY"),
    HOUR("HOUR"),
    MINUTE("MINUTE"),
    SECOND("SECOND"),
    MICROSECOND("MICROSECOND"),
    DAY_HOUR("DAY_HOUR"),
    DAY_MINUTE("DAY_MINUTE"),
    HOUR_MINUTE("HOUR_MINUTE"),
    HOUR_SECOND("HOUR_SECOND"),
    MINUTE_SECOND("MINUTE_SECOND"),
    SECOND_MICROSECOND("SECOND_MICROSECOND"),
    MINUTE_MICROSECOND("MINUTE_MICROSECOND"),
    HOUR_MICROSECOND("HOUR_MICROSECOND"),
    DAY_MICROSECOND("DAY_MICROSECOND")
    ;


    private final String symbol;


    IntervalQualifier(String symbol) {
        this.symbol = symbol;
    }

    public String getSymbol() {
        return symbol;
    }

    public static IntervalQualifier of(String qualifier) {
        qualifier = qualifier.toUpperCase();
        try {
            return valueOf(qualifier);
        } catch (Exception e) {
            for (IntervalQualifier value : values()) {
                if (value.symbol.equals(qualifier))
                    return value;
            }
            return null;
        }

    }

}
