package org.sunt.sqlanalysis.lineage.model.field

import org.sunt.sqlanalysis.lineage.model.table.PhysicalTable

class AllPhysicalField(table: PhysicalTable): PhysicalField("*", table)