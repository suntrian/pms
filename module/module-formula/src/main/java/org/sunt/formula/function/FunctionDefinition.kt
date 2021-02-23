package org.sunt.formula.function

import bsh.EvalError
import bsh.Interpreter
import org.sunt.formula.define.DataType
import java.util.regex.Pattern

class FunctionDefinition(val funcName: String) {

    var dataType: DataType = DataType.NONE

    var categories: Set<String> = emptySet()
    var alias: Set<String> = emptySet()
    var arguments: List<FunctionArgument> = emptyList()
    var description: String = ""
    var implement: String = ""
        set(value) {
            field = value
            this.functionImplement = FunctionImplement(this.funcName, this.implement)
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

    fun translate(vararg params: String): String {
        return this.functionImplement?.translate(params) ?: ""
    }

    @JvmOverloads
    fun translate(idxParams: List<String>, namedParams: Map<String, String> = emptyMap()): String {
        return this.functionImplement?.translate(idxParams.toTypedArray(), namedParams) ?: ""
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
        var optionValues: Set<Any> = emptySet()
        var defaultValue: Any? = null
        var genericType: String? = null

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

    class FunctionImplement(private val funcName: String, private val implement: String) {

        fun translate(idxParams: Array<out String>, namedParams: Map<String, String> = emptyMap()): String {
            var matcher = PARAM_PATTERN.matcher(this.implement)
            val sqlBuffer = StringBuffer()
            while (matcher.find()) {
                val idx: Int
                val name: String
                if (matcher.group("idx").also { idx = it.toInt() } != null) {
                    if (idxParams.size < idx) {
                        throw IllegalStateException("函数${funcName}参数个数不符")
                    }
                    if (idx == 0) {
                        matcher.appendReplacement(sqlBuffer, idxParams.joinToString(","))
                    } else {
                        matcher.appendReplacement(sqlBuffer, idxParams[idx - 1])
                    }
                } else if (matcher.group("arg").also { name = it } != null) {
                    if (namedParams[name] != null) {
                        matcher.appendReplacement(sqlBuffer, namedParams[name])
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

        companion object {
            //$1, $2, $3 按索引使用参数,  ${value} 按名称使用参数
            val PARAM_PATTERN = Pattern.compile("\\$((?<idx>\\d+)|\\{(?<arg>\\S+?)})")!!
            val RAW_CODE_PATTERN = Pattern.compile("\\{(.*)}", Pattern.DOTALL)!!
            val CODE_INTERPRETER = Interpreter()
        }

    }

}