package org.sunt.sqlanalysis.common

object Utf8BomPreHandler : PreHandler {

    private val UTF8_BOM = "\uFEFF"

    override fun handle(sql: String): String {
        return if (sql.startsWith(UTF8_BOM)) {
            sql.substring(1)
        } else sql
    }

    override val ord: Int
        get() = 0

}