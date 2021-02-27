package org.sunt.formula

import org.junit.jupiter.api.Test
import org.sunt.formula.define.DataType
import org.sunt.formula.define.IColumn
import org.sunt.formula.define.SqlDialect
import org.sunt.formula.support.TestColumn

class TestFormulaToSql {

    private fun getColumnByName(id: String, dataType: DataType): IColumn {
        println("请求字段NAME:${id}")
        return TestColumn(id, dataType)
    }

    private fun getColumnById(id: String, dataType: DataType): IColumn? {
        println("请求字段ID:${id}")
        return TestColumn(id, dataType)
    }


    @Test
    fun testExpressionToSql() {

        val helper = FormulaHelper.of({ id -> getColumnById(id, DataType.DECIMAL) },
            { name -> getColumnByName(name, DataType.DECIMAL) })
        val stmt = helper.toSql("x + y/3^4", SqlDialect.MYSQL);
        println(stmt)
        println(stmt.expression)

        val helper2 = FormulaHelper.of({ id -> getColumnById(id, DataType.STRING) },
            { name -> getColumnByName(name, DataType.STRING) })
        val stmt2 = helper2.toSql("x+y+z+X", SqlDialect.MYSQL);

        println(stmt2)
        println(stmt2.expression)
    }


    @Test
    fun testFunctionToSql() {
        val helper = FormulaHelper.of({ id -> getColumnById(id, DataType.DECIMAL) },
            { name -> getColumnByName(name, DataType.DECIMAL) });
        val stmt = helper.toSql("SUBSTRING(IFNULL(TO_STRING(xyz), ''), 2, 6) ", SqlDialect.MYSQL);
        println(stmt)
        println(stmt.expression)

        val stmt2 = helper.toSql("SUBSTR(IFNULL(TO_STRING(xyz), ''), 2, 6) ", SqlDialect.MYSQL);
        println(stmt2)
        println(stmt2.expression)

        val stmt3 = helper.toSql("COALESCE(abc, def, ghi, 2, 3)", SqlDialect.HIVE)
        println(stmt3)
        println(stmt3.expression)

        val stmt4 = helper.toSql("abc?: def ?: ghi?:2?:3?:'sfw'", SqlDialect.HIVE)
        println(stmt4)
        println(stmt4.expression)

        val stmt5 = helper.toSql("abc?: def", SqlDialect.HIVE)
        println(stmt5)
        println(stmt5.expression)

        val stmt6 = helper.toSql("COALESCE(abc, def, ghi, 2, 3, 'sfee')", SqlDialect.HIVE)
        println(stmt6)
        println(stmt6.expression)

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

        val stmt2 = helper.toSql("GROUP_COUNT(DISTINCT, abcd, eefe, aaab)", SqlDialect.HIVE)
        println(stmt2)
        println(stmt2.expression)

        val stmt3 = helper.toSql("GROUP_count(distinct, abcd?:efgh?:0, eefe, aaab)", SqlDialect.HIVE)
        println(stmt3)
        println(stmt3.expression)
        println("COUNT(DISTINCT COALESCE(abcd, efgh, 0))")
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
        }

    }

}