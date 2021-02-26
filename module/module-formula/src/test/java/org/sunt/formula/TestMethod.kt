package org.sunt.formula

import org.junit.jupiter.api.Test

class TestMethod {

    @Test
    fun testListIterator() {
        val x = mutableListOf("a", "b", "c", "d", "e")
        val it = x.listIterator()
        while (it.hasNext()) {
            val c = it.next()
            if (c == "b") {
                it.previous()
                it.add("123")
                it.next()
            }
        }
        println(x)
    }

    @Test
    fun tesRegex() {
        val numberRegex = Regex("[-+]?\\d+(\\.\\d+)?")
        val stringRegex = Regex("(['\"]).*\\1")
        println(numberRegex.matches("0.3"))
        println(numberRegex.matches("-00.03"))
        println(numberRegex.matches("-33"))
        println(numberRegex.matches("23"))
        println(numberRegex.matches("-0.0.3"))

        println(stringRegex.matches("'2353'"))
        println(stringRegex.matches("\"aaaa\""))
        println(stringRegex.matches("\"bbbb'"))

    }


}