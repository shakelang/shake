package com.shakelang.util.shason.collection

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
interface MutableListType<T, CT : ListType<T, CT, MCT>, MCT : MutableListType<T, CT, MCT>> :
    MutableList<T>, ListType<T, CT, MCT>

/**
 * A base API class for an implementation of [ListType]
 */
abstract class ListBase<T, CT : ListType<T, CT, MCT>, MCT : MutableListType<T, CT, MCT>>(

    /**
     * The [List] to create the [ListBase] from
     */
    val list: List<T>,

    ) : ListType<T, CT, MCT>, List<T> by list

/**
 * A base API class for an implementation of [MutableListType]
 */
abstract class MutableListBase<T, CT : ListType<T, CT, MCT>, MCT : MutableListType<T, CT, MCT>>(

    /**
     * The [MutableList] to create the [MutableListBase] from
     */
    val list: MutableList<T>,

    ) : MutableListType<T, CT, MCT>, MutableList<T> by list
