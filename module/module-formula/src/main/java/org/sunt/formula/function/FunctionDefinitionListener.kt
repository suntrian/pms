package org.sunt.formula.function

import org.sunt.formula.define.DataType
import org.sunt.formula.function.parser.*
import org.sunt.formula.suggestion.SuggestionItem
import java.util.*
import kotlin.collections.HashMap
import kotlin.collections.HashSet

class FunctionDefinitionListener : FunctionParserBaseListener() {

    val functionGroups: MutableMap<String, FunctionGroup> = TreeMap(String.CASE_INSENSITIVE_ORDER)
    private val customDataType: MutableMap<String, String> = TreeMap(String.CASE_INSENSITIVE_ORDER)
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

    override fun exitClassDeclare(ctx: FunctionParser.ClassDeclareContext) {
        val className = ctx.identifier(0).text
        val superClassName = if (ctx.identifier().size > 1) {
            ctx.identifier(1).text
        } else "Any"
        this.customDataType[className] = superClassName
    }

    override fun enterFunctionDefine(ctx: FunctionParser.FunctionDefineContext?) {
        this.functionArguments = LinkedList()
        this.typeParameters = HashSet(2)
    }

    override fun exitFunctionDefine(ctx: FunctionParser.FunctionDefineContext) {
        val functionName = ctx.simpleIdentifier().text
        val dataType = ctx.dataTypeNull().dataType().text
        val functionDefinition = FunctionDefinition(functionName, DataType.of(dataType))
        functionDefinition.arguments = FunctionDefinition.FunctionArgumentList(this.functionArguments)
        if (ctx.functionModifierList() != null) {
            for (annotationContext in ctx.functionModifierList().annotation()) {
                val annotationType = annotationContext.identifier().text
                val annotationParams =
                    annotationContext.functionParams()?.expression()?.map { it.text.trim('"') } ?: emptyList()
                when (annotationType) {
                    Category::class.simpleName, Category::class.qualifiedName -> {
                        functionDefinition.categories = annotationParams.toSet()
                    }
                    Alias::class.simpleName, Alias::class.qualifiedName -> {
                        functionDefinition.alias = annotationParams.toSet()
                    }
                    Description::class.simpleName, Description::class.qualifiedName -> {
                        functionDefinition.description =
                            annotationParams.getOrNull(0) ?: throw IllegalStateException("${functionName}未提供描述内容")
                    }
                    Translate::class.simpleName, Translate::class.qualifiedName -> {
                        functionDefinition.implement =
                            annotationParams.getOrNull(0) ?: throw IllegalStateException("${functionName}未提供转换SQL方法")
                        if (annotationParams.size > 1) {
                            val translatorClass = annotationParams[1].substringBefore("::")
                            val constructorArgs =
                                if (annotationParams.size > 2) annotationParams.subList(2, annotationParams.size)
                                    .toTypedArray() else emptyArray()
                            functionDefinition.setFunctionImplement(translatorClass, constructorArgs)
                        }
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
        for (dataTypeContext in ctx.dataTypeNull()) {
            this.typeParameters.add(dataTypeContext.dataType().text)
        }
    }

    override fun exitFunctionParamDefines(ctxx: FunctionParser.FunctionParamDefinesContext) {
        val paramSize = ctxx.functionParamDefine().size
        ctxx.functionParamDefine().forEachIndexed { index, ctx ->
            run {
                val paramName = ctx.simpleIdentifier().text
                val dataType = ctx.dataTypeNull().dataType().text
                val nullable = ctx.dataTypeNull().QUESTION() != null
                val functionArgument = FunctionDefinition.FunctionArgument(paramName, index, DataType.of(dataType))
                functionArgument.nullable = nullable
                if (this.typeParameters.contains(dataType)) {
                    functionArgument.genericType = dataType
                }
                if (ctx.functionParamModifierList() != null) {
                    for (annotationContext in ctx.functionParamModifierList().annotation()) {
                        val annotationType = annotationContext.identifier().text
                        val annotationParams =
                            annotationContext.functionParams()?.expression()?.map { it.text.trim('"') } ?: emptyList()
                        when (annotationType) {
                            Option::class.simpleName, Option::class.qualifiedName -> {
                                functionArgument.optionValues = annotationParams
                            }
                            Constant::class.simpleName, Constant::class.qualifiedName -> {
                                functionArgument.constant = true
                            }
                            Reserved::class.simpleName, Reserved::class.qualifiedName -> {
                                functionArgument.reserved = annotationParams
                            }
                            Suggest::class.simpleName, Suggest::class.qualifiedName -> {
                                if (annotationParams.size == 2) {
                                    functionArgument.suggest =
                                        SuggestionItem.of(annotationParams[1], annotationParams[0])
                                }
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