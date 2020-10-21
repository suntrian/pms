package org.sunt.sqlanalysis.lineage.model.field

import org.sunt.sqlanalysis.lineage.model.table.ITable


interface IField {

    val table: ITable

    val alias: String

}