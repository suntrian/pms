package org.sunt.query.model.sql

import org.sunt.query.define.SetType
import org.sunt.query.define.SqlDialect
import java.util.*

interface FromItem : SqlItem

class PhysicalTable : FromItem {

    override fun toSql(dialect: SqlDialect): String {
        TODO("Not yet implemented")
    }

}

class QueryTable : FromItem, SelectItem {

    var distinct: Boolean = false
    val select: MutableList<SelectItem> = LinkedList()
    val from: MutableList<FromItem> = LinkedList()
    val where: Predicate? = null
    val groupBy: MutableList<GroupByColumn> = LinkedList()
    var having: Predicate? = null
    val orderBy: MutableList<OrderByColumn> = LinkedList()
    var limit: Int? = null
    var offset: Int? = null
    override var alias: String? = null

    override fun toSql(dialect: SqlDialect): String {
        val sqlBuilder = StringBuilder("SELECT ")
        if (distinct) {
            sqlBuilder.append("DISTINCT ")
        }
        if (select.isEmpty()) {
            throw IllegalStateException("查询字段为空")
        } else {
            val selectIter = select.iterator()
            for (selectItem in selectIter) {
                sqlBuilder.append(selectItem.toSql(dialect))
                if (selectIter.hasNext()) {
                    sqlBuilder.append(", ")
                }
            }
        }

        if (from.isEmpty()) {
            return sqlBuilder.toString()
        } else {
            sqlBuilder.append(" FROM ")
            val fromIter = from.iterator()
            for (fromItem in fromIter) {
                sqlBuilder.append(fromItem.toSql(dialect))
                if (fromIter.hasNext()) {
                    sqlBuilder.append(", ")
                }
            }
        }

        if (where != null) {
            sqlBuilder.append(" WHERE ").append(where.toSql(dialect))
        }

        if (groupBy.isNotEmpty()) {
            sqlBuilder.append(" GROUP BY ")
            val groupIter = groupBy.iterator()
            for (groupByColumn in groupIter) {
                sqlBuilder.append(groupByColumn.toSql(dialect))
                if (groupIter.hasNext()) {
                    sqlBuilder.append(", ")
                }
            }
            if (having != null) {
                sqlBuilder.append(" HAVING ").append(having!!.toSql(dialect))
            }
        }

        if (orderBy.isNotEmpty()) {
            sqlBuilder.append(" ORDER BY ")
            val orderIter = orderBy.iterator()
            for (orderByColumn in orderIter) {
                sqlBuilder.append(orderByColumn.toSql(dialect))
                if (orderIter.hasNext()) {
                    sqlBuilder.append(", ")
                }
            }
        }

        if (limit != null) {
            sqlBuilder.append(" LIMIT ").append(limit)
            if (offset != null) {
                sqlBuilder.append(", ").append(offset)
            }
        }
        return sqlBuilder.toString()
    }

}

class SetTable : FromItem {

    lateinit var first: FromItem
    var extra: List<Pair<SetType, FromItem>> = emptyList()

    override fun toSql(dialect: SqlDialect): String {
        TODO("Not yet implemented")
    }

}