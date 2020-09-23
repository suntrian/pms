package org.sunt.sqlanalysis.model.table

open class PhysicalTable(val schema: String, val name: String) : ITable {

    private var alias = this.name;

    override fun getAlias(): String {
        return alias;
    }


}