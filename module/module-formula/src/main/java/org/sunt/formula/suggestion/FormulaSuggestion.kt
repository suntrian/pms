package org.sunt.formula.suggestion

class FormulaSuggestion {

    var expression: String = ""

    var tokens: List<FormulaToken> = emptyList()
        set(value) {
            field = value.sortedWith(Comparator.comparingInt { it.index })
        }

    var suggestions: List<TokenSuggestion> = emptyList()
        set(value) {
            field = value.sortedWith { p1, p2 -> p2.status.privilege - p1.status.privilege }
        }

    override fun toString(): String {
        return expression + " -> " + tokens.joinToString(" ") + suggestions.joinToString(";\n", "\n", "\n")
    }


}