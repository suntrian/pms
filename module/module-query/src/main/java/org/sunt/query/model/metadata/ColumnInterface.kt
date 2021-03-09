package org.sunt.query.model.metadata

interface ColumnInterface {

    fun getColumnById(id: String): Column?

    fun getColumnByName(name: String): Column?

}