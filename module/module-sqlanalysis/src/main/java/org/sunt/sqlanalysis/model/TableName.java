package org.sunt.sqlanalysis.model;

import org.sunt.sqlanalysis.exception.WillNeverHappenException;

import java.io.Serializable;
import java.util.Objects;

public class TableName implements Serializable {

    protected String category;

    protected String schema;

    protected String table;

    protected TableName(){}

    public TableName(String schema, String table) {
        this.schema = schema;
        this.table = table;
    }

    public TableName(String category, String schema, String table) {
        this(schema, table);
        this.category = category;
    }

    public TableName(String table) {
        String[] sections = table.split("\\.");
        if (sections.length == 1) {
            this.table = table;
        } else if (sections.length == 2) {
            this.schema = sections[0];
            this.table = sections[1];
        } else if (sections.length == 3 ) {
            this.category = sections[0];
            this.schema = sections[1];
            this.table = sections[2];
        } else {
            throw new WillNeverHappenException("过多的表名称：" + table);
        }
    }

    public String getCategory() {
        return unwrap(category);
    }

    public String getSchema() {
        return unwrap(schema);
    }

    public String getTable() {
        return unwrap(table);
    }

    public String getFullName() {
        return toString();
    }

    public static String unwrap(String identifier) {
        if (identifier == null) return null;
        if (identifier.startsWith("`") && identifier.endsWith("`")) {
            return identifier.substring(1, identifier.length()-1);
        } else if (identifier.startsWith("\"") && identifier.endsWith("\"")) {
            return identifier.substring(1, identifier.length()-1);
        } else if (identifier.startsWith("[") && identifier.endsWith("]")) {
            return identifier.substring(1, identifier.length()-1);
        }
        return identifier;
    }

    @Override
    public String toString() {
        if (category != null) {
            return category + "." + schema + "." + table;
        } else if (schema != null) {
            return schema + "." + table;
        } else {
            return table;
        }
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (!(o instanceof TableName)) return false;
        TableName tableName = (TableName) o;
        if (this.schema == null || tableName.schema == null) {
            return this.table.equalsIgnoreCase(tableName.table);
        } else if (this.category == null || tableName.category == null) {
            return this.schema.equalsIgnoreCase(tableName.schema)
                    && this.table.equalsIgnoreCase(tableName.table);
        } else {
            return this.category.equalsIgnoreCase(tableName.category)
                    && this.schema.equalsIgnoreCase(tableName.schema)
                    && this.table.equalsIgnoreCase(tableName.table);
        }
    }

    @Override
    public int hashCode() {
        return Objects.hash(table.toLowerCase());
    }
}
