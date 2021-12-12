package io.github.shakelang.parseutils.streaming.input

import io.github.shakelang.parseutils.bytes.*

class DataInputStream(private val data: InputStream) : InputStream() {

    /**
     * Reads the next byte of data from the input stream.
     */
    override fun read(): Int {
        return data.read()
    }

    /**
     * Reads some number of bytes from the input stream and stores them into the buffer array.
     */
    override fun read(b: ByteArray): Int {
        return data.read(b)
    }

    /**
     * Reads up to len bytes of data from the input stream into an array of bytes.
     */
    override fun read(b: ByteArray, off: Int, len: Int): Int {
        return data.read(b, off, len)
    }

    /**
     * Skips over and discards n bytes of data from this input stream.
     */
    override fun skip(n: Long): Long {
        return data.skip(n)
    }

    /**
     * Returns the number of bytes that can be read from this input stream without blocking.
     */
    override fun available(): Int {
        return data.available()
    }

    /**
     * Closes the input stream.
     */
    override fun close() {
        data.close()
    }

    /**
     * Marks the current position in this input stream.
     */
    override fun mark(readlimit: Int) {
        data.mark(readlimit)
    }

    /**
     * Repositions this stream to the position at the time the mark method was last called on this input stream.
     */
    override fun reset() {
        data.reset()
    }

    /**
     * Tests if this input stream supports the mark and reset methods.
     */
    override fun markSupported(): Boolean {
        return data.markSupported()
    }

    /**
     * Reads a single byte from the InputStream.
     */
    fun readByte(): Byte {
        return read().toByte()
    }

    /**
     * Reads a single short from the InputStream.
     */
    fun readShort(): Short {
        return readNBytes(2).toShort()
    }

    /**
     * Reads a single int from the InputStream.
     */
    fun readInt(): Int {
        return readNBytes(4).toInt()
    }

    /**
     * Reads a single long from the InputStream.
     */
    fun readLong(): Long {
        return readNBytes(8).toLong()
    }

    /**
     * Reads a single float from the InputStream.
     */
    fun readFloat(): Float {
        return readNBytes(4).toFloat()
    }

    /**
     * Reads a single double from the InputStream.
     */
    fun readDouble(): Double {
        return readNBytes(8).toDouble()
    }

    /**
     * Reads a single unsigned byte from the InputStream.
     */
    fun readUnsignedByte(): UByte {
        return readNBytes(1).toUnsignedByte()
    }

    /**
     * Reads a single unsigned short from the InputStream.
     */
    fun readUnsignedShort(): UShort {
        return readNBytes(2).toUnsignedShort()
    }

    /**
     * Reads a single unsigned int from the InputStream.
     */
    fun readUnsignedInt(): UInt {
        return readNBytes(4).toUnsignedInt()
    }

    /**
     * Reads a single unsigned long from the InputStream.
     */
    fun readUnsignedLong(): ULong {
        return readNBytes(8).toUnsignedLong()
    }

}