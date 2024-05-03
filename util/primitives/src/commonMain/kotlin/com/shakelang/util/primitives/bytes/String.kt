package com.shakelang.util.primitives.bytes

/**
 * Convert a [String] to a [ByteArray]
 *
 * @receiver The [String] to convert
 * @return The [ByteArray] representation of the [String]
 */
fun CharSequence.toBytes(): ByteArray = this.encodeToByteArray()

/**
 * Convert a [String] to a [ByteArray]
 *
 * @receiver The [String] to convert
 * @return The [ByteArray] representation of the [String]
 */
private fun CharSequence.encodeToByteArray(): ByteArray {
    val bytes = ByteArray(this.length)
    for (i in indices) {
        bytes[i] = this[i].code.toByte()
    }
    return bytes
}
