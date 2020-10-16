// Generated from D:/projects/pms/module/module-formula/src/main/java/org/sunt/formula/function\Function.g4 by ANTLR 4.8
package org.sunt.formula.function.parser;

import org.antlr.v4.runtime.*;
import org.antlr.v4.runtime.atn.ATN;
import org.antlr.v4.runtime.atn.ATNDeserializer;
import org.antlr.v4.runtime.atn.ParserATNSimulator;
import org.antlr.v4.runtime.atn.PredictionContextCache;
import org.antlr.v4.runtime.dfa.DFA;
import org.antlr.v4.runtime.tree.ParseTreeListener;
import org.antlr.v4.runtime.tree.TerminalNode;

import java.util.List;

@SuppressWarnings({"all", "warnings", "unchecked", "unused", "cast"})
public class FunctionParser extends Parser {
    static {
        RuntimeMetaData.checkVersion("4.8", RuntimeMetaData.VERSION);
    }

    protected static final DFA[] _decisionToDFA;
    protected static final PredictionContextCache _sharedContextCache =
            new PredictionContextCache();
    public static final int
            T__0 = 1, VARARG = 2, ALIAS = 3, DT_STRING = 4, DT_DECIMAL = 5, DT_INTEGER = 6, DT_BOOLEAN = 7,
            DT_DATETIME = 8, DT_DATE = 9, DT_TIME = 10, DT_ANY = 11, COLON = 12, COMMA = 13, TRANSFER = 14,
            S_BLOCK = 15, D_BLOCK = 16, L_BRACKET = 17, R_BRACKET = 18, LS_BRACKET = 19, RS_BRACKET = 20,
            SYMBOL = 21, IDENTIFIER = 22, INTEGER = 23, NUMBER = 24, STRING = 25, WS = 26, LINE_COMMENT = 27,
            DESCRIPTION = 28;
    public static final int
            RULE_root = 0, RULE_category = 1, RULE_functionItem = 2, RULE_functionAlias = 3,
            RULE_funcDefine = 4, RULE_funcImplement = 5, RULE_funcArgs = 6, RULE_funcArg = 7,
            RULE_enumerations = 8, RULE_allSymbol = 9, RULE_dataType = 10;

    private static String[] makeRuleNames() {
        return new String[]{
                "root", "category", "functionItem", "functionAlias", "funcDefine", "funcImplement",
                "funcArgs", "funcArg", "enumerations", "allSymbol", "dataType"
        };
    }

    public static final String[] ruleNames = makeRuleNames();

    private static String[] makeLiteralNames() {
        return new String[]{
                null, "'='", null, null, null, null, null, null, null, null, null, null,
                "':'", "','", null, "'''''", "'\"\"\"'", "'('", "')'", "'['", "']'"
        };
    }

    private static final String[] _LITERAL_NAMES = makeLiteralNames();

    private static String[] makeSymbolicNames() {
        return new String[]{
                null, null, "VARARG", "ALIAS", "DT_STRING", "DT_DECIMAL", "DT_INTEGER",
                "DT_BOOLEAN", "DT_DATETIME", "DT_DATE", "DT_TIME", "DT_ANY", "COLON",
                "COMMA", "TRANSFER", "S_BLOCK", "D_BLOCK", "L_BRACKET", "R_BRACKET",
                "LS_BRACKET", "RS_BRACKET", "SYMBOL", "IDENTIFIER", "INTEGER", "NUMBER",
                "STRING", "WS", "LINE_COMMENT", "DESCRIPTION"
        };
    }

    private static final String[] _SYMBOLIC_NAMES = makeSymbolicNames();
    public static final Vocabulary VOCABULARY = new VocabularyImpl(_LITERAL_NAMES, _SYMBOLIC_NAMES);

    /**
     * @deprecated Use {@link #VOCABULARY} instead.
     */
    @Deprecated
    public static final String[] tokenNames;

    static {
        tokenNames = new String[_SYMBOLIC_NAMES.length];
        for (int i = 0; i < tokenNames.length; i++) {
            tokenNames[i] = VOCABULARY.getLiteralName(i);
            if (tokenNames[i] == null) {
                tokenNames[i] = VOCABULARY.getSymbolicName(i);
            }

            if (tokenNames[i] == null) {
                tokenNames[i] = "<INVALID>";
            }
        }
    }

    @Override
    @Deprecated
    public String[] getTokenNames() {
        return tokenNames;
    }

    @Override

    public Vocabulary getVocabulary() {
        return VOCABULARY;
    }

    @Override
    public String getGrammarFileName() {
        return "Function.g4";
    }

    @Override
    public String[] getRuleNames() {
        return ruleNames;
    }

    @Override
    public String getSerializedATN() {
        return _serializedATN;
    }

    @Override
    public ATN getATN() {
        return _ATN;
    }

    public FunctionParser(TokenStream input) {
        super(input);
        _interp = new ParserATNSimulator(this, _ATN, _decisionToDFA, _sharedContextCache);
    }

    public static class RootContext extends ParserRuleContext {
        public List<FunctionItemContext> functionItem() {
            return getRuleContexts(FunctionItemContext.class);
        }

