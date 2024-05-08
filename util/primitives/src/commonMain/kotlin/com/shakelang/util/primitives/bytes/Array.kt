@file:Suppress("unused")

package com.shakelang.util.primitives.bytes

/**
 * Convert a byte array to a byte.
 * @throws IllegalArgumentException if the array is not of size 1
 * @return the byte
 */
fun Array<Byte>.toByte(): Byte {
    if (this.size != 1) throw IllegalArgumentException("ByteArray must be of size 1, but is ${this.size}")
    return this[0]
}

/**
 * Convert a byte array to a short (little endian).
 * @throws IllegalArgumentException if the array is not of size 2
 * @return the short
 */
fun Array<Byte>.toShortLE(): Short {
    if (this.size != 2) throw IllegalArgumentException("ByteArray must be of size 2, but is ${this.size}")
    return this.getShortLE(0)
}

/**
 * Convert a byte array to a short (big endian).
 * @throws IllegalArgumentException if the array is not of size 2
 * @return the short
 */
fun Array<Byte>.toShortBE(): Short {
    if (this.size != 2) throw IllegalArgumentException("ByteArray must be of size 2, but is ${this.size}")
    return this.getShortBE(0)
}

/**
 * Convert a byte array to shorts.
 * @throws IllegalArgumentException if the array is not of size 2
 * @return the shorts
 */
fun Array<Byte>.toShort(): Short = this.toShortBE()

/**
 * Convert a byte array to an int (little endian).
 * @throws IllegalArgumentException if the array is not of size 4
 * @return the int
 */
fun Array<Byte>.toIntLE(): Int {
    if (this.size != 4) throw IllegalArgumentException("ByteArray must be of size 4, but is ${this.size}")
    return this.getIntLE(0)
}

/**
 * Convert a byte array to an int (big endian).
 * @throws IllegalArgumentException if the array is not of size 4
 * @return the int
 */
fun Array<Byte>.toIntBE(): Int {
    if (this.size != 4) throw IllegalArgumentException("ByteArray must be of size 4, but is ${this.size}")
    return this.getIntBE(0)
}

/**
 * Convert a byte array to int.
 * @throws IllegalArgumentException if the array is not of size 4
 * @return the int
 */
fun Array<Byte>.toInt(): Int = this.toIntBE()

/**
 * Convert a byte array to a long (little endian).
 * @throws IllegalArgumentException if the array is not of size 8
 * @return the long
 */
fun Array<Byte>.toLongLE(): Long {
    if (this.size != 8) throw IllegalArgumentException("ByteArray must be of size 8, but is ${this.size}")
    return this.getLongLE(0)
}

/**
 * Convert a byte array to a long (big endian).
 * @throws IllegalArgumentException if the array is not of size 8
 * @return the long
 */
fun Array<Byte>.toLongBE(): Long {
    if (this.size != 8) throw IllegalArgumentException("ByteArray must be of size 8, but is ${this.size}")
    return this.getLongBE(0)
}

/**
 * Convert a byte array to long.
 * @throws IllegalArgumentException if the array is not of size 8
 * @return the long
 */
fun Array<Byte>.toLong(): Long = this.toLongBE()

/**
 * Convert a byte array to a float (little endian).
 * @throws IllegalArgumentException if the array is not of size 4
 * @return the float
 */
fun Array<Byte>.toFloatLE(): Float {
    if (this.size != 4) throw IllegalArgumentException("ByteArray must be of size 4")
    return this.getFloatLE(0)
}

/**
 * Convert a byte array to a float (big endian).
 * @throws IllegalArgumentException if the array is not of size 4
 * @return the float
 */
fun Array<Byte>.toFloatBE(): Float {
    if (this.size != 4) throw IllegalArgumentException("ByteArray must be of size 4")
    return this.getFloatBE(0)
}

/**
 * Convert a byte array to a float.
 * @throws IllegalArgumentException if the array is not of size 4
 * @return the float
 */
