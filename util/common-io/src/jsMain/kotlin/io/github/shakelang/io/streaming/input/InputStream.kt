package io.github.shakelang.io.streaming.input

import kotlin.math.min

actual abstract class InputStream {

    // MAX_SKIP_BUFFER_SIZE is used to determine the maximum buffer size to
    // use when skipping.
    private val MAX_SKIP_BUFFER_SIZE = 2048

    private val MAX_BUFFER_SIZE = Int.MAX_VALUE - 8

    /**
     * Returns an estimate of the number of bytes that can be read (or skipped over) from this input
     * stream without blocking by the next invocation of a method for this input stream.
     */
    actual open fun available(): Int = 0

    /**
     * Closes this input stream and releases any system resources associated with the stream.
     */
    actual open fun close() {}

    /**
     * Marks the current position in this input stream.
     */
    actual open fun mark(readlimit: Int) {}

    /**
     * Tests if this input stream supports the mark and reset methods.
     */
    actual open fun markSupported(): Boolean {
        throw UnsupportedOperationException()
    }

    /**
     * Reads the next byte of data from the input stream.
     */
    actual abstract fun read(): Int

    /**
     * Reads some number of bytes from the input stream and stores them into
     * the buffer array `b`. The number of bytes actually read is
     * returned as an integer.  This method blocks until input data is
     * available, end of file is detected, or an exception is thrown.
     *
     *
     *  If the length of `b` is zero, then no bytes are read and
     * `0` is returned; otherwise, there is an attempt to read at
     * least one byte. If no byte is available because the stream is at the
     * end of the file, the value `-1` is returned; otherwise, at
     * least one byte is read and stored into `b`.
     *
     *
     *  The first byte read is stored into element `b[0]`, the
     * next one into `b[1]`, and so on. The number of bytes read is,
     * at most, equal to the length of `b`. Let *k* be the
     * number of bytes actually read; these bytes will be stored in elements
     * `b[0]` through `b[`*k*`-1]`,
     * leaving elements `b[`*k*`]` through
     * `b[b.length-1]` unaffected.
     *
     *
     *  The `read(b)` method for class `InputStream`
     * has the same effect as: <pre>`read(b, 0, b.length) `</pre>
     *
     * @param      b   the buffer into which the data is read.
     * @return     the total number of bytes read into the buffer, or
     * `-1` if there is no more data because the end of
     * the stream has been reached.
     * @throws     IOException  If the first byte cannot be read for any reason
     * other than the end of the file, if the input stream has been
     * closed, or if some other I/O error occurs.
     * @throws     NullPointerException  if `b` is `null`.
     * @see java.io.InputStream.read
     */
    actual open fun read(b: ByteArray): Int {
        return read(b, 0, b.size)
    }

    /**
     * Reads up to `len` bytes of data from the input stream into
     * an array of bytes.  An attempt is made to read as many as
     * `len` bytes, but a smaller number may be read.
     * The number of bytes actually read is returned as an integer.
     *
     *
     *  This method blocks until input data is available, end of file is
     * detected, or an exception is thrown.
     *
     *
     *  If `len` is zero, then no bytes are read and
     * `0` is returned; otherwise, there is an attempt to read at
     * least one byte. If no byte is available because the stream is at end of
     * file, the value `-1` is returned; otherwise, at least one
     * byte is read and stored into `b`.
     *
     *
     *  The first byte read is stored into element `b[off]`, the
     * next one into `b[off+1]`, and so on. The number of bytes read
     * is, at most, equal to `len`. Let *k* be the number of
     * bytes actually read; these bytes will be stored in elements
     * `b[off]` through `b[off+`*k*`-1]`,
     * leaving elements `b[off+`*k*`]` through
     * `b[off+len-1]` unaffected.
     *
     *
     *  In every case, elements `b[0]` through
     * `b[off-1]` and elements `b[off+len]` through
     * `b[b.length-1]` are unaffected.
     *
     *
     *  The `read(b, off, len)` method
     * for class `InputStream` simply calls the method
     * `read()` repeatedly. If the first such call results in an
     * `IOException`, that exception is returned from the call to
     * the `read(b,` `off,` `len)` method.  If
     * any subsequent call to `read()` results in a
     * `IOException`, the exception is caught and treated as if it
     * were end of file; the bytes read up to that point are stored into
     * `b` and the number of bytes read before the exception
     * occurred is returned. The default implementation of this method blocks
     * until the requested amount of input data `len` has been read,
     * end of file is detected, or an exception is thrown. Subclasses are
     * encouraged to provide a more efficient implementation of this method.
     *
     * @param      b     the buffer into which the data is read.
     * @param      off   the start offset in array `b`
     * at which the data is written.
     * @param      len   the maximum number of bytes to read.
     * @return     the total number of bytes read into the buffer, or
     * `-1` if there is no more data because the end of
     * the stream has been reached.
     * @throws     IOException If the first byte cannot be read for any reason
     * other than end of file, or if the input stream has been closed,
     * or if some other I/O error occurs.
     * @throws     NullPointerException If `b` is `null`.
     * @throws     IndexOutOfBoundsException If `off` is negative,
     * `len` is negative, or `len` is greater than
     * `b.length - off`
     */
    actual open fun read(b: ByteArray, off: Int, len: Int): Int {
        checkFromIndexSize(off, len, b.size)
        if (len == 0) {
            return 0
        }
        var c = read()
        if (c == -1) {
            return -1
        }
        b[off] = c.toByte()
        var i = 1
        try {
            while (i < len) {
                c = read()
                if (c == -1) {
                    break
                }
                b[off + i] = c.toByte()
                i++
            }
        } catch (ee: Exception) {
            i = -1
        }
        return i
    }

    private fun checkFromIndexSize(off: Int, len: Int, size: Int) {
        if (off < 0) {
            throw IndexOutOfBoundsException("off < 0: $off")
        }
        if (len < 0) {
            throw IndexOutOfBoundsException("len < 0: $len")
        }
        if (off + len > size) {
            throw IndexOutOfBoundsException("off + len > size: off = $off, len = $len, size = $size")
        }
    }

    /**
     * Resets this input stream to the position at the time the mark method was last called on this
     * input stream.
     */
    actual open fun reset() {
        throw UnsupportedOperationException()
    }

    /**
     * Skips over and discards n bytes of data from this input stream.
     */
    actual open fun skip(n: Long): Long {
        var remaining = n
        var nr: Int

        if (n <= 0) {
            return 0
        }

        val size: Int = min(MAX_SKIP_BUFFER_SIZE.toLong(), remaining).toInt()
        val skipBuffer = ByteArray(size)
        while (remaining > 0) {
            nr = read(skipBuffer, 0, min(size.toLong(), remaining).toInt())
            if (nr < 0) {
                break
            }
            remaining -= nr.toLong()
        }

        return n - remaining
    }

    /**
     * Returns the number of bytes that can be read (or skipped over) from this input stream without
     */
    actual open fun readNBytes(n: Int): ByteArray {
        val b = ByteArray(n)
        var i = 0
        while (i < n) {
            val c = read()
            if (c == -1) {
                break
            }
            b[i] = c.toByte()
            i++
        }
        return b
    }

    /**
     * Returns the number of bytes that can be read (or skipped over) from this input stream without
     */
    actual open fun readNBytes(b: ByteArray, off: Int, len: Int): Int {
        checkFromIndexSize(off, len, b.size)
        if (len == 0) {
            return 0
        }
        var i = 0
        while (i < len) {
            val c = read()
            if (c == -1) {
                break
            }
            b[off + i] = c.toByte()
            i++
        }
        return i
    }

}