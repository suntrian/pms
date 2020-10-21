package org.sunt.sqlanalysis.lineage.model.table

import org.sunt.sqlanalysis.lineage.model.field.LogicalField

open class SelectTable(related: List<ITable>) : LogicalTable(related) {

    final override var alias: String = ""

    final override val fields: List<LogicalField> = emptyList()

}