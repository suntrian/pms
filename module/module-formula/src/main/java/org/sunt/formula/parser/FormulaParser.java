// Generated from D:/projects/pms/module/module-formula/src/main/java/org/sunt/formula/parser\Formula.g4 by ANTLR 4.9.1
package org.sunt.formula.parser;

import org.antlr.v4.runtime.*;
import org.antlr.v4.runtime.atn.ATN;
import org.antlr.v4.runtime.atn.ATNDeserializer;
import org.antlr.v4.runtime.atn.ParserATNSimulator;
import org.antlr.v4.runtime.atn.PredictionContextCache;
import org.antlr.v4.runtime.dfa.DFA;
import org.antlr.v4.runtime.tree.ParseTreeVisitor;
import org.antlr.v4.runtime.tree.TerminalNode;

import java.util.List;

@SuppressWarnings({"all", "warnings", "unchecked", "unused", "cast"})
public class FormulaParser extends Parser {
	static {
		RuntimeMetaData.checkVersion("4.9.1", RuntimeMetaData.VERSION);
	}

	protected static final DFA[] _decisionToDFA;
	protected static final PredictionContextCache _sharedContextCache =
			new PredictionContextCache();
	public static final int
			L_PARENTHESES = 1, R_PARENTHESES = 2, L_BRACE = 3, R_BRACE = 4, L_SQUARE = 5, R_SQUARE = 6,
			COMMA = 7, COLON = 8, MUL = 9, DIV = 10, PLUS = 11, MINUS = 12, POWER = 13, MOD = 14,
			IFNULL = 15, GREATER = 16, GREATER_EQUAL = 17, EQUAL = 18, LESS = 19, LESS_EQUAL = 20,
			NOT_EQUAL = 21, IF = 22, LIKE = 23, IN = 24, NOT = 25, AND = 26, OR = 27, XOR = 28, CASE = 29,
			WHEN = 30, THEN = 31, ELSE = 32, END = 33, BOOL = 34, NULL = 35, STRING = 36, INTEGER = 37,
			FLOAT = 38, COLUMN_ID = 39, IDENTITY = 40, COLUMN_NAME = 41, WS = 42, LINE_COMMENT = 43,
			BLOCK_COMMENT = 44;
	public static final int
			RULE_formula = 0, RULE_statement = 1, RULE_functionStatement = 2, RULE_functionParams = 3,
			RULE_functionParam = 4, RULE_statements = 5, RULE_caseStatement = 6, RULE_ifSpecial = 7,
			RULE_ifFunction = 8, RULE_ifStatement = 9, RULE_elseIfStatement = 10,
			RULE_elseStatement = 11, RULE_predictStatement = 12, RULE_constant = 13,
			RULE_column = 14;

	private static String[] makeRuleNames() {
		return new String[]{
				"formula", "statement", "functionStatement", "functionParams", "functionParam",
				"statements", "caseStatement", "ifSpecial", "ifFunction", "ifStatement",
				"elseIfStatement", "elseStatement", "predictStatement", "constant", "column"
		};
	}

	public static final String[] ruleNames = makeRuleNames();

