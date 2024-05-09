@file:Suppress("unused")

package com.shakelang.util.primitives.bytes

/**
 * Convert a byte list to a byte.
 * @throws IllegalArgumentException if the array is not of size 1
 * @return the byte
 */
fun List<Byte>.toByte(): Byte {
    if (this.size != 1) throw IllegalArgumentException("ByteArray must be of size 1, but is ${this.size}")
    return this[0]
}

/**
 * Convert a byte list to a short (little endian).
 * @throws IllegalArgumentException if the array is not of size 2
 * @return the short
 */
fun List<Byte>.toShortLE(): Short {
    if (this.size != 2) throw IllegalArgumentException("ByteArray must be of size 2, but is ${this.size}")
    return this.getShortLE(0)
}

/**
 * Convert a byte list to a short (big endian).
 * @throws IllegalArgumentException if the array is not of size 2
 * @return the short
 */
fun List<Byte>.toShortBE(): Short {
    if (this.size != 2) throw IllegalArgumentException("ByteArray must be of size 2, but is ${this.size}")
    return this.getShortBE(0)
}

/**
 * Convert a byte list to shorts.
 * @throws IllegalArgumentException if the array is not of size 2
 * @return the shorts
 */
fun List<Byte>.toShort(): Short = this.toShortBE()

/**
 * Convert a byte list to an int (little endian).
 * @throws IllegalArgumentException if the array is not of size 4
 * @return the int
 */
fun List<Byte>.toIntLE(): Int {
    if (this.size != 4) throw IllegalArgumentException("ByteArray must be of size 4, but is ${this.size}")
    return this.getIntLE(0)
}

/**
 * Convert a byte list to an int (big endian).
 * @throws IllegalArgumentException if the array is not of size 4
 * @return the int
 */
fun List<Byte>.toIntBE(): Int {
    if (this.size != 4) throw IllegalArgumentException("ByteArray must be of size 4, but is ${this.size}")
    return this.getIntBE(0)
}

/**
 * Convert a byte list to int.
 * @throws IllegalArgumentException if the array is not of size 4
 * @return the int
 */
fun List<Byte>.toInt(): Int = this.toIntBE()

/**
 * Convert a byte list to a long (little endian).
 * @throws IllegalArgumentException if the array is not of size 8
 * @return the long
 */
fun List<Byte>.toLongLE(): Long {
    if (this.size != 8) throw IllegalArgumentException("ByteArray must be of size 8, but is ${this.size}")
    return this.getLongLE(0)
}

/**
 * Convert a byte list to a long (big endian).
 * @throws IllegalArgumentException if the array is not of size 8
 * @return the long
 */
fun List<Byte>.toLongBE(): Long {
    if (this.size != 8) throw IllegalArgumentException("ByteArray must be of size 8, but is ${this.size}")
    return this.getLongBE(0)
}

/**
 * Convert a byte list to long.
 * @throws IllegalArgumentException if the array is not of size 8
 * @return the long
 */
fun List<Byte>.toLong(): Long = this.toLongBE()

/**
 * Convert a byte list to a float (little endian).
 * @throws IllegalArgumentException if the array is not of size 4
 * @return the float
 */
fun List<Byte>.toFloatLE(): Float {
    if (this.size != 4) throw IllegalArgumentException("ByteArray must be of size 4")
    return this.getFloatLE(0)
}

/**
 * Convert a byte list to a float (big endian).
 * @throws IllegalArgumentException if the array is not of size 4
 * @return the float
 */
fun List<Byte>.toFloatBE(): Float {
    if (this.size != 4) throw IllegalArgumentException("ByteArray must be of size 4")
    return this.getFloatBE(0)
}

/**
 * Convert a byte list to a float.
 * @throws IllegalArgumentException if the array is not of size 4
 * @return the float
 */
fun List<Byte>.toFloat(): Float {
    if (this.size != 4) throw IllegalArgumentException("ByteArray must be of size 4")
    return this.getFloat(0)
}

/**
 * Convert a byte list to a float.
 * @throws IllegalArgumentException if the array is not of size 4
 * @return the float
 */
fun List<Byte>.toDoubleLE(): Double {
    if (this.size != 8) throw IllegalArgumentException("ByteArray must be of size 8")
    return this.getDoubleLE(0)
}

/**
 * Convert a byte list to a float.
 * @throws IllegalArgumentException if the array is not of size 4
 * @return the float
 */
fun List<Byte>.toDoubleBE(): Double {
    if (this.size != 8) throw IllegalArgumentException("ByteArray must be of size 8")
    return this.getDouble(0)
}

/**
 * Convert a byte list to a double.
 * @throws IllegalArgumentException if the array is not of size 8
 * @return the double
 */
fun List<Byte>.toDouble(): Double = this.toDoubleBE()

/**
 * Convert a byte list to an unsigned char
 * @throws IllegalArgumentException if the array is not of size 1
 * @return the unsigned char
 */
fun List<Byte>.toUnsignedByte(): UByte = this.toByte().toUByte()

/**
 * Convert a byte list to an unsigned shorts
 * @throws IllegalArgumentException if the array is not of size 2
 * @return the unsigned shorts
 */
fun List<Byte>.toUnsignedShortLE(): UShort = this.toShortLE().toUShort()

/**
 * Convert a byte list to an unsigned shorts
 * @throws IllegalArgumentException if the array is not of size 2
 * @return the unsigned shorts
 */
fun List<Byte>.toUnsignedShortBE(): UShort = this.toShortBE().toUShort()

/**
 * Convert a byte list to an unsigned shorts
 * @throws IllegalArgumentException if the array is not of size 2
 * @return the unsigned shorts
 */
fun List<Byte>.toUnsignedShort(): UShort = this.toShort().toUShort()

/**
 * Convert a byte list to an unsigned int
 * @throws IllegalArgumentException if the array is not of size 4
 * @return the unsigned int
 */
fun List<Byte>.toUnsignedIntLE(): UInt = this.toIntLE().toUInt()

/**
 * Convert a byte list to an unsigned int
 * @throws IllegalArgumentException if the array is not of size 4
 * @return the unsigned int
 */
fun List<Byte>.toUnsignedIntBE(): UInt = this.toIntBE().toUInt()

/**
 * Convert a byte list to an unsigned int
 * @throws IllegalArgumentException if the array is not of size 4
 * @return the unsigned int
 */
fun List<Byte>.toUnsignedInt(): UInt = this.toInt().toUInt()

/**
 * Convert a byte list to an unsigned long
 * @throws IllegalArgumentException if the array is not of size 8
 * @return the unsigned long
 */
fun List<Byte>.toUnsignedLongLE(): ULong = this.toLongLE().toULong()

/**
 * Convert a byte list to an unsigned long
 * @throws IllegalArgumentException if the array is not of size 8
 * @return the unsigned long
 */
fun List<Byte>.toUnsignedLongBE(): ULong = this.toLongBE().toULong()

/**
 * Convert a byte list to an unsigned long
 * @throws IllegalArgumentException if the array is not of size 8
 * @return the unsigned long
 */
fun List<Byte>.toUnsignedLong(): ULong = this.toLong().toULong()

/**
 * Convert a byte list to an unsigned byte
 * @throws IllegalArgumentException if the array is not of size 1
 * @return the unsigned byte
 */
fun List<Byte>.toUByte(): UByte = this.toByte().toUByte()

/**
 * Convert a byte list to an unsigned short (little endian)
 * @throws IllegalArgumentException if the array is not of size 2
 * @return the unsigned short
 */
fun List<Byte>.toUShortLE(): UShort = this.toShortLE().toUShort()

/**
 * Convert a byte list to an unsigned short (big endian)
 * @throws IllegalArgumentException if the array is not of size 2
 * @return the unsigned short
 */
fun List<Byte>.toUShortBE(): UShort = this.toShortBE().toUShort()

/**
 * Convert a byte list to an unsigned short
 * @throws IllegalArgumentException if the array is not of size 2
 * @return the unsigned short
 */
fun List<Byte>.toUShort(): UShort = this.toShort().toUShort()

/**
 * Convert a byte list to an unsigned int (little endian)
 * @throws IllegalArgumentException if the array is not of size 4
 * @return the unsigned int
 */
