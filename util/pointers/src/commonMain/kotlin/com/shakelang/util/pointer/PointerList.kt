package com.shakelang.util.pointer

/**
 * Create a list of pointers pointing to the values of the list
 * @receiver The list of values the pointers should point to
 * @return The list of pointers
 * @since 0.1.0
 * @version 0.1.0
 */
fun <T> Iterable<T>.points(): PointerList<T> = map { Pointer.of(it) }

/**
 * Create a mutable list of pointers pointing to the values of the list
 * @receiver The list of values the pointers should point to
 * @return The list of pointers
 * @since 0.1.0
 * @version 0.1.0
 */
fun <T> MutableIterable<T>.mutablePoints(): MutablePointerList<T> = map { Pointer.of(it) }.toMutableList()

/**
 * A PointerList refers to a list of pointers.
 *
 * @param T The type of the value the pointers point to
 * @since 0.1.0
 * @version 0.1.0
 */
typealias PointerList<T> = List<Pointer<T>>

/**
 * A MutablePointerList refers to a list of mutable pointers.
 *
 * @param T The type of the value the pointers point to
 * @since 0.1.0
 * @version 0.1.0
 */
typealias MutablePointerList<T> = MutableList<Pointer<T>>

/**
 * You can create a list that points to the values of a [PointerList] with this function.
 *
 * @receiver The list of pointers
 * @return The list of values
 * @since 0.1.0
 * @version 0.1.0
 * @see PointerList
 * @see PointingList
 */
fun <T> PointerList<T>.values(): PointingList<T> = PointingList.from(this)

/**
 * You can create a list that points to the values of a [MutablePointerList] with this function.
 *
 * @receiver The list of pointers
 * @return The list of values
 * @since 0.1.0
 * @version 0.1.0
 * @see MutablePointerList
 * @see MutablePointingList
 */
fun <T> MutablePointerList<T>.values(): MutablePointingList<T> = MutablePointingList.from(this)

/**
 * A PointingList refers to a list of values that are pointed to by pointers.
 *
 * @param T The type of the value the pointers point to
 * @since 0.1.0
 * @version 0.1.0
 */
interface PointingList<T> : List<T> {

    /**
     * The list of pointers
     */
    val pointers: List<Pointer<T>>

    /**
     * The implementation of the [PointingList].
     * @param T The type of the value the pointers point to
     * @since 0.1.0
     * @version 0.1.0
     * @see PointingList
     */
    private class Impl<T>(

        /**
         * The list of pointers
         */
        override val pointers: List<Pointer<T>>,

        ) : PointingList<T> {

        /**
         * Returns the element at the specified index in the list.
         */
        override val size: Int
            get() = pointers.size

        /**
         * Returns the element at the specified index in the list.
         */
        override fun get(index: Int): T {
            return pointers[index].value
        }

        /**
         * Returns `true` if the list is empty (contains no elements), `false` otherwise.
         */
        override fun isEmpty(): Boolean {
            return pointers.isEmpty()
        }

        /**
         * Returns an iterator over the elements of this list in proper sequence.
         */
        override fun iterator(): Iterator<T> {
            val iterator = pointers.iterator()
            return object : Iterator<T> {
                override fun hasNext(): Boolean = iterator.hasNext()
                override fun next(): T = iterator.next().value
            }
        }

        /**
         * Returns a list iterator over the elements in this list (in proper sequence).
         */
        override fun listIterator(): ListIterator<T> {
            return listIterator(0)
        }

        /**
         * Returns a list iterator over the elements in this list (in proper sequence), starting at the specified [index].
         */
        override fun listIterator(index: Int): ListIterator<T> {
            val iterator = pointers.listIterator(index)
            return object : ListIterator<T> {
                override fun hasNext(): Boolean = iterator.hasNext()
                override fun next(): T = iterator.next().value
                override fun hasPrevious(): Boolean = iterator.hasPrevious()
                override fun previous(): T = iterator.previous().value
                override fun nextIndex(): Int = iterator.nextIndex()
                override fun previousIndex(): Int = iterator.previousIndex()
            }
        }

        /**
         * Returns a view of the portion of this list between the specified [fromIndex] (inclusive) and [toIndex] (exclusive).
         * The returned list is backed by this list, so non-structural changes in the returned list are reflected in this list, and vice-versa.
         *
         * Structural changes in the base list make the behavior of the view undefined.
         */
        override fun subList(fromIndex: Int, toIndex: Int): List<T> {
            return Impl(pointers.subList(fromIndex, toIndex))
        }

        /**
         * Returns the index of the last occurrence of the specified element in the list, or -1 if the specified
         * element is not contained in the list.
         */
        override fun lastIndexOf(element: T): Int {
            return pointers.indexOfLast { it.value == element }
        }

        /**
         * Returns the index of the first occurrence of the specified element in the list, or -1 if the specified
         * element is not contained in the list.
         */
        override fun indexOf(element: T): Int {
            return pointers.indexOfFirst { it.value == element }
        }

        /**
         * Returns `true` if all elements in the specified collection are contained in this collection.
         */
        override fun containsAll(elements: Collection<T>): Boolean {
            return elements.all { contains(it) }
        }

        /**
         * Returns `true` if the specified element is contained in this collection.
         */
        override fun contains(element: T): Boolean {
            return pointers.any { it.value == element }
        }
    }

