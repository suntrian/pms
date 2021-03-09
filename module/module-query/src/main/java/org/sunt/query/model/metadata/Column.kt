package org.sunt.query.model.metadata

import org.sunt.query.define.DataType

interface Column {
    val id: String
    val name: String
    val expression: String
    val dataType: DataType
    val format: String?
}