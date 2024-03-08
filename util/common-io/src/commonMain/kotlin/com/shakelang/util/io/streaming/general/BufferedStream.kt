package com.shakelang.util.io.streaming.general

/**
 * Buffer a stream
 * @param stream The stream to buffer
 * @param bufferSize The size of the buffer
 * @return The buffered stream
 * @since 0.6.0
 */
class BufferedStream<T>(private val stream: Stream<T>, private val bufferSize: Int = 8192) : Stream<T> {

    private val buffer = mutableListOf<T>()
    private var index = 0

    override fun read(): T {
        if (index >= buffer.size) {
            buffer.clear()
            for (i in 0 until bufferSize) {
                if (!stream.hasNext()) break
                buffer.add(stream.read())
            }
            index = 0
        }
        if (index >= buffer.size) throw IllegalStateException("Stream is empty")
        return buffer[index++]
    }

    override fun hasNext(): Boolean {
        return index < buffer.size || stream.hasNext()
    }
}

/**
 * Create a buffered stream
 * @receiver The stream to buffer
 * @param bufferSize The size of the buffer
 * @return The buffered stream
 * @since 0.6.0
 */
fun <T> Stream<T>.buffered(bufferSize: Int = 8192) = BufferedStream(this, bufferSize)
