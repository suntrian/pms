package org.sunt.sqlanalysis.lineage.model.table

class CreateTable(fullTableName: FullTableName) : LandTable(fullTableName) {

    var isColumnCreateTable: Boolean = false

    var comment: String? = null
    var temporary = false

}