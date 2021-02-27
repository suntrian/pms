package org.sunt.formula.suggestion

import org.sunt.formula.define.DataType
import org.sunt.formula.function.TokenItem
import org.sunt.formula.function.TokenStatus

class TokenSuggestion(
    val text: String,
    val start: Int,
    stop: Int
) {
    val stop = stop + 1;

    var comment: String = ""
    var status: TokenStatus = TokenStatus.NORMAL
    var leftPart: String = ""
    var scopes: Set<TokenItem> = emptySet()
    var dataTypes: Set<DataType> = emptySet()

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


}