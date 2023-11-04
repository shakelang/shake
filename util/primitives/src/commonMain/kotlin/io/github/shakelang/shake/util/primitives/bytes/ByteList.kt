@file:Suppress("nothing_to_inline", "unused")

package io.github.shakelang.shake.util.primitives.bytes

/**
 * Convert a byte array to a byte.
 *
 * @throws IllegalArgumentException if the array is not of size 1
 * @return the byte
 *
 * @since 0.1.0
 * @version 0.1.1
 */
fun List<Byte>.toByte(): Byte {
    if (this.size != 1) throw IllegalArgumentException("ByteArray must be of size 1, but is ${this.size}")
    return this[0]
}

/**
 * Convert a byte array to short.
 *
 * @throws IllegalArgumentException if the array is not of size 2
 * @return the short
 *
 * @since 0.1.0
 * @version 0.1.1
 */
fun List<Byte>.toShort(): Short {
    if (this.size != 2) throw IllegalArgumentException("ByteArray must be of size 2, but is ${this.size}")
    return this.getShort(0)
}

/**
 * Convert a byte array to int.
 *
 * @throws IllegalArgumentException if the array is not of size 4
 * @return the int
 *
 * @since 0.1.0
 * @version 0.1.1
 */
fun List<Byte>.toInt(): Int {
    if (this.size != 4) throw IllegalArgumentException("ByteArray must be of size 4, but is ${this.size}")
    return this.getInt(0)
}

/**
 * Convert a byte array to long.
 *
 * @throws IllegalArgumentException if the array is not of size 8
 * @return the long
 *
 * @since 0.1.0
 * @version 0.1.1
 */
fun List<Byte>.toLong(): Long {
    if (this.size != 8) throw IllegalArgumentException("ByteArray must be of size 8, but is ${this.size}")
    return this.getLong(0)
}

/**
 * Convert a byte array to a float.
 *
 * @throws IllegalArgumentException if the array is not of size 4
 * @return the float
 *
 * @since 0.1.0
 * @version 0.1.1
 */
fun List<Byte>.toFloat(): Float {
    if (this.size != 4) throw IllegalArgumentException("ByteArray must be of size 4")
    return this.getFloat(0)
}

/**
 * Convert a byte array to a double.
 *
 * @throws IllegalArgumentException if the array is not of size 8
 * @return the double
 *
 * @since 0.1.0
 * @version 0.1.1
 */
fun List<Byte>.toDouble(): Double {
    if (this.size != 8) throw IllegalArgumentException("ByteArray must be of size 8")
    return this.getDouble(0)
}

/**
 * Convert a byte array to an unsigned char
 *
 * @throws IllegalArgumentException if the array is not of size 1
 * @return the unsigned char
 *
 * @since 0.1.0
 * @version 0.1.1
 */
fun List<Byte>.toUnsignedByte(): UByte = this.toByte().toUByte()

/**
 * Convert a byte array to an unsigned short
 *
 * @throws IllegalArgumentException if the array is not of size 2
 * @return the unsigned short
 *
 * @since 0.1.0
 * @version 0.1.1
 */
fun List<Byte>.toUnsignedShort(): UShort = this.toShort().toUShort()

/**
 * Convert a byte array to an unsigned int
 *
 * @throws IllegalArgumentException if the array is not of size 4
 * @return the unsigned int
 *
 * @since 0.1.0
 * @version 0.1.1
 */
fun List<Byte>.toUnsignedInt(): UInt = this.toInt().toUInt()

/**
 * Convert a byte array to an unsigned long
 *
 * @throws IllegalArgumentException if the array is not of size 8
 * @return the unsigned long
 *
 * @since 0.1.0
 * @version 0.1.1
 */
fun List<Byte>.toUnsignedLong(): ULong = this.toLong().toULong()


/**
 * Set specific bytes in a byte array to a byte List
 *
 * @throws IllegalArgumentException if the array is not of size 1
 * @return the byte array
 *
 * @since 0.1.0
 * @version 0.1.1
 */
