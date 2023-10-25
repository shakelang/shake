@file:Suppress("NOTHING_TO_INLINE")
@file:JvmName("InputStreamCommons")
package io.github.shakelang.parseutils.streaming.input

import kotlin.jvm.JvmName

/**
 * This class is an abstract class for all input streams
 *
 * @author Nicolas Schmidt &lt;@nsc-de&gt;
 * @since 0.1.0
 * @version 0.1.0
 */
expect interface InputStream {
    /**
     * Returns an estimate of the number of bytes that can be read (or skipped over) from
     * this input
     * stream without blocking by the next invocation of a method for this input stream.
     *
     * @return an estimate of the number of bytes that can be read (or skipped over) from
     * this input stream without blocking or 0 when it reaches the end of the input stream.
     * @throws IOException if an I/O error occurs.
     * @throws UnsupportedOperationException if this method is not supported.
     *
     * @since 0.1.0
     * @version 0.1.0
     * @author Nicolas Schmidt &lt;@nsc-de&gt;
     *
     * @see InputStream.read
     * @see InputStream.readNBytes
     */
    open fun available(): Int

    /**
     * Closes this input stream and releases any system resources associated with the stream.
     *
     * @throws IOException if an I/O error occurs.
     * @throws UnsupportedOperationException if this method is not supported.
     *
     * @since 0.1.0
     * @version 0.1.0
     * @author Nicolas Schmidt &lt;@nsc-de&gt;
     */
    open fun close()

    /**
     * Marks the current position in this input stream.
     *
     * @param readlimit the maximum limit of bytes that can be read before the mark position becomes invalid.
     * @throws IOException if an I/O error occurs.
     * @throws UnsupportedOperationException if this method is not supported.
     *
     * @since 0.1.0
     * @version 0.1.0
     * @author Nicolas Schmidt &lt;@nsc-de&gt;
     *
     * @see InputStream.markSupported
     */
    open fun mark(readlimit: Int)

    /**
     * Tests if this input stream supports the mark and reset methods.
     *
     * @return true if this stream instance supports the mark and reset methods; false otherwise.
     * @throws IOException if an I/O error occurs.
     * @throws UnsupportedOperationException if this method is not supported.
     *
     * @since 0.1.0
     * @version 0.1.0
     * @author Nicolas Schmidt &lt;@nsc-de&gt;
     *
     * @see InputStream.mark
     */
    open fun markSupported(): Boolean

    /**
     * Reads the next byte of data from the input stream.
     *
     * @return the next byte of data, or -1 if the end of the stream is reached.
     * @throws IOException if an I/O error occurs.
     * @throws UnsupportedOperationException if this method is not supported.
     *
     * @since 0.1.0
     * @version 0.1.0
     * @author Nicolas Schmidt &lt;@nsc-de&gt;
     *
     * @see InputStream.read
     * @see InputStream.readNBytes
     */
    abstract fun read(): Int

    /**
     * Reads some number of bytes from the input stream and stores them into the buffer array b.
     *
     * @param b the buffer into which the data is read.
     * @return the total number of bytes read into the buffer, or -1 if there is no more data
     * because the end of the stream has been reached.
     * @throws IOException if an I/O error occurs.
     * @throws UnsupportedOperationException if this method is not supported.
     *
     * @since 0.1.0
     * @version 0.1.0
     * @author Nicolas Schmidt &lt;@nsc-de&gt;
     *
     * @see InputStream.read
     * @see InputStream.readNBytes
     */
    open fun read(b: ByteArray): Int

    /**
     * Reads up to len bytes of data from the input stream into an array of bytes.
     *
     * @param b the buffer into which the data is read.
     * @param off the start offset in array b at which the data is written.
     * @param len the maximum number of bytes to read.
     * @return the total number of bytes read into the buffer, or -1 if there is no more data
     * because the end of the stream has been reached.
     *
     * @throws IOException if an I/O error occurs.
     * @throws UnsupportedOperationException if this method is not supported.
     *
     * @since 0.1.0
     * @version 0.1.0
     * @author Nicolas Schmidt &lt;@nsc-de&gt;
     *
     * @see InputStream.read
     * @see InputStream.readNBytes
     */
    open fun read(b: ByteArray, off: Int, len: Int): Int

    /**
     * Resets this input stream to the position at the time the mark method was last called on this
     * input stream.
     *
     * @throws IOException if an I/O error occurs.
     * @throws UnsupportedOperationException if this method is not supported.
     *
     * @since 0.1.0
     * @version 0.1.0
     * @author Nicolas Schmidt &lt;@nsc-de&gt;
     *
     * @see InputStream.mark
     * @see InputStream.markSupported
     * @see InputStream.reset
     */
    open fun reset()

    /**
     * Skips over and discards n bytes of data from this input stream.
     *
     * @param n the number of bytes to be skipped.
     * @return the actual number of bytes skipped.
     * @throws IOException if an I/O error occurs.
     * @throws UnsupportedOperationException if this method is not supported.
     *
     * @since 0.1.0
     * @version 0.1.0
     * @author Nicolas Schmidt &lt;@nsc-de&gt;
     *
     * @see InputStream.skip
     * @see InputStream.skipNBytes
     */
    open fun skip(n: Long): Long

    /**
     * Returns the number of bytes that can be read (or skipped over) from this input stream without
     *
     * @return the number of bytes that can be read from this input stream without blocking.
     * @throws IOException if an I/O error occurs.
     * @throws UnsupportedOperationException if this method is not supported.
     *
     * @since 0.1.0
     * @version 0.1.0
     * @author Nicolas Schmidt &lt;@nsc-de&gt;
     *
     * @see InputStream.available
     * @see InputStream.read
     * @see InputStream.readNBytes
     */
    open fun readNBytes(n: Int): ByteArray

    /**
     * Returns the number of bytes that can be read (or skipped over) from this input stream without
     *
     * @param b the buffer into which the data is read.
     * @return the number of bytes that can be read from this input stream without blocking.
     * @throws IOException if an I/O error occurs.
     * @throws UnsupportedOperationException if this method is not supported.
     *
     * @since 0.1.0
     * @version 0.1.0
     * @author Nicolas Schmidt &lt;@nsc-de&gt;
     *
     * @see InputStream.available
     * @see InputStream.read
     * @see InputStream.readNBytes
     */
    open fun readNBytes(b: ByteArray, off: Int, len: Int): Int

}

