package org.sunt.sqlanalysis.lineage.model.field

import org.sunt.sqlanalysis.lineage.model.Relation
import org.sunt.sqlanalysis.lineage.model.table.LogicalTable

abstract class LogicalField (private val table: LogicalTable): ILogicalField {

    init {
        table.getFields().add(this)
    }

    private var relations: List<Relation> = emptyList()

    override fun getTable(): LogicalTable {
        return this.table
    }

    override fun getRelated(): List<Relation> {
        return this.relations
    }

}