        public FunctionItemContext functionItem(int i) {
            return getRuleContext(FunctionItemContext.class, i);
        }

        public List<FunctionAliasContext> functionAlias() {
            return getRuleContexts(FunctionAliasContext.class);
        }

        public FunctionAliasContext functionAlias(int i) {
            return getRuleContext(FunctionAliasContext.class, i);
        }

        public RootContext(ParserRuleContext parent, int invokingState) {
            super(parent, invokingState);
        }

        @Override
        public int getRuleIndex() {
            return RULE_root;
        }

        @Override
        public void enterRule(ParseTreeListener listener) {
            if (listener instanceof FunctionListener) ((FunctionListener) listener).enterRoot(this);
        }

        @Override
        public void exitRule(ParseTreeListener listener) {
            if (listener instanceof FunctionListener) ((FunctionListener) listener).exitRoot(this);
        }
    }

    public final RootContext root() throws RecognitionException {
        RootContext _localctx = new RootContext(_ctx, getState());
        enterRule(_localctx, 0, RULE_root);
        int _la;
        try {
            enterOuterAlt(_localctx, 1);
            {
                setState(24);
                _errHandler.sync(this);
                _la = _input.LA(1);
                do {
                    {
                        setState(24);
                        _errHandler.sync(this);
                        switch (_input.LA(1)) {
                            case LS_BRACKET: {
                                setState(22);
                                functionItem();
                            }
                            break;
                            case ALIAS: {
                                setState(23);
                                functionAlias();
                            }
                            break;
                            default:
                                throw new NoViableAltException(this);
                        }
                    }
                    setState(26);
                    _errHandler.sync(this);
                    _la = _input.LA(1);
                } while (_la == ALIAS || _la == LS_BRACKET);
            }
        } catch (RecognitionException re) {
            _localctx.exception = re;
            _errHandler.reportError(this, re);
            _errHandler.recover(this, re);
        } finally {
            exitRule();
        }
        return _localctx;
    }

    public static class CategoryContext extends ParserRuleContext {
        public TerminalNode LS_BRACKET() {
            return getToken(FunctionParser.LS_BRACKET, 0);
        }

        public TerminalNode IDENTIFIER() {
            return getToken(FunctionParser.IDENTIFIER, 0);
        }

        public TerminalNode RS_BRACKET() {
            return getToken(FunctionParser.RS_BRACKET, 0);
        }

        public CategoryContext(ParserRuleContext parent, int invokingState) {
            super(parent, invokingState);
        }

        @Override
        public int getRuleIndex() {
            return RULE_category;
        }

        @Override
        public void enterRule(ParseTreeListener listener) {
            if (listener instanceof FunctionListener) ((FunctionListener) listener).enterCategory(this);
        }

        @Override
        public void exitRule(ParseTreeListener listener) {
            if (listener instanceof FunctionListener) ((FunctionListener) listener).exitCategory(this);
        }
    }

    public final CategoryContext category() throws RecognitionException {
        CategoryContext _localctx = new CategoryContext(_ctx, getState());
        enterRule(_localctx, 2, RULE_category);
        try {
            enterOuterAlt(_localctx, 1);
            {
                setState(28);
                match(LS_BRACKET);
                setState(29);
                match(IDENTIFIER);
                setState(30);
                match(RS_BRACKET);
            }
        } catch (RecognitionException re) {
            _localctx.exception = re;
            _errHandler.reportError(this, re);
            _errHandler.recover(this, re);
        } finally {
            exitRule();
        }
        return _localctx;
    }

    public static class FunctionItemContext extends ParserRuleContext {
        public FuncDefineContext funcDefine() {
            return getRuleContext(FuncDefineContext.class, 0);
        }

        public TerminalNode TRANSFER() {
            return getToken(FunctionParser.TRANSFER, 0);
        }

        public FuncImplementContext funcImplement() {
            return getRuleContext(FuncImplementContext.class, 0);
        }

        public List<CategoryContext> category() {
            return getRuleContexts(CategoryContext.class);
        }

        public CategoryContext category(int i) {
            return getRuleContext(CategoryContext.class, i);
        }

        public FunctionAliasContext functionAlias() {
            return getRuleContext(FunctionAliasContext.class, 0);
        }

        public FunctionItemContext(ParserRuleContext parent, int invokingState) {
            super(parent, invokingState);
        }

        @Override
        public int getRuleIndex() {
            return RULE_functionItem;
        }

        @Override
        public void enterRule(ParseTreeListener listener) {
            if (listener instanceof FunctionListener) ((FunctionListener) listener).enterFunctionItem(this);
        }

        @Override
        public void exitRule(ParseTreeListener listener) {
            if (listener instanceof FunctionListener) ((FunctionListener) listener).exitFunctionItem(this);
        }
    }

