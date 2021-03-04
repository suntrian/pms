package org.sunt.api.customize.dictionary;

public enum DataType {

    INTEGER("Integer"),
    DECIMAL("Decimal"),
    STRING("String"),
    BOOLEAN("Boolean"),
    DATE("Date"),
    TIME("Time"),
    DATETIME("DateTime"),
    JSON("Json"),
    DICT("Dict"),
    LIST("List"),
    REF("Reference");

    private String label;

    DataType(String label) {
        this.label = label;
    }

    public String getLabel() {
        return label;
    }
}
