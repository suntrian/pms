package org.sunt.sqlanalysis.lineage.model.table

import org.sunt.sqlanalysis.lineage.model.field.PhysicalField
import java.util.*

open class PhysicalTable(val catalog: String?, val schema: String?, val table: String) : ITable {

    constructor(schema: String, table: String): this(null, schema, table)

    constructor(table: String): this(null, null, table)

    private var alias = this.table

    private val fields: MutableList<PhysicalField> = LinkedList()

    override fun getAlias(): String {
        return alias;
    }

    override fun getFields(): MutableList<PhysicalField> {
        return fields
    }

    fun getFullName(): String {
        return (if (catalog!=null) "$catalog." else "") + (if(schema != null) "$schema." else "")+ table;
    }

}