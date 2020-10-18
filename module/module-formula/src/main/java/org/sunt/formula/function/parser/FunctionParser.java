// Generated from C:/Users/suntr/Project/pms/module/module-formula/src/main/java/org/sunt/formula/function/parser\Function.g4 by ANTLR 4.8
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
	public static final int
			T__0 = 1, VARARG = 2, ALIAS = 3, DT_STRING = 4, DT_DECIMAL = 5, DT_INTEGER = 6, DT_BOOLEAN = 7,
			DT_DATETIME = 8, DT_DATE = 9, DT_TIME = 10, DT_ANY = 11, TYPE = 12, PLUS = 13, MINUS = 14,
			COLON = 15, COMMA = 16, DOT = 17, DOLLAR = 18, TRANSFER = 19, S_BLOCK = 20, D_BLOCK = 21,
			L_BRACKET = 22, R_BRACKET = 23, LS_BRACKET = 24, RS_BRACKET = 25, L_BRACE = 26,
			R_BRACE = 27, SYMBOL = 28, INTEGER = 29, NUMBER = 30, STRING = 31, IDENTIFIER = 32,
			NEW_LINE = 33, WS = 34, LINE_COMMENT = 35, DESCRIPTION = 36;
	public static final int
			RULE_root = 0, RULE_category = 1, RULE_functionItem = 2, RULE_functionAlias = 3,
			RULE_funcDefine = 4, RULE_funcImplement = 5, RULE_funcArgs = 6, RULE_funcArg = 7,
			RULE_argName = 8, RULE_argRef = 9, RULE_enumerations = 10, RULE_allSymbol = 11,
			RULE_dataType = 12;
	public static final String[] ruleNames = makeRuleNames();
	/**
	 * @deprecated Use {@link #VOCABULARY} instead.
	 */
	@Deprecated
	public static final String[] tokenNames;
	public static final String _serializedATN =
			"\3\u608b\ua72a\u8133\ub9ed\u417c\u3be7\u7786\u5964\3&\u00df\4\2\t\2\4" +
					"\3\t\3\4\4\t\4\4\5\t\5\4\6\t\6\4\7\t\7\4\b\t\b\4\t\t\t\4\n\t\n\4\13\t" +
					"\13\4\f\t\f\4\r\t\r\4\16\t\16\3\2\3\2\3\2\6\2 \n\2\r\2\16\2!\3\2\3\2\3" +
					"\3\3\3\3\3\3\3\3\3\7\3+\n\3\f\3\16\3.\13\3\3\3\5\3\61\n\3\3\4\3\4\7\4" +
					"\65\n\4\f\4\16\48\13\4\3\4\3\4\7\4<\n\4\f\4\16\4?\13\4\3\4\3\4\3\4\7\4" +
					"D\n\4\f\4\16\4G\13\4\3\4\3\4\3\4\7\4L\n\4\f\4\16\4O\13\4\3\4\3\4\3\5\3" +
					"\5\3\5\3\5\3\5\3\5\7\5Y\n\5\f\5\16\5\\\13\5\3\6\3\6\3\6\5\6a\n\6\3\6\3" +
					"\6\3\6\3\6\3\6\3\6\3\6\5\6j\n\6\3\7\3\7\7\7n\n\7\f\7\16\7q\13\7\3\7\5" +
					"\7t\n\7\3\7\3\7\3\7\3\7\6\7z\n\7\r\7\16\7{\3\7\3\7\3\7\3\7\3\7\6\7\u0083" +
					"\n\7\r\7\16\7\u0084\3\7\5\7\u0088\n\7\3\b\5\b\u008b\n\b\3\b\3\b\3\b\3" +
					"\b\3\b\3\b\7\b\u0093\n\b\f\b\16\b\u0096\13\b\3\b\5\b\u0099\n\b\3\b\5\b" +
					"\u009c\n\b\5\b\u009e\n\b\3\t\3\t\3\t\3\t\3\t\3\t\3\t\3\t\5\t\u00a8\n\t" +
					"\3\n\3\n\5\n\u00ac\n\n\3\13\3\13\3\13\3\13\3\13\3\13\3\13\5\13\u00b5\n" +
					"\13\3\f\3\f\3\f\7\f\u00ba\n\f\f\f\16\f\u00bd\13\f\3\f\3\f\3\f\7\f\u00c2" +
					"\n\f\f\f\16\f\u00c5\13\f\5\f\u00c7\n\f\3\r\3\r\3\r\3\r\3\r\3\r\3\r\3\r" +
					"\3\r\3\r\3\r\3\r\3\r\3\r\3\r\3\r\3\r\3\r\5\r\u00db\n\r\3\16\3\16\3\16" +
					"\t\66=EMo{\u0084\2\17\2\4\6\b\n\f\16\20\22\24\26\30\32\2\3\3\2\6\r\2\u0105" +
					"\2\37\3\2\2\2\4\60\3\2\2\2\6\62\3\2\2\2\bR\3\2\2\2\n]\3\2\2\2\f\u0087" +
					"\3\2\2\2\16\u009d\3\2\2\2\20\u009f\3\2\2\2\22\u00ab\3\2\2\2\24\u00ad\3" +
					"\2\2\2\26\u00c6\3\2\2\2\30\u00da\3\2\2\2\32\u00dc\3\2\2\2\34 \5\6\4\2" +
					"\35 \5\b\5\2\36 \7#\2\2\37\34\3\2\2\2\37\35\3\2\2\2\37\36\3\2\2\2 !\3" +
					"\2\2\2!\37\3\2\2\2!\"\3\2\2\2\"#\3\2\2\2#$\7\2\2\3$\3\3\2\2\2%\61\7\"" +
					"\2\2&\'\7\32\2\2\',\7\"\2\2()\7\22\2\2)+\7\"\2\2*(\3\2\2\2+.\3\2\2\2," +
					"*\3\2\2\2,-\3\2\2\2-/\3\2\2\2.,\3\2\2\2/\61\7\33\2\2\60%\3\2\2\2\60&\3" +
					"\2\2\2\61\5\3\2\2\2\62\66\7&\2\2\63\65\7#\2\2\64\63\3\2\2\2\658\3\2\2" +
					"\2\66\67\3\2\2\2\66\64\3\2\2\2\679\3\2\2\28\66\3\2\2\29=\5\4\3\2:<\7#" +
					"\2\2;:\3\2\2\2<?\3\2\2\2=>\3\2\2\2=;\3\2\2\2>@\3\2\2\2?=\3\2\2\2@A\7\21" +
					"\2\2AE\7\21\2\2BD\7#\2\2CB\3\2\2\2DG\3\2\2\2EF\3\2\2\2EC\3\2\2\2FH\3\2" +
					"\2\2GE\3\2\2\2HI\5\n\6\2IM\7\25\2\2JL\7#\2\2KJ\3\2\2\2LO\3\2\2\2MN\3\2" +
					"\2\2MK\3\2\2\2NP\3\2\2\2OM\3\2\2\2PQ\5\f\7\2Q\7\3\2\2\2RS\7\5\2\2ST\7" +
					"\"\2\2TU\7\3\2\2UZ\7\"\2\2VW\7\22\2\2WY\7\"\2\2XV\3\2\2\2Y\\\3\2\2\2Z" +
					"X\3\2\2\2Z[\3\2\2\2[\t\3\2\2\2\\Z\3\2\2\2]^\7\"\2\2^`\7\30\2\2_a\5\16" +
					"\b\2`_\3\2\2\2`a\3\2\2\2ab\3\2\2\2bc\7\31\2\2ci\7\21\2\2dj\5\32\16\2e" +
					"f\5\24\13\2fg\7\23\2\2gh\7\16\2\2hj\3\2\2\2id\3\2\2\2ie\3\2\2\2j\13\3" +
					"\2\2\2kn\5\24\13\2ln\5\30\r\2mk\3\2\2\2ml\3\2\2\2nq\3\2\2\2op\3\2\2\2" +
					"om\3\2\2\2ps\3\2\2\2qo\3\2\2\2rt\7#\2\2sr\3\2\2\2st\3\2\2\2t\u0088\3\2" +
					"\2\2uy\7\26\2\2vz\5\24\13\2wz\5\30\r\2xz\7#\2\2yv\3\2\2\2yw\3\2\2\2yx" +
					"\3\2\2\2z{\3\2\2\2{|\3\2\2\2{y\3\2\2\2|}\3\2\2\2}\u0088\7\26\2\2~\u0082" +
					"\7\27\2\2\177\u0083\5\24\13\2\u0080\u0083\5\30\r\2\u0081\u0083\7#\2\2" +
					"\u0082\177\3\2\2\2\u0082\u0080\3\2\2\2\u0082\u0081\3\2\2\2\u0083\u0084" +
					"\3\2\2\2\u0084\u0085\3\2\2\2\u0084\u0082\3\2\2\2\u0085\u0086\3\2\2\2\u0086" +
					"\u0088\7\27\2\2\u0087o\3\2\2\2\u0087u\3\2\2\2\u0087~\3\2\2\2\u0088\r\3" +
					"\2\2\2\u0089\u008b\7\4\2\2\u008a\u0089\3\2\2\2\u008a\u008b\3\2\2\2\u008b" +
					"\u008c\3\2\2\2\u008c\u009e\5\20\t\2\u008d\u008e\5\20\t\2\u008e\u0094\7" +
					"\22\2\2\u008f\u0090\5\20\t\2\u0090\u0091\7\22\2\2\u0091\u0093\3\2\2\2" +
					"\u0092\u008f\3\2\2\2\u0093\u0096\3\2\2\2\u0094\u0092\3\2\2\2\u0094\u0095" +
					"\3\2\2\2\u0095\u0098\3\2\2\2\u0096\u0094\3\2\2\2\u0097\u0099\7\4\2\2\u0098" +
					"\u0097\3\2\2\2\u0098\u0099\3\2\2\2\u0099\u009b\3\2\2\2\u009a\u009c\5\20" +
					"\t\2\u009b\u009a\3\2\2\2\u009b\u009c\3\2\2\2\u009c\u009e\3\2\2\2\u009d" +
					"\u008a\3\2\2\2\u009d\u008d\3\2\2\2\u009e\17\3\2\2\2\u009f\u00a0\5\22\n" +
					"\2\u00a0\u00a1\7\21\2\2\u00a1\u00a7\5\32\16\2\u00a2\u00a3\7\21\2\2\u00a3" +
					"\u00a4\7\32\2\2\u00a4\u00a5\5\26\f\2\u00a5\u00a6\7\33\2\2\u00a6\u00a8" +
					"\3\2\2\2\u00a7\u00a2\3\2\2\2\u00a7\u00a8\3\2\2\2\u00a8\21\3\2\2\2\u00a9" +
					"\u00ac\7\"\2\2\u00aa\u00ac\5\32\16\2\u00ab\u00a9\3\2\2\2\u00ab\u00aa\3" +
					"\2\2\2\u00ac\23\3\2\2\2\u00ad\u00b4\7\24\2\2\u00ae\u00b5\7\37\2\2\u00af" +
					"\u00b5\5\22\n\2\u00b0\u00b1\7\34\2\2\u00b1\u00b2\5\22\n\2\u00b2\u00b3" +
					"\7\35\2\2\u00b3\u00b5\3\2\2\2\u00b4\u00ae\3\2\2\2\u00b4\u00af\3\2\2\2" +
					"\u00b4\u00b0\3\2\2\2\u00b5\25\3\2\2\2\u00b6\u00bb\7!\2\2\u00b7\u00b8\7" +
					"\22\2\2\u00b8\u00ba\7!\2\2\u00b9\u00b7\3\2\2\2\u00ba\u00bd\3\2\2\2\u00bb" +
					"\u00b9\3\2\2\2\u00bb\u00bc\3\2\2\2\u00bc\u00c7\3\2\2\2\u00bd\u00bb\3\2" +
					"\2\2\u00be\u00c3\7 \2\2\u00bf\u00c0\7\22\2\2\u00c0\u00c2\7 \2\2\u00c1" +
					"\u00bf\3\2\2\2\u00c2\u00c5\3\2\2\2\u00c3\u00c1\3\2\2\2\u00c3\u00c4\3\2" +
					"\2\2\u00c4\u00c7\3\2\2\2\u00c5\u00c3\3\2\2\2\u00c6\u00b6\3\2\2\2\u00c6" +
					"\u00be\3\2\2\2\u00c7\27\3\2\2\2\u00c8\u00db\7\"\2\2\u00c9\u00db\7!\2\2" +
					"\u00ca\u00db\7 \2\2\u00cb\u00db\7\37\2\2\u00cc\u00db\7\24\2\2\u00cd\u00db" +
					"\7\17\2\2\u00ce\u00db\7\20\2\2\u00cf\u00db\7\21\2\2\u00d0\u00db\7\22\2" +
					"\2\u00d1\u00db\7\23\2\2\u00d2\u00db\7\30\2\2\u00d3\u00db\7\31\2\2\u00d4" +
					"\u00db\7\32\2\2\u00d5\u00db\7\33\2\2\u00d6\u00db\7\34\2\2\u00d7\u00db" +
					"\7\35\2\2\u00d8\u00db\7\36\2\2\u00d9\u00db\5\32\16\2\u00da\u00c8\3\2\2" +
					"\2\u00da\u00c9\3\2\2\2\u00da\u00ca\3\2\2\2\u00da\u00cb\3\2\2\2\u00da\u00cc" +
					"\3\2\2\2\u00da\u00cd\3\2\2\2\u00da\u00ce\3\2\2\2\u00da\u00cf\3\2\2\2\u00da" +
					"\u00d0\3\2\2\2\u00da\u00d1\3\2\2\2\u00da\u00d2\3\2\2\2\u00da\u00d3\3\2" +
					"\2\2\u00da\u00d4\3\2\2\2\u00da\u00d5\3\2\2\2\u00da\u00d6\3\2\2\2\u00da" +
					"\u00d7\3\2\2\2\u00da\u00d8\3\2\2\2\u00da\u00d9\3\2\2\2\u00db\31\3\2\2" +
					"\2\u00dc\u00dd\t\2\2\2\u00dd\33\3\2\2\2!\37!,\60\66=EMZ`imosy{\u0082\u0084" +
					"\u0087\u008a\u0094\u0098\u009b\u009d\u00a7\u00ab\u00b4\u00bb\u00c3\u00c6" +
					"\u00da";
	public static final ATN _ATN =
			new ATNDeserializer().deserialize(_serializedATN.toCharArray());
	protected static final DFA[] _decisionToDFA;
	protected static final PredictionContextCache _sharedContextCache =
			new PredictionContextCache();
	private static final String[] _LITERAL_NAMES = makeLiteralNames();
	private static final String[] _SYMBOLIC_NAMES = makeSymbolicNames();
	public static final Vocabulary VOCABULARY = new VocabularyImpl(_LITERAL_NAMES, _SYMBOLIC_NAMES);

	static {
		RuntimeMetaData.checkVersion("4.8", RuntimeMetaData.VERSION);
	}

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

	static {
		_decisionToDFA = new DFA[_ATN.getNumberOfDecisions()];
		for (int i = 0; i < _ATN.getNumberOfDecisions(); i++) {
			_decisionToDFA[i] = new DFA(_ATN.getDecisionState(i), i);
		}
	}

	public FunctionParser(TokenStream input) {
		super(input);
		_interp = new ParserATNSimulator(this, _ATN, _decisionToDFA, _sharedContextCache);
	}

	private static String[] makeRuleNames() {
		return new String[]{
				"root", "category", "functionItem", "functionAlias", "funcDefine", "funcImplement",
				"funcArgs", "funcArg", "argName", "argRef", "enumerations", "allSymbol",
				"dataType"
		};
	}

	private static String[] makeLiteralNames() {
		return new String[]{
				null, "'='", null, null, null, null, null, null, null, null, null, null,
				null, "'+'", "'-'", "':'", "','", "'.'", "'$'", null, "'''''", "'\"\"\"'",
				"'('", "')'", "'['", "']'", "'{'", "'}'"
		};
	}

	private static String[] makeSymbolicNames() {
		return new String[]{
				null, null, "VARARG", "ALIAS", "DT_STRING", "DT_DECIMAL", "DT_INTEGER",
				"DT_BOOLEAN", "DT_DATETIME", "DT_DATE", "DT_TIME", "DT_ANY", "TYPE",
				"PLUS", "MINUS", "COLON", "COMMA", "DOT", "DOLLAR", "TRANSFER", "S_BLOCK",
				"D_BLOCK", "L_BRACKET", "R_BRACKET", "LS_BRACKET", "RS_BRACKET", "L_BRACE",
				"R_BRACE", "SYMBOL", "INTEGER", "NUMBER", "STRING", "IDENTIFIER", "NEW_LINE",
				"WS", "LINE_COMMENT", "DESCRIPTION"
		};
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

	public final RootContext root() throws RecognitionException {
		RootContext _localctx = new RootContext(_ctx, getState());
		enterRule(_localctx, 0, RULE_root);
		int _la;
		try {
			enterOuterAlt(_localctx, 1);
			{
				setState(29);
				_errHandler.sync(this);
				_la = _input.LA(1);
				do {
					{
						setState(29);
						_errHandler.sync(this);
						switch (_input.LA(1)) {
							case DESCRIPTION: {
								setState(26);
								functionItem();
							}
							break;
							case ALIAS: {
								setState(27);
								functionAlias();
							}
							break;
							case NEW_LINE: {
								setState(28);
								match(NEW_LINE);
							}
							break;
							default:
								throw new NoViableAltException(this);
						}
					}
					setState(31);
					_errHandler.sync(this);
					_la = _input.LA(1);
				} while ((((_la) & ~0x3f) == 0 && ((1L << _la) & ((1L << ALIAS) | (1L << NEW_LINE) | (1L << DESCRIPTION))) != 0));
				setState(33);
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

	public final CategoryContext category() throws RecognitionException {
		CategoryContext _localctx = new CategoryContext(_ctx, getState());
		enterRule(_localctx, 2, RULE_category);
		int _la;
		try {
			setState(46);
			_errHandler.sync(this);
			switch (_input.LA(1)) {
				case IDENTIFIER:
					enterOuterAlt(_localctx, 1);
				{
					setState(35);
					match(IDENTIFIER);
				}
				break;
				case LS_BRACKET:
					enterOuterAlt(_localctx, 2);
				{
					setState(36);
					match(LS_BRACKET);
					setState(37);
					match(IDENTIFIER);
					setState(42);
					_errHandler.sync(this);
					_la = _input.LA(1);
					while (_la == COMMA) {
						{
							{
								setState(38);
								match(COMMA);
								setState(39);
								match(IDENTIFIER);
							}
						}
						setState(44);
						_errHandler.sync(this);
						_la = _input.LA(1);
					}
					setState(45);
					match(RS_BRACKET);
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

	public final FunctionItemContext functionItem() throws RecognitionException {
		FunctionItemContext _localctx = new FunctionItemContext(_ctx, getState());
		enterRule(_localctx, 4, RULE_functionItem);
		try {
			int _alt;
			enterOuterAlt(_localctx, 1);
			{
				setState(48);
				match(DESCRIPTION);
				setState(52);
				_errHandler.sync(this);
				_alt = getInterpreter().adaptivePredict(_input, 4, _ctx);
				while (_alt != 1 && _alt != org.antlr.v4.runtime.atn.ATN.INVALID_ALT_NUMBER) {
					if (_alt == 1 + 1) {
						{
							{
								setState(49);
								match(NEW_LINE);
							}
						}
					}
					setState(54);
					_errHandler.sync(this);
					_alt = getInterpreter().adaptivePredict(_input, 4, _ctx);
				}
				setState(55);
				category();
				setState(59);
				_errHandler.sync(this);
				_alt = getInterpreter().adaptivePredict(_input, 5, _ctx);
				while (_alt != 1 && _alt != org.antlr.v4.runtime.atn.ATN.INVALID_ALT_NUMBER) {
					if (_alt == 1 + 1) {
						{
							{
								setState(56);
								match(NEW_LINE);
							}
						}
					}
					setState(61);
					_errHandler.sync(this);
					_alt = getInterpreter().adaptivePredict(_input, 5, _ctx);
				}
				setState(62);
				match(COLON);
				setState(63);
				match(COLON);
				setState(67);
				_errHandler.sync(this);
				_alt = getInterpreter().adaptivePredict(_input, 6, _ctx);
				while (_alt != 1 && _alt != org.antlr.v4.runtime.atn.ATN.INVALID_ALT_NUMBER) {
					if (_alt == 1 + 1) {
						{
							{
								setState(64);
								match(NEW_LINE);
							}
						}
					}
					setState(69);
					_errHandler.sync(this);
					_alt = getInterpreter().adaptivePredict(_input, 6, _ctx);
				}
				setState(70);
				funcDefine();
				setState(71);
				match(TRANSFER);
				setState(75);
				_errHandler.sync(this);
				_alt = getInterpreter().adaptivePredict(_input, 7, _ctx);
				while (_alt != 1 && _alt != org.antlr.v4.runtime.atn.ATN.INVALID_ALT_NUMBER) {
					if (_alt == 1 + 1) {
						{
							{
								setState(72);
								match(NEW_LINE);
							}
						}
					}
					setState(77);
					_errHandler.sync(this);
					_alt = getInterpreter().adaptivePredict(_input, 7, _ctx);
				}
				setState(78);
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

	public final FunctionAliasContext functionAlias() throws RecognitionException {
		FunctionAliasContext _localctx = new FunctionAliasContext(_ctx, getState());
		enterRule(_localctx, 6, RULE_functionAlias);
		int _la;
		try {
			enterOuterAlt(_localctx, 1);
			{
				setState(80);
				match(ALIAS);
				setState(81);
				match(IDENTIFIER);
				setState(82);
				match(T__0);
				setState(83);
				match(IDENTIFIER);
				setState(88);
				_errHandler.sync(this);
				_la = _input.LA(1);
				while (_la == COMMA) {
					{
						{
							setState(84);
							match(COMMA);
							setState(85);
							match(IDENTIFIER);
						}
					}
					setState(90);
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

	public final FuncDefineContext funcDefine() throws RecognitionException {
		FuncDefineContext _localctx = new FuncDefineContext(_ctx, getState());
		enterRule(_localctx, 8, RULE_funcDefine);
		int _la;
		try {
			enterOuterAlt(_localctx, 1);
			{
				setState(91);
				match(IDENTIFIER);
				setState(92);
				match(L_BRACKET);
				setState(94);
				_errHandler.sync(this);
				_la = _input.LA(1);
				if ((((_la) & ~0x3f) == 0 && ((1L << _la) & ((1L << VARARG) | (1L << DT_STRING) | (1L << DT_DECIMAL) | (1L << DT_INTEGER) | (1L << DT_BOOLEAN) | (1L << DT_DATETIME) | (1L << DT_DATE) | (1L << DT_TIME) | (1L << DT_ANY) | (1L << IDENTIFIER))) != 0)) {
					{
						setState(93);
						funcArgs();
					}
				}

				setState(96);
				match(R_BRACKET);
				setState(97);
				match(COLON);
				setState(103);
				_errHandler.sync(this);
				switch (_input.LA(1)) {
					case DT_STRING:
					case DT_DECIMAL:
					case DT_INTEGER:
					case DT_BOOLEAN:
					case DT_DATETIME:
					case DT_DATE:
					case DT_TIME:
					case DT_ANY: {
						setState(98);
						dataType();
					}
					break;
					case DOLLAR: {
						setState(99);
						argRef();
						setState(100);
						match(DOT);
						setState(101);
						match(TYPE);
					}
					break;
					default:
						throw new NoViableAltException(this);
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

	public final FuncImplementContext funcImplement() throws RecognitionException {
		FuncImplementContext _localctx = new FuncImplementContext(_ctx, getState());
		enterRule(_localctx, 10, RULE_funcImplement);
		try {
			int _alt;
			setState(133);
			_errHandler.sync(this);
			switch (_input.LA(1)) {
				case EOF:
				case ALIAS:
				case DT_STRING:
				case DT_DECIMAL:
				case DT_INTEGER:
				case DT_BOOLEAN:
				case DT_DATETIME:
				case DT_DATE:
				case DT_TIME:
				case DT_ANY:
				case PLUS:
				case MINUS:
				case COLON:
				case COMMA:
				case DOT:
				case DOLLAR:
				case L_BRACKET:
				case R_BRACKET:
				case LS_BRACKET:
				case RS_BRACKET:
				case L_BRACE:
				case R_BRACE:
				case SYMBOL:
				case INTEGER:
				case NUMBER:
				case STRING:
				case IDENTIFIER:
				case NEW_LINE:
				case DESCRIPTION:
					enterOuterAlt(_localctx, 1);
				{
					setState(109);
					_errHandler.sync(this);
					_alt = getInterpreter().adaptivePredict(_input, 12, _ctx);
					while (_alt != 1 && _alt != org.antlr.v4.runtime.atn.ATN.INVALID_ALT_NUMBER) {
						if (_alt == 1 + 1) {
							{
								setState(107);
								_errHandler.sync(this);
								switch (getInterpreter().adaptivePredict(_input, 11, _ctx)) {
									case 1: {
										setState(105);
										argRef();
									}
									break;
									case 2: {
										setState(106);
										allSymbol();
									}
									break;
								}
							}
						}
						setState(111);
						_errHandler.sync(this);
						_alt = getInterpreter().adaptivePredict(_input, 12, _ctx);
					}
					setState(113);
					_errHandler.sync(this);
					switch (getInterpreter().adaptivePredict(_input, 13, _ctx)) {
						case 1: {
							setState(112);
							match(NEW_LINE);
						}
						break;
					}
				}
				break;
				case S_BLOCK:
					enterOuterAlt(_localctx, 2);
				{
					setState(115);
					match(S_BLOCK);
					setState(119);
					_errHandler.sync(this);
					_alt = 1 + 1;
					do {
						switch (_alt) {
							case 1 + 1: {
								setState(119);
								_errHandler.sync(this);
								switch (getInterpreter().adaptivePredict(_input, 14, _ctx)) {
									case 1: {
										setState(116);
										argRef();
									}
									break;
									case 2: {
										setState(117);
										allSymbol();
									}
									break;
									case 3: {
										setState(118);
										match(NEW_LINE);
									}
									break;
								}
							}
							break;
							default:
								throw new NoViableAltException(this);
						}
						setState(121);
						_errHandler.sync(this);
						_alt = getInterpreter().adaptivePredict(_input, 15, _ctx);
					} while (_alt != 1 && _alt != org.antlr.v4.runtime.atn.ATN.INVALID_ALT_NUMBER);
					setState(123);
					match(S_BLOCK);
				}
				break;
				case D_BLOCK:
					enterOuterAlt(_localctx, 3);
				{
					setState(124);
					match(D_BLOCK);
					setState(128);
					_errHandler.sync(this);
					_alt = 1 + 1;
					do {
						switch (_alt) {
							case 1 + 1: {
								setState(128);
								_errHandler.sync(this);
								switch (getInterpreter().adaptivePredict(_input, 16, _ctx)) {
									case 1: {
										setState(125);
										argRef();
									}
									break;
									case 2: {
										setState(126);
										allSymbol();
									}
									break;
									case 3: {
										setState(127);
										match(NEW_LINE);
									}
									break;
								}
							}
							break;
							default:
								throw new NoViableAltException(this);
						}
						setState(130);
						_errHandler.sync(this);
						_alt = getInterpreter().adaptivePredict(_input, 17, _ctx);
					} while (_alt != 1 && _alt != org.antlr.v4.runtime.atn.ATN.INVALID_ALT_NUMBER);
					setState(132);
					match(D_BLOCK);
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

	public final FuncArgsContext funcArgs() throws RecognitionException {
		FuncArgsContext _localctx = new FuncArgsContext(_ctx, getState());
		enterRule(_localctx, 12, RULE_funcArgs);
		int _la;
		try {
			int _alt;
			setState(155);
			_errHandler.sync(this);
			switch (getInterpreter().adaptivePredict(_input, 23, _ctx)) {
				case 1:
					enterOuterAlt(_localctx, 1);
				{
					setState(136);
					_errHandler.sync(this);
					_la = _input.LA(1);
					if (_la == VARARG) {
						{
							setState(135);
							match(VARARG);
						}
					}

					setState(138);
					funcArg();
				}
				break;
				case 2:
					enterOuterAlt(_localctx, 2);
				{
					setState(139);
					funcArg();
					setState(140);
					match(COMMA);
					setState(146);
					_errHandler.sync(this);
					_alt = getInterpreter().adaptivePredict(_input, 20, _ctx);
					while (_alt != 2 && _alt != org.antlr.v4.runtime.atn.ATN.INVALID_ALT_NUMBER) {
						if (_alt == 1) {
							{
								{
									setState(141);
									funcArg();
									setState(142);
									match(COMMA);
								}
							}
						}
						setState(148);
						_errHandler.sync(this);
						_alt = getInterpreter().adaptivePredict(_input, 20, _ctx);
					}
					setState(150);
					_errHandler.sync(this);
					_la = _input.LA(1);
					if (_la == VARARG) {
						{
							setState(149);
							match(VARARG);
						}
					}

					setState(153);
					_errHandler.sync(this);
					_la = _input.LA(1);
					if ((((_la) & ~0x3f) == 0 && ((1L << _la) & ((1L << DT_STRING) | (1L << DT_DECIMAL) | (1L << DT_INTEGER) | (1L << DT_BOOLEAN) | (1L << DT_DATETIME) | (1L << DT_DATE) | (1L << DT_TIME) | (1L << DT_ANY) | (1L << IDENTIFIER))) != 0)) {
						{
							setState(152);
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

	public final FuncArgContext funcArg() throws RecognitionException {
		FuncArgContext _localctx = new FuncArgContext(_ctx, getState());
		enterRule(_localctx, 14, RULE_funcArg);
		int _la;
		try {
			enterOuterAlt(_localctx, 1);
			{
				setState(157);
				argName();
				setState(158);
				match(COLON);
				setState(159);
				dataType();
				setState(165);
				_errHandler.sync(this);
				_la = _input.LA(1);
				if (_la == COLON) {
					{
						setState(160);
						match(COLON);
						setState(161);
						match(LS_BRACKET);
						setState(162);
						enumerations();
						setState(163);
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

	public final ArgNameContext argName() throws RecognitionException {
		ArgNameContext _localctx = new ArgNameContext(_ctx, getState());
		enterRule(_localctx, 16, RULE_argName);
		try {
			setState(169);
			_errHandler.sync(this);
			switch (_input.LA(1)) {
				case IDENTIFIER:
					enterOuterAlt(_localctx, 1);
				{
					setState(167);
					match(IDENTIFIER);
				}
				break;
				case DT_STRING:
				case DT_DECIMAL:
				case DT_INTEGER:
				case DT_BOOLEAN:
				case DT_DATETIME:
				case DT_DATE:
				case DT_TIME:
				case DT_ANY:
					enterOuterAlt(_localctx, 2);
				{
					setState(168);
					dataType();
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

	public final ArgRefContext argRef() throws RecognitionException {
		ArgRefContext _localctx = new ArgRefContext(_ctx, getState());
		enterRule(_localctx, 18, RULE_argRef);
		try {
			enterOuterAlt(_localctx, 1);
			{
				setState(171);
				match(DOLLAR);
				setState(178);
				_errHandler.sync(this);
				switch (_input.LA(1)) {
					case INTEGER: {
						setState(172);
						match(INTEGER);
					}
					break;
					case DT_STRING:
					case DT_DECIMAL:
					case DT_INTEGER:
					case DT_BOOLEAN:
					case DT_DATETIME:
					case DT_DATE:
					case DT_TIME:
					case DT_ANY:
					case IDENTIFIER: {
						setState(173);
						argName();
					}
					break;
					case L_BRACE: {
						setState(174);
						match(L_BRACE);
						setState(175);
						argName();
						setState(176);
						match(R_BRACE);
					}
					break;
					default:
						throw new NoViableAltException(this);
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

	public final EnumerationsContext enumerations() throws RecognitionException {
		EnumerationsContext _localctx = new EnumerationsContext(_ctx, getState());
		enterRule(_localctx, 20, RULE_enumerations);
		int _la;
		try {
			setState(196);
			_errHandler.sync(this);
			switch (_input.LA(1)) {
				case STRING:
					enterOuterAlt(_localctx, 1);
				{
					setState(180);
					match(STRING);
					setState(185);
					_errHandler.sync(this);
					_la = _input.LA(1);
					while (_la == COMMA) {
						{
							{
								setState(181);
								match(COMMA);
								setState(182);
								match(STRING);
							}
						}
						setState(187);
						_errHandler.sync(this);
						_la = _input.LA(1);
					}
				}
				break;
				case NUMBER:
					enterOuterAlt(_localctx, 2);
				{
					setState(188);
					match(NUMBER);
					setState(193);
					_errHandler.sync(this);
					_la = _input.LA(1);
					while (_la == COMMA) {
						{
							{
								setState(189);
								match(COMMA);
								setState(190);
								match(NUMBER);
							}
						}
						setState(195);
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

	public final AllSymbolContext allSymbol() throws RecognitionException {
		AllSymbolContext _localctx = new AllSymbolContext(_ctx, getState());
		enterRule(_localctx, 22, RULE_allSymbol);
		try {
			setState(216);
			_errHandler.sync(this);
			switch (_input.LA(1)) {
				case IDENTIFIER:
					enterOuterAlt(_localctx, 1);
				{
					setState(198);
					match(IDENTIFIER);
				}
				break;
				case STRING:
					enterOuterAlt(_localctx, 2);
				{
					setState(199);
					match(STRING);
				}
				break;
				case NUMBER:
					enterOuterAlt(_localctx, 3);
				{
					setState(200);
					match(NUMBER);
				}
				break;
				case INTEGER:
					enterOuterAlt(_localctx, 4);
				{
					setState(201);
					match(INTEGER);
				}
				break;
				case DOLLAR:
					enterOuterAlt(_localctx, 5);
				{
					setState(202);
					match(DOLLAR);
				}
				break;
				case PLUS:
					enterOuterAlt(_localctx, 6);
				{
					setState(203);
					match(PLUS);
				}
				break;
				case MINUS:
					enterOuterAlt(_localctx, 7);
				{
					setState(204);
					match(MINUS);
				}
				break;
				case COLON:
					enterOuterAlt(_localctx, 8);
				{
					setState(205);
					match(COLON);
				}
				break;
				case COMMA:
					enterOuterAlt(_localctx, 9);
				{
					setState(206);
					match(COMMA);
				}
				break;
				case DOT:
					enterOuterAlt(_localctx, 10);
				{
					setState(207);
					match(DOT);
				}
				break;
				case L_BRACKET:
					enterOuterAlt(_localctx, 11);
				{
					setState(208);
					match(L_BRACKET);
				}
				break;
				case R_BRACKET:
					enterOuterAlt(_localctx, 12);
				{
					setState(209);
					match(R_BRACKET);
				}
				break;
				case LS_BRACKET:
					enterOuterAlt(_localctx, 13);
				{
					setState(210);
					match(LS_BRACKET);
				}
				break;
				case RS_BRACKET:
					enterOuterAlt(_localctx, 14);
				{
					setState(211);
					match(RS_BRACKET);
				}
				break;
				case L_BRACE:
					enterOuterAlt(_localctx, 15);
				{
					setState(212);
					match(L_BRACE);
				}
				break;
				case R_BRACE:
					enterOuterAlt(_localctx, 16);
				{
					setState(213);
					match(R_BRACE);
				}
				break;
				case SYMBOL:
					enterOuterAlt(_localctx, 17);
				{
					setState(214);
					match(SYMBOL);
				}
				break;
				case DT_STRING:
				case DT_DECIMAL:
				case DT_INTEGER:
				case DT_BOOLEAN:
				case DT_DATETIME:
				case DT_DATE:
				case DT_TIME:
				case DT_ANY:
					enterOuterAlt(_localctx, 18);
				{
					setState(215);
					dataType();
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

	public final DataTypeContext dataType() throws RecognitionException {
		DataTypeContext _localctx = new DataTypeContext(_ctx, getState());
		enterRule(_localctx, 24, RULE_dataType);
		int _la;
		try {
			enterOuterAlt(_localctx, 1);
			{
				setState(218);
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

	public static class RootContext extends ParserRuleContext {
		public RootContext(ParserRuleContext parent, int invokingState) {
			super(parent, invokingState);
		}

		public TerminalNode EOF() {
			return getToken(FunctionParser.EOF, 0);
		}

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

		public List<TerminalNode> NEW_LINE() {
			return getTokens(FunctionParser.NEW_LINE);
		}

		public TerminalNode NEW_LINE(int i) {
			return getToken(FunctionParser.NEW_LINE, i);
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

	public static class CategoryContext extends ParserRuleContext {
		public CategoryContext(ParserRuleContext parent, int invokingState) {
			super(parent, invokingState);
		}

		public List<TerminalNode> IDENTIFIER() {
			return getTokens(FunctionParser.IDENTIFIER);
		}

		public TerminalNode IDENTIFIER(int i) {
			return getToken(FunctionParser.IDENTIFIER, i);
		}

		public TerminalNode LS_BRACKET() {
			return getToken(FunctionParser.LS_BRACKET, 0);
		}

		public TerminalNode RS_BRACKET() {
			return getToken(FunctionParser.RS_BRACKET, 0);
		}

		public List<TerminalNode> COMMA() {
			return getTokens(FunctionParser.COMMA);
		}

		public TerminalNode COMMA(int i) {
			return getToken(FunctionParser.COMMA, i);
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

	public static class FunctionItemContext extends ParserRuleContext {
		public FunctionItemContext(ParserRuleContext parent, int invokingState) {
			super(parent, invokingState);
		}

		public TerminalNode DESCRIPTION() {
			return getToken(FunctionParser.DESCRIPTION, 0);
		}

		public CategoryContext category() {
			return getRuleContext(CategoryContext.class, 0);
		}

		public List<TerminalNode> COLON() {
			return getTokens(FunctionParser.COLON);
		}

		public TerminalNode COLON(int i) {
			return getToken(FunctionParser.COLON, i);
		}

		public FuncDefineContext funcDefine() {
			return getRuleContext(FuncDefineContext.class, 0);
		}

		public TerminalNode TRANSFER() {
			return getToken(FunctionParser.TRANSFER, 0);
		}

		public FuncImplementContext funcImplement() {
			return getRuleContext(FuncImplementContext.class, 0);
		}

		public List<TerminalNode> NEW_LINE() {
			return getTokens(FunctionParser.NEW_LINE);
		}

		public TerminalNode NEW_LINE(int i) {
			return getToken(FunctionParser.NEW_LINE, i);
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

	public static class FunctionAliasContext extends ParserRuleContext {
		public FunctionAliasContext(ParserRuleContext parent, int invokingState) {
			super(parent, invokingState);
		}

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

	public static class FuncDefineContext extends ParserRuleContext {
		public FuncDefineContext(ParserRuleContext parent, int invokingState) {
			super(parent, invokingState);
		}

		public TerminalNode IDENTIFIER() {
			return getToken(FunctionParser.IDENTIFIER, 0);
		}

		public TerminalNode L_BRACKET() {
			return getToken(FunctionParser.L_BRACKET, 0);
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

		public ArgRefContext argRef() {
			return getRuleContext(ArgRefContext.class, 0);
		}

		public TerminalNode DOT() {
			return getToken(FunctionParser.DOT, 0);
		}

		public TerminalNode TYPE() {
			return getToken(FunctionParser.TYPE, 0);
		}

		public FuncArgsContext funcArgs() {
			return getRuleContext(FuncArgsContext.class, 0);
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

	public static class FuncImplementContext extends ParserRuleContext {
		public FuncImplementContext(ParserRuleContext parent, int invokingState) {
			super(parent, invokingState);
		}

		public List<ArgRefContext> argRef() {
			return getRuleContexts(ArgRefContext.class);
		}

		public ArgRefContext argRef(int i) {
			return getRuleContext(ArgRefContext.class, i);
		}

		public List<AllSymbolContext> allSymbol() {
			return getRuleContexts(AllSymbolContext.class);
		}

		public AllSymbolContext allSymbol(int i) {
			return getRuleContext(AllSymbolContext.class, i);
		}

		public List<TerminalNode> NEW_LINE() {
			return getTokens(FunctionParser.NEW_LINE);
		}

		public TerminalNode NEW_LINE(int i) {
			return getToken(FunctionParser.NEW_LINE, i);
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

	public static class FuncArgsContext extends ParserRuleContext {
		public FuncArgsContext(ParserRuleContext parent, int invokingState) {
			super(parent, invokingState);
		}

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

	public static class FuncArgContext extends ParserRuleContext {
		public FuncArgContext(ParserRuleContext parent, int invokingState) {
			super(parent, invokingState);
		}

		public ArgNameContext argName() {
			return getRuleContext(ArgNameContext.class, 0);
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

	public static class ArgNameContext extends ParserRuleContext {
		public ArgNameContext(ParserRuleContext parent, int invokingState) {
			super(parent, invokingState);
		}

		public TerminalNode IDENTIFIER() {
			return getToken(FunctionParser.IDENTIFIER, 0);
		}

		public DataTypeContext dataType() {
			return getRuleContext(DataTypeContext.class, 0);
		}

		@Override
		public int getRuleIndex() {
			return RULE_argName;
		}

		@Override
		public void enterRule(ParseTreeListener listener) {
			if (listener instanceof FunctionListener) ((FunctionListener) listener).enterArgName(this);
		}

		@Override
		public void exitRule(ParseTreeListener listener) {
			if (listener instanceof FunctionListener) ((FunctionListener) listener).exitArgName(this);
		}
	}

	public static class ArgRefContext extends ParserRuleContext {
		public ArgRefContext(ParserRuleContext parent, int invokingState) {
			super(parent, invokingState);
		}

		public TerminalNode DOLLAR() {
			return getToken(FunctionParser.DOLLAR, 0);
		}

		public TerminalNode INTEGER() {
			return getToken(FunctionParser.INTEGER, 0);
		}

		public ArgNameContext argName() {
			return getRuleContext(ArgNameContext.class, 0);
		}

		public TerminalNode L_BRACE() {
			return getToken(FunctionParser.L_BRACE, 0);
		}

		public TerminalNode R_BRACE() {
			return getToken(FunctionParser.R_BRACE, 0);
		}

		@Override
		public int getRuleIndex() {
			return RULE_argRef;
		}

		@Override
		public void enterRule(ParseTreeListener listener) {
			if (listener instanceof FunctionListener) ((FunctionListener) listener).enterArgRef(this);
		}

		@Override
		public void exitRule(ParseTreeListener listener) {
			if (listener instanceof FunctionListener) ((FunctionListener) listener).exitArgRef(this);
		}
	}

	public static class EnumerationsContext extends ParserRuleContext {
		public EnumerationsContext(ParserRuleContext parent, int invokingState) {
			super(parent, invokingState);
		}

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

	public static class AllSymbolContext extends ParserRuleContext {
		public AllSymbolContext(ParserRuleContext parent, int invokingState) {
			super(parent, invokingState);
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

		public TerminalNode DOLLAR() {
			return getToken(FunctionParser.DOLLAR, 0);
		}

		public TerminalNode PLUS() {
			return getToken(FunctionParser.PLUS, 0);
		}

		public TerminalNode MINUS() {
			return getToken(FunctionParser.MINUS, 0);
		}

		public TerminalNode COLON() {
			return getToken(FunctionParser.COLON, 0);
		}

		public TerminalNode COMMA() {
			return getToken(FunctionParser.COMMA, 0);
		}

		public TerminalNode DOT() {
			return getToken(FunctionParser.DOT, 0);
		}

		public TerminalNode L_BRACKET() {
			return getToken(FunctionParser.L_BRACKET, 0);
		}

		public TerminalNode R_BRACKET() {
			return getToken(FunctionParser.R_BRACKET, 0);
		}

		public TerminalNode LS_BRACKET() {
			return getToken(FunctionParser.LS_BRACKET, 0);
		}

		public TerminalNode RS_BRACKET() {
			return getToken(FunctionParser.RS_BRACKET, 0);
		}

		public TerminalNode L_BRACE() {
			return getToken(FunctionParser.L_BRACE, 0);
		}

		public TerminalNode R_BRACE() {
			return getToken(FunctionParser.R_BRACE, 0);
		}

		public TerminalNode SYMBOL() {
			return getToken(FunctionParser.SYMBOL, 0);
		}

		public DataTypeContext dataType() {
			return getRuleContext(DataTypeContext.class, 0);
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

	public static class DataTypeContext extends ParserRuleContext {
		public DataTypeContext(ParserRuleContext parent, int invokingState) {
			super(parent, invokingState);
		}

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
}