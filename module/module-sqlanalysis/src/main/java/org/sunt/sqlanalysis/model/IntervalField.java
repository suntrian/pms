package org.sunt.sqlanalysis.model;

import java.util.Collections;

public class IntervalField extends FunctionField{

    private Expression interval;
    private IntervalQualifier qualifier;

    public IntervalField(String expression) {
        super(expression);
        this.functionName = "INTERVAL";
    }

    public IntervalField feed(Expression interval, IntervalQualifier qualifier) {
        this.interval = interval;
        this.qualifier = qualifier;
        this.params = Collections.singletonList(interval);
        return this;
    }

    @Override
    public DataType getDataType() {
        return DataType.INTEGER;
    }

    public Expression getInterval() {
        return interval;
    }

    public IntervalQualifier getQualifier() {
        return qualifier;
    }

    @Override
    public String toString() {
        return "INTERVAL " + interval + " " + qualifier.name();
    }

    @Override
    public IntervalField clone() {
        return new IntervalField(expression).feed(interval.clone(), qualifier);
    }
}
