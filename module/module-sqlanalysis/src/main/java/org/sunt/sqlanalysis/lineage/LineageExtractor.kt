package org.sunt.sqlanalysis.lineage

import org.sunt.sqlanalysis.exception.IllegalSyntaxException
import org.sunt.sqlanalysis.lineage.RelationType.REPLICATION
import org.sunt.sqlanalysis.model.*
import java.util.*
import kotlin.math.min

object LineageExtractor{

    @JvmOverloads
    fun extract(table: Table, targetFields: List<SelectItem> = emptyList()): List<LineageNode> {
        return when (table) {
            is CreateTable -> {
                if (table.sourceTable.isEmpty() || table.sourceTable.all { it is EmptyTable }
                    || table.sourceTable.all { it == null }) {
                    return emptyList()
                }
                val srcTable = table.sourceTable[0]
                val fromNodes = extract(srcTable)
                val toNodes = table.fields.map { LineageNode(it, table) }
                if (toNodes.size == fromNodes.size) {
                    toNodes.onEachIndexed { index, lineageNode -> lineageNode.addRelatedNode(REPLICATION, fromNodes[index]) }
                } else if (toNodes.size ==1 && toNodes.all { it.isAsteriskField }) {
                    for (fromNode in fromNodes) {
                        toNodes[0].addRelatedNode(REPLICATION, fromNode)
                    }
                } else if (toNodes.isEmpty()) {
                    return fromNodes.map { LineageNode(AtomicField.of(it.field.label), table).apply { addRelatedNode(REPLICATION, it) } }
                } else {
                    throw IllegalSyntaxException("不认识这样的语法: $table")
                }
                return toNodes
            }
            is InsertTable -> {
                if (table.sourceTable.isEmpty() || table.sourceTable.all { it is EmptyTable }
                    || table.sourceTable.all{ it == null }) {
                    return emptyList()
                }
                val toNodes = if (!table.isRealFields){
                    listOf(LineageNode(AsteriskField("*"), table))
                } else {
                    table.fields.map { LineageNode(it, table) }
                }
                val srcTable = table.sourceTable[0]
                val fromNodes = extract(srcTable)
                if (toNodes.size == fromNodes.size) {
                    toNodes.onEachIndexed { index, lineageNode -> lineageNode.addRelatedNode(REPLICATION, fromNodes[index]) }
                } else if (toNodes.size == 1 && toNodes.all { it.field is AsteriskField }){
                    fromNodes.forEach { toNodes[0].addRelatedNode(REPLICATION, it) }
                } else {
                    throw IllegalSyntaxException("Insert表的字段个数与Select的字段个数不符: ${toNodes.size} <- ${fromNodes.size}")
                }
                return toNodes
            }
            is SetOperatorTable -> {
                var srcNodes = extract(table.firstTable)
                val srcNodesList = table.otherTables.map { it.setTable }.map { extract(it) }.toMutableList().also { it.add(0, srcNodes) }

                //找到没有 * 字段的查询，如果都存在，则使用第一个
                var indexWithoutAsteriskField = -1
                for ((index, tmpSrcNode) in srcNodesList.withIndex()) {
                    if (tmpSrcNode.none { it is AsteriskField }) {
                        indexWithoutAsteriskField = index
                        break
                    }
                }
                val toNodes = if (indexWithoutAsteriskField >= 0) {
                    srcNodes = srcNodesList[indexWithoutAsteriskField]
                    srcNodes.map { AtomicField(it.field.label) }.map { LineageNode(it, table) }
                } else {
                    //源可能有多个*字段， 需要保证目标最多只有一个*字段，否则很难处理
                    listOf(LineageNode(AsteriskField("*"), table))
                }

                val toNodeSize = toNodes.size
                for (tmpSrcNodes in srcNodesList) {
                    if (toNodeSize == tmpSrcNodes.size) {
                        for ((index, toNode) in toNodes.withIndex()) {
                            toNode.addRelatedNode(REPLICATION, tmpSrcNodes[index])
                        }
                    } else if (toNodeSize == 1 && toNodes.all { it is AsteriskField }){
                        for (tmpSrcNode in tmpSrcNodes) {
                            toNodes[0].addRelatedNode(REPLICATION, tmpSrcNode)
                        }
                    } else {
                        //最复杂的情况，如 select a, b, c, d, e, f, g FROM (select a, b, A.*, B.*, f, g from X, A, B, Y ....)
                        //采用最左最大匹配原则，如上例中， a->a, b->b, A.* -> c,d  B.*->e, f->f, g->g
                        var toNodeIndex = 0
                        for (tmpSrcNode in tmpSrcNodes) {
                            if (tmpSrcNode.field !is AsteriskField) {
                                toNodes[toNodeIndex++].addRelatedNode(REPLICATION, tmpSrcNode)
                            } else {
                                for (i in toNodeIndex..min(toNodeSize-1, (toNodeSize-tmpSrcNodes.size+toNodeIndex))) {
                                    toNodes[i].addRelatedNode(REPLICATION, tmpSrcNode)
                                }
                                toNodeIndex += toNodeSize - tmpSrcNodes.size + 1
                            }
                        }
                    }
                }
                if (targetFields.isNotEmpty()) {
                    if (targetFields.any{ it is AsteriskField}) {
                        return toNodes
                    }
                    val targetFieldLabels = targetFields.map { it.label.toLowerCase() }
                    return toNodes.filter { it is AsteriskField || targetFieldLabels.contains(it.field.label.toLowerCase()) }
                }
                return toNodes
            }
            is SelectTable -> {
                val srcTable = table.sourceTable[0] ?: return emptyList()
                val srcNodes = mutableListOf<LineageNode>()
                for (field in table.fields) {
                    val srcNode = LineageNode(field, table).also { srcNodes.add(it) }
                    for ((relType, atomField) in field.getRelatedAtomicField()) {
                        for (lineageNode in extract(srcTable, listOf(atomField))) {
                            srcNode.addRelatedNode(relType, lineageNode)
                        }
                    }
                }
                if (targetFields.isEmpty() || targetFields.any{ it is AsteriskField}) {
                    return srcNodes
                }
                val targetFieldLabels = targetFields.map { it.label.toLowerCase() }
                return srcNodes.filter { it.field is AsteriskField || targetFieldLabels.contains(it.field.label.toLowerCase()) }
            }
            is JoinTable -> {
                val innerTables = table.joinParts.map { it.joinTable }.toMutableList().apply { add(0, table.fromTable) }
                val innerTableMap = innerTables.map { it.label to it }.toMap(TreeMap(String.CASE_INSENSITIVE_ORDER))
                val toNodes = mutableListOf<LineageNode>()
                for (targetField in targetFields) {
                    when (targetField) {
                        is AtomicField -> {
                            if ((targetField.columnName.table?:"").isEmpty()) {
                                if (targetField is AsteriskField) {
                                    //只能有一个select * 字段
                                    return innerTables.flatMap { extract(it, targetFields) }.onEach { it.isPossible = true }
                                }
                                else toNodes.addAll(innerTables.flatMap { extract(it, listOf(targetField)) }.onEach { it.isPossible = true })
                            } else {
                                //可以有多个 select X.*字段
                                var srcTable: FromItem? = null
                                if (innerTableMap[targetField.columnName.table]?.also { srcTable = it } == null) {
                                    throw IllegalSyntaxException("表${targetField.columnName.table}不存在")
                                }
                                toNodes.addAll(extract(srcTable!!, listOf(targetField)))
                            }
                        }
                    }
                }
                return toNodes
            }
            is PhysicalTable -> {
                return targetFields.map { LineageNode(it.clone().also { f-> ( f as AtomicField).alias= Alias.EMPTY }, table) }
            }
            is DeleteTable -> emptyList()
            is UpdateTable -> emptyList()
            is DropTable -> emptyList()
            else -> return emptyList()
        }
    }

    fun extract(lineageNodes: List<LineageNode>): List<LineagePair> {
        val result = mutableListOf<LineagePair>()
        for (lineageNode in lineageNodes) {
            val physicalFields = extractPhysicalFields(lineageNode)
            result.addAll(physicalFields.map { LineagePair(it.second, lineageNode.columnName, it.first) })
        }
        return result
    }

    private fun extractPhysicalFields(rootNode: LineageNode): List<Pair<RelationType, ColumnName>> {
        val list = mutableListOf<Pair<RelationType, ColumnName>>()
        for (relatedNode in rootNode.relatedNodes) {
            val relType = relatedNode.key
            val subNode = relatedNode.value;
            if (subNode.table is PhysicalTable) {
                list.add(Pair(relType, subNode.columnName))
            } else {
                val subList = extractPhysicalFields(subNode).map { if ( it.first priorTo relType) it else Pair(relType, it.second) }
                list.addAll(subList)
            }
        }
        return list
    }
}

