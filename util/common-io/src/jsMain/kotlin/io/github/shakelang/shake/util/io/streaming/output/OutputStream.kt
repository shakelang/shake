package io.github.shakelang.shake.util.io.streaming.output

actual abstract class OutputStream {

    actual abstract fun write(b: Int)
    actual open fun write(b: ByteArray) = write(b, 0, b.size)
    actual open fun write(b: ByteArray, off: Int, len: Int) {
        if (off < 0 || len < 0 || off + len > b.size) throw IndexOutOfBoundsException()
        // len == 0 condition implicitly handled by loop bounds
        for (i in 0 until len) {
            write(b[off + i].toInt())
        }
    }

    actual open fun flush() {}
    actual open fun close() {}

}