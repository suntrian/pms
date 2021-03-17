package org.sunt.query.util

import java.util.*

open class CharTrie<V>() : Trie<V> {

    private val root = CharNode<V>('\u0000').also { it.eow=true }

    constructor(map: Map<String, V>): this() {
        putAll(map)
    }

    @Suppress("UNCHECKED_CAST")
    override fun prefixed(key: String): List<Map.Entry<String, V>> {
        val node = walk(key.toCharArray()) ?: return emptyList()
        return walk(node) as List<Map.Entry<String, V>>
    }

    @Suppress("UNCHECKED_CAST")
    override val entries: MutableSet<MutableMap.MutableEntry<String, V>>
        get() {
            val entries = walk()
            return entries.toMutableSet() as MutableSet<MutableMap.MutableEntry<String, V>>
        }
    override val keys: MutableSet<String>
        get() = walk().map { it.key }.toMutableSet()
    override val values: MutableCollection<V>
        get() = walk().mapNotNull { it.value }.toMutableSet()

    override var size: Int = 0

    override fun containsKey(key: String): Boolean = walk(key.toCharArray()) != null

    override fun containsValue(value: V): Boolean {
        return values.contains(value)
    }

    fun get(key: CharArray): V? {
        val node = walk(key)
        return node?.value
    }

    override fun get(key: String): V? = get(key.toCharArray())

    override fun isEmpty(): Boolean = root.children.isEmpty()

    override fun clear() {
        root.children.clear()
    }

    override fun put(key: String, value: V): V? {
        size++
        var node = root
        for (c in key.toCharArray()) {
            node = node.children[c]?:CharNode(c, node)
        }
        node.value = value
        node.eow = true
        return value
    }

    final override fun putAll(from: Map<out String, V>) {
        for ((k, v) in from) {
            put(k, v)
        }
    }

    override fun remove(key: String): V? {
        size--
        val node = walk(key.toCharArray())?: return null
        var last = node
        if (last.children.isNotEmpty()) {
            last.eow = false
        } else {
            while (!last.eow) {
                last.parent?.children?.remove(last.item)?:break
                last =last.parent!!
            }
        }
        return node.value
    }

    fun walk(key: CharArray): CharNode<V>? {
        var node = root
        for (c in key) {
            node = node.children[c]?: return null
        }
        return node
    }

    /**
     * 深度优先遍历树
     */
    @JvmOverloads
    fun walk(start: CharNode<V> = root): List<CharNode<V>> {
        val result = mutableListOf<CharNode<V>>()
        val stack =Stack<CharNode<V>>()
        stack.push(start)
        var node: CharNode<V>
        while (stack.isNotEmpty()) {
            node = stack.pop()
            for ((_, v) in node.children) {
                if (v.eow) {
                    result.add(v)
                }
                if (v.children.isNotEmpty()) {
                    stack.push(v)
                }
            }
        }
        return result
    }

    data class CharNode<V>(
        override val item: Char,
        var parent: CharNode<V>? = null
    ) : Trie.Node<Char, V>, MutableMap.MutableEntry<String, V?> {

        init {
            if (parent != null) {
                parent!!.children[item] = this
            }
        }

        var eow: Boolean = false           //end of word
        override var value: V? = null
        override val children: MutableMap<Char, CharNode<V>> = mutableMapOf()

        fun isRoot(): Boolean = parent == null

        override val key: String
            get() {
                val chars = StringBuilder()
                var node = this
                while (!node.isRoot()) {
                    chars.append(node.item)
                    node = node.parent?:break
                }
                return chars.reversed().toString()
            }

        override fun setValue(newValue: V?): V? {
            val oldValue = this.value
            this.value = newValue
            return oldValue
        }

        override fun toString(): String = "{ $key : $value }"

    }

}