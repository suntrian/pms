package org.sunt.formula

import org.antlr.v4.runtime.BaseErrorListener
import org.antlr.v4.runtime.RecognitionException
import org.antlr.v4.runtime.Recognizer
import org.antlr.v4.runtime.Token
import org.sunt.formula.parser.FormulaParser

internal class FormulaErrorListener : BaseErrorListener() {

    val syntaxErrors = mutableListOf<SyntaxError>()

    override fun syntaxError(
        recognizer: Recognizer<*, *>?,
        offendingSymbol: Any?,
        line: Int,
        charPositionInLine: Int,
        msg: String?,
        e: RecognitionException?
    ) {
        syntaxErrors.add(
            SyntaxError(
                recognizer,
                offendingSymbol as Token,
                line,
                charPositionInLine,
                msg,
                e?.expectedTokens ?: (recognizer as FormulaParser).expectedTokens,
                e
            )
        )
    }
}