package org.sunt.formula.function

import bsh.EvalError
import bsh.Interpreter
import org.sunt.formula.define.DataType
import org.sunt.formula.function.parser.FunctionTranslator
import org.sunt.formula.suggestion.SuggestionItem
import java.util.regex.Pattern

class FunctionDefinition(val funcName: String) {

    var dataType: DataType = DataType.NONE

    var categories: Set<String> = emptySet()
    var alias: Set<String> = emptySet()
    var arguments: FunctionArgumentList = FunctionArgumentList.emptyList
    var description: String = ""
    var implement: String = ""
        set(value) {
            field = value
            if (value.isNotBlank()) {
                this.functionImplement = FunctionImplement(this.funcName, this.implement)
            }
        }


    private var functionImplement: FunctionImplement? = null


    var typeParamIndex: Int? = null

    var private = false
    var abstract = false
    var override = false
    var overload = false

    constructor(funcName: String, dataType: DataType) : this(funcName) {
        this.dataType = dataType
    }

    fun setFunctionImplement(translatorClass: String, constructArgs: Array<String>) {
        this.functionImplement = FunctionImplement(this.funcName, translatorClass, constructArgs)
    }

    fun translate(vararg params: String): String {
        return this.functionImplement?.translate(this.arguments, params) ?: ""
    }

    @JvmOverloads
    fun translate(idxParams: List<String>, namedParams: Map<String, String> = emptyMap()): String {
        return this.functionImplement?.translate(this.arguments, idxParams.toTypedArray(), namedParams) ?: ""
    }

    override fun equals(other: Any?): Boolean {
        if (this === other) return true
        if (other !is FunctionDefinition) return false

        if (funcName != other.funcName) return false
        if (dataType != other.dataType) return false
        if (arguments != other.arguments) return false
        if (typeParamIndex != other.typeParamIndex) return false

        return true
    }

    override fun hashCode(): Int {
        var result = funcName.hashCode()
        result = 31 * result + dataType.hashCode()
        result = 31 * result + arguments.hashCode()
        result = 31 * result + (typeParamIndex ?: 0)
        return result
    }

