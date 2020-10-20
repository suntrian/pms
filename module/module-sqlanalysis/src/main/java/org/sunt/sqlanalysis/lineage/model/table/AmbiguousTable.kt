package org.sunt.sqlanalysis.lineage.model.table

import org.sunt.sqlanalysis.lineage.model.field.IField
import java.util.*
import kotlin.collections.LinkedHashSet

/**
 * 歧义表，当字段可能同时属于多个表时，使用此类型
 */
class AmbiguousTable(private val tables: Collection<ITable>) : ITable{

    private var fields: List<IField> = emptyList();

    override fun getAlias(): String {
        return ""
    }

    override fun getFields(): List<IField> {
        return fields
    }

}