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

/**
 * Convert a [ByteArray] to a [String]
 *
 * @receiver The [ByteArray] to convert
 * @return The [String] representation of the [ByteArray]
 */
fun CharSequence.decodeHexString(): ByteArray {
    val len = this.length
    require(len % 2 == 0) { "Invalid hex string" }
    val result = ByteArray(len / 2)
    for (i in 0 until len step 2) {
        val first = this[i].toString().toInt(16)
        val second = this[i + 1].toString().toInt(16)
        result[i / 2] = ((first shl 4) + second).toByte()
    }
    return result
}
