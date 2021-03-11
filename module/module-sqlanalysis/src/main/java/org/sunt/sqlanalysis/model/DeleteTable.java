package org.sunt.sqlanalysis.model;

import java.util.Collections;
import java.util.List;

public class DeleteTable extends LandTable implements AliasItem {

    private Alias alias;

    private Expression where;

    public DeleteTable(String tableName) {
        super(tableName);
    }

    public DeleteTable(String tableName, Expression where) {
        super(tableName);
        this.where = where;
    }

    public Expression getWhere() {
        return where;
    }

    public DeleteTable setWhere(Expression where) {
        this.where = where;
        return this;
    }

    @Override
    public String toString() {
        return "DELETE FROM " + getTableName() + (getAlias().isEmpty()?"": " "+getAlias()) + (where!=null?" WHERE " + where:"");
    }

    @Override
    public List<? extends Table> getSourceTable() {
        return Collections.singletonList(new PhysicalTable(getTableName().getFullName()));
    }

    @Override
    public Alias getAlias() {
        return alias != null? alias : Alias.EMPTY;
    }

    @Override
    public void setAlias(Alias alias) {
        this.alias = alias;
    }
}
