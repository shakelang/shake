package com.shakelang.util.io.streaming

/**
 * A stream is a sequence of elements that can be read one by one
 * @param T The type of the elements in the stream
 * @since 0.5.0
 */
interface Stream<T> {

    /**
     * Read the next element from the stream
     * @return The next element
     * @since 0.5.0
     */
    fun read(): T

    /**
     * Check if the stream has a next element
     * @return If the stream has a next element
     * @since 0.5.0
     */
    fun hasNext(): Boolean

    /**
     * Map the stream to another stream
     * @param R The type of the new stream
     * @param mapper The mapper function
     * @return The new stream
     * @since 0.5.0
     */
    fun <R> map(mapper: (T) -> R): Stream<R> = MapStream(this, mapper)

    /**
     * Map the stream to another stream
     * @param R The type of the new stream
     * @param mapper The mapper function
     * @return The new stream
     * @since 0.5.0
     */
    fun <R> mapIndexed(mapper: (Int, T) -> R): Stream<R> = IndexedMapStream(this, mapper)
}

/**
 * A LinkedList-based stream that can be appended to
 * @param T The type of the elements in the stream
 * @property head The first element in the stream
 * @property tail The last element in the stream
 * @constructor Create a new appendable stream
 * @since 0.5.0
 */
class AppendableStream<T> : Stream<T> {

    /**
     * The first element in the stream
     * @since 0.5.0
     */
    private var head: StreamElement<T>? = null

    /**
     * The last element in the stream
     * @since 0.5.0
     */
    private var tail: StreamElement<T>? = null

    /**
     * Append a new element to the stream
     * @param value The value of the new element
     * @since 0.5.0
     */
    fun append(value: T) {
        val newElement = StreamElement(value)

        if (head == null) {
            head = newElement
        } else {
            tail!!.attachNext(newElement)
        }

        tail = newElement
    }

    /**
     * Append multiple new elements to the stream
     * @param values The values of the new elements
     * @since 0.5.0
     */
    fun append(vararg values: T) {
        for (value in values) {
            this.append(value)
        }
    }

    private fun pop(): StreamElement<T>? {
        if (head == null) return null

        val head = this.head
        this.head = head!!.next
        return head
    }

    /**
     * Read the next element from the stream
     * @return The next element
     * @since 0.5.0
     */
    override fun read(): T {
        val head = this.pop() ?: throw IllegalStateException("Stream is empty")
        return head.value
    }

    /**
     * Check if the stream has a next element
     * @return If the stream has a next element
     * @since 0.5.0
     */
    override fun hasNext(): Boolean {
        return this.head != null
    }

    /**
     * A private class representing an element in the stream
     * @property value The value of the element
     * @since 0.5.0
     */
    private class StreamElement<T>(
        /**
         * The value of the element
         * @since 0.5.0
         */
        val value: T,
    ) {

        /**
         * The next element in the stream
         * @since 0.5.0
         */
        var next: StreamElement<T>? = null
            private set

        /**
         * Attach the next element to this element
         * @since 0.5.0
         */
        fun attachNext(next: StreamElement<T>?) {
            this.next = next
        }
    }
}

/**
 * Create a stream from a sequence
 * @param sequence The sequence to create the stream from
 * @return The created stream
 * @since 0.5.0
 */
class SequenceStream<T>(

    /**
     * The sequence to create the stream from
     * @since 0.5.0
     */
    sequence: Sequence<T>,
) : Stream<T> {

    /**
     * The iterator of the sequence
     * @since 0.5.0
     */
    val iterator = sequence.iterator()

    /**
     * Read the next element from the stream
     * @return The next element
     * @since 0.5.0
     */
    override fun read(): T {
        if (!hasNext()) throw IllegalStateException("Stream is empty")
        return iterator.next()
    }

    /**
     * Check if the stream has a next element
     * @return If the stream has a next element
     * @since 0.5.0
     */
    override fun hasNext() = iterator.hasNext()
}

/**
 * Create a stream from a list
 * The stream internally uses the list's iterator (because, depending on the list's implementation,
 * there might be performance gains over using the list's get method)
 * @param list The list to create the stream from
 * @return The created stream
 * @since 0.5.0
 */
class ListStream<T>(
    /**
     * The list to create the stream from
     * @since 0.5.0
     */
    private val list: List<T>,
) : Stream<T> {

    /**
     * The iterator of the list
     * @since 0.5.0
     */
    private val iterator = list.iterator()

    /**
     * Read the next element from the stream
     * @return The next element
     * @since 0.5.0
     */
    override fun read(): T {
        if (!hasNext()) throw IllegalStateException("Stream is empty")
        return iterator.next()
    }

    /**
     * Check if the stream has a next element
     * @return If the stream has a next element
     * @since 0.5.0
     */
    override fun hasNext() = iterator.hasNext()
}

