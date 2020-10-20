package org.sunt.sqlanalysis.lineage.model.table

class SelectTable(related: List<ITable>) : LogicalTable(related) {

    private var alias: String = ""

    override fun getAlias(): String {
        return this.alias
    }

}