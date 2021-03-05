package org.sunt.formula

import org.antlr.v4.runtime.TokenStreamRewriter
import org.sunt.formula.define.DataType
import org.sunt.formula.define.SqlDialect
import org.sunt.formula.exception.*
import org.sunt.formula.function.FunctionDefinition
import org.sunt.formula.function.StatementInfo
import org.sunt.formula.function.TokenStatus
import org.sunt.formula.parser.FormulaParser.*
import java.util.*
import kotlin.Comparator
import kotlin.collections.ArrayList
import kotlin.collections.HashMap

class FormulaToSqlVisitor(
    product: SqlDialect,
    columnInterface: ColumnInterface,
    rewriter: TokenStreamRewriter
) : AbstractFormulaVisitor(product, columnInterface, rewriter) {

    var formulaType = FormulaType.NORMAL
    val groupBys = mutableListOf<StatementInfo>()

    override fun visitFormula(ctx: FormulaContext): StatementInfo {
        return visitStatement(ctx.statement())
    }

    override fun visitParenthesesExpression(ctx: ParenthesesExpressionContext): StatementInfo {
        val stmt = visitStatement(ctx.statement())
        return StatementInfo(ctx).apply {
            expression = "(" + stmt.expression + ")"
            dataType = stmt.dataType
            status = stmt.status;
            children = listOf(stmt)
        }
    }

    override fun visitComparePredicate(ctx: ComparePredicateContext): StatementInfo {
        checkArgSize(ctx.statement(), 2)
        val left = visitStatement(ctx.statement(0))
        val right = visitStatement(ctx.statement(1))
        val predStmt = StatementInfo(ctx)
        if (VOCABULARY.getSymbolicName(NULL).equals(right.expression, true)) {
            if (ctx.op.type == EQUAL || ctx.op.type == NOT_EQUAL) {
                predStmt.expression = left.expression + when (ctx.op.type) {
                    EQUAL -> " IS NULL";
                    NOT_EQUAL -> " IS NOT NULL"
                    else -> ""
                }
                predStmt.status = left.status
                return predStmt
            } else {
                throw ParamTypeMismatchException("NULL只可用于==，!=的比较")
            }
        } else if (ctx.op.type == EQUAL || ctx.op.type == NOT_EQUAL) {
            //相等判断时，数据类型可不一致
        } else {
            if (!(left.dataType.isAssignableFrom(right.dataType) || right.dataType.isAssignableFrom(left.dataType))) {
                throw ParamTypeMismatchException(right.expression, left.dataType, right.dataType)
            }
        }
        with(predStmt) {
            this.dataType = DataType.BOOLEAN
            this.expression = left.expression + operatorMap[ctx.op.type] + right.expression
            this.children = listOf(left, right)
            this.status = maxOf(left.status, right.status, Comparator.comparingInt { it.privilege })
        }
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
        val rightStmts = visitStatements(ctx.statements())
        val rightStmtsIter = rightStmts.iterator()
        for (stmt in rightStmtsIter) {
            if (stmt.status.privilege > status.privilege) {
                status = stmt.status
            }
            exprBuilder.append(stmt.expression)
            if (rightStmtsIter.hasNext()) {
                exprBuilder.append(",")
            }
        }
        exprBuilder.append(")")
        return StatementInfo(ctx).apply {
            this.expression = exprBuilder.toString()
            this.dataType = DataType.BOOLEAN
            this.status = status
            this.children = LinkedList<StatementInfo>().also {
                it.add(left)
                it.addAll(rightStmts)
            }
        }
    }

    override fun visitMathExpression(ctx: MathExpressionContext): StatementInfo {
        checkArgSize(ctx.statement(), 2)
        val left = visitStatement(ctx.statement(0))
        val right = visitStatement(ctx.statement(1))
        val mathStmt = StatementInfo(ctx)
        mathStmt.children = listOf(left, right)
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
                throw ParamTypeMismatchException(ctx.text, DataType.DECIMAL, DataType.STRING)
            }
        } else {
            if (!left.dataType.isNumeric()) {
                throw ParamTypeMismatchException("${left.expression}要求为数字类型")
            } else if (!right.dataType.isNumeric()) {
                throw ParamTypeMismatchException("${right.expression}要求为数字类型")
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
        return StatementInfo(ctx).apply {
            expression = " NOT " + pred.expression
            dataType = DataType.BOOLEAN
            status = pred.status
            children = listOf(pred)
        }
    }

    override fun visitLogicalPredicate(ctx: LogicalPredicateContext): StatementInfo {
        checkArgSize(ctx.statement(), 2)
        val left = visitStatement(ctx.statement(0))
        checkDataType(left, DataType.BOOLEAN)
        val right = visitStatement(ctx.statement(1))
        checkDataType(right, DataType.BOOLEAN)
        return StatementInfo(ctx).apply {
            dataType = DataType.BOOLEAN
            status = maxOf(left.status, right.status, Comparator.comparingInt { it.privilege })
            expression = left.expression + operatorMap[ctx.op.type] + right.expression
            children = listOf(left, right)
        }
    }

    override fun visitIfnullExpression(ctx: IfnullExpressionContext): StatementInfo {
        checkArgSize(ctx.statement(), 2)
        val left = visitStatement(ctx.statement(0))
        val right = visitStatement(ctx.statement(1))
        return StatementInfo(ctx).apply {
            children = listOf(left, right)
            dataType = left.dataType
            status = maxOf(left.status, right.status, Comparator.comparingInt { it.privilege })
            expression = left.expression + ", " + right.expression
            if (ctx.parent !is IfnullExpressionContext) {
                expression = "COALESCE($expression)"
            }
        }
    }

    override fun visitLikePredicate(ctx: LikePredicateContext): StatementInfo {
        checkArgSize(ctx.statement(), 2)
        val left = visitStatement(ctx.statement(0))
        val not = ctx.NOT() != null
        val right = visitStatement(ctx.statement(1))
        checkDataType(right, DataType.STRING)
        return StatementInfo(ctx).apply {
            expression = left.expression + (if (not) " NOT" else "") + " LIKE " + right.expression
            status = left.status
            dataType = DataType.BOOLEAN
            children = listOf(left, right)
        }
    }

    override fun visitFunctionStatement(ctx: FunctionStatementContext): StatementInfo {
        val funcName = ctx.identity().text
        val functionDefines = (this.functionMap[funcName]
            ?: this.aliasFunctionNameMap[funcName]?.let { this.functionMap[it] })?.toMutableList()
            ?: throw IllegalStateException("函数${funcName}不存在")

        val params = if (ctx.functionParams() == null) emptyList()
        else recordCurrent(functionDefines, functionDefines.flatMap { it.arguments }.flatMap { it.reserved }.toSet()) {
            visitFunctionParams(ctx.functionParams())
        }
        val filledParams: MutableList<StatementInfo> = params.toMutableList()
        val finalFunctionDefine: FunctionDefinition = figureFunctionDefine(functionDefines, filledParams)
        kotlin.run {
            val isRoot = ctx.parent.parent is FormulaContext
            val uFuncName = funcName.toUpperCase()
            this.formulaType = when {
                uFuncName.startsWith("GROUP_") -> {
                    if (!isRoot) throw IllegalStateException("聚合函数不可嵌套使用")
                    this.groupBys.addAll(params.drop(finalFunctionDefine.arguments.indexOfFirst { it.vararg }))
                    FormulaType.AGGREGATE
                }
                uFuncName.endsWith("_OVER") -> {
                    if (!isRoot) throw IllegalStateException("窗口函数不可嵌套使用")
                    FormulaType.WINDOW
                }
                else -> FormulaType.NORMAL
            }
        }
        return StatementInfo(ctx).apply {
            status = params.map { it.status }.maxByOrNull { it.privilege } ?: TokenStatus.NORMAL
            dataType = if (finalFunctionDefine.typeParamIndex != null) filledParams[finalFunctionDefine.typeParamIndex!!].dataType else finalFunctionDefine.dataType
            children = filledParams
            expression = finalFunctionDefine.translate(this)
        }
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
            log.warn("CASE表达式多个条件返回的数据类型不一致")
        }
        return StatementInfo(ctx).apply {
            expression = exprBuilder.toString()
            dataType = stmts[0].dataType
            status = stmts.map { it.status }.maxByOrNull { it.privilege }!!
            children = stmts
        }
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
            log.warn("IF表达式多个条件返回的数据类型不一致")
        }
        return StatementInfo(ctx).apply {
            dataType = stmts[0].dataType
            expression = exprBuilder.toString()
            status = stmts.map { it.status }.maxByOrNull { it.privilege }!!
            children = stmts
        }
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
        return StatementInfo(ctx).apply {
            expression = "IF(${predStmt.expression}, ${trueStmt.expression}, ${falseStmt.expression})"
            dataType = trueStmt.dataType
            status = maxOf(trueStmt.status, falseStmt.status, Comparator.comparingInt { it.privilege })
            children = listOf(predStmt, trueStmt, falseStmt)
        }
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
            return StatementInfo(ctx).apply {
                expression = left.expression + operatorMap[ctx.op.type] + right.expression
                dataType = DataType.BOOLEAN
                status = maxOf(left.status, right.status, Comparator.comparingInt { it.privilege })
                children = listOf(left, right)
            }
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
        actualArgs: MutableList<StatementInfo>
    ): FunctionDefinition {
        val matched = HashMap<Int, FunctionDefinition>(functionDefines.size)
        val errorInfos = HashMap<Int, List<AbstractFormulaException>>(functionDefines.size)
        val matchedArgMap = HashMap<Int, Int>(functionDefines.size)

        outer@ for ((funcIndex, funcDefine) in functionDefines.withIndex()) {
            val errors = LinkedList<AbstractFormulaException>()
            val expectArgSize = funcDefine.arguments.size
            val actualArgSize = actualArgs.size
            val lastVararg = funcDefine.arguments.lastOrNull()?.vararg ?: false

            //无参函数
            if (actualArgSize == 0 && (expectArgSize == 0 || (expectArgSize == 1 && lastVararg))) {
                matched[funcIndex] = funcDefine
                continue
            } else if ((expectArgSize == 0 && actualArgSize > 0) || (actualArgSize == 0 && expectArgSize > 0)) {
                errors.add(
                    ParamsSizeMismatchException(
                        funcDefine.funcName,
                        funcDefine.arguments.size,
                        actualArgs.size
                    )
                );
                errorInfos[funcIndex] = errors
                continue
            }

            //处理泛型
            val genericTypeMap: MutableMap<String, DataType> = if (funcDefine.genericTypes.isNotEmpty()) {
                HashMap(2)
            } else mutableMapOf()

            //有参函数
            val expectIter = funcDefine.arguments.iterator()
            val actualIter = actualArgs.listIterator()

            var expectArg: FunctionDefinition.FunctionArgument = expectIter.next()
            var actualArg: StatementInfo = actualIter.next()
            var firstNoMatchArgType: DataType? = null
            while (true) {
                //泛型实际类型
                val genericRealType: DataType? = expectArg.genericType?.let { genericTypeMap[it] }

                //参数匹配
                try {
                    val score =
                        expectArg.match(actualArg.expression, actualArg.dataType, actualArg.token, genericRealType)
                    if (expectArg.genericType != null && genericRealType == null) {
                        genericTypeMap[expectArg.genericType!!] = actualArg.dataType
                    }
                    matchedArgMap.compute(funcIndex) { _, v -> if (v == null) score else v + score }
                } catch (e: ParamTypeMismatchException) {
                    //参数不匹配
                    if (firstNoMatchArgType == null) {
                        firstNoMatchArgType = genericRealType ?: expectArg.dataType
                    }
                    //无缺省参数或无下一形参
                    if (expectArg.defaultValue == null || !expectIter.hasNext()) {
                        errors.add(e)
                        errorInfos[funcIndex] = errors
                        continue@outer
                    }
                    //有缺省参数
                    else {
                        expectArg = expectIter.next()
                        continue
                    }
                }

                //无实参了
                if (!actualIter.hasNext()) {
                    while (expectIter.hasNext()) {
                        //还有形参
                        expectArg = expectIter.next()
                        //无缺省值
                        if (expectArg.defaultValue == null && !expectArg.vararg) {
                            errors.add(ParamsSizeMismatchException(funcDefine.funcName, expectArgSize, actualArgSize))
                            errorInfos[funcIndex] = errors
                            continue@outer
                        }
                    }
                    break
                }
                //无形参了
                if (!expectIter.hasNext()) {
                    //此处必然还有实参，那么如果不是可变参数，则不匹配
                    if (!expectArg.vararg) {
                        errors.add(ParamsSizeMismatchException(funcDefine.funcName, expectArgSize, actualArgSize))
                        errorInfos[funcIndex] = errors
                        continue@outer
                    } else {
                        actualArg = actualIter.next()
                        continue
                    }
                }

                expectArg = expectIter.next()
                actualArg = actualIter.next()
            }
            matched[funcIndex] = funcDefine
        }
        if (matched.isNotEmpty()) {
            val mostMatchFuncIndex = matchedArgMap.maxByOrNull { it.value }?.key ?: 0
            return matched[mostMatchFuncIndex] ?: matched.iterator().next().value
        }
        if (errorInfos.isNotEmpty()) {
            val mostMatchErrorIndex = matchedArgMap.maxByOrNull { it.value }?.key ?: 0
            throw errorInfos[mostMatchErrorIndex]?.getOrNull(0) ?: errorInfos.iterator().next().value[0]
        }
        throw WillNeverHappenException("不应该来这里")
    }
}