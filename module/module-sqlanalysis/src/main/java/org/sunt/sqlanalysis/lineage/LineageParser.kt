package org.sunt.sqlanalysis.lineage

import org.antlr.v4.runtime.CharStream
import org.antlr.v4.runtime.CharStreams
import org.antlr.v4.runtime.CommonTokenStream
import org.antlr.v4.runtime.misc.ParseCancellationException
import org.sunt.sqlanalysis.common.CaseCharStream
import org.sunt.sqlanalysis.common.PreHandler
import org.sunt.sqlanalysis.common.SqlProduct
import org.sunt.sqlanalysis.exception.DialectUnsupportedException
import org.sunt.sqlanalysis.exception.SqlParseException
import org.sunt.sqlanalysis.grammar.mysql.MySqlLexer
import org.sunt.sqlanalysis.grammar.mysql.MySqlParser
import org.sunt.sqlanalysis.grammar.plsql.PlSqlLexer
import org.sunt.sqlanalysis.grammar.plsql.PlSqlParser
import org.sunt.sqlanalysis.lineage.listener.MysqlLineageParserListener
import org.sunt.sqlanalysis.lineage.listener.OracleLineageParserListener
import org.sunt.sqlanalysis.lineage.model.table.LogicalTable

object LineageParser {


    fun parse(product: SqlProduct, sql: String): List<LogicalTable> {
        val isql = PreHandler.handle(sql)
        return when (product) {
            SqlProduct.MYSQL -> MysqlParser.parse(isql)
            SqlProduct.ORACLE -> OracleParser.parse(isql)
            else -> throw DialectUnsupportedException(product)
        }

    }

}

interface ILineageParser {

    @Throws(SqlParseException::class)
    fun parse(sql: String): List<LogicalTable>

}

object MysqlParser : ILineageParser {

    override fun parse(sql: String): List<LogicalTable> {
        val charStream: CharStream = CaseCharStream(CharStreams.fromString(sql), true)
        val mysqlLexer = MySqlLexer(charStream)
        val tokenStream = CommonTokenStream(mysqlLexer)
        val mysqlParser = MySqlParser(tokenStream)
        val mysqlListener = MysqlLineageParserListener()
        mysqlParser.addParseListener(mysqlListener)
        try {
            mysqlParser.root()
        } catch (e: ParseCancellationException) {
            throw SqlParseException(e.message)
        }
        return mysqlListener.getTables()
    }

}

private object OracleParser : ILineageParser {

    override fun parse(sql: String): List<LogicalTable> {
        val charStream: CharStream = CaseCharStream(CharStreams.fromString(sql), true)
        val oracleLexer = PlSqlLexer(charStream)
        val tokenStream = CommonTokenStream(oracleLexer)
        val oracleParser = PlSqlParser(tokenStream)
        val oracleListener = OracleLineageParserListener()
        oracleParser.addParseListener(oracleListener)
        try {
            oracleParser.sql_script()
        } catch (e: ParseCancellationException) {
            throw SqlParseException(e.message)
        }
        return oracleListener.getTables()
    }

}