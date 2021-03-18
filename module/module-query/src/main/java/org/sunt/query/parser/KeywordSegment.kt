package org.sunt.query.parser

import org.sunt.query.formula.suggestion.HintParser
import org.sunt.query.model.terms.*
import org.sunt.query.model.util.CharTrie
import java.util.*
import kotlin.collections.ArrayList

object KeywordSegment {

    private enum class ReservedType(desc: String) {
        FUNCTION("函数"),
        STOP_WORD("停止词"),
        SENTENCE("句式"),
    }

    private val reservedFunction = mapOf(
        "当前日期" to "CURRENT_DATE()",
        "当前时间" to "CURRENT_TIME()",
    )

    private val reservedStopWords = listOf(
        "什么",
        "谁",
    )

    private object KeywordReservedTrie: CharTrie<ReservedType>() {

        init {
            for ((word, _) in reservedFunction) {
                put(word, ReservedType.FUNCTION)
            }
            for (word in reservedStopWords) {
                put(word, ReservedType.STOP_WORD)
            }
            val reservedWords = KeywordLexer.reservedWords
            for (reservedWord in reservedWords) {
                put(reservedWord, ReservedType.SENTENCE)
            }
        }

    }

    private const val MODE_INITIAL = '1'.toByte()
    private const val MODE_IDENTITY = '2'.toByte()
    private const val MODE_FORMULA = '3'.toByte()
    private const val MODE_STRING = '4'.toByte()
    private const val MODE_HINT = '6'.toByte()
    private const val MODE_LINE_COMMENT = '7'.toByte()
    private const val MODE_BLOCK_COMMENT = '8'.toByte()

