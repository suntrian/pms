package org.sunt.sqlanalysis.exception

import org.sunt.sqlanalysis.common.SqlDialect

class DialectUnsupportedException : RuntimeException {

    var dialect: SqlDialect? = null

    constructor(dialect: SqlDialect) : super("暂不支持${dialect.name}的SQL解析")

    constructor(message: String) : super(message)
}