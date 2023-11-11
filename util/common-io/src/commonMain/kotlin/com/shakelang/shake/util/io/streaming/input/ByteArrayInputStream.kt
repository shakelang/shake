package com.shakelang.shake.util.io.streaming.input

import com.shakelang.shake.util.io.IOException

/**
 * A [ByteArrayInputStream] is an [InputStream] that reads from a [ByteArray]
 *
 * @param buf The [ByteArray] to read from
 *
 * @since 0.1.0
 * @version 0.1.1
 *
 * @see InputStream
 */
class ByteArrayInputStream(

    /**
     * The [ByteArray] to read from
     *
     * @since 0.1.0
     * @version 0.1.1
     */
    private var buf: ByteArray
) : InputStream() {

    /**
     * The current position in the [ByteArray]
     *
     * @since 0.1.0
     * @version 0.1.1
     */
    private var pos = 0

    /**
     * The mark of the [ByteArrayInputStream]
     *
     * @since 0.1.0
     * @version 0.1.1
     */
    private var mark = 0

    /**
     * The count of the [ByteArrayInputStream]
     *
     * @since 0.1.0
     * @version 0.1.1
     */
    private var count = 0

    init {
        this.count = buf.size
    }

    /**
     * Creates a [ByteArrayInputStream] from a [ByteArray]
     *
     * @param buf The [ByteArray] to read from
     * @param offset The offset to start reading from
     * @param length The length of the [ByteArrayInputStream]
     *
     * @since 0.1.0
     * @version 0.1.1
     */
    constructor(buf: ByteArray, offset: Int, length: Int) : this(buf) {
        this.count = length + offset
        this.pos = offset
        this.mark = offset
    }

    /**
     * Reads a single byte from the [ByteArrayInputStream]
     *
     * @return the read byte or -1 if the end of the [ByteArrayInputStream] is reached
     * @throws IOException if an I/O error occurs
     *
     * @since 0.1.0
     * @version 0.1.1
     */
    override fun read(): Int {
        if (pos >= count) {
            return -1
        }
        return buf[pos++].toInt() and 0xff
    }

    /**
     * Reads bytes from the [ByteArrayInputStream] into a [ByteArray]
     *
     * @param b The [ByteArray] to read into
     * @param off The offset to start reading into
     * @param len The length of the [ByteArray] to read into
     * @return the number of bytes read or -1 if the end of the [ByteArrayInputStream] is reached
     * @throws IOException if an I/O error occurs
     *
     * @since 0.1.0
     * @version 0.1.1
     */
    override fun read(b: ByteArray, off: Int, len: Int): Int {
        var l = len
        if (pos >= count) {
            return -1
        }
        if (pos + l > count) {
            l = count - pos
        }
        buf.copyInto(b, off, pos, l + pos)
        pos += l
        return l
    }

    /**
     * Skips bytes in the [ByteArrayInputStream]
     *
     * @param n The number of bytes to skip
     * @return the number of bytes skipped
     * @throws IOException if an I/O error occurs
     *
     * @since 0.1.0
     * @version 0.1.1
     */
    override fun skip(n: Long): Long {
        var l = n
        if (pos + l > count) {
            l = (count - pos).toLong()
        }
        if (l < 0) {
            return 0
        }
        pos += l.toInt()
        return l
    }

    /**
     * Returns the number of bytes that can be read from the [ByteArrayInputStream]
     *
     * @return the number of bytes that can be read from the [ByteArrayInputStream]
     * @throws IOException if an I/O error occurs
     *
     * @since 0.1.0
     * @version 0.1.1
     */
    override fun available(): Int {
        return count - pos
    }

    /**
     * Marks the current position in the [ByteArrayInputStream]
     *
     * @param readlimit The read limit
     * @throws IOException if an I/O error occurs
     *
     * @since 0.1.0
     * @version 0.1.1
     */
    override fun mark(readlimit: Int) {
        mark = pos
    }

    /**
     * Resets the [ByteArrayInputStream] to the last mark
     *
     * @throws IOException if an I/O error occurs
     *
     * @since 0.1.0
     * @version 0.1.1
     */
    override fun reset() {
        pos = mark
    }

    /**
     * Returns if the [ByteArrayInputStream] supports marking
     *
     * @return if the [ByteArrayInputStream] supports marking
     * @throws IOException if an I/O error occurs
     *
     * @since 0.1.0
     * @version 0.1.1
     */
    override fun markSupported(): Boolean {
        return true
    }

    /**
     * Closes the [ByteArrayInputStream]
     *
     * @throws IOException if an I/O error occurs
     *
     * @since 0.1.0
     * @version 0.1.1
     */
    override fun close() {
        buf = ByteArray(0)
        pos = 0
        mark = 0
        count = 0
    }

    /**
     * Returns a string representation of the [ByteArrayInputStream]
     *
     * @return a string representation of the [ByteArrayInputStream]
     *
     * @since 0.1.0
     * @version 0.1.1
     */
    override fun toString(): String {
        return "ByteArrayInputStream(pos=$pos, mark=$mark, count=$count)"
    }
}
