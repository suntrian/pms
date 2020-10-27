package org.sunt.sqlanalysis.lineage.model.table

open class SelectTable(related: List<ITable>) : LogicalTable(related) {

    final override var alias: String = ""

}