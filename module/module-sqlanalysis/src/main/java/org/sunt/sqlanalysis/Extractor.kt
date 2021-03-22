package org.sunt.sqlanalysis

import org.sunt.sqlanalysis.common.SqlDialect
import org.sunt.sqlanalysis.exception.DialectUnsupportedException
import org.sunt.sqlanalysis.lineage.LineageExtractor
import org.sunt.sqlanalysis.lineage.LineageNode
import org.sunt.sqlanalysis.lineage.LineagePair
import org.sunt.sqlanalysis.model.Table
import org.sunt.sqlanalysis.parser.hive.HiveParser
import org.sunt.sqlanalysis.parser.mysql.MysqlParser
import org.sunt.sqlanalysis.parser.oracle.OracleParser

class Extractor (private val sql: String,
                 private val dialect: SqlDialect
) {

    val tables: List<Table> = parseSql(this.sql, this.dialect)

    val lineageNodes: List<LineageNode> by lazy { this.tables.flatMap { extractLineageNodes(it) } }

    val lineagePairs: List<LineagePair> by lazy { extractLineagePairs(this.lineageNodes) }

    companion object {

        @JvmStatic
        fun parseSql(sql: String, dialect: SqlDialect): List<Table> {
            return when(dialect) {
                SqlDialect.ORACLE -> OracleParser.INSTANCE.parse(sql)
                SqlDialect.MYSQL, SqlDialect.MARIADB -> MysqlParser.INSTANCE.parse(sql)
                SqlDialect.HIVE, SqlDialect.IMPALA -> HiveParser.INSTANCE.parse(sql)
                else -> throw DialectUnsupportedException(dialect)
            }
        }

        @JvmStatic
        fun extractLineageNodes(table: Table): List<LineageNode> {
            return LineageExtractor.extract(table)
        }

        @JvmStatic
        fun extractLineagePairs(lineageNodes: List<LineageNode>): List<LineagePair> {
            return LineageExtractor.extract(lineageNodes)
        }

    }

}