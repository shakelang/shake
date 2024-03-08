package com.shakelang.util.io.streaming.input.shorts

import com.shakelang.util.io.IOException
import kotlin.math.min

/**
 * This class is an abstract class for all input streams
 *
 * @since 0.1.0
 * @version 0.1.0
 */
abstract class ShortInputStream {
    /**
     * Returns an estimate of the number of shorts that can be read (or skipped over) from
     * this input
     * stream without blocking by the next invocation of a method for this input stream.
     *
     * @return an estimate of the number of shorts that can be read (or skipped over) from
     * this input stream without blocking or 0 when it reaches the end of the input stream.
     * @throws IOException if an I/O error occurs.
     * @throws UnsupportedOperationException if this method is not supported.
     *
     * @see LongInputStream.read
     * @see LongInputStream.readNShorts
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
     * @param readlimit the maximum limit of shorts that can be read before the mark position becomes invalid.
     * @throws IOException if an I/O error occurs.
     * @throws UnsupportedOperationException if this method is not supported.
     *
     * @see LongInputStream.markSupported
     */
    open fun mark(readlimit: Int) {}

    /**
     * Tests if this input stream supports the mark and reset methods.
     *
     * @return true if this stream instance supports the mark and reset methods; false otherwise.
     * @throws IOException if an I/O error occurs.
     * @throws UnsupportedOperationException if this method is not supported.
     *
     * @see LongInputStream.mark
     */
    open fun markSupported(): Boolean = false

    /**
     * Reads the next short of data from the input stream.
     *
     * @return the next short of data, or -1 if the end of the stream is reached.
     * @throws IOException if an I/O error occurs.
     * @throws UnsupportedOperationException if this method is not supported.
     *
     * @see LongInputStream.read
     * @see LongInputStream.readNShorts
     */
    abstract fun read(): Short

    /**
     * Reads some number of shorts from the input stream and stores them into the buffer array b.
     *
     * @param b the buffer into which the data is read.
     * @return the total number of shorts read into the buffer, or -1 if there is no more data
     * because the end of the stream has been reached.
     * @throws IOException if an I/O error occurs.
     * @throws UnsupportedOperationException if this method is not supported.
     *
     * @see LongInputStream.read
     * @see LongInputStream.readNShorts
     */
    open fun read(b: ShortArray): Int = read(b, 0, b.size)

    /**
     * Reads up to len shorts of data from the input stream into an array of shorts.
     *
     * @param b the buffer into which the data is read.
     * @param off the start offset in array b at which the data is written.
     * @param len the maximum number of shorts to read.
     * @return the total number of shorts read into the buffer, or -1 if there is no more data
     * because the end of the stream has been reached.
     *
     * @throws IOException if an I/O error occurs.
     * @throws UnsupportedOperationException if this method is not supported.
     *
     * @see LongInputStream.read
     * @see LongInputStream.readNShorts
     */
    open fun read(b: ShortArray, off: Int, len: Int): Int {
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
     * @see LongInputStream.mark
     * @see LongInputStream.markSupported
     * @see LongInputStream.reset
     */
    open fun reset() {
        throw IOException("mark/reset not supported")
    }

    /**
     * Skips over and discards n shorts of data from this input stream.
     *
     * @param n the number of shorts to be skipped.
     * @return the actual number of shorts skipped.
     * @throws IOException if an I/O error occurs.
     * @throws UnsupportedOperationException if this method is not supported.
     *
     * @see LongInputStream.skip
     */
    open fun skip(n: Long): Long {
        var remaining = n
        val buffer = ShortArray(2048)
        var nr: Int
        if (n <= 0) return 0
        while (remaining > 0) {
            nr = read(buffer, 0, min(remaining, buffer.size.toLong()).toInt())
            if (nr < 0) break
            remaining -= nr
        }
        return n - remaining
    }

    /**
     * Returns the number of shorts that can be read (or skipped over) from this input stream without
     *
     * @return the number of shorts that can be read from this input stream without blocking.
     * @throws IOException if an I/O error occurs.
     * @throws UnsupportedOperationException if this method is not supported.
     *
     * @see LongInputStream.available
     * @see LongInputStream.read
     * @see LongInputStream.readNShorts
     */
    open fun readNShorts(n: Int): ShortArray {
        val result = ShortArray(n)
        var offset = 0
        var shortsRead: Int
        while (offset < n) {
            shortsRead = read(result, offset, n - offset)
            if (shortsRead == -1) break
            offset += shortsRead
        }
        if (offset == 0) return ShortArray(0)
        return result.copyOf(offset)
    }

    /**
     * Returns the number of shorts that can be read (or skipped over) from this input stream without
     *
     * @param b the buffer into which the data is read.
     * @return the number of shorts that can be read from this input stream without blocking.
     * @throws IOException if an I/O error occurs.
     * @throws UnsupportedOperationException if this method is not supported.
     *
     * @see LongInputStream.available
     * @see LongInputStream.read
     * @see LongInputStream.readNShorts
     */
    open fun readNShorts(b: ShortArray, off: Int, len: Int): Int {
        var offset = off
        var shortsRead: Int
        while (offset < len) {
            shortsRead = read(b, offset, len - offset)
            if (shortsRead == -1) break
            offset += shortsRead
        }
        return offset - off
    }
}
