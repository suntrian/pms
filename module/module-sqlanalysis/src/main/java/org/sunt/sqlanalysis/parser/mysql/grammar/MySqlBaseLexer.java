package org.sunt.sqlanalysis.parser.mysql.grammar;

import org.antlr.v4.runtime.CharStream;
import org.antlr.v4.runtime.Lexer;
import org.antlr.v4.runtime.Token;

public abstract class MySqlBaseLexer extends Lexer {

    public MySqlBaseLexer() {
    }

    public MySqlBaseLexer(CharStream input) {
        super(input);
    }

    private String delimiter = ";";

    public void setDelimiter(String delimiter) {
        this.delimiter = delimiter.toUpperCase();
    }

    @Override
    public Token nextToken() {
        Token nextToken = super.nextToken();
        if (";".equals(delimiter)) return nextToken;
        if (delimiter.equals(nextToken.getText())) {
            _type = MySqlLexer.DELIM;
            return nextToken;
        } else if (delimiter.startsWith(nextToken.getText())) {
            int i = nextToken.getText().length();
            for (; i < delimiter.length(); i++) {
                if (delimiter.charAt(i) != _input.LA(i)) {
                    return nextToken;
                }
            }
            while (_input.LA(i) == ' ' || _input.LA(i) == '\t') {
                i++;
            }
            if ('\r' == _input.LA(i) || '\n' == _input.LA(i)) {
                for (int j = nextToken.getText().length(); j < i; j++) {
                    _input.consume();
                }
                _type = MySqlLexer.DELIM;
                return _factory.create(MySqlLexer.DELIM, delimiter);
            }
        }
        return nextToken;
    }
}
