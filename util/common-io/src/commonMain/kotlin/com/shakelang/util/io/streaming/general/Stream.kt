package com.shakelang.util.io.streaming.general

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

    /**
     * For each element in the stream, call the given function
     * @param action The action to call
     * @since 0.5.1
     */
    fun forEach(action: (T) -> Unit) {
        while (hasNext()) {
            action(read())
        }
    }

    /**
     * For each element in the stream, call the given function
     * @param action The action to call
     * @since 0.5.1
     */
    fun forEachIndexed(action: (Int, T) -> Unit) {
        var index = 0
        while (hasNext()) {
            action(index++, read())
        }
    }
}

/**
 * Create a Stream from a [Sequence]
 * @receiver The sequence to create the stream from
 * @return The created stream
 * @since 0.5.0
 */
fun <T> streamOf(vararg elements: T) = elements.iterator().stream()
