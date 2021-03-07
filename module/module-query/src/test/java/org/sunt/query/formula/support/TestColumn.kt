package org.sunt.query.formula.support

import org.sunt.query.define.ColumnType
import org.sunt.query.define.DataType
import org.sunt.query.model.column.IColumn

class TestColumn @JvmOverloads constructor(
    override val id: String,
    override val name: String,
    override val expression: String,
    override val dataType: DataType,
    override val format: String = "",
    override val columnType: ColumnType = ColumnType.DIMENSION
) : IColumn {


}