fun List<Byte>.toUIntLE(): UInt = this.toIntLE().toUInt()

/**
 * Convert a byte list to an unsigned int (big endian)
 * @throws IllegalArgumentException if the array is not of size 4
 * @return the unsigned int
 */
fun List<Byte>.toUIntBE(): UInt = this.toIntBE().toUInt()

/**
 * Convert a byte list to an unsigned int
 * @throws IllegalArgumentException if the array is not of size 4
 * @return the unsigned int
 */
fun List<Byte>.toUInt(): UInt = this.toInt().toUInt()

/**
 * Convert a byte list to an unsigned long (little endian)
 * @throws IllegalArgumentException if the array is not of size 8
 * @return the unsigned long
 */
fun List<Byte>.toULongLE(): ULong = this.toLongLE().toULong()

/**
 * Convert a byte list to an unsigned long (big endian)
 * @throws IllegalArgumentException if the array is not of size 8
 * @return the unsigned long
 */
fun List<Byte>.toULongBE(): ULong = this.toLongBE().toULong()

/**
 * Convert a byte list to an unsigned long
 * @throws IllegalArgumentException if the array is not of size 8
 * @return the unsigned long
 */
fun List<Byte>.toULong(): ULong = this.toLong().toULong()

/**
 * Set specific bytes in a byte list to a byte list (big endian)
 * @throws IllegalArgumentException if the array is not of size 1
 * @return the byte list
 */
fun MutableList<Byte>.setBytesBE(startIndex: Int, bytes: ByteArray): List<Byte> {
    if (this.size < startIndex + bytes.size) {
        throw IllegalArgumentException("ByteArray must be of size ${startIndex + bytes.size}, but is ${this.size}")
    }
    if (startIndex < 0) throw IllegalArgumentException("startIndex must be >= 0, but is $startIndex")

    for (i in startIndex until startIndex + bytes.size) this[i] = bytes[i - startIndex]
    return this
}

/**
 * Set specific bytes in a byte list to a byte list (little endian)
 * @throws IllegalArgumentException if the array is not of size 1
 * @return the byte list
 */
fun MutableList<Byte>.setBytesLE(startIndex: Int, bytes: ByteArray): List<Byte> {
    if (this.size < startIndex + bytes.size) {
        throw IllegalArgumentException("ByteArray must be of size ${startIndex + bytes.size}, but is ${this.size}")
    }
    if (startIndex < 0) throw IllegalArgumentException("startIndex must be >= 0, but is $startIndex")

    for (i in startIndex until startIndex + bytes.size) this[i] = bytes[bytes.size - 1 - i + startIndex]
    return this
}

/**
 * Set specific bytes in a byte list to a byte list (big endian)
 * @throws IllegalArgumentException if the array is not of size 1
 * @return the byte list
 */
fun MutableList<Byte>.setBytes(startIndex: Int, bytes: ByteArray): List<Byte> = this.setBytesBE(startIndex, bytes)

/**
 * Set specific bytes in a byte list to a byte list (big endian)
 * @throws IllegalArgumentException if the array is not of size 1
 * @return the byte list
 */
fun MutableList<Byte>.setBytesBE(startIndex: Int, bytes: List<Byte>) = this.setBytesBE(startIndex, bytes.toByteArray())

/**
 * Set specific bytes in a byte list to a byte list (little endian)
 * @throws IllegalArgumentException if the array is not of size 1
 * @return the byte list
 */
fun MutableList<Byte>.setBytesLE(startIndex: Int, bytes: List<Byte>) = this.setBytesLE(startIndex, bytes.toByteArray())

/**
 * Set specific bytes in a byte list to a byte list (big endian)
 * @throws IllegalArgumentException if the array is not of size 1
 * @return the byte list
 */
fun MutableList<Byte>.setBytes(startIndex: Int, bytes: List<Byte>) = this.setBytes(startIndex, bytes.toByteArray())

/**
 * Set specific bytes in a byte list to a byte
 * @throws IllegalArgumentException if the array is not big enough
 * @return the byte list
 */
fun MutableList<Byte>.setByte(index: Int, byte: Byte): List<Byte> {
    if (this.size < index + 1) {
        throw IllegalArgumentException("ByteArray must be of size ${index + 1}, but is ${this.size}")
    }
    if (index < 0) throw IllegalArgumentException("index must be >= 0, but is $index")

    this[index] = byte
    return this
}

/**
 * Set specific bytes in a byte list to a shorts
 * @throws IllegalArgumentException if the array is not big enough
 * @return the byte list
 */
fun MutableList<Byte>.setShortLE(index: Int, short: Short): List<Byte> {
    if (this.size < index + 2) {
        throw IllegalArgumentException("ByteArray must be of size ${index + 2}, but is ${this.size}")
    }
    if (index < 0) throw IllegalArgumentException("index must be >= 0, but is $index")

    this[index] = (short.toInt() and 0xFF).toByte()
    this[index + 1] = (short.toInt() shr 8).toByte()
    return this
}

/**
 * Set specific bytes in a byte list to a shorts
 * @throws IllegalArgumentException if the array is not big enough
 * @return the byte list
 */
fun MutableList<Byte>.setShortBE(index: Int, short: Short): List<Byte> {
    if (this.size < index + 2) {
        throw IllegalArgumentException("ByteArray must be of size ${index + 2}, but is ${this.size}")
    }
    if (index < 0) throw IllegalArgumentException("index must be >= 0, but is $index")

    this[index] = (short.toInt() shr 8).toByte()
    this[index + 1] = (short.toInt() and 0xFF).toByte()
    return this
}

/**
 * Set specific bytes in a byte list to a shorts
 * @throws IllegalArgumentException if the array is not big enough
 * @return the byte list
 */
fun MutableList<Byte>.setShort(index: Int, short: Short): List<Byte> = this.setShortBE(index, short)

/**
 * Set specific bytes in a byte list to an int
 * @throws IllegalArgumentException if the array is not big enough
 * @return the byte list
 */
fun MutableList<Byte>.setIntLE(index: Int, int: Int): List<Byte> {
    if (this.size < index + 4) {
        throw IllegalArgumentException("ByteArray must be of size ${index + 4}, but is ${this.size}")
    }
    if (index < 0) throw IllegalArgumentException("index must be >= 0, but is $index")

    this[index] = (int and 0xFF).toByte()
    this[index + 1] = (int shr 8).toByte()
    this[index + 2] = (int shr 16).toByte()
    this[index + 3] = (int shr 24).toByte()
    return this
}

/**
 * Set specific bytes in a byte list to an int
 * @throws IllegalArgumentException if the array is not big enough
 * @return the byte list
 */
fun MutableList<Byte>.setIntBE(index: Int, int: Int): List<Byte> {
    if (this.size < index + 4) {
        throw IllegalArgumentException("ByteArray must be of size ${index + 4}, but is ${this.size}")
    }
    if (index < 0) throw IllegalArgumentException("index must be >= 0, but is $index")

    this[index] = (int shr 24).toByte()
    this[index + 1] = (int shr 16).toByte()
    this[index + 2] = (int shr 8).toByte()
    this[index + 3] = (int and 0xFF).toByte()
    return this
}

/**
 * Set specific bytes in a byte list to an int
 * @throws IllegalArgumentException if the array is not big enough
 * @return the byte list
 */
fun MutableList<Byte>.setInt(index: Int, int: Int): List<Byte> = this.setIntBE(index, int)

/**
 * Set specific bytes in a byte list to a long
 * @throws IllegalArgumentException if the array is not big enough
 * @return the byte list
 */
fun MutableList<Byte>.setLongLE(index: Int, long: Long): List<Byte> {
    if (this.size < index + 8) {
        throw IllegalArgumentException("ByteArray must be of size ${index + 8}, but is ${this.size}")
    }
    if (index < 0) throw IllegalArgumentException("index must be >= 0, but is $index")

    this[index] = (long and 0xFF).toByte()
    this[index + 1] = (long shr 8).toByte()
    this[index + 2] = (long shr 16).toByte()
    this[index + 3] = (long shr 24).toByte()
    this[index + 4] = (long shr 32).toByte()
    this[index + 5] = (long shr 40).toByte()
    this[index + 6] = (long shr 48).toByte()
    this[index + 7] = (long shr 56).toByte()
    return this
}

