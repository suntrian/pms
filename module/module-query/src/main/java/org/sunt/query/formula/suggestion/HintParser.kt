package org.sunt.query.formula.suggestion

import java.util.*

internal object HintParser {

    private const val MODE_KEY = 1
    private const val MODE_VAL = 2
//    private const val MODE_PAREN =3
//    private const val MODE_BRACT = 4
    private const val MODE_STRING = 5


    fun parse(hintText: String): Map<String, Any> {
        val hint = if (hintText.startsWith("/*+") && hintText.endsWith("*/")) {
            hintText.substring(3, hintText.length-2).trim()
        } else hintText.trim()
        if (hint.isBlank()) return emptyMap()
        val result = mutableMapOf<String, Any>()
        val segment = mutableListOf<Char>()
        var key = ""
        var value = ""
        var curMode = MODE_KEY
        val modeStack = Stack<Int>()
        var prev = '\u0000'
        var strStart = '\u0000'
        for (char in hint) {
            when (char) {
                '=' -> {
                    if (curMode == MODE_KEY ) {
                        if (segment.isNotEmpty()) {
                            key = String(segment.toCharArray())
                            segment.clear()
                            curMode = MODE_VAL
                        } else {
                            throw IllegalStateException("hint格式错误")
                        }
                    } else if (curMode == MODE_STRING) {
                        segment.add(char)
                    }
                }
                ',' -> {
                    if (curMode == MODE_VAL || curMode == MODE_KEY) {
                        if (segment.isNotEmpty()) {
                            if (key.isNotEmpty()) {
                                value = String(segment.toCharArray())
                                result[key] = value
                            } else {
                                result[String(segment.toCharArray())] = true
                            }
                        } else {
                            throw IllegalStateException("hint格式错误")
                        }
                        key = ""
                        segment.clear()
                        curMode = MODE_KEY
                    } else if (curMode == MODE_STRING) {
                        segment.add(char)
                    }
                }
                '\'', '"' -> {
                    if (curMode == MODE_KEY) {
                        throw IllegalStateException("hint格式错误")
                    } else if (curMode == MODE_VAL) {
                        modeStack.push(curMode)
                        curMode = MODE_STRING
                        strStart = char
                    } else if (curMode == MODE_STRING) {
                        if (char == strStart && prev != '\\') {
                            curMode = modeStack.pop()
                        } else {
                            if (prev == '\\') {
                                segment.removeLast()
                            }
                            segment.add(char)
                        }
                    }
                }
                ' ' -> {
                    if (curMode == MODE_STRING) {
                        segment.add(char)
                    }
                }
                else -> {
                    segment.add(char)
                }
            }
            prev = char
        }
        if (segment.isNotEmpty()) {
            if (key.isNotEmpty()) {
                result[key] = String(segment.toCharArray())
            } else {
                result[String(segment.toCharArray())] = true
            }
        }
        return result
    }

    fun generateHint(id: String?, name: String?): String {
        if (id == null || name == null) return ""
        return "/*+id=${id},name=${name}*/"
    }

    fun generateHint(map: Map<String, String?>): String {
        return map.filter { it.value != null }.entries
            .joinToString(",", "/*+", "*/"){ "${it.key}=${it.value}" }
    }
}