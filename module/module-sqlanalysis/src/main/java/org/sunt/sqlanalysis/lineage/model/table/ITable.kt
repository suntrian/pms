package org.sunt.sqlanalysis.lineage.model.table

import org.sunt.sqlanalysis.lineage.model.field.IField


interface ITable {

    fun getAlias(): String

    fun getFields(): List<IField>
}