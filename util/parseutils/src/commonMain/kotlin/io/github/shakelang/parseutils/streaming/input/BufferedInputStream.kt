package io.github.shakelang.parseutils.streaming.input

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
 * @author Nicolas Schmidt &lt;@nsc-de&gt;
 *
 * @see InputStream
 */
class BufferedInputStream (

    /**
     * The [InputStream] to buffer
     *
     * @since 0.1.0
     * @version 0.1.0
     * @author Nicolas Schmidt &lt;@nsc-de&gt;
     */
    private val input: InputStream,
    maxBuffer: Int = 8192
) : InputStream {

    /**
     * The buffer of the [BufferedInputStream]
     *
     * @since 0.1.0
     * @version 0.1.0
     * @author Nicolas Schmidt &lt;@nsc-de&gt;
     */
    private val buffer = ByteArray(maxBuffer)

    /**
     * The size of the buffer
     *
     * @since 0.1.0
     * @version 0.1.0
     * @author Nicolas Schmidt &lt;@nsc-de&gt;
     */
    private var bufferSize = 0

    /**
     * The position of the buffer
     *
     * @since 0.1.0
     * @version 0.1.0
     * @author Nicolas Schmidt &lt;@nsc-de&gt;
     */
    private var bufferPos = 0

    /**
     * Reads a single byte from the [BufferedInputStream]
     * @return the byte or -1 if the end of the stream is reached
     *
     * @since 0.1.0
     * @version 0.1.0
     * @author Nicolas Schmidt &lt;@nsc-de&gt;
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
     * @author Nicolas Schmidt &lt;@nsc-de&gt;
     */
    override fun read(b: ByteArray, off: Int, len: Int): Int {
        if (bufferPos == bufferSize) {
            bufferSize = input.read(buffer)
            bufferPos = 0
        }
        val read = min(bufferSize - bufferPos, len)
        buffer.copyInto(b, off, bufferPos, bufferPos + read)
        bufferPos += read
        return read
    }

    /**
     * Closes the [BufferedInputStream] and the [InputStream] it was created from
     *
     * @since 0.1.0
     * @version 0.1.0
     * @author Nicolas Schmidt &lt;@nsc-de&gt;
     */
    override fun close() {
        input.close()
    }
}