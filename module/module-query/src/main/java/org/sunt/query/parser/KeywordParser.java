// Generated from D:/projects/pms/module/module-query/src/main/java/org/sunt/query/parser\Keyword.g4 by ANTLR 4.9.1

package org.sunt.query.parser;

import org.antlr.v4.runtime.atn.*;
import org.antlr.v4.runtime.dfa.DFA;
import org.antlr.v4.runtime.*;
import org.antlr.v4.runtime.misc.*;
import org.antlr.v4.runtime.tree.*;
import java.util.List;
import java.util.Iterator;
import java.util.ArrayList;

@SuppressWarnings({"all", "warnings", "unchecked", "unused", "cast"})
public class KeywordParser extends Parser {
	static { RuntimeMetaData.checkVersion("4.9.1", RuntimeMetaData.VERSION); }

	protected static final DFA[] _decisionToDFA;
	protected static final PredictionContextCache _sharedContextCache =
		new PredictionContextCache();
	public static final int
		T__0=1, SEMICOLON=2, COUNT=3, COUNT_DISTINCT=4, SUM=5, AVG=6, GREATEST=7, 
		LEATEST=8, MAX=9, MIN=10, GROUP_YEAR=11, GROUP_QUARTER=12, GROUP_MONTH=13, 
		GROUP_WEEK=14, GROUP_DAY=15, GREATER=16, LESS=17, GREATER_EQUAL=18, LESS_EQUAL=19, 
		NOT_EQUAL=20, START_WITH=21, START=22, END_WITH=23, END=24, EQUAL=25, 
		CONTAINS=26, LAST_YEAR=27, LAST_QUARTER=28, LAST_MONTH=29, LAST_WEEK=30, 
		LAST_DAY=31, ORDER=32, ASC=33, DESC=34, REVERSE=35, INSIDE=36, BEFORE=37, 
		AFTER=38, AUX_ZAI=39, AUX_YI=40, AUX_HE=41, AUX_DE=42, AUX_MIN=43, L_PARENTHESES=44, 
		R_PARENTHESES=45, L_BRACE=46, R_BRACE=47, L_SQUARE=48, R_SQUARE=49, COMMA=50, 
		COLON=51, MUL=52, DIV=53, PLUS=54, MINUS=55, POWER=56, MOD=57, IFNULL=58, 
		IF=59, LIKE=60, IN=61, NOT=62, AND=63, OR=64, XOR=65, CASE=66, WHEN=67, 
		THEN=68, ELSE=69, BOOL=70, NULL=71, STRING=72, INTEGER=73, FLOAT=74, COLUMN_ID=75, 
		IDENTITY=76, COLUMN_NAME=77, HINT=78, WS=79, LINE_COMMENT=80, BLOCK_COMMENT=81;
	public static final int
		RULE_keywords = 0, RULE_keyword = 1, RULE_segment = 2, RULE_aggregate = 3, 
		RULE_group = 4, RULE_filter = 5, RULE_sort = 6, RULE_limit = 7, RULE_nonReserved = 8, 
		RULE_formula = 9, RULE_statement = 10, RULE_functionStatement = 11, RULE_functionParams = 12, 
		RULE_functionParam = 13, RULE_statements = 14, RULE_caseStatement = 15, 
		RULE_ifSpecial = 16, RULE_ifFunction = 17, RULE_ifStatement = 18, RULE_elseIfStatement = 19, 
		RULE_elseStatement = 20, RULE_predictStatement = 21, RULE_constant = 22, 
		RULE_column = 23, RULE_identity = 24;
	private static String[] makeRuleNames() {
		return new String[] {
			"keywords", "keyword", "segment", "aggregate", "group", "filter", "sort", 
			"limit", "nonReserved", "formula", "statement", "functionStatement", 
			"functionParams", "functionParam", "statements", "caseStatement", "ifSpecial", 
			"ifFunction", "ifStatement", "elseIfStatement", "elseStatement", "predictStatement", 
			"constant", "column", "identity"
		};
	}
	public static final String[] ruleNames = makeRuleNames();

