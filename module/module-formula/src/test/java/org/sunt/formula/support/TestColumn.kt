package org.sunt.formula.support

import org.sunt.formula.define.DataType
import org.sunt.formula.define.IColumn

class TestColumn : IColumn {

    private val id: String

    private val name: String

    private val expression: String

    private val dataType: DataType

    private val format: String

    @JvmOverloads
    constructor(id: String, name: String, expression: String, dataType: DataType, format: String = "") {
        this.id = id
        this.name = name
        this.expression = expression
        this.dataType = dataType
        this.format = format
    }

    override fun getId(): String {
        return id
    }

    override fun getName(): String {
        return name
    }

    override fun getExpression(): String {
        return this.expression
    }

    override fun getDataType(): DataType {
        return this.dataType
    }

    override fun getFormat(): String {
        return this.format
    }
}