package com.shakelang.util.io.streaming.general

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
 * Create a Stream from an [Iterator]
 * @receiver The iterator to create the stream from
 * @return The created stream
 * @since 0.5.0
 */
fun <T> Iterator<T>.stream() = IteratorStream(this)
