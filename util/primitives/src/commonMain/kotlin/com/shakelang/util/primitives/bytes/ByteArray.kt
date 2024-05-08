@file:Suppress("unused")

package com.shakelang.util.primitives.bytes

/**
 * Convert a byte array to a byte.
 * @throws IllegalArgumentException if the array is not of size 1
 * @return the byte
 */
fun ByteArray.toByte(): Byte {
    if (this.size != 1) throw IllegalArgumentException("ByteArray must be of size 1, but is ${this.size}")
    return this[0]
}

/**
 * Convert a byte array to a short (little endian).
 * @throws IllegalArgumentException if the array is not of size 2
 * @return the short
 */
fun ByteArray.toShortLE(): Short {
    if (this.size != 2) throw IllegalArgumentException("ByteArray must be of size 2, but is ${this.size}")
    return this.getShortLE(0)
}

/**
 * Convert a byte array to a short (big endian).
 * @throws IllegalArgumentException if the array is not of size 2
 * @return the short
 */
fun ByteArray.toShortBE(): Short {
    if (this.size != 2) throw IllegalArgumentException("ByteArray must be of size 2, but is ${this.size}")
    return this.getShortBE(0)
}

/**
 * Convert a byte array to shorts.
 * @throws IllegalArgumentException if the array is not of size 2
 * @return the shorts
 */
fun ByteArray.toShort(): Short = this.toShortBE()

/**
 * Convert a byte array to an int (little endian).
 * @throws IllegalArgumentException if the array is not of size 4
 * @return the int
 */
fun ByteArray.toIntLE(): Int {
    if (this.size != 4) throw IllegalArgumentException("ByteArray must be of size 4, but is ${this.size}")
    return this.getIntLE(0)
}

/**
 * Convert a byte array to an int (big endian).
 * @throws IllegalArgumentException if the array is not of size 4
 * @return the int
 */
fun ByteArray.toIntBE(): Int {
    if (this.size != 4) throw IllegalArgumentException("ByteArray must be of size 4, but is ${this.size}")
    return this.getIntBE(0)
}

/**
 * Convert a byte array to int.
 * @throws IllegalArgumentException if the array is not of size 4
 * @return the int
 */
fun ByteArray.toInt(): Int = this.toIntBE()

/**
 * Convert a byte array to a long (little endian).
 * @throws IllegalArgumentException if the array is not of size 8
 * @return the long
 */
fun ByteArray.toLongLE(): Long {
    if (this.size != 8) throw IllegalArgumentException("ByteArray must be of size 8, but is ${this.size}")
    return this.getLongLE(0)
}

/**
 * Convert a byte array to a long (big endian).
 * @throws IllegalArgumentException if the array is not of size 8
 * @return the long
 */
fun ByteArray.toLongBE(): Long {
    if (this.size != 8) throw IllegalArgumentException("ByteArray must be of size 8, but is ${this.size}")
    return this.getLongBE(0)
}

/**
 * Convert a byte array to long.
 * @throws IllegalArgumentException if the array is not of size 8
 * @return the long
 */
fun ByteArray.toLong(): Long = this.toLongBE()

/**
 * Convert a byte array to a float (little endian).
 * @throws IllegalArgumentException if the array is not of size 4
 * @return the float
 */
fun ByteArray.toFloatLE(): Float {
    if (this.size != 4) throw IllegalArgumentException("ByteArray must be of size 4")
    return this.getFloatLE(0)
}

/**
 * Convert a byte array to a float (big endian).
 * @throws IllegalArgumentException if the array is not of size 4
 * @return the float
 */
fun ByteArray.toFloatBE(): Float {
    if (this.size != 4) throw IllegalArgumentException("ByteArray must be of size 4")
    return this.getFloatBE(0)
}

/**
 * Convert a byte array to a float.
 * @throws IllegalArgumentException if the array is not of size 4
 * @return the float
 */
fun ByteArray.toFloat(): Float {
    if (this.size != 4) throw IllegalArgumentException("ByteArray must be of size 4")
    return this.getFloat(0)
}

/**
 * Convert a byte array to a float.
 * @throws IllegalArgumentException if the array is not of size 4
 * @return the float
 */
fun ByteArray.toDoubleLE(): Double {
    if (this.size != 8) throw IllegalArgumentException("ByteArray must be of size 8")
    return this.getDoubleLE(0)
}

/**
 * Convert a byte array to a float.
 * @throws IllegalArgumentException if the array is not of size 4
 * @return the float
 */
