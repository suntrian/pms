package org.sunt.formula.function.parser

import org.sunt.formula.FormulaHelper
import org.sunt.formula.define.DataType
import org.sunt.formula.define.IColumn
import org.sunt.formula.define.SqlDialect
import org.sunt.formula.function.FunctionDefinition
import org.sunt.formula.function.StatementInfo
import java.util.regex.Pattern


interface FunctionTranslator {

    /**
     * @param funcName 待转换的函数的名称，非SQL函数
     * @param expectArgs 待转换的函数的参数定义
     * @param actualArgs 实际的参数
     */
    fun translate(
        funcName: String,
        dialect: SqlDialect,
        expectArgs: List<FunctionDefinition.FunctionArgument>,
        actualArgs: List<StatementInfo?>
    ): String

    //fun translate(args: Map<String, String>): String

    companion object {
        @JvmStatic
        fun of(name: String, args: Array<out String>): FunctionTranslator {
            return when (name) {
                PartitionOrderTranslator::class.simpleName -> PartitionOrderTranslator(
                    args.getOrNull(0) ?: throw IllegalStateException("PartitionOrderTranslator未提供函数名称")
                )
                PreDefinedPartitionOrderTranslator::class.simpleName -> {
                    PreDefinedPartitionOrderTranslator(args[0], args[1].toInt(), args[2].toInt())
                }
                PreDefinedPartitionOrderFrameTranslator::class.simpleName -> {
                    PreDefinedPartitionOrderFrameTranslator(
                        args[0],
                        args[1].toInt(),
                        args[2].toInt(),
                        args[3].toInt(),
                        args[4].toInt(),
                        args[5].toInt()
                    )
                }
                LagLeadTranslator::class.simpleName -> {
                    LagLeadTranslator(args[0])
                }
                RawSqlTranslator::class.simpleName -> {
                    RawSqlTranslator
                }
                else -> EmptyTranslator
            }
        }
    }

}

object EmptyTranslator : FunctionTranslator {
    override fun translate(
        funcName: String,
        dialect: SqlDialect,
        expectArgs: List<FunctionDefinition.FunctionArgument>,
        actualArgs: List<StatementInfo?>
    ): String {
        throw IllegalAccessException("not supported")
    }
}

data class PartitionOrderTranslator(val funcName: String) : FunctionTranslator {

    override fun translate(
        funcName: String,
        dialect: SqlDialect,
        expectArgs: List<FunctionDefinition.FunctionArgument>,
        actualArgs: List<StatementInfo?>
    ): String {
        val builder = StringBuilder(this.funcName).append("() OVER (")
        renderPartitionAndOrderBy(
            builder,
            partition = actualArgs.getOrNull(0)?.expression,
            orderBy = actualArgs.getOrNull(1)?.expression
        )
        return builder.append(")").toString()
    }

}

data class PreDefinedPartitionOrderTranslator(val expression: String, val partitionIndex: Int, val orderByIndex: Int) :
    FunctionTranslator {

    override fun translate(
        funcName: String,
        dialect: SqlDialect,
        expectArgs: List<FunctionDefinition.FunctionArgument>,
        actualArgs: List<StatementInfo?>
    ): String {
        val actualArgStrs = actualArgs.map { it?.expression }
        val translatedExpr =
            FunctionDefinition.FunctionImplement.translate(funcName, expression, expectArgs, actualArgStrs)
        val builder = StringBuilder(translatedExpr).append(" OVER (")
        renderPartitionAndOrderBy(
            builder,
            partition = actualArgStrs.getOrNull(partitionIndex),
            orderBy = actualArgStrs.getOrNull(orderByIndex)
        )
        return builder.append(")").toString()
    }

}

