@file:Suppress( "unused")
package io.github.shakelang.primitives.bytes

/**
 * Convert a byte array to a byte.
 *
 * @throws IllegalArgumentException if the array is not of size 1
 * @return the byte
 *
 * @since 0.1.0
 * @version 0.1.1
 * @author [Nicolas Schmidt &lt;@nsc-de&gt;](https://github.com/nsc-de)
 */
fun ByteArray.toByte(): Byte {
    if(this.size != 1) throw IllegalArgumentException("ByteArray must be of size 1, but is ${this.size}")
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
 * @author [Nicolas Schmidt &lt;@nsc-de&gt;](https://github.com/nsc-de)
 */
fun ByteArray.toShort(): Short {
    if(this.size != 2) throw IllegalArgumentException("ByteArray must be of size 2, but is ${this.size}")
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
 * @author [Nicolas Schmidt &lt;@nsc-de&gt;](https://github.com/nsc-de)
 */
fun ByteArray.toInt(): Int {
    if(this.size != 4) throw IllegalArgumentException("ByteArray must be of size 4, but is ${this.size}")
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
 * @author [Nicolas Schmidt &lt;@nsc-de&gt;](https://github.com/nsc-de)
 */
fun ByteArray.toLong(): Long {
    if(this.size != 8) throw IllegalArgumentException("ByteArray must be of size 8, but is ${this.size}")
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
 * @author [Nicolas Schmidt &lt;@nsc-de&gt;](https://github.com/nsc-de)
 */
fun ByteArray.toFloat(): Float {
    if(this.size != 4) throw IllegalArgumentException("ByteArray must be of size 4")
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
 * @author [Nicolas Schmidt &lt;@nsc-de&gt;](https://github.com/nsc-de)
 */
fun ByteArray.toDouble(): Double {
    if(this.size != 8) throw IllegalArgumentException("ByteArray must be of size 8")
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
 * @author [Nicolas Schmidt &lt;@nsc-de&gt;](https://github.com/nsc-de)
 */
fun ByteArray.toUnsignedByte(): UByte = this.toByte().toUByte()

/**
 * Convert a byte array to an unsigned short
 *
 * @throws IllegalArgumentException if the array is not of size 2
 * @return the unsigned short
 *
 * @since 0.1.0
 * @version 0.1.1
 * @author [Nicolas Schmidt &lt;@nsc-de&gt;](https://github.com/nsc-de)
 */
fun ByteArray.toUnsignedShort(): UShort = this.toShort().toUShort()

/**
 * Convert a byte array to an unsigned int
 *
 * @throws IllegalArgumentException if the array is not of size 4
 * @return the unsigned int
 *
 * @since 0.1.0
 * @version 0.1.1
 * @author [Nicolas Schmidt &lt;@nsc-de&gt;](https://github.com/nsc-de)
 */
fun ByteArray.toUnsignedInt(): UInt = this.toInt().toUInt()

/**
 * Convert a byte array to an unsigned long
 *
 * @throws IllegalArgumentException if the array is not of size 8
 * @return the unsigned long
 *
 * @since 0.1.0
 * @version 0.1.1
 * @author [Nicolas Schmidt &lt;@nsc-de&gt;](https://github.com/nsc-de)
 */
fun ByteArray.toUnsignedLong(): ULong = this.toLong().toULong()


/**
 * Set specific bytes in a byte array to a byte array
 *
 * @throws IllegalArgumentException if the array is not of size 1
 * @return the byte array
 *
 * @since 0.1.0
 * @version 0.1.1
 * @author [Nicolas Schmidt &lt;@nsc-de&gt;](https://github.com/nsc-de)
 */
fun ByteArray.setBytes(startIndex: Int, bytes: ByteArray): ByteArray {

    if(this.size < startIndex + bytes.size)
        throw IllegalArgumentException("ByteArray must be of size ${startIndex + bytes.size}, but is ${this.size}")
    if(startIndex < 0) throw IllegalArgumentException("startIndex must be >= 0, but is $startIndex")

    for (i in startIndex until startIndex + bytes.size) this[i] = bytes[i - startIndex]
    return this
}

/**
 * Set specific bytes in a byte array to a byte
 *
 * @throws IllegalArgumentException if the array is not big enough
 * @return the byte array
 *
 * @since 0.1.0
 * @version 0.1.1
 * @author [Nicolas Schmidt &lt;@nsc-de&gt;](https://github.com/nsc-de)
 */
fun ByteArray.setByte(index: Int, byte: Byte): ByteArray {

    if(this.size < index + 1)
        throw IllegalArgumentException("ByteArray must be of size ${index + 1}, but is ${this.size}")
    if(index < 0) throw IllegalArgumentException("index must be >= 0, but is $index")

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
 * @author [Nicolas Schmidt &lt;@nsc-de&gt;](https://github.com/nsc-de)
 */
fun ByteArray.setShort(index: Int, short: Short): ByteArray {

    if(this.size < index + 2)
        throw IllegalArgumentException("ByteArray must be of size ${index + 2}, but is ${this.size}")
    if(index < 0) throw IllegalArgumentException("index must be >= 0, but is $index")

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
 * @author [Nicolas Schmidt &lt;@nsc-de&gt;](https://github.com/nsc-de)
 */
fun ByteArray.setInt(index: Int, int: Int): ByteArray {

    if(this.size < index + 4)
        throw IllegalArgumentException("ByteArray must be of size ${index + 4}, but is ${this.size}")
    if(index < 0) throw IllegalArgumentException("index must be >= 0, but is $index")

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
 * @author [Nicolas Schmidt &lt;@nsc-de&gt;](https://github.com/nsc-de)
 */
fun ByteArray.setLong(index: Int, long: Long): ByteArray {

    if(this.size < index + 8)
        throw IllegalArgumentException("ByteArray must be of size ${index + 8}, but is ${this.size}")
    if(index < 0) throw IllegalArgumentException("index must be >= 0, but is $index")

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
 * @author [Nicolas Schmidt &lt;@nsc-de&gt;](https://github.com/nsc-de)
 */
fun ByteArray.setFloat(index: Int, float: Float): ByteArray {
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
 * @author [Nicolas Schmidt &lt;@nsc-de&gt;](https://github.com/nsc-de)
 */
fun ByteArray.setDouble(index: Int, double: Double): ByteArray {
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
 * @author [Nicolas Schmidt &lt;@nsc-de&gt;](https://github.com/nsc-de)
 */
fun ByteArray.setUnsignedByte(index: Int, unsignedByte: UByte): ByteArray {

    if(this.size < index + 1)
        throw IllegalArgumentException("ByteArray must be of size ${index + 1}, but is ${this.size}")
    if(index < 0) throw IllegalArgumentException("index must be >= 0, but is $index")

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
 * @author [Nicolas Schmidt &lt;@nsc-de&gt;](https://github.com/nsc-de)
 */
fun ByteArray.setUnsignedShort(index: Int, unsignedShort: UShort): ByteArray {

    if(this.size < index + 2)
        throw IllegalArgumentException("ByteArray must be of size ${index + 2}, but is ${this.size}")
    if(index < 0) throw IllegalArgumentException("index must be >= 0, but is $index")

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
 * @author [Nicolas Schmidt &lt;@nsc-de&gt;](https://github.com/nsc-de)
 */
fun ByteArray.setUnsignedInt(index: Int, unsignedInt: UInt): ByteArray {

    if(this.size < index + 4)
        throw IllegalArgumentException("ByteArray must be of size ${index + 4}, but is ${this.size}")
    if(index < 0) throw IllegalArgumentException("index must be >= 0, but is $index")

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
 * @author [Nicolas Schmidt &lt;@nsc-de&gt;](https://github.com/nsc-de)
 */
fun ByteArray.setUnsignedLong(index: Int, unsignedLong: ULong): ByteArray {

    if(this.size < index + 8)
        throw IllegalArgumentException("ByteArray must be of size ${index + 8}, but is ${this.size}")
    if(index < 0) throw IllegalArgumentException("index must be >= 0, but is $index")

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
 * @author [Nicolas Schmidt &lt;@nsc-de&gt;](https://github.com/nsc-de)
 */
fun ByteArray.getByte(index: Int): Byte {

    if (this.size > index + 1)
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
 * @author [Nicolas Schmidt &lt;@nsc-de&gt;](https://github.com/nsc-de)
 */
fun ByteArray.getShort(index: Int): Short {

    if (this.size > index + 2)
        throw IllegalArgumentException("ByteArray must be of size ${index + 2}, but is ${this.size}")
    if (index < 0) throw IllegalArgumentException("index must be >= 0, but is $index")

    return ((this[index].toUByte().toInt() shl 8) or this[index + 1].toUByte().toInt()).toShort()

}

/**
 * Get specific int from a byte array at a given position
 *
 * @throws IllegalArgumentException if the array is not big enough
 * @return the int
 *
 * @since 0.1.0
 * @version 0.1.1
 * @author [Nicolas Schmidt &lt;@nsc-de&gt;](https://github.com/nsc-de)
 */
fun ByteArray.getInt(index: Int): Int {

        if (this.size > index + 4)
            throw IllegalArgumentException("ByteArray must be of size ${index + 4}, but is ${this.size}")
        if (index < 0) throw IllegalArgumentException("index must be >= 0, but is $index")

        return (this[index].toUByte().toInt() shl 8 or this[index + 1].toUByte().toInt() shl 8
                or this[index + 2].toUByte().toInt() shl 8 or this[index + 3].toUByte().toInt())
}

/**
 * Get specific long from a byte array at a given position
 *
 * @throws IllegalArgumentException if the array is not big enough
 * @return the long
 *
 * @since 0.1.0
 * @version 0.1.1
 * @author [Nicolas Schmidt &lt;@nsc-de&gt;](https://github.com/nsc-de)
 */
fun ByteArray.getLong(index: Int): Long {

        if (this.size > index + 8)
            throw IllegalArgumentException("ByteArray must be of size ${index + 8}, but is ${this.size}")
        if (index < 0) throw IllegalArgumentException("index must be >= 0, but is $index")

        return (this[index].toUByte().toLong() shl 8 or this[index + 1].toUByte().toLong() shl 8
                or this[index + 2].toUByte().toLong() shl 8 or this[index + 3].toUByte().toLong() shl 8
                or this[index + 4].toUByte().toLong() shl 8 or this[index + 5].toUByte().toLong() shl 8
                or this[index + 6].toUByte().toLong() shl 8 or this[index + 7].toUByte().toLong())
}

/**
 * Get specific float from a byte array at a given position
 *
 * @throws IllegalArgumentException if the array is not big enough
 * @return the float
 *
 * @since 0.1.0
 * @version 0.1.1
 * @author [Nicolas Schmidt &lt;@nsc-de&gt;](https://github.com/nsc-de)
 */
fun ByteArray.getFloat(index: Int): Float = Float.fromBits(this.getInt(index))

/**
 * Get specific double from a byte array at a given position
 *
 * @throws IllegalArgumentException if the array is not big enough
 * @return the double
 *
 * @since 0.1.0
 * @version 0.1.1
 * @author [Nicolas Schmidt &lt;@nsc-de&gt;](https://github.com/nsc-de)
 */
fun ByteArray.getDouble(index: Int): Double = Double.fromBits(this.getLong(index))

/**
 * Get specific unsigned char from a byte array at a given position
 *
 * @throws IllegalArgumentException if the array is not big enough
 * @return the unsigned char
 *
 * @since 0.1.0
 * @version 0.1.1
 * @author [Nicolas Schmidt &lt;@nsc-de&gt;](https://github.com/nsc-de)
 */
fun ByteArray.getUnsignedByte(index: Int): UByte = this.getByte(index).toUByte()

/**
 * Get specific unsigned short from a byte array at a given position
 *
 * @throws IllegalArgumentException if the array is not big enough
 * @return the unsigned short
 *
 * @since 0.1.0
 * @version 0.1.1
 * @author [Nicolas Schmidt &lt;@nsc-de&gt;](https://github.com/nsc-de)
 */
fun ByteArray.getUnsignedShort(index: Int): UShort = this.getShort(index).toUShort()

/**
 * Get specific unsigned int from a byte array at a given position
 *
 * @throws IllegalArgumentException if the array is not big enough
 * @return the unsigned int
 *
 * @since 0.1.0
 * @version 0.1.1
 * @author [Nicolas Schmidt &lt;@nsc-de&gt;](https://github.com/nsc-de)
 */
fun ByteArray.getUnsignedInt(index: Int): UInt = this.getInt(index).toUInt()

/**
 * Get specific unsigned long from a byte array at a given position
 *
 * @throws IllegalArgumentException if the array is not big enough
 * @return the unsigned long
 *
 * @since 0.1.0
 * @version 0.1.1
 * @author [Nicolas Schmidt &lt;@nsc-de&gt;](https://github.com/nsc-de)
 */
fun ByteArray.getUnsignedLong(index: Int): ULong = this.getLong(index).toULong()

/**
 * Get specific bytes from a byte array at a given position
 *
 * @throws IllegalArgumentException if the array is not big enough
 * @return the byte array
 *
 * @since 0.1.0
 * @version 0.1.1
 * @author [Nicolas Schmidt &lt;@nsc-de&gt;](https://github.com/nsc-de)
 */
fun ByteArray.getBytes(index: Int, length: Int): ByteArray = this.copyOfRange(index, index + length)

/**
 * Convert the ByteArray to a hexadecimal string
 *
 * @return the hexadecimal string
 *
 * @since 0.1.0
 * @version 0.1.1
 * @author [Nicolas Schmidt &lt;@nsc-de&gt;](https://github.com/nsc-de)
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
 *
 * @return the hexadecimal string
 *
 * @since 0.1.0
 * @version 0.1.1
 * @author [Nicolas Schmidt &lt;@nsc-de&gt;](https://github.com/nsc-de)
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
 *
 * @return the byte array
 *
 * @since 0.1.0
 * @version 0.1.1
 * @author [Nicolas Schmidt &lt;@nsc-de&gt;](https://github.com/nsc-de)
 */
@OptIn(ExperimentalUnsignedTypes::class)
fun byteArrayOf(vararg bytes: UByte): ByteArray {
    val array = ByteArray(bytes.size)
    for (i in bytes.indices) {
        array[i] = bytes[i].toByte()
    }
    return array
}
