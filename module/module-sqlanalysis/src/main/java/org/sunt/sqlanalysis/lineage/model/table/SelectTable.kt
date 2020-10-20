package org.sunt.sqlanalysis.lineage.model.table

class SelectTable() : LogicalTable() {

    private var alias: String = ""

    override fun getAlias(): String {
        return this.alias
    }

}