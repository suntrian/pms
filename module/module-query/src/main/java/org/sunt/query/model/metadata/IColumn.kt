package org.sunt.query.model.metadata

import org.sunt.query.define.ColumnType

interface IColumn : Column {

    val columnType: ColumnType

}