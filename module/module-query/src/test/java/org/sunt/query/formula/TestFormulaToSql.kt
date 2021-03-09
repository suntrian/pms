package org.sunt.query.formula

import org.junit.jupiter.api.Assertions
import org.junit.jupiter.api.Test
import org.sunt.query.define.DataType
import org.sunt.query.define.SqlDialect
import org.sunt.query.formula.function.StatementInfo
import org.sunt.query.formula.support.AllMatchColumn
import org.sunt.query.formula.support.ExactColumn
import org.sunt.query.formula.support.TestColumn
import org.sunt.query.formula.support.removeBlank
import org.sunt.query.model.metadata.Column

class TestFormulaToSql {

    private fun getColumnByName(id: String, dataType: DataType): Column? =
        AllMatchColumn(mapOf(id to dataType)).getColumnByName(id)

    private fun getColumnById(id: String, dataType: DataType): Column? =
        AllMatchColumn(mapOf(id to dataType)).getColumnById(id)


    @Test
    fun testExpressionToSql() {

        val helper = FormulaHelper.of({ id -> getColumnById(id, DataType.DECIMAL) },
            { name -> getColumnByName(name, DataType.DECIMAL) })
        val stmt = helper.toSql("x + y/3^4", SqlDialect.MYSQL);
        println(stmt)
        println(stmt.expression)
        Assertions.assertEquals("x+y/POWER(3, 4)".removeBlank(), stmt.expression.removeBlank())


        val helper2 = FormulaHelper.of({ id -> getColumnById(id, DataType.STRING) },
            { name -> getColumnByName(name, DataType.STRING) })
        val stmt2 = helper2.toSql("x+y+z+X", SqlDialect.MYSQL);

        println(stmt2)
        println(stmt2.expression)
        Assertions.assertEquals("CONCAT(x, y, z, X)".removeBlank(), stmt2.expression.removeBlank())
    }


    @Test
    fun testFunctionToSql() {
        val helper = FormulaHelper.of({ id -> getColumnById(id, DataType.DECIMAL) },
            { name -> getColumnByName(name, DataType.DECIMAL) });
        val stmt = helper.toSql("SUBSTRING(IFNULL(TO_STRING(xyz), ''), 2, 6) ", SqlDialect.MYSQL);
        println(stmt)
        println(stmt.expression)
        Assertions.assertEquals("SUBSTRING(IFNULL(CAST(xyz AS CHAR), ''), 2, 6)".removeBlank(), stmt.expression.removeBlank())

        val stmt2 = helper.toSql("SUBSTR(IFNULL(TO_STRING(xyz), ''), 2, 6) ", SqlDialect.MYSQL);
        println(stmt2)
        println(stmt2.expression)
        Assertions.assertEquals("SUBSTRING(IFNULL(CAST(xyz AS CHAR), ''), 2, 6)".removeBlank(), stmt2.expression.removeBlank())

        val stmt3 = helper.toSql("COALESCE(abc, def, ghi, 2, 3)", SqlDialect.HIVE)
        println(stmt3)
        println(stmt3.expression)
        Assertions.assertEquals("COALESCE(abc,def,ghi,2,3)".removeBlank(), stmt3.expression.removeBlank())

        val stmt4 = helper.toSql("abc?: def ?: ghi?:2?:3?:'sfw'", SqlDialect.HIVE)
        println(stmt4)
        println(stmt4.expression)
        Assertions.assertEquals("COALESCE(abc, def, ghi, 2, 3, 'sfw')".removeBlank(), stmt4.expression.removeBlank())

        val stmt5 = helper.toSql("abc?: def", SqlDialect.HIVE)
        println(stmt5)
        println(stmt5.expression)
        Assertions.assertEquals("COALESCE(abc, def)".removeBlank(), stmt5.expression.removeBlank())

//        val stmt6 = helper.toSql("COALESCE(abc, def, ghi, 2, 3, 'sfee')", SqlDialect.HIVE)
//        println(stmt6)
//        println(stmt6.expression)

        val stmt7 = helper.toSql("COALESCE(abc)", SqlDialect.HIVE)
        println(stmt7)
        println(stmt7.expression)
    }

