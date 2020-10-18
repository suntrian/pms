grammar Function;

root
    : (functionItem
    | functionAlias
    | NEW_LINE) +
    EOF
    ;

category
    : IDENTIFIER
    | LS_BRACKET IDENTIFIER (COMMA IDENTIFIER)* RS_BRACKET
    ;

functionItem
    : DESCRIPTION NEW_LINE*? category NEW_LINE*? COLON COLON NEW_LINE*? funcDefine TRANSFER NEW_LINE*? funcImplement
    ;

functionAlias
    : ALIAS IDENTIFIER '=' IDENTIFIER (COMMA IDENTIFIER)*
    ;

funcDefine
    : IDENTIFIER L_BRACKET funcArgs? R_BRACKET COLON (dataType | argRef DOT TYPE)
    ;

funcImplement
    : ( argRef | allSymbol)*? NEW_LINE?
    | S_BLOCK ( argRef | allSymbol | NEW_LINE)+? S_BLOCK
    | D_BLOCK ( argRef | allSymbol | NEW_LINE)+? D_BLOCK
    ;

funcArgs
    : VARARG? funcArg
    | funcArg COMMA (funcArg COMMA)* VARARG? funcArg?
    ;

funcArg
    : argName COLON dataType (COLON LS_BRACKET enumerations RS_BRACKET )?
    ;

argName
    : IDENTIFIER
    | dataType
    ;

argRef
    : DOLLAR (INTEGER | argName | L_BRACE argName R_BRACE)
    ;

enumerations
    : STRING (COMMA STRING)*
    | NUMBER (COMMA NUMBER)*
    ;

allSymbol
    : IDENTIFIER | STRING | NUMBER | INTEGER
    |  DOLLAR | PLUS | MINUS
    | COLON | COMMA | DOT
    | L_BRACKET | R_BRACKET | LS_BRACKET | RS_BRACKET | L_BRACE | R_BRACE
    | SYMBOL | dataType
    ;

dataType
    : DT_STRING | DT_DECIMAL | DT_INTEGER | DT_BOOLEAN | DT_DATETIME | DT_DATE | DT_TIME | DT_ANY
    ;

VARARG      : V A R A R G;
ALIAS       : A L I A S;
DT_STRING      : S T R I N G;
DT_DECIMAL     : D E C I M A L | D O U B L E;
DT_INTEGER     : I N T E G E R;
DT_BOOLEAN     : B O O L E A N | B O O L;
DT_DATETIME    : D A T E T I M E;
DT_DATE        : D A T E;
DT_TIME        : T I M E;
DT_ANY         : A N Y;
TYPE           : T Y P E;

PLUS  : '+';
MINUS : '-';
COLON : ':';
COMMA : ',';
DOT   : '.';
DOLLAR: '$';
TRANSFER : '-' '>';
S_BLOCK : '\'\'\'';
D_BLOCK : '"""';
L_BRACKET : '(';
R_BRACKET : ')';
LS_BRACKET: '[';
RS_BRACKET: ']';
L_BRACE : '{';
R_BRACE : '}';
SYMBOL: [:,;.{}[\]()*/];

INTEGER: DIGIT+;
NUMBER: DIGIT+ '.' DIGIT+;
STRING: '"'(ESC_DQUOTE|.)*?'"' | '\''(ESC_SQUOTE|.)*?'\'';
IDENTIFIER: [_a-zA-Z0-9\u4e00-\u9fa5]+;

NEW_LINE : NL+;
WS: BLANK+                      -> skip;
LINE_COMMENT: '//' ~[\r\n]*      -> skip;
DESCRIPTION: '/**' .*? '*/';

//NON_NL: ~[\r\n]+;

fragment BLANK:             [ \t\u000C];
fragment NL:                '\r' | '\r'?'\n';
fragment ESC_DQUOTE:        '\\"' | '\\\\';
fragment ESC_SQUOTE:        '\\\'' | '\\\\';
fragment DIGIT:             [0-9];
fragment ALPHA:             [A-Za-z_];
fragment CHINESE:           [\u4e00-\u9fa5];
fragment ANY:               .+?;
fragment A :                [aA] ;
fragment B :                [bB] ;
fragment C :                [cC] ;
fragment D :                [dD] ;
fragment E :                [eE] ;
fragment F :                [fF] ;
fragment G :                [gG] ;
fragment H :                [hH] ;
fragment I :                [iI] ;
fragment J :                [jJ] ;
fragment K :                [kK] ;
fragment L :                [lL] ;
fragment M :                [mM] ;
fragment N :                [nN] ;
fragment O :                [oO] ;
fragment P :                [pP] ;
fragment Q :                [qQ] ;
fragment R :                [rR] ;
fragment S :                [sS] ;
fragment T :                [tT] ;
fragment U :                [uU] ;
fragment V :                [vV] ;
fragment W :                [wW] ;
fragment X :                [xX] ;
fragment Y :                [yY] ;
fragment Z :                [zZ] ;