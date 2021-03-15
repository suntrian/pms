package org.sunt.query.formula.suggestion

import org.antlr.v4.runtime.ParserRuleContext
import org.antlr.v4.runtime.Token
import org.antlr.v4.runtime.tree.ErrorNode
import org.antlr.v4.runtime.tree.TerminalNode
import org.sunt.query.formula.function.TokenItem
import org.sunt.query.formula.function.TokenStatus
import org.sunt.query.formula.parser.FormulaParser.*
import org.sunt.query.model.metadata.Column

data class FormulaToken private constructor(
    var text: String,
    val start: Int,
    val stop: Int,
    val index: Int
) {

    constructor(token: Token) : this(token.text, token.startIndex, token.stopIndex, token.tokenIndex + 1)

    var id: String? = null
    var status: TokenStatus = TokenStatus.NORMAL
    var scope: TokenItem = TokenItem.NONE()
    var comment: String = ""


    companion object {

        val ErrorNode = FormulaToken("", -1, -1, -1)

        @JvmStatic
        fun from(node: TerminalNode): FormulaToken {
            if (node is ErrorNode) {
                return ErrorNode
            }
            return from(node.symbol)
        }

        @JvmStatic
        fun from(token: Token): FormulaToken {
            val formulaToken = FormulaToken(token)
            val text = token.text
            with(formulaToken) {
                when (token.type) {
                    L_PARENTHESES, R_PARENTHESES -> scope = TokenItem.PARENTHESES(text)
                    L_BRACE, R_BRACE -> scope = TokenItem.BRACE(text)
                    L_SQUARE, R_SQUARE -> scope = TokenItem.SQUARE(text)
                    CASE, WHEN, THEN, ELSE, END -> scope = TokenItem.RESERVED(text)
                    COMMA -> scope = TokenItem.COMMA()
                    GREATER, GREATER_EQUAL, LESS, LESS_EQUAL, EQUAL, NOT_EQUAL, LIKE, IN, IFNULL -> scope = TokenItem.CMP_OPERATOR(text)
                    PLUS, MINUS, DIV, MUL, POWER, MOD -> scope = TokenItem.CAL_OPERATOR(text)
                    AND, OR, XOR -> scope = TokenItem.LOG_OPERATOR(text)
                    BOOL, INTEGER, FLOAT, STRING, NULL -> TokenItem.CONSTANT(text)
                    COLUMN_ID -> {
                        scope = TokenItem.COLUMN()
                        id = text.substring(1)
                    }
                    COLUMN_NAME -> {
                        scope = TokenItem.COLUMN()
                    }
                    else -> {
                        scope = TokenItem.NONE()
                        comment = "未知的Token类型"
                    }
                }
            }

            return formulaToken
        }

        @JvmStatic
        fun from(ctx: ParserRuleContext, columnId:String? = null): FormulaToken {
            val formulaToken = FormulaToken(ctx.text, ctx.start.startIndex, ctx.stop.stopIndex, ctx.start.tokenIndex)
            with(formulaToken) {
                when(ctx) {
                    is ColumnIdContext-> {
                        scope = TokenItem.COLUMN()
                        id = ctx.COLUMN_ID().text.substring(1)
                    }
                    is ColumnNameContext -> {
                        scope = TokenItem.COLUMN()
                        id = columnId?:ctx.HINT()?.let { HintParser.parse(it.text) }?.get("id")?.toString()
                        text = ctx.COLUMN_NAME().text+ HintParser.generateHint(columnId, ctx.COLUMN_NAME().text)
                    }
                    is ColumnIdentityContext -> {
                        scope = TokenItem.COLUMN()
                        id = columnId?:ctx.HINT()?.let { HintParser.parse(it.text) }?.get("id")?.toString()
                        text = ctx.identity().text + HintParser.generateHint(columnId, ctx.identity().text)
                    }
                    else -> from(ctx.start)
                }
            }
            return formulaToken
        }

    }
}