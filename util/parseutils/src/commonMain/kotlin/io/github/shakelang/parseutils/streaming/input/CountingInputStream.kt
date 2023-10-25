package io.github.shakelang.parseutils.streaming.input

/**
 * This class is an input stream that reads from another input stream
 * and counts the read bytes
 *
 * @param data The input stream to count the read bytes from
 * @constructor Creates a new CountingInputStream
 *
 * @since 0.1.0
 * @version 0.1.0
 * @author Nicolas Schmidt &lt;@nsc-de&gt;
 */
class CountingInputStream (

    /**
     * The input stream to count the read bytes from
     *
     * @since 0.1.0
     * @version 0.1.0
     * @author Nicolas Schmidt &lt;@nsc-de&gt;
     */
    private val data: InputStream

) : InputStream {

    /**
     * The count of read bytes from the input stream
     *
     * @since 0.1.0
     * @version 0.1.0
     * @author Nicolas Schmidt &lt;@nsc-de&gt;
     */
    var count = 0L
        private set

    /**
     * Reads a single byte from the input stream
     * and increases the count by one
     *
     * @return the read byte or -1 if the end of the stream is reached
     *
     * @since 0.1.0
     * @version 0.1.0
     * @author Nicolas Schmidt &lt;@nsc-de&gt;
     *
     * @see InputStream.read
     * @see InputStream.readNBytes
     */
    override fun read(): Int {
        val read = data.read()
        if (read != -1) count++
        return read
    }

    /**
     * Reads bytes from the input stream
     * and increases the count by the number of read bytes
     *
     * @return the number of read bytes or -1 if the end of the stream is reached
     *
     * @since 0.1.0
     * @version 0.1.0
     * @author Nicolas Schmidt &lt;@nsc-de&gt;
     */
    override fun read(b: ByteArray): Int {
        val read = data.read(b)
        if (read != -1) count += read
        return read
    }

    /**
     * Reads bytes from the input stream
     * and increases the count by the number of read bytes
     *
     * @return the number of read bytes or -1 if the end of the stream is reached
     *
     * @since 0.1.0
     * @version 0.1.0
     * @author Nicolas Schmidt &lt;@nsc-de&gt;
     */
    override fun read(b: ByteArray, off: Int, len: Int): Int {
        val read = data.read(b, off, len)
        if (read != -1) count += read
        return read
    }

    /**
     * Skips bytes from the input stream
     * and increases the count by the number of skipped bytes
     *
     * @return the number of skipped bytes or -1 if the end of the stream is reached
     *
     * @since 0.1.0
     * @version 0.1.0
     * @author Nicolas Schmidt &lt;@nsc-de&gt;
     */
    override fun skip(n: Long): Long {
        val skipped = data.skip(n)
        count += skipped
        return skipped
    }

    /**
     * Gets the number of bytes that can be read from the input stream
     * without blocking and without increasing the count
     *
     * @return the number of bytes that can be read from the input stream
     *
     * @since 0.1.0
     * @version 0.1.0
     * @author Nicolas Schmidt &lt;@nsc-de&gt;
     */
    override fun available(): Int {
        return data.available()
    }

    /**
     * Closes the input stream
     *
     * @since 0.1.0
     * @version 0.1.0
     * @author Nicolas Schmidt &lt;@nsc-de&gt;
     */
    override fun close() {
        data.close()
    }
}