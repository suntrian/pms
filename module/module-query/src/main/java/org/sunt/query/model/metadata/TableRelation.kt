package org.sunt.query.model.metadata

class TableRelation(
    val source: ITable,
    val target: ITable,
    val relations: List<Pair<IColumn, IColumn>>
)