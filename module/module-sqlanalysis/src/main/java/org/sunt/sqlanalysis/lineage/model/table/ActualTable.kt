package org.sunt.sqlanalysis.lineage.model.table

import java.util.regex.Pattern

interface ActualTable : ITable {

    val fullNamePattern: Pattern
        get() = Pattern.compile("(?:(?<cat>[^.]+)\\.)?(?:(?:(?<scm>[^.]+)\\.)?(?<tab>[^.]+))")

    val catalog: String?

    val schema: String?

    val table: String

}