fun ByteArray.toDoubleBE(): Double {
    if (this.size != 8) throw IllegalArgumentException("ByteArray must be of size 8")
    return this.getDouble(0)
}

/**
 * Convert a byte array to a double.
 * @throws IllegalArgumentException if the array is not of size 8
 * @return the double
 */
fun ByteArray.toDouble(): Double = this.toDoubleBE()

/**
 * Convert a byte array to an unsigned char
 * @throws IllegalArgumentException if the array is not of size 1
 * @return the unsigned char
 */
fun ByteArray.toUnsignedByte(): UByte = this.toByte().toUByte()

/**
 * Convert a byte array to an unsigned shorts
 * @throws IllegalArgumentException if the array is not of size 2
 * @return the unsigned shorts
 */
fun ByteArray.toUnsignedShortLE(): UShort = this.toShortLE().toUShort()

/**
 * Convert a byte array to an unsigned shorts
 * @throws IllegalArgumentException if the array is not of size 2
 * @return the unsigned shorts
 */
fun ByteArray.toUnsignedShortBE(): UShort = this.toShortBE().toUShort()

/**
 * Convert a byte array to an unsigned shorts
 * @throws IllegalArgumentException if the array is not of size 2
 * @return the unsigned shorts
 */
fun ByteArray.toUnsignedShort(): UShort = this.toShort().toUShort()

/**
 * Convert a byte array to an unsigned int
 * @throws IllegalArgumentException if the array is not of size 4
 * @return the unsigned int
 */
fun ByteArray.toUnsignedIntLE(): UInt = this.toIntLE().toUInt()

/**
 * Convert a byte array to an unsigned int
 * @throws IllegalArgumentException if the array is not of size 4
 * @return the unsigned int
 */
fun ByteArray.toUnsignedIntBE(): UInt = this.toIntBE().toUInt()

/**
 * Convert a byte array to an unsigned int
 * @throws IllegalArgumentException if the array is not of size 4
 * @return the unsigned int
 */
fun ByteArray.toUnsignedInt(): UInt = this.toInt().toUInt()

/**
 * Convert a byte array to an unsigned long
 * @throws IllegalArgumentException if the array is not of size 8
 * @return the unsigned long
 */
fun ByteArray.toUnsignedLongLE(): ULong = this.toLongLE().toULong()

/**
 * Convert a byte array to an unsigned long
 * @throws IllegalArgumentException if the array is not of size 8
 * @return the unsigned long
 */
fun ByteArray.toUnsignedLongBE(): ULong = this.toLongBE().toULong()

/**
 * Convert a byte array to an unsigned long
 * @throws IllegalArgumentException if the array is not of size 8
 * @return the unsigned long
 */
fun ByteArray.toUnsignedLong(): ULong = this.toLong().toULong()

/**
 * Convert a byte array to an unsigned byte
 * @throws IllegalArgumentException if the array is not of size 1
 * @return the unsigned byte
 */
fun ByteArray.toUByte(): UByte = this.toByte().toUByte()

/**
 * Convert a byte array to an unsigned short (little endian)
 * @throws IllegalArgumentException if the array is not of size 2
 * @return the unsigned short
 */
fun ByteArray.toUShortLE(): UShort = this.toShortLE().toUShort()

/**
 * Convert a byte array to an unsigned short (big endian)
 * @throws IllegalArgumentException if the array is not of size 2
 * @return the unsigned short
 */
fun ByteArray.toUShortBE(): UShort = this.toShortBE().toUShort()

/**
 * Convert a byte array to an unsigned short
 * @throws IllegalArgumentException if the array is not of size 2
 * @return the unsigned short
 */
fun ByteArray.toUShort(): UShort = this.toShort().toUShort()

/**
 * Convert a byte array to an unsigned int (little endian)
 * @throws IllegalArgumentException if the array is not of size 4
 * @return the unsigned int
 */
fun ByteArray.toUIntLE(): UInt = this.toIntLE().toUInt()

/**
 * Convert a byte array to an unsigned int (big endian)
 * @throws IllegalArgumentException if the array is not of size 4
 * @return the unsigned int
 */
fun ByteArray.toUIntBE(): UInt = this.toIntBE().toUInt()

/**
 * Convert a byte array to an unsigned int
 * @throws IllegalArgumentException if the array is not of size 4
 * @return the unsigned int
 */
