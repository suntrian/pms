package org.sunt.sqlanalysis.lineage.model

import org.sunt.sqlanalysis.lineage.model.field.IField

open class Relation(val relationType: RelationType, val target: IField)