package io.github.shakelang.parseutils.streaming.output

import io.github.shakelang.parseutils.bytes.toBytes

class DataOutputStream(
    val output: OutputStream
) : OutputStream() {

    override fun flush() {
        output.flush()
    }

    override fun close() {
        output.close()
    }

    fun write(b: Boolean) {
        output.write(if (b) 1 else 0)
    }

    fun write(b: Byte) {
        output.write(b.toInt())
    }

    fun write(b: Short) {
        output.write(b.toBytes())
    }

    override fun write(b: Int) {
        output.write(b.toBytes())
    }

    fun write(b: Long) {
        output.write(b.toBytes())
    }

    fun write(b: Float) {
        output.write(b.toBytes())
    }

    fun write(b: Double) {
        output.write(b.toBytes())
    }

    fun write(b: UByte) {
        write(b.toByte())
    }

    fun write(b: UShort) {
        write(b.toShort())
    }

    fun write(b: UInt) {
        write(b.toInt())
    }

    fun write(b: ULong) {
        write(b.toLong())
    }

    fun write(b: Char) {
        write(b.code.toUByte())
    }

    fun write(b: BooleanArray) {
        b.forEach { write(it) }
    }

    fun write(b: BooleanArray, off: Int, len: Int) {
        for (i in off until off + len) {
            write(b[i])
        }
    }

    override fun write(b: ByteArray) {
        output.write(b)
    }

    override fun write(b: ByteArray, off: Int, len: Int) {
        output.write(b, off, len)
    }

    fun write(b: ShortArray) {
        b.forEach { write(it) }
    }

    fun write(b: ShortArray, off: Int, len: Int) {
        for (i in off until off + len) {
            write(b[i])
        }
    }

    fun write(b: IntArray) {
        b.forEach { write(it) }
    }

    fun write(b: IntArray, off: Int, len: Int) {
        for (i in off until off + len) {
            write(b[i])
        }
    }

    fun write(b: LongArray) {
        b.forEach { write(it) }
    }

    fun write(b: LongArray, off: Int, len: Int) {
        for (i in off until off + len) {
            write(b[i])
        }
    }

    fun write(b: FloatArray) {
        b.forEach { write(it) }
    }

    fun write(b: FloatArray, off: Int, len: Int) {
        for (i in off until off + len) {
            write(b[i])
        }
    }

    fun write(b: DoubleArray) {
        b.forEach { write(it) }
    }

    fun write(b: DoubleArray, off: Int, len: Int) {
        for (i in off until off + len) {
            write(b[i])
        }
    }

    fun write(b: CharArray) {
        b.forEach { write(it) }
    }

    fun write(b: CharArray, off: Int, len: Int) {
        for (i in off until off + len) {
            write(b[i])
        }
    }

    fun write(b: Array<UByte>) {
        b.forEach { write(it) }
    }

    fun write(b: Array<UByte>, off: Int, len: Int) {
        for (i in off until off + len) {
            write(b[i])
        }
    }

    fun write(b: Array<UShort>) {
        b.forEach { write(it) }
    }

    fun write(b: Array<UShort>, off: Int, len: Int) {
        for (i in off until off + len) {
            write(b[i])
        }
    }

    fun write(b: Array<UInt>) {
        b.forEach { write(it) }
    }

    fun write(b: Array<UInt>, off: Int, len: Int) {
        for (i in off until off + len) {
            write(b[i])
        }
    }

    fun write(b: Array<ULong>) {
        b.forEach { write(it) }
    }

    fun write(b: Array<ULong>, off: Int, len: Int) {
        for (i in off until off + len) {
            write(b[i])
        }
    }

    fun write(b: Array<Char>) {
        b.forEach { write(it) }
    }

    fun write(b: Array<Char>, off: Int, len: Int) {
        for (i in off until off + len) {
            write(b[i])
        }
    }

    fun write(b: Array<Boolean>) {
        b.forEach { write(it) }
    }

    fun write(b: Array<Boolean>, off: Int, len: Int) {
        for (i in off until off + len) {
            write(b[i])
        }
    }

    fun write(b: Array<Byte>) {
        b.forEach { write(it) }
    }

    fun write(b: Array<Byte>, off: Int, len: Int) {
        for (i in off until off + len) {
            write(b[i])
        }
    }

    fun write(b: Array<Short>) {
        b.forEach { write(it) }
    }

    fun write(b: Array<Short>, off: Int, len: Int) {
        for (i in off until off + len) {
            write(b[i])
        }
    }

    fun write(b: Array<Int>) {
        b.forEach { write(it) }
    }

    fun write(b: Array<Int>, off: Int, len: Int) {
        for (i in off until off + len) {
            write(b[i])
        }
    }

    fun write(b: Array<Long>) {
        b.forEach { write(it) }
    }

    fun write(b: Array<Long>, off: Int, len: Int) {
        for (i in off until off + len) {
            write(b[i])
        }
    }

    fun write(b: Array<Float>) {
        b.forEach { write(it) }
    }

    fun write(b: Array<Float>, off: Int, len: Int) {
        for (i in off until off + len) {
            write(b[i])
        }
    }

    fun write(b: Array<Double>) {
        b.forEach { write(it) }
    }

    fun write(b: Array<Double>, off: Int, len: Int) {
        for (i in off until off + len) {
            write(b[i])
        }
    }

    fun write(b: CharSequence) {
        b.forEach { write(it) }
    }

    fun write(b: CharSequence, off: Int, len: Int) {
        b.forEachIndexed { i, c ->
            if (i >= off && i < off + len) write(c)
        }
    }

}