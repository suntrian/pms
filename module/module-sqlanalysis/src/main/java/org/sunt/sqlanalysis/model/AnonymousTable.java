package org.sunt.sqlanalysis.model;

import java.util.Collection;
import java.util.Collections;
import java.util.LinkedList;
import java.util.List;

public class AnonymousTable implements Table {

    private final TableName tableName;

    private final List<AtomicField> fields = new LinkedList<>();

    private List<Table> sourceTables = Collections.emptyList();

    public AnonymousTable(String tableName) {
        this.tableName = new TableName(tableName);
    }

    @Override
    public List<? extends Table> getSourceTable() {
        return sourceTables;
    }

    public void setSourceTables(List<Table> sourceTables) {
        this.sourceTables = sourceTables;
    }

    public AnonymousTable addField(AtomicField field) {
        this.fields.add(field);
        return this;
    }

    public AnonymousTable addField(Collection<AtomicField> fields) {
        this.fields.addAll(fields);
        return this;
    }

    public TableName getTableName() {
        return tableName;
    }

    public List<AtomicField> getFields() {
        return Collections.unmodifiableList(this.fields);
    }

}
