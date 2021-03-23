package org.sunt.sqlanalysis.common

import java.util.concurrent.CopyOnWriteArrayList

object PreHandlerChain : PreHandler {

    private val handlers: MutableList<PreHandler> = CopyOnWriteArrayList()

    init {
        handlers.add(Utf8BomPreHandler)
        handlers.add(PlaceholderPreHandler("%d{yyyyMMdd}//%d{yyyyMM}"))
    }

    override fun handle(sql: String): String {
        var xsql: String = sql
        for (handler in handlers.sortedBy { it.ord }) {
            xsql = handler.handle(xsql)
        }
        return xsql
    }

}
