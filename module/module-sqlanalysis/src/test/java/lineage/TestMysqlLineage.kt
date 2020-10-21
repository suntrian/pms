package lineage

import org.junit.jupiter.api.Test
import org.slf4j.LoggerFactory
import org.sunt.sqlanalysis.common.SqlProduct
import org.sunt.sqlanalysis.lineage.LineageParser

class TestMysqlLineage {

    private val logger = LoggerFactory.getLogger(TestMysqlLineage::class.java)

    @Test
    fun testMysqlLineage() {
        val sql = "select x.a from X x WHERE x.b = 123 AND x.c IN (SElecT DistincT id FROM Y y)"
        val tables = LineageParser.parse(SqlProduct.MYSQL, sql)
        for (table in tables) {
            logger.info("alias: {}", table.alias)
        }
    }

}