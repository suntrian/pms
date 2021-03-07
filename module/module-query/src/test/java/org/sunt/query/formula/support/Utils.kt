package org.sunt.query.formula.support

fun CharSequence.removeBlank(): String = this.replace("\\s+".toRegex(), "")