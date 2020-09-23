package org.sunt.sqlanalysis.model.field

import org.sunt.sqlanalysis.model.table.LogicalTable

/**
 *  select字段
 */
class SelectField(val expr: String, val table: LogicalTable) : LogicalField() {

    private var alias: String = ""

    override fun getAlias(): String {
        return alias;
    }

}