package org.sunt.sqlanalysis.parser.mysql.grammar2;

import org.antlr.v4.runtime.Parser;
import org.antlr.v4.runtime.TokenStream;

/**
 * {@link <a href="https://hub.fastgit.org/mysql/mysql-workbench/tree/8.0/library/parsers/mysql">mysql-workbench</a>}
 */
public abstract class MySQLBaseRecognizer extends Parser {

    public MySQLBaseRecognizer(TokenStream input) {
        super(input);
    }

}
