package org.sunt.query.formula.function.parser

import org.sunt.query.define.DataType
import org.sunt.query.define.SqlDialect
import org.sunt.query.formula.FormulaHelper
import org.sunt.query.formula.function.FunctionDefinition
import org.sunt.query.formula.function.FunctionDefinitionParser
import org.sunt.query.formula.function.StatementInfo
import org.sunt.query.model.metadata.Column
import java.util.regex.Pattern


interface FunctionTranslator {

    /**
     * @param dialect 数据库方言
     * @param function 调用的函数
     * @param actualArgRoot 实际的参数根节点
     */
    fun translate(
        dialect: SqlDialect,
        function: FunctionDefinition,
        actualArgRoot: StatementInfo
    ): String

    fun translate(
        dialect: SqlDialect,
        function: String,
        vararg actualArgs: String?
    ): String {
        return translate(dialect,
            FunctionDefinitionParser.loadFunctions(dialect)[function]?.get(0) ?: throw IllegalStateException("函数${function}不存在"),
            StatementInfo.empty().apply {
                children = actualArgs.map { it?.let { StatementInfo.empty().apply { expression = it } } ?: StatementInfo.Empty }
            })
    }

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
                DateFunctionTranslator::class.simpleName -> {
                    DateFunctionTranslator
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
        dialect: SqlDialect,
        function: FunctionDefinition,
        actualArgRoot: StatementInfo
    ): String {
        throw IllegalAccessException("not supported")
    }
}

data class PartitionOrderTranslator(val funcName: String) : FunctionTranslator {

