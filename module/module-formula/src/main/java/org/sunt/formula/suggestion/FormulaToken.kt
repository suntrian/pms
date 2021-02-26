package org.sunt.formula.suggestion

import org.antlr.v4.runtime.Token
import org.sunt.formula.function.FunctionDefinition
import org.sunt.formula.function.FunctionDefinitionParser
import org.sunt.formula.function.TokenItem
import org.sunt.formula.parser.FormulaParser.*

class FormulaToken private constructor(
    val text: String,
    val start: Int,
    val stop: Int,
    val index: Int
) {

    constructor(token: Token) : this(token.text, token.startIndex, token.stopIndex, token.tokenIndex + 1)

    var id: String? = null
    var status: TokenStatus = TokenStatus.NORMAL
    lateinit var scope: TokenItem
    var comment: String = ""


    companion object {

        @JvmStatic
        fun from(token: Token): FormulaToken {
            val formulaToken = FormulaToken(token)
            val text = token.text
            with(formulaToken) {
                when (token.type) {
                    L_PARENTHESES, R_PARENTHESES -> scope = TokenItem.PARENTHESES(text)
                    CASE, WHEN, THEN, ELSE, END -> scope = TokenItem.RESERVED(text)
                    COMMA -> scope = TokenItem.COMMA()
                    GREATER, GREATER_EQUAL, LESS, LESS_EQUAL, EQUAL, NOT_EQUAL -> scope = TokenItem.CMP_OPERATOR(text)
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
                    IDENTITY -> {
                        var functions: List<FunctionDefinition> = emptyList()
                        if (FunctionDefinitionParser.loadFunctions()[text]?.also { functions = it }
                                ?.isNotEmpty() == true) {
                            scope = TokenItem.FUNCTION(text)
                            comment = functions[0].description
                        } else {
                            scope = TokenItem.COLUMN(text)
                        }
                    }
                    else -> {
                        scope = TokenItem.NONE()
                        comment = "未处理的Token类型"
                    }
                }
            }

            return formulaToken
        }

    }
}