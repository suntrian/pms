package org.sunt.query.formula

interface ColumnInterface {

    fun getColumnById(id: String): Column?

    fun getColumnByName(name: String): Column?

}