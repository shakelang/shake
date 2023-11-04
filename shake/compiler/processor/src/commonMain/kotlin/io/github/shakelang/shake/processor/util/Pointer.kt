package io.github.shakelang.shake.processor.util

interface Pointer<out T> {
    val value: T

    fun <A> transform(transform: (T) -> A): Pointer<A> {
        return object : Pointer<A> {
            override val value: A
                get() = transform(this@Pointer.value)
        }
    }

    fun <A> chain(transform: (T) -> Pointer<A>): Pointer<A> {
        return object : Pointer<A> {
            override val value: A
                get() = transform(this@Pointer.value).value
        }
    }

    fun <A> chainAllowNull(transform: (T) -> Pointer<A?>?): Pointer<A?> {
        return object : Pointer<A?> {
            override val value: A?
                get() = transform(this@Pointer.value)?.value
        }
    }


    companion object {
        fun <T> of(value: T) = object : Pointer<T> {
            override val value: T = value
        }

        fun <T> mutableOf(value: T) = object : MutablePointer<T> {
            override var value: T = value
        }

        fun <T> late() = object : LateInitPointer<T> {
            var realValue: T? = null

            override var isInitialized = false
                private set

            @Suppress("UNCHECKED_CAST")
            override val value: T
                get() = if (isInitialized) realValue as T else throw IllegalStateException("lateinit pointer is not initialized")

            override fun init(value: T) {
                if (isInitialized) throw IllegalStateException("late init pointer is already initialized")
                this.realValue = value
                isInitialized = true
            }
        }

        fun <T> lateMutable() = object : LateInitMutablePointer<T> {
            var realValue: T? = null

            override var isInitialized = false
                private set

            @Suppress("UNCHECKED_CAST")
            override var value: T
                get() = if (isInitialized) realValue as T else throw IllegalStateException("late init pointer is not initialized")
                set(value) {
                    realValue = value
                    isInitialized = true
                }

            override fun init(value: T) {
                if (isInitialized) throw IllegalStateException("late init pointer is already initialized")
                this.realValue = value
                isInitialized = true
            }
        }

        fun <T> task(task: () -> T) = object : Pointer<T> {
            override val value: T = task()
        }
    }
}

fun <T> latePoint(): LateInitPointer<T> = Pointer.late()
fun <T> lateMutablePoint(): LateInitMutablePointer<T> = Pointer.lateMutable()

fun <T> T.point(): Pointer<T> = Pointer.of(this)
fun <T> T.mutablePoint(): MutablePointer<T> = Pointer.mutableOf(this)

fun <T> Iterable<T>.points(): PointerList<T> = map { Pointer.of(it) }
fun <T> Iterable<T>.mutablePoints(): MutablePointerList<T> = map { Pointer.of(it) }.toMutableList()

interface MutablePointer<T> : Pointer<T> {
    override var value: T
}

interface LateInitPointer<T> : Pointer<T> {
    override val value: T
    val isInitialized: Boolean
    fun init(value: T)
}

interface LateInitMutablePointer<T> : MutablePointer<T>, LateInitPointer<T> {
    override var value: T
}

typealias PointerList<T> = List<Pointer<T>>
typealias MutablePointerList<T> = MutableList<Pointer<T>>

fun <T> PointerList<T>.values(): PointingList<T> = PointingList.from(this)
fun <T> MutablePointerList<T>.values(): MutablePointingList<T> = MutablePointingList.from(this)

interface PointingList<T> : List<T> {
    val pointers: List<Pointer<T>>

    private class Impl<T>(
        override val pointers: List<Pointer<T>>
    ) : PointingList<T> {
        override val size: Int
            get() = pointers.size

        /**
         * Returns the element at the specified index in the list.
         */
        override fun get(index: Int): T {
            return pointers[index].value
        }

        override fun isEmpty(): Boolean {
            return pointers.isEmpty()
        }

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

        override fun containsAll(elements: Collection<T>): Boolean {
            return pointers.all { it.value in elements }
        }

        override fun contains(element: T): Boolean {
            return pointers.any { it.value == element }
        }
    }

    companion object {
        fun <T> from(it: List<Pointer<T>>): PointingList<T> {
            return Impl(it)
        }
    }
}

interface MutablePointingList<T> : PointingList<T>, MutableList<T> {
    override val pointers: MutableList<Pointer<T>>

    class Impl<T>(
        override val pointers: MutablePointerList<T>
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
            return pointers.all { it.value in elements }
        }

        override fun contains(element: T): Boolean {
            return pointers.any { it.value == element }
        }

    }

    companion object {
        fun <T> from(it: MutablePointerList<T>): MutablePointingList<T> {
            return Impl(it)
        }
    }
}

fun <T> Pointer<T?>.notNull(msg: String? = null): Pointer<T> {
    return this.transform { it ?: throw IllegalStateException(msg ?: "null value not allowed") }
}