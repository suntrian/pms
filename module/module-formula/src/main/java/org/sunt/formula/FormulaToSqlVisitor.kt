package org.sunt.formula

import org.sunt.formula.define.DataType
import org.sunt.formula.define.SqlDialect
import org.sunt.formula.exception.*
import org.sunt.formula.function.FunctionDefinition
import org.sunt.formula.parser.FormulaParser.*
import org.sunt.formula.suggestion.TokenStatus
import java.util.*
import kotlin.Comparator
import kotlin.collections.ArrayList
import kotlin.collections.HashMap

class FormulaToSqlVisitor(
    product: SqlDialect,
    columnInterface: ColumnInterface
) : AbstractFormulaVisitor(product, columnInterface) {

    override fun visitFormula(ctx: FormulaContext): StatementInfo {
        return visitStatement(ctx.statement())
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
        checkArgSize(ctx.statement(), 2)
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

        predStmt.expression = left.expression + operatorMap[ctx.op.type] + right.expression
        predStmt.status = maxOf(left.status, right.status, Comparator.comparingInt { it.privilege })
        return predStmt
    }

    override fun visitInPredicate(ctx: InPredicateContext): StatementInfo {
        val left = visitStatement(ctx.statement())
        var status = left.status
        val exprBuilder = StringBuilder(left.expression)
        if (ctx.NOT() != null) {
            exprBuilder.append(" NOT")
        }
        exprBuilder.append(" IN (")
        val rightStmts = visitStatements(ctx.statements()).iterator()
        for (stmt in rightStmts) {
            if (stmt.status.privilege > status.privilege) {
                status = stmt.status
            }
            exprBuilder.append(stmt.expression)
            if (rightStmts.hasNext()) {
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
        return visitCaseStatement(ctx.caseStatement())
    }

    override fun visitMathExpression(ctx: MathExpressionContext): StatementInfo {
        checkArgSize(ctx.statement(), 2)
        val left = visitStatement(ctx.statement(0))
        val right = visitStatement(ctx.statement(1))
        val mathStmt = StatementInfo(ctx)
        mathStmt.status = maxOf(left.status, right.status, Comparator.comparingInt { it.privilege })
        if (left.dataType == DataType.STRING && right.dataType == DataType.STRING) {
            if (ctx.op.type == PLUS) {
                var expression = "${left.expression}, ${right.expression}"
                val isTop = ctx.parent !is StatementContext || ctx.op == null
                if (isTop) {
                    expression = "CONCAT($expression)"
                }
                mathStmt.expression = expression
                mathStmt.dataType = DataType.STRING
                return mathStmt
            } else {
                throw DataTypeMismatchException(ctx.text, DataType.DECIMAL, DataType.STRING)
            }
        } else {
            if (!left.dataType.isNumeric() || !right.dataType.isNumeric()) {
                throw DataTypeMismatchException("数据类型错误")
            }
        }

        when (ctx.op.type) {
            POWER -> {
                mathStmt.expression = "POWER( ${left.expression}, ${right.expression})"
                mathStmt.dataType = if (left.dataType == DataType.DECIMAL || right.dataType == DataType.DECIMAL) {
                    DataType.DECIMAL
                } else {
                    DataType.INTEGER
                }
            }
            MOD -> {
                mathStmt.expression = left.expression + operatorMap[MOD] + right.expression
                mathStmt.dataType = DataType.INTEGER
            }
            MUL -> {
                mathStmt.expression = left.expression + operatorMap[MUL] + right.expression
                mathStmt.dataType = if (left.dataType == DataType.DECIMAL || right.dataType == DataType.DECIMAL) {
                    DataType.DECIMAL
                } else {
                    DataType.INTEGER
                }
            }
            DIV -> {
                mathStmt.expression = left.expression + operatorMap[DIV] + right.expression
                mathStmt.dataType = DataType.DECIMAL
            }
            PLUS -> {
                mathStmt.expression = left.expression + operatorMap[PLUS] + right.expression
                mathStmt.dataType = DataType.commonType(left.dataType, right.dataType)
            }
            MINUS -> {
                mathStmt.expression = left.expression + operatorMap[MINUS] + right.expression
                mathStmt.dataType = DataType.commonType(left.dataType, right.dataType)
            }
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
        checkArgSize(ctx.statement(), 2)
        val left = visitStatement(ctx.statement(0))
        checkDataType(left, DataType.BOOLEAN)
        val right = visitStatement(ctx.statement(1))
        checkDataType(right, DataType.BOOLEAN)
        val stmt = StatementInfo(ctx)
        stmt.dataType = DataType.BOOLEAN
        stmt.status = maxOf(left.status, right.status, Comparator.comparingInt { it.privilege })
        stmt.expression = left.expression + operatorMap[ctx.op.type] + right.expression
        return stmt
    }

    override fun visitIfExpression(ctx: IfExpressionContext): StatementInfo {
        return visitIfSpecial(ctx.ifSpecial())
    }

    override fun visitIfnullExpression(ctx: IfnullExpressionContext): StatementInfo {
        checkArgSize(ctx.statement(), 2)
        val left = visitStatement(ctx.statement(0))
        val right = visitStatement(ctx.statement(1))
        val stmt = StatementInfo(ctx)
        with(stmt) {
            dataType = left.dataType
            status = maxOf(left.status, right.status, Comparator.comparingInt { it.privilege })
            //todo dialect related
            expression = "IFNULL(${left.expression}, ${right.expression})"
        }
        return stmt
    }

    override fun visitLikePredicate(ctx: LikePredicateContext): StatementInfo {
        checkArgSize(ctx.statement(), 2)
        val left = visitStatement(ctx.statement(0))
        val not = ctx.NOT() != null
        val right = visitStatement(ctx.statement(1))
        checkDataType(right, DataType.STRING)
        val likeStmt = StatementInfo(ctx)
        likeStmt.expression = left.expression + (if (not) " NOT" else "") + " LIKE " + right.expression
        likeStmt.status = left.status
        likeStmt.dataType = DataType.BOOLEAN
        return likeStmt
    }

    override fun visitSquareExpression(ctx: SquareExpressionContext): StatementInfo {
        val statements = ctx.statements()?.let { visitStatements(it) } ?: emptyList()
        val result = StatementInfo(ctx)
        with(result) {
            expression = statements.joinToString(", ") { it.expression }
            status = statements.map { it.status }.maxByOrNull { it.privilege } ?: TokenStatus.NORMAL
            val commonDataType = DataType.commonType(statements.map { it.dataType })
            dataType = DataType.of("List<${commonDataType}>")
        }
        return result
    }

    override fun visitStatements(ctx: StatementsContext): List<StatementInfo> {
        return ctx.statement().map { visitStatement(it) }
    }

    override fun visitFunctionExpression(ctx: FunctionExpressionContext): StatementInfo {
        return visitFunctionStatement(ctx.functionStatement())
    }

    override fun visitFunctionStatement(ctx: FunctionStatementContext): StatementInfo {
        val funcName = ctx.IDENTITY().text
        val functionDefines = this.functionMap[funcName] ?: this.functionMap[this.aliasFunctionNameMap[funcName]]
        ?: throw IllegalStateException("函数${funcName}不存在")

        val params =
            recordCurrent(functionDefines, functionDefines.flatMap { it.arguments }.flatMap { it.reserved }.toSet()) {
                visitFunctionParams(ctx.functionParams())
            }
        val filledParams: MutableList<StatementInfo?> = params.toMutableList()
        val finalFunctionDefine: FunctionDefinition = figureFunctionDefine(functionDefines, filledParams)
        val funcStmt = StatementInfo(ctx)
        funcStmt.status = params.map { it.status }.maxByOrNull { it.privilege }!!
        funcStmt.dataType =
            if (finalFunctionDefine.typeParamIndex != null) filledParams[finalFunctionDefine.typeParamIndex!!]!!.dataType else finalFunctionDefine.dataType
        funcStmt.expression = finalFunctionDefine.translate(filledParams.map { it?.expression })

        return funcStmt
    }

    override fun visitFunctionParams(ctx: FunctionParamsContext): List<StatementInfo> {
        val stmts: ArrayList<StatementInfo> = ArrayList(ctx.functionParam().size)
        for (paramCtx in ctx.functionParam()) {
            stmts.add(visitStatement(paramCtx.statement()))
        }
        return stmts
    }

    override fun visitCaseStatement(ctx: CaseStatementContext): StatementInfo {
        val stmts = mutableListOf<StatementInfo>()
        val exprBuilder = StringBuilder("CASE ")
        val whenSize = ctx.WHEN().size
        if (ctx.caseStmt == null) {
            exprBuilder.append("\n")
            for (i in 0 until whenSize) {
                val stmt = visitStatement(ctx.statement(i))
                stmts.add(stmt)
                exprBuilder.append("\t WHEN ").append(visitPredictStatement(ctx.predictStatement(i)).expression)
                        .append(" THEN ").append(stmt.expression).append("\n")
            }

        } else {
            exprBuilder.append(visitStatement(ctx.caseStmt).expression).append("\n")
            for (i in 0 until whenSize) {
                val stmt = visitStatement(ctx.statement(i + 1))
                stmts.add(stmt)
                exprBuilder.append("\t WHEN ").append(visitConstant(ctx.constant(i)).expression)
                        .append(" THEN ").append(stmt.expression).append("\n")
            }
        }
        if (ctx.elseStmt != null) {
            val stmt = visitStatement(ctx.elseStmt)
            stmts.add(stmt)
            exprBuilder.append("\t ELSE ").append(stmt.expression).append("\n")
        }
        exprBuilder.append(" END ")
        if (stmts.map { it.dataType }.distinct().count() > 1) {
            logger.warn("CASE表达式多个条件返回的数据类型不一致")
        }
        val caseStmt = StatementInfo(ctx)
        caseStmt.expression = exprBuilder.toString()
        caseStmt.dataType = stmts[0].dataType
        caseStmt.status = stmts.map { it.status }.maxByOrNull { it.privilege }!!
        return caseStmt
    }

    override fun visitIfStatement(ctx: IfStatementContext): StatementInfo {
        val stmts = mutableListOf<StatementInfo>()
        val exprBuilder = StringBuilder("CASE ")
        var predStmt = visitPredictStatement(ctx.predict)
        var stmt = visitStatement(ctx.statement())
        stmts.add(stmt)
        exprBuilder.append("\n").append("\t WHEN ").append(predStmt.expression)
                .append(" THEN ").append(stmt.expression).append("\n")
        for (elseIfStmt in ctx.elseIfStatement()) {
            predStmt = visitPredictStatement(elseIfStmt.ifStatement().predict)
            stmt = visitStatement(elseIfStmt.ifStatement().statement())
            stmts.add(stmt)
            exprBuilder.append("\n").append("\t WHEN ").append(predStmt.expression)
                    .append(" THEN ").append(stmt.expression).append("\n")
        }
        if (ctx.elseStatement() != null) {
            stmt = visitStatement(ctx.elseStatement().statement())
            stmts.add(stmt)
            exprBuilder.append("\t ELSE ").append(stmt.expression).append("\n")
        }
        exprBuilder.append(" END ")
        if (stmts.map { it.dataType }.distinct().count() > 1) {
            logger.warn("CASE表达式多个条件返回的数据类型不一致")
        }
        val ifStmt = StatementInfo(ctx)
        ifStmt.dataType = stmts[0].dataType
        ifStmt.expression = exprBuilder.toString()
        ifStmt.status = stmts.map { it.status }.maxByOrNull { it.privilege }!!
        return ifStmt
    }

    override fun visitIfSpecial(ctx: IfSpecialContext): StatementInfo {
        return when {
            ctx.ifFunction() != null -> visitIfFunction(ctx.ifFunction())
            ctx.ifStatement() != null -> visitIfStatement(ctx.ifStatement())
            else -> throw IllegalStateException("Not Expected Here")
        }
    }

    override fun visitIfFunction(ctx: IfFunctionContext): StatementInfo {
        val predStmt = visitPredictStatement(ctx.predict)
        checkDataType(predStmt, DataType.BOOLEAN)
        checkArgSize(ctx.statement(), 2)
        val trueStmt = visitStatement(ctx.statement(0))
        val falseStmt = visitStatement(ctx.statement(1))
        val ifStmt = StatementInfo(ctx)
        ifStmt.expression = "IF(${predStmt.expression}, ${trueStmt.expression}, ${falseStmt.expression})"
        ifStmt.dataType = trueStmt.dataType
        ifStmt.status = maxOf(trueStmt.status, falseStmt.status, Comparator.comparingInt { it.privilege })
        return ifStmt
    }

    override fun visitPredictStatement(ctx: PredictStatementContext): StatementInfo {
        if (ctx.statement() != null) {
            val stmt = visitStatement(ctx.statement())
            checkDataType(stmt, DataType.BOOLEAN)
            return stmt
        } else if (ctx.op != null) {
            checkArgSize(ctx.predictStatement(), 2)
            val left = visitPredictStatement(ctx.predictStatement(0))
            val right = visitPredictStatement(ctx.predictStatement(1))
            val predStmt = StatementInfo(ctx)
            predStmt.expression = left.expression + operatorMap[ctx.op.type] + right.expression
            predStmt.dataType = DataType.BOOLEAN
            predStmt.status = maxOf(left.status, right.status, Comparator.comparingInt { it.privilege })
            return predStmt
        } else if (ctx.L_PARENTHESES() != null && ctx.R_PARENTHESES() != null) {
            checkArgSize(ctx.predictStatement(), 1)
            val stmt = visitPredictStatement(ctx.predictStatement(0))
            stmt.expression = "(" + stmt.expression + ")"
            return stmt;
        }
        throw java.lang.IllegalStateException("Not Expected Here")
    }

    private fun figureFunctionDefine(
        functionDefines: List<FunctionDefinition>,
        actualArgs: MutableList<StatementInfo?>
    ): FunctionDefinition {
        val matched = ArrayList<FunctionDefinition>(functionDefines.size)
        val errorInfos = ArrayList<List<AbstractFormulaException>>(functionDefines.size)

        outer@ for (funcDefine in functionDefines) {
            val errors = LinkedList<AbstractFormulaException>()
            val expectArgSize = funcDefine.arguments.size
            val actualArgSize = actualArgs.size

            //处理泛型
            val genericTypeMap: MutableMap<String, DataType> = if (funcDefine.genericTypes.isNotEmpty()) {
                HashMap(2)
            } else mutableMapOf()

            //有参函数
            val expectIter = funcDefine.arguments.iterator()
            val actualIter = actualArgs.listIterator()

            var expectArg: FunctionDefinition.FunctionArgument? = null
            var actualArg: StatementInfo? = null
            var matchedArg = 0
            while (true) {
                //无实参了
                if (!actualIter.hasNext()) {
                    if (!expectIter.hasNext()) {
                        break
                    }
                    //还有形参
                    expectArg = expectIter.next()
                    //无缺省值
                    if (!expectArg.vararg && expectArg.defaultValue == null) {
                        errors.add(ParamsSizeMismatchException(funcDefine.funcName, expectArgSize, actualArgSize))
                        errorInfos.add(errors)
                        continue@outer
                    }
                    continue
                }
                //无形参了
                if (!expectIter.hasNext()) {
                    //此处必然还有实参，那么如果不是可变参数，则不匹配
                    if (expectArg == null || !expectArg.vararg) {
                        errors.add(ParamsSizeMismatchException(funcDefine.funcName, expectArgSize, actualArgSize))
                        errorInfos.add(errors)
                        continue@outer
                    }
                }

                if (expectArg == null) expectArg = expectIter.next()
                if (actualArg == null) actualArg = actualIter.next()

                //泛型实际类型
                val genericRealType: DataType? = expectArg.genericType?.let { genericTypeMap[it] }

                //参数匹配
                if (expectArg.match(actualArg!!.expression, actualArg.dataType, actualArg.token, genericRealType)) {
                    if (expectArg.genericType != null && genericRealType == null) {
                        genericTypeMap[expectArg.genericType!!] = actualArg.dataType
                    }
                    if (expectIter.hasNext()) expectArg = expectIter.next()
                    if (actualIter.hasNext()) actualArg = actualIter.next()
                    matchedArg++
                    continue
                }
                //参数不匹配
                //无缺省参数或无下一形参
                if (expectArg.defaultValue == null || !expectIter.hasNext()) {
                    errors.add(
                        DataTypeMismatchException(
                            actualArg.expression,
                            genericRealType ?: expectArg.dataType,
                            actualArg.dataType
                        )
                    )
                    errorInfos.add(errors)
                    continue@outer
                }
                //有缺省参数
                else {
                    expectArg = expectIter.next()
                }
            }
            matched.add(funcDefine)
        }
        if (matched.isNotEmpty()) {
            if (matched.size > 1) {
                throw AmbiguousFunctionException(matched)
            }
            return matched[0]
        }
        if (errorInfos.isNotEmpty()) {
            throw errorInfos[0][0]
        }
        throw WillNeverHappenException("不应该来这里")
    }
}