fun MutableList<Byte>.setBytes(startIndex: Int, bytes: ByteArray): MutableList<Byte> {
    for (i in startIndex until startIndex + bytes.size) this[i] = bytes[i - startIndex]
    return this
}

/**
 * Set specific bytes in a byte array to a byte List
 *
 * @throws IllegalArgumentException if the array is not of size 1
 * @return the byte array
 *
 * @since 0.1.0
 * @version 0.1.1
 */
fun MutableList<Byte>.setBytes(startIndex: Int, bytes: List<Byte>): MutableList<Byte> {
    for (i in startIndex until startIndex + bytes.size) this[i] = bytes[i - startIndex]
    return this
}

/**
 * Set specific bytes in a byte array to a byte
 *
 * @throws IllegalArgumentException if the array is not of size 1
 * @return the byte array
 *
 * @since 0.1.0
 * @version 0.1.1
 */
fun MutableList<Byte>.setByte(index: Int, byte: Byte): MutableList<Byte> {

    if (this.size < index + 1)
        throw IllegalArgumentException("ByteArray must be of size ${index + 1}, but is ${this.size}")
    if (index < 0) throw IllegalArgumentException("index must be >= 0, but is $index")

    this[index] = byte
    return this
}

/**
 * Set specific bytes in a byte array to a short
 *
 * @throws IllegalArgumentException if the array is not big enough
 * @return the byte array
 *
 * @since 0.1.0
 * @version 0.1.1
 */
fun MutableList<Byte>.setShort(index: Int, short: Short): MutableList<Byte> {

    if (this.size < index + 2)
        throw IllegalArgumentException("ByteArray must be of size ${index + 2}, but is ${this.size}")
    if (index < 0) throw IllegalArgumentException("index must be >= 0, but is $index")

    this[index] = (short.toInt() shr 8).toByte()
    this[index + 1] = (short.toInt() and 0xFF).toByte()
    return this
}


/**
 * Set specific bytes in a byte array to an int
 *
 * @throws IllegalArgumentException if the array is not big enough
 * @return the byte array
 *
 * @since 0.1.0
 * @version 0.1.1
 */
fun MutableList<Byte>.setInt(index: Int, int: Int): MutableList<Byte> {

    if (this.size < index + 4)
        throw IllegalArgumentException("ByteArray must be of size ${index + 4}, but is ${this.size}")
    if (index < 0) throw IllegalArgumentException("index must be >= 0, but is $index")

    this[index] = (int shr 24).toByte()
    this[index + 1] = (int shr 16).toByte()
    this[index + 2] = (int shr 8).toByte()
    this[index + 3] = (int and 0xFF).toByte()
    return this
}

/**
 * Set specific bytes in a byte array to a long
 *
 * @throws IllegalArgumentException if the array is not big enough
 * @return the byte array
 *
 * @since 0.1.0
 * @version 0.1.1
 */
