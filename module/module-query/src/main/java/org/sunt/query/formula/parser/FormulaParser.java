// Generated from D:/projects/pms/module/module-formula/src/main/java/org/sunt/formula/parser\Formula.g4 by ANTLR 4.9.1
package org.sunt.query.formula.parser;

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
			RULE_column = 14, RULE_identity = 15;

	private static String[] makeRuleNames() {
		return new String[]{
				"formula", "statement", "functionStatement", "functionParams", "functionParam",
				"statements", "caseStatement", "ifSpecial", "ifFunction", "ifStatement",
				"elseIfStatement", "elseStatement", "predictStatement", "constant", "column",
				"identity"
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
				setState(32);
				statement(0);
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
				setState(52);
				_errHandler.sync(this);
				switch (getInterpreter().adaptivePredict(_input, 1, _ctx)) {
					case 1: {
						_localctx = new IfExpressionContext(_localctx);
						_ctx = _localctx;
						_prevctx = _localctx;

						setState(36);
						ifSpecial();
					}
					break;
					case 2: {
						_localctx = new FunctionExpressionContext(_localctx);
						_ctx = _localctx;
						_prevctx = _localctx;
						setState(37);
						functionStatement();
					}
					break;
					case 3: {
						_localctx = new CaseExpressionContext(_localctx);
						_ctx = _localctx;
						_prevctx = _localctx;
						setState(38);
						caseStatement();
					}
					break;
					case 4: {
						_localctx = new ConstantExpressionContext(_localctx);
						_ctx = _localctx;
						_prevctx = _localctx;
						setState(39);
						constant();
					}
					break;
					case 5: {
						_localctx = new ColumnExpressionContext(_localctx);
						_ctx = _localctx;
						_prevctx = _localctx;
						setState(40);
						column();
					}
					break;
					case 6: {
						_localctx = new NotPredicateContext(_localctx);
						_ctx = _localctx;
						_prevctx = _localctx;
						setState(41);
						match(NOT);
						setState(42);
						statement(7);
					}
					break;
					case 7: {
						_localctx = new ParenthesesExpressionContext(_localctx);
						_ctx = _localctx;
						_prevctx = _localctx;
						setState(43);
						match(L_PARENTHESES);
						setState(44);
						statement(0);
						setState(45);
						match(R_PARENTHESES);
					}
					break;
					case 8: {
						_localctx = new SquareExpressionContext(_localctx);
						_ctx = _localctx;
						_prevctx = _localctx;
						setState(47);
						match(L_SQUARE);
						setState(49);
						_errHandler.sync(this);
						_la = _input.LA(1);
						if ((((_la) & ~0x3f) == 0 && ((1L << _la) & ((1L << L_PARENTHESES) | (1L << L_SQUARE) | (1L << PLUS) | (1L << MINUS) | (1L << IF) | (1L << NOT) | (1L << CASE) | (1L << BOOL) | (1L << NULL) | (1L << STRING) | (1L << INTEGER) | (1L << FLOAT) | (1L << COLUMN_ID) | (1L << IDENTITY) | (1L << COLUMN_NAME))) != 0)) {
							{
								setState(48);
								statements();
							}
						}

						setState(51);
						match(R_SQUARE);
					}
					break;
				}
				_ctx.stop = _input.LT(-1);
				setState(98);
				_errHandler.sync(this);
				_alt = getInterpreter().adaptivePredict(_input, 5, _ctx);
				while (_alt != 2 && _alt != org.antlr.v4.runtime.atn.ATN.INVALID_ALT_NUMBER) {
					if (_alt == 1) {
						if (_parseListeners != null) triggerExitRuleEvent();
						_prevctx = _localctx;
						{
							setState(96);
							_errHandler.sync(this);
							switch (getInterpreter().adaptivePredict(_input, 4, _ctx)) {
								case 1: {
									_localctx = new MathExpressionContext(new StatementContext(_parentctx, _parentState));
									pushNewRecursionContext(_localctx, _startState, RULE_statement);
									setState(54);
									if (!(precpred(_ctx, 14)))
										throw new FailedPredicateException(this, "precpred(_ctx, 14)");
									setState(55);
									((MathExpressionContext) _localctx).op = match(POWER);
									setState(56);
									statement(14);
								}
								break;
								case 2: {
									_localctx = new MathExpressionContext(new StatementContext(_parentctx, _parentState));
									pushNewRecursionContext(_localctx, _startState, RULE_statement);
									setState(57);
									if (!(precpred(_ctx, 13)))
										throw new FailedPredicateException(this, "precpred(_ctx, 13)");
									setState(58);
									((MathExpressionContext) _localctx).op = match(MOD);
									setState(59);
									statement(14);
								}
								break;
								case 3: {
									_localctx = new MathExpressionContext(new StatementContext(_parentctx, _parentState));
									pushNewRecursionContext(_localctx, _startState, RULE_statement);
									setState(60);
									if (!(precpred(_ctx, 12)))
										throw new FailedPredicateException(this, "precpred(_ctx, 12)");
									setState(61);
									((MathExpressionContext) _localctx).op = _input.LT(1);
									_la = _input.LA(1);
									if (!(_la == MUL || _la == DIV)) {
										((MathExpressionContext) _localctx).op = (Token) _errHandler.recoverInline(this);
									} else {
										if (_input.LA(1) == Token.EOF) matchedEOF = true;
										_errHandler.reportMatch(this);
										consume();
									}
									setState(62);
									statement(13);
								}
								break;
								case 4: {
									_localctx = new MathExpressionContext(new StatementContext(_parentctx, _parentState));
									pushNewRecursionContext(_localctx, _startState, RULE_statement);
									setState(63);
									if (!(precpred(_ctx, 11)))
										throw new FailedPredicateException(this, "precpred(_ctx, 11)");
									setState(64);
									((MathExpressionContext) _localctx).op = _input.LT(1);
									_la = _input.LA(1);
									if (!(_la == PLUS || _la == MINUS)) {
										((MathExpressionContext) _localctx).op = (Token) _errHandler.recoverInline(this);
									} else {
										if (_input.LA(1) == Token.EOF) matchedEOF = true;
										_errHandler.reportMatch(this);
										consume();
									}
									setState(65);
									statement(12);
								}
								break;
								case 5: {
									_localctx = new ComparePredicateContext(new StatementContext(_parentctx, _parentState));
									pushNewRecursionContext(_localctx, _startState, RULE_statement);
									setState(66);
									if (!(precpred(_ctx, 10)))
										throw new FailedPredicateException(this, "precpred(_ctx, 10)");
									setState(67);
									((ComparePredicateContext) _localctx).op = _input.LT(1);
									_la = _input.LA(1);
									if (!((((_la) & ~0x3f) == 0 && ((1L << _la) & ((1L << GREATER) | (1L << GREATER_EQUAL) | (1L << EQUAL) | (1L << LESS) | (1L << LESS_EQUAL) | (1L << NOT_EQUAL))) != 0))) {
										((ComparePredicateContext) _localctx).op = (Token) _errHandler.recoverInline(this);
									} else {
										if (_input.LA(1) == Token.EOF) matchedEOF = true;
										_errHandler.reportMatch(this);
										consume();
									}
									setState(68);
									statement(11);
								}
								break;
								case 6: {
									_localctx = new LikePredicateContext(new StatementContext(_parentctx, _parentState));
									pushNewRecursionContext(_localctx, _startState, RULE_statement);
									setState(69);
									if (!(precpred(_ctx, 8)))
										throw new FailedPredicateException(this, "precpred(_ctx, 8)");
									setState(71);
									_errHandler.sync(this);
									_la = _input.LA(1);
									if (_la == NOT) {
										{
											setState(70);
											match(NOT);
										}
									}

									setState(73);
									((LikePredicateContext) _localctx).op = match(LIKE);
									setState(74);
									statement(9);
								}
								break;
								case 7: {
									_localctx = new LogicalPredicateContext(new StatementContext(_parentctx, _parentState));
									pushNewRecursionContext(_localctx, _startState, RULE_statement);
									setState(75);
									if (!(precpred(_ctx, 6)))
										throw new FailedPredicateException(this, "precpred(_ctx, 6)");
									setState(76);
									((LogicalPredicateContext) _localctx).op = match(AND);
									setState(77);
									statement(7);
								}
								break;
								case 8: {
									_localctx = new LogicalPredicateContext(new StatementContext(_parentctx, _parentState));
									pushNewRecursionContext(_localctx, _startState, RULE_statement);
									setState(78);
									if (!(precpred(_ctx, 5)))
										throw new FailedPredicateException(this, "precpred(_ctx, 5)");
									setState(79);
									((LogicalPredicateContext) _localctx).op = match(XOR);
									setState(80);
									statement(6);
								}
								break;
								case 9: {
									_localctx = new LogicalPredicateContext(new StatementContext(_parentctx, _parentState));
									pushNewRecursionContext(_localctx, _startState, RULE_statement);
									setState(81);
									if (!(precpred(_ctx, 4)))
										throw new FailedPredicateException(this, "precpred(_ctx, 4)");
									setState(82);
									((LogicalPredicateContext) _localctx).op = match(OR);
									setState(83);
									statement(5);
								}
								break;
								case 10: {
									_localctx = new IfnullExpressionContext(new StatementContext(_parentctx, _parentState));
									pushNewRecursionContext(_localctx, _startState, RULE_statement);
									setState(84);
									if (!(precpred(_ctx, 1)))
										throw new FailedPredicateException(this, "precpred(_ctx, 1)");
									setState(85);
									match(IFNULL);
									setState(86);
									statement(2);
								}
								break;
								case 11: {
									_localctx = new InPredicateContext(new StatementContext(_parentctx, _parentState));
									pushNewRecursionContext(_localctx, _startState, RULE_statement);
									setState(87);
									if (!(precpred(_ctx, 9)))
										throw new FailedPredicateException(this, "precpred(_ctx, 9)");
									setState(89);
									_errHandler.sync(this);
									_la = _input.LA(1);
									if (_la == NOT) {
										{
											setState(88);
											match(NOT);
										}
									}

									setState(91);
									((InPredicateContext) _localctx).op = match(IN);
									setState(92);
									match(L_PARENTHESES);
									setState(93);
									statements();
									setState(94);
									match(R_PARENTHESES);
								}
								break;
							}
						}
					}
					setState(100);
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
		public IdentityContext identity() {
			return getRuleContext(IdentityContext.class, 0);
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
				setState(101);
				identity();
				setState(102);
				match(L_PARENTHESES);
				setState(104);
				_errHandler.sync(this);
				_la = _input.LA(1);
				if ((((_la) & ~0x3f) == 0 && ((1L << _la) & ((1L << L_PARENTHESES) | (1L << L_SQUARE) | (1L << PLUS) | (1L << MINUS) | (1L << IF) | (1L << NOT) | (1L << CASE) | (1L << BOOL) | (1L << NULL) | (1L << STRING) | (1L << INTEGER) | (1L << FLOAT) | (1L << COLUMN_ID) | (1L << IDENTITY) | (1L << COLUMN_NAME))) != 0)) {
					{
						setState(103);
						functionParams();
					}
				}

				setState(106);
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
				setState(108);
				functionParam();
				setState(113);
				_errHandler.sync(this);
				_la = _input.LA(1);
				while (_la == COMMA) {
					{
						{
							setState(109);
							match(COMMA);
							setState(110);
							functionParam();
						}
					}
					setState(115);
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

		public IdentityContext identity() {
			return getRuleContext(IdentityContext.class, 0);
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
			setState(121);
			_errHandler.sync(this);
			switch (getInterpreter().adaptivePredict(_input, 8, _ctx)) {
				case 1:
					enterOuterAlt(_localctx, 1);
				{
					setState(116);
					statement(0);
				}
				break;
				case 2:
					enterOuterAlt(_localctx, 2);
				{
					setState(117);
					identity();
					setState(118);
					match(EQUAL);
					setState(119);
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
				setState(123);
				statement(0);
				setState(128);
				_errHandler.sync(this);
				_la = _input.LA(1);
				while (_la == COMMA) {
					{
						{
							setState(124);
							match(COMMA);
							setState(125);
							statement(0);
						}
					}
					setState(130);
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
				setState(131);
				match(CASE);
				setState(151);
				_errHandler.sync(this);
				switch (_input.LA(1)) {
					case WHEN: {
						setState(137);
						_errHandler.sync(this);
						_la = _input.LA(1);
						do {
							{
								{
									setState(132);
									match(WHEN);
									setState(133);
									predictStatement(0);
									setState(134);
									match(THEN);
									setState(135);
									((CaseStatementContext) _localctx).thenStmt = statement(0);
								}
							}
							setState(139);
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
						setState(141);
						((CaseStatementContext) _localctx).caseStmt = statement(0);
						setState(147);
						_errHandler.sync(this);
						_la = _input.LA(1);
						do {
							{
								{
									setState(142);
									match(WHEN);
									setState(143);
									constant();
									setState(144);
									match(THEN);
									setState(145);
									((CaseStatementContext) _localctx).thenStmt = statement(0);
								}
							}
							setState(149);
							_errHandler.sync(this);
							_la = _input.LA(1);
						} while (_la == WHEN);
					}
					break;
					default:
						throw new NoViableAltException(this);
				}
				setState(155);
				_errHandler.sync(this);
				_la = _input.LA(1);
				if (_la == ELSE) {
					{
						setState(153);
						match(ELSE);
						setState(154);
						((CaseStatementContext) _localctx).elseStmt = statement(0);
					}
				}

				setState(157);
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
			setState(161);
			_errHandler.sync(this);
			switch (getInterpreter().adaptivePredict(_input, 14, _ctx)) {
				case 1:
					enterOuterAlt(_localctx, 1);
				{
					setState(159);
					ifFunction();
				}
				break;
				case 2:
					enterOuterAlt(_localctx, 2);
				{
					setState(160);
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
				setState(163);
				match(IF);
				setState(164);
				match(L_PARENTHESES);
				setState(165);
				((IfFunctionContext) _localctx).predict = predictStatement(0);
				setState(166);
				match(COMMA);
				setState(167);
				((IfFunctionContext) _localctx).ifTrue = statement(0);
				setState(168);
				match(COMMA);
				setState(169);
				((IfFunctionContext) _localctx).ifFalse = statement(0);
				setState(170);
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
				setState(172);
				match(IF);
				setState(173);
				match(L_PARENTHESES);
				setState(174);
				((IfStatementContext) _localctx).predict = predictStatement(0);
				setState(175);
				match(R_PARENTHESES);
				setState(176);
				match(L_BRACE);
				setState(177);
				statement(0);
				setState(178);
				match(R_BRACE);
				setState(182);
				_errHandler.sync(this);
				_alt = getInterpreter().adaptivePredict(_input, 15, _ctx);
				while (_alt != 2 && _alt != org.antlr.v4.runtime.atn.ATN.INVALID_ALT_NUMBER) {
					if (_alt == 1) {
						{
							{
								setState(179);
								elseIfStatement();
							}
						}
					}
					setState(184);
					_errHandler.sync(this);
					_alt = getInterpreter().adaptivePredict(_input, 15, _ctx);
				}
				setState(186);
				_errHandler.sync(this);
				switch (getInterpreter().adaptivePredict(_input, 16, _ctx)) {
					case 1: {
						setState(185);
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
				setState(188);
				match(ELSE);
				setState(189);
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
				setState(191);
				match(ELSE);
				setState(192);
				match(L_BRACE);
				setState(193);
				statement(0);
				setState(194);
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
				setState(202);
				_errHandler.sync(this);
				switch (getInterpreter().adaptivePredict(_input, 17, _ctx)) {
					case 1: {
						setState(197);
						statement(0);
					}
					break;
					case 2: {
						setState(198);
						match(L_PARENTHESES);
						setState(199);
						predictStatement(0);
						setState(200);
						match(R_PARENTHESES);
					}
					break;
				}
				_ctx.stop = _input.LT(-1);
				setState(215);
				_errHandler.sync(this);
				_alt = getInterpreter().adaptivePredict(_input, 19, _ctx);
				while (_alt != 2 && _alt != org.antlr.v4.runtime.atn.ATN.INVALID_ALT_NUMBER) {
					if (_alt == 1) {
						if (_parseListeners != null) triggerExitRuleEvent();
						_prevctx = _localctx;
						{
							setState(213);
							_errHandler.sync(this);
							switch (getInterpreter().adaptivePredict(_input, 18, _ctx)) {
								case 1: {
									_localctx = new PredictStatementContext(_parentctx, _parentState);
									pushNewRecursionContext(_localctx, _startState, RULE_predictStatement);
									setState(204);
									if (!(precpred(_ctx, 3)))
										throw new FailedPredicateException(this, "precpred(_ctx, 3)");
									setState(205);
									((PredictStatementContext) _localctx).op = match(AND);
									setState(206);
									predictStatement(4);
								}
								break;
								case 2: {
									_localctx = new PredictStatementContext(_parentctx, _parentState);
									pushNewRecursionContext(_localctx, _startState, RULE_predictStatement);
									setState(207);
									if (!(precpred(_ctx, 2)))
										throw new FailedPredicateException(this, "precpred(_ctx, 2)");
									setState(208);
									((PredictStatementContext) _localctx).op = match(XOR);
									setState(209);
									predictStatement(3);
								}
								break;
								case 3: {
									_localctx = new PredictStatementContext(_parentctx, _parentState);
									pushNewRecursionContext(_localctx, _startState, RULE_predictStatement);
									setState(210);
									if (!(precpred(_ctx, 1)))
										throw new FailedPredicateException(this, "precpred(_ctx, 1)");
									setState(211);
									((PredictStatementContext) _localctx).op = match(OR);
									setState(212);
									predictStatement(2);
								}
								break;
							}
						}
					}
					setState(217);
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
			setState(225);
			_errHandler.sync(this);
			switch (_input.LA(1)) {
				case STRING:
					enterOuterAlt(_localctx, 1);
				{
					setState(218);
					match(STRING);
				}
				break;
				case PLUS:
				case MINUS:
				case INTEGER:
				case FLOAT:
					enterOuterAlt(_localctx, 2);
				{
					setState(220);
					_errHandler.sync(this);
					_la = _input.LA(1);
					if (_la == PLUS || _la == MINUS) {
						{
							setState(219);
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

					setState(222);
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
					setState(223);
					match(BOOL);
				}
				break;
				case NULL:
					enterOuterAlt(_localctx, 4);
				{
					setState(224);
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

	public static class ColumnIdentityContext extends ColumnContext {
		public IdentityContext identity() {
			return getRuleContext(IdentityContext.class, 0);
		}

		public ColumnIdentityContext(ColumnContext ctx) {
			copyFrom(ctx);
		}

		@Override
		public <T> T accept(ParseTreeVisitor<? extends T> visitor) {
			if (visitor instanceof FormulaVisitor)
				return ((FormulaVisitor<? extends T>) visitor).visitColumnIdentity(this);
			else return visitor.visitChildren(this);
		}
	}

	public final ColumnContext column() throws RecognitionException {
		ColumnContext _localctx = new ColumnContext(_ctx, getState());
		enterRule(_localctx, 28, RULE_column);
		try {
			setState(230);
			_errHandler.sync(this);
			switch (_input.LA(1)) {
				case COLUMN_ID:
					_localctx = new ColumnIdContext(_localctx);
					enterOuterAlt(_localctx, 1);
				{
					setState(227);
					match(COLUMN_ID);
				}
				break;
				case COLUMN_NAME:
					_localctx = new ColumnNameContext(_localctx);
					enterOuterAlt(_localctx, 2);
				{
					setState(228);
					match(COLUMN_NAME);
				}
				break;
				case IDENTITY:
					_localctx = new ColumnIdentityContext(_localctx);
					enterOuterAlt(_localctx, 3);
				{
					setState(229);
					identity();
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

	public static class IdentityContext extends ParserRuleContext {
		public TerminalNode IDENTITY() {
			return getToken(FormulaParser.IDENTITY, 0);
		}

		public IdentityContext(ParserRuleContext parent, int invokingState) {
			super(parent, invokingState);
		}

		@Override
		public int getRuleIndex() {
			return RULE_identity;
		}

		@Override
		public <T> T accept(ParseTreeVisitor<? extends T> visitor) {
			if (visitor instanceof FormulaVisitor) return ((FormulaVisitor<? extends T>) visitor).visitIdentity(this);
			else return visitor.visitChildren(this);
		}
	}

	public final IdentityContext identity() throws RecognitionException {
		IdentityContext _localctx = new IdentityContext(_ctx, getState());
		enterRule(_localctx, 30, RULE_identity);
		try {
			enterOuterAlt(_localctx, 1);
			{
				setState(232);
				match(IDENTITY);
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
			"\3\u608b\ua72a\u8133\ub9ed\u417c\u3be7\u7786\u5964\3.\u00ed\4\2\t\2\4" +
					"\3\t\3\4\4\t\4\4\5\t\5\4\6\t\6\4\7\t\7\4\b\t\b\4\t\t\t\4\n\t\n\4\13\t" +
					"\13\4\f\t\f\4\r\t\r\4\16\t\16\4\17\t\17\4\20\t\20\4\21\t\21\3\2\3\2\3" +
					"\2\3\3\3\3\3\3\3\3\3\3\3\3\3\3\3\3\3\3\3\3\3\3\3\3\3\3\3\3\5\3\64\n\3" +
					"\3\3\5\3\67\n\3\3\3\3\3\3\3\3\3\3\3\3\3\3\3\3\3\3\3\3\3\3\3\3\3\3\3\3" +
					"\3\3\3\3\3\3\3\5\3J\n\3\3\3\3\3\3\3\3\3\3\3\3\3\3\3\3\3\3\3\3\3\3\3\3" +
					"\3\3\3\3\3\3\3\3\3\5\3\\\n\3\3\3\3\3\3\3\3\3\3\3\7\3c\n\3\f\3\16\3f\13" +
					"\3\3\4\3\4\3\4\5\4k\n\4\3\4\3\4\3\5\3\5\3\5\7\5r\n\5\f\5\16\5u\13\5\3" +
					"\6\3\6\3\6\3\6\3\6\5\6|\n\6\3\7\3\7\3\7\7\7\u0081\n\7\f\7\16\7\u0084\13" +
					"\7\3\b\3\b\3\b\3\b\3\b\3\b\6\b\u008c\n\b\r\b\16\b\u008d\3\b\3\b\3\b\3" +
					"\b\3\b\3\b\6\b\u0096\n\b\r\b\16\b\u0097\5\b\u009a\n\b\3\b\3\b\5\b\u009e" +
					"\n\b\3\b\3\b\3\t\3\t\5\t\u00a4\n\t\3\n\3\n\3\n\3\n\3\n\3\n\3\n\3\n\3\n" +
					"\3\13\3\13\3\13\3\13\3\13\3\13\3\13\3\13\7\13\u00b7\n\13\f\13\16\13\u00ba" +
					"\13\13\3\13\5\13\u00bd\n\13\3\f\3\f\3\f\3\r\3\r\3\r\3\r\3\r\3\16\3\16" +
					"\3\16\3\16\3\16\3\16\5\16\u00cd\n\16\3\16\3\16\3\16\3\16\3\16\3\16\3\16" +
					"\3\16\3\16\7\16\u00d8\n\16\f\16\16\16\u00db\13\16\3\17\3\17\5\17\u00df" +
					"\n\17\3\17\3\17\3\17\5\17\u00e4\n\17\3\20\3\20\3\20\5\20\u00e9\n\20\3" +
					"\21\3\21\3\21\2\4\4\32\22\2\4\6\b\n\f\16\20\22\24\26\30\32\34\36 \2\6" +
					"\3\2\13\f\3\2\r\16\3\2\22\27\3\2\'(\2\u0106\2\"\3\2\2\2\4\66\3\2\2\2\6" +
					"g\3\2\2\2\bn\3\2\2\2\n{\3\2\2\2\f}\3\2\2\2\16\u0085\3\2\2\2\20\u00a3\3" +
					"\2\2\2\22\u00a5\3\2\2\2\24\u00ae\3\2\2\2\26\u00be\3\2\2\2\30\u00c1\3\2" +
					"\2\2\32\u00cc\3\2\2\2\34\u00e3\3\2\2\2\36\u00e8\3\2\2\2 \u00ea\3\2\2\2" +
					"\"#\5\4\3\2#$\7\2\2\3$\3\3\2\2\2%&\b\3\1\2&\67\5\20\t\2\'\67\5\6\4\2(" +
					"\67\5\16\b\2)\67\5\34\17\2*\67\5\36\20\2+,\7\33\2\2,\67\5\4\3\t-.\7\3" +
					"\2\2./\5\4\3\2/\60\7\4\2\2\60\67\3\2\2\2\61\63\7\7\2\2\62\64\5\f\7\2\63" +
					"\62\3\2\2\2\63\64\3\2\2\2\64\65\3\2\2\2\65\67\7\b\2\2\66%\3\2\2\2\66\'" +
					"\3\2\2\2\66(\3\2\2\2\66)\3\2\2\2\66*\3\2\2\2\66+\3\2\2\2\66-\3\2\2\2\66" +
					"\61\3\2\2\2\67d\3\2\2\289\f\20\2\29:\7\17\2\2:c\5\4\3\20;<\f\17\2\2<=" +
					"\7\20\2\2=c\5\4\3\20>?\f\16\2\2?@\t\2\2\2@c\5\4\3\17AB\f\r\2\2BC\t\3\2" +
					"\2Cc\5\4\3\16DE\f\f\2\2EF\t\4\2\2Fc\5\4\3\rGI\f\n\2\2HJ\7\33\2\2IH\3\2" +
					"\2\2IJ\3\2\2\2JK\3\2\2\2KL\7\31\2\2Lc\5\4\3\13MN\f\b\2\2NO\7\34\2\2Oc" +
					"\5\4\3\tPQ\f\7\2\2QR\7\36\2\2Rc\5\4\3\bST\f\6\2\2TU\7\35\2\2Uc\5\4\3\7" +
					"VW\f\3\2\2WX\7\21\2\2Xc\5\4\3\4Y[\f\13\2\2Z\\\7\33\2\2[Z\3\2\2\2[\\\3" +
					"\2\2\2\\]\3\2\2\2]^\7\32\2\2^_\7\3\2\2_`\5\f\7\2`a\7\4\2\2ac\3\2\2\2b" +
					"8\3\2\2\2b;\3\2\2\2b>\3\2\2\2bA\3\2\2\2bD\3\2\2\2bG\3\2\2\2bM\3\2\2\2" +
					"bP\3\2\2\2bS\3\2\2\2bV\3\2\2\2bY\3\2\2\2cf\3\2\2\2db\3\2\2\2de\3\2\2\2" +
					"e\5\3\2\2\2fd\3\2\2\2gh\5 \21\2hj\7\3\2\2ik\5\b\5\2ji\3\2\2\2jk\3\2\2" +
					"\2kl\3\2\2\2lm\7\4\2\2m\7\3\2\2\2ns\5\n\6\2op\7\t\2\2pr\5\n\6\2qo\3\2" +
					"\2\2ru\3\2\2\2sq\3\2\2\2st\3\2\2\2t\t\3\2\2\2us\3\2\2\2v|\5\4\3\2wx\5" +
					" \21\2xy\7\24\2\2yz\5\4\3\2z|\3\2\2\2{v\3\2\2\2{w\3\2\2\2|\13\3\2\2\2" +
					"}\u0082\5\4\3\2~\177\7\t\2\2\177\u0081\5\4\3\2\u0080~\3\2\2\2\u0081\u0084" +
					"\3\2\2\2\u0082\u0080\3\2\2\2\u0082\u0083\3\2\2\2\u0083\r\3\2\2\2\u0084" +
					"\u0082\3\2\2\2\u0085\u0099\7\37\2\2\u0086\u0087\7 \2\2\u0087\u0088\5\32" +
					"\16\2\u0088\u0089\7!\2\2\u0089\u008a\5\4\3\2\u008a\u008c\3\2\2\2\u008b" +
					"\u0086\3\2\2\2\u008c\u008d\3\2\2\2\u008d\u008b\3\2\2\2\u008d\u008e\3\2" +
					"\2\2\u008e\u009a\3\2\2\2\u008f\u0095\5\4\3\2\u0090\u0091\7 \2\2\u0091" +
					"\u0092\5\34\17\2\u0092\u0093\7!\2\2\u0093\u0094\5\4\3\2\u0094\u0096\3" +
					"\2\2\2\u0095\u0090\3\2\2\2\u0096\u0097\3\2\2\2\u0097\u0095\3\2\2\2\u0097" +
					"\u0098\3\2\2\2\u0098\u009a\3\2\2\2\u0099\u008b\3\2\2\2\u0099\u008f\3\2" +
					"\2\2\u009a\u009d\3\2\2\2\u009b\u009c\7\"\2\2\u009c\u009e\5\4\3\2\u009d" +
					"\u009b\3\2\2\2\u009d\u009e\3\2\2\2\u009e\u009f\3\2\2\2\u009f\u00a0\7#" +
					"\2\2\u00a0\17\3\2\2\2\u00a1\u00a4\5\22\n\2\u00a2\u00a4\5\24\13\2\u00a3" +
					"\u00a1\3\2\2\2\u00a3\u00a2\3\2\2\2\u00a4\21\3\2\2\2\u00a5\u00a6\7\30\2" +
					"\2\u00a6\u00a7\7\3\2\2\u00a7\u00a8\5\32\16\2\u00a8\u00a9\7\t\2\2\u00a9" +
					"\u00aa\5\4\3\2\u00aa\u00ab\7\t\2\2\u00ab\u00ac\5\4\3\2\u00ac\u00ad\7\4" +
					"\2\2\u00ad\23\3\2\2\2\u00ae\u00af\7\30\2\2\u00af\u00b0\7\3\2\2\u00b0\u00b1" +
					"\5\32\16\2\u00b1\u00b2\7\4\2\2\u00b2\u00b3\7\5\2\2\u00b3\u00b4\5\4\3\2" +
					"\u00b4\u00b8\7\6\2\2\u00b5\u00b7\5\26\f\2\u00b6\u00b5\3\2\2\2\u00b7\u00ba" +
					"\3\2\2\2\u00b8\u00b6\3\2\2\2\u00b8\u00b9\3\2\2\2\u00b9\u00bc\3\2\2\2\u00ba" +
					"\u00b8\3\2\2\2\u00bb\u00bd\5\30\r\2\u00bc\u00bb\3\2\2\2\u00bc\u00bd\3" +
					"\2\2\2\u00bd\25\3\2\2\2\u00be\u00bf\7\"\2\2\u00bf\u00c0\5\24\13\2\u00c0" +
					"\27\3\2\2\2\u00c1\u00c2\7\"\2\2\u00c2\u00c3\7\5\2\2\u00c3\u00c4\5\4\3" +
					"\2\u00c4\u00c5\7\6\2\2\u00c5\31\3\2\2\2\u00c6\u00c7\b\16\1\2\u00c7\u00cd" +
					"\5\4\3\2\u00c8\u00c9\7\3\2\2\u00c9\u00ca\5\32\16\2\u00ca\u00cb\7\4\2\2" +
					"\u00cb\u00cd\3\2\2\2\u00cc\u00c6\3\2\2\2\u00cc\u00c8\3\2\2\2\u00cd\u00d9" +
					"\3\2\2\2\u00ce\u00cf\f\5\2\2\u00cf\u00d0\7\34\2\2\u00d0\u00d8\5\32\16" +
					"\6\u00d1\u00d2\f\4\2\2\u00d2\u00d3\7\36\2\2\u00d3\u00d8\5\32\16\5\u00d4" +
					"\u00d5\f\3\2\2\u00d5\u00d6\7\35\2\2\u00d6\u00d8\5\32\16\4\u00d7\u00ce" +
					"\3\2\2\2\u00d7\u00d1\3\2\2\2\u00d7\u00d4\3\2\2\2\u00d8\u00db\3\2\2\2\u00d9" +
					"\u00d7\3\2\2\2\u00d9\u00da\3\2\2\2\u00da\33\3\2\2\2\u00db\u00d9\3\2\2" +
					"\2\u00dc\u00e4\7&\2\2\u00dd\u00df\t\3\2\2\u00de\u00dd\3\2\2\2\u00de\u00df" +
					"\3\2\2\2\u00df\u00e0\3\2\2\2\u00e0\u00e4\t\5\2\2\u00e1\u00e4\7$\2\2\u00e2" +
					"\u00e4\7%\2\2\u00e3\u00dc\3\2\2\2\u00e3\u00de\3\2\2\2\u00e3\u00e1\3\2" +
					"\2\2\u00e3\u00e2\3\2\2\2\u00e4\35\3\2\2\2\u00e5\u00e9\7)\2\2\u00e6\u00e9" +
					"\7+\2\2\u00e7\u00e9\5 \21\2\u00e8\u00e5\3\2\2\2\u00e8\u00e6\3\2\2\2\u00e8" +
					"\u00e7\3\2\2\2\u00e9\37\3\2\2\2\u00ea\u00eb\7*\2\2\u00eb!\3\2\2\2\31\63" +
					"\66I[bdjs{\u0082\u008d\u0097\u0099\u009d\u00a3\u00b8\u00bc\u00cc\u00d7" +
					"\u00d9\u00de\u00e3\u00e8";
	public static final ATN _ATN =
			new ATNDeserializer().deserialize(_serializedATN.toCharArray());
	static {
		_decisionToDFA = new DFA[_ATN.getNumberOfDecisions()];
		for (int i = 0; i < _ATN.getNumberOfDecisions(); i++) {
			_decisionToDFA[i] = new DFA(_ATN.getDecisionState(i), i);
		}
	}
}