data class PreDefinedPartitionOrderFrameTranslator(
    val expression: String,
    val partitionIndex: Int,
    val orderByIndex: Int,
    val frameUnitIndex: Int,
    val frameFromIndex: Int,
    val frameEndIndex: Int
) : FunctionTranslator {
    override fun translate(
        funcName: String,
        dialect: SqlDialect,
        expectArgs: List<FunctionDefinition.FunctionArgument>,
        actualArgs: List<StatementInfo?>
    ): String {
        val actualArgStrs = actualArgs.map { it?.expression }
        val builder =
            StringBuilder(
                FunctionDefinition.FunctionImplement.translate(
                    funcName,
                    expression,
                    expectArgs,
                    actualArgStrs
                )
            )
                .append(" OVER (")
        renderPartitionAndOrderBy(
            builder,
            partition = actualArgStrs.getOrNull(partitionIndex),
            orderBy = actualArgStrs.getOrNull(orderByIndex)
        )

        val unit = actualArgStrs.getOrElse(frameUnitIndex) { i -> expectArgs[i].defaultValue?.toString() }
        val start = actualArgStrs.getOrElse(frameFromIndex) { i -> expectArgs[i].defaultValue?.toString() }?.toInt()
        val end = actualArgStrs.getOrElse(frameEndIndex) { i -> expectArgs[i].defaultValue?.toString() }?.toInt()
        if (unit != null || start != null || end != null) {
            if (start != 0 || end != 0) {
                builder.append(" ").append(unit).append(" ")
                if (end == 0) {
                    if (start!! > 0) {
                        builder.append(start).append(" FOLLOWING ")
                    } else {
                        builder.append(-start).append(" PRECEDING ")
                    }
                } else if (start == 0) {
                    builder.append(" BETWEEN CURRENT ROW AND ")
                    if (end!! > 0) {
                        builder.append(end).append(" FOLLOWING ")
                    } else {
                        builder.append(-end).append(" PRECEDING ")
                    }
                } else {
                    builder.append(" BETWEEN ")
                    if (start!! > 0) {
                        builder.append(start).append(" FOLLOWING ")
                    } else {
                        builder.append(-start).append(" PRECEDING ")
                    }
                    builder.append(" AND ")
                    if (end!! > 0) {
                        builder.append(end).append(" FOLLOWING ")
                    } else {
                        builder.append(-end).append(" PRECEDING ")
                    }
                }
            }
        }
        return builder.append(")").toString()
    }

}

data class LagLeadTranslator(val funcName: String) : FunctionTranslator {

    override fun translate(
        funcName: String,
        dialect: SqlDialect,
        expectArgs: List<FunctionDefinition.FunctionArgument>,
        actualArgs: List<StatementInfo?>
    ): String {
        val actualArgStrs = actualArgs.map { it?.expression }
        val argSize = actualArgStrs.size
        if (argSize > 5) {
            throw IllegalStateException("函数${this.funcName}调用参数过多")
        }
        val builder = StringBuilder(this.funcName).append("( ").append(actualArgStrs[0])
        if (argSize >= 4) {
            val offset = actualArgStrs.getOrElse(1) { i -> expectArgs[i].defaultValue?.toString() }?.toInt()
            if (offset != null && offset != 0) {
                builder.append(" ,").append(offset)
            }
        }
        if (argSize == 5) {
            val defaultVal = actualArgStrs.getOrNull(2)
            if (defaultVal != null) {
                builder.append(" ,").append(defaultVal)
            }
        }
        builder.append(") OVER (")
        renderPartitionAndOrderBy(
            builder,
            partition = actualArgStrs.getOrNull(argSize - 2),
            orderBy = actualArgStrs.getOrNull(argSize - 1)
        )
        return builder.append(")").toString()
    }

}

class DateFunctionTranslator(funcName: String) : FunctionTranslator {
    private val function = funcName.toUpperCase()

