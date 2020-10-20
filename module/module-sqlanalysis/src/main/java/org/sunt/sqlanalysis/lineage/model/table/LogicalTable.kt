package org.sunt.sqlanalysis.lineage.model.table

import org.sunt.sqlanalysis.lineage.model.field.LogicalField

abstract class LogicalTable : ITable {

    private var fields: List<LogicalField> = emptyList()

    private var related: List<ITable> = emptyList()

    override fun getFields(): List<LogicalField> {
        return fields
    }

    fun getRelated() : List<ITable> {
        return this.related
    }

}