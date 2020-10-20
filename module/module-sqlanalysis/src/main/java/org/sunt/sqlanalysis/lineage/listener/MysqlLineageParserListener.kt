package org.sunt.sqlanalysis.lineage.listener

import org.sunt.sqlanalysis.grammar.mysql.MySqlParser
import org.sunt.sqlanalysis.grammar.mysql.MySqlParserBaseListener
import org.sunt.sqlanalysis.lineage.model.table.LogicalTable
import org.sunt.sqlanalysis.lineage.model.table.SelectTable
import java.util.*

class MysqlLineageParserListener: MySqlParserBaseListener() {

    private val tables: MutableList<LogicalTable> = LinkedList()

    override fun exitDmlStatement(ctx: MySqlParser.DmlStatementContext) {
        if (ctx.selectStatement() != null) {
            this.tables.add(select(ctx.selectStatement()))
        } else if (ctx.insertStatement() != null) {

        } else if (ctx.deleteStatement() != null) {

        } else if (ctx.updateStatement() != null) {

        } else if (ctx.replaceStatement() != null) {

        } else {

        }
    }

    override fun exitDdlStatement(ctx: MySqlParser.DdlStatementContext) {
        if (ctx.createTable() != null) {

        } else if (ctx.createView() != null) {

        } else if (ctx.createProcedure() != null) {

        } else if (ctx.createFunction() != null) {

        }
    }


    private fun select(ctx: MySqlParser.SelectStatementContext): SelectTable {

    }



}