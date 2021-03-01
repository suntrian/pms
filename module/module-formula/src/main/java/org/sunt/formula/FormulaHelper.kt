package org.sunt.formula

import org.antlr.v4.runtime.CharStreams
import org.antlr.v4.runtime.CommonTokenStream
import org.antlr.v4.runtime.TokenStreamRewriter
import org.sunt.formula.define.IColumn
import org.sunt.formula.define.SqlDialect
import org.sunt.formula.function.StatementInfo
import org.sunt.formula.parser.FormulaLexer
import org.sunt.formula.parser.FormulaParser
import org.sunt.formula.suggestion.FormulaSuggestion
import org.sunt.formula.suggestion.SuggestErrorStrategy

class FormulaHelper private constructor(private val columnInterface: ColumnInterface) {

    fun toSql(expression: String, vendor: SqlDialect): StatementInfo {
        val lexer = FormulaLexer(CharStreams.fromString(expression))
        val tokens = CommonTokenStream(lexer)
        val rewriter = TokenStreamRewriter(tokens)
        val parser = FormulaParser(tokens)
        val toSqlVisitor = FormulaToSqlVisitor(vendor, columnInterface, rewriter)
        return toSqlVisitor.visitFormula(parser.formula())
    }

    fun suggest(expression: String, cursor: Int, vendor: SqlDialect): FormulaSuggestion {
        val lexer = FormulaLexer(CharStreams.fromString(expression))
        val tokens = CommonTokenStream(lexer)
        val rewriter = TokenStreamRewriter(tokens)
        val parser = FormulaParser(tokens)
        val suggestVisitor = FormulaSuggestVisitor(vendor, columnInterface, rewriter, cursor)
        parser.removeErrorListeners()
        parser.addErrorListener(suggestVisitor)
        parser.errorHandler = SuggestErrorStrategy()
        return suggestVisitor.visitFormula(parser.formula())
    }

    companion object {

        @JvmStatic
        fun of(getColumnById: (String) -> IColumn?, getColumnByName: (String) -> IColumn?): FormulaHelper {
            return FormulaHelper(object : ColumnInterface {
                override fun getColumnById(id: String): IColumn? = getColumnById(id)

                override fun getColumnByName(name: String): IColumn? = getColumnByName(name)
            })
        }

        @JvmStatic
        fun of(columnInterface: ColumnInterface): FormulaHelper {
            return FormulaHelper(columnInterface)
        }

    }

}