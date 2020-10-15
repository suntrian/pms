// Generated from C:/Users/suntr/Project/pms/module/module-formula/src/main/java/org/sunt/formula/parser\Formula.g4 by ANTLR 4.8
package org.sunt.formula.parser;

import org.antlr.v4.runtime.*;
import org.antlr.v4.runtime.atn.ATN;
import org.antlr.v4.runtime.atn.ATNDeserializer;
import org.antlr.v4.runtime.atn.LexerATNSimulator;
import org.antlr.v4.runtime.atn.PredictionContextCache;
import org.antlr.v4.runtime.dfa.DFA;

@SuppressWarnings({"all", "warnings", "unchecked", "unused", "cast"})
public class FormulaLexer extends Lexer {
	public static final int
			L_PARENTHESES = 1, R_PARENTHESES = 2, L_BRACE = 3, R_BRACE = 4, COMMA = 5, COLON = 6,
			MUL = 7, DIV = 8, PLUS = 9, MINUS = 10, POWER = 11, MOD = 12, GREATER = 13, GREATER_EQUAL = 14,
			EQUAL = 15, LESS = 16, LESS_EQUAL = 17, NOT_EQUAL = 18, IF = 19, LIKE = 20, IN = 21,
			NOT = 22, AND = 23, OR = 24, XOR = 25, CASE = 26, WHEN = 27, THEN = 28, ELSE = 29, END = 30,
			BOOL = 31, NULL = 32, STRING = 33, INTEGER = 34, FLOAT = 35, COLUMN_ID = 36, IDENTITY = 37,
			COLUMN_NAME = 38, WS = 39, LINE_COMMENT = 40, BLOCK_COMMENT = 41;
	public static final String[] ruleNames = makeRuleNames();
	/**
	 * @deprecated Use {@link #VOCABULARY} instead.
	 */
	@Deprecated
	public static final String[] tokenNames;
	public static final String _serializedATN =
			"\3\u608b\ua72a\u8133\ub9ed\u417c\u3be7\u7786\u5964\2+\u01cf\b\1\4\2\t" +
					"\2\4\3\t\3\4\4\t\4\4\5\t\5\4\6\t\6\4\7\t\7\4\b\t\b\4\t\t\t\4\n\t\n\4\13" +
					"\t\13\4\f\t\f\4\r\t\r\4\16\t\16\4\17\t\17\4\20\t\20\4\21\t\21\4\22\t\22" +
					"\4\23\t\23\4\24\t\24\4\25\t\25\4\26\t\26\4\27\t\27\4\30\t\30\4\31\t\31" +
					"\4\32\t\32\4\33\t\33\4\34\t\34\4\35\t\35\4\36\t\36\4\37\t\37\4 \t \4!" +
					"\t!\4\"\t\"\4#\t#\4$\t$\4%\t%\4&\t&\4\'\t\'\4(\t(\4)\t)\4*\t*\4+\t+\4" +
					",\t,\4-\t-\4.\t.\4/\t/\4\60\t\60\4\61\t\61\4\62\t\62\4\63\t\63\4\64\t" +
					"\64\4\65\t\65\4\66\t\66\4\67\t\67\48\t8\49\t9\4:\t:\4;\t;\4<\t<\4=\t=" +
					"\4>\t>\4?\t?\4@\t@\4A\tA\4B\tB\4C\tC\4D\tD\4E\tE\4F\tF\4G\tG\4H\tH\4I" +
					"\tI\4J\tJ\4K\tK\4L\tL\4M\tM\4N\tN\4O\tO\4P\tP\3\2\3\2\3\3\3\3\3\4\3\4" +
					"\3\5\3\5\3\6\3\6\3\7\3\7\3\b\3\b\3\t\3\t\3\n\3\n\3\13\3\13\3\f\3\f\3\r" +
					"\3\r\3\r\3\r\3\r\5\r\u00bd\n\r\3\16\3\16\3\17\3\17\3\17\3\20\3\20\3\20" +
					"\5\20\u00c7\n\20\3\21\3\21\3\22\3\22\3\22\3\23\3\23\3\23\3\23\5\23\u00d2" +
					"\n\23\3\24\3\24\3\24\3\25\3\25\3\25\3\25\3\25\3\26\3\26\3\26\3\27\3\27" +
					"\3\27\3\27\3\27\5\27\u00e4\n\27\3\30\3\30\3\30\3\30\3\30\3\30\5\30\u00ec" +
					"\n\30\3\31\3\31\3\31\3\31\3\31\5\31\u00f3\n\31\3\32\3\32\3\32\3\32\3\33" +
					"\3\33\3\33\3\33\3\33\3\34\3\34\3\34\3\34\3\34\3\35\3\35\3\35\3\35\3\35" +
					"\3\36\3\36\3\36\3\36\3\36\3\37\3\37\3\37\3\37\3 \3 \3 \3 \3 \3 \3 \3 " +
					"\3 \3 \3 \5 \u011c\n \3!\3!\3!\3!\3!\3\"\3\"\3\"\7\"\u0126\n\"\f\"\16" +
					"\"\u0129\13\"\3\"\3\"\3\"\3\"\7\"\u012f\n\"\f\"\16\"\u0132\13\"\3\"\5" +
					"\"\u0135\n\"\3#\6#\u0138\n#\r#\16#\u0139\3$\6$\u013d\n$\r$\16$\u013e\3" +
					"$\3$\6$\u0143\n$\r$\16$\u0144\3%\3%\3%\6%\u014a\n%\r%\16%\u014b\3&\3&" +
					"\3&\6&\u0151\n&\r&\16&\u0152\3\'\3\'\3\'\3\'\3(\6(\u015a\n(\r(\16(\u015b" +
					"\3(\3(\3)\3)\3)\3)\7)\u0164\n)\f)\16)\u0167\13)\3)\3)\3*\3*\3*\3*\7*\u016f" +
					"\n*\f*\16*\u0172\13*\3*\3*\3*\3*\3*\3+\3+\3,\3,\3-\3-\3.\3.\3/\3/\3/\3" +
					"/\5/\u0185\n/\3\60\3\60\3\60\3\60\5\60\u018b\n\60\3\61\3\61\3\62\3\62" +
					"\3\63\3\63\3\64\3\64\3\65\3\65\3\66\6\66\u0198\n\66\r\66\16\66\u0199\3" +
					"\67\3\67\38\38\39\39\3:\3:\3;\3;\3<\3<\3=\3=\3>\3>\3?\3?\3@\3@\3A\3A\3" +
					"B\3B\3C\3C\3D\3D\3E\3E\3F\3F\3G\3G\3H\3H\3I\3I\3J\3J\3K\3K\3L\3L\3M\3" +
					"M\3N\3N\3O\3O\3P\3P\6\u0127\u0130\u0170\u0199\2Q\3\3\5\4\7\5\t\6\13\7" +
					"\r\b\17\t\21\n\23\13\25\f\27\r\31\16\33\17\35\20\37\21!\22#\23%\24\'\25" +
					")\26+\27-\30/\31\61\32\63\33\65\34\67\359\36;\37= ?!A\"C#E$G%I&K\'M(O" +
					")Q*S+U\2W\2Y\2[\2]\2_\2a\2c\2e\2g\2i\2k\2m\2o\2q\2s\2u\2w\2y\2{\2}\2\177" +
					"\2\u0081\2\u0083\2\u0085\2\u0087\2\u0089\2\u008b\2\u008d\2\u008f\2\u0091" +
					"\2\u0093\2\u0095\2\u0097\2\u0099\2\u009b\2\u009d\2\u009f\2\3\2#\4\2##" +
					"\u0080\u0080\4\2\f\f\17\17\4\2--//\5\2C\\aac|\3\2\62;\3\2\u4e02\u9fa7" +
					"\5\2\13\f\16\17\"\"\4\2CCcc\4\2DDdd\4\2EEee\4\2FFff\4\2GGgg\4\2HHhh\4" +
					"\2IIii\4\2JJjj\4\2KKkk\4\2LLll\4\2MMmm\4\2NNnn\4\2OOoo\4\2PPpp\4\2QQq" +
					"q\4\2RRrr\4\2SSss\4\2TTtt\4\2UUuu\4\2VVvv\4\2WWww\4\2XXxx\4\2YYyy\4\2" +
					"ZZzz\4\2[[{{\4\2\\\\||\2\u01c2\2\3\3\2\2\2\2\5\3\2\2\2\2\7\3\2\2\2\2\t" +
					"\3\2\2\2\2\13\3\2\2\2\2\r\3\2\2\2\2\17\3\2\2\2\2\21\3\2\2\2\2\23\3\2\2" +
					"\2\2\25\3\2\2\2\2\27\3\2\2\2\2\31\3\2\2\2\2\33\3\2\2\2\2\35\3\2\2\2\2" +
					"\37\3\2\2\2\2!\3\2\2\2\2#\3\2\2\2\2%\3\2\2\2\2\'\3\2\2\2\2)\3\2\2\2\2" +
					"+\3\2\2\2\2-\3\2\2\2\2/\3\2\2\2\2\61\3\2\2\2\2\63\3\2\2\2\2\65\3\2\2\2" +
					"\2\67\3\2\2\2\29\3\2\2\2\2;\3\2\2\2\2=\3\2\2\2\2?\3\2\2\2\2A\3\2\2\2\2" +
					"C\3\2\2\2\2E\3\2\2\2\2G\3\2\2\2\2I\3\2\2\2\2K\3\2\2\2\2M\3\2\2\2\2O\3" +
					"\2\2\2\2Q\3\2\2\2\2S\3\2\2\2\3\u00a1\3\2\2\2\5\u00a3\3\2\2\2\7\u00a5\3" +
					"\2\2\2\t\u00a7\3\2\2\2\13\u00a9\3\2\2\2\r\u00ab\3\2\2\2\17\u00ad\3\2\2" +
					"\2\21\u00af\3\2\2\2\23\u00b1\3\2\2\2\25\u00b3\3\2\2\2\27\u00b5\3\2\2\2" +
					"\31\u00bc\3\2\2\2\33\u00be\3\2\2\2\35\u00c0\3\2\2\2\37\u00c6\3\2\2\2!" +
					"\u00c8\3\2\2\2#\u00ca\3\2\2\2%\u00d1\3\2\2\2\'\u00d3\3\2\2\2)\u00d6\3" +
					"\2\2\2+\u00db\3\2\2\2-\u00e3\3\2\2\2/\u00eb\3\2\2\2\61\u00f2\3\2\2\2\63" +
					"\u00f4\3\2\2\2\65\u00f8\3\2\2\2\67\u00fd\3\2\2\29\u0102\3\2\2\2;\u0107" +
					"\3\2\2\2=\u010c\3\2\2\2?\u011b\3\2\2\2A\u011d\3\2\2\2C\u0134\3\2\2\2E" +
					"\u0137\3\2\2\2G\u013c\3\2\2\2I\u0146\3\2\2\2K\u0150\3\2\2\2M\u0154\3\2" +
					"\2\2O\u0159\3\2\2\2Q\u015f\3\2\2\2S\u016a\3\2\2\2U\u0178\3\2\2\2W\u017a" +
					"\3\2\2\2Y\u017c\3\2\2\2[\u017e\3\2\2\2]\u0184\3\2\2\2_\u018a\3\2\2\2a" +
					"\u018c\3\2\2\2c\u018e\3\2\2\2e\u0190\3\2\2\2g\u0192\3\2\2\2i\u0194\3\2" +
					"\2\2k\u0197\3\2\2\2m\u019b\3\2\2\2o\u019d\3\2\2\2q\u019f\3\2\2\2s\u01a1" +
					"\3\2\2\2u\u01a3\3\2\2\2w\u01a5\3\2\2\2y\u01a7\3\2\2\2{\u01a9\3\2\2\2}" +
					"\u01ab\3\2\2\2\177\u01ad\3\2\2\2\u0081\u01af\3\2\2\2\u0083\u01b1\3\2\2" +
					"\2\u0085\u01b3\3\2\2\2\u0087\u01b5\3\2\2\2\u0089\u01b7\3\2\2\2\u008b\u01b9" +
					"\3\2\2\2\u008d\u01bb\3\2\2\2\u008f\u01bd\3\2\2\2\u0091\u01bf\3\2\2\2\u0093" +
					"\u01c1\3\2\2\2\u0095\u01c3\3\2\2\2\u0097\u01c5\3\2\2\2\u0099\u01c7\3\2" +
					"\2\2\u009b\u01c9\3\2\2\2\u009d\u01cb\3\2\2\2\u009f\u01cd\3\2\2\2\u00a1" +
					"\u00a2\7*\2\2\u00a2\4\3\2\2\2\u00a3\u00a4\7+\2\2\u00a4\6\3\2\2\2\u00a5" +
					"\u00a6\7}\2\2\u00a6\b\3\2\2\2\u00a7\u00a8\7\177\2\2\u00a8\n\3\2\2\2\u00a9" +
					"\u00aa\7.\2\2\u00aa\f\3\2\2\2\u00ab\u00ac\7<\2\2\u00ac\16\3\2\2\2\u00ad" +
					"\u00ae\7,\2\2\u00ae\20\3\2\2\2\u00af\u00b0\7\61\2\2\u00b0\22\3\2\2\2\u00b1" +
					"\u00b2\7-\2\2\u00b2\24\3\2\2\2\u00b3\u00b4\7/\2\2\u00b4\26\3\2\2\2\u00b5" +
					"\u00b6\7`\2\2\u00b6\30\3\2\2\2\u00b7\u00bd\7\'\2\2\u00b8\u00b9\5\u0085" +
					"C\2\u00b9\u00ba\5\u0089E\2\u00ba\u00bb\5s:\2\u00bb\u00bd\3\2\2\2\u00bc" +
					"\u00b7\3\2\2\2\u00bc\u00b8\3\2\2\2\u00bd\32\3\2\2\2\u00be\u00bf\7@\2\2" +
					"\u00bf\34\3\2\2\2\u00c0\u00c1\7@\2\2\u00c1\u00c2\7?\2\2\u00c2\36\3\2\2" +
					"\2\u00c3\u00c4\7?\2\2\u00c4\u00c7\7?\2\2\u00c5\u00c7\7?\2\2\u00c6\u00c3" +
					"\3\2\2\2\u00c6\u00c5\3\2\2\2\u00c7 \3\2\2\2\u00c8\u00c9\7>\2\2\u00c9\"" +
					"\3\2\2\2\u00ca\u00cb\7>\2\2\u00cb\u00cc\7?\2\2\u00cc$\3\2\2\2\u00cd\u00ce" +
					"\7>\2\2\u00ce\u00d2\7@\2\2\u00cf\u00d0\7#\2\2\u00d0\u00d2\7?\2\2\u00d1" +
					"\u00cd\3\2\2\2\u00d1\u00cf\3\2\2\2\u00d2&\3\2\2\2\u00d3\u00d4\5}?\2\u00d4" +
					"\u00d5\5w<\2\u00d5(\3\2\2\2\u00d6\u00d7\5\u0083B\2\u00d7\u00d8\5}?\2\u00d8" +
					"\u00d9\5\u0081A\2\u00d9\u00da\5u;\2\u00da*\3\2\2\2\u00db\u00dc\5}?\2\u00dc" +
					"\u00dd\5\u0087D\2\u00dd,\3\2\2\2\u00de\u00e4\t\2\2\2\u00df\u00e0\5\u0087" +
					"D\2\u00e0\u00e1\5\u0089E\2\u00e1\u00e2\5\u0093J\2\u00e2\u00e4\3\2\2\2" +
					"\u00e3\u00de\3\2\2\2\u00e3\u00df\3\2\2\2\u00e4.\3\2\2\2\u00e5\u00e6\7" +
					"(\2\2\u00e6\u00ec\7(\2\2\u00e7\u00e8\5m\67\2\u00e8\u00e9\5\u0087D\2\u00e9" +
					"\u00ea\5s:\2\u00ea\u00ec\3\2\2\2\u00eb\u00e5\3\2\2\2\u00eb\u00e7\3\2\2" +
					"\2\u00ec\60\3\2\2\2\u00ed\u00ee\7~\2\2\u00ee\u00f3\7~\2\2\u00ef\u00f0" +
					"\5\u0089E\2\u00f0\u00f1\5\u008fH\2\u00f1\u00f3\3\2\2\2\u00f2\u00ed\3\2" +
					"\2\2\u00f2\u00ef\3\2\2\2\u00f3\62\3\2\2\2\u00f4\u00f5\5\u009bN\2\u00f5" +
					"\u00f6\5\u0089E\2\u00f6\u00f7\5\u008fH\2\u00f7\64\3\2\2\2\u00f8\u00f9" +
					"\5q9\2\u00f9\u00fa\5m\67\2\u00fa\u00fb\5\u0091I\2\u00fb\u00fc\5u;\2\u00fc" +
					"\66\3\2\2\2\u00fd\u00fe\5\u0099M\2\u00fe\u00ff\5{>\2\u00ff\u0100\5u;\2" +
					"\u0100\u0101\5\u0087D\2\u01018\3\2\2\2\u0102\u0103\5\u0093J\2\u0103\u0104" +
					"\5{>\2\u0104\u0105\5u;\2\u0105\u0106\5\u0087D\2\u0106:\3\2\2\2\u0107\u0108" +
					"\5u;\2\u0108\u0109\5\u0083B\2\u0109\u010a\5\u0091I\2\u010a\u010b\5u;\2" +
					"\u010b<\3\2\2\2\u010c\u010d\5u;\2\u010d\u010e\5\u0087D\2\u010e\u010f\5" +
					"s:\2\u010f>\3\2\2\2\u0110\u0111\5\u0093J\2\u0111\u0112\5\u008fH\2\u0112" +
					"\u0113\5\u0095K\2\u0113\u0114\5u;\2\u0114\u011c\3\2\2\2\u0115\u0116\5" +
					"w<\2\u0116\u0117\5m\67\2\u0117\u0118\5\u0083B\2\u0118\u0119\5\u0091I\2" +
					"\u0119\u011a\5u;\2\u011a\u011c\3\2\2\2\u011b\u0110\3\2\2\2\u011b\u0115" +
					"\3\2\2\2\u011c@\3\2\2\2\u011d\u011e\5\u0087D\2\u011e\u011f\5\u0095K\2" +
					"\u011f\u0120\5\u0083B\2\u0120\u0121\5\u0083B\2\u0121B\3\2\2\2\u0122\u0127" +
					"\7$\2\2\u0123\u0126\5]/\2\u0124\u0126\13\2\2\2\u0125\u0123\3\2\2\2\u0125" +
					"\u0124\3\2\2\2\u0126\u0129\3\2\2\2\u0127\u0128\3\2\2\2\u0127\u0125\3\2" +
					"\2\2\u0128\u012a\3\2\2\2\u0129\u0127\3\2\2\2\u012a\u0135\7$\2\2\u012b" +
					"\u0130\7)\2\2\u012c\u012f\5_\60\2\u012d\u012f\13\2\2\2\u012e\u012c\3\2" +
					"\2\2\u012e\u012d\3\2\2\2\u012f\u0132\3\2\2\2\u0130\u0131\3\2\2\2\u0130" +
					"\u012e\3\2\2\2\u0131\u0133\3\2\2\2\u0132\u0130\3\2\2\2\u0133\u0135\7)" +
					"\2\2\u0134\u0122\3\2\2\2\u0134\u012b\3\2\2\2\u0135D\3\2\2\2\u0136\u0138" +
					"\5c\62\2\u0137\u0136\3\2\2\2\u0138\u0139\3\2\2\2\u0139\u0137\3\2\2\2\u0139" +
					"\u013a\3\2\2\2\u013aF\3\2\2\2\u013b\u013d\5c\62\2\u013c\u013b\3\2\2\2" +
					"\u013d\u013e\3\2\2\2\u013e\u013c\3\2\2\2\u013e\u013f\3\2\2\2\u013f\u0140" +
					"\3\2\2\2\u0140\u0142\5U+\2\u0141\u0143\5c\62\2\u0142\u0141\3\2\2\2\u0143" +
					"\u0144\3\2\2\2\u0144\u0142\3\2\2\2\u0144\u0145\3\2\2\2\u0145H\3\2\2\2" +
					"\u0146\u0149\5Y-\2\u0147\u014a\5c\62\2\u0148\u014a\5a\61\2\u0149\u0147" +
					"\3\2\2\2\u0149\u0148\3\2\2\2\u014a\u014b\3\2\2\2\u014b\u0149\3\2\2\2\u014b" +
					"\u014c\3\2\2\2\u014cJ\3\2\2\2\u014d\u0151\5a\61\2\u014e\u0151\5c\62\2" +
					"\u014f\u0151\5e\63\2\u0150\u014d\3\2\2\2\u0150\u014e\3\2\2\2\u0150\u014f" +
					"\3\2\2\2\u0151\u0152\3\2\2\2\u0152\u0150\3\2\2\2\u0152\u0153\3\2\2\2\u0153" +
					"L\3\2\2\2\u0154\u0155\5W,\2\u0155\u0156\5k\66\2\u0156\u0157\5W,\2\u0157" +
					"N\3\2\2\2\u0158\u015a\5i\65\2\u0159\u0158\3\2\2\2\u015a\u015b\3\2\2\2" +
					"\u015b\u0159\3\2\2\2\u015b\u015c\3\2\2\2\u015c\u015d\3\2\2\2\u015d\u015e" +
					"\b(\2\2\u015eP\3\2\2\2\u015f\u0160\7\61\2\2\u0160\u0161\7\61\2\2\u0161" +
					"\u0165\3\2\2\2\u0162\u0164\n\3\2\2\u0163\u0162\3\2\2\2\u0164\u0167\3\2" +
					"\2\2\u0165\u0163\3\2\2\2\u0165\u0166\3\2\2\2\u0166\u0168\3\2\2\2\u0167" +
					"\u0165\3\2\2\2\u0168\u0169\b)\2\2\u0169R\3\2\2\2\u016a\u016b\7\61\2\2" +
					"\u016b\u016c\7,\2\2\u016c\u0170\3\2\2\2\u016d\u016f\13\2\2\2\u016e\u016d" +
					"\3\2\2\2\u016f\u0172\3\2\2\2\u0170\u0171\3\2\2\2\u0170\u016e\3\2\2\2\u0171" +
					"\u0173\3\2\2\2\u0172\u0170\3\2\2\2\u0173\u0174\7,\2\2\u0174\u0175\7\61" +
					"\2\2\u0175\u0176\3\2\2\2\u0176\u0177\b*\3\2\u0177T\3\2\2\2\u0178\u0179" +
					"\7\60\2\2\u0179V\3\2\2\2\u017a\u017b\7b\2\2\u017bX\3\2\2\2\u017c\u017d" +
					"\7%\2\2\u017dZ\3\2\2\2\u017e\u017f\t\4\2\2\u017f\\\3\2\2\2\u0180\u0181" +
					"\7^\2\2\u0181\u0185\7$\2\2\u0182\u0183\7^\2\2\u0183\u0185\7^\2\2\u0184" +
					"\u0180\3\2\2\2\u0184\u0182\3\2\2\2\u0185^\3\2\2\2\u0186\u0187\7^\2\2\u0187" +
					"\u018b\7)\2\2\u0188\u0189\7^\2\2\u0189\u018b\7^\2\2\u018a\u0186\3\2\2" +
					"\2\u018a\u0188\3\2\2\2\u018b`\3\2\2\2\u018c\u018d\t\5\2\2\u018db\3\2\2" +
					"\2\u018e\u018f\t\6\2\2\u018fd\3\2\2\2\u0190\u0191\t\7\2\2\u0191f\3\2\2" +
					"\2\u0192\u0193\t\3\2\2\u0193h\3\2\2\2\u0194\u0195\t\b\2\2\u0195j\3\2\2" +
					"\2\u0196\u0198\13\2\2\2\u0197\u0196\3\2\2\2\u0198\u0199\3\2\2\2\u0199" +
					"\u019a\3\2\2\2\u0199\u0197\3\2\2\2\u019al\3\2\2\2\u019b\u019c\t\t\2\2" +
					"\u019cn\3\2\2\2\u019d\u019e\t\n\2\2\u019ep\3\2\2\2\u019f\u01a0\t\13\2" +
					"\2\u01a0r\3\2\2\2\u01a1\u01a2\t\f\2\2\u01a2t\3\2\2\2\u01a3\u01a4\t\r\2" +
					"\2\u01a4v\3\2\2\2\u01a5\u01a6\t\16\2\2\u01a6x\3\2\2\2\u01a7\u01a8\t\17" +
					"\2\2\u01a8z\3\2\2\2\u01a9\u01aa\t\20\2\2\u01aa|\3\2\2\2\u01ab\u01ac\t" +
					"\21\2\2\u01ac~\3\2\2\2\u01ad\u01ae\t\22\2\2\u01ae\u0080\3\2\2\2\u01af" +
					"\u01b0\t\23\2\2\u01b0\u0082\3\2\2\2\u01b1\u01b2\t\24\2\2\u01b2\u0084\3" +
					"\2\2\2\u01b3\u01b4\t\25\2\2\u01b4\u0086\3\2\2\2\u01b5\u01b6\t\26\2\2\u01b6" +
					"\u0088\3\2\2\2\u01b7\u01b8\t\27\2\2\u01b8\u008a\3\2\2\2\u01b9\u01ba\t" +
					"\30\2\2\u01ba\u008c\3\2\2\2\u01bb\u01bc\t\31\2\2\u01bc\u008e\3\2\2\2\u01bd" +
					"\u01be\t\32\2\2\u01be\u0090\3\2\2\2\u01bf\u01c0\t\33\2\2\u01c0\u0092\3" +
					"\2\2\2\u01c1\u01c2\t\34\2\2\u01c2\u0094\3\2\2\2\u01c3\u01c4\t\35\2\2\u01c4" +
					"\u0096\3\2\2\2\u01c5\u01c6\t\36\2\2\u01c6\u0098\3\2\2\2\u01c7\u01c8\t" +
					"\37\2\2\u01c8\u009a\3\2\2\2\u01c9\u01ca\t \2\2\u01ca\u009c\3\2\2\2\u01cb" +
					"\u01cc\t!\2\2\u01cc\u009e\3\2\2\2\u01cd\u01ce\t\"\2\2\u01ce\u00a0\3\2" +
					"\2\2\34\2\u00bc\u00c6\u00d1\u00e3\u00eb\u00f2\u011b\u0125\u0127\u012e" +
					"\u0130\u0134\u0139\u013e\u0144\u0149\u014b\u0150\u0152\u015b\u0165\u0170" +
					"\u0184\u018a\u0199\4\b\2\2\2\3\2";
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