    @Test
    fun testGroupFunction() {
        val helper = FormulaHelper.of({ id -> getColumnById(id, DataType.STRING) },
            { name -> getColumnByName(name, DataType.STRING) })
        val stmt = helper.toSql("GROUP_COUNT(abcd, eefe, aaab)", SqlDialect.HIVE)
        println(stmt)
        println(stmt.expression)
        Assertions.assertEquals("COUNT(abcd)".removeBlank(), stmt.expression.removeBlank())

        val stmt2 = helper.toSql("GROUP_COUNT(DISTINCT, abcd, eefe, aaab)", SqlDialect.HIVE)
        println(stmt2)
        println(stmt2.expression)
        Assertions.assertEquals("COUNT(DISTINCT abcd)".removeBlank(), stmt2.expression.removeBlank())

        val stmt3 = helper.toSql("GROUP_count(distinct, abcd?:efgh?:0, eefe, aaab)", SqlDialect.HIVE)
        println(stmt3)
        println(stmt3.expression)
        println("COUNT(DISTINCT COALESCE(abcd, efgh, 0))")
        Assertions.assertEquals("COUNT(DISTINCT COALESCE(abcd, efgh, 0))".removeBlank().toUpperCase(), stmt3.expression.removeBlank().toUpperCase())
    }

    @Test
    fun testWindowFunction() {
        val helper = FormulaHelper.of({ id -> getColumnById(id, DataType.STRING) },
            { name -> getColumnByName(name, DataType.STRING) })

        val map = mapOf(
            "LAG_OVER(abcd, 1, 'abcd', [ddd], [aaa, bbb])" to "LAG(abcd, 1, 'abcd') OVER ( PARTITION BY ddd ORDER BY aaa, bbb)",
            "LAG_OVER(abcd, 1, 'abcd', [ddd,eee], ORDER_BY(aaa, ORDER_ITEM(bbb, DESC)))" to "LAG(abcd, 1, 'abcd') OVER (PARTITION BY ddd, eee ORDER BY aaa, bbb DESC)",
            "LAG_OVER(abcd, 1, 'abcd', PARTITION_BY(ddd,eee), ORDER_BY(aaa, ORDER_ITEM(bbb, DESC)))" to "LAG(abcd, 1, 'abcd') OVER (PARTITION BY ddd, eee ORDER BY aaa, bbb DESC)",
            "LAG_OVER(abcd, 1, 'abcd', null, ORDER_BY(aaa, ORDER_ITEM(bbb, DESC)))" to "LAG(abcd, 1, 'abcd') OVER (ORDER BY aaa, bbb DESC)",
            "LEAD_OVER(abcd, [], [aaaa])" to "LEAD(abcd) OVER (ORDER BY aaaa)",
            "GROUP_COUNT(DISTINCT, abcd, DDDD, FFFFF)" to "COUNT(DISTINCT abcd)",
            "MAX_OVER(abcd, [], [aaaa], ROWS, -1, 1)" to "MAX(abcd) OVER ( ORDER BY aaaa ROWS BETWEEN 1 PRECEDING AND 1 FOLLOWING)",
            "MIN_OVER(abcd, [], [aaaa])" to "MIN(abcd) OVER (ORDER BY aaaa)",
        )


        for ((f, e) in map) {
            println("FUNCTION: $f")
            val stmt = helper.toSql(f, SqlDialect.HIVE)
            println(stmt)
            println("EXPECT: $e")
            println("ACTUAL: " + stmt.expression)
            Assertions.assertEquals(e.removeBlank(), stmt.expression.removeBlank())
        }

    }

    @Test
    fun testRawSql() {
        val helper = FormulaHelper.of(AllMatchColumn(emptyMap()))

        val map = mapOf(
            "RAW_SQL('CONCAT({0})', abcd, bbbb, ccc, dddd)" to "CONCAT(abcd, bbbb, ccc, dddd)",
            "RAW_SQL('CONCAT({1}, {2}, {3}, {0})', abcd, bbbb, ccc)" to "CONCAT(abcd, bbbb, ccc)",
            "RAW_SQL(\"DATEDIFF({1}, {2}, {3})\", abcd, bcde, 'year')" to "DATEDIFF(abcd, bcde, 'year')"
        )

        for ((f, e) in map) {
            println("FUNCTION: $f")
            val stmt = helper.toSql(f, SqlDialect.HIVE)
            println(stmt)
            println("EXPECT: $e")
            println("ACTUAL: " + stmt.expression)
            Assertions.assertEquals(e.removeBlank(), stmt.expression.removeBlank())
        }
    }

    @Test
    fun testErrorRawSql() {
        val helper = FormulaHelper.of(AllMatchColumn(emptyMap()))

        val map = mapOf(
            "RAW_SQL('CONCAT({    100     })', abcd, bbbb, ccc, dddd)" to "CONCAT(bbbb)",
            "RAW_SQL('CONCAT({1}, {2}, {4}, {0})', abcd, bbbb, ccc)" to "CONCAT(abcd, bbbb, ccc)",
            "RAW_SQL(\"DATEDIFF({1}, {2}, {4})\", abcd, bcde, 'year')" to "DATEDIFF(abcd, bcde, 'year')"
        )

        for ((f, e) in map) {
            println("FUNCTION: $f")
            Assertions.assertThrows(IllegalStateException::class.java) {
                val stmt = helper.toSql(f, SqlDialect.HIVE)
                println(stmt)
                println("EXPECT: $e")
                println("ACTUAL: " + stmt.expression)
                Assertions.assertEquals(e.removeBlank(), stmt.expression.removeBlank())
            }
        }
    }

