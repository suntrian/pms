package org.sunt.sqlanalysis.lineage.model.field

import org.sunt.sqlanalysis.lineage.model.table.CreateTable

class CreateField(val name: String, table: CreateTable) : LogicalField(table) {

    override val alias: String = name

    var dataType: String = ""
    var dataLength: Int? = null
    var dataPrecision: Int? = null
    var default: Any? = null
    var nullable = true
    var primaryKey: Boolean = false
    var unique: Boolean = false
    var autoIncrement: Boolean = false;
    var comment: String = ""
    var partitionKey = false
    var indexKey = false
    var foreignKey = false
    var referenceKeys = emptyList<String>()

}