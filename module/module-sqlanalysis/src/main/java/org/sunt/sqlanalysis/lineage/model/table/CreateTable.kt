package org.sunt.sqlanalysis.lineage.model.table

class CreateTable(fullTableName: FullTableName) : LandTable(fullTableName) {

    constructor(schema: String?, table: String) : this(FullTableName(null, schema, table))

    constructor(table: String) : this(FullTableName(null, null, table))

}