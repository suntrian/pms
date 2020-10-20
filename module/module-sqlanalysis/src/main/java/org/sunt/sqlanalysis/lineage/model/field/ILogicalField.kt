package org.sunt.sqlanalysis.lineage.model.field

import org.sunt.sqlanalysis.lineage.model.Relation
import org.sunt.sqlanalysis.lineage.model.table.LogicalTable

interface ILogicalField : IField{

    override fun getTable(): LogicalTable

    fun getRelated(): List<Relation>

}