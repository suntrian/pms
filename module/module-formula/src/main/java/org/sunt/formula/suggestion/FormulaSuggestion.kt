package org.sunt.formula.suggestion

class FormulaSuggestion(
    val expression: String,
    tokens: List<FormulaToken?>,
    suggestions: List<TokenSuggestion?>
) {

    val tokens: List<FormulaToken> = tokens.filterNotNull().sortedWith(Comparator.comparingInt { it.index })

    val suggestions = suggestions.filterNotNull().sortedWith(Comparator.comparingInt { it.status.privilege })

}