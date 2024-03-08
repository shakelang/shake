package com.shakelang.util.io.streaming.input

import com.shakelang.util.io.IOException
import kotlin.math.min

/**
 * This class is an abstract class for all input streams
 */
abstract class ObjectInputStream<T> {
    /**
     * Returns an estimate of the number of objects that can be read (or skipped over) from
     * this input
     * stream without blocking by the next invocation of a method for this input stream.
     *
     * @return an estimate of the number of objects that can be read (or skipped over) from
     * this input stream without blocking or 0 when it reaches the end of the input stream.
     * @throws IOException if an I/O error occurs.
     * @throws UnsupportedOperationException if this method is not supported.
     *
     * @see ObjectInputStream.read
     * @see ObjectInputStream.readNObjects
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
     * @param readlimit the maximum limit of objects that can be read before the mark position becomes invalid.
     * @throws IOException if an I/O error occurs.
     * @throws UnsupportedOperationException if this method is not supported.
     *
     * @see ObjectInputStream.markSupported
     */
    open fun mark(readlimit: Int) {}

    /**
     * Tests if this input stream supports the mark and reset methods.
     *
     * @return true if this stream instance supports the mark and reset methods; false otherwise.
     * @throws IOException if an I/O error occurs.
     * @throws UnsupportedOperationException if this method is not supported.
     *
     * @see ObjectInputStream.mark
     */
    open fun markSupported(): Boolean = false

    /**
     * Reads the next object of data from the input stream.
     *
     * @return the next object of data, or -1 if the end of the stream is reached.
     * @throws IOException if an I/O error occurs.
     * @throws UnsupportedOperationException if this method is not supported.
     *
     * @see ObjectInputStream.read
     * @see ObjectInputStream.readNObjects
     */
    abstract fun read(): T?

    /**
     * Reads some number of objects from the input stream and stores them into the buffer array b.
     *
     * @param b the buffer into which the data is read.
     * @return the total number of objects read into the buffer, or -1 if there is no more data
     * because the end of the stream has been reached.
     * @throws IOException if an I/O error occurs.
     * @throws UnsupportedOperationException if this method is not supported.
     *
     * @see ObjectInputStream.read
     * @see ObjectInputStream.readNObjects
     */
    open fun read(b: Array<T?>): Int = read(b, 0, b.size)

    /**
     * Reads up to len objects of data from the input stream into an array of objects.
     *
     * @param b the buffer into which the data is read.
     * @param off the start offset in array b at which the data is written.
     * @param len the maximum number of objects to read.
     * @return the total number of objects read into the buffer, or -1 if there is no more data
     * because the end of the stream has been reached.
     *
     * @throws IOException if an I/O error occurs.
     * @throws UnsupportedOperationException if this method is not supported.
     *
     * @see ObjectInputStream.read
     * @see ObjectInputStream.readNObjects
     */
    open fun read(b: Array<T?>, off: Int, len: Int): Int {
        if (len < 0) throw IllegalArgumentException("len < 0")
        if (off < 0 || off > b.size || len > b.size - off) throw IndexOutOfBoundsException()
        var c = 0
        for (i in 0 until len) {
            val result = read() ?: return if (c == 0) -1 else c
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
     * @see ObjectInputStream.mark
     * @see ObjectInputStream.markSupported
     * @see ObjectInputStream.reset
     */
    open fun reset() {
        throw IOException("mark/reset not supported")
    }

    /**
     * Skips over and discards n objects of data from this input stream.
     *
     * @param n the number of objects to be skipped.
     * @return the actual number of objects skipped.
     * @throws IOException if an I/O error occurs.
     * @throws UnsupportedOperationException if this method is not supported.
     *
     * @see ObjectInputStream.skip
     */
    open fun skip(n: Long, arr: Array<T?>): Long {
        var remaining = n
        val buffer = arr.createNewArrayOfNulls(2048)
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
     * Returns the number of objects that can be read (or skipped over) from this input stream without
     *
     * @return the number of objects that can be read from this input stream without blocking.
     * @throws IOException if an I/O error occurs.
     * @throws UnsupportedOperationException if this method is not supported.
     *
     * @see ObjectInputStream.available
     * @see ObjectInputStream.read
     * @see ObjectInputStream.readNObjects
     */
    open fun readNObjects(n: Int, arr: Array<T?>): Array<T?> {
        val result = arr.createNewArrayOfNulls(2048)
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

    /**
     * Returns the number of objects that can be read (or skipped over) from this input stream without
     *
     * @param b the buffer into which the data is read.
     * @return the number of objects that can be read from this input stream without blocking.
     * @throws IOException if an I/O error occurs.
     * @throws UnsupportedOperationException if this method is not supported.
     *
     * @see ObjectInputStream.available
     * @see ObjectInputStream.read
     * @see ObjectInputStream.readNObjects
     */
    open fun readNObjects(b: Array<T?>, off: Int, len: Int): Int {
        var offset = off
        var objectsRead: Int
        while (offset < len) {
            objectsRead = read(b, offset, len - offset)
            if (objectsRead == -1) break
            offset += objectsRead
        }
        return offset - off
    }
}

expect fun <T> Array<T>.createNewArrayOfNulls(size: Int): Array<T?>
