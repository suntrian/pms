package org.sunt.sqlanalysis.common

interface PreHandler {

    fun handle(sql: String): String

    val ord
        get() = Int.MAX_VALUE

}