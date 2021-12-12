package io.github.shakelang.parseutils.streaming.input

expect abstract class InputStream() {
    /**
     * Returns an estimate of the number of bytes that can be read (or skipped over) from this input
     * stream without blocking by the next invocation of a method for this input stream.
     */
    open fun available(): Int

    /**
     * Closes this input stream and releases any system resources associated with the stream.
     */
    open fun close()

    /**
     * Marks the current position in this input stream.
     */
    open fun mark(readlimit: Int)

    /**
     * Tests if this input stream supports the mark and reset methods.
     */
    open fun markSupported(): Boolean

    /**
     * Reads the next byte of data from the input stream.
     */
    abstract fun read(): Int

    /**
     * Reads some number of bytes from the input stream and stores them into the buffer array b.
     */
    open fun read(b: ByteArray): Int

    /**
     * Reads up to len bytes of data from the input stream into an array of bytes.
     */
    open fun read(b: ByteArray, off: Int, len: Int): Int

    /**
     * Resets this input stream to the position at the time the mark method was last called on this
     * input stream.
     */
    open fun reset()

    /**
     * Skips over and discards n bytes of data from this input stream.
     */
    open fun skip(n: Long): Long

    /**
     * Returns the number of bytes that can be read (or skipped over) from this input stream without
     */
    open fun readNBytes(n: Int): ByteArray

    /**
     * Returns the number of bytes that can be read (or skipped over) from this input stream without
     */
    open fun readNBytes(b: ByteArray, off: Int, len: Int): Int

}