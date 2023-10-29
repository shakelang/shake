package io.github.shakelang.io.streaming.output

/**
 * An [OutputStream] that counts the amount of bytes written to it
 *
 * @param out The [OutputStream] to write to
 *
 * @since 0.1.0
 * @version 0.1.1
 * @author [Nicolas Schmidt &lt;@nsc-de&gt;](https://github.com/nsc-de/)
 */
class CountingOutputStream(

    /**
     * The [OutputStream] to write to
     *
     * @since 0.1.0
     * @version 0.1.1
     * @author [Nicolas Schmidt &lt;@nsc-de&gt;](https://github.com/nsc-de/)
     */
    val out: OutputStream,

) : OutputStream() {

    /**
     * The amount of bytes written to the [OutputStream]
     *
     * @since 0.1.0
     * @version 0.1.1
     * @author [Nicolas Schmidt &lt;@nsc-de&gt;](https://github.com/nsc-de/)
     */
    var count: Long = 0
        private set

    /**
     * Writes a single byte to the [OutputStream]
     *
     * @param b The byte to write
     *
     * @since 0.1.0
     * @version 0.1.1
     * @author [Nicolas Schmidt &lt;@nsc-de&gt;](https://github.com/nsc-de/)
     */
    override fun write(b: Int) {
        out.write(b)
        count++
    }

    /**
     * Writes a byte array to the [OutputStream]
     *
     * @param b The byte array to write
     *
     * @since 0.1.0
     * @version 0.1.1
     * @author [Nicolas Schmidt &lt;@nsc-de&gt;](https://github.com/nsc-de/)
     */
    override fun write(b: ByteArray) {
        out.write(b)
        count += b.size.toLong()
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
     * @author [Nicolas Schmidt &lt;@nsc-de&gt;](https://github.com/nsc-de/)
     */
    override fun write(b: ByteArray, off: Int, len: Int) {
        out.write(b, off, len)
        count += len.toLong()
    }

    /**
     * Flushes the [OutputStream]
     *
     * @since 0.1.0
     * @version 0.1.1
     * @author [Nicolas Schmidt &lt;@nsc-de&gt;](https://github.com/nsc-de/)
     */
    override fun flush() {
        out.flush()
    }

    /**
     * Closes the [OutputStream]
     *
     * @since 0.1.0
     * @version 0.1.1
     * @author [Nicolas Schmidt &lt;@nsc-de&gt;](https://github.com/nsc-de/)
     */
    override fun close() {
        out.close()
    }
}