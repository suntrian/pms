package org.sunt.sqlanalysis.model;

import java.util.Collection;
import java.util.Collections;
import java.util.LinkedList;
import java.util.List;

public class CreateTable extends LandTable{

    private boolean view = false;

    private boolean materialized = false;

    private boolean temporary = false;

    private final List<SelectExpr> fields = new LinkedList<>();

    private Table sourceTable;

    private String comment;

    public CreateTable(String tableName) {
        super(tableName);
    }

    public CreateTable addField(SelectExpr field) {
        this.fields.add(field);
        return this;
    }

    public CreateTable addField(Collection<SelectExpr> fields) {
        this.fields.addAll(fields);
        return this;
    }

    public List<SelectExpr> getFields() {
        return Collections.unmodifiableList(fields);
    }

    public List<Table> getSourceTable() {
        return Collections.singletonList(sourceTable);
    }

    public String getComment() {
        return comment;
    }

    public boolean isView() {
        return view;
    }

    public boolean isMaterialized() {
        return materialized;
    }

    public boolean isTemporary() {
        return temporary;
    }

    public CreateTable setSourceTable(Table sourceTable) {
        this.sourceTable = sourceTable;
        return this;
    }

    public CreateTable setComment(String comment) {
        this.comment = comment;
        return this;
    }

    public CreateTable setView(boolean view) {
        this.view = view;
        return this;
    }

    public CreateTable setMaterialized(boolean materialized) {
        this.materialized = materialized;
        return this;
    }

    public void setTemporary(boolean temporary) {
        this.temporary = temporary;
    }

    @Override
    public String toString() {
        StringBuilder builder = new StringBuilder( "CREATE ").append(materialized?"MATERIALIZED ":"").append(view?"VIEW ":"TABLE ").append(getTableName());
        List<CreateField> partitionFields = new LinkedList<>();
        if (!getFields().isEmpty() && getFields().stream().anyMatch(i->i instanceof CreateField)) {
            builder.append(" (\n");
            for (SelectExpr field : getFields()) {
                if (((CreateField) field).isPartitionField()) {
                    partitionFields.add((CreateField) field);
                    continue;
                }
                builder.append(" ").append(field).append(",\n");
            }
            builder.replace(builder.length()-2, builder.length(), "\n)");
        }
        if ( getComment() != null && !getComment().isEmpty()) {
            builder.append(" COMMENT '").append(getComment()).append("'\n");
        }
        if (!partitionFields.isEmpty()) {
            builder.append(" PARTITIONED BY (");
            for (CreateField partitionField : partitionFields) {
                builder.append(partitionField).append(", ");
            }
            builder.replace(builder.length()-2, builder.length(), ")\n");
        }
        if (sourceTable != null) {
            if (sourceTable instanceof SelectTable) {
                builder.append(" AS \n").append(sourceTable);
            } else if (sourceTable instanceof PhysicalTable) {
                builder.append(" LIKE ").append(sourceTable);
            }
        }
        return builder.toString();
    }
}
