package io.github.shakelang.io.streaming.output

import kotlin.jvm.Synchronized

open class BufferedOutputStream (
    val out: OutputStream,
    bufferSize: Int = 8192
) : OutputStream() {
    /**
     * The internal buffer where data is stored.
     */
    protected val buf: ByteArray = ByteArray(bufferSize)

    /**
     * The number of valid bytes in the buffer. This value is always
     * in the range `0` through `buf.length`; elements
     * `buf[0]` through `buf[count-1]` contain valid
     * byte data.
     */
    protected var count = 0

    /** Flush the internal buffer  */
    private fun flushBuffer() {
        if (count > 0) {
            out.write(buf, 0, count)
            count = 0
        }
    }

    /**
     * Writes the specified byte to this buffered output stream.
     *
     * @param b the byte to be written.
     */
    @Synchronized
    override fun write(b: Int) {
        if (count >= buf.size) {
            flushBuffer()
        }
        buf[count++] = b.toByte()
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
     * @param      b     the data.
     * @param      off   the start offset in the data.
     * @param      len   the number of bytes to write.
     */
    @Synchronized
    override fun write(b: ByteArray, off: Int, len: Int) {
        if (len >= buf.size) {
            /* If the request length exceeds the size of the output buffer,
               flush the output buffer and then write the data directly.
               In this way buffered streams will cascade harmlessly. */
            flushBuffer()
            out.write(b, off, len)
            return
        }
        if (len > buf.size - count) {
            flushBuffer()
        }
        b.copyInto(buf, count, off, off + len)
        count += len
    }

    /**
     * Flushes this buffered output stream. This forces any buffered
     * output bytes to be written out to the underlying output stream.
     */
    @Synchronized
    override fun flush() {
        flushBuffer()
        out.flush()
    }

}