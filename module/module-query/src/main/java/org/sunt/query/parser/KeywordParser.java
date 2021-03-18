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
		AFTER=38, AUX_ZAI=39, AUX_YI=40, AUX_HE=41, AUX_DE=42, AUX_MIN=43, LOGIC_OR=44, 
		LOGIC_AND=45, L_PARENTHESES=46, R_PARENTHESES=47, L_BRACE=48, R_BRACE=49, 
		L_SQUARE=50, R_SQUARE=51, COMMA=52, COLON=53, MUL=54, DIV=55, PLUS=56, 
		MINUS=57, POWER=58, MOD=59, IFNULL=60, IF=61, LIKE=62, IN=63, NOT=64, 
		AND=65, OR=66, XOR=67, CASE=68, WHEN=69, THEN=70, ELSE=71, BOOL=72, NULL=73, 
		STRING=74, INTEGER=75, FLOAT=76, COLUMN_ID=77, IDENTITY=78, COLUMN_NAME=79, 
		HINT=80, WS=81, LINE_COMMENT=82, BLOCK_COMMENT=83;
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
			"'\u5728'", null, null, "'\u7684'", null, null, null, "'('", "')'", "'{'", 
			"'}'", "'['", "']'", "','", "':'", "'*'", "'/'", "'+'", "'-'", "'^'"
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
			"AUX_HE", "AUX_DE", "AUX_MIN", "LOGIC_OR", "LOGIC_AND", "L_PARENTHESES", 
			"R_PARENTHESES", "L_BRACE", "R_BRACE", "L_SQUARE", "R_SQUARE", "COMMA", 
			"COLON", "MUL", "DIV", "PLUS", "MINUS", "POWER", "MOD", "IFNULL", "IF", 
			"LIKE", "IN", "NOT", "AND", "OR", "XOR", "CASE", "WHEN", "THEN", "ELSE", 
			"BOOL", "NULL", "STRING", "INTEGER", "FLOAT", "COLUMN_ID", "IDENTITY", 
			"COLUMN_NAME", "HINT", "WS", "LINE_COMMENT", "BLOCK_COMMENT"
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
			} while ( (((_la) & ~0x3f) == 0 && ((1L << _la) & ((1L << COUNT) | (1L << COUNT_DISTINCT) | (1L << SUM) | (1L << AVG) | (1L << GREATEST) | (1L << LEATEST) | (1L << MAX) | (1L << MIN) | (1L << GROUP_YEAR) | (1L << GROUP_QUARTER) | (1L << GROUP_MONTH) | (1L << GROUP_WEEK) | (1L << GROUP_DAY) | (1L << EQUAL) | (1L << ORDER) | (1L << AUX_ZAI) | (1L << AUX_YI) | (1L << AUX_HE) | (1L << AUX_DE) | (1L << AUX_MIN) | (1L << L_PARENTHESES) | (1L << L_SQUARE) | (1L << PLUS) | (1L << MINUS) | (1L << IF) | (1L << LIKE))) != 0) || ((((_la - 64)) & ~0x3f) == 0 && ((1L << (_la - 64)) & ((1L << (NOT - 64)) | (1L << (CASE - 64)) | (1L << (BOOL - 64)) | (1L << (NULL - 64)) | (1L << (STRING - 64)) | (1L << (INTEGER - 64)) | (1L << (FLOAT - 64)) | (1L << (COLUMN_ID - 64)) | (1L << (IDENTITY - 64)) | (1L << (COLUMN_NAME - 64)))) != 0) );
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
				filter(0);
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
			switch ( getInterpreter().adaptivePredict(_input,5,_ctx) ) {
			case 1:
				{
				setState(74);
				match(HINT);
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
		public Token op;
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
	public static class LogicOrFilterContext extends FilterContext {
		public Token op;
		public List<FilterContext> filter() {
			return getRuleContexts(FilterContext.class);
		}
		public FilterContext filter(int i) {
			return getRuleContext(FilterContext.class,i);
		}
		public TerminalNode LOGIC_OR() { return getToken(KeywordParser.LOGIC_OR, 0); }
		public LogicOrFilterContext(FilterContext ctx) { copyFrom(ctx); }
		@Override
		public <T> T accept(ParseTreeVisitor<? extends T> visitor) {
			if ( visitor instanceof KeywordVisitor ) return ((KeywordVisitor<? extends T>)visitor).visitLogicOrFilter(this);
			else return visitor.visitChildren(this);
		}
	}
	public static class MostFilterContext extends FilterContext {
		public Token op;
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
		public Token op;
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
		public Token op;
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
		public Token op;
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
		public Token op;
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
	public static class LogicAndFilterContext extends FilterContext {
		public Token op;
		public List<FilterContext> filter() {
			return getRuleContexts(FilterContext.class);
		}
		public FilterContext filter(int i) {
			return getRuleContext(FilterContext.class,i);
		}
		public TerminalNode LOGIC_AND() { return getToken(KeywordParser.LOGIC_AND, 0); }
		public LogicAndFilterContext(FilterContext ctx) { copyFrom(ctx); }
		@Override
		public <T> T accept(ParseTreeVisitor<? extends T> visitor) {
			if ( visitor instanceof KeywordVisitor ) return ((KeywordVisitor<? extends T>)visitor).visitLogicAndFilter(this);
			else return visitor.visitChildren(this);
		}
	}

	public final FilterContext filter() throws RecognitionException {
		return filter(0);
	}

	private FilterContext filter(int _p) throws RecognitionException {
		ParserRuleContext _parentctx = _ctx;
		int _parentState = getState();
		FilterContext _localctx = new FilterContext(_ctx, _parentState);
		FilterContext _prevctx = _localctx;
		int _startState = 10;
		enterRecursionRule(_localctx, 10, RULE_filter, _p);
		int _la;
		try {
			int _alt;
			enterOuterAlt(_localctx, 1);
			{
			setState(121);
			_errHandler.sync(this);
			switch ( getInterpreter().adaptivePredict(_input,7,_ctx) ) {
			case 1:
				{
				_localctx = new MostFilterContext(_localctx);
				_ctx = _localctx;
				_prevctx = _localctx;

				setState(90);
				segment();
				setState(91);
				((MostFilterContext)_localctx).op = _input.LT(1);
				_la = _input.LA(1);
				if ( !(_la==GREATEST || _la==LEATEST) ) {
					((MostFilterContext)_localctx).op = (Token)_errHandler.recoverInline(this);
				}
				else {
					if ( _input.LA(1)==Token.EOF ) matchedEOF = true;
					_errHandler.reportMatch(this);
					consume();
				}
				}
				break;
			case 2:
				{
				_localctx = new CompareFilterContext(_localctx);
				_ctx = _localctx;
				_prevctx = _localctx;
				setState(93);
				segment();
				setState(94);
				((CompareFilterContext)_localctx).op = _input.LT(1);
				_la = _input.LA(1);
				if ( !((((_la) & ~0x3f) == 0 && ((1L << _la) & ((1L << GREATER) | (1L << LESS) | (1L << GREATER_EQUAL) | (1L << LESS_EQUAL) | (1L << NOT_EQUAL) | (1L << EQUAL) | (1L << CONTAINS))) != 0)) ) {
					((CompareFilterContext)_localctx).op = (Token)_errHandler.recoverInline(this);
				}
				else {
					if ( _input.LA(1)==Token.EOF ) matchedEOF = true;
					_errHandler.reportMatch(this);
					consume();
				}
				setState(95);
				segment();
				}
				break;
			case 3:
				{
				_localctx = new BetweenFilterContext(_localctx);
				_ctx = _localctx;
				_prevctx = _localctx;
				setState(97);
				segment();
				setState(98);
				((BetweenFilterContext)_localctx).op = _input.LT(1);
				_la = _input.LA(1);
				if ( !(_la==T__0 || _la==AUX_ZAI) ) {
					((BetweenFilterContext)_localctx).op = (Token)_errHandler.recoverInline(this);
				}
				else {
					if ( _input.LA(1)==Token.EOF ) matchedEOF = true;
					_errHandler.reportMatch(this);
					consume();
				}
				setState(99);
				segment();
				setState(100);
				match(AUX_HE);
				setState(101);
				segment();
				setState(102);
				match(INSIDE);
				}
				break;
			case 4:
				{
				_localctx = new StringFilterContext(_localctx);
				_ctx = _localctx;
				_prevctx = _localctx;
				setState(104);
				segment();
				setState(105);
				((StringFilterContext)_localctx).op = _input.LT(1);
				_la = _input.LA(1);
				if ( !(_la==START_WITH || _la==END_WITH) ) {
					((StringFilterContext)_localctx).op = (Token)_errHandler.recoverInline(this);
				}
				else {
					if ( _input.LA(1)==Token.EOF ) matchedEOF = true;
					_errHandler.reportMatch(this);
					consume();
				}
				setState(106);
				segment();
				}
				break;
			case 5:
				{
				_localctx = new StringFilterContext(_localctx);
				_ctx = _localctx;
				_prevctx = _localctx;
				setState(108);
				segment();
				setState(109);
				((StringFilterContext)_localctx).op = match(AUX_YI);
				setState(110);
				segment();
				setState(111);
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
				{
				_localctx = new LastDateFilterContext(_localctx);
				_ctx = _localctx;
				_prevctx = _localctx;
				setState(113);
				segment();
				setState(114);
				((LastDateFilterContext)_localctx).op = _input.LT(1);
				_la = _input.LA(1);
				if ( !((((_la) & ~0x3f) == 0 && ((1L << _la) & ((1L << LAST_YEAR) | (1L << LAST_QUARTER) | (1L << LAST_MONTH) | (1L << LAST_WEEK) | (1L << LAST_DAY))) != 0)) ) {
					((LastDateFilterContext)_localctx).op = (Token)_errHandler.recoverInline(this);
				}
				else {
					if ( _input.LA(1)==Token.EOF ) matchedEOF = true;
					_errHandler.reportMatch(this);
					consume();
				}
				}
				break;
			case 7:
				{
				_localctx = new CompareDateFilterContext(_localctx);
				_ctx = _localctx;
				_prevctx = _localctx;
				setState(116);
				segment();
				setState(117);
				match(AUX_ZAI);
				setState(118);
				segment();
				setState(119);
				((CompareDateFilterContext)_localctx).op = _input.LT(1);
				_la = _input.LA(1);
				if ( !(_la==BEFORE || _la==AFTER) ) {
					((CompareDateFilterContext)_localctx).op = (Token)_errHandler.recoverInline(this);
				}
				else {
					if ( _input.LA(1)==Token.EOF ) matchedEOF = true;
					_errHandler.reportMatch(this);
					consume();
				}
				}
				break;
			}
			_ctx.stop = _input.LT(-1);
			setState(131);
			_errHandler.sync(this);
			_alt = getInterpreter().adaptivePredict(_input,9,_ctx);
			while ( _alt!=2 && _alt!= ATN.INVALID_ALT_NUMBER ) {
				if ( _alt==1 ) {
					if ( _parseListeners!=null ) triggerExitRuleEvent();
					_prevctx = _localctx;
					{
					setState(129);
					_errHandler.sync(this);
					switch ( getInterpreter().adaptivePredict(_input,8,_ctx) ) {
					case 1:
						{
						_localctx = new LogicAndFilterContext(new FilterContext(_parentctx, _parentState));
						pushNewRecursionContext(_localctx, _startState, RULE_filter);
						setState(123);
						if (!(precpred(_ctx, 2))) throw new FailedPredicateException(this, "precpred(_ctx, 2)");
						setState(124);
						((LogicAndFilterContext)_localctx).op = match(LOGIC_AND);
						setState(125);
						filter(3);
						}
						break;
					case 2:
						{
						_localctx = new LogicOrFilterContext(new FilterContext(_parentctx, _parentState));
						pushNewRecursionContext(_localctx, _startState, RULE_filter);
						setState(126);
						if (!(precpred(_ctx, 1))) throw new FailedPredicateException(this, "precpred(_ctx, 1)");
						setState(127);
						((LogicOrFilterContext)_localctx).op = match(LOGIC_OR);
						setState(128);
						filter(2);
						}
						break;
					}
					} 
				}
				setState(133);
				_errHandler.sync(this);
				_alt = getInterpreter().adaptivePredict(_input,9,_ctx);
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
			setState(135);
			_errHandler.sync(this);
			switch ( getInterpreter().adaptivePredict(_input,10,_ctx) ) {
			case 1:
				{
				setState(134);
				match(AUX_YI);
				}
				break;
			}
			setState(137);
			segment();
			setState(138);
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
			setState(156);
			_errHandler.sync(this);
			switch ( getInterpreter().adaptivePredict(_input,13,_ctx) ) {
			case 1:
				enterOuterAlt(_localctx, 1);
				{
				setState(140);
				segment();
				setState(142);
				_errHandler.sync(this);
				_la = _input.LA(1);
				if (_la==ORDER) {
					{
					setState(141);
					match(ORDER);
					}
				}

				setState(145);
				_errHandler.sync(this);
				_la = _input.LA(1);
				if (_la==AUX_ZAI) {
					{
					setState(144);
					match(AUX_ZAI);
					}
				}

				setState(147);
				_la = _input.LA(1);
				if ( !(_la==BEFORE || _la==AFTER) ) {
				_errHandler.recoverInline(this);
				}
				else {
					if ( _input.LA(1)==Token.EOF ) matchedEOF = true;
					_errHandler.reportMatch(this);
					consume();
				}
				setState(148);
				match(INTEGER);
				setState(149);
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
				setState(151);
				segment();
				setState(152);
				match(REVERSE);
				setState(153);
				match(INTEGER);
				setState(154);
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
			setState(158);
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
			setState(160);
			statement(0);
			setState(161);
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
			setState(180);
			_errHandler.sync(this);
			switch ( getInterpreter().adaptivePredict(_input,15,_ctx) ) {
			case 1:
				{
				_localctx = new IfExpressionContext(_localctx);
				_ctx = _localctx;
				_prevctx = _localctx;

				setState(164);
				ifSpecial();
				}
				break;
			case 2:
				{
				_localctx = new FunctionExpressionContext(_localctx);
				_ctx = _localctx;
				_prevctx = _localctx;
				setState(165);
				functionStatement();
				}
				break;
			case 3:
				{
				_localctx = new CaseExpressionContext(_localctx);
				_ctx = _localctx;
				_prevctx = _localctx;
				setState(166);
				caseStatement();
				}
				break;
			case 4:
				{
				_localctx = new ConstantExpressionContext(_localctx);
				_ctx = _localctx;
				_prevctx = _localctx;
				setState(167);
				constant();
				}
				break;
			case 5:
				{
				_localctx = new ColumnExpressionContext(_localctx);
				_ctx = _localctx;
				_prevctx = _localctx;
				setState(168);
				column();
				}
				break;
			case 6:
				{
				_localctx = new NotPredicateContext(_localctx);
				_ctx = _localctx;
				_prevctx = _localctx;
				setState(169);
				match(NOT);
				setState(170);
				statement(7);
				}
				break;
			case 7:
				{
				_localctx = new ParenthesesExpressionContext(_localctx);
				_ctx = _localctx;
				_prevctx = _localctx;
				setState(171);
				match(L_PARENTHESES);
				setState(172);
				statement(0);
				setState(173);
				match(R_PARENTHESES);
				}
				break;
			case 8:
				{
				_localctx = new SquareExpressionContext(_localctx);
				_ctx = _localctx;
				_prevctx = _localctx;
				setState(175);
				match(L_SQUARE);
				setState(177);
				_errHandler.sync(this);
				_la = _input.LA(1);
				if (((((_la - 46)) & ~0x3f) == 0 && ((1L << (_la - 46)) & ((1L << (L_PARENTHESES - 46)) | (1L << (L_SQUARE - 46)) | (1L << (PLUS - 46)) | (1L << (MINUS - 46)) | (1L << (IF - 46)) | (1L << (LIKE - 46)) | (1L << (NOT - 46)) | (1L << (CASE - 46)) | (1L << (BOOL - 46)) | (1L << (NULL - 46)) | (1L << (STRING - 46)) | (1L << (INTEGER - 46)) | (1L << (FLOAT - 46)) | (1L << (COLUMN_ID - 46)) | (1L << (IDENTITY - 46)) | (1L << (COLUMN_NAME - 46)))) != 0)) {
					{
					setState(176);
					statements();
					}
				}

				setState(179);
				match(R_SQUARE);
				}
				break;
			}
			_ctx.stop = _input.LT(-1);
			setState(226);
			_errHandler.sync(this);
			_alt = getInterpreter().adaptivePredict(_input,19,_ctx);
			while ( _alt!=2 && _alt!= ATN.INVALID_ALT_NUMBER ) {
				if ( _alt==1 ) {
					if ( _parseListeners!=null ) triggerExitRuleEvent();
					_prevctx = _localctx;
					{
					setState(224);
					_errHandler.sync(this);
					switch ( getInterpreter().adaptivePredict(_input,18,_ctx) ) {
					case 1:
						{
						_localctx = new MathExpressionContext(new StatementContext(_parentctx, _parentState));
						pushNewRecursionContext(_localctx, _startState, RULE_statement);
						setState(182);
						if (!(precpred(_ctx, 14))) throw new FailedPredicateException(this, "precpred(_ctx, 14)");
						setState(183);
						((MathExpressionContext)_localctx).op = match(POWER);
						setState(184);
						statement(14);
						}
						break;
					case 2:
						{
						_localctx = new MathExpressionContext(new StatementContext(_parentctx, _parentState));
						pushNewRecursionContext(_localctx, _startState, RULE_statement);
						setState(185);
						if (!(precpred(_ctx, 13))) throw new FailedPredicateException(this, "precpred(_ctx, 13)");
						setState(186);
						((MathExpressionContext)_localctx).op = match(MOD);
						setState(187);
						statement(14);
						}
						break;
					case 3:
						{
						_localctx = new MathExpressionContext(new StatementContext(_parentctx, _parentState));
						pushNewRecursionContext(_localctx, _startState, RULE_statement);
						setState(188);
						if (!(precpred(_ctx, 12))) throw new FailedPredicateException(this, "precpred(_ctx, 12)");
						setState(189);
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
						setState(190);
						statement(13);
						}
						break;
					case 4:
						{
						_localctx = new MathExpressionContext(new StatementContext(_parentctx, _parentState));
						pushNewRecursionContext(_localctx, _startState, RULE_statement);
						setState(191);
						if (!(precpred(_ctx, 11))) throw new FailedPredicateException(this, "precpred(_ctx, 11)");
						setState(192);
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
						setState(193);
						statement(12);
						}
						break;
					case 5:
						{
						_localctx = new ComparePredicateContext(new StatementContext(_parentctx, _parentState));
						pushNewRecursionContext(_localctx, _startState, RULE_statement);
						setState(194);
						if (!(precpred(_ctx, 10))) throw new FailedPredicateException(this, "precpred(_ctx, 10)");
						setState(195);
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
						setState(196);
						statement(11);
						}
						break;
					case 6:
						{
						_localctx = new LikePredicateContext(new StatementContext(_parentctx, _parentState));
						pushNewRecursionContext(_localctx, _startState, RULE_statement);
						setState(197);
						if (!(precpred(_ctx, 8))) throw new FailedPredicateException(this, "precpred(_ctx, 8)");
						setState(199);
						_errHandler.sync(this);
						_la = _input.LA(1);
						if (_la==NOT) {
							{
							setState(198);
							match(NOT);
							}
						}

						setState(201);
						((LikePredicateContext)_localctx).op = match(LIKE);
						setState(202);
						statement(9);
						}
						break;
					case 7:
						{
						_localctx = new LogicalPredicateContext(new StatementContext(_parentctx, _parentState));
						pushNewRecursionContext(_localctx, _startState, RULE_statement);
						setState(203);
						if (!(precpred(_ctx, 6))) throw new FailedPredicateException(this, "precpred(_ctx, 6)");
						setState(204);
						((LogicalPredicateContext)_localctx).op = match(AND);
						setState(205);
						statement(7);
						}
						break;
					case 8:
						{
						_localctx = new LogicalPredicateContext(new StatementContext(_parentctx, _parentState));
						pushNewRecursionContext(_localctx, _startState, RULE_statement);
						setState(206);
						if (!(precpred(_ctx, 5))) throw new FailedPredicateException(this, "precpred(_ctx, 5)");
						setState(207);
						((LogicalPredicateContext)_localctx).op = match(XOR);
						setState(208);
						statement(6);
						}
						break;
					case 9:
						{
						_localctx = new LogicalPredicateContext(new StatementContext(_parentctx, _parentState));
						pushNewRecursionContext(_localctx, _startState, RULE_statement);
						setState(209);
						if (!(precpred(_ctx, 4))) throw new FailedPredicateException(this, "precpred(_ctx, 4)");
						setState(210);
						((LogicalPredicateContext)_localctx).op = match(OR);
						setState(211);
						statement(5);
						}
						break;
					case 10:
						{
						_localctx = new IfnullExpressionContext(new StatementContext(_parentctx, _parentState));
						pushNewRecursionContext(_localctx, _startState, RULE_statement);
						setState(212);
						if (!(precpred(_ctx, 1))) throw new FailedPredicateException(this, "precpred(_ctx, 1)");
						setState(213);
						match(IFNULL);
						setState(214);
						statement(2);
						}
						break;
					case 11:
						{
						_localctx = new InPredicateContext(new StatementContext(_parentctx, _parentState));
						pushNewRecursionContext(_localctx, _startState, RULE_statement);
						setState(215);
						if (!(precpred(_ctx, 9))) throw new FailedPredicateException(this, "precpred(_ctx, 9)");
						setState(217);
						_errHandler.sync(this);
						_la = _input.LA(1);
						if (_la==NOT) {
							{
							setState(216);
							match(NOT);
							}
						}

						setState(219);
						((InPredicateContext)_localctx).op = match(IN);
						setState(220);
						match(L_PARENTHESES);
						setState(221);
						statements();
						setState(222);
						match(R_PARENTHESES);
						}
						break;
					}
					} 
				}
				setState(228);
				_errHandler.sync(this);
				_alt = getInterpreter().adaptivePredict(_input,19,_ctx);
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
			setState(229);
			identity();
			setState(230);
			match(L_PARENTHESES);
			setState(232);
			_errHandler.sync(this);
			_la = _input.LA(1);
			if (((((_la - 46)) & ~0x3f) == 0 && ((1L << (_la - 46)) & ((1L << (L_PARENTHESES - 46)) | (1L << (L_SQUARE - 46)) | (1L << (PLUS - 46)) | (1L << (MINUS - 46)) | (1L << (IF - 46)) | (1L << (LIKE - 46)) | (1L << (NOT - 46)) | (1L << (CASE - 46)) | (1L << (BOOL - 46)) | (1L << (NULL - 46)) | (1L << (STRING - 46)) | (1L << (INTEGER - 46)) | (1L << (FLOAT - 46)) | (1L << (COLUMN_ID - 46)) | (1L << (IDENTITY - 46)) | (1L << (COLUMN_NAME - 46)))) != 0)) {
				{
				setState(231);
				functionParams();
				}
			}

			setState(234);
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
			setState(236);
			functionParam();
			setState(241);
			_errHandler.sync(this);
			_la = _input.LA(1);
			while (_la==COMMA) {
				{
				{
				setState(237);
				match(COMMA);
				setState(238);
				functionParam();
				}
				}
				setState(243);
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
			setState(249);
			_errHandler.sync(this);
			switch ( getInterpreter().adaptivePredict(_input,22,_ctx) ) {
			case 1:
				enterOuterAlt(_localctx, 1);
				{
				setState(244);
				statement(0);
				}
				break;
			case 2:
				enterOuterAlt(_localctx, 2);
				{
				setState(245);
				identity();
				setState(246);
				match(EQUAL);
				setState(247);
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
			setState(251);
			statement(0);
			setState(256);
			_errHandler.sync(this);
			_la = _input.LA(1);
			while (_la==COMMA) {
				{
				{
				setState(252);
				match(COMMA);
				setState(253);
				statement(0);
				}
				}
				setState(258);
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
			setState(259);
			match(CASE);
			setState(279);
			_errHandler.sync(this);
			switch (_input.LA(1)) {
			case WHEN:
				{
				setState(265); 
				_errHandler.sync(this);
				_la = _input.LA(1);
				do {
					{
					{
					setState(260);
					match(WHEN);
					setState(261);
					predictStatement(0);
					setState(262);
					match(THEN);
					setState(263);
					((CaseStatementContext)_localctx).thenStmt = statement(0);
					}
					}
					setState(267); 
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
				setState(269);
				((CaseStatementContext)_localctx).caseStmt = statement(0);
				setState(275); 
				_errHandler.sync(this);
				_la = _input.LA(1);
				do {
					{
					{
					setState(270);
					match(WHEN);
					setState(271);
					constant();
					setState(272);
					match(THEN);
					setState(273);
					((CaseStatementContext)_localctx).thenStmt = statement(0);
					}
					}
					setState(277); 
					_errHandler.sync(this);
					_la = _input.LA(1);
				} while ( _la==WHEN );
				}
				break;
			default:
				throw new NoViableAltException(this);
			}
			setState(283);
			_errHandler.sync(this);
			_la = _input.LA(1);
			if (_la==ELSE) {
				{
				setState(281);
				match(ELSE);
				setState(282);
				((CaseStatementContext)_localctx).elseStmt = statement(0);
				}
			}

			setState(285);
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
			setState(289);
			_errHandler.sync(this);
			switch ( getInterpreter().adaptivePredict(_input,28,_ctx) ) {
			case 1:
				enterOuterAlt(_localctx, 1);
				{
				setState(287);
				ifFunction();
				}
				break;
			case 2:
				enterOuterAlt(_localctx, 2);
				{
				setState(288);
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
			setState(291);
			match(IF);
			setState(292);
			match(L_PARENTHESES);
			setState(293);
			((IfFunctionContext)_localctx).predict = predictStatement(0);
			setState(294);
			match(COMMA);
			setState(295);
			((IfFunctionContext)_localctx).ifTrue = statement(0);
			setState(296);
			match(COMMA);
			setState(297);
			((IfFunctionContext)_localctx).ifFalse = statement(0);
			setState(298);
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
			setState(300);
			match(IF);
			setState(301);
			match(L_PARENTHESES);
			setState(302);
			((IfStatementContext)_localctx).predict = predictStatement(0);
			setState(303);
			match(R_PARENTHESES);
			setState(304);
			match(L_BRACE);
			setState(305);
			statement(0);
			setState(306);
			match(R_BRACE);
			setState(310);
			_errHandler.sync(this);
			_alt = getInterpreter().adaptivePredict(_input,29,_ctx);
			while ( _alt!=2 && _alt!= ATN.INVALID_ALT_NUMBER ) {
				if ( _alt==1 ) {
					{
					{
					setState(307);
					elseIfStatement();
					}
					} 
				}
				setState(312);
				_errHandler.sync(this);
				_alt = getInterpreter().adaptivePredict(_input,29,_ctx);
			}
			setState(314);
			_errHandler.sync(this);
			switch ( getInterpreter().adaptivePredict(_input,30,_ctx) ) {
			case 1:
				{
				setState(313);
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
			setState(316);
			match(ELSE);
			setState(317);
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
			setState(319);
			match(ELSE);
			setState(320);
			match(L_BRACE);
			setState(321);
			statement(0);
			setState(322);
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
			setState(330);
			_errHandler.sync(this);
			switch ( getInterpreter().adaptivePredict(_input,31,_ctx) ) {
			case 1:
				{
				setState(325);
				statement(0);
				}
				break;
			case 2:
				{
				setState(326);
				match(L_PARENTHESES);
				setState(327);
				predictStatement(0);
				setState(328);
				match(R_PARENTHESES);
				}
				break;
			}
			_ctx.stop = _input.LT(-1);
			setState(343);
			_errHandler.sync(this);
			_alt = getInterpreter().adaptivePredict(_input,33,_ctx);
			while ( _alt!=2 && _alt!= ATN.INVALID_ALT_NUMBER ) {
				if ( _alt==1 ) {
					if ( _parseListeners!=null ) triggerExitRuleEvent();
					_prevctx = _localctx;
					{
					setState(341);
					_errHandler.sync(this);
					switch ( getInterpreter().adaptivePredict(_input,32,_ctx) ) {
					case 1:
						{
						_localctx = new PredictStatementContext(_parentctx, _parentState);
						pushNewRecursionContext(_localctx, _startState, RULE_predictStatement);
						setState(332);
						if (!(precpred(_ctx, 3))) throw new FailedPredicateException(this, "precpred(_ctx, 3)");
						setState(333);
						((PredictStatementContext)_localctx).op = match(AND);
						setState(334);
						predictStatement(4);
						}
						break;
					case 2:
						{
						_localctx = new PredictStatementContext(_parentctx, _parentState);
						pushNewRecursionContext(_localctx, _startState, RULE_predictStatement);
						setState(335);
						if (!(precpred(_ctx, 2))) throw new FailedPredicateException(this, "precpred(_ctx, 2)");
						setState(336);
						((PredictStatementContext)_localctx).op = match(XOR);
						setState(337);
						predictStatement(3);
						}
						break;
					case 3:
						{
						_localctx = new PredictStatementContext(_parentctx, _parentState);
						pushNewRecursionContext(_localctx, _startState, RULE_predictStatement);
						setState(338);
						if (!(precpred(_ctx, 1))) throw new FailedPredicateException(this, "precpred(_ctx, 1)");
						setState(339);
						((PredictStatementContext)_localctx).op = match(OR);
						setState(340);
						predictStatement(2);
						}
						break;
					}
					} 
				}
				setState(345);
				_errHandler.sync(this);
				_alt = getInterpreter().adaptivePredict(_input,33,_ctx);
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
			setState(353);
			_errHandler.sync(this);
			switch (_input.LA(1)) {
			case STRING:
				enterOuterAlt(_localctx, 1);
				{
				setState(346);
				match(STRING);
				}
				break;
			case PLUS:
			case MINUS:
			case INTEGER:
			case FLOAT:
				enterOuterAlt(_localctx, 2);
				{
				setState(348);
				_errHandler.sync(this);
				_la = _input.LA(1);
				if (_la==PLUS || _la==MINUS) {
					{
					setState(347);
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

				setState(350);
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
				setState(351);
				match(BOOL);
				}
				break;
			case NULL:
				enterOuterAlt(_localctx, 4);
				{
				setState(352);
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
			setState(364);
			_errHandler.sync(this);
			switch (_input.LA(1)) {
			case COLUMN_ID:
				_localctx = new ColumnIdContext(_localctx);
				enterOuterAlt(_localctx, 1);
				{
				setState(355);
				match(COLUMN_ID);
				}
				break;
			case COLUMN_NAME:
				_localctx = new ColumnNameContext(_localctx);
				enterOuterAlt(_localctx, 2);
				{
				setState(356);
				match(COLUMN_NAME);
				setState(358);
				_errHandler.sync(this);
				switch ( getInterpreter().adaptivePredict(_input,36,_ctx) ) {
				case 1:
					{
					setState(357);
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
				setState(360);
				identity();
				setState(362);
				_errHandler.sync(this);
				switch ( getInterpreter().adaptivePredict(_input,37,_ctx) ) {
				case 1:
					{
					setState(361);
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
			setState(366);
			_la = _input.LA(1);
			if ( !(((((_la - 61)) & ~0x3f) == 0 && ((1L << (_la - 61)) & ((1L << (IF - 61)) | (1L << (LIKE - 61)) | (1L << (NOT - 61)) | (1L << (IDENTITY - 61)))) != 0)) ) {
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
		case 5:
			return filter_sempred((FilterContext)_localctx, predIndex);
		case 10:
			return statement_sempred((StatementContext)_localctx, predIndex);
		case 21:
			return predictStatement_sempred((PredictStatementContext)_localctx, predIndex);
		}
		return true;
	}
	private boolean filter_sempred(FilterContext _localctx, int predIndex) {
		switch (predIndex) {
		case 0:
			return precpred(_ctx, 2);
		case 1:
			return precpred(_ctx, 1);
		}
		return true;
	}
	private boolean statement_sempred(StatementContext _localctx, int predIndex) {
		switch (predIndex) {
		case 2:
			return precpred(_ctx, 14);
		case 3:
			return precpred(_ctx, 13);
		case 4:
			return precpred(_ctx, 12);
		case 5:
			return precpred(_ctx, 11);
		case 6:
			return precpred(_ctx, 10);
		case 7:
			return precpred(_ctx, 8);
		case 8:
			return precpred(_ctx, 6);
		case 9:
			return precpred(_ctx, 5);
		case 10:
			return precpred(_ctx, 4);
		case 11:
			return precpred(_ctx, 1);
		case 12:
			return precpred(_ctx, 9);
		}
		return true;
	}
	private boolean predictStatement_sempred(PredictStatementContext _localctx, int predIndex) {
		switch (predIndex) {
		case 13:
			return precpred(_ctx, 3);
		case 14:
			return precpred(_ctx, 2);
		case 15:
			return precpred(_ctx, 1);
		}
		return true;
	}

	public static final String _serializedATN =
		"\3\u608b\ua72a\u8133\ub9ed\u417c\u3be7\u7786\u5964\3U\u0173\4\2\t\2\4"+
		"\3\t\3\4\4\t\4\4\5\t\5\4\6\t\6\4\7\t\7\4\b\t\b\4\t\t\t\4\n\t\n\4\13\t"+
		"\13\4\f\t\f\4\r\t\r\4\16\t\16\4\17\t\17\4\20\t\20\4\21\t\21\4\22\t\22"+
		"\4\23\t\23\4\24\t\24\4\25\t\25\4\26\t\26\4\27\t\27\4\30\t\30\4\31\t\31"+
		"\4\32\t\32\3\2\3\2\5\2\67\n\2\6\29\n\2\r\2\16\2:\3\2\3\2\3\3\3\3\3\3\3"+
		"\3\3\3\3\3\5\3E\n\3\3\4\3\4\6\4I\n\4\r\4\16\4J\3\4\5\4N\n\4\3\5\3\5\3"+
		"\5\3\5\3\5\3\5\3\5\5\5W\n\5\3\6\3\6\3\6\3\7\3\7\3\7\3\7\3\7\3\7\3\7\3"+
		"\7\3\7\3\7\3\7\3\7\3\7\3\7\3\7\3\7\3\7\3\7\3\7\3\7\3\7\3\7\3\7\3\7\3\7"+
		"\3\7\3\7\3\7\3\7\3\7\3\7\3\7\5\7|\n\7\3\7\3\7\3\7\3\7\3\7\3\7\7\7\u0084"+
		"\n\7\f\7\16\7\u0087\13\7\3\b\5\b\u008a\n\b\3\b\3\b\3\b\3\t\3\t\5\t\u0091"+
		"\n\t\3\t\5\t\u0094\n\t\3\t\3\t\3\t\3\t\3\t\3\t\3\t\3\t\3\t\5\t\u009f\n"+
		"\t\3\n\3\n\3\13\3\13\3\13\3\f\3\f\3\f\3\f\3\f\3\f\3\f\3\f\3\f\3\f\3\f"+
		"\3\f\3\f\3\f\5\f\u00b4\n\f\3\f\5\f\u00b7\n\f\3\f\3\f\3\f\3\f\3\f\3\f\3"+
		"\f\3\f\3\f\3\f\3\f\3\f\3\f\3\f\3\f\3\f\3\f\5\f\u00ca\n\f\3\f\3\f\3\f\3"+
		"\f\3\f\3\f\3\f\3\f\3\f\3\f\3\f\3\f\3\f\3\f\3\f\3\f\5\f\u00dc\n\f\3\f\3"+
		"\f\3\f\3\f\3\f\7\f\u00e3\n\f\f\f\16\f\u00e6\13\f\3\r\3\r\3\r\5\r\u00eb"+
		"\n\r\3\r\3\r\3\16\3\16\3\16\7\16\u00f2\n\16\f\16\16\16\u00f5\13\16\3\17"+
		"\3\17\3\17\3\17\3\17\5\17\u00fc\n\17\3\20\3\20\3\20\7\20\u0101\n\20\f"+
		"\20\16\20\u0104\13\20\3\21\3\21\3\21\3\21\3\21\3\21\6\21\u010c\n\21\r"+
		"\21\16\21\u010d\3\21\3\21\3\21\3\21\3\21\3\21\6\21\u0116\n\21\r\21\16"+
		"\21\u0117\5\21\u011a\n\21\3\21\3\21\5\21\u011e\n\21\3\21\3\21\3\22\3\22"+
		"\5\22\u0124\n\22\3\23\3\23\3\23\3\23\3\23\3\23\3\23\3\23\3\23\3\24\3\24"+
		"\3\24\3\24\3\24\3\24\3\24\3\24\7\24\u0137\n\24\f\24\16\24\u013a\13\24"+
		"\3\24\5\24\u013d\n\24\3\25\3\25\3\25\3\26\3\26\3\26\3\26\3\26\3\27\3\27"+
		"\3\27\3\27\3\27\3\27\5\27\u014d\n\27\3\27\3\27\3\27\3\27\3\27\3\27\3\27"+
		"\3\27\3\27\7\27\u0158\n\27\f\27\16\27\u015b\13\27\3\30\3\30\5\30\u015f"+
		"\n\30\3\30\3\30\3\30\5\30\u0164\n\30\3\31\3\31\3\31\5\31\u0169\n\31\3"+
		"\31\3\31\5\31\u016d\n\31\5\31\u016f\n\31\3\32\3\32\3\32\2\5\f\26,\33\2"+
		"\4\6\b\n\f\16\20\22\24\26\30\32\34\36 \"$&(*,.\60\62\2\23\4\2\5\b\13\f"+
		"\3\2\r\21\3\2\t\n\4\2\22\26\33\34\4\2\3\3))\4\2\27\27\31\31\4\2\30\30"+
		"\32\32\3\2\35!\3\2\'(\3\2#$\3\2,-\6\2\5\21\33\33\"\")-\3\289\3\2:;\4\2"+
		"\22\26\33\33\3\2MN\5\2?@BBPP\2\u019d\28\3\2\2\2\4D\3\2\2\2\6H\3\2\2\2"+
		"\bV\3\2\2\2\nX\3\2\2\2\f{\3\2\2\2\16\u0089\3\2\2\2\20\u009e\3\2\2\2\22"+
		"\u00a0\3\2\2\2\24\u00a2\3\2\2\2\26\u00b6\3\2\2\2\30\u00e7\3\2\2\2\32\u00ee"+
		"\3\2\2\2\34\u00fb\3\2\2\2\36\u00fd\3\2\2\2 \u0105\3\2\2\2\"\u0123\3\2"+
		"\2\2$\u0125\3\2\2\2&\u012e\3\2\2\2(\u013e\3\2\2\2*\u0141\3\2\2\2,\u014c"+
		"\3\2\2\2.\u0163\3\2\2\2\60\u016e\3\2\2\2\62\u0170\3\2\2\2\64\66\5\4\3"+
		"\2\65\67\7\4\2\2\66\65\3\2\2\2\66\67\3\2\2\2\679\3\2\2\28\64\3\2\2\29"+
		":\3\2\2\2:8\3\2\2\2:;\3\2\2\2;<\3\2\2\2<=\7\2\2\3=\3\3\2\2\2>E\5\16\b"+
		"\2?E\5\b\5\2@E\5\f\7\2AE\5\n\6\2BE\5\20\t\2CE\5\6\4\2D>\3\2\2\2D?\3\2"+
		"\2\2D@\3\2\2\2DA\3\2\2\2DB\3\2\2\2DC\3\2\2\2E\5\3\2\2\2FI\5\26\f\2GI\5"+
		"\22\n\2HF\3\2\2\2HG\3\2\2\2IJ\3\2\2\2JH\3\2\2\2JK\3\2\2\2KM\3\2\2\2LN"+
		"\7R\2\2ML\3\2\2\2MN\3\2\2\2N\7\3\2\2\2OP\5\6\4\2PQ\t\2\2\2QW\3\2\2\2R"+
		"S\7\t\2\2SW\5\6\4\2TU\7\n\2\2UW\5\6\4\2VO\3\2\2\2VR\3\2\2\2VT\3\2\2\2"+
		"W\t\3\2\2\2XY\5\6\4\2YZ\t\3\2\2Z\13\3\2\2\2[\\\b\7\1\2\\]\5\6\4\2]^\t"+
		"\4\2\2^|\3\2\2\2_`\5\6\4\2`a\t\5\2\2ab\5\6\4\2b|\3\2\2\2cd\5\6\4\2de\t"+
		"\6\2\2ef\5\6\4\2fg\7+\2\2gh\5\6\4\2hi\7&\2\2i|\3\2\2\2jk\5\6\4\2kl\t\7"+
		"\2\2lm\5\6\4\2m|\3\2\2\2no\5\6\4\2op\7*\2\2pq\5\6\4\2qr\t\b\2\2r|\3\2"+
		"\2\2st\5\6\4\2tu\t\t\2\2u|\3\2\2\2vw\5\6\4\2wx\7)\2\2xy\5\6\4\2yz\t\n"+
		"\2\2z|\3\2\2\2{[\3\2\2\2{_\3\2\2\2{c\3\2\2\2{j\3\2\2\2{n\3\2\2\2{s\3\2"+
		"\2\2{v\3\2\2\2|\u0085\3\2\2\2}~\f\4\2\2~\177\7/\2\2\177\u0084\5\f\7\5"+
		"\u0080\u0081\f\3\2\2\u0081\u0082\7.\2\2\u0082\u0084\5\f\7\4\u0083}\3\2"+
		"\2\2\u0083\u0080\3\2\2\2\u0084\u0087\3\2\2\2\u0085\u0083\3\2\2\2\u0085"+
		"\u0086\3\2\2\2\u0086\r\3\2\2\2\u0087\u0085\3\2\2\2\u0088\u008a\7*\2\2"+
		"\u0089\u0088\3\2\2\2\u0089\u008a\3\2\2\2\u008a\u008b\3\2\2\2\u008b\u008c"+
		"\5\6\4\2\u008c\u008d\t\13\2\2\u008d\17\3\2\2\2\u008e\u0090\5\6\4\2\u008f"+
		"\u0091\7\"\2\2\u0090\u008f\3\2\2\2\u0090\u0091\3\2\2\2\u0091\u0093\3\2"+
		"\2\2\u0092\u0094\7)\2\2\u0093\u0092\3\2\2\2\u0093\u0094\3\2\2\2\u0094"+
		"\u0095\3\2\2\2\u0095\u0096\t\n\2\2\u0096\u0097\7M\2\2\u0097\u0098\t\f"+
		"\2\2\u0098\u009f\3\2\2\2\u0099\u009a\5\6\4\2\u009a\u009b\7%\2\2\u009b"+
		"\u009c\7M\2\2\u009c\u009d\t\f\2\2\u009d\u009f\3\2\2\2\u009e\u008e\3\2"+
		"\2\2\u009e\u0099\3\2\2\2\u009f\21\3\2\2\2\u00a0\u00a1\t\r\2\2\u00a1\23"+
		"\3\2\2\2\u00a2\u00a3\5\26\f\2\u00a3\u00a4\7\2\2\3\u00a4\25\3\2\2\2\u00a5"+
		"\u00a6\b\f\1\2\u00a6\u00b7\5\"\22\2\u00a7\u00b7\5\30\r\2\u00a8\u00b7\5"+
		" \21\2\u00a9\u00b7\5.\30\2\u00aa\u00b7\5\60\31\2\u00ab\u00ac\7B\2\2\u00ac"+
		"\u00b7\5\26\f\t\u00ad\u00ae\7\60\2\2\u00ae\u00af\5\26\f\2\u00af\u00b0"+
		"\7\61\2\2\u00b0\u00b7\3\2\2\2\u00b1\u00b3\7\64\2\2\u00b2\u00b4\5\36\20"+
		"\2\u00b3\u00b2\3\2\2\2\u00b3\u00b4\3\2\2\2\u00b4\u00b5\3\2\2\2\u00b5\u00b7"+
		"\7\65\2\2\u00b6\u00a5\3\2\2\2\u00b6\u00a7\3\2\2\2\u00b6\u00a8\3\2\2\2"+
		"\u00b6\u00a9\3\2\2\2\u00b6\u00aa\3\2\2\2\u00b6\u00ab\3\2\2\2\u00b6\u00ad"+
		"\3\2\2\2\u00b6\u00b1\3\2\2\2\u00b7\u00e4\3\2\2\2\u00b8\u00b9\f\20\2\2"+
		"\u00b9\u00ba\7<\2\2\u00ba\u00e3\5\26\f\20\u00bb\u00bc\f\17\2\2\u00bc\u00bd"+
		"\7=\2\2\u00bd\u00e3\5\26\f\20\u00be\u00bf\f\16\2\2\u00bf\u00c0\t\16\2"+
		"\2\u00c0\u00e3\5\26\f\17\u00c1\u00c2\f\r\2\2\u00c2\u00c3\t\17\2\2\u00c3"+
		"\u00e3\5\26\f\16\u00c4\u00c5\f\f\2\2\u00c5\u00c6\t\20\2\2\u00c6\u00e3"+
		"\5\26\f\r\u00c7\u00c9\f\n\2\2\u00c8\u00ca\7B\2\2\u00c9\u00c8\3\2\2\2\u00c9"+
		"\u00ca\3\2\2\2\u00ca\u00cb\3\2\2\2\u00cb\u00cc\7@\2\2\u00cc\u00e3\5\26"+
		"\f\13\u00cd\u00ce\f\b\2\2\u00ce\u00cf\7C\2\2\u00cf\u00e3\5\26\f\t\u00d0"+
		"\u00d1\f\7\2\2\u00d1\u00d2\7E\2\2\u00d2\u00e3\5\26\f\b\u00d3\u00d4\f\6"+
		"\2\2\u00d4\u00d5\7D\2\2\u00d5\u00e3\5\26\f\7\u00d6\u00d7\f\3\2\2\u00d7"+
		"\u00d8\7>\2\2\u00d8\u00e3\5\26\f\4\u00d9\u00db\f\13\2\2\u00da\u00dc\7"+
		"B\2\2\u00db\u00da\3\2\2\2\u00db\u00dc\3\2\2\2\u00dc\u00dd\3\2\2\2\u00dd"+
		"\u00de\7A\2\2\u00de\u00df\7\60\2\2\u00df\u00e0\5\36\20\2\u00e0\u00e1\7"+
		"\61\2\2\u00e1\u00e3\3\2\2\2\u00e2\u00b8\3\2\2\2\u00e2\u00bb\3\2\2\2\u00e2"+
		"\u00be\3\2\2\2\u00e2\u00c1\3\2\2\2\u00e2\u00c4\3\2\2\2\u00e2\u00c7\3\2"+
		"\2\2\u00e2\u00cd\3\2\2\2\u00e2\u00d0\3\2\2\2\u00e2\u00d3\3\2\2\2\u00e2"+
		"\u00d6\3\2\2\2\u00e2\u00d9\3\2\2\2\u00e3\u00e6\3\2\2\2\u00e4\u00e2\3\2"+
		"\2\2\u00e4\u00e5\3\2\2\2\u00e5\27\3\2\2\2\u00e6\u00e4\3\2\2\2\u00e7\u00e8"+
		"\5\62\32\2\u00e8\u00ea\7\60\2\2\u00e9\u00eb\5\32\16\2\u00ea\u00e9\3\2"+
		"\2\2\u00ea\u00eb\3\2\2\2\u00eb\u00ec\3\2\2\2\u00ec\u00ed\7\61\2\2\u00ed"+
		"\31\3\2\2\2\u00ee\u00f3\5\34\17\2\u00ef\u00f0\7\66\2\2\u00f0\u00f2\5\34"+
		"\17\2\u00f1\u00ef\3\2\2\2\u00f2\u00f5\3\2\2\2\u00f3\u00f1\3\2\2\2\u00f3"+
		"\u00f4\3\2\2\2\u00f4\33\3\2\2\2\u00f5\u00f3\3\2\2\2\u00f6\u00fc\5\26\f"+
		"\2\u00f7\u00f8\5\62\32\2\u00f8\u00f9\7\33\2\2\u00f9\u00fa\5\26\f\2\u00fa"+
		"\u00fc\3\2\2\2\u00fb\u00f6\3\2\2\2\u00fb\u00f7\3\2\2\2\u00fc\35\3\2\2"+
		"\2\u00fd\u0102\5\26\f\2\u00fe\u00ff\7\66\2\2\u00ff\u0101\5\26\f\2\u0100"+
		"\u00fe\3\2\2\2\u0101\u0104\3\2\2\2\u0102\u0100\3\2\2\2\u0102\u0103\3\2"+
		"\2\2\u0103\37\3\2\2\2\u0104\u0102\3\2\2\2\u0105\u0119\7F\2\2\u0106\u0107"+
		"\7G\2\2\u0107\u0108\5,\27\2\u0108\u0109\7H\2\2\u0109\u010a\5\26\f\2\u010a"+
		"\u010c\3\2\2\2\u010b\u0106\3\2\2\2\u010c\u010d\3\2\2\2\u010d\u010b\3\2"+
		"\2\2\u010d\u010e\3\2\2\2\u010e\u011a\3\2\2\2\u010f\u0115\5\26\f\2\u0110"+
		"\u0111\7G\2\2\u0111\u0112\5.\30\2\u0112\u0113\7H\2\2\u0113\u0114\5\26"+
		"\f\2\u0114\u0116\3\2\2\2\u0115\u0110\3\2\2\2\u0116\u0117\3\2\2\2\u0117"+
		"\u0115\3\2\2\2\u0117\u0118\3\2\2\2\u0118\u011a\3\2\2\2\u0119\u010b\3\2"+
		"\2\2\u0119\u010f\3\2\2\2\u011a\u011d\3\2\2\2\u011b\u011c\7I\2\2\u011c"+
		"\u011e\5\26\f\2\u011d\u011b\3\2\2\2\u011d\u011e\3\2\2\2\u011e\u011f\3"+
		"\2\2\2\u011f\u0120\7\32\2\2\u0120!\3\2\2\2\u0121\u0124\5$\23\2\u0122\u0124"+
		"\5&\24\2\u0123\u0121\3\2\2\2\u0123\u0122\3\2\2\2\u0124#\3\2\2\2\u0125"+
		"\u0126\7?\2\2\u0126\u0127\7\60\2\2\u0127\u0128\5,\27\2\u0128\u0129\7\66"+
		"\2\2\u0129\u012a\5\26\f\2\u012a\u012b\7\66\2\2\u012b\u012c\5\26\f\2\u012c"+
		"\u012d\7\61\2\2\u012d%\3\2\2\2\u012e\u012f\7?\2\2\u012f\u0130\7\60\2\2"+
		"\u0130\u0131\5,\27\2\u0131\u0132\7\61\2\2\u0132\u0133\7\62\2\2\u0133\u0134"+
		"\5\26\f\2\u0134\u0138\7\63\2\2\u0135\u0137\5(\25\2\u0136\u0135\3\2\2\2"+
		"\u0137\u013a\3\2\2\2\u0138\u0136\3\2\2\2\u0138\u0139\3\2\2\2\u0139\u013c"+
		"\3\2\2\2\u013a\u0138\3\2\2\2\u013b\u013d\5*\26\2\u013c\u013b\3\2\2\2\u013c"+
		"\u013d\3\2\2\2\u013d\'\3\2\2\2\u013e\u013f\7I\2\2\u013f\u0140\5&\24\2"+
		"\u0140)\3\2\2\2\u0141\u0142\7I\2\2\u0142\u0143\7\62\2\2\u0143\u0144\5"+
		"\26\f\2\u0144\u0145\7\63\2\2\u0145+\3\2\2\2\u0146\u0147\b\27\1\2\u0147"+
		"\u014d\5\26\f\2\u0148\u0149\7\60\2\2\u0149\u014a\5,\27\2\u014a\u014b\7"+
		"\61\2\2\u014b\u014d\3\2\2\2\u014c\u0146\3\2\2\2\u014c\u0148\3\2\2\2\u014d"+
		"\u0159\3\2\2\2\u014e\u014f\f\5\2\2\u014f\u0150\7C\2\2\u0150\u0158\5,\27"+
		"\6\u0151\u0152\f\4\2\2\u0152\u0153\7E\2\2\u0153\u0158\5,\27\5\u0154\u0155"+
		"\f\3\2\2\u0155\u0156\7D\2\2\u0156\u0158\5,\27\4\u0157\u014e\3\2\2\2\u0157"+
		"\u0151\3\2\2\2\u0157\u0154\3\2\2\2\u0158\u015b\3\2\2\2\u0159\u0157\3\2"+
		"\2\2\u0159\u015a\3\2\2\2\u015a-\3\2\2\2\u015b\u0159\3\2\2\2\u015c\u0164"+
		"\7L\2\2\u015d\u015f\t\17\2\2\u015e\u015d\3\2\2\2\u015e\u015f\3\2\2\2\u015f"+
		"\u0160\3\2\2\2\u0160\u0164\t\21\2\2\u0161\u0164\7J\2\2\u0162\u0164\7K"+
		"\2\2\u0163\u015c\3\2\2\2\u0163\u015e\3\2\2\2\u0163\u0161\3\2\2\2\u0163"+
		"\u0162\3\2\2\2\u0164/\3\2\2\2\u0165\u016f\7O\2\2\u0166\u0168\7Q\2\2\u0167"+
		"\u0169\7R\2\2\u0168\u0167\3\2\2\2\u0168\u0169\3\2\2\2\u0169\u016f\3\2"+
		"\2\2\u016a\u016c\5\62\32\2\u016b\u016d\7R\2\2\u016c\u016b\3\2\2\2\u016c"+
		"\u016d\3\2\2\2\u016d\u016f\3\2\2\2\u016e\u0165\3\2\2\2\u016e\u0166\3\2"+
		"\2\2\u016e\u016a\3\2\2\2\u016f\61\3\2\2\2\u0170\u0171\t\22\2\2\u0171\63"+
		"\3\2\2\2)\66:DHJMV{\u0083\u0085\u0089\u0090\u0093\u009e\u00b3\u00b6\u00c9"+
		"\u00db\u00e2\u00e4\u00ea\u00f3\u00fb\u0102\u010d\u0117\u0119\u011d\u0123"+
		"\u0138\u013c\u014c\u0157\u0159\u015e\u0163\u0168\u016c\u016e";
	public static final ATN _ATN =
		new ATNDeserializer().deserialize(_serializedATN.toCharArray());
	static {
		_decisionToDFA = new DFA[_ATN.getNumberOfDecisions()];
		for (int i = 0; i < _ATN.getNumberOfDecisions(); i++) {
			_decisionToDFA[i] = new DFA(_ATN.getDecisionState(i), i);
		}
	}
}