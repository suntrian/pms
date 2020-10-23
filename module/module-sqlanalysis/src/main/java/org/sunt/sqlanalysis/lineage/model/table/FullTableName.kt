package org.sunt.sqlanalysis.lineage.model.table

data class FullTableName(val catalog: String?, val schema: String?, val table: String) {

    fun getFullName(): String {
        return (if (catalog != null) "$catalog." else "") + (if (schema != null) "$schema." else "") + table
    }

    override fun toString(): String {
        return getFullName()
    }

}