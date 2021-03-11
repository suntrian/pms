package org.sunt.sqlanalysis.model;

import org.sunt.sqlanalysis.exception.WillNeverHappenException;

import java.util.Objects;

public class ColumnName extends TableName {

    private final String column;

    public ColumnName(String catalog, String schema, String table, String column) {
        super(catalog, schema, table);
        this.column = column;
    }

    public ColumnName(String column) {
        String[] sections = column.split("\\.");
        if (sections.length == 1) {
            this.column = sections[0];
        } else if (sections.length == 2) {
            this.table = sections[0];
            this.column = sections[1];
        } else if (sections.length == 3 ) {
            this.schema = sections[0];
            this.table = sections[1];
            this.column = sections[2];
        } else if (sections.length == 4) {
            this.category = sections[0];
            this.schema = sections[1];
            this.table = sections[2];
            this.column = sections[3];
        } else {
            throw new WillNeverHappenException("过多的字段名称：" + column);
        }
    }

    public String getColumn() {
        return unwrap(column);
    }

    public TableName getTableName() {
        return (table == null || table.isEmpty())? null : new TableName(category, schema, table);
    }

    public String getFullName() {
        return toString();
    }

    @Override
    public String toString() {
        if (category != null) {
            return category + "." + schema + "." + table + "." + column;
        } else if (schema != null) {
            return schema + "." + table + "." + column;
        } else if (table != null){
            return table + "." + column;
        } else {
            return column;
        }
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (!(o instanceof ColumnName)) return false;
        ColumnName that = (ColumnName) o;
        if (table == null || that.table == null) {
            return column.equalsIgnoreCase(that.column);
        } else if (schema == null || that.schema == null) {
            return table.equalsIgnoreCase(that.table) && column.equalsIgnoreCase(that.column);
        } else if (category == null || that.category == null) {
            return schema.equalsIgnoreCase(that.schema) && table.equalsIgnoreCase(that.table) && column.equalsIgnoreCase(that.column);
        } else {
            return category.equalsIgnoreCase(that.category)
                    && schema.equalsIgnoreCase(that.schema)
                    && table.equalsIgnoreCase(that.table)
                    && column.equalsIgnoreCase(that.column);
        }
    }

    @Override
    public int hashCode() {
        return Objects.hash(column.toLowerCase());
    }
}
