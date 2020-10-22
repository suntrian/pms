package org.sunt.sqlanalysis.lineage.model.field

import org.sunt.sqlanalysis.lineage.model.table.EmptyTable

class VariableField(val name: String, table: EmptyTable) : LogicalField(table) {

    override val alias: String = name

}