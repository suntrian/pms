package org.sunt.sqlanalysis.model

enum class RelationType(val label: String, val desc: String) {

    EQUIVALENT("造价关系", "可用于JOIN"),
    GENERATION("计算生成", "如A+B=C"),
    AGGREGATION("聚合生成", "如MAX(A)"),
    FILTERATION("过滤条件", "where A = 1")


}