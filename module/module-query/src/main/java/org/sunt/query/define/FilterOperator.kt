package org.sunt.query.define

enum class FilterOperator(val argSize: Int) : Operator {

    EQUAL(1),
    GREATER(1),
    LESS(1),
    GREATER_EQUAL(1),
    LESS_EQUAL(1),
    START_WITH(1),
    END_WITH(1),
    CONTAINS(1),
    LIKE(1),
    BETWEEN(2),
    IN(-1),


}