package org.sunt.sqlanalysis.lineage.model.table

class UpdateTable(catalog: String?, schema: String?, table: String) : LandTable(catalog, schema, table) {

    constructor(schema: String?, table: String) : this(null, schema, table)

    constructor(table: String) : this(null, null, table)


}