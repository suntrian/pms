package org.sunt.formula.function

import org.antlr.v4.runtime.ParserRuleContext
import org.sunt.formula.define.DataType

class StatementInfo private constructor(
    val origin: String,
    val startIndex: Int,
    val stopIndex: Int,
    val startToken: Int,
    val stopToken: Int
) {

    constructor(context: ParserRuleContext?) : this(
        context?.text ?: "",
        context?.start?.startIndex ?: -1,
        context?.stop?.stopIndex ?: -1,
        context?.start?.tokenIndex ?: -1,
        context?.stop?.tokenIndex ?: -1
    )

    var children: List<StatementInfo> = emptyList()

    var expression: String = ""

    var dataType: DataType = DataType.NONE

    var status: TokenStatus = TokenStatus.NONE

    var token: TokenItem = TokenItem.NONE()

    var payload: Any = Unit

    companion object {

        @JvmField
        val Empty = StatementInfo("", -1, -1, -1, -1)

        @JvmStatic
        fun empty() = StatementInfo("", -1, -1, -1, -1)
    }

}