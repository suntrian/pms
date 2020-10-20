package org.sunt.sqlanalysis.lineage.model.field

import org.sunt.sqlanalysis.lineage.model.table.PhysicalTable

open class PhysicalField(val name: String, private val table: PhysicalTable) : IPhysicalField {

    init {
        table.getFields().add(this)
    }

    private var alias = name;

    override fun getTable(): PhysicalTable {
        return this.table
    }

    override fun getAlias(): String {
        return alias;
    }

    override fun getFullName(): String {
        return table.getFullName() + "." + name
    }

}