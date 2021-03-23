package org.sunt.query.model.util

interface Trie<V> : MutableMap<String, V> {

    fun prefixed(key: String): List<Map.Entry<String, V>>

    override val entries: MutableSet<MutableMap.MutableEntry<String, V>>

    override val keys: MutableSet<String>

    override val values: MutableCollection<V>

    override val size: Int

    override fun containsKey(key: String): Boolean

    override fun containsValue(value: V): Boolean

    override fun get(key: String): V?

    override fun isEmpty(): Boolean

    override fun clear()

    override fun put(key: String, value: V): V?

    override fun putAll(from: Map<out String, V>)

    override fun remove(key: String): V?

    interface Node<K, V> {
        val item: K
        val value: V?
        val children: Map<K, out Node<K, V>>
    }

}