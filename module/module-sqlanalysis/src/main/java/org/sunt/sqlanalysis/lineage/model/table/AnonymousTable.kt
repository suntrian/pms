package org.sunt.sqlanalysis.lineage.model.table

import org.sunt.sqlanalysis.lineage.model.field.IField

class AnonymousTable(val name: String) : LogicalTable(emptyList()) {

    override var alias: String = name

    override val fields: List<IField> = emptyList()

}