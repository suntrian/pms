package org.sunt.sqlanalysis.lineage.model.table

import org.sunt.sqlanalysis.lineage.model.field.PhysicalField
import java.util.*
import java.util.regex.Matcher

open class PhysicalTable(catalog: String?, schema: String?, table: String) : ActualTable, ITable {

    final override val catalog: String?
    final override val schema: String?
    final override val table: String
    final override var alias: String
    final override val fields: MutableList<PhysicalField>

    init {
        var matcher: Matcher? = null
        if (catalog == null && schema == null && fullNamePattern.matcher(table).also { matcher = it }.matches()) {
            this.catalog = matcher!!.group("cat")
            this.schema = matcher!!.group("scm")
            this.table = matcher!!.group("tab")
        } else {
            this.catalog = catalog
            this.schema = schema
            this.table = table
        }
        this.alias = this.table
        this.fields = LinkedList()
    }

    constructor(schema: String, table: String) : this(null, schema, table)

    constructor(table: String) : this(null, null, table)

    fun getFullName(): String {
        return (if (catalog != null) "$catalog." else "") + (if (schema != null) "$schema." else "") + table;
    }

}