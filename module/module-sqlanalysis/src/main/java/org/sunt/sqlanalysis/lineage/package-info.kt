package org.sunt.sqlanalysis.lineage

import org.sunt.sqlanalysis.model.*

fun Expression.getRelatedAtomicField(): List<Pair<RelationType, AtomicField>> {
    return when(this) {
        is AtomicField -> listOf(Pair(RelationType.REPLICATION, this))
        is MultiOperatorField -> mutableListOf<Pair<RelationType, AtomicField>>().apply {
            addAll(field.getRelatedAtomicField().map { Pair(RelationType.CALCULATION, it.second) })
            addAll(values.flatMap { it.getRelatedAtomicField() })
        }
        is BinaryOperatorField -> return mutableListOf<Pair<RelationType, AtomicField>>().apply {
                addAll(left.getRelatedAtomicField().map { if ( it.first priorTo RelationType.CALCULATION) it else Pair(
                    RelationType.CALCULATION, it.second) })
                addAll(right.getRelatedAtomicField().map { if (it.first priorTo RelationType.CALCULATION) it else Pair(
                    RelationType.CALCULATION, it.second) })
            }
        is UnaryOperatorField -> field.getRelatedAtomicField().map { if (it.first priorTo RelationType.TRANSFORMATION) it else Pair(
            RelationType.TRANSFORMATION, it.second) }
        is CastField -> field.getRelatedAtomicField().map { if (it.first  priorTo RelationType.TRANSFORMATION) it else Pair(
            RelationType.TRANSFORMATION, it.second) }
        is IntervalField -> interval.getRelatedAtomicField().map { if (it.first priorTo RelationType.TRANSFORMATION) it else Pair(
            RelationType.TRANSFORMATION, it.second) }
        is WindowField -> {
            val result = params.flatMap { it.getRelatedAtomicField() }
                .map { if (it.first priorTo RelationType.AGGREGATION) it else Pair(RelationType.AGGREGATION, it.second) }
                .toMutableList()
            this.partitionByFields?.flatMap { it.getRelatedAtomicField() }?.map { Pair(RelationType.PARTITION, it.second) }?.onEach { result.add(it) }
            return result
        }
        is FunctionField -> {
            if (functionName.equals("COUNT", true) && params.any{ it.expression.endsWith("*") }) {
                //特殊处理count(*)的情形，不返回到所有字段的血缘关系
                return emptyList()
            }
            //单参数函数，关系设为TRANSFORM类型
            val relType = if (this.isAggregate) RelationType.AGGREGATION else if (params.filterNot { it is KeywordField }.size == 1) RelationType.TRANSFORMATION else RelationType.CALCULATION
            return params.flatMap { it.getRelatedAtomicField() }.map { if (it.first priorTo relType) it else Pair(relType, it.second) }
        }
        is CaseField -> mutableListOf<Pair<RelationType, AtomicField>>().apply {
            addAll(caseExpr?.getRelatedAtomicField()?.map { Pair(RelationType.CONDITION, it.second) }?: emptyList())
            addAll(elseExpr?.getRelatedAtomicField()?.map { if (it.first priorTo RelationType.REPLICATION) it else Pair(
                RelationType.REPLICATION, it.second) }?: emptyList())
            addAll(whenExpr.map { it.`when` }.flatMap { it.getRelatedAtomicField() }.map { Pair(RelationType.CONDITION, it.second) })
            addAll(whenExpr.map { it.then }.flatMap { it.getRelatedAtomicField() }.map { if (it.first priorTo RelationType.REPLICATION) it else Pair(
                RelationType.REPLICATION, it.second) })
        }
        is SelectTable -> fields[0].getRelatedAtomicField()
        is CompositeField -> fields.flatMap { it.getRelatedAtomicField() }
        is ConstantField, is KeywordField -> emptyList()
        else -> emptyList()
    }.distinct()
}

fun Table.getRelatedPhysicalTable(): List<PhysicalTable> {
    return when(this) {
        is PhysicalTable -> listOf(this)
        is JoinTable -> this.joinParts.flatMap { it.joinTable.getRelatedPhysicalTable() }
            .toMutableList().also { it.addAll(this.fromTable.getRelatedPhysicalTable()) }.distinct()
        is SetOperatorTable -> this.otherTables.flatMap { it.setTable.getRelatedPhysicalTable() }
            .toMutableList().also { it.addAll(0, this.firstTable.getRelatedPhysicalTable()) }.distinct()
        is SelectTable -> this.sourceTable.flatMap { it.getRelatedPhysicalTable() }.distinct()
        is LandTable -> this.sourceTable.flatMap { it.getRelatedPhysicalTable() }.distinct()
        is MultiInsertTable -> this.sourceTable.flatMap { it.getRelatedPhysicalTable() }.distinct()
        else -> emptyList()
    }
}