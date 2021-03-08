package org.sunt.query.model.sql

import org.sunt.query.define.AggregateType
import org.sunt.query.define.SqlDialect

interface Statement : SqlItem

interface SelectItem : Statement {
    var alias: String?
}

class AsteriskColumn(override var alias: String?) : SelectItem {

    override fun toSql(dialect: SqlDialect): String = "*"

}

class AggregateColumn(val aggType: AggregateType, val distinct: Boolean, val field: SelectItem, override var alias: String?) : SelectItem {
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

