package org.sunt.sqlanalysis.parser

import org.junit.jupiter.api.Assertions
import org.junit.jupiter.api.function.ThrowingSupplier
import org.junit.jupiter.params.ParameterizedTest
import org.junit.jupiter.params.provider.MethodSource
import org.slf4j.LoggerFactory
import org.sunt.sqlanalysis.common.PreHandlerChain
import org.sunt.sqlanalysis.common.SqlDialect
import org.sunt.sqlanalysis.support.FileVisitor
import java.nio.charset.Charset
import java.util.stream.Stream

class TestOracleParser {

    @ParameterizedTest(name = "[{index}] {0}")
    @MethodSource("listFiles")
    fun testParseOracleSql(fileName: String, fileContent: String ) {
        log.info("解析{}", fileName)
        val sql = PreHandlerChain.handle(fileContent)
        log.info("SQL: \n{}", sql)
        val tables = Assertions.assertDoesNotThrow( ThrowingSupplier{ SqlParser.parse(sql, SqlDialect.ORACLE, ErrorStrategy.THROW) } )
        Assertions.assertTrue(tables.isNotEmpty())
        for (table in tables) {
            log.info("{}", table)
        }
    }

    companion object {
        private val log = LoggerFactory.getLogger(TestOracleParser::class.java)

        @JvmStatic
        fun listFiles(): Stream<Array<String>> {
            val oracleRoot = "src/test/resources/sqls/oracle/oracle.zip"
            return FileVisitor.visitZipFiles(oracleRoot, Charset.forName("GBK"))
        }

    }

}