package io.github.shakelang.parseutils.bytes

/**
 * Convert a byte array to a byte.
 */
fun ByteArray.toByte(): Byte {
    if(this.size != 1) throw IllegalArgumentException("ByteArray must be of size 1, but is ${this.size}")
    return this[0]
}

/**
 * Convert a byte array to short.
 */
fun ByteArray.toShort(): Short {
    if(this.size != 2) throw IllegalArgumentException("ByteArray must be of size 2, but is ${this.size}")
    return this.getShort(0)
}

/**
 * Convert a byte array to int.
 */
fun ByteArray.toInt(): Int {
    if(this.size != 4) throw IllegalArgumentException("ByteArray must be of size 4, but is ${this.size}")
    return this.getInt(0)
}

/**
 * Convert a byte array to long.
 */
fun ByteArray.toLong(): Long {
    if(this.size != 8) throw IllegalArgumentException("ByteArray must be of size 8, but is ${this.size}")
    return this.getLong(0)
}

/**
 * Convert a byte array to a float.
 */
fun ByteArray.toFloat(): Float {
    if(this.size != 4) throw IllegalArgumentException("ByteArray must be of size 4")
    return this.getFloat(0)
}

/**
 * Convert a byte array to a double.
 */
fun ByteArray.toDouble(): Double {
    if(this.size != 8) throw IllegalArgumentException("ByteArray must be of size 8")
    return this.getDouble(0)
}

/**
 * Convert a byte array to an unsigned char
 */
fun ByteArray.toUnsignedByte(): UByte = this.toByte().toUByte()

/**
 * Convert a byte array to an unsigned short
 */
fun ByteArray.toUnsignedShort(): UShort = this.toShort().toUShort()

/**
 * Convert a byte array to an unsigned int
 */
fun ByteArray.toUnsignedInt(): UInt = this.toInt().toUInt()

/**
 * Convert a byte array to an unsigned long
 */
fun ByteArray.toUnsignedLong(): ULong = this.toLong().toULong()


/**
 * Set specific bytes in a byte array to a byte array
 */
fun ByteArray.setBytes(startIndex: Int, bytes: ByteArray): ByteArray {
    for (i in startIndex until startIndex + bytes.size) this[i] = bytes[i - startIndex]
    return this
}

/**
 * Set specific bytes in a byte array to a byte
 */
fun ByteArray.setByte(index: Int, byte: Byte): ByteArray {
    this[index] = byte
    return this
}

/**
 * Set specific bytes in a byte array to a short
 */
fun ByteArray.setShort(index: Int, short: Short): ByteArray {
    this[index] = (short.toInt() shr 8).toByte()
    this[index + 1] = (short.toInt() and 0xFF).toByte()
    return this
}


/**
 * Set specific bytes in a byte array to an int
 */
fun ByteArray.setInt(index: Int, int: Int): ByteArray {
    this[index] = (int shr 24).toByte()
    this[index + 1] = (int shr 16).toByte()
    this[index + 2] = (int shr 8).toByte()
    this[index + 3] = (int and 0xFF).toByte()
    return this
}

/**
 * Set specific bytes in a byte array to a long
 */
fun ByteArray.setLong(index: Int, long: Long): ByteArray {
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
fun ByteArray.setFloat(index: Int, float: Float): ByteArray {
    this.setInt(index, float.toBits())
    return this
}

/**
 * Set specific bytes in a byte array to a double
 */
fun ByteArray.setDouble(index: Int, double: Double): ByteArray {
    this.setLong(index, double.toBits())
    return this
}

/**
 * Set specific bytes in a byte array to an unsigned char
 */
fun ByteArray.setUnsignedByte(index: Int, unsignedByte: UByte): ByteArray {
    this[index] = unsignedByte.toByte()
    return this
}

/**
 * Set specific bytes in a byte array to an unsigned short
 */
fun ByteArray.setUnsignedShort(index: Int, unsignedShort: UShort): ByteArray {
    this[index] = (unsignedShort.toInt() shr 8).toByte()
    this[index + 1] = (unsignedShort.toInt() and 0xFF).toByte()
    return this
}

/**
 * Set specific bytes in a byte array to an unsigned int
 */
fun ByteArray.setUnsignedInt(index: Int, unsignedInt: UInt): ByteArray {
    this[index] = (unsignedInt shr 24).toByte()
    this[index + 1] = (unsignedInt shr 16).toByte()
    this[index + 2] = (unsignedInt shr 8).toByte()
    this[index + 3] = (unsignedInt and 0xFFu).toByte()
    return this
}

/**
 * Set specific bytes in a byte array to an unsigned long
 */
fun ByteArray.setUnsignedLong(index: Int, unsignedLong: ULong): ByteArray {
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
fun ByteArray.getByte(index: Int): Byte = this[index]

/**
 * Get specific short from a byte array at a given position
 */
fun ByteArray.getShort(index: Int): Short = (this[index].toUByte().toInt() shl 8 or this[index + 1].toUByte().toInt()).toShort()

/**
 * Get specific int from a byte array at a given position
 */
fun ByteArray.getInt(index: Int): Int = (this[index].toUByte().toInt() shl 24 or this[index + 1].toUByte().toInt() shl 16
        or this[index + 2].toUByte().toInt() shl 8 or this[index + 3].toUByte().toInt())

/**
 * Get specific long from a byte array at a given position
 */
fun ByteArray.getLong(index: Int): Long = (this[index].toUByte().toLong() shl 56 or this[index + 1].toUByte().toLong() shl 48
        or this[index + 2].toUByte().toLong() shl 40 or this[index + 3].toUByte().toLong() shl 32
        or this[index + 4].toUByte().toLong() shl 24 or this[index + 5].toUByte().toLong() shl 16
        or this[index + 6].toUByte().toLong() shl 8 or this[index + 7].toUByte().toLong())

/**
 * Get specific float from a byte array at a given position
 */
fun ByteArray.getFloat(index: Int): Float = Float.fromBits(this.getInt(index))

/**
 * Get specific double from a byte array at a given position
 */
fun ByteArray.getDouble(index: Int): Double = Double.fromBits(this.getLong(index))

/**
 * Get specific unsigned char from a byte array at a given position
 */
fun ByteArray.getUnsignedByte(index: Int): UByte = this[index].toUByte()

/**
 * Get specific unsigned short from a byte array at a given position
 */
fun ByteArray.getUnsignedShort(index: Int): UShort = (this[index].toUByte().toInt() shl 8 or this[index + 1].toUByte().toInt()).toUShort()

/**
 * Get specific unsigned int from a byte array at a given position
 */
fun ByteArray.getUnsignedInt(index: Int): UInt = (this[index].toUByte().toInt() shl 24 or this[index + 1].toUByte().toInt() shl 16
        or this[index + 2].toUByte().toInt() shl 8 or this[index + 3].toUByte().toInt()).toUInt()

/**
 * Get specific unsigned long from a byte array at a given position
 */
fun ByteArray.getUnsignedLong(index: Int): ULong = (this[index].toUByte().toLong() shl 56 or this[index + 1].toUByte().toLong() shl 48
        or this[index + 2].toUByte().toLong() shl 40 or this[index + 3].toUByte().toLong() shl 32
        or this[index + 4].toUByte().toLong() shl 24 or this[index + 5].toUByte().toLong() shl 16
        or this[index + 6].toUByte().toLong() shl 8 or this[index + 7].toUByte().toLong()).toULong()

fun ByteArray.getBytes(index: Int, length: Int): ByteArray = this.copyOfRange(index, index + length)