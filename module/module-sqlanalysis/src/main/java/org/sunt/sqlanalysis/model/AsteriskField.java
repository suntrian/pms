package org.sunt.sqlanalysis.model;

public class AsteriskField extends AtomicField {

    public AsteriskField(String expression) {
        super(expression);
    }

    @Override
    public DataType getDataType() {
        return DataType.NONE;
    }

    @Override
    public Alias getAlias() {
        return Alias.EMPTY;
    }

    @Override
    public AsteriskField clone() {
        return new AsteriskField(expression);
    }
}
