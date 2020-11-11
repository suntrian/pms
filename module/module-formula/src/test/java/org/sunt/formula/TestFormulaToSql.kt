package org.sunt.formula

import org.junit.jupiter.api.Test
import org.sunt.formula.define.DataType
import org.sunt.formula.define.IColumn
import org.sunt.formula.define.SqlProduct
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

        val helper = FormulaHelper.of({ id -> getColumnById(id, DataType.DECIMAL) }, { name -> getColumnByName(name, DataType.DECIMAL) })
        val stmt = helper.toSql("x + y/3^4", SqlProduct.MYSQL);
        println(stmt)
        println(stmt.expression)

        val helper2 = FormulaHelper.of({ id -> getColumnById(id, DataType.STRING) }, { name -> getColumnByName(name, DataType.STRING) })
        val stmt2 = helper2.toSql("x+y+z+X", SqlProduct.MYSQL);

        println(stmt2)
        println(stmt2.expression)
    }


    @Test
    fun testFunctionToSql() {
        val helper = FormulaHelper.of({ id -> getColumnById(id, DataType.DECIMAL) }, { name -> getColumnByName(name, DataType.DECIMAL) });
        val stmt = helper.toSql("SUBSTRING(IFNULL(TO_STRING(xyz), ''), 2, 6) ", SqlProduct.MYSQL);
        println(stmt)
        println(stmt.expression)
    }

}