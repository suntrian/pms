lexer grammar FunctionLexer;

LINE_COMMENT: '//' ~[\r\n]*                     -> channel(HIDDEN);
BLOCK_COMMENT: '/*' ( BLOCK_COMMENT | .)*? '*/' -> channel(HIDDEN);

WS: BLANK+                                      -> skip;
NEWLINE: NL+                                    -> skip;

AT: '@';
LANGLE: '<';
RANGLE: '>';
LPAREN: '(';
RPAREN: ')';
LCURL: '{';
RCURL: '}';
EQUAL: '=';
COLONCOLON: '::';
COLON: ':';
COMMA: ',';
DOT: '.';
ASTERISK: '*';
SEMICOLON: ';';
PACKAGE: 'package';
IMPORT: 'import';
CLASS: 'class';
INTERFACE: 'interface';
VARARG: 'vararg';
OVERRIDE: 'override';
PRIVATE: 'private';
ABSTRACT: 'abstract';
TYPEALIAS: 'typealias';
FUN: 'fun';
DT_STRING: 'String';
DT_INT: 'Int' | 'Integer';
DT_ANY: 'Any';
DT_BOOLEAN: 'Boolean' | 'Decimal';
DT_DATE: 'Date';
DT_TIME: 'Time';
DT_DATETIME: 'DateTime';
DT_DOUBLE: 'Double';

NUMBER: [-+]? (DIGIT+ | DIGIT+ DOT DIGIT);
STRING: '"'(ESC_DQUOTE|.)*?'"' | '\''(ESC_SQUOTE|.)*?'\'' | '"""' .*? '"""';


IDENTIFIER: (ALPHA | CHINESE | '_') (ALPHA | CHINESE | DIGIT | '_')* | '`' ~('`')+ '`';


fragment BLANK:             [ \t\u000C];
fragment NL:                '\r' | '\r'?'\n';
fragment ESC_DQUOTE:        '\\"' | '\\\\';
fragment ESC_SQUOTE:        '\\\'' | '\\\\';
fragment DIGIT:             [0-9];
fragment ALPHA:             [A-Za-z_];
fragment CHINESE:           [\u4e00-\u9fa5];