package org.sunt.query.model.metadata

interface SchemaInterface : ColumnInterface {

    override fun getColumnById(id: String): IColumn?

    override fun getColumnByName(name: String): IColumn?

    fun getTableRelation(first: ITable, second: ITable): List<TableRelation>


}