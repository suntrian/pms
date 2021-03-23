package org.sunt.sqlanalysis.model;

public interface SelectItem extends Expression, AliasItem {

    String getLabel();

    @Override
    SelectItem clone();

    DataType getDataType();
}
