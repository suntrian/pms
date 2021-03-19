package org.sunt.sqlanalysis.model;

import java.util.Collections;
import java.util.List;

public class UpdateTable extends LandTable implements AliasItem {

    private Alias alias;

    private Expression where;

    private Table sourceTable;

    public UpdateTable(String tableName) {
        super(tableName);
    }

    public UpdateTable(String tableName, Expression where) {
        super(tableName);
        this.where = where;
    }

    public Expression getWhere() {
        return where;
    }

    public UpdateTable setWhere(Expression where) {
        this.where = where;
        return this;
    }

    @Override
    public List<? extends Table> getSourceTable() {
        return sourceTable!=null
                ? Collections.singletonList(sourceTable)
                : Collections.singletonList(new PhysicalTable(getTableName().getFullName()));
    }

    public void setSourceTable(Table sourceTable) {
        this.sourceTable = sourceTable;
    }

    @Override
    public String toString() {
        StringBuilder sqlBuilder = new StringBuilder("UPDATE ")
                .append(getTableName());
        if (!getAlias().isEmpty()) {
            sqlBuilder.append(" ").append(getAlias());
        }
        sqlBuilder.append(" SET ... ")
                .append(" WHERE ");
        if (where != null) {
            sqlBuilder.append(where);
        }
        return sqlBuilder.toString();
    }

    @Override
    public Alias getAlias() {
        return alias != null ? alias : Alias.EMPTY;
    }

    @Override
    public void setAlias(Alias alias) {
        this.alias = alias;
    }
}
