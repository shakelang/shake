@file:Suppress("nothing_to_inline", "unused")

package com.shakelang.shake.util.primitives.bytes

/**
 * Convert Byte to ByteArray
 *
 * @return The ByteArray
 *
 * @since 0.1.0
 * @version 0.1.1
 */
fun Byte.toBytes(): ByteArray = byteArrayOf(this)

/**
 * Convert Short to ByteArray
 *
 * @return The ByteArray
 *
 * @since 0.1.0
 * @version 0.1.1
 */
fun Short.toBytes(): ByteArray = byteArrayOf((this.toInt() shr 8).toByte(), this.toByte())

/**
 * Convert Int to ByteArray
 *
 * @return The ByteArray
 *
 * @since 0.1.0
 * @version 0.1.1
 */
fun Int.toBytes(): ByteArray = byteArrayOf(
    (this shr 24).toByte(),
    (this shr 16).toByte(),
    (this shr 8).toByte(),
    this.toByte()
)

/**
 * Convert Long to ByteArray
 *
 * @return The ByteArray
 *
 * @since 0.1.0
 * @version 0.1.1
 */
fun Long.toBytes(): ByteArray = byteArrayOf(
    (this shr 56).toByte(),
    (this shr 48).toByte(),
    (this shr 40).toByte(),
    (this shr 32).toByte(),
    (this shr 24).toByte(),
    (this shr 16).toByte(),
    (this shr 8).toByte(),
    this.toByte()
)

/**
 * Convert Float to ByteArray
 *
 * @return The ByteArray
 *
 * @since 0.1.0
 * @version 0.1.1
 */
fun Float.toBytes(): ByteArray = this.toBits().toBytes()

/**
 * Convert Double to ByteArray
 *
 * @return The ByteArray
 *
 * @since 0.1.0
 * @version 0.1.1
 */
fun Double.toBytes(): ByteArray = this.toBits().toBytes()

/**
 * Convert UByte to ByteArray
 *
 * @return The ByteArray
 *
 * @since 0.1.0
 * @version 0.1.1
 */
fun UByte.toBytes(): ByteArray = toByte().toBytes()

/**
 * Convert UShort to ByteArray
 *
 * @return The ByteArray
 *
 * @since 0.1.0
 * @version 0.1.1
 */
fun UShort.toBytes(): ByteArray = toShort().toBytes()

/**
 * Convert UInt to ByteArray
 *
 * @return The ByteArray
 *
 * @since 0.1.0
 * @version 0.1.1
 */
fun UInt.toBytes(): ByteArray = toInt().toBytes()

/**
 * Convert ULong to ByteArray
 *
 * @return The ByteArray
 *
 * @since 0.1.0
 * @version 0.1.1
 */
fun ULong.toBytes(): ByteArray = toLong().toBytes()

/**
 * Byte of byte
 *
 * @return The byte
 *
 * @since 0.1.0
 * @version 0.1.1
 */
inline fun byteOf(b: Byte): Byte = b

/**
 * Short of two bytes
 *
 * @return The short
 *
 * @since 0.1.0
 * @version 0.1.1
 */
inline fun shortOf(b0: Byte, b1: Byte): Short = (b0.toUByte().toInt() shl 8 or b1.toInt()).toShort()

/**
 * Int of four bytes
 *
 * @return The int
 *
 * @since 0.1.0
 * @version 0.1.1
 */
inline fun intOf(b0: Byte, b1: Byte, b2: Byte, b3: Byte) =
    b0.toUByte().toInt() shl 8 or b1.toUByte().toInt() shl 8 or
            b2.toUByte().toInt() shl 8 or b3.toUByte().toInt()

/**
 * Long of eight bytes
 *
 * @return The long
 *
 * @since 0.1.0
 * @version 0.1.1
 */
inline fun longOf(b0: Byte, b1: Byte, b2: Byte, b3: Byte, b4: Byte, b5: Byte, b6: Byte, b7: Byte) =
    b0.toUByte().toLong() shl 8 or b1.toUByte().toLong() shl 8 or b2.toUByte().toLong() shl 8 or b3.toUByte()
        .toLong() shl
            8 or b4.toUByte().toLong() shl 8 or b5.toUByte().toLong() shl 8 or b6.toUByte()
        .toLong() shl 8 or b7.toUByte().toLong()

/**
 * Float of four bytes
 *
 * @return The float
 *
 * @since 0.1.0
 * @version 0.1.1
 */
inline fun floatOf(b0: Byte, b1: Byte, b2: Byte, b3: Byte) = Float.fromBits(intOf(b0, b1, b2, b3))

/**
 * Double of four bytes
 *
 * @return The double
 *
 * @since 0.1.0
 * @version 0.1.1
 */
inline fun doubleOf(b0: Byte, b1: Byte, b2: Byte, b3: Byte, b4: Byte, b5: Byte, b6: Byte, b7: Byte) =
    Double.fromBits(longOf(b0, b1, b2, b3, b4, b5, b6, b7))

/**
 * UByte of one byte
 *
 * @return The UByte
 *
 * @since 0.1.0
 * @version 0.1.1
 */
inline fun ubyteOf(b: Byte) = byteOf(b).toUByte()

/**
 * UShort of two bytes
 *
 * @return The UShort
 *
 * @since 0.1.0
 * @version 0.1.1
 */
inline fun ushortOf(b0: Byte, b1: Byte): UShort = shortOf(b0, b1).toUShort()

/**
 * UInt of four bytes
 *
 * @return The UInt
 *
 * @since 0.1.0
 * @version 0.1.1
 */
inline fun uintOf(b0: Byte, b1: Byte, b2: Byte, b3: Byte) = intOf(b0, b1, b2, b3).toUInt()

/**
 * ULong of eight bytes
 *
 * @return The ULong
 *
 * @since 0.1.0
 * @version 0.1.1
 */
inline fun ulongOf(b0: Byte, b1: Byte, b2: Byte, b3: Byte, b4: Byte, b5: Byte, b6: Byte, b7: Byte) =
    longOf(b0, b1, b2, b3, b4, b5, b6, b7).toULong()

/**
 * UByte of one byte
 *
 * @return The UByte
 *
 * @since 0.1.0
 * @version 0.1.1
 */
inline fun unsignedByteOf(b: Byte) = ubyteOf(b)

/**
 * UShort of two bytes
 *
 * @return The UShort
 *
 * @since 0.1.0
 * @version 0.1.1
 */
inline fun unsignedShortOf(b0: Byte, b1: Byte): UShort = ushortOf(b0, b1)

/**
 * UInt of four bytes
 *
 * @return The UInt
 *
 * @since 0.1.0
 * @version 0.1.1
 */
inline fun unsignedIntOf(b0: Byte, b1: Byte, b2: Byte, b3: Byte) = uintOf(b0, b1, b2, b3)

/**
 * ULong of eight bytes
 *
 * @return The ULong
 *
 * @since 0.1.0
 * @version 0.1.1
 */
inline fun unsignedLongOf(b0: Byte, b1: Byte, b2: Byte, b3: Byte, b4: Byte, b5: Byte, b6: Byte, b7: Byte) =
    ulongOf(b0, b1, b2, b3, b4, b5, b6, b7)
