package org.sunt.sqlanalysis.common

import org.antlr.v4.runtime.CharStream
import org.antlr.v4.runtime.misc.Interval

open class CaseCharStream(private val charStream: CharStream, private val upperCase: Boolean) : CharStream {

    override fun consume() {
        return this.charStream.consume()
    }

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

    override fun mark(): Int {
        return this.charStream.mark()
    }

    override fun release(p0: Int) {
        return this.charStream.release(p0)
    }

    override fun index(): Int {
        return this.charStream.index()
    }

    override fun seek(p0: Int) {
        return this.charStream.seek(p0)
    }

    override fun size(): Int {
        return this.charStream.size()
    }

    override fun getSourceName(): String {
        return this.charStream.sourceName
    }

    override fun getText(p0: Interval?): String {
        return this.charStream.getText(p0)
    }
}