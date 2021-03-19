package org.sunt.sqlanalysis.parser.mysql

import org.antlr.v4.runtime.*
import org.slf4j.LoggerFactory
import org.sunt.sqlanalysis.model.Table
import org.sunt.sqlanalysis.parser.CaseCharStream
import org.sunt.sqlanalysis.parser.ErrorListener
import org.sunt.sqlanalysis.parser.ErrorStrategy
import org.sunt.sqlanalysis.parser.SqlParser
import org.sunt.sqlanalysis.parser.mysql.grammar.MySqlLexer
import org.sunt.sqlanalysis.parser.mysql.grammar.MySqlParser

internal class MysqlParser
@JvmOverloads constructor (private val onErrorStrategy: ErrorStrategy = ErrorStrategy.RETURN): SqlParser {

    private val errorListener = ErrorListener()

    override fun parse(sql: String): List<Table> {
        val charStream: CharStream = CaseCharStream(CharStreams.fromString(sql), true)
        val lexer = MySqlLexer(charStream)
        val tokenStream: TokenStream = CommonTokenStream(lexer)
        val parser = MySqlParser(tokenStream)
        val listener = MysqlSqlParseListener(tokenStream)
        parser.addParseListener(listener)
        parser.removeErrorListeners()
        parser.addErrorListener(errorListener)
        try {
            parser.root()
        } catch (e: RecognitionException) {
            log.error(e.message, e)
            errorListener.exceptions.add(e)
        } catch (e: Exception) {
            log.error("{}:{}", e.javaClass.simpleName, e.message, e)
            errorListener.exceptions.add(e)
        }
        if (hasExceptions()) {
            when(onErrorStrategy) {
                ErrorStrategy.RETURN ->{}
                ErrorStrategy.THROW ->{throw exceptions()[0]}
                ErrorStrategy.SWALLOW ->{this.errorListener.exceptions.clear()}
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

        private val log = LoggerFactory.getLogger(MysqlParser::class.java)

        @JvmField val INSTANCE = MysqlParser(ErrorStrategy.SWALLOW)

    }

}