    public final FunctionItemContext functionItem() throws RecognitionException {
        FunctionItemContext _localctx = new FunctionItemContext(_ctx, getState());
        enterRule(_localctx, 4, RULE_functionItem);
        int _la;
        try {
            enterOuterAlt(_localctx, 1);
            {
                setState(33);
                _errHandler.sync(this);
                _la = _input.LA(1);
                do {
                    {
                        {
                            setState(32);
                            category();
                        }
                    }
                    setState(35);
                    _errHandler.sync(this);
                    _la = _input.LA(1);
                } while (_la == LS_BRACKET);
                setState(38);
                _errHandler.sync(this);
                _la = _input.LA(1);
                if (_la == ALIAS) {
                    {
                        setState(37);
                        functionAlias();
                    }
                }

                setState(40);
                funcDefine();
                setState(41);
                match(TRANSFER);
                setState(42);
                funcImplement();
            }
        } catch (RecognitionException re) {
            _localctx.exception = re;
            _errHandler.reportError(this, re);
            _errHandler.recover(this, re);
        } finally {
            exitRule();
        }
        return _localctx;
    }

    public static class FunctionAliasContext extends ParserRuleContext {
        public TerminalNode ALIAS() {
            return getToken(FunctionParser.ALIAS, 0);
        }

        public List<TerminalNode> IDENTIFIER() {
            return getTokens(FunctionParser.IDENTIFIER);
        }

        public TerminalNode IDENTIFIER(int i) {
            return getToken(FunctionParser.IDENTIFIER, i);
        }

        public List<TerminalNode> COMMA() {
            return getTokens(FunctionParser.COMMA);
        }

        public TerminalNode COMMA(int i) {
            return getToken(FunctionParser.COMMA, i);
        }

        public FunctionAliasContext(ParserRuleContext parent, int invokingState) {
            super(parent, invokingState);
        }

        @Override
        public int getRuleIndex() {
            return RULE_functionAlias;
        }

        @Override
        public void enterRule(ParseTreeListener listener) {
            if (listener instanceof FunctionListener) ((FunctionListener) listener).enterFunctionAlias(this);
        }

        @Override
        public void exitRule(ParseTreeListener listener) {
            if (listener instanceof FunctionListener) ((FunctionListener) listener).exitFunctionAlias(this);
        }
    }

    public final FunctionAliasContext functionAlias() throws RecognitionException {
        FunctionAliasContext _localctx = new FunctionAliasContext(_ctx, getState());
        enterRule(_localctx, 6, RULE_functionAlias);
        int _la;
        try {
            enterOuterAlt(_localctx, 1);
            {
                setState(44);
                match(ALIAS);
                setState(45);
                match(IDENTIFIER);
                setState(46);
                match(T__0);
                setState(47);
                match(IDENTIFIER);
                setState(52);
                _errHandler.sync(this);
                _la = _input.LA(1);
                while (_la == COMMA) {
                    {
                        {
                            setState(48);
                            match(COMMA);
                            setState(49);
                            match(IDENTIFIER);
                        }
                    }
                    setState(54);
                    _errHandler.sync(this);
                    _la = _input.LA(1);
                }
            }
        } catch (RecognitionException re) {
            _localctx.exception = re;
            _errHandler.reportError(this, re);
            _errHandler.recover(this, re);
        } finally {
            exitRule();
        }
        return _localctx;
    }

    public static class FuncDefineContext extends ParserRuleContext {
        public TerminalNode IDENTIFIER() {
            return getToken(FunctionParser.IDENTIFIER, 0);
        }

        public TerminalNode L_BRACKET() {
            return getToken(FunctionParser.L_BRACKET, 0);
        }

        public FuncArgsContext funcArgs() {
            return getRuleContext(FuncArgsContext.class, 0);
        }

        public TerminalNode R_BRACKET() {
            return getToken(FunctionParser.R_BRACKET, 0);
        }

        public TerminalNode COLON() {
            return getToken(FunctionParser.COLON, 0);
        }

        public DataTypeContext dataType() {
            return getRuleContext(DataTypeContext.class, 0);
        }

        public FuncDefineContext(ParserRuleContext parent, int invokingState) {
            super(parent, invokingState);
        }

        @Override
        public int getRuleIndex() {
            return RULE_funcDefine;
        }

        @Override
        public void enterRule(ParseTreeListener listener) {
            if (listener instanceof FunctionListener) ((FunctionListener) listener).enterFuncDefine(this);
        }

        @Override
        public void exitRule(ParseTreeListener listener) {
            if (listener instanceof FunctionListener) ((FunctionListener) listener).exitFuncDefine(this);
        }
    }

    public final FuncDefineContext funcDefine() throws RecognitionException {
        FuncDefineContext _localctx = new FuncDefineContext(_ctx, getState());
        enterRule(_localctx, 8, RULE_funcDefine);
        try {
            enterOuterAlt(_localctx, 1);
            {
                setState(55);
                match(IDENTIFIER);
                setState(56);
                match(L_BRACKET);
                setState(57);
                funcArgs();
                setState(58);
                match(R_BRACKET);
                setState(59);
                match(COLON);
                setState(60);
                dataType();
            }
        } catch (RecognitionException re) {
            _localctx.exception = re;
            _errHandler.reportError(this, re);
            _errHandler.recover(this, re);
        } finally {
            exitRule();
        }
        return _localctx;
    }

    public static class FuncImplementContext extends ParserRuleContext {
        public List<AllSymbolContext> allSymbol() {
            return getRuleContexts(AllSymbolContext.class);
        }

