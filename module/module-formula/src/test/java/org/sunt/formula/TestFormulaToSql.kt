package org.sunt.formula

import org.junit.jupiter.api.Test
import org.sunt.formula.define.DataType
import org.sunt.formula.define.IColumn
import org.sunt.formula.define.SqlDialect
import org.sunt.formula.support.TestColumn

class TestFormulaToSql {

    private fun getColumnByName(id: String, dataType: DataType): IColumn {
        return TestColumn(id, dataType)
    }

    private fun getColumnById(id: String, dataType: DataType): IColumn? {
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
    }

    @Test
    fun testWindowFunction() {
        val helper = FormulaHelper.of({ id -> getColumnById(id, DataType.STRING) },
            { name -> getColumnByName(name, DataType.STRING) })

        val map = mapOf(
//            "LAG_OVER(abcd, 1, 'abcd', [ddd], [aaa, bbb])" to "LAG(abcd, 1, 'abcd') OVER ( PARTITION BY ddd ORDER BY aaa, bbb)",
//            "LAG_OVER(abcd, 1, 'abcd', [ddd,eee], ORDER_BY(aaa, ORDER_ITEM(bbb, DESC)))" to "LAG(abcd, 1, 'abcd') OVER (PARTITION BY ddd, eee ORDER BY aaa, bbb DESC)",
//            "LAG_OVER(abcd, 1, 'abcd', PARTITION_BY(ddd,eee), ORDER_BY(aaa, ORDER_ITEM(bbb, DESC)))" to "LAG(abcd, 1, 'abcd') OVER (PARTITION BY ddd, eee ORDER BY aaa, bbb DESC)",
//            "LAG_OVER(abcd, 1, 'abcd', null, ORDER_BY(aaa, ORDER_ITEM(bbb, DESC)))" to "LAG(abcd, 1, 'abcd') OVER (ORDER BY aaa, bbb DESC)",
            "LEAD_OVER(abcd, [], [aaaa])" to "LEAD(abcd) OVER (ORDER BY aaaa)"
        )


        for ((f, e) in map) {
            val stmt = helper.toSql(f, SqlDialect.HIVE)
            println(stmt)
            println("expect:$e")
            println("actual:" + stmt.expression)
        }

    }

}