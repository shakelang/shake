package com.github.shakelang.shake.util.json



interface MapType<K, V, MT : MapType<K, V, MT, MMT>, MMT: MutableMapType<K, V, MT, MMT>> : Map<K, V> {

    fun toMap(): MT
    fun toMutableMap(): MMT

}

interface MutableMapType<K, V, MT : MapType<K, V, MT, MMT>, MMT: MutableMapType<K, V, MT, MMT>>
    : MutableMap<K, V>, MapType<K, V, MT, MMT>

abstract class MapBase<K, V, MT : MapType<K, V, MT, MMT>, MMT: MutableMapType<K, V, MT, MMT>> (

    val map: Map<K, V>,

    ) : MapType<K, V, MT, MMT> {

    override val entries: Set<Map.Entry<K, V>>
        get() = this.map.entries

    override val keys: Set<K>
        get() = this.map.keys

    override val size: Int
        get() = this.map.size

    override val values: Collection<V>
        get() = this.map.values

    override fun containsKey(key: K): Boolean
            = this.map.containsKey(key)

    override fun containsValue(value: V): Boolean
            = this.map.containsValue(value)

    override fun get(key: K): V?
            = this.map[key]

    override fun isEmpty(): Boolean
            = this.map.isEmpty()

}

abstract class MutableMapBase<K, V, MT : MapType<K, V, MT, MMT>, MMT: MutableMapType<K, V, MT, MMT>> (

    val map: MutableMap<K, V>,

    ) : MutableMapType<K, V, MT, MMT> {

    override val entries: MutableSet<MutableMap.MutableEntry<K, V>>
        get() = this.map.entries

    override val keys: MutableSet<K>
        get() = this.map.keys

    override val size: Int
        get() = this.map.size

    override val values: MutableCollection<V>
        get() = this.map.values

    override fun containsKey(key: K): Boolean
            = this.map.containsKey(key)

    override fun containsValue(value: V): Boolean
            = this.map.containsValue(value)

    override fun get(key: K): V?
            = this.map[key]

    override fun isEmpty(): Boolean
            = this.map.isEmpty()

    override fun clear()
            = this.map.clear()

    override fun put(key: K, value: V): V?
            = this.map.put(key, value)

    override fun putAll(from: Map<out K, V>)
            = this.map.putAll(from)

    override fun remove(key: K): V?
            = this.map.remove(key)

}