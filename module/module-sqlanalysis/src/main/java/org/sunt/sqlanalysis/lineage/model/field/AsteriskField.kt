package org.sunt.sqlanalysis.lineage.model.field

import org.sunt.sqlanalysis.lineage.model.table.LogicalTable

class AsteriskField(table: LogicalTable): LogicalField(table) {

    override fun getAlias(): String {
        return "*";
    }

}