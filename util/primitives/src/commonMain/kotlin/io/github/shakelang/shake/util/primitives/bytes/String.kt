package io.github.shakelang.shake.util.primitives.bytes

/**
 * Convert a [String] to a [ByteArray]
 *
 * @receiver The [String] to convert
 * @return The [ByteArray] representation of the [String]
 *
 * @since 0.1.0
 * @version 0.1.1
 * @author [Nicolas Schmidt &lt;@nsc-de&gt;](https://github.com/nsc-de)
 */
fun CharSequence.toBytes(): ByteArray = this.encodeToByteArray()

/**
 * Convert a [String] to a [ByteArray]
 *
 * @receiver The [String] to convert
 * @return The [ByteArray] representation of the [String]
 *
 * @since 0.1.0
 * @version 0.1.1
 * @author [Nicolas Schmidt &lt;@nsc-de&gt;](https://github.com/nsc-de)
 */
private fun CharSequence.encodeToByteArray(): ByteArray {
    val bytes = ByteArray(this.length)
    for (i in 0 until this.length) {
        bytes[i] = this[i].code.toByte()
    }
    return bytes
}
