// Generated from D:/projects/pms/module/module-query/src/main/java/org/sunt/query/formula/parser\Formula.g4 by ANTLR 4.9.1
package org.sunt.query.formula.parser;
import org.antlr.v4.runtime.Lexer;
import org.antlr.v4.runtime.CharStream;
import org.antlr.v4.runtime.Token;
import org.antlr.v4.runtime.TokenStream;
import org.antlr.v4.runtime.*;
import org.antlr.v4.runtime.atn.*;
import org.antlr.v4.runtime.dfa.DFA;
import org.antlr.v4.runtime.misc.*;

@SuppressWarnings({"all", "warnings", "unchecked", "unused", "cast"})
public class FormulaLexer extends Lexer {
	static { RuntimeMetaData.checkVersion("4.9.1", RuntimeMetaData.VERSION); }

	protected static final DFA[] _decisionToDFA;
	protected static final PredictionContextCache _sharedContextCache =
		new PredictionContextCache();
	public static final int
		L_PARENTHESES=1, R_PARENTHESES=2, L_BRACE=3, R_BRACE=4, L_SQUARE=5, R_SQUARE=6, 
		COMMA=7, COLON=8, MUL=9, DIV=10, PLUS=11, MINUS=12, POWER=13, MOD=14, 
		IFNULL=15, GREATER=16, GREATER_EQUAL=17, EQUAL=18, LESS=19, LESS_EQUAL=20, 
		NOT_EQUAL=21, IF=22, LIKE=23, IN=24, NOT=25, AND=26, OR=27, XOR=28, CASE=29, 
		WHEN=30, THEN=31, ELSE=32, END=33, BOOL=34, NULL=35, STRING=36, INTEGER=37, 
		FLOAT=38, COLUMN_ID=39, IDENTITY=40, COLUMN_NAME=41, HINT=42, WS=43, LINE_COMMENT=44, 
		BLOCK_COMMENT=45;
	public static String[] channelNames = {
		"DEFAULT_TOKEN_CHANNEL", "HIDDEN"
	};

	public static String[] modeNames = {
		"DEFAULT_MODE"
	};

	private static String[] makeRuleNames() {
		return new String[] {
			"L_PARENTHESES", "R_PARENTHESES", "L_BRACE", "R_BRACE", "L_SQUARE", "R_SQUARE", 
			"COMMA", "COLON", "MUL", "DIV", "PLUS", "MINUS", "POWER", "MOD", "IFNULL", 
			"GREATER", "GREATER_EQUAL", "EQUAL", "LESS", "LESS_EQUAL", "NOT_EQUAL", 
			"IF", "LIKE", "IN", "NOT", "AND", "OR", "XOR", "CASE", "WHEN", "THEN", 
			"ELSE", "END", "BOOL", "NULL", "STRING", "INTEGER", "FLOAT", "COLUMN_ID", 
			"IDENTITY", "COLUMN_NAME", "HINT", "WS", "LINE_COMMENT", "BLOCK_COMMENT", 
			"DOT", "BACK_QUOTE", "SHARP", "AT", "SIGN", "ESC_DQUOTE", "ESC_SQUOTE", 
			"ALPHA", "DIGIT", "CHINESE", "NL", "BLANK", "ANY", "A", "B", "C", "D", 
			"E", "F", "G", "H", "I", "J", "K", "L", "M", "N", "O", "P", "Q", "R", 
			"S", "T", "U", "V", "W", "X", "Y", "Z"
		};
	}
	public static final String[] ruleNames = makeRuleNames();

