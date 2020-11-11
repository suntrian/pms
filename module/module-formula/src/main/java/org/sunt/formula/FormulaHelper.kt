package org.sunt.formula

import org.antlr.v4.runtime.CharStreams
import org.antlr.v4.runtime.CommonTokenStream
import org.sunt.formula.define.IColumn
import org.sunt.formula.define.SqlProduct
import org.sunt.formula.parser.FormulaLexer
import org.sunt.formula.parser.FormulaParser

class FormulaHelper private constructor(private val getColumnById: (String) -> IColumn?, private val getColumnByName: (String) -> IColumn?) {

    fun toSql(expression: String, vendor: SqlProduct): StatementInfo {
        val lexer = FormulaLexer(CharStreams.fromString(expression))
        val tokens = CommonTokenStream(lexer)
        val parser = FormulaParser(tokens)
        val toSqlVisitor = FormulaToSqlVisitor(vendor, getColumnById = this.getColumnById, getColumnByName = this.getColumnByName)
        return toSqlVisitor.visitFormula(parser.formula())
    }

    companion object {

        @JvmStatic
        fun of(getColumnById: (String) -> IColumn?, getColumnByName: (String) -> IColumn?): FormulaHelper {
            return FormulaHelper(getColumnById, getColumnByName)
        }

    }

}