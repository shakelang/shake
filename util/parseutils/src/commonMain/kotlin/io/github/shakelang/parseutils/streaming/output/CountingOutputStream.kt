package io.github.shakelang.parseutils.streaming.output

class CountingOutputStream(
    val out: OutputStream,
) : OutputStream() {
    private var count: Long = 0

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

    fun getCount(): Long {
        return count
    }
}