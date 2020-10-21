package org.sunt.sqlanalysis.lineage.model.field

import org.sunt.sqlanalysis.lineage.model.table.UnionTable

open class UnionField(name: String, table: UnionTable) : SelectField(name, table) {


}