	public FormulaLexer(CharStream input) {
		super(input);
		_interp = new LexerATNSimulator(this, _ATN, _decisionToDFA, _sharedContextCache);
	}

	private static String[] makeRuleNames() {
		return new String[]{
				"L_PARENTHESES", "R_PARENTHESES", "L_BRACE", "R_BRACE", "COMMA", "COLON",
				"MUL", "DIV", "PLUS", "MINUS", "POWER", "MOD", "GREATER", "GREATER_EQUAL",
				"EQUAL", "LESS", "LESS_EQUAL", "NOT_EQUAL", "IF", "LIKE", "IN", "NOT",
				"AND", "OR", "XOR", "CASE", "WHEN", "THEN", "ELSE", "END", "BOOL", "NULL",
				"STRING", "INTEGER", "FLOAT", "COLUMN_ID", "IDENTITY", "COLUMN_NAME",
				"WS", "LINE_COMMENT", "BLOCK_COMMENT", "DOT", "BACK_QUOTE", "SHARP",
				"SIGN", "ESC_DQUOTE", "ESC_SQUOTE", "ALPHA", "DIGIT", "CHINESE", "NL",
				"BLANK", "ANY", "A", "B", "C", "D", "E", "F", "G", "H", "I", "J", "K",
				"L", "M", "N", "O", "P", "Q", "R", "S", "T", "U", "V", "W", "X", "Y",
				"Z"
		};
	}

	private static String[] makeLiteralNames() {
		return new String[]{
				null, "'('", "')'", "'{'", "'}'", "','", "':'", "'*'", "'/'", "'+'",
				"'-'", "'^'", null, "'>'", "'>='", null, "'<'", "'<='"
		};
	}

	private static String[] makeSymbolicNames() {
		return new String[]{
				null, "L_PARENTHESES", "R_PARENTHESES", "L_BRACE", "R_BRACE", "COMMA",
				"COLON", "MUL", "DIV", "PLUS", "MINUS", "POWER", "MOD", "GREATER", "GREATER_EQUAL",
				"EQUAL", "LESS", "LESS_EQUAL", "NOT_EQUAL", "IF", "LIKE", "IN", "NOT",
				"AND", "OR", "XOR", "CASE", "WHEN", "THEN", "ELSE", "END", "BOOL", "NULL",
				"STRING", "INTEGER", "FLOAT", "COLUMN_ID", "IDENTITY", "COLUMN_NAME",
				"WS", "LINE_COMMENT", "BLOCK_COMMENT"
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
		return "Formula.g4";
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