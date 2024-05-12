package com.shakelang.util.io.streaming.input.bytes

import com.shakelang.util.io.IOException
import com.shakelang.util.primitives.bytes.*

/**
 * A [DataInputStream] is a [InputStream] that can read data from a [InputStream]
 *
 * @param data The [InputStream] to read from
 *
 * @since 0.1.0
 * @version 0.1.1
 */
class DataInputStream(private val data: InputStream) : InputStream() {

    /**
     * Reads the next byte of data from the input stream.
     *
     * @return the next byte of data, or -1 if the end of the stream is reached.
     *
     * @throws IOException if an I/O error occurs.
     */
    override fun read(): Int {
        return data.read()
    }

    /**
     * Reads some number of bytes from the input stream and stores them
     * into the buffer array.
     *
     * @param b the buffer into which the data is read.
     * @return the total number of bytes read into the buffer, or -1 if
     * @throws IOException if an I/O error occurs.
     *
     * @since 0.1.0
     * @version 0.1.0
     */
    override fun read(b: ByteArray): Int {
        return data.read(b)
    }

    /**
     * Reads up to len bytes of data from the input stream into an array of bytes.
     *
     * @param b the buffer into which the data is read.
     * @param off the start offset in the destination array b
     * @param len the maximum number of bytes read.
     * @return the total number of bytes read into the buffer, or -1 if
     * @throws IOException if an I/O error occurs.
     */
    override fun read(b: ByteArray, off: Int, len: Int): Int {
        return data.read(b, off, len)
    }

    /**
     * Skips over and discards n bytes of data from this input stream.
     *
     * @param n the number of bytes to be skipped.
     * @return the actual number of bytes skipped.
     * @throws IOException if an I/O error occurs.
     */
    override fun skip(n: Long): Long {
        return data.skip(n)
    }

    /**
     * Returns the number of bytes that can be read from this input stream without blocking.
     *
     * @return the number of bytes that can be read from this input stream without blocking.
     * @throws IOException if an I/O error occurs.
     */
    override fun available(): Int {
        return data.available()
    }

    /**
     * Closes this input stream and releases any system resources associated with the stream.
     *
     * @throws IOException if an I/O error occurs.
     */
    override fun close() {
        data.close()
    }

    /**
     * Marks the current position in this input stream.
     *
     * @param readlimit the maximum limit of bytes that can be read before the mark
     * position becomes invalid.
     * @throws IOException if an I/O error occurs.
     * @throws UnsupportedOperationException if this method is not supported.
     *
     * @see InputStream.markSupported
     */
    override fun mark(readlimit: Int) {
        data.mark(readlimit)
    }

    /**
     * Repositions this stream to the position at the time the mark
     * method was last called on this input stream.
     *
     * @throws IOException if an I/O error occurs.
     * @throws UnsupportedOperationException if this method is not supported.
     */
    override fun reset() {
        data.reset()
    }

    /**
     * Tests if this input stream supports the mark and reset methods.
     *
     * @return true if this stream instance supports the mark and reset methods;
     * false otherwise.
     * @throws IOException if an I/O error occurs.
     * @throws UnsupportedOperationException if this method is not supported.
     */
    override fun markSupported(): Boolean {
        return data.markSupported()
    }

    /**
     * Reads the next byte of data from the input stream.
     *
     * @return the next byte of data, or -1 if the end of the stream is reached.
     * @throws IOException if an I/O error occurs.
     * @throws IllegalStateException if the stream is closed
     * @throws UnsupportedOperationException if this method is not supported.
     */
    fun readByte(): Byte {
        return read().toByte()
    }

    /**
     * Reads a single shorts from the InputStream. (big endian)
     *
     * @return the short that was read
     * @throws IOException if an I/O error occurs.
     * @throws IllegalStateException if the stream is closed
     * @throws IllegalArgumentException if the stream is not a valid shorts
     * @throws UnsupportedOperationException if this method is not supported.
     */
    fun readShort(): Short {
        return readNBytes(2).toShort()
    }

    /**
     * Reads a single short from the InputStream. (big endian)
     *
     * @return the short that was read
     * @throws IOException if an I/O error occurs.
     * @throws IllegalStateException if the stream is closed
     * @throws IllegalArgumentException if the stream is not a valid short
     * @throws UnsupportedOperationException if this method is not supported.
     */
    fun readShortBE(): Short {
        return readNBytes(2).toShortBE()
    }

