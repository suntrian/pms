package org.sunt.sqlanalysis.model;

import java.util.Collections;
import java.util.LinkedList;
import java.util.List;
import java.util.Objects;

public class PhysicalTable implements FromItem, AliasItem {

    private final TableName tableName;

    private Alias alias;

    private final List<AtomicField> fields = new LinkedList<>();

    public PhysicalTable(String tableName) {
        this.tableName = new TableName(tableName);
    }

    public PhysicalTable addField(AtomicField field) {
        this.fields.add(field);
        return this;
    }

    public void setAlias(Alias alias) {
        this.alias = alias;
    }

    @Override
    public Alias getAlias() {
        return alias != null? alias : Alias.EMPTY;
    }

    public TableName getTableName() {
        return tableName;
    }

    @Override
    public List<AtomicField> getFields() {
        return Collections.unmodifiableList(this.fields);
    }

    @Override
    public String getLabel() {
        return alias!=null&&!alias.isEmpty()?alias.getAlias():tableName.table;
    }

    @Override
    public String toString() {
        return tableName.toString();
    }

    @Override
    public List<Table> getSourceTable() {
        return Collections.emptyList();
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (!(o instanceof PhysicalTable)) return false;
        PhysicalTable that = (PhysicalTable) o;
        return Objects.equals(tableName, that.tableName) && Objects.equals(alias, that.alias);
    }

    @Override
    public int hashCode() {
        return Objects.hash(tableName, alias);
    }
}
