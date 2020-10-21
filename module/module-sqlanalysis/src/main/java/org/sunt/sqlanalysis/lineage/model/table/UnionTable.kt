package org.sunt.sqlanalysis.lineage.model.table

open class UnionTable(related: List<ITable>) : SelectTable(related) {

    var allAsteriskField = false

}