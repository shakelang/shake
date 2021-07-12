package com.github.shakelang.shake.util.json.collection

import com.github.shakelang.shake.util.json.map.MapBase
import com.github.shakelang.shake.util.json.map.MapType


/**
 * A type for an own [Collection] implementation.
 *
 * @author [Nicolas Schmidt &lt;@nsc-de&gt;](https://github.com/nsc-de)
 */
interface CollectionType<T, CT : CollectionType<T, CT, MCT>, MCT: MutableCollectionType<T, CT, MCT>> : Collection<T> {

    /**
     * Create a new [Collection] from this [Collection].
     *
     * @author [Nicolas Schmidt &lt;@nsc-de&gt;](https://github.com/nsc-de)
     */
    fun toCollection(): CT

    /**
     * Create a new [MutableCollection] from this [Collection].
     *
     * @author [Nicolas Schmidt &lt;@nsc-de&gt;](https://github.com/nsc-de)
     */
    fun toMutableCollection(): MCT

}


/**
 * A type for an own mutable [Collection] implementation.
 *
 * @author [Nicolas Schmidt &lt;@nsc-de&gt;](https://github.com/nsc-de)
 */
interface MutableCollectionType<T, CT : CollectionType<T, CT, MCT>, MCT: MutableCollectionType<T, CT, MCT>>
    : MutableCollection<T>, CollectionType<T, CT, MCT>

/**
 * A base API class for an implementation of [CollectionType]
 *
 * @author [Nicolas Schmidt &lt;@nsc-de&gt;](https://github.com/nsc-de)
 */
abstract class CollectionBase<T, CT : CollectionType<T, CT, MCT>, MCT: MutableCollectionType<T, CT, MCT>> (

    /**
     * The [Collection] to create the [CollectionBase] from
     */
    val collection: Collection<T>,

) : CollectionType<T, CT, MCT> {

    /**
     * Returns the size of the collection.
     */
    override val size: Int
        get() = collection.size

    /**
     * Checks if the specified element is contained in this collection.
     *
     * @author [Nicolas Schmidt &lt;@nsc-de&gt;](https://github.com/nsc-de)
     */
    override fun contains(element: T): Boolean
            = collection.contains(element)

    /**
     * Checks if all elements in the specified collection are contained in this collection.
     *
     * @author [Nicolas Schmidt &lt;@nsc-de&gt;](https://github.com/nsc-de)
     */
    override fun containsAll(elements: Collection<T>): Boolean
            = collection.containsAll(elements)

    /**
     * Returns `true` if the collection is empty (contains no elements), `false` otherwise.
     *
     * @author [Nicolas Schmidt &lt;@nsc-de&gt;](https://github.com/nsc-de)
     */
    override fun isEmpty(): Boolean
            = collection.isEmpty()

    /**
     * Returns an iterator over the elements of this object.
     *
     * @author [Nicolas Schmidt &lt;@nsc-de&gt;](https://github.com/nsc-de)
     */
    override fun iterator(): Iterator<T>
            = collection.iterator()

}

/**
 * A base API class for an implementation of [MutableCollectionType]
 *
 * @author [Nicolas Schmidt &lt;@nsc-de&gt;](https://github.com/nsc-de)
 */
abstract class MutableCollectionBase<T, CT : CollectionType<T, CT, MCT>, MCT: MutableCollectionType<T, CT, MCT>> (

    /**
     * The [MutableCollection] to create the [MutableCollectionBase] from
     */
    val collection: MutableCollection<T>,

) : MutableCollectionType<T, CT, MCT> {

    /**
     * Returns the size of the collection.
     */
    override val size: Int
        get() = collection.size

    /**
     * Checks if the specified element is contained in this collection.
     *
     * @author [Nicolas Schmidt &lt;@nsc-de&gt;](https://github.com/nsc-de)
     */
    override fun contains(element: T): Boolean
            = collection.contains(element)

    /**
     * Checks if all elements in the specified collection are contained in this collection.
     *
     * @author [Nicolas Schmidt &lt;@nsc-de&gt;](https://github.com/nsc-de)
     */
    override fun containsAll(elements: Collection<T>): Boolean
            = collection.containsAll(elements)

    /**
     * Returns `true` if the collection is empty (contains no elements), `false` otherwise.
     *
     * @author [Nicolas Schmidt &lt;@nsc-de&gt;](https://github.com/nsc-de)
     */
    override fun isEmpty(): Boolean
            = collection.isEmpty()

    /**
     * Returns an iterator over the elements of this object.
     *
     * @author [Nicolas Schmidt &lt;@nsc-de&gt;](https://github.com/nsc-de)
     */
    override fun iterator(): MutableIterator<T>
            = collection.iterator()

    /**
     * Adds the specified element to the collection.
     *
     * @return `true` if the element has been added, `false` if the collection does not support duplicates
     * and the element is already contained in the collection.
     *
     * @author [Nicolas Schmidt &lt;@nsc-de&gt;](https://github.com/nsc-de)
     */
    override fun add(element: T): Boolean
        = collection.add(element)

    /**
     * Adds all of the elements of the specified collection to this collection.
     *
     * @return `true` if any of the specified elements was added to the collection, `false` if the collection was not modified.
     *
     * @author [Nicolas Schmidt &lt;@nsc-de&gt;](https://github.com/nsc-de)
     */
    override fun addAll(elements: Collection<T>): Boolean
        = collection.addAll(elements)

    /**
     * Removes all elements from this collection.
     *
     * @author [Nicolas Schmidt &lt;@nsc-de&gt;](https://github.com/nsc-de)
     */
    override fun clear()
        = collection.clear()

    /**
     * Removes a single instance of the specified element from this
     * collection, if it is present.
     *
     * @return `true` if the element has been successfully removed; `false` if it was not present in the collection.
     *
     * @author [Nicolas Schmidt &lt;@nsc-de&gt;](https://github.com/nsc-de)
     */
    override fun remove(element: T): Boolean
        = collection.remove(element)

    /**
     * Removes all of this collection's elements that are also contained in the specified collection.
     *
     * @return `true` if any of the specified elements was removed from the collection, `false` if the collection was not modified.
     *
     * @author [Nicolas Schmidt &lt;@nsc-de&gt;](https://github.com/nsc-de)
     */
    override fun removeAll(elements: Collection<T>): Boolean
        = collection.removeAll(elements)

    /**
     * Removes all of this collection's elements that are also contained in the specified collection.
     *
     * @return `true` if any of the specified elements was removed from the collection, `false` if the collection was not modified.
     *
     * @author [Nicolas Schmidt &lt;@nsc-de&gt;](https://github.com/nsc-de)
     */
    override fun retainAll(elements: Collection<T>): Boolean
        = collection.removeAll(elements)

}