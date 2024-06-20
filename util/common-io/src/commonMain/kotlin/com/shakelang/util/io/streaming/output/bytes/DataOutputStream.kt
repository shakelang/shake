package com.shakelang.util.io.streaming.output.bytes

import com.shakelang.util.primitives.bytes.toBytes
import com.shakelang.util.primitives.bytes.toBytesLE

/**
 * A [DataOutputStream] is a [OutputStream] that can write primitive types
 *
 * @param output The [OutputStream] to write to
 * @constructor Creates a [DataOutputStream] with the given [OutputStream]
 *
 * @since 0.1.0
 * @version 0.1.1
 */
@OptIn(ExperimentalUnsignedTypes::class)
class DataOutputStream(
    /**
     * The [OutputStream] to write to
     */
    val output: OutputStream,
) : OutputStream() {

    /**
     * Writes a single byte to the [OutputStream]
     * @param b The byte to write
     */
    override fun write(b: Int) {
        output.write(b)
    }

    /**
     * Flushes the [OutputStream]
     */
    override fun flush() {
        output.flush()
    }

    /**
     * Closes the [OutputStream]
     */
    override fun close() {
        output.close()
    }

    /**
     * Writes a boolean to the [OutputStream]
     * @param b The boolean to write
     */
    fun writeBoolean(b: Boolean) {
        write(if (b) 1 else 0)
    }

    /**
     * Writes a byte to the [OutputStream]
     * @param b The byte to write
     */
    fun writeByte(b: Byte) {
        write(b.toInt())
    }

    /**
     * Writes a short to the [OutputStream] (big endian)
     * @param b The shorts to write
     */
    fun writeShortBE(b: Short) {
        write(b.toBytes())
    }

    /**
     * Writes a short to the [OutputStream] (little endian)
     * @param b The shorts to write
     */
    fun writeShortLE(b: Short) {
        write(b.toBytesLE())
    }

    /**
     * Writes a short to the [OutputStream] (big endian)
     * @param b The shorts to write
     */
    fun writeShort(b: Short) {
        write(b.toBytes())
    }

    /**
     * Writes an int to the [OutputStream] (big endian)
     * @param b The int to write
     */
    fun writeIntBE(b: Int) {
        write(b.toBytes())
    }

    /**
     * Writes an int to the [OutputStream] (little endian)
     * @param b The int to write
     */
    fun writeIntLE(b: Int) {
        write(b.toBytesLE())
    }

    /**
     * Writes an int to the [OutputStream] (big endian)
     * @param b The int to write
     */
    fun writeInt(b: Int) {
        write(b.toBytes())
    }

    /**
     * Writes a long to the [OutputStream] (big endian)
     * @param b The long to write
     */
    fun writeLongBE(b: Long) {
        write(b.toBytes())
    }

    /**
     * Writes a long to the [OutputStream] (little endian)
     * @param b The long to write
     */
    fun writeLongLE(b: Long) {
        write(b.toBytesLE())
    }

    /**
     * Writes a long to the [OutputStream] (big endian)
     * @param b The long to write
     */
    fun writeLong(b: Long) {
        write(b.toBytes())
    }

    /**
     * Writes a float to the [OutputStream] (big endian)
     * @param b The float to write
     */
    fun writeFloatBE(b: Float) {
        write(b.toBytes())
    }

    /**
     * Writes a float to the [OutputStream] (little endian)
     * @param b The float to write
     */
    fun writeFloatLE(b: Float) {
        write(b.toBytesLE())
    }

    /**
     * Writes a float to the [OutputStream] (big endian)
     * @param b The float to write
     */
    fun writeFloat(b: Float) {
        write(b.toBytes())
    }

    /**
     * Writes a double to the [OutputStream] (big endian)
     * @param b The double to write
     */
    fun writeDoubleBE(b: Double) {
        write(b.toBytes())
    }

    /**
     * Writes a double to the [OutputStream] (little endian)
     * @param b The double to write
     */
    fun writeDoubleLE(b: Double) {
        write(b.toBytesLE())
    }

    /**
     * Writes a double to the [OutputStream] (big endian)
     * @param b The double to write
     */
    fun writeDouble(b: Double) {
        write(b.toBytes())
    }

    /**
     * Writes an unsigned byte to the [OutputStream]
     * @param b The unsigned byte to write
     */
    fun writeUnsignedByte(b: UByte) {
        writeByte(b.toByte())
    }

    /**
     * Writes an unsigned short to the [OutputStream] (big endian)
     * @param b The unsigned short to write
     */
    fun writeUnsignedShortBE(b: UShort) {
        writeShortBE(b.toShort())
    }

    /**
     * Writes an unsigned short to the [OutputStream] (little endian)
     * @param b The unsigned short to write
     */
    fun writeUnsignedShortLE(b: UShort) {
        writeShortLE(b.toShort())
    }

    /**
     * Writes an unsigned shorts to the [OutputStream] (big endian)
     * @param b The unsigned shorts to write
     */
    fun writeUnsignedShort(b: UShort) {
        writeShort(b.toShort())
    }

    /**
     * Writes an unsigned int to the [OutputStream] (big endian)
     * @param b The unsigned int to write
     */
    fun writeUnsignedIntBE(b: UInt) {
        writeIntBE(b.toInt())
    }

    /**
     * Writes an unsigned int to the [OutputStream] (little endian)
     * @param b The unsigned int to write
     */
    fun writeUnsignedIntLE(b: UInt) {
        writeIntLE(b.toInt())
    }

    /**
     * Writes an unsigned int to the [OutputStream] (big endian)
     * @param b The unsigned int to write
     */
    fun writeUnsignedInt(b: UInt) {
        writeInt(b.toInt())
    }

    /**
     * Writes an unsigned long to the [OutputStream] (big endian)
     * @param b The unsigned long to write
     */
    fun writeUnsignedLongBE(b: ULong) {
        writeLongBE(b.toLong())
    }

    /**
     * Writes an unsigned long to the [OutputStream] (little endian)
     * @param b The unsigned long to write
     */
    fun writeUnsignedLongLE(b: ULong) {
        writeLongLE(b.toLong())
    }

    /**
     * Writes an unsigned long to the [OutputStream] (big endian)
     * @param b The unsigned long to write
     */
    fun writeUnsignedLong(b: ULong) {
        writeLong(b.toLong())
    }

    /**
     * Writes a char to the [OutputStream] using UTF-16 (LE)
     * @param b The char to write
     */
    fun writeCharUTF16(b: Char) {
        write((b.code shr 8).toUByte().toInt())
        write((b.code).toUByte().toInt())
    }

    /**
     * Writes a char to the [OutputStream] using UTF-8
     * @param b The char to write using UTF-8
     */
    fun writeCharUTF8(b: Char) {
        writeUnsignedByte(b.code.toUByte())
    }

    /**
     * Writes a byte array to the [OutputStream]
     * @param b The byte array to write
     */
    fun writeBooleanArray(b: BooleanArray) {
        b.forEach { writeBoolean(it) }
    }

    /**
     * Writes a byte array to the [OutputStream]
     * @param b The byte array to write
     * @param off The offset to start writing
     * @param len The length of the array to write
     *
     * @throws IndexOutOfBoundsException If the offset or the length is out of bounds
     * @throws IllegalArgumentException If the length is negative
     */
    fun writeBooleanArray(b: BooleanArray, off: Int, len: Int) {
        for (i in off until off + len) {
            writeBoolean(b[i])
        }
    }

    /**
     * Writes a byte array to the [OutputStream]
     * @param b The byte array to write
     *
     * @throws IndexOutOfBoundsException If the offset or the length is out of bounds
     * @throws IllegalArgumentException If the length is negative
     */
    fun writeBooleanArray(b: Array<Boolean>) {
        b.forEach { writeBoolean(it) }
    }

    /**
     * Writes a byte array to the [OutputStream]
     * @param b The byte array to write
     * @param off The offset to start writing
     * @param len The length of the array to write
     *
     * @throws IndexOutOfBoundsException If the offset or the length is out of bounds
     * @throws IllegalArgumentException If the length is negative
     */
    fun writeBooleanArray(b: Array<Boolean>, off: Int, len: Int) {
        for (i in off until off + len) {
            writeBoolean(b[i])
        }
    }

    /**
     * Writes a byte array to the [OutputStream]
     * @param b The byte array to write
     *
     * @throws IndexOutOfBoundsException If the offset or the length is out of bounds
     * @throws IllegalArgumentException If the length is negative
     */
    fun writeByteArray(b: ByteArray) {
        write(b)
    }

    /**
     * Writes a byte array to the [OutputStream]
     * @param b The byte array to write
     * @param off The offset to start writing
     * @param len The length of the array to write
     *
     * @throws IndexOutOfBoundsException If the offset or the length is out of bounds
     * @throws IllegalArgumentException If the length is negative
     */
    fun writeByteArray(b: ByteArray, off: Int, len: Int) {
        write(b, off, len)
    }

    /**
     * Writes a byte array to the [OutputStream]
     * @param b The byte array to write
     *
     * @throws IndexOutOfBoundsException If the offset or the length is out of bounds
     * @throws IllegalArgumentException If the length is negative
     */
    fun writeByteArray(b: Array<Byte>) {
        write(b.toByteArray())
    }

    /**
     * Writes a byte array to the [OutputStream]
     * @param b The byte array to write
     * @param off The offset to start writing
     * @param len The length of the array to write
     *
     * @throws IndexOutOfBoundsException If the offset or the length is out of bounds
     * @throws IllegalArgumentException If the length is negative
     */
    fun writeByteArray(b: Array<Byte>, off: Int, len: Int) {
        write(b.toByteArray(), off, len)
    }

    /**
     * Writes a shorts array to the [OutputStream]
     * @param b The shorts array to write
     */
    fun writeShortArray(b: ShortArray) {
        b.forEach { writeShort(it) }
    }

    /**
     * Writes a shorts array to the [OutputStream]
     * @param b The shorts array to write
     * @param off The offset to start writing
     * @param len The length of the array to write
     *
     * @throws IndexOutOfBoundsException If the offset or the length is out of bounds
     * @throws IllegalArgumentException If the length is negative
     */
    fun writeShortArray(b: ShortArray, off: Int, len: Int) {
        for (i in off until off + len) {
            writeShort(b[i])
        }
    }

    /**
     * Writes a shorts array to the [OutputStream]
     * @param b The shorts array to write
     */
    fun writeShortArray(b: Array<Short>) {
        b.forEach { writeShort(it) }
    }

    /**
     * Writes a shorts array to the [OutputStream]
     * @param b The shorts array to write
     * @param off The offset to start writing
     * @param len The length of the array to write
     *
     * @throws IndexOutOfBoundsException If the offset or the length is out of bounds
     * @throws IllegalArgumentException If the length is negative
     */
    fun writeShortArray(b: Array<Short>, off: Int, len: Int) {
        for (i in off until off + len) {
            writeShort(b[i])
        }
    }

    /**
     * Writes an int array to the [OutputStream]
     * @param b The int array to write
     */
    fun writeIntArray(b: IntArray) {
        b.forEach { writeInt(it) }
    }

    /**
     * Writes an int array to the [OutputStream]
     * @param b The int array to write
     * @param off The offset to start writing
     * @param len The length of the array to write
     *
     * @throws IndexOutOfBoundsException If the offset or the length is out of bounds
     * @throws IllegalArgumentException If the length is negative
     */
    fun writeIntArray(b: IntArray, off: Int, len: Int) {
        for (i in off until off + len) {
            writeInt(b[i])
        }
    }

    /**
     * Writes an int array to the [OutputStream]
     * @param b The int array to write
     */
    fun writeIntArray(b: Array<Int>) {
        b.forEach { writeInt(it) }
    }

    /**
     * Writes an int array to the [OutputStream]
     * @param b The int array to write
     * @param off The offset to start writing
     * @param len The length of the array to write
     *
     * @throws IndexOutOfBoundsException If the offset or the length is out of bounds
     * @throws IllegalArgumentException If the length is negative
     */
    fun writeIntArray(b: Array<Int>, off: Int, len: Int) {
        for (i in off until off + len) {
            writeInt(b[i])
        }
    }

    /**
     * Writes a long array to the [OutputStream]
     * @param b The long array to write
     */
    fun writeLongArray(b: LongArray) {
        b.forEach { writeLong(it) }
    }

    /**
     * Writes a long array to the [OutputStream]
     * @param b The long array to write
     * @param off The offset to start writing
     * @param len The length of the array to write
     */
    fun writeLongArray(b: LongArray, off: Int, len: Int) {
        for (i in off until off + len) {
            writeLong(b[i])
        }
    }

    /**
     * Writes a long array to the [OutputStream]
     * @param b The long array to write
     *
     * @throws IndexOutOfBoundsException If the offset or the length is out of bounds
     * @throws IllegalArgumentException If the length is negative
     * @throws NullPointerException If the array is null
     */
    fun writeLongArray(b: Array<Long>) {
        b.forEach { writeLong(it) }
    }

    /**
     * Writes a long array to the [OutputStream]
     * @param b The long array to write
     * @param off The offset to start writing
     * @param len The length of the array to write
     *
     * @throws IndexOutOfBoundsException If the offset or the length is out of bounds
     * @throws IllegalArgumentException If the length is negative
     * @throws NullPointerException If the array is null
     */
    fun writeLongArray(b: Array<Long>, off: Int, len: Int) {
        for (i in off until off + len) {
            writeLong(b[i])
        }
    }

    /**
     * Writes a float array to the [OutputStream]
     * @param b The float array to write
     *
     * @throws IndexOutOfBoundsException If the offset or the length is out of bounds
     * @throws IllegalArgumentException If the length is negative
     * @throws NullPointerException If the array is null
     */
    fun writeFloatArray(b: FloatArray) {
        b.forEach { writeFloat(it) }
    }

    /**
     * Writes a float array to the [OutputStream]
     * @param b The float array to write
     * @param off The offset to start writing
     * @param len The length of the array to write
     *
     * @throws IndexOutOfBoundsException If the offset or the length is out of bounds
     * @throws IllegalArgumentException If the length is negative
     * @throws NullPointerException If the array is null
     */
    fun writeFloatArray(b: FloatArray, off: Int, len: Int) {
        for (i in off until off + len) {
            writeFloat(b[i])
        }
    }

    /**
     * Writes a float array to the [OutputStream]
     * @param b The float array to write
     *
     * @throws IndexOutOfBoundsException If the offset or the length is out of bounds
     * @throws IllegalArgumentException If the length is negative
     * @throws NullPointerException If the array is null
     */
    fun writeFloatArray(b: Array<Float>) {
        b.forEach { writeFloat(it) }
    }

    /**
     * Writes a float array to the [OutputStream]
     * @param b The float array to write
     * @param off The offset to start writing
     * @param len The length of the array to write
     *
     * @throws IndexOutOfBoundsException If the offset or the length is out of bounds
     * @throws IllegalArgumentException If the length is negative
     * @throws NullPointerException If the array is null
     */
    fun writeFloatArray(b: Array<Float>, off: Int, len: Int) {
        for (i in off until off + len) {
            writeFloat(b[i])
        }
    }

    /**
     * Writes a double array to the [OutputStream]
     * @param b The double array to write
     *
     * @throws IndexOutOfBoundsException If the offset or the length is out of bounds
     * @throws IllegalArgumentException If the length is negative
     * @throws NullPointerException If the array is null
     */
    fun writeDoubleArray(b: DoubleArray) {
        b.forEach { writeDouble(it) }
    }

    /**
     * Writes a double array to the [OutputStream]
     * @param b The double array to write
     * @param off The offset to start writing
     * @param len The length of the array to write
     *
     * @throws IndexOutOfBoundsException If the offset or the length is out of bounds
     * @throws NullPointerException If the array is null
     * @throws IllegalArgumentException If the length is negative
     */
    fun writeDoubleArray(b: DoubleArray, off: Int, len: Int) {
        for (i in off until off + len) {
            writeDouble(b[i])
        }
    }

    /**
     * Writes a double array to the [OutputStream]
     * @param b The double array to write
     *
     * @throws IndexOutOfBoundsException If the offset or the length is out of bounds
     * @throws NullPointerException If the array is null
     * @throws IllegalArgumentException If the length is negative
     */
    fun writeDoubleArray(b: Array<Double>) {
        b.forEach { writeDouble(it) }
    }

    /**
     * Writes a double array to the [OutputStream]
     * @param b The double array to write
     * @param off The offset to start writing
     * @param len The length of the array to write
     *
     * @throws IndexOutOfBoundsException If the offset or the length is out of bounds
     * @throws NullPointerException If the array is null
     * @throws IllegalArgumentException If the length is negative
     */
    fun writeDoubleArray(b: Array<Double>, off: Int, len: Int) {
        for (i in off until off + len) {
            writeDouble(b[i])
        }
    }

    /**
     * Writes a char array to the [OutputStream]
     * @param b The char array to write
     *
     * @throws IndexOutOfBoundsException If the offset or the length is out of bounds
     * @throws NullPointerException If the array is null
     * @throws IllegalArgumentException If the length is negative
     * @throws IOException If an I/O error occurs
     */
    fun writeUnsignedByteArray(b: UByteArray) {
        b.forEach { writeUnsignedByte(it) }
    }

    /**
     * Writes a char array to the [OutputStream]
     * @param b The char array to write
     * @param off The offset to start writing
     * @param len The length of the array to write
     *
     * @throws IndexOutOfBoundsException If the offset or the length is out of bounds
     * @throws NullPointerException If the array is null
     * @throws IllegalArgumentException If the length is negative
     * @throws IOException If an I/O error occurs
     */
    fun writeUnsignedByteArray(b: UByteArray, off: Int, len: Int) {
        for (i in off until off + len) {
            writeUnsignedByte(b[i])
        }
    }

    /**
     * Writes a char array to the [OutputStream]
     * @param b The char array to write
     *
     * @throws IndexOutOfBoundsException If the offset or the length is out of bounds
     * @throws NullPointerException If the array is null
     * @throws IllegalArgumentException If the length is negative
     * @throws IOException If an I/O error occurs
     */
    fun writeUnsignedByteArray(b: Array<UByte>) {
        b.forEach { writeUnsignedByte(it) }
    }

    /**
     * Writes a char array to the [OutputStream]
     * @param b The char array to write
     * @param off The offset to start writing
     * @param len The length of the array to write
     *
     * @throws IndexOutOfBoundsException If the offset or the length is out of bounds
     * @throws NullPointerException If the array is null
     * @throws IllegalArgumentException If the length is negative
     * @throws IOException If an I/O error occurs
     */
    fun writeUnsignedByteArray(b: Array<UByte>, off: Int, len: Int) {
        for (i in off until off + len) {
            writeUnsignedByte(b[i])
        }
    }

    /**
     * Writes a char array to the [OutputStream]
     * @param b The char array to write
     *
     * @throws IndexOutOfBoundsException If the offset or the length is out of bounds
     * @throws NullPointerException If the array is null
     * @throws IOException If an I/O error occurs
     * @throws IllegalArgumentException If the length is negative
     */
    fun writeUnsignedShortArray(b: UShortArray) {
        b.forEach { writeUnsignedShort(it) }
    }

    /**
     * Writes a char array to the [OutputStream]
     * @param b The char array to write
     * @param off The offset to start writing
     * @param len The length of the array to write
     *
     * @throws NullPointerException If the array is null
     * @throws IndexOutOfBoundsException If the offset or the length is out of bounds
     * @throws IOException If an I/O error occurs
     * @throws IllegalArgumentException If the length is negative
     */
    fun writeUnsignedShortArray(b: UShortArray, off: Int, len: Int) {
        for (i in off until off + len) {
            writeUnsignedShort(b[i])
        }
    }

    /**
     * Writes a char array to the [OutputStream]
     * @param b The char array to write
     *
     * @throws NullPointerException If the array is null
     * @throws IndexOutOfBoundsException If the offset or the length is out of bounds
     * @throws IOException If an I/O error occurs
     * @throws IllegalArgumentException If the length is negative
     */
    fun writeUnsignedShortArray(b: Array<UShort>) {
        b.forEach { writeUnsignedShort(it) }
    }

    /**
     * Writes a char array to the [OutputStream]
     * @param b The char array to write
     * @param off The offset to start writing
     * @param len The length of the array to write
     *
     * @throws NullPointerException If the array is null
     * @throws IndexOutOfBoundsException If the offset or the length is out of bounds
     * @throws IOException If an I/O error occurs
     * @throws IllegalArgumentException If the length is negative
     */
    fun writeUnsignedShortArray(b: Array<UShort>, off: Int, len: Int) {
        for (i in off until off + len) {
            writeUnsignedShort(b[i])
        }
    }

    /**
     * Writes a char array to the [OutputStream]
     * @param b The char array to write
     *
     * @throws NullPointerException If the array is null
     * @throws IndexOutOfBoundsException If the offset or the length is out of bounds
     * @throws IOException If an I/O error occurs
     */
    fun writeUnsignedIntArray(b: UIntArray) {
        b.forEach { writeUnsignedInt(it) }
    }

    /**
     * Writes a char array to the [OutputStream]
     * @param b The char array to write
     * @param off The offset to start writing
     * @param len The length of the array to write
     *
     * @throws NullPointerException If the array is null
     * @throws IndexOutOfBoundsException If the offset or the length is out of bounds
     * @throws IOException If an I/O error occurs
     * @throws IllegalArgumentException If the length is negative
     */
    fun writeUnsignedIntArray(b: UIntArray, off: Int, len: Int) {
        for (i in off until off + len) {
            writeUnsignedInt(b[i])
        }
    }

    /**
     * Writes a char array to the [OutputStream]
     * @param b The char array to write
     *
     * @throws NullPointerException If the array is null
     * @throws IndexOutOfBoundsException If the offset or the length is out of bounds
     * @throws IOException If an I/O error occurs
     * @throws IllegalArgumentException If the length is negative
     */
    fun writeUnsignedIntArray(b: Array<UInt>) {
        b.forEach { writeUnsignedInt(it) }
    }

    /**
     * Writes a char array to the [OutputStream]
     * @param b The char array to write
     * @param off The offset to start writing
     * @param len The length of the array to write
     *
     * @throws NullPointerException If the array is null
     * @throws IndexOutOfBoundsException If the offset or the length is out of bounds
     * @throws IOException If an I/O error occurs
     * @throws IllegalArgumentException If the length is negative
     */
    fun writeUnsignedIntArray(b: Array<UInt>, off: Int, len: Int) {
        for (i in off until off + len) {
            writeUnsignedInt(b[i])
        }
    }

    /**
     * Writes a char array to the [OutputStream]
     * @param b The char array to write
     * @throws IOException If an I/O error occurs
     */
    fun writeUnsignedLongArray(b: ULongArray) {
        b.forEach { writeUnsignedLong(it) }
    }

    /**
     * Writes a char array to the [OutputStream]
     * @param b The char array to write
     * @param off The offset to start writing
     * @param len The length of the array to write
     * @throws IOException If an I/O error occurs
     *
     * @throws NullPointerException If the array is null
     * @throws IndexOutOfBoundsException If the offset or the length is out of bounds
     * @throws IllegalArgumentException If the length is negative
     */
    fun writeUnsignedLongArray(b: ULongArray, off: Int, len: Int) {
        for (i in off until off + len) {
            writeUnsignedLong(b[i])
        }
    }

    /**
     * Writes a char array to the [OutputStream]
     * @param b The char array to write
     * @throws IOException If an I/O error occurs
     */
    fun writeUnsignedLongArray(b: Array<ULong>) {
        b.forEach { writeUnsignedLong(it) }
    }

    /**
     * Writes a char array to the [OutputStream]
     * @param b The char array to write
     * @param off The offset to start writing
     * @param len The length of the array to write
     * @throws IOException If an I/O error occurs
     *
     * @throws NullPointerException If the array is null
     * @throws IndexOutOfBoundsException If the offset or the length is out of bounds
     * @throws IllegalArgumentException If the length is negative
     */
    fun writeUnsignedLongArray(b: Array<ULong>, off: Int, len: Int) {
        for (i in off until off + len) {
            writeUnsignedLong(b[i])
        }
    }

    /**
     * Writes a char array to the [OutputStream]
     * @param b The char array to write
     * @throws IOException If an I/O error occurs
     */
    fun writeCharArrayUTF8(b: CharArray) {
        b.forEach { writeCharUTF8(it) }
    }

    /**
     * Writes a char array to the [OutputStream]
     * @param b The char array to write
     * @param off The offset to start writing
     * @param len The length of the array to write
     * @throws IOException If an I/O error occurs
     * @throws NullPointerException If the array is null
     * @throws IndexOutOfBoundsException If the offset or the length is out of bounds
     * @throws IllegalArgumentException If the length is negative
     */
    fun writeCharArrayUTF8(b: CharArray, off: Int, len: Int) {
        for (i in off until off + len) {
            writeCharUTF8(b[i])
        }
    }

    /**
     * Writes a char array to the [OutputStream]
     * @param b The char array to write
     * @throws IOException If an I/O error occurs
     */
    fun writeCharArrayUTF8(b: Array<Char>) {
        b.forEach { writeCharUTF8(it) }
    }

    /**
     * Writes a char array to the [OutputStream]
     * @param b The char array to write
     * @param off The offset to start writing
     * @param len The length of the array to write
     * @throws IOException If an I/O error occurs
     * @throws NullPointerException If the array is null
     * @throws IndexOutOfBoundsException If the offset or the length is out of bounds
     */
    fun writeCharArrayUTF8(b: Array<Char>, off: Int, len: Int) {
        for (i in off until off + len) {
            writeCharUTF8(b[i])
        }
    }

    /**
     * Writes a char array to the [OutputStream]
     * @param b The char array to write
     * @throws IOException If an I/O error occurs
     */
    fun writeCharArray(b: CharArray) {
        b.forEach { writeCharUTF16(it) }
    }

    /**
     * Writes a char array to the [OutputStream]
     * @param b The char array to write
     * @param off The offset to start writing
     * @param len The length of the array to write
     * @throws IOException If an I/O error occurs
     * @throws NullPointerException If the array is null
     * @throws IndexOutOfBoundsException If the offset or the length is out of bounds
     *
     * @throws IllegalArgumentException If the length is negative
     */
    fun writeCharArray(b: CharArray, off: Int, len: Int) {
        for (i in off until off + len) {
            writeCharUTF16(b[i])
        }
    }

    /**
     * Writes a char array to the [OutputStream]
     * @param b The char array to write
     * @throws IOException If an I/O error occurs
     *
     * @throws NullPointerException If the array is null
     * @throws IndexOutOfBoundsException If the offset or the length is out of bounds
     *
     * @throws IllegalArgumentException If the length is negative
     */
    fun writeCharArray(b: Array<Char>) {
        b.forEach { writeCharUTF16(it) }
    }

    /**
     * Writes a char array to the [OutputStream]
     * @param b The char array to write
     * @param off The offset to start writing
     * @param len The length of the array to write
     * @throws IOException If an I/O error occurs
     * @throws NullPointerException If the array is null
     * @throws IndexOutOfBoundsException If the offset or the length is out of bounds
     *
     * @throws IllegalArgumentException If the length is negative
     */
    fun writeCharArray(b: Array<Char>, off: Int, len: Int) {
        for (i in off until off + len) {
            writeCharUTF16(b[i])
        }
    }

    /**
     * Writes a string to the [OutputStream]
     * @param str The string to write
     * @throws IOException If an I/O error occurs
     *
     * @throws NullPointerException If the string is null
     * @throws IndexOutOfBoundsException If the offset or the length is out of bounds
     *
     * @throws IllegalArgumentException If the length is negative
     */
    fun writeUTF8(str: CharSequence) {
        writeUTF8(str, 0, str.length)
    }

    /**
     * Writes a string to the [OutputStream]
     * @param str The string to write
     * @param off The offset to start writing
     * @param len The length of the string to write
     * @throws IOException If an I/O error occurs
     * @throws NullPointerException If the string is null
     * @throws IndexOutOfBoundsException If the offset or the length is out of bounds
     *
     * @throws IllegalArgumentException If the length is negative
     */
    fun writeUTF8(str: CharSequence, off: Int, len: Int) {
        val bytes = str.toBytes()
        writeUnsignedShort(len.toUShort())
        writeByteArray(bytes, off, len)
    }

    /**
     * Writes a string to the [OutputStream]
     * @param str The string to write
     * @throws IOException If an I/O error occurs
     */
    fun writeStringUTF8(str: CharSequence) {
        writeUTF8(str)
    }

    /**
     * Writes a string to the [OutputStream]
     * @param str The string to write
     * @param off The offset to start writing
     * @param len The length of the string to write
     * @throws IOException If an I/O error occurs
     * @throws NullPointerException If the string is null
     * @throws IndexOutOfBoundsException If the offset or the length is out of bounds
     *
     * @throws IllegalArgumentException If the length is negative
     */
    fun writeStringUTF8(str: CharSequence, off: Int, len: Int) {
        writeUTF8(str, off, len)
    }
}
