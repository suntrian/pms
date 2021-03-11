package org.sunt.sqlanalysis.model;

import java.util.Collections;
import java.util.List;

public class EmptyTable implements FromItem{

    private String expression = "INVALID_TABLE";

    public EmptyTable(String expression) {
        this.expression = expression;
    }

    public EmptyTable() {
    }

    @Override
    public List<SelectItem> getFields() {
        return Collections.emptyList();
    }

    @Override
    public String getLabel() {
        return "";
    }

    @Override
    public List<Table> getSourceTable() {
        return Collections.emptyList();
    }

    @Override
    public String toString() {
        return expression;
    }
}
