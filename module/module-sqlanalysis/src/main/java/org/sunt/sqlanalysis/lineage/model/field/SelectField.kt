package org.sunt.sqlanalysis.lineage.model.field

import org.sunt.sqlanalysis.lineage.model.table.LogicalTable

/**
 *  select字段
 */
class SelectField(val expr: String, table: LogicalTable) : LogicalField(table) {

    private var alias: String = expr

    override fun getAlias(): String {
        return alias
    }

}