/**
 * Returns a DataInputStream that reads from this input stream.
 *
 * @return a DataInputStream that reads from this input stream.
 *
 * @since 0.1.0
 * @version 0.1.0
 * @author Nicolas Schmidt &lt;@nsc-de&gt;
 */
inline fun InputStream.asDataInputStream(): DataInputStream {
    return DataInputStream(this)
}

/**
 * Returns a DataInputStream that reads from this input stream.
 *
 * @return a DataInputStream that reads from this input stream.
 *
 * @since 0.1.0
 * @version 0.1.0
 * @author Nicolas Schmidt &lt;@nsc-de&gt;
 */
inline val InputStream.dataStream: DataInputStream
    get() = DataInputStream(this)

/**
 * Returns a CountingInputStream that reads from this input stream.
 *
 * @return a CountingInputStream that reads from this input stream.
 *
 * @since 0.1.0
 * @version 0.1.0
 * @author Nicolas Schmidt &lt;@nsc-de&gt;
 */
inline fun InputStream.asCountingInputStream(): CountingInputStream {
    return CountingInputStream(this)
}

/**
 * Returns a CountingInputStream that reads from this input stream.
 *
 * @return a CountingInputStream that reads from this input stream.
 *
 * @since 0.1.0
 * @version 0.1.0
 * @author Nicolas Schmidt &lt;@nsc-de&gt;
 */
inline val InputStream.countingStream: CountingInputStream
    get() = CountingInputStream(this)

/**
 * Returns a BufferedInputStream that reads from this input stream.
 *
 * @return a BufferedInputStream that reads from this input stream.
 *
 * @since 0.1.0
 * @version 0.1.0
 * @author Nicolas Schmidt &lt;@nsc-de&gt;
 */
inline fun InputStream.asBufferedInputStream(): BufferedInputStream {
    return BufferedInputStream(this)
}

/**
 * Returns a BufferedInputStream that reads from this input stream.
 *
 * @return a BufferedInputStream that reads from this input stream.
 *
 * @since 0.1.0
 * @version 0.1.0
 * @author Nicolas Schmidt &lt;@nsc-de&gt;
 */
inline val InputStream.bufferedStream: BufferedInputStream
    get() = BufferedInputStream(this)

/**
 * Returns a BufferedInputStream that reads from this input stream.
 *
 * @param bufferSize the size of the buffer
 * @return a BufferedInputStream that reads from this input stream.
 *
 * @since 0.1.1
 * @version 0.1.1
 * @author Nicolas Schmidt &lt;@nsc-de&gt;
 */
val InputStream.readFully: ByteArray
    get() {
        val list = mutableListOf<Byte>()
        while (true) {
            val i = read()
            if (i == -1) break
            list.add(i.toByte())
        }
        return list.toByteArray()
    }