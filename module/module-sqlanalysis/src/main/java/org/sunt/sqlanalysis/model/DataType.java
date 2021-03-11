package org.sunt.sqlanalysis.model;

public enum DataType {

    NONE(),
    ANY(),
    STRING(),
    DECIMAL(),
    INTEGER(),
    DATE(),
    DATETIME(),
    TIME(),
    BOOL(),
    ARRAY(),
    MAP(),
    BINARY(),
    OTHER();


    public static DataType of(String type) {
        if (null == type) return OTHER;
        type = type.toUpperCase();
        switch (type) {
            case "STRING":
            case "VARCHAR":
            case "CHAR":
                return STRING;
            case "INT":
            case "INTEGER":
                return INTEGER;
            case "DATE":
                return DATE;
            case "DATETIME":
            case "TIMESTAMP":
                return DATETIME;
            case "TIME":
                return TIME;
            case "BOOL":
            case "BOOLEAN":
                return BOOL;
            case "BINARY":
            case "BLOB":
                return BINARY;
            default:
                if (type.contains("INT")) {
                    return INTEGER;
                } else if (type.contains("TIMESTAMP")) {
                    return DATETIME;
                } else if (type.contains("CHAR") || type.contains("TEXT")) {
                    return STRING;
                } else if (type.contains("DOUBLE") || type.contains("DEC") || type.contains("FLOAT")) {
                    return DECIMAL;
                } else if (type.contains("BLOB")) {
                    return BINARY;
                } else if (type.contains("ARRAY") || type.contains("LIST")) {
                    return ARRAY;
                } else if (type.contains("MAP")) {
                    return MAP;
                } else {
                    return OTHER;
                }
        }
    }

}
