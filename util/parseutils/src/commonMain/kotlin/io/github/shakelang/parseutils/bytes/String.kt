package io.github.shakelang.parseutils.bytes

fun CharSequence.toBytes(): ByteArray = this.encodeToByteArray()
private fun CharSequence.encodeToByteArray(): ByteArray {
    val bytes = ByteArray(this.length)
    for (i in 0 until this.length) {
        bytes[i] = this[i].toByte()
    }
    return bytes
}
