parser grammar FunctionParser;

options { tokenVocab=FunctionLexer; }

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
    : DESCRIPTION NEW_LINE*? category NEW_LINE*? COLON COLON NEW_LINE*? funcDefine NEW_LINE*? funcImplement
    ;

functionAlias
    : ALIAS IDENTIFIER EQUAL IDENTIFIER (COMMA IDENTIFIER)*
    ;

funcDefine
    : IDENTIFIER L_BRACKET funcArgs? R_BRACKET COLON (dataType | argRef DOT TYPE)
    ;

funcImplement
    : TRANSFER_L funcImplementBody NEWLINE?
    | TRANSFER_S funcImplementBody S_BLOCK
    | TRANSFER_D funcImplementBody D_BLOCK
    ;

funcImplementBody
    : ( ANY_L | WS_L )+?
    | ( ANY_S | WS_S )+?
    | ( ANY_D | WS_D )+?
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
