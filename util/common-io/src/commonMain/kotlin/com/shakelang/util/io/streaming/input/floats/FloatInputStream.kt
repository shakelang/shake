package com.shakelang.util.io.streaming.input.floats

import com.shakelang.util.io.IOException
import kotlin.math.min

/**
 * This class is an abstract class for all input streams
 *
 * @since 0.1.0
 * @version 0.1.0
 */
abstract class FloatInputStream {
    /**
     * Returns an estimate of the number of floats that can be read (or skipped over) from
     * this input
     * stream without blocking by the next invocation of a method for this input stream.
     *
     * @return an estimate of the number of floats that can be read (or skipped over) from
     * this input stream without blocking or 0 when it reaches the end of the input stream.
     * @throws IOException if an I/O error occurs.
     * @throws UnsupportedOperationException if this method is not supported.
     *
     * @see FloatInputStream.read
     * @see FloatInputStream.readNFloats
     */
    open fun available() = 0

    /**
     * Closes this input stream and releases any system resources associated with the stream.
     *
     * @throws IOException if an I/O error occurs.
     * @throws UnsupportedOperationException if this method is not supported.
     */
    open fun close() {}

    /**
     * Marks the current position in this input stream.
     *
     * @param readlimit the maximum limit of floats that can be read before the mark position becomes invalid.
     * @throws IOException if an I/O error occurs.
     * @throws UnsupportedOperationException if this method is not supported.
     *
     * @see FloatInputStream.markSupported
     */
    open fun mark(readlimit: Int) {}

    /**
     * Tests if this input stream supports the mark and reset methods.
     *
     * @return true if this stream instance supports the mark and reset methods; false otherwise.
     * @throws IOException if an I/O error occurs.
     * @throws UnsupportedOperationException if this method is not supported.
     *
     * @see FloatInputStream.mark
     */
    open fun markSupported(): Boolean = false

    /**
     * Reads the next float of data from the input stream.
     *
     * @return the next float of data, or -1 if the end of the stream is reached.
     * @throws IOException if an I/O error occurs.
     * @throws UnsupportedOperationException if this method is not supported.
     *
     * @see FloatInputStream.read
     * @see FloatInputStream.readNFloats
     */
    abstract fun read(): Float

    /**
     * Reads some number of floats from the input stream and stores them into the buffer array b.
     *
     * @param b the buffer into which the data is read.
     * @return the total number of floats read into the buffer, or -1 if there is no more data
     * because the end of the stream has been reached.
     * @throws IOException if an I/O error occurs.
     * @throws UnsupportedOperationException if this method is not supported.
     *
     * @see FloatInputStream.read
     * @see FloatInputStream.readNFloats
     */
    open fun read(b: FloatArray): Int = read(b, 0, b.size)

    /**
     * Reads up to len floats of data from the input stream into an array of floats.
     *
     * @param b the buffer into which the data is read.
     * @param off the start offset in array b at which the data is written.
     * @param len the maximum number of floats to read.
     * @return the total number of floats read into the buffer, or -1 if there is no more data
     * because the end of the stream has been reached.
     *
     * @throws IOException if an I/O error occurs.
     * @throws UnsupportedOperationException if this method is not supported.
     *
     * @see FloatInputStream.read
     * @see FloatInputStream.readNFloats
     */
    open fun read(b: FloatArray, off: Int, len: Int): Int {
        if (len < 0) throw IllegalArgumentException("len < 0")
        if (off < 0 || off > b.size || len > b.size - off) throw IndexOutOfBoundsException()
        var c = 0
        for (i in 0 until len) {
            val result = read()
            if (result.toInt() == -1) return if (c == 0) -1 else c
            b[off + i] = result
            c++
        }
        return c
    }

    /**
     * Resets this input stream to the position at the time the mark method was last called on this
     * input stream.
     *
     * @throws IOException if an I/O error occurs.
     * @throws UnsupportedOperationException if this method is not supported.
     *
     * @see FloatInputStream.mark
     * @see FloatInputStream.markSupported
     * @see FloatInputStream.reset
     */
    open fun reset() {
        throw IOException("mark/reset not supported")
    }

    /**
     * Skips over and discards n floats of data from this input stream.
     *
     * @param n the number of floats to be skipped.
     * @return the actual number of floats skipped.
     * @throws IOException if an I/O error occurs.
     * @throws UnsupportedOperationException if this method is not supported.
     *
     * @see FloatInputStream.skip
     */
    open fun skip(n: Float): Float {
        var remaining = n
        val buffer = FloatArray(2048)
        var nr: Int
        if (n <= 0) return 0f
        while (remaining > 0) {
            nr = read(buffer, 0, min(remaining, buffer.size.toFloat()).toInt())
            if (nr < 0) break
            remaining -= nr
        }
        return n - remaining
    }

    /**
     * Returns the number of floats that can be read (or skipped over) from this input stream without
     *
     * @return the number of floats that can be read from this input stream without blocking.
     * @throws IOException if an I/O error occurs.
     * @throws UnsupportedOperationException if this method is not supported.
     *
     * @see FloatInputStream.available
     * @see FloatInputStream.read
     * @see FloatInputStream.readNFloats
     */
    open fun readNFloats(n: Int): FloatArray {
        val result = FloatArray(n)
        var offset = 0
        var floatsRead: Int
        while (offset < n) {
            floatsRead = read(result, offset, n - offset)
            if (floatsRead == -1) break
            offset += floatsRead
        }
        if (offset == 0) return FloatArray(0)
        return result.copyOf(offset)
    }

    /**
     * Returns the number of floats that can be read (or skipped over) from this input stream without
     *
     * @param b the buffer into which the data is read.
     * @return the number of floats that can be read from this input stream without blocking.
     * @throws IOException if an I/O error occurs.
     * @throws UnsupportedOperationException if this method is not supported.
     *
     * @see FloatInputStream.available
     * @see FloatInputStream.read
     * @see FloatInputStream.readNFloats
     */
    open fun readNFloats(b: FloatArray, off: Int, len: Int): Int {
        var offset = off
        var floatsRead: Int
        while (offset < len) {
            floatsRead = read(b, offset, len - offset)
            if (floatsRead == -1) break
            offset += floatsRead
        }
        return offset - off
    }
}
