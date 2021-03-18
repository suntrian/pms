package org.sunt.query.model.terms

import org.sunt.query.model.util.LinkNode

private interface Term {
    val text: String
    val length: Int
        get() = text.length
}

abstract class AbstractTerm(override val text: String) : Term {
    override fun toString(): String = text
}

sealed class LinkedTerm(text: String) : AbstractTerm(text), LinkNode<LinkedTerm> {
    override var next: LinkedTerm? = null
    override var prev: LinkedTerm? = null
}

class UnknownTerm(
    text: String,
): LinkedTerm(text)

class SplitterTerm(
    val splitter: Char,
): LinkedTerm(splitter.toString())

open class ReservedTerm(
    text: String,
): LinkedTerm(text)

class IdentityTerm(
    text: String,
): LinkedTerm(text)

class FormulaTerm(
    text: String,
): LinkedTerm(text)

class CommentTerm(
    comment: String,
): LinkedTerm(comment)

class DateTerm(
    text: String,
): LinkedTerm(text)

class LogicTerm(
    text: String,
): LinkedTerm(text)

class NumberTerm(
    text: String,
): LinkedTerm(text)

class StringTerm(
    text: String,
): LinkedTerm(text)

class QuestionTerm(
    text: String,
): LinkedTerm(text)