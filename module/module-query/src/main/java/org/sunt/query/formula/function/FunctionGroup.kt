package org.sunt.query.formula.function

class FunctionGroup(val dialect: String, val functions: List<FunctionDefinition>) {

    var parentGroup: FunctionGroup? = null

    override fun toString(): String {
        return "$dialect: { functionSize: ${functions.size},  parent: $parentGroup }"
    }


}