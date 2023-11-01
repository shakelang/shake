package io.github.shakelang.shake.util.shason.map


/**
 * A type for an own map implementation.
 *
 * @author [Nicolas Schmidt &lt;@nsc-de&gt;](https://github.com/nsc-de)
 */
interface MapType<K, V, MT : MapType<K, V, MT, MMT>, MMT : MutableMapType<K, V, MT, MMT>> : Map<K, V> {

    /**
     * Create a new map from this map
     *
     * @author [Nicolas Schmidt &lt;@nsc-de&gt;](https://github.com/nsc-de)
     */
    fun toMap(): MT

    /**
     * Create a mutable map from this map
     *
     * @author [Nicolas Schmidt &lt;@nsc-de&gt;](https://github.com/nsc-de)
     */
    fun toMutableMap(): MMT

}


/**
 * A type for an own mutable map implementation.
 *
 * @author [Nicolas Schmidt &lt;@nsc-de&gt;](https://github.com/nsc-de)
 */
interface MutableMapType<K, V, MT : MapType<K, V, MT, MMT>, MMT : MutableMapType<K, V, MT, MMT>>
    : MutableMap<K, V>, MapType<K, V, MT, MMT>

/**
 * A base API class for an implementation of [MapType]
 *
 * @author [Nicolas Schmidt &lt;@nsc-de&gt;](https://github.com/nsc-de)
 */
abstract class MapBase<K, V, MT : MapType<K, V, MT, MMT>, MMT : MutableMapType<K, V, MT, MMT>>(

    /**
     * The [Map] to create the [MapBase] from
     */
    val map: Map<K, V>,

    ) : MapType<K, V, MT, MMT> {

    /**
     * Returns a read-only [Set] of all key/value pairs in this map.
     */
    override val entries: Set<Map.Entry<K, V>>
        get() = this.map.entries

    /**
     * Returns a read-only [Collection] of all values in this map. Note that this collection may contain
     * duplicate values.
     */
    override val keys: Set<K>
        get() = this.map.keys

    /**
     * Returns the number of key/value pairs in the map.
     */
    override val size: Int
        get() = this.map.size

    /**
     * Returns a read-only [Collection] of all values in this map. Note that this collection may contain
     * duplicate values.
     */
    override val values: Collection<V>
        get() = this.map.values

    /**
     * Returns `true` if the map contains the specified [key].
     *
     * @author [Nicolas Schmidt &lt;@nsc-de&gt;](https://github.com/nsc-de)
     */
    override fun containsKey(key: K): Boolean = this.map.containsKey(key)

    /**
     * Returns `true` if the map maps one or more keys to the specified [value].
     *
     * @author [Nicolas Schmidt &lt;@nsc-de&gt;](https://github.com/nsc-de)
     */
    override fun containsValue(value: V): Boolean = this.map.containsValue(value)

    /**
     * Returns the value corresponding to the given [key], or `null` if such a key is not present in the map.
     *
     * @author [Nicolas Schmidt &lt;@nsc-de&gt;](https://github.com/nsc-de)
     */
    override fun get(key: K): V? = this.map[key]

    /**
     * Returns `true` if the map is empty (contains no elements), `false` otherwise.
     *
     * @author [Nicolas Schmidt &lt;@nsc-de&gt;](https://github.com/nsc-de)
     */
    override fun isEmpty(): Boolean = this.map.isEmpty()

}

abstract class MutableMapBase<K, V, MT : MapType<K, V, MT, MMT>, MMT : MutableMapType<K, V, MT, MMT>>(

    /**
     * The [MutableMap] to create the [MutableMapBase] from
     */
    val map: MutableMap<K, V>,

    ) : MutableMapType<K, V, MT, MMT> {

    /**
     * Returns a read-only [Set] of all key/value pairs in this map.
     */
    override val entries: MutableSet<MutableMap.MutableEntry<K, V>>
        get() = this.map.entries

    /**
     * Returns a read-only [Collection] of all values in this map. Note that this collection may contain
     * duplicate values.
     */
    override val keys: MutableSet<K>
        get() = this.map.keys

    /**
     * Returns the number of key/value pairs in the map.
     */
    override val size: Int
        get() = this.map.size

    /**
     * Returns a read-only [Collection] of all values in this map. Note that this collection may contain
     * duplicate values.
     */
    override val values: MutableCollection<V>
        get() = this.map.values

    /**
     * Returns `true` if the map contains the specified [key].
     *
     * @author [Nicolas Schmidt &lt;@nsc-de&gt;](https://github.com/nsc-de)
     */
    override fun containsKey(key: K): Boolean = this.map.containsKey(key)

    /**
     * Returns `true` if the map maps one or more keys to the specified [value].
     *
     * @author [Nicolas Schmidt &lt;@nsc-de&gt;](https://github.com/nsc-de)
     */
    override fun containsValue(value: V): Boolean = this.map.containsValue(value)

    /**
     * Returns the value corresponding to the given [key], or `null` if such a key is not present in the map.
     *
     * @author [Nicolas Schmidt &lt;@nsc-de&gt;](https://github.com/nsc-de)
     */
    override fun get(key: K): V? = this.map[key]

    /**
     * Returns `true` if the map is empty (contains no elements), `false` otherwise.
     *
     * @author [Nicolas Schmidt &lt;@nsc-de&gt;](https://github.com/nsc-de)
     */
    override fun isEmpty(): Boolean = this.map.isEmpty()

    /**
     * Removes all elements from this map.
     *
     * @author [Nicolas Schmidt &lt;@nsc-de&gt;](https://github.com/nsc-de)
     */
    override fun clear() = this.map.clear()

    /**
     * Associates the specified [value] with the specified [key] in the map.
     *
     * @return the previous value associated with the key, or `null` if the key was not present in the map.
     *
     * @author [Nicolas Schmidt &lt;@nsc-de&gt;](https://github.com/nsc-de)
     */
    override fun put(key: K, value: V): V? = this.map.put(key, value)

    /**
     * Updates this map with key/value pairs from the specified map [from].
     *
     * @author [Nicolas Schmidt &lt;@nsc-de&gt;](https://github.com/nsc-de)
     */
    override fun putAll(from: Map<out K, V>) = this.map.putAll(from)

    /**
     * Removes the specified key and its corresponding value from this map.
     *
     * @return the previous value associated with the key, or `null` if the key was not present in the map.
     *
     * @author [Nicolas Schmidt &lt;@nsc-de&gt;](https://github.com/nsc-de)
     */
    override fun remove(key: K): V? = this.map.remove(key)

}