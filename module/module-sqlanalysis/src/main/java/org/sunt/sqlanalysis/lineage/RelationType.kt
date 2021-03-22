package org.sunt.sqlanalysis.lineage

enum class RelationType(val relevance: Int, comment: String, vararg example: String) {
    REPLICATION(2, "复制",
        "insert into A(a) select b from B: [B.b -> A.a]"),
    EQUATION(2, "相等",
        "select * from A join B ON A.a = B.b: [A.a -> B.b]"),
    TRANSFORMATION(3, "转换",
        "insert into A(a) select FLOOR(b) from B: [B.b -> A.a]"),
    CALCULATION(4, "函数",
        "insert into A(a) select CONCAT(b, c) FROM B: [B.b -> A.a] "),
    AGGREGATION(5, "聚合",
        "insert into A(a) select sum(b) FROM B: [B.b -> A.a]"),
    EXPLODE(5, "分解",
        "insert into A(a) select explode(b) from B: [B.b -> A.a]", ""),
    CONDITION(0, "条件",
        "insert into A(a) select 1 FROM B where b = 1: [B.b -> A.a]", "insert into A(a) select case b when 1 then m then 2 then n end from B: [B.b -> A.a]"),
    PARTITION(1, "分区",
        "insert into A(a) partition(x) select b, y from B: [B.y -> A.x]", "insert into A(a) select lead(x) over (partition by b ) from B: [B.b -> A.a] ");

    infix fun priorTo (relType: RelationType) : Boolean {
        return this.relevance >= relType.relevance
    }

}