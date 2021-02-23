package org.sunt.formula

import org.sunt.formula.define.IColumn
import org.sunt.formula.define.SqlDialect
import java.util.function.Function

class FormulaSuggestVisitor(
    product: SqlDialect,
    getColumnById: Function<String, IColumn?>,
    getColumnByName: Function<String, IColumn?>
) : AbstractFormulaVisitor(product, getColumnById, getColumnByName) {

}