fun MutableList<Byte>.setLong(index: Int, long: Long): MutableList<Byte> {

    if (this.size < index + 8)
        throw IllegalArgumentException("ByteArray must be of size ${index + 8}, but is ${this.size}")
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
 * Set specific bytes in a byte array to a float
 *
 * @throws IllegalArgumentException if the array is not big enough
 * @return the byte array
 *
 * @since 0.1.0
 * @version 0.1.1
 */
fun MutableList<Byte>.setFloat(index: Int, float: Float): MutableList<Byte> {

    if (this.size < index + 4)
        throw IllegalArgumentException("ByteArray must be of size ${index + 4}, but is ${this.size}")
    if (index < 0) throw IllegalArgumentException("index must be >= 0, but is $index")

    this.setInt(index, float.toBits())
    return this
}

/**
 * Set specific bytes in a byte array to a double
 *
 * @throws IllegalArgumentException if the array is not big enough
 * @return the byte array
 *
 * @since 0.1.0
 * @version 0.1.1
 */
fun MutableList<Byte>.setDouble(index: Int, double: Double): MutableList<Byte> {

    if (this.size < index + 8)
        throw IllegalArgumentException("ByteArray must be of size ${index + 8}, but is ${this.size}")
    if (index < 0) throw IllegalArgumentException("index must be >= 0, but is $index")

    this.setLong(index, double.toBits())
    return this
}

/**
 * Set specific bytes in a byte array to an unsigned char
 *
 * @throws IllegalArgumentException if the array is not big enough
 * @return the byte array
 *
 * @since 0.1.0
 * @version 0.1.1
 */
fun MutableList<Byte>.setUnsignedByte(index: Int, unsignedByte: UByte): MutableList<Byte> {

    if (this.size < index + 1)
        throw IllegalArgumentException("ByteArray must be of size ${index + 1}, but is ${this.size}")
    if (index < 0) throw IllegalArgumentException("index must be >= 0, but is $index")

    this[index] = unsignedByte.toByte()
    return this
}

/**
 * Set specific bytes in a byte array to an unsigned short
 *
 * @throws IllegalArgumentException if the array is not big enough
 * @return the byte array
 *
 * @since 0.1.0
 * @version 0.1.1
 */
fun MutableList<Byte>.setUnsignedShort(index: Int, unsignedShort: UShort): MutableList<Byte> {

    if (this.size < index + 2)
        throw IllegalArgumentException("ByteArray must be of size ${index + 2}, but is ${this.size}")
    if (index < 0) throw IllegalArgumentException("index must be >= 0, but is $index")

    this[index] = (unsignedShort.toInt() shr 8).toByte()
    this[index + 1] = (unsignedShort.toInt() and 0xFF).toByte()
    return this
}

/**
 * Set specific bytes in a byte array to an unsigned int
 *
 * @throws IllegalArgumentException if the array is not big enough
 * @return the byte array
 *
 * @since 0.1.0
 * @version 0.1.1
 */
fun MutableList<Byte>.setUnsignedInt(index: Int, unsignedInt: UInt): MutableList<Byte> {

    if (this.size < index + 4)
        throw IllegalArgumentException("ByteArray must be of size ${index + 4}, but is ${this.size}")
    if (index < 0) throw IllegalArgumentException("index must be >= 0, but is $index")

    this[index] = (unsignedInt shr 24).toByte()
    this[index + 1] = (unsignedInt shr 16).toByte()
    this[index + 2] = (unsignedInt shr 8).toByte()
    this[index + 3] = (unsignedInt and 0xFFu).toByte()
    return this
}

/**
 * Set specific bytes in a byte array to an unsigned long
 *
 * @throws IllegalArgumentException if the array is not big enough
 * @return the byte array
 *
 * @since 0.1.0
 * @version 0.1.1
 */
fun MutableList<Byte>.setUnsignedLong(index: Int, unsignedLong: ULong): MutableList<Byte> {

    if (this.size < index + 8)
        throw IllegalArgumentException("ByteArray must be of size ${index + 8}, but is ${this.size}")
    if (index < 0) throw IllegalArgumentException("index must be >= 0, but is $index")

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
 *
 * @throws IllegalArgumentException if the array is not big enough
 * @return the byte
 *
 * @since 0.1.0
 * @version 0.1.1
 */
fun List<Byte>.getByte(index: Int): Byte {

    if (this.size < index + 1)
        throw IllegalArgumentException("ByteArray must be of size ${index + 1}, but is ${this.size}")
    if (index < 0) throw IllegalArgumentException("index must be >= 0, but is $index")

    return this[index]

}

/**
 * Get specific short from a byte array at a given position
 *
 * @throws IllegalArgumentException if the array is not big enough
 * @return the short
 *
 * @since 0.1.0
 * @version 0.1.1
 */
fun List<Byte>.getShort(index: Int): Short {

    if (this.size < index + 2)
        throw IllegalArgumentException("ByteArray must be of size ${index + 2}, but is ${this.size}")
    if (index < 0) throw IllegalArgumentException("index must be >= 0, but is $index")

    return shortOf(this[index], this[index + 1])
}

/**
 * Get specific int from a byte array at a given position
 *
 * @throws IllegalArgumentException if the array is not big enough
 * @return the int
 *
 * @since 0.1.0
 * @version 0.1.1
 */
fun List<Byte>.getInt(index: Int): Int {
    if (this.size < index + 4)
        throw IllegalArgumentException("ByteArray must be of size ${index + 4}, but is ${this.size}")
    if (index < 0) throw IllegalArgumentException("index must be >= 0, but is $index")

    return intOf(this[index], this[index + 1], this[index + 2], this[index + 3])
}

/**
 * Get specific long from a byte array at a given position
 *
 * @throws IllegalArgumentException if the array is not big enough
 * @return the long
 *
 * @since 0.1.0
 * @version 0.1.1
 */
fun List<Byte>.getLong(index: Int): Long {

    if (this.size < index + 8)
        throw IllegalArgumentException("ByteArray must be of size ${index + 8}, but is ${this.size}")
    if (index < 0) throw IllegalArgumentException("index must be >= 0, but is $index")

    return longOf(
        this[index], this[index + 1], this[index + 2],
        this[index + 3], this[index + 4], this[index + 5], this[index + 6], this[index + 7]
    )
}

/**
 * Get specific float from a byte array at a given position
 *
 * @throws IllegalArgumentException if the array is not big enough
 * @return the float
 *
 * @since 0.1.0
 * @version 0.1.1
 */
fun List<Byte>.getFloat(index: Int): Float = Float.fromBits(this.getInt(index))

/**
 * Get specific double from a byte array at a given position
 *
 * @throws IllegalArgumentException if the array is not big enough
 * @return the double
 *
 * @since 0.1.0
 * @version 0.1.1
 */
fun List<Byte>.getDouble(index: Int): Double = Double.fromBits(this.getLong(index))

/**
 * Get specific unsigned byte from a byte array at a given position
 *
 * @throws IllegalArgumentException if the array is not big enough
 * @return the unsigned byte
 *
 * @since 0.1.0
 * @version 0.1.1
 */
fun List<Byte>.getUnsignedByte(index: Int): UByte = this[index].toUByte()

/**
 * Get specific unsigned short from a byte array at a given position
 *
 * @throws IllegalArgumentException if the array is not big enough
 * @return the unsigned short
 *
 * @since 0.1.0
 * @version 0.1.1
 */
fun List<Byte>.getUnsignedShort(index: Int): UShort = unsignedShortOf(this[index], this[index + 1])

/**
 * Get specific unsigned int from a byte array at a given position
 *
 * @throws IllegalArgumentException if the array is not big enough
 * @return the unsigned int
 *
 * @since 0.1.0
 * @version 0.1.1
 */
fun List<Byte>.getUnsignedInt(index: Int): UInt =
    unsignedIntOf(this[index], this[index + 1], this[index + 2], this[index + 3])

/**
 * Get specific unsigned long from a byte array at a given position
 *
 * @throws IllegalArgumentException if the array is not big enough
 * @return the unsigned long
 *
 * @since 0.1.0
 * @version 0.1.1
 */
fun List<Byte>.getUnsignedLong(index: Int): ULong = unsignedLongOf(
    this[index], this[index + 1], this[index + 2],
    this[index + 3], this[index + 4], this[index + 5], this[index + 6], this[index + 7]
)

/**
 * Remove the last byte from a byte array
 *
 * @throws IllegalArgumentException if the array is not big enough
 * @return the last byte
 *
 * @since 0.1.0
 * @version 0.1.1
 */
inline fun MutableList<Byte>.removeLastByte(): Byte {
    if (this.size < 1) throw IllegalArgumentException("ByteArray must be of size 1, but is ${this.size}")
    return this.removeLast()
}

/**
 * Remove the last short from a byte array
 *
 * @throws IllegalArgumentException if the array is not big enough
 * @return the last short
 *
 * @since 0.1.0
 * @version 0.1.1
 */
inline fun MutableList<Byte>.removeLastShort(): Short {
    if (this.size < 2) throw IllegalArgumentException("ByteArray must be of size 2, but is ${this.size}")
    val bytes = ByteArray(2) { this.removeLast() }
    return shortOf(bytes[1], bytes[0])
}

/**
 * Remove the last int from a byte array
 *
 * @throws IllegalArgumentException if the array is not big enough
 * @return the last int
 *
 * @since 0.1.0
 * @version 0.1.1
 */
inline fun MutableList<Byte>.removeLastInt(): Int {
    if (this.size < 4) throw IllegalArgumentException("ByteArray must be of size 4, but is ${this.size}")
    val bytes = ByteArray(4) { this.removeLast() }
    return intOf(bytes[3], bytes[2], bytes[1], bytes[0])
}

/**
 * Remove the last long from a byte array
 *
 * @throws IllegalArgumentException if the array is not big enough
 * @return the last long
 *
 * @since 0.1.0
 * @version 0.1.1
 */
inline fun MutableList<Byte>.removeLastLong(): Long {
    if (this.size < 8) throw IllegalArgumentException("ByteArray must be of size 8, but is ${this.size}")
    val bytes = ByteArray(8) { this.removeLast() }
    return longOf(bytes[7], bytes[6], bytes[5], bytes[4], bytes[3], bytes[2], bytes[1], bytes[0])
}

/**
 * Remove the last float from a byte array
 *
 * @throws IllegalArgumentException if the array is not big enough
 * @return the last float
 *
 * @since 0.1.0
 * @version 0.1.1
 */
inline fun MutableList<Byte>.removeLastFloat(): Float = Float.fromBits(this.removeLastInt())

/**
 * Remove the last double from a byte array
 *
 * @throws IllegalArgumentException if the array is not big enough
 * @return the last double
 *
 * @since 0.1.0
 * @version 0.1.1
 */
inline fun MutableList<Byte>.removeLastDouble(): Double = Double.fromBits(this.removeLastLong())

/**
 * Remove the last unsigned byte from a byte array
 *
 * @throws IllegalArgumentException if the array is not big enough
 * @return the last unsigned byte
 *
 * @since 0.1.0
 * @version 0.1.1
 */
inline fun MutableList<Byte>.removeLastUnsignedByte(): UByte = this.removeLastByte().toUByte()

/**
 * Remove the last unsigned short from a byte array
 *
 * @throws IllegalArgumentException if the array is not big enough
 * @return the last unsigned short
 *
 * @since 0.1.0
 * @version 0.1.1
 */
inline fun MutableList<Byte>.removeLastUnsignedShort(): UShort = this.removeLastShort().toUShort()

/**
 * Remove the last unsigned int from a byte array
 *
 * @throws IllegalArgumentException if the array is not big enough
 * @return the last unsigned int
 *
 * @since 0.1.0
 * @version 0.1.1
 */
inline fun MutableList<Byte>.removeLastUnsignedInt(): UInt = this.removeLastInt().toUInt()

/**
 * Remove the last unsigned long from a byte array
 *
 * @throws IllegalArgumentException if the array is not big enough
 * @return the last unsigned long
 *
 * @since 0.1.0
 * @version 0.1.1
 */
inline fun MutableList<Byte>.removeLastUnsignedLong(): ULong = this.removeLastLong().toULong()


/**
 * Get a byte array from a byte array
 *
 * @throws IllegalArgumentException if the array is not big enough
 * @return the byte array
 *
 * @since 0.1.0
 * @version 0.1.1
 */
fun List<Byte>.getBytes(index: Int, length: Int): ByteArray {

    if (this.size < index + length)
        throw IllegalArgumentException("ByteArray must be of size ${index + length}, but is ${this.size}")
    if (index < 0) throw IllegalArgumentException("index must be >= 0, but is $index")

    return this.subList(index, index + length).toByteArray()
}

/**
 * Get a byte array from a byte array
 *
 * @throws IllegalArgumentException if the array is not big enough
 * @return the byte array
 *
 * @since 0.1.0
 * @version 0.1.1
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
 * Get a byte array from a byte array
 *
 * @throws IllegalArgumentException if the array is not big enough
 * @return the byte array
 *
 * @since 0.1.0
 * @version 0.1.1
 */
fun List<Byte>.toUtf8String(): String {
    val sb = StringBuilder()
    for (b in this) {
        sb.append(b.toInt().toChar())
    }
    return sb.toString()
}

/**
 * Append byte to a byte List
 *
 * @return the byte array
 *
 * @since 0.1.1
 * @version 0.1.1
 */
fun MutableList<Byte>.append(byte: Byte): MutableList<Byte> {
    this.add(byte)
    return this
}

/**
 * Append short to a byte List
 *
 * @return the byte array
 *
 * @since 0.1.1
 * @version 0.1.1
 */
inline fun MutableList<Byte>.append(short: Short): MutableList<Byte> {
    val bytes = short.toBytes().toList()
    this.addAll(bytes)
    return this
}

/**
 * Append int to a byte List
 *
 * @return the byte array
 *
 * @since 0.1.1
 * @version 0.1.1
 */
inline fun MutableList<Byte>.append(int: Int): MutableList<Byte> {
    val bytes = int.toBytes().toList()
    this.addAll(bytes)
    return this
}

/**
 * Append long to a byte List
 *
 * @return the byte array
 *
 * @since 0.1.1
 * @version 0.1.1
 *
 * @see [Long.toBytes]
 * @see [MutableList.append]
 */
inline fun MutableList<Byte>.append(long: Long): MutableList<Byte> {
    val bytes = long.toBytes().toList()
    this.addAll(bytes)
    return this
}

/**
 * Append float to a byte List
 *
 * @return the byte array
 *
 * @since 0.1.1
 * @version 0.1.1
 *
 * @see [Float.toBytes]
 * @see [MutableList.append]
 */
inline fun MutableList<Byte>.append(float: Float): MutableList<Byte> {
    val bytes = float.toBytes().toList()
    this.addAll(bytes)
    return this
}

/**
 * Append double to a byte List
 *
 * @return the byte array
 *
 * @since 0.1.1
 * @version 0.1.1
 *
 * @see [Double.toBytes]
 * @see [MutableList.append]
 */
inline fun MutableList<Byte>.append(double: Double): MutableList<Byte> {
    val bytes = double.toBytes().toList()
    this.addAll(bytes)
    return this
}

/**
 * Append unsigned byte to a byte List
 *
 * @return the byte array
 *
 * @since 0.1.1
 * @version 0.1.1
 *
 * @see [UByte.toByte]
 * @see [MutableList.append]
 */
inline fun MutableList<Byte>.append(unsignedByte: UByte): MutableList<Byte> {
    this.add(unsignedByte.toByte())
    return this
}

/**
 * Append unsigned short to a byte List
 *
 * @return the byte array
 *
 * @since 0.1.1
 * @version 0.1.1
 *
 * @see [UShort.toBytes]
 * @see [MutableList.append]
 */
inline fun MutableList<Byte>.append(unsignedShort: UShort): MutableList<Byte>
    = this.append(unsignedShort.toShort())

/**
 * Append unsigned int to a byte List
 *
 * @return the byte array
 *
 * @since 0.1.1
 * @version 0.1.1
 *
 * @see [UInt.toBytes]
 * @see [MutableList.append]
 */
inline fun MutableList<Byte>.append(unsignedInt: UInt): MutableList<Byte>
    = this.append(unsignedInt.toInt())

/**
 * Append unsigned long to a byte List
 *
 * @return the byte array
 *
 * @since 0.1.1
 * @version 0.1.1
 *
 * @see [ULong.toBytes]
 * @see [MutableList.append]
 */
inline fun MutableList<Byte>.append(unsignedLong: ULong): MutableList<Byte>
    = this.append(unsignedLong.toLong())