    /**
     * Reads a single short from the InputStream. (little endian)
     *
     * @return the short that was read
     * @throws IOException if an I/O error occurs.
     * @throws IllegalStateException if the stream is closed
     * @throws IllegalArgumentException if the stream is not a valid short
     * @throws UnsupportedOperationException if this method is not supported.
     */
    fun readShortLE(): Short {
        return readNBytes(2).toShortLE()
    }

    /**
     * Reads a single int from the InputStream. (big endian)
     *
     * @return the int that was read
     * @throws IOException if an I/O error occurs.
     * @throws IllegalStateException if the stream is closed
     * @throws IllegalArgumentException if the stream is not a valid int
     * @throws UnsupportedOperationException if this method is not supported.
     */
    fun readInt(): Int {
        return readNBytes(4).toInt()
    }

    /**
     * Reads a single int from the InputStream. (big endian)
     *
     * @return the int that was read
     * @throws IOException if an I/O error occurs.
     * @throws IllegalStateException if the stream is closed
     * @throws IllegalArgumentException if the stream is not a valid int
     * @throws UnsupportedOperationException if this method is not supported.
     */
    fun readIntBE(): Int {
        return readNBytes(4).toIntBE()
    }

    /**
     * Reads a single int from the InputStream. (little endian)
     *
     * @return the int that was read
     * @throws IOException if an I/O error occurs.
     * @throws IllegalStateException if the stream is closed
     * @throws IllegalArgumentException if the stream is not a valid int
     * @throws UnsupportedOperationException if this method is not supported.
     */
    fun readIntLE(): Int {
        return readNBytes(4).toIntLE()
    }

    /**
     * Reads a single long from the InputStream. (big endian)
     *
     * @return the long that was read
     * @throws IOException if an I/O error occurs.
     * @throws IllegalStateException if the stream is closed
     * @throws IllegalArgumentException if the stream is not a valid long
     * @throws UnsupportedOperationException if this method is not supported.
     */
    fun readLong(): Long {
        return readNBytes(8).toLong()
    }

    /**
     * Reads a single long from the InputStream. (big endian)
     *
     * @return the long that was read
     * @throws IOException if an I/O error occurs.
     * @throws IllegalStateException if the stream is closed
     * @throws IllegalArgumentException if the stream is not a valid long
     * @throws UnsupportedOperationException if this method is not supported.
     */
    fun readLongBE(): Long {
        return readNBytes(8).toLongBE()
    }

    /**
     * Reads a single long from the InputStream. (little endian)
     *
     * @return the long that was read
     * @throws IOException if an I/O error occurs.
     * @throws IllegalStateException if the stream is closed
     * @throws IllegalArgumentException if the stream is not a valid long
     * @throws UnsupportedOperationException if this method is not supported.
     */
    fun readLongLE(): Long {
        return readNBytes(8).toLongLE()
    }

    /**
     * Reads a single float from the InputStream. (big endian)
     *
     * @return the float that was read
     * @throws IOException if an I/O error occurs.
     * @throws IllegalStateException if the stream is closed
     * @throws IllegalArgumentException if the stream is not a valid long
     * @throws UnsupportedOperationException if this method is not supported.
     */
    fun readFloat(): Float {
        return readNBytes(4).toFloat()
    }

    /**
     * Reads a single float from the InputStream. (big endian)
     *
     * @return the float that was read
     * @throws IOException if an I/O error occurs.
     * @throws IllegalStateException if the stream is closed
     * @throws IllegalArgumentException if the stream is not a valid long
     * @throws UnsupportedOperationException if this method is not supported.
     */
    fun readFloatBE(): Float {
        return readNBytes(4).toFloatBE()
    }

    /**
     * Reads a single float from the InputStream. (little endian)
     *
     * @return the float that was read
     * @throws IOException if an I/O error occurs.
     * @throws IllegalStateException if the stream is closed
     * @throws IllegalArgumentException if the stream is not a valid long
     * @throws UnsupportedOperationException if this method is not supported.
     */
    fun readFloatLE(): Float {
        return readNBytes(4).toFloatLE()
    }

    /**
     * Reads a single double from the InputStream. (big endian)
     *
     * @return the double that was read
     * @throws IOException if an I/O error occurs.
     * @throws IllegalStateException if the stream is closed
     * @throws IllegalArgumentException if the stream is not a valid long
     * @throws UnsupportedOperationException if this method is not supported.
     */
    fun readDouble(): Double {
        return readNBytes(8).toDouble()
    }

