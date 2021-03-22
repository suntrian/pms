package org.sunt.sqlanalysis.lineage

import org.junit.jupiter.api.Test
import org.sunt.sqlanalysis.Extractor
import org.sunt.sqlanalysis.common.SqlDialect
import org.sunt.sqlanalysis.support.FileVisitor
import org.sunt.sqlanalysis.support.PrintHelper

class TestMysqlLineage {

    @Test
    fun extractLineageNodes() {
        val fileContent = FileVisitor.visitFile("src/test/resources/sqls/mysql/crm.sql")
        val extractor = Extractor(fileContent, SqlDialect.MYSQL)
        for (table in extractor.tables) {
            println(table)
        }
        PrintHelper.printLineage(extractor.lineageNodes)
        PrintHelper.printFinalLineage(extractor.lineagePairs)

    }


}