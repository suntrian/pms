package org.sunt.sqlanalysis.model;

import java.util.List;

public interface Table {

    List<? extends Table> getSourceTable();

}
