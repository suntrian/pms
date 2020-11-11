// Generated from C:/Users/suntr/Project/pms/module/module-formula/src/main/java/org/sunt/formula/function/parser\FunctionParser.g4 by ANTLR 4.8
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
			VARARG = 1, ALIAS = 2, DT_STRING = 3, DT_DECIMAL = 4, DT_INTEGER = 5, DT_BOOLEAN = 6,
			DT_DATETIME = 7, DT_DATE = 8, DT_TIME = 9, DT_ANY = 10, TYPE = 11, PLUS = 12, MINUS = 13,
			COLON = 14, COMMA = 15, DOT = 16, EQUAL = 17, DOLLAR = 18, L_BRACKET = 19, R_BRACKET = 20,
			LS_BRACKET = 21, RS_BRACKET = 22, L_BRACE = 23, R_BRACE = 24, SYMBOL = 25, INTEGER = 26,
			NUMBER = 27, STRING = 28, IDENTIFIER = 29, NEW_LINE = 30, WS = 31, LINE_COMMENT = 32,
			DESCRIPTION = 33, TRANSFER_D = 34, TRANSFER_S = 35, TRANSFER_L = 36, COMMENT_L = 37,
			COMMENT_BL = 38, WS_L = 39, ANY_L = 40, NEWLINE = 41, COMMENT_S = 42, COMMENT_BS = 43,
			WS_S = 44, ANY_S = 45, S_BLOCK = 46, COMMENT_D = 47, COMMENT_BD = 48, WS_D = 49, ANY_D = 50,
			D_BLOCK = 51;
	public static final int
			RULE_root = 0, RULE_category = 1, RULE_functionItem = 2, RULE_functionAlias = 3,
			RULE_funcDefine = 4, RULE_funcImplement = 5, RULE_funcImplementBody = 6,
			RULE_funcArgs = 7, RULE_funcArg = 8, RULE_argName = 9, RULE_argRef = 10,
			RULE_enumerations = 11, RULE_allSymbol = 12, RULE_dataType = 13;
	/**
	 * @deprecated Use {@link #VOCABULARY} instead.
	 */
	@Deprecated
	public static final String[] tokenNames;
	public static final String _serializedATN =
			"\3\u608b\ua72a\u8133\ub9ed\u417c\u3be7\u7786\u5964\3\65\u00e2\4\2\t\2" +
					"\4\3\t\3\4\4\t\4\4\5\t\5\4\6\t\6\4\7\t\7\4\b\t\b\4\t\t\t\4\n\t\n\4\13" +
					"\t\13\4\f\t\f\4\r\t\r\4\16\t\16\4\17\t\17\3\2\3\2\3\2\6\2\"\n\2\r\2\16" +
					"\2#\3\2\3\2\3\3\3\3\3\3\3\3\3\3\7\3-\n\3\f\3\16\3\60\13\3\3\3\5\3\63\n" +
					"\3\3\4\3\4\7\4\67\n\4\f\4\16\4:\13\4\3\4\3\4\7\4>\n\4\f\4\16\4A\13\4\3" +
					"\4\3\4\3\4\7\4F\n\4\f\4\16\4I\13\4\3\4\3\4\7\4M\n\4\f\4\16\4P\13\4\3\4" +
					"\3\4\3\5\3\5\3\5\3\5\3\5\3\5\7\5Z\n\5\f\5\16\5]\13\5\3\6\3\6\3\6\5\6b" +
					"\n\6\3\6\3\6\3\6\3\6\3\6\3\6\3\6\5\6k\n\6\3\7\3\7\3\7\5\7p\n\7\3\7\3\7" +
					"\3\7\3\7\3\7\3\7\3\7\3\7\5\7z\n\7\3\b\6\b}\n\b\r\b\16\b~\3\b\6\b\u0082" +
					"\n\b\r\b\16\b\u0083\3\b\6\b\u0087\n\b\r\b\16\b\u0088\5\b\u008b\n\b\3\t" +
					"\5\t\u008e\n\t\3\t\3\t\3\t\3\t\3\t\3\t\7\t\u0096\n\t\f\t\16\t\u0099\13" +
					"\t\3\t\5\t\u009c\n\t\3\t\5\t\u009f\n\t\5\t\u00a1\n\t\3\n\3\n\3\n\3\n\3" +
					"\n\3\n\3\n\3\n\5\n\u00ab\n\n\3\13\3\13\5\13\u00af\n\13\3\f\3\f\3\f\3\f" +
					"\3\f\3\f\3\f\5\f\u00b8\n\f\3\r\3\r\3\r\7\r\u00bd\n\r\f\r\16\r\u00c0\13" +
					"\r\3\r\3\r\3\r\7\r\u00c5\n\r\f\r\16\r\u00c8\13\r\5\r\u00ca\n\r\3\16\3" +
					"\16\3\16\3\16\3\16\3\16\3\16\3\16\3\16\3\16\3\16\3\16\3\16\3\16\3\16\3" +
					"\16\3\16\3\16\5\16\u00de\n\16\3\17\3\17\3\17\t8?GN~\u0083\u0088\2\20\2" +
					"\4\6\b\n\f\16\20\22\24\26\30\32\34\2\6\3\2)*\3\2./\3\2\63\64\3\2\5\f\2" +
					"\u0104\2!\3\2\2\2\4\62\3\2\2\2\6\64\3\2\2\2\bS\3\2\2\2\n^\3\2\2\2\fy\3" +
					"\2\2\2\16\u008a\3\2\2\2\20\u00a0\3\2\2\2\22\u00a2\3\2\2\2\24\u00ae\3\2" +
					"\2\2\26\u00b0\3\2\2\2\30\u00c9\3\2\2\2\32\u00dd\3\2\2\2\34\u00df\3\2\2" +
					"\2\36\"\5\6\4\2\37\"\5\b\5\2 \"\7 \2\2!\36\3\2\2\2!\37\3\2\2\2! \3\2\2" +
					"\2\"#\3\2\2\2#!\3\2\2\2#$\3\2\2\2$%\3\2\2\2%&\7\2\2\3&\3\3\2\2\2\'\63" +
					"\7\37\2\2()\7\27\2\2).\7\37\2\2*+\7\21\2\2+-\7\37\2\2,*\3\2\2\2-\60\3" +
					"\2\2\2.,\3\2\2\2./\3\2\2\2/\61\3\2\2\2\60.\3\2\2\2\61\63\7\30\2\2\62\'" +
					"\3\2\2\2\62(\3\2\2\2\63\5\3\2\2\2\648\7#\2\2\65\67\7 \2\2\66\65\3\2\2" +
					"\2\67:\3\2\2\289\3\2\2\28\66\3\2\2\29;\3\2\2\2:8\3\2\2\2;?\5\4\3\2<>\7" +
					" \2\2=<\3\2\2\2>A\3\2\2\2?@\3\2\2\2?=\3\2\2\2@B\3\2\2\2A?\3\2\2\2BC\7" +
					"\20\2\2CG\7\20\2\2DF\7 \2\2ED\3\2\2\2FI\3\2\2\2GH\3\2\2\2GE\3\2\2\2HJ" +
					"\3\2\2\2IG\3\2\2\2JN\5\n\6\2KM\7 \2\2LK\3\2\2\2MP\3\2\2\2NO\3\2\2\2NL" +
					"\3\2\2\2OQ\3\2\2\2PN\3\2\2\2QR\5\f\7\2R\7\3\2\2\2ST\7\4\2\2TU\7\37\2\2" +
					"UV\7\23\2\2V[\7\37\2\2WX\7\21\2\2XZ\7\37\2\2YW\3\2\2\2Z]\3\2\2\2[Y\3\2" +
					"\2\2[\\\3\2\2\2\\\t\3\2\2\2][\3\2\2\2^_\7\37\2\2_a\7\25\2\2`b\5\20\t\2" +
					"a`\3\2\2\2ab\3\2\2\2bc\3\2\2\2cd\7\26\2\2dj\7\20\2\2ek\5\34\17\2fg\5\26" +
					"\f\2gh\7\22\2\2hi\7\r\2\2ik\3\2\2\2je\3\2\2\2jf\3\2\2\2k\13\3\2\2\2lm" +
					"\7&\2\2mo\5\16\b\2np\7+\2\2on\3\2\2\2op\3\2\2\2pz\3\2\2\2qr\7%\2\2rs\5" +
					"\16\b\2st\7\60\2\2tz\3\2\2\2uv\7$\2\2vw\5\16\b\2wx\7\65\2\2xz\3\2\2\2" +
					"yl\3\2\2\2yq\3\2\2\2yu\3\2\2\2z\r\3\2\2\2{}\t\2\2\2|{\3\2\2\2}~\3\2\2" +
					"\2~\177\3\2\2\2~|\3\2\2\2\177\u008b\3\2\2\2\u0080\u0082\t\3\2\2\u0081" +
					"\u0080\3\2\2\2\u0082\u0083\3\2\2\2\u0083\u0084\3\2\2\2\u0083\u0081\3\2" +
					"\2\2\u0084\u008b\3\2\2\2\u0085\u0087\t\4\2\2\u0086\u0085\3\2\2\2\u0087" +
					"\u0088\3\2\2\2\u0088\u0089\3\2\2\2\u0088\u0086\3\2\2\2\u0089\u008b\3\2" +
					"\2\2\u008a|\3\2\2\2\u008a\u0081\3\2\2\2\u008a\u0086\3\2\2\2\u008b\17\3" +
					"\2\2\2\u008c\u008e\7\3\2\2\u008d\u008c\3\2\2\2\u008d\u008e\3\2\2\2\u008e" +
					"\u008f\3\2\2\2\u008f\u00a1\5\22\n\2\u0090\u0091\5\22\n\2\u0091\u0097\7" +
					"\21\2\2\u0092\u0093\5\22\n\2\u0093\u0094\7\21\2\2\u0094\u0096\3\2\2\2" +
					"\u0095\u0092\3\2\2\2\u0096\u0099\3\2\2\2\u0097\u0095\3\2\2\2\u0097\u0098" +
					"\3\2\2\2\u0098\u009b\3\2\2\2\u0099\u0097\3\2\2\2\u009a\u009c\7\3\2\2\u009b" +
					"\u009a\3\2\2\2\u009b\u009c\3\2\2\2\u009c\u009e\3\2\2\2\u009d\u009f\5\22" +
					"\n\2\u009e\u009d\3\2\2\2\u009e\u009f\3\2\2\2\u009f\u00a1\3\2\2\2\u00a0" +
					"\u008d\3\2\2\2\u00a0\u0090\3\2\2\2\u00a1\21\3\2\2\2\u00a2\u00a3\5\24\13" +
					"\2\u00a3\u00a4\7\20\2\2\u00a4\u00aa\5\34\17\2\u00a5\u00a6\7\20\2\2\u00a6" +
					"\u00a7\7\27\2\2\u00a7\u00a8\5\30\r\2\u00a8\u00a9\7\30\2\2\u00a9\u00ab" +
					"\3\2\2\2\u00aa\u00a5\3\2\2\2\u00aa\u00ab\3\2\2\2\u00ab\23\3\2\2\2\u00ac" +
					"\u00af\7\37\2\2\u00ad\u00af\5\34\17\2\u00ae\u00ac\3\2\2\2\u00ae\u00ad" +
					"\3\2\2\2\u00af\25\3\2\2\2\u00b0\u00b7\7\24\2\2\u00b1\u00b8\7\34\2\2\u00b2" +
					"\u00b8\5\24\13\2\u00b3\u00b4\7\31\2\2\u00b4\u00b5\5\24\13\2\u00b5\u00b6" +
					"\7\32\2\2\u00b6\u00b8\3\2\2\2\u00b7\u00b1\3\2\2\2\u00b7\u00b2\3\2\2\2" +
					"\u00b7\u00b3\3\2\2\2\u00b8\27\3\2\2\2\u00b9\u00be\7\36\2\2\u00ba\u00bb" +
					"\7\21\2\2\u00bb\u00bd\7\36\2\2\u00bc\u00ba\3\2\2\2\u00bd\u00c0\3\2\2\2" +
					"\u00be\u00bc\3\2\2\2\u00be\u00bf\3\2\2\2\u00bf\u00ca\3\2\2\2\u00c0\u00be" +
					"\3\2\2\2\u00c1\u00c6\7\35\2\2\u00c2\u00c3\7\21\2\2\u00c3\u00c5\7\35\2" +
					"\2\u00c4\u00c2\3\2\2\2\u00c5\u00c8\3\2\2\2\u00c6\u00c4\3\2\2\2\u00c6\u00c7" +
					"\3\2\2\2\u00c7\u00ca\3\2\2\2\u00c8\u00c6\3\2\2\2\u00c9\u00b9\3\2\2\2\u00c9" +
					"\u00c1\3\2\2\2\u00ca\31\3\2\2\2\u00cb\u00de\7\37\2\2\u00cc\u00de\7\36" +
					"\2\2\u00cd\u00de\7\35\2\2\u00ce\u00de\7\34\2\2\u00cf\u00de\7\24\2\2\u00d0" +
					"\u00de\7\16\2\2\u00d1\u00de\7\17\2\2\u00d2\u00de\7\20\2\2\u00d3\u00de" +
					"\7\21\2\2\u00d4\u00de\7\22\2\2\u00d5\u00de\7\25\2\2\u00d6\u00de\7\26\2" +
					"\2\u00d7\u00de\7\27\2\2\u00d8\u00de\7\30\2\2\u00d9\u00de\7\31\2\2\u00da" +
					"\u00de\7\32\2\2\u00db\u00de\7\33\2\2\u00dc\u00de\5\34\17\2\u00dd\u00cb" +
					"\3\2\2\2\u00dd\u00cc\3\2\2\2\u00dd\u00cd\3\2\2\2\u00dd\u00ce\3\2\2\2\u00dd" +
					"\u00cf\3\2\2\2\u00dd\u00d0\3\2\2\2\u00dd\u00d1\3\2\2\2\u00dd\u00d2\3\2" +
					"\2\2\u00dd\u00d3\3\2\2\2\u00dd\u00d4\3\2\2\2\u00dd\u00d5\3\2\2\2\u00dd" +
					"\u00d6\3\2\2\2\u00dd\u00d7\3\2\2\2\u00dd\u00d8\3\2\2\2\u00dd\u00d9\3\2" +
					"\2\2\u00dd\u00da\3\2\2\2\u00dd\u00db\3\2\2\2\u00dd\u00dc\3\2\2\2\u00de" +
					"\33\3\2\2\2\u00df\u00e0\t\5\2\2\u00e0\35\3\2\2\2\37!#.\628?GN[ajoy~\u0083" +
					"\u0088\u008a\u008d\u0097\u009b\u009e\u00a0\u00aa\u00ae\u00b7\u00be\u00c6" +
					"\u00c9\u00dd";
	public static final ATN _ATN =
			new ATNDeserializer().deserialize(_serializedATN.toCharArray());
	protected static final DFA[] _decisionToDFA;
	public static final String[] ruleNames = makeRuleNames();
	protected static final PredictionContextCache _sharedContextCache =
			new PredictionContextCache();
	private static final String[] _LITERAL_NAMES = makeLiteralNames();

	static {
		RuntimeMetaData.checkVersion("4.8", RuntimeMetaData.VERSION);
	}

	private static final String[] _SYMBOLIC_NAMES = makeSymbolicNames();
	public static final Vocabulary VOCABULARY = new VocabularyImpl(_LITERAL_NAMES, _SYMBOLIC_NAMES);

	static {
		_decisionToDFA = new DFA[_ATN.getNumberOfDecisions()];
		for (int i = 0; i < _ATN.getNumberOfDecisions(); i++) {
			_decisionToDFA[i] = new DFA(_ATN.getDecisionState(i), i);
		}
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

	private static String[] makeRuleNames() {
		return new String[]{
				"root", "category", "functionItem", "functionAlias", "funcDefine", "funcImplement",
				"funcImplementBody", "funcArgs", "funcArg", "argName", "argRef", "enumerations",
				"allSymbol", "dataType"
		};
	}

	private static String[] makeLiteralNames() {
		return new String[]{
				null, null, null, null, null, null, null, null, null, null, null, null,
				"'+'", "'-'", "':'", "','", "'.'", "'='", "'$'", "'('", "')'", "'['",
				"']'", "'{'", "'}'", null, null, null, null, null, null, null, null,
				null, null, null, "'->'", null, null, null, null, null, null, null, null,
				null, "'''''", null, null, null, null, "'\"\"\"'"
		};
	}

	public FunctionParser(TokenStream input) {
		super(input);
		_interp = new ParserATNSimulator(this, _ATN, _decisionToDFA, _sharedContextCache);
	}

	private static String[] makeSymbolicNames() {
		return new String[]{
				null, "VARARG", "ALIAS", "DT_STRING", "DT_DECIMAL", "DT_INTEGER", "DT_BOOLEAN",
				"DT_DATETIME", "DT_DATE", "DT_TIME", "DT_ANY", "TYPE", "PLUS", "MINUS",
				"COLON", "COMMA", "DOT", "EQUAL", "DOLLAR", "L_BRACKET", "R_BRACKET",
				"LS_BRACKET", "RS_BRACKET", "L_BRACE", "R_BRACE", "SYMBOL", "INTEGER",
				"NUMBER", "STRING", "IDENTIFIER", "NEW_LINE", "WS", "LINE_COMMENT", "DESCRIPTION",
				"TRANSFER_D", "TRANSFER_S", "TRANSFER_L", "COMMENT_L", "COMMENT_BL",
				"WS_L", "ANY_L", "NEWLINE", "COMMENT_S", "COMMENT_BS", "WS_S", "ANY_S",
				"S_BLOCK", "COMMENT_D", "COMMENT_BD", "WS_D", "ANY_D", "D_BLOCK"
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
		return _ATN; }

	public final RootContext root() throws RecognitionException {
		RootContext _localctx = new RootContext(_ctx, getState());
		enterRule(_localctx, 0, RULE_root);
		int _la;
		try {
			enterOuterAlt(_localctx, 1);
			{
				setState(31);
				_errHandler.sync(this);
				_la = _input.LA(1);
				do {
					{
						setState(31);
						_errHandler.sync(this);
						switch (_input.LA(1)) {
							case DESCRIPTION: {
								setState(28);
								functionItem();
							}
							break;
							case ALIAS: {
								setState(29);
								functionAlias();
							}
							break;
							case NEW_LINE: {
								setState(30);
								match(NEW_LINE);
							}
							break;
							default:
								throw new NoViableAltException(this);
						}
					}
					setState(33);
					_errHandler.sync(this);
					_la = _input.LA(1);
				} while ((((_la) & ~0x3f) == 0 && ((1L << _la) & ((1L << ALIAS) | (1L << NEW_LINE) | (1L << DESCRIPTION))) != 0));
				setState(35);
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
			setState(48);
			_errHandler.sync(this);
			switch (_input.LA(1)) {
				case IDENTIFIER:
					enterOuterAlt(_localctx, 1);
				{
					setState(37);
					match(IDENTIFIER);
				}
				break;
				case LS_BRACKET:
					enterOuterAlt(_localctx, 2);
				{
					setState(38);
					match(LS_BRACKET);
					setState(39);
					match(IDENTIFIER);
					setState(44);
					_errHandler.sync(this);
					_la = _input.LA(1);
					while (_la == COMMA) {
						{
							{
								setState(40);
								match(COMMA);
								setState(41);
								match(IDENTIFIER);
							}
						}
						setState(46);
						_errHandler.sync(this);
						_la = _input.LA(1);
					}
					setState(47);
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
				setState(50);
				match(DESCRIPTION);
				setState(54);
				_errHandler.sync(this);
				_alt = getInterpreter().adaptivePredict(_input, 4, _ctx);
				while (_alt != 1 && _alt != org.antlr.v4.runtime.atn.ATN.INVALID_ALT_NUMBER) {
					if (_alt == 1 + 1) {
						{
							{
								setState(51);
								match(NEW_LINE);
							}
						}
					}
					setState(56);
					_errHandler.sync(this);
					_alt = getInterpreter().adaptivePredict(_input, 4, _ctx);
				}
				setState(57);
				category();
				setState(61);
				_errHandler.sync(this);
				_alt = getInterpreter().adaptivePredict(_input, 5, _ctx);
				while (_alt != 1 && _alt != org.antlr.v4.runtime.atn.ATN.INVALID_ALT_NUMBER) {
					if (_alt == 1 + 1) {
						{
							{
								setState(58);
								match(NEW_LINE);
							}
						}
					}
					setState(63);
					_errHandler.sync(this);
					_alt = getInterpreter().adaptivePredict(_input, 5, _ctx);
				}
				setState(64);
				match(COLON);
				setState(65);
				match(COLON);
				setState(69);
				_errHandler.sync(this);
				_alt = getInterpreter().adaptivePredict(_input, 6, _ctx);
				while (_alt != 1 && _alt != org.antlr.v4.runtime.atn.ATN.INVALID_ALT_NUMBER) {
					if (_alt == 1 + 1) {
						{
							{
								setState(66);
								match(NEW_LINE);
							}
						}
					}
					setState(71);
					_errHandler.sync(this);
					_alt = getInterpreter().adaptivePredict(_input, 6, _ctx);
				}
				setState(72);
				funcDefine();
				setState(76);
				_errHandler.sync(this);
				_alt = getInterpreter().adaptivePredict(_input, 7, _ctx);
				while (_alt != 1 && _alt != org.antlr.v4.runtime.atn.ATN.INVALID_ALT_NUMBER) {
					if (_alt == 1 + 1) {
						{
							{
								setState(73);
								match(NEW_LINE);
							}
						}
					}
					setState(78);
					_errHandler.sync(this);
					_alt = getInterpreter().adaptivePredict(_input, 7, _ctx);
				}
				setState(79);
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
				setState(81);
				match(ALIAS);
				setState(82);
				match(IDENTIFIER);
				setState(83);
				match(EQUAL);
				setState(84);
				match(IDENTIFIER);
				setState(89);
				_errHandler.sync(this);
				_la = _input.LA(1);
				while (_la == COMMA) {
					{
						{
							setState(85);
							match(COMMA);
							setState(86);
							match(IDENTIFIER);
						}
					}
					setState(91);
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
				setState(92);
				match(IDENTIFIER);
				setState(93);
				match(L_BRACKET);
				setState(95);
				_errHandler.sync(this);
				_la = _input.LA(1);
				if ((((_la) & ~0x3f) == 0 && ((1L << _la) & ((1L << VARARG) | (1L << DT_STRING) | (1L << DT_DECIMAL) | (1L << DT_INTEGER) | (1L << DT_BOOLEAN) | (1L << DT_DATETIME) | (1L << DT_DATE) | (1L << DT_TIME) | (1L << DT_ANY) | (1L << IDENTIFIER))) != 0)) {
					{
						setState(94);
						funcArgs();
					}
				}

				setState(97);
				match(R_BRACKET);
				setState(98);
				match(COLON);
				setState(104);
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
						setState(99);
						dataType();
					}
					break;
					case DOLLAR: {
						setState(100);
						argRef();
						setState(101);
						match(DOT);
						setState(102);
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
		int _la;
		try {
			setState(119);
			_errHandler.sync(this);
			switch (_input.LA(1)) {
				case TRANSFER_L:
					enterOuterAlt(_localctx, 1);
				{
					setState(106);
					match(TRANSFER_L);
					setState(107);
					funcImplementBody();
					setState(109);
					_errHandler.sync(this);
					_la = _input.LA(1);
					if (_la == NEWLINE) {
						{
							setState(108);
							match(NEWLINE);
						}
					}

				}
				break;
				case TRANSFER_S:
					enterOuterAlt(_localctx, 2);
				{
					setState(111);
					match(TRANSFER_S);
					setState(112);
					funcImplementBody();
					setState(113);
					match(S_BLOCK);
				}
				break;
				case TRANSFER_D:
					enterOuterAlt(_localctx, 3);
				{
					setState(115);
					match(TRANSFER_D);
					setState(116);
					funcImplementBody();
					setState(117);
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

	public final FuncImplementBodyContext funcImplementBody() throws RecognitionException {
		FuncImplementBodyContext _localctx = new FuncImplementBodyContext(_ctx, getState());
		enterRule(_localctx, 12, RULE_funcImplementBody);
		int _la;
		try {
			int _alt;
			setState(136);
			_errHandler.sync(this);
			switch (_input.LA(1)) {
				case WS_L:
				case ANY_L:
					enterOuterAlt(_localctx, 1);
				{
					setState(122);
					_errHandler.sync(this);
					_alt = 1 + 1;
					do {
						switch (_alt) {
							case 1 + 1: {
								{
									setState(121);
									_la = _input.LA(1);
									if (!(_la == WS_L || _la == ANY_L)) {
										_errHandler.recoverInline(this);
									} else {
										if (_input.LA(1) == Token.EOF) matchedEOF = true;
										_errHandler.reportMatch(this);
										consume();
									}
								}
							}
							break;
							default:
								throw new NoViableAltException(this);
						}
						setState(124);
						_errHandler.sync(this);
						_alt = getInterpreter().adaptivePredict(_input, 13, _ctx);
					} while (_alt != 1 && _alt != org.antlr.v4.runtime.atn.ATN.INVALID_ALT_NUMBER);
				}
				break;
				case WS_S:
				case ANY_S:
					enterOuterAlt(_localctx, 2);
				{
					setState(127);
					_errHandler.sync(this);
					_alt = 1 + 1;
					do {
						switch (_alt) {
							case 1 + 1: {
								{
									setState(126);
									_la = _input.LA(1);
									if (!(_la == WS_S || _la == ANY_S)) {
										_errHandler.recoverInline(this);
									} else {
										if (_input.LA(1) == Token.EOF) matchedEOF = true;
										_errHandler.reportMatch(this);
										consume();
									}
								}
							}
							break;
							default:
								throw new NoViableAltException(this);
						}
						setState(129);
						_errHandler.sync(this);
						_alt = getInterpreter().adaptivePredict(_input, 14, _ctx);
					} while (_alt != 1 && _alt != org.antlr.v4.runtime.atn.ATN.INVALID_ALT_NUMBER);
				}
				break;
				case WS_D:
				case ANY_D:
					enterOuterAlt(_localctx, 3);
				{
					setState(132);
					_errHandler.sync(this);
					_alt = 1 + 1;
					do {
						switch (_alt) {
							case 1 + 1: {
								{
									setState(131);
									_la = _input.LA(1);
									if (!(_la == WS_D || _la == ANY_D)) {
										_errHandler.recoverInline(this);
									} else {
										if (_input.LA(1) == Token.EOF) matchedEOF = true;
										_errHandler.reportMatch(this);
										consume();
									}
								}
							}
							break;
							default:
								throw new NoViableAltException(this);
						}
						setState(134);
						_errHandler.sync(this);
						_alt = getInterpreter().adaptivePredict(_input, 15, _ctx);
					} while (_alt != 1 && _alt != org.antlr.v4.runtime.atn.ATN.INVALID_ALT_NUMBER);
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
		enterRule(_localctx, 14, RULE_funcArgs);
		int _la;
		try {
			int _alt;
			setState(158);
			_errHandler.sync(this);
			switch (getInterpreter().adaptivePredict(_input, 21, _ctx)) {
				case 1:
					enterOuterAlt(_localctx, 1);
				{
					setState(139);
					_errHandler.sync(this);
					_la = _input.LA(1);
					if (_la == VARARG) {
						{
							setState(138);
							match(VARARG);
						}
					}

					setState(141);
					funcArg();
				}
				break;
				case 2:
					enterOuterAlt(_localctx, 2);
				{
					setState(142);
					funcArg();
					setState(143);
					match(COMMA);
					setState(149);
					_errHandler.sync(this);
					_alt = getInterpreter().adaptivePredict(_input, 18, _ctx);
					while (_alt != 2 && _alt != org.antlr.v4.runtime.atn.ATN.INVALID_ALT_NUMBER) {
						if (_alt == 1) {
							{
								{
									setState(144);
									funcArg();
									setState(145);
									match(COMMA);
								}
							}
						}
						setState(151);
						_errHandler.sync(this);
						_alt = getInterpreter().adaptivePredict(_input, 18, _ctx);
					}
					setState(153);
					_errHandler.sync(this);
					_la = _input.LA(1);
					if (_la == VARARG) {
						{
							setState(152);
							match(VARARG);
						}
					}

					setState(156);
					_errHandler.sync(this);
					_la = _input.LA(1);
					if ((((_la) & ~0x3f) == 0 && ((1L << _la) & ((1L << DT_STRING) | (1L << DT_DECIMAL) | (1L << DT_INTEGER) | (1L << DT_BOOLEAN) | (1L << DT_DATETIME) | (1L << DT_DATE) | (1L << DT_TIME) | (1L << DT_ANY) | (1L << IDENTIFIER))) != 0)) {
						{
							setState(155);
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
		enterRule(_localctx, 16, RULE_funcArg);
		int _la;
		try {
			enterOuterAlt(_localctx, 1);
			{
				setState(160);
				argName();
				setState(161);
				match(COLON);
				setState(162);
				dataType();
				setState(168);
				_errHandler.sync(this);
				_la = _input.LA(1);
				if (_la == COLON) {
					{
						setState(163);
						match(COLON);
						setState(164);
						match(LS_BRACKET);
						setState(165);
						enumerations();
						setState(166);
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
		enterRule(_localctx, 18, RULE_argName);
		try {
			setState(172);
			_errHandler.sync(this);
			switch (_input.LA(1)) {
				case IDENTIFIER:
					enterOuterAlt(_localctx, 1);
				{
					setState(170);
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
					setState(171);
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
		enterRule(_localctx, 20, RULE_argRef);
		try {
			enterOuterAlt(_localctx, 1);
			{
				setState(174);
				match(DOLLAR);
				setState(181);
				_errHandler.sync(this);
				switch (_input.LA(1)) {
					case INTEGER: {
						setState(175);
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
						setState(176);
						argName();
					}
					break;
					case L_BRACE: {
						setState(177);
						match(L_BRACE);
						setState(178);
						argName();
						setState(179);
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
		enterRule(_localctx, 22, RULE_enumerations);
		int _la;
		try {
			setState(199);
			_errHandler.sync(this);
			switch (_input.LA(1)) {
				case STRING:
					enterOuterAlt(_localctx, 1);
				{
					setState(183);
					match(STRING);
					setState(188);
					_errHandler.sync(this);
					_la = _input.LA(1);
					while (_la == COMMA) {
						{
							{
								setState(184);
								match(COMMA);
								setState(185);
								match(STRING);
							}
						}
						setState(190);
						_errHandler.sync(this);
						_la = _input.LA(1);
					}
				}
				break;
				case NUMBER:
					enterOuterAlt(_localctx, 2);
				{
					setState(191);
					match(NUMBER);
					setState(196);
					_errHandler.sync(this);
					_la = _input.LA(1);
					while (_la == COMMA) {
						{
							{
								setState(192);
								match(COMMA);
								setState(193);
								match(NUMBER);
							}
						}
						setState(198);
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
		enterRule(_localctx, 24, RULE_allSymbol);
		try {
			setState(219);
			_errHandler.sync(this);
			switch (_input.LA(1)) {
				case IDENTIFIER:
					enterOuterAlt(_localctx, 1);
				{
					setState(201);
					match(IDENTIFIER);
				}
				break;
				case STRING:
					enterOuterAlt(_localctx, 2);
				{
					setState(202);
					match(STRING);
				}
				break;
				case NUMBER:
					enterOuterAlt(_localctx, 3);
				{
					setState(203);
					match(NUMBER);
				}
				break;
				case INTEGER:
					enterOuterAlt(_localctx, 4);
				{
					setState(204);
					match(INTEGER);
				}
				break;
				case DOLLAR:
					enterOuterAlt(_localctx, 5);
				{
					setState(205);
					match(DOLLAR);
				}
				break;
				case PLUS:
					enterOuterAlt(_localctx, 6);
				{
					setState(206);
					match(PLUS);
				}
				break;
				case MINUS:
					enterOuterAlt(_localctx, 7);
				{
					setState(207);
					match(MINUS);
				}
				break;
				case COLON:
					enterOuterAlt(_localctx, 8);
				{
					setState(208);
					match(COLON);
				}
				break;
				case COMMA:
					enterOuterAlt(_localctx, 9);
				{
					setState(209);
					match(COMMA);
				}
				break;
				case DOT:
					enterOuterAlt(_localctx, 10);
				{
					setState(210);
					match(DOT);
				}
				break;
				case L_BRACKET:
					enterOuterAlt(_localctx, 11);
				{
					setState(211);
					match(L_BRACKET);
				}
				break;
				case R_BRACKET:
					enterOuterAlt(_localctx, 12);
				{
					setState(212);
					match(R_BRACKET);
				}
				break;
				case LS_BRACKET:
					enterOuterAlt(_localctx, 13);
				{
					setState(213);
					match(LS_BRACKET);
				}
				break;
				case RS_BRACKET:
					enterOuterAlt(_localctx, 14);
				{
					setState(214);
					match(RS_BRACKET);
				}
				break;
				case L_BRACE:
					enterOuterAlt(_localctx, 15);
				{
					setState(215);
					match(L_BRACE);
				}
				break;
				case R_BRACE:
					enterOuterAlt(_localctx, 16);
				{
					setState(216);
					match(R_BRACE);
				}
				break;
				case SYMBOL:
					enterOuterAlt(_localctx, 17);
				{
					setState(217);
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
					setState(218);
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
		enterRule(_localctx, 26, RULE_dataType);
		int _la;
		try {
			enterOuterAlt(_localctx, 1);
			{
				setState(221);
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
			if (listener instanceof FunctionParserListener) ((FunctionParserListener) listener).enterRoot(this);
		}

		@Override
		public void exitRule(ParseTreeListener listener) {
			if (listener instanceof FunctionParserListener) ((FunctionParserListener) listener).exitRoot(this);
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
			if (listener instanceof FunctionParserListener) ((FunctionParserListener) listener).enterCategory(this);
		}

		@Override
		public void exitRule(ParseTreeListener listener) {
			if (listener instanceof FunctionParserListener) ((FunctionParserListener) listener).exitCategory(this);
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
			if (listener instanceof FunctionParserListener) ((FunctionParserListener) listener).enterFunctionItem(this);
		}

		@Override
		public void exitRule(ParseTreeListener listener) {
			if (listener instanceof FunctionParserListener) ((FunctionParserListener) listener).exitFunctionItem(this);
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

		public TerminalNode EQUAL() {
			return getToken(FunctionParser.EQUAL, 0);
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
			if (listener instanceof FunctionParserListener) ((FunctionParserListener) listener).enterFunctionAlias(this);
		}

		@Override
		public void exitRule(ParseTreeListener listener) {
			if (listener instanceof FunctionParserListener) ((FunctionParserListener) listener).exitFunctionAlias(this);
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
			if (listener instanceof FunctionParserListener) ((FunctionParserListener) listener).enterFuncDefine(this);
		}

		@Override
		public void exitRule(ParseTreeListener listener) {
			if (listener instanceof FunctionParserListener) ((FunctionParserListener) listener).exitFuncDefine(this);
		}
	}

	public static class FuncImplementContext extends ParserRuleContext {
		public FuncImplementContext(ParserRuleContext parent, int invokingState) {
			super(parent, invokingState);
		}

		public TerminalNode TRANSFER_L() {
			return getToken(FunctionParser.TRANSFER_L, 0);
		}

		public FuncImplementBodyContext funcImplementBody() {
			return getRuleContext(FuncImplementBodyContext.class, 0);
		}

		public TerminalNode NEWLINE() {
			return getToken(FunctionParser.NEWLINE, 0);
		}

		public TerminalNode TRANSFER_S() {
			return getToken(FunctionParser.TRANSFER_S, 0);
		}

		public TerminalNode S_BLOCK() {
			return getToken(FunctionParser.S_BLOCK, 0);
		}

		public TerminalNode TRANSFER_D() {
			return getToken(FunctionParser.TRANSFER_D, 0);
		}

		public TerminalNode D_BLOCK() {
			return getToken(FunctionParser.D_BLOCK, 0);
		}

		@Override
		public int getRuleIndex() {
			return RULE_funcImplement;
		}

		@Override
		public void enterRule(ParseTreeListener listener) {
			if (listener instanceof FunctionParserListener) ((FunctionParserListener) listener).enterFuncImplement(this);
		}

		@Override
		public void exitRule(ParseTreeListener listener) {
			if (listener instanceof FunctionParserListener) ((FunctionParserListener) listener).exitFuncImplement(this);
		}
	}

	public static class FuncImplementBodyContext extends ParserRuleContext {
		public FuncImplementBodyContext(ParserRuleContext parent, int invokingState) {
			super(parent, invokingState);
		}

		public List<TerminalNode> ANY_L() {
			return getTokens(FunctionParser.ANY_L);
		}

		public TerminalNode ANY_L(int i) {
			return getToken(FunctionParser.ANY_L, i);
		}

		public List<TerminalNode> WS_L() {
			return getTokens(FunctionParser.WS_L);
		}

		public TerminalNode WS_L(int i) {
			return getToken(FunctionParser.WS_L, i);
		}

		public List<TerminalNode> ANY_S() {
			return getTokens(FunctionParser.ANY_S);
		}

		public TerminalNode ANY_S(int i) {
			return getToken(FunctionParser.ANY_S, i);
		}

		public List<TerminalNode> WS_S() {
			return getTokens(FunctionParser.WS_S);
		}

		public TerminalNode WS_S(int i) {
			return getToken(FunctionParser.WS_S, i);
		}

		public List<TerminalNode> ANY_D() {
			return getTokens(FunctionParser.ANY_D);
		}

		public TerminalNode ANY_D(int i) {
			return getToken(FunctionParser.ANY_D, i);
		}

		public List<TerminalNode> WS_D() {
			return getTokens(FunctionParser.WS_D);
		}

		public TerminalNode WS_D(int i) {
			return getToken(FunctionParser.WS_D, i);
		}

		@Override
		public int getRuleIndex() {
			return RULE_funcImplementBody;
		}

		@Override
		public void enterRule(ParseTreeListener listener) {
			if (listener instanceof FunctionParserListener) ((FunctionParserListener) listener).enterFuncImplementBody(this);
		}

		@Override
		public void exitRule(ParseTreeListener listener) {
			if (listener instanceof FunctionParserListener) ((FunctionParserListener) listener).exitFuncImplementBody(this);
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
			if (listener instanceof FunctionParserListener) ((FunctionParserListener) listener).enterFuncArgs(this);
		}

		@Override
		public void exitRule(ParseTreeListener listener) {
			if (listener instanceof FunctionParserListener) ((FunctionParserListener) listener).exitFuncArgs(this);
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
			if (listener instanceof FunctionParserListener) ((FunctionParserListener) listener).enterFuncArg(this);
		}

		@Override
		public void exitRule(ParseTreeListener listener) {
			if (listener instanceof FunctionParserListener) ((FunctionParserListener) listener).exitFuncArg(this);
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
			if (listener instanceof FunctionParserListener) ((FunctionParserListener) listener).enterArgName(this);
		}

		@Override
		public void exitRule(ParseTreeListener listener) {
			if (listener instanceof FunctionParserListener) ((FunctionParserListener) listener).exitArgName(this);
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
			if (listener instanceof FunctionParserListener) ((FunctionParserListener) listener).enterArgRef(this);
		}

		@Override
		public void exitRule(ParseTreeListener listener) {
			if (listener instanceof FunctionParserListener) ((FunctionParserListener) listener).exitArgRef(this);
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
			if (listener instanceof FunctionParserListener) ((FunctionParserListener) listener).enterEnumerations(this);
		}

		@Override
		public void exitRule(ParseTreeListener listener) {
			if (listener instanceof FunctionParserListener) ((FunctionParserListener) listener).exitEnumerations(this);
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
			if (listener instanceof FunctionParserListener) ((FunctionParserListener) listener).enterAllSymbol(this);
		}

		@Override
		public void exitRule(ParseTreeListener listener) {
			if (listener instanceof FunctionParserListener) ((FunctionParserListener) listener).exitAllSymbol(this);
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
			if (listener instanceof FunctionParserListener) ((FunctionParserListener) listener).enterDataType(this);
		}

		@Override
		public void exitRule(ParseTreeListener listener) {
			if (listener instanceof FunctionParserListener) ((FunctionParserListener) listener).exitDataType(this);
		}
	}
}