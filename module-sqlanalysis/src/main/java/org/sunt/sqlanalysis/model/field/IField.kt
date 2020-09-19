package org.sunt.sqlanalysis.model.field

interface IField {

    fun getAlias(): String

    fun getEquivalents(): List<IField>

}