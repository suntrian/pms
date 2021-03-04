package org.sunt.keyword.define

import org.sunt.formula.define.DataType

interface IColumn {
    val id: String
    val name: String
    val expression: String
    val dataType: DataType
    val format: String?
}