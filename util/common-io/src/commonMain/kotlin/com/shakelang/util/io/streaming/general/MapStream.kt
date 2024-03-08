package com.shakelang.util.io.streaming.general

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
