@file:Suppress("nothing_to_inline", "unused")
package io.github.shakelang.parseutils.bytes

/**
 * Convert Byte to ByteArray
 */
fun Byte.toBytes(): ByteArray = byteArrayOf(this)

/**
 * Convert Short to ByteArray
 */
fun Short.toBytes(): ByteArray = byteArrayOf((this.toInt() shr 8).toByte(), this.toByte())

/**
 * Convert Int to ByteArray
 */
fun Int.toBytes(): ByteArray = byteArrayOf((this shr 24).toByte(), (this shr 16).toByte(),
    (this shr 8).toByte(), this.toByte())

/**
 * Convert Long to ByteArray
 */
fun Long.toBytes(): ByteArray = byteArrayOf((this shr 56).toByte(), (this shr 48).toByte(), (this shr 40).toByte(),
    (this shr 32).toByte(), (this shr 24).toByte(), (this shr 16).toByte(), (this shr 8).toByte(), this.toByte())

/**
 * Convert Float to ByteArray
 */
fun Float.toBytes(): ByteArray = this.toBits().toBytes()

/**
 * Convert Double to ByteArray
 */
fun Double.toBytes(): ByteArray = this.toBits().toBytes()

/**
 * Convert UByte to ByteArray
 */
fun UByte.toBytes(): ByteArray = toByte().toBytes()

/**
 * Convert UShort to ByteArray
 */
fun UShort.toBytes(): ByteArray = toShort().toBytes()

/**
 * Convert UInt to ByteArray
 */
fun UInt.toBytes(): ByteArray = toInt().toBytes()

/**
 * Convert ULong to ByteArray
 */
fun ULong.toBytes(): ByteArray = toLong().toBytes()

/**
 * Byte of byte
 */
inline fun byteOf(b: Byte): Byte = b

/**
 * Short of two bytes
 */
inline fun shortOf(b0: Byte, b1: Byte): Short = (b0.toInt() shl 8 or b1.toInt()).toShort()

/**
 * Int of four bytes
 */
inline fun intOf(b0: Byte, b1: Byte, b2: Byte, b3: Byte) = b0.toInt() shl 8 or b1.toInt() shl 8 or b2.toInt() shl 8 or b3.toInt()

/**
 * Long of eight bytes
 */
inline fun longOf(b0: Byte, b1: Byte, b2: Byte, b3: Byte, b4: Byte, b5: Byte, b6: Byte, b7: Byte)
    = b0.toLong() shl 8 or b1.toLong() shl 8 or b2.toLong() shl 8 or b3.toLong() shl 8 or b4.toLong() shl 8 or
        b5.toLong() shl 8 or b6.toLong() shl 8 or b7.toLong()

/**
 * Float of four bytes
 */
inline fun floatOf(b0: Byte, b1: Byte, b2: Byte, b3: Byte) = Float.fromBits(intOf(b0, b1, b2, b3))

/**
 * Double of four bytes
 */
inline fun doubleOf(b0: Byte, b1: Byte, b2: Byte, b3: Byte, b4: Byte, b5: Byte, b6: Byte, b7: Byte)
    = Double.fromBits(longOf(b0, b1, b2, b3, b4, b5, b6, b7))

/**
 * UByte of one byte
 */
inline fun ubyteOf(b: Byte) = byteOf(b).toUByte()

/**
 * UShort of two bytes
 */
inline fun ushortOf(b0: Byte, b1: Byte): UShort = shortOf(b0, b1).toUShort()

/**
 * UInt of four bytes
 */
inline fun uintOf(b0: Byte, b1: Byte, b2: Byte, b3: Byte) = intOf(b0, b1, b2, b3).toUInt()

/**
 * ULong of eight bytes
 */
inline fun ulongOf(b0: Byte, b1: Byte, b2: Byte, b3: Byte, b4: Byte, b5: Byte, b6: Byte, b7: Byte)
        = longOf(b0, b1, b2, b3, b4, b5, b6, b7).toULong()

/**
 * UByte of one byte
 */
inline fun unsignedByteOf(b: Byte) = ubyteOf(b)

/**
 * UShort of two bytes
 */
inline fun unsignedShortOf(b0: Byte, b1: Byte): UShort = ushortOf(b0, b1)

/**
 * UInt of four bytes
 */
inline fun unsignedIntOf(b0: Byte, b1: Byte, b2: Byte, b3: Byte) = uintOf(b0, b1, b2, b3)

/**
 * ULong of eight bytes
 */
inline fun unsignedLongOf(b0: Byte, b1: Byte, b2: Byte, b3: Byte, b4: Byte, b5: Byte, b6: Byte, b7: Byte)
        = ulongOf(b0, b1, b2, b3, b4, b5, b6, b7)

