package org.sunt.sqlanalysis.model;

public class VariableField extends AtomicField {

    public VariableField(String expression) {
        super(expression);
    }

    @Override
    public VariableField clone() {
        return new VariableField(this.expression);
    }

}
