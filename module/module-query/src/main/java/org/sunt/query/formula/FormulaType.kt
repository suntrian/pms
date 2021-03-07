package org.sunt.query.formula

enum class FormulaType(val id: Int) {

    NORMAL(1),
    AGGREGATE(2),
    WINDOW(3);

    companion object {

        @JvmStatic
        fun of(id: Int): FormulaType {
            return when (id) {
                2 -> AGGREGATE
                3 -> WINDOW
                else -> NORMAL
            }
        }

    }
}