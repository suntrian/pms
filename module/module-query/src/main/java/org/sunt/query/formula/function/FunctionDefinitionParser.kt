package org.sunt.query.formula.function

import org.antlr.v4.runtime.CharStreams
import org.antlr.v4.runtime.CommonTokenStream
import org.antlr.v4.runtime.misc.ParseCancellationException
import org.slf4j.LoggerFactory
import org.sunt.query.define.SqlDialect
import org.sunt.query.formula.function.parser.FunctionLexer
import org.sunt.query.formula.function.parser.FunctionParser
import java.net.URI
import java.util.*
import java.util.concurrent.ConcurrentHashMap

object FunctionDefinitionParser {

    private val log = LoggerFactory.getLogger(FunctionDefinitionParser.javaClass)
    private val functionDefUri = URI("classpath://function/function.kt")
    private val dialectCache: MutableMap<SqlDialect, Map<String, List<FunctionDefinition>>> = ConcurrentHashMap()

    @JvmStatic
    @JvmOverloads
    fun loadFunctions(
        dialect: SqlDialect = SqlDialect.DEFALUT,
        category: String? = null
    ): Map<String, List<FunctionDefinition>> {
        var dialectFunctions = dialectCache[dialect]
        if (dialectFunctions == null) {
            synchronized(dialectCache) {
                dialectFunctions = dialectCache[dialect]
                if (dialectFunctions == null) {
                    val dialectName = dialect.name.takeIf { it != "DEFAULT" } ?: "Common"
                    val functionGroup = _loadFunctions(functionDefUri)[dialectName]
                        ?: throw IllegalStateException("未找到${dialectName}的函数定义")
                    dialectFunctions = mergeFunction(functionGroup)
                    dialectFunctions!!.forEach { (_, defs) -> defs.forEach { it.setDialect(dialect) } }
                    dialectCache[dialect] = dialectFunctions!!
                }
            }
        }
        if (category != null) {
            val categoryFunctionMap = TreeMap<String, List<FunctionDefinition>>(String.CASE_INSENSITIVE_ORDER)
            for ((funcName, funcDefs) in dialectFunctions!!) {
                val categoryDefs = funcDefs.filter { it.categories.contains(category) }
                if (categoryDefs.isNotEmpty()) {
                    categoryFunctionMap[funcName] = categoryDefs
                }
            }
            dialectFunctions = categoryFunctionMap
        }
        return dialectFunctions!!
    }

    @JvmStatic
    @JvmOverloads
    fun listFunctions(dialect: SqlDialect = SqlDialect.DEFALUT, category: String? = null): List<FunctionDefinition> {
        return loadFunctions(dialect, category).values.flatten()
    }

    private fun _loadFunctions(uri: URI): Map<String, FunctionGroup> {
        val inputStream = when (uri.scheme) {
            "classpath" -> FunctionDefinitionParser.javaClass.getResourceAsStream(
                "/${uri.host}${
                    if (uri.path.startsWith(
                            '/'
                        )
                    ) uri.path else '/' + uri.path
                }"
            )
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
                        function.implement = functions.firstOrNull { it.implement.isNotBlank() }?.implement ?: ""
                        if (function.implement.isBlank()) {
                            function.functionImplement =
                                functions.firstOrNull { it.functionImplement != null }?.functionImplement
                                    ?: throw IllegalStateException("函数${funcName}未提供转换SQL方法")
                        }
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
        val functionMap = TreeMap(parentFunctions as SortedMap<String, List<FunctionDefinition>>)
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