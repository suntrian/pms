package org.sunt.sqlanalysis.lineage.model.table

import org.sunt.sqlanalysis.lineage.model.field.LogicalField
import java.util.*

abstract class LogicalTable(private var related: List<ITable>) : ITable {

    private val fields: MutableList<LogicalField> = LinkedList()

    override fun getFields(): MutableList<LogicalField> {
        return fields
    }

    fun getRelated(): List<ITable> {
        return this.related
    }

}