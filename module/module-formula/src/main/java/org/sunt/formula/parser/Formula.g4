grammar Formula;

formula
    : statement EOF
    ;

// reference {https://dev.mysql.com/doc/refman/8.0/en/operator-precedence.html}
statement
    : ifSpecial                                                                                 #ifExpression
    | functionStatement                                                                         #functionExpression
    | caseStatement                                                                             #caseExpression
    | constant                                                                                  #constantExpression
    | column                                                                                    #columnExpression
    //指数运算右结合，即2^3^4的意义为2^(3^4)
    | <assoc=right> statement op=POWER statement                                                #mathExpression
    | statement op=MOD statement                                                                #mathExpression
    | statement op=(MUL | DIV) statement                                                        #mathExpression
    | statement op=(PLUS | MINUS) statement                                                     #mathExpression
    | statement op=(GREATER | GREATER_EQUAL | LESS | LESS_EQUAL | EQUAL | NOT_EQUAL) statement  #comparePredicate
    | statement NOT? op=IN L_PARENTHESES statement (COMMA statement)* R_PARENTHESES             #inPredicate
    | statement NOT? op=LIKE STRING                                                             #likePredicate
    | NOT statement                                                                             #notPredicate
    | statement op= AND statement                                                               #logicalPredicate
    | statement op= XOR statement                                                               #logicalPredicate
    | statement op= OR statement                                                                #logicalPredicate
    | L_PARENTHESES statement R_PARENTHESES                                                     #parenthesesExpression
    ;

functionStatement
    : IDENTITY
    L_PARENTHESES
    functionParams
    R_PARENTHESES
    ;

functionParams
    : statement (COMMA statement)*
    ;

caseStatement
    : CASE
     ( (WHEN predictStatement THEN thenStmt=statement)+ | caseStmt=statement (WHEN constant THEN thenStmt=statement)+ )
     (ELSE elseStmt=statement)?
     END
    ;

ifSpecial
    : ifFunction
    | ifStatement
    ;

ifFunction
    : IF
    L_PARENTHESES predict=predictStatement COMMA ifTrue=statement COMMA ifFalse=statement R_PARENTHESES
    ;

ifStatement
    : IF
    L_PARENTHESES predict=predictStatement R_PARENTHESES L_BRACE statement R_BRACE
    elseIfStatement*
    elseStatement?
    ;

elseIfStatement
    : ELSE ifStatement
    ;

elseStatement
    : ELSE L_BRACE statement R_BRACE
    ;

predictStatement
    : statement
    | L_PARENTHESES predictStatement R_PARENTHESES
    | predictStatement op=AND predictStatement
    | predictStatement op=XOR predictStatement
    | predictStatement op=OR predictStatement
   ;

constant
    : STRING
    | (PLUS | MINUS)? (INTEGER | FLOAT)
    | BOOL
    | NULL
    ;

column
    : COLUMN_ID         # columnId
    | COLUMN_NAME       # columnName
    | IDENTITY          # identity
    ;

L_PARENTHESES: '(';
R_PARENTHESES: ')';
L_BRACE:    '{';
R_BRACE:    '}';
COMMA: ',';
COLON:  ':';

MUL: '*';
DIV: '/';
PLUS: '+';
MINUS: '-';
POWER: '^';
MOD: '%' | M O D;

GREATER: '>';
GREATER_EQUAL: '>=';
EQUAL: '==' | '=';
LESS: '<';
LESS_EQUAL: '<=';
NOT_EQUAL: '<>' | '!=';

IF: I F;
LIKE: L I K E;
IN: I N;
NOT: '!' | '~' | N O T;

AND: '&&' | A N D;
OR: '||'  | O R;
XOR: X O R;

CASE: C A S E;
WHEN: W H E N;
THEN: T H E N;
ELSE: E L S E;
END: E N D;

BOOL: T R U E | F A L S E;
NULL: N U L L ;
STRING: '"'(ESC_DQUOTE|.)*?'"' | '\''(ESC_SQUOTE|.)*?'\'';
INTEGER: DIGIT+;
FLOAT:  DIGIT+ DOT DIGIT+;
//FUNC_START: FUNC_NAME WS* L_PARENTHESES;
COLUMN_ID: SHARP (DIGIT|ALPHA)+ ;
IDENTITY: (ALPHA | DIGIT | CHINESE)+ ;
COLUMN_NAME: BACK_QUOTE ANY BACK_QUOTE;

WS: BLANK+                      -> skip;
LINE_COMMENT: '//'~[\r\n]*      -> skip;
BLOCK_COMMENT: '/*' .*? '*/'    -> channel(HIDDEN);

fragment DOT:               '.';
fragment BACK_QUOTE:        '`';
fragment SHARP:             '#';
fragment SIGN:              [+-];
fragment ESC_DQUOTE:        '\\"' | '\\\\';
fragment ESC_SQUOTE:        '\\\'' | '\\\\';
fragment ALPHA:             [A-Za-z_];
fragment DIGIT:             [0-9];
fragment CHINESE:           [\u4e00-\u9fa5];
fragment NL:                [\r\n];
fragment BLANK:             [ \t\r\n\u000C];
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