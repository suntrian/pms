package org.sunt.sqlanalysis.model

import org.sunt.sqlanalysis.model.field.IField

class Relation(val relationType: RelationType, val source: IField, val target: IField) {


}