	private static String[] makeLiteralNames() {
		return new String[] {
			null, "'\u4ECB\u4E8E'", "';'", null, null, null, null, null, null, null, 
			null, null, null, null, null, null, null, null, null, null, null, null, 
			null, null, null, null, "'\u5305\u542B'", null, "'\u5728\u4E0A\u5B63\u5EA6'", 
			null, null, null, null, null, null, "'\u5012\u6570'", null, null, null, 
			"'\u5728'", null, null, "'\u7684'", null, "'('", "')'", "'{'", "'}'", 
			"'['", "']'", "','", "':'", "'*'", "'/'", "'+'", "'-'", "'^'"
		};
	}
	private static final String[] _LITERAL_NAMES = makeLiteralNames();
	private static String[] makeSymbolicNames() {
		return new String[] {
			null, null, "SEMICOLON", "COUNT", "COUNT_DISTINCT", "SUM", "AVG", "GREATEST", 
			"LEATEST", "MAX", "MIN", "GROUP_YEAR", "GROUP_QUARTER", "GROUP_MONTH", 
			"GROUP_WEEK", "GROUP_DAY", "GREATER", "LESS", "GREATER_EQUAL", "LESS_EQUAL", 
			"NOT_EQUAL", "START_WITH", "START", "END_WITH", "END", "EQUAL", "CONTAINS", 
			"LAST_YEAR", "LAST_QUARTER", "LAST_MONTH", "LAST_WEEK", "LAST_DAY", "ORDER", 
			"ASC", "DESC", "REVERSE", "INSIDE", "BEFORE", "AFTER", "AUX_ZAI", "AUX_YI", 
			"AUX_HE", "AUX_DE", "AUX_MIN", "L_PARENTHESES", "R_PARENTHESES", "L_BRACE", 
			"R_BRACE", "L_SQUARE", "R_SQUARE", "COMMA", "COLON", "MUL", "DIV", "PLUS", 
			"MINUS", "POWER", "MOD", "IFNULL", "IF", "LIKE", "IN", "NOT", "AND", 
			"OR", "XOR", "CASE", "WHEN", "THEN", "ELSE", "BOOL", "NULL", "STRING", 
			"INTEGER", "FLOAT", "COLUMN_ID", "IDENTITY", "COLUMN_NAME", "HINT", "WS", 
			"LINE_COMMENT", "BLOCK_COMMENT"
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
	public String getGrammarFileName() { return "Keyword.g4"; }

	@Override
	public String[] getRuleNames() { return ruleNames; }

	@Override
	public String getSerializedATN() { return _serializedATN; }

	@Override
	public ATN getATN() { return _ATN; }

	public KeywordParser(TokenStream input) {
		super(input);
		_interp = new ParserATNSimulator(this,_ATN,_decisionToDFA,_sharedContextCache);
	}

	public static class KeywordsContext extends ParserRuleContext {
		public TerminalNode EOF() { return getToken(KeywordParser.EOF, 0); }
		public List<KeywordContext> keyword() {
			return getRuleContexts(KeywordContext.class);
		}
		public KeywordContext keyword(int i) {
			return getRuleContext(KeywordContext.class,i);
		}
		public List<TerminalNode> SEMICOLON() { return getTokens(KeywordParser.SEMICOLON); }
		public TerminalNode SEMICOLON(int i) {
			return getToken(KeywordParser.SEMICOLON, i);
		}
		public KeywordsContext(ParserRuleContext parent, int invokingState) {
			super(parent, invokingState);
		}
		@Override public int getRuleIndex() { return RULE_keywords; }
		@Override
		public <T> T accept(ParseTreeVisitor<? extends T> visitor) {
			if ( visitor instanceof KeywordVisitor ) return ((KeywordVisitor<? extends T>)visitor).visitKeywords(this);
			else return visitor.visitChildren(this);
		}
	}

	public final KeywordsContext keywords() throws RecognitionException {
		KeywordsContext _localctx = new KeywordsContext(_ctx, getState());
		enterRule(_localctx, 0, RULE_keywords);
		int _la;
		try {
			enterOuterAlt(_localctx, 1);
			{
			setState(54); 
			_errHandler.sync(this);
			_la = _input.LA(1);
			do {
				{
				{
				setState(50);
				keyword();
				setState(52);
				_errHandler.sync(this);
				_la = _input.LA(1);
				if (_la==SEMICOLON) {
					{
					setState(51);
					match(SEMICOLON);
					}
				}

				}
				}
				setState(56); 
				_errHandler.sync(this);
				_la = _input.LA(1);
			} while ( (((_la) & ~0x3f) == 0 && ((1L << _la) & ((1L << COUNT) | (1L << COUNT_DISTINCT) | (1L << SUM) | (1L << AVG) | (1L << GREATEST) | (1L << LEATEST) | (1L << MAX) | (1L << MIN) | (1L << GROUP_YEAR) | (1L << GROUP_QUARTER) | (1L << GROUP_MONTH) | (1L << GROUP_WEEK) | (1L << GROUP_DAY) | (1L << EQUAL) | (1L << ORDER) | (1L << AUX_ZAI) | (1L << AUX_YI) | (1L << AUX_HE) | (1L << AUX_DE) | (1L << AUX_MIN) | (1L << L_PARENTHESES) | (1L << L_SQUARE) | (1L << PLUS) | (1L << MINUS) | (1L << IF) | (1L << LIKE) | (1L << NOT))) != 0) || ((((_la - 66)) & ~0x3f) == 0 && ((1L << (_la - 66)) & ((1L << (CASE - 66)) | (1L << (BOOL - 66)) | (1L << (NULL - 66)) | (1L << (STRING - 66)) | (1L << (INTEGER - 66)) | (1L << (FLOAT - 66)) | (1L << (COLUMN_ID - 66)) | (1L << (IDENTITY - 66)) | (1L << (COLUMN_NAME - 66)))) != 0) );
			setState(58);
			match(EOF);
			}
		}
		catch (RecognitionException re) {
			_localctx.exception = re;
			_errHandler.reportError(this, re);
			_errHandler.recover(this, re);
		}
		finally {
			exitRule();
		}
		return _localctx;
	}

	public static class KeywordContext extends ParserRuleContext {
		public SortContext sort() {
			return getRuleContext(SortContext.class,0);
		}
		public AggregateContext aggregate() {
			return getRuleContext(AggregateContext.class,0);
		}
		public FilterContext filter() {
			return getRuleContext(FilterContext.class,0);
		}
		public GroupContext group() {
			return getRuleContext(GroupContext.class,0);
		}
		public LimitContext limit() {
			return getRuleContext(LimitContext.class,0);
		}
		public SegmentContext segment() {
			return getRuleContext(SegmentContext.class,0);
		}
		public KeywordContext(ParserRuleContext parent, int invokingState) {
			super(parent, invokingState);
		}
		@Override public int getRuleIndex() { return RULE_keyword; }
		@Override
		public <T> T accept(ParseTreeVisitor<? extends T> visitor) {
			if ( visitor instanceof KeywordVisitor ) return ((KeywordVisitor<? extends T>)visitor).visitKeyword(this);
			else return visitor.visitChildren(this);
		}
	}

	public final KeywordContext keyword() throws RecognitionException {
		KeywordContext _localctx = new KeywordContext(_ctx, getState());
		enterRule(_localctx, 2, RULE_keyword);
		try {
			setState(66);
			_errHandler.sync(this);
			switch ( getInterpreter().adaptivePredict(_input,2,_ctx) ) {
			case 1:
				enterOuterAlt(_localctx, 1);
				{
				setState(60);
				sort();
				}
				break;
			case 2:
				enterOuterAlt(_localctx, 2);
				{
				setState(61);
				aggregate();
				}
				break;
			case 3:
				enterOuterAlt(_localctx, 3);
				{
				setState(62);
				filter();
				}
				break;
			case 4:
				enterOuterAlt(_localctx, 4);
				{
				setState(63);
				group();
				}
				break;
			case 5:
				enterOuterAlt(_localctx, 5);
				{
				setState(64);
				limit();
				}
				break;
			case 6:
				enterOuterAlt(_localctx, 6);
				{
				setState(65);
				segment();
				}
				break;
			}
		}
		catch (RecognitionException re) {
			_localctx.exception = re;
			_errHandler.reportError(this, re);
			_errHandler.recover(this, re);
		}
		finally {
			exitRule();
		}
		return _localctx;
	}

	public static class SegmentContext extends ParserRuleContext {
		public List<StatementContext> statement() {
			return getRuleContexts(StatementContext.class);
		}
		public StatementContext statement(int i) {
			return getRuleContext(StatementContext.class,i);
		}
		public List<NonReservedContext> nonReserved() {
			return getRuleContexts(NonReservedContext.class);
		}
		public NonReservedContext nonReserved(int i) {
			return getRuleContext(NonReservedContext.class,i);
		}
		public TerminalNode HINT() { return getToken(KeywordParser.HINT, 0); }
		public SegmentContext(ParserRuleContext parent, int invokingState) {
			super(parent, invokingState);
		}
		@Override public int getRuleIndex() { return RULE_segment; }
		@Override
		public <T> T accept(ParseTreeVisitor<? extends T> visitor) {
			if ( visitor instanceof KeywordVisitor ) return ((KeywordVisitor<? extends T>)visitor).visitSegment(this);
			else return visitor.visitChildren(this);
		}
	}

	public final SegmentContext segment() throws RecognitionException {
		SegmentContext _localctx = new SegmentContext(_ctx, getState());
		enterRule(_localctx, 4, RULE_segment);
		int _la;
		try {
			int _alt;
			enterOuterAlt(_localctx, 1);
			{
			setState(70); 
			_errHandler.sync(this);
			_alt = 1;
			do {
				switch (_alt) {
				case 1:
					{
					setState(70);
					_errHandler.sync(this);
					switch (_input.LA(1)) {
					case L_PARENTHESES:
					case L_SQUARE:
					case PLUS:
					case MINUS:
					case IF:
					case LIKE:
					case NOT:
					case CASE:
					case BOOL:
					case NULL:
					case STRING:
					case INTEGER:
					case FLOAT:
					case COLUMN_ID:
					case IDENTITY:
					case COLUMN_NAME:
						{
						setState(68);
						statement(0);
						}
						break;
					case COUNT:
					case COUNT_DISTINCT:
					case SUM:
					case AVG:
					case GREATEST:
					case LEATEST:
					case MAX:
					case MIN:
					case GROUP_YEAR:
					case GROUP_QUARTER:
					case GROUP_MONTH:
					case GROUP_WEEK:
					case GROUP_DAY:
					case EQUAL:
					case ORDER:
					case AUX_ZAI:
					case AUX_YI:
					case AUX_HE:
					case AUX_DE:
					case AUX_MIN:
						{
						setState(69);
						nonReserved();
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
				setState(72); 
				_errHandler.sync(this);
				_alt = getInterpreter().adaptivePredict(_input,4,_ctx);
			} while ( _alt!=2 && _alt!= ATN.INVALID_ALT_NUMBER );
			setState(75);
			_errHandler.sync(this);
			_la = _input.LA(1);
			if (_la==HINT) {
				{
				setState(74);
				match(HINT);
				}
			}

			}
		}
		catch (RecognitionException re) {
			_localctx.exception = re;
			_errHandler.reportError(this, re);
			_errHandler.recover(this, re);
		}
		finally {
			exitRule();
		}
		return _localctx;
	}

	public static class AggregateContext extends ParserRuleContext {
		public SegmentContext segment() {
			return getRuleContext(SegmentContext.class,0);
		}
		public TerminalNode COUNT() { return getToken(KeywordParser.COUNT, 0); }
		public TerminalNode COUNT_DISTINCT() { return getToken(KeywordParser.COUNT_DISTINCT, 0); }
		public TerminalNode SUM() { return getToken(KeywordParser.SUM, 0); }
		public TerminalNode AVG() { return getToken(KeywordParser.AVG, 0); }
		public TerminalNode MAX() { return getToken(KeywordParser.MAX, 0); }
		public TerminalNode MIN() { return getToken(KeywordParser.MIN, 0); }
		public TerminalNode GREATEST() { return getToken(KeywordParser.GREATEST, 0); }
		public TerminalNode LEATEST() { return getToken(KeywordParser.LEATEST, 0); }
		public AggregateContext(ParserRuleContext parent, int invokingState) {
			super(parent, invokingState);
		}
		@Override public int getRuleIndex() { return RULE_aggregate; }
		@Override
		public <T> T accept(ParseTreeVisitor<? extends T> visitor) {
			if ( visitor instanceof KeywordVisitor ) return ((KeywordVisitor<? extends T>)visitor).visitAggregate(this);
			else return visitor.visitChildren(this);
		}
	}

	public final AggregateContext aggregate() throws RecognitionException {
		AggregateContext _localctx = new AggregateContext(_ctx, getState());
		enterRule(_localctx, 6, RULE_aggregate);
		int _la;
		try {
			setState(84);
			_errHandler.sync(this);
			switch ( getInterpreter().adaptivePredict(_input,6,_ctx) ) {
			case 1:
				enterOuterAlt(_localctx, 1);
				{
				setState(77);
				segment();
				setState(78);
				_la = _input.LA(1);
				if ( !((((_la) & ~0x3f) == 0 && ((1L << _la) & ((1L << COUNT) | (1L << COUNT_DISTINCT) | (1L << SUM) | (1L << AVG) | (1L << MAX) | (1L << MIN))) != 0)) ) {
				_errHandler.recoverInline(this);
				}
				else {
					if ( _input.LA(1)==Token.EOF ) matchedEOF = true;
					_errHandler.reportMatch(this);
					consume();
				}
				}
				break;
			case 2:
				enterOuterAlt(_localctx, 2);
				{
				setState(80);
				match(GREATEST);
				setState(81);
				segment();
				}
				break;
			case 3:
				enterOuterAlt(_localctx, 3);
				{
				setState(82);
				match(LEATEST);
				setState(83);
				segment();
				}
				break;
			}
		}
		catch (RecognitionException re) {
			_localctx.exception = re;
			_errHandler.reportError(this, re);
			_errHandler.recover(this, re);
		}
		finally {
			exitRule();
		}
		return _localctx;
	}

	public static class GroupContext extends ParserRuleContext {
		public SegmentContext segment() {
			return getRuleContext(SegmentContext.class,0);
		}
		public TerminalNode GROUP_YEAR() { return getToken(KeywordParser.GROUP_YEAR, 0); }
		public TerminalNode GROUP_QUARTER() { return getToken(KeywordParser.GROUP_QUARTER, 0); }
		public TerminalNode GROUP_MONTH() { return getToken(KeywordParser.GROUP_MONTH, 0); }
		public TerminalNode GROUP_WEEK() { return getToken(KeywordParser.GROUP_WEEK, 0); }
		public TerminalNode GROUP_DAY() { return getToken(KeywordParser.GROUP_DAY, 0); }
		public GroupContext(ParserRuleContext parent, int invokingState) {
			super(parent, invokingState);
		}
		@Override public int getRuleIndex() { return RULE_group; }
		@Override
		public <T> T accept(ParseTreeVisitor<? extends T> visitor) {
			if ( visitor instanceof KeywordVisitor ) return ((KeywordVisitor<? extends T>)visitor).visitGroup(this);
			else return visitor.visitChildren(this);
		}
	}

	public final GroupContext group() throws RecognitionException {
		GroupContext _localctx = new GroupContext(_ctx, getState());
		enterRule(_localctx, 8, RULE_group);
		int _la;
		try {
			enterOuterAlt(_localctx, 1);
			{
			setState(86);
			segment();
			setState(87);
			_la = _input.LA(1);
			if ( !((((_la) & ~0x3f) == 0 && ((1L << _la) & ((1L << GROUP_YEAR) | (1L << GROUP_QUARTER) | (1L << GROUP_MONTH) | (1L << GROUP_WEEK) | (1L << GROUP_DAY))) != 0)) ) {
			_errHandler.recoverInline(this);
			}
			else {
				if ( _input.LA(1)==Token.EOF ) matchedEOF = true;
				_errHandler.reportMatch(this);
				consume();
			}
			}
		}
		catch (RecognitionException re) {
			_localctx.exception = re;
			_errHandler.reportError(this, re);
			_errHandler.recover(this, re);
		}
		finally {
			exitRule();
		}
		return _localctx;
	}

	public static class FilterContext extends ParserRuleContext {
		public FilterContext(ParserRuleContext parent, int invokingState) {
			super(parent, invokingState);
		}
		@Override public int getRuleIndex() { return RULE_filter; }
	 
		public FilterContext() { }
		public void copyFrom(FilterContext ctx) {
			super.copyFrom(ctx);
		}
	}
	public static class CompareDateFilterContext extends FilterContext {
		public List<SegmentContext> segment() {
			return getRuleContexts(SegmentContext.class);
		}
		public SegmentContext segment(int i) {
			return getRuleContext(SegmentContext.class,i);
		}
		public TerminalNode AUX_ZAI() { return getToken(KeywordParser.AUX_ZAI, 0); }
		public TerminalNode BEFORE() { return getToken(KeywordParser.BEFORE, 0); }
		public TerminalNode AFTER() { return getToken(KeywordParser.AFTER, 0); }
		public CompareDateFilterContext(FilterContext ctx) { copyFrom(ctx); }
		@Override
		public <T> T accept(ParseTreeVisitor<? extends T> visitor) {
			if ( visitor instanceof KeywordVisitor ) return ((KeywordVisitor<? extends T>)visitor).visitCompareDateFilter(this);
			else return visitor.visitChildren(this);
		}
	}
	public static class MostFilterContext extends FilterContext {
		public SegmentContext segment() {
			return getRuleContext(SegmentContext.class,0);
		}
		public TerminalNode GREATEST() { return getToken(KeywordParser.GREATEST, 0); }
		public TerminalNode LEATEST() { return getToken(KeywordParser.LEATEST, 0); }
		public MostFilterContext(FilterContext ctx) { copyFrom(ctx); }
		@Override
		public <T> T accept(ParseTreeVisitor<? extends T> visitor) {
			if ( visitor instanceof KeywordVisitor ) return ((KeywordVisitor<? extends T>)visitor).visitMostFilter(this);
			else return visitor.visitChildren(this);
		}
	}
	public static class BetweenFilterContext extends FilterContext {
		public List<SegmentContext> segment() {
			return getRuleContexts(SegmentContext.class);
		}
		public SegmentContext segment(int i) {
			return getRuleContext(SegmentContext.class,i);
		}
		public TerminalNode AUX_HE() { return getToken(KeywordParser.AUX_HE, 0); }
		public TerminalNode INSIDE() { return getToken(KeywordParser.INSIDE, 0); }
		public TerminalNode AUX_ZAI() { return getToken(KeywordParser.AUX_ZAI, 0); }
		public BetweenFilterContext(FilterContext ctx) { copyFrom(ctx); }
		@Override
		public <T> T accept(ParseTreeVisitor<? extends T> visitor) {
			if ( visitor instanceof KeywordVisitor ) return ((KeywordVisitor<? extends T>)visitor).visitBetweenFilter(this);
			else return visitor.visitChildren(this);
		}
	}
	public static class CompareFilterContext extends FilterContext {
		public List<SegmentContext> segment() {
			return getRuleContexts(SegmentContext.class);
		}
		public SegmentContext segment(int i) {
			return getRuleContext(SegmentContext.class,i);
		}
		public TerminalNode GREATER() { return getToken(KeywordParser.GREATER, 0); }
		public TerminalNode LESS() { return getToken(KeywordParser.LESS, 0); }
		public TerminalNode GREATER_EQUAL() { return getToken(KeywordParser.GREATER_EQUAL, 0); }
		public TerminalNode LESS_EQUAL() { return getToken(KeywordParser.LESS_EQUAL, 0); }
		public TerminalNode EQUAL() { return getToken(KeywordParser.EQUAL, 0); }
		public TerminalNode NOT_EQUAL() { return getToken(KeywordParser.NOT_EQUAL, 0); }
		public TerminalNode CONTAINS() { return getToken(KeywordParser.CONTAINS, 0); }
		public CompareFilterContext(FilterContext ctx) { copyFrom(ctx); }
		@Override
		public <T> T accept(ParseTreeVisitor<? extends T> visitor) {
			if ( visitor instanceof KeywordVisitor ) return ((KeywordVisitor<? extends T>)visitor).visitCompareFilter(this);
			else return visitor.visitChildren(this);
		}
	}
	public static class StringFilterContext extends FilterContext {
		public List<SegmentContext> segment() {
			return getRuleContexts(SegmentContext.class);
		}
		public SegmentContext segment(int i) {
			return getRuleContext(SegmentContext.class,i);
		}
		public TerminalNode START_WITH() { return getToken(KeywordParser.START_WITH, 0); }
		public TerminalNode END_WITH() { return getToken(KeywordParser.END_WITH, 0); }
		public TerminalNode AUX_YI() { return getToken(KeywordParser.AUX_YI, 0); }
		public TerminalNode START() { return getToken(KeywordParser.START, 0); }
		public TerminalNode END() { return getToken(KeywordParser.END, 0); }
		public StringFilterContext(FilterContext ctx) { copyFrom(ctx); }
		@Override
		public <T> T accept(ParseTreeVisitor<? extends T> visitor) {
			if ( visitor instanceof KeywordVisitor ) return ((KeywordVisitor<? extends T>)visitor).visitStringFilter(this);
			else return visitor.visitChildren(this);
		}
	}
	public static class LastDateFilterContext extends FilterContext {
		public SegmentContext segment() {
			return getRuleContext(SegmentContext.class,0);
		}
		public TerminalNode LAST_YEAR() { return getToken(KeywordParser.LAST_YEAR, 0); }
		public TerminalNode LAST_QUARTER() { return getToken(KeywordParser.LAST_QUARTER, 0); }
		public TerminalNode LAST_MONTH() { return getToken(KeywordParser.LAST_MONTH, 0); }
		public TerminalNode LAST_WEEK() { return getToken(KeywordParser.LAST_WEEK, 0); }
		public TerminalNode LAST_DAY() { return getToken(KeywordParser.LAST_DAY, 0); }
		public LastDateFilterContext(FilterContext ctx) { copyFrom(ctx); }
		@Override
		public <T> T accept(ParseTreeVisitor<? extends T> visitor) {
			if ( visitor instanceof KeywordVisitor ) return ((KeywordVisitor<? extends T>)visitor).visitLastDateFilter(this);
			else return visitor.visitChildren(this);
		}
	}

	public final FilterContext filter() throws RecognitionException {
		FilterContext _localctx = new FilterContext(_ctx, getState());
		enterRule(_localctx, 10, RULE_filter);
		int _la;
		try {
			setState(120);
			_errHandler.sync(this);
			switch ( getInterpreter().adaptivePredict(_input,7,_ctx) ) {
			case 1:
				_localctx = new MostFilterContext(_localctx);
				enterOuterAlt(_localctx, 1);
				{
				setState(89);
				segment();
				setState(90);
				_la = _input.LA(1);
				if ( !(_la==GREATEST || _la==LEATEST) ) {
				_errHandler.recoverInline(this);
				}
				else {
					if ( _input.LA(1)==Token.EOF ) matchedEOF = true;
					_errHandler.reportMatch(this);
					consume();
				}
				}
				break;
			case 2:
				_localctx = new CompareFilterContext(_localctx);
				enterOuterAlt(_localctx, 2);
				{
				setState(92);
				segment();
				setState(93);
				_la = _input.LA(1);
				if ( !((((_la) & ~0x3f) == 0 && ((1L << _la) & ((1L << GREATER) | (1L << LESS) | (1L << GREATER_EQUAL) | (1L << LESS_EQUAL) | (1L << NOT_EQUAL) | (1L << EQUAL) | (1L << CONTAINS))) != 0)) ) {
				_errHandler.recoverInline(this);
				}
				else {
					if ( _input.LA(1)==Token.EOF ) matchedEOF = true;
					_errHandler.reportMatch(this);
					consume();
				}
				setState(94);
				segment();
				}
				break;
			case 3:
				_localctx = new BetweenFilterContext(_localctx);
				enterOuterAlt(_localctx, 3);
				{
				setState(96);
				segment();
				setState(97);
				_la = _input.LA(1);
				if ( !(_la==T__0 || _la==AUX_ZAI) ) {
				_errHandler.recoverInline(this);
				}
				else {
					if ( _input.LA(1)==Token.EOF ) matchedEOF = true;
					_errHandler.reportMatch(this);
					consume();
				}
				setState(98);
				segment();
				setState(99);
				match(AUX_HE);
				setState(100);
				segment();
				setState(101);
				match(INSIDE);
				}
				break;
			case 4:
				_localctx = new StringFilterContext(_localctx);
				enterOuterAlt(_localctx, 4);
				{
				setState(103);
				segment();
				setState(104);
				_la = _input.LA(1);
				if ( !(_la==START_WITH || _la==END_WITH) ) {
				_errHandler.recoverInline(this);
				}
				else {
					if ( _input.LA(1)==Token.EOF ) matchedEOF = true;
					_errHandler.reportMatch(this);
					consume();
				}
				setState(105);
				segment();
				}
				break;
			case 5:
				_localctx = new StringFilterContext(_localctx);
				enterOuterAlt(_localctx, 5);
				{
				setState(107);
				segment();
				setState(108);
				match(AUX_YI);
				setState(109);
				segment();
				setState(110);
				_la = _input.LA(1);
				if ( !(_la==START || _la==END) ) {
				_errHandler.recoverInline(this);
				}
				else {
					if ( _input.LA(1)==Token.EOF ) matchedEOF = true;
					_errHandler.reportMatch(this);
					consume();
				}
				}
				break;
			case 6:
				_localctx = new LastDateFilterContext(_localctx);
				enterOuterAlt(_localctx, 6);
				{
				setState(112);
				segment();
				setState(113);
				_la = _input.LA(1);
				if ( !((((_la) & ~0x3f) == 0 && ((1L << _la) & ((1L << LAST_YEAR) | (1L << LAST_QUARTER) | (1L << LAST_MONTH) | (1L << LAST_WEEK) | (1L << LAST_DAY))) != 0)) ) {
				_errHandler.recoverInline(this);
				}
				else {
					if ( _input.LA(1)==Token.EOF ) matchedEOF = true;
					_errHandler.reportMatch(this);
					consume();
				}
				}
				break;
			case 7:
				_localctx = new CompareDateFilterContext(_localctx);
				enterOuterAlt(_localctx, 7);
				{
				setState(115);
				segment();
				setState(116);
				match(AUX_ZAI);
				setState(117);
				segment();
				setState(118);
				_la = _input.LA(1);
				if ( !(_la==BEFORE || _la==AFTER) ) {
				_errHandler.recoverInline(this);
				}
				else {
					if ( _input.LA(1)==Token.EOF ) matchedEOF = true;
					_errHandler.reportMatch(this);
					consume();
				}
				}
				break;
			}
		}
		catch (RecognitionException re) {
			_localctx.exception = re;
			_errHandler.reportError(this, re);
			_errHandler.recover(this, re);
		}
		finally {
			exitRule();
		}
		return _localctx;
	}

	public static class SortContext extends ParserRuleContext {
		public SegmentContext segment() {
			return getRuleContext(SegmentContext.class,0);
		}
		public TerminalNode ASC() { return getToken(KeywordParser.ASC, 0); }
		public TerminalNode DESC() { return getToken(KeywordParser.DESC, 0); }
		public TerminalNode AUX_YI() { return getToken(KeywordParser.AUX_YI, 0); }
		public SortContext(ParserRuleContext parent, int invokingState) {
			super(parent, invokingState);
		}
		@Override public int getRuleIndex() { return RULE_sort; }
		@Override
		public <T> T accept(ParseTreeVisitor<? extends T> visitor) {
			if ( visitor instanceof KeywordVisitor ) return ((KeywordVisitor<? extends T>)visitor).visitSort(this);
			else return visitor.visitChildren(this);
		}
	}

	public final SortContext sort() throws RecognitionException {
		SortContext _localctx = new SortContext(_ctx, getState());
		enterRule(_localctx, 12, RULE_sort);
		int _la;
		try {
			enterOuterAlt(_localctx, 1);
			{
			setState(123);
			_errHandler.sync(this);
			switch ( getInterpreter().adaptivePredict(_input,8,_ctx) ) {
			case 1:
				{
				setState(122);
				match(AUX_YI);
				}
				break;
			}
			setState(125);
			segment();
			setState(126);
			_la = _input.LA(1);
			if ( !(_la==ASC || _la==DESC) ) {
			_errHandler.recoverInline(this);
			}
			else {
				if ( _input.LA(1)==Token.EOF ) matchedEOF = true;
				_errHandler.reportMatch(this);
				consume();
			}
			}
		}
		catch (RecognitionException re) {
			_localctx.exception = re;
			_errHandler.reportError(this, re);
			_errHandler.recover(this, re);
		}
		finally {
			exitRule();
		}
		return _localctx;
	}

	public static class LimitContext extends ParserRuleContext {
		public SegmentContext segment() {
			return getRuleContext(SegmentContext.class,0);
		}
		public TerminalNode INTEGER() { return getToken(KeywordParser.INTEGER, 0); }
		public TerminalNode BEFORE() { return getToken(KeywordParser.BEFORE, 0); }
		public TerminalNode AFTER() { return getToken(KeywordParser.AFTER, 0); }
		public TerminalNode AUX_MIN() { return getToken(KeywordParser.AUX_MIN, 0); }
		public TerminalNode AUX_DE() { return getToken(KeywordParser.AUX_DE, 0); }
		public TerminalNode ORDER() { return getToken(KeywordParser.ORDER, 0); }
		public TerminalNode AUX_ZAI() { return getToken(KeywordParser.AUX_ZAI, 0); }
		public TerminalNode REVERSE() { return getToken(KeywordParser.REVERSE, 0); }
		public LimitContext(ParserRuleContext parent, int invokingState) {
			super(parent, invokingState);
		}
		@Override public int getRuleIndex() { return RULE_limit; }
		@Override
		public <T> T accept(ParseTreeVisitor<? extends T> visitor) {
			if ( visitor instanceof KeywordVisitor ) return ((KeywordVisitor<? extends T>)visitor).visitLimit(this);
			else return visitor.visitChildren(this);
		}
	}

	public final LimitContext limit() throws RecognitionException {
		LimitContext _localctx = new LimitContext(_ctx, getState());
		enterRule(_localctx, 14, RULE_limit);
		int _la;
		try {
			setState(144);
			_errHandler.sync(this);
			switch ( getInterpreter().adaptivePredict(_input,11,_ctx) ) {
			case 1:
				enterOuterAlt(_localctx, 1);
				{
				setState(128);
				segment();
				setState(130);
				_errHandler.sync(this);
				_la = _input.LA(1);
				if (_la==ORDER) {
					{
					setState(129);
					match(ORDER);
					}
				}

				setState(133);
				_errHandler.sync(this);
				_la = _input.LA(1);
				if (_la==AUX_ZAI) {
					{
					setState(132);
					match(AUX_ZAI);
					}
				}

				setState(135);
				_la = _input.LA(1);
				if ( !(_la==BEFORE || _la==AFTER) ) {
				_errHandler.recoverInline(this);
				}
				else {
					if ( _input.LA(1)==Token.EOF ) matchedEOF = true;
					_errHandler.reportMatch(this);
					consume();
				}
				setState(136);
				match(INTEGER);
				setState(137);
				_la = _input.LA(1);
				if ( !(_la==AUX_DE || _la==AUX_MIN) ) {
				_errHandler.recoverInline(this);
				}
				else {
					if ( _input.LA(1)==Token.EOF ) matchedEOF = true;
					_errHandler.reportMatch(this);
					consume();
				}
				}
				break;
			case 2:
				enterOuterAlt(_localctx, 2);
				{
				setState(139);
				segment();
				setState(140);
				match(REVERSE);
				setState(141);
				match(INTEGER);
				setState(142);
				_la = _input.LA(1);
				if ( !(_la==AUX_DE || _la==AUX_MIN) ) {
				_errHandler.recoverInline(this);
				}
				else {
					if ( _input.LA(1)==Token.EOF ) matchedEOF = true;
					_errHandler.reportMatch(this);
					consume();
				}
				}
				break;
			}
		}
		catch (RecognitionException re) {
			_localctx.exception = re;
			_errHandler.reportError(this, re);
			_errHandler.recover(this, re);
		}
		finally {
			exitRule();
		}
		return _localctx;
	}

	public static class NonReservedContext extends ParserRuleContext {
		public TerminalNode COUNT() { return getToken(KeywordParser.COUNT, 0); }
		public TerminalNode COUNT_DISTINCT() { return getToken(KeywordParser.COUNT_DISTINCT, 0); }
		public TerminalNode SUM() { return getToken(KeywordParser.SUM, 0); }
		public TerminalNode AVG() { return getToken(KeywordParser.AVG, 0); }
		public TerminalNode GREATEST() { return getToken(KeywordParser.GREATEST, 0); }
		public TerminalNode LEATEST() { return getToken(KeywordParser.LEATEST, 0); }
		public TerminalNode MAX() { return getToken(KeywordParser.MAX, 0); }
		public TerminalNode MIN() { return getToken(KeywordParser.MIN, 0); }
		public TerminalNode GROUP_YEAR() { return getToken(KeywordParser.GROUP_YEAR, 0); }
		public TerminalNode GROUP_QUARTER() { return getToken(KeywordParser.GROUP_QUARTER, 0); }
		public TerminalNode GROUP_MONTH() { return getToken(KeywordParser.GROUP_MONTH, 0); }
		public TerminalNode GROUP_WEEK() { return getToken(KeywordParser.GROUP_WEEK, 0); }
		public TerminalNode GROUP_DAY() { return getToken(KeywordParser.GROUP_DAY, 0); }
		public TerminalNode EQUAL() { return getToken(KeywordParser.EQUAL, 0); }
		public TerminalNode ORDER() { return getToken(KeywordParser.ORDER, 0); }
		public TerminalNode AUX_ZAI() { return getToken(KeywordParser.AUX_ZAI, 0); }
		public TerminalNode AUX_YI() { return getToken(KeywordParser.AUX_YI, 0); }
		public TerminalNode AUX_HE() { return getToken(KeywordParser.AUX_HE, 0); }
		public TerminalNode AUX_DE() { return getToken(KeywordParser.AUX_DE, 0); }
		public TerminalNode AUX_MIN() { return getToken(KeywordParser.AUX_MIN, 0); }
		public NonReservedContext(ParserRuleContext parent, int invokingState) {
			super(parent, invokingState);
		}
		@Override public int getRuleIndex() { return RULE_nonReserved; }
		@Override
		public <T> T accept(ParseTreeVisitor<? extends T> visitor) {
			if ( visitor instanceof KeywordVisitor ) return ((KeywordVisitor<? extends T>)visitor).visitNonReserved(this);
			else return visitor.visitChildren(this);
		}
	}

	public final NonReservedContext nonReserved() throws RecognitionException {
		NonReservedContext _localctx = new NonReservedContext(_ctx, getState());
		enterRule(_localctx, 16, RULE_nonReserved);
		int _la;
		try {
			enterOuterAlt(_localctx, 1);
			{
			setState(146);
			_la = _input.LA(1);
			if ( !((((_la) & ~0x3f) == 0 && ((1L << _la) & ((1L << COUNT) | (1L << COUNT_DISTINCT) | (1L << SUM) | (1L << AVG) | (1L << GREATEST) | (1L << LEATEST) | (1L << MAX) | (1L << MIN) | (1L << GROUP_YEAR) | (1L << GROUP_QUARTER) | (1L << GROUP_MONTH) | (1L << GROUP_WEEK) | (1L << GROUP_DAY) | (1L << EQUAL) | (1L << ORDER) | (1L << AUX_ZAI) | (1L << AUX_YI) | (1L << AUX_HE) | (1L << AUX_DE) | (1L << AUX_MIN))) != 0)) ) {
			_errHandler.recoverInline(this);
			}
			else {
				if ( _input.LA(1)==Token.EOF ) matchedEOF = true;
				_errHandler.reportMatch(this);
				consume();
			}
			}
		}
		catch (RecognitionException re) {
			_localctx.exception = re;
			_errHandler.reportError(this, re);
			_errHandler.recover(this, re);
		}
		finally {
			exitRule();
		}
		return _localctx;
	}

	public static class FormulaContext extends ParserRuleContext {
		public StatementContext statement() {
			return getRuleContext(StatementContext.class,0);
		}
		public TerminalNode EOF() { return getToken(KeywordParser.EOF, 0); }
		public FormulaContext(ParserRuleContext parent, int invokingState) {
			super(parent, invokingState);
		}
		@Override public int getRuleIndex() { return RULE_formula; }
		@Override
		public <T> T accept(ParseTreeVisitor<? extends T> visitor) {
			if ( visitor instanceof KeywordVisitor ) return ((KeywordVisitor<? extends T>)visitor).visitFormula(this);
			else return visitor.visitChildren(this);
		}
	}

	public final FormulaContext formula() throws RecognitionException {
		FormulaContext _localctx = new FormulaContext(_ctx, getState());
		enterRule(_localctx, 18, RULE_formula);
		try {
			enterOuterAlt(_localctx, 1);
			{
			setState(148);
			statement(0);
			setState(149);
			match(EOF);
			}
		}
		catch (RecognitionException re) {
			_localctx.exception = re;
			_errHandler.reportError(this, re);
			_errHandler.recover(this, re);
		}
		finally {
			exitRule();
		}
		return _localctx;
	}

	public static class StatementContext extends ParserRuleContext {
		public StatementContext(ParserRuleContext parent, int invokingState) {
			super(parent, invokingState);
		}
		@Override public int getRuleIndex() { return RULE_statement; }
	 
		public StatementContext() { }
		public void copyFrom(StatementContext ctx) {
			super.copyFrom(ctx);
		}
	}
	public static class ConstantExpressionContext extends StatementContext {
		public ConstantContext constant() {
			return getRuleContext(ConstantContext.class,0);
		}
		public ConstantExpressionContext(StatementContext ctx) { copyFrom(ctx); }
		@Override
		public <T> T accept(ParseTreeVisitor<? extends T> visitor) {
			if ( visitor instanceof KeywordVisitor ) return ((KeywordVisitor<? extends T>)visitor).visitConstantExpression(this);
			else return visitor.visitChildren(this);
		}
	}
	public static class InPredicateContext extends StatementContext {
		public Token op;
		public StatementContext statement() {
			return getRuleContext(StatementContext.class,0);
		}
		public TerminalNode L_PARENTHESES() { return getToken(KeywordParser.L_PARENTHESES, 0); }
		public StatementsContext statements() {
			return getRuleContext(StatementsContext.class,0);
		}
		public TerminalNode R_PARENTHESES() { return getToken(KeywordParser.R_PARENTHESES, 0); }
		public TerminalNode IN() { return getToken(KeywordParser.IN, 0); }
		public TerminalNode NOT() { return getToken(KeywordParser.NOT, 0); }
		public InPredicateContext(StatementContext ctx) { copyFrom(ctx); }
		@Override
		public <T> T accept(ParseTreeVisitor<? extends T> visitor) {
			if ( visitor instanceof KeywordVisitor ) return ((KeywordVisitor<? extends T>)visitor).visitInPredicate(this);
			else return visitor.visitChildren(this);
		}
	}
	public static class MathExpressionContext extends StatementContext {
		public Token op;
		public List<StatementContext> statement() {
			return getRuleContexts(StatementContext.class);
		}
		public StatementContext statement(int i) {
			return getRuleContext(StatementContext.class,i);
		}
		public TerminalNode POWER() { return getToken(KeywordParser.POWER, 0); }
		public TerminalNode MOD() { return getToken(KeywordParser.MOD, 0); }
		public TerminalNode MUL() { return getToken(KeywordParser.MUL, 0); }
		public TerminalNode DIV() { return getToken(KeywordParser.DIV, 0); }
		public TerminalNode PLUS() { return getToken(KeywordParser.PLUS, 0); }
		public TerminalNode MINUS() { return getToken(KeywordParser.MINUS, 0); }
		public MathExpressionContext(StatementContext ctx) { copyFrom(ctx); }
		@Override
		public <T> T accept(ParseTreeVisitor<? extends T> visitor) {
			if ( visitor instanceof KeywordVisitor ) return ((KeywordVisitor<? extends T>)visitor).visitMathExpression(this);
			else return visitor.visitChildren(this);
		}
	}
	public static class LikePredicateContext extends StatementContext {
		public Token op;
		public List<StatementContext> statement() {
			return getRuleContexts(StatementContext.class);
		}
		public StatementContext statement(int i) {
			return getRuleContext(StatementContext.class,i);
		}
		public TerminalNode LIKE() { return getToken(KeywordParser.LIKE, 0); }
		public TerminalNode NOT() { return getToken(KeywordParser.NOT, 0); }
		public LikePredicateContext(StatementContext ctx) { copyFrom(ctx); }
		@Override
		public <T> T accept(ParseTreeVisitor<? extends T> visitor) {
			if ( visitor instanceof KeywordVisitor ) return ((KeywordVisitor<? extends T>)visitor).visitLikePredicate(this);
			else return visitor.visitChildren(this);
		}
	}
	public static class FunctionExpressionContext extends StatementContext {
		public FunctionStatementContext functionStatement() {
			return getRuleContext(FunctionStatementContext.class,0);
		}
		public FunctionExpressionContext(StatementContext ctx) { copyFrom(ctx); }
		@Override
		public <T> T accept(ParseTreeVisitor<? extends T> visitor) {
			if ( visitor instanceof KeywordVisitor ) return ((KeywordVisitor<? extends T>)visitor).visitFunctionExpression(this);
			else return visitor.visitChildren(this);
		}
	}
	public static class ParenthesesExpressionContext extends StatementContext {
		public TerminalNode L_PARENTHESES() { return getToken(KeywordParser.L_PARENTHESES, 0); }
		public StatementContext statement() {
			return getRuleContext(StatementContext.class,0);
		}
		public TerminalNode R_PARENTHESES() { return getToken(KeywordParser.R_PARENTHESES, 0); }
		public ParenthesesExpressionContext(StatementContext ctx) { copyFrom(ctx); }
		@Override
		public <T> T accept(ParseTreeVisitor<? extends T> visitor) {
			if ( visitor instanceof KeywordVisitor ) return ((KeywordVisitor<? extends T>)visitor).visitParenthesesExpression(this);
			else return visitor.visitChildren(this);
		}
	}
	public static class SquareExpressionContext extends StatementContext {
		public TerminalNode L_SQUARE() { return getToken(KeywordParser.L_SQUARE, 0); }
		public TerminalNode R_SQUARE() { return getToken(KeywordParser.R_SQUARE, 0); }
		public StatementsContext statements() {
			return getRuleContext(StatementsContext.class,0);
		}
		public SquareExpressionContext(StatementContext ctx) { copyFrom(ctx); }
		@Override
		public <T> T accept(ParseTreeVisitor<? extends T> visitor) {
			if ( visitor instanceof KeywordVisitor ) return ((KeywordVisitor<? extends T>)visitor).visitSquareExpression(this);
			else return visitor.visitChildren(this);
		}
	}
	public static class ComparePredicateContext extends StatementContext {
		public Token op;
		public List<StatementContext> statement() {
			return getRuleContexts(StatementContext.class);
		}
		public StatementContext statement(int i) {
			return getRuleContext(StatementContext.class,i);
		}
		public TerminalNode GREATER() { return getToken(KeywordParser.GREATER, 0); }
		public TerminalNode GREATER_EQUAL() { return getToken(KeywordParser.GREATER_EQUAL, 0); }
		public TerminalNode LESS() { return getToken(KeywordParser.LESS, 0); }
		public TerminalNode LESS_EQUAL() { return getToken(KeywordParser.LESS_EQUAL, 0); }
		public TerminalNode EQUAL() { return getToken(KeywordParser.EQUAL, 0); }
		public TerminalNode NOT_EQUAL() { return getToken(KeywordParser.NOT_EQUAL, 0); }
		public ComparePredicateContext(StatementContext ctx) { copyFrom(ctx); }
		@Override
		public <T> T accept(ParseTreeVisitor<? extends T> visitor) {
			if ( visitor instanceof KeywordVisitor ) return ((KeywordVisitor<? extends T>)visitor).visitComparePredicate(this);
			else return visitor.visitChildren(this);
		}
	}
	public static class IfnullExpressionContext extends StatementContext {
		public List<StatementContext> statement() {
			return getRuleContexts(StatementContext.class);
		}
		public StatementContext statement(int i) {
			return getRuleContext(StatementContext.class,i);
		}
		public TerminalNode IFNULL() { return getToken(KeywordParser.IFNULL, 0); }
		public IfnullExpressionContext(StatementContext ctx) { copyFrom(ctx); }
		@Override
		public <T> T accept(ParseTreeVisitor<? extends T> visitor) {
			if ( visitor instanceof KeywordVisitor ) return ((KeywordVisitor<? extends T>)visitor).visitIfnullExpression(this);
			else return visitor.visitChildren(this);
		}
	}
	public static class CaseExpressionContext extends StatementContext {
		public CaseStatementContext caseStatement() {
			return getRuleContext(CaseStatementContext.class,0);
		}
		public CaseExpressionContext(StatementContext ctx) { copyFrom(ctx); }
		@Override
		public <T> T accept(ParseTreeVisitor<? extends T> visitor) {
			if ( visitor instanceof KeywordVisitor ) return ((KeywordVisitor<? extends T>)visitor).visitCaseExpression(this);
			else return visitor.visitChildren(this);
		}
	}
	public static class ColumnExpressionContext extends StatementContext {
		public ColumnContext column() {
			return getRuleContext(ColumnContext.class,0);
		}
		public ColumnExpressionContext(StatementContext ctx) { copyFrom(ctx); }
		@Override
		public <T> T accept(ParseTreeVisitor<? extends T> visitor) {
			if ( visitor instanceof KeywordVisitor ) return ((KeywordVisitor<? extends T>)visitor).visitColumnExpression(this);
			else return visitor.visitChildren(this);
		}
	}
	public static class NotPredicateContext extends StatementContext {
		public TerminalNode NOT() { return getToken(KeywordParser.NOT, 0); }
		public StatementContext statement() {
			return getRuleContext(StatementContext.class,0);
		}
		public NotPredicateContext(StatementContext ctx) { copyFrom(ctx); }
		@Override
		public <T> T accept(ParseTreeVisitor<? extends T> visitor) {
			if ( visitor instanceof KeywordVisitor ) return ((KeywordVisitor<? extends T>)visitor).visitNotPredicate(this);
			else return visitor.visitChildren(this);
		}
	}
	public static class LogicalPredicateContext extends StatementContext {
		public Token op;
		public List<StatementContext> statement() {
			return getRuleContexts(StatementContext.class);
		}
		public StatementContext statement(int i) {
			return getRuleContext(StatementContext.class,i);
		}
		public TerminalNode AND() { return getToken(KeywordParser.AND, 0); }
		public TerminalNode XOR() { return getToken(KeywordParser.XOR, 0); }
		public TerminalNode OR() { return getToken(KeywordParser.OR, 0); }
		public LogicalPredicateContext(StatementContext ctx) { copyFrom(ctx); }
		@Override
		public <T> T accept(ParseTreeVisitor<? extends T> visitor) {
			if ( visitor instanceof KeywordVisitor ) return ((KeywordVisitor<? extends T>)visitor).visitLogicalPredicate(this);
			else return visitor.visitChildren(this);
		}
	}
	public static class IfExpressionContext extends StatementContext {
		public IfSpecialContext ifSpecial() {
			return getRuleContext(IfSpecialContext.class,0);
		}
		public IfExpressionContext(StatementContext ctx) { copyFrom(ctx); }
		@Override
		public <T> T accept(ParseTreeVisitor<? extends T> visitor) {
			if ( visitor instanceof KeywordVisitor ) return ((KeywordVisitor<? extends T>)visitor).visitIfExpression(this);
			else return visitor.visitChildren(this);
		}
	}

	public final StatementContext statement() throws RecognitionException {
		return statement(0);
	}

	private StatementContext statement(int _p) throws RecognitionException {
		ParserRuleContext _parentctx = _ctx;
		int _parentState = getState();
		StatementContext _localctx = new StatementContext(_ctx, _parentState);
		StatementContext _prevctx = _localctx;
		int _startState = 20;
		enterRecursionRule(_localctx, 20, RULE_statement, _p);
		int _la;
		try {
			int _alt;
			enterOuterAlt(_localctx, 1);
			{
			setState(168);
			_errHandler.sync(this);
			switch ( getInterpreter().adaptivePredict(_input,13,_ctx) ) {
			case 1:
				{
				_localctx = new IfExpressionContext(_localctx);
				_ctx = _localctx;
				_prevctx = _localctx;

				setState(152);
				ifSpecial();
				}
				break;
			case 2:
				{
				_localctx = new FunctionExpressionContext(_localctx);
				_ctx = _localctx;
				_prevctx = _localctx;
				setState(153);
				functionStatement();
				}
				break;
			case 3:
				{
				_localctx = new CaseExpressionContext(_localctx);
				_ctx = _localctx;
				_prevctx = _localctx;
				setState(154);
				caseStatement();
				}
				break;
			case 4:
				{
				_localctx = new ConstantExpressionContext(_localctx);
				_ctx = _localctx;
				_prevctx = _localctx;
				setState(155);
				constant();
				}
				break;
			case 5:
				{
				_localctx = new ColumnExpressionContext(_localctx);
				_ctx = _localctx;
				_prevctx = _localctx;
				setState(156);
				column();
				}
				break;
			case 6:
				{
				_localctx = new NotPredicateContext(_localctx);
				_ctx = _localctx;
				_prevctx = _localctx;
				setState(157);
				match(NOT);
				setState(158);
				statement(7);
				}
				break;
			case 7:
				{
				_localctx = new ParenthesesExpressionContext(_localctx);
				_ctx = _localctx;
				_prevctx = _localctx;
				setState(159);
				match(L_PARENTHESES);
				setState(160);
				statement(0);
				setState(161);
				match(R_PARENTHESES);
				}
				break;
			case 8:
				{
				_localctx = new SquareExpressionContext(_localctx);
				_ctx = _localctx;
				_prevctx = _localctx;
				setState(163);
				match(L_SQUARE);
				setState(165);
				_errHandler.sync(this);
				_la = _input.LA(1);
				if (((((_la - 44)) & ~0x3f) == 0 && ((1L << (_la - 44)) & ((1L << (L_PARENTHESES - 44)) | (1L << (L_SQUARE - 44)) | (1L << (PLUS - 44)) | (1L << (MINUS - 44)) | (1L << (IF - 44)) | (1L << (LIKE - 44)) | (1L << (NOT - 44)) | (1L << (CASE - 44)) | (1L << (BOOL - 44)) | (1L << (NULL - 44)) | (1L << (STRING - 44)) | (1L << (INTEGER - 44)) | (1L << (FLOAT - 44)) | (1L << (COLUMN_ID - 44)) | (1L << (IDENTITY - 44)) | (1L << (COLUMN_NAME - 44)))) != 0)) {
					{
					setState(164);
					statements();
					}
				}

				setState(167);
				match(R_SQUARE);
				}
				break;
			}
			_ctx.stop = _input.LT(-1);
			setState(214);
			_errHandler.sync(this);
			_alt = getInterpreter().adaptivePredict(_input,17,_ctx);
			while ( _alt!=2 && _alt!= ATN.INVALID_ALT_NUMBER ) {
				if ( _alt==1 ) {
					if ( _parseListeners!=null ) triggerExitRuleEvent();
					_prevctx = _localctx;
					{
					setState(212);
					_errHandler.sync(this);
					switch ( getInterpreter().adaptivePredict(_input,16,_ctx) ) {
					case 1:
						{
						_localctx = new MathExpressionContext(new StatementContext(_parentctx, _parentState));
						pushNewRecursionContext(_localctx, _startState, RULE_statement);
						setState(170);
						if (!(precpred(_ctx, 14))) throw new FailedPredicateException(this, "precpred(_ctx, 14)");
						setState(171);
						((MathExpressionContext)_localctx).op = match(POWER);
						setState(172);
						statement(14);
						}
						break;
					case 2:
						{
						_localctx = new MathExpressionContext(new StatementContext(_parentctx, _parentState));
						pushNewRecursionContext(_localctx, _startState, RULE_statement);
						setState(173);
						if (!(precpred(_ctx, 13))) throw new FailedPredicateException(this, "precpred(_ctx, 13)");
						setState(174);
						((MathExpressionContext)_localctx).op = match(MOD);
						setState(175);
						statement(14);
						}
						break;
					case 3:
						{
						_localctx = new MathExpressionContext(new StatementContext(_parentctx, _parentState));
						pushNewRecursionContext(_localctx, _startState, RULE_statement);
						setState(176);
						if (!(precpred(_ctx, 12))) throw new FailedPredicateException(this, "precpred(_ctx, 12)");
						setState(177);
						((MathExpressionContext)_localctx).op = _input.LT(1);
						_la = _input.LA(1);
						if ( !(_la==MUL || _la==DIV) ) {
							((MathExpressionContext)_localctx).op = (Token)_errHandler.recoverInline(this);
						}
						else {
							if ( _input.LA(1)==Token.EOF ) matchedEOF = true;
							_errHandler.reportMatch(this);
							consume();
						}
						setState(178);
						statement(13);
						}
						break;
					case 4:
						{
						_localctx = new MathExpressionContext(new StatementContext(_parentctx, _parentState));
						pushNewRecursionContext(_localctx, _startState, RULE_statement);
						setState(179);
						if (!(precpred(_ctx, 11))) throw new FailedPredicateException(this, "precpred(_ctx, 11)");
						setState(180);
						((MathExpressionContext)_localctx).op = _input.LT(1);
						_la = _input.LA(1);
						if ( !(_la==PLUS || _la==MINUS) ) {
							((MathExpressionContext)_localctx).op = (Token)_errHandler.recoverInline(this);
						}
						else {
							if ( _input.LA(1)==Token.EOF ) matchedEOF = true;
							_errHandler.reportMatch(this);
							consume();
						}
						setState(181);
						statement(12);
						}
						break;
					case 5:
						{
						_localctx = new ComparePredicateContext(new StatementContext(_parentctx, _parentState));
						pushNewRecursionContext(_localctx, _startState, RULE_statement);
						setState(182);
						if (!(precpred(_ctx, 10))) throw new FailedPredicateException(this, "precpred(_ctx, 10)");
						setState(183);
						((ComparePredicateContext)_localctx).op = _input.LT(1);
						_la = _input.LA(1);
						if ( !((((_la) & ~0x3f) == 0 && ((1L << _la) & ((1L << GREATER) | (1L << LESS) | (1L << GREATER_EQUAL) | (1L << LESS_EQUAL) | (1L << NOT_EQUAL) | (1L << EQUAL))) != 0)) ) {
							((ComparePredicateContext)_localctx).op = (Token)_errHandler.recoverInline(this);
						}
						else {
							if ( _input.LA(1)==Token.EOF ) matchedEOF = true;
							_errHandler.reportMatch(this);
							consume();
						}
						setState(184);
						statement(11);
						}
						break;
					case 6:
						{
						_localctx = new LikePredicateContext(new StatementContext(_parentctx, _parentState));
						pushNewRecursionContext(_localctx, _startState, RULE_statement);
						setState(185);
						if (!(precpred(_ctx, 8))) throw new FailedPredicateException(this, "precpred(_ctx, 8)");
						setState(187);
						_errHandler.sync(this);
						_la = _input.LA(1);
						if (_la==NOT) {
							{
							setState(186);
							match(NOT);
							}
						}

						setState(189);
						((LikePredicateContext)_localctx).op = match(LIKE);
						setState(190);
						statement(9);
						}
						break;
					case 7:
						{
						_localctx = new LogicalPredicateContext(new StatementContext(_parentctx, _parentState));
						pushNewRecursionContext(_localctx, _startState, RULE_statement);
						setState(191);
						if (!(precpred(_ctx, 6))) throw new FailedPredicateException(this, "precpred(_ctx, 6)");
						setState(192);
						((LogicalPredicateContext)_localctx).op = match(AND);
						setState(193);
						statement(7);
						}
						break;
					case 8:
						{
						_localctx = new LogicalPredicateContext(new StatementContext(_parentctx, _parentState));
						pushNewRecursionContext(_localctx, _startState, RULE_statement);
						setState(194);
						if (!(precpred(_ctx, 5))) throw new FailedPredicateException(this, "precpred(_ctx, 5)");
						setState(195);
						((LogicalPredicateContext)_localctx).op = match(XOR);
						setState(196);
						statement(6);
						}
						break;
					case 9:
						{
						_localctx = new LogicalPredicateContext(new StatementContext(_parentctx, _parentState));
						pushNewRecursionContext(_localctx, _startState, RULE_statement);
						setState(197);
						if (!(precpred(_ctx, 4))) throw new FailedPredicateException(this, "precpred(_ctx, 4)");
						setState(198);
						((LogicalPredicateContext)_localctx).op = match(OR);
						setState(199);
						statement(5);
						}
						break;
					case 10:
						{
						_localctx = new IfnullExpressionContext(new StatementContext(_parentctx, _parentState));
						pushNewRecursionContext(_localctx, _startState, RULE_statement);
						setState(200);
						if (!(precpred(_ctx, 1))) throw new FailedPredicateException(this, "precpred(_ctx, 1)");
						setState(201);
						match(IFNULL);
						setState(202);
						statement(2);
						}
						break;
					case 11:
						{
						_localctx = new InPredicateContext(new StatementContext(_parentctx, _parentState));
						pushNewRecursionContext(_localctx, _startState, RULE_statement);
						setState(203);
						if (!(precpred(_ctx, 9))) throw new FailedPredicateException(this, "precpred(_ctx, 9)");
						setState(205);
						_errHandler.sync(this);
						_la = _input.LA(1);
						if (_la==NOT) {
							{
							setState(204);
							match(NOT);
							}
						}

						setState(207);
						((InPredicateContext)_localctx).op = match(IN);
						setState(208);
						match(L_PARENTHESES);
						setState(209);
						statements();
						setState(210);
						match(R_PARENTHESES);
						}
						break;
					}
					} 
				}
				setState(216);
				_errHandler.sync(this);
				_alt = getInterpreter().adaptivePredict(_input,17,_ctx);
			}
			}
		}
		catch (RecognitionException re) {
			_localctx.exception = re;
			_errHandler.reportError(this, re);
			_errHandler.recover(this, re);
		}
		finally {
			unrollRecursionContexts(_parentctx);
		}
		return _localctx;
	}

	public static class FunctionStatementContext extends ParserRuleContext {
		public IdentityContext identity() {
			return getRuleContext(IdentityContext.class,0);
		}
		public TerminalNode L_PARENTHESES() { return getToken(KeywordParser.L_PARENTHESES, 0); }
		public TerminalNode R_PARENTHESES() { return getToken(KeywordParser.R_PARENTHESES, 0); }
		public FunctionParamsContext functionParams() {
			return getRuleContext(FunctionParamsContext.class,0);
		}
		public FunctionStatementContext(ParserRuleContext parent, int invokingState) {
			super(parent, invokingState);
		}
		@Override public int getRuleIndex() { return RULE_functionStatement; }
		@Override
		public <T> T accept(ParseTreeVisitor<? extends T> visitor) {
			if ( visitor instanceof KeywordVisitor ) return ((KeywordVisitor<? extends T>)visitor).visitFunctionStatement(this);
			else return visitor.visitChildren(this);
		}
	}

	public final FunctionStatementContext functionStatement() throws RecognitionException {
		FunctionStatementContext _localctx = new FunctionStatementContext(_ctx, getState());
		enterRule(_localctx, 22, RULE_functionStatement);
		int _la;
		try {
			enterOuterAlt(_localctx, 1);
			{
			setState(217);
			identity();
			setState(218);
			match(L_PARENTHESES);
			setState(220);
			_errHandler.sync(this);
			_la = _input.LA(1);
			if (((((_la - 44)) & ~0x3f) == 0 && ((1L << (_la - 44)) & ((1L << (L_PARENTHESES - 44)) | (1L << (L_SQUARE - 44)) | (1L << (PLUS - 44)) | (1L << (MINUS - 44)) | (1L << (IF - 44)) | (1L << (LIKE - 44)) | (1L << (NOT - 44)) | (1L << (CASE - 44)) | (1L << (BOOL - 44)) | (1L << (NULL - 44)) | (1L << (STRING - 44)) | (1L << (INTEGER - 44)) | (1L << (FLOAT - 44)) | (1L << (COLUMN_ID - 44)) | (1L << (IDENTITY - 44)) | (1L << (COLUMN_NAME - 44)))) != 0)) {
				{
				setState(219);
				functionParams();
				}
			}

			setState(222);
			match(R_PARENTHESES);
			}
		}
		catch (RecognitionException re) {
			_localctx.exception = re;
			_errHandler.reportError(this, re);
			_errHandler.recover(this, re);
		}
		finally {
			exitRule();
		}
		return _localctx;
	}

	public static class FunctionParamsContext extends ParserRuleContext {
		public List<FunctionParamContext> functionParam() {
			return getRuleContexts(FunctionParamContext.class);
		}
		public FunctionParamContext functionParam(int i) {
			return getRuleContext(FunctionParamContext.class,i);
		}
		public List<TerminalNode> COMMA() { return getTokens(KeywordParser.COMMA); }
		public TerminalNode COMMA(int i) {
			return getToken(KeywordParser.COMMA, i);
		}
		public FunctionParamsContext(ParserRuleContext parent, int invokingState) {
			super(parent, invokingState);
		}
		@Override public int getRuleIndex() { return RULE_functionParams; }
		@Override
		public <T> T accept(ParseTreeVisitor<? extends T> visitor) {
			if ( visitor instanceof KeywordVisitor ) return ((KeywordVisitor<? extends T>)visitor).visitFunctionParams(this);
			else return visitor.visitChildren(this);
		}
	}

	public final FunctionParamsContext functionParams() throws RecognitionException {
		FunctionParamsContext _localctx = new FunctionParamsContext(_ctx, getState());
		enterRule(_localctx, 24, RULE_functionParams);
		int _la;
		try {
			enterOuterAlt(_localctx, 1);
			{
			setState(224);
			functionParam();
			setState(229);
			_errHandler.sync(this);
			_la = _input.LA(1);
			while (_la==COMMA) {
				{
				{
				setState(225);
				match(COMMA);
				setState(226);
				functionParam();
				}
				}
				setState(231);
				_errHandler.sync(this);
				_la = _input.LA(1);
			}
			}
		}
		catch (RecognitionException re) {
			_localctx.exception = re;
			_errHandler.reportError(this, re);
			_errHandler.recover(this, re);
		}
		finally {
			exitRule();
		}
		return _localctx;
	}

	public static class FunctionParamContext extends ParserRuleContext {
		public StatementContext statement() {
			return getRuleContext(StatementContext.class,0);
		}
		public IdentityContext identity() {
			return getRuleContext(IdentityContext.class,0);
		}
		public TerminalNode EQUAL() { return getToken(KeywordParser.EQUAL, 0); }
		public FunctionParamContext(ParserRuleContext parent, int invokingState) {
			super(parent, invokingState);
		}
		@Override public int getRuleIndex() { return RULE_functionParam; }
		@Override
		public <T> T accept(ParseTreeVisitor<? extends T> visitor) {
			if ( visitor instanceof KeywordVisitor ) return ((KeywordVisitor<? extends T>)visitor).visitFunctionParam(this);
			else return visitor.visitChildren(this);
		}
	}

	public final FunctionParamContext functionParam() throws RecognitionException {
		FunctionParamContext _localctx = new FunctionParamContext(_ctx, getState());
		enterRule(_localctx, 26, RULE_functionParam);
		try {
			setState(237);
			_errHandler.sync(this);
			switch ( getInterpreter().adaptivePredict(_input,20,_ctx) ) {
			case 1:
				enterOuterAlt(_localctx, 1);
				{
				setState(232);
				statement(0);
				}
				break;
			case 2:
				enterOuterAlt(_localctx, 2);
				{
				setState(233);
				identity();
				setState(234);
				match(EQUAL);
				setState(235);
				statement(0);
				}
				break;
			}
		}
		catch (RecognitionException re) {
			_localctx.exception = re;
			_errHandler.reportError(this, re);
			_errHandler.recover(this, re);
		}
		finally {
			exitRule();
		}
		return _localctx;
	}

	public static class StatementsContext extends ParserRuleContext {
		public List<StatementContext> statement() {
			return getRuleContexts(StatementContext.class);
		}
		public StatementContext statement(int i) {
			return getRuleContext(StatementContext.class,i);
		}
		public List<TerminalNode> COMMA() { return getTokens(KeywordParser.COMMA); }
		public TerminalNode COMMA(int i) {
			return getToken(KeywordParser.COMMA, i);
		}
		public StatementsContext(ParserRuleContext parent, int invokingState) {
			super(parent, invokingState);
		}
		@Override public int getRuleIndex() { return RULE_statements; }
		@Override
		public <T> T accept(ParseTreeVisitor<? extends T> visitor) {
			if ( visitor instanceof KeywordVisitor ) return ((KeywordVisitor<? extends T>)visitor).visitStatements(this);
			else return visitor.visitChildren(this);
		}
	}

	public final StatementsContext statements() throws RecognitionException {
		StatementsContext _localctx = new StatementsContext(_ctx, getState());
		enterRule(_localctx, 28, RULE_statements);
		int _la;
		try {
			enterOuterAlt(_localctx, 1);
			{
			setState(239);
			statement(0);
			setState(244);
			_errHandler.sync(this);
			_la = _input.LA(1);
			while (_la==COMMA) {
				{
				{
				setState(240);
				match(COMMA);
				setState(241);
				statement(0);
				}
				}
				setState(246);
				_errHandler.sync(this);
				_la = _input.LA(1);
			}
			}
		}
		catch (RecognitionException re) {
			_localctx.exception = re;
			_errHandler.reportError(this, re);
			_errHandler.recover(this, re);
		}
		finally {
			exitRule();
		}
		return _localctx;
	}

	public static class CaseStatementContext extends ParserRuleContext {
		public StatementContext thenStmt;
		public StatementContext caseStmt;
		public StatementContext elseStmt;
		public TerminalNode CASE() { return getToken(KeywordParser.CASE, 0); }
		public TerminalNode END() { return getToken(KeywordParser.END, 0); }
		public List<StatementContext> statement() {
			return getRuleContexts(StatementContext.class);
		}
		public StatementContext statement(int i) {
			return getRuleContext(StatementContext.class,i);
		}
		public TerminalNode ELSE() { return getToken(KeywordParser.ELSE, 0); }
		public List<TerminalNode> WHEN() { return getTokens(KeywordParser.WHEN); }
		public TerminalNode WHEN(int i) {
			return getToken(KeywordParser.WHEN, i);
		}
		public List<PredictStatementContext> predictStatement() {
			return getRuleContexts(PredictStatementContext.class);
		}
		public PredictStatementContext predictStatement(int i) {
			return getRuleContext(PredictStatementContext.class,i);
		}
		public List<TerminalNode> THEN() { return getTokens(KeywordParser.THEN); }
		public TerminalNode THEN(int i) {
			return getToken(KeywordParser.THEN, i);
		}
		public List<ConstantContext> constant() {
			return getRuleContexts(ConstantContext.class);
		}
		public ConstantContext constant(int i) {
			return getRuleContext(ConstantContext.class,i);
		}
		public CaseStatementContext(ParserRuleContext parent, int invokingState) {
			super(parent, invokingState);
		}
		@Override public int getRuleIndex() { return RULE_caseStatement; }
		@Override
		public <T> T accept(ParseTreeVisitor<? extends T> visitor) {
			if ( visitor instanceof KeywordVisitor ) return ((KeywordVisitor<? extends T>)visitor).visitCaseStatement(this);
			else return visitor.visitChildren(this);
		}
	}

	public final CaseStatementContext caseStatement() throws RecognitionException {
		CaseStatementContext _localctx = new CaseStatementContext(_ctx, getState());
		enterRule(_localctx, 30, RULE_caseStatement);
		int _la;
		try {
			enterOuterAlt(_localctx, 1);
			{
			setState(247);
			match(CASE);
			setState(267);
			_errHandler.sync(this);
			switch (_input.LA(1)) {
			case WHEN:
				{
				setState(253); 
				_errHandler.sync(this);
				_la = _input.LA(1);
				do {
					{
					{
					setState(248);
					match(WHEN);
					setState(249);
					predictStatement(0);
					setState(250);
					match(THEN);
					setState(251);
					((CaseStatementContext)_localctx).thenStmt = statement(0);
					}
					}
					setState(255); 
					_errHandler.sync(this);
					_la = _input.LA(1);
				} while ( _la==WHEN );
				}
				break;
			case L_PARENTHESES:
			case L_SQUARE:
			case PLUS:
			case MINUS:
			case IF:
			case LIKE:
			case NOT:
			case CASE:
			case BOOL:
			case NULL:
			case STRING:
			case INTEGER:
			case FLOAT:
			case COLUMN_ID:
			case IDENTITY:
			case COLUMN_NAME:
				{
				setState(257);
				((CaseStatementContext)_localctx).caseStmt = statement(0);
				setState(263); 
				_errHandler.sync(this);
				_la = _input.LA(1);
				do {
					{
					{
					setState(258);
					match(WHEN);
					setState(259);
					constant();
					setState(260);
					match(THEN);
					setState(261);
					((CaseStatementContext)_localctx).thenStmt = statement(0);
					}
					}
					setState(265); 
					_errHandler.sync(this);
					_la = _input.LA(1);
				} while ( _la==WHEN );
				}
				break;
			default:
				throw new NoViableAltException(this);
			}
			setState(271);
			_errHandler.sync(this);
			_la = _input.LA(1);
			if (_la==ELSE) {
				{
				setState(269);
				match(ELSE);
				setState(270);
				((CaseStatementContext)_localctx).elseStmt = statement(0);
				}
			}

			setState(273);
			match(END);
			}
		}
		catch (RecognitionException re) {
			_localctx.exception = re;
			_errHandler.reportError(this, re);
			_errHandler.recover(this, re);
		}
		finally {
			exitRule();
		}
		return _localctx;
	}

	public static class IfSpecialContext extends ParserRuleContext {
		public IfFunctionContext ifFunction() {
			return getRuleContext(IfFunctionContext.class,0);
		}
		public IfStatementContext ifStatement() {
			return getRuleContext(IfStatementContext.class,0);
		}
		public IfSpecialContext(ParserRuleContext parent, int invokingState) {
			super(parent, invokingState);
		}
		@Override public int getRuleIndex() { return RULE_ifSpecial; }
		@Override
		public <T> T accept(ParseTreeVisitor<? extends T> visitor) {
			if ( visitor instanceof KeywordVisitor ) return ((KeywordVisitor<? extends T>)visitor).visitIfSpecial(this);
			else return visitor.visitChildren(this);
		}
	}

	public final IfSpecialContext ifSpecial() throws RecognitionException {
		IfSpecialContext _localctx = new IfSpecialContext(_ctx, getState());
		enterRule(_localctx, 32, RULE_ifSpecial);
		try {
			setState(277);
			_errHandler.sync(this);
			switch ( getInterpreter().adaptivePredict(_input,26,_ctx) ) {
			case 1:
				enterOuterAlt(_localctx, 1);
				{
				setState(275);
				ifFunction();
				}
				break;
			case 2:
				enterOuterAlt(_localctx, 2);
				{
				setState(276);
				ifStatement();
				}
				break;
			}
		}
		catch (RecognitionException re) {
			_localctx.exception = re;
			_errHandler.reportError(this, re);
			_errHandler.recover(this, re);
		}
		finally {
			exitRule();
		}
		return _localctx;
	}

	public static class IfFunctionContext extends ParserRuleContext {
		public PredictStatementContext predict;
		public StatementContext ifTrue;
		public StatementContext ifFalse;
		public TerminalNode IF() { return getToken(KeywordParser.IF, 0); }
		public TerminalNode L_PARENTHESES() { return getToken(KeywordParser.L_PARENTHESES, 0); }
		public List<TerminalNode> COMMA() { return getTokens(KeywordParser.COMMA); }
		public TerminalNode COMMA(int i) {
			return getToken(KeywordParser.COMMA, i);
		}
		public TerminalNode R_PARENTHESES() { return getToken(KeywordParser.R_PARENTHESES, 0); }
		public PredictStatementContext predictStatement() {
			return getRuleContext(PredictStatementContext.class,0);
		}
		public List<StatementContext> statement() {
			return getRuleContexts(StatementContext.class);
		}
		public StatementContext statement(int i) {
			return getRuleContext(StatementContext.class,i);
		}
		public IfFunctionContext(ParserRuleContext parent, int invokingState) {
			super(parent, invokingState);
		}
		@Override public int getRuleIndex() { return RULE_ifFunction; }
		@Override
		public <T> T accept(ParseTreeVisitor<? extends T> visitor) {
			if ( visitor instanceof KeywordVisitor ) return ((KeywordVisitor<? extends T>)visitor).visitIfFunction(this);
			else return visitor.visitChildren(this);
		}
	}

	public final IfFunctionContext ifFunction() throws RecognitionException {
		IfFunctionContext _localctx = new IfFunctionContext(_ctx, getState());
		enterRule(_localctx, 34, RULE_ifFunction);
		try {
			enterOuterAlt(_localctx, 1);
			{
			setState(279);
			match(IF);
			setState(280);
			match(L_PARENTHESES);
			setState(281);
			((IfFunctionContext)_localctx).predict = predictStatement(0);
			setState(282);
			match(COMMA);
			setState(283);
			((IfFunctionContext)_localctx).ifTrue = statement(0);
			setState(284);
			match(COMMA);
			setState(285);
			((IfFunctionContext)_localctx).ifFalse = statement(0);
			setState(286);
			match(R_PARENTHESES);
			}
		}
		catch (RecognitionException re) {
			_localctx.exception = re;
			_errHandler.reportError(this, re);
			_errHandler.recover(this, re);
		}
		finally {
			exitRule();
		}
		return _localctx;
	}

	public static class IfStatementContext extends ParserRuleContext {
		public PredictStatementContext predict;
		public TerminalNode IF() { return getToken(KeywordParser.IF, 0); }
		public TerminalNode L_PARENTHESES() { return getToken(KeywordParser.L_PARENTHESES, 0); }
		public TerminalNode R_PARENTHESES() { return getToken(KeywordParser.R_PARENTHESES, 0); }
		public TerminalNode L_BRACE() { return getToken(KeywordParser.L_BRACE, 0); }
		public StatementContext statement() {
			return getRuleContext(StatementContext.class,0);
		}
		public TerminalNode R_BRACE() { return getToken(KeywordParser.R_BRACE, 0); }
		public PredictStatementContext predictStatement() {
			return getRuleContext(PredictStatementContext.class,0);
		}
		public List<ElseIfStatementContext> elseIfStatement() {
			return getRuleContexts(ElseIfStatementContext.class);
		}
		public ElseIfStatementContext elseIfStatement(int i) {
			return getRuleContext(ElseIfStatementContext.class,i);
		}
		public ElseStatementContext elseStatement() {
			return getRuleContext(ElseStatementContext.class,0);
		}
		public IfStatementContext(ParserRuleContext parent, int invokingState) {
			super(parent, invokingState);
		}
		@Override public int getRuleIndex() { return RULE_ifStatement; }
		@Override
		public <T> T accept(ParseTreeVisitor<? extends T> visitor) {
			if ( visitor instanceof KeywordVisitor ) return ((KeywordVisitor<? extends T>)visitor).visitIfStatement(this);
			else return visitor.visitChildren(this);
		}
	}

	public final IfStatementContext ifStatement() throws RecognitionException {
		IfStatementContext _localctx = new IfStatementContext(_ctx, getState());
		enterRule(_localctx, 36, RULE_ifStatement);
		try {
			int _alt;
			enterOuterAlt(_localctx, 1);
			{
			setState(288);
			match(IF);
			setState(289);
			match(L_PARENTHESES);
			setState(290);
			((IfStatementContext)_localctx).predict = predictStatement(0);
			setState(291);
			match(R_PARENTHESES);
			setState(292);
			match(L_BRACE);
			setState(293);
			statement(0);
			setState(294);
			match(R_BRACE);
			setState(298);
			_errHandler.sync(this);
			_alt = getInterpreter().adaptivePredict(_input,27,_ctx);
			while ( _alt!=2 && _alt!= ATN.INVALID_ALT_NUMBER ) {
				if ( _alt==1 ) {
					{
					{
					setState(295);
					elseIfStatement();
					}
					} 
				}
				setState(300);
				_errHandler.sync(this);
				_alt = getInterpreter().adaptivePredict(_input,27,_ctx);
			}
			setState(302);
			_errHandler.sync(this);
			switch ( getInterpreter().adaptivePredict(_input,28,_ctx) ) {
			case 1:
				{
				setState(301);
				elseStatement();
				}
				break;
			}
			}
		}
		catch (RecognitionException re) {
			_localctx.exception = re;
			_errHandler.reportError(this, re);
			_errHandler.recover(this, re);
		}
		finally {
			exitRule();
		}
		return _localctx;
	}

	public static class ElseIfStatementContext extends ParserRuleContext {
		public TerminalNode ELSE() { return getToken(KeywordParser.ELSE, 0); }
		public IfStatementContext ifStatement() {
			return getRuleContext(IfStatementContext.class,0);
		}
		public ElseIfStatementContext(ParserRuleContext parent, int invokingState) {
			super(parent, invokingState);
		}
		@Override public int getRuleIndex() { return RULE_elseIfStatement; }
		@Override
		public <T> T accept(ParseTreeVisitor<? extends T> visitor) {
			if ( visitor instanceof KeywordVisitor ) return ((KeywordVisitor<? extends T>)visitor).visitElseIfStatement(this);
			else return visitor.visitChildren(this);
		}
	}

	public final ElseIfStatementContext elseIfStatement() throws RecognitionException {
		ElseIfStatementContext _localctx = new ElseIfStatementContext(_ctx, getState());
		enterRule(_localctx, 38, RULE_elseIfStatement);
		try {
			enterOuterAlt(_localctx, 1);
			{
			setState(304);
			match(ELSE);
			setState(305);
			ifStatement();
			}
		}
		catch (RecognitionException re) {
			_localctx.exception = re;
			_errHandler.reportError(this, re);
			_errHandler.recover(this, re);
		}
		finally {
			exitRule();
		}
		return _localctx;
	}

	public static class ElseStatementContext extends ParserRuleContext {
		public TerminalNode ELSE() { return getToken(KeywordParser.ELSE, 0); }
		public TerminalNode L_BRACE() { return getToken(KeywordParser.L_BRACE, 0); }
		public StatementContext statement() {
			return getRuleContext(StatementContext.class,0);
		}
		public TerminalNode R_BRACE() { return getToken(KeywordParser.R_BRACE, 0); }
		public ElseStatementContext(ParserRuleContext parent, int invokingState) {
			super(parent, invokingState);
		}
		@Override public int getRuleIndex() { return RULE_elseStatement; }
		@Override
		public <T> T accept(ParseTreeVisitor<? extends T> visitor) {
			if ( visitor instanceof KeywordVisitor ) return ((KeywordVisitor<? extends T>)visitor).visitElseStatement(this);
			else return visitor.visitChildren(this);
		}
	}

	public final ElseStatementContext elseStatement() throws RecognitionException {
		ElseStatementContext _localctx = new ElseStatementContext(_ctx, getState());
		enterRule(_localctx, 40, RULE_elseStatement);
		try {
			enterOuterAlt(_localctx, 1);
			{
			setState(307);
			match(ELSE);
			setState(308);
			match(L_BRACE);
			setState(309);
			statement(0);
			setState(310);
			match(R_BRACE);
			}
		}
		catch (RecognitionException re) {
			_localctx.exception = re;
			_errHandler.reportError(this, re);
			_errHandler.recover(this, re);
		}
		finally {
			exitRule();
		}
		return _localctx;
	}

	public static class PredictStatementContext extends ParserRuleContext {
		public Token op;
		public StatementContext statement() {
			return getRuleContext(StatementContext.class,0);
		}
		public TerminalNode L_PARENTHESES() { return getToken(KeywordParser.L_PARENTHESES, 0); }
		public List<PredictStatementContext> predictStatement() {
			return getRuleContexts(PredictStatementContext.class);
		}
		public PredictStatementContext predictStatement(int i) {
			return getRuleContext(PredictStatementContext.class,i);
		}
		public TerminalNode R_PARENTHESES() { return getToken(KeywordParser.R_PARENTHESES, 0); }
		public TerminalNode AND() { return getToken(KeywordParser.AND, 0); }
		public TerminalNode XOR() { return getToken(KeywordParser.XOR, 0); }
		public TerminalNode OR() { return getToken(KeywordParser.OR, 0); }
		public PredictStatementContext(ParserRuleContext parent, int invokingState) {
			super(parent, invokingState);
		}
		@Override public int getRuleIndex() { return RULE_predictStatement; }
		@Override
		public <T> T accept(ParseTreeVisitor<? extends T> visitor) {
			if ( visitor instanceof KeywordVisitor ) return ((KeywordVisitor<? extends T>)visitor).visitPredictStatement(this);
			else return visitor.visitChildren(this);
		}
	}

	public final PredictStatementContext predictStatement() throws RecognitionException {
		return predictStatement(0);
	}

	private PredictStatementContext predictStatement(int _p) throws RecognitionException {
		ParserRuleContext _parentctx = _ctx;
		int _parentState = getState();
		PredictStatementContext _localctx = new PredictStatementContext(_ctx, _parentState);
		PredictStatementContext _prevctx = _localctx;
		int _startState = 42;
		enterRecursionRule(_localctx, 42, RULE_predictStatement, _p);
		try {
			int _alt;
			enterOuterAlt(_localctx, 1);
			{
			setState(318);
			_errHandler.sync(this);
			switch ( getInterpreter().adaptivePredict(_input,29,_ctx) ) {
			case 1:
				{
				setState(313);
				statement(0);
				}
				break;
			case 2:
				{
				setState(314);
				match(L_PARENTHESES);
				setState(315);
				predictStatement(0);
				setState(316);
				match(R_PARENTHESES);
				}
				break;
			}
			_ctx.stop = _input.LT(-1);
			setState(331);
			_errHandler.sync(this);
			_alt = getInterpreter().adaptivePredict(_input,31,_ctx);
			while ( _alt!=2 && _alt!= ATN.INVALID_ALT_NUMBER ) {
				if ( _alt==1 ) {
					if ( _parseListeners!=null ) triggerExitRuleEvent();
					_prevctx = _localctx;
					{
					setState(329);
					_errHandler.sync(this);
					switch ( getInterpreter().adaptivePredict(_input,30,_ctx) ) {
					case 1:
						{
						_localctx = new PredictStatementContext(_parentctx, _parentState);
						pushNewRecursionContext(_localctx, _startState, RULE_predictStatement);
						setState(320);
						if (!(precpred(_ctx, 3))) throw new FailedPredicateException(this, "precpred(_ctx, 3)");
						setState(321);
						((PredictStatementContext)_localctx).op = match(AND);
						setState(322);
						predictStatement(4);
						}
						break;
					case 2:
						{
						_localctx = new PredictStatementContext(_parentctx, _parentState);
						pushNewRecursionContext(_localctx, _startState, RULE_predictStatement);
						setState(323);
						if (!(precpred(_ctx, 2))) throw new FailedPredicateException(this, "precpred(_ctx, 2)");
						setState(324);
						((PredictStatementContext)_localctx).op = match(XOR);
						setState(325);
						predictStatement(3);
						}
						break;
					case 3:
						{
						_localctx = new PredictStatementContext(_parentctx, _parentState);
						pushNewRecursionContext(_localctx, _startState, RULE_predictStatement);
						setState(326);
						if (!(precpred(_ctx, 1))) throw new FailedPredicateException(this, "precpred(_ctx, 1)");
						setState(327);
						((PredictStatementContext)_localctx).op = match(OR);
						setState(328);
						predictStatement(2);
						}
						break;
					}
					} 
				}
				setState(333);
				_errHandler.sync(this);
				_alt = getInterpreter().adaptivePredict(_input,31,_ctx);
			}
			}
		}
		catch (RecognitionException re) {
			_localctx.exception = re;
			_errHandler.reportError(this, re);
			_errHandler.recover(this, re);
		}
		finally {
			unrollRecursionContexts(_parentctx);
		}
		return _localctx;
	}

	public static class ConstantContext extends ParserRuleContext {
		public TerminalNode STRING() { return getToken(KeywordParser.STRING, 0); }
		public TerminalNode INTEGER() { return getToken(KeywordParser.INTEGER, 0); }
		public TerminalNode FLOAT() { return getToken(KeywordParser.FLOAT, 0); }
		public TerminalNode PLUS() { return getToken(KeywordParser.PLUS, 0); }
		public TerminalNode MINUS() { return getToken(KeywordParser.MINUS, 0); }
		public TerminalNode BOOL() { return getToken(KeywordParser.BOOL, 0); }
		public TerminalNode NULL() { return getToken(KeywordParser.NULL, 0); }
		public ConstantContext(ParserRuleContext parent, int invokingState) {
			super(parent, invokingState);
		}
		@Override public int getRuleIndex() { return RULE_constant; }
		@Override
		public <T> T accept(ParseTreeVisitor<? extends T> visitor) {
			if ( visitor instanceof KeywordVisitor ) return ((KeywordVisitor<? extends T>)visitor).visitConstant(this);
			else return visitor.visitChildren(this);
		}
	}

	public final ConstantContext constant() throws RecognitionException {
		ConstantContext _localctx = new ConstantContext(_ctx, getState());
		enterRule(_localctx, 44, RULE_constant);
		int _la;
		try {
			setState(341);
			_errHandler.sync(this);
			switch (_input.LA(1)) {
			case STRING:
				enterOuterAlt(_localctx, 1);
				{
				setState(334);
				match(STRING);
				}
				break;
			case PLUS:
			case MINUS:
			case INTEGER:
			case FLOAT:
				enterOuterAlt(_localctx, 2);
				{
				setState(336);
				_errHandler.sync(this);
				_la = _input.LA(1);
				if (_la==PLUS || _la==MINUS) {
					{
					setState(335);
					_la = _input.LA(1);
					if ( !(_la==PLUS || _la==MINUS) ) {
					_errHandler.recoverInline(this);
					}
					else {
						if ( _input.LA(1)==Token.EOF ) matchedEOF = true;
						_errHandler.reportMatch(this);
						consume();
					}
					}
				}

				setState(338);
				_la = _input.LA(1);
				if ( !(_la==INTEGER || _la==FLOAT) ) {
				_errHandler.recoverInline(this);
				}
				else {
					if ( _input.LA(1)==Token.EOF ) matchedEOF = true;
					_errHandler.reportMatch(this);
					consume();
				}
				}
				break;
			case BOOL:
				enterOuterAlt(_localctx, 3);
				{
				setState(339);
				match(BOOL);
				}
				break;
			case NULL:
				enterOuterAlt(_localctx, 4);
				{
				setState(340);
				match(NULL);
				}
				break;
			default:
				throw new NoViableAltException(this);
			}
		}
		catch (RecognitionException re) {
			_localctx.exception = re;
			_errHandler.reportError(this, re);
			_errHandler.recover(this, re);
		}
		finally {
			exitRule();
		}
		return _localctx;
	}

	public static class ColumnContext extends ParserRuleContext {
		public ColumnContext(ParserRuleContext parent, int invokingState) {
			super(parent, invokingState);
		}
		@Override public int getRuleIndex() { return RULE_column; }
	 
		public ColumnContext() { }
		public void copyFrom(ColumnContext ctx) {
			super.copyFrom(ctx);
		}
	}
	public static class ColumnIdContext extends ColumnContext {
		public TerminalNode COLUMN_ID() { return getToken(KeywordParser.COLUMN_ID, 0); }
		public ColumnIdContext(ColumnContext ctx) { copyFrom(ctx); }
		@Override
		public <T> T accept(ParseTreeVisitor<? extends T> visitor) {
			if ( visitor instanceof KeywordVisitor ) return ((KeywordVisitor<? extends T>)visitor).visitColumnId(this);
			else return visitor.visitChildren(this);
		}
	}
	public static class ColumnNameContext extends ColumnContext {
		public TerminalNode COLUMN_NAME() { return getToken(KeywordParser.COLUMN_NAME, 0); }
		public TerminalNode HINT() { return getToken(KeywordParser.HINT, 0); }
		public ColumnNameContext(ColumnContext ctx) { copyFrom(ctx); }
		@Override
		public <T> T accept(ParseTreeVisitor<? extends T> visitor) {
			if ( visitor instanceof KeywordVisitor ) return ((KeywordVisitor<? extends T>)visitor).visitColumnName(this);
			else return visitor.visitChildren(this);
		}
	}
	public static class ColumnIdentityContext extends ColumnContext {
		public IdentityContext identity() {
			return getRuleContext(IdentityContext.class,0);
		}
		public TerminalNode HINT() { return getToken(KeywordParser.HINT, 0); }
		public ColumnIdentityContext(ColumnContext ctx) { copyFrom(ctx); }
		@Override
		public <T> T accept(ParseTreeVisitor<? extends T> visitor) {
			if ( visitor instanceof KeywordVisitor ) return ((KeywordVisitor<? extends T>)visitor).visitColumnIdentity(this);
			else return visitor.visitChildren(this);
		}
	}

	public final ColumnContext column() throws RecognitionException {
		ColumnContext _localctx = new ColumnContext(_ctx, getState());
		enterRule(_localctx, 46, RULE_column);
		try {
			setState(352);
			_errHandler.sync(this);
			switch (_input.LA(1)) {
			case COLUMN_ID:
				_localctx = new ColumnIdContext(_localctx);
				enterOuterAlt(_localctx, 1);
				{
				setState(343);
				match(COLUMN_ID);
				}
				break;
			case COLUMN_NAME:
				_localctx = new ColumnNameContext(_localctx);
				enterOuterAlt(_localctx, 2);
				{
				setState(344);
				match(COLUMN_NAME);
				setState(346);
				_errHandler.sync(this);
				switch ( getInterpreter().adaptivePredict(_input,34,_ctx) ) {
				case 1:
					{
					setState(345);
					match(HINT);
					}
					break;
				}
				}
				break;
			case IF:
			case LIKE:
			case NOT:
			case IDENTITY:
				_localctx = new ColumnIdentityContext(_localctx);
				enterOuterAlt(_localctx, 3);
				{
				setState(348);
				identity();
				setState(350);
				_errHandler.sync(this);
				switch ( getInterpreter().adaptivePredict(_input,35,_ctx) ) {
				case 1:
					{
					setState(349);
					match(HINT);
					}
					break;
				}
				}
				break;
			default:
				throw new NoViableAltException(this);
			}
		}
		catch (RecognitionException re) {
			_localctx.exception = re;
			_errHandler.reportError(this, re);
			_errHandler.recover(this, re);
		}
		finally {
			exitRule();
		}
		return _localctx;
	}

	public static class IdentityContext extends ParserRuleContext {
		public TerminalNode IDENTITY() { return getToken(KeywordParser.IDENTITY, 0); }
		public TerminalNode IF() { return getToken(KeywordParser.IF, 0); }
		public TerminalNode LIKE() { return getToken(KeywordParser.LIKE, 0); }
		public TerminalNode NOT() { return getToken(KeywordParser.NOT, 0); }
		public IdentityContext(ParserRuleContext parent, int invokingState) {
			super(parent, invokingState);
		}
		@Override public int getRuleIndex() { return RULE_identity; }
		@Override
		public <T> T accept(ParseTreeVisitor<? extends T> visitor) {
			if ( visitor instanceof KeywordVisitor ) return ((KeywordVisitor<? extends T>)visitor).visitIdentity(this);
			else return visitor.visitChildren(this);
		}
	}

	public final IdentityContext identity() throws RecognitionException {
		IdentityContext _localctx = new IdentityContext(_ctx, getState());
		enterRule(_localctx, 48, RULE_identity);
		int _la;
		try {
			enterOuterAlt(_localctx, 1);
			{
			setState(354);
			_la = _input.LA(1);
			if ( !(((((_la - 59)) & ~0x3f) == 0 && ((1L << (_la - 59)) & ((1L << (IF - 59)) | (1L << (LIKE - 59)) | (1L << (NOT - 59)) | (1L << (IDENTITY - 59)))) != 0)) ) {
			_errHandler.recoverInline(this);
			}
			else {
				if ( _input.LA(1)==Token.EOF ) matchedEOF = true;
				_errHandler.reportMatch(this);
				consume();
			}
			}
		}
		catch (RecognitionException re) {
			_localctx.exception = re;
			_errHandler.reportError(this, re);
			_errHandler.recover(this, re);
		}
		finally {
			exitRule();
		}
		return _localctx;
	}

	public boolean sempred(RuleContext _localctx, int ruleIndex, int predIndex) {
		switch (ruleIndex) {
		case 10:
			return statement_sempred((StatementContext)_localctx, predIndex);
		case 21:
			return predictStatement_sempred((PredictStatementContext)_localctx, predIndex);
		}
		return true;
	}
	private boolean statement_sempred(StatementContext _localctx, int predIndex) {
		switch (predIndex) {
		case 0:
			return precpred(_ctx, 14);
		case 1:
			return precpred(_ctx, 13);
		case 2:
			return precpred(_ctx, 12);
		case 3:
			return precpred(_ctx, 11);
		case 4:
			return precpred(_ctx, 10);
		case 5:
			return precpred(_ctx, 8);
		case 6:
			return precpred(_ctx, 6);
		case 7:
			return precpred(_ctx, 5);
		case 8:
			return precpred(_ctx, 4);
		case 9:
			return precpred(_ctx, 1);
		case 10:
			return precpred(_ctx, 9);
		}
		return true;
	}
	private boolean predictStatement_sempred(PredictStatementContext _localctx, int predIndex) {
		switch (predIndex) {
		case 11:
			return precpred(_ctx, 3);
		case 12:
			return precpred(_ctx, 2);
		case 13:
			return precpred(_ctx, 1);
		}
		return true;
	}

	public static final String _serializedATN =
		"\3\u608b\ua72a\u8133\ub9ed\u417c\u3be7\u7786\u5964\3S\u0167\4\2\t\2\4"+
		"\3\t\3\4\4\t\4\4\5\t\5\4\6\t\6\4\7\t\7\4\b\t\b\4\t\t\t\4\n\t\n\4\13\t"+
		"\13\4\f\t\f\4\r\t\r\4\16\t\16\4\17\t\17\4\20\t\20\4\21\t\21\4\22\t\22"+
		"\4\23\t\23\4\24\t\24\4\25\t\25\4\26\t\26\4\27\t\27\4\30\t\30\4\31\t\31"+
		"\4\32\t\32\3\2\3\2\5\2\67\n\2\6\29\n\2\r\2\16\2:\3\2\3\2\3\3\3\3\3\3\3"+
		"\3\3\3\3\3\5\3E\n\3\3\4\3\4\6\4I\n\4\r\4\16\4J\3\4\5\4N\n\4\3\5\3\5\3"+
		"\5\3\5\3\5\3\5\3\5\5\5W\n\5\3\6\3\6\3\6\3\7\3\7\3\7\3\7\3\7\3\7\3\7\3"+
		"\7\3\7\3\7\3\7\3\7\3\7\3\7\3\7\3\7\3\7\3\7\3\7\3\7\3\7\3\7\3\7\3\7\3\7"+
		"\3\7\3\7\3\7\3\7\3\7\3\7\5\7{\n\7\3\b\5\b~\n\b\3\b\3\b\3\b\3\t\3\t\5\t"+
		"\u0085\n\t\3\t\5\t\u0088\n\t\3\t\3\t\3\t\3\t\3\t\3\t\3\t\3\t\3\t\5\t\u0093"+
		"\n\t\3\n\3\n\3\13\3\13\3\13\3\f\3\f\3\f\3\f\3\f\3\f\3\f\3\f\3\f\3\f\3"+
		"\f\3\f\3\f\3\f\5\f\u00a8\n\f\3\f\5\f\u00ab\n\f\3\f\3\f\3\f\3\f\3\f\3\f"+
		"\3\f\3\f\3\f\3\f\3\f\3\f\3\f\3\f\3\f\3\f\3\f\5\f\u00be\n\f\3\f\3\f\3\f"+
		"\3\f\3\f\3\f\3\f\3\f\3\f\3\f\3\f\3\f\3\f\3\f\3\f\3\f\5\f\u00d0\n\f\3\f"+
		"\3\f\3\f\3\f\3\f\7\f\u00d7\n\f\f\f\16\f\u00da\13\f\3\r\3\r\3\r\5\r\u00df"+
		"\n\r\3\r\3\r\3\16\3\16\3\16\7\16\u00e6\n\16\f\16\16\16\u00e9\13\16\3\17"+
		"\3\17\3\17\3\17\3\17\5\17\u00f0\n\17\3\20\3\20\3\20\7\20\u00f5\n\20\f"+
		"\20\16\20\u00f8\13\20\3\21\3\21\3\21\3\21\3\21\3\21\6\21\u0100\n\21\r"+
		"\21\16\21\u0101\3\21\3\21\3\21\3\21\3\21\3\21\6\21\u010a\n\21\r\21\16"+
		"\21\u010b\5\21\u010e\n\21\3\21\3\21\5\21\u0112\n\21\3\21\3\21\3\22\3\22"+
		"\5\22\u0118\n\22\3\23\3\23\3\23\3\23\3\23\3\23\3\23\3\23\3\23\3\24\3\24"+
		"\3\24\3\24\3\24\3\24\3\24\3\24\7\24\u012b\n\24\f\24\16\24\u012e\13\24"+
		"\3\24\5\24\u0131\n\24\3\25\3\25\3\25\3\26\3\26\3\26\3\26\3\26\3\27\3\27"+
		"\3\27\3\27\3\27\3\27\5\27\u0141\n\27\3\27\3\27\3\27\3\27\3\27\3\27\3\27"+
		"\3\27\3\27\7\27\u014c\n\27\f\27\16\27\u014f\13\27\3\30\3\30\5\30\u0153"+
		"\n\30\3\30\3\30\3\30\5\30\u0158\n\30\3\31\3\31\3\31\5\31\u015d\n\31\3"+
		"\31\3\31\5\31\u0161\n\31\5\31\u0163\n\31\3\32\3\32\3\32\2\4\26,\33\2\4"+
		"\6\b\n\f\16\20\22\24\26\30\32\34\36 \"$&(*,.\60\62\2\23\4\2\5\b\13\f\3"+
		"\2\r\21\3\2\t\n\4\2\22\26\33\34\4\2\3\3))\4\2\27\27\31\31\4\2\30\30\32"+
		"\32\3\2\35!\3\2\'(\3\2#$\3\2,-\6\2\5\21\33\33\"\")-\3\2\66\67\3\289\4"+
		"\2\22\26\33\33\3\2KL\5\2=>@@NN\2\u018f\28\3\2\2\2\4D\3\2\2\2\6H\3\2\2"+
		"\2\bV\3\2\2\2\nX\3\2\2\2\fz\3\2\2\2\16}\3\2\2\2\20\u0092\3\2\2\2\22\u0094"+
		"\3\2\2\2\24\u0096\3\2\2\2\26\u00aa\3\2\2\2\30\u00db\3\2\2\2\32\u00e2\3"+
		"\2\2\2\34\u00ef\3\2\2\2\36\u00f1\3\2\2\2 \u00f9\3\2\2\2\"\u0117\3\2\2"+
		"\2$\u0119\3\2\2\2&\u0122\3\2\2\2(\u0132\3\2\2\2*\u0135\3\2\2\2,\u0140"+
		"\3\2\2\2.\u0157\3\2\2\2\60\u0162\3\2\2\2\62\u0164\3\2\2\2\64\66\5\4\3"+
		"\2\65\67\7\4\2\2\66\65\3\2\2\2\66\67\3\2\2\2\679\3\2\2\28\64\3\2\2\29"+
		":\3\2\2\2:8\3\2\2\2:;\3\2\2\2;<\3\2\2\2<=\7\2\2\3=\3\3\2\2\2>E\5\16\b"+
		"\2?E\5\b\5\2@E\5\f\7\2AE\5\n\6\2BE\5\20\t\2CE\5\6\4\2D>\3\2\2\2D?\3\2"+
		"\2\2D@\3\2\2\2DA\3\2\2\2DB\3\2\2\2DC\3\2\2\2E\5\3\2\2\2FI\5\26\f\2GI\5"+
		"\22\n\2HF\3\2\2\2HG\3\2\2\2IJ\3\2\2\2JH\3\2\2\2JK\3\2\2\2KM\3\2\2\2LN"+
		"\7P\2\2ML\3\2\2\2MN\3\2\2\2N\7\3\2\2\2OP\5\6\4\2PQ\t\2\2\2QW\3\2\2\2R"+
		"S\7\t\2\2SW\5\6\4\2TU\7\n\2\2UW\5\6\4\2VO\3\2\2\2VR\3\2\2\2VT\3\2\2\2"+
		"W\t\3\2\2\2XY\5\6\4\2YZ\t\3\2\2Z\13\3\2\2\2[\\\5\6\4\2\\]\t\4\2\2]{\3"+
		"\2\2\2^_\5\6\4\2_`\t\5\2\2`a\5\6\4\2a{\3\2\2\2bc\5\6\4\2cd\t\6\2\2de\5"+
		"\6\4\2ef\7+\2\2fg\5\6\4\2gh\7&\2\2h{\3\2\2\2ij\5\6\4\2jk\t\7\2\2kl\5\6"+
		"\4\2l{\3\2\2\2mn\5\6\4\2no\7*\2\2op\5\6\4\2pq\t\b\2\2q{\3\2\2\2rs\5\6"+
		"\4\2st\t\t\2\2t{\3\2\2\2uv\5\6\4\2vw\7)\2\2wx\5\6\4\2xy\t\n\2\2y{\3\2"+
		"\2\2z[\3\2\2\2z^\3\2\2\2zb\3\2\2\2zi\3\2\2\2zm\3\2\2\2zr\3\2\2\2zu\3\2"+
		"\2\2{\r\3\2\2\2|~\7*\2\2}|\3\2\2\2}~\3\2\2\2~\177\3\2\2\2\177\u0080\5"+
		"\6\4\2\u0080\u0081\t\13\2\2\u0081\17\3\2\2\2\u0082\u0084\5\6\4\2\u0083"+
		"\u0085\7\"\2\2\u0084\u0083\3\2\2\2\u0084\u0085\3\2\2\2\u0085\u0087\3\2"+
		"\2\2\u0086\u0088\7)\2\2\u0087\u0086\3\2\2\2\u0087\u0088\3\2\2\2\u0088"+
		"\u0089\3\2\2\2\u0089\u008a\t\n\2\2\u008a\u008b\7K\2\2\u008b\u008c\t\f"+
		"\2\2\u008c\u0093\3\2\2\2\u008d\u008e\5\6\4\2\u008e\u008f\7%\2\2\u008f"+
		"\u0090\7K\2\2\u0090\u0091\t\f\2\2\u0091\u0093\3\2\2\2\u0092\u0082\3\2"+
		"\2\2\u0092\u008d\3\2\2\2\u0093\21\3\2\2\2\u0094\u0095\t\r\2\2\u0095\23"+
		"\3\2\2\2\u0096\u0097\5\26\f\2\u0097\u0098\7\2\2\3\u0098\25\3\2\2\2\u0099"+
		"\u009a\b\f\1\2\u009a\u00ab\5\"\22\2\u009b\u00ab\5\30\r\2\u009c\u00ab\5"+
		" \21\2\u009d\u00ab\5.\30\2\u009e\u00ab\5\60\31\2\u009f\u00a0\7@\2\2\u00a0"+
		"\u00ab\5\26\f\t\u00a1\u00a2\7.\2\2\u00a2\u00a3\5\26\f\2\u00a3\u00a4\7"+
		"/\2\2\u00a4\u00ab\3\2\2\2\u00a5\u00a7\7\62\2\2\u00a6\u00a8\5\36\20\2\u00a7"+
		"\u00a6\3\2\2\2\u00a7\u00a8\3\2\2\2\u00a8\u00a9\3\2\2\2\u00a9\u00ab\7\63"+
		"\2\2\u00aa\u0099\3\2\2\2\u00aa\u009b\3\2\2\2\u00aa\u009c\3\2\2\2\u00aa"+
		"\u009d\3\2\2\2\u00aa\u009e\3\2\2\2\u00aa\u009f\3\2\2\2\u00aa\u00a1\3\2"+
		"\2\2\u00aa\u00a5\3\2\2\2\u00ab\u00d8\3\2\2\2\u00ac\u00ad\f\20\2\2\u00ad"+
		"\u00ae\7:\2\2\u00ae\u00d7\5\26\f\20\u00af\u00b0\f\17\2\2\u00b0\u00b1\7"+
		";\2\2\u00b1\u00d7\5\26\f\20\u00b2\u00b3\f\16\2\2\u00b3\u00b4\t\16\2\2"+
		"\u00b4\u00d7\5\26\f\17\u00b5\u00b6\f\r\2\2\u00b6\u00b7\t\17\2\2\u00b7"+
		"\u00d7\5\26\f\16\u00b8\u00b9\f\f\2\2\u00b9\u00ba\t\20\2\2\u00ba\u00d7"+
		"\5\26\f\r\u00bb\u00bd\f\n\2\2\u00bc\u00be\7@\2\2\u00bd\u00bc\3\2\2\2\u00bd"+
		"\u00be\3\2\2\2\u00be\u00bf\3\2\2\2\u00bf\u00c0\7>\2\2\u00c0\u00d7\5\26"+
		"\f\13\u00c1\u00c2\f\b\2\2\u00c2\u00c3\7A\2\2\u00c3\u00d7\5\26\f\t\u00c4"+
		"\u00c5\f\7\2\2\u00c5\u00c6\7C\2\2\u00c6\u00d7\5\26\f\b\u00c7\u00c8\f\6"+
		"\2\2\u00c8\u00c9\7B\2\2\u00c9\u00d7\5\26\f\7\u00ca\u00cb\f\3\2\2\u00cb"+
		"\u00cc\7<\2\2\u00cc\u00d7\5\26\f\4\u00cd\u00cf\f\13\2\2\u00ce\u00d0\7"+
		"@\2\2\u00cf\u00ce\3\2\2\2\u00cf\u00d0\3\2\2\2\u00d0\u00d1\3\2\2\2\u00d1"+
		"\u00d2\7?\2\2\u00d2\u00d3\7.\2\2\u00d3\u00d4\5\36\20\2\u00d4\u00d5\7/"+
		"\2\2\u00d5\u00d7\3\2\2\2\u00d6\u00ac\3\2\2\2\u00d6\u00af\3\2\2\2\u00d6"+
		"\u00b2\3\2\2\2\u00d6\u00b5\3\2\2\2\u00d6\u00b8\3\2\2\2\u00d6\u00bb\3\2"+
		"\2\2\u00d6\u00c1\3\2\2\2\u00d6\u00c4\3\2\2\2\u00d6\u00c7\3\2\2\2\u00d6"+
		"\u00ca\3\2\2\2\u00d6\u00cd\3\2\2\2\u00d7\u00da\3\2\2\2\u00d8\u00d6\3\2"+
		"\2\2\u00d8\u00d9\3\2\2\2\u00d9\27\3\2\2\2\u00da\u00d8\3\2\2\2\u00db\u00dc"+
		"\5\62\32\2\u00dc\u00de\7.\2\2\u00dd\u00df\5\32\16\2\u00de\u00dd\3\2\2"+
		"\2\u00de\u00df\3\2\2\2\u00df\u00e0\3\2\2\2\u00e0\u00e1\7/\2\2\u00e1\31"+
		"\3\2\2\2\u00e2\u00e7\5\34\17\2\u00e3\u00e4\7\64\2\2\u00e4\u00e6\5\34\17"+
		"\2\u00e5\u00e3\3\2\2\2\u00e6\u00e9\3\2\2\2\u00e7\u00e5\3\2\2\2\u00e7\u00e8"+
		"\3\2\2\2\u00e8\33\3\2\2\2\u00e9\u00e7\3\2\2\2\u00ea\u00f0\5\26\f\2\u00eb"+
		"\u00ec\5\62\32\2\u00ec\u00ed\7\33\2\2\u00ed\u00ee\5\26\f\2\u00ee\u00f0"+
		"\3\2\2\2\u00ef\u00ea\3\2\2\2\u00ef\u00eb\3\2\2\2\u00f0\35\3\2\2\2\u00f1"+
		"\u00f6\5\26\f\2\u00f2\u00f3\7\64\2\2\u00f3\u00f5\5\26\f\2\u00f4\u00f2"+
		"\3\2\2\2\u00f5\u00f8\3\2\2\2\u00f6\u00f4\3\2\2\2\u00f6\u00f7\3\2\2\2\u00f7"+
		"\37\3\2\2\2\u00f8\u00f6\3\2\2\2\u00f9\u010d\7D\2\2\u00fa\u00fb\7E\2\2"+
		"\u00fb\u00fc\5,\27\2\u00fc\u00fd\7F\2\2\u00fd\u00fe\5\26\f\2\u00fe\u0100"+
		"\3\2\2\2\u00ff\u00fa\3\2\2\2\u0100\u0101\3\2\2\2\u0101\u00ff\3\2\2\2\u0101"+
		"\u0102\3\2\2\2\u0102\u010e\3\2\2\2\u0103\u0109\5\26\f\2\u0104\u0105\7"+
		"E\2\2\u0105\u0106\5.\30\2\u0106\u0107\7F\2\2\u0107\u0108\5\26\f\2\u0108"+
		"\u010a\3\2\2\2\u0109\u0104\3\2\2\2\u010a\u010b\3\2\2\2\u010b\u0109\3\2"+
		"\2\2\u010b\u010c\3\2\2\2\u010c\u010e\3\2\2\2\u010d\u00ff\3\2\2\2\u010d"+
		"\u0103\3\2\2\2\u010e\u0111\3\2\2\2\u010f\u0110\7G\2\2\u0110\u0112\5\26"+
		"\f\2\u0111\u010f\3\2\2\2\u0111\u0112\3\2\2\2\u0112\u0113\3\2\2\2\u0113"+
		"\u0114\7\32\2\2\u0114!\3\2\2\2\u0115\u0118\5$\23\2\u0116\u0118\5&\24\2"+
		"\u0117\u0115\3\2\2\2\u0117\u0116\3\2\2\2\u0118#\3\2\2\2\u0119\u011a\7"+
		"=\2\2\u011a\u011b\7.\2\2\u011b\u011c\5,\27\2\u011c\u011d\7\64\2\2\u011d"+
		"\u011e\5\26\f\2\u011e\u011f\7\64\2\2\u011f\u0120\5\26\f\2\u0120\u0121"+
		"\7/\2\2\u0121%\3\2\2\2\u0122\u0123\7=\2\2\u0123\u0124\7.\2\2\u0124\u0125"+
		"\5,\27\2\u0125\u0126\7/\2\2\u0126\u0127\7\60\2\2\u0127\u0128\5\26\f\2"+
		"\u0128\u012c\7\61\2\2\u0129\u012b\5(\25\2\u012a\u0129\3\2\2\2\u012b\u012e"+
		"\3\2\2\2\u012c\u012a\3\2\2\2\u012c\u012d\3\2\2\2\u012d\u0130\3\2\2\2\u012e"+
		"\u012c\3\2\2\2\u012f\u0131\5*\26\2\u0130\u012f\3\2\2\2\u0130\u0131\3\2"+
		"\2\2\u0131\'\3\2\2\2\u0132\u0133\7G\2\2\u0133\u0134\5&\24\2\u0134)\3\2"+
		"\2\2\u0135\u0136\7G\2\2\u0136\u0137\7\60\2\2\u0137\u0138\5\26\f\2\u0138"+
		"\u0139\7\61\2\2\u0139+\3\2\2\2\u013a\u013b\b\27\1\2\u013b\u0141\5\26\f"+
		"\2\u013c\u013d\7.\2\2\u013d\u013e\5,\27\2\u013e\u013f\7/\2\2\u013f\u0141"+
		"\3\2\2\2\u0140\u013a\3\2\2\2\u0140\u013c\3\2\2\2\u0141\u014d\3\2\2\2\u0142"+
		"\u0143\f\5\2\2\u0143\u0144\7A\2\2\u0144\u014c\5,\27\6\u0145\u0146\f\4"+
		"\2\2\u0146\u0147\7C\2\2\u0147\u014c\5,\27\5\u0148\u0149\f\3\2\2\u0149"+
		"\u014a\7B\2\2\u014a\u014c\5,\27\4\u014b\u0142\3\2\2\2\u014b\u0145\3\2"+
		"\2\2\u014b\u0148\3\2\2\2\u014c\u014f\3\2\2\2\u014d\u014b\3\2\2\2\u014d"+
		"\u014e\3\2\2\2\u014e-\3\2\2\2\u014f\u014d\3\2\2\2\u0150\u0158\7J\2\2\u0151"+
		"\u0153\t\17\2\2\u0152\u0151\3\2\2\2\u0152\u0153\3\2\2\2\u0153\u0154\3"+
		"\2\2\2\u0154\u0158\t\21\2\2\u0155\u0158\7H\2\2\u0156\u0158\7I\2\2\u0157"+
		"\u0150\3\2\2\2\u0157\u0152\3\2\2\2\u0157\u0155\3\2\2\2\u0157\u0156\3\2"+
		"\2\2\u0158/\3\2\2\2\u0159\u0163\7M\2\2\u015a\u015c\7O\2\2\u015b\u015d"+
		"\7P\2\2\u015c\u015b\3\2\2\2\u015c\u015d\3\2\2\2\u015d\u0163\3\2\2\2\u015e"+
		"\u0160\5\62\32\2\u015f\u0161\7P\2\2\u0160\u015f\3\2\2\2\u0160\u0161\3"+
		"\2\2\2\u0161\u0163\3\2\2\2\u0162\u0159\3\2\2\2\u0162\u015a\3\2\2\2\u0162"+
		"\u015e\3\2\2\2\u0163\61\3\2\2\2\u0164\u0165\t\22\2\2\u0165\63\3\2\2\2"+
		"\'\66:DHJMVz}\u0084\u0087\u0092\u00a7\u00aa\u00bd\u00cf\u00d6\u00d8\u00de"+
		"\u00e7\u00ef\u00f6\u0101\u010b\u010d\u0111\u0117\u012c\u0130\u0140\u014b"+
		"\u014d\u0152\u0157\u015c\u0160\u0162";
	public static final ATN _ATN =
		new ATNDeserializer().deserialize(_serializedATN.toCharArray());
	static {
		_decisionToDFA = new DFA[_ATN.getNumberOfDecisions()];
		for (int i = 0; i < _ATN.getNumberOfDecisions(); i++) {
			_decisionToDFA[i] = new DFA(_ATN.getDecisionState(i), i);
		}
	}
}