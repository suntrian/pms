package org.sunt.formula.function

import bsh.EvalError
import bsh.Interpreter
import org.sunt.formula.define.DataType
import org.sunt.formula.function.parser.FunctionTranslator
import java.util.regex.Pattern

class FunctionDefinition(val funcName: String) {

    var dataType: DataType = DataType.NONE

    var categories: Set<String> = emptySet()
    var alias: Set<String> = emptySet()
    var arguments: FunctionArgumentList = FunctionArgumentList.emptyList
    var description: String = ""
    internal var implement: String = ""
        set(value) {
            field = value
            if (value.isNotBlank()) {
                this.functionImplement = FunctionImplement(this.funcName, this.implement)
            }
        }


    internal var functionImplement: FunctionImplement? = null

    var genericTypes: List<String> = emptyList()
    var typeParamIndex: Int? = null

    var private = false
    var abstract = false
    var override = false
    var overload = false

    constructor(funcName: String, dataType: DataType) : this(funcName) {
        this.dataType = dataType
    }

    internal fun setFunctionImplement(translatorClass: String, constructArgs: Array<String>) {
        this.functionImplement = FunctionImplement(this.funcName, translatorClass, constructArgs)
    }

    fun translate(params: List<StatementInfo?>): String {
        return this.functionImplement?.translate(this.arguments, params) ?: ""
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
                " : ${if (typeParamIndex != null) "$${typeParamIndex}.type" else dataType} -> $implement "
    }

    fun getDefinition(): String {
        val builder = StringBuilder()
        if (private) builder.append("private ")
        if (abstract) builder.append("abstract ")
        if (override) builder.append("override ")
        builder.append("fun ")
        if (genericTypes.isNotEmpty()) builder.append("<").append(genericTypes.joinToString(", ")).append("> ")
        builder.append(funcName).append("( ")
        for (argument in arguments) {
            if (argument.vararg) builder.append("vararg ")
            builder.append(argument.name).append(": ").append(argument.genericType ?: argument.dataType)
            if (argument.nullable) builder.append("?")
            builder.append(", ")
        }
        builder.append(")").append(":")
        if (typeParamIndex != null) {
            builder.append(arguments[typeParamIndex!!].genericType)
        } else {
            builder.append(dataType)
        }
        return builder.toString()
    }

    class FunctionArgument(val name: String, val index: Int, val dataType: DataType) {

        var vararg: Boolean = false
        var optionValues: List<Any> = emptyList()
        var defaultValue: Any? = null
        var genericType: String? = null
        var nullable: Boolean = false
        var constant: Boolean = false
        var reserved: List<String> = emptyList()
        var suggest: TokenItem = TokenItem.NONE()

        @JvmOverloads
        fun match(expr: String, dataType: DataType, tokenItem: TokenItem?, genericRealType: DataType? = null): Boolean {
            if (!this.dataType.isAssignableFrom(dataType)) {
                return false
            }
            if (genericType != null && genericRealType?.isAssignableFrom(dataType) == false) {
                return false
            }
            if (this.reserved.isNotEmpty() && !reserved.contains(expr.toUpperCase())) {
                return false
            }
            if (this.reserved.isEmpty() && tokenItem?.scope?.equals(TokenScope.RESERVED) == true) {
                return false
            }
            if (optionValues.isNotEmpty() && !optionValues.contains(expr)) {
                return false
            }
            if (constant && !(numberRegex.matches(expr) || stringRegex.matches(expr))) {
                return false
            }
            if (!nullable && "null".equals(expr, true)) {
                return false
            }
            return true
        }

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

        companion object {
            private val numberRegex = Regex("[-+]?\\d+(\\.\\d+)?")
            private val stringRegex = Regex("(['\"]).*\\1")
        }
    }

    class FunctionArgumentList(private val arguments: List<FunctionArgument>) : List<FunctionArgument> by arguments {

