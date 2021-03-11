package org.sunt.sqlanalysis.model;


import java.util.Objects;

public abstract class LandTable implements Table{

    private TableName tableName;

    public LandTable(String tableName) {
        this.tableName = new TableName(tableName);
    }

    public TableName getTableName() {
        return tableName;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (!(o instanceof LandTable)) return false;
        if (!this.getClass().equals(o.getClass())) return false;
        LandTable landTable = (LandTable) o;
        return tableName.equals(landTable.tableName);
    }

    @Override
    public int hashCode() {
        return Objects.hash(tableName);
    }
}
