package org.sunt.query.model.sql

import org.sunt.query.define.SqlDialect

class Predicate : SqlItem {

    override fun toSql(dialect: SqlDialect): String {
        TODO("Not yet implemented")
    }

    companion object {
        @JvmField
        val EMPTY = Predicate()
    }

}

