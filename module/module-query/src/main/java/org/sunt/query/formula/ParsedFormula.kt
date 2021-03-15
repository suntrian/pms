package org.sunt.query.formula

import org.sunt.query.define.DataType
import org.sunt.query.model.metadata.Column

class ParsedFormula(
    val expression: String,
    val formula: String,
    val dataType: DataType,
    val formulaType: FormulaType
) {

    var payload:Any = Unit
    var relatedColumns = emptyList<Column>()

    override fun toString(): String {
        return "ParsedFormula(expression='$expression', formula='$formula', dataType=$dataType, formulaType=$formulaType, payload=$payload, relatedColumns=$relatedColumns)"
    }


}