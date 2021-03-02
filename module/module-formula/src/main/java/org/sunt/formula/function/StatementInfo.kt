package org.sunt.formula.function

import org.antlr.v4.runtime.ParserRuleContext
import org.sunt.formula.define.DataType

data class StatementInfo(val origin: String,
                         val startIndex: Int,
                         val stopIndex: Int,
                         val startToken: Int,
                         val stopToken: Int) {

    constructor(context: ParserRuleContext?) : this(context?.text ?: "",
            context?.start?.startIndex ?: -1,
            context?.stop?.stopIndex ?: -1,
            context?.start?.tokenIndex ?: -1,
            context?.stop?.tokenIndex ?: -1)

    var expression: String = ""

    var dataType: DataType = DataType.NONE

    var status: TokenStatus = TokenStatus.NONE

    var token: TokenItem = TokenItem.NONE()

    var payload: Any = Unit

}