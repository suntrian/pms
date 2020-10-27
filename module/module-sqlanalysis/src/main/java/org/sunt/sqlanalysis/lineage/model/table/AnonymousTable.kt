package org.sunt.sqlanalysis.lineage.model.table

class AnonymousTable(val name: String) : LogicalTable(emptyList()) {

    override var alias: String = name

}