fun Array<Byte>.toFloat(): Float {
    if (this.size != 4) throw IllegalArgumentException("ByteArray must be of size 4")
    return this.getFloat(0)
}

/**
 * Convert a byte array to a float.
 * @throws IllegalArgumentException if the array is not of size 4
 * @return the float
 */
fun Array<Byte>.toDoubleLE(): Double {
    if (this.size != 8) throw IllegalArgumentException("ByteArray must be of size 8")
    return this.getDoubleLE(0)
}

/**
 * Convert a byte array to a float.
 * @throws IllegalArgumentException if the array is not of size 4
 * @return the float
 */
fun Array<Byte>.toDoubleBE(): Double {
    if (this.size != 8) throw IllegalArgumentException("ByteArray must be of size 8")
    return this.getDouble(0)
}

/**
 * Convert a byte array to a double.
 * @throws IllegalArgumentException if the array is not of size 8
 * @return the double
 */
fun Array<Byte>.toDouble(): Double = this.toDoubleBE()

/**
 * Convert a byte array to an unsigned char
 * @throws IllegalArgumentException if the array is not of size 1
 * @return the unsigned char
 */
fun Array<Byte>.toUnsignedByte(): UByte = this.toByte().toUByte()

/**
 * Convert a byte array to an unsigned shorts
 * @throws IllegalArgumentException if the array is not of size 2
 * @return the unsigned shorts
 */
fun Array<Byte>.toUnsignedShortLE(): UShort = this.toShortLE().toUShort()

/**
 * Convert a byte array to an unsigned shorts
 * @throws IllegalArgumentException if the array is not of size 2
 * @return the unsigned shorts
 */
fun Array<Byte>.toUnsignedShortBE(): UShort = this.toShortBE().toUShort()

/**
 * Convert a byte array to an unsigned shorts
 * @throws IllegalArgumentException if the array is not of size 2
 * @return the unsigned shorts
 */
fun Array<Byte>.toUnsignedShort(): UShort = this.toShort().toUShort()

/**
 * Convert a byte array to an unsigned int
 * @throws IllegalArgumentException if the array is not of size 4
 * @return the unsigned int
 */
fun Array<Byte>.toUnsignedIntLE(): UInt = this.toIntLE().toUInt()

/**
 * Convert a byte array to an unsigned int
 * @throws IllegalArgumentException if the array is not of size 4
 * @return the unsigned int
 */
fun Array<Byte>.toUnsignedIntBE(): UInt = this.toIntBE().toUInt()

/**
 * Convert a byte array to an unsigned int
 * @throws IllegalArgumentException if the array is not of size 4
 * @return the unsigned int
 */
fun Array<Byte>.toUnsignedInt(): UInt = this.toInt().toUInt()

/**
 * Convert a byte array to an unsigned long
 * @throws IllegalArgumentException if the array is not of size 8
 * @return the unsigned long
 */
fun Array<Byte>.toUnsignedLongLE(): ULong = this.toLongLE().toULong()

/**
 * Convert a byte array to an unsigned long
 * @throws IllegalArgumentException if the array is not of size 8
 * @return the unsigned long
 */
fun Array<Byte>.toUnsignedLongBE(): ULong = this.toLongBE().toULong()

/**
 * Convert a byte array to an unsigned long
 * @throws IllegalArgumentException if the array is not of size 8
 * @return the unsigned long
 */
fun Array<Byte>.toUnsignedLong(): ULong = this.toLong().toULong()

/**
 * Convert a byte array to an unsigned byte
 * @throws IllegalArgumentException if the array is not of size 1
 * @return the unsigned byte
 */
fun Array<Byte>.toUByte(): UByte = this.toByte().toUByte()

/**
 * Convert a byte array to an unsigned short (little endian)
 * @throws IllegalArgumentException if the array is not of size 2
 * @return the unsigned short
 */
fun Array<Byte>.toUShortLE(): UShort = this.toShortLE().toUShort()

