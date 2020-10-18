// Generated from C:/Users/suntr/Project/pms/module/module-formula/src/main/java/org/sunt/formula/function/parser\Function.g4 by ANTLR 4.8
package org.sunt.formula.function.parser;

import org.antlr.v4.runtime.*;
import org.antlr.v4.runtime.atn.ATN;
import org.antlr.v4.runtime.atn.ATNDeserializer;
import org.antlr.v4.runtime.atn.LexerATNSimulator;
import org.antlr.v4.runtime.atn.PredictionContextCache;
import org.antlr.v4.runtime.dfa.DFA;

@SuppressWarnings({"all", "warnings", "unchecked", "unused", "cast"})
public class FunctionLexer extends Lexer {
	public static final int
			T__0 = 1, VARARG = 2, ALIAS = 3, DT_STRING = 4, DT_DECIMAL = 5, DT_INTEGER = 6, DT_BOOLEAN = 7,
			DT_DATETIME = 8, DT_DATE = 9, DT_TIME = 10, DT_ANY = 11, TYPE = 12, PLUS = 13, MINUS = 14,
			COLON = 15, COMMA = 16, DOT = 17, DOLLAR = 18, TRANSFER = 19, S_BLOCK = 20, D_BLOCK = 21,
			L_BRACKET = 22, R_BRACKET = 23, LS_BRACKET = 24, RS_BRACKET = 25, L_BRACE = 26,
			R_BRACE = 27, SYMBOL = 28, INTEGER = 29, NUMBER = 30, STRING = 31, IDENTIFIER = 32,
			NEW_LINE = 33, WS = 34, LINE_COMMENT = 35, DESCRIPTION = 36;
	public static final String[] ruleNames = makeRuleNames();
	/**
	 * @deprecated Use {@link #VOCABULARY} instead.
	 */
	@Deprecated
	public static final String[] tokenNames;
	public static final String _serializedATN =
			"\3\u608b\ua72a\u8133\ub9ed\u417c\u3be7\u7786\u5964\2&\u01af\b\1\4\2\t" +
					"\2\4\3\t\3\4\4\t\4\4\5\t\5\4\6\t\6\4\7\t\7\4\b\t\b\4\t\t\t\4\n\t\n\4\13" +
					"\t\13\4\f\t\f\4\r\t\r\4\16\t\16\4\17\t\17\4\20\t\20\4\21\t\21\4\22\t\22" +
					"\4\23\t\23\4\24\t\24\4\25\t\25\4\26\t\26\4\27\t\27\4\30\t\30\4\31\t\31" +
					"\4\32\t\32\4\33\t\33\4\34\t\34\4\35\t\35\4\36\t\36\4\37\t\37\4 \t \4!" +
					"\t!\4\"\t\"\4#\t#\4$\t$\4%\t%\4&\t&\4\'\t\'\4(\t(\4)\t)\4*\t*\4+\t+\4" +
					",\t,\4-\t-\4.\t.\4/\t/\4\60\t\60\4\61\t\61\4\62\t\62\4\63\t\63\4\64\t" +
					"\64\4\65\t\65\4\66\t\66\4\67\t\67\48\t8\49\t9\4:\t:\4;\t;\4<\t<\4=\t=" +
					"\4>\t>\4?\t?\4@\t@\4A\tA\4B\tB\4C\tC\4D\tD\4E\tE\4F\tF\4G\tG\3\2\3\2\3" +
					"\3\3\3\3\3\3\3\3\3\3\3\3\3\3\4\3\4\3\4\3\4\3\4\3\4\3\5\3\5\3\5\3\5\3\5" +
					"\3\5\3\5\3\6\3\6\3\6\3\6\3\6\3\6\3\6\3\6\3\6\3\6\3\6\3\6\3\6\3\6\3\6\5" +
					"\6\u00b5\n\6\3\7\3\7\3\7\3\7\3\7\3\7\3\7\3\7\3\b\3\b\3\b\3\b\3\b\3\b\3" +
					"\b\3\b\3\b\3\b\3\b\3\b\3\b\5\b\u00cc\n\b\3\t\3\t\3\t\3\t\3\t\3\t\3\t\3" +
					"\t\3\t\3\n\3\n\3\n\3\n\3\n\3\13\3\13\3\13\3\13\3\13\3\f\3\f\3\f\3\f\3" +
					"\r\3\r\3\r\3\r\3\r\3\16\3\16\3\17\3\17\3\20\3\20\3\21\3\21\3\22\3\22\3" +
					"\23\3\23\3\24\3\24\3\24\3\25\3\25\3\25\3\25\3\26\3\26\3\26\3\26\3\27\3" +
					"\27\3\30\3\30\3\31\3\31\3\32\3\32\3\33\3\33\3\34\3\34\3\35\3\35\3\36\6" +
					"\36\u0110\n\36\r\36\16\36\u0111\3\37\6\37\u0115\n\37\r\37\16\37\u0116" +
					"\3\37\3\37\6\37\u011b\n\37\r\37\16\37\u011c\3 \3 \3 \7 \u0122\n \f \16" +
					" \u0125\13 \3 \3 \3 \3 \7 \u012b\n \f \16 \u012e\13 \3 \5 \u0131\n \3" +
					"!\6!\u0134\n!\r!\16!\u0135\3\"\6\"\u0139\n\"\r\"\16\"\u013a\3#\6#\u013e" +
					"\n#\r#\16#\u013f\3#\3#\3$\3$\3$\3$\7$\u0148\n$\f$\16$\u014b\13$\3$\3$" +
					"\3%\3%\3%\3%\3%\7%\u0154\n%\f%\16%\u0157\13%\3%\3%\3%\3&\3&\3\'\3\'\5" +
					"\'\u0160\n\'\3\'\5\'\u0163\n\'\3(\3(\3(\3(\5(\u0169\n(\3)\3)\3)\3)\5)" +
					"\u016f\n)\3*\3*\3+\3+\3,\3,\3-\6-\u0178\n-\r-\16-\u0179\3.\3.\3/\3/\3" +
					"\60\3\60\3\61\3\61\3\62\3\62\3\63\3\63\3\64\3\64\3\65\3\65\3\66\3\66\3" +
					"\67\3\67\38\38\39\39\3:\3:\3;\3;\3<\3<\3=\3=\3>\3>\3?\3?\3@\3@\3A\3A\3" +
					"B\3B\3C\3C\3D\3D\3E\3E\3F\3F\3G\3G\6\u0123\u012c\u0155\u0179\2H\3\3\5" +
					"\4\7\5\t\6\13\7\r\b\17\t\21\n\23\13\25\f\27\r\31\16\33\17\35\20\37\21" +
					"!\22#\23%\24\'\25)\26+\27-\30/\31\61\32\63\33\65\34\67\359\36;\37= ?!" +
					"A\"C#E$G%I&K\2M\2O\2Q\2S\2U\2W\2Y\2[\2]\2_\2a\2c\2e\2g\2i\2k\2m\2o\2q" +
					"\2s\2u\2w\2y\2{\2}\2\177\2\u0081\2\u0083\2\u0085\2\u0087\2\u0089\2\u008b" +
					"\2\u008d\2\3\2#\n\2*,..\60\61<=]]__}}\177\177\7\2\62;C\\aac|\u4e02\u9fa7" +
					"\4\2\f\f\17\17\5\2\13\13\16\16\"\"\3\2\62;\5\2C\\aac|\3\2\u4e02\u9fa7" +
					"\4\2CCcc\4\2DDdd\4\2EEee\4\2FFff\4\2GGgg\4\2HHhh\4\2IIii\4\2JJjj\4\2K" +
					"Kkk\4\2LLll\4\2MMmm\4\2NNnn\4\2OOoo\4\2PPpp\4\2QQqq\4\2RRrr\4\2SSss\4" +
					"\2TTtt\4\2UUuu\4\2VVvv\4\2WWww\4\2XXxx\4\2YYyy\4\2ZZzz\4\2[[{{\4\2\\\\" +
					"||\2\u01a0\2\3\3\2\2\2\2\5\3\2\2\2\2\7\3\2\2\2\2\t\3\2\2\2\2\13\3\2\2" +
					"\2\2\r\3\2\2\2\2\17\3\2\2\2\2\21\3\2\2\2\2\23\3\2\2\2\2\25\3\2\2\2\2\27" +
					"\3\2\2\2\2\31\3\2\2\2\2\33\3\2\2\2\2\35\3\2\2\2\2\37\3\2\2\2\2!\3\2\2" +
					"\2\2#\3\2\2\2\2%\3\2\2\2\2\'\3\2\2\2\2)\3\2\2\2\2+\3\2\2\2\2-\3\2\2\2" +
					"\2/\3\2\2\2\2\61\3\2\2\2\2\63\3\2\2\2\2\65\3\2\2\2\2\67\3\2\2\2\29\3\2" +
					"\2\2\2;\3\2\2\2\2=\3\2\2\2\2?\3\2\2\2\2A\3\2\2\2\2C\3\2\2\2\2E\3\2\2\2" +
					"\2G\3\2\2\2\2I\3\2\2\2\3\u008f\3\2\2\2\5\u0091\3\2\2\2\7\u0098\3\2\2\2" +
					"\t\u009e\3\2\2\2\13\u00b4\3\2\2\2\r\u00b6\3\2\2\2\17\u00cb\3\2\2\2\21" +
					"\u00cd\3\2\2\2\23\u00d6\3\2\2\2\25\u00db\3\2\2\2\27\u00e0\3\2\2\2\31\u00e4" +
					"\3\2\2\2\33\u00e9\3\2\2\2\35\u00eb\3\2\2\2\37\u00ed\3\2\2\2!\u00ef\3\2" +
					"\2\2#\u00f1\3\2\2\2%\u00f3\3\2\2\2\'\u00f5\3\2\2\2)\u00f8\3\2\2\2+\u00fc" +
					"\3\2\2\2-\u0100\3\2\2\2/\u0102\3\2\2\2\61\u0104\3\2\2\2\63\u0106\3\2\2" +
					"\2\65\u0108\3\2\2\2\67\u010a\3\2\2\29\u010c\3\2\2\2;\u010f\3\2\2\2=\u0114" +
					"\3\2\2\2?\u0130\3\2\2\2A\u0133\3\2\2\2C\u0138\3\2\2\2E\u013d\3\2\2\2G" +
					"\u0143\3\2\2\2I\u014e\3\2\2\2K\u015b\3\2\2\2M\u0162\3\2\2\2O\u0168\3\2" +
					"\2\2Q\u016e\3\2\2\2S\u0170\3\2\2\2U\u0172\3\2\2\2W\u0174\3\2\2\2Y\u0177" +
					"\3\2\2\2[\u017b\3\2\2\2]\u017d\3\2\2\2_\u017f\3\2\2\2a\u0181\3\2\2\2c" +
					"\u0183\3\2\2\2e\u0185\3\2\2\2g\u0187\3\2\2\2i\u0189\3\2\2\2k\u018b\3\2" +
					"\2\2m\u018d\3\2\2\2o\u018f\3\2\2\2q\u0191\3\2\2\2s\u0193\3\2\2\2u\u0195" +
					"\3\2\2\2w\u0197\3\2\2\2y\u0199\3\2\2\2{\u019b\3\2\2\2}\u019d\3\2\2\2\177" +
					"\u019f\3\2\2\2\u0081\u01a1\3\2\2\2\u0083\u01a3\3\2\2\2\u0085\u01a5\3\2" +
					"\2\2\u0087\u01a7\3\2\2\2\u0089\u01a9\3\2\2\2\u008b\u01ab\3\2\2\2\u008d" +
					"\u01ad\3\2\2\2\u008f\u0090\7?\2\2\u0090\4\3\2\2\2\u0091\u0092\5\u0085" +
					"C\2\u0092\u0093\5[.\2\u0093\u0094\5}?\2\u0094\u0095\5[.\2\u0095\u0096" +
					"\5}?\2\u0096\u0097\5g\64\2\u0097\6\3\2\2\2\u0098\u0099\5[.\2\u0099\u009a" +
					"\5q9\2\u009a\u009b\5k\66\2\u009b\u009c\5[.\2\u009c\u009d\5\177@\2\u009d" +
					"\b\3\2\2\2\u009e\u009f\5\177@\2\u009f\u00a0\5\u0081A\2\u00a0\u00a1\5}" +
					"?\2\u00a1\u00a2\5k\66\2\u00a2\u00a3\5u;\2\u00a3\u00a4\5g\64\2\u00a4\n" +
					"\3\2\2\2\u00a5\u00a6\5a\61\2\u00a6\u00a7\5c\62\2\u00a7\u00a8\5_\60\2\u00a8" +
					"\u00a9\5k\66\2\u00a9\u00aa\5s:\2\u00aa\u00ab\5[.\2\u00ab\u00ac\5q9\2\u00ac" +
					"\u00b5\3\2\2\2\u00ad\u00ae\5a\61\2\u00ae\u00af\5w<\2\u00af\u00b0\5\u0083" +
					"B\2\u00b0\u00b1\5]/\2\u00b1\u00b2\5q9\2\u00b2\u00b3\5c\62\2\u00b3\u00b5" +
					"\3\2\2\2\u00b4\u00a5\3\2\2\2\u00b4\u00ad\3\2\2\2\u00b5\f\3\2\2\2\u00b6" +
					"\u00b7\5k\66\2\u00b7\u00b8\5u;\2\u00b8\u00b9\5\u0081A\2\u00b9\u00ba\5" +
					"c\62\2\u00ba\u00bb\5g\64\2\u00bb\u00bc\5c\62\2\u00bc\u00bd\5}?\2\u00bd" +
					"\16\3\2\2\2\u00be\u00bf\5]/\2\u00bf\u00c0\5w<\2\u00c0\u00c1\5w<\2\u00c1" +
					"\u00c2\5q9\2\u00c2\u00c3\5c\62\2\u00c3\u00c4\5[.\2\u00c4\u00c5\5u;\2\u00c5" +
					"\u00cc\3\2\2\2\u00c6\u00c7\5]/\2\u00c7\u00c8\5w<\2\u00c8\u00c9\5w<\2\u00c9" +
					"\u00ca\5q9\2\u00ca\u00cc\3\2\2\2\u00cb\u00be\3\2\2\2\u00cb\u00c6\3\2\2" +
					"\2\u00cc\20\3\2\2\2\u00cd\u00ce\5a\61\2\u00ce\u00cf\5[.\2\u00cf\u00d0" +
					"\5\u0081A\2\u00d0\u00d1\5c\62\2\u00d1\u00d2\5\u0081A\2\u00d2\u00d3\5k" +
					"\66\2\u00d3\u00d4\5s:\2\u00d4\u00d5\5c\62\2\u00d5\22\3\2\2\2\u00d6\u00d7" +
					"\5a\61\2\u00d7\u00d8\5[.\2\u00d8\u00d9\5\u0081A\2\u00d9\u00da\5c\62\2" +
					"\u00da\24\3\2\2\2\u00db\u00dc\5\u0081A\2\u00dc\u00dd\5k\66\2\u00dd\u00de" +
					"\5s:\2\u00de\u00df\5c\62\2\u00df\26\3\2\2\2\u00e0\u00e1\5[.\2\u00e1\u00e2" +
					"\5u;\2\u00e2\u00e3\5\u008bF\2\u00e3\30\3\2\2\2\u00e4\u00e5\5\u0081A\2" +
					"\u00e5\u00e6\5\u008bF\2\u00e6\u00e7\5y=\2\u00e7\u00e8\5c\62\2\u00e8\32" +
					"\3\2\2\2\u00e9\u00ea\7-\2\2\u00ea\34\3\2\2\2\u00eb\u00ec\7/\2\2\u00ec" +
					"\36\3\2\2\2\u00ed\u00ee\7<\2\2\u00ee \3\2\2\2\u00ef\u00f0\7.\2\2\u00f0" +
					"\"\3\2\2\2\u00f1\u00f2\7\60\2\2\u00f2$\3\2\2\2\u00f3\u00f4\7&\2\2\u00f4" +
					"&\3\2\2\2\u00f5\u00f6\7/\2\2\u00f6\u00f7\7@\2\2\u00f7(\3\2\2\2\u00f8\u00f9" +
					"\7)\2\2\u00f9\u00fa\7)\2\2\u00fa\u00fb\7)\2\2\u00fb*\3\2\2\2\u00fc\u00fd" +
					"\7$\2\2\u00fd\u00fe\7$\2\2\u00fe\u00ff\7$\2\2\u00ff,\3\2\2\2\u0100\u0101" +
					"\7*\2\2\u0101.\3\2\2\2\u0102\u0103\7+\2\2\u0103\60\3\2\2\2\u0104\u0105" +
					"\7]\2\2\u0105\62\3\2\2\2\u0106\u0107\7_\2\2\u0107\64\3\2\2\2\u0108\u0109" +
					"\7}\2\2\u0109\66\3\2\2\2\u010a\u010b\7\177\2\2\u010b8\3\2\2\2\u010c\u010d" +
					"\t\2\2\2\u010d:\3\2\2\2\u010e\u0110\5S*\2\u010f\u010e\3\2\2\2\u0110\u0111" +
					"\3\2\2\2\u0111\u010f\3\2\2\2\u0111\u0112\3\2\2\2\u0112<\3\2\2\2\u0113" +
					"\u0115\5S*\2\u0114\u0113\3\2\2\2\u0115\u0116\3\2\2\2\u0116\u0114\3\2\2" +
					"\2\u0116\u0117\3\2\2\2\u0117\u0118\3\2\2\2\u0118\u011a\7\60\2\2\u0119" +
					"\u011b\5S*\2\u011a\u0119\3\2\2\2\u011b\u011c\3\2\2\2\u011c\u011a\3\2\2" +
					"\2\u011c\u011d\3\2\2\2\u011d>\3\2\2\2\u011e\u0123\7$\2\2\u011f\u0122\5" +
					"O(\2\u0120\u0122\13\2\2\2\u0121\u011f\3\2\2\2\u0121\u0120\3\2\2\2\u0122" +
					"\u0125\3\2\2\2\u0123\u0124\3\2\2\2\u0123\u0121\3\2\2\2\u0124\u0126\3\2" +
					"\2\2\u0125\u0123\3\2\2\2\u0126\u0131\7$\2\2\u0127\u012c\7)\2\2\u0128\u012b" +
					"\5Q)\2\u0129\u012b\13\2\2\2\u012a\u0128\3\2\2\2\u012a\u0129\3\2\2\2\u012b" +
					"\u012e\3\2\2\2\u012c\u012d\3\2\2\2\u012c\u012a\3\2\2\2\u012d\u012f\3\2" +
					"\2\2\u012e\u012c\3\2\2\2\u012f\u0131\7)\2\2\u0130\u011e\3\2\2\2\u0130" +
					"\u0127\3\2\2\2\u0131@\3\2\2\2\u0132\u0134\t\3\2\2\u0133\u0132\3\2\2\2" +
					"\u0134\u0135\3\2\2\2\u0135\u0133\3\2\2\2\u0135\u0136\3\2\2\2\u0136B\3" +
					"\2\2\2\u0137\u0139\5M\'\2\u0138\u0137\3\2\2\2\u0139\u013a\3\2\2\2\u013a" +
					"\u0138\3\2\2\2\u013a\u013b\3\2\2\2\u013bD\3\2\2\2\u013c\u013e\5K&\2\u013d" +
					"\u013c\3\2\2\2\u013e\u013f\3\2\2\2\u013f\u013d\3\2\2\2\u013f\u0140\3\2" +
					"\2\2\u0140\u0141\3\2\2\2\u0141\u0142\b#\2\2\u0142F\3\2\2\2\u0143\u0144" +
					"\7\61\2\2\u0144\u0145\7\61\2\2\u0145\u0149\3\2\2\2\u0146\u0148\n\4\2\2" +
					"\u0147\u0146\3\2\2\2\u0148\u014b\3\2\2\2\u0149\u0147\3\2\2\2\u0149\u014a" +
					"\3\2\2\2\u014a\u014c\3\2\2\2\u014b\u0149\3\2\2\2\u014c\u014d\b$\2\2\u014d" +
					"H\3\2\2\2\u014e\u014f\7\61\2\2\u014f\u0150\7,\2\2\u0150\u0151\7,\2\2\u0151" +
					"\u0155\3\2\2\2\u0152\u0154\13\2\2\2\u0153\u0152\3\2\2\2\u0154\u0157\3" +
					"\2\2\2\u0155\u0156\3\2\2\2\u0155\u0153\3\2\2\2\u0156\u0158\3\2\2\2\u0157" +
					"\u0155\3\2\2\2\u0158\u0159\7,\2\2\u0159\u015a\7\61\2\2\u015aJ\3\2\2\2" +
					"\u015b\u015c\t\5\2\2\u015cL\3\2\2\2\u015d\u0163\7\17\2\2\u015e\u0160\7" +
					"\17\2\2\u015f\u015e\3\2\2\2\u015f\u0160\3\2\2\2\u0160\u0161\3\2\2\2\u0161" +
					"\u0163\7\f\2\2\u0162\u015d\3\2\2\2\u0162\u015f\3\2\2\2\u0163N\3\2\2\2" +
					"\u0164\u0165\7^\2\2\u0165\u0169\7$\2\2\u0166\u0167\7^\2\2\u0167\u0169" +
					"\7^\2\2\u0168\u0164\3\2\2\2\u0168\u0166\3\2\2\2\u0169P\3\2\2\2\u016a\u016b" +
					"\7^\2\2\u016b\u016f\7)\2\2\u016c\u016d\7^\2\2\u016d\u016f\7^\2\2\u016e" +
					"\u016a\3\2\2\2\u016e\u016c\3\2\2\2\u016fR\3\2\2\2\u0170\u0171\t\6\2\2" +
					"\u0171T\3\2\2\2\u0172\u0173\t\7\2\2\u0173V\3\2\2\2\u0174\u0175\t\b\2\2" +
					"\u0175X\3\2\2\2\u0176\u0178\13\2\2\2\u0177\u0176\3\2\2\2\u0178\u0179\3" +
					"\2\2\2\u0179\u017a\3\2\2\2\u0179\u0177\3\2\2\2\u017aZ\3\2\2\2\u017b\u017c" +
					"\t\t\2\2\u017c\\\3\2\2\2\u017d\u017e\t\n\2\2\u017e^\3\2\2\2\u017f\u0180" +
					"\t\13\2\2\u0180`\3\2\2\2\u0181\u0182\t\f\2\2\u0182b\3\2\2\2\u0183\u0184" +
					"\t\r\2\2\u0184d\3\2\2\2\u0185\u0186\t\16\2\2\u0186f\3\2\2\2\u0187\u0188" +
					"\t\17\2\2\u0188h\3\2\2\2\u0189\u018a\t\20\2\2\u018aj\3\2\2\2\u018b\u018c" +
					"\t\21\2\2\u018cl\3\2\2\2\u018d\u018e\t\22\2\2\u018en\3\2\2\2\u018f\u0190" +
					"\t\23\2\2\u0190p\3\2\2\2\u0191\u0192\t\24\2\2\u0192r\3\2\2\2\u0193\u0194" +
					"\t\25\2\2\u0194t\3\2\2\2\u0195\u0196\t\26\2\2\u0196v\3\2\2\2\u0197\u0198" +
					"\t\27\2\2\u0198x\3\2\2\2\u0199\u019a\t\30\2\2\u019az\3\2\2\2\u019b\u019c" +
					"\t\31\2\2\u019c|\3\2\2\2\u019d\u019e\t\32\2\2\u019e~\3\2\2\2\u019f\u01a0" +
					"\t\33\2\2\u01a0\u0080\3\2\2\2\u01a1\u01a2\t\34\2\2\u01a2\u0082\3\2\2\2" +
					"\u01a3\u01a4\t\35\2\2\u01a4\u0084\3\2\2\2\u01a5\u01a6\t\36\2\2\u01a6\u0086" +
					"\3\2\2\2\u01a7\u01a8\t\37\2\2\u01a8\u0088\3\2\2\2\u01a9\u01aa\t \2\2\u01aa" +
					"\u008a\3\2\2\2\u01ab\u01ac\t!\2\2\u01ac\u008c\3\2\2\2\u01ad\u01ae\t\"" +
					"\2\2\u01ae\u008e\3\2\2\2\27\2\u00b4\u00cb\u0111\u0116\u011c\u0121\u0123" +
					"\u012a\u012c\u0130\u0135\u013a\u013f\u0149\u0155\u015f\u0162\u0168\u016e" +
					"\u0179\3\b\2\2";
	public static final ATN _ATN =
			new ATNDeserializer().deserialize(_serializedATN.toCharArray());
	protected static final DFA[] _decisionToDFA;
	protected static final PredictionContextCache _sharedContextCache =
			new PredictionContextCache();
	private static final String[] _LITERAL_NAMES = makeLiteralNames();
	private static final String[] _SYMBOLIC_NAMES = makeSymbolicNames();
	public static final Vocabulary VOCABULARY = new VocabularyImpl(_LITERAL_NAMES, _SYMBOLIC_NAMES);
	public static String[] channelNames = {
			"DEFAULT_TOKEN_CHANNEL", "HIDDEN"
	};
	public static String[] modeNames = {
			"DEFAULT_MODE"
	};

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

