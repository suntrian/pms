package org.sunt.formula.support

import org.sunt.formula.define.DataType
import org.sunt.formula.define.IColumn

class TestColumn : IColumn {

    private val expression: String

    private val dataType: DataType

    constructor(expression: String, dataType: DataType) {
        this.expression = expression
        this.dataType = dataType
    }

    override fun getExpression(): String {
        return this.expression
    }

    override fun getDataType(): DataType {
        return this.dataType
    }
}