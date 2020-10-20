package org.sunt.sqlanalysis.lineage.model.field

import org.sunt.sqlanalysis.lineage.model.table.ITable


interface IField {

    fun getTable(): ITable

    fun getAlias(): String

}