	public FunctionLexer(CharStream input) {
		super(input);
		_interp = new LexerATNSimulator(this, _ATN, _decisionToDFA, _sharedContextCache);
	}

	private static String[] makeRuleNames() {
		return new String[]{
				"T__0", "VARARG", "ALIAS", "DT_STRING", "DT_DECIMAL", "DT_INTEGER", "DT_BOOLEAN",
				"DT_DATETIME", "DT_DATE", "DT_TIME", "DT_ANY", "TYPE", "PLUS", "MINUS",
				"COLON", "COMMA", "DOT", "DOLLAR", "TRANSFER", "S_BLOCK", "D_BLOCK",
				"L_BRACKET", "R_BRACKET", "LS_BRACKET", "RS_BRACKET", "L_BRACE", "R_BRACE",
				"SYMBOL", "INTEGER", "NUMBER", "STRING", "IDENTIFIER", "NEW_LINE", "WS",
				"LINE_COMMENT", "DESCRIPTION", "BLANK", "NL", "ESC_DQUOTE", "ESC_SQUOTE",
				"DIGIT", "ALPHA", "CHINESE", "ANY", "A", "B", "C", "D", "E", "F", "G",
				"H", "I", "J", "K", "L", "M", "N", "O", "P", "Q", "R", "S", "T", "U",
				"V", "W", "X", "Y", "Z"
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
	public String[] getChannelNames() {
		return channelNames;
	}

	@Override
	public String[] getModeNames() {
		return modeNames;
	}

	@Override
	public ATN getATN() {
		return _ATN;
	}
}