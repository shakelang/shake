package com.shakelang.util.io.streaming.general

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
 * Flatten a stream of streams
 * @receiver The stream of streams to flatten
 * @return The flattened stream
 * @since 0.5.0
 */
fun <T> Stream<out Stream<T>>.flatten() = FlatStream(this)
