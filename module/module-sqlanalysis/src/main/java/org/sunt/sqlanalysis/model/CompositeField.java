package org.sunt.sqlanalysis.model;

import java.util.Arrays;
import java.util.Collections;
import java.util.List;
import java.util.Objects;
import java.util.stream.Collectors;

public class CompositeField extends SelectExpr {

    private List<SelectItem> fields = Collections.emptyList();

    public CompositeField(String expression) {
        super(expression);
    }

    public CompositeField feed(List<SelectItem> fields) {
        this.fields = fields.stream().filter(Objects::nonNull).collect(Collectors.toList());
        return this;
    }

    public CompositeField feed(SelectItem... fields) {
        this.fields = Arrays.stream(fields).filter(Objects::nonNull).collect(Collectors.toList());
        return this;
    }

    public List<SelectItem> getFields() {
        return fields;
    }

    @Override
    public DataType getDataType() {
        return DataType.NONE;
    }

    @Override
    public SelectExpr clone() {
        return new CompositeField(this.expression).feed(this.fields.stream().map(SelectItem::clone).collect(Collectors.toList()));
    }

}
