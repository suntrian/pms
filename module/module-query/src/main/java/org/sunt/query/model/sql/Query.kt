package org.sunt.query.model.sql

import org.sunt.query.define.SqlDialect

class Query(val mainQuery: QueryTable, val with: Map<String, QueryTable> = emptyMap()) : FromItem {

    override fun toSql(dialect: SqlDialect): String {
        if (with.isEmpty()) return mainQuery.toSql(dialect)
        val sqlBuilder = StringBuilder("WITH ")
        val withIter = with.iterator()
        for ((name, subQuery) in withIter) {
            sqlBuilder.append(name).append(" AS (").append(subQuery.toSql(dialect)).append(" )")
            if (withIter.hasNext()) {
                sqlBuilder.append(", ")
            }
        }
        sqlBuilder.append(mainQuery)
        return mainQuery.toSql(dialect)
    }

}