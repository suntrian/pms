package org.sunt.query.define

enum class SetType(val expression: String) {

    UNION_ALL("UNION ALL"),
    UNION_DISTINCT("UNION"),
    EXCEPT_ALL("EXCEPT ALL"),
    EXCEPT_DISTINCT("EXCEPT"),
    INTERSECT_ALL("INTERSECT ALL"),
    INTERSECT_DISTINCT("INTERSECT")

}