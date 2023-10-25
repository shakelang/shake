package io.github.shakelang.parseutils.streaming.input

import kotlin.math.min

class BufferedInputStream (
    private val input: InputStream,
    maxBuffer: Int = 8192
) : InputStream {
    private val buffer = ByteArray(maxBuffer)
    private var bufferSize = 0
    private var bufferPos = 0

    override fun read(): Int {
        if (bufferPos == bufferSize) {
            bufferSize = input.read(buffer)
            bufferPos = 0
        }
        return if (bufferSize == -1) -1 else buffer[bufferPos++].toInt() and 0xff
    }

    override fun read(b: ByteArray, off: Int, len: Int): Int {
        if (bufferPos == bufferSize) {
            bufferSize = input.read(buffer)
            bufferPos = 0
        }
        val read = min(bufferSize - bufferPos, len)
        buffer.copyInto(b, off, bufferPos, bufferPos + read)
        bufferPos += read
        return read
    }

    override fun close() {
        input.close()
    }
}