package org.sunt.sqlanalysis.model;

public enum  JoinType {
    COMMA(","),
    INNER("INNER JOIN"),
    LEFT("LEFT JOIN"),
    RIGHT("RIGHT JOIN"),
    FULL("FULL JOIN"),
    CROSS("CROSS JOIN"),
    UNIQUE("UNIQUEJOIN"),
    LEFT_SEMI("LEFT SEMI JOIN"),
    ANTI("ANTI JOIN"),
    NATURAL("NATURAL JOIN"),
    NATURAL_LEFT("NATURAL LEFT JOIN"),
    NATURAL_RIGHT("NATURAL RIGHT JOIN"),
    STRAIGHT("STRAIGHT JOIN");

    private final String symbol;

    JoinType(String symbol) {
        this.symbol = symbol;
    }

    public String getSymbol() {
        return symbol;
    }

    public static JoinType of(String type) {
        type = type.trim().toUpperCase().replaceAll("\\s+", " ");
        switch (type) {
            case ",":
                return COMMA;
            case "JOIN":
            case "INNER JOIN":
                return INNER;
            case "LEFT JOIN":
            case "LEFT OUTER JOIN":
                return LEFT;
            case "RIGHT JOIN":
            case "RIGHT OUTER JOIN":
                return RIGHT;
            case "CROSS JOIN":
                return CROSS;
            case "FULL JOIN":
            case "FULL OUTER JOIN":
                return FULL;
            case "LEFT SEMI JOIN":
                return LEFT_SEMI;
            case "UNIQUEJOIN":
                return UNIQUE;
        }
        return INNER;
    }

}
