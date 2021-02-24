package org.sunt.formula.function

import org.sunt.formula.define.DataType
import org.sunt.formula.function.parser.*
import java.util.*
import kotlin.collections.HashMap
import kotlin.collections.HashSet

class FunctionDefinitionListener : FunctionParserBaseListener() {

    val functionGroups: MutableMap<String, FunctionGroup> = TreeMap(String.CASE_INSENSITIVE_ORDER)
    private lateinit var dialect: String
    private lateinit var functionDefinitions: MutableList<FunctionDefinition>
    private lateinit var functionArguments: MutableList<FunctionDefinition.FunctionArgument>
    private lateinit var typeParameters: MutableSet<String>
    private lateinit var overloadFunctions: MutableMap<String, MutableList<FunctionDefinition>>

    override fun enterDialectInterface(ctx: FunctionParser.DialectInterfaceContext) {
        this.dialect = "Common"
        this.functionDefinitions = LinkedList()
        this.overloadFunctions = HashMap()
    }

    override fun exitDialectInterface(ctx: FunctionParser.DialectInterfaceContext) {
        this.dialect = ctx.simpleIdentifier().text
        val functionGroup = FunctionGroup(this.dialect, this.functionDefinitions)
        if (ctx.COLON() != null) {
            val parentDialect = ctx.identifier().text
            functionGroup.parentGroup = this.functionGroups[parentDialect]
        }
        this.functionGroups[this.dialect] = functionGroup
    }

    override fun enterFunctionDefine(ctx: FunctionParser.FunctionDefineContext?) {
        this.functionArguments = LinkedList()
        this.typeParameters = HashSet(2)
    }

    override fun exitFunctionDefine(ctx: FunctionParser.FunctionDefineContext) {
        val functionName = ctx.simpleIdentifier().text
        val dataType = ctx.dataType().text
        val functionDefinition = FunctionDefinition(functionName, DataType.of(dataType))
        functionDefinition.arguments = this.functionArguments
        if (ctx.functionModifierList() != null) {
            for (annotationContext in ctx.functionModifierList().annotation()) {
                val annotationType = annotationContext.identifier().text
                val annotationParams =
                    annotationContext.functionParamUsages()?.expression()?.map { it.text.trim('"') } ?: emptyList()
                when (annotationType) {
                    Category::class.simpleName, Category::class.qualifiedName -> {
                        functionDefinition.categories = annotationParams.toSet()
                    }
                    Alias::class.simpleName, Alias::class.qualifiedName -> {
                        functionDefinition.alias = annotationParams.toSet()
                    }
                    Description::class.simpleName, Description::class.qualifiedName -> {
                        functionDefinition.description =
                            annotationParams.getOrNull(0) ?: throw IllegalStateException("未提供函数描述内容")
                    }
                    Translate::class.simpleName, Translate::class.qualifiedName -> {
                        functionDefinition.implement =
                            annotationParams.getOrNull(0) ?: throw IllegalStateException("未提供函数转换SQL方法")
                    }
                    Overload::class.simpleName, Overload::class.qualifiedName -> {
                        functionDefinition.overload = true
                    }
                }
            }
            for (functionModifierContext in ctx.functionModifierList().functionModifier()) {
                functionDefinition.abstract = functionModifierContext.ABSTRACT() != null
                functionDefinition.override = functionModifierContext.OVERRIDE() != null
                functionDefinition.private = functionModifierContext.PRIVATE() != null
            }
        }

        if (this.typeParameters.contains(dataType)) {
            functionDefinition.typeParamIndex = this.functionArguments.first { it.genericType == dataType }.index
        }
        this.functionDefinitions.add(functionDefinition)

    }

    override fun exitTypeParameters(ctx: FunctionParser.TypeParametersContext) {
        for (dataTypeContext in ctx.dataType()) {
            this.typeParameters.add(dataTypeContext.text)
        }
    }

    override fun exitFunctionParamDefines(ctxx: FunctionParser.FunctionParamDefinesContext) {
        val paramSize = ctxx.functionParamDefine().size
        ctxx.functionParamDefine().forEachIndexed { index, ctx ->
            run {
                val paramName = ctx.simpleIdentifier().text
                val dataType = ctx.dataType().text
                val functionArgument = FunctionDefinition.FunctionArgument(paramName, index, DataType.of(dataType))
                if (this.typeParameters.contains(dataType)) {
                    functionArgument.genericType = dataType
                }
                if (ctx.functionParamModifierList() != null) {
                    for (annotationContext in ctx.functionParamModifierList().annotation()) {
                        val annotationType = annotationContext.identifier().text
                        val annotationParams = annotationContext.functionParamUsages().expression().map { it.text }
                        when (annotationType) {
                            Option::class.simpleName, Option::class.qualifiedName -> {
                                functionArgument.optionValues = annotationParams.toSet()
                            }
                        }
                    }
                    for (functionParamModifierContext in ctx.functionParamModifierList().functionParamModifier()) {
                        val varargs = functionParamModifierContext.VARARG() != null
                        if (varargs && index != paramSize - 1) {
                            throw IllegalStateException("vararg可变参数仅可为最后一个参数")
                        }
                        functionArgument.vararg = varargs
                    }
                }
                if (ctx.expression() != null) {
                    val defaultValue = ctx.expression().text
                    functionArgument.defaultValue = defaultValue
                }
                this.functionArguments.add(functionArgument)
            }
        }
    }

}