grammar Function;

root
    : functionItem +
    ;

category
    : '[' ANY+ ']'
    ;

functionItem
    : category funcDefine TRANSFER funcImplement
    ;

funcDefine
    : IDENTIFIER L_BRACKET funcArgs R_BRACKET COLON dataType
    ;

funcImplement
    :
    | S_BLOCK (ANY | allSymbol)+ S_BLOCK
    | D_BLOCK (ANY | allSymbol)+ D_BLOCK
    ;

funcArgs
    : VARARG? funcArg
    | funcArg COMMA (funcArg COMMA)* VARARG? funcArg?
    ;

funcArg
    : IDENTIFIER COLON dataType (COLON '[' enumerations ']' )?
    ;

enumerations
    : STRING (COMMA STRING)*
    | NUMBER (COMMA NUMBER)*
    ;

allSymbol
    : COLON | COMMA | NEWLINE | L_BRACKET | R_BRACKET | IDENTIFIER
    ;

dataType
    : 'STRING' | 'DECIMAL' | 'INTEGER' | 'BOOLEAN' | 'DATETIME' | 'DATE' | 'TIME' | 'ANY'
    ;

VARARG : 'VARARG' | 'vararg';
COLON : ':';
COMMA : ',';
TRANSFER : '-' '>';
S_BLOCK : '\'\'\'';
D_BLOCK : '"""';
L_BRACKET : '(';
R_BRACKET : ')';
IDENTIFIER: [a-zA-Z$_0-9]+;
INTEGER: DIGIT+;
NUMBER: DIGIT+ '.' DIGIT+;
STRING: '"'(ESC_DQUOTE|.)*?'"' | '\''(ESC_SQUOTE|.)*?'\'';
STRING_LITERAL: ;

WS: BLANK+                      -> skip;
LINE_COMMENT: '#' ~[\r\n]*      -> skip;
DESCRIPTION: '/**' .*? '*/'     -> channel(HIDDEN);

fragment BLANK:             [ \t\r\n\u000C];
fragment NL:                [\r\n];
fragment ESC_DQUOTE:        '\\"' | '\\\\';
fragment ESC_SQUOTE:        '\\\'' | '\\\\';
fragment DIGIT:             [0-9];
fragment ALPHA:             [A-Za-z_];
fragment CHINESE:           [\u4e00-\u9fa5];
fragment ANY:               .+?;