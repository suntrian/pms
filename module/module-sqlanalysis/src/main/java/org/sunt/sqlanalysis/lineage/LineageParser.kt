package org.sunt.sqlanalysis.lineage

import org.sunt.sqlanalysis.common.PreHandlerChain
import org.sunt.sqlanalysis.common.SqlDialect
import org.sunt.sqlanalysis.exception.DialectUnsupportedException

object LineageParser {


    fun parse(dialect: SqlDialect, sql: String): List<LineageNode> {
        val isql = PreHandlerChain.handle(sql)
        return when (dialect) {
            else -> throw DialectUnsupportedException(dialect)
        }

    }

}
