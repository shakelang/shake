package com.shakelang.util.io.streaming.input

/**
 * A CountingInputStream counts the number of objects read from an underlying ObjectInputStream.
 */
class CountingObjectInputStream<T>(
    private val inputStream: ObjectInputStream<T>,
) : ObjectInputStream<T>() {

    var count = 0
        private set

    override fun read(): T? {
        val obj = inputStream.read()
        if (obj != null) {
            count++
        }
        return obj
    }

    override fun read(b: Array<T?>, off: Int, len: Int): Int {
        val readCount = inputStream.read(b, off, len)
        if (readCount > 0) {
            count += readCount
        }
        return readCount
    }

    override fun available(): Int = inputStream.available()

    override fun close() {
        inputStream.close()
    }

    override fun mark(readlimit: Int) {
        inputStream.mark(readlimit)
    }

    override fun markSupported(): Boolean = inputStream.markSupported()

    override fun reset() {
        count = 0
        inputStream.reset()
    }

    override fun skip(n: Long, arr: Array<T?>): Long {
        val skipped = inputStream.skip(n, arr)
        count += skipped.toInt() // Assuming that skipped does not exceed Int.MAX_VALUE
        return skipped
    }
}
