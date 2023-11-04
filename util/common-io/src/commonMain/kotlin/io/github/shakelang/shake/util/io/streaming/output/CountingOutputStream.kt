package io.github.shakelang.shake.util.io.streaming.output

/**
 * An [OutputStream] that counts the amount of bytes written to it
 *
 * @param out The [OutputStream] to write to
 *
 * @since 0.1.0
 * @version 0.1.1
 */
class CountingOutputStream(

    /**
     * The [OutputStream] to write to
     *
     * @since 0.1.0
     * @version 0.1.1
     */
    val out: OutputStream,

) : OutputStream() {

    /**
     * The amount of bytes written to the [OutputStream]
     *
     * @deprecated Use [byteCount] instead
     *
     * @since 0.1.0
     * @version 0.1.1
     */
    val count: Long get() = byteCount

    /**
     * The count of written bytes to the output stream
     *
     * @since 0.1.1
     * @version 0.1.1
     */
    var byteCount: Long = 0L
        private set

    /**
     * The count of write operations to the output stream
     *
     * @since 0.1.1
     * @version 0.1.1
     */
    var operationCount: Long = 0L
        private set

    /**
     * Writes a single byte to the [OutputStream]
     *
     * @param b The byte to write
     *
     * @since 0.1.0
     * @version 0.1.1
     */
    override fun write(b: Int) {
        out.write(b)
        byteCount++
        operationCount++
    }

    /**
     * Writes a byte array to the [OutputStream]
     *
     * @param b The byte array to write
     *
     * @since 0.1.0
     * @version 0.1.1
     */
    override fun write(b: ByteArray) {
        out.write(b)
        byteCount += b.size.toLong()
        operationCount++
    }

    /**
     * Writes a byte array to the [OutputStream]
     *
     * @param b The byte array to write
     * @param off The offset to start writing
     * @param len The amount of bytes to write
     *
     * @since 0.1.0
     * @version 0.1.1
     */
    override fun write(b: ByteArray, off: Int, len: Int) {
        out.write(b, off, len)
        byteCount += len.toLong()
        operationCount++
    }

    /**
     * Flushes the [OutputStream]
     *
     * @since 0.1.0
     * @version 0.1.1
     */
    override fun flush() {
        out.flush()
    }

    /**
     * Closes the [OutputStream]
     *
     * @since 0.1.0
     * @version 0.1.1
     */
    override fun close() {
        out.close()
    }
}