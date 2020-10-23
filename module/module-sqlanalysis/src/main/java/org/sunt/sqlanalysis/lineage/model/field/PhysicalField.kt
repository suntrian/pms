package org.sunt.sqlanalysis.lineage.model.field

import org.sunt.sqlanalysis.lineage.model.table.PhysicalTable

open class PhysicalField(val name: String, final override val table: PhysicalTable) : IPhysicalField {

    final override var alias = name;

    init {
        table.fields.add(this)
    }

    override fun getFullName(): String {
        return table.getFullName() + "." + name
    }

}