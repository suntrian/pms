package org.sunt.sqlanalysis.lineage.listener

import org.sunt.sqlanalysis.lineage.model.table.LogicalTable

interface LineageListener {

    fun getTables(): List<LogicalTable>

}