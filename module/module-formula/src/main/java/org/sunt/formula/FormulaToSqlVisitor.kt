package org.sunt.formula

import org.sunt.formula.define.DataType
import org.sunt.formula.define.IColumn
import org.sunt.formula.define.SqlProduct
import org.sunt.formula.parser.FormulaParser.*
import java.util.function.Function

class FormulaToSqlVisitor(product: SqlProduct, getColumnById: Function<String?, IColumn?>, getColumnByName: Function<String?, IColumn?>) : AbstractFormulaVisitor(product, getColumnById, getColumnByName) {

    override fun visitFormula(ctx: FormulaContext): StatementInfo {
        return visitStatement(ctx.statement())
    }

    private fun visitStatement(ctx: StatementContext?): StatementInfo {
        return when (ctx) {
            is FunctionExpressionContext -> visitFunctionExpression(ctx)
            is ConstantExpressionContext -> visitConstantExpression(ctx)
            is ColumnExpressionContext -> visitColumnExpression(ctx)
            is MathExpressionContext -> visitMathExpression(ctx)
            is ComparePredicateContext -> visitComparePredicate(ctx)
            is LogicalPredicateContext -> visitLogicalPredicate(ctx)
            is IfExpressionContext -> visitIfExpression(ctx)
            is CaseExpressionContext -> visitCaseExpression(ctx)
            is InPredicateContext -> visitInPredicate(ctx)
            is ParenthesesExpressionContext -> visitParenthesesExpression(ctx)
            is NotPredicateContext -> visitNotPredicate(ctx)
            is LikePredicateContext -> visitLikePredicate(ctx)
            else -> StatementInfo(ctx)
        }
    }

    override fun visitParenthesesExpression(ctx: ParenthesesExpressionContext): StatementInfo {
        val stmt = visitStatement(ctx.statement())
        val parenthesesStmt = StatementInfo(ctx);
        parenthesesStmt.expression = "(" + stmt.expression + ")"
        parenthesesStmt.dataType = stmt.dataType
        parenthesesStmt.status = stmt.status;
        return parenthesesStmt
    }

    override fun visitComparePredicate(ctx: ComparePredicateContext): StatementInfo {
        val left = visitStatement(ctx.statement(0))
        val right = visitStatement(ctx.statement(1))
        val predStmt = StatementInfo(ctx)
        predStmt.dataType = DataType.BOOLEAN
        if (ctx.op.type == EQUAL || ctx.op.type == NOT_EQUAL) {
            //相等判断时，数据类型可不一致
            if (VOCABULARY.getSymbolicName(NULL).equals(right.expression, true)) {
                predStmt.expression = left.expression + when (ctx.op.type) {
                    EQUAL -> " IS NULL";
                    NOT_EQUAL -> " IS NOT NULL"
                    else -> ""
                }
                predStmt.status = left.status
                return predStmt
            }
        } else {
            checkDataType(right, left.dataType)
        }

        when (ctx.op.type) {
            GREATER, GREATER_EQUAL, LESS, LESS_EQUAL -> predStmt.expression = left.expression + ctx.op.text + right.expression
            EQUAL -> predStmt.expression = left.expression + "=" + right.expression
            NOT_EQUAL -> predStmt.expression = left.expression + "!=" + right.expression
        }

        predStmt.status = maxOf(left.status, right.status, Comparator.comparingInt { it.privilege })
        return predStmt
    }

    override fun visitInPredicate(ctx: InPredicateContext): StatementInfo {
        if (ctx.statement() == null || ctx.statement().size <= 1) {
            throw IllegalStateException("操作数个数不符")
        }
        val left = visitStatement(ctx.statement(0))
        var status = left.status
        val exprBuilder = StringBuilder(left.expression)
        if (ctx.NOT() != null) {
            exprBuilder.append(" NOT")
        }
        exprBuilder.append(" IN (")
        for (i in 1..ctx.statement().size) {
            val stmt = visitStatement(ctx.statement(i))
            if (stmt.status.privilege > status.privilege) {
                status = stmt.status
            }
            exprBuilder.append(stmt.expression)
            if (i != ctx.statement().size - 1) {
                exprBuilder.append(",")
            }
        }
        exprBuilder.append(")")
        val inStmt = StatementInfo(ctx)
        inStmt.expression = exprBuilder.toString()
        inStmt.dataType = DataType.BOOLEAN
        inStmt.status = status
        return inStmt
    }

    override fun visitCaseExpression(ctx: CaseExpressionContext): StatementInfo {
        return super.visitCaseExpression(ctx)!!
    }

    override fun visitMathExpression(ctx: MathExpressionContext): StatementInfo {
        if (ctx.statement() == null || ctx.statement().size != 2) {
            throw java.lang.IllegalStateException("操作数个数不符")
        }
        val left = visitStatement(ctx.statement(0))
        val right = visitStatement(ctx.statement(1))
        val mathStmt = StatementInfo(ctx)
        if (left.dataType == DataType.STRING && right.dataType == DataType.STRING) {
            if (ctx.op.type == PLUS) {
                //todo String concat and type convert if not string
                return mathStmt
            } else {
                throw IllegalStateException("数据类型错误")
            }
        } else {
            if (!left.dataType.isNumeric() || !right.dataType.isNumeric()) {
                throw IllegalStateException("数据类型错误")
            }
        }

        mathStmt.status = maxOf(left.status, right.status, Comparator.comparingInt { it.privilege })
        when (ctx.op.type) {
            POWER -> {
                mathStmt.expression = left.expression + "^" + right.expression
                mathStmt.dataType = if (left.dataType == DataType.DECIMAL || right.dataType == DataType.DECIMAL) {
                    DataType.DECIMAL
                } else {
                    DataType.INTEGER
                }
            }
            MOD -> {
                mathStmt.expression = left.expression + " % " + right.expression
                mathStmt.dataType = DataType.INTEGER
            }
            MUL -> {
                mathStmt.expression = left.expression + " * " + right.expression
                mathStmt.dataType = if (left.dataType == DataType.DECIMAL || right.dataType == DataType.DECIMAL) {
                    DataType.DECIMAL
                } else {
                    DataType.INTEGER
                }
            }
            DIV -> {
                mathStmt.expression = left.expression + " / " + right.expression
                mathStmt.dataType = DataType.DECIMAL
            }
            PLUS -> mathStmt.expression = left.expression + " + " + right.expression
            MINUS -> mathStmt.expression = left.expression + " - " + right.expression
        }

        return mathStmt
    }

