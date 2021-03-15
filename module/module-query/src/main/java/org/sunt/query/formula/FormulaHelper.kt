package org.sunt.query.formula

import org.antlr.v4.runtime.CharStreams
import org.antlr.v4.runtime.CommonTokenStream
import org.antlr.v4.runtime.TokenStreamRewriter
import org.slf4j.LoggerFactory
import org.sunt.query.define.SqlDialect
import org.sunt.query.formula.parser.FormulaLexer
import org.sunt.query.formula.parser.FormulaParser
import org.sunt.query.formula.suggestion.FormulaSuggestion
import org.sunt.query.formula.suggestion.SuggestErrorStrategy
import org.sunt.query.model.metadata.Column
import org.sunt.query.model.metadata.ColumnInterface

class FormulaHelper private constructor(private val columnInterface: ColumnInterface) {

    fun toSql(expression: String, vendor: SqlDialect): ParsedFormula {
        val set = if (CurrentContainer.get() == null) {
            CurrentContainer.set(this)
            true
        } else false
        try {
            val lexer = FormulaLexer(CharStreams.fromString(expression))
            val tokens = CommonTokenStream(lexer)
            val rewriter = TokenStreamRewriter(tokens)
            val parser = FormulaParser(tokens)
            val toSqlVisitor = FormulaToSqlVisitor(vendor, columnInterface, rewriter)
            val parsedFormula = toSqlVisitor.visitFormula(parser.formula())
            log.trace("解析{} Formula: {}; 生成Sql: {}", vendor, expression, parsedFormula.formula)
            return parsedFormula
        } finally {
            if (set) {
                CurrentContainer.remove()
            }
        }
    }

    fun suggest(expression: String, cursor: Int, vendor: SqlDialect): FormulaSuggestion {
        val set = if (CurrentContainer.get() == null) {
            CurrentContainer.set(this)
            true
        } else false
        try {
            val lexer = FormulaLexer(CharStreams.fromString(expression))
            val tokens = CommonTokenStream(lexer)
            //val rewriter = TokenStreamRewriter(tokens)
            val parser = FormulaParser(tokens)
            val correctCursor = if (cursor < 0 || cursor > expression.length) expression.length else cursor
            val suggestVisitor = FormulaSuggestVisitor(vendor, columnInterface, correctCursor)
            parser.removeErrorListeners()
            parser.addErrorListener(suggestVisitor)
            parser.errorHandler = SuggestErrorStrategy()
            val formulaSuggestion = suggestVisitor.visitFormula(parser.formula())
            log.trace("推荐{} Formula: {}; 生成tokens: {}", vendor, expression, formulaSuggestion.tokens.joinToString("") { it.text })
            return formulaSuggestion
        } finally {
            if (set) {
                CurrentContainer.remove()
            }
        }

    }

    companion object {

        private val log = LoggerFactory.getLogger(FormulaHelper::class.java)
        private val CurrentContainer = ThreadLocal<FormulaHelper>()

        @JvmStatic
        fun of(getColumnById: (String) -> Column?, getColumnByName: (String) -> Column?): FormulaHelper {
            return FormulaHelper(object : ColumnInterface {
                override fun getColumnById(id: String): Column? = getColumnById(id)

                override fun getColumnByName(name: String): Column? = getColumnByName(name)
            })
        }

        @JvmStatic
        fun of(columnInterface: ColumnInterface): FormulaHelper {
            return FormulaHelper(columnInterface)
        }

        internal fun ofCurrent(): FormulaHelper {
            return CurrentContainer.get() ?: throw IllegalStateException("ofCurrent方法仅限内部使用")
        }

    }

}