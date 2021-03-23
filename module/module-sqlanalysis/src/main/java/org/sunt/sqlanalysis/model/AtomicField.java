package org.sunt.sqlanalysis.model;

import java.util.Objects;

public class AtomicField extends SelectExpr{

    private boolean physical = false;

    private boolean partition = false;

    protected ColumnName columnName;

    public AtomicField(String expression) {
        super(expression);
        setColumnName(expression);
    }

    public static AtomicField of(String expression) {
        if (expression.endsWith("*")) {
            return new AsteriskField(expression);
        } else {
            return new AtomicField(expression);
        }
    }

    @Override
    public String getLabel() {
        return alias!=null&&!alias.isEmpty()? alias.getAlias() : columnName != null? columnName.getColumn() : expression;
    }

    public ColumnName getColumnName() {
        return columnName;
    }

    public AtomicField setColumnName(String columnName) {
        this.columnName = new ColumnName(columnName);
        return this;
    }

    public boolean isPhysical() {
        return physical;
    }

    public AtomicField setPhysical(boolean physical) {
        this.physical = physical;
        return this;
    }

    public boolean isPartition() {
        return partition;
    }

    public void setPartition(boolean partition) {
        this.partition = partition;
    }

    @Override
    public String toString() {
        return getExpression();
    }

    @Override
    public AtomicField clone() {
        AtomicField field = new AtomicField(expression);
        field.setPhysical(physical);
        return field;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (!(o instanceof AtomicField)) return false;
        AtomicField field = (AtomicField) o;
        return physical == field.physical && Objects.equals(columnName, field.columnName);
    }

    @Override
    public int hashCode() {
        return Objects.hash(physical, columnName);
    }
}