    override fun visitNotPredicate(ctx: NotPredicateContext): StatementInfo {
        val pred = visitStatement(ctx.statement())
        checkDataType(pred, DataType.BOOLEAN)
        val stmt = StatementInfo(ctx)
        stmt.expression = " NOT " + pred.expression
        stmt.dataType = DataType.BOOLEAN
        stmt.status = pred.status
        return stmt
    }

    override fun visitLogicalPredicate(ctx: LogicalPredicateContext): StatementInfo {
        if (ctx.statement().size != 2) {
            throw java.lang.IllegalStateException("操作数个数不符")
        }
        val left = visitStatement(ctx.statement(0))
        checkDataType(left, DataType.BOOLEAN)
        val right = visitStatement(ctx.statement(1))
        checkDataType(right, DataType.BOOLEAN)
        val stmt = StatementInfo(ctx)
        stmt.dataType = DataType.BOOLEAN
        stmt.status = maxOf(left.status, right.status, Comparator.comparingInt { it.privilege })
        stmt.expression = left.expression + ctx.op.text + right.expression
        return stmt
    }

    override fun visitIfExpression(ctx: IfExpressionContext): StatementInfo {
        return super.visitIfExpression(ctx)!!
    }

    override fun visitLikePredicate(ctx: LikePredicateContext): StatementInfo {
        val left = visitStatement(ctx.statement())
        val not = ctx.NOT() != null
        val right = ctx.STRING().text;
        val likeStmt = StatementInfo(ctx)
        likeStmt.expression = left.expression + if (not) {
            " NOT"
        } else {
            ""
        } + " LIKE " + right
        likeStmt.status = left.status
        likeStmt.dataType = DataType.BOOLEAN
        return likeStmt
    }

    override fun visitFunctionExpression(ctx: FunctionExpressionContext): StatementInfo {
        return visitFunctionStatement(ctx.functionStatement())
    }

    override fun visitFunctionStatement(ctx: FunctionStatementContext): StatementInfo {
        val funcName = ctx.IDENTITY().text
        val params = visitFunctionParams(ctx.functionParams())
        val funcStmt = StatementInfo(ctx)
        funcStmt.status = params.status
        funcStmt.dataType = DataType.NONE
        funcStmt.expression = ""
        return funcStmt
    }

    override fun visitFunctionParams(ctx: FunctionParamsContext): StatementInfo {
        val stmts: ArrayList<StatementInfo> = ArrayList(ctx.statement().size)
        for (statementContext in ctx.statement()) {
            stmts.add(visitStatement(statementContext))
        }
        val stmt = StatementInfo(ctx)
        stmt.expression = stmts.joinToString(",") { it.expression }
        stmt.status = stmts.map { it.status }.maxByOrNull { it.privilege }!!
        return stmt
    }

    override fun visitCaseStatement(ctx: CaseStatementContext): StatementInfo {
        return super.visitCaseStatement(ctx)!!
    }

    override fun visitIfSpecial(ctx: IfSpecialContext): StatementInfo {
        return when {
            ctx.ifFunction() != null -> visitIfFunction(ctx.ifFunction())
            ctx.ifStatement() != null -> visitIfStatement(ctx.ifStatement())
            else -> throw IllegalStateException("Not Expected Here")
        }
    }

    override fun visitIfFunction(ctx: IfFunctionContext): StatementInfo {
        return super.visitIfFunction(ctx)!!
    }

    override fun visitIfStatement(ctx: IfStatementContext): StatementInfo {
        return super.visitIfStatement(ctx)!!
    }

    override fun visitElseIfStatement(ctx: ElseIfStatementContext): StatementInfo {
        return super.visitElseIfStatement(ctx)!!
    }

    override fun visitElseStatement(ctx: ElseStatementContext): StatementInfo {
        return super.visitElseStatement(ctx)!!
    }

    override fun visitPredictStatement(ctx: PredictStatementContext): StatementInfo {
        if (ctx.statement() != null) {
            val stmt = visitStatement(ctx.statement())
            checkDataType(stmt, DataType.BOOLEAN)
            return stmt
        } else if (ctx.op != null) {
            val left = visitPredictStatement(ctx.predictStatement(0))
            val right = visitPredictStatement(ctx.predictStatement(1))
            val predStmt = StatementInfo(ctx)
            predStmt.expression = left.expression + ctx.op.text + right.expression
            predStmt.dataType = DataType.BOOLEAN
            predStmt.status = maxOf(left.status, right.status, Comparator.comparingInt { it.privilege })
            return predStmt
        }
        return super.visitPredictStatement(ctx)!!
    }

}