// Generated from D:/projects/pms/module/module-formula/src/main/java/org/sunt/formula/parser\Formula.g4 by ANTLR 4.8
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
		RuntimeMetaData.checkVersion("4.8", RuntimeMetaData.VERSION);
	}

	protected static final DFA[] _decisionToDFA;
	protected static final PredictionContextCache _sharedContextCache =
			new PredictionContextCache();
	public static final int
			L_PARENTHESES = 1, R_PARENTHESES = 2, L_BRACE = 3, R_BRACE = 4, COMMA = 5, COLON = 6,
			MUL = 7, DIV = 8, PLUS = 9, MINUS = 10, POWER = 11, MOD = 12, GREATER = 13, GREATER_EQUAL = 14,
			EQUAL = 15, LESS = 16, LESS_EQUAL = 17, NOT_EQUAL = 18, IF = 19, LIKE = 20, IN = 21,
			NOT = 22, AND = 23, OR = 24, XOR = 25, CASE = 26, WHEN = 27, THEN = 28, ELSE = 29, END = 30,
			BOOL = 31, NULL = 32, STRING = 33, INTEGER = 34, FLOAT = 35, COLUMN_ID = 36, IDENTITY = 37,
			COLUMN_NAME = 38, WS = 39, LINE_COMMENT = 40, BLOCK_COMMENT = 41;
	public static final int
			RULE_formula = 0, RULE_statement = 1, RULE_functionStatement = 2, RULE_functionParams = 3,
			RULE_caseStatement = 4, RULE_ifSpecial = 5, RULE_ifFunction = 6, RULE_ifStatement = 7,
			RULE_elseIfStatement = 8, RULE_elseStatement = 9, RULE_predictStatement = 10,
			RULE_constant = 11, RULE_column = 12;

	private static String[] makeRuleNames() {
		return new String[]{
				"formula", "statement", "functionStatement", "functionParams", "caseStatement",
				"ifSpecial", "ifFunction", "ifStatement", "elseIfStatement", "elseStatement",
				"predictStatement", "constant", "column"
		};
	}

	public static final String[] ruleNames = makeRuleNames();

	private static String[] makeLiteralNames() {
		return new String[]{
				null, "'('", "')'", "'{'", "'}'", "','", "':'", "'*'", "'/'", "'+'",
				"'-'", "'^'", null, "'>'", "'>='", null, "'<'", "'<='"
		};
	}

	private static final String[] _LITERAL_NAMES = makeLiteralNames();

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
				setState(26);
				statement(0);
				setState(27);
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

	public static class InPredicateContext extends StatementContext {
		public Token op;

		public List<StatementContext> statement() {
			return getRuleContexts(StatementContext.class);
		}

		public StatementContext statement(int i) {
			return getRuleContext(StatementContext.class, i);
		}

		public TerminalNode L_PARENTHESES() {
			return getToken(FormulaParser.L_PARENTHESES, 0);
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

		public List<TerminalNode> COMMA() {
			return getTokens(FormulaParser.COMMA);
		}

		public TerminalNode COMMA(int i) {
			return getToken(FormulaParser.COMMA, i);
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

	public static class LikePredicateContext extends StatementContext {
		public Token op;

		public StatementContext statement() {
			return getRuleContext(StatementContext.class, 0);
		}

		public TerminalNode STRING() {
			return getToken(FormulaParser.STRING, 0);
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
				setState(41);
				_errHandler.sync(this);
				switch (getInterpreter().adaptivePredict(_input, 0, _ctx)) {
					case 1: {
						_localctx = new IfExpressionContext(_localctx);
						_ctx = _localctx;
						_prevctx = _localctx;

						setState(30);
						ifSpecial();
					}
					break;
					case 2: {
						_localctx = new FunctionExpressionContext(_localctx);
						_ctx = _localctx;
						_prevctx = _localctx;
						setState(31);
						functionStatement();
					}
					break;
					case 3: {
						_localctx = new CaseExpressionContext(_localctx);
						_ctx = _localctx;
						_prevctx = _localctx;
						setState(32);
						caseStatement();
					}
					break;
					case 4: {
						_localctx = new ConstantExpressionContext(_localctx);
						_ctx = _localctx;
						_prevctx = _localctx;
						setState(33);
						constant();
					}
					break;
					case 5: {
						_localctx = new ColumnExpressionContext(_localctx);
						_ctx = _localctx;
						_prevctx = _localctx;
						setState(34);
						column();
					}
					break;
					case 6: {
						_localctx = new NotPredicateContext(_localctx);
						_ctx = _localctx;
						_prevctx = _localctx;
						setState(35);
						match(NOT);
						setState(36);
						statement(5);
					}
					break;
					case 7: {
						_localctx = new ParenthesesExpressionContext(_localctx);
						_ctx = _localctx;
						_prevctx = _localctx;
						setState(37);
						match(L_PARENTHESES);
						setState(38);
						statement(0);
						setState(39);
						match(R_PARENTHESES);
					}
					break;
				}
				_ctx.stop = _input.LT(-1);
				setState(91);
				_errHandler.sync(this);
				_alt = getInterpreter().adaptivePredict(_input, 5, _ctx);
				while (_alt != 2 && _alt != org.antlr.v4.runtime.atn.ATN.INVALID_ALT_NUMBER) {
					if (_alt == 1) {
						if (_parseListeners != null) triggerExitRuleEvent();
						_prevctx = _localctx;
						{
							setState(89);
							_errHandler.sync(this);
							switch (getInterpreter().adaptivePredict(_input, 4, _ctx)) {
								case 1: {
									_localctx = new MathExpressionContext(new StatementContext(_parentctx, _parentState));
									pushNewRecursionContext(_localctx, _startState, RULE_statement);
									setState(43);
									if (!(precpred(_ctx, 12)))
										throw new FailedPredicateException(this, "precpred(_ctx, 12)");
									setState(44);
									((MathExpressionContext) _localctx).op = match(POWER);
									setState(45);
									statement(12);
								}
								break;
								case 2: {
									_localctx = new MathExpressionContext(new StatementContext(_parentctx, _parentState));
									pushNewRecursionContext(_localctx, _startState, RULE_statement);
									setState(46);
									if (!(precpred(_ctx, 11)))
										throw new FailedPredicateException(this, "precpred(_ctx, 11)");
									setState(47);
									((MathExpressionContext) _localctx).op = match(MOD);
									setState(48);
									statement(12);
								}
								break;
								case 3: {
									_localctx = new MathExpressionContext(new StatementContext(_parentctx, _parentState));
									pushNewRecursionContext(_localctx, _startState, RULE_statement);
									setState(49);
									if (!(precpred(_ctx, 10)))
										throw new FailedPredicateException(this, "precpred(_ctx, 10)");
									setState(50);
									((MathExpressionContext) _localctx).op = _input.LT(1);
									_la = _input.LA(1);
									if (!(_la == MUL || _la == DIV)) {
										((MathExpressionContext) _localctx).op = (Token) _errHandler.recoverInline(this);
									} else {
										if (_input.LA(1) == Token.EOF) matchedEOF = true;
										_errHandler.reportMatch(this);
										consume();
									}
									setState(51);
									statement(11);
								}
								break;
								case 4: {
									_localctx = new MathExpressionContext(new StatementContext(_parentctx, _parentState));
									pushNewRecursionContext(_localctx, _startState, RULE_statement);
									setState(52);
									if (!(precpred(_ctx, 9)))
										throw new FailedPredicateException(this, "precpred(_ctx, 9)");
									setState(53);
									((MathExpressionContext) _localctx).op = _input.LT(1);
									_la = _input.LA(1);
									if (!(_la == PLUS || _la == MINUS)) {
										((MathExpressionContext) _localctx).op = (Token) _errHandler.recoverInline(this);
									} else {
										if (_input.LA(1) == Token.EOF) matchedEOF = true;
										_errHandler.reportMatch(this);
										consume();
									}
									setState(54);
									statement(10);
								}
								break;
								case 5: {
									_localctx = new ComparePredicateContext(new StatementContext(_parentctx, _parentState));
									pushNewRecursionContext(_localctx, _startState, RULE_statement);
									setState(55);
									if (!(precpred(_ctx, 8)))
										throw new FailedPredicateException(this, "precpred(_ctx, 8)");
									setState(56);
									((ComparePredicateContext) _localctx).op = _input.LT(1);
									_la = _input.LA(1);
									if (!((((_la) & ~0x3f) == 0 && ((1L << _la) & ((1L << GREATER) | (1L << GREATER_EQUAL) | (1L << EQUAL) | (1L << LESS) | (1L << LESS_EQUAL) | (1L << NOT_EQUAL))) != 0))) {
										((ComparePredicateContext) _localctx).op = (Token) _errHandler.recoverInline(this);
									} else {
										if (_input.LA(1) == Token.EOF) matchedEOF = true;
										_errHandler.reportMatch(this);
										consume();
									}
									setState(57);
									statement(9);
								}
								break;
								case 6: {
									_localctx = new LogicalPredicateContext(new StatementContext(_parentctx, _parentState));
									pushNewRecursionContext(_localctx, _startState, RULE_statement);
									setState(58);
									if (!(precpred(_ctx, 4)))
										throw new FailedPredicateException(this, "precpred(_ctx, 4)");
									setState(59);
									((LogicalPredicateContext) _localctx).op = match(AND);
									setState(60);
									statement(5);
								}
								break;
								case 7: {
									_localctx = new LogicalPredicateContext(new StatementContext(_parentctx, _parentState));
									pushNewRecursionContext(_localctx, _startState, RULE_statement);
									setState(61);
									if (!(precpred(_ctx, 3)))
										throw new FailedPredicateException(this, "precpred(_ctx, 3)");
									setState(62);
									((LogicalPredicateContext) _localctx).op = match(XOR);
									setState(63);
									statement(4);
								}
								break;
								case 8: {
									_localctx = new LogicalPredicateContext(new StatementContext(_parentctx, _parentState));
									pushNewRecursionContext(_localctx, _startState, RULE_statement);
									setState(64);
									if (!(precpred(_ctx, 2)))
										throw new FailedPredicateException(this, "precpred(_ctx, 2)");
									setState(65);
									((LogicalPredicateContext) _localctx).op = match(OR);
									setState(66);
									statement(3);
								}
								break;
								case 9: {
									_localctx = new InPredicateContext(new StatementContext(_parentctx, _parentState));
									pushNewRecursionContext(_localctx, _startState, RULE_statement);
									setState(67);
									if (!(precpred(_ctx, 7)))
										throw new FailedPredicateException(this, "precpred(_ctx, 7)");
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
									((InPredicateContext) _localctx).op = match(IN);
									setState(72);
									match(L_PARENTHESES);
									setState(73);
									statement(0);
									setState(78);
									_errHandler.sync(this);
									_la = _input.LA(1);
									while (_la == COMMA) {
										{
											{
												setState(74);
												match(COMMA);
												setState(75);
												statement(0);
											}
										}
										setState(80);
										_errHandler.sync(this);
										_la = _input.LA(1);
									}
									setState(81);
									match(R_PARENTHESES);
								}
								break;
								case 10: {
									_localctx = new LikePredicateContext(new StatementContext(_parentctx, _parentState));
									pushNewRecursionContext(_localctx, _startState, RULE_statement);
									setState(83);
									if (!(precpred(_ctx, 6)))
										throw new FailedPredicateException(this, "precpred(_ctx, 6)");
									setState(85);
									_errHandler.sync(this);
									_la = _input.LA(1);
									if (_la == NOT) {
										{
											setState(84);
											match(NOT);
										}
									}

									setState(87);
									((LikePredicateContext) _localctx).op = match(LIKE);
									setState(88);
									match(STRING);
								}
								break;
							}
						}
					}
					setState(93);
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

		public FunctionParamsContext functionParams() {
			return getRuleContext(FunctionParamsContext.class, 0);
		}

		public TerminalNode R_PARENTHESES() {
			return getToken(FormulaParser.R_PARENTHESES, 0);
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
		try {
			enterOuterAlt(_localctx, 1);
			{
				setState(94);
				match(IDENTITY);
				setState(95);
				match(L_PARENTHESES);
				setState(96);
				functionParams();
				setState(97);
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
				setState(99);
				statement(0);
				setState(104);
				_errHandler.sync(this);
				_la = _input.LA(1);
				while (_la == COMMA) {
					{
						{
							setState(100);
							match(COMMA);
							setState(101);
							statement(0);
						}
					}
					setState(106);
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
		enterRule(_localctx, 8, RULE_caseStatement);
		int _la;
		try {
			enterOuterAlt(_localctx, 1);
			{
				setState(107);
				match(CASE);
				setState(127);
				_errHandler.sync(this);
				switch (_input.LA(1)) {
					case WHEN: {
						setState(113);
						_errHandler.sync(this);
						_la = _input.LA(1);
						do {
							{
								{
									setState(108);
									match(WHEN);
									setState(109);
									predictStatement(0);
									setState(110);
									match(THEN);
									setState(111);
									((CaseStatementContext) _localctx).thenStmt = statement(0);
								}
							}
							setState(115);
							_errHandler.sync(this);
							_la = _input.LA(1);
						} while (_la == WHEN);
					}
					break;
					case L_PARENTHESES:
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
						setState(117);
						((CaseStatementContext) _localctx).caseStmt = statement(0);
						setState(123);
						_errHandler.sync(this);
						_la = _input.LA(1);
						do {
							{
								{
									setState(118);
									match(WHEN);
									setState(119);
									constant();
									setState(120);
									match(THEN);
									setState(121);
									((CaseStatementContext) _localctx).thenStmt = statement(0);
								}
							}
							setState(125);
							_errHandler.sync(this);
							_la = _input.LA(1);
						} while (_la == WHEN);
					}
					break;
					default:
						throw new NoViableAltException(this);
				}
				setState(131);
				_errHandler.sync(this);
				_la = _input.LA(1);
				if (_la == ELSE) {
					{
						setState(129);
						match(ELSE);
						setState(130);
						((CaseStatementContext) _localctx).elseStmt = statement(0);
					}
				}

				setState(133);
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
		enterRule(_localctx, 10, RULE_ifSpecial);
		try {
			setState(137);
			_errHandler.sync(this);
			switch (getInterpreter().adaptivePredict(_input, 11, _ctx)) {
				case 1:
					enterOuterAlt(_localctx, 1);
				{
					setState(135);
					ifFunction();
				}
				break;
				case 2:
					enterOuterAlt(_localctx, 2);
				{
					setState(136);
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
		enterRule(_localctx, 12, RULE_ifFunction);
		try {
			enterOuterAlt(_localctx, 1);
			{
				setState(139);
				match(IF);
				setState(140);
				match(L_PARENTHESES);
				setState(141);
				((IfFunctionContext) _localctx).predict = predictStatement(0);
				setState(142);
				match(COMMA);
				setState(143);
				((IfFunctionContext) _localctx).ifTrue = statement(0);
				setState(144);
				match(COMMA);
				setState(145);
				((IfFunctionContext) _localctx).ifFalse = statement(0);
				setState(146);
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
		enterRule(_localctx, 14, RULE_ifStatement);
		try {
			int _alt;
			enterOuterAlt(_localctx, 1);
			{
				setState(148);
				match(IF);
				setState(149);
				match(L_PARENTHESES);
				setState(150);
				((IfStatementContext) _localctx).predict = predictStatement(0);
				setState(151);
				match(R_PARENTHESES);
				setState(152);
				match(L_BRACE);
				setState(153);
				statement(0);
				setState(154);
				match(R_BRACE);
				setState(158);
				_errHandler.sync(this);
				_alt = getInterpreter().adaptivePredict(_input, 12, _ctx);
				while (_alt != 2 && _alt != org.antlr.v4.runtime.atn.ATN.INVALID_ALT_NUMBER) {
					if (_alt == 1) {
						{
							{
								setState(155);
								elseIfStatement();
							}
						}
					}
					setState(160);
					_errHandler.sync(this);
					_alt = getInterpreter().adaptivePredict(_input, 12, _ctx);
				}
				setState(162);
				_errHandler.sync(this);
				switch (getInterpreter().adaptivePredict(_input, 13, _ctx)) {
					case 1: {
						setState(161);
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
		enterRule(_localctx, 16, RULE_elseIfStatement);
		try {
			enterOuterAlt(_localctx, 1);
			{
				setState(164);
				match(ELSE);
				setState(165);
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
		enterRule(_localctx, 18, RULE_elseStatement);
		try {
			enterOuterAlt(_localctx, 1);
			{
				setState(167);
				match(ELSE);
				setState(168);
				match(L_BRACE);
				setState(169);
				statement(0);
				setState(170);
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
		int _startState = 20;
		enterRecursionRule(_localctx, 20, RULE_predictStatement, _p);
		try {
			int _alt;
			enterOuterAlt(_localctx, 1);
			{
				setState(178);
				_errHandler.sync(this);
				switch (getInterpreter().adaptivePredict(_input, 14, _ctx)) {
					case 1: {
						setState(173);
						statement(0);
					}
					break;
					case 2: {
						setState(174);
						match(L_PARENTHESES);
						setState(175);
						predictStatement(0);
						setState(176);
						match(R_PARENTHESES);
					}
					break;
				}
				_ctx.stop = _input.LT(-1);
				setState(191);
				_errHandler.sync(this);
				_alt = getInterpreter().adaptivePredict(_input, 16, _ctx);
				while (_alt != 2 && _alt != org.antlr.v4.runtime.atn.ATN.INVALID_ALT_NUMBER) {
					if (_alt == 1) {
						if (_parseListeners != null) triggerExitRuleEvent();
						_prevctx = _localctx;
						{
							setState(189);
							_errHandler.sync(this);
							switch (getInterpreter().adaptivePredict(_input, 15, _ctx)) {
								case 1: {
									_localctx = new PredictStatementContext(_parentctx, _parentState);
									pushNewRecursionContext(_localctx, _startState, RULE_predictStatement);
									setState(180);
									if (!(precpred(_ctx, 3)))
										throw new FailedPredicateException(this, "precpred(_ctx, 3)");
									setState(181);
									((PredictStatementContext) _localctx).op = match(AND);
									setState(182);
									predictStatement(4);
								}
								break;
								case 2: {
									_localctx = new PredictStatementContext(_parentctx, _parentState);
									pushNewRecursionContext(_localctx, _startState, RULE_predictStatement);
									setState(183);
									if (!(precpred(_ctx, 2)))
										throw new FailedPredicateException(this, "precpred(_ctx, 2)");
									setState(184);
									((PredictStatementContext) _localctx).op = match(XOR);
									setState(185);
									predictStatement(3);
								}
								break;
								case 3: {
									_localctx = new PredictStatementContext(_parentctx, _parentState);
									pushNewRecursionContext(_localctx, _startState, RULE_predictStatement);
									setState(186);
									if (!(precpred(_ctx, 1)))
										throw new FailedPredicateException(this, "precpred(_ctx, 1)");
									setState(187);
									((PredictStatementContext) _localctx).op = match(OR);
									setState(188);
									predictStatement(2);
								}
								break;
							}
						}
					}
					setState(193);
					_errHandler.sync(this);
					_alt = getInterpreter().adaptivePredict(_input, 16, _ctx);
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
		enterRule(_localctx, 22, RULE_constant);
		int _la;
		try {
			setState(201);
			_errHandler.sync(this);
			switch (_input.LA(1)) {
				case STRING:
					enterOuterAlt(_localctx, 1);
				{
					setState(194);
					match(STRING);
				}
				break;
				case PLUS:
				case MINUS:
				case INTEGER:
				case FLOAT:
					enterOuterAlt(_localctx, 2);
				{
					setState(196);
					_errHandler.sync(this);
					_la = _input.LA(1);
					if (_la == PLUS || _la == MINUS) {
						{
							setState(195);
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

					setState(198);
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
					setState(199);
					match(BOOL);
				}
				break;
				case NULL:
					enterOuterAlt(_localctx, 4);
				{
					setState(200);
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
		enterRule(_localctx, 24, RULE_column);
		try {
			setState(206);
			_errHandler.sync(this);
			switch (_input.LA(1)) {
				case COLUMN_ID:
					_localctx = new ColumnIdContext(_localctx);
					enterOuterAlt(_localctx, 1);
				{
					setState(203);
					match(COLUMN_ID);
				}
				break;
				case COLUMN_NAME:
					_localctx = new ColumnNameContext(_localctx);
					enterOuterAlt(_localctx, 2);
				{
					setState(204);
					match(COLUMN_NAME);
				}
				break;
				case IDENTITY:
					_localctx = new IdentityContext(_localctx);
					enterOuterAlt(_localctx, 3);
				{
					setState(205);
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
			case 10:
				return predictStatement_sempred((PredictStatementContext) _localctx, predIndex);
		}
		return true;
	}
	private boolean statement_sempred(StatementContext _localctx, int predIndex) {
		switch (predIndex) {
			case 0:
				return precpred(_ctx, 12);
			case 1:
				return precpred(_ctx, 11);
			case 2:
				return precpred(_ctx, 10);
			case 3:
				return precpred(_ctx, 9);
			case 4:
				return precpred(_ctx, 8);
			case 5:
				return precpred(_ctx, 4);
			case 6:
				return precpred(_ctx, 3);
			case 7:
				return precpred(_ctx, 2);
			case 8:
				return precpred(_ctx, 7);
			case 9:
				return precpred(_ctx, 6);
		}
		return true;
	}
	private boolean predictStatement_sempred(PredictStatementContext _localctx, int predIndex) {
		switch (predIndex) {
			case 10:
				return precpred(_ctx, 3);
			case 11:
				return precpred(_ctx, 2);
			case 12:
				return precpred(_ctx, 1);
		}
		return true;
	}

	public static final String _serializedATN =
			"\3\u608b\ua72a\u8133\ub9ed\u417c\u3be7\u7786\u5964\3+\u00d3\4\2\t\2\4" +
					"\3\t\3\4\4\t\4\4\5\t\5\4\6\t\6\4\7\t\7\4\b\t\b\4\t\t\t\4\n\t\n\4\13\t" +
					"\13\4\f\t\f\4\r\t\r\4\16\t\16\3\2\3\2\3\2\3\3\3\3\3\3\3\3\3\3\3\3\3\3" +
					"\3\3\3\3\3\3\3\3\3\3\5\3,\n\3\3\3\3\3\3\3\3\3\3\3\3\3\3\3\3\3\3\3\3\3" +
					"\3\3\3\3\3\3\3\3\3\3\3\3\3\3\3\3\3\3\3\3\3\3\3\3\3\3\3\3\3\3\3\3\5\3H" +
					"\n\3\3\3\3\3\3\3\3\3\3\3\7\3O\n\3\f\3\16\3R\13\3\3\3\3\3\3\3\3\3\5\3X" +
					"\n\3\3\3\3\3\7\3\\\n\3\f\3\16\3_\13\3\3\4\3\4\3\4\3\4\3\4\3\5\3\5\3\5" +
					"\7\5i\n\5\f\5\16\5l\13\5\3\6\3\6\3\6\3\6\3\6\3\6\6\6t\n\6\r\6\16\6u\3" +
					"\6\3\6\3\6\3\6\3\6\3\6\6\6~\n\6\r\6\16\6\177\5\6\u0082\n\6\3\6\3\6\5\6" +
					"\u0086\n\6\3\6\3\6\3\7\3\7\5\7\u008c\n\7\3\b\3\b\3\b\3\b\3\b\3\b\3\b\3" +
					"\b\3\b\3\t\3\t\3\t\3\t\3\t\3\t\3\t\3\t\7\t\u009f\n\t\f\t\16\t\u00a2\13" +
					"\t\3\t\5\t\u00a5\n\t\3\n\3\n\3\n\3\13\3\13\3\13\3\13\3\13\3\f\3\f\3\f" +
					"\3\f\3\f\3\f\5\f\u00b5\n\f\3\f\3\f\3\f\3\f\3\f\3\f\3\f\3\f\3\f\7\f\u00c0" +
					"\n\f\f\f\16\f\u00c3\13\f\3\r\3\r\5\r\u00c7\n\r\3\r\3\r\3\r\5\r\u00cc\n" +
					"\r\3\16\3\16\3\16\5\16\u00d1\n\16\3\16\2\4\4\26\17\2\4\6\b\n\f\16\20\22" +
					"\24\26\30\32\2\6\3\2\t\n\3\2\13\f\3\2\17\24\3\2$%\2\u00ea\2\34\3\2\2\2" +
					"\4+\3\2\2\2\6`\3\2\2\2\be\3\2\2\2\nm\3\2\2\2\f\u008b\3\2\2\2\16\u008d" +
					"\3\2\2\2\20\u0096\3\2\2\2\22\u00a6\3\2\2\2\24\u00a9\3\2\2\2\26\u00b4\3" +
					"\2\2\2\30\u00cb\3\2\2\2\32\u00d0\3\2\2\2\34\35\5\4\3\2\35\36\7\2\2\3\36" +
					"\3\3\2\2\2\37 \b\3\1\2 ,\5\f\7\2!,\5\6\4\2\",\5\n\6\2#,\5\30\r\2$,\5\32" +
					"\16\2%&\7\30\2\2&,\5\4\3\7\'(\7\3\2\2()\5\4\3\2)*\7\4\2\2*,\3\2\2\2+\37" +
					"\3\2\2\2+!\3\2\2\2+\"\3\2\2\2+#\3\2\2\2+$\3\2\2\2+%\3\2\2\2+\'\3\2\2\2" +
					",]\3\2\2\2-.\f\16\2\2./\7\r\2\2/\\\5\4\3\16\60\61\f\r\2\2\61\62\7\16\2" +
					"\2\62\\\5\4\3\16\63\64\f\f\2\2\64\65\t\2\2\2\65\\\5\4\3\r\66\67\f\13\2" +
					"\2\678\t\3\2\28\\\5\4\3\f9:\f\n\2\2:;\t\4\2\2;\\\5\4\3\13<=\f\6\2\2=>" +
					"\7\31\2\2>\\\5\4\3\7?@\f\5\2\2@A\7\33\2\2A\\\5\4\3\6BC\f\4\2\2CD\7\32" +
					"\2\2D\\\5\4\3\5EG\f\t\2\2FH\7\30\2\2GF\3\2\2\2GH\3\2\2\2HI\3\2\2\2IJ\7" +
					"\27\2\2JK\7\3\2\2KP\5\4\3\2LM\7\7\2\2MO\5\4\3\2NL\3\2\2\2OR\3\2\2\2PN" +
					"\3\2\2\2PQ\3\2\2\2QS\3\2\2\2RP\3\2\2\2ST\7\4\2\2T\\\3\2\2\2UW\f\b\2\2" +
					"VX\7\30\2\2WV\3\2\2\2WX\3\2\2\2XY\3\2\2\2YZ\7\26\2\2Z\\\7#\2\2[-\3\2\2" +
					"\2[\60\3\2\2\2[\63\3\2\2\2[\66\3\2\2\2[9\3\2\2\2[<\3\2\2\2[?\3\2\2\2[" +
					"B\3\2\2\2[E\3\2\2\2[U\3\2\2\2\\_\3\2\2\2][\3\2\2\2]^\3\2\2\2^\5\3\2\2" +
					"\2_]\3\2\2\2`a\7\'\2\2ab\7\3\2\2bc\5\b\5\2cd\7\4\2\2d\7\3\2\2\2ej\5\4" +
					"\3\2fg\7\7\2\2gi\5\4\3\2hf\3\2\2\2il\3\2\2\2jh\3\2\2\2jk\3\2\2\2k\t\3" +
					"\2\2\2lj\3\2\2\2m\u0081\7\34\2\2no\7\35\2\2op\5\26\f\2pq\7\36\2\2qr\5" +
					"\4\3\2rt\3\2\2\2sn\3\2\2\2tu\3\2\2\2us\3\2\2\2uv\3\2\2\2v\u0082\3\2\2" +
					"\2w}\5\4\3\2xy\7\35\2\2yz\5\30\r\2z{\7\36\2\2{|\5\4\3\2|~\3\2\2\2}x\3" +
					"\2\2\2~\177\3\2\2\2\177}\3\2\2\2\177\u0080\3\2\2\2\u0080\u0082\3\2\2\2" +
					"\u0081s\3\2\2\2\u0081w\3\2\2\2\u0082\u0085\3\2\2\2\u0083\u0084\7\37\2" +
					"\2\u0084\u0086\5\4\3\2\u0085\u0083\3\2\2\2\u0085\u0086\3\2\2\2\u0086\u0087" +
					"\3\2\2\2\u0087\u0088\7 \2\2\u0088\13\3\2\2\2\u0089\u008c\5\16\b\2\u008a" +
					"\u008c\5\20\t\2\u008b\u0089\3\2\2\2\u008b\u008a\3\2\2\2\u008c\r\3\2\2" +
					"\2\u008d\u008e\7\25\2\2\u008e\u008f\7\3\2\2\u008f\u0090\5\26\f\2\u0090" +
					"\u0091\7\7\2\2\u0091\u0092\5\4\3\2\u0092\u0093\7\7\2\2\u0093\u0094\5\4" +
					"\3\2\u0094\u0095\7\4\2\2\u0095\17\3\2\2\2\u0096\u0097\7\25\2\2\u0097\u0098" +
					"\7\3\2\2\u0098\u0099\5\26\f\2\u0099\u009a\7\4\2\2\u009a\u009b\7\5\2\2" +
					"\u009b\u009c\5\4\3\2\u009c\u00a0\7\6\2\2\u009d\u009f\5\22\n\2\u009e\u009d" +
					"\3\2\2\2\u009f\u00a2\3\2\2\2\u00a0\u009e\3\2\2\2\u00a0\u00a1\3\2\2\2\u00a1" +
					"\u00a4\3\2\2\2\u00a2\u00a0\3\2\2\2\u00a3\u00a5\5\24\13\2\u00a4\u00a3\3" +
					"\2\2\2\u00a4\u00a5\3\2\2\2\u00a5\21\3\2\2\2\u00a6\u00a7\7\37\2\2\u00a7" +
					"\u00a8\5\20\t\2\u00a8\23\3\2\2\2\u00a9\u00aa\7\37\2\2\u00aa\u00ab\7\5" +
					"\2\2\u00ab\u00ac\5\4\3\2\u00ac\u00ad\7\6\2\2\u00ad\25\3\2\2\2\u00ae\u00af" +
					"\b\f\1\2\u00af\u00b5\5\4\3\2\u00b0\u00b1\7\3\2\2\u00b1\u00b2\5\26\f\2" +
					"\u00b2\u00b3\7\4\2\2\u00b3\u00b5\3\2\2\2\u00b4\u00ae\3\2\2\2\u00b4\u00b0" +
					"\3\2\2\2\u00b5\u00c1\3\2\2\2\u00b6\u00b7\f\5\2\2\u00b7\u00b8\7\31\2\2" +
					"\u00b8\u00c0\5\26\f\6\u00b9\u00ba\f\4\2\2\u00ba\u00bb\7\33\2\2\u00bb\u00c0" +
					"\5\26\f\5\u00bc\u00bd\f\3\2\2\u00bd\u00be\7\32\2\2\u00be\u00c0\5\26\f" +
					"\4\u00bf\u00b6\3\2\2\2\u00bf\u00b9\3\2\2\2\u00bf\u00bc\3\2\2\2\u00c0\u00c3" +
					"\3\2\2\2\u00c1\u00bf\3\2\2\2\u00c1\u00c2\3\2\2\2\u00c2\27\3\2\2\2\u00c3" +
					"\u00c1\3\2\2\2\u00c4\u00cc\7#\2\2\u00c5\u00c7\t\3\2\2\u00c6\u00c5\3\2" +
					"\2\2\u00c6\u00c7\3\2\2\2\u00c7\u00c8\3\2\2\2\u00c8\u00cc\t\5\2\2\u00c9" +
					"\u00cc\7!\2\2\u00ca\u00cc\7\"\2\2\u00cb\u00c4\3\2\2\2\u00cb\u00c6\3\2" +
					"\2\2\u00cb\u00c9\3\2\2\2\u00cb\u00ca\3\2\2\2\u00cc\31\3\2\2\2\u00cd\u00d1" +
					"\7&\2\2\u00ce\u00d1\7(\2\2\u00cf\u00d1\7\'\2\2\u00d0\u00cd\3\2\2\2\u00d0" +
					"\u00ce\3\2\2\2\u00d0\u00cf\3\2\2\2\u00d1\33\3\2\2\2\26+GPW[]ju\177\u0081" +
					"\u0085\u008b\u00a0\u00a4\u00b4\u00bf\u00c1\u00c6\u00cb\u00d0";
	public static final ATN _ATN =
			new ATNDeserializer().deserialize(_serializedATN.toCharArray());

	static {
		_decisionToDFA = new DFA[_ATN.getNumberOfDecisions()];
		for (int i = 0; i < _ATN.getNumberOfDecisions(); i++) {
			_decisionToDFA[i] = new DFA(_ATN.getDecisionState(i), i);
		}
	}
}