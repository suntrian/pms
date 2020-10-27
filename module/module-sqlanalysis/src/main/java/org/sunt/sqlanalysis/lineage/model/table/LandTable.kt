package org.sunt.sqlanalysis.lineage.model.table

abstract class LandTable(final override val fullTableName: FullTableName) : ActualTable, LogicalTable(emptyList()) {

    override var alias: String = fullTableName.table

    fun getFullName(): String {
        return fullTableName.getFullName()
    }

}