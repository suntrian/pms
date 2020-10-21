package org.sunt.sqlanalysis.exception

import org.sunt.sqlanalysis.common.SqlProduct

class DialectUnsupportedException : RuntimeException {

    var dialect: SqlProduct? = null

    constructor(dialect: SqlProduct) : super("暂不支持${dialect.name}的SQL解析")

    constructor(message: String) : super(message)
}