	private static String[] makeLiteralNames() {
		return new String[] {
			null, "'('", "')'", "'{'", "'}'", "'['", "']'", "','", "':'", "'*'", 
			"'/'", "'+'", "'-'", "'^'", null, null, "'>'", "'>='", null, "'<'", "'<='"
		};
	}
	private static final String[] _LITERAL_NAMES = makeLiteralNames();
	private static String[] makeSymbolicNames() {
		return new String[] {
			null, "L_PARENTHESES", "R_PARENTHESES", "L_BRACE", "R_BRACE", "L_SQUARE", 
			"R_SQUARE", "COMMA", "COLON", "MUL", "DIV", "PLUS", "MINUS", "POWER", 
			"MOD", "IFNULL", "GREATER", "GREATER_EQUAL", "EQUAL", "LESS", "LESS_EQUAL", 
			"NOT_EQUAL", "IF", "LIKE", "IN", "NOT", "AND", "OR", "XOR", "CASE", "WHEN", 
			"THEN", "ELSE", "END", "BOOL", "NULL", "STRING", "INTEGER", "FLOAT", 
			"COLUMN_ID", "IDENTITY", "COLUMN_NAME", "HINT", "WS", "LINE_COMMENT", 
			"BLOCK_COMMENT"
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


	public FormulaLexer(CharStream input) {
		super(input);
		_interp = new LexerATNSimulator(this,_ATN,_decisionToDFA,_sharedContextCache);
	}

	@Override
	public String getGrammarFileName() { return "Formula.g4"; }

	@Override
	public String[] getRuleNames() { return ruleNames; }

	@Override
	public String getSerializedATN() { return _serializedATN; }

	@Override
	public String[] getChannelNames() { return channelNames; }

	@Override
	public String[] getModeNames() { return modeNames; }

	@Override
	public ATN getATN() { return _ATN; }

	public static final String _serializedATN =
		"\3\u608b\ua72a\u8133\ub9ed\u417c\u3be7\u7786\u5964\2/\u01f5\b\1\4\2\t"+
		"\2\4\3\t\3\4\4\t\4\4\5\t\5\4\6\t\6\4\7\t\7\4\b\t\b\4\t\t\t\4\n\t\n\4\13"+
		"\t\13\4\f\t\f\4\r\t\r\4\16\t\16\4\17\t\17\4\20\t\20\4\21\t\21\4\22\t\22"+
		"\4\23\t\23\4\24\t\24\4\25\t\25\4\26\t\26\4\27\t\27\4\30\t\30\4\31\t\31"+
		"\4\32\t\32\4\33\t\33\4\34\t\34\4\35\t\35\4\36\t\36\4\37\t\37\4 \t \4!"+
		"\t!\4\"\t\"\4#\t#\4$\t$\4%\t%\4&\t&\4\'\t\'\4(\t(\4)\t)\4*\t*\4+\t+\4"+
		",\t,\4-\t-\4.\t.\4/\t/\4\60\t\60\4\61\t\61\4\62\t\62\4\63\t\63\4\64\t"+
		"\64\4\65\t\65\4\66\t\66\4\67\t\67\48\t8\49\t9\4:\t:\4;\t;\4<\t<\4=\t="+
		"\4>\t>\4?\t?\4@\t@\4A\tA\4B\tB\4C\tC\4D\tD\4E\tE\4F\tF\4G\tG\4H\tH\4I"+
		"\tI\4J\tJ\4K\tK\4L\tL\4M\tM\4N\tN\4O\tO\4P\tP\4Q\tQ\4R\tR\4S\tS\4T\tT"+
		"\4U\tU\3\2\3\2\3\3\3\3\3\4\3\4\3\5\3\5\3\6\3\6\3\7\3\7\3\b\3\b\3\t\3\t"+
		"\3\n\3\n\3\13\3\13\3\f\3\f\3\r\3\r\3\16\3\16\3\17\3\17\3\17\3\17\3\17"+
		"\5\17\u00cb\n\17\3\20\3\20\7\20\u00cf\n\20\f\20\16\20\u00d2\13\20\3\20"+
		"\3\20\3\21\3\21\3\22\3\22\3\22\3\23\3\23\3\23\5\23\u00de\n\23\3\24\3\24"+
		"\3\25\3\25\3\25\3\26\3\26\3\26\3\26\5\26\u00e9\n\26\3\27\3\27\3\27\3\30"+
		"\3\30\3\30\3\30\3\30\3\31\3\31\3\31\3\32\3\32\3\32\3\32\3\32\5\32\u00fb"+
		"\n\32\3\33\3\33\3\33\3\33\3\33\3\33\5\33\u0103\n\33\3\34\3\34\3\34\3\34"+
		"\3\34\5\34\u010a\n\34\3\35\3\35\3\35\3\35\3\36\3\36\3\36\3\36\3\36\3\37"+
		"\3\37\3\37\3\37\3\37\3 \3 \3 \3 \3 \3!\3!\3!\3!\3!\3\"\3\"\3\"\3\"\3#"+
		"\3#\3#\3#\3#\3#\3#\3#\3#\3#\3#\5#\u0133\n#\3$\3$\3$\3$\3$\3%\3%\3%\7%"+
		"\u013d\n%\f%\16%\u0140\13%\3%\3%\3%\3%\7%\u0146\n%\f%\16%\u0149\13%\3"+
		"%\5%\u014c\n%\3&\6&\u014f\n&\r&\16&\u0150\3\'\6\'\u0154\n\'\r\'\16\'\u0155"+
		"\3\'\3\'\6\'\u015a\n\'\r\'\16\'\u015b\3(\3(\3(\6(\u0161\n(\r(\16(\u0162"+
		"\3)\3)\3)\6)\u0168\n)\r)\16)\u0169\3*\3*\3*\3*\3+\3+\3+\3+\3+\7+\u0175"+
		"\n+\f+\16+\u0178\13+\3+\3+\3+\3,\6,\u017e\n,\r,\16,\u017f\3,\3,\3-\3-"+
		"\3-\3-\7-\u0188\n-\f-\16-\u018b\13-\3-\3-\3.\3.\3.\3.\7.\u0193\n.\f.\16"+
		".\u0196\13.\3.\3.\3.\3.\3.\3/\3/\3\60\3\60\3\61\3\61\3\62\3\62\3\63\3"+
		"\63\3\64\3\64\3\64\3\64\5\64\u01ab\n\64\3\65\3\65\3\65\3\65\5\65\u01b1"+
		"\n\65\3\66\3\66\3\67\3\67\38\38\39\39\3:\3:\3;\6;\u01be\n;\r;\16;\u01bf"+
		"\3<\3<\3=\3=\3>\3>\3?\3?\3@\3@\3A\3A\3B\3B\3C\3C\3D\3D\3E\3E\3F\3F\3G"+
		"\3G\3H\3H\3I\3I\3J\3J\3K\3K\3L\3L\3M\3M\3N\3N\3O\3O\3P\3P\3Q\3Q\3R\3R"+
		"\3S\3S\3T\3T\3U\3U\7\u013e\u0147\u0176\u0194\u01bf\2V\3\3\5\4\7\5\t\6"+
		"\13\7\r\b\17\t\21\n\23\13\25\f\27\r\31\16\33\17\35\20\37\21!\22#\23%\24"+
		"\'\25)\26+\27-\30/\31\61\32\63\33\65\34\67\359\36;\37= ?!A\"C#E$G%I&K"+
		"\'M(O)Q*S+U,W-Y.[/]\2_\2a\2c\2e\2g\2i\2k\2m\2o\2q\2s\2u\2w\2y\2{\2}\2"+
		"\177\2\u0081\2\u0083\2\u0085\2\u0087\2\u0089\2\u008b\2\u008d\2\u008f\2"+
		"\u0091\2\u0093\2\u0095\2\u0097\2\u0099\2\u009b\2\u009d\2\u009f\2\u00a1"+
		"\2\u00a3\2\u00a5\2\u00a7\2\u00a9\2\3\2#\4\2##\u0080\u0080\4\2\f\f\17\17"+
		"\4\2--//\5\2C\\aac|\3\2\62;\3\2\u4e02\u9fa7\5\2\13\f\16\17\"\"\4\2CCc"+
		"c\4\2DDdd\4\2EEee\4\2FFff\4\2GGgg\4\2HHhh\4\2IIii\4\2JJjj\4\2KKkk\4\2"+
		"LLll\4\2MMmm\4\2NNnn\4\2OOoo\4\2PPpp\4\2QQqq\4\2RRrr\4\2SSss\4\2TTtt\4"+
		"\2UUuu\4\2VVvv\4\2WWww\4\2XXxx\4\2YYyy\4\2ZZzz\4\2[[{{\4\2\\\\||\2\u01e9"+
		"\2\3\3\2\2\2\2\5\3\2\2\2\2\7\3\2\2\2\2\t\3\2\2\2\2\13\3\2\2\2\2\r\3\2"+
		"\2\2\2\17\3\2\2\2\2\21\3\2\2\2\2\23\3\2\2\2\2\25\3\2\2\2\2\27\3\2\2\2"+
		"\2\31\3\2\2\2\2\33\3\2\2\2\2\35\3\2\2\2\2\37\3\2\2\2\2!\3\2\2\2\2#\3\2"+
		"\2\2\2%\3\2\2\2\2\'\3\2\2\2\2)\3\2\2\2\2+\3\2\2\2\2-\3\2\2\2\2/\3\2\2"+
		"\2\2\61\3\2\2\2\2\63\3\2\2\2\2\65\3\2\2\2\2\67\3\2\2\2\29\3\2\2\2\2;\3"+
		"\2\2\2\2=\3\2\2\2\2?\3\2\2\2\2A\3\2\2\2\2C\3\2\2\2\2E\3\2\2\2\2G\3\2\2"+
		"\2\2I\3\2\2\2\2K\3\2\2\2\2M\3\2\2\2\2O\3\2\2\2\2Q\3\2\2\2\2S\3\2\2\2\2"+
		"U\3\2\2\2\2W\3\2\2\2\2Y\3\2\2\2\2[\3\2\2\2\3\u00ab\3\2\2\2\5\u00ad\3\2"+
		"\2\2\7\u00af\3\2\2\2\t\u00b1\3\2\2\2\13\u00b3\3\2\2\2\r\u00b5\3\2\2\2"+
		"\17\u00b7\3\2\2\2\21\u00b9\3\2\2\2\23\u00bb\3\2\2\2\25\u00bd\3\2\2\2\27"+
		"\u00bf\3\2\2\2\31\u00c1\3\2\2\2\33\u00c3\3\2\2\2\35\u00ca\3\2\2\2\37\u00cc"+
		"\3\2\2\2!\u00d5\3\2\2\2#\u00d7\3\2\2\2%\u00dd\3\2\2\2\'\u00df\3\2\2\2"+
		")\u00e1\3\2\2\2+\u00e8\3\2\2\2-\u00ea\3\2\2\2/\u00ed\3\2\2\2\61\u00f2"+
		"\3\2\2\2\63\u00fa\3\2\2\2\65\u0102\3\2\2\2\67\u0109\3\2\2\29\u010b\3\2"+
		"\2\2;\u010f\3\2\2\2=\u0114\3\2\2\2?\u0119\3\2\2\2A\u011e\3\2\2\2C\u0123"+
		"\3\2\2\2E\u0132\3\2\2\2G\u0134\3\2\2\2I\u014b\3\2\2\2K\u014e\3\2\2\2M"+
		"\u0153\3\2\2\2O\u015d\3\2\2\2Q\u0167\3\2\2\2S\u016b\3\2\2\2U\u016f\3\2"+
		"\2\2W\u017d\3\2\2\2Y\u0183\3\2\2\2[\u018e\3\2\2\2]\u019c\3\2\2\2_\u019e"+
		"\3\2\2\2a\u01a0\3\2\2\2c\u01a2\3\2\2\2e\u01a4\3\2\2\2g\u01aa\3\2\2\2i"+
		"\u01b0\3\2\2\2k\u01b2\3\2\2\2m\u01b4\3\2\2\2o\u01b6\3\2\2\2q\u01b8\3\2"+
		"\2\2s\u01ba\3\2\2\2u\u01bd\3\2\2\2w\u01c1\3\2\2\2y\u01c3\3\2\2\2{\u01c5"+
		"\3\2\2\2}\u01c7\3\2\2\2\177\u01c9\3\2\2\2\u0081\u01cb\3\2\2\2\u0083\u01cd"+
		"\3\2\2\2\u0085\u01cf\3\2\2\2\u0087\u01d1\3\2\2\2\u0089\u01d3\3\2\2\2\u008b"+
		"\u01d5\3\2\2\2\u008d\u01d7\3\2\2\2\u008f\u01d9\3\2\2\2\u0091\u01db\3\2"+
		"\2\2\u0093\u01dd\3\2\2\2\u0095\u01df\3\2\2\2\u0097\u01e1\3\2\2\2\u0099"+
		"\u01e3\3\2\2\2\u009b\u01e5\3\2\2\2\u009d\u01e7\3\2\2\2\u009f\u01e9\3\2"+
		"\2\2\u00a1\u01eb\3\2\2\2\u00a3\u01ed\3\2\2\2\u00a5\u01ef\3\2\2\2\u00a7"+
		"\u01f1\3\2\2\2\u00a9\u01f3\3\2\2\2\u00ab\u00ac\7*\2\2\u00ac\4\3\2\2\2"+
		"\u00ad\u00ae\7+\2\2\u00ae\6\3\2\2\2\u00af\u00b0\7}\2\2\u00b0\b\3\2\2\2"+
		"\u00b1\u00b2\7\177\2\2\u00b2\n\3\2\2\2\u00b3\u00b4\7]\2\2\u00b4\f\3\2"+
		"\2\2\u00b5\u00b6\7_\2\2\u00b6\16\3\2\2\2\u00b7\u00b8\7.\2\2\u00b8\20\3"+
		"\2\2\2\u00b9\u00ba\7<\2\2\u00ba\22\3\2\2\2\u00bb\u00bc\7,\2\2\u00bc\24"+
		"\3\2\2\2\u00bd\u00be\7\61\2\2\u00be\26\3\2\2\2\u00bf\u00c0\7-\2\2\u00c0"+
		"\30\3\2\2\2\u00c1\u00c2\7/\2\2\u00c2\32\3\2\2\2\u00c3\u00c4\7`\2\2\u00c4"+
		"\34\3\2\2\2\u00c5\u00cb\7\'\2\2\u00c6\u00c7\5\u008fH\2\u00c7\u00c8\5\u0093"+
		"J\2\u00c8\u00c9\5}?\2\u00c9\u00cb\3\2\2\2\u00ca\u00c5\3\2\2\2\u00ca\u00c6"+
		"\3\2\2\2\u00cb\36\3\2\2\2\u00cc\u00d0\7A\2\2\u00cd\u00cf\5W,\2\u00ce\u00cd"+
		"\3\2\2\2\u00cf\u00d2\3\2\2\2\u00d0\u00ce\3\2\2\2\u00d0\u00d1\3\2\2\2\u00d1"+
		"\u00d3\3\2\2\2\u00d2\u00d0\3\2\2\2\u00d3\u00d4\7<\2\2\u00d4 \3\2\2\2\u00d5"+
		"\u00d6\7@\2\2\u00d6\"\3\2\2\2\u00d7\u00d8\7@\2\2\u00d8\u00d9\7?\2\2\u00d9"+
		"$\3\2\2\2\u00da\u00db\7?\2\2\u00db\u00de\7?\2\2\u00dc\u00de\7?\2\2\u00dd"+
		"\u00da\3\2\2\2\u00dd\u00dc\3\2\2\2\u00de&\3\2\2\2\u00df\u00e0\7>\2\2\u00e0"+
		"(\3\2\2\2\u00e1\u00e2\7>\2\2\u00e2\u00e3\7?\2\2\u00e3*\3\2\2\2\u00e4\u00e5"+
		"\7>\2\2\u00e5\u00e9\7@\2\2\u00e6\u00e7\7#\2\2\u00e7\u00e9\7?\2\2\u00e8"+
		"\u00e4\3\2\2\2\u00e8\u00e6\3\2\2\2\u00e9,\3\2\2\2\u00ea\u00eb\5\u0087"+
		"D\2\u00eb\u00ec\5\u0081A\2\u00ec.\3\2\2\2\u00ed\u00ee\5\u008dG\2\u00ee"+
		"\u00ef\5\u0087D\2\u00ef\u00f0\5\u008bF\2\u00f0\u00f1\5\177@\2\u00f1\60"+
		"\3\2\2\2\u00f2\u00f3\5\u0087D\2\u00f3\u00f4\5\u0091I\2\u00f4\62\3\2\2"+
		"\2\u00f5\u00fb\t\2\2\2\u00f6\u00f7\5\u0091I\2\u00f7\u00f8\5\u0093J\2\u00f8"+
		"\u00f9\5\u009dO\2\u00f9\u00fb\3\2\2\2\u00fa\u00f5\3\2\2\2\u00fa\u00f6"+
		"\3\2\2\2\u00fb\64\3\2\2\2\u00fc\u00fd\7(\2\2\u00fd\u0103\7(\2\2\u00fe"+
		"\u00ff\5w<\2\u00ff\u0100\5\u0091I\2\u0100\u0101\5}?\2\u0101\u0103\3\2"+
		"\2\2\u0102\u00fc\3\2\2\2\u0102\u00fe\3\2\2\2\u0103\66\3\2\2\2\u0104\u0105"+
		"\7~\2\2\u0105\u010a\7~\2\2\u0106\u0107\5\u0093J\2\u0107\u0108\5\u0099"+
		"M\2\u0108\u010a\3\2\2\2\u0109\u0104\3\2\2\2\u0109\u0106\3\2\2\2\u010a"+
		"8\3\2\2\2\u010b\u010c\5\u00a5S\2\u010c\u010d\5\u0093J\2\u010d\u010e\5"+
		"\u0099M\2\u010e:\3\2\2\2\u010f\u0110\5{>\2\u0110\u0111\5w<\2\u0111\u0112"+
		"\5\u009bN\2\u0112\u0113\5\177@\2\u0113<\3\2\2\2\u0114\u0115\5\u00a3R\2"+
		"\u0115\u0116\5\u0085C\2\u0116\u0117\5\177@\2\u0117\u0118\5\u0091I\2\u0118"+
		">\3\2\2\2\u0119\u011a\5\u009dO\2\u011a\u011b\5\u0085C\2\u011b\u011c\5"+
		"\177@\2\u011c\u011d\5\u0091I\2\u011d@\3\2\2\2\u011e\u011f\5\177@\2\u011f"+
		"\u0120\5\u008dG\2\u0120\u0121\5\u009bN\2\u0121\u0122\5\177@\2\u0122B\3"+
		"\2\2\2\u0123\u0124\5\177@\2\u0124\u0125\5\u0091I\2\u0125\u0126\5}?\2\u0126"+
		"D\3\2\2\2\u0127\u0128\5\u009dO\2\u0128\u0129\5\u0099M\2\u0129\u012a\5"+
		"\u009fP\2\u012a\u012b\5\177@\2\u012b\u0133\3\2\2\2\u012c\u012d\5\u0081"+
		"A\2\u012d\u012e\5w<\2\u012e\u012f\5\u008dG\2\u012f\u0130\5\u009bN\2\u0130"+
		"\u0131\5\177@\2\u0131\u0133\3\2\2\2\u0132\u0127\3\2\2\2\u0132\u012c\3"+
		"\2\2\2\u0133F\3\2\2\2\u0134\u0135\5\u0091I\2\u0135\u0136\5\u009fP\2\u0136"+
		"\u0137\5\u008dG\2\u0137\u0138\5\u008dG\2\u0138H\3\2\2\2\u0139\u013e\7"+
		"$\2\2\u013a\u013d\5g\64\2\u013b\u013d\13\2\2\2\u013c\u013a\3\2\2\2\u013c"+
		"\u013b\3\2\2\2\u013d\u0140\3\2\2\2\u013e\u013f\3\2\2\2\u013e\u013c\3\2"+
		"\2\2\u013f\u0141\3\2\2\2\u0140\u013e\3\2\2\2\u0141\u014c\7$\2\2\u0142"+
		"\u0147\7)\2\2\u0143\u0146\5i\65\2\u0144\u0146\13\2\2\2\u0145\u0143\3\2"+
		"\2\2\u0145\u0144\3\2\2\2\u0146\u0149\3\2\2\2\u0147\u0148\3\2\2\2\u0147"+
		"\u0145\3\2\2\2\u0148\u014a\3\2\2\2\u0149\u0147\3\2\2\2\u014a\u014c\7)"+
		"\2\2\u014b\u0139\3\2\2\2\u014b\u0142\3\2\2\2\u014cJ\3\2\2\2\u014d\u014f"+
		"\5m\67\2\u014e\u014d\3\2\2\2\u014f\u0150\3\2\2\2\u0150\u014e\3\2\2\2\u0150"+
		"\u0151\3\2\2\2\u0151L\3\2\2\2\u0152\u0154\5m\67\2\u0153\u0152\3\2\2\2"+
		"\u0154\u0155\3\2\2\2\u0155\u0153\3\2\2\2\u0155\u0156\3\2\2\2\u0156\u0157"+
		"\3\2\2\2\u0157\u0159\5]/\2\u0158\u015a\5m\67\2\u0159\u0158\3\2\2\2\u015a"+
		"\u015b\3\2\2\2\u015b\u0159\3\2\2\2\u015b\u015c\3\2\2\2\u015cN\3\2\2\2"+
		"\u015d\u0160\5a\61\2\u015e\u0161\5m\67\2\u015f\u0161\5k\66\2\u0160\u015e"+
		"\3\2\2\2\u0160\u015f\3\2\2\2\u0161\u0162\3\2\2\2\u0162\u0160\3\2\2\2\u0162"+
		"\u0163\3\2\2\2\u0163P\3\2\2\2\u0164\u0168\5k\66\2\u0165\u0168\5m\67\2"+
		"\u0166\u0168\5o8\2\u0167\u0164\3\2\2\2\u0167\u0165\3\2\2\2\u0167\u0166"+
		"\3\2\2\2\u0168\u0169\3\2\2\2\u0169\u0167\3\2\2\2\u0169\u016a\3\2\2\2\u016a"+
		"R\3\2\2\2\u016b\u016c\5_\60\2\u016c\u016d\5u;\2\u016d\u016e\5_\60\2\u016e"+
		"T\3\2\2\2\u016f\u0170\7\61\2\2\u0170\u0171\7,\2\2\u0171\u0172\7-\2\2\u0172"+
		"\u0176\3\2\2\2\u0173\u0175\13\2\2\2\u0174\u0173\3\2\2\2\u0175\u0178\3"+
		"\2\2\2\u0176\u0177\3\2\2\2\u0176\u0174\3\2\2\2\u0177\u0179\3\2\2\2\u0178"+
		"\u0176\3\2\2\2\u0179\u017a\7,\2\2\u017a\u017b\7\61\2\2\u017bV\3\2\2\2"+
		"\u017c\u017e\5s:\2\u017d\u017c\3\2\2\2\u017e\u017f\3\2\2\2\u017f\u017d"+
		"\3\2\2\2\u017f\u0180\3\2\2\2\u0180\u0181\3\2\2\2\u0181\u0182\b,\2\2\u0182"+
		"X\3\2\2\2\u0183\u0184\7\61\2\2\u0184\u0185\7\61\2\2\u0185\u0189\3\2\2"+
		"\2\u0186\u0188\n\3\2\2\u0187\u0186\3\2\2\2\u0188\u018b\3\2\2\2\u0189\u0187"+
		"\3\2\2\2\u0189\u018a\3\2\2\2\u018a\u018c\3\2\2\2\u018b\u0189\3\2\2\2\u018c"+
		"\u018d\b-\2\2\u018dZ\3\2\2\2\u018e\u018f\7\61\2\2\u018f\u0190\7,\2\2\u0190"+
		"\u0194\3\2\2\2\u0191\u0193\13\2\2\2\u0192\u0191\3\2\2\2\u0193\u0196\3"+
		"\2\2\2\u0194\u0195\3\2\2\2\u0194\u0192\3\2\2\2\u0195\u0197\3\2\2\2\u0196"+
		"\u0194\3\2\2\2\u0197\u0198\7,\2\2\u0198\u0199\7\61\2\2\u0199\u019a\3\2"+
		"\2\2\u019a\u019b\b.\3\2\u019b\\\3\2\2\2\u019c\u019d\7\60\2\2\u019d^\3"+
		"\2\2\2\u019e\u019f\7b\2\2\u019f`\3\2\2\2\u01a0\u01a1\7%\2\2\u01a1b\3\2"+
		"\2\2\u01a2\u01a3\7B\2\2\u01a3d\3\2\2\2\u01a4\u01a5\t\4\2\2\u01a5f\3\2"+
		"\2\2\u01a6\u01a7\7^\2\2\u01a7\u01ab\7$\2\2\u01a8\u01a9\7^\2\2\u01a9\u01ab"+
		"\7^\2\2\u01aa\u01a6\3\2\2\2\u01aa\u01a8\3\2\2\2\u01abh\3\2\2\2\u01ac\u01ad"+
		"\7^\2\2\u01ad\u01b1\7)\2\2\u01ae\u01af\7^\2\2\u01af\u01b1\7^\2\2\u01b0"+
		"\u01ac\3\2\2\2\u01b0\u01ae\3\2\2\2\u01b1j\3\2\2\2\u01b2\u01b3\t\5\2\2"+
		"\u01b3l\3\2\2\2\u01b4\u01b5\t\6\2\2\u01b5n\3\2\2\2\u01b6\u01b7\t\7\2\2"+
		"\u01b7p\3\2\2\2\u01b8\u01b9\t\3\2\2\u01b9r\3\2\2\2\u01ba\u01bb\t\b\2\2"+
		"\u01bbt\3\2\2\2\u01bc\u01be\13\2\2\2\u01bd\u01bc\3\2\2\2\u01be\u01bf\3"+
		"\2\2\2\u01bf\u01c0\3\2\2\2\u01bf\u01bd\3\2\2\2\u01c0v\3\2\2\2\u01c1\u01c2"+
		"\t\t\2\2\u01c2x\3\2\2\2\u01c3\u01c4\t\n\2\2\u01c4z\3\2\2\2\u01c5\u01c6"+
		"\t\13\2\2\u01c6|\3\2\2\2\u01c7\u01c8\t\f\2\2\u01c8~\3\2\2\2\u01c9\u01ca"+
		"\t\r\2\2\u01ca\u0080\3\2\2\2\u01cb\u01cc\t\16\2\2\u01cc\u0082\3\2\2\2"+
		"\u01cd\u01ce\t\17\2\2\u01ce\u0084\3\2\2\2\u01cf\u01d0\t\20\2\2\u01d0\u0086"+
		"\3\2\2\2\u01d1\u01d2\t\21\2\2\u01d2\u0088\3\2\2\2\u01d3\u01d4\t\22\2\2"+
		"\u01d4\u008a\3\2\2\2\u01d5\u01d6\t\23\2\2\u01d6\u008c\3\2\2\2\u01d7\u01d8"+
		"\t\24\2\2\u01d8\u008e\3\2\2\2\u01d9\u01da\t\25\2\2\u01da\u0090\3\2\2\2"+
		"\u01db\u01dc\t\26\2\2\u01dc\u0092\3\2\2\2\u01dd\u01de\t\27\2\2\u01de\u0094"+
		"\3\2\2\2\u01df\u01e0\t\30\2\2\u01e0\u0096\3\2\2\2\u01e1\u01e2\t\31\2\2"+
		"\u01e2\u0098\3\2\2\2\u01e3\u01e4\t\32\2\2\u01e4\u009a\3\2\2\2\u01e5\u01e6"+
		"\t\33\2\2\u01e6\u009c\3\2\2\2\u01e7\u01e8\t\34\2\2\u01e8\u009e\3\2\2\2"+
		"\u01e9\u01ea\t\35\2\2\u01ea\u00a0\3\2\2\2\u01eb\u01ec\t\36\2\2\u01ec\u00a2"+
		"\3\2\2\2\u01ed\u01ee\t\37\2\2\u01ee\u00a4\3\2\2\2\u01ef\u01f0\t \2\2\u01f0"+
		"\u00a6\3\2\2\2\u01f1\u01f2\t!\2\2\u01f2\u00a8\3\2\2\2\u01f3\u01f4\t\""+
		"\2\2\u01f4\u00aa\3\2\2\2\36\2\u00ca\u00d0\u00dd\u00e8\u00fa\u0102\u0109"+
		"\u0132\u013c\u013e\u0145\u0147\u014b\u0150\u0155\u015b\u0160\u0162\u0167"+
		"\u0169\u0176\u017f\u0189\u0194\u01aa\u01b0\u01bf\4\b\2\2\2\3\2";
	public static final ATN _ATN =
		new ATNDeserializer().deserialize(_serializedATN.toCharArray());
	static {
		_decisionToDFA = new DFA[_ATN.getNumberOfDecisions()];
		for (int i = 0; i < _ATN.getNumberOfDecisions(); i++) {
			_decisionToDFA[i] = new DFA(_ATN.getDecisionState(i), i);
		}
	}
}