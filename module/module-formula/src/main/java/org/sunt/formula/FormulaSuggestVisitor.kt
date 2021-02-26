package org.sunt.formula

import org.sunt.formula.define.SqlDialect

class FormulaSuggestVisitor(
    product: SqlDialect,
    columnInterface: ColumnInterface
) : AbstractFormulaVisitor(product, columnInterface) {

}