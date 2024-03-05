package com.shakelang.util.io.streaming.output.bytes

import kotlin.jvm.Synchronized

/**
 * The class implements a buffered output stream. By setting up such
 * an output stream, an application can write bytes to the underlying
 * output stream without necessarily causing a call to the underlying
 * system for each byte written.
 *
 * @param out the underlying output stream.
 * @param bufferSize the buffer size.
 * @constructor Creates a new buffered output stream to write data to the
 *
 * @since 0.1.0
 * @version 0.1.1
 */
open class BufferedOutputStream(

    /**
     * The underlying output stream to be filtered.
     *
     * @since 0.1.0
     * @version 0.1.1
     */
    val out: OutputStream,
    bufferSize: Int = 8192,
) : OutputStream() {
    /**
     * The internal buffer where data is stored.
     *
     * @since 0.1.0
     * @version 0.1.1
     */
    private val buf: ByteArray = ByteArray(bufferSize)

    /**
     * The number of valid bytes in the buffer. This value is always
     * in the range `0` through `buf.length`; elements
     * `buf[0]` through `buf[count-1]` contain valid
     * byte data.
     *
     * @since 0.1.0
     * @version 0.1.1
     */
    protected var count = 0

    /**
     * Flush the internal buffer
     *
     * @param array something to append to the buffer before flushing it
     * @throws IOException if an I/O error occurs.
     *
     * @since 0.1.0
     * @version 0.1.1
     */
    private fun flushBuffer(array: ByteArray = byteArrayOf()) {
        if (count + array.size > 0) {
            out.write(byteArrayOf(*buf.copyOfRange(0, count), *array))
            count = 0
        }
    }

    /**
     * Writes the specified byte to this buffered output stream.
     *
     * @param b the byte to be written.
     * @throws IOException if an I/O error occurs.
     *
     * @since 0.1.0
     * @version 0.1.1
     */
    @Synchronized
    override fun write(b: Int) {
        if (count >= buf.size) {
            flushBuffer()
        }
        buf[count++] = b.toByte()
    }

    /**
     * Writes `b.length` bytes from the specified byte array
     * to this buffered output stream.
     *
     * @param b the data.
     * @throws IOException if an I/O error occurs.
     *
     * @since 0.1.0
     * @version 0.1.1
     */
    override fun write(b: ByteArray) {
        this.write(b, 0, b.size)
    }

    /**
     * Writes `len` bytes from the specified byte array
     * starting at offset `off` to this buffered output stream.
     *
     *
     *  Ordinarily this method stores bytes from the given array into this
     * stream's buffer, flushing the buffer to the underlying output stream as
     * needed.  If the requested length is at least as large as this stream's
     * buffer, however, then this method will flush the buffer and write the
     * bytes directly to the underlying output stream.  Thus redundant
     * `BufferedOutputStream`s will not copy data unnecessarily.
     *
     * @param b     the data.
     * @param off   the start offset in the data.
     * @param len   the number of bytes to write.
     * @throws IOException
     *
     * @since 0.1.0
     * @version 0.1.1
     */
    @Synchronized
    override fun write(b: ByteArray, off: Int, len: Int) {
        if (len + count >= buf.size) {
            // When the request length exceeds the remaining space in
            // the buffer, flush it and then write the data directly.
            flushBuffer(b)
            return
        }
        b.copyInto(buf, count, off, off + len)
        count += len
    }

    /**
     * Flushes this buffered output stream. This forces any buffered
     * output bytes to be written out to the underlying output stream.
     *
     * @throws IOException if an I/O error occurs.
     *
     * @since 0.1.0
     * @version 0.1.1
     */
    @Synchronized
    override fun flush() {
        flushBuffer()
        out.flush()
    }

    /**
     * Closes this buffered output stream. This method simply flushes
     * this stream and then calls the underlying output stream's
     * `close` method, which releases the underlying output
     * stream's resources.
     *
     * @throws IOException if an I/O error occurs.
     *
     * @since 0.1.0
     * @version 0.1.1
     */
    override fun close() {
        flush()
        out.close()
    }
}