    override fun toString(): String {
        return "${description}\n[${categories.joinToString(",")}]::" +
                " $funcName( ${
                    arguments.joinToString {
                        "${if (it.vararg) "vararg " else ""} ${it.name}:${it.dataType} ${
                            if (it.optionValues.isEmpty()) "" else it.optionValues.joinToString(
                                ",",
                                "[",
                                "]"
                            )
                        }"
                    }
                } ) " +
                " : ${if (typeParamIndex != null) "$${typeParamIndex}.type" else dataType.name} -> $implement "
    }


    class FunctionArgument(val name: String, val index: Int, val dataType: DataType) {

        var vararg: Boolean = false
        var optionValues: List<Any> = emptyList()
        var defaultValue: Any? = null
        var genericType: String? = null
        var nullable: Boolean = false
        var constant: Boolean = false
        var reserved: List<String> = emptyList()
        var suggest: SuggestionItem = SuggestionItem.NONE()

        override fun equals(other: Any?): Boolean {
            if (this === other) return true
            if (other !is FunctionArgument) return false

            //if (name != other.name) return false
            if (index != other.index) return false
            if (dataType != other.dataType) return false
            if (vararg != other.vararg) return false
            //if (defaultValue != other.defaultValue) return false

            return true
        }

        override fun hashCode(): Int {
            var result = name.hashCode()
            result = 31 * result + index
            result = 31 * result + dataType.hashCode()
            result = 31 * result + vararg.hashCode()
            result = 31 * result + (defaultValue?.hashCode() ?: 0)
            return result
        }

    }

    class FunctionArgumentList(private val arguments: List<FunctionArgument>) : List<FunctionArgument> by arguments {

        override fun get(index: Int): FunctionArgument {
            if (index < arguments.size) {
                return arguments[index]
            } else {
                val last = arguments.lastOrNull() ?: throw IndexOutOfBoundsException(index)
                if (last.vararg) {
                    return last
                }
                throw IndexOutOfBoundsException(index)
            }
        }

        companion object {
            val emptyList = FunctionArgumentList(emptyList())
        }

    }

    class FunctionImplement(private val funcName: String, private val implement: String) : FunctionTranslator {

        private var translator: FunctionTranslator? = null

        constructor(funcName: String, translatorClass: String, constructArgs: Array<String>) : this(funcName, "") {
            this.translator = FunctionTranslator.of(translatorClass, constructArgs)
        }

        override fun translate(funcName: String, funcArgs: List<FunctionArgument>, args: Array<out String?>): String {
            return Companion.translate(this.funcName, this.implement, funcArgs, args)
        }

        fun translate(
            arguments: List<FunctionArgument>,
            idxParams: Array<out String>,
            namedParams: Map<String, String> = emptyMap()
        ): String {
            return if (this.implement.isNotBlank()) {
                translateByScript(arguments, idxParams, namedParams)
            } else if (this.translator != null) {
                translateByClass(arguments, idxParams, namedParams)
            } else throw IllegalStateException("${funcName}未提供转换SQL方法")
        }

        private fun translateByScript(
            arguments: List<FunctionArgument>,
            idxParams: Array<out String>,
            namedParams: Map<String, String> = emptyMap()
        ): String {
            return Companion.translate(funcName, implement, arguments, idxParams, namedParams)
        }

        @Suppress("unused")
        private fun translateByClass(
            arguments: List<FunctionArgument>,
            idxParams: Array<out String>,
            namedParams: Map<String, String> = emptyMap()
        ): String {
            return this.translator!!.translate(funcName, arguments, idxParams)
        }

        companion object {
            //$1, $2, $3 按索引使用参数,  ${value} 按名称使用参数
            val PARAM_PATTERN = Pattern.compile("\\$((?<idx>\\d+)|\\{(?<arg>\\S+?)})")!!
            val RAW_CODE_PATTERN = Pattern.compile("\\{(.*)}", Pattern.DOTALL)!!
            val CODE_INTERPRETER = Interpreter()

            fun translate(
                funcName: String,
                implement: String,
                arguments: List<FunctionArgument>,
                idxParams: Array<out String?>,
                namedParams: Map<String, String?> = emptyMap()
            ): String {
                var matcher = PARAM_PATTERN.matcher(implement)
                val sqlBuffer = StringBuffer()
                while (matcher.find()) {
                    val idx: Int
                    val name: String
                    if (matcher.group("idx").also { idx = it.toInt() } != null) {
                        if (idxParams.size < idx) {
                            if (arguments.size < idx || arguments[idx - 1].defaultValue == null) {
                                throw IllegalStateException("函数${funcName}参数个数不符")
                            }
                            matcher.appendReplacement(sqlBuffer, arguments[idx - 1].defaultValue.toString())
                        } else {
                            if (idx == 0) {
                                matcher.appendReplacement(sqlBuffer, idxParams.joinToString(","))
                            } else {
                                matcher.appendReplacement(sqlBuffer, idxParams[idx - 1])
                            }
                        }
                    } else if (matcher.group("arg").also { name = it } != null) {
                        if (namedParams[name] != null) {
                            matcher.appendReplacement(sqlBuffer, namedParams[name])
                        } else {
                            matcher.appendReplacement(
                                sqlBuffer,
                                arguments.firstOrNull { it.name == name }?.defaultValue?.toString()
                                    ?: throw IllegalStateException("函数${funcName}未提供参数${name}")
                            )
                        }
                    }
                }
                matcher.appendTail(sqlBuffer)
                matcher = RAW_CODE_PATTERN.matcher(sqlBuffer)
                val sqlBuffer2 = StringBuffer()
                while (matcher.find()) {
                    val rawCode = matcher.group(1)
                    try {
                        val result: Any = CODE_INTERPRETER.eval(rawCode)
                            ?: throw IllegalStateException("函数${funcName}转换方法${implement}返回为空")
                        matcher.appendReplacement(sqlBuffer2, result.toString())
                    } catch (e: EvalError) {
                        throw IllegalStateException("函数${funcName}转换方法${implement}执行错误", e)
                    }
                }
                matcher.appendTail(sqlBuffer2)
                return sqlBuffer2.toString()
            }

        }

    }

}