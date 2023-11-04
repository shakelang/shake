package io.github.shakelang.shake.util.shason.collection

/**
 * A type for an own [List] implementation.
 */
interface ListType<T, CT : ListType<T, CT, MCT>, MCT : MutableListType<T, CT, MCT>> : List<T> {

    /**
     * Create a new [List] from this [List].
     */
    fun toCollection(): CT

    /**
     * Create a new [MutableCollection] from this [Collection].
     */
    fun toMutableCollection(): MCT

}


/**
 * A type for an own [MutableList] implementation.
 */
interface MutableListType<T, CT : ListType<T, CT, MCT>, MCT : MutableListType<T, CT, MCT>>
    : MutableList<T>, ListType<T, CT, MCT>

/**
 * A base API class for an implementation of [ListType]
 */
abstract class ListBase<T, CT : ListType<T, CT, MCT>, MCT : MutableListType<T, CT, MCT>>(

    /**
     * The [List] to create the [ListBase] from
     */
    val list: List<T>,

) : ListType<T, CT, MCT> {

    /**
     * Returns the size of the collection.
     */
    override val size: Int
        get() = list.size

    /**
     * Checks if the specified element is contained in this list.
     */
    override fun contains(element: T): Boolean = list.contains(element)

    /**
     * Checks if all elements in the specified collection are contained in this list.
     */
    override fun containsAll(elements: Collection<T>): Boolean = list.containsAll(elements)

    /**
     * Returns `true` if the list is empty (contains no elements), `false` otherwise.
     */
    override fun isEmpty(): Boolean = list.isEmpty()

    /**
     * Returns an iterator over the elements of this object.
     */
    override fun iterator(): Iterator<T> = list.iterator()

    override fun get(index: Int): T = list[index]

    override fun indexOf(element: T): Int = list.indexOf(element)

    override fun lastIndexOf(element: T): Int = list.lastIndexOf(element)

    override fun listIterator(): ListIterator<T> = list.listIterator()

    override fun listIterator(index: Int): ListIterator<T> = list.listIterator()

    override fun subList(fromIndex: Int, toIndex: Int): List<T> = list.subList(fromIndex, toIndex)

}

/**
 * A base API class for an implementation of [MutableListType]
 */
abstract class MutableListBase<T, CT : ListType<T, CT, MCT>, MCT : MutableListType<T, CT, MCT>>(

    /**
     * The [MutableList] to create the [MutableListBase] from
     */
    val list: MutableList<T>,

    ) : MutableListType<T, CT, MCT> {

    /**
     * Returns the size of the list.
     */
    override val size: Int
        get() = list.size

    /**
     * Checks if the specified element is contained in this list.
     */
    override fun contains(element: T): Boolean = list.contains(element)

    /**
     * Checks if all elements in the specified collection are contained in this list.
     */
    override fun containsAll(elements: Collection<T>): Boolean = list.containsAll(elements)

    /**
     * Returns `true` if the list is empty (contains no elements), `false` otherwise.
     */
    override fun isEmpty(): Boolean = list.isEmpty()

    /**
     * Returns an iterator over the elements of this object.
     */
    override fun iterator(): MutableIterator<T> = list.iterator()

    /**
     * Adds the specified element to the list.
     *
     * @return `true` if the element has been added, `false` if the list does not support duplicates
     * and the element is already contained in the list.
     */
    override fun add(element: T): Boolean = list.add(element)

    /**
     * Adds all of the elements of the specified collection to this list.
     *
     * @return `true` if any of the specified elements was added to the list, `false` if the list was not modified.
     */
    override fun addAll(elements: Collection<T>): Boolean = list.addAll(elements)

    /**
     * Removes all elements from this list.
     */
    override fun clear() = list.clear()

    /**
     * Removes a single instance of the specified element from this
     * list, if it is present.
     *
     * @return `true` if the element has been successfully removed; `false` if it was not present in the list.
     */
    override fun remove(element: T): Boolean = list.remove(element)

    /**
     * Removes all of this list's elements that are also contained in the specified collection.
     *
     * @return `true` if any of the specified elements was removed from the list, `false` if the list was not modified.
     */
    override fun removeAll(elements: Collection<T>): Boolean = list.removeAll(elements)

    /**
     * Removes all of this list's elements that are also contained in the specified collection.
     *
     * @return `true` if any of the specified elements was removed from the list, `false` if the list was not modified.
     */
    override fun retainAll(elements: Collection<T>): Boolean = list.removeAll(elements)

    override fun get(index: Int): T = list[index]

    override fun indexOf(element: T): Int = list.indexOf(element)

    override fun lastIndexOf(element: T): Int = list.lastIndexOf(element)

    override fun listIterator(): MutableListIterator<T> = list.listIterator()

    override fun listIterator(index: Int): MutableListIterator<T> = list.listIterator()

    override fun subList(fromIndex: Int, toIndex: Int): MutableList<T> = list.subList(fromIndex, toIndex)

    override fun add(index: Int, element: T) = list.add(index, element)

    override fun addAll(index: Int, elements: Collection<T>): Boolean = list.addAll(index, elements)

    override fun removeAt(index: Int): T = list.removeAt(index)

    override fun set(index: Int, element: T): T = list.set(index, element)

}