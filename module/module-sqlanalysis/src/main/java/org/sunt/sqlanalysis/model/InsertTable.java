package org.sunt.sqlanalysis.model;

import java.util.Collection;
import java.util.Collections;
import java.util.LinkedList;
import java.util.List;

public class InsertTable extends LandTable{

    private boolean overwrite = false;

    private final List<AtomicField> fields = new LinkedList<>();

    private boolean realFields = false;

    private SelectTable sourceTable;

    private List<SelectExpr> partitionFields;

    public InsertTable(String tableName) {
        super(tableName);
    }

    public InsertTable addFields(AtomicField field) {
        this.fields.add(field);
        return this;
    }

    public InsertTable addFields(Collection<AtomicField> fields) {
        this.fields.addAll(fields);
        return this;
    }

    public InsertTable setSourceTable(SelectTable sourceTable) {
        this.sourceTable = sourceTable;
        return this;
    }

    public InsertTable setOverwrite(boolean overwrite) {
        this.overwrite = overwrite;
        return this;
    }

    public InsertTable setRealFields(boolean realFields) {
        this.realFields = realFields;
        return this;
    }

    public List<AtomicField> getFields() {
        return Collections.unmodifiableList(fields);
    }

    public boolean isRealFields() {
        return realFields;
    }

    public List<SelectTable> getSourceTable() {
        return Collections.singletonList(sourceTable);
    }

    public boolean isOverwrite() {
        return overwrite;
    }

    public List<SelectExpr> getPartitionFields() {
        return partitionFields;
    }

    public void setPartitionFields(List<SelectExpr> partitionFields) {
        this.partitionFields = partitionFields;
    }

    @Override
    public String toString() {
        StringBuilder sqlBuilder = new StringBuilder("INSERT ").append(overwrite?"OVERWRITE ":"INTO ").append(getTableName()).append(" ");
        if (realFields && !fields.isEmpty()){
            sqlBuilder.append("(");
            for (AtomicField field : fields) {
                sqlBuilder.append(field).append(',');
            }
            sqlBuilder.replace(sqlBuilder.length()-1, sqlBuilder.length(), ") ");
        }
        return sqlBuilder.append(sourceTable).toString();
    }
}
