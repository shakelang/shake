package io.github.shakelang.io.streaming.output

/**
 * A [ByteArrayOutputStream] is a [OutputStream] that writes to a [ByteArray]
 *
 * @constructor Creates a [ByteArrayOutputStream]
 *
 * @since 0.1.0
 * @version 0.1.1
 * @author Nicolas Schmidt &lt;@nsc-de&gt;
 */
class ByteArrayOutputStream : OutputStream() {

    /**
     * The buffer of the [ByteArrayOutputStream]
     *
     * @since 0.1.0
     * @version 0.1.1
     * @author Nicolas Schmidt &lt;@nsc-de&gt;
     */
    private var buf: ByteArray = ByteArray(8192)

    /**
     * The count of the [ByteArrayOutputStream]
     *
     * @since 0.1.0
     * @version 0.1.1
     * @author Nicolas Schmidt &lt;@nsc-de&gt;
     */
    private var count: Int = 0

    /**
     * Writes a byte to the [ByteArrayOutputStream]
     *
     * @param b The byte to write
     *
     * @since 0.1.0
     * @version 0.1.1
     * @author Nicolas Schmidt &lt;@nsc-de&gt;
     */
    fun write(b: Byte) {
        if (count == buf.size) {
            val newbuf = ByteArray(buf.size * 2)
            buf.copyInto(newbuf, 0)
            buf = newbuf
        }
        buf[count++] = b
    }

    /**
     * Writes a byte array to the [ByteArrayOutputStream]
     *
     * @param b The byte array to write
     *
     * @since 0.1.0
     * @version 0.1.1
     * @author Nicolas Schmidt &lt;@nsc-de&gt;
     */
    override fun write(b: ByteArray) {
        for (i in b.indices) write(b[i])
    }

    /**
     * Writes a byte array to the [ByteArrayOutputStream]
     *
     * @param b The byte array to write
     * @param off The offset to start writing
     * @param len The length of the bytes to write
     *
     * @since 0.1.0
     * @version 0.1.1
     * @author Nicolas Schmidt &lt;@nsc-de&gt;
     */
    override fun write(b: ByteArray, off: Int, len: Int) {
        for (i in off until off + len) write(b[i])
    }

    /**
     * Writes a byte to the [ByteArrayOutputStream]
     *
     * @param b The byte to write
     *
     * @since 0.1.0
     * @version 0.1.1
     * @author Nicolas Schmidt &lt;@nsc-de&gt;
     */
    override fun write(b: Int) {
        write(b.toByte())
    }

    /**
     * Returns the content of the [ByteArrayOutputStream] as a [ByteArray]
     *
     * @return the content of the [ByteArrayOutputStream] as a [ByteArray]
     *
     * @since 0.1.0
     * @version 0.1.1
     * @author Nicolas Schmidt &lt;@nsc-de&gt;
     */
    fun toByteArray(): ByteArray {
        val newbuf = ByteArray(count)
        buf.copyInto(newbuf, 0, 0, count)
        return newbuf
    }

    /**
     * Resets the [ByteArrayOutputStream]
     *
     * @since 0.1.0
     * @version 0.1.1
     * @author Nicolas Schmidt &lt;@nsc-de&gt;
     */
    fun reset() {
        count = 0
        buf = ByteArray(8192)
    }

    /**
     * Returns the size of the [ByteArrayOutputStream]
     *
     * @return the size of the [ByteArrayOutputStream]
     *
     * @since 0.1.0
     * @version 0.1.1
     * @author Nicolas Schmidt &lt;@nsc-de&gt;
     */
    fun size(): Int {
        return count
    }

}