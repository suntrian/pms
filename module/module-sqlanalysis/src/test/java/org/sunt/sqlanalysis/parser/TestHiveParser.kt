package org.sunt.sqlanalysis.parser

import org.junit.jupiter.api.Assertions
import org.junit.jupiter.api.DisplayName
import org.junit.jupiter.api.function.ThrowingSupplier
import org.junit.jupiter.params.ParameterizedTest
import org.junit.jupiter.params.provider.MethodSource
import org.slf4j.LoggerFactory
import org.sunt.sqlanalysis.common.PreHandlerChain
import org.sunt.sqlanalysis.common.SqlDialect
import org.sunt.sqlanalysis.support.FileVisitor
import java.util.stream.Stream


class TestHiveParser {


    @DisplayName("hive解析测试")
    @ParameterizedTest(name = "[{index}] {0}")
    @MethodSource("fileDecompressed")
    fun testParseHiveSql(fileName: String, fileContent: String) {
        log.info("解析{}", fileName)
        val sql = PreHandlerChain.handle(fileContent)
        log.info("SQL: \n{}", sql)
        val tables = Assertions.assertDoesNotThrow( ThrowingSupplier{ SqlParser.parse(sql, SqlDialect.HIVE, ErrorStrategy.THROW) } )
        Assertions.assertTrue(tables.isNotEmpty())
        for (table in tables) {
            log.info("{}", table)
        }
    }


    companion object {

        private val log = LoggerFactory.getLogger(TestHiveParser::class.java)

        @JvmStatic
        fun fileDecompressed(): Stream<Array<String>> {
            val zipFilePath = "src/test/resources/sqls/hive/shzq_600+.zip"
            return FileVisitor.visitZipFiles(zipFilePath)
        }

    }

}