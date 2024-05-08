@file:Suppress("nothing_to_inline", "unused")

package com.shakelang.util.primitives.bytes

/**
 * Convert Byte to ByteArray (so 0x12 will be [0x12])
 * @return The ByteArray
 */
fun Byte.toBytes(): ByteArray = byteArrayOf(this)

/**
 * Convert [Short] to [ByteArray] (Little Endian, so 0x1234 will be [0x34, 0x12])
 * @return The ByteArray
 */
fun Short.toBytesLE(): ByteArray = byteArrayOf(this.toByte(), (this.toInt() shr 8).toByte())

/**
 * Convert [Short] to [ByteArray] (Big Endian) (so 0x1234 will be [0x12, 0x34])
 * @return The ByteArray
 */
fun Short.toBytesBE(): ByteArray = byteArrayOf((this.toInt() shr 8).toByte(), this.toByte())

/**
 * Convert Short to ByteArray (Big Endian)
 * @return The ByteArray
 */
fun Short.toBytes(): ByteArray = toBytesBE()

/**
 * Convert Int to ByteArray (Little Endian) (so 0x12345678 will be [0x78, 0x56, 0x34, 0x12])
 * @return The ByteArray
 */
fun Int.toBytesLE(): ByteArray = byteArrayOf(
    this.toByte(),
    (this shr 8).toByte(),
    (this shr 16).toByte(),
    (this shr 24).toByte(),
)

/**
 * Convert Int to ByteArray (Big Endian) (so 0x12345678 will be [0x12, 0x34, 0x56, 0x78])
 * @return The ByteArray
 */
fun Int.toBytesBE(): ByteArray = byteArrayOf(
    (this shr 24).toByte(),
    (this shr 16).toByte(),
    (this shr 8).toByte(),
    this.toByte(),
)

/**
 * Convert Int to ByteArray (Big Endian)
 * @return The ByteArray
 */
fun Int.toBytes(): ByteArray = toBytesBE()

/**
 * Convert Long to ByteArray (Little Endian)
 * @return The ByteArray
 */
fun Long.toBytesLE() = byteArrayOf(
    this.toByte(),
    (this shr 8).toByte(),
    (this shr 16).toByte(),
    (this shr 24).toByte(),
    (this shr 32).toByte(),
    (this shr 40).toByte(),
    (this shr 48).toByte(),
    (this shr 56).toByte(),
)

/**
 * Convert a Long to a ByteArray (Big Endian)
 * @return The ByteArray
 */
fun Long.toBytesBE() = byteArrayOf(
    (this shr 56).toByte(),
    (this shr 48).toByte(),
    (this shr 40).toByte(),
    (this shr 32).toByte(),
    (this shr 24).toByte(),
    (this shr 16).toByte(),
    (this shr 8).toByte(),
    this.toByte(),
)

/**
 * Convert Long to ByteArray (Big Endian)
 * @return The ByteArray
 */
fun Long.toBytes(): ByteArray = toBytesBE()

/**
 * Convert Float to ByteArray (Little Endian)
 * @return The ByteArray
 */
fun Float.toBytesLE(): ByteArray = toBits().toBytesLE()

/**
 * Convert Float to ByteArray (Big Endian)
 * @return The ByteArray
 */
fun Float.toBytesBE(): ByteArray = toBits().toBytesBE()

/**
 * Convert Float to ByteArray (Big Endian)
 * @return The ByteArray
 */
fun Float.toBytes(): ByteArray = toBytesBE()

/**
 * Convert Double to ByteArray (Little Endian)
 * @return The ByteArray
 */
fun Double.toBytesLE(): ByteArray = toBits().toBytesLE()

/**
 * Convert Double to ByteArray (Big Endian)
 * @return The ByteArray
 */
fun Double.toBytesBE(): ByteArray = toBits().toBytesBE()

/**
 * Convert Double to ByteArray (Big Endian)
 * @return The ByteArray
 */
fun Double.toBytes(): ByteArray = toBytesBE()

/**
 * Convert UByte to ByteArray
 * @return The ByteArray
 */
fun UByte.toBytes(): ByteArray = toByte().toBytes()

/**
 * Convert UShort to ByteArray (Little Endian)
 * @return The ByteArray
 */
fun UShort.toBytesLE(): ByteArray = toShort().toBytesLE()

/**
 * Convert UShort to ByteArray (Big Endian)
 * @return The ByteArray
 */
fun UShort.toBytesBE(): ByteArray = toShort().toBytesBE()

/**
 * Convert UShort to ByteArray
 * @return The ByteArray
 */
