package org.sunt.query.util

import kotlin.math.exp

object MathUtil {

    @JvmStatic
    fun sigmoid(value: Double): Double = 1 / (1 + exp(-value))

}