package org.sunt.formula

import org.sunt.formula.define.IColumn
import org.sunt.formula.define.SqlProduct
import java.util.function.Function

class FormulaSuggestVisitor(product: SqlProduct, getColumnById: Function<String, IColumn?>, getColumnByName: Function<String, IColumn?>)
    : AbstractFormulaVisitor(product, getColumnById, getColumnByName) {

}