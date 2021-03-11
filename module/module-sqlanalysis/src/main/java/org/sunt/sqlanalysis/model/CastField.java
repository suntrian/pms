package org.sunt.sqlanalysis.model;

import java.util.Collections;

public class CastField extends FunctionField{

    private Expression field;

    private DataType castDataType;

    public CastField(String expression) {
        super(expression);
        this.functionName = "CAST";
    }

    @Override
    public DataType getDataType() {
        return castDataType;
    }

    public Expression getField() {
        return field;
    }

    public DataType getCastDataType() {
        return castDataType;
    }

    public CastField feed(Expression field, DataType dataType) {
        this.field = field;
        this.castDataType = dataType;
        this.params = Collections.singletonList(field);
        return this;
    }

    @Override
    public CastField clone() {
        return new CastField(expression).feed(field.clone(), castDataType);
    }

    @Override
    public String toString() {
        return "CAST(" + this.field + " AS " + this.castDataType.name() + " )";
    }

}
