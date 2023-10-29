package io.github.shakelang.io.streaming.output

class ByteArrayOutputStream : OutputStream() {
    
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

    override fun write(b: ByteArray) {
        for (i in b.indices) write(b[i])
    }

    override fun write(b: ByteArray, off: Int, len: Int) {
        for (i in off until off + len) write(b[i])
    }

    override fun write(b: Int) {
        write(b.toByte())
    }

    fun toByteArray(): ByteArray {
        val newbuf = ByteArray(count)
        buf.copyInto(newbuf, 0, 0, count)
        return newbuf
    }

    fun reset() {
        count = 0
        buf = ByteArray(8192)
    }

    fun size(): Int {
        return count
    }

}