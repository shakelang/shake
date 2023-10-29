package io.github.shakelang.io.streaming.output

class CountingOutputStream(
    val out: OutputStream,
) : OutputStream() {
    var count: Long = 0
        private set

    override fun write(b: Int) {
        out.write(b)
        count++
    }

    override fun write(b: ByteArray) {
        out.write(b)
        count += b.size.toLong()
    }

    override fun write(b: ByteArray, off: Int, len: Int) {
        out.write(b, off, len)
        count += len.toLong()
    }

    override fun flush() {
        out.flush()
    }

    override fun close() {
        out.close()
    }
}