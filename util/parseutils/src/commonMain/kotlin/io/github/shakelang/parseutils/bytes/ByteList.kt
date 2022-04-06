@file:Suppress("nothing_to_inline", "unused")
package io.github.shakelang.parseutils.bytes

import io.github.shakelang.parseutils.streaming.input.ByteArrayInputStream
import io.github.shakelang.parseutils.streaming.input.CountingInputStream
import io.github.shakelang.parseutils.streaming.input.DataInputStream

/**
 * Convert a byte array to a byte.
 */
fun List<Byte>.toByte(): Byte {
    if(this.size != 1) throw IllegalArgumentException("ByteArray must be of size 1, but is ${this.size}")
    return this[0]
}

/**
 * Convert a byte array to short.
 */
fun List<Byte>.toShort(): Short {
    if(this.size != 2) throw IllegalArgumentException("ByteArray must be of size 2, but is ${this.size}")
    return this.getShort(0)
}

/**
 * Convert a byte array to int.
 */
fun List<Byte>.toInt(): Int {
    if(this.size != 4) throw IllegalArgumentException("ByteArray must be of size 4, but is ${this.size}")
    return this.getInt(0)
}

/**
 * Convert a byte array to long.
 */
fun List<Byte>.toLong(): Long {
    if(this.size != 8) throw IllegalArgumentException("ByteArray must be of size 8, but is ${this.size}")
    return this.getLong(0)
}

/**
 * Convert a byte array to a float.
 */
fun List<Byte>.toFloat(): Float {
    if(this.size != 4) throw IllegalArgumentException("ByteArray must be of size 4")
    return this.getFloat(0)
}

/**
 * Convert a byte array to a double.
 */
fun List<Byte>.toDouble(): Double {
    if(this.size != 8) throw IllegalArgumentException("ByteArray must be of size 8")
    return this.getDouble(0)
}

/**
 * Convert a byte array to an unsigned char
 */
fun List<Byte>.toUnsignedByte(): UByte = this.toByte().toUByte()

/**
 * Convert a byte array to an unsigned short
 */
fun List<Byte>.toUnsignedShort(): UShort = this.toShort().toUShort()

/**
 * Convert a byte array to an unsigned int
 */
fun List<Byte>.toUnsignedInt(): UInt = this.toInt().toUInt()

/**
 * Convert a byte array to an unsigned long
 */
fun List<Byte>.toUnsignedLong(): ULong = this.toLong().toULong()


/**
 * Set specific bytes in a byte array to a byte array
 */
fun MutableList<Byte>.setBytes(startIndex: Int, bytes: ByteArray): MutableList<Byte> {
    for (i in startIndex until startIndex + bytes.size) this[i] = bytes[i - startIndex]
    return this
}

/**
 * Set specific bytes in a byte array to a byte
 */
fun MutableList<Byte>.setByte(index: Int, byte: Byte): MutableList<Byte> {
    this[index] = byte
    return this
}

/**
 * Set specific bytes in a byte array to a short
 */
fun MutableList<Byte>.setShort(index: Int, short: Short): MutableList<Byte> {
    this[index] = (short.toInt() shr 8).toByte()
    this[index + 1] = (short.toInt() and 0xFF).toByte()
    return this
}


/**
 * Set specific bytes in a byte array to an int
 */
fun MutableList<Byte>.setInt(index: Int, int: Int): MutableList<Byte> {
    this[index] = (int shr 24).toByte()
    this[index + 1] = (int shr 16).toByte()
    this[index + 2] = (int shr 8).toByte()
    this[index + 3] = (int and 0xFF).toByte()
    return this
}

/**
 * Set specific bytes in a byte array to a long
 */
