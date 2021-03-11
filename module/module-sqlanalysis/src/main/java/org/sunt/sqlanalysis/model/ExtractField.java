package org.sunt.sqlanalysis.model;

import java.util.Collections;

public class ExtractField extends FunctionField{

    private TimeQualifier qualifier;

    private Expression timeField;

    public ExtractField(String expression) {
        super(expression);
        this.functionName = "EXTRACT";
    }

    public ExtractField feed(TimeQualifier qualifier, Expression timeField) {
        this.qualifier = qualifier;
        this.timeField = timeField;
        this.params = Collections.singletonList(timeField);
        return this;
    }

    public TimeQualifier getQualifier() {
        return qualifier;
    }

    public Expression getTimeField() {
        return timeField;
    }

    @Override
    public DataType getDataType() {
        return DataType.INTEGER;
    }

    @Override
    public String toString() {
        return "EXTRACT ( " + qualifier.name() + " FROM " + timeField + " )";
    }

    @Override
    public ExtractField clone() {
        return new ExtractField(expression).feed(qualifier, timeField.clone());
    }
}
