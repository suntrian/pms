package org.sunt.sqlanalysis.lineage.model.table

import org.sunt.sqlanalysis.lineage.model.field.IField


interface ITable {

    val alias: String

    val fields: List<IField>

}