fun MutableList<Byte>.setLong(index: Int, long: Long): MutableList<Byte> {
    this[index] = (long shr 56).toByte()
    this[index + 1] = (long shr 48).toByte()
    this[index + 2] = (long shr 40).toByte()
    this[index + 3] = (long shr 32).toByte()
    this[index + 4] = (long shr 24).toByte()
    this[index + 5] = (long shr 16).toByte()
    this[index + 6] = (long shr 8).toByte()
    this[index + 7] = (long and 0xFF).toByte()
    return this
}


/**
 * Set specific bytes in a byte array to a float
 */
fun MutableList<Byte>.setFloat(index: Int, float: Float): MutableList<Byte> {
    this.setInt(index, float.toBits())
    return this
}

/**
 * Set specific bytes in a byte array to a double
 */
fun MutableList<Byte>.setDouble(index: Int, double: Double): MutableList<Byte> {
    this.setLong(index, double.toBits())
    return this
}

/**
 * Set specific bytes in a byte array to an unsigned char
 */
fun MutableList<Byte>.setUnsignedByte(index: Int, unsignedByte: UByte): MutableList<Byte> {
    this[index] = unsignedByte.toByte()
    return this
}

/**
 * Set specific bytes in a byte array to an unsigned short
 */
fun MutableList<Byte>.setUnsignedShort(index: Int, unsignedShort: UShort): MutableList<Byte> {
    this[index] = (unsignedShort.toInt() shr 8).toByte()
    this[index + 1] = (unsignedShort.toInt() and 0xFF).toByte()
    return this
}

/**
 * Set specific bytes in a byte array to an unsigned int
 */
fun MutableList<Byte>.setUnsignedInt(index: Int, unsignedInt: UInt): MutableList<Byte> {
    this[index] = (unsignedInt shr 24).toByte()
    this[index + 1] = (unsignedInt shr 16).toByte()
    this[index + 2] = (unsignedInt shr 8).toByte()
    this[index + 3] = (unsignedInt and 0xFFu).toByte()
    return this
}

/**
 * Set specific bytes in a byte array to an unsigned long
 */
fun MutableList<Byte>.setUnsignedLong(index: Int, unsignedLong: ULong): MutableList<Byte> {
    this[index] = (unsignedLong shr 56).toByte()
    this[index + 1] = (unsignedLong shr 48).toByte()
    this[index + 2] = (unsignedLong shr 40).toByte()
    this[index + 3] = (unsignedLong shr 32).toByte()
    this[index + 4] = (unsignedLong shr 24).toByte()
    this[index + 5] = (unsignedLong shr 16).toByte()
    this[index + 6] = (unsignedLong shr 8).toByte()
    this[index + 7] = (unsignedLong and 0xFFu).toByte()
    return this
}


/**
 * Get specific byte from a byte array at a given position
 */
fun List<Byte>.getByte(index: Int): Byte = this[index]

/**
 * Get specific short from a byte array at a given position
 */
fun List<Byte>.getShort(index: Int): Short = shortOf(this[index], this[index + 1])

/**
 * Get specific int from a byte array at a given position
 */
fun List<Byte>.getInt(index: Int): Int = intOf(this[index], this[index + 1], this[index + 2], this[index + 3])

/**
 * Get specific long from a byte array at a given position
 */
fun List<Byte>.getLong(index: Int): Long = longOf(this[index], this[index + 1], this[index + 2], this[index + 3],
        this[index + 4], this[index + 5], this[index + 6], this[index + 7])

/**
 * Get specific float from a byte array at a given position
 */
fun List<Byte>.getFloat(index: Int): Float = Float.fromBits(this.getInt(index))

/**
 * Get specific double from a byte array at a given position
 */
fun List<Byte>.getDouble(index: Int): Double = Double.fromBits(this.getLong(index))

/**
 * Get specific unsigned char from a byte array at a given position
 */
fun List<Byte>.getUnsignedByte(index: Int): UByte = this[index].toUByte()

/**
 * Get specific unsigned short from a byte array at a given position
 */
fun List<Byte>.getUnsignedShort(index: Int): UShort = unsignedShortOf(this[index], this[index + 1])

