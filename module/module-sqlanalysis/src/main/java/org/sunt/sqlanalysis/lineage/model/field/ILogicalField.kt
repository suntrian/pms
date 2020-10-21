package org.sunt.sqlanalysis.lineage.model.field

import org.sunt.sqlanalysis.lineage.model.Relation

interface ILogicalField : IField{

    fun getRelated(): List<Relation>

}