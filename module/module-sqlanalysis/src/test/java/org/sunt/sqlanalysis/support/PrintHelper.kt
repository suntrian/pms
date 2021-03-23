package org.sunt.sqlanalysis.support

import org.slf4j.Logger
import org.slf4j.LoggerFactory
import org.sunt.sqlanalysis.lineage.LineageNode
import org.sunt.sqlanalysis.lineage.LineagePair
import org.sunt.sqlanalysis.model.ColumnName

object PrintHelper {

    private val log: Logger = LoggerFactory.getLogger(PrintHelper::class.java)

    @JvmStatic
    fun printLineage(lineageNodes: List<LineageNode>) {
        for (lineageNode in lineageNodes) {
            val columnName: ColumnName = lineageNode.columnName
            for ((key, value) in lineageNode.relatedNodes) {
                if (value.isPossible) {
                    log.info("{} -{} ~~> {}", value.columnName, key.name, columnName)
                } else {
                    log.info("{} -{} --> {}", value.columnName, key.name, columnName)
                }
            }
        }
        val childrenNodes = lineageNodes.flatMap { it.relatedNodes.map { obj->obj.value } }
        if (childrenNodes.isNotEmpty()) {
            printLineage(childrenNodes)
        }
    }

    @JvmStatic
    fun printFinalLineage(lineagePairs: List<LineagePair>) {
        for (lineage in lineagePairs) {
            log.info("{} - {} - {}", lineage.source, lineage.relationType.name, lineage.target)
        }
    }



}