package org.sunt.formula

import org.antlr.v4.runtime.NoViableAltException
import org.antlr.v4.runtime.RecognitionException
import org.antlr.v4.runtime.Recognizer
import org.antlr.v4.runtime.Token
import org.antlr.v4.runtime.misc.IntervalSet

class SyntaxError(
    val recognizer: Recognizer<*, *>?,
    offendingSymbols: Token?,
    val line: Int,
    val charPositionInLine: Int,
    val message: String?,
    val expectTokens: IntervalSet?,
    val e: RecognitionException?
) {

    val offendingSymbols = if (e is NoViableAltException) e.startToken else offendingSymbols

}