        public AllSymbolContext allSymbol(int i) {
            return getRuleContext(AllSymbolContext.class, i);
        }

        public List<TerminalNode> S_BLOCK() {
            return getTokens(FunctionParser.S_BLOCK);
        }

        public TerminalNode S_BLOCK(int i) {
            return getToken(FunctionParser.S_BLOCK, i);
        }

        public List<TerminalNode> D_BLOCK() {
            return getTokens(FunctionParser.D_BLOCK);
        }

        public TerminalNode D_BLOCK(int i) {
            return getToken(FunctionParser.D_BLOCK, i);
        }

        public FuncImplementContext(ParserRuleContext parent, int invokingState) {
            super(parent, invokingState);
        }

        @Override
        public int getRuleIndex() {
            return RULE_funcImplement;
        }

        @Override
        public void enterRule(ParseTreeListener listener) {
            if (listener instanceof FunctionListener) ((FunctionListener) listener).enterFuncImplement(this);
        }

        @Override
        public void exitRule(ParseTreeListener listener) {
            if (listener instanceof FunctionListener) ((FunctionListener) listener).exitFuncImplement(this);
        }
    }

    public final FuncImplementContext funcImplement() throws RecognitionException {
        FuncImplementContext _localctx = new FuncImplementContext(_ctx, getState());
        enterRule(_localctx, 10, RULE_funcImplement);
        try {
            int _alt;
            setState(84);
            _errHandler.sync(this);
            switch (getInterpreter().adaptivePredict(_input, 11, _ctx)) {
                case 1:
                    enterOuterAlt(_localctx, 1);
                {
                    setState(64);
                    _errHandler.sync(this);
                    _alt = 1 + 1;
                    do {
                        switch (_alt) {
                            case 1 + 1: {
                                setState(64);
                                _errHandler.sync(this);
                                switch (getInterpreter().adaptivePredict(_input, 5, _ctx)) {
                                    case 1: {
                                        setState(62);
                                        matchWildcard();
                                    }
                                    break;
                                    case 2: {
                                        setState(63);
                                        allSymbol();
                                    }
                                    break;
                                }
                            }
                            break;
                            default:
                                throw new NoViableAltException(this);
                        }
                        setState(66);
                        _errHandler.sync(this);
                        _alt = getInterpreter().adaptivePredict(_input, 6, _ctx);
                    } while (_alt != 1 && _alt != org.antlr.v4.runtime.atn.ATN.INVALID_ALT_NUMBER);
                }
                break;
                case 2:
                    enterOuterAlt(_localctx, 2);
                {
                    setState(68);
                    match(S_BLOCK);
                    setState(71);
                    _errHandler.sync(this);
                    _alt = 1 + 1;
                    do {
                        switch (_alt) {
                            case 1 + 1: {
                                setState(71);
                                _errHandler.sync(this);
                                switch (getInterpreter().adaptivePredict(_input, 7, _ctx)) {
                                    case 1: {
                                        setState(69);
                                        matchWildcard();
                                    }
                                    break;
                                    case 2: {
                                        setState(70);
                                        allSymbol();
                                    }
                                    break;
                                }
                            }
                            break;
                            default:
                                throw new NoViableAltException(this);
                        }
                        setState(73);
                        _errHandler.sync(this);
                        _alt = getInterpreter().adaptivePredict(_input, 8, _ctx);
                    } while (_alt != 1 && _alt != org.antlr.v4.runtime.atn.ATN.INVALID_ALT_NUMBER);
                    setState(75);
                    match(S_BLOCK);
                }
                break;
                case 3:
                    enterOuterAlt(_localctx, 3);
                {
                    setState(76);
                    match(D_BLOCK);
                    setState(79);
                    _errHandler.sync(this);
                    _alt = 1 + 1;
                    do {
                        switch (_alt) {
                            case 1 + 1: {
                                setState(79);
                                _errHandler.sync(this);
                                switch (getInterpreter().adaptivePredict(_input, 9, _ctx)) {
                                    case 1: {
                                        setState(77);
                                        matchWildcard();
                                    }
                                    break;
                                    case 2: {
                                        setState(78);
                                        allSymbol();
                                    }
                                    break;
                                }
                            }
                            break;
                            default:
                                throw new NoViableAltException(this);
                        }
                        setState(81);
                        _errHandler.sync(this);
                        _alt = getInterpreter().adaptivePredict(_input, 10, _ctx);
                    } while (_alt != 1 && _alt != org.antlr.v4.runtime.atn.ATN.INVALID_ALT_NUMBER);
                    setState(83);
                    match(D_BLOCK);
                }
                break;
            }
        } catch (RecognitionException re) {
            _localctx.exception = re;
            _errHandler.reportError(this, re);
            _errHandler.recover(this, re);
        } finally {
            exitRule();
        }
        return _localctx;
    }

    public static class FuncArgsContext extends ParserRuleContext {
        public List<FuncArgContext> funcArg() {
            return getRuleContexts(FuncArgContext.class);
        }

        public FuncArgContext funcArg(int i) {
            return getRuleContext(FuncArgContext.class, i);
        }

        public TerminalNode VARARG() {
            return getToken(FunctionParser.VARARG, 0);
        }

