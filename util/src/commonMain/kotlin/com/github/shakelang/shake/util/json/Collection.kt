package com.github.shakelang.shake.util.json



interface CollectionType<T, CT : CollectionType<T, CT, MCT>, MCT: MutableCollectionType<T, CT, MCT>> : Collection<T> {

    fun toMap(): CT
    fun toMutableMap(): MCT

}

interface MutableCollectionType<T, CT : CollectionType<T, CT, MCT>, MCT: MutableCollectionType<T, CT, MCT>>
    : MutableCollection<T>, CollectionType<T, CT, MCT>

abstract class CollectionBase<T, CT : CollectionType<T, CT, MCT>, MCT: MutableCollectionType<T, CT, MCT>> (

    val collection: Collection<T>,

    ) : CollectionType<T, CT, MCT> {

    override val size: Int
        get() = collection.size

    override fun contains(element: T): Boolean
            = collection.contains(element)

    override fun containsAll(elements: Collection<T>): Boolean
            = collection.containsAll(elements)

    override fun isEmpty(): Boolean
            = collection.isEmpty()

    override fun iterator(): Iterator<T>
            = collection.iterator()

}

abstract class MutableCollectionBase<T, CT : CollectionType<T, CT, MCT>, MCT: MutableCollectionType<T, CT, MCT>> (

    val collection: MutableCollection<T>,

) : MutableCollectionType<T, CT, MCT> {

    override val size: Int
        get() = collection.size

    override fun contains(element: T): Boolean
        = collection.contains(element)

    override fun containsAll(elements: Collection<T>): Boolean
        = collection.containsAll(elements)

    override fun isEmpty(): Boolean
        = collection.isEmpty()

    override fun iterator(): MutableIterator<T>
        = collection.iterator()

    override fun add(element: T): Boolean
        = collection.add(element)

    override fun addAll(elements: Collection<T>): Boolean
        = collection.addAll(elements)

    override fun clear()
        = collection.clear()

    override fun remove(element: T): Boolean
        = collection.remove(element)

    override fun removeAll(elements: Collection<T>): Boolean
        = collection.removeAll(elements)

    override fun retainAll(elements: Collection<T>): Boolean
        = collection.removeAll(elements)

}