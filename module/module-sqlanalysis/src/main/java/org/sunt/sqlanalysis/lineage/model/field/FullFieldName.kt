package org.sunt.sqlanalysis.lineage.model.field

data class FullFieldName(val catalog: String?, val schema: String?, val table: String?, val field: String) {

    fun getFullName(): String {
        return ((if (catalog != null) "$catalog." else "") + (if (schema != null) "$schema." else "") + (if (table != null) "$table." else "")) + field
    }

    override fun toString(): String {
        return getFullName()
    }

}