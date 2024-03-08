package com.shakelang.util.io.streaming.general

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
 * Create a Stream from an [Array]
 * @receiver The array to create the stream from
 * @return The created stream
 * @since 0.5.0
 */
fun <T> Array<T>.stream() = ArrayStream(this)
