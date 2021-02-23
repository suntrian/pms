// Generated from D:/projects/pms/module/module-formula/src/main/java/org/sunt/formula/functionKt/parser\FunctionParser.g4 by ANTLR 4.9.1
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
		RuntimeMetaData.checkVersion("4.9.1", RuntimeMetaData.VERSION);
	}

	protected static final DFA[] _decisionToDFA;
	protected static final PredictionContextCache _sharedContextCache =
			new PredictionContextCache();
	public static final int
			LINE_COMMENT = 1, BLOCK_COMMENT = 2, WS = 3, NEWLINE = 4, AT = 5, LANGLE = 6, RANGLE = 7,
			LPAREN = 8, RPAREN = 9, LCURL = 10, RCURL = 11, EQUAL = 12, COLONCOLON = 13, COLON = 14,
			COMMA = 15, DOT = 16, ASTERISK = 17, SEMICOLON = 18, PACKAGE = 19, IMPORT = 20, CLASS = 21,
			INTERFACE = 22, VARARG = 23, OVERRIDE = 24, PRIVATE = 25, ABSTRACT = 26, TYPEALIAS = 27,
			FUN = 28, DT_STRING = 29, DT_INT = 30, DT_ANY = 31, DT_BOOLEAN = 32, DT_DATE = 33,
			DT_TIME = 34, DT_DATETIME = 35, DT_DOUBLE = 36, NUMBER = 37, STRING = 38, IDENTIFIER = 39;
	public static final int
			RULE_root = 0, RULE_packageDeclare = 1, RULE_importDeclare = 2, RULE_dialectInterface = 3,
			RULE_typeAliasDelare = 4, RULE_functionDefine = 5, RULE_functionModifierList = 6,
			RULE_annotation = 7, RULE_functionModifier = 8, RULE_typeParameters = 9,
			RULE_functionParamDefines = 10, RULE_functionParamDefine = 11, RULE_functionParamModifierList = 12,
			RULE_functionParamModifier = 13, RULE_functionParamUsages = 14, RULE_expression = 15,
			RULE_dataType = 16, RULE_identifier = 17, RULE_simpleIdentifier = 18;

	private static String[] makeRuleNames() {
		return new String[]{
				"root", "packageDeclare", "importDeclare", "dialectInterface", "typeAliasDelare",
				"functionDefine", "functionModifierList", "annotation", "functionModifier",
				"typeParameters", "functionParamDefines", "functionParamDefine", "functionParamModifierList",
				"functionParamModifier", "functionParamUsages", "expression", "dataType",
				"identifier", "simpleIdentifier"
		};
	}

	public static final String[] ruleNames = makeRuleNames();

	private static String[] makeLiteralNames() {
		return new String[]{
				null, null, null, null, null, "'@'", "'<'", "'>'", "'('", "')'", "'{'",
				"'}'", "'='", "'::'", "':'", "','", "'.'", "'*'", "';'", "'package'",
				"'import'", "'class'", "'interface'", "'vararg'", "'override'", "'private'",
				"'abstract'", "'typealias'", "'fun'", "'String'", null, "'Any'", null,
				"'Date'", "'Time'", "'DateTime'", "'Double'"
		};
	}

	private static final String[] _LITERAL_NAMES = makeLiteralNames();

	private static String[] makeSymbolicNames() {
		return new String[]{
				null, "LINE_COMMENT", "BLOCK_COMMENT", "WS", "NEWLINE", "AT", "LANGLE",
				"RANGLE", "LPAREN", "RPAREN", "LCURL", "RCURL", "EQUAL", "COLONCOLON",
				"COLON", "COMMA", "DOT", "ASTERISK", "SEMICOLON", "PACKAGE", "IMPORT",
				"CLASS", "INTERFACE", "VARARG", "OVERRIDE", "PRIVATE", "ABSTRACT", "TYPEALIAS",
				"FUN", "DT_STRING", "DT_INT", "DT_ANY", "DT_BOOLEAN", "DT_DATE", "DT_TIME",
				"DT_DATETIME", "DT_DOUBLE", "NUMBER", "STRING", "IDENTIFIER"
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
		return "FunctionParser.g4";
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
		public PackageDeclareContext packageDeclare() {
			return getRuleContext(PackageDeclareContext.class, 0);
		}

		public TerminalNode EOF() {
			return getToken(FunctionParser.EOF, 0);
		}

		public List<ImportDeclareContext> importDeclare() {
			return getRuleContexts(ImportDeclareContext.class);
		}

		public ImportDeclareContext importDeclare(int i) {
			return getRuleContext(ImportDeclareContext.class, i);
		}

		public List<TypeAliasDelareContext> typeAliasDelare() {
			return getRuleContexts(TypeAliasDelareContext.class);
		}

		public TypeAliasDelareContext typeAliasDelare(int i) {
			return getRuleContext(TypeAliasDelareContext.class, i);
		}

		public List<DialectInterfaceContext> dialectInterface() {
			return getRuleContexts(DialectInterfaceContext.class);
		}

		public DialectInterfaceContext dialectInterface(int i) {
			return getRuleContext(DialectInterfaceContext.class, i);
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
			if (listener instanceof FunctionParserListener) ((FunctionParserListener) listener).enterRoot(this);
		}

		@Override
		public void exitRule(ParseTreeListener listener) {
			if (listener instanceof FunctionParserListener) ((FunctionParserListener) listener).exitRoot(this);
		}
	}

	public final RootContext root() throws RecognitionException {
		RootContext _localctx = new RootContext(_ctx, getState());
		enterRule(_localctx, 0, RULE_root);
		int _la;
		try {
			enterOuterAlt(_localctx, 1);
			{
				setState(38);
				packageDeclare();
				setState(42);
				_errHandler.sync(this);
				_la = _input.LA(1);
				while (_la == IMPORT) {
					{
						{
							setState(39);
							importDeclare();
						}
					}
					setState(44);
					_errHandler.sync(this);
					_la = _input.LA(1);
				}
				setState(49);
				_errHandler.sync(this);
				_la = _input.LA(1);
				while (_la == INTERFACE || _la == TYPEALIAS) {
					{
						setState(47);
						_errHandler.sync(this);
						switch (_input.LA(1)) {
							case TYPEALIAS: {
								setState(45);
								typeAliasDelare();
							}
							break;
							case INTERFACE: {
								setState(46);
								dialectInterface();
							}
							break;
							default:
								throw new NoViableAltException(this);
						}
					}
					setState(51);
					_errHandler.sync(this);
					_la = _input.LA(1);
				}
				setState(52);
				match(EOF);
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

	public static class PackageDeclareContext extends ParserRuleContext {
		public TerminalNode PACKAGE() {
			return getToken(FunctionParser.PACKAGE, 0);
		}

		public IdentifierContext identifier() {
			return getRuleContext(IdentifierContext.class, 0);
		}

		public TerminalNode SEMICOLON() {
			return getToken(FunctionParser.SEMICOLON, 0);
		}

		public PackageDeclareContext(ParserRuleContext parent, int invokingState) {
			super(parent, invokingState);
		}

		@Override
		public int getRuleIndex() {
			return RULE_packageDeclare;
		}

		@Override
		public void enterRule(ParseTreeListener listener) {
			if (listener instanceof FunctionParserListener)
				((FunctionParserListener) listener).enterPackageDeclare(this);
		}

		@Override
		public void exitRule(ParseTreeListener listener) {
			if (listener instanceof FunctionParserListener)
				((FunctionParserListener) listener).exitPackageDeclare(this);
		}
	}

	public final PackageDeclareContext packageDeclare() throws RecognitionException {
		PackageDeclareContext _localctx = new PackageDeclareContext(_ctx, getState());
		enterRule(_localctx, 2, RULE_packageDeclare);
		int _la;
		try {
			enterOuterAlt(_localctx, 1);
			{
				setState(54);
				match(PACKAGE);
				setState(55);
				identifier();
				setState(57);
				_errHandler.sync(this);
				_la = _input.LA(1);
				if (_la == SEMICOLON) {
					{
						setState(56);
						match(SEMICOLON);
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

	public static class ImportDeclareContext extends ParserRuleContext {
		public TerminalNode IMPORT() {
			return getToken(FunctionParser.IMPORT, 0);
		}

		public IdentifierContext identifier() {
			return getRuleContext(IdentifierContext.class, 0);
		}

		public TerminalNode DOT() {
			return getToken(FunctionParser.DOT, 0);
		}

		public TerminalNode ASTERISK() {
			return getToken(FunctionParser.ASTERISK, 0);
		}

		public TerminalNode SEMICOLON() {
			return getToken(FunctionParser.SEMICOLON, 0);
		}

		public ImportDeclareContext(ParserRuleContext parent, int invokingState) {
			super(parent, invokingState);
		}

		@Override
		public int getRuleIndex() {
			return RULE_importDeclare;
		}

		@Override
		public void enterRule(ParseTreeListener listener) {
			if (listener instanceof FunctionParserListener)
				((FunctionParserListener) listener).enterImportDeclare(this);
		}

		@Override
		public void exitRule(ParseTreeListener listener) {
			if (listener instanceof FunctionParserListener) ((FunctionParserListener) listener).exitImportDeclare(this);
		}
	}

	public final ImportDeclareContext importDeclare() throws RecognitionException {
		ImportDeclareContext _localctx = new ImportDeclareContext(_ctx, getState());
		enterRule(_localctx, 4, RULE_importDeclare);
		int _la;
		try {
			enterOuterAlt(_localctx, 1);
			{
				setState(59);
				match(IMPORT);
				setState(60);
				identifier();
				setState(63);
				_errHandler.sync(this);
				_la = _input.LA(1);
				if (_la == DOT) {
					{
						setState(61);
						match(DOT);
						setState(62);
						match(ASTERISK);
					}
				}

				setState(66);
				_errHandler.sync(this);
				_la = _input.LA(1);
				if (_la == SEMICOLON) {
					{
						setState(65);
						match(SEMICOLON);
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

	public static class DialectInterfaceContext extends ParserRuleContext {
		public TerminalNode INTERFACE() {
			return getToken(FunctionParser.INTERFACE, 0);
		}

		public SimpleIdentifierContext simpleIdentifier() {
			return getRuleContext(SimpleIdentifierContext.class, 0);
		}

		public TerminalNode COLON() {
			return getToken(FunctionParser.COLON, 0);
		}

		public IdentifierContext identifier() {
			return getRuleContext(IdentifierContext.class, 0);
		}

		public TerminalNode LCURL() {
			return getToken(FunctionParser.LCURL, 0);
		}

		public TerminalNode RCURL() {
			return getToken(FunctionParser.RCURL, 0);
		}

		public List<FunctionDefineContext> functionDefine() {
			return getRuleContexts(FunctionDefineContext.class);
		}

		public FunctionDefineContext functionDefine(int i) {
			return getRuleContext(FunctionDefineContext.class, i);
		}

		public DialectInterfaceContext(ParserRuleContext parent, int invokingState) {
			super(parent, invokingState);
		}

		@Override
		public int getRuleIndex() {
			return RULE_dialectInterface;
		}

		@Override
		public void enterRule(ParseTreeListener listener) {
			if (listener instanceof FunctionParserListener)
				((FunctionParserListener) listener).enterDialectInterface(this);
		}

		@Override
		public void exitRule(ParseTreeListener listener) {
			if (listener instanceof FunctionParserListener)
				((FunctionParserListener) listener).exitDialectInterface(this);
		}
	}

	public final DialectInterfaceContext dialectInterface() throws RecognitionException {
		DialectInterfaceContext _localctx = new DialectInterfaceContext(_ctx, getState());
		enterRule(_localctx, 6, RULE_dialectInterface);
		int _la;
		try {
			enterOuterAlt(_localctx, 1);
			{
				setState(68);
				match(INTERFACE);
				setState(69);
				simpleIdentifier();
				setState(72);
				_errHandler.sync(this);
				_la = _input.LA(1);
				if (_la == COLON) {
					{
						setState(70);
						match(COLON);
						setState(71);
						identifier();
					}
				}

				setState(82);
				_errHandler.sync(this);
				_la = _input.LA(1);
				if (_la == LCURL) {
					{
						setState(74);
						match(LCURL);
						setState(78);
						_errHandler.sync(this);
						_la = _input.LA(1);
						while ((((_la) & ~0x3f) == 0 && ((1L << _la) & ((1L << AT) | (1L << OVERRIDE) | (1L << PRIVATE) | (1L << ABSTRACT) | (1L << FUN))) != 0)) {
							{
								{
									setState(75);
									functionDefine();
								}
							}
							setState(80);
							_errHandler.sync(this);
							_la = _input.LA(1);
						}
						setState(81);
						match(RCURL);
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

	public static class TypeAliasDelareContext extends ParserRuleContext {
		public TerminalNode TYPEALIAS() {
			return getToken(FunctionParser.TYPEALIAS, 0);
		}

		public TerminalNode EQUAL() {
			return getToken(FunctionParser.EQUAL, 0);
		}

		public List<IdentifierContext> identifier() {
			return getRuleContexts(IdentifierContext.class);
		}

		public IdentifierContext identifier(int i) {
			return getRuleContext(IdentifierContext.class, i);
		}

		public DataTypeContext dataType() {
			return getRuleContext(DataTypeContext.class, 0);
		}

		public TypeAliasDelareContext(ParserRuleContext parent, int invokingState) {
			super(parent, invokingState);
		}

		@Override
		public int getRuleIndex() {
			return RULE_typeAliasDelare;
		}

		@Override
		public void enterRule(ParseTreeListener listener) {
			if (listener instanceof FunctionParserListener)
				((FunctionParserListener) listener).enterTypeAliasDelare(this);
		}

		@Override
		public void exitRule(ParseTreeListener listener) {
			if (listener instanceof FunctionParserListener)
				((FunctionParserListener) listener).exitTypeAliasDelare(this);
		}
	}

	public final TypeAliasDelareContext typeAliasDelare() throws RecognitionException {
		TypeAliasDelareContext _localctx = new TypeAliasDelareContext(_ctx, getState());
		enterRule(_localctx, 8, RULE_typeAliasDelare);
		try {
			enterOuterAlt(_localctx, 1);
			{
				setState(84);
				match(TYPEALIAS);
				setState(87);
				_errHandler.sync(this);
				switch (getInterpreter().adaptivePredict(_input, 9, _ctx)) {
					case 1: {
						setState(85);
						identifier();
					}
					break;
					case 2: {
						setState(86);
						dataType();
					}
					break;
				}
				setState(89);
				match(EQUAL);
				setState(90);
				identifier();
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

	public static class FunctionDefineContext extends ParserRuleContext {
		public TerminalNode FUN() {
			return getToken(FunctionParser.FUN, 0);
		}

		public SimpleIdentifierContext simpleIdentifier() {
			return getRuleContext(SimpleIdentifierContext.class, 0);
		}

		public TerminalNode LPAREN() {
			return getToken(FunctionParser.LPAREN, 0);
		}

		public TerminalNode RPAREN() {
			return getToken(FunctionParser.RPAREN, 0);
		}

		public TerminalNode COLON() {
			return getToken(FunctionParser.COLON, 0);
		}

		public DataTypeContext dataType() {
			return getRuleContext(DataTypeContext.class, 0);
		}

		public FunctionModifierListContext functionModifierList() {
			return getRuleContext(FunctionModifierListContext.class, 0);
		}

		public TypeParametersContext typeParameters() {
			return getRuleContext(TypeParametersContext.class, 0);
		}

		public FunctionParamDefinesContext functionParamDefines() {
			return getRuleContext(FunctionParamDefinesContext.class, 0);
		}

		public TerminalNode SEMICOLON() {
			return getToken(FunctionParser.SEMICOLON, 0);
		}

		public FunctionDefineContext(ParserRuleContext parent, int invokingState) {
			super(parent, invokingState);
		}

		@Override
		public int getRuleIndex() {
			return RULE_functionDefine;
		}

		@Override
		public void enterRule(ParseTreeListener listener) {
			if (listener instanceof FunctionParserListener)
				((FunctionParserListener) listener).enterFunctionDefine(this);
		}

		@Override
		public void exitRule(ParseTreeListener listener) {
			if (listener instanceof FunctionParserListener)
				((FunctionParserListener) listener).exitFunctionDefine(this);
		}
	}

	public final FunctionDefineContext functionDefine() throws RecognitionException {
		FunctionDefineContext _localctx = new FunctionDefineContext(_ctx, getState());
		enterRule(_localctx, 10, RULE_functionDefine);
		int _la;
		try {
			enterOuterAlt(_localctx, 1);
			{
				setState(93);
				_errHandler.sync(this);
				_la = _input.LA(1);
				if ((((_la) & ~0x3f) == 0 && ((1L << _la) & ((1L << AT) | (1L << OVERRIDE) | (1L << PRIVATE) | (1L << ABSTRACT))) != 0)) {
					{
						setState(92);
						functionModifierList();
					}
				}

				setState(95);
				match(FUN);
				setState(97);
				_errHandler.sync(this);
				_la = _input.LA(1);
				if (_la == LANGLE) {
					{
						setState(96);
						typeParameters();
					}
				}

				setState(99);
				simpleIdentifier();
				setState(100);
				match(LPAREN);
				setState(102);
				_errHandler.sync(this);
				_la = _input.LA(1);
				if ((((_la) & ~0x3f) == 0 && ((1L << _la) & ((1L << AT) | (1L << PACKAGE) | (1L << IMPORT) | (1L << INTERFACE) | (1L << VARARG) | (1L << IDENTIFIER))) != 0)) {
					{
						setState(101);
						functionParamDefines();
					}
				}

				setState(104);
				match(RPAREN);
				setState(105);
				match(COLON);
				setState(106);
				dataType();
				setState(108);
				_errHandler.sync(this);
				_la = _input.LA(1);
				if (_la == SEMICOLON) {
					{
						setState(107);
						match(SEMICOLON);
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

	public static class FunctionModifierListContext extends ParserRuleContext {
		public List<AnnotationContext> annotation() {
			return getRuleContexts(AnnotationContext.class);
		}

		public AnnotationContext annotation(int i) {
			return getRuleContext(AnnotationContext.class, i);
		}

		public List<FunctionModifierContext> functionModifier() {
			return getRuleContexts(FunctionModifierContext.class);
		}

		public FunctionModifierContext functionModifier(int i) {
			return getRuleContext(FunctionModifierContext.class, i);
		}

		public FunctionModifierListContext(ParserRuleContext parent, int invokingState) {
			super(parent, invokingState);
		}

		@Override
		public int getRuleIndex() {
			return RULE_functionModifierList;
		}

		@Override
		public void enterRule(ParseTreeListener listener) {
			if (listener instanceof FunctionParserListener)
				((FunctionParserListener) listener).enterFunctionModifierList(this);
		}

		@Override
		public void exitRule(ParseTreeListener listener) {
			if (listener instanceof FunctionParserListener)
				((FunctionParserListener) listener).exitFunctionModifierList(this);
		}
	}

	public final FunctionModifierListContext functionModifierList() throws RecognitionException {
		FunctionModifierListContext _localctx = new FunctionModifierListContext(_ctx, getState());
		enterRule(_localctx, 12, RULE_functionModifierList);
		int _la;
		try {
			enterOuterAlt(_localctx, 1);
			{
				setState(112);
				_errHandler.sync(this);
				_la = _input.LA(1);
				do {
					{
						setState(112);
						_errHandler.sync(this);
						switch (_input.LA(1)) {
							case AT: {
								setState(110);
								annotation();
							}
							break;
							case OVERRIDE:
							case PRIVATE:
							case ABSTRACT: {
								setState(111);
								functionModifier();
							}
							break;
							default:
								throw new NoViableAltException(this);
						}
					}
					setState(114);
					_errHandler.sync(this);
					_la = _input.LA(1);
				} while ((((_la) & ~0x3f) == 0 && ((1L << _la) & ((1L << AT) | (1L << OVERRIDE) | (1L << PRIVATE) | (1L << ABSTRACT))) != 0));
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

	public static class AnnotationContext extends ParserRuleContext {
		public TerminalNode AT() {
			return getToken(FunctionParser.AT, 0);
		}

		public IdentifierContext identifier() {
			return getRuleContext(IdentifierContext.class, 0);
		}

		public TerminalNode LPAREN() {
			return getToken(FunctionParser.LPAREN, 0);
		}

		public TerminalNode RPAREN() {
			return getToken(FunctionParser.RPAREN, 0);
		}

		public FunctionParamUsagesContext functionParamUsages() {
			return getRuleContext(FunctionParamUsagesContext.class, 0);
		}

		public AnnotationContext(ParserRuleContext parent, int invokingState) {
			super(parent, invokingState);
		}

		@Override
		public int getRuleIndex() {
			return RULE_annotation;
		}

		@Override
		public void enterRule(ParseTreeListener listener) {
			if (listener instanceof FunctionParserListener) ((FunctionParserListener) listener).enterAnnotation(this);
		}

		@Override
		public void exitRule(ParseTreeListener listener) {
			if (listener instanceof FunctionParserListener) ((FunctionParserListener) listener).exitAnnotation(this);
		}
	}

	public final AnnotationContext annotation() throws RecognitionException {
		AnnotationContext _localctx = new AnnotationContext(_ctx, getState());
		enterRule(_localctx, 14, RULE_annotation);
		int _la;
		try {
			enterOuterAlt(_localctx, 1);
			{
				setState(116);
				match(AT);
				setState(117);
				identifier();
				setState(123);
				_errHandler.sync(this);
				_la = _input.LA(1);
				if (_la == LPAREN) {
					{
						setState(118);
						match(LPAREN);
						setState(120);
						_errHandler.sync(this);
						_la = _input.LA(1);
						if (_la == NUMBER || _la == STRING) {
							{
								setState(119);
								functionParamUsages();
							}
						}

						setState(122);
						match(RPAREN);
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

	public static class FunctionModifierContext extends ParserRuleContext {
		public TerminalNode OVERRIDE() {
			return getToken(FunctionParser.OVERRIDE, 0);
		}

		public TerminalNode PRIVATE() {
			return getToken(FunctionParser.PRIVATE, 0);
		}

		public TerminalNode ABSTRACT() {
			return getToken(FunctionParser.ABSTRACT, 0);
		}

		public FunctionModifierContext(ParserRuleContext parent, int invokingState) {
			super(parent, invokingState);
		}

		@Override
		public int getRuleIndex() {
			return RULE_functionModifier;
		}

		@Override
		public void enterRule(ParseTreeListener listener) {
			if (listener instanceof FunctionParserListener)
				((FunctionParserListener) listener).enterFunctionModifier(this);
		}

		@Override
		public void exitRule(ParseTreeListener listener) {
			if (listener instanceof FunctionParserListener)
				((FunctionParserListener) listener).exitFunctionModifier(this);
		}
	}

	public final FunctionModifierContext functionModifier() throws RecognitionException {
		FunctionModifierContext _localctx = new FunctionModifierContext(_ctx, getState());
		enterRule(_localctx, 16, RULE_functionModifier);
		int _la;
		try {
			enterOuterAlt(_localctx, 1);
			{
				setState(125);
				_la = _input.LA(1);
				if (!((((_la) & ~0x3f) == 0 && ((1L << _la) & ((1L << OVERRIDE) | (1L << PRIVATE) | (1L << ABSTRACT))) != 0))) {
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

	public static class TypeParametersContext extends ParserRuleContext {
		public TerminalNode LANGLE() {
			return getToken(FunctionParser.LANGLE, 0);
		}

		public List<DataTypeContext> dataType() {
			return getRuleContexts(DataTypeContext.class);
		}

		public DataTypeContext dataType(int i) {
			return getRuleContext(DataTypeContext.class, i);
		}

		public TerminalNode RANGLE() {
			return getToken(FunctionParser.RANGLE, 0);
		}

		public List<TerminalNode> COMMA() {
			return getTokens(FunctionParser.COMMA);
		}

		public TerminalNode COMMA(int i) {
			return getToken(FunctionParser.COMMA, i);
		}

		public TypeParametersContext(ParserRuleContext parent, int invokingState) {
			super(parent, invokingState);
		}

		@Override
		public int getRuleIndex() {
			return RULE_typeParameters;
		}

		@Override
		public void enterRule(ParseTreeListener listener) {
			if (listener instanceof FunctionParserListener)
				((FunctionParserListener) listener).enterTypeParameters(this);
		}

		@Override
		public void exitRule(ParseTreeListener listener) {
			if (listener instanceof FunctionParserListener)
				((FunctionParserListener) listener).exitTypeParameters(this);
		}
	}

	public final TypeParametersContext typeParameters() throws RecognitionException {
		TypeParametersContext _localctx = new TypeParametersContext(_ctx, getState());
		enterRule(_localctx, 18, RULE_typeParameters);
		int _la;
		try {
			enterOuterAlt(_localctx, 1);
			{
				setState(127);
				match(LANGLE);
				setState(128);
				dataType();
				setState(133);
				_errHandler.sync(this);
				_la = _input.LA(1);
				while (_la == COMMA) {
					{
						{
							setState(129);
							match(COMMA);
							setState(130);
							dataType();
						}
					}
					setState(135);
					_errHandler.sync(this);
					_la = _input.LA(1);
				}
				setState(136);
				match(RANGLE);
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

	public static class FunctionParamDefinesContext extends ParserRuleContext {
		public List<FunctionParamDefineContext> functionParamDefine() {
			return getRuleContexts(FunctionParamDefineContext.class);
		}

		public FunctionParamDefineContext functionParamDefine(int i) {
			return getRuleContext(FunctionParamDefineContext.class, i);
		}

		public List<TerminalNode> COMMA() {
			return getTokens(FunctionParser.COMMA);
		}

		public TerminalNode COMMA(int i) {
			return getToken(FunctionParser.COMMA, i);
		}

		public FunctionParamDefinesContext(ParserRuleContext parent, int invokingState) {
			super(parent, invokingState);
		}

		@Override
		public int getRuleIndex() {
			return RULE_functionParamDefines;
		}

		@Override
		public void enterRule(ParseTreeListener listener) {
			if (listener instanceof FunctionParserListener)
				((FunctionParserListener) listener).enterFunctionParamDefines(this);
		}

		@Override
		public void exitRule(ParseTreeListener listener) {
			if (listener instanceof FunctionParserListener)
				((FunctionParserListener) listener).exitFunctionParamDefines(this);
		}
	}

	public final FunctionParamDefinesContext functionParamDefines() throws RecognitionException {
		FunctionParamDefinesContext _localctx = new FunctionParamDefinesContext(_ctx, getState());
		enterRule(_localctx, 20, RULE_functionParamDefines);
		int _la;
		try {
			int _alt;
			enterOuterAlt(_localctx, 1);
			{
				setState(138);
				functionParamDefine();
				setState(143);
				_errHandler.sync(this);
				_alt = getInterpreter().adaptivePredict(_input, 19, _ctx);
				while (_alt != 2 && _alt != ATN.INVALID_ALT_NUMBER) {
					if (_alt == 1) {
						{
							{
								setState(139);
								match(COMMA);
								setState(140);
								functionParamDefine();
							}
						}
					}
					setState(145);
					_errHandler.sync(this);
					_alt = getInterpreter().adaptivePredict(_input, 19, _ctx);
				}
				setState(147);
				_errHandler.sync(this);
				_la = _input.LA(1);
				if (_la == COMMA) {
					{
						setState(146);
						match(COMMA);
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

	public static class FunctionParamDefineContext extends ParserRuleContext {
		public SimpleIdentifierContext simpleIdentifier() {
			return getRuleContext(SimpleIdentifierContext.class, 0);
		}

		public TerminalNode COLON() {
			return getToken(FunctionParser.COLON, 0);
		}

		public DataTypeContext dataType() {
			return getRuleContext(DataTypeContext.class, 0);
		}

		public FunctionParamModifierListContext functionParamModifierList() {
			return getRuleContext(FunctionParamModifierListContext.class, 0);
		}

		public TerminalNode EQUAL() {
			return getToken(FunctionParser.EQUAL, 0);
		}

		public ExpressionContext expression() {
			return getRuleContext(ExpressionContext.class, 0);
		}

		public FunctionParamDefineContext(ParserRuleContext parent, int invokingState) {
			super(parent, invokingState);
		}

		@Override
		public int getRuleIndex() {
			return RULE_functionParamDefine;
		}

		@Override
		public void enterRule(ParseTreeListener listener) {
			if (listener instanceof FunctionParserListener)
				((FunctionParserListener) listener).enterFunctionParamDefine(this);
		}

		@Override
		public void exitRule(ParseTreeListener listener) {
			if (listener instanceof FunctionParserListener)
				((FunctionParserListener) listener).exitFunctionParamDefine(this);
		}
	}

	public final FunctionParamDefineContext functionParamDefine() throws RecognitionException {
		FunctionParamDefineContext _localctx = new FunctionParamDefineContext(_ctx, getState());
		enterRule(_localctx, 22, RULE_functionParamDefine);
		int _la;
		try {
			enterOuterAlt(_localctx, 1);
			{
				setState(150);
				_errHandler.sync(this);
				switch (getInterpreter().adaptivePredict(_input, 21, _ctx)) {
					case 1: {
						setState(149);
						functionParamModifierList();
					}
					break;
				}
				setState(152);
				simpleIdentifier();
				setState(153);
				match(COLON);
				setState(154);
				dataType();
				setState(157);
				_errHandler.sync(this);
				_la = _input.LA(1);
				if (_la == EQUAL) {
					{
						setState(155);
						match(EQUAL);
						setState(156);
						expression();
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

	public static class FunctionParamModifierListContext extends ParserRuleContext {
		public List<AnnotationContext> annotation() {
			return getRuleContexts(AnnotationContext.class);
		}

		public AnnotationContext annotation(int i) {
			return getRuleContext(AnnotationContext.class, i);
		}

		public List<FunctionParamModifierContext> functionParamModifier() {
			return getRuleContexts(FunctionParamModifierContext.class);
		}

		public FunctionParamModifierContext functionParamModifier(int i) {
			return getRuleContext(FunctionParamModifierContext.class, i);
		}

		public FunctionParamModifierListContext(ParserRuleContext parent, int invokingState) {
			super(parent, invokingState);
		}

		@Override
		public int getRuleIndex() {
			return RULE_functionParamModifierList;
		}

		@Override
		public void enterRule(ParseTreeListener listener) {
			if (listener instanceof FunctionParserListener)
				((FunctionParserListener) listener).enterFunctionParamModifierList(this);
		}

		@Override
		public void exitRule(ParseTreeListener listener) {
			if (listener instanceof FunctionParserListener)
				((FunctionParserListener) listener).exitFunctionParamModifierList(this);
		}
	}

	public final FunctionParamModifierListContext functionParamModifierList() throws RecognitionException {
		FunctionParamModifierListContext _localctx = new FunctionParamModifierListContext(_ctx, getState());
		enterRule(_localctx, 24, RULE_functionParamModifierList);
		try {
			int _alt;
			enterOuterAlt(_localctx, 1);
			{
				setState(161);
				_errHandler.sync(this);
				_alt = 1;
				do {
					switch (_alt) {
						case 1: {
							setState(161);
							_errHandler.sync(this);
							switch (_input.LA(1)) {
								case AT: {
									setState(159);
									annotation();
								}
								break;
								case VARARG: {
									setState(160);
									functionParamModifier();
								}
								break;
								default:
									throw new NoViableAltException(this);
							}
						}
						break;
						default:
							throw new NoViableAltException(this);
					}
					setState(163);
					_errHandler.sync(this);
					_alt = getInterpreter().adaptivePredict(_input, 24, _ctx);
				} while (_alt != 2 && _alt != ATN.INVALID_ALT_NUMBER);
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

	public static class FunctionParamModifierContext extends ParserRuleContext {
		public TerminalNode VARARG() {
			return getToken(FunctionParser.VARARG, 0);
		}

		public FunctionParamModifierContext(ParserRuleContext parent, int invokingState) {
			super(parent, invokingState);
		}

		@Override
		public int getRuleIndex() {
			return RULE_functionParamModifier;
		}

		@Override
		public void enterRule(ParseTreeListener listener) {
			if (listener instanceof FunctionParserListener)
				((FunctionParserListener) listener).enterFunctionParamModifier(this);
		}

		@Override
		public void exitRule(ParseTreeListener listener) {
			if (listener instanceof FunctionParserListener)
				((FunctionParserListener) listener).exitFunctionParamModifier(this);
		}
	}

	public final FunctionParamModifierContext functionParamModifier() throws RecognitionException {
		FunctionParamModifierContext _localctx = new FunctionParamModifierContext(_ctx, getState());
		enterRule(_localctx, 26, RULE_functionParamModifier);
		try {
			enterOuterAlt(_localctx, 1);
			{
				setState(165);
				match(VARARG);
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

	public static class FunctionParamUsagesContext extends ParserRuleContext {
		public List<ExpressionContext> expression() {
			return getRuleContexts(ExpressionContext.class);
		}

		public ExpressionContext expression(int i) {
			return getRuleContext(ExpressionContext.class, i);
		}

		public List<TerminalNode> COMMA() {
			return getTokens(FunctionParser.COMMA);
		}

		public TerminalNode COMMA(int i) {
			return getToken(FunctionParser.COMMA, i);
		}

		public FunctionParamUsagesContext(ParserRuleContext parent, int invokingState) {
			super(parent, invokingState);
		}

		@Override
		public int getRuleIndex() {
			return RULE_functionParamUsages;
		}

		@Override
		public void enterRule(ParseTreeListener listener) {
			if (listener instanceof FunctionParserListener)
				((FunctionParserListener) listener).enterFunctionParamUsages(this);
		}

		@Override
		public void exitRule(ParseTreeListener listener) {
			if (listener instanceof FunctionParserListener)
				((FunctionParserListener) listener).exitFunctionParamUsages(this);
		}
	}

	public final FunctionParamUsagesContext functionParamUsages() throws RecognitionException {
		FunctionParamUsagesContext _localctx = new FunctionParamUsagesContext(_ctx, getState());
		enterRule(_localctx, 28, RULE_functionParamUsages);
		int _la;
		try {
			enterOuterAlt(_localctx, 1);
			{
				setState(167);
				expression();
				setState(172);
				_errHandler.sync(this);
				_la = _input.LA(1);
				while (_la == COMMA) {
					{
						{
							setState(168);
							match(COMMA);
							setState(169);
							expression();
						}
					}
					setState(174);
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

	public static class ExpressionContext extends ParserRuleContext {
		public TerminalNode STRING() {
			return getToken(FunctionParser.STRING, 0);
		}

		public TerminalNode NUMBER() {
			return getToken(FunctionParser.NUMBER, 0);
		}

		public ExpressionContext(ParserRuleContext parent, int invokingState) {
			super(parent, invokingState);
		}

		@Override
		public int getRuleIndex() {
			return RULE_expression;
		}

		@Override
		public void enterRule(ParseTreeListener listener) {
			if (listener instanceof FunctionParserListener) ((FunctionParserListener) listener).enterExpression(this);
		}

		@Override
		public void exitRule(ParseTreeListener listener) {
			if (listener instanceof FunctionParserListener) ((FunctionParserListener) listener).exitExpression(this);
		}
	}

	public final ExpressionContext expression() throws RecognitionException {
		ExpressionContext _localctx = new ExpressionContext(_ctx, getState());
		enterRule(_localctx, 30, RULE_expression);
		int _la;
		try {
			enterOuterAlt(_localctx, 1);
			{
				setState(175);
				_la = _input.LA(1);
				if (!(_la == NUMBER || _la == STRING)) {
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
		public TerminalNode DT_BOOLEAN() {
			return getToken(FunctionParser.DT_BOOLEAN, 0);
		}

		public TerminalNode DT_INT() {
			return getToken(FunctionParser.DT_INT, 0);
		}

		public TerminalNode DT_DOUBLE() {
			return getToken(FunctionParser.DT_DOUBLE, 0);
		}

		public TerminalNode DT_STRING() {
			return getToken(FunctionParser.DT_STRING, 0);
		}

		public TerminalNode DT_DATE() {
			return getToken(FunctionParser.DT_DATE, 0);
		}

		public TerminalNode DT_TIME() {
			return getToken(FunctionParser.DT_TIME, 0);
		}

		public TerminalNode DT_DATETIME() {
			return getToken(FunctionParser.DT_DATETIME, 0);
		}

		public TerminalNode DT_ANY() {
			return getToken(FunctionParser.DT_ANY, 0);
		}

		public IdentifierContext identifier() {
			return getRuleContext(IdentifierContext.class, 0);
		}

		public TypeParametersContext typeParameters() {
			return getRuleContext(TypeParametersContext.class, 0);
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
			if (listener instanceof FunctionParserListener) ((FunctionParserListener) listener).enterDataType(this);
		}

		@Override
		public void exitRule(ParseTreeListener listener) {
			if (listener instanceof FunctionParserListener) ((FunctionParserListener) listener).exitDataType(this);
		}
	}

	public final DataTypeContext dataType() throws RecognitionException {
		DataTypeContext _localctx = new DataTypeContext(_ctx, getState());
		enterRule(_localctx, 32, RULE_dataType);
		int _la;
		try {
			setState(189);
			_errHandler.sync(this);
			switch (_input.LA(1)) {
				case DT_BOOLEAN:
					enterOuterAlt(_localctx, 1);
				{
					setState(177);
					match(DT_BOOLEAN);
				}
				break;
				case DT_INT:
					enterOuterAlt(_localctx, 2);
				{
					setState(178);
					match(DT_INT);
				}
				break;
				case DT_DOUBLE:
					enterOuterAlt(_localctx, 3);
				{
					setState(179);
					match(DT_DOUBLE);
				}
				break;
				case DT_STRING:
					enterOuterAlt(_localctx, 4);
				{
					setState(180);
					match(DT_STRING);
				}
				break;
				case DT_DATE:
					enterOuterAlt(_localctx, 5);
				{
					setState(181);
					match(DT_DATE);
				}
				break;
				case DT_TIME:
					enterOuterAlt(_localctx, 6);
				{
					setState(182);
					match(DT_TIME);
				}
				break;
				case DT_DATETIME:
					enterOuterAlt(_localctx, 7);
				{
					setState(183);
					match(DT_DATETIME);
				}
				break;
				case DT_ANY:
					enterOuterAlt(_localctx, 8);
				{
					setState(184);
					match(DT_ANY);
				}
				break;
				case PACKAGE:
				case IMPORT:
				case INTERFACE:
				case VARARG:
				case IDENTIFIER:
					enterOuterAlt(_localctx, 9);
				{
					setState(185);
					identifier();
					setState(187);
					_errHandler.sync(this);
					_la = _input.LA(1);
					if (_la == LANGLE) {
						{
							setState(186);
							typeParameters();
						}
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

	public static class IdentifierContext extends ParserRuleContext {
		public List<SimpleIdentifierContext> simpleIdentifier() {
			return getRuleContexts(SimpleIdentifierContext.class);
		}

		public SimpleIdentifierContext simpleIdentifier(int i) {
			return getRuleContext(SimpleIdentifierContext.class, i);
		}

		public List<TerminalNode> DOT() {
			return getTokens(FunctionParser.DOT);
		}

		public TerminalNode DOT(int i) {
			return getToken(FunctionParser.DOT, i);
		}

		public IdentifierContext(ParserRuleContext parent, int invokingState) {
			super(parent, invokingState);
		}

		@Override
		public int getRuleIndex() {
			return RULE_identifier;
		}

		@Override
		public void enterRule(ParseTreeListener listener) {
			if (listener instanceof FunctionParserListener) ((FunctionParserListener) listener).enterIdentifier(this);
		}

		@Override
		public void exitRule(ParseTreeListener listener) {
			if (listener instanceof FunctionParserListener) ((FunctionParserListener) listener).exitIdentifier(this);
		}
	}

	public final IdentifierContext identifier() throws RecognitionException {
		IdentifierContext _localctx = new IdentifierContext(_ctx, getState());
		enterRule(_localctx, 34, RULE_identifier);
		try {
			int _alt;
			enterOuterAlt(_localctx, 1);
			{
				setState(191);
				simpleIdentifier();
				setState(196);
				_errHandler.sync(this);
				_alt = getInterpreter().adaptivePredict(_input, 28, _ctx);
				while (_alt != 2 && _alt != ATN.INVALID_ALT_NUMBER) {
					if (_alt == 1) {
						{
							{
								setState(192);
								match(DOT);
								setState(193);
								simpleIdentifier();
							}
						}
					}
					setState(198);
					_errHandler.sync(this);
					_alt = getInterpreter().adaptivePredict(_input, 28, _ctx);
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

	public static class SimpleIdentifierContext extends ParserRuleContext {
		public TerminalNode IDENTIFIER() {
			return getToken(FunctionParser.IDENTIFIER, 0);
		}

		public TerminalNode PACKAGE() {
			return getToken(FunctionParser.PACKAGE, 0);
		}

		public TerminalNode IMPORT() {
			return getToken(FunctionParser.IMPORT, 0);
		}

		public TerminalNode INTERFACE() {
			return getToken(FunctionParser.INTERFACE, 0);
		}

		public TerminalNode VARARG() {
			return getToken(FunctionParser.VARARG, 0);
		}

		public SimpleIdentifierContext(ParserRuleContext parent, int invokingState) {
			super(parent, invokingState);
		}

		@Override
		public int getRuleIndex() {
			return RULE_simpleIdentifier;
		}

		@Override
		public void enterRule(ParseTreeListener listener) {
			if (listener instanceof FunctionParserListener)
				((FunctionParserListener) listener).enterSimpleIdentifier(this);
		}

		@Override
		public void exitRule(ParseTreeListener listener) {
			if (listener instanceof FunctionParserListener)
				((FunctionParserListener) listener).exitSimpleIdentifier(this);
		}
	}

	public final SimpleIdentifierContext simpleIdentifier() throws RecognitionException {
		SimpleIdentifierContext _localctx = new SimpleIdentifierContext(_ctx, getState());
		enterRule(_localctx, 36, RULE_simpleIdentifier);
		int _la;
		try {
			enterOuterAlt(_localctx, 1);
			{
				setState(199);
				_la = _input.LA(1);
				if (!((((_la) & ~0x3f) == 0 && ((1L << _la) & ((1L << PACKAGE) | (1L << IMPORT) | (1L << INTERFACE) | (1L << VARARG) | (1L << IDENTIFIER))) != 0))) {
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
			"\3\u608b\ua72a\u8133\ub9ed\u417c\u3be7\u7786\u5964\3)\u00cc\4\2\t\2\4" +
					"\3\t\3\4\4\t\4\4\5\t\5\4\6\t\6\4\7\t\7\4\b\t\b\4\t\t\t\4\n\t\n\4\13\t" +
					"\13\4\f\t\f\4\r\t\r\4\16\t\16\4\17\t\17\4\20\t\20\4\21\t\21\4\22\t\22" +
					"\4\23\t\23\4\24\t\24\3\2\3\2\7\2+\n\2\f\2\16\2.\13\2\3\2\3\2\7\2\62\n" +
					"\2\f\2\16\2\65\13\2\3\2\3\2\3\3\3\3\3\3\5\3<\n\3\3\4\3\4\3\4\3\4\5\4B" +
					"\n\4\3\4\5\4E\n\4\3\5\3\5\3\5\3\5\5\5K\n\5\3\5\3\5\7\5O\n\5\f\5\16\5R" +
					"\13\5\3\5\5\5U\n\5\3\6\3\6\3\6\5\6Z\n\6\3\6\3\6\3\6\3\7\5\7`\n\7\3\7\3" +
					"\7\5\7d\n\7\3\7\3\7\3\7\5\7i\n\7\3\7\3\7\3\7\3\7\5\7o\n\7\3\b\3\b\6\b" +
					"s\n\b\r\b\16\bt\3\t\3\t\3\t\3\t\5\t{\n\t\3\t\5\t~\n\t\3\n\3\n\3\13\3\13" +
					"\3\13\3\13\7\13\u0086\n\13\f\13\16\13\u0089\13\13\3\13\3\13\3\f\3\f\3" +
					"\f\7\f\u0090\n\f\f\f\16\f\u0093\13\f\3\f\5\f\u0096\n\f\3\r\5\r\u0099\n" +
					"\r\3\r\3\r\3\r\3\r\3\r\5\r\u00a0\n\r\3\16\3\16\6\16\u00a4\n\16\r\16\16" +
					"\16\u00a5\3\17\3\17\3\20\3\20\3\20\7\20\u00ad\n\20\f\20\16\20\u00b0\13" +
					"\20\3\21\3\21\3\22\3\22\3\22\3\22\3\22\3\22\3\22\3\22\3\22\3\22\5\22\u00be" +
					"\n\22\5\22\u00c0\n\22\3\23\3\23\3\23\7\23\u00c5\n\23\f\23\16\23\u00c8" +
					"\13\23\3\24\3\24\3\24\2\2\25\2\4\6\b\n\f\16\20\22\24\26\30\32\34\36 \"" +
					"$&\2\5\3\2\32\34\3\2\'(\5\2\25\26\30\31))\2\u00dc\2(\3\2\2\2\48\3\2\2" +
					"\2\6=\3\2\2\2\bF\3\2\2\2\nV\3\2\2\2\f_\3\2\2\2\16r\3\2\2\2\20v\3\2\2\2" +
					"\22\177\3\2\2\2\24\u0081\3\2\2\2\26\u008c\3\2\2\2\30\u0098\3\2\2\2\32" +
					"\u00a3\3\2\2\2\34\u00a7\3\2\2\2\36\u00a9\3\2\2\2 \u00b1\3\2\2\2\"\u00bf" +
					"\3\2\2\2$\u00c1\3\2\2\2&\u00c9\3\2\2\2(,\5\4\3\2)+\5\6\4\2*)\3\2\2\2+" +
					".\3\2\2\2,*\3\2\2\2,-\3\2\2\2-\63\3\2\2\2.,\3\2\2\2/\62\5\n\6\2\60\62" +
					"\5\b\5\2\61/\3\2\2\2\61\60\3\2\2\2\62\65\3\2\2\2\63\61\3\2\2\2\63\64\3" +
					"\2\2\2\64\66\3\2\2\2\65\63\3\2\2\2\66\67\7\2\2\3\67\3\3\2\2\289\7\25\2" +
					"\29;\5$\23\2:<\7\24\2\2;:\3\2\2\2;<\3\2\2\2<\5\3\2\2\2=>\7\26\2\2>A\5" +
					"$\23\2?@\7\22\2\2@B\7\23\2\2A?\3\2\2\2AB\3\2\2\2BD\3\2\2\2CE\7\24\2\2" +
					"DC\3\2\2\2DE\3\2\2\2E\7\3\2\2\2FG\7\30\2\2GJ\5&\24\2HI\7\20\2\2IK\5$\23" +
					"\2JH\3\2\2\2JK\3\2\2\2KT\3\2\2\2LP\7\f\2\2MO\5\f\7\2NM\3\2\2\2OR\3\2\2" +
					"\2PN\3\2\2\2PQ\3\2\2\2QS\3\2\2\2RP\3\2\2\2SU\7\r\2\2TL\3\2\2\2TU\3\2\2" +
					"\2U\t\3\2\2\2VY\7\35\2\2WZ\5$\23\2XZ\5\"\22\2YW\3\2\2\2YX\3\2\2\2Z[\3" +
					"\2\2\2[\\\7\16\2\2\\]\5$\23\2]\13\3\2\2\2^`\5\16\b\2_^\3\2\2\2_`\3\2\2" +
					"\2`a\3\2\2\2ac\7\36\2\2bd\5\24\13\2cb\3\2\2\2cd\3\2\2\2de\3\2\2\2ef\5" +
					"&\24\2fh\7\n\2\2gi\5\26\f\2hg\3\2\2\2hi\3\2\2\2ij\3\2\2\2jk\7\13\2\2k" +
					"l\7\20\2\2ln\5\"\22\2mo\7\24\2\2nm\3\2\2\2no\3\2\2\2o\r\3\2\2\2ps\5\20" +
					"\t\2qs\5\22\n\2rp\3\2\2\2rq\3\2\2\2st\3\2\2\2tr\3\2\2\2tu\3\2\2\2u\17" +
					"\3\2\2\2vw\7\7\2\2w}\5$\23\2xz\7\n\2\2y{\5\36\20\2zy\3\2\2\2z{\3\2\2\2" +
					"{|\3\2\2\2|~\7\13\2\2}x\3\2\2\2}~\3\2\2\2~\21\3\2\2\2\177\u0080\t\2\2" +
					"\2\u0080\23\3\2\2\2\u0081\u0082\7\b\2\2\u0082\u0087\5\"\22\2\u0083\u0084" +
					"\7\21\2\2\u0084\u0086\5\"\22\2\u0085\u0083\3\2\2\2\u0086\u0089\3\2\2\2" +
					"\u0087\u0085\3\2\2\2\u0087\u0088\3\2\2\2\u0088\u008a\3\2\2\2\u0089\u0087" +
					"\3\2\2\2\u008a\u008b\7\t\2\2\u008b\25\3\2\2\2\u008c\u0091\5\30\r\2\u008d" +
					"\u008e\7\21\2\2\u008e\u0090\5\30\r\2\u008f\u008d\3\2\2\2\u0090\u0093\3" +
					"\2\2\2\u0091\u008f\3\2\2\2\u0091\u0092\3\2\2\2\u0092\u0095\3\2\2\2\u0093" +
					"\u0091\3\2\2\2\u0094\u0096\7\21\2\2\u0095\u0094\3\2\2\2\u0095\u0096\3" +
					"\2\2\2\u0096\27\3\2\2\2\u0097\u0099\5\32\16\2\u0098\u0097\3\2\2\2\u0098" +
					"\u0099\3\2\2\2\u0099\u009a\3\2\2\2\u009a\u009b\5&\24\2\u009b\u009c\7\20" +
					"\2\2\u009c\u009f\5\"\22\2\u009d\u009e\7\16\2\2\u009e\u00a0\5 \21\2\u009f" +
					"\u009d\3\2\2\2\u009f\u00a0\3\2\2\2\u00a0\31\3\2\2\2\u00a1\u00a4\5\20\t" +
					"\2\u00a2\u00a4\5\34\17\2\u00a3\u00a1\3\2\2\2\u00a3\u00a2\3\2\2\2\u00a4" +
					"\u00a5\3\2\2\2\u00a5\u00a3\3\2\2\2\u00a5\u00a6\3\2\2\2\u00a6\33\3\2\2" +
					"\2\u00a7\u00a8\7\31\2\2\u00a8\35\3\2\2\2\u00a9\u00ae\5 \21\2\u00aa\u00ab" +
					"\7\21\2\2\u00ab\u00ad\5 \21\2\u00ac\u00aa\3\2\2\2\u00ad\u00b0\3\2\2\2" +
					"\u00ae\u00ac\3\2\2\2\u00ae\u00af\3\2\2\2\u00af\37\3\2\2\2\u00b0\u00ae" +
					"\3\2\2\2\u00b1\u00b2\t\3\2\2\u00b2!\3\2\2\2\u00b3\u00c0\7\"\2\2\u00b4" +
					"\u00c0\7 \2\2\u00b5\u00c0\7&\2\2\u00b6\u00c0\7\37\2\2\u00b7\u00c0\7#\2" +
					"\2\u00b8\u00c0\7$\2\2\u00b9\u00c0\7%\2\2\u00ba\u00c0\7!\2\2\u00bb\u00bd" +
					"\5$\23\2\u00bc\u00be\5\24\13\2\u00bd\u00bc\3\2\2\2\u00bd\u00be\3\2\2\2" +
					"\u00be\u00c0\3\2\2\2\u00bf\u00b3\3\2\2\2\u00bf\u00b4\3\2\2\2\u00bf\u00b5" +
					"\3\2\2\2\u00bf\u00b6\3\2\2\2\u00bf\u00b7\3\2\2\2\u00bf\u00b8\3\2\2\2\u00bf" +
					"\u00b9\3\2\2\2\u00bf\u00ba\3\2\2\2\u00bf\u00bb\3\2\2\2\u00c0#\3\2\2\2" +
					"\u00c1\u00c6\5&\24\2\u00c2\u00c3\7\22\2\2\u00c3\u00c5\5&\24\2\u00c4\u00c2" +
					"\3\2\2\2\u00c5\u00c8\3\2\2\2\u00c6\u00c4\3\2\2\2\u00c6\u00c7\3\2\2\2\u00c7" +
					"%\3\2\2\2\u00c8\u00c6\3\2\2\2\u00c9\u00ca\t\4\2\2\u00ca\'\3\2\2\2\37," +
					"\61\63;ADJPTY_chnrtz}\u0087\u0091\u0095\u0098\u009f\u00a3\u00a5\u00ae" +
					"\u00bd\u00bf\u00c6";
	public static final ATN _ATN =
			new ATNDeserializer().deserialize(_serializedATN.toCharArray());

	static {
		_decisionToDFA = new DFA[_ATN.getNumberOfDecisions()];
		for (int i = 0; i < _ATN.getNumberOfDecisions(); i++) {
			_decisionToDFA[i] = new DFA(_ATN.getDecisionState(i), i);
		}
	}
}