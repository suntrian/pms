package org.sunt.sqlanalysis.model.field

import org.sunt.sqlanalysis.model.table.PhysicalTable
import java.util.*

open class PhysicalField(val name: String, val table: PhysicalTable) : IField {

    private var alias = name;

    /**
     * join关系
     * 可以出现在 table1 join table2 on table1.x = table2.y ;
     * 还有出现在 from table1, table2 where table1.x = table2.y;
     * 等
     * 就可以认为 table1.x is equivalent to table2.y
     */
    private val equivalents: List<IField> = LinkedList()
    override fun getEquivalents(): List<IField> {
        return equivalents
    }

    private val lineages: List<IField> = LinkedList()

    override fun getAlias(): String {
        return alias;
    }

}