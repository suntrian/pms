package org.sunt.sqlanalysis.lineage.model.table

import org.sunt.sqlanalysis.lineage.model.field.PhysicalField
import java.util.*

open class PhysicalTable(final override val fullTableName: FullTableName) : ActualTable, ITable {

    final override var alias: String = fullTableName.table
    final override val fields: MutableList<PhysicalField> = LinkedList()

    fun getFullName(): String {
        return fullTableName.getFullName()
    }

}