        public List<TerminalNode> COMMA() {
            return getTokens(FunctionParser.COMMA);
        }

        public TerminalNode COMMA(int i) {
            return getToken(FunctionParser.COMMA, i);
        }

        public FuncArgsContext(ParserRuleContext parent, int invokingState) {
            super(parent, invokingState);
        }

        @Override
        public int getRuleIndex() {
            return RULE_funcArgs;
        }

        @Override
        public void enterRule(ParseTreeListener listener) {
            if (listener instanceof FunctionListener) ((FunctionListener) listener).enterFuncArgs(this);
        }

        @Override
        public void exitRule(ParseTreeListener listener) {
            if (listener instanceof FunctionListener) ((FunctionListener) listener).exitFuncArgs(this);
        }
    }

    public final FuncArgsContext funcArgs() throws RecognitionException {
        FuncArgsContext _localctx = new FuncArgsContext(_ctx, getState());
        enterRule(_localctx, 12, RULE_funcArgs);
        int _la;
        try {
            int _alt;
            setState(106);
            _errHandler.sync(this);
            switch (getInterpreter().adaptivePredict(_input, 16, _ctx)) {
                case 1:
                    enterOuterAlt(_localctx, 1);
                {
                    setState(87);
                    _errHandler.sync(this);
                    _la = _input.LA(1);
                    if (_la == VARARG) {
                        {
                            setState(86);
                            match(VARARG);
                        }
                    }

                    setState(89);
                    funcArg();
                }
                break;
                case 2:
                    enterOuterAlt(_localctx, 2);
                {
                    setState(90);
                    funcArg();
                    setState(91);
                    match(COMMA);
                    setState(97);
                    _errHandler.sync(this);
                    _alt = getInterpreter().adaptivePredict(_input, 13, _ctx);
                    while (_alt != 2 && _alt != org.antlr.v4.runtime.atn.ATN.INVALID_ALT_NUMBER) {
                        if (_alt == 1) {
                            {
                                {
                                    setState(92);
                                    funcArg();
                                    setState(93);
                                    match(COMMA);
                                }
                            }
                        }
                        setState(99);
                        _errHandler.sync(this);
                        _alt = getInterpreter().adaptivePredict(_input, 13, _ctx);
                    }
                    setState(101);
                    _errHandler.sync(this);
                    _la = _input.LA(1);
                    if (_la == VARARG) {
                        {
                            setState(100);
                            match(VARARG);
                        }
                    }

                    setState(104);
                    _errHandler.sync(this);
                    _la = _input.LA(1);
                    if (_la == IDENTIFIER) {
                        {
                            setState(103);
                            funcArg();
                        }
                    }

                }
                break;
            }
        } catch (RecognitionException re) {
            _localctx.exception = re;
            _errHandler.reportError(this, re);
            _errHandler.recover(this, re);
        } finally {
            exitRule();
        }
        return _localctx;
    }

    public static class FuncArgContext extends ParserRuleContext {
        public TerminalNode IDENTIFIER() {
            return getToken(FunctionParser.IDENTIFIER, 0);
        }

        public List<TerminalNode> COLON() {
            return getTokens(FunctionParser.COLON);
        }

        public TerminalNode COLON(int i) {
            return getToken(FunctionParser.COLON, i);
        }

        public DataTypeContext dataType() {
            return getRuleContext(DataTypeContext.class, 0);
        }

        public TerminalNode LS_BRACKET() {
            return getToken(FunctionParser.LS_BRACKET, 0);
        }

        public EnumerationsContext enumerations() {
            return getRuleContext(EnumerationsContext.class, 0);
        }

        public TerminalNode RS_BRACKET() {
            return getToken(FunctionParser.RS_BRACKET, 0);
        }

        public FuncArgContext(ParserRuleContext parent, int invokingState) {
            super(parent, invokingState);
        }

        @Override
        public int getRuleIndex() {
            return RULE_funcArg;
        }

        @Override
        public void enterRule(ParseTreeListener listener) {
            if (listener instanceof FunctionListener) ((FunctionListener) listener).enterFuncArg(this);
        }

        @Override
        public void exitRule(ParseTreeListener listener) {
            if (listener instanceof FunctionListener) ((FunctionListener) listener).exitFuncArg(this);
        }
    }

    public final FuncArgContext funcArg() throws RecognitionException {
        FuncArgContext _localctx = new FuncArgContext(_ctx, getState());
        enterRule(_localctx, 14, RULE_funcArg);
        int _la;
        try {
            enterOuterAlt(_localctx, 1);
            {
                setState(108);
                match(IDENTIFIER);
                setState(109);
                match(COLON);
                setState(110);
                dataType();
                setState(116);
                _errHandler.sync(this);
                _la = _input.LA(1);
                if (_la == COLON) {
                    {
                        setState(111);
                        match(COLON);
                        setState(112);
                        match(LS_BRACKET);
                        setState(113);
                        enumerations();
                        setState(114);
                        match(RS_BRACKET);
                    }
                }

            }
        } catch (RecognitionException re) {
            _localctx.exception = re;
            _errHandler.reportError(this, re);
            _errHandler.recover(this, re);
        } finally {
            exitRule();
        }
        return _localctx;
    }

