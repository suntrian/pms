package org.sunt.query.model.column

import org.sunt.query.define.ColumnType
import org.sunt.query.define.DataType

interface IColumn {

    val id: String
    val name: String
    val expression: String
    val dataType: DataType
    val format: String?

    val columnType: ColumnType

}