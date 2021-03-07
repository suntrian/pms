package org.sunt.query.formula.suggestion

import org.antlr.v4.runtime.ParserRuleContext
import org.antlr.v4.runtime.Token
import org.antlr.v4.runtime.tree.TerminalNode
import org.sunt.query.define.DataType
import org.sunt.query.formula.function.TokenItem
import org.sunt.query.formula.function.TokenStatus

class TokenSuggestion constructor(
    val text: String,
    val tokenIndex: Int,
    val start: Int,
    val stop: Int
) {
    var comment: String = ""
    var status: TokenStatus = TokenStatus.NORMAL
    var leftPart: String? = null
    var scopes: Set<TokenItem> = emptySet()
    var dataTypes: Set<DataType> = emptySet()
        set(value) {
            if (value.contains(DataType.DECIMAL)) {
                if (!value.contains(DataType.INTEGER)) {
                    field = value.toMutableSet().also { it.add(DataType.INTEGER) }
                    return
                }
            }
            field = value
        }

    fun setScopes(vararg scopes: TokenItem) {
        this.scopes = scopes.toSet()
    }

    fun setScopes(scopes: Collection<TokenItem>) {
        this.scopes = scopes.toSet()
    }

    fun setDataTypes(vararg dataTypes: DataType) {
        this.dataTypes = dataTypes.toSet()
    }

    fun setDataTypes(dataTypes: Collection<DataType>) {
        this.dataTypes = dataTypes.toSet()
    }

    override fun toString(): String {
        return "TokenSuggestion(text='$text', tokenIndex=$tokenIndex, start=$start, stop=$stop, comment='$comment', status=$status, leftPart=$leftPart, scopes=$scopes, dataTypes=$dataTypes)"
    }


    companion object {

        @JvmStatic
        internal fun ofThis(token: Token): TokenSuggestion {
            return TokenSuggestion(token.text, token.tokenIndex, token.startIndex, token.stopIndex + 1)
        }

        @JvmStatic
        internal fun ofThis(node: TerminalNode): TokenSuggestion {
            return ofThis(node.symbol)
        }

        @JvmStatic
        internal fun ofThis(ctx: ParserRuleContext): TokenSuggestion {
            return TokenSuggestion(ctx.text, ctx.start.tokenIndex, ctx.start.startIndex, ctx.stop.stopIndex + 1)
        }

        @JvmStatic
        @JvmOverloads
        internal fun ofNext(node: TerminalNode, text: String = ""): TokenSuggestion {
            return ofNext(node.symbol, text)
        }

        @JvmStatic
        @JvmOverloads
        internal fun ofNext(token: Token, text: String = ""): TokenSuggestion {
            return TokenSuggestion(text, token.tokenIndex, token.stopIndex + 1, token.stopIndex + 1)
        }

    }

}