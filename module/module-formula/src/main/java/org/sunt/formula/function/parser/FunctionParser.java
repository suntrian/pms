// Generated from D:/projects/pms/module/module-formula/src/main/java/org/sunt/formula/function/parser\FunctionParser.g4 by ANTLR 4.9.1
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
			COMMA = 15, DOT = 16, ASTERISK = 17, SEMICOLON = 18, QUESTION = 19, PACKAGE = 20,
			IMPORT = 21, CLASS = 22, INTERFACE = 23, VARARG = 24, OVERRIDE = 25, PRIVATE = 26,
			ABSTRACT = 27, TYPEALIAS = 28, FUN = 29, DT_STRING = 30, DT_INT = 31, DT_ANY = 32,
			DT_BOOLEAN = 33, DT_DATE = 34, DT_TIME = 35, DT_DATETIME = 36, DT_DOUBLE = 37, NULL = 38,
			NUMBER = 39, STRING = 40, IDENTIFIER = 41;
	public static final int
			RULE_root = 0, RULE_packageDeclare = 1, RULE_importDeclare = 2, RULE_dialectInterface = 3,
			RULE_typeAliasDelare = 4, RULE_classDeclare = 5, RULE_functionDefine = 6,
			RULE_functionModifierList = 7, RULE_annotation = 8, RULE_functionModifier = 9,
			RULE_functionImplement = 10, RULE_functionSimpleImpl = 11, RULE_functionFullImpl = 12,
			RULE_typeParameters = 13, RULE_functionParamDefines = 14, RULE_functionParamDefine = 15,
			RULE_functionParamModifierList = 16, RULE_functionParamModifier = 17,
			RULE_functionParams = 18, RULE_functionCall = 19, RULE_expression = 20,
			RULE_dataType = 21, RULE_dataTypeNull = 22, RULE_identifier = 23, RULE_simpleIdentifier = 24;

	private static String[] makeRuleNames() {
		return new String[]{
				"root", "packageDeclare", "importDeclare", "dialectInterface", "typeAliasDelare",
				"classDeclare", "functionDefine", "functionModifierList", "annotation",
				"functionModifier", "functionImplement", "functionSimpleImpl", "functionFullImpl",
				"typeParameters", "functionParamDefines", "functionParamDefine", "functionParamModifierList",
				"functionParamModifier", "functionParams", "functionCall", "expression",
				"dataType", "dataTypeNull", "identifier", "simpleIdentifier"
		};
	}
	public static final String[] ruleNames = makeRuleNames();

	private static String[] makeLiteralNames() {
		return new String[]{
				null, null, null, null, null, "'@'", "'<'", "'>'", "'('", "')'", "'{'",
				"'}'", "'='", "'::'", "':'", "','", "'.'", "'*'", "';'", "'?'", "'package'",
				"'import'", "'class'", "'interface'", "'vararg'", "'override'", "'private'",
				"'abstract'", "'typealias'", "'fun'", "'String'", null, "'Any'", null,
				"'Date'", "'Time'", "'DateTime'", "'Double'", "'null'"
		};
	}
	private static final String[] _LITERAL_NAMES = makeLiteralNames();
	private static String[] makeSymbolicNames() {
		return new String[]{
				null, "LINE_COMMENT", "BLOCK_COMMENT", "WS", "NEWLINE", "AT", "LANGLE",
				"RANGLE", "LPAREN", "RPAREN", "LCURL", "RCURL", "EQUAL", "COLONCOLON",
				"COLON", "COMMA", "DOT", "ASTERISK", "SEMICOLON", "QUESTION", "PACKAGE",
				"IMPORT", "CLASS", "INTERFACE", "VARARG", "OVERRIDE", "PRIVATE", "ABSTRACT",
				"TYPEALIAS", "FUN", "DT_STRING", "DT_INT", "DT_ANY", "DT_BOOLEAN", "DT_DATE",
				"DT_TIME", "DT_DATETIME", "DT_DOUBLE", "NULL", "NUMBER", "STRING", "IDENTIFIER"
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

		public List<ClassDeclareContext> classDeclare() {
			return getRuleContexts(ClassDeclareContext.class);
		}

		public ClassDeclareContext classDeclare(int i) {
			return getRuleContext(ClassDeclareContext.class, i);
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
				setState(50);
				packageDeclare();
				setState(54);
				_errHandler.sync(this);
				_la = _input.LA(1);
				while (_la == IMPORT) {
					{
						{
							setState(51);
							importDeclare();
						}
					}
					setState(56);
					_errHandler.sync(this);
					_la = _input.LA(1);
				}
				setState(62);
				_errHandler.sync(this);
				_la = _input.LA(1);
				while ((((_la) & ~0x3f) == 0 && ((1L << _la) & ((1L << CLASS) | (1L << INTERFACE) | (1L << TYPEALIAS))) != 0)) {
					{
						setState(60);
						_errHandler.sync(this);
						switch (_input.LA(1)) {
							case TYPEALIAS: {
								setState(57);
								typeAliasDelare();
							}
							break;
							case CLASS: {
								setState(58);
								classDeclare();
							}
							break;
							case INTERFACE: {
								setState(59);
								dialectInterface();
							}
							break;
							default:
								throw new NoViableAltException(this);
						}
					}
					setState(64);
					_errHandler.sync(this);
					_la = _input.LA(1);
				}
				setState(65);
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
				setState(67);
				match(PACKAGE);
				setState(68);
				identifier();
				setState(70);
				_errHandler.sync(this);
				_la = _input.LA(1);
				if (_la == SEMICOLON) {
					{
						setState(69);
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
				setState(72);
				match(IMPORT);
				setState(73);
				identifier();
				setState(76);
				_errHandler.sync(this);
				_la = _input.LA(1);
				if (_la == DOT) {
					{
						setState(74);
						match(DOT);
						setState(75);
						match(ASTERISK);
					}
				}

				setState(79);
				_errHandler.sync(this);
				_la = _input.LA(1);
				if (_la == SEMICOLON) {
					{
						setState(78);
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
				setState(81);
				match(INTERFACE);
				setState(82);
				simpleIdentifier();
				setState(85);
				_errHandler.sync(this);
				_la = _input.LA(1);
				if (_la == COLON) {
					{
						setState(83);
						match(COLON);
						setState(84);
						identifier();
					}
				}

				setState(95);
				_errHandler.sync(this);
				_la = _input.LA(1);
				if (_la == LCURL) {
					{
						setState(87);
						match(LCURL);
						setState(91);
						_errHandler.sync(this);
						_la = _input.LA(1);
						while ((((_la) & ~0x3f) == 0 && ((1L << _la) & ((1L << AT) | (1L << OVERRIDE) | (1L << PRIVATE) | (1L << ABSTRACT) | (1L << FUN))) != 0)) {
							{
								{
									setState(88);
									functionDefine();
								}
							}
							setState(93);
							_errHandler.sync(this);
							_la = _input.LA(1);
						}
						setState(94);
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

		public DataTypeNullContext dataTypeNull() {
			return getRuleContext(DataTypeNullContext.class, 0);
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
				setState(97);
				match(TYPEALIAS);
				setState(100);
				_errHandler.sync(this);
				switch (getInterpreter().adaptivePredict(_input, 9, _ctx)) {
					case 1: {
						setState(98);
						identifier();
					}
					break;
					case 2: {
						setState(99);
						dataTypeNull();
					}
					break;
				}
				setState(102);
				match(EQUAL);
				setState(103);
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

	public static class ClassDeclareContext extends ParserRuleContext {
		public TerminalNode CLASS() {
			return getToken(FunctionParser.CLASS, 0);
		}

		public List<IdentifierContext> identifier() {
			return getRuleContexts(IdentifierContext.class);
		}

		public IdentifierContext identifier(int i) {
			return getRuleContext(IdentifierContext.class, i);
		}

		public TerminalNode COLON() {
			return getToken(FunctionParser.COLON, 0);
		}

		public TerminalNode LCURL() {
			return getToken(FunctionParser.LCURL, 0);
		}

		public TerminalNode RCURL() {
			return getToken(FunctionParser.RCURL, 0);
		}

		public ClassDeclareContext(ParserRuleContext parent, int invokingState) {
			super(parent, invokingState);
		}

		@Override
		public int getRuleIndex() {
			return RULE_classDeclare;
		}

		@Override
		public void enterRule(ParseTreeListener listener) {
			if (listener instanceof FunctionParserListener) ((FunctionParserListener) listener).enterClassDeclare(this);
		}

		@Override
		public void exitRule(ParseTreeListener listener) {
			if (listener instanceof FunctionParserListener) ((FunctionParserListener) listener).exitClassDeclare(this);
		}
	}

	public final ClassDeclareContext classDeclare() throws RecognitionException {
		ClassDeclareContext _localctx = new ClassDeclareContext(_ctx, getState());
		enterRule(_localctx, 10, RULE_classDeclare);
		int _la;
		try {
			enterOuterAlt(_localctx, 1);
			{
				setState(105);
				match(CLASS);
				setState(106);
				identifier();
				setState(109);
				_errHandler.sync(this);
				_la = _input.LA(1);
				if (_la == COLON) {
					{
						setState(107);
						match(COLON);
						setState(108);
						identifier();
					}
				}

				setState(113);
				_errHandler.sync(this);
				_la = _input.LA(1);
				if (_la == LCURL) {
					{
						setState(111);
						match(LCURL);
						setState(112);
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

		public DataTypeNullContext dataTypeNull() {
			return getRuleContext(DataTypeNullContext.class, 0);
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

		public FunctionImplementContext functionImplement() {
			return getRuleContext(FunctionImplementContext.class, 0);
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
		enterRule(_localctx, 12, RULE_functionDefine);
		int _la;
		try {
			enterOuterAlt(_localctx, 1);
			{
				setState(116);
				_errHandler.sync(this);
				_la = _input.LA(1);
				if ((((_la) & ~0x3f) == 0 && ((1L << _la) & ((1L << AT) | (1L << OVERRIDE) | (1L << PRIVATE) | (1L << ABSTRACT))) != 0)) {
					{
						setState(115);
						functionModifierList();
					}
				}

				setState(118);
				match(FUN);
				setState(120);
				_errHandler.sync(this);
				_la = _input.LA(1);
				if (_la == LANGLE) {
					{
						setState(119);
						typeParameters();
					}
				}

				setState(122);
				simpleIdentifier();
				setState(123);
				match(LPAREN);
				setState(125);
				_errHandler.sync(this);
				_la = _input.LA(1);
				if ((((_la) & ~0x3f) == 0 && ((1L << _la) & ((1L << AT) | (1L << PACKAGE) | (1L << IMPORT) | (1L << INTERFACE) | (1L << VARARG) | (1L << IDENTIFIER))) != 0)) {
					{
						setState(124);
						functionParamDefines();
					}
				}

				setState(127);
				match(RPAREN);
				setState(128);
				match(COLON);
				setState(129);
				dataTypeNull();
				setState(131);
				_errHandler.sync(this);
				_la = _input.LA(1);
				if (_la == LCURL || _la == EQUAL) {
					{
						setState(130);
						functionImplement();
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
		enterRule(_localctx, 14, RULE_functionModifierList);
		int _la;
		try {
			enterOuterAlt(_localctx, 1);
			{
				setState(135);
				_errHandler.sync(this);
				_la = _input.LA(1);
				do {
					{
						setState(135);
						_errHandler.sync(this);
						switch (_input.LA(1)) {
							case AT: {
								setState(133);
								annotation();
							}
							break;
							case OVERRIDE:
							case PRIVATE:
							case ABSTRACT: {
								setState(134);
								functionModifier();
							}
							break;
							default:
								throw new NoViableAltException(this);
						}
					}
					setState(137);
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

		public FunctionParamsContext functionParams() {
			return getRuleContext(FunctionParamsContext.class, 0);
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
		enterRule(_localctx, 16, RULE_annotation);
		int _la;
		try {
			enterOuterAlt(_localctx, 1);
			{
				setState(139);
				match(AT);
				setState(140);
				identifier();
				setState(146);
				_errHandler.sync(this);
				_la = _input.LA(1);
				if (_la == LPAREN) {
					{
						setState(141);
						match(LPAREN);
						setState(143);
						_errHandler.sync(this);
						_la = _input.LA(1);
						if ((((_la) & ~0x3f) == 0 && ((1L << _la) & ((1L << PACKAGE) | (1L << IMPORT) | (1L << INTERFACE) | (1L << VARARG) | (1L << NULL) | (1L << NUMBER) | (1L << STRING) | (1L << IDENTIFIER))) != 0)) {
							{
								setState(142);
								functionParams();
							}
						}

						setState(145);
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
		enterRule(_localctx, 18, RULE_functionModifier);
		int _la;
		try {
			enterOuterAlt(_localctx, 1);
			{
				setState(148);
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

	public static class FunctionImplementContext extends ParserRuleContext {
		public FunctionSimpleImplContext functionSimpleImpl() {
			return getRuleContext(FunctionSimpleImplContext.class, 0);
		}

		public FunctionFullImplContext functionFullImpl() {
			return getRuleContext(FunctionFullImplContext.class, 0);
		}

		public FunctionImplementContext(ParserRuleContext parent, int invokingState) {
			super(parent, invokingState);
		}

		@Override
		public int getRuleIndex() {
			return RULE_functionImplement;
		}

		@Override
		public void enterRule(ParseTreeListener listener) {
			if (listener instanceof FunctionParserListener)
				((FunctionParserListener) listener).enterFunctionImplement(this);
		}

		@Override
		public void exitRule(ParseTreeListener listener) {
			if (listener instanceof FunctionParserListener)
				((FunctionParserListener) listener).exitFunctionImplement(this);
		}
	}

	public final FunctionImplementContext functionImplement() throws RecognitionException {
		FunctionImplementContext _localctx = new FunctionImplementContext(_ctx, getState());
		enterRule(_localctx, 20, RULE_functionImplement);
		try {
			setState(152);
			_errHandler.sync(this);
			switch (_input.LA(1)) {
				case EQUAL:
					enterOuterAlt(_localctx, 1);
				{
					setState(150);
					functionSimpleImpl();
				}
				break;
				case LCURL:
					enterOuterAlt(_localctx, 2);
				{
					setState(151);
					functionFullImpl();
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

	public static class FunctionSimpleImplContext extends ParserRuleContext {
		public TerminalNode EQUAL() {
			return getToken(FunctionParser.EQUAL, 0);
		}

		public ExpressionContext expression() {
			return getRuleContext(ExpressionContext.class, 0);
		}

		public FunctionSimpleImplContext(ParserRuleContext parent, int invokingState) {
			super(parent, invokingState);
		}

		@Override
		public int getRuleIndex() {
			return RULE_functionSimpleImpl;
		}

		@Override
		public void enterRule(ParseTreeListener listener) {
			if (listener instanceof FunctionParserListener)
				((FunctionParserListener) listener).enterFunctionSimpleImpl(this);
		}

		@Override
		public void exitRule(ParseTreeListener listener) {
			if (listener instanceof FunctionParserListener)
				((FunctionParserListener) listener).exitFunctionSimpleImpl(this);
		}
	}

	public final FunctionSimpleImplContext functionSimpleImpl() throws RecognitionException {
		FunctionSimpleImplContext _localctx = new FunctionSimpleImplContext(_ctx, getState());
		enterRule(_localctx, 22, RULE_functionSimpleImpl);
		try {
			enterOuterAlt(_localctx, 1);
			{
				setState(154);
				match(EQUAL);
				setState(155);
				expression();
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

	public static class FunctionFullImplContext extends ParserRuleContext {
		public TerminalNode LCURL() {
			return getToken(FunctionParser.LCURL, 0);
		}

		public TerminalNode RCURL() {
			return getToken(FunctionParser.RCURL, 0);
		}

		public FunctionFullImplContext(ParserRuleContext parent, int invokingState) {
			super(parent, invokingState);
		}

		@Override
		public int getRuleIndex() {
			return RULE_functionFullImpl;
		}

		@Override
		public void enterRule(ParseTreeListener listener) {
			if (listener instanceof FunctionParserListener)
				((FunctionParserListener) listener).enterFunctionFullImpl(this);
		}

		@Override
		public void exitRule(ParseTreeListener listener) {
			if (listener instanceof FunctionParserListener)
				((FunctionParserListener) listener).exitFunctionFullImpl(this);
		}
	}

	public final FunctionFullImplContext functionFullImpl() throws RecognitionException {
		FunctionFullImplContext _localctx = new FunctionFullImplContext(_ctx, getState());
		enterRule(_localctx, 24, RULE_functionFullImpl);
		try {
			int _alt;
			enterOuterAlt(_localctx, 1);
			{
				setState(157);
				match(LCURL);
				{
					setState(161);
					_errHandler.sync(this);
					_alt = getInterpreter().adaptivePredict(_input, 21, _ctx);
					while (_alt != 1 && _alt != org.antlr.v4.runtime.atn.ATN.INVALID_ALT_NUMBER) {
						if (_alt == 1 + 1) {
							{
								{
									setState(158);
									matchWildcard();
								}
							}
						}
						setState(163);
						_errHandler.sync(this);
						_alt = getInterpreter().adaptivePredict(_input, 21, _ctx);
					}
				}
				setState(164);
				match(RCURL);
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

		public List<DataTypeNullContext> dataTypeNull() {
			return getRuleContexts(DataTypeNullContext.class);
		}

		public DataTypeNullContext dataTypeNull(int i) {
			return getRuleContext(DataTypeNullContext.class, i);
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
		enterRule(_localctx, 26, RULE_typeParameters);
		int _la;
		try {
			enterOuterAlt(_localctx, 1);
			{
				setState(166);
				match(LANGLE);
				setState(167);
				dataTypeNull();
				setState(172);
				_errHandler.sync(this);
				_la = _input.LA(1);
				while (_la == COMMA) {
					{
						{
							setState(168);
							match(COMMA);
							setState(169);
							dataTypeNull();
						}
					}
					setState(174);
					_errHandler.sync(this);
					_la = _input.LA(1);
				}
				setState(175);
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
		enterRule(_localctx, 28, RULE_functionParamDefines);
		int _la;
		try {
			int _alt;
			enterOuterAlt(_localctx, 1);
			{
				setState(177);
				functionParamDefine();
				setState(182);
				_errHandler.sync(this);
				_alt = getInterpreter().adaptivePredict(_input, 23, _ctx);
				while (_alt != 2 && _alt != org.antlr.v4.runtime.atn.ATN.INVALID_ALT_NUMBER) {
					if (_alt == 1) {
						{
							{
								setState(178);
								match(COMMA);
								setState(179);
								functionParamDefine();
							}
						}
					}
					setState(184);
					_errHandler.sync(this);
					_alt = getInterpreter().adaptivePredict(_input, 23, _ctx);
				}
				setState(186);
				_errHandler.sync(this);
				_la = _input.LA(1);
				if (_la == COMMA) {
					{
						setState(185);
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

		public DataTypeNullContext dataTypeNull() {
			return getRuleContext(DataTypeNullContext.class, 0);
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
		enterRule(_localctx, 30, RULE_functionParamDefine);
		int _la;
		try {
			enterOuterAlt(_localctx, 1);
			{
				setState(189);
				_errHandler.sync(this);
				switch (getInterpreter().adaptivePredict(_input, 25, _ctx)) {
					case 1: {
						setState(188);
						functionParamModifierList();
					}
					break;
				}
				setState(191);
				simpleIdentifier();
				setState(192);
				match(COLON);
				setState(193);
				dataTypeNull();
				setState(196);
				_errHandler.sync(this);
				_la = _input.LA(1);
				if (_la == EQUAL) {
					{
						setState(194);
						match(EQUAL);
						setState(195);
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
		enterRule(_localctx, 32, RULE_functionParamModifierList);
		try {
			int _alt;
			enterOuterAlt(_localctx, 1);
			{
				setState(200);
				_errHandler.sync(this);
				_alt = 1;
				do {
					switch (_alt) {
						case 1: {
							setState(200);
							_errHandler.sync(this);
							switch (_input.LA(1)) {
								case AT: {
									setState(198);
									annotation();
								}
								break;
								case VARARG: {
									setState(199);
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
					setState(202);
					_errHandler.sync(this);
					_alt = getInterpreter().adaptivePredict(_input, 28, _ctx);
				} while (_alt != 2 && _alt != org.antlr.v4.runtime.atn.ATN.INVALID_ALT_NUMBER);
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
		enterRule(_localctx, 34, RULE_functionParamModifier);
		try {
			enterOuterAlt(_localctx, 1);
			{
				setState(204);
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

	public static class FunctionParamsContext extends ParserRuleContext {
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

		public FunctionParamsContext(ParserRuleContext parent, int invokingState) {
			super(parent, invokingState);
		}

		@Override
		public int getRuleIndex() {
			return RULE_functionParams;
		}

		@Override
		public void enterRule(ParseTreeListener listener) {
			if (listener instanceof FunctionParserListener)
				((FunctionParserListener) listener).enterFunctionParams(this);
		}

		@Override
		public void exitRule(ParseTreeListener listener) {
			if (listener instanceof FunctionParserListener)
				((FunctionParserListener) listener).exitFunctionParams(this);
		}
	}

	public final FunctionParamsContext functionParams() throws RecognitionException {
		FunctionParamsContext _localctx = new FunctionParamsContext(_ctx, getState());
		enterRule(_localctx, 36, RULE_functionParams);
		int _la;
		try {
			enterOuterAlt(_localctx, 1);
			{
				setState(206);
				expression();
				setState(211);
				_errHandler.sync(this);
				_la = _input.LA(1);
				while (_la == COMMA) {
					{
						{
							setState(207);
							match(COMMA);
							setState(208);
							expression();
						}
					}
					setState(213);
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

	public static class FunctionCallContext extends ParserRuleContext {
		public IdentifierContext identifier() {
			return getRuleContext(IdentifierContext.class, 0);
		}

		public TerminalNode LPAREN() {
			return getToken(FunctionParser.LPAREN, 0);
		}

		public TerminalNode RPAREN() {
			return getToken(FunctionParser.RPAREN, 0);
		}

		public FunctionParamsContext functionParams() {
			return getRuleContext(FunctionParamsContext.class, 0);
		}

		public FunctionCallContext(ParserRuleContext parent, int invokingState) {
			super(parent, invokingState);
		}

		@Override
		public int getRuleIndex() {
			return RULE_functionCall;
		}

		@Override
		public void enterRule(ParseTreeListener listener) {
			if (listener instanceof FunctionParserListener) ((FunctionParserListener) listener).enterFunctionCall(this);
		}

		@Override
		public void exitRule(ParseTreeListener listener) {
			if (listener instanceof FunctionParserListener) ((FunctionParserListener) listener).exitFunctionCall(this);
		}
	}

	public final FunctionCallContext functionCall() throws RecognitionException {
		FunctionCallContext _localctx = new FunctionCallContext(_ctx, getState());
		enterRule(_localctx, 38, RULE_functionCall);
		int _la;
		try {
			enterOuterAlt(_localctx, 1);
			{
				setState(214);
				identifier();
				setState(215);
				match(LPAREN);
				setState(217);
				_errHandler.sync(this);
				_la = _input.LA(1);
				if ((((_la) & ~0x3f) == 0 && ((1L << _la) & ((1L << PACKAGE) | (1L << IMPORT) | (1L << INTERFACE) | (1L << VARARG) | (1L << NULL) | (1L << NUMBER) | (1L << STRING) | (1L << IDENTIFIER))) != 0)) {
					{
						setState(216);
						functionParams();
					}
				}

				setState(219);
				match(RPAREN);
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

		public TerminalNode NULL() {
			return getToken(FunctionParser.NULL, 0);
		}

		public FunctionCallContext functionCall() {
			return getRuleContext(FunctionCallContext.class, 0);
		}

		public IdentifierContext identifier() {
			return getRuleContext(IdentifierContext.class, 0);
		}

		public TerminalNode COLONCOLON() {
			return getToken(FunctionParser.COLONCOLON, 0);
		}

		public TerminalNode CLASS() {
			return getToken(FunctionParser.CLASS, 0);
		}

		public TerminalNode EQUAL() {
			return getToken(FunctionParser.EQUAL, 0);
		}

		public ExpressionContext expression() {
			return getRuleContext(ExpressionContext.class, 0);
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
		enterRule(_localctx, 40, RULE_expression);
		try {
			setState(233);
			_errHandler.sync(this);
			switch (getInterpreter().adaptivePredict(_input, 31, _ctx)) {
				case 1:
					enterOuterAlt(_localctx, 1);
				{
					setState(221);
					match(STRING);
				}
				break;
				case 2:
					enterOuterAlt(_localctx, 2);
				{
					setState(222);
					match(NUMBER);
				}
				break;
				case 3:
					enterOuterAlt(_localctx, 3);
				{
					setState(223);
					match(NULL);
				}
				break;
				case 4:
					enterOuterAlt(_localctx, 4);
				{
					setState(224);
					functionCall();
				}
				break;
				case 5:
					enterOuterAlt(_localctx, 5);
				{
					setState(225);
					identifier();
					setState(226);
					match(COLONCOLON);
					setState(227);
					match(CLASS);
				}
				break;
				case 6:
					enterOuterAlt(_localctx, 6);
				{
					setState(229);
					identifier();
					setState(230);
					match(EQUAL);
					setState(231);
					expression();
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
		enterRule(_localctx, 42, RULE_dataType);
		int _la;
		try {
			setState(247);
			_errHandler.sync(this);
			switch (_input.LA(1)) {
				case DT_BOOLEAN:
					enterOuterAlt(_localctx, 1);
				{
					setState(235);
					match(DT_BOOLEAN);
				}
				break;
				case DT_INT:
					enterOuterAlt(_localctx, 2);
				{
					setState(236);
					match(DT_INT);
				}
				break;
				case DT_DOUBLE:
					enterOuterAlt(_localctx, 3);
				{
					setState(237);
					match(DT_DOUBLE);
				}
				break;
				case DT_STRING:
					enterOuterAlt(_localctx, 4);
				{
					setState(238);
					match(DT_STRING);
				}
				break;
				case DT_DATE:
					enterOuterAlt(_localctx, 5);
				{
					setState(239);
					match(DT_DATE);
				}
				break;
				case DT_TIME:
					enterOuterAlt(_localctx, 6);
				{
					setState(240);
					match(DT_TIME);
				}
				break;
				case DT_DATETIME:
					enterOuterAlt(_localctx, 7);
				{
					setState(241);
					match(DT_DATETIME);
				}
				break;
				case DT_ANY:
					enterOuterAlt(_localctx, 8);
				{
					setState(242);
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
					setState(243);
					identifier();
					setState(245);
					_errHandler.sync(this);
					_la = _input.LA(1);
					if (_la == LANGLE) {
						{
							setState(244);
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

	public static class DataTypeNullContext extends ParserRuleContext {
		public DataTypeContext dataType() {
			return getRuleContext(DataTypeContext.class, 0);
		}

		public TerminalNode QUESTION() {
			return getToken(FunctionParser.QUESTION, 0);
		}

		public DataTypeNullContext(ParserRuleContext parent, int invokingState) {
			super(parent, invokingState);
		}

		@Override
		public int getRuleIndex() {
			return RULE_dataTypeNull;
		}

		@Override
		public void enterRule(ParseTreeListener listener) {
			if (listener instanceof FunctionParserListener) ((FunctionParserListener) listener).enterDataTypeNull(this);
		}

		@Override
		public void exitRule(ParseTreeListener listener) {
			if (listener instanceof FunctionParserListener) ((FunctionParserListener) listener).exitDataTypeNull(this);
		}
	}

	public final DataTypeNullContext dataTypeNull() throws RecognitionException {
		DataTypeNullContext _localctx = new DataTypeNullContext(_ctx, getState());
		enterRule(_localctx, 44, RULE_dataTypeNull);
		int _la;
		try {
			enterOuterAlt(_localctx, 1);
			{
				setState(249);
				dataType();
				setState(251);
				_errHandler.sync(this);
				_la = _input.LA(1);
				if (_la == QUESTION) {
					{
						setState(250);
						match(QUESTION);
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
		enterRule(_localctx, 46, RULE_identifier);
		try {
			int _alt;
			enterOuterAlt(_localctx, 1);
			{
				setState(253);
				simpleIdentifier();
				setState(258);
				_errHandler.sync(this);
				_alt = getInterpreter().adaptivePredict(_input, 35, _ctx);
				while (_alt != 2 && _alt != org.antlr.v4.runtime.atn.ATN.INVALID_ALT_NUMBER) {
					if (_alt == 1) {
						{
							{
								setState(254);
								match(DOT);
								setState(255);
								simpleIdentifier();
							}
						}
					}
					setState(260);
					_errHandler.sync(this);
					_alt = getInterpreter().adaptivePredict(_input, 35, _ctx);
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
		enterRule(_localctx, 48, RULE_simpleIdentifier);
		int _la;
		try {
			enterOuterAlt(_localctx, 1);
			{
				setState(261);
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
			"\3\u608b\ua72a\u8133\ub9ed\u417c\u3be7\u7786\u5964\3+\u010a\4\2\t\2\4" +
					"\3\t\3\4\4\t\4\4\5\t\5\4\6\t\6\4\7\t\7\4\b\t\b\4\t\t\t\4\n\t\n\4\13\t" +
					"\13\4\f\t\f\4\r\t\r\4\16\t\16\4\17\t\17\4\20\t\20\4\21\t\21\4\22\t\22" +
					"\4\23\t\23\4\24\t\24\4\25\t\25\4\26\t\26\4\27\t\27\4\30\t\30\4\31\t\31" +
					"\4\32\t\32\3\2\3\2\7\2\67\n\2\f\2\16\2:\13\2\3\2\3\2\3\2\7\2?\n\2\f\2" +
					"\16\2B\13\2\3\2\3\2\3\3\3\3\3\3\5\3I\n\3\3\4\3\4\3\4\3\4\5\4O\n\4\3\4" +
					"\5\4R\n\4\3\5\3\5\3\5\3\5\5\5X\n\5\3\5\3\5\7\5\\\n\5\f\5\16\5_\13\5\3" +
					"\5\5\5b\n\5\3\6\3\6\3\6\5\6g\n\6\3\6\3\6\3\6\3\7\3\7\3\7\3\7\5\7p\n\7" +
					"\3\7\3\7\5\7t\n\7\3\b\5\bw\n\b\3\b\3\b\5\b{\n\b\3\b\3\b\3\b\5\b\u0080" +
					"\n\b\3\b\3\b\3\b\3\b\5\b\u0086\n\b\3\t\3\t\6\t\u008a\n\t\r\t\16\t\u008b" +
					"\3\n\3\n\3\n\3\n\5\n\u0092\n\n\3\n\5\n\u0095\n\n\3\13\3\13\3\f\3\f\5\f" +
					"\u009b\n\f\3\r\3\r\3\r\3\16\3\16\7\16\u00a2\n\16\f\16\16\16\u00a5\13\16" +
					"\3\16\3\16\3\17\3\17\3\17\3\17\7\17\u00ad\n\17\f\17\16\17\u00b0\13\17" +
					"\3\17\3\17\3\20\3\20\3\20\7\20\u00b7\n\20\f\20\16\20\u00ba\13\20\3\20" +
					"\5\20\u00bd\n\20\3\21\5\21\u00c0\n\21\3\21\3\21\3\21\3\21\3\21\5\21\u00c7" +
					"\n\21\3\22\3\22\6\22\u00cb\n\22\r\22\16\22\u00cc\3\23\3\23\3\24\3\24\3" +
					"\24\7\24\u00d4\n\24\f\24\16\24\u00d7\13\24\3\25\3\25\3\25\5\25\u00dc\n" +
					"\25\3\25\3\25\3\26\3\26\3\26\3\26\3\26\3\26\3\26\3\26\3\26\3\26\3\26\3" +
					"\26\5\26\u00ec\n\26\3\27\3\27\3\27\3\27\3\27\3\27\3\27\3\27\3\27\3\27" +
					"\5\27\u00f8\n\27\5\27\u00fa\n\27\3\30\3\30\5\30\u00fe\n\30\3\31\3\31\3" +
					"\31\7\31\u0103\n\31\f\31\16\31\u0106\13\31\3\32\3\32\3\32\3\u00a3\2\33" +
					"\2\4\6\b\n\f\16\20\22\24\26\30\32\34\36 \"$&(*,.\60\62\2\4\3\2\33\35\5" +
					"\2\26\27\31\32++\2\u0120\2\64\3\2\2\2\4E\3\2\2\2\6J\3\2\2\2\bS\3\2\2\2" +
					"\nc\3\2\2\2\fk\3\2\2\2\16v\3\2\2\2\20\u0089\3\2\2\2\22\u008d\3\2\2\2\24" +
					"\u0096\3\2\2\2\26\u009a\3\2\2\2\30\u009c\3\2\2\2\32\u009f\3\2\2\2\34\u00a8" +
					"\3\2\2\2\36\u00b3\3\2\2\2 \u00bf\3\2\2\2\"\u00ca\3\2\2\2$\u00ce\3\2\2" +
					"\2&\u00d0\3\2\2\2(\u00d8\3\2\2\2*\u00eb\3\2\2\2,\u00f9\3\2\2\2.\u00fb" +
					"\3\2\2\2\60\u00ff\3\2\2\2\62\u0107\3\2\2\2\648\5\4\3\2\65\67\5\6\4\2\66" +
					"\65\3\2\2\2\67:\3\2\2\28\66\3\2\2\289\3\2\2\29@\3\2\2\2:8\3\2\2\2;?\5" +
					"\n\6\2<?\5\f\7\2=?\5\b\5\2>;\3\2\2\2><\3\2\2\2>=\3\2\2\2?B\3\2\2\2@>\3" +
					"\2\2\2@A\3\2\2\2AC\3\2\2\2B@\3\2\2\2CD\7\2\2\3D\3\3\2\2\2EF\7\26\2\2F" +
					"H\5\60\31\2GI\7\24\2\2HG\3\2\2\2HI\3\2\2\2I\5\3\2\2\2JK\7\27\2\2KN\5\60" +
					"\31\2LM\7\22\2\2MO\7\23\2\2NL\3\2\2\2NO\3\2\2\2OQ\3\2\2\2PR\7\24\2\2Q" +
					"P\3\2\2\2QR\3\2\2\2R\7\3\2\2\2ST\7\31\2\2TW\5\62\32\2UV\7\20\2\2VX\5\60" +
					"\31\2WU\3\2\2\2WX\3\2\2\2Xa\3\2\2\2Y]\7\f\2\2Z\\\5\16\b\2[Z\3\2\2\2\\" +
					"_\3\2\2\2][\3\2\2\2]^\3\2\2\2^`\3\2\2\2_]\3\2\2\2`b\7\r\2\2aY\3\2\2\2" +
					"ab\3\2\2\2b\t\3\2\2\2cf\7\36\2\2dg\5\60\31\2eg\5.\30\2fd\3\2\2\2fe\3\2" +
					"\2\2gh\3\2\2\2hi\7\16\2\2ij\5\60\31\2j\13\3\2\2\2kl\7\30\2\2lo\5\60\31" +
					"\2mn\7\20\2\2np\5\60\31\2om\3\2\2\2op\3\2\2\2ps\3\2\2\2qr\7\f\2\2rt\7" +
					"\r\2\2sq\3\2\2\2st\3\2\2\2t\r\3\2\2\2uw\5\20\t\2vu\3\2\2\2vw\3\2\2\2w" +
					"x\3\2\2\2xz\7\37\2\2y{\5\34\17\2zy\3\2\2\2z{\3\2\2\2{|\3\2\2\2|}\5\62" +
					"\32\2}\177\7\n\2\2~\u0080\5\36\20\2\177~\3\2\2\2\177\u0080\3\2\2\2\u0080" +
					"\u0081\3\2\2\2\u0081\u0082\7\13\2\2\u0082\u0083\7\20\2\2\u0083\u0085\5" +
					".\30\2\u0084\u0086\5\26\f\2\u0085\u0084\3\2\2\2\u0085\u0086\3\2\2\2\u0086" +
					"\17\3\2\2\2\u0087\u008a\5\22\n\2\u0088\u008a\5\24\13\2\u0089\u0087\3\2" +
					"\2\2\u0089\u0088\3\2\2\2\u008a\u008b\3\2\2\2\u008b\u0089\3\2\2\2\u008b" +
					"\u008c\3\2\2\2\u008c\21\3\2\2\2\u008d\u008e\7\7\2\2\u008e\u0094\5\60\31" +
					"\2\u008f\u0091\7\n\2\2\u0090\u0092\5&\24\2\u0091\u0090\3\2\2\2\u0091\u0092" +
					"\3\2\2\2\u0092\u0093\3\2\2\2\u0093\u0095\7\13\2\2\u0094\u008f\3\2\2\2" +
					"\u0094\u0095\3\2\2\2\u0095\23\3\2\2\2\u0096\u0097\t\2\2\2\u0097\25\3\2" +
					"\2\2\u0098\u009b\5\30\r\2\u0099\u009b\5\32\16\2\u009a\u0098\3\2\2\2\u009a" +
					"\u0099\3\2\2\2\u009b\27\3\2\2\2\u009c\u009d\7\16\2\2\u009d\u009e\5*\26" +
					"\2\u009e\31\3\2\2\2\u009f\u00a3\7\f\2\2\u00a0\u00a2\13\2\2\2\u00a1\u00a0" +
					"\3\2\2\2\u00a2\u00a5\3\2\2\2\u00a3\u00a4\3\2\2\2\u00a3\u00a1\3\2\2\2\u00a4" +
					"\u00a6\3\2\2\2\u00a5\u00a3\3\2\2\2\u00a6\u00a7\7\r\2\2\u00a7\33\3\2\2" +
					"\2\u00a8\u00a9\7\b\2\2\u00a9\u00ae\5.\30\2\u00aa\u00ab\7\21\2\2\u00ab" +
					"\u00ad\5.\30\2\u00ac\u00aa\3\2\2\2\u00ad\u00b0\3\2\2\2\u00ae\u00ac\3\2" +
					"\2\2\u00ae\u00af\3\2\2\2\u00af\u00b1\3\2\2\2\u00b0\u00ae\3\2\2\2\u00b1" +
					"\u00b2\7\t\2\2\u00b2\35\3\2\2\2\u00b3\u00b8\5 \21\2\u00b4\u00b5\7\21\2" +
					"\2\u00b5\u00b7\5 \21\2\u00b6\u00b4\3\2\2\2\u00b7\u00ba\3\2\2\2\u00b8\u00b6" +
					"\3\2\2\2\u00b8\u00b9\3\2\2\2\u00b9\u00bc\3\2\2\2\u00ba\u00b8\3\2\2\2\u00bb" +
					"\u00bd\7\21\2\2\u00bc\u00bb\3\2\2\2\u00bc\u00bd\3\2\2\2\u00bd\37\3\2\2" +
					"\2\u00be\u00c0\5\"\22\2\u00bf\u00be\3\2\2\2\u00bf\u00c0\3\2\2\2\u00c0" +
					"\u00c1\3\2\2\2\u00c1\u00c2\5\62\32\2\u00c2\u00c3\7\20\2\2\u00c3\u00c6" +
					"\5.\30\2\u00c4\u00c5\7\16\2\2\u00c5\u00c7\5*\26\2\u00c6\u00c4\3\2\2\2" +
					"\u00c6\u00c7\3\2\2\2\u00c7!\3\2\2\2\u00c8\u00cb\5\22\n\2\u00c9\u00cb\5" +
					"$\23\2\u00ca\u00c8\3\2\2\2\u00ca\u00c9\3\2\2\2\u00cb\u00cc\3\2\2\2\u00cc" +
					"\u00ca\3\2\2\2\u00cc\u00cd\3\2\2\2\u00cd#\3\2\2\2\u00ce\u00cf\7\32\2\2" +
					"\u00cf%\3\2\2\2\u00d0\u00d5\5*\26\2\u00d1\u00d2\7\21\2\2\u00d2\u00d4\5" +
					"*\26\2\u00d3\u00d1\3\2\2\2\u00d4\u00d7\3\2\2\2\u00d5\u00d3\3\2\2\2\u00d5" +
					"\u00d6\3\2\2\2\u00d6\'\3\2\2\2\u00d7\u00d5\3\2\2\2\u00d8\u00d9\5\60\31" +
					"\2\u00d9\u00db\7\n\2\2\u00da\u00dc\5&\24\2\u00db\u00da\3\2\2\2\u00db\u00dc" +
					"\3\2\2\2\u00dc\u00dd\3\2\2\2\u00dd\u00de\7\13\2\2\u00de)\3\2\2\2\u00df" +
					"\u00ec\7*\2\2\u00e0\u00ec\7)\2\2\u00e1\u00ec\7(\2\2\u00e2\u00ec\5(\25" +
					"\2\u00e3\u00e4\5\60\31\2\u00e4\u00e5\7\17\2\2\u00e5\u00e6\7\30\2\2\u00e6" +
					"\u00ec\3\2\2\2\u00e7\u00e8\5\60\31\2\u00e8\u00e9\7\16\2\2\u00e9\u00ea" +
					"\5*\26\2\u00ea\u00ec\3\2\2\2\u00eb\u00df\3\2\2\2\u00eb\u00e0\3\2\2\2\u00eb" +
					"\u00e1\3\2\2\2\u00eb\u00e2\3\2\2\2\u00eb\u00e3\3\2\2\2\u00eb\u00e7\3\2" +
					"\2\2\u00ec+\3\2\2\2\u00ed\u00fa\7#\2\2\u00ee\u00fa\7!\2\2\u00ef\u00fa" +
					"\7\'\2\2\u00f0\u00fa\7 \2\2\u00f1\u00fa\7$\2\2\u00f2\u00fa\7%\2\2\u00f3" +
					"\u00fa\7&\2\2\u00f4\u00fa\7\"\2\2\u00f5\u00f7\5\60\31\2\u00f6\u00f8\5" +
					"\34\17\2\u00f7\u00f6\3\2\2\2\u00f7\u00f8\3\2\2\2\u00f8\u00fa\3\2\2\2\u00f9" +
					"\u00ed\3\2\2\2\u00f9\u00ee\3\2\2\2\u00f9\u00ef\3\2\2\2\u00f9\u00f0\3\2" +
					"\2\2\u00f9\u00f1\3\2\2\2\u00f9\u00f2\3\2\2\2\u00f9\u00f3\3\2\2\2\u00f9" +
					"\u00f4\3\2\2\2\u00f9\u00f5\3\2\2\2\u00fa-\3\2\2\2\u00fb\u00fd\5,\27\2" +
					"\u00fc\u00fe\7\25\2\2\u00fd\u00fc\3\2\2\2\u00fd\u00fe\3\2\2\2\u00fe/\3" +
					"\2\2\2\u00ff\u0104\5\62\32\2\u0100\u0101\7\22\2\2\u0101\u0103\5\62\32" +
					"\2\u0102\u0100\3\2\2\2\u0103\u0106\3\2\2\2\u0104\u0102\3\2\2\2\u0104\u0105" +
					"\3\2\2\2\u0105\61\3\2\2\2\u0106\u0104\3\2\2\2\u0107\u0108\t\3\2\2\u0108" +
					"\63\3\2\2\2&8>@HNQW]afosvz\177\u0085\u0089\u008b\u0091\u0094\u009a\u00a3" +
					"\u00ae\u00b8\u00bc\u00bf\u00c6\u00ca\u00cc\u00d5\u00db\u00eb\u00f7\u00f9" +
					"\u00fd\u0104";
	public static final ATN _ATN =
			new ATNDeserializer().deserialize(_serializedATN.toCharArray());
	static {
		_decisionToDFA = new DFA[_ATN.getNumberOfDecisions()];
		for (int i = 0; i < _ATN.getNumberOfDecisions(); i++) {
			_decisionToDFA[i] = new DFA(_ATN.getDecisionState(i), i);
		}
	}
}