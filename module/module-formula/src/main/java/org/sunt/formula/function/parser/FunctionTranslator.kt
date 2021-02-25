package org.sunt.formula.function.parser

import org.sunt.formula.function.FunctionDefinition


interface FunctionTranslator {

    /**
     * @param funcName 待转换的函数的名称，非SQL函数
     * @param funcArgs 待转换的函数的参数定义
     * @param args 实际的参数
     */
    fun translate(
        funcName: String,
        funcArgs: List<FunctionDefinition.FunctionArgument>,
        args: Array<out String?>
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
                else -> EmptyTranslator
            }
        }
    }

}

object EmptyTranslator : FunctionTranslator {
    override fun translate(
        funcName: String,
        funcArgs: List<FunctionDefinition.FunctionArgument>,
        args: Array<out String?>
    ): String {
        throw IllegalAccessException("not supported")
    }
}

data class PartitionOrderTranslator(val funcName: String) : FunctionTranslator {

    override fun translate(
        funcName: String,
        funcArgs: List<FunctionDefinition.FunctionArgument>,
        args: Array<out String?>
    ): String {
        val builder = StringBuilder(this.funcName).append("() OVER (")
        renderPartitionAndOrderBy(builder, partition = args.getOrNull(0), orderBy = args.getOrNull(1))
        return builder.append(")").toString()
    }

}

data class PreDefinedPartitionOrderTranslator(val expression: String, val partitionIndex: Int, val orderByIndex: Int) :
    FunctionTranslator {

    override fun translate(
        funcName: String,
        funcArgs: List<FunctionDefinition.FunctionArgument>,
        args: Array<out String?>
    ): String {
        val translatedExpr = FunctionDefinition.FunctionImplement.translate(funcName, expression, funcArgs, args)
        val builder = StringBuilder(translatedExpr).append(" OVER (")
        renderPartitionAndOrderBy(
            builder,
            partition = args.getOrNull(partitionIndex),
            orderBy = args.getOrNull(orderByIndex)
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
        funcArgs: List<FunctionDefinition.FunctionArgument>,
        args: Array<out String?>
    ): String {
        val builder =
            StringBuilder(FunctionDefinition.FunctionImplement.translate(funcName, expression, funcArgs, args))
                .append(" OVER (")
        renderPartitionAndOrderBy(
            builder,
            partition = args.getOrNull(partitionIndex),
            orderBy = args.getOrNull(orderByIndex)
        )

        val unit = args.getOrElse(frameUnitIndex) { i -> funcArgs[i].defaultValue?.toString() }
        val start = args.getOrElse(frameFromIndex) { i -> funcArgs[i].defaultValue?.toString() }?.toInt()
        val end = args.getOrElse(frameEndIndex) { i -> funcArgs[i].defaultValue?.toString() }?.toInt()
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
        funcArgs: List<FunctionDefinition.FunctionArgument>,
        args: Array<out String?>
    ): String {
        val builder = StringBuilder(this.funcName).append("( ").append(args[0])
        val offset = args.getOrElse(1) { i -> funcArgs[i].defaultValue?.toString() }?.toInt()
        if (offset != null && offset != 0) {
            builder.append(" ,").append(offset)
        }
        val defaultVal = args.getOrNull(2)
        if (defaultVal != null) {
            builder.append(" ,").append(defaultVal)
        }
        builder.append(") OVER (")
        renderPartitionAndOrderBy(builder, partition = args.getOrNull(3), orderBy = args.getOrNull(4))
        return builder.append(")").toString()
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