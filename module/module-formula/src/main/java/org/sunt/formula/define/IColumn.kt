package org.sunt.formula.define

interface IColumn {
    val id: String
    val name: String
    val expression: String
    val dataType: DataType
    val format: String?
}