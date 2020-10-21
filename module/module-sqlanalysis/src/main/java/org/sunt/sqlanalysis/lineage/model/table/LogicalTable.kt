package org.sunt.sqlanalysis.lineage.model.table

abstract class LogicalTable(private var related: List<ITable>) : ITable {

    fun getRelated(): List<ITable> {
        return this.related
    }

}