    @JvmStatic
    fun segment(text: String): TermList {
        val tokens = ArrayList<LinkedTerm>(text.length/4)
        val chars = StringBuilder(text.length)
        var curMode = MODE_INITIAL
        val modeStack = Stack<Byte>()
        var prev = '\u0000'
        var openSeg = '\u0000'

        var lastTrieNode: CharTrie.CharNode<ReservedType>? = null

        fun addToken(text: String) {
            val term = when(curMode) {
                MODE_INITIAL -> UnknownTerm(text)
                MODE_IDENTITY -> IdentityTerm(text)
                MODE_FORMULA -> FormulaTerm(text)
                MODE_STRING -> StringTerm(text)
                MODE_LINE_COMMENT, MODE_BLOCK_COMMENT -> CommentTerm(text)
                else -> UnknownTerm(text)
            }
            tokens.add(term)
        }

        fun resetChars() {
            lastTrieNode = null
            if (chars.isNotEmpty()){
                addToken(chars.toString())
                chars.clear()
            }
        }
        fun matchReserved(trieNode: CharTrie.CharNode<ReservedType>) {
            val matchedKey = trieNode.key
            if ( matchedKey.length < chars.length) {
                addToken(chars.substring(0, chars.length-matchedKey.length))
            }
            if (trieNode.value?.equals(ReservedType.FUNCTION) == true) {
                tokens.add(ReservedTerm(matchedKey+HintParser.generateHint(mapOf("type" to "function", "name" to reservedFunction[matchedKey]))))
            } else {
                tokens.add(ReservedTerm(matchedKey))
            }
        }
        fun isAlpha(char: Char): Boolean {
            return char in 'A'..'Z' || char in 'a'..'z' || char == '_'
        }
        fun isDigit(char: Char): Boolean {
            return char in '0'..'9'
        }

        val textIter = text.iterator()
        var char = textIter.nextChar()
        while (true) {
            val endOfWord = !textIter.hasNext()
            val toContinue =  if ((curMode == MODE_STRING || curMode == MODE_IDENTITY)
                && (char != openSeg || prev == '\\')) true                                              //字符串或者标识符
            else if ((curMode == MODE_LINE_COMMENT) && (char != '\r' && char!= '\n')) true              //行注释
            else if (curMode == MODE_BLOCK_COMMENT && char == '+' && chars.length==2) false               //进入hint模式
            else if ((curMode == MODE_BLOCK_COMMENT || curMode == MODE_HINT) && (char != '/' || prev!='*' )) true //块注释或hint模式
            else (curMode == MODE_FORMULA && modeStack.peek() != MODE_FORMULA) && (char != ')')         //formula模式
            if (toContinue){
                chars.append(char)
                prev = char
                if (!endOfWord){
                    char = textIter.nextChar()
                    continue
                } else break
            }

            when {
                " ;\r\n".indexOf(char)>=0 -> {
                    resetChars()
                    tokens.add(SplitterTerm(char))
                    if (';' == char) {
                        curMode = MODE_INITIAL
                        modeStack.clear()
                    }
                }
                "`'\"".indexOf(char)>=0 -> {
                    val nextMode = if ('`' == char) MODE_IDENTITY else MODE_STRING
                    if (curMode == nextMode){
                        curMode = modeStack.pop()
                        chars.append(char)
                        resetChars()
                    } else {
                        resetChars()
                        chars.append(char)
                        modeStack.push(curMode)
                        curMode = nextMode
                        openSeg = char
                    }
                }
                '*' == char -> {
                    if (prev == '/') {
                        modeStack.push(curMode)
                        curMode = MODE_BLOCK_COMMENT
                    } else {
                        resetChars()
                    }
                    chars.append(char)
                }
                '+' == char -> {
                    if (curMode == MODE_BLOCK_COMMENT && chars.length == 2) {
                        curMode = MODE_HINT
                    } else {
                        resetChars()
                    }
                    chars.append(char)
                }
                '/' == char -> {
                    if ((curMode == MODE_BLOCK_COMMENT || curMode == MODE_HINT) && prev == '*') {
                        chars.append(char)
                        if (curMode == MODE_HINT) {
                            val hint = HintParser.parse(chars.toString())
                            val fieldName = hint["name"]
                            if (fieldName != null) {
                                //根据hint中的字段，合并分词
                                var seg = fieldName as String
                                var matched = false
                                var lastMatchedToken = 0
                                for (i in tokens.size-1 downTo 0) {
                                    lastMatchedToken = i
                                    val token = tokens[i]
                                    if (token.length == seg.length) {
                                        val realFieldName = tokens.subList(i, tokens.size).joinToString(separator = ""){ it.text }
                                        if (realFieldName != fieldName) {
                                            chars.clear()
                                        } else matched = true
                                        break
                                    } else if (token.length > seg.length) {
                                        val realFieldName = token.text.substring(token.length-seg.length) + tokens.subList(i+1, tokens.size).joinToString(""){ it.text }
                                        if (realFieldName != fieldName) {
                                            chars.clear()
                                        } else {
                                            tokens[i] = UnknownTerm(token.text.substring(0, token.length-seg.length))
                                            matched = true
                                        }
                                        break
                                    } else {
                                        seg = seg.substring(0, seg.length-token.length)
                                    }
                                }
                                if (matched) {
                                    if (lastMatchedToken != tokens.size-1) {
                                        for (i in tokens.size-1 downTo lastMatchedToken+1) tokens.removeAt(i)
                                        tokens.add(IdentityTerm(fieldName+chars))
                                    } else {
                                        tokens[lastMatchedToken] = IdentityTerm(tokens[lastMatchedToken].text + chars)
                                    }
                                    chars.clear()
                                }
                            }
                        }
                        resetChars()
                        curMode = modeStack.pop()
                    } else {
                        resetChars()
                        chars.append(char)
                    }
                }
                '(' == char -> {
                    resetChars()
                    modeStack.push(curMode)
                    curMode = MODE_FORMULA
                    chars.append(char)
                }
                ')' == char -> {
                    curMode = modeStack.pop()
                    chars.append(char)
                    if (curMode != MODE_FORMULA) {
                        resetChars()
                    }
                }
                isDigit(char) -> {
                    if (!isDigit(prev)) {
                        resetChars()
                    }
                    chars.append(char)
                }
                isAlpha(char) -> {
                    if (!isAlpha(prev)) {
                        resetChars()
                    }
                    chars.append(char)
                }
                else -> {
                    if (isDigit(prev) || isDigit(prev)) {
                        resetChars()
                    }

                    if (lastTrieNode == null) {
                        val trieNodes = KeywordReservedTrie.walk(charArrayOf(char))
                        if (trieNodes != null) {
                            lastTrieNode = trieNodes
                        }
                        chars.append(char)
                    } else {
                        val childNode = lastTrieNode!!.children[char]
                        if (childNode == null ) {
                            //找到最近匹配的关键词
                            var parentNode = lastTrieNode
                            while (!parentNode!!.eow) parentNode = parentNode.parent
                            if (!parentNode.isRoot()) {
                                matchReserved(parentNode)
                                chars.clear()
                                //可能是新的关键字，继续匹配当前字符
                                lastTrieNode = null
                                continue
                            }
                        }
                        lastTrieNode = childNode
                        chars.append(char)
                    }
                }
            }

            prev = char
            if (!endOfWord) {
                char = textIter.nextChar()
            } else break
        }
        if (chars.isNotEmpty()) {
            if (lastTrieNode!=null && lastTrieNode!!.eow) {
                matchReserved(lastTrieNode!!)
            } else {
                addToken(chars.toString())
            }
        }
        return TermList(tokens)
    }

}