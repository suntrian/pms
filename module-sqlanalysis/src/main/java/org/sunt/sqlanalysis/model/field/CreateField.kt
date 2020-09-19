package org.sunt.sqlanalysis.model.field

import org.sunt.sqlanalysis.model.table.CreateTable

class CreateField(val name: String, val table: CreateTable) : LogicalField() {

    private var dataType: String = ""
    private var default: Unit? = null
    private var primaryKey: Boolean = false
    private var autoIncrement: Boolean = false;
    private var comment: String = ""

    override fun getAlias(): String {
        return this.name
    }

}