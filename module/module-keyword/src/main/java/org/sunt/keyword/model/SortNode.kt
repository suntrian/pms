package org.sunt.keyword.model

class SortNode(text: String, column: ColumnNode, val asc: Boolean) : ColumnWrapNode(text, column) {
}