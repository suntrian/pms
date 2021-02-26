package org.sunt.formula

import org.sunt.formula.define.IColumn

interface ColumnInterface {

    fun getColumnById(id: String): IColumn?

    fun getColumnByName(name: String): IColumn?

}