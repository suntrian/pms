parser grammar FunctionParser;

options { tokenVocab=FunctionLexer; }

root
    : packageDeclare
    importDeclare*
    (typeAliasDelare | dialectInterface)*
    EOF
    ;

packageDeclare
    : PACKAGE identifier SEMICOLON?
    ;

importDeclare
    : IMPORT identifier (DOT ASTERISK)? SEMICOLON?
    ;

dialectInterface
    : INTERFACE simpleIdentifier (COLON identifier)? (LCURL functionDefine* RCURL)?
    ;

typeAliasDelare
    : TYPEALIAS (identifier | dataType) EQUAL identifier
    ;

functionDefine
    : functionModifierList? FUN typeParameters? simpleIdentifier LPAREN functionParamDefines? RPAREN COLON dataType SEMICOLON?
    ;

functionModifierList
    : (annotation | functionModifier)+
    ;

annotation
    : AT identifier (LPAREN functionParamUsages? RPAREN)?
    ;

functionModifier
    : OVERRIDE
    | PRIVATE
    | ABSTRACT
    ;

typeParameters
    : LANGLE dataType (COMMA dataType)* RANGLE
    ;

functionParamDefines
    : functionParamDefine  (COMMA functionParamDefine)* COMMA?
    ;

functionParamDefine
    : functionParamModifierList? simpleIdentifier COLON dataType (EQUAL expression)?
    ;

functionParamModifierList
    : (annotation | functionParamModifier)+
    ;

functionParamModifier
    : VARARG        //used by last parameter
    ;

functionParamUsages
    : expression (COMMA expression)*
    ;

expression
    : STRING
    | NUMBER
    ;

dataType
    : DT_BOOLEAN
    | DT_INT
    | DT_DOUBLE
    | DT_STRING
    | DT_DATE
    | DT_TIME
    | DT_DATETIME
    | DT_ANY
    | identifier typeParameters?
    ;

identifier
    : simpleIdentifier (DOT simpleIdentifier)*
    ;

simpleIdentifier
    : IDENTIFIER
    | PACKAGE
    | IMPORT
    | INTERFACE
    | VARARG
    ;