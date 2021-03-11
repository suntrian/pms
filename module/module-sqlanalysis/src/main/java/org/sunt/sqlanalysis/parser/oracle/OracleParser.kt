package org.sunt.sqlanalysis.parser.oracle

import org.antlr.v4.runtime.*
import org.slf4j.LoggerFactory
import org.sunt.sqlanalysis.exception.SqlParseException
import org.sunt.sqlanalysis.model.Table
import org.sunt.sqlanalysis.parser.CaseCharStream
import org.sunt.sqlanalysis.parser.ErrorListener
import org.sunt.sqlanalysis.parser.ErrorStrategy
import org.sunt.sqlanalysis.parser.SqlParser
import org.sunt.sqlanalysis.parser.oracle.grammar.PlSqlLexer
import org.sunt.sqlanalysis.parser.oracle.grammar.PlSqlParser


internal class OracleParser
@JvmOverloads constructor (private val onErrorStrategy: ErrorStrategy = ErrorStrategy.RETURN): SqlParser {

    private val errorListener = ErrorListener()
    private val MAX_SQL_LEN = 50_000_000


    override fun parse(sql: String): List<Table> {
        if (sql.length > MAX_SQL_LEN) {
            throw SqlParseException("The sql should not be more than 5MB")
        }
        val charStream: CharStream = CaseCharStream(CharStreams.fromString(sql), true)
        val lexer = PlSqlLexer(charStream)
        val tokenStream: TokenStream = CommonTokenStream(lexer)
        val parser = PlSqlParser(tokenStream)
        val listener = OracleSqlParseListener(tokenStream)
        parser.addParseListener(listener)
        parser.addErrorListener(errorListener)
        try {
            parser.sql_script()
            parser.interpreter.clearDFA()
        } catch (e: RecognitionException) {
            log.error(e.message, e)
            errorListener.exceptions.add(e)
        } catch (e: Exception) {
            log.error("{}:{}", e.javaClass.simpleName, e.message, e)
            errorListener.exceptions.add(e)
        }

        if (hasExceptions()) {
            when(onErrorStrategy) {
                ErrorStrategy.RETURN->{}
                ErrorStrategy.THROW->{throw exceptions()[0]}
                ErrorStrategy.SWALLOW->{this.errorListener.exceptions.clear()}
            }
        }

        return listener.tables
    }

    override fun hasExceptions(): Boolean {
        return exceptions().isNotEmpty()
    }

    override fun exceptions(): List<Exception> {
        return this.errorListener.exceptions
    }

    companion object {

        private val log = LoggerFactory.getLogger(OracleParser::class.java)

        @JvmField val INSTANCE = OracleParser(ErrorStrategy.SWALLOW)

    }

}