fun ByteArray.toUInt(): UInt = this.toInt().toUInt()

/**
 * Convert a byte array to an unsigned long (little endian)
 * @throws IllegalArgumentException if the array is not of size 8
 * @return the unsigned long
 */
fun ByteArray.toULongLE(): ULong = this.toLongLE().toULong()

/**
 * Convert a byte array to an unsigned long (big endian)
 * @throws IllegalArgumentException if the array is not of size 8
 * @return the unsigned long
 */
fun ByteArray.toULongBE(): ULong = this.toLongBE().toULong()

/**
 * Convert a byte array to an unsigned long
 * @throws IllegalArgumentException if the array is not of size 8
 * @return the unsigned long
 */
fun ByteArray.toULong(): ULong = this.toLong().toULong()

/**
 * Set specific bytes in a byte array to a byte array (big endian)
 * @throws IllegalArgumentException if the array is not of size 1
 * @return the byte array
 */
fun ByteArray.setBytesBE(startIndex: Int, bytes: ByteArray): ByteArray {
    if (this.size < startIndex + bytes.size) {
        throw IllegalArgumentException("ByteArray must be of size ${startIndex + bytes.size}, but is ${this.size}")
    }
    if (startIndex < 0) throw IllegalArgumentException("startIndex must be >= 0, but is $startIndex")

    for (i in startIndex until startIndex + bytes.size) this[i] = bytes[i - startIndex]
    return this
}

/**
 * Set specific bytes in a byte array to a byte array (little endian)
 * @throws IllegalArgumentException if the array is not of size 1
 * @return the byte array
 */
fun ByteArray.setBytesLE(startIndex: Int, bytes: ByteArray): ByteArray {
    if (this.size < startIndex + bytes.size) {
        throw IllegalArgumentException("ByteArray must be of size ${startIndex + bytes.size}, but is ${this.size}")
    }
    if (startIndex < 0) throw IllegalArgumentException("startIndex must be >= 0, but is $startIndex")

    for (i in startIndex until startIndex + bytes.size) this[i] = bytes[bytes.size - 1 - i + startIndex]
    return this
}

/**
 * Set specific bytes in a byte array to a byte array (big endian)
 * @throws IllegalArgumentException if the array is not of size 1
 * @return the byte array
 */
fun ByteArray.setBytes(startIndex: Int, bytes: ByteArray): ByteArray = this.setBytesBE(startIndex, bytes)

/**
 * Set specific bytes in a byte array to a byte
 * @throws IllegalArgumentException if the array is not big enough
 * @return the byte array
 */
fun ByteArray.setByte(index: Int, byte: Byte): ByteArray {
    if (this.size < index + 1) {
        throw IllegalArgumentException("ByteArray must be of size ${index + 1}, but is ${this.size}")
    }
    if (index < 0) throw IllegalArgumentException("index must be >= 0, but is $index")

    this[index] = byte
    return this
}

/**
 * Set specific bytes in a byte array to a shorts
 * @throws IllegalArgumentException if the array is not big enough
 * @return the byte array
 */
fun ByteArray.setShortLE(index: Int, short: Short): ByteArray {
    if (this.size < index + 2) {
        throw IllegalArgumentException("ByteArray must be of size ${index + 2}, but is ${this.size}")
    }
    if (index < 0) throw IllegalArgumentException("index must be >= 0, but is $index")

    this[index] = (short.toInt() and 0xFF).toByte()
    this[index + 1] = (short.toInt() shr 8).toByte()
    return this
}

/**
 * Set specific bytes in a byte array to a shorts
 * @throws IllegalArgumentException if the array is not big enough
 * @return the byte array
 */
fun ByteArray.setShortBE(index: Int, short: Short): ByteArray {
    if (this.size < index + 2) {
        throw IllegalArgumentException("ByteArray must be of size ${index + 2}, but is ${this.size}")
    }
    if (index < 0) throw IllegalArgumentException("index must be >= 0, but is $index")

    this[index] = (short.toInt() shr 8).toByte()
    this[index + 1] = (short.toInt() and 0xFF).toByte()
    return this
}

/**
 * Set specific bytes in a byte array to a shorts
 * @throws IllegalArgumentException if the array is not big enough
 * @return the byte array
 */
fun ByteArray.setShort(index: Int, short: Short): ByteArray = this.setShortBE(index, short)

/**
 * Set specific bytes in a byte array to an int
 * @throws IllegalArgumentException if the array is not big enough
 * @return the byte array
 */
