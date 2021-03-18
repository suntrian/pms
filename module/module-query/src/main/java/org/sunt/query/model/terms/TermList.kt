package org.sunt.query.model.terms

import org.sunt.query.model.util.LinkList

class TermList(terms: MutableList<LinkedTerm>) : LinkList<LinkedTerm>(terms) {
    constructor(): this(ArrayList())

    override fun toString(): String {
        return list.joinToString(" ") { it.text }
    }


}