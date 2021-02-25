parser grammar FunctionParser;

options { tokenVocab=FunctionLexer; }

root
    : packageDeclare
    importDeclare*
    (typeAliasDelare
    | classDeclare
    | dialectInterface)*
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
    : TYPEALIAS (identifier | dataTypeNull) EQUAL identifier
    ;

classDeclare
    :  CLASS identifier (COLON identifier)? (LCURL RCURL)?
    ;

functionDefine
    : functionModifierList? FUN typeParameters? simpleIdentifier LPAREN functionParamDefines? RPAREN COLON dataTypeNull  functionImplement?
    ;

functionModifierList
    : (annotation | functionModifier)+
    ;

annotation
    : AT identifier (LPAREN functionParams? RPAREN)?
    ;

functionModifier
    : OVERRIDE
    | PRIVATE
    | ABSTRACT
    ;

functionImplement
    : functionSimpleImpl
    | functionFullImpl
    ;

functionSimpleImpl
    : EQUAL expression
    ;

functionFullImpl
    : LCURL (.*?) RCURL
    ;

typeParameters
    : LANGLE dataTypeNull (COMMA dataTypeNull)* RANGLE
    ;

functionParamDefines
    : functionParamDefine  (COMMA functionParamDefine)* COMMA?
    ;

functionParamDefine
    : functionParamModifierList? simpleIdentifier COLON dataTypeNull (EQUAL expression)?
    ;

functionParamModifierList
    : (annotation | functionParamModifier)+
    ;

functionParamModifier
    : VARARG        //used by last parameter
    ;

functionParams
    : expression (COMMA expression)*
    ;

functionCall
    : identifier LPAREN functionParams? RPAREN
    ;

expression
    : STRING
    | NUMBER
    | NULL
    | functionCall
    | identifier COLONCOLON CLASS
    | identifier EQUAL expression
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

dataTypeNull
    : dataType QUESTION?
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