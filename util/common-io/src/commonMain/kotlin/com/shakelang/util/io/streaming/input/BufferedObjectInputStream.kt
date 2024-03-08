package com.shakelang.util.io.streaming.input

import com.shakelang.util.io.IOException

/**
 * This class is a buffered input stream for objects
 * @param inputStream The input stream to buffer
 * @param bufferSize The size of the buffer (default: 2048)
 */
class BufferedObjectInputStream<T>(
    private val inputStream: ObjectInputStream<T>,
    private val bufferSize: Int = 2048,
    buffer: Array<T?>,
) : ObjectInputStream<T>() {

    private val buffer: Array<T?> = buffer.createNewArrayOfNulls(bufferSize)
    private var bufferStart = 0
    private var bufferEnd = 0

    private fun refillBuffer() {
        bufferEnd = inputStream.read(buffer, 0, bufferSize)
        bufferStart = 0
    }

    override fun read(): T? {
        if (bufferStart >= bufferEnd) {
            refillBuffer()
        }
        if (bufferStart >= bufferEnd) {
            return null // End of stream
        }
        return buffer[bufferStart++].also { buffer[bufferStart - 1] = null } // Clear reference after reading
    }

    override fun read(b: Array<T?>, off: Int, len: Int): Int {
        if (len < 0) throw IllegalArgumentException("len < 0")
        if (off < 0 || off > b.size || len > b.size - off) throw IndexOutOfBoundsException()

        var totalRead = 0
        while (totalRead < len) {
            val obj = read() ?: break // End of stream
            b[off + totalRead++] = obj
        }
        return if (totalRead == 0 && len > 0) -1 else totalRead
    }

    override fun available(): Int {
        return (bufferEnd - bufferStart) + inputStream.available()
    }

    override fun close() {
        inputStream.close()
    }

    override fun mark(readlimit: Int) {
        throw UnsupportedOperationException("mark not supported in BufferedObjectInputStream")
    }

    override fun markSupported(): Boolean = false

    override fun reset() {
        throw IOException("reset not supported")
    }

    override fun skip(n: Long, arr: Array<T?>): Long {
        var toSkip = n
        while (toSkip > 0 && read() != null) {
            toSkip--
        }
        return n - toSkip
    }

    // Implementation of readNObjects is as per the logic in the original ObjectInputStream
    override fun readNObjects(n: Int, arr: Array<T?>): Array<T?> {
        val result = arr.createNewArrayOfNulls(bufferSize)
        var offset = 0
        var objectsRead: Int
        while (offset < n) {
            objectsRead = read(result, offset, n - offset)
            if (objectsRead == -1) break
            offset += objectsRead
        }
        if (offset == 0) return arr.createNewArrayOfNulls(0)
        return result.copyOf(offset)
    }
}