    /**
     * Reads a single double from the InputStream. (big endian)
     *
     * @return the double that was read
     * @throws IOException if an I/O error occurs.
     * @throws IllegalStateException if the stream is closed
     * @throws IllegalArgumentException if the stream is not a valid long
     * @throws UnsupportedOperationException if this method is not supported.
     */
    fun readDoubleBE(): Double {
        return readNBytes(8).toDoubleBE()
    }

    /**
     * Reads a single double from the InputStream. (little endian)
     *
     * @return the double that was read
     * @throws IOException if an I/O error occurs.
     * @throws IllegalStateException if the stream is closed
     * @throws IllegalArgumentException if the stream is not a valid long
     * @throws UnsupportedOperationException if this method is not supported.
     */
    fun readDoubleLE(): Double {
        return readNBytes(8).toDoubleLE()
    }

    /**
     * Reads a single boolean from the InputStream.
     *
     * @return the boolean that was read
     * @throws IOException if an I/O error occurs.
     * @throws IllegalStateException if the stream is closed
     * @throws IllegalArgumentException if the stream is not a valid long
     * @throws UnsupportedOperationException if this method is not supported.
     */
    fun readUnsignedByte(): UByte {
        return readByte().toUByte()
    }

    /**
     * Reads a single unsigned shorts from the InputStream. (big endian)
     *
     * @return the unsigned shorts that was read
     * @throws IOException if an I/O error occurs.
     * @throws IllegalStateException if the stream is closed
     * @throws IllegalArgumentException if the stream is not a valid long
     * @throws UnsupportedOperationException if this method is not supported.
     */
    fun readUnsignedShort(): UShort {
        return readNBytes(2).toUnsignedShort()
    }

    /**
     * Reads a single unsigned shorts from the InputStream. (big endian)
     *
     * @return the unsigned shorts that was read
     * @throws IOException if an I/O error occurs.
     * @throws IllegalStateException if the stream is closed
     * @throws IllegalArgumentException if the stream is not a valid long
     * @throws UnsupportedOperationException if this method is not supported.
     */
    fun readUnsignedShortBE(): UShort {
        return readNBytes(2).toUnsignedShortBE()
    }

    /**
     * Reads a single unsigned shorts from the InputStream. (little endian)
     *
     * @return the unsigned shorts that was read
     * @throws IOException if an I/O error occurs.
     * @throws IllegalStateException if the stream is closed
     * @throws IllegalArgumentException if the stream is not a valid long
     * @throws UnsupportedOperationException if this method is not supported.
     */
    fun readUnsignedShortLE(): UShort {
        return readNBytes(2).toUnsignedShortLE()
    }

    /**
     * Reads a single unsigned int from the InputStream. (big endian)
     *
     * @return the unsigned int that was read
     * @throws IOException if an I/O error occurs.
     * @throws IllegalStateException if the stream is closed
     * @throws IllegalArgumentException if the stream is not a valid long
     * @throws UnsupportedOperationException if this method is not supported.
     */
    fun readUnsignedInt(): UInt {
        return readNBytes(4).toUnsignedInt()
    }

    /**
     * Reads a single unsigned int from the InputStream. (big endian)
     *
     * @return the unsigned int that was read
     * @throws IOException if an I/O error occurs.
     * @throws IllegalStateException if the stream is closed
     * @throws IllegalArgumentException if the stream is not a valid long
     * @throws UnsupportedOperationException if this method is not supported.
     */
    fun readUnsignedIntBE(): UInt {
        return readNBytes(4).toUnsignedIntBE()
    }

    /**
     * Reads a single unsigned int from the InputStream. (little endian)
     *
     * @return the unsigned int that was read
     * @throws IOException if an I/O error occurs.
     * @throws IllegalStateException if the stream is closed
     * @throws IllegalArgumentException if the stream is not a valid long
     * @throws UnsupportedOperationException if this method is not supported.
     */
    fun readUnsignedIntLE(): UInt {
        return readNBytes(4).toUnsignedIntLE()
    }