    public static class EnumerationsContext extends ParserRuleContext {
        public List<TerminalNode> STRING() {
            return getTokens(FunctionParser.STRING);
        }

        public TerminalNode STRING(int i) {
            return getToken(FunctionParser.STRING, i);
        }

        public List<TerminalNode> COMMA() {
            return getTokens(FunctionParser.COMMA);
        }

        public TerminalNode COMMA(int i) {
            return getToken(FunctionParser.COMMA, i);
        }

        public List<TerminalNode> NUMBER() {
            return getTokens(FunctionParser.NUMBER);
        }

        public TerminalNode NUMBER(int i) {
            return getToken(FunctionParser.NUMBER, i);
        }

        public EnumerationsContext(ParserRuleContext parent, int invokingState) {
            super(parent, invokingState);
        }

        @Override
        public int getRuleIndex() {
            return RULE_enumerations;
        }

        @Override
        public void enterRule(ParseTreeListener listener) {
            if (listener instanceof FunctionListener) ((FunctionListener) listener).enterEnumerations(this);
        }

        @Override
        public void exitRule(ParseTreeListener listener) {
            if (listener instanceof FunctionListener) ((FunctionListener) listener).exitEnumerations(this);
        }
    }

    public final EnumerationsContext enumerations() throws RecognitionException {
        EnumerationsContext _localctx = new EnumerationsContext(_ctx, getState());
        enterRule(_localctx, 16, RULE_enumerations);
        int _la;
        try {
            setState(134);
            _errHandler.sync(this);
            switch (_input.LA(1)) {
                case STRING:
                    enterOuterAlt(_localctx, 1);
                {
                    setState(118);
                    match(STRING);
                    setState(123);
                    _errHandler.sync(this);
                    _la = _input.LA(1);
                    while (_la == COMMA) {
                        {
                            {
                                setState(119);
                                match(COMMA);
                                setState(120);
                                match(STRING);
                            }
                        }
                        setState(125);
                        _errHandler.sync(this);
                        _la = _input.LA(1);
                    }
                }
                break;
                case NUMBER:
                    enterOuterAlt(_localctx, 2);
                {
                    setState(126);
                    match(NUMBER);
                    setState(131);
                    _errHandler.sync(this);
                    _la = _input.LA(1);
                    while (_la == COMMA) {
                        {
                            {
                                setState(127);
                                match(COMMA);
                                setState(128);
                                match(NUMBER);
                            }
                        }
                        setState(133);
                        _errHandler.sync(this);
                        _la = _input.LA(1);
                    }
                }
                break;
                default:
                    throw new NoViableAltException(this);
            }
        } catch (RecognitionException re) {
            _localctx.exception = re;
            _errHandler.reportError(this, re);
            _errHandler.recover(this, re);
        } finally {
            exitRule();
        }
        return _localctx;
    }

    public static class AllSymbolContext extends ParserRuleContext {
        public TerminalNode COLON() {
            return getToken(FunctionParser.COLON, 0);
        }

        public TerminalNode COMMA() {
            return getToken(FunctionParser.COMMA, 0);
        }

        public TerminalNode L_BRACKET() {
            return getToken(FunctionParser.L_BRACKET, 0);
        }

        public TerminalNode R_BRACKET() {
            return getToken(FunctionParser.R_BRACKET, 0);
        }

        public TerminalNode IDENTIFIER() {
            return getToken(FunctionParser.IDENTIFIER, 0);
        }

        public TerminalNode STRING() {
            return getToken(FunctionParser.STRING, 0);
        }

        public TerminalNode NUMBER() {
            return getToken(FunctionParser.NUMBER, 0);
        }

        public TerminalNode INTEGER() {
            return getToken(FunctionParser.INTEGER, 0);
        }

        public TerminalNode LS_BRACKET() {
            return getToken(FunctionParser.LS_BRACKET, 0);
        }

        public TerminalNode RS_BRACKET() {
            return getToken(FunctionParser.RS_BRACKET, 0);
        }

        public AllSymbolContext(ParserRuleContext parent, int invokingState) {
            super(parent, invokingState);
        }

        @Override
        public int getRuleIndex() {
            return RULE_allSymbol;
        }

        @Override
        public void enterRule(ParseTreeListener listener) {
            if (listener instanceof FunctionListener) ((FunctionListener) listener).enterAllSymbol(this);
        }

        @Override
        public void exitRule(ParseTreeListener listener) {
            if (listener instanceof FunctionListener) ((FunctionListener) listener).exitAllSymbol(this);
        }
    }

