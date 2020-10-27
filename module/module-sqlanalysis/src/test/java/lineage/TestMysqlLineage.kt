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
            for (field in table.fields) {
                logger.info("field:{}", field.alias)
            }
        }
    }

    @Test
    fun testMysqlCreateTable() {
        val sql = "CREATE TABLE xyz( x int(6) not null primary key auto_increment , y varchar(6) default 'aaa', z timestamp comment 'zzzzz') ";
        val tables = LineageParser.parse(SqlProduct.MYSQL, sql)
        for (table in tables) {
            logger.info("table:{}", table.alias)
            for (field in table.fields) {
                logger.info("field:{}", field.alias)
                logger.info("field:{}", field)
            }
        }

    }

}