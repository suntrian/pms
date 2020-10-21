package org.sunt.sqlanalysis.lineage.listener

import org.sunt.sqlanalysis.grammar.plsql.PlSqlParserBaseListener
import org.sunt.sqlanalysis.lineage.model.table.LogicalTable
import java.util.*

class OracleLineageParserListener : PlSqlParserBaseListener(), LineageListener {

    private val tables: List<LogicalTable> = LinkedList()

    override fun getTables(): List<LogicalTable> {
        return this.tables
    }

}