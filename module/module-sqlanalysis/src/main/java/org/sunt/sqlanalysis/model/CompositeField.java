package org.sunt.sqlanalysis.model;

import java.util.Arrays;
import java.util.Collections;
import java.util.List;
import java.util.Objects;
import java.util.stream.Collectors;

public class CompositeField extends SelectExpr {

    private List<Expression> fields = Collections.emptyList();

    private boolean wrapParenthesis = false;

    public CompositeField(String expression) {
        super(expression);
    }

    public CompositeField(String expression, boolean wrapParenthesis) {
        super(expression);
        this.wrapParenthesis = wrapParenthesis;
    }

    public CompositeField feed(List<Expression> fields) {
        this.fields = fields.stream().filter(Objects::nonNull).collect(Collectors.toList());
        return this;
    }

    public CompositeField feed(Expression... fields) {
        this.fields = Arrays.stream(fields).filter(Objects::nonNull).collect(Collectors.toList());
        return this;
    }

    public List<Expression> getFields() {
        return fields;
    }

    @Override
    public DataType getDataType() {
        return DataType.NONE;
    }

    @Override
    public SelectExpr clone() {
        return new CompositeField(this.expression).feed(this.fields.stream().map(Expression::clone).collect(Collectors.toList()));
    }

}