fun UShort.toBytes(): ByteArray = toBytesBE()

/**
 * Convert UInt to ByteArray (Little Endian)
 * @return The ByteArray
 */
fun UInt.toBytesLE(): ByteArray = toInt().toBytesLE()

/**
 * Convert UInt to ByteArray (Big Endian)
 * @return The ByteArray
 */
fun UInt.toBytesBE(): ByteArray = toInt().toBytesBE()

/**
 * Convert UInt to ByteArray
 * @return The ByteArray
 */
fun UInt.toBytes(): ByteArray = toBytesBE()

/**
 * Convert ULong to ByteArray (Little Endian)
 * @return The ByteArray
 */
fun ULong.toBytesLE(): ByteArray = toLong().toBytesLE()

/**
 * Convert ULong to ByteArray (Big Endian)
 * @return The ByteArray
 */
fun ULong.toBytesBE(): ByteArray = toLong().toBytesBE()

/**
 * Convert ULong to ByteArray
 * @return The ByteArray
 */
fun ULong.toBytes(): ByteArray = toBytesBE()

/**
 * Byte of byte
 * @return The byte
 */
inline fun byteOf(b: Byte): Byte = b

/**
 * Short of two bytes
 * @return The shorts
 */
inline fun shortOf(b0: Byte, b1: Byte): Short = (b0.toUByte().toInt() shl 8 or b1.toInt()).toShort()

/**
 * Int of four bytes
 * @return The int
 */
inline fun intOf(b0: Byte, b1: Byte, b2: Byte, b3: Byte) =
    b0.toUByte().toInt() shl 8 or b1.toUByte().toInt() shl 8 or
        b2.toUByte().toInt() shl 8 or b3.toUByte().toInt()

/**
 * Long of eight bytes
 * @return The long
 */
inline fun longOf(b0: Byte, b1: Byte, b2: Byte, b3: Byte, b4: Byte, b5: Byte, b6: Byte, b7: Byte) =
    b0.toUByte().toLong() shl 8 or b1.toUByte().toLong() shl 8 or b2.toUByte().toLong() shl 8 or b3.toUByte()
        .toLong() shl
        8 or b4.toUByte().toLong() shl 8 or b5.toUByte().toLong() shl 8 or b6.toUByte()
            .toLong() shl 8 or b7.toUByte().toLong()

/**
 * Float of four bytes
 * @return The float
 */
inline fun floatOf(b0: Byte, b1: Byte, b2: Byte, b3: Byte) = Float.fromBits(intOf(b0, b1, b2, b3))

/**
 * Double of four bytes
 * @return The double
 */
inline fun doubleOf(b0: Byte, b1: Byte, b2: Byte, b3: Byte, b4: Byte, b5: Byte, b6: Byte, b7: Byte) =
    Double.fromBits(longOf(b0, b1, b2, b3, b4, b5, b6, b7))

/**
 * UByte of one byte
 * @return The UByte
 */
inline fun ubyteOf(b: Byte) = byteOf(b).toUByte()

/**
 * UShort of two bytes
 * @return The UShort
 */
inline fun ushortOf(b0: Byte, b1: Byte): UShort = shortOf(b0, b1).toUShort()

/**
 * UInt of four bytes
 * @return The UInt
 */
inline fun uintOf(b0: Byte, b1: Byte, b2: Byte, b3: Byte) = intOf(b0, b1, b2, b3).toUInt()

/**
 * ULong of eight bytes
 * @return The ULong
 */
inline fun ulongOf(b0: Byte, b1: Byte, b2: Byte, b3: Byte, b4: Byte, b5: Byte, b6: Byte, b7: Byte) =
    longOf(b0, b1, b2, b3, b4, b5, b6, b7).toULong()

/**
 * UByte of one byte
 * @return The UByte
 */
inline fun unsignedByteOf(b: Byte) = ubyteOf(b)

/**
 * UShort of two bytes
 * @return The UShort
 */
inline fun unsignedShortOf(b0: Byte, b1: Byte): UShort = ushortOf(b0, b1)

/**
 * UInt of four bytes
 * @return The UInt
 */
inline fun unsignedIntOf(b0: Byte, b1: Byte, b2: Byte, b3: Byte) = uintOf(b0, b1, b2, b3)

/**
 * ULong of eight bytes
 * @return The ULong
 */
inline fun unsignedLongOf(b0: Byte, b1: Byte, b2: Byte, b3: Byte, b4: Byte, b5: Byte, b6: Byte, b7: Byte) =
    ulongOf(b0, b1, b2, b3, b4, b5, b6, b7)
