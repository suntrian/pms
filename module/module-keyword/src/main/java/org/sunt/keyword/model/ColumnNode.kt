package org.sunt.keyword.model

import org.sunt.keyword.define.IColumn

class ColumnNode(override val text: String, val column: IColumn) : KeywordNode {
}