package org.sunt.sqlanalysis.model;

import java.util.Collections;
import java.util.List;

public class DropTable extends LandTable{

    private boolean ifExists = false;

    public DropTable(String tableName) {
        super(tableName);
    }

    public DropTable setIfExists(boolean ifExists) {
        this.ifExists = ifExists;
        return this;
    }

    @Override
    public String toString() {
        return "DROP TABLE " + (ifExists?"IF EXISTS ":"") + getTableName();
    }

    @Override
    public List<? extends Table> getSourceTable() {
        return Collections.singletonList(new PhysicalTable(getTableName().getFullName()));
    }
}
