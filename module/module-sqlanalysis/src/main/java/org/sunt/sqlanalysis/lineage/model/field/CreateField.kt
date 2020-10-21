package org.sunt.sqlanalysis.lineage.model.field

import org.sunt.sqlanalysis.lineage.model.table.CreateTable

class CreateField(val name: String, override val table: CreateTable) : LogicalField(table) {

    override val alias: String = name

    private var dataType: String = ""
    private var dataLength: Int? = null
    private var dataPrecision: Int? = null
    private var default: Any? = null
    private var primaryKey: Boolean = false
    private var autoIncrement: Boolean = false;
    private var comment: String = ""


}