    @Test
    fun testDefaultArgFunction() {
        val helper = FormulaHelper.of(
            AllMatchColumn(
                mapOf(
                    "abcd" to DataType.DATETIME,
                    "bcde" to DataType.DATETIME,
                    "cdef" to DataType.STRING
                )
            )
        )

        val map = mapOf(
            "TO_DOUBLE(cdef)" to "CAST(cdef AS DECIMAL(38, 2))",
            "DATEDIFF(abcd, bcde)" to "DATEDIFF(abcd, bcde)",
            "DATEDIFF(abcd, bcde, 'day')" to "DATEDIFF(abcd, bcde)",
            "DATEDIFF(abcd, bcde, 'year')" to "YEAR(abcd) - YEAR(bcde)",
//            "DATEDIFF(abcd, bcde, \"year\")" to "YEAR(abcd) - YEAR(bcde)",
//            "DATEDIFF(abcd, bcde, \"day\")" to "DATEDIFF(abcd, bcde)",
        )

        for ((f, e) in map) {
            println("FUNCTION: $f")
            val stmt = helper.toSql(f, SqlDialect.HIVE)
            println(stmt)
            println("EXPECT: $e")
            println("ACTUAL: " + stmt.expression)
            Assertions.assertEquals(e.removeBlank(), stmt.expression.removeBlank())
        }
    }

    @Test
    fun testAmbiguousFunction() {
        val helper = FormulaHelper.of(AllMatchColumn(mapOf("abcd" to DataType.INTEGER)))
        val formula = "STDDEV_OVER(abcd, [], [bcde])"
        val stmt = helper.toSql(formula, SqlDialect.HIVE)
        println(stmt.expression)
        Assertions.assertEquals("STDDEV(abcd) OVER ( ORDER BY bcde)".removeBlank(), stmt.expression.removeBlank())

    }

    @Test
    fun testGroupConcat() {
        val helper = FormulaHelper.of(AllMatchColumn(mapOf("abcd" to DataType.STRING)))
        val stmt1 = helper.toSql("GROUP_CONCAT(abcd, ',', bcde, cdef)", SqlDialect.HIVE);
        println(stmt1.expression)
        Assertions.assertEquals("GROUP_CONCAT(abcd, ',')", stmt1.expression)

        val stmt2 = helper.toSql("GROUP_CONCAT(abcd, bcde, cdef, defg)", SqlDialect.HIVE);
        println(stmt2.expression)
        Assertions.assertEquals("GROUP_CONCAT(abcd)", stmt2.expression)

    }

    @Test
    fun testDateConvert() {
        val helper = FormulaHelper.of(
            ExactColumn(
                mapOf(
                    "abcd" to TestColumn("1234", "abcd", "abcd", DataType.DATE),
                    "bcde" to TestColumn("2345", "bcde", "bcde", DataType.DATETIME),
                    "cdef" to TestColumn("3456", "cdef", "cdef", DataType.STRING, "yyyy-MM-dd"),
                    "defg" to TestColumn("4567", "defg", "defg", DataType.STRING, "yyyyMMdd"),
                    "efgh" to TestColumn("5678", "efgh", "efgh", DataType.INTEGER, "yyyyMMdd"),
                )
            )
        )
        var stmt: StatementInfo
        stmt = helper.toSql("YEAR(abcd)", SqlDialect.HIVE)
        println(stmt.expression)
        Assertions.assertEquals("YEAR(abcd)", stmt.expression)

        stmt = helper.toSql("QUARTER(bcde)", SqlDialect.HIVE)
        println(stmt.expression)
        Assertions.assertEquals("CEILING(MONTH(bcde)/3)", stmt.expression)

        stmt = helper.toSql("QUARTER(bcde)", SqlDialect.MYSQL)
        println(stmt.expression)
        Assertions.assertEquals("QUARTER(bcde)", stmt.expression)

        stmt = helper.toSql("QUARTER(defg)", SqlDialect.HIVE)
        println(stmt.expression)
        Assertions.assertEquals(
            "CEIL(CAST(SUBSTRING(defg, 5, 2) AS DECIMAL) / 3)".removeBlank(),
            stmt.expression.removeBlank()
        )

        stmt = helper.toSql("DATE_ROLLUP(cdef, 'WEEK')", SqlDialect.HIVE)
        println(stmt.expression)
        Assertions.assertEquals("CONCAT(SUBSTRING(cdef, 1, 4), '-', WEEKOFYEAR(TO_TIMESTAMP(cdef, 'yyyy-MM-dd')))".removeBlank(), stmt.expression.removeBlank())

    }
}