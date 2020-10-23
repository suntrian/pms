package org.sunt.sqlanalysis.lineage.model.table

import org.sunt.sqlanalysis.lineage.model.field.IField

abstract class LandTable(final override val fullTableName: FullTableName) : ActualTable, LogicalTable(emptyList()) {

    override var alias: String = fullTableName.table
    override val fields: List<IField> = emptyList()


    fun getFullName(): String {
        return fullTableName.getFullName()
    }

}