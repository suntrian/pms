package org.sunt.sqlanalysis.lineage.model.table

import org.sunt.sqlanalysis.lineage.model.field.IField
import java.util.regex.Matcher

abstract class LandTable(catalog: String?,
                         schema: String?,
                         table: String
) : ActualTable, LogicalTable(emptyList()) {
    final override val catalog: String?
    final override val schema: String?
    final override val table: String
    override var alias: String = table
    override val fields: List<IField> = emptyList()


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

    }


    fun getFullName(): String {
        return (if (catalog != null) "$catalog." else "") + (if (schema != null) "$schema." else "") + table;
    }

}