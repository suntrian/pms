package org.sunt.sqlanalysis.parser

import org.sunt.sqlanalysis.common.SqlDialect
import org.sunt.sqlanalysis.exception.DialectUnsupportedException
import org.sunt.sqlanalysis.model.Table
import org.sunt.sqlanalysis.parser.hive.HiveParser
import org.sunt.sqlanalysis.parser.mysql.MysqlParser
import org.sunt.sqlanalysis.parser.oracle.OracleParser

interface SqlParser {

    fun parse(sql: String): List<Table>

    companion object {

        @JvmOverloads
        @JvmStatic
        fun parse(sql: String, dialect: SqlDialect, errorStrategy: ErrorStrategy=ErrorStrategy.RETURN): List<Table> {
            val sqlParser = when (dialect) {
                SqlDialect.HIVE, SqlDialect.IMPALA -> if (ErrorStrategy.SWALLOW == errorStrategy) HiveParser.INSTANCE else HiveParser(errorStrategy)
                SqlDialect.MYSQL, SqlDialect.MARIADB -> if (ErrorStrategy.SWALLOW == errorStrategy) MysqlParser.INSTANCE else MysqlParser(errorStrategy)
                SqlDialect.ORACLE -> if (ErrorStrategy.SWALLOW == errorStrategy) OracleParser.INSTANCE else OracleParser(errorStrategy)
                else -> throw DialectUnsupportedException(dialect)
            }
            return sqlParser.parse(sql)
        }
    }

    fun hasExceptions(): Boolean
    fun exceptions(): List<Exception>
}