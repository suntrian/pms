package org.sunt.sqlanalysis.lineage.model.field

import org.sunt.sqlanalysis.lineage.model.table.AnonymousTable

class VariableField(val name: String, table: AnonymousTable) : LogicalField(table) {

    override val alias: String = name

}