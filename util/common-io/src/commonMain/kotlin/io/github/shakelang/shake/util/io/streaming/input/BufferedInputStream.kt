package io.github.shakelang.shake.util.io.streaming.input

import io.github.shakelang.shake.util.io.IOException
import kotlin.math.min

/**
 * A [BufferedInputStream] is created from an [InputStream] and buffers the data
 * to make reading faster. (It's recommended to use this class if you want to read
 * data from a [InputStream])
 *
 * @param input The [InputStream] to buffer
 * @param maxBuffer The size of the buffer (default: 8192)
 *
 * @since 0.1.0
 * @version 0.1.0
 *
 * @see InputStream
 */
class BufferedInputStream (

    /**
     * The [InputStream] to buffer
     *
     * @since 0.1.0
     * @version 0.1.0
     */
    private val input: io.github.shakelang.shake.util.io.streaming.input.InputStream,
    maxBuffer: Int = 8192
) : io.github.shakelang.shake.util.io.streaming.input.InputStream() {

    /**
     * The buffer of the [BufferedInputStream]
     *
     * @since 0.1.0
     * @version 0.1.0
     */
    private val buffer = ByteArray(maxBuffer)

    /**
     * The size of the buffer
     *
     * @since 0.1.0
     * @version 0.1.0
     */
    private var bufferSize = 0

    /**
     * The position of the buffer
     *
     * @since 0.1.0
     * @version 0.1.0
     */
    private var bufferPos = 0

    /**
     * Reads a single byte from the [BufferedInputStream]
     * @return the byte or -1 if the end of the stream is reached
     *
     * @since 0.1.0
     * @version 0.1.0
     */
    override fun read(): Int {
        if (bufferPos == bufferSize) {
            bufferSize = input.read(buffer)
            bufferPos = 0
        }
        return if (bufferSize == -1) -1 else buffer[bufferPos++].toInt() and 0xff
    }

    /**
     * Reads bytes from the [BufferedInputStream] into the given array
     * @param b the array to read into
     * @param off the offset to start reading at
     * @param len the maximum number of bytes to read
     * @return the number of bytes read or -1 if the end of the stream is reached
     *
     * @since 0.1.0
     * @version 0.1.0
     */
    override fun read(b: ByteArray, off: Int, len: Int): Int {
        return this.readNBytes(b, off, len)
    }

    /**
     * Reads bytes from the [BufferedInputStream] into the given array
     * @param b the array to read into
     * @return the number of bytes read or -1 if the end of the stream is reached
     *
     * @since 0.1.1
     * @version 0.1.1
     */
    override fun read(b: ByteArray): Int {
        return read(b, 0, b.size)
    }

    /**
     * Skips over and discards n bytes of data from this input stream.
     *
     * @param n the number of bytes to be skipped.
     * @return the actual number of bytes skipped.
     * @throws IOException if an I/O error occurs.
     * @throws UnsupportedOperationException if this method is not supported.
     *
     * @since 0.1.1
     * @version 0.1.1
     */
    override fun skip(n: Long): Long {
        var skipped = 0L
        while (skipped < n) {
            if (bufferPos == bufferSize) {
                bufferSize = input.read(buffer)
                bufferPos = 0
            }
            if (bufferSize == -1) break
            val skip = min(bufferSize - bufferPos, (n - skipped).toInt())
            bufferPos += skip
            skipped += skip
        }
        return skipped
    }

    /**
     * Reads bytes from the [BufferedInputStream] into the given array
     * @param n the number of bytes to read
     * @return the number of bytes read or -1 if the end of the stream is reached
     *
     * @since 0.1.1
     * @version 0.1.1
     */
    override fun readNBytes(n: Int): ByteArray {
        val bytes = ByteArray(n)
        read(bytes)
        return bytes
    }

    /**
     * Resets this input stream to the position at the time the mark method was last called on this
     * input stream.
     *
     * @throws IOException if an I/O error occurs.
     * @throws UnsupportedOperationException if this method is not supported.
     *
     * @since 0.1.1
     * @version 0.1.1
     */
    override fun reset() {
        input.reset()
        bufferPos = 0
        bufferSize = 0
    }

    /**
     * Marks the current position in this input stream.
     *
     * @param readlimit the maximum limit of bytes that can be read before the mark position becomes invalid.
     * @throws IOException if an I/O error occurs.
     * @throws UnsupportedOperationException if this method is not supported.
     *
     * @since 0.1.1
     * @version 0.1.1
     */
    override fun mark(readlimit: Int) {
        input.mark(readlimit)
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
        // TODO: This marks the input stream, but we already have stuff in the buffer
        return input.markSupported()
    }

    /**
     * Returns an estimate of the number of bytes that can be read (or skipped over) from this input stream without
     * blocking by the next invocation of a method for this input stream.
     *
     * @return an estimate of the number of bytes that can be read (or skipped over) f
     * rom this input stream without blocking or 0 when it reaches the end of the input stream.
     * @throws IOException if an I/O error occurs.
     * @throws UnsupportedOperationException if this method is not supported.
     *
     * @since 0.1.1
     * @version 0.1.1
     */
    override fun available(): Int {
        return bufferSize - bufferPos + input.available()
    }

    /**
     * Reads up to len bytes of data from the input stream into an array of bytes.
     *
     * @throws IOException if an I/O error occurs.
     * @throws UnsupportedOperationException if this method is not supported.
     *
     * @since 0.1.1
     * @version 0.1.1
     */
    override fun readNBytes(b: ByteArray, off: Int, len: Int): Int {
        var read = 0
        for (i in 0 until len) {
            val r = read()
            if (r == -1) break
            b[off + i] = r.toByte()
            read++
        }
        return read
    }

    /**
     * Closes the [BufferedInputStream] and the [InputStream] it was created from
     *
     * @since 0.1.0
     * @version 0.1.0
     */
    override fun close() {
        input.close()
    }

    override fun toString(): String {
        return "BufferedInputStream(input=$input, bufferSize=$bufferSize, bufferPos=$bufferPos)"
    }
}