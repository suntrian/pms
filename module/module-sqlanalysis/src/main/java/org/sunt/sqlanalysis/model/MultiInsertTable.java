package org.sunt.sqlanalysis.model;

import java.util.Collections;
import java.util.List;
import java.util.stream.Collectors;

public class MultiInsertTable implements Table{

    private final FromItem sourceTable;

    private final List<InsertTable> insertTables;

    public MultiInsertTable(FromItem sourceTable, List<InsertTable> insertTables) {
        this.sourceTable = sourceTable;
        this.insertTables = insertTables;
    }

    @Override
    public String toString() {
        return insertTables.stream().map(InsertTable::toString).collect(Collectors.joining(";\n", "", ";"));
    }

    @Override
    public List<? extends Table> getSourceTable() {
        return Collections.singletonList(sourceTable);
    }
}
