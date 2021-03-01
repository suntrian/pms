package org.sunt.formula

import org.antlr.v4.runtime.ParserRuleContext
import org.antlr.v4.runtime.Token
import org.antlr.v4.runtime.TokenStreamRewriter
import org.antlr.v4.runtime.misc.IntervalSet
import org.antlr.v4.runtime.tree.TerminalNode
import org.sunt.formula.define.DataType
import org.sunt.formula.define.IColumn
import org.sunt.formula.define.SqlDialect
import org.sunt.formula.function.*
import org.sunt.formula.parser.FormulaParser.*
import org.sunt.formula.suggestion.FormulaSuggestion
import org.sunt.formula.suggestion.TokenSuggestion
import java.util.*
import kotlin.collections.ArrayList
import kotlin.collections.component1
import kotlin.collections.component2
import kotlin.collections.set

private typealias ExpectArgMap = MutableMap<FunctionDefinition, Set<FunctionDefinition.FunctionArgument>>
private typealias GenericsMap = MutableMap<FunctionDefinition, MutableMap<String, DataType>>

class FormulaSuggestVisitor(
    product: SqlDialect,
    columnInterface: ColumnInterface,
    rewriter: TokenStreamRewriter,
    private val cursor: Int
) : AbstractFormulaVisitor(product, columnInterface, rewriter) {

    private val tokenSuggestions: MutableList<TokenSuggestion> = mutableListOf()

    override fun visitFormula(ctx: FormulaContext): FormulaSuggestion {
        visitStatement(ctx.statement())
        if (this.syntaxErrors.isNotEmpty() && tokenSuggestions.isEmpty()) {
            this.tokenSuggestions.addAll(this.syntaxErrors.map { suggestFromSyntaxError(it, this.cursor) })
        }
        return FormulaSuggestion().also {
            it.expression = rewriter.text
            it.suggestions = this.tokenSuggestions
        }
    }

    override fun visitFunctionStatement(ctx: FunctionStatementContext): StatementInfo {
        val functionNameNode = ctx.identity().IDENTITY()
        val functionNameStmt = visitIdentity(ctx.identity())
        val functionName = functionNameNode.text

        @Suppress("UNCHECKED_CAST")
        val functionDefines = (
                if (functionNameStmt.token.scope == TokenScope.FUNCTION && functionNameStmt.payload is List<*>)
                    functionNameStmt.payload as List<FunctionDefinition>
                else null)?.toMutableList()
        //未匹配函数
        if (functionDefines == null || functionDefines.isEmpty()) {
            return StatementInfo(ctx).apply {
                expression = ctx.text
                token = TokenItem.FUNCTION()
                status = TokenStatus.UNKNOWN
                dataType = DataType.NONE
            }
        }
        //无参数
        if (ctx.functionParams() == null) {
            val firstArgs = functionDefines.map { it.arguments.getOrNull(0) }
            var tokenStatus = TokenStatus.NORMAL
            for (firstArg in firstArgs) {
                //存在无参函数或者单可变参数函数,且无右括号时，推荐右括号
                if (firstArg == null || firstArg.vararg) {
                    //无右括号时
                    if (ctx.R_PARENTHESES()?.isNotValid() == true || isCursorTokenEnd(ctx.L_PARENTHESES())) {
                        val tokenSuggestion = TokenSuggestion.ofNext(ctx.L_PARENTHESES())
                            .apply {
                                status = TokenStatus.EXPECTED
                                scopes = setOf(TokenItem.PARENTHESES(")"))
                                comment = "期待')'"
                            }
                        this.tokenSuggestions.add(tokenSuggestion)
                        tokenStatus = TokenStatus.EXPECTED
                    }
                    if (firstArg == null) continue
                }
                //当前非可变参数，或者光标在此时，才推荐参数
                if (!firstArg.vararg || isCursorTokenEnd(ctx.L_PARENTHESES())) {
                    val tokenSuggestion = TokenSuggestion.ofNext(ctx.L_PARENTHESES()).apply {
                        status = TokenStatus.EXPECTED
                        setDataTypes(firstArg.dataType)
                        if (firstArg.reserved.isNotEmpty()) {
                            scopes = firstArg.reserved.map { TokenItem.RESERVED(it) }.toSet()
                            comment = "期待关键词[${firstArg.reserved.joinToString(",")}]"
                        } else if (firstArg.optionValues.isNotEmpty()) {
                            scopes = firstArg.optionValues.map { TokenItem.CONSTANT(it.toString()) }.toSet()
                            comment = "期待选项[${firstArg.optionValues.joinToString(",")}]"
                        } else if (firstArg.constant) {
                            scopes = setOf(TokenItem.NONE())
                            comment = "期待${firstArg.dataType}类型常量"
                        } else if (firstArg.suggest != TokenItem.NONE()) {
                            scopes = setOf(firstArg.suggest)
                        } else {
                            scopes = setOf(TokenItem.FUNCTION(), TokenItem.COLUMN())
                        }
                    }
                    this.tokenSuggestions.add(tokenSuggestion)
                    tokenStatus = TokenStatus.EXPECTED
                }
            }
            return StatementInfo(ctx).apply {
                expression = ctx.text
                token = TokenItem.FUNCTION(functionName)
                status = tokenStatus
                dataType = functionDefines[0].dataType
            }
        }

        val firstFuncDataType = functionDefines[0].dataType
        //有参数, 随着遍历可能会删除当前的函数定义
        val actualParams = recordCurrent(functionDefines, emptySet()) {
            visitFunctionParams(ctx.functionParams())
        }
        //参数匹配
        if (functionDefines.isNotEmpty() && actualParams.all { it.status.privilege <= TokenStatus.INFO.privilege }) {
            if (ctx.R_PARENTHESES()?.isNotValid() == true) {
                this.tokenSuggestions.add(
                    TokenSuggestion.ofNext(ctx.stop)
                        .apply {
                            status = TokenStatus.EXPECTED
                            scopes = setOf(TokenItem.PARENTHESES(")"))
                            comment = "期待')'"
                        })
            }
        }

        return StatementInfo(ctx).apply {
//           参数可能不全，无法翻译成SQL, 只好取原文
            expression = ctx.text
            token = TokenItem.FUNCTION(functionName)
            status = actualParams.map { it.status }.minOrNull()!!
            dataType = functionDefines.getOrNull(0)?.dataType ?: firstFuncDataType
        }
    }

    override fun visitFunctionParams(ctx: FunctionParamsContext): List<StatementInfo> {
        var lastUnVisitedArg = ctx.functionParam().size
        val result = ArrayList<StatementInfo>(lastUnVisitedArg)

        fun candidateArguments(index: Int): ExpectArgMap {
            val expectArgMap = mutableMapOf<FunctionDefinition, Set<FunctionDefinition.FunctionArgument>>()
            for (candidate in this.currentFunctionCandidates) {
                val expectArgs = mutableSetOf<FunctionDefinition.FunctionArgument>()
                var expectArg = candidate.arguments.getOrNull(index) ?: continue
                expectArgs.add(expectArg)

                if (expectArg.defaultValue != null) {
                    //如果有默认值，则推荐下一个无默认值参数
                    for (i in index until candidate.arguments.size) {
                        expectArg = candidate.arguments.getOrNull(i) ?: continue
                        if (expectArg.defaultValue != null) continue
                        expectArgs.add(expectArg)
                    }
                }
                if (expectArgs.isNotEmpty()) {
                    expectArgMap[candidate] = expectArgs
                }
            }
            return expectArgMap
        }

        val genericTypeMap: GenericsMap = mutableMapOf()
        fun candidateTypesAndOptions(arguments: ExpectArgMap): Pair<Set<DataType>, Set<String>> {
            val currentDataTypes = mutableSetOf<DataType>()
            val currentOptions = mutableSetOf<String>()
            for ((funcDef, expArgs) in arguments) {
                for (expectArg in expArgs) {
                    if (expectArg.reserved.isNotEmpty()) {
                        currentOptions.addAll(expectArg.reserved)
                    } else if (expectArg.optionValues.isNotEmpty()) {
                        currentOptions.addAll(expectArg.optionValues.map { it.toString() })
                    }
                    var expectType = expectArg.genericType
                        ?.let { genericTypeMap[funcDef]?.get(expectArg.genericType) }
                        ?: expectArg.dataType
                    currentDataTypes.add(expectType)
                }
            }
            return currentDataTypes to currentOptions
        }

        val tempSuggestions: MutableMap<FunctionDefinition, MutableList<TokenSuggestion>> = mutableMapOf()

        for ((index, paramCtx) in ctx.functionParam().withIndex()) {
            //参数可能是antlr自动纠错给补上的，如果是这样，则跳出循环，进行统一处理推荐下一参数
            if (!paramCtx.isValid()) {
                lastUnVisitedArg = index
                break
            }

            //先遍历一遍函数定义，获取要求的数据类型
            val (currentDataTypes, currentOptions) = candidateTypesAndOptions(candidateArguments(index))

            if (currentDataTypes.isEmpty()) {
                //参数过多
                this.tokenSuggestions.add(TokenSuggestion.ofThis(paramCtx).apply {
                    status = TokenStatus.ERROR
                    comment = "多余的参数:" + paramCtx.text
                })
                result.add(StatementInfo(paramCtx).apply {
                    expression = paramCtx.text
                    status = TokenStatus.ERROR
                })

                continue
            }

            val paramStmt = recordCurrent(currentDataTypes, currentOptions) {
                visitStatement(paramCtx.statement())
            }
            result.add(paramStmt)
            if (paramStmt.status.privilege >= TokenStatus.UNKNOWN.privilege) {
                continue
            }
            val currentFunctionsIter = this.currentFunctionCandidates.iterator()
            for (candidate in currentFunctionsIter) {
                val expectArg = candidate.arguments.getOrNull(index) ?: continue
                val expectDataType = expectArg.genericType
                    ?.let { genericTypeMap[candidate]?.get(expectArg.genericType) }
                if (expectArg.match(paramStmt.expression, paramStmt.dataType, paramStmt.token, expectDataType)) {
                    if (expectArg.genericType != null && expectDataType == null) {
                        genericTypeMap.putIfAbsent(candidate, mutableMapOf())
                        genericTypeMap[candidate]!![expectArg.genericType!!] = paramStmt.dataType
                    }
                } else {
                    tempSuggestions.putIfAbsent(candidate, mutableListOf())
                    tempSuggestions[candidate]!!.add(TokenSuggestion.ofThis(paramCtx)
                        .apply {
                            status = TokenStatus.ERROR
                            dataTypes = setOf(expectDataType ?: expectArg.dataType)
                            scopes = functionAndColumn
                        })
                    currentFunctionsIter.remove()
                }
            }
            if (this.currentFunctionCandidates.isEmpty()) {
                if (tempSuggestions.isNotEmpty()) {
                    this.tokenSuggestions.addAll(tempSuggestions.values.iterator().next())
                }
                break
            }

        }

        //还有参数
        val expectArgMap = candidateArguments(lastUnVisitedArg)
        if (expectArgMap.isNotEmpty()) {
            val expectArgs = expectArgMap.values.flatten()
            val hasComma = ctx.COMMA(lastUnVisitedArg - 1) != null
            val noneVararg = expectArgs.none { it.vararg }
            if (!hasComma) {
                //推荐,的情况
                //0,前面的参数无错误，如 CONCAT(TO_STRING(abc
                //1,存在非可变参数
                //2,可变参数，且光标处于当前位置
                if ((result.last().status.privilege < TokenStatus.EXPECTED.privilege) && (noneVararg || isCursorTokenEnd(ctx.functionParam(lastUnVisitedArg - 1).stop))) {
                    tokenSuggestions.add(TokenSuggestion.ofNext(ctx.functionParam(lastUnVisitedArg - 1).stop).apply {
                        status = TokenStatus.EXPECTED
                        scopes = setOf(TokenItem.COMMA())
                        comment = "期待','"
                    })
                }
            } else {
                val expectArgTypes = expectArgMap.flatMap { map ->
                    map.value.map { arg ->
                        arg.genericType?.let { type ->
                            genericTypeMap[map.key]?.get(type)
                        } ?: arg.dataType
                    }
                }.toSet()
                tokenSuggestions.add(TokenSuggestion.ofNext(ctx.COMMA(lastUnVisitedArg - 1)).apply {
                    status = TokenStatus.EXPECTED
                    dataTypes = expectArgTypes
                    if (expectArgs.any { it.reserved.isNotEmpty() }) {
                        scopes = expectArgs.first { it.reserved.isNotEmpty() }.reserved.map { TokenItem.RESERVED(it) }
                            .toSet()
                    } else if (expectArgs.any { it.optionValues.isNotEmpty() }) {
                        scopes =
                            expectArgs.first { it.optionValues.isNotEmpty() }.optionValues.map { TokenItem.CONSTANT(it.toString()) }
                                .toSet()
                    } else if (expectArgs.any { it.suggest != TokenItem.NONE() }) {
                        scopes = setOf(expectArgs.first { it.suggest != TokenItem.NONE() }.suggest)
                    } else if (expectArgs.any { it.constant }) {
                        comment = "期待${expectArgs[0]}类型常量"
                    } else {
                        scopes = functionAndColumn
                    }
                })
            }

            //如果剩余参数中不是可变参数
            //则将缺少的参数也作为错误信息加入参数列表返回
            //否则返回正常的参数列表，用于上层推荐)
            if (hasComma || noneVararg) {
                result.add(StatementInfo("", -1, -1, -1, -1).apply {
                    status = TokenStatus.EXPECTED
                })
            }

        }

        return result
    }

    override fun visitIdentity(ctx: IdentityContext): StatementInfo {
        val identity = ctx.IDENTITY()
        val text = identity.text
        val functions = functionMap[text] ?: aliasFunctionNameMap[text]?.let { functionMap[it] } ?: emptyList()
        val isFuncName = functions.isNotEmpty()
        val isReserved = currentOptValueCandidates.contains(text.toUpperCase())
        var column: IColumn? = null
        val isColName =
            (!isFuncName && !isReserved) && (columnInterface.getColumnByName(text)?.also { column = it } != null)
        //不是函数也不是字段
        if (!isFuncName && !isReserved && !isColName) {
            if (text.length > 2 && currentOptValueCandidates.any { it.startsWith(text.toUpperCase()) }) {
                this.tokenSuggestions.add(TokenSuggestion.ofThis(identity).apply {
                    scopes = currentOptValueCandidates.map { TokenItem.RESERVED(it) }.toSet()
                    dataTypes = setOf(DataType.ANY)
                    status = TokenStatus.UNKNOWN
                    comment = "期待[" + currentOptValueCandidates.joinToString(", ") + "]"
                    leftPart = identity.takeIf { isCursorToken(it) || isCursorTokenEnd(it) }
                        ?.let { it.text.substring(0, cursor - it.symbol.startIndex) }
                })
            }
            val tokenSuggestion = TokenSuggestion.ofThis(identity).apply {
                scopes = if (ctx.parent is FunctionStatementContext) setOf(TokenItem.FUNCTION(text)) else functionAndColumn
                dataTypes = currentDataTypeCandidates
                status = TokenStatus.UNKNOWN
                comment = "未识别的内容:$text"
                leftPart = identity.takeIf { isCursorToken(it) || isCursorTokenEnd(it) }
                    ?.let { it.text.substring(0, cursor - it.symbol.startIndex) }
            }
            this.tokenSuggestions.add(tokenSuggestion)
            return StatementInfo(ctx).apply {
                status = TokenStatus.UNKNOWN
                dataType = DataType.ANY
                expression = text
                if (ctx.parent is FunctionStatementContext) {
                    token = TokenItem.FUNCTION(text)
                    payload = emptyList<FunctionDefinition>()
                }
            }

        } else if (isColName) {
            rewriter.replace(ctx.start, ctx.stop, convertColumnExpression(column!!))
        }
        if (isCursorToken(identity) || isCursorTokenEnd(identity)) {
            if (isReserved) {
                this.tokenSuggestions.add(TokenSuggestion.ofThis(identity).apply {
                    status = TokenStatus.NORMAL
                    scopes = currentOptValueCandidates.map { TokenItem.RESERVED(it) }.toSet()
                    dataTypes = setOf(DataType.ANY)
                    leftPart = text.substring(0, cursor - identity.symbol.startIndex)
                })
            } else {
                //光标所在节点时，推荐匹配的函数或者字段
                //或者光标位于函数节点之后，推荐左括号或者继续匹配函数或者字段
                this.tokenSuggestions.add(TokenSuggestion.ofThis(identity).apply {
                    status = TokenStatus.NORMAL
                    scopes = setOf(TokenItem.FUNCTION(), TokenItem.COLUMN())
                    dataTypes = currentDataTypeCandidates
                    leftPart = text.substring(0, cursor - identity.symbol.startIndex)
                })
            }
            if (isFuncName && isCursorTokenEnd(identity)) {
                if (ctx.parent !is FunctionStatementContext || (ctx.parent as FunctionStatementContext).L_PARENTHESES()
                        ?.isNotValid() == true
                ) {
                    //光标位置函数名称结尾时，且下一token非(时，推荐左括号
                    this.tokenSuggestions.add(TokenSuggestion.ofNext(identity).apply {
                        scopes = setOf(TokenItem.PARENTHESES("("))
                        status = TokenStatus.EXPECTED
                        comment = "期待'('"
                    })
                }
            }
        }
        return StatementInfo(ctx).apply {
            when {
                isColName -> {
                    expression = column!!.expression
                    token = TokenItem.COLUMN(text)
                    status = TokenStatus.NORMAL
                    dataType = column!!.dataType
                    payload = column!!
                }
                isReserved -> {
                    expression = text.toUpperCase()
                    token = TokenItem.RESERVED(expression)
                    status = TokenStatus.NORMAL
                    dataType = DataType.ANY
                    payload = expression
                }
                isFuncName -> {
                    expression = text
                    token = TokenItem.FUNCTION(text)
                    status = TokenStatus.EXPECTED
                    dataType = functions[0].dataType
                    payload = functions
                }
            }
        }
    }

    override fun visitConstant(ctx: ConstantContext): StatementInfo {
        if (isCursorToken(ctx.start) || isCursorTokenEnd(ctx.start)) {
            if (currentOptValueCandidates.isNotEmpty()) {
                this.tokenSuggestions.add(TokenSuggestion.ofThis(ctx).apply {
                    status = TokenStatus.NORMAL
                    scopes = currentOptValueCandidates.map { TokenItem.CONSTANT(it) }.toSet()
                    dataTypes = currentDataTypeCandidates
                    comment = "期待[${currentOptValueCandidates.joinToString(", ")}]常量"
                })
            }
        }
        return super.visitConstant(ctx)
    }

    override fun visitMathExpression(ctx: MathExpressionContext): StatementInfo {
        return visitOperatorExpression(ctx, ctx.op, ctx.statement(0), ctx.statement(1))
    }

    override fun visitComparePredicate(ctx: ComparePredicateContext): StatementInfo {
        return visitOperatorExpression(ctx, ctx.op, ctx.statement(0), ctx.statement(1))
    }

    override fun visitLogicalPredicate(ctx: LogicalPredicateContext): StatementInfo {
        return visitOperatorExpression(ctx, ctx.op, ctx.statement(0), ctx.statement(1))
    }

    override fun visitLikePredicate(ctx: LikePredicateContext): StatementInfo {
        return visitOperatorExpression(ctx, ctx.op, ctx.statement(0), ctx.statement(1))
    }

    private fun visitOperatorExpression(
        ctx: ParserRuleContext,
        op: Token,
        leftCtx: StatementContext,
        rightCtx: StatementContext?
    ): StatementInfo {
        val expectedTypesToReturnType: Pair<Set<DataType>, DataType?> = when (op.type) {
            POWER, MOD, MUL, DIV, PLUS, MINUS -> if (PLUS == op.type) setOf(
                DataType.INTEGER,
                DataType.DECIMAL,
                DataType.STRING
            ) to null
            else setOf(DataType.INTEGER, DataType.DECIMAL) to null
            GREATER_EQUAL, GREATER, LESS_EQUAL, LESS -> setOf(
                DataType.INTEGER,
                DataType.DECIMAL,
                DataType.STRING
            ) to DataType.BOOLEAN
            EQUAL, NOT_EQUAL -> emptySet<DataType>() to DataType.BOOLEAN
            AND, XOR, OR -> setOf(DataType.BOOLEAN) to DataType.BOOLEAN
            LIKE -> setOf(DataType.STRING) to DataType.BOOLEAN
            else -> emptySet<DataType>() to DataType.NONE
        }
        val left = recordCurrent(expectedTypesToReturnType.first, emptySet()) {
            visitStatement(leftCtx)
        }
        val right = if (rightCtx?.isValid() == true) recordCurrent(expectedTypesToReturnType.first, emptySet()) {
            visitStatement(rightCtx)
        } else null
        if (right == null) {
            this.tokenSuggestions.add(TokenSuggestion.ofNext(op).apply {
                dataTypes = expectedTypesToReturnType.first
                scopes = functionAndColumn
                status = TokenStatus.EXPECTED
            })
        }
        val stmt = StatementInfo(ctx).apply {
            expression = left.expression + operatorMap[op.type] + (right?.expression ?: "")
            dataType = expectedTypesToReturnType.second ?: left.dataType
            status = if (right == null) TokenStatus.EXPECTED else left.status
        }
        return checkAndReturn(stmt)
    }

    override fun visitIfSpecial(ctx: IfSpecialContext): StatementInfo {
        TODO("Not yet implemented")
    }

    override fun visitCaseStatement(ctx: CaseStatementContext): StatementInfo {
        TODO("Not yet implemented")
    }

    override fun visitInPredicate(ctx: InPredicateContext): StatementInfo {
        TODO("Not yet implemented")
    }

    override fun visitParenthesesExpression(ctx: ParenthesesExpressionContext): StatementInfo {
        var expStatus = TokenStatus.NORMAL
        if (ctx.R_PARENTHESES()?.isNotValid() == true) {
            this.tokenSuggestions.add(TokenSuggestion.ofNext(ctx.statement().stop)
                .apply {
                    this.status = TokenStatus.EXPECTED
                    this.scopes = setOf(TokenItem.PARENTHESES(")"))
                    this.comment = "期待')'"
                })
            expStatus = TokenStatus.EXPECTED
        }
        val stmt = visitStatement(ctx.statement())
        return StatementInfo(ctx).apply {
            expression = "(" + stmt.expression + (ctx.R_PARENTHESES()?.text ?: "")
            status = maxOf(expStatus, stmt.status, Comparator.comparingInt { it.privilege })
            dataType = stmt.dataType
            token = stmt.token
        }
    }

    override fun visitNotPredicate(ctx: NotPredicateContext): StatementInfo {
        val pred = if (ctx.statement()?.isValid() == false) {
            this.tokenSuggestions.add(TokenSuggestion.ofNext(ctx.NOT())
                .apply {
                    dataTypes = setOf(DataType.BOOLEAN)
                    scopes = functionAndColumn
                    status = TokenStatus.EXPECTED
                })
            null
        } else recordCurrent(setOf(DataType.BOOLEAN), emptySet()) {
            visitStatement(ctx.statement())
        }
        val stmt = StatementInfo(ctx).apply {
            expression = " NOT " + (pred?.expression ?: "")
            dataType = DataType.BOOLEAN
            status = pred?.status ?: TokenStatus.EXPECTED
        }
        return checkAndReturn(stmt)
    }

    override fun visitSquareExpression(ctx: SquareExpressionContext): StatementInfo {
        val lastToken = if (ctx.statements()?.isEmpty == true) {
            this.tokenSuggestions.add(TokenSuggestion.ofNext(ctx.L_SQUARE()).apply {
                scopes = functionAndColumn
                status = TokenStatus.EXPECTED
                dataTypes = setOf(DataType.ANY)
                for (candidate in currentDataTypeCandidates) {
                    if (candidate.genericType.isNotEmpty()) {
                        dataTypes = candidate.genericType.toSet()
                        break
                    }
                }
            })
            ctx.L_SQUARE().symbol
        } else ctx.statements().stop
        if (ctx.R_SQUARE()?.isNotValid() == true) {
            this.tokenSuggestions.add(TokenSuggestion.ofNext(lastToken).apply {
                scopes = setOf(TokenItem.SQUARE("]"))
                status = TokenStatus.EXPECTED
                comment = "期待']'"
            })
        }
        val stmt = super.visitSquareExpression(ctx)
        if (ctx.R_SQUARE() == null) {
            stmt.status = maxOf(TokenStatus.EXPECTED, stmt.status, Comparator.comparingInt { it.privilege })
        }
        return stmt
    }

    override fun visitIfnullExpression(ctx: IfnullExpressionContext): StatementInfo {
        if (ctx.statement(1)?.isValid() == false) {
            tokenSuggestions.add(TokenSuggestion.ofNext(ctx.IFNULL()).apply {
                status = TokenStatus.EXPECTED
                dataTypes = currentDataTypeCandidates
                scopes = functionAndColumn
            })
        }
        val left = visitStatement(ctx.statement(0))
        val right = ctx.statement(1)?.let { visitStatement(it) }
        return StatementInfo(ctx).apply {
            dataType = left.dataType
            status = if (right == null) TokenStatus.EXPECTED else minOf(left.status, right.status)
            expression = left.expression + ", " + (right?.expression ?: "")
            if (ctx.parent !is IfnullExpressionContext) {
                expression = "COALESCE($expression)"
            }
        }
    }

    private fun checkAndReturn(stmt: StatementInfo): StatementInfo {
        return if (currentDataTypeCandidates.isEmpty()) {
            stmt
        } else if (currentDataTypeCandidates.contains(stmt.dataType)) {
            stmt
        } else {
            this.tokenSuggestions.add(TokenSuggestion(stmt.expression, stmt.startToken, stmt.startIndex, stmt.stopIndex)
                .apply {
                    status = TokenStatus.ERROR
                    scopes = functionAndColumn
                    dataTypes = currentDataTypeCandidates
                })
            stmt
        }
    }

    private fun isCursorToken(token: Token): Boolean = token.startIndex <= cursor && cursor <= token.stopIndex
    private fun isCursorToken(node: TerminalNode): Boolean = isCursorToken(node.symbol)
    private fun isCursorTokenEnd(token: Token): Boolean = cursor == token.stopIndex + 1
    private fun isCursorTokenEnd(node: TerminalNode): Boolean = isCursorTokenEnd(node.symbol)

    private fun suggestFromSyntaxError(syntaxError: SyntaxError, cursor: Int): TokenSuggestion {
        val eof = syntaxError.offendingSymbols?.type == Token.EOF
        val intervalSet = syntaxError.expectTokens
        log.info("expect token {} at {}", intervalSet.toString(VOCABULARY), syntaxError.offendingSymbols?.text)
        val offendingSymbols = syntaxError.offendingSymbols
        if (offendingSymbols != null) {
            return TokenSuggestion.ofThis(offendingSymbols).apply {
                comment = syntaxError.message ?: syntaxError.e?.message ?: "未知错误"
                dataTypes = currentDataTypeCandidates
                if (eof) {
                    scopes = getSuggestScopeFromExpected(intervalSet)
                    status = TokenStatus.EXPECTED
                } else {
                    scopes = if (currentOptValueCandidates.isEmpty()) getSuggestScopeFromExpected(intervalSet)
                    else mutableSetOf<TokenItem>().apply {
                        addAll(getSuggestScopeFromExpected(intervalSet))
                        addAll(currentOptValueCandidates.map { TokenItem.CONSTANT(it) })
                    }
                    status = TokenStatus.ERROR
                }
            }
        } else {
            return TokenSuggestion("", -1, -1, -1).apply {
                status = TokenStatus.ERROR
                comment = syntaxError.message ?: syntaxError.e?.message ?: "未知错误"
            }
        }
    }

    private fun getSuggestScopeFromExpected(intervalSet: IntervalSet): Set<TokenItem> {
        if (intervalSet.isNil) return emptySet()
        val tokenItems = mutableSetOf<TokenItem>()
        if (intervalSet.contains(IDENTITY)) {
            tokenItems.add(TokenItem.FUNCTION())
            tokenItems.add(TokenItem.COLUMN())
        }
        for (tok in intArrayOf(CASE, WHEN, THEN, ELSE, END)) {
            if (intervalSet.contains(tok)) {
                tokenItems.add(TokenItem.RESERVED(VOCABULARY.getDisplayName(tok)))
            }
        }
        for (tok in intArrayOf(R_PARENTHESES)) {
            if (intervalSet.contains(tok)) {
                tokenItems.add(TokenItem.PARENTHESES(VOCABULARY.getDisplayName(tok).trim('\'')))
            }
        }
        return tokenItems
    }

    companion object {
        private val functionAndColumn = setOf<TokenItem>(TokenItem.COLUMN(), TokenItem.FUNCTION())
    }

}