    public final AllSymbolContext allSymbol() throws RecognitionException {
        AllSymbolContext _localctx = new AllSymbolContext(_ctx, getState());
        enterRule(_localctx, 18, RULE_allSymbol);
        int _la;
        try {
            enterOuterAlt(_localctx, 1);
            {
                setState(136);
                _la = _input.LA(1);
                if (!((((_la) & ~0x3f) == 0 && ((1L << _la) & ((1L << COLON) | (1L << COMMA) | (1L << L_BRACKET) | (1L << R_BRACKET) | (1L << LS_BRACKET) | (1L << RS_BRACKET) | (1L << IDENTIFIER) | (1L << INTEGER) | (1L << NUMBER) | (1L << STRING))) != 0))) {
                    _errHandler.recoverInline(this);
                } else {
                    if (_input.LA(1) == Token.EOF) matchedEOF = true;
                    _errHandler.reportMatch(this);
                    consume();
                }
            }
        } catch (RecognitionException re) {
            _localctx.exception = re;
            _errHandler.reportError(this, re);
            _errHandler.recover(this, re);
        } finally {
            exitRule();
        }
        return _localctx;
    }

    public static class DataTypeContext extends ParserRuleContext {
        public TerminalNode DT_STRING() {
            return getToken(FunctionParser.DT_STRING, 0);
        }

        public TerminalNode DT_DECIMAL() {
            return getToken(FunctionParser.DT_DECIMAL, 0);
        }

        public TerminalNode DT_INTEGER() {
            return getToken(FunctionParser.DT_INTEGER, 0);
        }

        public TerminalNode DT_BOOLEAN() {
            return getToken(FunctionParser.DT_BOOLEAN, 0);
        }

        public TerminalNode DT_DATETIME() {
            return getToken(FunctionParser.DT_DATETIME, 0);
        }

        public TerminalNode DT_DATE() {
            return getToken(FunctionParser.DT_DATE, 0);
        }

        public TerminalNode DT_TIME() {
            return getToken(FunctionParser.DT_TIME, 0);
        }

        public TerminalNode DT_ANY() {
            return getToken(FunctionParser.DT_ANY, 0);
        }

        public DataTypeContext(ParserRuleContext parent, int invokingState) {
            super(parent, invokingState);
        }

        @Override
        public int getRuleIndex() {
            return RULE_dataType;
        }

        @Override
        public void enterRule(ParseTreeListener listener) {
            if (listener instanceof FunctionListener) ((FunctionListener) listener).enterDataType(this);
        }

        @Override
        public void exitRule(ParseTreeListener listener) {
            if (listener instanceof FunctionListener) ((FunctionListener) listener).exitDataType(this);
        }
    }

    public final DataTypeContext dataType() throws RecognitionException {
        DataTypeContext _localctx = new DataTypeContext(_ctx, getState());
        enterRule(_localctx, 20, RULE_dataType);
        int _la;
        try {
            enterOuterAlt(_localctx, 1);
            {
                setState(138);
                _la = _input.LA(1);
                if (!((((_la) & ~0x3f) == 0 && ((1L << _la) & ((1L << DT_STRING) | (1L << DT_DECIMAL) | (1L << DT_INTEGER) | (1L << DT_BOOLEAN) | (1L << DT_DATETIME) | (1L << DT_DATE) | (1L << DT_TIME) | (1L << DT_ANY))) != 0))) {
                    _errHandler.recoverInline(this);
                } else {
                    if (_input.LA(1) == Token.EOF) matchedEOF = true;
                    _errHandler.reportMatch(this);
                    consume();
                }
            }
        } catch (RecognitionException re) {
            _localctx.exception = re;
            _errHandler.reportError(this, re);
            _errHandler.recover(this, re);
        } finally {
            exitRule();
        }
        return _localctx;
    }

