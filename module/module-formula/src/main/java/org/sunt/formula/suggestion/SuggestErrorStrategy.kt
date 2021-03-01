package org.sunt.formula.suggestion

import org.antlr.v4.runtime.DefaultErrorStrategy
import org.antlr.v4.runtime.Parser

class SuggestErrorStrategy : DefaultErrorStrategy() {


    override fun sync(recognizer: Parser?) {

    }

}