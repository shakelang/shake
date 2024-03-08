package com.shakelang.util.io.streaming.general

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
 * Create a Stream from a [Sequence]
 * @receiver The sequence to create the stream from
 * @return The created stream
 * @since 0.5.0
 */
fun <T> Sequence<T>.stream() = SequenceStream(this)
