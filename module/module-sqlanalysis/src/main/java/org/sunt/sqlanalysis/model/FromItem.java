package org.sunt.sqlanalysis.model;

import java.util.List;

public interface FromItem extends Table {

    List<? extends SelectItem> getFields();

    String getLabel();

}
