package org.sunt.sqlanalysis.lineage.model.field

import org.sunt.sqlanalysis.lineage.model.table.CreateTable

class CreateField(val name: String, table: CreateTable) : LogicalField(table) {

    private var dataType: String = ""
    private var dataLength: Int? = null
    private var dataPrecision: Int? = null
    private var default: Any? = null
    private var primaryKey: Boolean = false
    private var autoIncrement: Boolean = false;
    private var comment: String = ""

    override fun getAlias(): String {
        return this.name
    }

}