/**
 * Create a stream from an array
 * @param array The array to create the stream from
 * @return The created stream
 * @since 0.5.0
 */
class ArrayStream<T>(

    /**
     * The array to create the stream from
     * @since 0.5.0
     */
    private val array: Array<T>,
) : Stream<T> {

    /**
     * The current index of the stream
     * @since 0.5.0
     */
    private var index = 0

    /**
     * Read the next element from the stream
     * @return The next element
     * @since 0.5.0
     */
    override fun read(): T {
        if (!hasNext()) throw IllegalStateException("Stream is empty")
        return array[index++]
    }

    /**
     * Check if the stream has a next element
     * @return If the stream has a next element
     * @since 0.5.0
     */
    override fun hasNext() = index < array.size
}

/**
 * Create a stream from an iterator
 * @param iterator The iterator to create the stream from
 * @return The created stream
 * @since 0.5.0
 */
class IteratorStream<T>(

    /**
     * The iterator to create the stream from
     * @since 0.5.0
     */
    private val iterator: Iterator<T>,

) : Stream<T> {

    /**
     * Read the next element from the stream
     * @return The next element
     * @since 0.5.0
     */
    override fun read(): T {
        if (!hasNext()) throw IllegalStateException("Stream is empty")
        return iterator.next()
    }

    /**
     * Check if the stream has a next element
     * @return If the stream has a next element
     * @since 0.5.0
     */
    override fun hasNext() = iterator.hasNext()
}

/**
 * Map a stream to another stream
 * @param R The type of the new stream
 * @param mapper The mapper function
 * @return The new stream
 * @since 0.5.0
 */
class MapStream<T, R>(

    /**
     * The stream to map
     * @since 0.5.0
     */
    private val stream: Stream<T>,

    /**
     * The mapper function
     * @since 0.5.0
     */
    private val mapper: (T) -> R,
) : Stream<R> {

    /**
     * Read the next element from the stream
     * @return The next element
     * @since 0.5.0
     */
    override fun read(): R {
        return mapper(stream.read())
    }

    /**
     * Check if the stream has a next element
     * @return If the stream has a next element
     * @since 0.5.0
     */
    override fun hasNext(): Boolean {
        return stream.hasNext()
    }
}

class IndexedMapStream<T, R>(

    private val stream: Stream<T>,
    private val mapper: (Int, T) -> R,

) : Stream<R> {

    private var index = 0

    override fun read(): R {
        return mapper(index++, stream.read())
    }

    override fun hasNext(): Boolean {
        return stream.hasNext()
    }
}

/**
 * A stream that flattens a stream of streams
 * @param T The type of the elements in the stream
 * @property stream The stream of streams to flatten
 * @constructor Create a new flat stream
 * @since 0.5.0
 */
class FlatStream<T>(

    private val stream: Stream<out Stream<T>>,

) : Stream<T> {

    private var currentStream: Stream<T>? = null

    /**
     * Read the next element from the stream
     * @return The next element
     * @since 0.5.0
     */
    override fun read(): T {
        if (currentStream == null || !currentStream!!.hasNext()) {
            if (!stream.hasNext()) throw IllegalStateException("Stream is empty")
            currentStream = stream.read()
        }
        return currentStream!!.read()
    }

    /**
     * Check if the stream has a next element
     * @return If the stream has a next element
     * @since 0.5.0
     */
    override fun hasNext(): Boolean {
        return currentStream?.hasNext() ?: stream.hasNext()
    }
}

/**
 * Create a Stream from a [Sequence]
 * @receiver The sequence to create the stream from
 * @return The created stream
 * @since 0.5.0
 */
fun <T> Sequence<T>.stream() = SequenceStream(this)

/**
 * Create a Stream from a [List]
 * @receiver The list to create the stream from
 * @return The created stream
 * @since 0.5.0
 */
fun <T> List<T>.stream() = ListStream(this)

/**
 * Create a Stream from an [Array]
 * @receiver The array to create the stream from
 * @return The created stream
 * @since 0.5.0
 */
fun <T> Array<T>.stream() = ArrayStream(this)

/**
 * Create a Stream from an [Iterator]
 * @receiver The iterator to create the stream from
 * @return The created stream
 * @since 0.5.0
 */
fun <T> Iterator<T>.stream() = IteratorStream(this)

/**
 * Create a Stream from a [Sequence]
 * @receiver The sequence to create the stream from
 * @return The created stream
 * @since 0.5.0
 */
fun <T> streamOf(vararg elements: T) = elements.iterator().stream()

fun <T> Stream<out Stream<T>>.flatten() = FlatStream(this)
