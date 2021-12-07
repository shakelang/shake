package io.github.shakelang.parseutils.streaming

class CountingInputStream (private val data: InputStream) : InputStream() {
    private var count = 0L
    override fun read(): Int {
        val read = data.read()
        if (read != -1) count++
        return read
    }

    override fun read(b: ByteArray): Int {
        val read = data.read(b)
        if (read != -1) count += read
        return read
    }

    override fun read(b: ByteArray, off: Int, len: Int): Int {
        val read = data.read(b, off, len)
        if (read != -1) count += read
        return read
    }

    override fun skip(n: Long): Long {
        val skipped = data.skip(n)
        count += skipped
        return skipped
    }

    override fun available(): Int {
        return data.available()
    }

    override fun close() {
        data.close()
    }

    fun getCount(): Long {
        return count
    }
}