package org.sunt.sqlanalysis.lineage.model.field

import org.sunt.sqlanalysis.lineage.model.table.ITable
import org.sunt.sqlanalysis.lineage.model.table.PhysicalTable

interface IPhysicalField : IField{

    override fun getTable(): PhysicalTable

    fun getFullName(): String

}