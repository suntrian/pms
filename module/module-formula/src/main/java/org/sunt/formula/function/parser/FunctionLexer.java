// Generated from D:/projects/pms/module/module-formula/src/main/java/org/sunt/formula/function\Function.g4 by ANTLR 4.8
package org.sunt.formula.function.parser;

import org.antlr.v4.runtime.*;
import org.antlr.v4.runtime.atn.ATN;
import org.antlr.v4.runtime.atn.ATNDeserializer;
import org.antlr.v4.runtime.atn.LexerATNSimulator;
import org.antlr.v4.runtime.atn.PredictionContextCache;
import org.antlr.v4.runtime.dfa.DFA;

@SuppressWarnings({"all", "warnings", "unchecked", "unused", "cast"})
public class FunctionLexer extends Lexer {
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
    public static String[] channelNames = {
            "DEFAULT_TOKEN_CHANNEL", "HIDDEN"
    };

    public static String[] modeNames = {
            "DEFAULT_MODE"
    };

    private static String[] makeRuleNames() {
        return new String[]{
                "T__0", "VARARG", "ALIAS", "DT_STRING", "DT_DECIMAL", "DT_INTEGER", "DT_BOOLEAN",
                "DT_DATETIME", "DT_DATE", "DT_TIME", "DT_ANY", "COLON", "COMMA", "TRANSFER",
                "S_BLOCK", "D_BLOCK", "L_BRACKET", "R_BRACKET", "LS_BRACKET", "RS_BRACKET",
                "SYMBOL", "IDENTIFIER", "INTEGER", "NUMBER", "STRING", "WS", "LINE_COMMENT",
                "DESCRIPTION", "BLANK", "NL", "ESC_DQUOTE", "ESC_SQUOTE", "DIGIT", "ALPHA",
                "CHINESE", "ANY", "A", "B", "C", "D", "E", "F", "G", "H", "I", "J", "K",
                "L", "M", "N", "O", "P", "Q", "R", "S", "T", "U", "V", "W", "X", "Y",
                "Z"
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


    public FunctionLexer(CharStream input) {
        super(input);
        _interp = new LexerATNSimulator(this, _ATN, _decisionToDFA, _sharedContextCache);
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

    public static final String _serializedATN =
            "\3\u608b\ua72a\u8133\ub9ed\u417c\u3be7\u7786\u5964\2\36\u0176\b\1\4\2" +
                    "\t\2\4\3\t\3\4\4\t\4\4\5\t\5\4\6\t\6\4\7\t\7\4\b\t\b\4\t\t\t\4\n\t\n\4" +
                    "\13\t\13\4\f\t\f\4\r\t\r\4\16\t\16\4\17\t\17\4\20\t\20\4\21\t\21\4\22" +
                    "\t\22\4\23\t\23\4\24\t\24\4\25\t\25\4\26\t\26\4\27\t\27\4\30\t\30\4\31" +
                    "\t\31\4\32\t\32\4\33\t\33\4\34\t\34\4\35\t\35\4\36\t\36\4\37\t\37\4 \t" +
                    " \4!\t!\4\"\t\"\4#\t#\4$\t$\4%\t%\4&\t&\4\'\t\'\4(\t(\4)\t)\4*\t*\4+\t" +
                    "+\4,\t,\4-\t-\4.\t.\4/\t/\4\60\t\60\4\61\t\61\4\62\t\62\4\63\t\63\4\64" +
                    "\t\64\4\65\t\65\4\66\t\66\4\67\t\67\48\t8\49\t9\4:\t:\4;\t;\4<\t<\4=\t" +
                    "=\4>\t>\4?\t?\3\2\3\2\3\3\3\3\3\3\3\3\3\3\3\3\3\3\3\4\3\4\3\4\3\4\3\4" +
                    "\3\4\3\5\3\5\3\5\3\5\3\5\3\5\3\5\3\6\3\6\3\6\3\6\3\6\3\6\3\6\3\6\3\7\3" +
                    "\7\3\7\3\7\3\7\3\7\3\7\3\7\3\b\3\b\3\b\3\b\3\b\3\b\3\b\3\b\3\t\3\t\3\t" +
                    "\3\t\3\t\3\t\3\t\3\t\3\t\3\n\3\n\3\n\3\n\3\n\3\13\3\13\3\13\3\13\3\13" +
                    "\3\f\3\f\3\f\3\f\3\r\3\r\3\16\3\16\3\17\3\17\3\17\3\20\3\20\3\20\3\20" +
                    "\3\21\3\21\3\21\3\21\3\22\3\22\3\23\3\23\3\24\3\24\3\25\3\25\3\26\3\26" +
                    "\3\27\6\27\u00df\n\27\r\27\16\27\u00e0\3\30\6\30\u00e4\n\30\r\30\16\30" +
                    "\u00e5\3\31\6\31\u00e9\n\31\r\31\16\31\u00ea\3\31\3\31\6\31\u00ef\n\31" +
                    "\r\31\16\31\u00f0\3\32\3\32\3\32\7\32\u00f6\n\32\f\32\16\32\u00f9\13\32" +
                    "\3\32\3\32\3\32\3\32\7\32\u00ff\n\32\f\32\16\32\u0102\13\32\3\32\5\32" +
                    "\u0105\n\32\3\33\6\33\u0108\n\33\r\33\16\33\u0109\3\33\3\33\3\34\3\34" +
                    "\3\34\3\34\7\34\u0112\n\34\f\34\16\34\u0115\13\34\3\34\3\34\3\35\3\35" +
                    "\3\35\3\35\3\35\7\35\u011e\n\35\f\35\16\35\u0121\13\35\3\35\3\35\3\35" +
                    "\3\35\3\35\3\36\3\36\3\37\3\37\3 \3 \3 \3 \5 \u0130\n \3!\3!\3!\3!\5!" +
                    "\u0136\n!\3\"\3\"\3#\3#\3$\3$\3%\6%\u013f\n%\r%\16%\u0140\3&\3&\3\'\3" +
                    "\'\3(\3(\3)\3)\3*\3*\3+\3+\3,\3,\3-\3-\3.\3.\3/\3/\3\60\3\60\3\61\3\61" +
                    "\3\62\3\62\3\63\3\63\3\64\3\64\3\65\3\65\3\66\3\66\3\67\3\67\38\38\39" +
                    "\39\3:\3:\3;\3;\3<\3<\3=\3=\3>\3>\3?\3?\6\u00f7\u0100\u011f\u0140\2@\3" +
                    "\3\5\4\7\5\t\6\13\7\r\b\17\t\21\n\23\13\25\f\27\r\31\16\33\17\35\20\37" +
                    "\21!\22#\23%\24\'\25)\26+\27-\30/\31\61\32\63\33\65\34\67\359\36;\2=\2" +
                    "?\2A\2C\2E\2G\2I\2K\2M\2O\2Q\2S\2U\2W\2Y\2[\2]\2_\2a\2c\2e\2g\2i\2k\2" +
                    "m\2o\2q\2s\2u\2w\2y\2{\2}\2\3\2#\n\2*+..\60\60<=]]__}}\177\177\b\2&&\62" +
                    ";C\\aac|\u4e02\u9fa7\4\2\f\f\17\17\5\2\13\f\16\17\"\"\3\2\62;\5\2C\\a" +
                    "ac|\3\2\u4e02\u9fa7\4\2CCcc\4\2DDdd\4\2EEee\4\2FFff\4\2GGgg\4\2HHhh\4" +
                    "\2IIii\4\2JJjj\4\2KKkk\4\2LLll\4\2MMmm\4\2NNnn\4\2OOoo\4\2PPpp\4\2QQq" +
                    "q\4\2RRrr\4\2SSss\4\2TTtt\4\2UUuu\4\2VVvv\4\2WWww\4\2XXxx\4\2YYyy\4\2" +
                    "ZZzz\4\2[[{{\4\2\\\\||\2\u0162\2\3\3\2\2\2\2\5\3\2\2\2\2\7\3\2\2\2\2\t" +
                    "\3\2\2\2\2\13\3\2\2\2\2\r\3\2\2\2\2\17\3\2\2\2\2\21\3\2\2\2\2\23\3\2\2" +
                    "\2\2\25\3\2\2\2\2\27\3\2\2\2\2\31\3\2\2\2\2\33\3\2\2\2\2\35\3\2\2\2\2" +
                    "\37\3\2\2\2\2!\3\2\2\2\2#\3\2\2\2\2%\3\2\2\2\2\'\3\2\2\2\2)\3\2\2\2\2" +
                    "+\3\2\2\2\2-\3\2\2\2\2/\3\2\2\2\2\61\3\2\2\2\2\63\3\2\2\2\2\65\3\2\2\2" +
                    "\2\67\3\2\2\2\29\3\2\2\2\3\177\3\2\2\2\5\u0081\3\2\2\2\7\u0088\3\2\2\2" +
                    "\t\u008e\3\2\2\2\13\u0095\3\2\2\2\r\u009d\3\2\2\2\17\u00a5\3\2\2\2\21" +
                    "\u00ad\3\2\2\2\23\u00b6\3\2\2\2\25\u00bb\3\2\2\2\27\u00c0\3\2\2\2\31\u00c4" +
                    "\3\2\2\2\33\u00c6\3\2\2\2\35\u00c8\3\2\2\2\37\u00cb\3\2\2\2!\u00cf\3\2" +
                    "\2\2#\u00d3\3\2\2\2%\u00d5\3\2\2\2\'\u00d7\3\2\2\2)\u00d9\3\2\2\2+\u00db" +
                    "\3\2\2\2-\u00de\3\2\2\2/\u00e3\3\2\2\2\61\u00e8\3\2\2\2\63\u0104\3\2\2" +
                    "\2\65\u0107\3\2\2\2\67\u010d\3\2\2\29\u0118\3\2\2\2;\u0127\3\2\2\2=\u0129" +
                    "\3\2\2\2?\u012f\3\2\2\2A\u0135\3\2\2\2C\u0137\3\2\2\2E\u0139\3\2\2\2G" +
                    "\u013b\3\2\2\2I\u013e\3\2\2\2K\u0142\3\2\2\2M\u0144\3\2\2\2O\u0146\3\2" +
                    "\2\2Q\u0148\3\2\2\2S\u014a\3\2\2\2U\u014c\3\2\2\2W\u014e\3\2\2\2Y\u0150" +
                    "\3\2\2\2[\u0152\3\2\2\2]\u0154\3\2\2\2_\u0156\3\2\2\2a\u0158\3\2\2\2c" +
                    "\u015a\3\2\2\2e\u015c\3\2\2\2g\u015e\3\2\2\2i\u0160\3\2\2\2k\u0162\3\2" +
                    "\2\2m\u0164\3\2\2\2o\u0166\3\2\2\2q\u0168\3\2\2\2s\u016a\3\2\2\2u\u016c" +
                    "\3\2\2\2w\u016e\3\2\2\2y\u0170\3\2\2\2{\u0172\3\2\2\2}\u0174\3\2\2\2\177" +
                    "\u0080\7?\2\2\u0080\4\3\2\2\2\u0081\u0082\5u;\2\u0082\u0083\5K&\2\u0083" +
                    "\u0084\5m\67\2\u0084\u0085\5K&\2\u0085\u0086\5m\67\2\u0086\u0087\5W,\2" +
                    "\u0087\6\3\2\2\2\u0088\u0089\5K&\2\u0089\u008a\5a\61\2\u008a\u008b\5[" +
                    ".\2\u008b\u008c\5K&\2\u008c\u008d\5o8\2\u008d\b\3\2\2\2\u008e\u008f\5" +
                    "o8\2\u008f\u0090\5q9\2\u0090\u0091\5m\67\2\u0091\u0092\5[.\2\u0092\u0093" +
                    "\5e\63\2\u0093\u0094\5W,\2\u0094\n\3\2\2\2\u0095\u0096\5Q)\2\u0096\u0097" +
                    "\5S*\2\u0097\u0098\5O(\2\u0098\u0099\5[.\2\u0099\u009a\5c\62\2\u009a\u009b" +
                    "\5K&\2\u009b\u009c\5a\61\2\u009c\f\3\2\2\2\u009d\u009e\5[.\2\u009e\u009f" +
                    "\5e\63\2\u009f\u00a0\5q9\2\u00a0\u00a1\5S*\2\u00a1\u00a2\5W,\2\u00a2\u00a3" +
                    "\5S*\2\u00a3\u00a4\5m\67\2\u00a4\16\3\2\2\2\u00a5\u00a6\5M\'\2\u00a6\u00a7" +
                    "\5g\64\2\u00a7\u00a8\5g\64\2\u00a8\u00a9\5a\61\2\u00a9\u00aa\5S*\2\u00aa" +
                    "\u00ab\5K&\2\u00ab\u00ac\5e\63\2\u00ac\20\3\2\2\2\u00ad\u00ae\5Q)\2\u00ae" +
                    "\u00af\5K&\2\u00af\u00b0\5q9\2\u00b0\u00b1\5S*\2\u00b1\u00b2\5q9\2\u00b2" +
                    "\u00b3\5[.\2\u00b3\u00b4\5c\62\2\u00b4\u00b5\5S*\2\u00b5\22\3\2\2\2\u00b6" +
                    "\u00b7\5Q)\2\u00b7\u00b8\5K&\2\u00b8\u00b9\5q9\2\u00b9\u00ba\5S*\2\u00ba" +
                    "\24\3\2\2\2\u00bb\u00bc\5q9\2\u00bc\u00bd\5[.\2\u00bd\u00be\5c\62\2\u00be" +
                    "\u00bf\5S*\2\u00bf\26\3\2\2\2\u00c0\u00c1\5K&\2\u00c1\u00c2\5e\63\2\u00c2" +
                    "\u00c3\5{>\2\u00c3\30\3\2\2\2\u00c4\u00c5\7<\2\2\u00c5\32\3\2\2\2\u00c6" +
                    "\u00c7\7.\2\2\u00c7\34\3\2\2\2\u00c8\u00c9\7/\2\2\u00c9\u00ca\7@\2\2\u00ca" +
                    "\36\3\2\2\2\u00cb\u00cc\7)\2\2\u00cc\u00cd\7)\2\2\u00cd\u00ce\7)\2\2\u00ce" +
                    " \3\2\2\2\u00cf\u00d0\7$\2\2\u00d0\u00d1\7$\2\2\u00d1\u00d2\7$\2\2\u00d2" +
                    "\"\3\2\2\2\u00d3\u00d4\7*\2\2\u00d4$\3\2\2\2\u00d5\u00d6\7+\2\2\u00d6" +
                    "&\3\2\2\2\u00d7\u00d8\7]\2\2\u00d8(\3\2\2\2\u00d9\u00da\7_\2\2\u00da*" +
                    "\3\2\2\2\u00db\u00dc\t\2\2\2\u00dc,\3\2\2\2\u00dd\u00df\t\3\2\2\u00de" +
                    "\u00dd\3\2\2\2\u00df\u00e0\3\2\2\2\u00e0\u00de\3\2\2\2\u00e0\u00e1\3\2" +
                    "\2\2\u00e1.\3\2\2\2\u00e2\u00e4\5C\"\2\u00e3\u00e2\3\2\2\2\u00e4\u00e5" +
                    "\3\2\2\2\u00e5\u00e3\3\2\2\2\u00e5\u00e6\3\2\2\2\u00e6\60\3\2\2\2\u00e7" +
                    "\u00e9\5C\"\2\u00e8\u00e7\3\2\2\2\u00e9\u00ea\3\2\2\2\u00ea\u00e8\3\2" +
                    "\2\2\u00ea\u00eb\3\2\2\2\u00eb\u00ec\3\2\2\2\u00ec\u00ee\7\60\2\2\u00ed" +
                    "\u00ef\5C\"\2\u00ee\u00ed\3\2\2\2\u00ef\u00f0\3\2\2\2\u00f0\u00ee\3\2" +
                    "\2\2\u00f0\u00f1\3\2\2\2\u00f1\62\3\2\2\2\u00f2\u00f7\7$\2\2\u00f3\u00f6" +
                    "\5? \2\u00f4\u00f6\13\2\2\2\u00f5\u00f3\3\2\2\2\u00f5\u00f4\3\2\2\2\u00f6" +
                    "\u00f9\3\2\2\2\u00f7\u00f8\3\2\2\2\u00f7\u00f5\3\2\2\2\u00f8\u00fa\3\2" +
                    "\2\2\u00f9\u00f7\3\2\2\2\u00fa\u0105\7$\2\2\u00fb\u0100\7)\2\2\u00fc\u00ff" +
                    "\5A!\2\u00fd\u00ff\13\2\2\2\u00fe\u00fc\3\2\2\2\u00fe\u00fd\3\2\2\2\u00ff" +
                    "\u0102\3\2\2\2\u0100\u0101\3\2\2\2\u0100\u00fe\3\2\2\2\u0101\u0103\3\2" +
                    "\2\2\u0102\u0100\3\2\2\2\u0103\u0105\7)\2\2\u0104\u00f2\3\2\2\2\u0104" +
                    "\u00fb\3\2\2\2\u0105\64\3\2\2\2\u0106\u0108\5;\36\2\u0107\u0106\3\2\2" +
                    "\2\u0108\u0109\3\2\2\2\u0109\u0107\3\2\2\2\u0109\u010a\3\2\2\2\u010a\u010b" +
                    "\3\2\2\2\u010b\u010c\b\33\2\2\u010c\66\3\2\2\2\u010d\u010e\7\61\2\2\u010e" +
                    "\u010f\7\61\2\2\u010f\u0113\3\2\2\2\u0110\u0112\n\4\2\2\u0111\u0110\3" +
                    "\2\2\2\u0112\u0115\3\2\2\2\u0113\u0111\3\2\2\2\u0113\u0114\3\2\2\2\u0114" +
                    "\u0116\3\2\2\2\u0115\u0113\3\2\2\2\u0116\u0117\b\34\2\2\u01178\3\2\2\2" +
                    "\u0118\u0119\7\61\2\2\u0119\u011a\7,\2\2\u011a\u011b\7,\2\2\u011b\u011f" +
                    "\3\2\2\2\u011c\u011e\13\2\2\2\u011d\u011c\3\2\2\2\u011e\u0121\3\2\2\2" +
                    "\u011f\u0120\3\2\2\2\u011f\u011d\3\2\2\2\u0120\u0122\3\2\2\2\u0121\u011f" +
                    "\3\2\2\2\u0122\u0123\7,\2\2\u0123\u0124\7\61\2\2\u0124\u0125\3\2\2\2\u0125" +
                    "\u0126\b\35\3\2\u0126:\3\2\2\2\u0127\u0128\t\5\2\2\u0128<\3\2\2\2\u0129" +
                    "\u012a\t\4\2\2\u012a>\3\2\2\2\u012b\u012c\7^\2\2\u012c\u0130\7$\2\2\u012d" +
                    "\u012e\7^\2\2\u012e\u0130\7^\2\2\u012f\u012b\3\2\2\2\u012f\u012d\3\2\2" +
                    "\2\u0130@\3\2\2\2\u0131\u0132\7^\2\2\u0132\u0136\7)\2\2\u0133\u0134\7" +
                    "^\2\2\u0134\u0136\7^\2\2\u0135\u0131\3\2\2\2\u0135\u0133\3\2\2\2\u0136" +
                    "B\3\2\2\2\u0137\u0138\t\6\2\2\u0138D\3\2\2\2\u0139\u013a\t\7\2\2\u013a" +
                    "F\3\2\2\2\u013b\u013c\t\b\2\2\u013cH\3\2\2\2\u013d\u013f\13\2\2\2\u013e" +
                    "\u013d\3\2\2\2\u013f\u0140\3\2\2\2\u0140\u0141\3\2\2\2\u0140\u013e\3\2" +
                    "\2\2\u0141J\3\2\2\2\u0142\u0143\t\t\2\2\u0143L\3\2\2\2\u0144\u0145\t\n" +
                    "\2\2\u0145N\3\2\2\2\u0146\u0147\t\13\2\2\u0147P\3\2\2\2\u0148\u0149\t" +
                    "\f\2\2\u0149R\3\2\2\2\u014a\u014b\t\r\2\2\u014bT\3\2\2\2\u014c\u014d\t" +
                    "\16\2\2\u014dV\3\2\2\2\u014e\u014f\t\17\2\2\u014fX\3\2\2\2\u0150\u0151" +
                    "\t\20\2\2\u0151Z\3\2\2\2\u0152\u0153\t\21\2\2\u0153\\\3\2\2\2\u0154\u0155" +
                    "\t\22\2\2\u0155^\3\2\2\2\u0156\u0157\t\23\2\2\u0157`\3\2\2\2\u0158\u0159" +
                    "\t\24\2\2\u0159b\3\2\2\2\u015a\u015b\t\25\2\2\u015bd\3\2\2\2\u015c\u015d" +
                    "\t\26\2\2\u015df\3\2\2\2\u015e\u015f\t\27\2\2\u015fh\3\2\2\2\u0160\u0161" +
                    "\t\30\2\2\u0161j\3\2\2\2\u0162\u0163\t\31\2\2\u0163l\3\2\2\2\u0164\u0165" +
                    "\t\32\2\2\u0165n\3\2\2\2\u0166\u0167\t\33\2\2\u0167p\3\2\2\2\u0168\u0169" +
                    "\t\34\2\2\u0169r\3\2\2\2\u016a\u016b\t\35\2\2\u016bt\3\2\2\2\u016c\u016d" +
                    "\t\36\2\2\u016dv\3\2\2\2\u016e\u016f\t\37\2\2\u016fx\3\2\2\2\u0170\u0171" +
                    "\t \2\2\u0171z\3\2\2\2\u0172\u0173\t!\2\2\u0173|\3\2\2\2\u0174\u0175\t" +
                    "\"\2\2\u0175~\3\2\2\2\22\2\u00e0\u00e5\u00ea\u00f0\u00f5\u00f7\u00fe\u0100" +
                    "\u0104\u0109\u0113\u011f\u012f\u0135\u0140\4\b\2\2\2\3\2";
    public static final ATN _ATN =
            new ATNDeserializer().deserialize(_serializedATN.toCharArray());

    static {
        _decisionToDFA = new DFA[_ATN.getNumberOfDecisions()];
        for (int i = 0; i < _ATN.getNumberOfDecisions(); i++) {
            _decisionToDFA[i] = new DFA(_ATN.getDecisionState(i), i);
        }
    }
}