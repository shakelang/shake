package io.github.shakelang.parseutils.streaming.input

class ByteArrayInputStream(
    private var buf: ByteArray
) : InputStream() {

    private var pos = 0
    private var mark = 0
    private var count = 0

    init {
        this.count = buf.size
    }

    constructor(buf: ByteArray, offset: Int, length: Int) : this(buf) {
        this.count = length
        this.pos = offset
        this.mark = offset
    }

    override fun read(): Int {
        if (pos >= count) {
            return -1
        }
        return buf[pos++].toInt() and 0xff
    }

    override fun read(b: ByteArray, off: Int, len: Int): Int {
        var l = len
        if (pos >= count) {
            return -1
        }
        if (pos + l > count) {
            l = count - pos
        }
        b.copyInto(b, pos, off, l)
        pos += l
        return l
    }

    override fun skip(n: Long): Long {
        var l = n
        if (pos + l > count) {
            l = (count - pos).toLong()
        }
        if (l < 0) {
            return 0
        }
        pos += l.toInt()
        return l
    }

    override fun available(): Int {
        return count - pos
    }

    override fun mark(readlimit: Int) {
        mark = pos
    }

    override fun reset() {
        pos = mark
    }

    override fun markSupported(): Boolean {
        return true
    }

    override fun close() {
        buf = ByteArray(0)
        pos = 0
        mark = 0
        count = 0
    }

    override fun toString(): String {
        return "ByteArrayInputStream[pos=$pos, mark=$mark, count=$count]"
    }

}