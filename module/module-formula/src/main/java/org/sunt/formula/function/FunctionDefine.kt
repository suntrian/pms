package org.sunt.formula.function

import bsh.EvalError
import bsh.Interpreter
import org.sunt.formula.define.DataType
import java.util.regex.Pattern

class FunctionDefine(val funcName: String) {

    var dataType: DataType = DataType.NONE

    val categories: Set<String> = mutableSetOf();
    val alias: Set<String> = mutableSetOf()
    val args: List<FunctionArgDefine> = mutableListOf();

    var description: String = ""
    var implement: String = ""
        set(imp) {
            field = imp
            this.functionImplement = FunctionImplement(this.funcName, this.implement)
        }

    var typeParamIndex: Int? = null
    private var functionImplement: FunctionImplement? = null

    constructor(funcName: String, dataType: DataType) : this(funcName) {
        this.dataType = dataType
    }

    override fun toString(): String {
        return "[${categories.joinToString(",")}]::" +
                "$funcName( ${args.joinToString { "${if (it.vararg) "vararg " else ""} ${it.name}:${it.dataType} ${if (it.enumValues.isEmpty()) "" else it.enumValues.joinToString(",", "[", "]")}" }} ) " +
                ": ${dataType.name}"
    }

    override fun equals(other: Any?): Boolean {
        if (this === other) return true
        if (javaClass != other?.javaClass) return false

        other as FunctionDefine

        if (funcName != other.funcName) return false
        if (dataType != other.dataType) return false
        if (args != other.args) return false
        if (typeParamIndex != other.typeParamIndex) return false

        return true
    }

    override fun hashCode(): Int {
        var result = funcName.hashCode()
        result = 31 * result + dataType.hashCode()
        result = 31 * result + args.hashCode()
        result = 31 * result + (typeParamIndex ?: 0)
        return result
    }

}

class FunctionArgDefine(val name: String, val dataType: DataType) {

    var vararg: Boolean = false
    var enumValues: Set<Any> = mutableSetOf()

    override fun equals(other: Any?): Boolean {
        if (this === other) return true
        if (javaClass != other?.javaClass) return false

        other as FunctionArgDefine

        if (name != other.name) return false
        if (dataType != other.dataType) return false
        if (vararg != other.vararg) return false

        return true
    }

    override fun hashCode(): Int {
        var result = name.hashCode()
        result = 31 * result + dataType.hashCode()
        result = 31 * result + vararg.hashCode()
        return result
    }


}


class FunctionImplement(private val funcName: String, private val implement: String) {

    companion object {

        val PARAM_PATTERN = Pattern.compile("\\$((?<idx>\\d+)|\\{(?<arg>\\S+?)})")!!
        val RAW_CODE_PATTERN = Pattern.compile("\\{(.*)}", Pattern.DOTALL)!!
        val CODE_INTERPRETER = Interpreter()
    }

    fun translate(vararg params: String): String {
        var matcher = PARAM_PATTERN.matcher(this.implement)
        val sqlBuffer = StringBuffer()
        while (matcher.find()) {
            val idx: Int
            val name: String
            if (matcher.group("idx").also { idx = it.toInt() } != null) {
                if (params.size < idx) {
                    throw IllegalStateException("函数${funcName}参数个数不符")
                }
                if (idx == 0) {
                    matcher.appendReplacement(sqlBuffer, params.joinToString(","))
                } else {
                    matcher.appendReplacement(sqlBuffer, params[idx - 1])
                }
            } else if (matcher.group("arg").also { name = it } != null) {
                //todo 按参数名称替换参数

            }
        }
        matcher.appendTail(sqlBuffer)
        matcher = RAW_CODE_PATTERN.matcher(sqlBuffer)
        val sqlBuffer2 = StringBuffer()
        while (matcher.find()) {
            val rawCode = matcher.group(1)
            try {
                val result: Any = CODE_INTERPRETER.eval(rawCode) ?: throw IllegalStateException("函数${funcName}转换方法${implement}返回为空")
                matcher.appendReplacement(sqlBuffer2, result.toString())
            } catch (e: EvalError) {
                throw IllegalStateException("函数${funcName}转换方法${implement}执行错误", e)
            }
        }
        matcher.appendTail(sqlBuffer2)
        return sqlBuffer2.toString()
    }

}