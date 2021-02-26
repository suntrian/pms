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
			GREATER = 15, GREATER_EQUAL = 16, EQUAL = 17, LESS = 18, LESS_EQUAL = 19, NOT_EQUAL = 20,
			IF = 21, LIKE = 22, IN = 23, NOT = 24, AND = 25, OR = 26, XOR = 27, CASE = 28, WHEN = 29,
			THEN = 30, ELSE = 31, END = 32, BOOL = 33, NULL = 34, STRING = 35, INTEGER = 36, FLOAT = 37,
			COLUMN_ID = 38, IDENTITY = 39, COLUMN_NAME = 40, WS = 41, LINE_COMMENT = 42, BLOCK_COMMENT = 43;
	public static final int
			RULE_formula = 0, RULE_statement = 1, RULE_functionStatement = 2, RULE_functionParams = 3,
			RULE_functionParam = 4, RULE_caseStatement = 5, RULE_ifSpecial = 6, RULE_ifFunction = 7,
			RULE_ifStatement = 8, RULE_elseIfStatement = 9, RULE_elseStatement = 10,
			RULE_predictStatement = 11, RULE_constant = 12, RULE_column = 13;

	private static String[] makeRuleNames() {
		return new String[]{
				"formula", "statement", "functionStatement", "functionParams", "functionParam",
				"caseStatement", "ifSpecial", "ifFunction", "ifStatement", "elseIfStatement",
				"elseStatement", "predictStatement", "constant", "column"
		};
	}

	public static final String[] ruleNames = makeRuleNames();

	private static String[] makeLiteralNames() {
		return new String[]{
				null, "'('", "')'", "'{'", "'}'", "'['", "']'", "','", "':'", "'*'",
				"'/'", "'+'", "'-'", "'^'", null, "'>'", "'>='", null, "'<'", "'<='"
		};
	}
	private static final String[] _LITERAL_NAMES = makeLiteralNames();
	private static String[] makeSymbolicNames() {
		return new String[]{
				null, "L_PARENTHESES", "R_PARENTHESES", "L_BRACE", "R_BRACE", "L_SQUARE",
				"R_SQUARE", "COMMA", "COLON", "MUL", "DIV", "PLUS", "MINUS", "POWER",
				"MOD", "GREATER", "GREATER_EQUAL", "EQUAL", "LESS", "LESS_EQUAL", "NOT_EQUAL",
				"IF", "LIKE", "IN", "NOT", "AND", "OR", "XOR", "CASE", "WHEN", "THEN",
				"ELSE", "END", "BOOL", "NULL", "STRING", "INTEGER", "FLOAT", "COLUMN_ID",
				"IDENTITY", "COLUMN_NAME", "WS", "LINE_COMMENT", "BLOCK_COMMENT"
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
				setState(28);
				statement(0);
				setState(29);
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
				setState(55);
				_errHandler.sync(this);
				switch (getInterpreter().adaptivePredict(_input, 2, _ctx)) {
					case 1: {
						_localctx = new IfExpressionContext(_localctx);
						_ctx = _localctx;
						_prevctx = _localctx;

						setState(32);
						ifSpecial();
					}
					break;
					case 2: {
						_localctx = new FunctionExpressionContext(_localctx);
						_ctx = _localctx;
						_prevctx = _localctx;
						setState(33);
						functionStatement();
					}
					break;
					case 3: {
						_localctx = new CaseExpressionContext(_localctx);
						_ctx = _localctx;
						_prevctx = _localctx;
						setState(34);
						caseStatement();
					}
					break;
					case 4: {
						_localctx = new ConstantExpressionContext(_localctx);
						_ctx = _localctx;
						_prevctx = _localctx;
						setState(35);
						constant();
					}
					break;
					case 5: {
						_localctx = new ColumnExpressionContext(_localctx);
						_ctx = _localctx;
						_prevctx = _localctx;
						setState(36);
						column();
					}
					break;
					case 6: {
						_localctx = new NotPredicateContext(_localctx);
						_ctx = _localctx;
						_prevctx = _localctx;
						setState(37);
						match(NOT);
						setState(38);
						statement(6);
					}
					break;
					case 7: {
						_localctx = new ParenthesesExpressionContext(_localctx);
						_ctx = _localctx;
						_prevctx = _localctx;
						setState(39);
						match(L_PARENTHESES);
						setState(40);
						statement(0);
						setState(41);
						match(R_PARENTHESES);
					}
					break;
					case 8: {
						_localctx = new SquareExpressionContext(_localctx);
						_ctx = _localctx;
						_prevctx = _localctx;
						setState(43);
						match(L_SQUARE);
						setState(52);
						_errHandler.sync(this);
						_la = _input.LA(1);
						if ((((_la) & ~0x3f) == 0 && ((1L << _la) & ((1L << L_PARENTHESES) | (1L << L_SQUARE) | (1L << PLUS) | (1L << MINUS) | (1L << IF) | (1L << NOT) | (1L << CASE) | (1L << BOOL) | (1L << NULL) | (1L << STRING) | (1L << INTEGER) | (1L << FLOAT) | (1L << COLUMN_ID) | (1L << IDENTITY) | (1L << COLUMN_NAME))) != 0)) {
							{
								setState(44);
								statement(0);
								setState(49);
								_errHandler.sync(this);
								_la = _input.LA(1);
								while (_la == COMMA) {
									{
										{
											setState(45);
											match(COMMA);
											setState(46);
											statement(0);
										}
									}
									setState(51);
									_errHandler.sync(this);
									_la = _input.LA(1);
								}
							}
						}

						setState(54);
						match(R_SQUARE);
					}
					break;
				}
				_ctx.stop = _input.LT(-1);
				setState(105);
				_errHandler.sync(this);
				_alt = getInterpreter().adaptivePredict(_input, 7, _ctx);
				while (_alt != 2 && _alt != ATN.INVALID_ALT_NUMBER) {
					if (_alt == 1) {
						if (_parseListeners != null) triggerExitRuleEvent();
						_prevctx = _localctx;
						{
							setState(103);
							_errHandler.sync(this);
							switch (getInterpreter().adaptivePredict(_input, 6, _ctx)) {
								case 1: {
									_localctx = new MathExpressionContext(new StatementContext(_parentctx, _parentState));
									pushNewRecursionContext(_localctx, _startState, RULE_statement);
									setState(57);
									if (!(precpred(_ctx, 13)))
										throw new FailedPredicateException(this, "precpred(_ctx, 13)");
									setState(58);
									((MathExpressionContext) _localctx).op = match(POWER);
									setState(59);
									statement(13);
								}
								break;
								case 2: {
									_localctx = new MathExpressionContext(new StatementContext(_parentctx, _parentState));
									pushNewRecursionContext(_localctx, _startState, RULE_statement);
									setState(60);
									if (!(precpred(_ctx, 12)))
										throw new FailedPredicateException(this, "precpred(_ctx, 12)");
									setState(61);
									((MathExpressionContext) _localctx).op = match(MOD);
									setState(62);
									statement(13);
								}
								break;
								case 3: {
									_localctx = new MathExpressionContext(new StatementContext(_parentctx, _parentState));
									pushNewRecursionContext(_localctx, _startState, RULE_statement);
									setState(63);
									if (!(precpred(_ctx, 11)))
										throw new FailedPredicateException(this, "precpred(_ctx, 11)");
									setState(64);
									((MathExpressionContext) _localctx).op = _input.LT(1);
									_la = _input.LA(1);
									if (!(_la == MUL || _la == DIV)) {
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
								case 4: {
									_localctx = new MathExpressionContext(new StatementContext(_parentctx, _parentState));
									pushNewRecursionContext(_localctx, _startState, RULE_statement);
									setState(66);
									if (!(precpred(_ctx, 10)))
										throw new FailedPredicateException(this, "precpred(_ctx, 10)");
									setState(67);
									((MathExpressionContext) _localctx).op = _input.LT(1);
									_la = _input.LA(1);
									if (!(_la == PLUS || _la == MINUS)) {
										((MathExpressionContext) _localctx).op = (Token) _errHandler.recoverInline(this);
									} else {
										if (_input.LA(1) == Token.EOF) matchedEOF = true;
										_errHandler.reportMatch(this);
										consume();
									}
									setState(68);
									statement(11);
								}
								break;
								case 5: {
									_localctx = new ComparePredicateContext(new StatementContext(_parentctx, _parentState));
									pushNewRecursionContext(_localctx, _startState, RULE_statement);
									setState(69);
									if (!(precpred(_ctx, 9)))
										throw new FailedPredicateException(this, "precpred(_ctx, 9)");
									setState(70);
									((ComparePredicateContext) _localctx).op = _input.LT(1);
									_la = _input.LA(1);
									if (!((((_la) & ~0x3f) == 0 && ((1L << _la) & ((1L << GREATER) | (1L << GREATER_EQUAL) | (1L << EQUAL) | (1L << LESS) | (1L << LESS_EQUAL) | (1L << NOT_EQUAL))) != 0))) {
										((ComparePredicateContext) _localctx).op = (Token) _errHandler.recoverInline(this);
									} else {
										if (_input.LA(1) == Token.EOF) matchedEOF = true;
										_errHandler.reportMatch(this);
										consume();
									}
									setState(71);
									statement(10);
								}
								break;
								case 6: {
									_localctx = new LogicalPredicateContext(new StatementContext(_parentctx, _parentState));
									pushNewRecursionContext(_localctx, _startState, RULE_statement);
									setState(72);
									if (!(precpred(_ctx, 5)))
										throw new FailedPredicateException(this, "precpred(_ctx, 5)");
									setState(73);
									((LogicalPredicateContext) _localctx).op = match(AND);
									setState(74);
									statement(6);
								}
								break;
								case 7: {
									_localctx = new LogicalPredicateContext(new StatementContext(_parentctx, _parentState));
									pushNewRecursionContext(_localctx, _startState, RULE_statement);
									setState(75);
									if (!(precpred(_ctx, 4)))
										throw new FailedPredicateException(this, "precpred(_ctx, 4)");
									setState(76);
									((LogicalPredicateContext) _localctx).op = match(XOR);
									setState(77);
									statement(5);
								}
								break;
								case 8: {
									_localctx = new LogicalPredicateContext(new StatementContext(_parentctx, _parentState));
									pushNewRecursionContext(_localctx, _startState, RULE_statement);
									setState(78);
									if (!(precpred(_ctx, 3)))
										throw new FailedPredicateException(this, "precpred(_ctx, 3)");
									setState(79);
									((LogicalPredicateContext) _localctx).op = match(OR);
									setState(80);
									statement(4);
								}
								break;
								case 9: {
									_localctx = new InPredicateContext(new StatementContext(_parentctx, _parentState));
									pushNewRecursionContext(_localctx, _startState, RULE_statement);
									setState(81);
									if (!(precpred(_ctx, 8)))
										throw new FailedPredicateException(this, "precpred(_ctx, 8)");
									setState(83);
									_errHandler.sync(this);
									_la = _input.LA(1);
									if (_la == NOT) {
										{
											setState(82);
											match(NOT);
										}
									}

									setState(85);
									((InPredicateContext) _localctx).op = match(IN);
									setState(86);
									match(L_PARENTHESES);
									setState(87);
									statement(0);
									setState(92);
									_errHandler.sync(this);
									_la = _input.LA(1);
									while (_la == COMMA) {
										{
											{
												setState(88);
												match(COMMA);
												setState(89);
												statement(0);
											}
										}
										setState(94);
										_errHandler.sync(this);
										_la = _input.LA(1);
									}
									setState(95);
									match(R_PARENTHESES);
								}
								break;
								case 10: {
									_localctx = new LikePredicateContext(new StatementContext(_parentctx, _parentState));
									pushNewRecursionContext(_localctx, _startState, RULE_statement);
									setState(97);
									if (!(precpred(_ctx, 7)))
										throw new FailedPredicateException(this, "precpred(_ctx, 7)");
									setState(99);
									_errHandler.sync(this);
									_la = _input.LA(1);
									if (_la == NOT) {
										{
											setState(98);
											match(NOT);
										}
									}

									setState(101);
									((LikePredicateContext) _localctx).op = match(LIKE);
									setState(102);
									match(STRING);
								}
								break;
							}
						}
					}
					setState(107);
					_errHandler.sync(this);
					_alt = getInterpreter().adaptivePredict(_input, 7, _ctx);
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
				setState(108);
				match(IDENTITY);
				setState(109);
				match(L_PARENTHESES);
				setState(111);
				_errHandler.sync(this);
				_la = _input.LA(1);
				if ((((_la) & ~0x3f) == 0 && ((1L << _la) & ((1L << L_PARENTHESES) | (1L << L_SQUARE) | (1L << PLUS) | (1L << MINUS) | (1L << IF) | (1L << NOT) | (1L << CASE) | (1L << BOOL) | (1L << NULL) | (1L << STRING) | (1L << INTEGER) | (1L << FLOAT) | (1L << COLUMN_ID) | (1L << IDENTITY) | (1L << COLUMN_NAME))) != 0)) {
					{
						setState(110);
						functionParams();
					}
				}

				setState(113);
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
				setState(115);
				functionParam();
				setState(120);
				_errHandler.sync(this);
				_la = _input.LA(1);
				while (_la == COMMA) {
					{
						{
							setState(116);
							match(COMMA);
							setState(117);
							functionParam();
						}
					}
					setState(122);
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
			setState(127);
			_errHandler.sync(this);
			switch (getInterpreter().adaptivePredict(_input, 10, _ctx)) {
				case 1:
					enterOuterAlt(_localctx, 1);
				{
					setState(123);
					statement(0);
				}
				break;
				case 2:
					enterOuterAlt(_localctx, 2);
				{
					setState(124);
					match(IDENTITY);
					setState(125);
					match(EQUAL);
					setState(126);
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
		enterRule(_localctx, 10, RULE_caseStatement);
		int _la;
		try {
			enterOuterAlt(_localctx, 1);
			{
				setState(129);
				match(CASE);
				setState(149);
				_errHandler.sync(this);
				switch (_input.LA(1)) {
					case WHEN: {
						setState(135);
						_errHandler.sync(this);
						_la = _input.LA(1);
						do {
							{
								{
									setState(130);
									match(WHEN);
									setState(131);
									predictStatement(0);
									setState(132);
									match(THEN);
									setState(133);
									((CaseStatementContext) _localctx).thenStmt = statement(0);
								}
							}
							setState(137);
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
						setState(139);
						((CaseStatementContext) _localctx).caseStmt = statement(0);
						setState(145);
						_errHandler.sync(this);
						_la = _input.LA(1);
						do {
							{
								{
									setState(140);
									match(WHEN);
									setState(141);
									constant();
									setState(142);
									match(THEN);
									setState(143);
									((CaseStatementContext) _localctx).thenStmt = statement(0);
								}
							}
							setState(147);
							_errHandler.sync(this);
							_la = _input.LA(1);
						} while (_la == WHEN);
					}
					break;
					default:
						throw new NoViableAltException(this);
				}
				setState(153);
				_errHandler.sync(this);
				_la = _input.LA(1);
				if (_la == ELSE) {
					{
						setState(151);
						match(ELSE);
						setState(152);
						((CaseStatementContext) _localctx).elseStmt = statement(0);
					}
				}

				setState(155);
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
		enterRule(_localctx, 12, RULE_ifSpecial);
		try {
			setState(159);
			_errHandler.sync(this);
			switch (getInterpreter().adaptivePredict(_input, 15, _ctx)) {
				case 1:
					enterOuterAlt(_localctx, 1);
				{
					setState(157);
					ifFunction();
				}
				break;
				case 2:
					enterOuterAlt(_localctx, 2);
				{
					setState(158);
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
		enterRule(_localctx, 14, RULE_ifFunction);
		try {
			enterOuterAlt(_localctx, 1);
			{
				setState(161);
				match(IF);
				setState(162);
				match(L_PARENTHESES);
				setState(163);
				((IfFunctionContext) _localctx).predict = predictStatement(0);
				setState(164);
				match(COMMA);
				setState(165);
				((IfFunctionContext) _localctx).ifTrue = statement(0);
				setState(166);
				match(COMMA);
				setState(167);
				((IfFunctionContext) _localctx).ifFalse = statement(0);
				setState(168);
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
		enterRule(_localctx, 16, RULE_ifStatement);
		try {
			int _alt;
			enterOuterAlt(_localctx, 1);
			{
				setState(170);
				match(IF);
				setState(171);
				match(L_PARENTHESES);
				setState(172);
				((IfStatementContext) _localctx).predict = predictStatement(0);
				setState(173);
				match(R_PARENTHESES);
				setState(174);
				match(L_BRACE);
				setState(175);
				statement(0);
				setState(176);
				match(R_BRACE);
				setState(180);
				_errHandler.sync(this);
				_alt = getInterpreter().adaptivePredict(_input, 16, _ctx);
				while (_alt != 2 && _alt != ATN.INVALID_ALT_NUMBER) {
					if (_alt == 1) {
						{
							{
								setState(177);
								elseIfStatement();
							}
						}
					}
					setState(182);
					_errHandler.sync(this);
					_alt = getInterpreter().adaptivePredict(_input, 16, _ctx);
				}
				setState(184);
				_errHandler.sync(this);
				switch (getInterpreter().adaptivePredict(_input, 17, _ctx)) {
					case 1: {
						setState(183);
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
		enterRule(_localctx, 18, RULE_elseIfStatement);
		try {
			enterOuterAlt(_localctx, 1);
			{
				setState(186);
				match(ELSE);
				setState(187);
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
		enterRule(_localctx, 20, RULE_elseStatement);
		try {
			enterOuterAlt(_localctx, 1);
			{
				setState(189);
				match(ELSE);
				setState(190);
				match(L_BRACE);
				setState(191);
				statement(0);
				setState(192);
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
		int _startState = 22;
		enterRecursionRule(_localctx, 22, RULE_predictStatement, _p);
		try {
			int _alt;
			enterOuterAlt(_localctx, 1);
			{
				setState(200);
				_errHandler.sync(this);
				switch (getInterpreter().adaptivePredict(_input, 18, _ctx)) {
					case 1: {
						setState(195);
						statement(0);
					}
					break;
					case 2: {
						setState(196);
						match(L_PARENTHESES);
						setState(197);
						predictStatement(0);
						setState(198);
						match(R_PARENTHESES);
					}
					break;
				}
				_ctx.stop = _input.LT(-1);
				setState(213);
				_errHandler.sync(this);
				_alt = getInterpreter().adaptivePredict(_input, 20, _ctx);
				while (_alt != 2 && _alt != ATN.INVALID_ALT_NUMBER) {
					if (_alt == 1) {
						if (_parseListeners != null) triggerExitRuleEvent();
						_prevctx = _localctx;
						{
							setState(211);
							_errHandler.sync(this);
							switch (getInterpreter().adaptivePredict(_input, 19, _ctx)) {
								case 1: {
									_localctx = new PredictStatementContext(_parentctx, _parentState);
									pushNewRecursionContext(_localctx, _startState, RULE_predictStatement);
									setState(202);
									if (!(precpred(_ctx, 3)))
										throw new FailedPredicateException(this, "precpred(_ctx, 3)");
									setState(203);
									((PredictStatementContext) _localctx).op = match(AND);
									setState(204);
									predictStatement(4);
								}
								break;
								case 2: {
									_localctx = new PredictStatementContext(_parentctx, _parentState);
									pushNewRecursionContext(_localctx, _startState, RULE_predictStatement);
									setState(205);
									if (!(precpred(_ctx, 2)))
										throw new FailedPredicateException(this, "precpred(_ctx, 2)");
									setState(206);
									((PredictStatementContext) _localctx).op = match(XOR);
									setState(207);
									predictStatement(3);
								}
								break;
								case 3: {
									_localctx = new PredictStatementContext(_parentctx, _parentState);
									pushNewRecursionContext(_localctx, _startState, RULE_predictStatement);
									setState(208);
									if (!(precpred(_ctx, 1)))
										throw new FailedPredicateException(this, "precpred(_ctx, 1)");
									setState(209);
									((PredictStatementContext) _localctx).op = match(OR);
									setState(210);
									predictStatement(2);
								}
								break;
							}
						}
					}
					setState(215);
					_errHandler.sync(this);
					_alt = getInterpreter().adaptivePredict(_input, 20, _ctx);
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
		enterRule(_localctx, 24, RULE_constant);
		int _la;
		try {
			setState(223);
			_errHandler.sync(this);
			switch (_input.LA(1)) {
				case STRING:
					enterOuterAlt(_localctx, 1);
				{
					setState(216);
					match(STRING);
				}
				break;
				case PLUS:
				case MINUS:
				case INTEGER:
				case FLOAT:
					enterOuterAlt(_localctx, 2);
				{
					setState(218);
					_errHandler.sync(this);
					_la = _input.LA(1);
					if (_la == PLUS || _la == MINUS) {
						{
							setState(217);
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

					setState(220);
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
					setState(221);
					match(BOOL);
				}
				break;
				case NULL:
					enterOuterAlt(_localctx, 4);
				{
					setState(222);
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
		enterRule(_localctx, 26, RULE_column);
		try {
			setState(228);
			_errHandler.sync(this);
			switch (_input.LA(1)) {
				case COLUMN_ID:
					_localctx = new ColumnIdContext(_localctx);
					enterOuterAlt(_localctx, 1);
				{
					setState(225);
					match(COLUMN_ID);
				}
				break;
				case COLUMN_NAME:
					_localctx = new ColumnNameContext(_localctx);
					enterOuterAlt(_localctx, 2);
				{
					setState(226);
					match(COLUMN_NAME);
				}
				break;
				case IDENTITY:
					_localctx = new IdentityContext(_localctx);
					enterOuterAlt(_localctx, 3);
				{
					setState(227);
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
			case 11:
				return predictStatement_sempred((PredictStatementContext) _localctx, predIndex);
		}
		return true;
	}
	private boolean statement_sempred(StatementContext _localctx, int predIndex) {
		switch (predIndex) {
			case 0:
				return precpred(_ctx, 13);
			case 1:
				return precpred(_ctx, 12);
			case 2:
				return precpred(_ctx, 11);
			case 3:
				return precpred(_ctx, 10);
			case 4:
				return precpred(_ctx, 9);
			case 5:
				return precpred(_ctx, 5);
			case 6:
				return precpred(_ctx, 4);
			case 7:
				return precpred(_ctx, 3);
			case 8:
				return precpred(_ctx, 8);
			case 9:
				return precpred(_ctx, 7);
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
			"\3\u608b\ua72a\u8133\ub9ed\u417c\u3be7\u7786\u5964\3-\u00e9\4\2\t\2\4" +
					"\3\t\3\4\4\t\4\4\5\t\5\4\6\t\6\4\7\t\7\4\b\t\b\4\t\t\t\4\n\t\n\4\13\t" +
					"\13\4\f\t\f\4\r\t\r\4\16\t\16\4\17\t\17\3\2\3\2\3\2\3\3\3\3\3\3\3\3\3" +
					"\3\3\3\3\3\3\3\3\3\3\3\3\3\3\3\3\3\3\3\3\3\3\3\7\3\62\n\3\f\3\16\3\65" +
					"\13\3\5\3\67\n\3\3\3\5\3:\n\3\3\3\3\3\3\3\3\3\3\3\3\3\3\3\3\3\3\3\3\3" +
					"\3\3\3\3\3\3\3\3\3\3\3\3\3\3\3\3\3\3\3\3\3\3\3\3\3\3\3\3\3\3\3\3\5\3V" +
					"\n\3\3\3\3\3\3\3\3\3\3\3\7\3]\n\3\f\3\16\3`\13\3\3\3\3\3\3\3\3\3\5\3f" +
					"\n\3\3\3\3\3\7\3j\n\3\f\3\16\3m\13\3\3\4\3\4\3\4\5\4r\n\4\3\4\3\4\3\5" +
					"\3\5\3\5\7\5y\n\5\f\5\16\5|\13\5\3\6\3\6\3\6\3\6\5\6\u0082\n\6\3\7\3\7" +
					"\3\7\3\7\3\7\3\7\6\7\u008a\n\7\r\7\16\7\u008b\3\7\3\7\3\7\3\7\3\7\3\7" +
					"\6\7\u0094\n\7\r\7\16\7\u0095\5\7\u0098\n\7\3\7\3\7\5\7\u009c\n\7\3\7" +
					"\3\7\3\b\3\b\5\b\u00a2\n\b\3\t\3\t\3\t\3\t\3\t\3\t\3\t\3\t\3\t\3\n\3\n" +
					"\3\n\3\n\3\n\3\n\3\n\3\n\7\n\u00b5\n\n\f\n\16\n\u00b8\13\n\3\n\5\n\u00bb" +
					"\n\n\3\13\3\13\3\13\3\f\3\f\3\f\3\f\3\f\3\r\3\r\3\r\3\r\3\r\3\r\5\r\u00cb" +
					"\n\r\3\r\3\r\3\r\3\r\3\r\3\r\3\r\3\r\3\r\7\r\u00d6\n\r\f\r\16\r\u00d9" +
					"\13\r\3\16\3\16\5\16\u00dd\n\16\3\16\3\16\3\16\5\16\u00e2\n\16\3\17\3" +
					"\17\3\17\5\17\u00e7\n\17\3\17\2\4\4\30\20\2\4\6\b\n\f\16\20\22\24\26\30" +
					"\32\34\2\6\3\2\13\f\3\2\r\16\3\2\21\26\3\2&\'\2\u0104\2\36\3\2\2\2\49" +
					"\3\2\2\2\6n\3\2\2\2\bu\3\2\2\2\n\u0081\3\2\2\2\f\u0083\3\2\2\2\16\u00a1" +
					"\3\2\2\2\20\u00a3\3\2\2\2\22\u00ac\3\2\2\2\24\u00bc\3\2\2\2\26\u00bf\3" +
					"\2\2\2\30\u00ca\3\2\2\2\32\u00e1\3\2\2\2\34\u00e6\3\2\2\2\36\37\5\4\3" +
					"\2\37 \7\2\2\3 \3\3\2\2\2!\"\b\3\1\2\":\5\16\b\2#:\5\6\4\2$:\5\f\7\2%" +
					":\5\32\16\2&:\5\34\17\2\'(\7\32\2\2(:\5\4\3\b)*\7\3\2\2*+\5\4\3\2+,\7" +
					"\4\2\2,:\3\2\2\2-\66\7\7\2\2.\63\5\4\3\2/\60\7\t\2\2\60\62\5\4\3\2\61" +
					"/\3\2\2\2\62\65\3\2\2\2\63\61\3\2\2\2\63\64\3\2\2\2\64\67\3\2\2\2\65\63" +
					"\3\2\2\2\66.\3\2\2\2\66\67\3\2\2\2\678\3\2\2\28:\7\b\2\29!\3\2\2\29#\3" +
					"\2\2\29$\3\2\2\29%\3\2\2\29&\3\2\2\29\'\3\2\2\29)\3\2\2\29-\3\2\2\2:k" +
					"\3\2\2\2;<\f\17\2\2<=\7\17\2\2=j\5\4\3\17>?\f\16\2\2?@\7\20\2\2@j\5\4" +
					"\3\17AB\f\r\2\2BC\t\2\2\2Cj\5\4\3\16DE\f\f\2\2EF\t\3\2\2Fj\5\4\3\rGH\f" +
					"\13\2\2HI\t\4\2\2Ij\5\4\3\fJK\f\7\2\2KL\7\33\2\2Lj\5\4\3\bMN\f\6\2\2N" +
					"O\7\35\2\2Oj\5\4\3\7PQ\f\5\2\2QR\7\34\2\2Rj\5\4\3\6SU\f\n\2\2TV\7\32\2" +
					"\2UT\3\2\2\2UV\3\2\2\2VW\3\2\2\2WX\7\31\2\2XY\7\3\2\2Y^\5\4\3\2Z[\7\t" +
					"\2\2[]\5\4\3\2\\Z\3\2\2\2]`\3\2\2\2^\\\3\2\2\2^_\3\2\2\2_a\3\2\2\2`^\3" +
					"\2\2\2ab\7\4\2\2bj\3\2\2\2ce\f\t\2\2df\7\32\2\2ed\3\2\2\2ef\3\2\2\2fg" +
					"\3\2\2\2gh\7\30\2\2hj\7%\2\2i;\3\2\2\2i>\3\2\2\2iA\3\2\2\2iD\3\2\2\2i" +
					"G\3\2\2\2iJ\3\2\2\2iM\3\2\2\2iP\3\2\2\2iS\3\2\2\2ic\3\2\2\2jm\3\2\2\2" +
					"ki\3\2\2\2kl\3\2\2\2l\5\3\2\2\2mk\3\2\2\2no\7)\2\2oq\7\3\2\2pr\5\b\5\2" +
					"qp\3\2\2\2qr\3\2\2\2rs\3\2\2\2st\7\4\2\2t\7\3\2\2\2uz\5\n\6\2vw\7\t\2" +
					"\2wy\5\n\6\2xv\3\2\2\2y|\3\2\2\2zx\3\2\2\2z{\3\2\2\2{\t\3\2\2\2|z\3\2" +
					"\2\2}\u0082\5\4\3\2~\177\7)\2\2\177\u0080\7\23\2\2\u0080\u0082\5\4\3\2" +
					"\u0081}\3\2\2\2\u0081~\3\2\2\2\u0082\13\3\2\2\2\u0083\u0097\7\36\2\2\u0084" +
					"\u0085\7\37\2\2\u0085\u0086\5\30\r\2\u0086\u0087\7 \2\2\u0087\u0088\5" +
					"\4\3\2\u0088\u008a\3\2\2\2\u0089\u0084\3\2\2\2\u008a\u008b\3\2\2\2\u008b" +
					"\u0089\3\2\2\2\u008b\u008c\3\2\2\2\u008c\u0098\3\2\2\2\u008d\u0093\5\4" +
					"\3\2\u008e\u008f\7\37\2\2\u008f\u0090\5\32\16\2\u0090\u0091\7 \2\2\u0091" +
					"\u0092\5\4\3\2\u0092\u0094\3\2\2\2\u0093\u008e\3\2\2\2\u0094\u0095\3\2" +
					"\2\2\u0095\u0093\3\2\2\2\u0095\u0096\3\2\2\2\u0096\u0098\3\2\2\2\u0097" +
					"\u0089\3\2\2\2\u0097\u008d\3\2\2\2\u0098\u009b\3\2\2\2\u0099\u009a\7!" +
					"\2\2\u009a\u009c\5\4\3\2\u009b\u0099\3\2\2\2\u009b\u009c\3\2\2\2\u009c" +
					"\u009d\3\2\2\2\u009d\u009e\7\"\2\2\u009e\r\3\2\2\2\u009f\u00a2\5\20\t" +
					"\2\u00a0\u00a2\5\22\n\2\u00a1\u009f\3\2\2\2\u00a1\u00a0\3\2\2\2\u00a2" +
					"\17\3\2\2\2\u00a3\u00a4\7\27\2\2\u00a4\u00a5\7\3\2\2\u00a5\u00a6\5\30" +
					"\r\2\u00a6\u00a7\7\t\2\2\u00a7\u00a8\5\4\3\2\u00a8\u00a9\7\t\2\2\u00a9" +
					"\u00aa\5\4\3\2\u00aa\u00ab\7\4\2\2\u00ab\21\3\2\2\2\u00ac\u00ad\7\27\2" +
					"\2\u00ad\u00ae\7\3\2\2\u00ae\u00af\5\30\r\2\u00af\u00b0\7\4\2\2\u00b0" +
					"\u00b1\7\5\2\2\u00b1\u00b2\5\4\3\2\u00b2\u00b6\7\6\2\2\u00b3\u00b5\5\24" +
					"\13\2\u00b4\u00b3\3\2\2\2\u00b5\u00b8\3\2\2\2\u00b6\u00b4\3\2\2\2\u00b6" +
					"\u00b7\3\2\2\2\u00b7\u00ba\3\2\2\2\u00b8\u00b6\3\2\2\2\u00b9\u00bb\5\26" +
					"\f\2\u00ba\u00b9\3\2\2\2\u00ba\u00bb\3\2\2\2\u00bb\23\3\2\2\2\u00bc\u00bd" +
					"\7!\2\2\u00bd\u00be\5\22\n\2\u00be\25\3\2\2\2\u00bf\u00c0\7!\2\2\u00c0" +
					"\u00c1\7\5\2\2\u00c1\u00c2\5\4\3\2\u00c2\u00c3\7\6\2\2\u00c3\27\3\2\2" +
					"\2\u00c4\u00c5\b\r\1\2\u00c5\u00cb\5\4\3\2\u00c6\u00c7\7\3\2\2\u00c7\u00c8" +
					"\5\30\r\2\u00c8\u00c9\7\4\2\2\u00c9\u00cb\3\2\2\2\u00ca\u00c4\3\2\2\2" +
					"\u00ca\u00c6\3\2\2\2\u00cb\u00d7\3\2\2\2\u00cc\u00cd\f\5\2\2\u00cd\u00ce" +
					"\7\33\2\2\u00ce\u00d6\5\30\r\6\u00cf\u00d0\f\4\2\2\u00d0\u00d1\7\35\2" +
					"\2\u00d1\u00d6\5\30\r\5\u00d2\u00d3\f\3\2\2\u00d3\u00d4\7\34\2\2\u00d4" +
					"\u00d6\5\30\r\4\u00d5\u00cc\3\2\2\2\u00d5\u00cf\3\2\2\2\u00d5\u00d2\3" +
					"\2\2\2\u00d6\u00d9\3\2\2\2\u00d7\u00d5\3\2\2\2\u00d7\u00d8\3\2\2\2\u00d8" +
					"\31\3\2\2\2\u00d9\u00d7\3\2\2\2\u00da\u00e2\7%\2\2\u00db\u00dd\t\3\2\2" +
					"\u00dc\u00db\3\2\2\2\u00dc\u00dd\3\2\2\2\u00dd\u00de\3\2\2\2\u00de\u00e2" +
					"\t\5\2\2\u00df\u00e2\7#\2\2\u00e0\u00e2\7$\2\2\u00e1\u00da\3\2\2\2\u00e1" +
					"\u00dc\3\2\2\2\u00e1\u00df\3\2\2\2\u00e1\u00e0\3\2\2\2\u00e2\33\3\2\2" +
					"\2\u00e3\u00e7\7(\2\2\u00e4\u00e7\7*\2\2\u00e5\u00e7\7)\2\2\u00e6\u00e3" +
					"\3\2\2\2\u00e6\u00e4\3\2\2\2\u00e6\u00e5\3\2\2\2\u00e7\35\3\2\2\2\32\63" +
					"\669U^eikqz\u0081\u008b\u0095\u0097\u009b\u00a1\u00b6\u00ba\u00ca\u00d5" +
					"\u00d7\u00dc\u00e1\u00e6";
	public static final ATN _ATN =
			new ATNDeserializer().deserialize(_serializedATN.toCharArray());
	static {
		_decisionToDFA = new DFA[_ATN.getNumberOfDecisions()];
		for (int i = 0; i < _ATN.getNumberOfDecisions(); i++) {
			_decisionToDFA[i] = new DFA(_ATN.getDecisionState(i), i);
		}
	}
}