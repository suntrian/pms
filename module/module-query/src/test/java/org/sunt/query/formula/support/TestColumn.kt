package org.sunt.query.formula.support

import org.sunt.query.define.DataType
import org.sunt.query.model.metadata.Column

class TestColumn @JvmOverloads constructor(
    override val id: String,
    override val name: String,
    override val expression: String,
    override val dataType: DataType,
    override val format: String = "",
) : Column {

    override fun toString(): String {
        return "TestColumn(id='$id', name='$name', expression='$expression', dataType=$dataType, format='$format')"
    }
}