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
