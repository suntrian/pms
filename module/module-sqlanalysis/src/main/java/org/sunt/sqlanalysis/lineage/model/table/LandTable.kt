package org.sunt.sqlanalysis.lineage.model.table

abstract class LandTable(val catalog: String?, val schema: String?, val table: String) : LogicalTable(emptyList()) {

    override fun getAlias(): String {
        return this.table
    }

    fun getFullName(): String {
        return (if (catalog != null) "$catalog." else "") + (if (schema != null) "$schema." else "") + table;
    }

}