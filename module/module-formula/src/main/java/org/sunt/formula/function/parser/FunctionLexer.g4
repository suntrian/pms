lexer grammar FunctionLexer;


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
EQUAL : '=';
DOLLAR: '$';

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


TRANSFER_D : '->' NEW_LINE*? '"""'     -> pushMode(IMPLEMENT_D);
TRANSFER_S : '->' NEW_LINE*? '\'\'\''  -> pushMode(IMPLEMENT_S);
TRANSFER_L : '->'           -> pushMode(IMPLEMENT_L);

mode IMPLEMENT_L;
COMMENT_L: '//' ~[\r\n]*    -> skip;
COMMENT_BL: '/*' .*? '*/'   -> skip;
WS_L: BLANK+;
ANY_L: ~[ \t\r\n]+;
NEWLINE: NL+                -> popMode;

mode IMPLEMENT_S;
COMMENT_S: '//' ~[\r\n]*    -> skip;
COMMENT_BS: '/*' .*? '*/'   -> skip;
WS_S: (BLANK|NL)+;
ANY_S: ANY;
S_BLOCK : '\'\'\''          -> popMode;

mode IMPLEMENT_D;
COMMENT_D: '//' ~[\r\n]*    -> skip;
COMMENT_BD: '/*' .*? '*/'   -> skip;
WS_D: (BLANK|NL)+;
ANY_D: ANY;
D_BLOCK : '"""'             -> popMode;

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