fun ByteArray.setIntLE(index: Int, int: Int): ByteArray {
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
 * Set specific bytes in a byte array to an int
 * @throws IllegalArgumentException if the array is not big enough
 * @return the byte array
 */
fun ByteArray.setIntBE(index: Int, int: Int): ByteArray {
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
 * Set specific bytes in a byte array to an int
 * @throws IllegalArgumentException if the array is not big enough
 * @return the byte array
 */
fun ByteArray.setInt(index: Int, int: Int): ByteArray = this.setIntBE(index, int)

/**
 * Set specific bytes in a byte array to a long
 * @throws IllegalArgumentException if the array is not big enough
 * @return the byte array
 */
fun ByteArray.setLongLE(index: Int, long: Long): ByteArray {
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
 * Set specific bytes in a byte array to a long
 * @throws IllegalArgumentException if the array is not big enough
 * @return the byte array
 */
fun ByteArray.setLongBE(index: Int, long: Long): ByteArray {
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
 * Set specific bytes in a byte array to a long
 * @throws IllegalArgumentException if the array is not big enough
 * @return the byte array
 */
fun ByteArray.setLong(index: Int, long: Long): ByteArray = this.setLongBE(index, long)

/**
 * Set specific bytes in a byte array to a float
 * @throws IllegalArgumentException if the array is not big enough
 * @return the byte array
 */
fun ByteArray.setFloatLE(index: Int, float: Float): ByteArray {
    if (this.size < index + 4) {
        throw IllegalArgumentException("ByteArray must be of size ${index + 4}, but is ${this.size}")
    }
    if (index < 0) throw IllegalArgumentException("index must be >= 0, but is $index")

    this.setIntLE(index, float.toRawBits())
    return this
}

/**
 * Set specific bytes in a byte array to a float
 * @throws IllegalArgumentException if the array is not big enough
 * @return the byte array
 */
fun ByteArray.setFloatBE(index: Int, float: Float): ByteArray {
    if (this.size < index + 4) {
        throw IllegalArgumentException("ByteArray must be of size ${index + 4}, but is ${this.size}")
    }
    if (index < 0) throw IllegalArgumentException("index must be >= 0, but is $index")

    this.setIntBE(index, float.toRawBits())
    return this
}

/**
 * Set specific bytes in a byte array to a float
 * @throws IllegalArgumentException if the array is not big enough
 * @return the byte array
 */
fun ByteArray.setFloat(index: Int, float: Float): ByteArray = this.setFloatBE(index, float)

/**
 * Set specific bytes in a byte array to a double
 * @throws IllegalArgumentException if the array is not big enough
 * @return the byte array
 */
fun ByteArray.setDoubleLE(index: Int, double: Double): ByteArray {
    if (this.size < index + 8) {
        throw IllegalArgumentException("ByteArray must be of size ${index + 8}, but is ${this.size}")
    }
    if (index < 0) throw IllegalArgumentException("index must be >= 0, but is $index")

    this.setLongLE(index, double.toRawBits())
    return this
}

/**
 * Set specific bytes in a byte array to a double
 * @throws IllegalArgumentException if the array is not big enough
 * @return the byte array
 */
fun ByteArray.setDoubleBE(index: Int, double: Double): ByteArray {
    if (this.size < index + 8) {
        throw IllegalArgumentException("ByteArray must be of size ${index + 8}, but is ${this.size}")
    }
    if (index < 0) throw IllegalArgumentException("index must be >= 0, but is $index")

    this.setLongBE(index, double.toRawBits())
    return this
}

/**
 * Set specific bytes in a byte array to a double
 * @throws IllegalArgumentException if the array is not big enough
 * @return the byte array
 */
fun ByteArray.setDouble(index: Int, double: Double): ByteArray = this.setDoubleBE(index, double)

/**
 * Set specific bytes in a byte array to an unsigned char
 * @throws IllegalArgumentException if the array is not big enough
 * @return the byte array
 */
fun ByteArray.setUnsignedByte(index: Int, unsignedByte: UByte) = this.setByte(index, unsignedByte.toByte())

/**
 * Set specific bytes in a byte array to an unsigned char
 * @throws IllegalArgumentException if the array is not big enough
 * @return the byte array
 */
fun ByteArray.setUByte(index: Int, unsignedByte: UByte): ByteArray {
    if (this.size < index + 1) {
        throw IllegalArgumentException("ByteArray must be of size ${index + 1}, but is ${this.size}")
    }
    if (index < 0) throw IllegalArgumentException("index must be >= 0, but is $index")

    this[index] = unsignedByte.toByte()
    return this
}

/**
 * Set specific bytes in a byte array to an unsigned short
 * @throws IllegalArgumentException if the array is not big enough
 * @return the byte array
 */
fun ByteArray.setUShortLE(index: Int, unsignedShort: UShort): ByteArray {
    if (this.size < index + 2) {
        throw IllegalArgumentException("ByteArray must be of size ${index + 2}, but is ${this.size}")
    }
    if (index < 0) throw IllegalArgumentException("index must be >= 0, but is $index")

    this[index] = (unsignedShort.toInt() and 0xFF).toByte()
    this[index + 1] = (unsignedShort.toInt() shr 8).toByte()
    return this
}

/**
 * Set specific bytes in a byte array to an unsigned short
 * @throws IllegalArgumentException if the array is not big enough
 * @return the byte array
 */
fun ByteArray.setUShortBE(index: Int, unsignedShort: UShort): ByteArray {
    if (this.size < index + 2) {
        throw IllegalArgumentException("ByteArray must be of size ${index + 2}, but is ${this.size}")
    }
    if (index < 0) throw IllegalArgumentException("index must be >= 0, but is $index")

    this[index] = (unsignedShort.toInt() shr 8).toByte()
    this[index + 1] = (unsignedShort.toInt() and 0xFF).toByte()
    return this
}

/**
 * Set specific bytes in a byte array to an unsigned short
 * @throws IllegalArgumentException if the array is not big enough
 * @return the byte array
 */
fun ByteArray.setUShort(index: Int, unsignedShort: UShort): ByteArray = this.setUShortBE(index, unsignedShort)

/**
 * Set specific bytes in a byte array to an unsigned short
 * @throws IllegalArgumentException if the array is not big enough
 * @return the byte array
 */
fun ByteArray.setUnsignedShortLE(index: Int, unsignedShort: UShort): ByteArray = this.setUShortLE(index, unsignedShort)

/**
 * Set specific bytes in a byte array to an unsigned short
 * @throws IllegalArgumentException if the array is not big enough
 * @return the byte array
 */
fun ByteArray.setUnsignedShortBE(index: Int, unsignedShort: UShort): ByteArray = this.setUShortBE(index, unsignedShort)

/**
 * Set specific bytes in a byte array to an unsigned short
 * @throws IllegalArgumentException if the array is not big enough
 * @return the byte array
 */
fun ByteArray.setUnsignedShort(index: Int, unsignedShort: UShort): ByteArray = this.setUShort(index, unsignedShort)

/**
 * Set specific bytes in a byte array to an unsigned int
 * @throws IllegalArgumentException if the array is not big enough
 * @return the byte array
 */
fun ByteArray.setUIntLE(index: Int, unsignedInt: UInt): ByteArray {
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
 * Set specific bytes in a byte array to an unsigned int
 * @throws IllegalArgumentException if the array is not big enough
 * @return the byte array
 */
fun ByteArray.setUIntBE(index: Int, unsignedInt: UInt): ByteArray {
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
 * Set specific bytes in a byte array to an unsigned int
 * @throws IllegalArgumentException if the array is not big enough
 * @return the byte array
 */
fun ByteArray.setUInt(index: Int, unsignedInt: UInt): ByteArray = this.setUIntBE(index, unsignedInt)

/**
 * Set specific bytes in a byte array to an unsigned int
 * @throws IllegalArgumentException if the array is not big enough
 * @return the byte array
 */
fun ByteArray.setUnsignedIntLE(index: Int, unsignedInt: UInt): ByteArray = this.setUIntLE(index, unsignedInt)

/**
 * Set specific bytes in a byte array to an unsigned int
 * @throws IllegalArgumentException if the array is not big enough
 * @return the byte array
 */
fun ByteArray.setUnsignedIntBE(index: Int, unsignedInt: UInt): ByteArray = this.setUIntBE(index, unsignedInt)

/**
 * Set specific bytes in a byte array to an unsigned int
 * @throws IllegalArgumentException if the array is not big enough
 * @return the byte array
 */
fun ByteArray.setUnsignedInt(index: Int, unsignedInt: UInt): ByteArray = this.setUInt(index, unsignedInt)

/**
 * Set specific bytes in a byte array to an unsigned long
 * @throws IllegalArgumentException if the array is not big enough
 * @return the byte array
 */
fun ByteArray.setULongLE(index: Int, unsignedLong: ULong): ByteArray {
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
 * Set specific bytes in a byte array to an unsigned long
 * @throws IllegalArgumentException if the array is not big enough
 * @return the byte array
 */
fun ByteArray.setULongBE(index: Int, unsignedLong: ULong): ByteArray {
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
 * Set specific bytes in a byte array to an unsigned long
 * @throws IllegalArgumentException if the array is not big enough
 * @return the byte array
 */
fun ByteArray.setULong(index: Int, unsignedLong: ULong): ByteArray = this.setULongBE(index, unsignedLong)

/**
 * Set specific bytes in a byte array to an unsigned long
 * @throws IllegalArgumentException if the array is not big enough
 * @return the byte array
 */
fun ByteArray.setUnsignedLongLE(index: Int, unsignedLong: ULong): ByteArray = this.setULongLE(index, unsignedLong)

/**
 * Set specific bytes in a byte array to an unsigned long
 * @throws IllegalArgumentException if the array is not big enough
 * @return the byte array
 */
fun ByteArray.setUnsignedLongBE(index: Int, unsignedLong: ULong): ByteArray = this.setULongBE(index, unsignedLong)

/**
 * Set specific bytes in a byte array to an unsigned long
 * @throws IllegalArgumentException if the array is not big enough
 * @return the byte array
 */
fun ByteArray.setUnsignedLong(index: Int, unsignedLong: ULong): ByteArray = this.setULong(index, unsignedLong)

/**
 * Get specific byte from a byte array at a given position
 * @throws IllegalArgumentException if the array is not big enough
 * @return the byte
 */
fun ByteArray.getByte(index: Int): Byte {
    if (this.size < index + 1) {
        throw IllegalArgumentException("ByteArray must be of size ${index + 1}, but is ${this.size}")
    }
    if (index < 0) throw IllegalArgumentException("index must be >= 0, but is $index")

    return this[index]
}

/**
 * Get specific shorts from a byte array at a given position
 * @throws IllegalArgumentException if the array is not big enough
 * @return the shorts
 */
fun ByteArray.getShort(index: Int): Short = this.getShortBE(index)

/**
 * Get specific shorts from a byte array at a given position [little endian], so 0x01 0x02 will be 0x0201
 * @throws IllegalArgumentException if the array is not big enough
 * @return the shorts
 */
fun ByteArray.getShortLE(index: Int): Short {
    if (this.size < index + 2) {
        throw IllegalArgumentException("ByteArray must be of size ${index + 2}, but is ${this.size}")
    }
    if (index < 0) throw IllegalArgumentException("index must be >= 0, but is $index")

    return ((this[index + 1].toUByte().toInt() shl 8) or this[index].toUByte().toInt()).toShort()
}

/**
 * Get specific shorts from a byte array at a given position [big endian], so 0x01 0x02 will be 0x0102
 * @throws IllegalArgumentException if the array is not big enough
 * @return the shorts
 */
fun ByteArray.getShortBE(index: Int): Short {
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
 * Get specific int from a byte array at a given position [little endian], so 0x01 0x02 0x03 0x04 will be 0x04030201
 * @throws IllegalArgumentException if the array is not big enough
 * @return the int
 */
fun ByteArray.getIntLE(index: Int): Int {
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
 * Get specific int from a byte array at a given position [big endian], so 0x01 0x02 0x03 0x04 will be 0x01020304
 * @throws IllegalArgumentException if the array is not big enough
 * @return the int
 */
fun ByteArray.getIntBE(index: Int): Int {
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
 * Get specific int from a byte array at a given position
 * @throws IllegalArgumentException if the array is not big enough
 * @return the int
 */
fun ByteArray.getInt(index: Int) = getIntBE(index)

/**
 * Get specific long from a byte array at a given position [little endian], so 0x01 0x02 0x03 0x04 0x05 0x06 0x07 0x08 will be 0x0807060504030201
 * @throws IllegalArgumentException if the array is not big enough
 * @return the long
 */
fun ByteArray.getLongLE(index: Int): Long {
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
 * Get specific long from a byte array at a given position [big endian], so 0x01 0x02 0x03 0x04 0x05 0x06 0x07 0x08 will be 0x0102030405060708
 * @throws IllegalArgumentException if the array is not big enough
 * @return the long
 */
fun ByteArray.getLongBE(index: Int): Long {
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
 * Get specific long from a byte array at a given position
 * @throws IllegalArgumentException if the array is not big enough
 * @return the long
 */
fun ByteArray.getLong(index: Int) = getLongBE(index)

/**
 * Get specific float from a byte array at a given position
 * @throws IllegalArgumentException if the array is not big enough
 * @return the float
 */
fun ByteArray.getFloatLE(index: Int): Float = Float.fromBits(this.getIntLE(index))

/**
 * Get specific float from a byte array at a given position
 * @throws IllegalArgumentException if the array is not big enough
 * @return the float
 */
fun ByteArray.getFloatBE(index: Int): Float = Float.fromBits(this.getIntBE(index))

/**
 * Get specific float from a byte array at a given position
 * @throws IllegalArgumentException if the array is not big enough
 * @return the float
 */
fun ByteArray.getFloat(index: Int): Float = this.getFloatBE(index)

/**
 * Get specific double from a byte array at a given position
 * @throws IllegalArgumentException if the array is not big enough
 * @return the double
 */
fun ByteArray.getDoubleLE(index: Int): Double = Double.fromBits(this.getLongLE(index))

/**
 * Get specific double from a byte array at a given position
 * @throws IllegalArgumentException if the array is not big enough
 * @return the double
 */
fun ByteArray.getDoubleBE(index: Int): Double = Double.fromBits(this.getLongBE(index))

/**
 * Get specific double from a byte array at a given position
 * @throws IllegalArgumentException if the array is not big enough
 * @return the double
 */
fun ByteArray.getDouble(index: Int): Double = this.getDoubleBE(index)

/**
 * Get specific unsigned char from a byte array at a given position
 * @throws IllegalArgumentException if the array is not big enough
 * @return the unsigned char
 */
fun ByteArray.getUnsignedByte(index: Int): UByte = this.getByte(index).toUByte()

/**
 * Get specific unsigned shorts from a byte array at a given position
 * @throws IllegalArgumentException if the array is not big enough
 * @return the unsigned shorts
 */
fun ByteArray.getUnsignedShortLE(index: Int): UShort = this.getShortLE(index).toUShort()

/**
 * Get specific unsigned shorts from a byte array at a given position
 * @throws IllegalArgumentException if the array is not big enough
 * @return the unsigned shorts
 */
fun ByteArray.getUnsignedShortBE(index: Int): UShort = this.getShortBE(index).toUShort()

/**
 * Get specific unsigned shorts from a byte array at a given position
 * @throws IllegalArgumentException if the array is not big enough
 * @return the unsigned shorts
 */
fun ByteArray.getUnsignedShort(index: Int): UShort = this.getUnsignedShortBE(index)

/**
 * Get specific unsigned int from a byte array at a given position
 * @throws IllegalArgumentException if the array is not big enough
 * @return the unsigned int
 */
fun ByteArray.getUnsignedIntLE(index: Int): UInt = this.getIntLE(index).toUInt()

/**
 * Get specific unsigned int from a byte array at a given position
 * @throws IllegalArgumentException if the array is not big enough
 * @return the unsigned int
 */
fun ByteArray.getUnsignedIntBE(index: Int): UInt = this.getIntBE(index).toUInt()

/**
 * Get specific unsigned int from a byte array at a given position
 * @throws IllegalArgumentException if the array is not big enough
 * @return the unsigned int
 */
fun ByteArray.getUnsignedInt(index: Int): UInt = this.getUnsignedIntBE(index)

/**
 * Get specific unsigned long from a byte array at a given position
 * @throws IllegalArgumentException if the array is not big enough
 * @return the unsigned long
 */
fun ByteArray.getUnsignedLongLE(index: Int): ULong = this.getLongLE(index).toULong()

/**
 * Get specific unsigned long from a byte array at a given position
 * @throws IllegalArgumentException if the array is not big enough
 * @return the unsigned long
 */
fun ByteArray.getUnsignedLongBE(index: Int): ULong = this.getLongBE(index).toULong()

/**
 * Get specific unsigned long from a byte array at a given position
 * @throws IllegalArgumentException if the array is not big enough
 * @return the unsigned long
 */
fun ByteArray.getUnsignedLong(index: Int): ULong = this.getUnsignedLongBE(index)

/**
 * Get specific unsigned byte from a byte array at a given position
 * @throws IllegalArgumentException if the array is not big enough
 * @return the unsigned byte
 */
fun ByteArray.getUByte(index: Int): UByte = this.getByte(index).toUByte()

/**
 * Get specific unsigned short from a byte array at a given position
 * @throws IllegalArgumentException if the array is not big enough
 * @return the unsigned short
 */
fun ByteArray.getUShortLE(index: Int): UShort = this.getShortLE(index).toUShort()

/**
 * Get specific unsigned short from a byte array at a given position
 * @throws IllegalArgumentException if the array is not big enough
 * @return the unsigned short
 */
fun ByteArray.getUShortBE(index: Int): UShort = this.getShortBE(index).toUShort()

/**
 * Get specific unsigned short from a byte array at a given position
 * @throws IllegalArgumentException if the array is not big enough
 * @return the unsigned short
 */
fun ByteArray.getUShort(index: Int): UShort = this.getUShortBE(index)

/**
 * Get specific unsigned int from a byte array at a given position
 * @throws IllegalArgumentException if the array is not big enough
 * @return the unsigned int
 */
fun ByteArray.getUIntLE(index: Int): UInt = this.getIntLE(index).toUInt()

/**
 * Get specific unsigned int from a byte array at a given position
 * @throws IllegalArgumentException if the array is not big enough
 * @return the unsigned int
 */
fun ByteArray.getUIntBE(index: Int): UInt = this.getIntBE(index).toUInt()

/**
 * Get specific unsigned int from a byte array at a given position
 * @throws IllegalArgumentException if the array is not big enough
 * @return the unsigned int
 */
fun ByteArray.getUInt(index: Int): UInt = this.getUIntBE(index)

/**
 * Get specific unsigned long from a byte array at a given position
 * @throws IllegalArgumentException if the array is not big enough
 * @return the unsigned long
 */
fun ByteArray.getULongLE(index: Int): ULong = this.getLongLE(index).toULong()

/**
 * Get specific unsigned long from a byte array at a given position
 * @throws IllegalArgumentException if the array is not big enough
 * @return the unsigned long
 */
fun ByteArray.getULongBE(index: Int): ULong = this.getLongBE(index).toULong()

/**
 * Get specific unsigned long from a byte array at a given position
 * @throws IllegalArgumentException if the array is not big enough
 * @return the unsigned long
 */
fun ByteArray.getULong(index: Int): ULong = this.getULongBE(index)

/**
 * Get specific bytes from a byte array at a given position [little endian]
 * @throws IllegalArgumentException if the array is not big enough
 * @return the byte array
 */
fun ByteArray.getBytesLE(index: Int, length: Int): ByteArray {
    if (this.size < index + length) {
        throw IllegalArgumentException("ByteArray must be of size ${index + length}, but is ${this.size}")
    }
    if (index < 0) throw IllegalArgumentException("index must be >= 0, but is $index")

    val array = ByteArray(length)
    for (i in 0 until length) array[i] = this[index + length - i - 1]
    return array
}

/**
 * Get specific bytes from a byte array at a given position [big endian]
 * @throws IllegalArgumentException if the array is not big enough
 * @return the byte array
 */
fun ByteArray.getBytesBE(index: Int, length: Int): ByteArray {
    if (this.size < index + length) {
        throw IndexOutOfBoundsException("ByteArray must be of size ${index + length}, but is ${this.size}")
    }
    return this.copyOfRange(index, index + length)
}

/**
 * Get specific bytes from a byte array at a given position
 * @throws IllegalArgumentException if the array is not big enough
 * @return the byte array
 */
fun ByteArray.getBytes(index: Int, length: Int): ByteArray = this.getBytesBE(index, length)

/**
 * Convert the ByteArray to a hexadecimal string
 * @return the hexadecimal string
 */
fun ByteArray.toHexString(): String {
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
fun ByteArray.toUtf8String(): String {
    val sb = StringBuilder()
    for (b in this) {
        sb.append(b.toInt().toChar())
    }
    return sb.toString()
}

/**
 * Create a ByteArray from unsigned bytes
 * @return the byte array
 */
@OptIn(ExperimentalUnsignedTypes::class)
fun byteArrayOf(vararg bytes: UByte): ByteArray {
    val array = ByteArray(bytes.size)
    for (i in bytes.indices) {
        array[i] = bytes[i].toByte()
    }
    return array
}

fun byteArrayOf(vararg bytes: Byte): ByteArray {
    return bytes.toTypedArray().toByteArray()
}

fun byteArrayOf(): ByteArray {
    return ByteArray(0)
}