    public static final String _serializedATN =
            "\3\u608b\ua72a\u8133\ub9ed\u417c\u3be7\u7786\u5964\3\36\u008f\4\2\t\2" +
                    "\4\3\t\3\4\4\t\4\4\5\t\5\4\6\t\6\4\7\t\7\4\b\t\b\4\t\t\t\4\n\t\n\4\13" +
                    "\t\13\4\f\t\f\3\2\3\2\6\2\33\n\2\r\2\16\2\34\3\3\3\3\3\3\3\3\3\4\6\4$" +
                    "\n\4\r\4\16\4%\3\4\5\4)\n\4\3\4\3\4\3\4\3\4\3\5\3\5\3\5\3\5\3\5\3\5\7" +
                    "\5\65\n\5\f\5\16\58\13\5\3\6\3\6\3\6\3\6\3\6\3\6\3\6\3\7\3\7\6\7C\n\7" +
                    "\r\7\16\7D\3\7\3\7\3\7\6\7J\n\7\r\7\16\7K\3\7\3\7\3\7\3\7\6\7R\n\7\r\7" +
                    "\16\7S\3\7\5\7W\n\7\3\b\5\bZ\n\b\3\b\3\b\3\b\3\b\3\b\3\b\7\bb\n\b\f\b" +
                    "\16\be\13\b\3\b\5\bh\n\b\3\b\5\bk\n\b\5\bm\n\b\3\t\3\t\3\t\3\t\3\t\3\t" +
                    "\3\t\3\t\5\tw\n\t\3\n\3\n\3\n\7\n|\n\n\f\n\16\n\177\13\n\3\n\3\n\3\n\7" +
                    "\n\u0084\n\n\f\n\16\n\u0087\13\n\5\n\u0089\n\n\3\13\3\13\3\f\3\f\3\f\5" +
                    "DKS\2\r\2\4\6\b\n\f\16\20\22\24\26\2\4\5\2\16\17\23\26\30\33\3\2\6\r\2" +
                    "\u0099\2\32\3\2\2\2\4\36\3\2\2\2\6#\3\2\2\2\b.\3\2\2\2\n9\3\2\2\2\fV\3" +
                    "\2\2\2\16l\3\2\2\2\20n\3\2\2\2\22\u0088\3\2\2\2\24\u008a\3\2\2\2\26\u008c" +
                    "\3\2\2\2\30\33\5\6\4\2\31\33\5\b\5\2\32\30\3\2\2\2\32\31\3\2\2\2\33\34" +
                    "\3\2\2\2\34\32\3\2\2\2\34\35\3\2\2\2\35\3\3\2\2\2\36\37\7\25\2\2\37 \7" +
                    "\30\2\2 !\7\26\2\2!\5\3\2\2\2\"$\5\4\3\2#\"\3\2\2\2$%\3\2\2\2%#\3\2\2" +
                    "\2%&\3\2\2\2&(\3\2\2\2\')\5\b\5\2(\'\3\2\2\2()\3\2\2\2)*\3\2\2\2*+\5\n" +
                    "\6\2+,\7\20\2\2,-\5\f\7\2-\7\3\2\2\2./\7\5\2\2/\60\7\30\2\2\60\61\7\3" +
                    "\2\2\61\66\7\30\2\2\62\63\7\17\2\2\63\65\7\30\2\2\64\62\3\2\2\2\658\3" +
                    "\2\2\2\66\64\3\2\2\2\66\67\3\2\2\2\67\t\3\2\2\28\66\3\2\2\29:\7\30\2\2" +
                    ":;\7\23\2\2;<\5\16\b\2<=\7\24\2\2=>\7\16\2\2>?\5\26\f\2?\13\3\2\2\2@C" +
                    "\13\2\2\2AC\5\24\13\2B@\3\2\2\2BA\3\2\2\2CD\3\2\2\2DE\3\2\2\2DB\3\2\2" +
                    "\2EW\3\2\2\2FI\7\21\2\2GJ\13\2\2\2HJ\5\24\13\2IG\3\2\2\2IH\3\2\2\2JK\3" +
                    "\2\2\2KL\3\2\2\2KI\3\2\2\2LM\3\2\2\2MW\7\21\2\2NQ\7\22\2\2OR\13\2\2\2" +
                    "PR\5\24\13\2QO\3\2\2\2QP\3\2\2\2RS\3\2\2\2ST\3\2\2\2SQ\3\2\2\2TU\3\2\2" +
                    "\2UW\7\22\2\2VB\3\2\2\2VF\3\2\2\2VN\3\2\2\2W\r\3\2\2\2XZ\7\4\2\2YX\3\2" +
                    "\2\2YZ\3\2\2\2Z[\3\2\2\2[m\5\20\t\2\\]\5\20\t\2]c\7\17\2\2^_\5\20\t\2" +
                    "_`\7\17\2\2`b\3\2\2\2a^\3\2\2\2be\3\2\2\2ca\3\2\2\2cd\3\2\2\2dg\3\2\2" +
                    "\2ec\3\2\2\2fh\7\4\2\2gf\3\2\2\2gh\3\2\2\2hj\3\2\2\2ik\5\20\t\2ji\3\2" +
                    "\2\2jk\3\2\2\2km\3\2\2\2lY\3\2\2\2l\\\3\2\2\2m\17\3\2\2\2no\7\30\2\2o" +
                    "p\7\16\2\2pv\5\26\f\2qr\7\16\2\2rs\7\25\2\2st\5\22\n\2tu\7\26\2\2uw\3" +
                    "\2\2\2vq\3\2\2\2vw\3\2\2\2w\21\3\2\2\2x}\7\33\2\2yz\7\17\2\2z|\7\33\2" +
                    "\2{y\3\2\2\2|\177\3\2\2\2}{\3\2\2\2}~\3\2\2\2~\u0089\3\2\2\2\177}\3\2" +
                    "\2\2\u0080\u0085\7\32\2\2\u0081\u0082\7\17\2\2\u0082\u0084\7\32\2\2\u0083" +
                    "\u0081\3\2\2\2\u0084\u0087\3\2\2\2\u0085\u0083\3\2\2\2\u0085\u0086\3\2" +
                    "\2\2\u0086\u0089\3\2\2\2\u0087\u0085\3\2\2\2\u0088x\3\2\2\2\u0088\u0080" +
                    "\3\2\2\2\u0089\23\3\2\2\2\u008a\u008b\t\2\2\2\u008b\25\3\2\2\2\u008c\u008d" +
                    "\t\3\2\2\u008d\27\3\2\2\2\27\32\34%(\66BDIKQSVYcgjlv}\u0085\u0088";
    public static final ATN _ATN =
            new ATNDeserializer().deserialize(_serializedATN.toCharArray());

    static {
        _decisionToDFA = new DFA[_ATN.getNumberOfDecisions()];
        for (int i = 0; i < _ATN.getNumberOfDecisions(); i++) {
            _decisionToDFA[i] = new DFA(_ATN.getDecisionState(i), i);
        }
    }
}