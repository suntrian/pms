package org.sunt.formula.function

import org.antlr.v4.runtime.CharStreams
import org.antlr.v4.runtime.CommonTokenStream
import org.antlr.v4.runtime.misc.ParseCancellationException
import org.slf4j.LoggerFactory
import org.sunt.formula.define.SqlDialect
import org.sunt.formula.function.parser.FunctionLexer
import org.sunt.formula.function.parser.FunctionParser
import java.net.URI
import java.util.*
import java.util.concurrent.ConcurrentHashMap

object FunctionDefinitionParser {

    private val log = LoggerFactory.getLogger(FunctionDefinitionParser.javaClass)
    private val functionDefUri = URI("classpath://function/function.kt")
    private val dialectCache: MutableMap<SqlDialect, Map<String, List<FunctionDefinition>>> = ConcurrentHashMap()

    @JvmOverloads
    fun loadFunctions(dialect: SqlDialect = SqlDialect.DEFALUT): Map<String, List<FunctionDefinition>> {
        var dialectFunctions = dialectCache[dialect]
        if (dialectFunctions == null) {
            synchronized(dialectCache) {
                dialectFunctions = dialectCache[dialect]
                if (dialectFunctions == null) {
                    val functionGroup = _loadFunctions(functionDefUri)[dialect?.name ?: "Common"]
                        ?: throw IllegalStateException("未找到${dialect?.name ?: "Common"}的函数定义")
                    dialectFunctions = mergeFunction(functionGroup)
                    dialectCache[dialect] = dialectFunctions!!
                }
            }
        }
        return dialectFunctions!!
    }

    @JvmOverloads
    fun listFunctions(dialect: SqlDialect = SqlDialect.DEFALUT): List<FunctionDefinition> {
        return loadFunctions(dialect).values.flatten()
    }

    private fun _loadFunctions(uri: URI): Map<String, FunctionGroup> {
        val inputStream = when (uri.scheme) {
            "classpath" -> this.javaClass.getResourceAsStream("/${uri.host}/${uri.path}")
            else -> uri.toURL().openStream()
        }
        val functionLexer = FunctionLexer(CharStreams.fromStream(inputStream))
        val tokenStream = CommonTokenStream(functionLexer)
        val functionParser = FunctionParser(tokenStream)
        val functionListener = FunctionDefinitionListener()
        functionParser.removeErrorListeners()
        functionParser.addParseListener(functionListener)
        try {
            functionParser.root()
            log.info("函数定义文件{}加载成功", uri)
        } catch (e: ParseCancellationException) {
            log.error("函数定义文件{}加载失败", uri, e)
        }
        return functionListener.functionGroups
    }

    private fun mergeFunction(functionGroup: FunctionGroup): Map<String, List<FunctionDefinition>> {
        if (functionGroup.parentGroup == null) {
            return handleOverload(functionGroup.functions)
        }
        val thisFunctions = functionGroup.functions
        val parentGroup = functionGroup.parentGroup!!
        val parentFunctionMap = mergeFunction(parentGroup)

        val thisFunctionMap = handleOverload(thisFunctions)
        return handleOverride(thisFunctionMap, parentFunctionMap)
    }

    //处理overload的函数
    private fun handleOverload(functionList: List<FunctionDefinition>): Map<String, List<FunctionDefinition>> {
        val funcGroupByName = functionList.groupByTo(TreeMap(String.CASE_INSENSITIVE_ORDER)) { it.funcName }
        for ((funcName, functions) in funcGroupByName) {
            for (function in functions) {
                if (function.overload) {
                    if (function.description.isBlank()) {
                        function.description = functions.firstOrNull { it.description.isNotBlank() }?.description ?: ""
                    }
                    if (function.categories.isEmpty()) {
                        function.categories =
                            functions.firstOrNull { it.categories.isNotEmpty() }?.categories ?: emptySet()
                    }
                    if (function.alias.isEmpty()) {
                        function.alias = functions.firstOrNull { it.alias.isNotEmpty() }?.alias ?: emptySet()
                    }
                    if (function.implement.isBlank()) {
                        function.implement = functions.firstOrNull { it.implement.isNotBlank() }?.implement
                            ?: throw IllegalStateException("函数${funcName}未提供转换SQL方法")
                    }
                }
            }
        }
        return funcGroupByName
    }

    //处理override的函数
    private fun handleOverride(
        childFunctions: Map<String, List<FunctionDefinition>>,
        parentFunctions: Map<String, List<FunctionDefinition>>
    ): Map<String, List<FunctionDefinition>> {
        val functionMap = TreeMap(parentFunctions)
        for ((thisFuncName, thisFuncList) in childFunctions) {
            val parentFuncList = functionMap[thisFuncName]
            if (parentFuncList == null) {
                functionMap[thisFuncName] = thisFuncList
            } else {
                @Suppress("NAME_SHADOWING")
                val parentFuncList = parentFuncList.toMutableList()
                for (thisFuncDef in thisFuncList) {
                    val it = parentFuncList.listIterator()
                    var foundFuncImpl = false
                    while (it.hasNext()) {
                        val parentFuncDef = it.next()
                        if (thisFuncDef == parentFuncDef) {
                            foundFuncImpl = true
                            if (thisFuncDef.alias.isEmpty()) thisFuncDef.alias = parentFuncDef.alias
                            if (thisFuncDef.categories.isEmpty()) thisFuncDef.categories = parentFuncDef.categories
                            if (thisFuncDef.description.isBlank()) thisFuncDef.description = parentFuncDef.description
                            for ((index, argument) in thisFuncDef.arguments.withIndex()) {
                                val parentArg = parentFuncDef.arguments[index]
                                if (argument.optionValues.isEmpty() && parentArg.optionValues.isNotEmpty()) {
                                    argument.optionValues = parentArg.optionValues
                                }
                                if (argument.defaultValue == null && parentArg.defaultValue != null) {
                                    argument.defaultValue = parentArg.defaultValue
                                }
                            }
                            it.set(thisFuncDef)
                            break
                        }
                    }
                    if (!foundFuncImpl) {
                        it.add(thisFuncDef)
                    }
                }
                functionMap[thisFuncName] = parentFuncList
            }
        }
        return functionMap
    }
}