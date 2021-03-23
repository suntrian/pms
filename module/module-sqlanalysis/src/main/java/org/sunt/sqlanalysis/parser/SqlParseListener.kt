package org.sunt.sqlanalysis.parser

import org.antlr.v4.runtime.ParserRuleContext
import org.antlr.v4.runtime.Token
import org.antlr.v4.runtime.TokenStream
import org.antlr.v4.runtime.misc.Interval
import org.sunt.sqlanalysis.model.Expression
import org.sunt.sqlanalysis.model.Table

internal interface SqlParseListener {

    val tables: MutableList<Table>

    val tokenStream: TokenStream

    fun ParserRuleContext.getRawText(): String {
        return tokenStream.getText(start, stop)
    }

    fun ParserRuleContext.getRawText(begin: Int, end: Int): String {
        return tokenStream.getText(Interval.of(begin, end))
    }

    fun Expression.setPosition(ctx: ParserRuleContext) {
        setCharPos(ctx.start.startIndex, ctx.stop.stopIndex+1)
        setTokenPos(ctx.start.tokenIndex, ctx.stop.tokenIndex)
    }

    fun Expression.setPosition(token: Token) {
        setCharPos(token.startIndex, token.stopIndex)
        setTokenPos(token.tokenIndex, token.tokenIndex)
    }

    fun Expression.setPosition(start: Token, stop: Token) {
        setCharPos(start.startIndex, stop.stopIndex)
        setTokenPos(start.tokenIndex, stop.tokenIndex)
    }
}