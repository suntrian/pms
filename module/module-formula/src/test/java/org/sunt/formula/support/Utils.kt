package org.sunt.formula.support

fun CharSequence.removeBlank(): String = this.replace("\\s+".toRegex(), "")