    override fun translate(
        dialect: SqlDialect,
        function: FunctionDefinition,
        actualArgRoot: StatementInfo
    ): String {
        val actualArgs = actualArgRoot.children
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
        dialect: SqlDialect,
        function: FunctionDefinition,
        actualArgRoot: StatementInfo
    ): String {
        val actualArgs = actualArgRoot.children
        val actualArgStrs = actualArgs.map { it.expression }
        val translatedExpr =
            FunctionDefinition.FunctionImplement.translate(function.funcName, expression, function, actualArgStrs)
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
        dialect: SqlDialect,
        function: FunctionDefinition,
        actualArgRoot: StatementInfo
    ): String {
        val expectArgs = function.arguments
        val actualArgs = actualArgRoot.children
        val actualArgStrs = actualArgs.map { it.expression }
        val builder =
            StringBuilder(FunctionDefinition.FunctionImplement.translate(function.funcName, expression, function, actualArgStrs))
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
        dialect: SqlDialect,
        function: FunctionDefinition,
        actualArgRoot: StatementInfo
    ): String {
        val expectArgs = function.arguments
        val actualArgs = actualArgRoot.children
        val actualArgStrs = actualArgs.map { it.expression }
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

object DateFunctionTranslator : FunctionTranslator {

    override fun translate(dialect: SqlDialect, function: FunctionDefinition, actualArgRoot: StatementInfo): String {
        val actualArgs = actualArgRoot.children
        val functionName = function.funcName.toUpperCase()
        val dateUnit = if ("DATE_ROLLUP" == functionName) {
            if ((actualArgs.size != 2 || actualArgs.any { it == StatementInfo.Empty })) {
                throw IllegalStateException("${functionName}要求两个参数")
            }
            actualArgs[1].expression.trim('"', '\'').toUpperCase()
        } else if (actualArgs.size != 1 || actualArgs[0] == StatementInfo.Empty) {
            throw IllegalStateException("${functionName}要求一个参数")
        } else ""
        val field = actualArgs[0]
        var expression = field.expression
        if (field.dataType == DataType.DATE || field.dataType == DataType.DATETIME) {
            return when (functionName) {
                "YEAR" -> "YEAR(${expression})"
                "QUARTER" -> "QUARTER(${expression})"
                "MONTH" -> "MONTH(${expression})"
                "WEEK" -> "WEEKOFYEAR(${expression})"
                "DAY" -> "DAY(${expression})"
                "DATE_ROLLUP" -> functionMap[dialect]?.let {
                    it[dateUnit]?.invoke(expression) ?: throw IllegalStateException("不支持的时间维度:${dateUnit}")
                } ?: throw IllegalStateException("未支持的数据库方言:$dialect")
                else -> throw IllegalStateException("不支持的函数${functionName}")
            }
        } else if (field.dataType != DataType.INTEGER && field.dataType != DataType.STRING) {
            throw IllegalStateException("${field.dataType}不是有效的时间字段")
        }
        var fieldFormat = ""
        val column = if (field.payload !is Column
            || (field.payload as Column).format?.also { fieldFormat = it.trim() }?.isBlank() != false
        ) {
            throw IllegalStateException("${expression}未配置时间格式")
        } else field.payload as Column
        if (field.dataType == DataType.INTEGER) {
            expression = "TO_STRING(${expression})"
        }
        var start: Int
        val transform = when (functionName) {
            "YEAR" -> {
                if (fieldFormat.indexOf('y').also { start = it } >= 0) {
                    "TO_INTEGER(SUBSTRING(${expression}, ${fieldFormat.indexOf('y') + 1}, ${fieldFormat.count { it == 'y' }}))"
                } else {
                    throw IllegalStateException("字段${column.name}格式${fieldFormat}无法转换为年")
                }
            }
            "QUARTER" -> {
                if (fieldFormat.indexOf('q').also { start = it } >= 0) {
                    "TO_INTEGER(SUBSTRING(${expression}, ${start + 1}, ${fieldFormat.count { it == 'q' }}))"
                } else if (fieldFormat.indexOf('M').also { start = it } >= 0) {
                    "CEILING(TO_INTEGER(SUBSTRING(${expression}, ${start + 1}, ${fieldFormat.count { it == 'M' }}))/3)"
                } else {
                    throw IllegalStateException("字段${column.name}格式${fieldFormat}无法转换为季度")
                }
            }
            "MONTH" -> {
                if (fieldFormat.indexOf('M').also { start = it } >= 0) {
                    "TO_INTEGER(SUBSTRING(${expression}, ${start + 1}, ${fieldFormat.count { it == 'M' }}))"
                } else {
                    throw IllegalStateException("字段${column.name}格式${fieldFormat}无法转换为月")
                }
            }
            "WEEK" -> {
                if (fieldFormat.indexOf('w').also { start = it } >= 0) {
                    "TO_INTEGER(SUBSTRING(${expression}, ${start + 1}, ${fieldFormat.count { it == 'w' }}))"
                } else if (fieldFormat.contains('y') && fieldFormat.contains('M') && fieldFormat.contains('d')) {
                    "WEEK(TO_DATE(${expression}, '${fieldFormat}'))"
                } else {
                    throw IllegalStateException("字段${column.name}格式${fieldFormat}无法转换为周")
                }
            }
            "DAY" -> {
                if (fieldFormat.indexOf('d').also { start = it } >= 0) {
                    "TO_INTEGER(SUBSTRING(${expression}, ${start + 1}, ${fieldFormat.count { it == 'd' }}))"
                } else {
                    throw IllegalStateException("字段${column.name}格式${fieldFormat}无法转换为天")
                }
            }
            "DATE_ROLLUP" -> {
                //此处假设时间字段格式为年月日或者日月年格式，如月日年不好处理
                return when (dateUnit) {
                    "YEAR" -> {
                        if (fieldFormat.indexOf('y').also { start = it } >= 0) {
                            "SUBSTRING(${expression}, ${start + 1}, ${fieldFormat.count { it == 'y' }})"
                        } else throw IllegalStateException("字段${column.name}格式${fieldFormat}无法转换到年")
                    }
                    "QUARTER" -> {
                        if (fieldFormat.indexOf('y').also { start = it } >= 0) {
                            if (fieldFormat.contains('Q') || fieldFormat.contains('q')) {
                                fieldFormat = fieldFormat.replace("[^yQq]".toRegex(), " ")
                                "SUBSTRING(${expression}, ${fieldFormat.indexOfFirst { it != ' ' }}, ${fieldFormat.trim().length}"
                            } else if (fieldFormat.contains('M')) {
                                "CONCAT(".plus(
                                    translate(dialect, function,
                                        StatementInfo.empty().apply { children = actualArgs.also { it[1].expression = "YEAR" } })
                                )
                                    .plus(", '-', ")
                                    .plus(
                                        translate(dialect, FunctionDefinitionParser.loadFunctions(dialect)["QUARTER"]?.get(0)!!,
                                            StatementInfo.empty().apply { children = listOf(actualArgs[0]) })
                                    )
                                    .plus(")")
                            }
                        }
                        throw IllegalStateException("字段${column.name}格式${fieldFormat}无法转换到季")
                    }
                    "MONTH" -> {
                        fieldFormat = fieldFormat.takeIf { it.contains('M') }?.replace("[^yM]".toRegex(), " ")
                            ?: throw IllegalStateException("字段${column.name}格式${fieldFormat}无法转换到月")
                        "SUBSTRING(${expression}, ${fieldFormat.indexOfFirst { it != ' ' } + 1}, ${fieldFormat.trim().length})"
                    }
                    "WEEK" -> {
                        fieldFormat = fieldFormat.takeIf { it.contains('d') }?.replace("[^yMd]".toRegex(), " ")
                            ?: throw IllegalStateException("字段${column.name}格式${fieldFormat}无法转换到周")
                        "CONCAT(".plus(
                            this.translate(
                                dialect,
                                function,
                                StatementInfo.empty().apply { children = actualArgs.also { it[1].expression = "YEAR" } })
                        )
                            .plus(", '-', ")
                            .plus(
                                translate(dialect, FunctionDefinitionParser.loadFunctions(dialect)["WEEK"]?.get(0)!!,
                                    StatementInfo.empty().apply { children = listOf(actualArgs[0]) })
                            )
                            .plus(")")
                    }
                    "DAY" -> {
                        fieldFormat = fieldFormat.takeIf { it.contains('d') }?.replace("[^yMd]".toRegex(), " ")
                            ?: throw IllegalStateException("字段${column.name}格式${fieldFormat}无法转换到天")
                        if (fieldFormat.startsWith(' ') || fieldFormat.endsWith(' ')) {
                            "SUBSTRING(${expression}, ${fieldFormat.indexOfFirst { it != ' ' } + 1}, ${fieldFormat.trim().length})"
                        } else expression
                    }
                    else -> throw IllegalStateException("不支持的时间维度:${dateUnit}")
                }
            }
            else -> throw IllegalStateException("不支持的函数${functionName}")
        }
        return FormulaHelper.ofCurrent().toSql(transform, dialect).expression
    }

    private val functionMap = mapOf(
        SqlDialect.MYSQL to mapOf(
            "YEAR" to { field: String -> "DATE_FORMAT(${field}, '%Y')" },
            "QUARTER" to { field: String -> "CONCAT(YEAR(${field}), '-', QUARTER(${field}))" },
            "MONTH" to { field: String -> "DATE_FORMAT(${field}, '%Y-%m')" },
            "WEEK" to { field: String -> "CONCAT(YEAR(${field}), '-', WEEKOFYEAR(${field}))" },
            "DAY" to { field: String -> "DATE_FORMAT(${field}, '%Y-%m-%d')" },
        ),
        SqlDialect.HIVE to mapOf(
            "YEAR" to { field: String -> "FROM_TIMESTAMP(${field}, 'yyyy')" },
            "QUARTER" to { field: String -> "CONCAT(CAST(YEAR(${field}) AS VARCHAR), '-', CAST(CEIL(MONTH(${field})/3) AS VARCHAR))" },
            "MONTH" to { field: String -> "FROM_TIMESTAMP(${field}, 'yyyy-MM')" },
            "WEEK" to { field: String -> "CONCAT(CAST(YEAR(${field}) AS VARCHAR), '-', CAST(WEEKOFYEAR(${field}) AS VARCHAR))" },
            "DAY" to { field: String -> "FROM_TIMESTAMP(${field}, 'yyyy-MM-dd')" },
        ),
        SqlDialect.IMPALA to mapOf(
            "YEAR" to { field: String -> "FROM_TIMESTAMP(${field}, 'yyyy')" },
            "QUARTER" to { field: String -> "CONCAT(CAST(YEAR(${field}) AS VARCHAR), '-', CAST(CEIL(MONTH(${field})/3) AS VARCHAR))" },
            "MONTH" to { field: String -> "FROM_TIMESTAMP(${field}, 'yyyy-MM')" },
            "WEEK" to { field: String -> "CONCAT(CAST(YEAR(${field}) AS VARCHAR), '-', CAST(WEEKOFYEAR(${field}) AS VARCHAR))" },
            "DAY" to { field: String -> "FROM_TIMESTAMP(${field}, 'yyyy-MM-dd')" },
        ),
    )

}

object RawSqlTranslator : FunctionTranslator {

    private const val MaxArgSize = 10
    private val ParamPattern = Pattern.compile("(?:,\\s*)?\\{\\s*(\\d+)\\s*}")

    override fun translate(
        dialect: SqlDialect,
        function: FunctionDefinition,
        actualArgRoot: StatementInfo
    ): String {
        val actualArgs = actualArgRoot.children
        val argSize = actualArgs.size
        if (argSize == 0) {
            throw IllegalStateException("函数RAW_SQL未提供数据库SQL方法参数")
        }
        val rawSql = actualArgs[0].expression.trim('"', '\'').takeIf { it.isNotBlank() } ?: throw IllegalStateException("函数RAW_SQL未提供数据库SQL方法参数")
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
                    actualArgs.subList(lastArgIndex + 1, actualArgs.size).joinToString(", ") { it.expression }
                } else {
                    actualArgs[index].expression
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