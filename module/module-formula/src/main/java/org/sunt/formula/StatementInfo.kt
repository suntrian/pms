package org.sunt.formula

import org.antlr.v4.runtime.ParserRuleContext
import org.sunt.formula.define.DataType
import org.sunt.formula.suggestion.SuggestionScope
import org.sunt.formula.suggestion.TokenStatus

data class StatementInfo(val origin: String,
                         val startIndex: Int,
                         val stopIndex: Int,
                         val startToken: Int,
                         val stopToken: Int) {

    public constructor(context: ParserRuleContext?) {
        if (context == null) {
            StatementInfo("", -1, -1, -1, -1)
        } else {
            StatementInfo(context.text, context.start.startIndex, context.stop.stopIndex, context.start.tokenIndex, context.stop.tokenIndex)
        }
    }

    var expression: String = ""

    var dataType: DataType = DataType.NONE

    var status: TokenStatus = TokenStatus.NONE

    var scope: SuggestionScope = SuggestionScope.NONE()

}