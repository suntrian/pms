package org.sunt.formula.support

import org.sunt.formula.define.DataType
import org.sunt.formula.define.IColumn

class TestColumn @JvmOverloads constructor(
    override val id: String,
    override val name: String,
    override val expression: String,
    override val dataType: DataType,
    override val format: String = ""
) : IColumn {


}