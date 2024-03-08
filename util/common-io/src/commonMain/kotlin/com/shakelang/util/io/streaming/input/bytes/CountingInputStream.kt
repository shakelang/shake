package com.shakelang.util.io.streaming.input.bytes

import com.shakelang.util.io.IOException

/**
 * This class is an input stream that reads from another input stream
 * and counts the read bytes
 *
 * @param data The input stream to count the read bytes from
 * @constructor Creates a new CountingInputStream
 *
 * @since 0.1.0
 * @version 0.1.1
 */
class CountingInputStream(

    /**
     * The input stream to count the read bytes from
     *
     * @since 0.1.0
     * @version 0.1.1
     */
    private val data: InputStream,

) : InputStream() {

    /**
     * The count of read bytes from the input stream
     *
     * @since 0.1.0
     * @version 0.1.1
     *
     * @deprecated Use [byteCount] instead.
     */
    val count get() = byteCount

    /**
     * The count of read bytes from the input stream
     *
     * @since 0.1.1
     * @version 0.1.1
     */
    var byteCount: Long = 0L
        private set

    /**
     * The count of read operations from the input stream
     *
     * @since 0.1.1
     * @version 0.1.1
     */
    var operationCount: Long = 0L
        private set

    /**
     * Reads a single byte from the input stream
     * and increases the count by one
     *
     * @return the read byte or -1 if the end of the stream is reached
     * @throws IOException if an I/O error occurs
     *
     * @since 0.1.0
     * @version 0.1.1
     *
     * @see InputStream.read
     * @see InputStream.readNBytes
     */
    override fun read(): Int {
        operationCount++
        val read = data.read()
        if (read != -1) byteCount++
        return read
    }

    /**
     * Reads bytes from the input stream
     * and increases the count by the number of read bytes
     *
     * @return the number of read bytes or -1 if the end of the stream is reached
     * @throws IOException if an I/O error occurs
     *
     * @since 0.1.0
     * @version 0.1.1
     */
    override fun read(b: ByteArray): Int {
        operationCount++
        val read = data.read(b)
        if (read != -1) byteCount += read
        return read
    }

    /**
     * Reads bytes from the input stream
     * and increases the count by the number of read bytes
     *
     * @return the number of read bytes or -1 if the end of the stream is reached
     * @throws IOException if an I/O error occurs
     *
     * @since 0.1.0
     * @version 0.1.1
     */
    override fun read(b: ByteArray, off: Int, len: Int): Int {
        operationCount++
        val read = data.read(b, off, len)
        if (read != -1) byteCount += read
        return read
    }

    /**
     * Skips bytes from the input stream
     * and increases the count by the number of skipped bytes
     *
     * @return the number of skipped bytes or -1 if the end of the stream is reached
     * @throws IOException if an I/O error occurs
     *
     * @since 0.1.0
     * @version 0.1.1
     */
    override fun skip(n: Long): Long {
        operationCount++
        val skipped = data.skip(n)
        byteCount += skipped
        return skipped
    }

    /**
     * Reads bytes from the input stream
     * and increases the count by the number of read bytes
     *
     * @return the number of read bytes or -1 if the end of the stream is reached
     * @throws IOException if an I/O error occurs
     *
     * @since 0.1.1
     * @version 0.1.1
     */
    override fun readNBytes(n: Int): ByteArray {
        operationCount++
        val read = data.readNBytes(n)
        byteCount += read.size
        return read
    }

    /**
     * Reads bytes from the input streamand increases the count by the number
     * of read bytes
     *
     * @return the number of read bytes or -1 if the end of the stream is reached
     * @throws IOException if an I/O error occurs
     *
     * @since 0.1.1
     * @version 0.1.1
     */
    override fun readNBytes(b: ByteArray, off: Int, len: Int): Int {
        operationCount++
        val read = data.readNBytes(b, off, len)
        byteCount += read
        return read
    }

    /**
     * Checks if the input stream supports marking/resetting
     *
     * @return true if the input stream supports marking/resetting
     *
     * @since 0.1.1
     * @version 0.1.1
     */
    override fun markSupported(): Boolean {
        return data.markSupported()
    }

    /**
     * Marks the current position in the input stream
     *
     * @param readlimit the maximum limit of bytes that can be read before
     * the mark position becomes invalid
     *
     * @since 0.1.1
     * @version 0.1.1
     */
    override fun mark(readlimit: Int) {
        data.mark(readlimit)
    }

    /**
     * Resets the input stream to the last marked position
     *
     * @since 0.1.1
     * @version 0.1.1
     */
    override fun reset() {
        operationCount = 0
        byteCount = 0
        data.reset()
    }

    /**
     * Gets the number of bytes that can be read from the input stream
     * without blocking and without increasing the count
     *
     * @return the number of bytes that can be read from the input stream
     *
     * @since 0.1.0
     * @version 0.1.1
     */
    override fun available(): Int {
        return data.available()
    }

    /**
     * Closes the input stream
     *
     * @since 0.1.0
     * @version 0.1.1
     */
    override fun close() {
        data.close()
    }

    override fun toString(): String {
        return "CountingInputStream(data=$data, byteCount=$byteCount, operationCount=$operationCount)"
    }
}