/**
 * Set specific bytes in a byte list to a long
 * @throws IllegalArgumentException if the array is not big enough
 * @return the byte list
 */
fun MutableList<Byte>.setLongBE(index: Int, long: Long): List<Byte> {
    if (this.size < index + 8) {
        throw IllegalArgumentException("ByteArray must be of size ${index + 8}, but is ${this.size}")
    }
    if (index < 0) throw IllegalArgumentException("index must be >= 0, but is $index")

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
 * Set specific bytes in a byte list to a long
 * @throws IllegalArgumentException if the array is not big enough
 * @return the byte list
 */
fun MutableList<Byte>.setLong(index: Int, long: Long): List<Byte> = this.setLongBE(index, long)

/**
 * Set specific bytes in a byte list to a float
 * @throws IllegalArgumentException if the array is not big enough
 * @return the byte list
 */
fun MutableList<Byte>.setFloatLE(index: Int, float: Float): List<Byte> {
    if (this.size < index + 4) {
        throw IllegalArgumentException("ByteArray must be of size ${index + 4}, but is ${this.size}")
    }
    if (index < 0) throw IllegalArgumentException("index must be >= 0, but is $index")

    this.setIntLE(index, float.toRawBits())
    return this
}

/**
 * Set specific bytes in a byte list to a float
 * @throws IllegalArgumentException if the array is not big enough
 * @return the byte list
 */
fun MutableList<Byte>.setFloatBE(index: Int, float: Float): List<Byte> {
    if (this.size < index + 4) {
        throw IllegalArgumentException("ByteArray must be of size ${index + 4}, but is ${this.size}")
    }
    if (index < 0) throw IllegalArgumentException("index must be >= 0, but is $index")

    this.setIntBE(index, float.toRawBits())
    return this
}

/**
 * Set specific bytes in a byte list to a float
 * @throws IllegalArgumentException if the array is not big enough
 * @return the byte list
 */
fun MutableList<Byte>.setFloat(index: Int, float: Float): List<Byte> = this.setFloatBE(index, float)

/**
 * Set specific bytes in a byte list to a double
 * @throws IllegalArgumentException if the array is not big enough
 * @return the byte list
 */
fun MutableList<Byte>.setDoubleLE(index: Int, double: Double): List<Byte> {
    if (this.size < index + 8) {
        throw IllegalArgumentException("ByteArray must be of size ${index + 8}, but is ${this.size}")
    }
    if (index < 0) throw IllegalArgumentException("index must be >= 0, but is $index")

    this.setLongLE(index, double.toRawBits())
    return this
}

/**
 * Set specific bytes in a byte list to a double
 * @throws IllegalArgumentException if the array is not big enough
 * @return the byte list
 */
fun MutableList<Byte>.setDoubleBE(index: Int, double: Double): List<Byte> {
    if (this.size < index + 8) {
        throw IllegalArgumentException("ByteArray must be of size ${index + 8}, but is ${this.size}")
    }
    if (index < 0) throw IllegalArgumentException("index must be >= 0, but is $index")

    this.setLongBE(index, double.toRawBits())
    return this
}

/**
 * Set specific bytes in a byte list to a double
 * @throws IllegalArgumentException if the array is not big enough
 * @return the byte list
 */
fun MutableList<Byte>.setDouble(index: Int, double: Double): List<Byte> = this.setDoubleBE(index, double)

/**
 * Set specific bytes in a byte list to an unsigned char
 * @throws IllegalArgumentException if the array is not big enough
 * @return the byte list
 */
fun MutableList<Byte>.setUnsignedByte(index: Int, unsignedByte: UByte) = this.setByte(index, unsignedByte.toByte())

/**
 * Set specific bytes in a byte list to an unsigned char
 * @throws IllegalArgumentException if the array is not big enough
 * @return the byte list
 */
fun MutableList<Byte>.setUByte(index: Int, unsignedByte: UByte): List<Byte> {
    if (this.size < index + 1) {
        throw IllegalArgumentException("ByteArray must be of size ${index + 1}, but is ${this.size}")
    }
    if (index < 0) throw IllegalArgumentException("index must be >= 0, but is $index")

    this[index] = unsignedByte.toByte()
    return this
}

/**
 * Set specific bytes in a byte list to an unsigned short
 * @throws IllegalArgumentException if the array is not big enough
 * @return the byte list
 */
fun MutableList<Byte>.setUShortLE(index: Int, unsignedShort: UShort): List<Byte> {
    if (this.size < index + 2) {
        throw IllegalArgumentException("ByteArray must be of size ${index + 2}, but is ${this.size}")
    }
    if (index < 0) throw IllegalArgumentException("index must be >= 0, but is $index")

    this[index] = (unsignedShort.toInt() and 0xFF).toByte()
    this[index + 1] = (unsignedShort.toInt() shr 8).toByte()
    return this
}

/**
 * Set specific bytes in a byte list to an unsigned short
 * @throws IllegalArgumentException if the array is not big enough
 * @return the byte list
 */
fun MutableList<Byte>.setUShortBE(index: Int, unsignedShort: UShort): List<Byte> {
    if (this.size < index + 2) {
        throw IllegalArgumentException("ByteArray must be of size ${index + 2}, but is ${this.size}")
    }
    if (index < 0) throw IllegalArgumentException("index must be >= 0, but is $index")

    this[index] = (unsignedShort.toInt() shr 8).toByte()
    this[index + 1] = (unsignedShort.toInt() and 0xFF).toByte()
    return this
}

/**
 * Set specific bytes in a byte list to an unsigned short
 * @throws IllegalArgumentException if the array is not big enough
 * @return the byte list
 */
fun MutableList<Byte>.setUShort(index: Int, unsignedShort: UShort): List<Byte> = this.setUShortBE(index, unsignedShort)

/**
 * Set specific bytes in a byte list to an unsigned short
 * @throws IllegalArgumentException if the array is not big enough
 * @return the byte list
 */
fun MutableList<Byte>.setUnsignedShortLE(index: Int, unsignedShort: UShort): List<Byte> = this.setUShortLE(index, unsignedShort)

/**
 * Set specific bytes in a byte list to an unsigned short
 * @throws IllegalArgumentException if the array is not big enough
 * @return the byte list
 */
fun MutableList<Byte>.setUnsignedShortBE(index: Int, unsignedShort: UShort): List<Byte> = this.setUShortBE(index, unsignedShort)

/**
 * Set specific bytes in a byte list to an unsigned short
 * @throws IllegalArgumentException if the array is not big enough
 * @return the byte list
 */
fun MutableList<Byte>.setUnsignedShort(index: Int, unsignedShort: UShort): List<Byte> = this.setUShort(index, unsignedShort)

/**
 * Set specific bytes in a byte list to an unsigned int
 * @throws IllegalArgumentException if the array is not big enough
 * @return the byte list
 */
fun MutableList<Byte>.setUIntLE(index: Int, unsignedInt: UInt): List<Byte> {
    if (this.size < index + 4) {
        throw IllegalArgumentException("ByteArray must be of size ${index + 4}, but is ${this.size}")
    }
    if (index < 0) throw IllegalArgumentException("index must be >= 0, but is $index")

    this[index] = (unsignedInt.toInt() and 0xFF).toByte()
    this[index + 1] = (unsignedInt.toInt() shr 8).toByte()
    this[index + 2] = (unsignedInt.toInt() shr 16).toByte()
    this[index + 3] = (unsignedInt.toInt() shr 24).toByte()
    return this
}

/**
 * Set specific bytes in a byte list to an unsigned int
 * @throws IllegalArgumentException if the array is not big enough
 * @return the byte list
 */
fun MutableList<Byte>.setUIntBE(index: Int, unsignedInt: UInt): List<Byte> {
    if (this.size < index + 4) {
        throw IllegalArgumentException("ByteArray must be of size ${index + 4}, but is ${this.size}")
    }
    if (index < 0) throw IllegalArgumentException("index must be >= 0, but is $index")

    this[index] = (unsignedInt.toInt() shr 24).toByte()
    this[index + 1] = (unsignedInt.toInt() shr 16).toByte()
    this[index + 2] = (unsignedInt.toInt() shr 8).toByte()
    this[index + 3] = (unsignedInt.toInt() and 0xFF).toByte()
    return this
}

/**
 * Set specific bytes in a byte list to an unsigned int
 * @throws IllegalArgumentException if the array is not big enough
 * @return the byte list
 */
fun MutableList<Byte>.setUInt(index: Int, unsignedInt: UInt): List<Byte> = this.setUIntBE(index, unsignedInt)

/**
 * Set specific bytes in a byte list to an unsigned int
 * @throws IllegalArgumentException if the array is not big enough
 * @return the byte list
 */
fun MutableList<Byte>.setUnsignedIntLE(index: Int, unsignedInt: UInt): List<Byte> = this.setUIntLE(index, unsignedInt)

/**
 * Set specific bytes in a byte list to an unsigned int
 * @throws IllegalArgumentException if the array is not big enough
 * @return the byte list
 */
fun MutableList<Byte>.setUnsignedIntBE(index: Int, unsignedInt: UInt): List<Byte> = this.setUIntBE(index, unsignedInt)

/**
 * Set specific bytes in a byte list to an unsigned int
 * @throws IllegalArgumentException if the array is not big enough
 * @return the byte list
 */
fun MutableList<Byte>.setUnsignedInt(index: Int, unsignedInt: UInt): List<Byte> = this.setUInt(index, unsignedInt)

/**
 * Set specific bytes in a byte list to an unsigned long
 * @throws IllegalArgumentException if the array is not big enough
 * @return the byte list
 */
fun MutableList<Byte>.setULongLE(index: Int, unsignedLong: ULong): List<Byte> {
    if (this.size < index + 8) {
        throw IllegalArgumentException("ByteArray must be of size ${index + 8}, but is ${this.size}")
    }
    if (index < 0) throw IllegalArgumentException("index must be >= 0, but is $index")

    this[index] = (unsignedLong.toLong() and 0xFF).toByte()
    this[index + 1] = (unsignedLong.toLong() shr 8).toByte()
    this[index + 2] = (unsignedLong.toLong() shr 16).toByte()
    this[index + 3] = (unsignedLong.toLong() shr 24).toByte()
    this[index + 4] = (unsignedLong.toLong() shr 32).toByte()
    this[index + 5] = (unsignedLong.toLong() shr 40).toByte()
    this[index + 6] = (unsignedLong.toLong() shr 48).toByte()
    this[index + 7] = (unsignedLong.toLong() shr 56).toByte()
    return this
}

/**
 * Set specific bytes in a byte list to an unsigned long
 * @throws IllegalArgumentException if the array is not big enough
 * @return the byte list
 */
fun MutableList<Byte>.setULongBE(index: Int, unsignedLong: ULong): List<Byte> {
    if (this.size < index + 8) {
        throw IllegalArgumentException("ByteArray must be of size ${index + 8}, but is ${this.size}")
    }
    if (index < 0) throw IllegalArgumentException("index must be >= 0, but is $index")

    this[index] = (unsignedLong.toLong() shr 56).toByte()
    this[index + 1] = (unsignedLong.toLong() shr 48).toByte()
    this[index + 2] = (unsignedLong.toLong() shr 40).toByte()
    this[index + 3] = (unsignedLong.toLong() shr 32).toByte()
    this[index + 4] = (unsignedLong.toLong() shr 24).toByte()
    this[index + 5] = (unsignedLong.toLong() shr 16).toByte()
    this[index + 6] = (unsignedLong.toLong() shr 8).toByte()
    this[index + 7] = (unsignedLong.toLong() and 0xFF).toByte()
    return this
}

/**
 * Set specific bytes in a byte list to an unsigned long
 * @throws IllegalArgumentException if the array is not big enough
 * @return the byte list
 */
fun MutableList<Byte>.setULong(index: Int, unsignedLong: ULong): List<Byte> = this.setULongBE(index, unsignedLong)

/**
 * Set specific bytes in a byte list to an unsigned long
 * @throws IllegalArgumentException if the array is not big enough
 * @return the byte list
 */
fun MutableList<Byte>.setUnsignedLongLE(index: Int, unsignedLong: ULong): List<Byte> = this.setULongLE(index, unsignedLong)

/**
 * Set specific bytes in a byte list to an unsigned long
 * @throws IllegalArgumentException if the array is not big enough
 * @return the byte list
 */
fun MutableList<Byte>.setUnsignedLongBE(index: Int, unsignedLong: ULong): List<Byte> = this.setULongBE(index, unsignedLong)

/**
 * Set specific bytes in a byte list to an unsigned long
 * @throws IllegalArgumentException if the array is not big enough
 * @return the byte list
 */
fun MutableList<Byte>.setUnsignedLong(index: Int, unsignedLong: ULong): List<Byte> = this.setULong(index, unsignedLong)

/**
 * Get specific byte from a byte list at a given position
 * @throws IllegalArgumentException if the array is not big enough
 * @return the byte
 */
fun List<Byte>.getByte(index: Int): Byte {
    if (this.size < index + 1) {
        throw IllegalArgumentException("ByteArray must be of size ${index + 1}, but is ${this.size}")
    }
    if (index < 0) throw IllegalArgumentException("index must be >= 0, but is $index")

    return this[index]
}

/**
 * Get specific shorts from a byte list at a given position
 * @throws IllegalArgumentException if the array is not big enough
 * @return the shorts
 */
fun List<Byte>.getShort(index: Int): Short = this.getShortBE(index)

/**
 * Get specific shorts from a byte list at a given position [little endian], so 0x01 0x02 will be 0x0201
 * @throws IllegalArgumentException if the array is not big enough
 * @return the shorts
 */
fun List<Byte>.getShortLE(index: Int): Short {
    if (this.size < index + 2) {
        throw IllegalArgumentException("ByteArray must be of size ${index + 2}, but is ${this.size}")
    }
    if (index < 0) throw IllegalArgumentException("index must be >= 0, but is $index")

    return ((this[index + 1].toUByte().toInt() shl 8) or this[index].toUByte().toInt()).toShort()
}

/**
 * Get specific shorts from a byte list at a given position [big endian], so 0x01 0x02 will be 0x0102
 * @throws IllegalArgumentException if the array is not big enough
 * @return the shorts
 */
fun List<Byte>.getShortBE(index: Int): Short {
    if (this.size < index + 2) {
        throw IllegalArgumentException("ByteArray must be of size ${index + 2}, but is ${this.size}")
    }
    if (index < 0) throw IllegalArgumentException("index must be >= 0, but is $index")

    return (
        this[index].toUByte().toInt() shl 8
            or this[index + 1].toUByte().toInt()
        ).toShort()
}

/**
 * Get specific int from a byte list at a given position [little endian], so 0x01 0x02 0x03 0x04 will be 0x04030201
 * @throws IllegalArgumentException if the array is not big enough
 * @return the int
 */
fun List<Byte>.getIntLE(index: Int): Int {
    if (this.size < index + 4) {
        throw IllegalArgumentException("ByteArray must be of size ${index + 4}, but is ${this.size}")
    }
    if (index < 0) throw IllegalArgumentException("index must be >= 0, but is $index")

    return (
        this[index + 3].toUByte().toInt() shl 8
            or this[index + 2].toUByte().toInt() shl 8
            or this[index + 1].toUByte().toInt() shl 8
            or this[index].toUByte().toInt()
        )
}

/**
 * Get specific int from a byte list at a given position [big endian], so 0x01 0x02 0x03 0x04 will be 0x01020304
 * @throws IllegalArgumentException if the array is not big enough
 * @return the int
 */
fun List<Byte>.getIntBE(index: Int): Int {
    if (this.size < index + 4) {
        throw IllegalArgumentException("ByteArray must be of size ${index + 4}, but is ${this.size}")
    }
    if (index < 0) throw IllegalArgumentException("index must be >= 0, but is $index")

    return (
        this[index].toUByte().toInt() shl 8
            or this[index + 1].toUByte().toInt() shl 8
            or this[index + 2].toUByte().toInt() shl 8
            or this[index + 3].toUByte().toInt()
        )
}

/**
 * Get specific int from a byte list at a given position
 * @throws IllegalArgumentException if the array is not big enough
 * @return the int
 */
fun List<Byte>.getInt(index: Int) = getIntBE(index)

/**
 * Get specific long from a byte list at a given position [little endian], so 0x01 0x02 0x03 0x04 0x05 0x06 0x07 0x08 will be 0x0807060504030201
 * @throws IllegalArgumentException if the array is not big enough
 * @return the long
 */
fun List<Byte>.getLongLE(index: Int): Long {
    if (this.size < index + 8) {
        throw IllegalArgumentException("ByteArray must be of size ${index + 8}, but is ${this.size}")
    }
    if (index < 0) throw IllegalArgumentException("index must be >= 0, but is $index")

    return (
        this[index + 7].toUByte().toLong() shl 8
            or this[index + 6].toUByte().toLong() shl 8
            or this[index + 5].toUByte().toLong() shl 8
            or this[index + 4].toUByte().toLong() shl 8
            or this[index + 3].toUByte().toLong() shl 8
            or this[index + 2].toUByte().toLong() shl 8
            or this[index + 1].toUByte().toLong() shl 8
            or this[index].toUByte().toLong()
        )
}

/**
 * Get specific long from a byte list at a given position [big endian], so 0x01 0x02 0x03 0x04 0x05 0x06 0x07 0x08 will be 0x0102030405060708
 * @throws IllegalArgumentException if the array is not big enough
 * @return the long
 */
fun List<Byte>.getLongBE(index: Int): Long {
    if (this.size < index + 8) {
        throw IllegalArgumentException("ByteArray must be of size ${index + 8}, but is ${this.size}")
    }
    if (index < 0) throw IllegalArgumentException("index must be >= 0, but is $index")

    return (
        this[index].toUByte().toLong() shl 8
            or this[index + 1].toUByte().toLong() shl 8
            or this[index + 2].toUByte().toLong() shl 8
            or this[index + 3].toUByte().toLong() shl 8
            or this[index + 4].toUByte().toLong() shl 8
            or this[index + 5].toUByte().toLong() shl 8
            or this[index + 6].toUByte().toLong() shl 8
            or this[index + 7].toUByte().toLong()
        )
}

/**
 * Get specific long from a byte list at a given position
 * @throws IllegalArgumentException if the array is not big enough
 * @return the long
 */
fun List<Byte>.getLong(index: Int) = getLongBE(index)

/**
 * Get specific float from a byte list at a given position
 * @throws IllegalArgumentException if the array is not big enough
 * @return the float
 */
fun List<Byte>.getFloatLE(index: Int): Float = Float.fromBits(this.getIntLE(index))

/**
 * Get specific float from a byte list at a given position
 * @throws IllegalArgumentException if the array is not big enough
 * @return the float
 */
fun List<Byte>.getFloatBE(index: Int): Float = Float.fromBits(this.getIntBE(index))

/**
 * Get specific float from a byte list at a given position
 * @throws IllegalArgumentException if the array is not big enough
 * @return the float
 */
fun List<Byte>.getFloat(index: Int): Float = this.getFloatBE(index)

/**
 * Get specific double from a byte list at a given position
 * @throws IllegalArgumentException if the array is not big enough
 * @return the double
 */
fun List<Byte>.getDoubleLE(index: Int): Double = Double.fromBits(this.getLongLE(index))

/**
 * Get specific double from a byte list at a given position
 * @throws IllegalArgumentException if the array is not big enough
 * @return the double
 */
fun List<Byte>.getDoubleBE(index: Int): Double = Double.fromBits(this.getLongBE(index))

/**
 * Get specific double from a byte list at a given position
 * @throws IllegalArgumentException if the array is not big enough
 * @return the double
 */
fun List<Byte>.getDouble(index: Int): Double = this.getDoubleBE(index)

/**
 * Get specific unsigned char from a byte list at a given position
 * @throws IllegalArgumentException if the array is not big enough
 * @return the unsigned char
 */
fun List<Byte>.getUnsignedByte(index: Int): UByte = this.getByte(index).toUByte()

/**
 * Get specific unsigned shorts from a byte list at a given position
 * @throws IllegalArgumentException if the array is not big enough
 * @return the unsigned shorts
 */
fun List<Byte>.getUnsignedShortLE(index: Int): UShort = this.getShortLE(index).toUShort()

/**
 * Get specific unsigned shorts from a byte list at a given position
 * @throws IllegalArgumentException if the array is not big enough
 * @return the unsigned shorts
 */
fun List<Byte>.getUnsignedShortBE(index: Int): UShort = this.getShortBE(index).toUShort()

/**
 * Get specific unsigned shorts from a byte list at a given position
 * @throws IllegalArgumentException if the array is not big enough
 * @return the unsigned shorts
 */
fun List<Byte>.getUnsignedShort(index: Int): UShort = this.getUnsignedShortBE(index)

/**
 * Get specific unsigned int from a byte list at a given position
 * @throws IllegalArgumentException if the array is not big enough
 * @return the unsigned int
 */
fun List<Byte>.getUnsignedIntLE(index: Int): UInt = this.getIntLE(index).toUInt()

/**
 * Get specific unsigned int from a byte list at a given position
 * @throws IllegalArgumentException if the array is not big enough
 * @return the unsigned int
 */
fun List<Byte>.getUnsignedIntBE(index: Int): UInt = this.getIntBE(index).toUInt()

/**
 * Get specific unsigned int from a byte list at a given position
 * @throws IllegalArgumentException if the array is not big enough
 * @return the unsigned int
 */
fun List<Byte>.getUnsignedInt(index: Int): UInt = this.getUnsignedIntBE(index)

/**
 * Get specific unsigned long from a byte list at a given position
 * @throws IllegalArgumentException if the array is not big enough
 * @return the unsigned long
 */
fun List<Byte>.getUnsignedLongLE(index: Int): ULong = this.getLongLE(index).toULong()

/**
 * Get specific unsigned long from a byte list at a given position
 * @throws IllegalArgumentException if the array is not big enough
 * @return the unsigned long
 */
fun List<Byte>.getUnsignedLongBE(index: Int): ULong = this.getLongBE(index).toULong()

/**
 * Get specific unsigned long from a byte list at a given position
 * @throws IllegalArgumentException if the array is not big enough
 * @return the unsigned long
 */
fun List<Byte>.getUnsignedLong(index: Int): ULong = this.getUnsignedLongBE(index)

/**
 * Get specific unsigned byte from a byte list at a given position
 * @throws IllegalArgumentException if the array is not big enough
 * @return the unsigned byte
 */
fun List<Byte>.getUByte(index: Int): UByte = this.getByte(index).toUByte()

/**
 * Get specific unsigned short from a byte list at a given position
 * @throws IllegalArgumentException if the array is not big enough
 * @return the unsigned short
 */
fun List<Byte>.getUShortLE(index: Int): UShort = this.getShortLE(index).toUShort()

/**
 * Get specific unsigned short from a byte list at a given position
 * @throws IllegalArgumentException if the array is not big enough
 * @return the unsigned short
 */
fun List<Byte>.getUShortBE(index: Int): UShort = this.getShortBE(index).toUShort()

/**
 * Get specific unsigned short from a byte list at a given position
 * @throws IllegalArgumentException if the array is not big enough
 * @return the unsigned short
 */
fun List<Byte>.getUShort(index: Int): UShort = this.getUShortBE(index)

/**
 * Get specific unsigned int from a byte list at a given position
 * @throws IllegalArgumentException if the array is not big enough
 * @return the unsigned int
 */
fun List<Byte>.getUIntLE(index: Int): UInt = this.getIntLE(index).toUInt()

/**
 * Get specific unsigned int from a byte list at a given position
 * @throws IllegalArgumentException if the array is not big enough
 * @return the unsigned int
 */
fun List<Byte>.getUIntBE(index: Int): UInt = this.getIntBE(index).toUInt()

/**
 * Get specific unsigned int from a byte list at a given position
 * @throws IllegalArgumentException if the array is not big enough
 * @return the unsigned int
 */
fun List<Byte>.getUInt(index: Int): UInt = this.getUIntBE(index)

/**
 * Get specific unsigned long from a byte list at a given position
 * @throws IllegalArgumentException if the array is not big enough
 * @return the unsigned long
 */
fun List<Byte>.getULongLE(index: Int): ULong = this.getLongLE(index).toULong()

/**
 * Get specific unsigned long from a byte list at a given position
 * @throws IllegalArgumentException if the array is not big enough
 * @return the unsigned long
 */
fun List<Byte>.getULongBE(index: Int): ULong = this.getLongBE(index).toULong()

/**
 * Get specific unsigned long from a byte list at a given position
 * @throws IllegalArgumentException if the array is not big enough
 * @return the unsigned long
 */
fun List<Byte>.getULong(index: Int): ULong = this.getULongBE(index)

/**
 * Get specific bytes from a byte list at a given position [little endian]
 * @throws IllegalArgumentException if the array is not big enough
 * @return the byte list
 */
fun List<Byte>.getBytesLE(index: Int, length: Int): List<Byte> {
    if (this.size < index + length) {
        throw IllegalArgumentException("ByteArray must be of size ${index + length}, but is ${this.size}")
    }
    if (index < 0) throw IllegalArgumentException("index must be >= 0, but is $index")

    val array = List(length) { this[index + length - it - 1] }
    return array
}

/**
 * Get specific bytes from a byte list at a given position [big endian]
 * @throws IllegalArgumentException if the array is not big enough
 * @return the byte list
 */
fun List<Byte>.getBytesBE(index: Int, length: Int): List<Byte> {
    if (this.size < index + length) {
        throw IndexOutOfBoundsException("ByteArray must be of size ${index + length}, but is ${this.size}")
    }
    return this.subList(index, index + length)
}

/**
 * Get specific bytes from a byte list at a given position
 * @throws IllegalArgumentException if the array is not big enough
 * @return the byte list
 */
fun List<Byte>.getBytes(index: Int, length: Int): List<Byte> = this.getBytesBE(index, length)

/**
 * Convert the ByteArray to a hexadecimal string
 * @return the hexadecimal string
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
 * @return the hexadecimal string
 */
fun List<Byte>.toUtf8String(): String {
    val sb = StringBuilder()
    for (b in this) {
        sb.append(b.toInt().toChar())
    }
    return sb.toString()
}

/**
 * Append bytes to a byte list from a byte
 * @param bytes the byte to append
 * @return the byte list
 */
fun MutableList<Byte>.appendByte(bytes: Byte): MutableList<Byte> {
    this.add(bytes)
    return this
}

/**
 * Append bytes to a byte list from a byte
 * @param bytes the byte to append
 * @return the byte list
 */
fun MutableList<Byte>.append(bytes: Byte) = this.appendByte(bytes)

/**
 * Append bytes to a byte list from a short (Big Endian)
 * @param bytes the short to append
 * @return the byte list
 */
fun MutableList<Byte>.appendShortBE(bytes: Short): MutableList<Byte> {
    this.add((bytes.toInt() shr 8).toByte())
    this.add((bytes.toInt() and 0xFF).toByte())
    return this
}

/**
 * Append bytes to a byte list from a short (Little Endian)
 * @param bytes the short to append
 * @return the byte list
 */
fun MutableList<Byte>.appendShortLE(bytes: Short): MutableList<Byte> {
    this.add((bytes.toInt() and 0xFF).toByte())
    this.add((bytes.toInt() shr 8).toByte())
    return this
}

/**
 * Append bytes to a byte list from a short
 * @param bytes the short to append
 * @return the byte list
 */
fun MutableList<Byte>.appendShort(bytes: Short): MutableList<Byte> = this.appendShortBE(bytes)

/**
 * Append bytes to a byte list from a short (Big Endian)
 * @param bytes the short to append
 * @return the byte list
 */
fun MutableList<Byte>.append(bytes: Short) = this.appendShortBE(bytes)

/**
 * Append bytes to a byte list from an int (Big Endian)
 * @param bytes the int to append
 * @return the byte list
 */
fun MutableList<Byte>.appendIntBE(bytes: Int): MutableList<Byte> {
    this.add((bytes shr 24).toByte())
    this.add((bytes shr 16).toByte())
    this.add((bytes shr 8).toByte())
    this.add((bytes and 0xFF).toByte())
    return this
}

/**
 * Append bytes to a byte list from an int (Little Endian)
 * @param bytes the int to append
 * @return the byte list
 */
fun MutableList<Byte>.appendIntLE(bytes: Int): MutableList<Byte> {
    this.add(bytes.toByte())
    this.add((bytes shr 8).toByte())
    this.add((bytes shr 16).toByte())
    this.add((bytes shr 24).toByte())
    return this
}

/**
 * Append bytes to a byte list from an int
 * @param bytes the int to append
 * @return the byte list
 */
fun MutableList<Byte>.appendInt(bytes: Int): MutableList<Byte> = this.appendIntBE(bytes)

/**
 * Append bytes to a byte list from an int (Big Endian)
 * @param bytes the int to append
 * @return the byte list
 */
fun MutableList<Byte>.append(bytes: Int) = this.appendIntBE(bytes)

/**
 * Append bytes to a byte list from a long (Big Endian)
 * @param bytes the long to append
 * @return the byte list
 */
fun MutableList<Byte>.appendLongBE(bytes: Long): MutableList<Byte> {
    this.add((bytes shr 56).toByte())
    this.add((bytes shr 48).toByte())
    this.add((bytes shr 40).toByte())
    this.add((bytes shr 32).toByte())
    this.add((bytes shr 24).toByte())
    this.add((bytes shr 16).toByte())
    this.add((bytes shr 8).toByte())
    this.add((bytes and 0xFF).toByte())
    return this
}

/**
 * Append bytes to a byte list from a long (Little Endian)
 * @param bytes the long to append
 * @return the byte list
 */
fun MutableList<Byte>.appendLongLE(bytes: Long): MutableList<Byte> {
    this.add((bytes and 0xFF).toByte())
    this.add((bytes shr 8).toByte())
    this.add((bytes shr 16).toByte())
    this.add((bytes shr 24).toByte())
    this.add((bytes shr 32).toByte())
    this.add((bytes shr 40).toByte())
    this.add((bytes shr 48).toByte())
    this.add((bytes shr 56).toByte())
    return this
}

/**
 * Append bytes to a byte list from a long
 * @param bytes the long to append
 * @return the byte list
 */
fun MutableList<Byte>.appendLong(bytes: Long): MutableList<Byte> = this.appendLongBE(bytes)

/**
 * Append bytes to a byte list from a long (Big Endian)
 * @param bytes the long to append
 * @return the byte list
 */
fun MutableList<Byte>.append(bytes: Long) = this.appendLongBE(bytes)

/**
 * Append bytes to a byte list from a float (Big Endian)
 * @param bytes the float to append
 * @return the byte list
 */
fun MutableList<Byte>.appendFloatBE(bytes: Float): MutableList<Byte> = this.appendIntBE(bytes.toRawBits())

/**
 * Append bytes to a byte list from a float (Little Endian)
 * @param bytes the float to append
 * @return the byte list
 */
fun MutableList<Byte>.appendFloatLE(bytes: Float): MutableList<Byte> = this.appendIntLE(bytes.toRawBits())

/**
 * Append bytes to a byte list from a float
 * @param bytes the float to append
 * @return the byte list
 */
fun MutableList<Byte>.appendFloat(bytes: Float): MutableList<Byte> = this.appendFloatBE(bytes)

/**
 * Append bytes to a byte list from a float (Big Endian)
 * @param bytes the float to append
 * @return the byte list
 */
fun MutableList<Byte>.append(bytes: Float) = this.appendFloatBE(bytes)

/**
 * Append bytes to a byte list from a double (Big Endian)
 * @param bytes the double to append
 * @return the byte list
 */
fun MutableList<Byte>.appendDoubleBE(bytes: Double): MutableList<Byte> = this.appendLongBE(bytes.toRawBits())

/**
 * Append bytes to a byte list from a double (Little Endian)
 * @param bytes the double to append
 * @return the byte list
 */
fun MutableList<Byte>.appendDoubleLE(bytes: Double): MutableList<Byte> = this.appendLongLE(bytes.toRawBits())

/**
 * Append bytes to a byte list from a double
 * @param bytes the double to append
 * @return the byte list
 */
fun MutableList<Byte>.appendDouble(bytes: Double): MutableList<Byte> = this.appendDoubleBE(bytes)

/**
 * Append bytes to a byte list from a double (Big Endian)
 * @param bytes the double to append
 * @return the byte list
 */
fun MutableList<Byte>.append(bytes: Double) = this.appendDoubleBE(bytes)

/**
 * Append bytes to a byte list from an unsigned char
 * @param bytes the unsigned char to append
 * @return the byte list
 */
fun MutableList<Byte>.appendUnsignedByte(bytes: UByte): MutableList<Byte> = this.appendByte(bytes.toByte())

/**
 * Append bytes to a byte list from an unsigned char
 * @param bytes the unsigned char to append
 * @return the byte list
 */
fun MutableList<Byte>.appendUByte(bytes: UByte): MutableList<Byte> = this.appendUnsignedByte(bytes)

/**
 * Append bytes to a byte list from an unsigned char
 * @param bytes the unsigned char to append
 * @return the byte list
 */
fun MutableList<Byte>.append(bytes: UByte): MutableList<Byte> = this.appendUnsignedByte(bytes)

/**
 * Append bytes to a byte list from an unsigned short (Big Endian)
 * @param bytes the unsigned short to append
 * @return the byte list
 */
fun MutableList<Byte>.appendUnsignedShortBE(bytes: UShort): MutableList<Byte> = this.appendShortBE(bytes.toShort())

/**
 * Append bytes to a byte list from an unsigned short (Little Endian)
 * @param bytes the unsigned short to append
 * @return the byte list
 */
fun MutableList<Byte>.appendUnsignedShortLE(bytes: UShort): MutableList<Byte> = this.appendShortLE(bytes.toShort())

/**
 * Append bytes to a byte list from an unsigned short
 * @param bytes the unsigned short to append
 * @return the byte list
 */
fun MutableList<Byte>.appendUnsignedShort(bytes: UShort): MutableList<Byte> = this.appendUnsignedShortBE(bytes)

/**
 * Append bytes to a byte list from an unsigned short (Big Endian)
 * @param bytes the unsigned short to append
 * @return the byte list
 */
fun MutableList<Byte>.appendUShortBE(bytes: UShort): MutableList<Byte> = this.appendShortBE(bytes.toShort())

/**
 * Append bytes to a byte list from an unsigned short (Little Endian)
 * @param bytes the unsigned short to append
 * @return the byte list
 */
fun MutableList<Byte>.appendUShortLE(bytes: UShort): MutableList<Byte> = this.appendShortLE(bytes.toShort())

/**
 * Append bytes to a byte list from an unsigned short
 * @param bytes the unsigned short to append
 * @return the byte list
 */
fun MutableList<Byte>.appendUShort(bytes: UShort): MutableList<Byte> = this.appendUnsignedShortBE(bytes)

/**
 * Append bytes to a byte list from an unsigned short (Big Endian)
 * @param bytes the unsigned short to append
 * @return the byte list
 */
fun MutableList<Byte>.append(bytes: UShort) = this.appendUnsignedShortBE(bytes)

/**
 * Append bytes to a byte list from an unsigned int (Big Endian)
 * @param bytes the unsigned int to append
 * @return the byte list
 */
fun MutableList<Byte>.appendUnsignedIntBE(bytes: UInt): MutableList<Byte> = this.appendIntBE(bytes.toInt())

/**
 * Append bytes to a byte list from an unsigned int (Little Endian)
 * @param bytes the unsigned int to append
 * @return the byte list
 */
fun MutableList<Byte>.appendUnsignedIntLE(bytes: UInt): MutableList<Byte> = this.appendIntLE(bytes.toInt())

/**
 * Append bytes to a byte list from an unsigned int
 * @param bytes the unsigned int to append
 * @return the byte list
 */
fun MutableList<Byte>.appendUnsignedInt(bytes: UInt): MutableList<Byte> = this.appendUnsignedIntBE(bytes)

/**
 * Append bytes to a byte list from an unsigned int (Big Endian)
 * @param bytes the unsigned int to append
 * @return the byte list
 */
fun MutableList<Byte>.appendUIntBE(bytes: UInt): MutableList<Byte> = this.appendIntBE(bytes.toInt())

/**
 * Append bytes to a byte list from an unsigned int (Little Endian)
 * @param bytes the unsigned int to append
 * @return the byte list
 */
fun MutableList<Byte>.appendUIntLE(bytes: UInt): MutableList<Byte> = this.appendIntLE(bytes.toInt())

/**
 * Append bytes to a byte list from an unsigned int
 * @param bytes the unsigned int to append
 * @return the byte list
 */
fun MutableList<Byte>.appendUInt(bytes: UInt): MutableList<Byte> = this.appendUnsignedIntBE(bytes)

/**
 * Append bytes to a byte list from an unsigned int (Big Endian)
 * @param bytes the unsigned int to append
 * @return the byte list
 */
fun MutableList<Byte>.append(bytes: UInt) = this.appendUnsignedIntBE(bytes)

/**
 * Append bytes to a byte list from an unsigned long (Big Endian)
 * @param bytes the unsigned long to append
 * @return the byte list
 */
fun MutableList<Byte>.appendUnsignedLongBE(bytes: ULong): MutableList<Byte> = this.appendLongBE(bytes.toLong())

/**
 * Append bytes to a byte list from an unsigned long (Little Endian)
 * @param bytes the unsigned long to append
 * @return the byte list
 */
fun MutableList<Byte>.appendUnsignedLongLE(bytes: ULong): MutableList<Byte> = this.appendLongLE(bytes.toLong())

/**
 * Append bytes to a byte list from an unsigned long
 * @param bytes the unsigned long to append
 * @return the byte list
 */
fun MutableList<Byte>.appendUnsignedLong(bytes: ULong): MutableList<Byte> = this.appendUnsignedLongBE(bytes)

/**
 * Append bytes to a byte list from an unsigned long (Big Endian)
 * @param bytes the unsigned long to append
 * @return the byte list
 */
fun MutableList<Byte>.appendULongBE(bytes: ULong): MutableList<Byte> = this.appendLongBE(bytes.toLong())

/**
 * Append bytes to a byte list from an unsigned long (Little Endian)
 * @param bytes the unsigned long to append
 * @return the byte list
 */
fun MutableList<Byte>.appendULongLE(bytes: ULong): MutableList<Byte> = this.appendLongLE(bytes.toLong())

/**
 * Append bytes to a byte list from an unsigned long
 * @param bytes the unsigned long to append
 * @return the byte list
 */
fun MutableList<Byte>.appendULong(bytes: ULong): MutableList<Byte> = this.appendUnsignedLongBE(bytes)

/**
 * Append bytes to a byte list from an unsigned long (Big Endian)
 * @param bytes the unsigned long to append
 * @return the byte list
 */
fun MutableList<Byte>.append(bytes: ULong) = this.appendUnsignedLongBE(bytes)

/**
 * Append bytes to a byte list from a byte list (Big Endian)
 * @param bytes the byte list to append
 * @return the byte list
 */
fun MutableList<Byte>.appendBytesBE(bytes: List<Byte>): MutableList<Byte> {
    this.addAll(bytes)
    return this
}

/**
 * Append bytes to a byte list from a byte list (Little Endian)
 * @param bytes the byte list to append
 * @return the byte list
 */
fun MutableList<Byte>.appendBytesLE(bytes: List<Byte>): MutableList<Byte> {
    this.addAll(bytes.reversed())
    return this
}

/**
 * Append bytes to a byte list from a byte list (Big Endian)
 * @param bytes the byte list to append
 * @return the byte list
 */
fun MutableList<Byte>.appendBytes(bytes: List<Byte>): MutableList<Byte> = this.appendBytesBE(bytes)

/**
 * Append bytes to a byte list from a byte list (Big Endian)
 * @param bytes the byte list to append
 * @return the byte list
 */
fun MutableList<Byte>.append(bytes: List<Byte>) = this.appendBytes(bytes)

/**
 * Append bytes to a byte list from a byte list (Big Endian)
 * @param bytes the byte list to append
 * @return the byte list
 */
fun MutableList<Byte>.appendBytesBE(bytes: ByteArray) {
    this.addAll(bytes.toList())
}

/**
 * Append bytes to a byte list from a byte list (Little Endian)
 * @param bytes the byte list to append
 * @return the byte list
 */
fun MutableList<Byte>.appendBytesLE(bytes: ByteArray) {
    this.addAll(bytes.reversed().toList())
}

/**
 * Append bytes to a byte list from a byte list
 * @param bytes the byte list to append
 * @return the byte list
 */
fun MutableList<Byte>.appendBytes(bytes: ByteArray) = this.appendBytesBE(bytes)

/**
 * Append bytes to a byte list from a byte list (Big Endian)
 * @param bytes the byte list to append
 * @return the byte list
 */
fun MutableList<Byte>.append(bytes: ByteArray) = this.appendBytes(bytes)

/**
 * Remove last bytes from a byte list at
 * @param length the length of bytes to remove
 * @return the byte list
 */
fun MutableList<Byte>.removeLastBytes(length: Int): ByteArray {
    if (this.size < length) {
        throw IllegalArgumentException("Not enough bytes to remove")
    }

    val bytes = ByteArray(length)
    for (i in (length - 1) downTo 0) {
        bytes[i] = this.removeLast()
    }
    return bytes
}

/**
 * Remove last byte from a byte list
 * @return the removed byte
 */
fun MutableList<Byte>.removeLastByte() = this.removeAt(this.size - 1)

/**
 * Remove last short from a byte list at a given position (Big Endian)
 * @return the short removed
 */
fun MutableList<Byte>.removeLastShortBE() = this.removeLastBytes(2).toShortBE()

/**
 * Remove last short from a byte list at a given position (Little Endian)
 * @return the short removed
 */
fun MutableList<Byte>.removeLastShortLE() = this.removeLastBytes(2).toShortLE()

/**
 * Remove last short from a byte list at a given position
 * @return the short removed
 */
fun MutableList<Byte>.removeLastShort() = this.removeLastShortBE()

/**
 * Remove last int from a byte list at a given position (Big Endian)
 * @return the int removed
 */
fun MutableList<Byte>.removeLastIntBE() = this.removeLastBytes(4).toIntBE()

/**
 * Remove last int from a byte list at a given position (Little Endian)
 * @return the int removed
 */
fun MutableList<Byte>.removeLastIntLE() = this.removeLastBytes(4).toIntLE()

/**
 * Remove last int from a byte list at a given position
 * @return the int removed
 */
fun MutableList<Byte>.removeLastInt() = this.removeLastIntBE()

/**
 * Remove last long from a byte list at a given position (Big Endian)
 * @return the long removed
 */
fun MutableList<Byte>.removeLastLongBE() = this.removeLastBytes(8).toLongBE()

/**
 * Remove last long from a byte list at a given position (Little Endian)
 * @return the long removed
 */
fun MutableList<Byte>.removeLastLongLE() = this.removeLastBytes(8).toLongLE()

/**
 * Remove last long from a byte list at a given position
 * @return the long removed
 */
fun MutableList<Byte>.removeLastLong() = this.removeLastLongBE()

/**
 * Remove last float from a byte list at a given position (Big Endian)
 * @return the float removed
 */
fun MutableList<Byte>.removeLastFloatBE() = this.removeLastBytes(4).toFloatBE()

/**
 * Remove last float from a byte list at a given position (Little Endian)
 * @return the float removed
 */
fun MutableList<Byte>.removeLastFloatLE() = this.removeLastBytes(4).toFloatLE()

/**
 * Remove last float from a byte list at a given position
 * @return the float removed
 */
fun MutableList<Byte>.removeLastFloat() = this.removeLastFloatBE()

/**
 * Remove last double from a byte list at a given position (Big Endian)
 * @return the double removed
 */
fun MutableList<Byte>.removeLastDoubleBE() = this.removeLastBytes(8).toDoubleBE()

/**
 * Remove last double from a byte list at a given position (Little Endian)
 * @return the double removed
 */
fun MutableList<Byte>.removeLastDoubleLE() = this.removeLastBytes(8).toDoubleLE()

/**
 * Remove last double from a byte list at a given position
 * @return the double removed
 */
fun MutableList<Byte>.removeLastDouble() = this.removeLastDoubleBE()

/**
 * Remove last unsigned byte from a byte list at a given position
 * @return the unsigned byte removed
 */
fun MutableList<Byte>.removeLastUnsignedByte() = this.removeLastByte().toUByte()

/**
 * Remove last unsigned byte from a byte list at a given position
 * @return the unsigned byte removed
 */
fun MutableList<Byte>.removeLastUByte() = this.removeLastByte().toUByte()

/**
 * Remove last unsigned short from a byte list at a given position (Big Endian)
 * @return the unsigned short removed
 */
fun MutableList<Byte>.removeLastUnsignedShortBE() = this.removeLastBytes(2).toUnsignedShortBE()

/**
 * Remove last unsigned short from a byte list at a given position (Little Endian)
 * @return the unsigned short removed
 */
fun MutableList<Byte>.removeLastUnsignedShortLE() = this.removeLastBytes(2).toUnsignedShortLE()

/**
 * Remove last unsigned short from a byte list at a given position
 * @return the unsigned short removed
 */
fun MutableList<Byte>.removeLastUnsignedShort() = this.removeLastUnsignedShortBE()

/**
 * Remove last unsigned short from a byte list at a given position (Big Endian)
 * @return the unsigned short removed
 */
fun MutableList<Byte>.removeLastUShortBE() = this.removeLastUnsignedShortBE()

/**
 * Remove last unsigned short from a byte list at a given position (Little Endian)
 * @return the unsigned short removed
 */
fun MutableList<Byte>.removeLastUShortLE() = this.removeLastUnsignedShortLE()

/**
 * Remove last unsigned short from a byte list at a given position
 * @return the unsigned short removed
 */
fun MutableList<Byte>.removeLastUShort() = this.removeLastUShortBE()

/**
 * Remove last unsigned int from a byte list at a given position (Big Endian)
 * @return the unsigned int removed
 */
fun MutableList<Byte>.removeLastUnsignedIntBE() = this.removeLastBytes(4).toUnsignedIntBE()

/**
 * Remove last unsigned int from a byte list at a given position (Little Endian)
 * @return the unsigned int removed
 */
fun MutableList<Byte>.removeLastUnsignedIntLE() = this.removeLastBytes(4).toUnsignedIntLE()

/**
 * Remove last unsigned int from a byte list at a given position
 * @return the unsigned int removed
 */
fun MutableList<Byte>.removeLastUnsignedInt() = this.removeLastUnsignedIntBE()

/**
 * Remove last unsigned int from a byte list at a given position (Big Endian)
 * @return the unsigned int removed
 */

fun MutableList<Byte>.removeLastUIntBE() = this.removeLastUnsignedIntBE()

/**
 * Remove last unsigned int from a byte list at a given position (Little Endian)
 * @return the unsigned int removed
 */
fun MutableList<Byte>.removeLastUIntLE() = this.removeLastUnsignedIntLE()

/**
 * Remove last unsigned int from a byte list at a given position
 * @return the unsigned int removed
 */
fun MutableList<Byte>.removeLastUInt() = this.removeLastUIntBE()

/**
 * Remove last unsigned long from a byte list at a given position (Big Endian)
 * @return the unsigned long removed
 */
fun MutableList<Byte>.removeLastUnsignedLongBE() = this.removeLastBytes(8).toUnsignedLongBE()

/**
 * Remove last unsigned long from a byte list at a given position (Little Endian)
 * @return the unsigned long removed
 */
fun MutableList<Byte>.removeLastUnsignedLongLE() = this.removeLastBytes(8).toUnsignedLongLE()

/**
 * Remove last unsigned long from a byte list at a given position
 * @return the unsigned long removed
 */
fun MutableList<Byte>.removeLastUnsignedLong() = this.removeLastUnsignedLongBE()

/**
 * Remove last unsigned long from a byte list at a given position (Big Endian)
 * @return the unsigned long removed
 */
fun MutableList<Byte>.removeLastULongBE() = this.removeLastUnsignedLongBE()

/**
 * Remove last unsigned long from a byte list at a given position (Little Endian)
 * @return the unsigned long removed
 */
fun MutableList<Byte>.removeLastULongLE() = this.removeLastUnsignedLongLE()

/**
 * Remove last unsigned long from a byte list at a given position
 * @return the unsigned long removed
 */
fun MutableList<Byte>.removeLastULong() = this.removeLastULongBE()
