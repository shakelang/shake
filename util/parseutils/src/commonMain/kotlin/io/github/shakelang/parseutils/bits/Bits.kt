package io.github.shakelang.parseutils.bits

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
    return (this[0].toUByte().toInt() shl 8 or this[1].toUByte().toInt()).toShort()
}

/**
 * Convert a byte array to int.
 */
fun ByteArray.toInt(): Int {
    if(this.size != 4) throw IllegalArgumentException("ByteArray must be of size 4, but is ${this.size}")
    return (this[0].toUByte().toInt() shl 8 or this[1].toUByte().toInt() shl 8
            or this[2].toUByte().toInt() shl 8 or this[3].toUByte().toInt())
}

/**
 * Convert a byte array to long.
 */
fun ByteArray.toLong(): Long {
    if(this.size != 8) throw IllegalArgumentException("ByteArray must be of size 8, but is ${this.size}")
    return (this[0].toUByte().toLong() shl 56 or this[1].toUByte().toLong() shl 48
            or this[2].toUByte().toLong() shl 40 or this[3].toUByte().toLong() shl 32
            or this[4].toUByte().toLong() shl 24 or this[5].toUByte().toLong() shl 16
            or this[6].toUByte().toLong() shl 8 or this[7].toUByte().toLong())
}

/**
 * Convert a byte array to a float.
 */
fun ByteArray.toFloat(): Float {
    if(this.size != 4) throw IllegalArgumentException("ByteArray must be of size 4")
    return Float.fromBits(this.toInt())
}

/**
 * Convert a byte array to a double.
 */
fun ByteArray.toDouble(): Double {
    if(this.size != 8) throw IllegalArgumentException("ByteArray must be of size 8")
    return Double.fromBits(this.toLong())
}

fun ByteArray.toUnsignedByte(): UByte = this.toByte().toUByte()
fun ByteArray.toUnsignedShort(): UShort = this.toShort().toUShort()
fun ByteArray.toUnsignedInt(): UInt = this.toInt().toUInt()
fun ByteArray.toUnsignedLong(): ULong = this.toLong().toULong()