/**
 * Convert a byte array to an unsigned short (big endian)
 * @throws IllegalArgumentException if the array is not of size 2
 * @return the unsigned short
 */
fun Array<Byte>.toUShortBE(): UShort = this.toShortBE().toUShort()

/**
 * Convert a byte array to an unsigned short
 * @throws IllegalArgumentException if the array is not of size 2
 * @return the unsigned short
 */
fun Array<Byte>.toUShort(): UShort = this.toShort().toUShort()

/**
 * Convert a byte array to an unsigned int (little endian)
 * @throws IllegalArgumentException if the array is not of size 4
 * @return the unsigned int
 */
fun Array<Byte>.toUIntLE(): UInt = this.toIntLE().toUInt()

/**
 * Convert a byte array to an unsigned int (big endian)
 * @throws IllegalArgumentException if the array is not of size 4
 * @return the unsigned int
 */
fun Array<Byte>.toUIntBE(): UInt = this.toIntBE().toUInt()

/**
 * Convert a byte array to an unsigned int
 * @throws IllegalArgumentException if the array is not of size 4
 * @return the unsigned int
 */
fun Array<Byte>.toUInt(): UInt = this.toInt().toUInt()

/**
 * Convert a byte array to an unsigned long (little endian)
 * @throws IllegalArgumentException if the array is not of size 8
 * @return the unsigned long
 */
fun Array<Byte>.toULongLE(): ULong = this.toLongLE().toULong()

/**
 * Convert a byte array to an unsigned long (big endian)
 * @throws IllegalArgumentException if the array is not of size 8
 * @return the unsigned long
 */
fun Array<Byte>.toULongBE(): ULong = this.toLongBE().toULong()

/**
 * Convert a byte array to an unsigned long
 * @throws IllegalArgumentException if the array is not of size 8
 * @return the unsigned long
 */
fun Array<Byte>.toULong(): ULong = this.toLong().toULong()

/**
 * Set specific bytes in a byte array to a byte array (big endian)
 * @throws IllegalArgumentException if the array is not of size 1
 * @return the byte array
 */