    companion object {

        /**
         * Create a new [PointingList] from the given list of pointers
         *
         * @param it The list of pointers
         * @return The new [PointingList]
         * @since 0.1.0
         * @version 0.1.0
         */
        fun <T> from(it: List<Pointer<T>>): PointingList<T> {
            return Impl(it)
        }
    }
}

/**
 * A MutablePointingList refers to a list of values that are pointed to by mutable pointers.
 *
 * @param T The type of the value the pointers point to
 * @since 0.1.0
 * @version 0.1.0
 */
interface MutablePointingList<T> : PointingList<T>, MutableList<T> {

    /**
     * The list of pointers
     */
    override val pointers: MutableList<Pointer<T>>

    /**
     * The implementation of the [MutablePointingList].
     * @param T The type of the value the pointers point to
     * @since 0.1.0
     * @version 0.1.0
     * @see MutablePointingList
     */
    class Impl<T>(
        /**
         * The list of pointers
         */
        override val pointers: MutablePointerList<T>,
    ) : MutablePointingList<T> {
        override val size: Int
            get() = pointers.size

        override fun get(index: Int): T {
            return pointers[index].value
        }

        override fun isEmpty(): Boolean {
            return pointers.isEmpty()
        }

        override fun iterator(): MutableIterator<T> {
            val iterator = pointers.iterator()
            return object : MutableIterator<T> {
                override fun hasNext(): Boolean = iterator.hasNext()
                override fun next(): T = iterator.next().value
                override fun remove() = iterator.remove()
            }
        }

        override fun listIterator(): MutableListIterator<T> {
            return listIterator(0)
        }

        override fun listIterator(index: Int): MutableListIterator<T> {
            val iterator = pointers.listIterator(index)
            return object : MutableListIterator<T> {
                override fun add(element: T) = iterator.add(Pointer.of(element))
                override fun hasNext(): Boolean = iterator.hasNext()
                override fun next(): T = iterator.next().value
                override fun hasPrevious(): Boolean = iterator.hasPrevious()
                override fun previous(): T = iterator.previous().value
                override fun nextIndex(): Int = iterator.nextIndex()
                override fun previousIndex(): Int = iterator.previousIndex()
                override fun remove() = iterator.remove()
                override fun set(element: T) = iterator.set(Pointer.of(element))
            }
        }

        override fun subList(fromIndex: Int, toIndex: Int): MutableList<T> {
            return Impl(pointers.subList(fromIndex, toIndex))
        }

        override fun clear() {
            pointers.clear()
        }

        override fun removeAt(index: Int): T {
            return pointers.removeAt(index).value
        }

        override fun set(index: Int, element: T): T {
            return pointers.set(index, Pointer.of(element)).value
        }

        override fun retainAll(elements: Collection<T>): Boolean {
            return pointers.retainAll { it.value in elements }
        }

        override fun removeAll(elements: Collection<T>): Boolean {
            return pointers.removeAll { it.value in elements }
        }

        override fun remove(element: T): Boolean {
            return pointers.removeAll { it.value == element }
        }

        override fun addAll(elements: Collection<T>): Boolean {
            return pointers.addAll(elements.map { Pointer.of(it) })
        }

        override fun addAll(index: Int, elements: Collection<T>): Boolean {
            return pointers.addAll(index, elements.map { Pointer.of(it) })
        }

        override fun add(index: Int, element: T) {
            pointers.add(index, Pointer.of(element))
        }

        override fun add(element: T): Boolean {
            return pointers.add(Pointer.of(element))
        }

        override fun lastIndexOf(element: T): Int {
            return pointers.indexOfLast { it.value == element }
        }

        override fun indexOf(element: T): Int {
            return pointers.indexOfFirst { it.value == element }
        }

        override fun containsAll(elements: Collection<T>): Boolean {
            return elements.all { contains(it) }
        }

        override fun contains(element: T): Boolean {
            return pointers.any { it.value == element }
        }
    }

    companion object {

        /**
         * Create a new [MutablePointingList] from the given list of pointers
         *
         * @param it The list of pointers
         * @return The new [MutablePointingList]
         * @since 0.1.0
         * @version 0.1.0
         */
        fun <T> from(it: MutablePointerList<T>): MutablePointingList<T> {
            return Impl(it)
        }
    }
}
