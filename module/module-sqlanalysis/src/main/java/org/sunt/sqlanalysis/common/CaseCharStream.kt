package org.sunt.sqlanalysis.common

import org.antlr.v4.runtime.CharStream

open class CaseCharStream(private val charStream: CharStream, private val upperCase: Boolean) : CharStream by charStream {

    override fun LA(p0: Int): Int {
        val c = this.charStream.LA(p0)
        return if (this.upperCase && c in 'a'.toInt()..'z'.toInt()) {
            c - ('a'.toInt() - 'A'.toInt())
        } else if (!this.upperCase && c in 'A'.toInt()..'Z'.toInt()) {
            c + ('a'.toInt() - 'A'.toInt())
        } else {
            c
        }
    }

}