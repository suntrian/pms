package org.sunt.sqlanalysis.model;

public class ConstantField extends SelectExpr{

    private boolean nullValue;
    private boolean boolValue;
    private boolean stringValue;
    private boolean numberValue;

    public ConstantField(String expression) {
        super(expression);
    }

    @Override
    public DataType getDataType() {
        return null;
    }


    @Override
    public ConstantField clone() {
        return new ConstantField(expression);
    }
}
