package org.sunt.formula.function

import org.antlr.v4.runtime.CharStreams
import org.antlr.v4.runtime.CommonTokenStream
import org.antlr.v4.runtime.misc.ParseCancellationException
import org.slf4j.LoggerFactory
import org.sunt.formula.define.SqlProduct
import org.sunt.formula.function.parser.FunctionLexer
import org.sunt.formula.function.parser.FunctionParser
import java.net.URI
import java.util.*

object FunctionDefineParser {

    private val logger = LoggerFactory.getLogger(FunctionDefineParser.javaClass)

    private val defaultProductFunctionMap: MutableMap<String, List<FunctionDefine>> = TreeMap(String.CASE_INSENSITIVE_ORDER)
    private val productFunctionMap: MutableMap<SqlProduct, Map<String, List<FunctionDefine>>> = EnumMap(SqlProduct::class.java)

    fun getFunctionMapByProduct(product: SqlProduct): Map<String, List<FunctionDefine>> {
        if (this.defaultProductFunctionMap.isEmpty()) {
            synchronized(this.defaultProductFunctionMap) {
                if (this.defaultProductFunctionMap.isEmpty()) {
                    loadDefaultFunction()
                }
            }
        }

        if (!this.productFunctionMap.containsKey(product)) {
            synchronized(this.productFunctionMap) {
                if (!this.productFunctionMap.containsKey(product)) {
                    var functionMap = loadFunction(URI("classpath://function/function_${product.name.toLowerCase()}.lst")).groupBy { it.funcName }
                    functionMap = mergeFunction(functionMap)
                    this.productFunctionMap[product] = functionMap
                }
            }
        }
        return this.productFunctionMap.getOrDefault(product, Collections.emptyMap()).toMap()
    }

    private fun loadFunction(uri: URI): List<FunctionDefine> {
        val inputStream = when (uri.scheme) {
            "classpath" -> this.javaClass.getResourceAsStream("/${uri.host}/${uri.path}")
            else -> uri.toURL().openStream()
        }

        val functionLexer = FunctionLexer(CharStreams.fromStream(inputStream))
        val tokenStream = CommonTokenStream(functionLexer)
        val functionParser = FunctionParser(tokenStream)
        val functionListener = FunctionDefineListener(tokenStream);
        functionParser.addParseListener(functionListener)
        try {
            functionParser.root()
            logger.info("函数定义文件{}加载成功", uri)
        } catch (e: ParseCancellationException) {
            logger.error("解析Formula函数列表失败", e)
        }
        return functionListener.functions
    }

    private fun mergeFunction(productMap: Map<String, List<FunctionDefine>>): Map<String, List<FunctionDefine>> {
        val defaultProductMapCopy: MutableMap<String, List<FunctionDefine>> = TreeMap(String.CASE_INSENSITIVE_ORDER)
        defaultProductMapCopy.putAll(this.defaultProductFunctionMap)
        for ((funcName, defines) in productMap) {
            if (defaultProductMapCopy.containsKey(funcName)) {
                val defaultDefines = defaultProductMapCopy.getOrDefault(funcName, mutableListOf()).toMutableList()
                for (define in defines) {
                    defaultDefines.remove(define)
                    defaultDefines.add(define)
                }
            } else {
                defaultProductMapCopy[funcName] = defines
            }
        }
        return defaultProductMapCopy
    }

    private fun loadDefaultFunction() {
        val functionDefines = loadFunction(URI("classpath://function/function.lst"))
        this.defaultProductFunctionMap.putAll(functionDefines.groupBy { it.funcName })
    }
}