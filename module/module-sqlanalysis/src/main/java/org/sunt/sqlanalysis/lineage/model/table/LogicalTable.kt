package org.sunt.sqlanalysis.lineage.model.table

import org.sunt.sqlanalysis.lineage.model.field.LogicalField

abstract class LogicalTable(private var related: List<ITable>) : ITable {

    override val fields: MutableList<LogicalField> = mutableListOf()

    fun getRelated(): List<ITable> {
        return this.related
    }

}