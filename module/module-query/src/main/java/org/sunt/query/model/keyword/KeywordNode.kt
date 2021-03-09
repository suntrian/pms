package org.sunt.query.model.keyword

import org.sunt.query.define.AggregateType
import org.sunt.query.define.LogicalOperator
import org.sunt.query.define.Operator
import org.sunt.query.define.PredicateOperator
import org.sunt.query.model.metadata.IColumn
import java.math.BigDecimal

sealed class KeywordNode {
    abstract val text: String
}

class LimitNode internal constructor(override val text: String, val limit: Int, val offset: Int = 0) : KeywordNode()

abstract class ValueNode internal constructor(override val text: String) : KeywordNode()

class StringValueNode internal constructor(text: String) : ValueNode(text)

class NumberValueNode internal constructor(text: String) : ValueNode(text) {
    val number = BigDecimal(text)
}

abstract class PredicateNode internal constructor(override val text: String) : KeywordNode()

class BoolValueNode private constructor(isTrue: Boolean) : PredicateNode(if (isTrue) "TRUE" else "FALSE") {
    companion object {
        @JvmField
        val TrueNode = BoolValueNode(true)

        @JvmField
        val FalseNode = BoolValueNode(false)
    }
}

class DateValueNode internal constructor(text: String, val format: String = "") : ValueNode(text)

open class ColumnNode internal constructor(text: String, val column: IColumn) : PredicateNode(text)

open class FunctionNode internal constructor(text: String) : PredicateNode(text)

//用作排序的关键词组
class SortNode internal constructor(override val text: String, val fieldNode: PredicateNode, val asc: Boolean = true) : KeywordNode()

//用作聚合的关键词组
class AggregateNode internal constructor(override val text: String, fieldNode: PredicateNode, val aggregateType: AggregateType, val distinct: Boolean = false) : KeywordNode()

abstract class OperatorNode internal constructor(text: String, val fieldNode: PredicateNode, val op: Operator) : PredicateNode(text)

//用途过滤条件的关键词组
abstract class FilterNode internal constructor(text: String, fieldNode: PredicateNode, predicateOp: PredicateOperator) : OperatorNode(text, fieldNode, predicateOp)

//逻辑组合
class LogicalNode internal constructor(text: String, left: PredicateNode, logicOp: LogicalOperator, val right: PredicateNode) : OperatorNode(text, left, logicOp)

//一元操作的关键词组
class SoloFilterNode internal constructor(text: String, fieldNode: PredicateNode, predicateOp: PredicateOperator) : FilterNode(text, fieldNode, predicateOp) {
    init {
        if (predicateOp.argSize != 0) throw IllegalStateException("${predicateOp}要求参数")
    }
}

//二元操作的关键词组
open class BinaryFilterNode internal constructor(text: String, fieldNode: PredicateNode, predicateOp: PredicateOperator, val value: ValueNode) :
    FilterNode(text, fieldNode, predicateOp) {
    init {
        if (predicateOp.argSize != 1) throw IllegalStateException("${predicateOp}要求两个参数")
    }
}

//字段值查询的关键词
class ColumnValueNode internal constructor(text: String, fieldNode: PredicateNode, value: ValueNode) : BinaryFilterNode(text, fieldNode, PredicateOperator.EQUAL, value)

//三元操作的关键词组
class TripleFilterNode internal constructor(text: String, fieldNode: PredicateNode, predicateOp: PredicateOperator, val first: ValueNode, val second: ValueNode) :
    FilterNode(text, fieldNode, predicateOp)

class MultiFilterNode internal constructor(text: String, fieldNode: PredicateNode, predicateOp: PredicateOperator, val values: List<ValueNode>) :
    FilterNode(text, fieldNode, predicateOp)
