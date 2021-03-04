package org.sunt.keyword.define

enum class AggregateType(val id: Int, private val label: String) {

    COUNT(1, ""),
    MAX(2, ""),
    MIN(3, ""),
    AVG(4, ""),
    SUM(5, "");

    companion object {

        @JvmStatic
        fun of(id: Int): AggregateType {
            return values().firstOrNull { it.id == id }
                ?: throw IllegalStateException("未定义的聚合类型:$id")
        }

    }

}