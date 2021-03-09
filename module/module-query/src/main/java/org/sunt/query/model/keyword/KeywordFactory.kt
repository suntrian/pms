package org.sunt.query.model.keyword

import org.sunt.query.define.AggregateType
import org.sunt.query.define.LogicalOperator
import org.sunt.query.define.PredicateOperator
import org.sunt.query.model.metadata.IColumn

object KeywordFactory {

    fun produceColumn(text: String, column: IColumn): ColumnNode = ColumnNode(text, column)

    fun produceColumnValue(text: String, column: IColumn): ColumnValueNode = ColumnValueNode(text, ColumnNode(text, column), StringValueNode(text))

    fun produceColumnValue(text: String, columnNode: ColumnNode): ColumnValueNode = ColumnValueNode(text, columnNode, StringValueNode(text))

    fun produceFilter(text: String, column: IColumn, predicateOp: PredicateOperator, vararg values: String): FilterNode {
        return produceFilter(text, ColumnNode("", column), predicateOp, *values)
    }

    fun produceFilter(text: String, predicateNode: PredicateNode, predicateOp: PredicateOperator, vararg values: String): FilterNode {
        return when (predicateOp.argSize) {
            0 -> SoloFilterNode(text, predicateNode, predicateOp)
            1 -> BinaryFilterNode(text, predicateNode, predicateOp, values.getOrNull(0)?.let { StringValueNode(it) } ?: throw IllegalArgumentException("${predicateOp}需要一个参数"))
            2 -> TripleFilterNode(text, predicateNode, predicateOp, values.getOrNull(0)?.let { StringValueNode(it) } ?: throw IllegalArgumentException("${predicateOp}需要二个参数"),
                values.getOrNull(1)?.let { StringValueNode(it) } ?: throw IllegalArgumentException("${predicateOp}需要二个参数")
            )
            else -> MultiFilterNode(text, predicateNode, predicateOp, values.map { StringValueNode(it) })
        }
    }

    fun produceLogical(left: FilterNode, logicOp: LogicalOperator, right: FilterNode): LogicalNode {
        return LogicalNode("", left, logicOp, right)
    }

    fun produceAggregate(text: String, column: IColumn, aggType: AggregateType, distinct: Boolean = false): AggregateNode {
        return produceAggregate(text, ColumnNode("", column), aggType, distinct)
    }

    fun produceAggregate(text: String, fieldNode: PredicateNode, aggType: AggregateType, distinct: Boolean = false): AggregateNode =
        AggregateNode(text, fieldNode, aggType, distinct)

    fun produceSort(text: String, column: IColumn, asc: Boolean = true): SortNode {
        return produceSort(text, ColumnNode("", column), asc)
    }

    fun produceSort(text: String, fieldNode: PredicateNode, asc: Boolean = true): SortNode = SortNode(text, fieldNode, asc)

    fun produceLimit(text: String, limit: Int, offset: Int = 0): LimitNode = LimitNode(text, limit, offset)

}