	private static String[] makeLiteralNames() {
		return new String[]{
				null, "'('", "')'", "'{'", "'}'", "'['", "']'", "','", "':'", "'*'",
				"'/'", "'+'", "'-'", "'^'", null, null, "'>'", "'>='", null, "'<'", "'<='"
		};
	}
	private static final String[] _LITERAL_NAMES = makeLiteralNames();
	private static String[] makeSymbolicNames() {
		return new String[]{
				null, "L_PARENTHESES", "R_PARENTHESES", "L_BRACE", "R_BRACE", "L_SQUARE",
				"R_SQUARE", "COMMA", "COLON", "MUL", "DIV", "PLUS", "MINUS", "POWER",
				"MOD", "IFNULL", "GREATER", "GREATER_EQUAL", "EQUAL", "LESS", "LESS_EQUAL",
				"NOT_EQUAL", "IF", "LIKE", "IN", "NOT", "AND", "OR", "XOR", "CASE", "WHEN",
				"THEN", "ELSE", "END", "BOOL", "NULL", "STRING", "INTEGER", "FLOAT",
				"COLUMN_ID", "IDENTITY", "COLUMN_NAME", "WS", "LINE_COMMENT", "BLOCK_COMMENT"
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
	public ATN getATN() {
		return _ATN;
	}

	public FormulaParser(TokenStream input) {
		super(input);
		_interp = new ParserATNSimulator(this, _ATN, _decisionToDFA, _sharedContextCache);
	}

	public static class FormulaContext extends ParserRuleContext {
		public StatementContext statement() {
			return getRuleContext(StatementContext.class, 0);
		}

		public TerminalNode EOF() {
			return getToken(FormulaParser.EOF, 0);
		}

		public FormulaContext(ParserRuleContext parent, int invokingState) {
			super(parent, invokingState);
		}

		@Override
		public int getRuleIndex() {
			return RULE_formula;
		}

		@Override
		public <T> T accept(ParseTreeVisitor<? extends T> visitor) {
			if (visitor instanceof FormulaVisitor) return ((FormulaVisitor<? extends T>) visitor).visitFormula(this);
			else return visitor.visitChildren(this);
		}
	}

	public final FormulaContext formula() throws RecognitionException {
		FormulaContext _localctx = new FormulaContext(_ctx, getState());
		enterRule(_localctx, 0, RULE_formula);
		try {
			enterOuterAlt(_localctx, 1);
			{
				setState(30);
				statement(0);
				setState(31);
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

	public static class StatementContext extends ParserRuleContext {
		public StatementContext(ParserRuleContext parent, int invokingState) {
			super(parent, invokingState);
		}

		@Override
		public int getRuleIndex() {
			return RULE_statement;
		}

		public StatementContext() {
		}

		public void copyFrom(StatementContext ctx) {
			super.copyFrom(ctx);
		}
	}

	public static class ConstantExpressionContext extends StatementContext {
		public ConstantContext constant() {
			return getRuleContext(ConstantContext.class, 0);
		}

		public ConstantExpressionContext(StatementContext ctx) {
			copyFrom(ctx);
		}

		@Override
		public <T> T accept(ParseTreeVisitor<? extends T> visitor) {
			if (visitor instanceof FormulaVisitor)
				return ((FormulaVisitor<? extends T>) visitor).visitConstantExpression(this);
			else return visitor.visitChildren(this);
		}
	}

	public static class InPredicateContext extends StatementContext {
		public Token op;

		public StatementContext statement() {
			return getRuleContext(StatementContext.class, 0);
		}

		public TerminalNode L_PARENTHESES() {
			return getToken(FormulaParser.L_PARENTHESES, 0);
		}

		public StatementsContext statements() {
			return getRuleContext(StatementsContext.class, 0);
		}

		public TerminalNode R_PARENTHESES() {
			return getToken(FormulaParser.R_PARENTHESES, 0);
		}

		public TerminalNode IN() {
			return getToken(FormulaParser.IN, 0);
		}

		public TerminalNode NOT() {
			return getToken(FormulaParser.NOT, 0);
		}

		public InPredicateContext(StatementContext ctx) {
			copyFrom(ctx);
		}

		@Override
		public <T> T accept(ParseTreeVisitor<? extends T> visitor) {
			if (visitor instanceof FormulaVisitor)
				return ((FormulaVisitor<? extends T>) visitor).visitInPredicate(this);
			else return visitor.visitChildren(this);
		}
	}

	public static class MathExpressionContext extends StatementContext {
		public Token op;

		public List<StatementContext> statement() {
			return getRuleContexts(StatementContext.class);
		}

		public StatementContext statement(int i) {
			return getRuleContext(StatementContext.class, i);
		}

		public TerminalNode POWER() {
			return getToken(FormulaParser.POWER, 0);
		}

		public TerminalNode MOD() {
			return getToken(FormulaParser.MOD, 0);
		}

		public TerminalNode MUL() {
			return getToken(FormulaParser.MUL, 0);
		}

		public TerminalNode DIV() {
			return getToken(FormulaParser.DIV, 0);
		}

		public TerminalNode PLUS() {
			return getToken(FormulaParser.PLUS, 0);
		}

		public TerminalNode MINUS() {
			return getToken(FormulaParser.MINUS, 0);
		}

		public MathExpressionContext(StatementContext ctx) {
			copyFrom(ctx);
		}

		@Override
		public <T> T accept(ParseTreeVisitor<? extends T> visitor) {
			if (visitor instanceof FormulaVisitor)
				return ((FormulaVisitor<? extends T>) visitor).visitMathExpression(this);
			else return visitor.visitChildren(this);
		}
	}

	public static class LikePredicateContext extends StatementContext {
		public Token op;

		public List<StatementContext> statement() {
			return getRuleContexts(StatementContext.class);
		}

		public StatementContext statement(int i) {
			return getRuleContext(StatementContext.class, i);
		}

		public TerminalNode LIKE() {
			return getToken(FormulaParser.LIKE, 0);
		}

		public TerminalNode NOT() {
			return getToken(FormulaParser.NOT, 0);
		}

		public LikePredicateContext(StatementContext ctx) {
			copyFrom(ctx);
		}

		@Override
		public <T> T accept(ParseTreeVisitor<? extends T> visitor) {
			if (visitor instanceof FormulaVisitor)
				return ((FormulaVisitor<? extends T>) visitor).visitLikePredicate(this);
			else return visitor.visitChildren(this);
		}
	}

	public static class FunctionExpressionContext extends StatementContext {
		public FunctionStatementContext functionStatement() {
			return getRuleContext(FunctionStatementContext.class, 0);
		}

		public FunctionExpressionContext(StatementContext ctx) {
			copyFrom(ctx);
		}

		@Override
		public <T> T accept(ParseTreeVisitor<? extends T> visitor) {
			if (visitor instanceof FormulaVisitor)
				return ((FormulaVisitor<? extends T>) visitor).visitFunctionExpression(this);
			else return visitor.visitChildren(this);
		}
	}

	public static class ParenthesesExpressionContext extends StatementContext {
		public TerminalNode L_PARENTHESES() {
			return getToken(FormulaParser.L_PARENTHESES, 0);
		}

		public StatementContext statement() {
			return getRuleContext(StatementContext.class, 0);
		}

		public TerminalNode R_PARENTHESES() {
			return getToken(FormulaParser.R_PARENTHESES, 0);
		}

		public ParenthesesExpressionContext(StatementContext ctx) {
			copyFrom(ctx);
		}

		@Override
		public <T> T accept(ParseTreeVisitor<? extends T> visitor) {
			if (visitor instanceof FormulaVisitor)
				return ((FormulaVisitor<? extends T>) visitor).visitParenthesesExpression(this);
			else return visitor.visitChildren(this);
		}
	}

	public static class SquareExpressionContext extends StatementContext {
		public TerminalNode L_SQUARE() {
			return getToken(FormulaParser.L_SQUARE, 0);
		}

		public TerminalNode R_SQUARE() {
			return getToken(FormulaParser.R_SQUARE, 0);
		}

		public StatementsContext statements() {
			return getRuleContext(StatementsContext.class, 0);
		}

		public SquareExpressionContext(StatementContext ctx) {
			copyFrom(ctx);
		}

		@Override
		public <T> T accept(ParseTreeVisitor<? extends T> visitor) {
			if (visitor instanceof FormulaVisitor)
				return ((FormulaVisitor<? extends T>) visitor).visitSquareExpression(this);
			else return visitor.visitChildren(this);
		}
	}

	public static class ComparePredicateContext extends StatementContext {
		public Token op;

		public List<StatementContext> statement() {
			return getRuleContexts(StatementContext.class);
		}

		public StatementContext statement(int i) {
			return getRuleContext(StatementContext.class, i);
		}

		public TerminalNode GREATER() {
			return getToken(FormulaParser.GREATER, 0);
		}

		public TerminalNode GREATER_EQUAL() {
			return getToken(FormulaParser.GREATER_EQUAL, 0);
		}

		public TerminalNode LESS() {
			return getToken(FormulaParser.LESS, 0);
		}

		public TerminalNode LESS_EQUAL() {
			return getToken(FormulaParser.LESS_EQUAL, 0);
		}

		public TerminalNode EQUAL() {
			return getToken(FormulaParser.EQUAL, 0);
		}

		public TerminalNode NOT_EQUAL() {
			return getToken(FormulaParser.NOT_EQUAL, 0);
		}

		public ComparePredicateContext(StatementContext ctx) {
			copyFrom(ctx);
		}

		@Override
		public <T> T accept(ParseTreeVisitor<? extends T> visitor) {
			if (visitor instanceof FormulaVisitor)
				return ((FormulaVisitor<? extends T>) visitor).visitComparePredicate(this);
			else return visitor.visitChildren(this);
		}
	}

	public static class IfnullExpressionContext extends StatementContext {
		public List<StatementContext> statement() {
			return getRuleContexts(StatementContext.class);
		}

		public StatementContext statement(int i) {
			return getRuleContext(StatementContext.class, i);
		}

		public TerminalNode IFNULL() {
			return getToken(FormulaParser.IFNULL, 0);
		}

		public IfnullExpressionContext(StatementContext ctx) {
			copyFrom(ctx);
		}

		@Override
		public <T> T accept(ParseTreeVisitor<? extends T> visitor) {
			if (visitor instanceof FormulaVisitor)
				return ((FormulaVisitor<? extends T>) visitor).visitIfnullExpression(this);
			else return visitor.visitChildren(this);
		}
	}

	public static class CaseExpressionContext extends StatementContext {
		public CaseStatementContext caseStatement() {
			return getRuleContext(CaseStatementContext.class, 0);
		}

		public CaseExpressionContext(StatementContext ctx) {
			copyFrom(ctx);
		}

		@Override
		public <T> T accept(ParseTreeVisitor<? extends T> visitor) {
			if (visitor instanceof FormulaVisitor)
				return ((FormulaVisitor<? extends T>) visitor).visitCaseExpression(this);
			else return visitor.visitChildren(this);
		}
	}

	public static class ColumnExpressionContext extends StatementContext {
		public ColumnContext column() {
			return getRuleContext(ColumnContext.class, 0);
		}

		public ColumnExpressionContext(StatementContext ctx) {
			copyFrom(ctx);
		}

		@Override
		public <T> T accept(ParseTreeVisitor<? extends T> visitor) {
			if (visitor instanceof FormulaVisitor)
				return ((FormulaVisitor<? extends T>) visitor).visitColumnExpression(this);
			else return visitor.visitChildren(this);
		}
	}

	public static class NotPredicateContext extends StatementContext {
		public TerminalNode NOT() {
			return getToken(FormulaParser.NOT, 0);
		}

		public StatementContext statement() {
			return getRuleContext(StatementContext.class, 0);
		}

		public NotPredicateContext(StatementContext ctx) {
			copyFrom(ctx);
		}

		@Override
		public <T> T accept(ParseTreeVisitor<? extends T> visitor) {
			if (visitor instanceof FormulaVisitor)
				return ((FormulaVisitor<? extends T>) visitor).visitNotPredicate(this);
			else return visitor.visitChildren(this);
		}
	}

	public static class LogicalPredicateContext extends StatementContext {
		public Token op;

		public List<StatementContext> statement() {
			return getRuleContexts(StatementContext.class);
		}

		public StatementContext statement(int i) {
			return getRuleContext(StatementContext.class, i);
		}

		public TerminalNode AND() {
			return getToken(FormulaParser.AND, 0);
		}

		public TerminalNode XOR() {
			return getToken(FormulaParser.XOR, 0);
		}

		public TerminalNode OR() {
			return getToken(FormulaParser.OR, 0);
		}

		public LogicalPredicateContext(StatementContext ctx) {
			copyFrom(ctx);
		}

		@Override
		public <T> T accept(ParseTreeVisitor<? extends T> visitor) {
			if (visitor instanceof FormulaVisitor)
				return ((FormulaVisitor<? extends T>) visitor).visitLogicalPredicate(this);
			else return visitor.visitChildren(this);
		}
	}

	public static class IfExpressionContext extends StatementContext {
		public IfSpecialContext ifSpecial() {
			return getRuleContext(IfSpecialContext.class, 0);
		}

		public IfExpressionContext(StatementContext ctx) {
			copyFrom(ctx);
		}

		@Override
		public <T> T accept(ParseTreeVisitor<? extends T> visitor) {
			if (visitor instanceof FormulaVisitor)
				return ((FormulaVisitor<? extends T>) visitor).visitIfExpression(this);
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
		int _startState = 2;
		enterRecursionRule(_localctx, 2, RULE_statement, _p);
		int _la;
		try {
			int _alt;
			enterOuterAlt(_localctx, 1);
			{
				setState(50);
				_errHandler.sync(this);
				switch (getInterpreter().adaptivePredict(_input, 1, _ctx)) {
					case 1: {
						_localctx = new IfExpressionContext(_localctx);
						_ctx = _localctx;
						_prevctx = _localctx;

						setState(34);
						ifSpecial();
					}
					break;
					case 2: {
						_localctx = new FunctionExpressionContext(_localctx);
						_ctx = _localctx;
						_prevctx = _localctx;
						setState(35);
						functionStatement();
					}
					break;
					case 3: {
						_localctx = new CaseExpressionContext(_localctx);
						_ctx = _localctx;
						_prevctx = _localctx;
						setState(36);
						caseStatement();
					}
					break;
					case 4: {
						_localctx = new ConstantExpressionContext(_localctx);
						_ctx = _localctx;
						_prevctx = _localctx;
						setState(37);
						constant();
					}
					break;
					case 5: {
						_localctx = new ColumnExpressionContext(_localctx);
						_ctx = _localctx;
						_prevctx = _localctx;
						setState(38);
						column();
					}
					break;
					case 6: {
						_localctx = new NotPredicateContext(_localctx);
						_ctx = _localctx;
						_prevctx = _localctx;
						setState(39);
						match(NOT);
						setState(40);
						statement(7);
					}
					break;
					case 7: {
						_localctx = new ParenthesesExpressionContext(_localctx);
						_ctx = _localctx;
						_prevctx = _localctx;
						setState(41);
						match(L_PARENTHESES);
						setState(42);
						statement(0);
						setState(43);
						match(R_PARENTHESES);
					}
					break;
					case 8: {
						_localctx = new SquareExpressionContext(_localctx);
						_ctx = _localctx;
						_prevctx = _localctx;
						setState(45);
						match(L_SQUARE);
						setState(47);
						_errHandler.sync(this);
						_la = _input.LA(1);
						if ((((_la) & ~0x3f) == 0 && ((1L << _la) & ((1L << L_PARENTHESES) | (1L << L_SQUARE) | (1L << PLUS) | (1L << MINUS) | (1L << IF) | (1L << NOT) | (1L << CASE) | (1L << BOOL) | (1L << NULL) | (1L << STRING) | (1L << INTEGER) | (1L << FLOAT) | (1L << COLUMN_ID) | (1L << IDENTITY) | (1L << COLUMN_NAME))) != 0)) {
							{
								setState(46);
								statements();
							}
						}

						setState(49);
						match(R_SQUARE);
					}
					break;
				}
				_ctx.stop = _input.LT(-1);
				setState(96);
				_errHandler.sync(this);
				_alt = getInterpreter().adaptivePredict(_input, 5, _ctx);
				while (_alt != 2 && _alt != org.antlr.v4.runtime.atn.ATN.INVALID_ALT_NUMBER) {
					if (_alt == 1) {
						if (_parseListeners != null) triggerExitRuleEvent();
						_prevctx = _localctx;
						{
							setState(94);
							_errHandler.sync(this);
							switch (getInterpreter().adaptivePredict(_input, 4, _ctx)) {
								case 1: {
									_localctx = new MathExpressionContext(new StatementContext(_parentctx, _parentState));
									pushNewRecursionContext(_localctx, _startState, RULE_statement);
									setState(52);
									if (!(precpred(_ctx, 14)))
										throw new FailedPredicateException(this, "precpred(_ctx, 14)");
									setState(53);
									((MathExpressionContext) _localctx).op = match(POWER);
									setState(54);
									statement(14);
								}
								break;
								case 2: {
									_localctx = new MathExpressionContext(new StatementContext(_parentctx, _parentState));
									pushNewRecursionContext(_localctx, _startState, RULE_statement);
									setState(55);
									if (!(precpred(_ctx, 13)))
										throw new FailedPredicateException(this, "precpred(_ctx, 13)");
									setState(56);
									((MathExpressionContext) _localctx).op = match(MOD);
									setState(57);
									statement(14);
								}
								break;
								case 3: {
									_localctx = new MathExpressionContext(new StatementContext(_parentctx, _parentState));
									pushNewRecursionContext(_localctx, _startState, RULE_statement);
									setState(58);
									if (!(precpred(_ctx, 12)))
										throw new FailedPredicateException(this, "precpred(_ctx, 12)");
									setState(59);
									((MathExpressionContext) _localctx).op = _input.LT(1);
									_la = _input.LA(1);
									if (!(_la == MUL || _la == DIV)) {
										((MathExpressionContext) _localctx).op = (Token) _errHandler.recoverInline(this);
									} else {
										if (_input.LA(1) == Token.EOF) matchedEOF = true;
										_errHandler.reportMatch(this);
										consume();
									}
									setState(60);
									statement(13);
								}
								break;
								case 4: {
									_localctx = new MathExpressionContext(new StatementContext(_parentctx, _parentState));
									pushNewRecursionContext(_localctx, _startState, RULE_statement);
									setState(61);
									if (!(precpred(_ctx, 11)))
										throw new FailedPredicateException(this, "precpred(_ctx, 11)");
									setState(62);
									((MathExpressionContext) _localctx).op = _input.LT(1);
									_la = _input.LA(1);
									if (!(_la == PLUS || _la == MINUS)) {
										((MathExpressionContext) _localctx).op = (Token) _errHandler.recoverInline(this);
									} else {
										if (_input.LA(1) == Token.EOF) matchedEOF = true;
										_errHandler.reportMatch(this);
										consume();
									}
									setState(63);
									statement(12);
								}
								break;
								case 5: {
									_localctx = new ComparePredicateContext(new StatementContext(_parentctx, _parentState));
									pushNewRecursionContext(_localctx, _startState, RULE_statement);
									setState(64);
									if (!(precpred(_ctx, 10)))
										throw new FailedPredicateException(this, "precpred(_ctx, 10)");
									setState(65);
									((ComparePredicateContext) _localctx).op = _input.LT(1);
									_la = _input.LA(1);
									if (!((((_la) & ~0x3f) == 0 && ((1L << _la) & ((1L << GREATER) | (1L << GREATER_EQUAL) | (1L << EQUAL) | (1L << LESS) | (1L << LESS_EQUAL) | (1L << NOT_EQUAL))) != 0))) {
										((ComparePredicateContext) _localctx).op = (Token) _errHandler.recoverInline(this);
									} else {
										if (_input.LA(1) == Token.EOF) matchedEOF = true;
										_errHandler.reportMatch(this);
										consume();
									}
									setState(66);
									statement(11);
								}
								break;
								case 6: {
									_localctx = new LikePredicateContext(new StatementContext(_parentctx, _parentState));
									pushNewRecursionContext(_localctx, _startState, RULE_statement);
									setState(67);
									if (!(precpred(_ctx, 8)))
										throw new FailedPredicateException(this, "precpred(_ctx, 8)");
									setState(69);
									_errHandler.sync(this);
									_la = _input.LA(1);
									if (_la == NOT) {
										{
											setState(68);
											match(NOT);
										}
									}

									setState(71);
									((LikePredicateContext) _localctx).op = match(LIKE);
									setState(72);
									statement(9);
								}
								break;
								case 7: {
									_localctx = new LogicalPredicateContext(new StatementContext(_parentctx, _parentState));
									pushNewRecursionContext(_localctx, _startState, RULE_statement);
									setState(73);
									if (!(precpred(_ctx, 6)))
										throw new FailedPredicateException(this, "precpred(_ctx, 6)");
									setState(74);
									((LogicalPredicateContext) _localctx).op = match(AND);
									setState(75);
									statement(7);
								}
								break;
								case 8: {
									_localctx = new LogicalPredicateContext(new StatementContext(_parentctx, _parentState));
									pushNewRecursionContext(_localctx, _startState, RULE_statement);
									setState(76);
									if (!(precpred(_ctx, 5)))
										throw new FailedPredicateException(this, "precpred(_ctx, 5)");
									setState(77);
									((LogicalPredicateContext) _localctx).op = match(XOR);
									setState(78);
									statement(6);
								}
								break;
								case 9: {
									_localctx = new LogicalPredicateContext(new StatementContext(_parentctx, _parentState));
									pushNewRecursionContext(_localctx, _startState, RULE_statement);
									setState(79);
									if (!(precpred(_ctx, 4)))
										throw new FailedPredicateException(this, "precpred(_ctx, 4)");
									setState(80);
									((LogicalPredicateContext) _localctx).op = match(OR);
									setState(81);
									statement(5);
								}
								break;
								case 10: {
									_localctx = new IfnullExpressionContext(new StatementContext(_parentctx, _parentState));
									pushNewRecursionContext(_localctx, _startState, RULE_statement);
									setState(82);
									if (!(precpred(_ctx, 1)))
										throw new FailedPredicateException(this, "precpred(_ctx, 1)");
									setState(83);
									match(IFNULL);
									setState(84);
									statement(2);
								}
								break;
								case 11: {
									_localctx = new InPredicateContext(new StatementContext(_parentctx, _parentState));
									pushNewRecursionContext(_localctx, _startState, RULE_statement);
									setState(85);
									if (!(precpred(_ctx, 9)))
										throw new FailedPredicateException(this, "precpred(_ctx, 9)");
									setState(87);
									_errHandler.sync(this);
									_la = _input.LA(1);
									if (_la == NOT) {
										{
											setState(86);
											match(NOT);
										}
									}

									setState(89);
									((InPredicateContext) _localctx).op = match(IN);
									setState(90);
									match(L_PARENTHESES);
									setState(91);
									statements();
									setState(92);
									match(R_PARENTHESES);
								}
								break;
							}
						}
					}
					setState(98);
					_errHandler.sync(this);
					_alt = getInterpreter().adaptivePredict(_input, 5, _ctx);
				}
			}
		} catch (RecognitionException re) {
			_localctx.exception = re;
			_errHandler.reportError(this, re);
			_errHandler.recover(this, re);
		} finally {
			unrollRecursionContexts(_parentctx);
		}
		return _localctx;
	}

	public static class FunctionStatementContext extends ParserRuleContext {
		public TerminalNode IDENTITY() {
			return getToken(FormulaParser.IDENTITY, 0);
		}

		public TerminalNode L_PARENTHESES() {
			return getToken(FormulaParser.L_PARENTHESES, 0);
		}

		public TerminalNode R_PARENTHESES() {
			return getToken(FormulaParser.R_PARENTHESES, 0);
		}

		public FunctionParamsContext functionParams() {
			return getRuleContext(FunctionParamsContext.class, 0);
		}

		public FunctionStatementContext(ParserRuleContext parent, int invokingState) {
			super(parent, invokingState);
		}

		@Override
		public int getRuleIndex() {
			return RULE_functionStatement;
		}

		@Override
		public <T> T accept(ParseTreeVisitor<? extends T> visitor) {
			if (visitor instanceof FormulaVisitor)
				return ((FormulaVisitor<? extends T>) visitor).visitFunctionStatement(this);
			else return visitor.visitChildren(this);
		}
	}

	public final FunctionStatementContext functionStatement() throws RecognitionException {
		FunctionStatementContext _localctx = new FunctionStatementContext(_ctx, getState());
		enterRule(_localctx, 4, RULE_functionStatement);
		int _la;
		try {
			enterOuterAlt(_localctx, 1);
			{
				setState(99);
				match(IDENTITY);
				setState(100);
				match(L_PARENTHESES);
				setState(102);
				_errHandler.sync(this);
				_la = _input.LA(1);
				if ((((_la) & ~0x3f) == 0 && ((1L << _la) & ((1L << L_PARENTHESES) | (1L << L_SQUARE) | (1L << PLUS) | (1L << MINUS) | (1L << IF) | (1L << NOT) | (1L << CASE) | (1L << BOOL) | (1L << NULL) | (1L << STRING) | (1L << INTEGER) | (1L << FLOAT) | (1L << COLUMN_ID) | (1L << IDENTITY) | (1L << COLUMN_NAME))) != 0)) {
					{
						setState(101);
						functionParams();
					}
				}

				setState(104);
				match(R_PARENTHESES);
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
		public List<FunctionParamContext> functionParam() {
			return getRuleContexts(FunctionParamContext.class);
		}

		public FunctionParamContext functionParam(int i) {
			return getRuleContext(FunctionParamContext.class, i);
		}

		public List<TerminalNode> COMMA() {
			return getTokens(FormulaParser.COMMA);
		}

		public TerminalNode COMMA(int i) {
			return getToken(FormulaParser.COMMA, i);
		}

		public FunctionParamsContext(ParserRuleContext parent, int invokingState) {
			super(parent, invokingState);
		}

		@Override
		public int getRuleIndex() {
			return RULE_functionParams;
		}

		@Override
		public <T> T accept(ParseTreeVisitor<? extends T> visitor) {
			if (visitor instanceof FormulaVisitor)
				return ((FormulaVisitor<? extends T>) visitor).visitFunctionParams(this);
			else return visitor.visitChildren(this);
		}
	}

	public final FunctionParamsContext functionParams() throws RecognitionException {
		FunctionParamsContext _localctx = new FunctionParamsContext(_ctx, getState());
		enterRule(_localctx, 6, RULE_functionParams);
		int _la;
		try {
			enterOuterAlt(_localctx, 1);
			{
				setState(106);
				functionParam();
				setState(111);
				_errHandler.sync(this);
				_la = _input.LA(1);
				while (_la == COMMA) {
					{
						{
							setState(107);
							match(COMMA);
							setState(108);
							functionParam();
						}
					}
					setState(113);
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

	public static class FunctionParamContext extends ParserRuleContext {
		public StatementContext statement() {
			return getRuleContext(StatementContext.class, 0);
		}

		public TerminalNode IDENTITY() {
			return getToken(FormulaParser.IDENTITY, 0);
		}

		public TerminalNode EQUAL() {
			return getToken(FormulaParser.EQUAL, 0);
		}

		public FunctionParamContext(ParserRuleContext parent, int invokingState) {
			super(parent, invokingState);
		}

		@Override
		public int getRuleIndex() {
			return RULE_functionParam;
		}

		@Override
		public <T> T accept(ParseTreeVisitor<? extends T> visitor) {
			if (visitor instanceof FormulaVisitor)
				return ((FormulaVisitor<? extends T>) visitor).visitFunctionParam(this);
			else return visitor.visitChildren(this);
		}
	}

	public final FunctionParamContext functionParam() throws RecognitionException {
		FunctionParamContext _localctx = new FunctionParamContext(_ctx, getState());
		enterRule(_localctx, 8, RULE_functionParam);
		try {
			setState(118);
			_errHandler.sync(this);
			switch (getInterpreter().adaptivePredict(_input, 8, _ctx)) {
				case 1:
					enterOuterAlt(_localctx, 1);
				{
					setState(114);
					statement(0);
				}
				break;
				case 2:
					enterOuterAlt(_localctx, 2);
				{
					setState(115);
					match(IDENTITY);
					setState(116);
					match(EQUAL);
					setState(117);
					statement(0);
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

	public static class StatementsContext extends ParserRuleContext {
		public List<StatementContext> statement() {
			return getRuleContexts(StatementContext.class);
		}

		public StatementContext statement(int i) {
			return getRuleContext(StatementContext.class, i);
		}

		public List<TerminalNode> COMMA() {
			return getTokens(FormulaParser.COMMA);
		}

		public TerminalNode COMMA(int i) {
			return getToken(FormulaParser.COMMA, i);
		}

		public StatementsContext(ParserRuleContext parent, int invokingState) {
			super(parent, invokingState);
		}

		@Override
		public int getRuleIndex() {
			return RULE_statements;
		}

		@Override
		public <T> T accept(ParseTreeVisitor<? extends T> visitor) {
			if (visitor instanceof FormulaVisitor) return ((FormulaVisitor<? extends T>) visitor).visitStatements(this);
			else return visitor.visitChildren(this);
		}
	}

	public final StatementsContext statements() throws RecognitionException {
		StatementsContext _localctx = new StatementsContext(_ctx, getState());
		enterRule(_localctx, 10, RULE_statements);
		int _la;
		try {
			enterOuterAlt(_localctx, 1);
			{
				setState(120);
				statement(0);
				setState(125);
				_errHandler.sync(this);
				_la = _input.LA(1);
				while (_la == COMMA) {
					{
						{
							setState(121);
							match(COMMA);
							setState(122);
							statement(0);
						}
					}
					setState(127);
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

	public static class CaseStatementContext extends ParserRuleContext {
		public StatementContext thenStmt;
		public StatementContext caseStmt;
		public StatementContext elseStmt;

		public TerminalNode CASE() {
			return getToken(FormulaParser.CASE, 0);
		}

		public TerminalNode END() {
			return getToken(FormulaParser.END, 0);
		}

		public List<StatementContext> statement() {
			return getRuleContexts(StatementContext.class);
		}

		public StatementContext statement(int i) {
			return getRuleContext(StatementContext.class, i);
		}

		public TerminalNode ELSE() {
			return getToken(FormulaParser.ELSE, 0);
		}

		public List<TerminalNode> WHEN() {
			return getTokens(FormulaParser.WHEN);
		}

		public TerminalNode WHEN(int i) {
			return getToken(FormulaParser.WHEN, i);
		}

		public List<PredictStatementContext> predictStatement() {
			return getRuleContexts(PredictStatementContext.class);
		}

		public PredictStatementContext predictStatement(int i) {
			return getRuleContext(PredictStatementContext.class, i);
		}

		public List<TerminalNode> THEN() {
			return getTokens(FormulaParser.THEN);
		}

		public TerminalNode THEN(int i) {
			return getToken(FormulaParser.THEN, i);
		}

		public List<ConstantContext> constant() {
			return getRuleContexts(ConstantContext.class);
		}

		public ConstantContext constant(int i) {
			return getRuleContext(ConstantContext.class, i);
		}

		public CaseStatementContext(ParserRuleContext parent, int invokingState) {
			super(parent, invokingState);
		}

		@Override
		public int getRuleIndex() {
			return RULE_caseStatement;
		}

		@Override
		public <T> T accept(ParseTreeVisitor<? extends T> visitor) {
			if (visitor instanceof FormulaVisitor)
				return ((FormulaVisitor<? extends T>) visitor).visitCaseStatement(this);
			else return visitor.visitChildren(this);
		}
	}

	public final CaseStatementContext caseStatement() throws RecognitionException {
		CaseStatementContext _localctx = new CaseStatementContext(_ctx, getState());
		enterRule(_localctx, 12, RULE_caseStatement);
		int _la;
		try {
			enterOuterAlt(_localctx, 1);
			{
				setState(128);
				match(CASE);
				setState(148);
				_errHandler.sync(this);
				switch (_input.LA(1)) {
					case WHEN: {
						setState(134);
						_errHandler.sync(this);
						_la = _input.LA(1);
						do {
							{
								{
									setState(129);
									match(WHEN);
									setState(130);
									predictStatement(0);
									setState(131);
									match(THEN);
									setState(132);
									((CaseStatementContext) _localctx).thenStmt = statement(0);
								}
							}
							setState(136);
							_errHandler.sync(this);
							_la = _input.LA(1);
						} while (_la == WHEN);
					}
					break;
					case L_PARENTHESES:
					case L_SQUARE:
					case PLUS:
					case MINUS:
					case IF:
					case NOT:
					case CASE:
					case BOOL:
					case NULL:
					case STRING:
					case INTEGER:
					case FLOAT:
					case COLUMN_ID:
					case IDENTITY:
					case COLUMN_NAME: {
						setState(138);
						((CaseStatementContext) _localctx).caseStmt = statement(0);
						setState(144);
						_errHandler.sync(this);
						_la = _input.LA(1);
						do {
							{
								{
									setState(139);
									match(WHEN);
									setState(140);
									constant();
									setState(141);
									match(THEN);
									setState(142);
									((CaseStatementContext) _localctx).thenStmt = statement(0);
								}
							}
							setState(146);
							_errHandler.sync(this);
							_la = _input.LA(1);
						} while (_la == WHEN);
					}
					break;
					default:
						throw new NoViableAltException(this);
				}
				setState(152);
				_errHandler.sync(this);
				_la = _input.LA(1);
				if (_la == ELSE) {
					{
						setState(150);
						match(ELSE);
						setState(151);
						((CaseStatementContext) _localctx).elseStmt = statement(0);
					}
				}

				setState(154);
				match(END);
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

	public static class IfSpecialContext extends ParserRuleContext {
		public IfFunctionContext ifFunction() {
			return getRuleContext(IfFunctionContext.class, 0);
		}

		public IfStatementContext ifStatement() {
			return getRuleContext(IfStatementContext.class, 0);
		}

		public IfSpecialContext(ParserRuleContext parent, int invokingState) {
			super(parent, invokingState);
		}

		@Override
		public int getRuleIndex() {
			return RULE_ifSpecial;
		}

		@Override
		public <T> T accept(ParseTreeVisitor<? extends T> visitor) {
			if (visitor instanceof FormulaVisitor) return ((FormulaVisitor<? extends T>) visitor).visitIfSpecial(this);
			else return visitor.visitChildren(this);
		}
	}

	public final IfSpecialContext ifSpecial() throws RecognitionException {
		IfSpecialContext _localctx = new IfSpecialContext(_ctx, getState());
		enterRule(_localctx, 14, RULE_ifSpecial);
		try {
			setState(158);
			_errHandler.sync(this);
			switch (getInterpreter().adaptivePredict(_input, 14, _ctx)) {
				case 1:
					enterOuterAlt(_localctx, 1);
				{
					setState(156);
					ifFunction();
				}
				break;
				case 2:
					enterOuterAlt(_localctx, 2);
				{
					setState(157);
					ifStatement();
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

	public static class IfFunctionContext extends ParserRuleContext {
		public PredictStatementContext predict;
		public StatementContext ifTrue;
		public StatementContext ifFalse;

		public TerminalNode IF() {
			return getToken(FormulaParser.IF, 0);
		}

		public TerminalNode L_PARENTHESES() {
			return getToken(FormulaParser.L_PARENTHESES, 0);
		}

		public List<TerminalNode> COMMA() {
			return getTokens(FormulaParser.COMMA);
		}

		public TerminalNode COMMA(int i) {
			return getToken(FormulaParser.COMMA, i);
		}

		public TerminalNode R_PARENTHESES() {
			return getToken(FormulaParser.R_PARENTHESES, 0);
		}

		public PredictStatementContext predictStatement() {
			return getRuleContext(PredictStatementContext.class, 0);
		}

		public List<StatementContext> statement() {
			return getRuleContexts(StatementContext.class);
		}

		public StatementContext statement(int i) {
			return getRuleContext(StatementContext.class, i);
		}

		public IfFunctionContext(ParserRuleContext parent, int invokingState) {
			super(parent, invokingState);
		}

		@Override
		public int getRuleIndex() {
			return RULE_ifFunction;
		}

		@Override
		public <T> T accept(ParseTreeVisitor<? extends T> visitor) {
			if (visitor instanceof FormulaVisitor) return ((FormulaVisitor<? extends T>) visitor).visitIfFunction(this);
			else return visitor.visitChildren(this);
		}
	}

	public final IfFunctionContext ifFunction() throws RecognitionException {
		IfFunctionContext _localctx = new IfFunctionContext(_ctx, getState());
		enterRule(_localctx, 16, RULE_ifFunction);
		try {
			enterOuterAlt(_localctx, 1);
			{
				setState(160);
				match(IF);
				setState(161);
				match(L_PARENTHESES);
				setState(162);
				((IfFunctionContext) _localctx).predict = predictStatement(0);
				setState(163);
				match(COMMA);
				setState(164);
				((IfFunctionContext) _localctx).ifTrue = statement(0);
				setState(165);
				match(COMMA);
				setState(166);
				((IfFunctionContext) _localctx).ifFalse = statement(0);
				setState(167);
				match(R_PARENTHESES);
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

	public static class IfStatementContext extends ParserRuleContext {
		public PredictStatementContext predict;

		public TerminalNode IF() {
			return getToken(FormulaParser.IF, 0);
		}

		public TerminalNode L_PARENTHESES() {
			return getToken(FormulaParser.L_PARENTHESES, 0);
		}

		public TerminalNode R_PARENTHESES() {
			return getToken(FormulaParser.R_PARENTHESES, 0);
		}

		public TerminalNode L_BRACE() {
			return getToken(FormulaParser.L_BRACE, 0);
		}

		public StatementContext statement() {
			return getRuleContext(StatementContext.class, 0);
		}

		public TerminalNode R_BRACE() {
			return getToken(FormulaParser.R_BRACE, 0);
		}

		public PredictStatementContext predictStatement() {
			return getRuleContext(PredictStatementContext.class, 0);
		}

		public List<ElseIfStatementContext> elseIfStatement() {
			return getRuleContexts(ElseIfStatementContext.class);
		}

		public ElseIfStatementContext elseIfStatement(int i) {
			return getRuleContext(ElseIfStatementContext.class, i);
		}

		public ElseStatementContext elseStatement() {
			return getRuleContext(ElseStatementContext.class, 0);
		}

		public IfStatementContext(ParserRuleContext parent, int invokingState) {
			super(parent, invokingState);
		}

		@Override
		public int getRuleIndex() {
			return RULE_ifStatement;
		}

		@Override
		public <T> T accept(ParseTreeVisitor<? extends T> visitor) {
			if (visitor instanceof FormulaVisitor)
				return ((FormulaVisitor<? extends T>) visitor).visitIfStatement(this);
			else return visitor.visitChildren(this);
		}
	}

	public final IfStatementContext ifStatement() throws RecognitionException {
		IfStatementContext _localctx = new IfStatementContext(_ctx, getState());
		enterRule(_localctx, 18, RULE_ifStatement);
		try {
			int _alt;
			enterOuterAlt(_localctx, 1);
			{
				setState(169);
				match(IF);
				setState(170);
				match(L_PARENTHESES);
				setState(171);
				((IfStatementContext) _localctx).predict = predictStatement(0);
				setState(172);
				match(R_PARENTHESES);
				setState(173);
				match(L_BRACE);
				setState(174);
				statement(0);
				setState(175);
				match(R_BRACE);
				setState(179);
				_errHandler.sync(this);
				_alt = getInterpreter().adaptivePredict(_input, 15, _ctx);
				while (_alt != 2 && _alt != org.antlr.v4.runtime.atn.ATN.INVALID_ALT_NUMBER) {
					if (_alt == 1) {
						{
							{
								setState(176);
								elseIfStatement();
							}
						}
					}
					setState(181);
					_errHandler.sync(this);
					_alt = getInterpreter().adaptivePredict(_input, 15, _ctx);
				}
				setState(183);
				_errHandler.sync(this);
				switch (getInterpreter().adaptivePredict(_input, 16, _ctx)) {
					case 1: {
						setState(182);
						elseStatement();
					}
					break;
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

	public static class ElseIfStatementContext extends ParserRuleContext {
		public TerminalNode ELSE() {
			return getToken(FormulaParser.ELSE, 0);
		}

		public IfStatementContext ifStatement() {
			return getRuleContext(IfStatementContext.class, 0);
		}

		public ElseIfStatementContext(ParserRuleContext parent, int invokingState) {
			super(parent, invokingState);
		}

		@Override
		public int getRuleIndex() {
			return RULE_elseIfStatement;
		}

		@Override
		public <T> T accept(ParseTreeVisitor<? extends T> visitor) {
			if (visitor instanceof FormulaVisitor)
				return ((FormulaVisitor<? extends T>) visitor).visitElseIfStatement(this);
			else return visitor.visitChildren(this);
		}
	}

	public final ElseIfStatementContext elseIfStatement() throws RecognitionException {
		ElseIfStatementContext _localctx = new ElseIfStatementContext(_ctx, getState());
		enterRule(_localctx, 20, RULE_elseIfStatement);
		try {
			enterOuterAlt(_localctx, 1);
			{
				setState(185);
				match(ELSE);
				setState(186);
				ifStatement();
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

	public static class ElseStatementContext extends ParserRuleContext {
		public TerminalNode ELSE() {
			return getToken(FormulaParser.ELSE, 0);
		}

		public TerminalNode L_BRACE() {
			return getToken(FormulaParser.L_BRACE, 0);
		}

		public StatementContext statement() {
			return getRuleContext(StatementContext.class, 0);
		}

		public TerminalNode R_BRACE() {
			return getToken(FormulaParser.R_BRACE, 0);
		}

		public ElseStatementContext(ParserRuleContext parent, int invokingState) {
			super(parent, invokingState);
		}

		@Override
		public int getRuleIndex() {
			return RULE_elseStatement;
		}

		@Override
		public <T> T accept(ParseTreeVisitor<? extends T> visitor) {
			if (visitor instanceof FormulaVisitor)
				return ((FormulaVisitor<? extends T>) visitor).visitElseStatement(this);
			else return visitor.visitChildren(this);
		}
	}

	public final ElseStatementContext elseStatement() throws RecognitionException {
		ElseStatementContext _localctx = new ElseStatementContext(_ctx, getState());
		enterRule(_localctx, 22, RULE_elseStatement);
		try {
			enterOuterAlt(_localctx, 1);
			{
				setState(188);
				match(ELSE);
				setState(189);
				match(L_BRACE);
				setState(190);
				statement(0);
				setState(191);
				match(R_BRACE);
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

	public static class PredictStatementContext extends ParserRuleContext {
		public Token op;

		public StatementContext statement() {
			return getRuleContext(StatementContext.class, 0);
		}

		public TerminalNode L_PARENTHESES() {
			return getToken(FormulaParser.L_PARENTHESES, 0);
		}

		public List<PredictStatementContext> predictStatement() {
			return getRuleContexts(PredictStatementContext.class);
		}

		public PredictStatementContext predictStatement(int i) {
			return getRuleContext(PredictStatementContext.class, i);
		}

		public TerminalNode R_PARENTHESES() {
			return getToken(FormulaParser.R_PARENTHESES, 0);
		}

		public TerminalNode AND() {
			return getToken(FormulaParser.AND, 0);
		}

		public TerminalNode XOR() {
			return getToken(FormulaParser.XOR, 0);
		}

		public TerminalNode OR() {
			return getToken(FormulaParser.OR, 0);
		}

		public PredictStatementContext(ParserRuleContext parent, int invokingState) {
			super(parent, invokingState);
		}

		@Override
		public int getRuleIndex() {
			return RULE_predictStatement;
		}

		@Override
		public <T> T accept(ParseTreeVisitor<? extends T> visitor) {
			if (visitor instanceof FormulaVisitor)
				return ((FormulaVisitor<? extends T>) visitor).visitPredictStatement(this);
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
		int _startState = 24;
		enterRecursionRule(_localctx, 24, RULE_predictStatement, _p);
		try {
			int _alt;
			enterOuterAlt(_localctx, 1);
			{
				setState(199);
				_errHandler.sync(this);
				switch (getInterpreter().adaptivePredict(_input, 17, _ctx)) {
					case 1: {
						setState(194);
						statement(0);
					}
					break;
					case 2: {
						setState(195);
						match(L_PARENTHESES);
						setState(196);
						predictStatement(0);
						setState(197);
						match(R_PARENTHESES);
					}
					break;
				}
				_ctx.stop = _input.LT(-1);
				setState(212);
				_errHandler.sync(this);
				_alt = getInterpreter().adaptivePredict(_input, 19, _ctx);
				while (_alt != 2 && _alt != org.antlr.v4.runtime.atn.ATN.INVALID_ALT_NUMBER) {
					if (_alt == 1) {
						if (_parseListeners != null) triggerExitRuleEvent();
						_prevctx = _localctx;
						{
							setState(210);
							_errHandler.sync(this);
							switch (getInterpreter().adaptivePredict(_input, 18, _ctx)) {
								case 1: {
									_localctx = new PredictStatementContext(_parentctx, _parentState);
									pushNewRecursionContext(_localctx, _startState, RULE_predictStatement);
									setState(201);
									if (!(precpred(_ctx, 3)))
										throw new FailedPredicateException(this, "precpred(_ctx, 3)");
									setState(202);
									((PredictStatementContext) _localctx).op = match(AND);
									setState(203);
									predictStatement(4);
								}
								break;
								case 2: {
									_localctx = new PredictStatementContext(_parentctx, _parentState);
									pushNewRecursionContext(_localctx, _startState, RULE_predictStatement);
									setState(204);
									if (!(precpred(_ctx, 2)))
										throw new FailedPredicateException(this, "precpred(_ctx, 2)");
									setState(205);
									((PredictStatementContext) _localctx).op = match(XOR);
									setState(206);
									predictStatement(3);
								}
								break;
								case 3: {
									_localctx = new PredictStatementContext(_parentctx, _parentState);
									pushNewRecursionContext(_localctx, _startState, RULE_predictStatement);
									setState(207);
									if (!(precpred(_ctx, 1)))
										throw new FailedPredicateException(this, "precpred(_ctx, 1)");
									setState(208);
									((PredictStatementContext) _localctx).op = match(OR);
									setState(209);
									predictStatement(2);
								}
								break;
							}
						}
					}
					setState(214);
					_errHandler.sync(this);
					_alt = getInterpreter().adaptivePredict(_input, 19, _ctx);
				}
			}
		} catch (RecognitionException re) {
			_localctx.exception = re;
			_errHandler.reportError(this, re);
			_errHandler.recover(this, re);
		} finally {
			unrollRecursionContexts(_parentctx);
		}
		return _localctx;
	}

	public static class ConstantContext extends ParserRuleContext {
		public TerminalNode STRING() {
			return getToken(FormulaParser.STRING, 0);
		}

		public TerminalNode INTEGER() {
			return getToken(FormulaParser.INTEGER, 0);
		}

		public TerminalNode FLOAT() {
			return getToken(FormulaParser.FLOAT, 0);
		}

		public TerminalNode PLUS() {
			return getToken(FormulaParser.PLUS, 0);
		}

		public TerminalNode MINUS() {
			return getToken(FormulaParser.MINUS, 0);
		}

		public TerminalNode BOOL() {
			return getToken(FormulaParser.BOOL, 0);
		}

		public TerminalNode NULL() {
			return getToken(FormulaParser.NULL, 0);
		}

		public ConstantContext(ParserRuleContext parent, int invokingState) {
			super(parent, invokingState);
		}

		@Override
		public int getRuleIndex() {
			return RULE_constant;
		}

		@Override
		public <T> T accept(ParseTreeVisitor<? extends T> visitor) {
			if (visitor instanceof FormulaVisitor) return ((FormulaVisitor<? extends T>) visitor).visitConstant(this);
			else return visitor.visitChildren(this);
		}
	}

	public final ConstantContext constant() throws RecognitionException {
		ConstantContext _localctx = new ConstantContext(_ctx, getState());
		enterRule(_localctx, 26, RULE_constant);
		int _la;
		try {
			setState(222);
			_errHandler.sync(this);
			switch (_input.LA(1)) {
				case STRING:
					enterOuterAlt(_localctx, 1);
				{
					setState(215);
					match(STRING);
				}
				break;
				case PLUS:
				case MINUS:
				case INTEGER:
				case FLOAT:
					enterOuterAlt(_localctx, 2);
				{
					setState(217);
					_errHandler.sync(this);
					_la = _input.LA(1);
					if (_la == PLUS || _la == MINUS) {
						{
							setState(216);
							_la = _input.LA(1);
							if (!(_la == PLUS || _la == MINUS)) {
								_errHandler.recoverInline(this);
							} else {
								if (_input.LA(1) == Token.EOF) matchedEOF = true;
								_errHandler.reportMatch(this);
								consume();
							}
						}
					}

					setState(219);
					_la = _input.LA(1);
					if (!(_la == INTEGER || _la == FLOAT)) {
						_errHandler.recoverInline(this);
					} else {
						if (_input.LA(1) == Token.EOF) matchedEOF = true;
						_errHandler.reportMatch(this);
						consume();
					}
				}
				break;
				case BOOL:
					enterOuterAlt(_localctx, 3);
				{
					setState(220);
					match(BOOL);
				}
				break;
				case NULL:
					enterOuterAlt(_localctx, 4);
				{
					setState(221);
					match(NULL);
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

	public static class ColumnContext extends ParserRuleContext {
		public ColumnContext(ParserRuleContext parent, int invokingState) {
			super(parent, invokingState);
		}

		@Override
		public int getRuleIndex() {
			return RULE_column;
		}

		public ColumnContext() {
		}

		public void copyFrom(ColumnContext ctx) {
			super.copyFrom(ctx);
		}
	}

	public static class ColumnIdContext extends ColumnContext {
		public TerminalNode COLUMN_ID() {
			return getToken(FormulaParser.COLUMN_ID, 0);
		}

		public ColumnIdContext(ColumnContext ctx) {
			copyFrom(ctx);
		}

		@Override
		public <T> T accept(ParseTreeVisitor<? extends T> visitor) {
			if (visitor instanceof FormulaVisitor) return ((FormulaVisitor<? extends T>) visitor).visitColumnId(this);
			else return visitor.visitChildren(this);
		}
	}

	public static class IdentityContext extends ColumnContext {
		public TerminalNode IDENTITY() {
			return getToken(FormulaParser.IDENTITY, 0);
		}

		public IdentityContext(ColumnContext ctx) {
			copyFrom(ctx);
		}

		@Override
		public <T> T accept(ParseTreeVisitor<? extends T> visitor) {
			if (visitor instanceof FormulaVisitor) return ((FormulaVisitor<? extends T>) visitor).visitIdentity(this);
			else return visitor.visitChildren(this);
		}
	}

	public static class ColumnNameContext extends ColumnContext {
		public TerminalNode COLUMN_NAME() {
			return getToken(FormulaParser.COLUMN_NAME, 0);
		}

		public ColumnNameContext(ColumnContext ctx) {
			copyFrom(ctx);
		}

		@Override
		public <T> T accept(ParseTreeVisitor<? extends T> visitor) {
			if (visitor instanceof FormulaVisitor) return ((FormulaVisitor<? extends T>) visitor).visitColumnName(this);
			else return visitor.visitChildren(this);
		}
	}

	public final ColumnContext column() throws RecognitionException {
		ColumnContext _localctx = new ColumnContext(_ctx, getState());
		enterRule(_localctx, 28, RULE_column);
		try {
			setState(227);
			_errHandler.sync(this);
			switch (_input.LA(1)) {
				case COLUMN_ID:
					_localctx = new ColumnIdContext(_localctx);
					enterOuterAlt(_localctx, 1);
				{
					setState(224);
					match(COLUMN_ID);
				}
				break;
				case COLUMN_NAME:
					_localctx = new ColumnNameContext(_localctx);
					enterOuterAlt(_localctx, 2);
				{
					setState(225);
					match(COLUMN_NAME);
				}
				break;
				case IDENTITY:
					_localctx = new IdentityContext(_localctx);
					enterOuterAlt(_localctx, 3);
				{
					setState(226);
					match(IDENTITY);
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

	public boolean sempred(RuleContext _localctx, int ruleIndex, int predIndex) {
		switch (ruleIndex) {
			case 1:
				return statement_sempred((StatementContext) _localctx, predIndex);
			case 12:
				return predictStatement_sempred((PredictStatementContext) _localctx, predIndex);
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
			"\3\u608b\ua72a\u8133\ub9ed\u417c\u3be7\u7786\u5964\3.\u00e8\4\2\t\2\4" +
					"\3\t\3\4\4\t\4\4\5\t\5\4\6\t\6\4\7\t\7\4\b\t\b\4\t\t\t\4\n\t\n\4\13\t" +
					"\13\4\f\t\f\4\r\t\r\4\16\t\16\4\17\t\17\4\20\t\20\3\2\3\2\3\2\3\3\3\3" +
					"\3\3\3\3\3\3\3\3\3\3\3\3\3\3\3\3\3\3\3\3\3\3\3\3\5\3\62\n\3\3\3\5\3\65" +
					"\n\3\3\3\3\3\3\3\3\3\3\3\3\3\3\3\3\3\3\3\3\3\3\3\3\3\3\3\3\3\3\3\3\3\3" +
					"\3\5\3H\n\3\3\3\3\3\3\3\3\3\3\3\3\3\3\3\3\3\3\3\3\3\3\3\3\3\3\3\3\3\3" +
					"\3\3\3\5\3Z\n\3\3\3\3\3\3\3\3\3\3\3\7\3a\n\3\f\3\16\3d\13\3\3\4\3\4\3" +
					"\4\5\4i\n\4\3\4\3\4\3\5\3\5\3\5\7\5p\n\5\f\5\16\5s\13\5\3\6\3\6\3\6\3" +
					"\6\5\6y\n\6\3\7\3\7\3\7\7\7~\n\7\f\7\16\7\u0081\13\7\3\b\3\b\3\b\3\b\3" +
					"\b\3\b\6\b\u0089\n\b\r\b\16\b\u008a\3\b\3\b\3\b\3\b\3\b\3\b\6\b\u0093" +
					"\n\b\r\b\16\b\u0094\5\b\u0097\n\b\3\b\3\b\5\b\u009b\n\b\3\b\3\b\3\t\3" +
					"\t\5\t\u00a1\n\t\3\n\3\n\3\n\3\n\3\n\3\n\3\n\3\n\3\n\3\13\3\13\3\13\3" +
					"\13\3\13\3\13\3\13\3\13\7\13\u00b4\n\13\f\13\16\13\u00b7\13\13\3\13\5" +
					"\13\u00ba\n\13\3\f\3\f\3\f\3\r\3\r\3\r\3\r\3\r\3\16\3\16\3\16\3\16\3\16" +
					"\3\16\5\16\u00ca\n\16\3\16\3\16\3\16\3\16\3\16\3\16\3\16\3\16\3\16\7\16" +
					"\u00d5\n\16\f\16\16\16\u00d8\13\16\3\17\3\17\5\17\u00dc\n\17\3\17\3\17" +
					"\3\17\5\17\u00e1\n\17\3\20\3\20\3\20\5\20\u00e6\n\20\3\20\2\4\4\32\21" +
					"\2\4\6\b\n\f\16\20\22\24\26\30\32\34\36\2\6\3\2\13\f\3\2\r\16\3\2\22\27" +
					"\3\2\'(\2\u0102\2 \3\2\2\2\4\64\3\2\2\2\6e\3\2\2\2\bl\3\2\2\2\nx\3\2\2" +
					"\2\fz\3\2\2\2\16\u0082\3\2\2\2\20\u00a0\3\2\2\2\22\u00a2\3\2\2\2\24\u00ab" +
					"\3\2\2\2\26\u00bb\3\2\2\2\30\u00be\3\2\2\2\32\u00c9\3\2\2\2\34\u00e0\3" +
					"\2\2\2\36\u00e5\3\2\2\2 !\5\4\3\2!\"\7\2\2\3\"\3\3\2\2\2#$\b\3\1\2$\65" +
					"\5\20\t\2%\65\5\6\4\2&\65\5\16\b\2\'\65\5\34\17\2(\65\5\36\20\2)*\7\33" +
					"\2\2*\65\5\4\3\t+,\7\3\2\2,-\5\4\3\2-.\7\4\2\2.\65\3\2\2\2/\61\7\7\2\2" +
					"\60\62\5\f\7\2\61\60\3\2\2\2\61\62\3\2\2\2\62\63\3\2\2\2\63\65\7\b\2\2" +
					"\64#\3\2\2\2\64%\3\2\2\2\64&\3\2\2\2\64\'\3\2\2\2\64(\3\2\2\2\64)\3\2" +
					"\2\2\64+\3\2\2\2\64/\3\2\2\2\65b\3\2\2\2\66\67\f\20\2\2\678\7\17\2\28" +
					"a\5\4\3\209:\f\17\2\2:;\7\20\2\2;a\5\4\3\20<=\f\16\2\2=>\t\2\2\2>a\5\4" +
					"\3\17?@\f\r\2\2@A\t\3\2\2Aa\5\4\3\16BC\f\f\2\2CD\t\4\2\2Da\5\4\3\rEG\f" +
					"\n\2\2FH\7\33\2\2GF\3\2\2\2GH\3\2\2\2HI\3\2\2\2IJ\7\31\2\2Ja\5\4\3\13" +
					"KL\f\b\2\2LM\7\34\2\2Ma\5\4\3\tNO\f\7\2\2OP\7\36\2\2Pa\5\4\3\bQR\f\6\2" +
					"\2RS\7\35\2\2Sa\5\4\3\7TU\f\3\2\2UV\7\21\2\2Va\5\4\3\4WY\f\13\2\2XZ\7" +
					"\33\2\2YX\3\2\2\2YZ\3\2\2\2Z[\3\2\2\2[\\\7\32\2\2\\]\7\3\2\2]^\5\f\7\2" +
					"^_\7\4\2\2_a\3\2\2\2`\66\3\2\2\2`9\3\2\2\2`<\3\2\2\2`?\3\2\2\2`B\3\2\2" +
					"\2`E\3\2\2\2`K\3\2\2\2`N\3\2\2\2`Q\3\2\2\2`T\3\2\2\2`W\3\2\2\2ad\3\2\2" +
					"\2b`\3\2\2\2bc\3\2\2\2c\5\3\2\2\2db\3\2\2\2ef\7*\2\2fh\7\3\2\2gi\5\b\5" +
					"\2hg\3\2\2\2hi\3\2\2\2ij\3\2\2\2jk\7\4\2\2k\7\3\2\2\2lq\5\n\6\2mn\7\t" +
					"\2\2np\5\n\6\2om\3\2\2\2ps\3\2\2\2qo\3\2\2\2qr\3\2\2\2r\t\3\2\2\2sq\3" +
					"\2\2\2ty\5\4\3\2uv\7*\2\2vw\7\24\2\2wy\5\4\3\2xt\3\2\2\2xu\3\2\2\2y\13" +
					"\3\2\2\2z\177\5\4\3\2{|\7\t\2\2|~\5\4\3\2}{\3\2\2\2~\u0081\3\2\2\2\177" +
					"}\3\2\2\2\177\u0080\3\2\2\2\u0080\r\3\2\2\2\u0081\177\3\2\2\2\u0082\u0096" +
					"\7\37\2\2\u0083\u0084\7 \2\2\u0084\u0085\5\32\16\2\u0085\u0086\7!\2\2" +
					"\u0086\u0087\5\4\3\2\u0087\u0089\3\2\2\2\u0088\u0083\3\2\2\2\u0089\u008a" +
					"\3\2\2\2\u008a\u0088\3\2\2\2\u008a\u008b\3\2\2\2\u008b\u0097\3\2\2\2\u008c" +
					"\u0092\5\4\3\2\u008d\u008e\7 \2\2\u008e\u008f\5\34\17\2\u008f\u0090\7" +
					"!\2\2\u0090\u0091\5\4\3\2\u0091\u0093\3\2\2\2\u0092\u008d\3\2\2\2\u0093" +
					"\u0094\3\2\2\2\u0094\u0092\3\2\2\2\u0094\u0095\3\2\2\2\u0095\u0097\3\2" +
					"\2\2\u0096\u0088\3\2\2\2\u0096\u008c\3\2\2\2\u0097\u009a\3\2\2\2\u0098" +
					"\u0099\7\"\2\2\u0099\u009b\5\4\3\2\u009a\u0098\3\2\2\2\u009a\u009b\3\2" +
					"\2\2\u009b\u009c\3\2\2\2\u009c\u009d\7#\2\2\u009d\17\3\2\2\2\u009e\u00a1" +
					"\5\22\n\2\u009f\u00a1\5\24\13\2\u00a0\u009e\3\2\2\2\u00a0\u009f\3\2\2" +
					"\2\u00a1\21\3\2\2\2\u00a2\u00a3\7\30\2\2\u00a3\u00a4\7\3\2\2\u00a4\u00a5" +
					"\5\32\16\2\u00a5\u00a6\7\t\2\2\u00a6\u00a7\5\4\3\2\u00a7\u00a8\7\t\2\2" +
					"\u00a8\u00a9\5\4\3\2\u00a9\u00aa\7\4\2\2\u00aa\23\3\2\2\2\u00ab\u00ac" +
					"\7\30\2\2\u00ac\u00ad\7\3\2\2\u00ad\u00ae\5\32\16\2\u00ae\u00af\7\4\2" +
					"\2\u00af\u00b0\7\5\2\2\u00b0\u00b1\5\4\3\2\u00b1\u00b5\7\6\2\2\u00b2\u00b4" +
					"\5\26\f\2\u00b3\u00b2\3\2\2\2\u00b4\u00b7\3\2\2\2\u00b5\u00b3\3\2\2\2" +
					"\u00b5\u00b6\3\2\2\2\u00b6\u00b9\3\2\2\2\u00b7\u00b5\3\2\2\2\u00b8\u00ba" +
					"\5\30\r\2\u00b9\u00b8\3\2\2\2\u00b9\u00ba\3\2\2\2\u00ba\25\3\2\2\2\u00bb" +
					"\u00bc\7\"\2\2\u00bc\u00bd\5\24\13\2\u00bd\27\3\2\2\2\u00be\u00bf\7\"" +
					"\2\2\u00bf\u00c0\7\5\2\2\u00c0\u00c1\5\4\3\2\u00c1\u00c2\7\6\2\2\u00c2" +
					"\31\3\2\2\2\u00c3\u00c4\b\16\1\2\u00c4\u00ca\5\4\3\2\u00c5\u00c6\7\3\2" +
					"\2\u00c6\u00c7\5\32\16\2\u00c7\u00c8\7\4\2\2\u00c8\u00ca\3\2\2\2\u00c9" +
					"\u00c3\3\2\2\2\u00c9\u00c5\3\2\2\2\u00ca\u00d6\3\2\2\2\u00cb\u00cc\f\5" +
					"\2\2\u00cc\u00cd\7\34\2\2\u00cd\u00d5\5\32\16\6\u00ce\u00cf\f\4\2\2\u00cf" +
					"\u00d0\7\36\2\2\u00d0\u00d5\5\32\16\5\u00d1\u00d2\f\3\2\2\u00d2\u00d3" +
					"\7\35\2\2\u00d3\u00d5\5\32\16\4\u00d4\u00cb\3\2\2\2\u00d4\u00ce\3\2\2" +
					"\2\u00d4\u00d1\3\2\2\2\u00d5\u00d8\3\2\2\2\u00d6\u00d4\3\2\2\2\u00d6\u00d7" +
					"\3\2\2\2\u00d7\33\3\2\2\2\u00d8\u00d6\3\2\2\2\u00d9\u00e1\7&\2\2\u00da" +
					"\u00dc\t\3\2\2\u00db\u00da\3\2\2\2\u00db\u00dc\3\2\2\2\u00dc\u00dd\3\2" +
					"\2\2\u00dd\u00e1\t\5\2\2\u00de\u00e1\7$\2\2\u00df\u00e1\7%\2\2\u00e0\u00d9" +
					"\3\2\2\2\u00e0\u00db\3\2\2\2\u00e0\u00de\3\2\2\2\u00e0\u00df\3\2\2\2\u00e1" +
					"\35\3\2\2\2\u00e2\u00e6\7)\2\2\u00e3\u00e6\7+\2\2\u00e4\u00e6\7*\2\2\u00e5" +
					"\u00e2\3\2\2\2\u00e5\u00e3\3\2\2\2\u00e5\u00e4\3\2\2\2\u00e6\37\3\2\2" +
					"\2\31\61\64GY`bhqx\177\u008a\u0094\u0096\u009a\u00a0\u00b5\u00b9\u00c9" +
					"\u00d4\u00d6\u00db\u00e0\u00e5";
	public static final ATN _ATN =
			new ATNDeserializer().deserialize(_serializedATN.toCharArray());
	static {
		_decisionToDFA = new DFA[_ATN.getNumberOfDecisions()];
		for (int i = 0; i < _ATN.getNumberOfDecisions(); i++) {
			_decisionToDFA[i] = new DFA(_ATN.getDecisionState(i), i);
		}
	}
}