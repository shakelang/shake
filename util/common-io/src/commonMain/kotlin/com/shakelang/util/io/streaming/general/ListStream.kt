package com.shakelang.util.io.streaming.general

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
 * Create a Stream from a [List]
 * @receiver The list to create the stream from
 * @return The created stream
 * @since 0.5.0
 */
fun <T> List<T>.stream() = ListStream(this)