        init {
            val iter = arguments.iterator()
            for (functionArgument in iter) {
                if (functionArgument.vararg && iter.hasNext()) {
                    throw IllegalStateException("可变参数只可用于最后一个参数")
                }
            }
        }

        override fun get(index: Int): FunctionArgument {
            if (index >= 0 && index < arguments.size) {
                return arguments[index]
            } else {
                val last = arguments.lastOrNull() ?: throw IndexOutOfBoundsException(index)
                if (last.vararg) {
                    return last
                }
                throw IndexOutOfBoundsException(index)
            }
        }

        fun getOrNull(index: Int): FunctionArgument? {
            return try {
                get(index)
            } catch (e: IndexOutOfBoundsException) {
                return null
            }
        }

        fun getOrElse(index: Int, defaultValue: (Int) -> FunctionArgument?): FunctionArgument? {
            return getOrNull(index) ?: defaultValue(index)
        }

        override fun equals(other: Any?): Boolean {
            if (this === other) return true
            if (other !is FunctionArgumentList) return false
            if (arguments.size != other.arguments.size) return false
            val thisIter = arguments.iterator()
            val otherIter = other.arguments.iterator()
            while (thisIter.hasNext() && otherIter.hasNext()) {
                if (thisIter.next() != otherIter.next()) {
                    return false
                }
            }
            return true
        }

        override fun hashCode(): Int {
            return arguments.hashCode()
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

        override fun translate(
            funcName: String,
            expectArgs: List<FunctionArgument>,
            actualArgs: List<StatementInfo?>
        ): String {
            return Companion.translate(this.funcName, this.implement, expectArgs, actualArgs.map { it?.expression })
        }

        fun translate(expectArgs: List<FunctionArgument>, actualArgs: List<StatementInfo?>): String {
            return if (this.implement.isNotBlank()) {
                Companion.translate(funcName, implement, expectArgs, actualArgs.map { it?.expression })
            } else if (this.translator != null) {
                this.translator!!.translate(funcName, expectArgs, actualArgs)
            } else throw IllegalStateException("${funcName}未提供转换SQL方法")
        }

        companion object {
            //$1, $2, $3 按索引使用参数,  ${value} 按名称使用参数
            private val PARAM_PATTERN = Pattern.compile("\\$((?<idx>\\d+)|\\{(?<arg>\\S+?)})")!!
            private val RAW_CODE_PATTERN = Pattern.compile("\\{(.*)}", Pattern.DOTALL)!!
            private val CODE_INTERPRETER = Interpreter()

            fun translate(
                funcName: String,
                implement: String,
                arguments: List<FunctionArgument>,
                idxParams: List<String?>,
                namedParams: Map<String, String?> = emptyMap()
            ): String {
                var matcher = PARAM_PATTERN.matcher(implement)
                val sqlBuffer = StringBuffer()
                var lastMatchedIndex = 0
                while (matcher.find()) {
                    val idx: Int
                    val name: String
                    if (matcher.group("idx").also { idx = it.toInt() } != null) {
                        if (idx > lastMatchedIndex) lastMatchedIndex = idx
                        if (idxParams.size < idx) {
                            if (arguments.size < idx || arguments[idx - 1].defaultValue == null) {
                                throw IllegalStateException("函数${funcName}参数个数不符")
                            }
                            matcher.appendReplacement(sqlBuffer, arguments[idx - 1].defaultValue.toString())
                        } else {
                            if (idx == 0) {
                                matcher.appendReplacement(sqlBuffer, idxParams.drop(lastMatchedIndex).joinToString(","))
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
                            ?: throw IllegalStateException("函数${funcName}转换方法${rawCode}返回为空")
                        matcher.appendReplacement(sqlBuffer2, result.toString())
                    } catch (e: EvalError) {
                        throw IllegalStateException("函数${funcName}转换方法${rawCode}执行错误", e)
                    }
                }
                matcher.appendTail(sqlBuffer2)
                return sqlBuffer2.toString()
            }

        }

    }

}