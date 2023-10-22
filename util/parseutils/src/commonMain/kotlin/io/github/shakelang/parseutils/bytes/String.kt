package io.github.shakelang.parseutils.bytes

import io.github.shakelang.parseutils.streaming.input.ByteArrayInputStream

fun CharSequence.toBytes(): ByteArray = this.encodeToByteArray()
private fun CharSequence.encodeToByteArray(): ByteArray {
    val bytes = ByteArray(this.length)
    for (i in 0 until this.length) {
        bytes[i] = this[i].code.toByte()
    }
    return bytes
}

fun CharSequence.byteInputStream() = ByteArrayInputStream(this.toBytes())