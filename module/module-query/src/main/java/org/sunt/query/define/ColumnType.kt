package org.sunt.query.define

enum class ColumnType(val id: Int, private val label: String) {

    MEASURE(1, "度量"),
    DIMENSION(2, "维度"),
    TEMPORAL(3, "时间"),
    UNKNOWN(99, "未知");

    companion object {
        @JvmStatic
        fun of(id: Int): ColumnType {
            return ColumnType.values().firstOrNull { it.id == id }
                ?: throw IllegalStateException("未定义的字段类型:$id")
        }
    }

}