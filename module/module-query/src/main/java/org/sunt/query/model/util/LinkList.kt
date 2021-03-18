package org.sunt.query.model.util

import java.util.Comparator
import java.util.function.Predicate
import java.util.function.UnaryOperator

open class LinkList<T: LinkNode<T>>(protected val list: MutableList<T>): MutableList<T> by list{

    private var tail: T? = makeLink(list)

    override fun add(element: T): Boolean {
        if (tail!=null) tail!!.next = element
        element.prev = tail
        tail = element
        return list.add(element)
    }

    override fun add(index: Int, element: T) {
        list.add(index, element)
        if (index>0) {
            list[index-1].apply {
                next = element
                element.prev = this
            }
        }
        if (index<list.size) {
            list[index+1].apply {
                prev = element
                element.next = this
            }
        } else {
            this.tail = element
        }
    }

    override fun addAll(index: Int, elements: Collection<T>): Boolean {
        if (elements.isEmpty()) return false
        val last = makeLink(elements)
        val first = elements.iterator().next()
        if (index==list.size) {
            tail?.next = first
            first.prev = tail
            tail = last
        } else {
            val item = list[index]
            if (item.prev != null) {
                item.prev!!.next = first
                first.prev = item.prev
            }
            last!!.next = item
            item.prev = last
        }
        return list.addAll(index, elements)
    }

    override fun addAll(elements: Collection<T>): Boolean {
        return addAll(list.size, elements)
    }

    override fun remove(element: T): Boolean {
        val index = list.indexOf(element)
        if (index<0) return false
        removeAt(index)
        return list.remove(element)
    }

    override fun removeAll(elements: Collection<T>): Boolean {
        var deleted = false
        for (element in elements) {
            deleted = deleted || remove(element)
        }
        return deleted
    }

    override fun removeAt(index: Int): T {
        val old = list[index]
        if (old.prev != null) {
            old.prev!!.next = old.next
        }
        if (old.next != null) {
            old.next!!.prev = old.prev
        } else {
            this.tail = old.prev
        }
        return list.removeAt(index)
    }

    override fun removeIf(filter: Predicate<in T>): Boolean {
        val elements = list.filter{ filter.test(it) }
        return removeAll(elements)
    }

    override fun replaceAll(operator: UnaryOperator<T>) {
        throw UnsupportedOperationException()
    }

    override fun set(index: Int, element: T): T {
        val old = list.set(index, element)
        if (old.prev!=null) {
            old.prev!!.next = element
            element.prev = old.prev
        }
        if (old.next != null) {
            old.next!!.prev = element
            element.next = old.next
        } else {
            this.tail = element
        }
        return old
    }

    override fun sort(c: Comparator<in T>?) {
        throw UnsupportedOperationException()
    }

    override fun subList(fromIndex: Int, toIndex: Int): LinkList<T> {
        val subList = list.subList(fromIndex, toIndex)
        return LinkList(subList)
    }

    override fun retainAll(elements: Collection<T>): Boolean {
        throw UnsupportedOperationException()
    }

    fun replace(from: Int, to: Int, element: T): Boolean {
        return replace(from, to, listOf(element))
    }

    fun replace(from: Int, to: Int, elements: Collection<T>): Boolean {
        if (from<0 || to>=list.size || to > from) throw IllegalArgumentException()
        if (elements.isEmpty() && from == to) return false
        for (i in from until to) {
            removeAt(i)
        }
        return addAll(from, elements)
    }

    private fun makeLink(elements: Collection<T>): T? {
        var prev: T? = null
        for (item in elements) {
            item.prev = prev
            if (prev != null) prev.next = item
            prev = item
        }
        prev?.next = null
        return prev
    }
}