    /**
     * Reads a single unsigned long from the InputStream. (big endian)
     *
     * @return the unsigned long that was read
     * @throws IOException if an I/O error occurs.
     * @throws IllegalStateException if the stream is closed
     * @throws IllegalArgumentException if the stream is not a valid long
     * @throws UnsupportedOperationException if this method is not supported.
     */
    fun readUnsignedLong(): ULong {
        return readNBytes(8).toUnsignedLong()
    }

    /**
     * Reads a single unsigned long from the InputStream. (big endian)
     *
     * @return the unsigned long that was read
     * @throws IOException if an I/O error occurs.
     * @throws IllegalStateException if the stream is closed
     * @throws IllegalArgumentException if the stream is not a valid long
     * @throws UnsupportedOperationException if this method is not supported.
     */
    fun readUnsignedLongBE(): ULong {
        return readNBytes(8).toUnsignedLongBE()
    }

    /**
     * Reads a single unsigned long from the InputStream. (little endian)
     *
     * @return the unsigned long that was read
     * @throws IOException if an I/O error occurs.
     * @throws IllegalStateException if the stream is closed
     * @throws IllegalArgumentException if the stream is not a valid long
     * @throws UnsupportedOperationException if this method is not supported.
     */
    fun readUnsignedLongLE(): ULong {
        return readNBytes(8).toUnsignedLongLE()
    }

    /**
     * Reads UTF-8 encoded string from the InputStream.
     *
     * @param length the length of the string to read
     * @return the string that was read
     * @throws IOException if an I/O error occurs.
     * @throws IllegalStateException if the stream is closed
     * @throws IllegalArgumentException if the length is negative
     * @throws IllegalArgumentException if the stream is not a valid UTF-8 encoded string
     * @throws UnsupportedOperationException if this method is not supported.
     */
    fun readUTF(length: Int): String {
        return readNBytes(length).toUtf8String()
    }

    /**
     * Reads UTF-8 encoded string from the InputStream.
     *
     * @return the string that was read
     * @throws IOException if an I/O error occurs.
     * @throws IllegalStateException if the stream is closed
     * @throws IllegalArgumentException if the length is negative
     * @throws IllegalArgumentException if the stream is not a valid UTF-8 encoded string
     * @throws UnsupportedOperationException if this method is not supported.
     */
    fun readUTF(): String {
        val length = readUnsignedShort()
        return readUTF(length.toInt())
    }

    /**
     * Reads UTF-8 encoded string from the InputStream.
     *
     * @param length the length of the string to read
     * @return the string that was read
     * @throws IOException if an I/O error occurs.
     * @throws IllegalStateException if the stream is closed
     * @throws IllegalArgumentException if the length is negative
     * @throws IllegalArgumentException if the stream is not a valid UTF-8 encoded string
     * @throws UnsupportedOperationException if this method is not supported.
     */
    fun readUTF8(length: Int): String {
        return readNBytes(length).toUtf8String()
    }

    /**
     * Reads UTF-8 encoded string from the InputStream.
     *
     * @return the string that was read
     * @throws IOException if an I/O error occurs.
     * @throws IllegalStateException if the stream is closed
     * @throws IllegalArgumentException if the length is negative
     * @throws IllegalArgumentException if the stream is not a valid UTF-8 encoded string
     * @throws UnsupportedOperationException if this method is not supported.
     */
    fun readUTF8(): String {
        val length = readUnsignedShort()
        return readUTF(length.toInt())
    }

    /**
     * Reads UTF-16 encoded string from the InputStream.
     *
     * @param length the length of the string to read
     * @return the string that was read
     * @throws IOException if an I/O error occurs.
     * @throws IllegalStateException if the stream is closed
     * @throws IllegalArgumentException if the length is negative
     * @throws IllegalArgumentException if the stream is not a valid UTF-16 encoded string
     * @throws UnsupportedOperationException if this method is not supported.
     */
    fun readUTF16(length: Int): String {
        return CharArray(length) { readUnsignedShort().toInt().toChar() }.concatToString()
    }

    /**
     * Reads UTF-16 encoded string from the InputStream.
     *
     * @return the string that was read
     * @throws IOException if an I/O error occurs.
     * @throws IllegalStateException if the stream is closed
     * @throws IllegalArgumentException if the length is negative
     * @throws IllegalArgumentException if the stream is not a valid UTF-16 encoded string
     * @throws UnsupportedOperationException if this method is not supported.
     */
    fun readUTF16(): String {
        val length = readUnsignedShort()
        return readUTF16(length.toInt())
    }
}
