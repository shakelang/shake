package io.github.shakelang.parseutils.streaming.output

class ByteArrayOutputStream {

    private var buf: ByteArray = ByteArray(8192)
    private var count: Int = 0

    fun write(b: Byte) {
        if (count == buf.size) {
            val newbuf = ByteArray(buf.size * 2)
            buf.copyInto(newbuf, 0)
            buf = newbuf
        }
        buf[count++] = b
    }

    fun toByteArray(): ByteArray {
        val newbuf = ByteArray(count)
        buf.copyInto(newbuf, 0)
        return newbuf
    }

    fun reset() {
        count = 0
    }

    fun size(): Int {
        return count
    }

}