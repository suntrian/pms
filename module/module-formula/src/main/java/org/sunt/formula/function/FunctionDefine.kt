package org.sunt.formula.function

import org.sunt.formula.define.DataType

class FunctionDefine(val funcName: String, val dataType: DataType) {

    val categories: Set<String> = mutableSetOf();
    val alias: Set<String> = mutableSetOf()
    val args: List<FunctionArgDefine> = mutableListOf();

    var description: String = ""
    var implement: String = ""

    class FunctionArgDefine(val name: String, val dataType: DataType) {

        var vararg: Boolean = false
        var enumValues: Set<Any> = mutableSetOf()

    }
}