    override fun translate(funcName: String, dialect: SqlDialect, expectArgs: List<FunctionDefinition.FunctionArgument>, actualArgs: List<StatementInfo?>): String {
        if ("DATE_FORMAT" == function && (actualArgs.size != 2 || actualArgs.any { it == null })) {
            throw IllegalStateException("${function}要求两个参数")
        } else if (actualArgs.size != 1 || actualArgs[0] == null) {
            throw IllegalStateException("${function}要求一个参数")
        }
        val field = actualArgs[0]!!
        var expression = field.expression
        if (field.dataType == DataType.DATE || field.dataType == DataType.DATETIME) {
            return when (this.function) {
                "YEAR" -> "YEAR(${expression})"
                "QUARTER" -> "QUARTER(${expression})"
                "MONTH" -> "MONTH(${expression})"
                "WEEK" -> "WEEKOFYEAR(${expression})"
                "DAY" -> "DAY(${expression})"
                "DATE_FORMAT" -> {
                    val format = actualArgs[1]!!.expression
                    //TODO
                    ""
                }
                else -> throw IllegalStateException("不支持的函数${this.function}")
            }
        } else if (field.dataType != DataType.INTEGER && field.dataType != DataType.STRING) {
            throw IllegalStateException("${field.dataType}不是有效的时间字段")
        }
        var format: String = ""
        if (field.payload !is IColumn || (field.payload as IColumn).format?.also { format = it }?.isBlank() != false) {
            throw IllegalStateException("${expression}未配置时间格式")
        }
        if (field.dataType == DataType.INTEGER) {
            expression = "TO_STRING(${expression})"
        }
        var start: Int = 0
        val transform = when (this.function) {
            "YEAR" -> {
                if (format.indexOf('y').also { start = it } >= 0) {
                    "TO_INTEGER(SUBSTRING(${expression}, ${format.indexOf('y') + 1}, ${format.count { it == 'y' }}))"
                } else {
                    throw IllegalStateException("${format}无法转换为年")
                }
            }
            "QUARTER" -> {
                if (format.indexOf('q').also { start = it } >= 0) {
                    "TO_INTEGER(SUBSTRING(${expression}, ${start + 1}, ${format.count { it == 'q' }}))"
                } else if (format.indexOf('M').also { start = it } >= 0) {
                    "CEILING(TO_INTEGER(SUBSTRING(${expression}, ${start + 1}, ${format.count { it == 'M' }}))/3)"
                } else {
                    throw IllegalStateException("${format}无法转换为季度")
                }
            }
            "MONTH" -> {
                if (format.indexOf('M').also { start = it } >= 0) {
                    "TO_INTEGER(SUBSTRING(${expression}, ${start + 1}, ${format.count { it == 'M' }}))"
                } else {
                    throw IllegalStateException("${format}无法转换为月")
                }
            }
            "WEEK" -> {
                if (format.indexOf('w').also { start = it } >= 0) {
                    "TO_INTEGER(SUBSTRING(${expression}, ${start + 1}, ${format.count { it == 'w' }}))"
                } else if (format.contains('y') && format.contains('M') && format.contains('d')) {
                    "WEEK(TO_DATE(${expression}, ${format}))"
                } else {
                    throw IllegalStateException("${format}无法转换为周")
                }
            }
            "DAY" -> {
                if (format.indexOf('d').also { start = it } >= 0) {
                    "TO_INTEGER(SUBSTRING(${expression}, ${start + 1}, ${format.count { it == 'd' }}))"
                } else {
                    throw IllegalStateException("${format}无法转换为天")
                }
            }
            "DATE_FORMAT" -> {
                //TODO
                ""
            }
            else -> throw IllegalStateException("不支持的函数${this.function}")
        }
        return FormulaHelper.ofCurrent().toSql(transform, dialect).expression
    }

}

object RawSqlTranslator : FunctionTranslator {

    private const val MaxArgSize = 10
    private val ParamPattern = Pattern.compile("(?:,\\s*)?\\{\\s*(\\d+)\\s*}")

    override fun translate(
        funcName: String,
        dialect: SqlDialect,
        expectArgs: List<FunctionDefinition.FunctionArgument>,
        actualArgs: List<StatementInfo?>
    ): String {
        val argSize = actualArgs.size
        if (argSize == 0) {
            throw IllegalStateException("函数RAW_SQL未提供数据库SQL方法参数")
        }
        val rawSql = actualArgs[0]?.expression?.trim('"', '\'')
            ?: throw IllegalStateException("函数RAW_SQL未提供数据库SQL方法参数")
        val matcher = ParamPattern.matcher(rawSql)
        var lastArgIndex = 0
        while (matcher.find()) {
            val index = matcher.group(1).toInt()
            if (index < 0 || index >= MaxArgSize) {
                throw IllegalStateException("参数索引应在[0-${MaxArgSize - 1}]之间")
            }
            if (index + 1 > actualArgs.size) {
                throw IllegalStateException("未提供{$index}参数")
            }
            if (index > lastArgIndex) lastArgIndex = index
        }
        matcher.reset()
        val sqlBuffer = StringBuffer()
        while (matcher.find()) {
            val hasComma = matcher.group(0).startsWith(",")
            val index = matcher.group(1).toInt()
            var expression = try {
                if (index == 0) {
                    actualArgs.subList(lastArgIndex + 1, actualArgs.size).filterNotNull()
                        .joinToString(", ") { it.expression }
                } else {
                    actualArgs[index]?.expression ?: ""
                }
            } catch (e: IndexOutOfBoundsException) {
                throw IllegalStateException("未提供${index}参数")
            }
            if (hasComma && expression.isNotBlank()) {
                expression = ", $expression"
            }
            matcher.appendReplacement(sqlBuffer, expression)
        }
        matcher.appendTail(sqlBuffer)
        return sqlBuffer.toString()
    }

}

private fun renderPartitionAndOrderBy(builder: StringBuilder, partition: String?, orderBy: String?) {
    if (partition != null && partition != "" && !"null".equals(partition, true)) {
        builder.append(" PARTITION BY ").append(partition)
    }
    if (orderBy != null && orderBy != "" && !"null".equals(orderBy, true)) {
        builder.append(" ORDER BY ").append(orderBy)
    }
}