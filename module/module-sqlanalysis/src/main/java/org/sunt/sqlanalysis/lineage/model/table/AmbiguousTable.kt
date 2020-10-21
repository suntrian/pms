package org.sunt.sqlanalysis.lineage.model.table

import org.sunt.sqlanalysis.lineage.model.field.IField

/**
 * 歧义表，当字段可能同时属于多个表时，使用此类型
 */
class AmbiguousTable(private val tables: Collection<ITable>) : LogicalTable(emptyList()) {

    override var fields: List<IField> = emptyList();

    override val alias: String
        get() = ""

}