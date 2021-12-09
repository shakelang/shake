package io.github.shakelang.parseutils.bytes

/**
 * Convert Byte to ByteArray
 */
fun Byte.toBytes(): ByteArray = byteArrayOf(this)

/**
 * Convert Short to ByteArray
 */
fun Short.toBytes(): ByteArray = byteArrayOf(this.toByte(), (this.toInt() shr 8).toByte())

/**
 * Convert Int to ByteArray
 */
fun Int.toBytes(): ByteArray = byteArrayOf(this.toByte(), (this shr 8).toByte(), (this shr 16).toByte(), (this shr 24).toByte())

/**
 * Convert Long to ByteArray
 */
fun Long.toBytes(): ByteArray = byteArrayOf(this.toByte(), (this.toInt() shr 8).toByte(), (this.toInt() shr 16).toByte(),
    (this.toInt() shr 24).toByte(), (this.toInt() shr 32).toByte(), (this.toInt() shr 40).toByte(),
    (this.toInt() shr 48).toByte(), (this.toInt() shr 56).toByte())

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
fun UByte.toBytes(): ByteArray = byteArrayOf(this.toByte())

/**
 * Convert UShort to ByteArray
 */
fun UShort.toBytes(): ByteArray = byteArrayOf(this.toByte(), (this.toInt() shr 8).toByte())

/**
 * Convert UInt to ByteArray
 */
fun UInt.toBytes(): ByteArray = byteArrayOf(this.toByte(), (this.toInt() shr 8).toByte(), (this.toInt() shr 16).toByte(),
    (this.toInt() shr 24).toByte())

/**
 * Convert ULong to ByteArray
 */
fun ULong.toBytes(): ByteArray = byteArrayOf(this.toByte(), (this.toInt() shr 8).toByte(), (this.toInt() shr 16).toByte(),
    (this.toInt() shr 24).toByte(), (this.toInt() shr 32).toByte(), (this.toInt() shr 40).toByte(),
    (this.toInt() shr 48).toByte(), (this.toInt() shr 56).toByte())