fun Array<Byte>.setBytesBE(startIndex: Int, bytes: ByteArray): Array<Byte> {
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
fun Array<Byte>.setBytesLE(startIndex: Int, bytes: ByteArray): Array<Byte> {
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
fun Array<Byte>.setBytes(startIndex: Int, bytes: ByteArray): Array<Byte> = this.setBytesBE(startIndex, bytes)

/**
 * Set specific bytes in a byte array to a byte array (big endian)
 * @throws IllegalArgumentException if the array is not of size 1
 * @return the byte array
 */
fun Array<Byte>.setBytesBE(startIndex: Int, bytes: Array<Byte>) = this.setBytesBE(startIndex, bytes.toByteArray())

/**
 * Set specific bytes in a byte array to a byte array (little endian)
 * @throws IllegalArgumentException if the array is not of size 1
 * @return the byte array
 */
fun Array<Byte>.setBytesLE(startIndex: Int, bytes: Array<Byte>) = this.setBytesLE(startIndex, bytes.toByteArray())

/**
 * Set specific bytes in a byte array to a byte array (big endian)
 * @throws IllegalArgumentException if the array is not of size 1
 * @return the byte array
 */
fun Array<Byte>.setBytes(startIndex: Int, bytes: Array<Byte>) = this.setBytes(startIndex, bytes.toByteArray())

/**
 * Set specific bytes in a byte array to a byte
 * @throws IllegalArgumentException if the array is not big enough
 * @return the byte array
 */
fun Array<Byte>.setByte(index: Int, byte: Byte): Array<Byte> {
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
fun Array<Byte>.setShortLE(index: Int, short: Short): Array<Byte> {
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
fun Array<Byte>.setShortBE(index: Int, short: Short): Array<Byte> {
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
fun Array<Byte>.setShort(index: Int, short: Short): Array<Byte> = this.setShortBE(index, short)

/**
 * Set specific bytes in a byte array to an int
 * @throws IllegalArgumentException if the array is not big enough
 * @return the byte array
 */
fun Array<Byte>.setIntLE(index: Int, int: Int): Array<Byte> {
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
fun Array<Byte>.setIntBE(index: Int, int: Int): Array<Byte> {
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
fun Array<Byte>.setInt(index: Int, int: Int): Array<Byte> = this.setIntBE(index, int)

/**
 * Set specific bytes in a byte array to a long
 * @throws IllegalArgumentException if the array is not big enough
 * @return the byte array
 */
fun Array<Byte>.setLongLE(index: Int, long: Long): Array<Byte> {
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
fun Array<Byte>.setLongBE(index: Int, long: Long): Array<Byte> {
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
fun Array<Byte>.setLong(index: Int, long: Long): Array<Byte> = this.setLongBE(index, long)

/**
 * Set specific bytes in a byte array to a float
 * @throws IllegalArgumentException if the array is not big enough
 * @return the byte array
 */
fun Array<Byte>.setFloatLE(index: Int, float: Float): Array<Byte> {
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
fun Array<Byte>.setFloatBE(index: Int, float: Float): Array<Byte> {
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
fun Array<Byte>.setFloat(index: Int, float: Float): Array<Byte> = this.setFloatBE(index, float)

/**
 * Set specific bytes in a byte array to a double
 * @throws IllegalArgumentException if the array is not big enough
 * @return the byte array
 */
fun Array<Byte>.setDoubleLE(index: Int, double: Double): Array<Byte> {
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
fun Array<Byte>.setDoubleBE(index: Int, double: Double): Array<Byte> {
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
fun Array<Byte>.setDouble(index: Int, double: Double): Array<Byte> = this.setDoubleBE(index, double)

/**
 * Set specific bytes in a byte array to an unsigned char
 * @throws IllegalArgumentException if the array is not big enough
 * @return the byte array
 */
fun Array<Byte>.setUnsignedByte(index: Int, unsignedByte: UByte) = this.setByte(index, unsignedByte.toByte())

/**
 * Set specific bytes in a byte array to an unsigned char
 * @throws IllegalArgumentException if the array is not big enough
 * @return the byte array
 */
fun Array<Byte>.setUByte(index: Int, unsignedByte: UByte): Array<Byte> {
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
fun Array<Byte>.setUShortLE(index: Int, unsignedShort: UShort): Array<Byte> {
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
fun Array<Byte>.setUShortBE(index: Int, unsignedShort: UShort): Array<Byte> {
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
fun Array<Byte>.setUShort(index: Int, unsignedShort: UShort): Array<Byte> = this.setUShortBE(index, unsignedShort)

/**
 * Set specific bytes in a byte array to an unsigned short
 * @throws IllegalArgumentException if the array is not big enough
 * @return the byte array
 */
fun Array<Byte>.setUnsignedShortLE(index: Int, unsignedShort: UShort): Array<Byte> = this.setUShortLE(index, unsignedShort)

/**
 * Set specific bytes in a byte array to an unsigned short
 * @throws IllegalArgumentException if the array is not big enough
 * @return the byte array
 */
fun Array<Byte>.setUnsignedShortBE(index: Int, unsignedShort: UShort): Array<Byte> = this.setUShortBE(index, unsignedShort)

/**
 * Set specific bytes in a byte array to an unsigned short
 * @throws IllegalArgumentException if the array is not big enough
 * @return the byte array
 */
fun Array<Byte>.setUnsignedShort(index: Int, unsignedShort: UShort): Array<Byte> = this.setUShort(index, unsignedShort)

/**
 * Set specific bytes in a byte array to an unsigned int
 * @throws IllegalArgumentException if the array is not big enough
 * @return the byte array
 */
fun Array<Byte>.setUIntLE(index: Int, unsignedInt: UInt): Array<Byte> {
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
fun Array<Byte>.setUIntBE(index: Int, unsignedInt: UInt): Array<Byte> {
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
fun Array<Byte>.setUInt(index: Int, unsignedInt: UInt): Array<Byte> = this.setUIntBE(index, unsignedInt)

/**
 * Set specific bytes in a byte array to an unsigned int
 * @throws IllegalArgumentException if the array is not big enough
 * @return the byte array
 */
fun Array<Byte>.setUnsignedIntLE(index: Int, unsignedInt: UInt): Array<Byte> = this.setUIntLE(index, unsignedInt)

/**
 * Set specific bytes in a byte array to an unsigned int
 * @throws IllegalArgumentException if the array is not big enough
 * @return the byte array
 */
fun Array<Byte>.setUnsignedIntBE(index: Int, unsignedInt: UInt): Array<Byte> = this.setUIntBE(index, unsignedInt)

/**
 * Set specific bytes in a byte array to an unsigned int
 * @throws IllegalArgumentException if the array is not big enough
 * @return the byte array
 */
fun Array<Byte>.setUnsignedInt(index: Int, unsignedInt: UInt): Array<Byte> = this.setUInt(index, unsignedInt)

/**
 * Set specific bytes in a byte array to an unsigned long
 * @throws IllegalArgumentException if the array is not big enough
 * @return the byte array
 */
fun Array<Byte>.setULongLE(index: Int, unsignedLong: ULong): Array<Byte> {
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
fun Array<Byte>.setULongBE(index: Int, unsignedLong: ULong): Array<Byte> {
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
fun Array<Byte>.setULong(index: Int, unsignedLong: ULong): Array<Byte> = this.setULongBE(index, unsignedLong)

/**
 * Set specific bytes in a byte array to an unsigned long
 * @throws IllegalArgumentException if the array is not big enough
 * @return the byte array
 */
fun Array<Byte>.setUnsignedLongLE(index: Int, unsignedLong: ULong): Array<Byte> = this.setULongLE(index, unsignedLong)

/**
 * Set specific bytes in a byte array to an unsigned long
 * @throws IllegalArgumentException if the array is not big enough
 * @return the byte array
 */
fun Array<Byte>.setUnsignedLongBE(index: Int, unsignedLong: ULong): Array<Byte> = this.setULongBE(index, unsignedLong)

/**
 * Set specific bytes in a byte array to an unsigned long
 * @throws IllegalArgumentException if the array is not big enough
 * @return the byte array
 */
fun Array<Byte>.setUnsignedLong(index: Int, unsignedLong: ULong): Array<Byte> = this.setULong(index, unsignedLong)

/**
 * Get specific byte from a byte array at a given position
 * @throws IllegalArgumentException if the array is not big enough
 * @return the byte
 */
fun Array<Byte>.getByte(index: Int): Byte {
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
fun Array<Byte>.getShort(index: Int): Short = this.getShortBE(index)

/**
 * Get specific shorts from a byte array at a given position [little endian], so 0x01 0x02 will be 0x0201
 * @throws IllegalArgumentException if the array is not big enough
 * @return the shorts
 */
fun Array<Byte>.getShortLE(index: Int): Short {
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
fun Array<Byte>.getShortBE(index: Int): Short {
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
fun Array<Byte>.getIntLE(index: Int): Int {
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
fun Array<Byte>.getIntBE(index: Int): Int {
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
fun Array<Byte>.getInt(index: Int) = getIntBE(index)

/**
 * Get specific long from a byte array at a given position [little endian], so 0x01 0x02 0x03 0x04 0x05 0x06 0x07 0x08 will be 0x0807060504030201
 * @throws IllegalArgumentException if the array is not big enough
 * @return the long
 */
fun Array<Byte>.getLongLE(index: Int): Long {
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
fun Array<Byte>.getLongBE(index: Int): Long {
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
fun Array<Byte>.getLong(index: Int) = getLongBE(index)

/**
 * Get specific float from a byte array at a given position
 * @throws IllegalArgumentException if the array is not big enough
 * @return the float
 */
fun Array<Byte>.getFloatLE(index: Int): Float = Float.fromBits(this.getIntLE(index))

/**
 * Get specific float from a byte array at a given position
 * @throws IllegalArgumentException if the array is not big enough
 * @return the float
 */
fun Array<Byte>.getFloatBE(index: Int): Float = Float.fromBits(this.getIntBE(index))

/**
 * Get specific float from a byte array at a given position
 * @throws IllegalArgumentException if the array is not big enough
 * @return the float
 */
fun Array<Byte>.getFloat(index: Int): Float = this.getFloatBE(index)

/**
 * Get specific double from a byte array at a given position
 * @throws IllegalArgumentException if the array is not big enough
 * @return the double
 */
fun Array<Byte>.getDoubleLE(index: Int): Double = Double.fromBits(this.getLongLE(index))

/**
 * Get specific double from a byte array at a given position
 * @throws IllegalArgumentException if the array is not big enough
 * @return the double
 */
fun Array<Byte>.getDoubleBE(index: Int): Double = Double.fromBits(this.getLongBE(index))

/**
 * Get specific double from a byte array at a given position
 * @throws IllegalArgumentException if the array is not big enough
 * @return the double
 */
fun Array<Byte>.getDouble(index: Int): Double = this.getDoubleBE(index)

/**
 * Get specific unsigned char from a byte array at a given position
 * @throws IllegalArgumentException if the array is not big enough
 * @return the unsigned char
 */
fun Array<Byte>.getUnsignedByte(index: Int): UByte = this.getByte(index).toUByte()

/**
 * Get specific unsigned shorts from a byte array at a given position
 * @throws IllegalArgumentException if the array is not big enough
 * @return the unsigned shorts
 */
fun Array<Byte>.getUnsignedShortLE(index: Int): UShort = this.getShortLE(index).toUShort()

/**
 * Get specific unsigned shorts from a byte array at a given position
 * @throws IllegalArgumentException if the array is not big enough
 * @return the unsigned shorts
 */
fun Array<Byte>.getUnsignedShortBE(index: Int): UShort = this.getShortBE(index).toUShort()

/**
 * Get specific unsigned shorts from a byte array at a given position
 * @throws IllegalArgumentException if the array is not big enough
 * @return the unsigned shorts
 */
fun Array<Byte>.getUnsignedShort(index: Int): UShort = this.getUnsignedShortBE(index)

/**
 * Get specific unsigned int from a byte array at a given position
 * @throws IllegalArgumentException if the array is not big enough
 * @return the unsigned int
 */
fun Array<Byte>.getUnsignedIntLE(index: Int): UInt = this.getIntLE(index).toUInt()

/**
 * Get specific unsigned int from a byte array at a given position
 * @throws IllegalArgumentException if the array is not big enough
 * @return the unsigned int
 */
fun Array<Byte>.getUnsignedIntBE(index: Int): UInt = this.getIntBE(index).toUInt()

/**
 * Get specific unsigned int from a byte array at a given position
 * @throws IllegalArgumentException if the array is not big enough
 * @return the unsigned int
 */
fun Array<Byte>.getUnsignedInt(index: Int): UInt = this.getUnsignedIntBE(index)

/**
 * Get specific unsigned long from a byte array at a given position
 * @throws IllegalArgumentException if the array is not big enough
 * @return the unsigned long
 */
fun Array<Byte>.getUnsignedLongLE(index: Int): ULong = this.getLongLE(index).toULong()

/**
 * Get specific unsigned long from a byte array at a given position
 * @throws IllegalArgumentException if the array is not big enough
 * @return the unsigned long
 */
fun Array<Byte>.getUnsignedLongBE(index: Int): ULong = this.getLongBE(index).toULong()

/**
 * Get specific unsigned long from a byte array at a given position
 * @throws IllegalArgumentException if the array is not big enough
 * @return the unsigned long
 */
fun Array<Byte>.getUnsignedLong(index: Int): ULong = this.getUnsignedLongBE(index)

/**
 * Get specific unsigned byte from a byte array at a given position
 * @throws IllegalArgumentException if the array is not big enough
 * @return the unsigned byte
 */
fun Array<Byte>.getUByte(index: Int): UByte = this.getByte(index).toUByte()

/**
 * Get specific unsigned short from a byte array at a given position
 * @throws IllegalArgumentException if the array is not big enough
 * @return the unsigned short
 */
fun Array<Byte>.getUShortLE(index: Int): UShort = this.getShortLE(index).toUShort()

/**
 * Get specific unsigned short from a byte array at a given position
 * @throws IllegalArgumentException if the array is not big enough
 * @return the unsigned short
 */
fun Array<Byte>.getUShortBE(index: Int): UShort = this.getShortBE(index).toUShort()

/**
 * Get specific unsigned short from a byte array at a given position
 * @throws IllegalArgumentException if the array is not big enough
 * @return the unsigned short
 */
fun Array<Byte>.getUShort(index: Int): UShort = this.getUShortBE(index)

/**
 * Get specific unsigned int from a byte array at a given position
 * @throws IllegalArgumentException if the array is not big enough
 * @return the unsigned int
 */
fun Array<Byte>.getUIntLE(index: Int): UInt = this.getIntLE(index).toUInt()

/**
 * Get specific unsigned int from a byte array at a given position
 * @throws IllegalArgumentException if the array is not big enough
 * @return the unsigned int
 */
fun Array<Byte>.getUIntBE(index: Int): UInt = this.getIntBE(index).toUInt()

/**
 * Get specific unsigned int from a byte array at a given position
 * @throws IllegalArgumentException if the array is not big enough
 * @return the unsigned int
 */
fun Array<Byte>.getUInt(index: Int): UInt = this.getUIntBE(index)

/**
 * Get specific unsigned long from a byte array at a given position
 * @throws IllegalArgumentException if the array is not big enough
 * @return the unsigned long
 */
fun Array<Byte>.getULongLE(index: Int): ULong = this.getLongLE(index).toULong()

/**
 * Get specific unsigned long from a byte array at a given position
 * @throws IllegalArgumentException if the array is not big enough
 * @return the unsigned long
 */
fun Array<Byte>.getULongBE(index: Int): ULong = this.getLongBE(index).toULong()

/**
 * Get specific unsigned long from a byte array at a given position
 * @throws IllegalArgumentException if the array is not big enough
 * @return the unsigned long
 */
fun Array<Byte>.getULong(index: Int): ULong = this.getULongBE(index)

/**
 * Get specific bytes from a byte array at a given position [little endian]
 * @throws IllegalArgumentException if the array is not big enough
 * @return the byte array
 */
fun Array<Byte>.getBytesLE(index: Int, length: Int): Array<Byte> {
    if (this.size < index + length) {
        throw IllegalArgumentException("ByteArray must be of size ${index + length}, but is ${this.size}")
    }
    if (index < 0) throw IllegalArgumentException("index must be >= 0, but is $index")

    val array = Array(length) { this[index + length - it - 1] }
    return array
}

/**
 * Get specific bytes from a byte array at a given position [big endian]
 * @throws IllegalArgumentException if the array is not big enough
 * @return the byte array
 */
fun Array<Byte>.getBytesBE(index: Int, length: Int): Array<Byte> {
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
fun Array<Byte>.getBytes(index: Int, length: Int): Array<Byte> = this.getBytesBE(index, length)

/**
 * Convert the ByteArray to a hexadecimal string
 * @return the hexadecimal string
 */
fun Array<Byte>.toHexString(): String {
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
fun Array<Byte>.toUtf8String(): String {
    val sb = StringBuilder()
    for (b in this) {
        sb.append(b.toInt().toChar())
    }
    return sb.toString()
}
