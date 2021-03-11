package org.sunt.sqlanalysis.model;

public enum TimeQualifier {

    YEAR,
    QUARTER,
    MONTH,
    WEEK,
    DAY,
    DOW,
    HOUR,
    MINUTE,
    SECOND;

    public static TimeQualifier of(String qualifier) {
        qualifier = qualifier.trim().toUpperCase();
        return TimeQualifier.valueOf(qualifier);
    }

}
