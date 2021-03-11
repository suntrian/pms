package org.sunt.sqlanalysis.parser

enum class ErrorStrategy(desc: String) {
    SWALLOW("有错误就呑了"),
    THROW("有错误就抛了"),
    RETURN("有错误先呑了再一起返回")
}