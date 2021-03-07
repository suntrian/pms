package org.sunt.query.formula

import org.sunt.query.model.column.IColumn

interface ColumnInterface {

    fun getColumnById(id: String): IColumn?

    fun getColumnByName(name: String): IColumn?

}