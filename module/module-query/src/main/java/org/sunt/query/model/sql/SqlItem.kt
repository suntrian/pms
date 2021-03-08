package org.sunt.query.model.sql

import org.sunt.query.define.SqlDialect

interface SqlItem {

    fun toSql(dialect: SqlDialect): String

}