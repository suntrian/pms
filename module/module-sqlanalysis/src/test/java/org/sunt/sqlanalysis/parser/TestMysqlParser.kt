package org.sunt.sqlanalysis.parser

import org.junit.jupiter.api.Assertions
import org.junit.jupiter.api.Test
import org.sunt.sqlanalysis.parser.mysql.MysqlParser
import org.sunt.sqlanalysis.support.FileVisitor

class TestMysqlParser {

    @Test
    fun testMysqlProcedure() {
        val filePath = "src/test/resources/sqls/mysql/crm.sql"
        val fileContent = FileVisitor.visitFile(filePath)
        val tables = MysqlParser.INSTANCE.parse(fileContent)
        Assertions.assertEquals(6, tables.size)
        for (table in tables) {
            println(table)
        }

    }

}