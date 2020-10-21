package org.sunt.sqlanalysis.lineage.model.field

import org.sunt.sqlanalysis.lineage.model.Relation
import org.sunt.sqlanalysis.lineage.model.table.LogicalTable
import java.util.*

abstract class LogicalField(override val table: LogicalTable) : ILogicalField {

    init {
        table.fields.toMutableList().add(this)
    }

    private var relations: MutableList<Relation> = LinkedList()

    override fun getRelated(): MutableList<Relation> {
        return this.relations
    }

}