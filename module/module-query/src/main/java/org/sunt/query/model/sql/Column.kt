package org.sunt.query.model.sql

import org.sunt.query.define.*

interface SelectItem : SqlItem {
    var alias: String?
}

interface Predicate : SelectItem

class AsteriskColumn(override var alias: String?) : SelectItem {

    override fun toSql(dialect: SqlDialect): String = "*"

}

class AggregateColumn(
    val aggType: AggregateType,
    val distinct: Boolean,
    val field: SelectItem,
    override var alias: String?
) : SelectItem {
    init {
        if (field is AggregateColumn || field is AggregateFormulaColumn || field is WindowFormulaColumn) {
            throw IllegalStateException("")
        }
    }

    override fun toSql(dialect: SqlDialect): String {
        return aggType.name + (if (distinct) "( DISTINCT " else "( ") + field.toSql(dialect) + ")"
    }

}

abstract class UnaryColumn(override var alias: String?) : SelectItem

abstract class MultiColumn(override var alias: String?) : SelectItem

class ConstantColumn(var value: String, alias: String?) : UnaryColumn(alias) {
    override fun toSql(dialect: SqlDialect): String = value
}

class PhysicalColumn(alias: String?) : UnaryColumn(alias) {

    override fun toSql(dialect: SqlDialect): String {
        TODO("Not yet implemented")
    }

}


abstract class FormulaColumn(alias: String?) : MultiColumn(alias) {

    override fun toSql(dialect: SqlDialect): String {
        TODO("Not yet implemented")
    }

}

class NormalFormulaColumn(alias: String?) : FormulaColumn(alias)

class AggregateFormulaColumn(alias: String?) : FormulaColumn(alias)

class WindowFormulaColumn(alias: String?) : FormulaColumn(alias)

class RawFormulaColumn(alias: String?) : FormulaColumn(alias)

class OrderByColumn(val field: SelectItem, val asc: Boolean = true) : SqlItem {

    override fun toSql(dialect: SqlDialect): String {
        return field.alias ?: field.toSql(dialect) + (if (asc) " ASC" else " DESC")
    }

}

class GroupByColumn(val selectItem: SelectItem) : SqlItem {
    override fun toSql(dialect: SqlDialect): String {
        return selectItem.toSql(dialect)
    }

}

abstract class BasePredicate(val field: SelectItem, val op: Operator, override var alias: String? = "") : Predicate

open class BinaryPredicate(left: SelectItem, predicateOp: PredicateOperator, val right: SelectItem) :
    BasePredicate(left, predicateOp) {
    override fun toSql(dialect: SqlDialect): String {
        TODO("Not yet implemented")
    }
}

open class TriplePredicate(
    left: SelectItem,
    predicateOp: PredicateOperator,
    val value1: SelectItem,
    val value2: SelectItem
) : BasePredicate(left, predicateOp) {
    override fun toSql(dialect: SqlDialect): String {
        TODO("Not yet implemented")
    }
}

open class MultiPredicate(left: SelectItem, predicateOp: PredicateOperator, vararg val value: SelectItem) :
    BasePredicate(left, predicateOp) {
    override fun toSql(dialect: SqlDialect): String {
        TODO("Not yet implemented")
    }
}

open class LogicalPredicate(left: SelectItem, logicalOp: LogicalOperator, val right: SelectItem) :
    BasePredicate(left, logicalOp) {
    override fun toSql(dialect: SqlDialect): String {
        TODO("Not yet implemented")
    }
}