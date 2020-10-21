package org.sunt.sqlanalysis.common

import java.util.concurrent.CopyOnWriteArrayList
import java.util.regex.Pattern

object PreHandler : Handler {

    private val handlers: MutableList<Handler> = CopyOnWriteArrayList()

    fun register(handler: Handler) {
        this.handlers.add(handler)
    }

    override fun handle(sql: String): String {
        var xsql: String = sql
        for (handler in handlers.sortedBy { it.order() }) {
            xsql = handler.handle(xsql)
        }
        return xsql
    }

    override fun order(): Int = 0
}

interface Handler {

    fun handle(sql: String): String

    fun order(): Int

}

class PlaceholderHandler(placeholders: List<String>) : Handler {

    private val placeholderPatterns: List<Pattern> = placeholders.map { Pattern.compile(it) }.toList()

    init {
        PreHandler.register(this)
    }

    override fun handle(sql: String): String {
        var xsql = sql
        for (placeholderPattern in this.placeholderPatterns) {
            val matcher = placeholderPattern.matcher(xsql)
            xsql = matcher.replaceAll("88888888")
        }
        return xsql
    }

    override fun order(): Int = 1

}