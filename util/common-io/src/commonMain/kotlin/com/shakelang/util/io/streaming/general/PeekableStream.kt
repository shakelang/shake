package com.shakelang.util.io.streaming.general

/**
 * A stream that can be peeked
 * @param T The type of the elements in the stream
 * @property stream The stream to peek
 * @constructor Create a new peekable stream
 * @since 0.6.0
 */
open class PeekableStream<T>(private val stream: Stream<T>) : Stream<T> {

    private val buffer = mutableListOf<T>()

    private fun fillBuffer(amount: Int) {
        for (i in 0 until amount) {
            if (!stream.hasNext()) break
            buffer.add(stream.read())
        }
    }

    /**
     * Peek at the next elements in the stream
     * @param amount The amount of elements to peek
     * @return The peeked elements
     * @since 0.6.0
     */
    fun peek(amount: Int): List<T> {
        fillBuffer(amount)
        return buffer.take(amount)
    }

    /**
     * Peek at the next element in the stream
     * @return The peeked element
     * @since 0.6.0
     */
    fun peek(): T {
        fillBuffer(1)
        if (buffer.isEmpty()) throw IllegalStateException("Stream is empty")
        return buffer[0]
    }

    /**
     * Peek at the next element in the stream
     * @return The peeked element or null if the stream is empty
     * @since 0.6.0
     */
    fun peekOrNull(): T? {
        fillBuffer(1)
        return buffer.getOrNull(0)
    }

    /**
     * Peek at the last elements in the stream
     * @param amount The amount of elements to peek
     * @return The peeked elements
     * @since 0.6.0
     */
    fun peekLast(): T {
        fillBuffer(1)
        if (buffer.isEmpty()) throw IllegalStateException("Stream is empty")
        return buffer[buffer.size - 1]
    }

    /**
     * Peek at the last element in the stream
     * @return The peeked element or null if the stream is empty
     * @since 0.6.0
     */
    fun peekLastOrNull(): T? {
        fillBuffer(1)
        return buffer.getOrNull(buffer.size - 1)
    }

    /**
     * Peek at all elements in the stream
     * @return The peeked elements
     * @since 0.6.0
     */
    fun peekAll(): List<T> {
        fillBuffer(Int.MAX_VALUE)
        return buffer.toList()
    }

    /**
     * Peek at all elements in the stream
     * @return The peeked elements or null if the stream is empty
     * @since 0.6.0
     */
    fun peekAllOrNull(): List<T>? {
        fillBuffer(Int.MAX_VALUE)
        return buffer.takeIf { it.isNotEmpty() }
    }

    /**
     * Peek at all elements in the stream and clear the buffer
     * @return The peeked elements
     * @since 0.6.0
     */
    fun peekAllAndClear(): List<T> {
        val result = peekAll()
        buffer.clear()
        return result
    }

    /**
     * Peek at all elements in the stream and clear the buffer
     * @return The peeked elements or null if the stream is empty
     * @since 0.6.0
     */
    fun peekAllOrNullAndClear(): List<T>? {
        val result = peekAllOrNull()
        buffer.clear()
        return result
    }

    /**
     * Read the next element from the stream
     * @return The next element
     * @since 0.6.0
     */
    override fun read(): T {
        if (buffer.isEmpty()) fillBuffer(1)
        if (buffer.isEmpty()) throw IllegalStateException("Stream is empty")
        return buffer.removeAt(0)
    }

    /**
     * Check if the stream has a next element
     * @return If the stream has a next element
     * @since 0.6.0
     */
    override fun hasNext(): Boolean {
        return buffer.isNotEmpty() || stream.hasNext()
    }
}

fun <T> Stream<T>.peekable() = PeekableStream(this)
