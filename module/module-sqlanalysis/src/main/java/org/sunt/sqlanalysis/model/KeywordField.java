package org.sunt.sqlanalysis.model;

public class KeywordField extends SelectExpr{

    public KeywordField(String expression) {
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
    public String toString() {
        return getExpression();
    }

    @Override
    public KeywordField clone() {
        return new KeywordField(expression);
    }
}
