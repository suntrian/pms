package org.sunt.sqlanalysis.lineage.model.field

import org.sunt.sqlanalysis.lineage.model.table.LogicalTable

/**
 *  select字段
 */
open class SelectField(val expr: String, table: LogicalTable) : LogicalField(table) {

    final override var alias: String = expr

}