/**
 * Get specific unsigned int from a byte array at a given position
 */
fun List<Byte>.getUnsignedInt(index: Int): UInt =
    unsignedIntOf(this[index], this[index + 1], this[index + 2], this[index + 3])

/**
 * Get specific unsigned long from a byte array at a given position
 */
fun List<Byte>.getUnsignedLong(index: Int): ULong = unsignedLongOf(this[index], this[index + 1], this[index + 2],
    this[index + 3], this[index + 4], this[index + 5], this[index + 6], this[index + 7])

inline fun MutableList<Byte>.removeLastByte(): Byte = this.removeLast()
inline fun MutableList<Byte>.removeLastShort(): Short {
    val bytes = ByteArray(2) { this.removeLast() }
    return shortOf(bytes[1], bytes[0])
}
inline fun MutableList<Byte>.removeLastInt(): Int {
    val bytes = ByteArray(4) { this.removeLast() }
    return intOf(bytes[3], bytes[2], bytes[1], bytes[0])
}
inline fun MutableList<Byte>.removeLastLong(): Long {
    val bytes = ByteArray(8) { this.removeLast() }
    return longOf(bytes[7], bytes[6], bytes[5], bytes[4], bytes[3], bytes[2], bytes[1], bytes[0])
}
inline fun MutableList<Byte>.removeLastFloat(): Float {
    val bytes = ByteArray(4) { this.removeLast() }
    return floatOf(bytes[3], bytes[2], bytes[1], bytes[0])
}
inline fun MutableList<Byte>.removeLastDouble(): Double {
    val bytes = ByteArray(4) { this.removeLast() }
    return doubleOf(bytes[7], bytes[6], bytes[5], bytes[4], bytes[3], bytes[2], bytes[1], bytes[0])
}

inline fun MutableList<Byte>.removeLastUnsignedByte(): UByte = this.removeLast().toUByte()
inline fun MutableList<Byte>.removeLastUnsignedShort(): UShort {
    val bytes = ByteArray(2) { this.removeLast() }
    return unsignedShortOf(bytes[1], bytes[0])
}
inline fun MutableList<Byte>.removeLastUnsignedInt(): UInt {
    val bytes = ByteArray(4) { this.removeLast() }
    return unsignedIntOf(bytes[3], bytes[2], bytes[1], bytes[0])
}
inline fun MutableList<Byte>.removeLastUnsignedLong(): ULong {
    val bytes = ByteArray(4) { this.removeLast() }
    return unsignedLongOf(bytes[7], bytes[6], bytes[5], bytes[4], bytes[3], bytes[2], bytes[1], bytes[0])
}


/**
 * Get specific bytes from a byte array at a given position
 */
fun List<Byte>.getBytes(index: Int, length: Int): ByteArray = this.subList(index, index + length).toByteArray()

/**
 * Convert the ByteArray to a hexadecimal string
 */
fun List<Byte>.toHexString(): String {
    val sb = StringBuilder()
    for (b in this) {
        val i = b.toUByte().toString(16)
        if (i.length == 1) sb.append("0")
        sb.append(i)
    }
    return sb.toString()
}

/**
 * Convert the Bytes from the ByteArray to String
 */
fun List<Byte>.toUtf8String(): String {
    val sb = StringBuilder()
    for (b in this) {
        sb.append(b.toInt().toChar())
    }
    return sb.toString()
}

/**
 * Byte array to ByteArrayInputStream
 */
fun List<Byte>.inputStream(): ByteArrayInputStream = ByteArrayInputStream(this.toByteArray())

/**
 * Byte array to DataInputStream
 */
fun List<Byte>.dataStream(): DataInputStream = DataInputStream(this.inputStream())

/**
 * Byte array to CountingInputStream
 */
fun List<Byte>.countingStream(): CountingInputStream = CountingInputStream(this.inputStream())

