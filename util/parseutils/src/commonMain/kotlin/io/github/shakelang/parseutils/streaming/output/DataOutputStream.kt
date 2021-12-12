package io.github.shakelang.parseutils.streaming.output

import io.github.shakelang.parseutils.bytes.toBytes

class DataOutputStream(
    val output: OutputStream
) : OutputStream() {

    override fun write(b: Int) {
        output.write(b)
    }

    override fun flush() {
        output.flush()
    }

    override fun close() {
        output.close()
    }

    fun writeBoolean(b: Boolean) {
        write(if (b) 1 else 0)
    }

    fun writeByte(b: Byte) {
        write(b.toInt())
    }

    fun writeShort(b: Short) {
        write(b.toBytes())
    }

    fun writeInt(b: Int) {
        write(b.toBytes())
    }

    fun writeLong(b: Long) {
        write(b.toBytes())
    }

    fun writeFloat(b: Float) {
        write(b.toBytes())
    }

    fun writeDouble(b: Double) {
        write(b.toBytes())
    }

    fun writeUnsignedByte(b: UByte) {
        writeByte(b.toByte())
    }

    fun writeUnsignedShort(b: UShort) {
        writeShort(b.toShort())
    }

    fun writeUnsignedInt(b: UInt) {
        writeInt(b.toInt())
    }

    fun writeUnsignedLong(b: ULong) {
        writeLong(b.toLong())
    }

    fun writeChar(b: Char) {
        writeUnsignedByte(b.code.toUByte())
    }

    fun writeBooleanArray(b: BooleanArray) {
        b.forEach { writeBoolean(it) }
    }

    fun writeBooleanArray(b: BooleanArray, off: Int, len: Int) {
        for (i in off until off + len) {
            writeBoolean(b[i])
        }
    }

    fun writeByteArray(b: ByteArray) {
        write(b)
    }

    fun writeByteArray(b: ByteArray, off: Int, len: Int) {
        write(b, off, len)
    }

    fun writeShortArray(b: ShortArray) {
        b.forEach { writeShort(it) }
    }

    fun writeShortArray(b: ShortArray, off: Int, len: Int) {
        for (i in off until off + len) {
            writeShort(b[i])
        }
    }

    fun writeIntArray(b: IntArray) {
        b.forEach { writeInt(it) }
    }

    fun writeIntArray(b: IntArray, off: Int, len: Int) {
        for (i in off until off + len) {
            writeInt(b[i])
        }
    }

    fun writeLongArray(b: LongArray) {
        b.forEach { writeLong(it) }
    }

    fun writeLongArray(b: LongArray, off: Int, len: Int) {
        for (i in off until off + len) {
            writeLong(b[i])
        }
    }

    fun writeFloatArray(b: FloatArray) {
        b.forEach { writeFloat(it) }
    }

    fun writeFloatArray(b: FloatArray, off: Int, len: Int) {
        for (i in off until off + len) {
            writeFloat(b[i])
        }
    }

    fun writeDoubleArray(b: DoubleArray) {
        b.forEach { writeDouble(it) }
    }

    fun writeDoubleArray(b: DoubleArray, off: Int, len: Int) {
        for (i in off until off + len) {
            writeDouble(b[i])
        }
    }

    fun writeCharArray(b: CharArray) {
        b.forEach { writeChar(it) }
    }

    fun writeCharArray(b: CharArray, off: Int, len: Int) {
        for (i in off until off + len) {
            writeChar(b[i])
        }
    }

    fun writeUnsignedByteArray(b: Array<UByte>, off: Int, len: Int) {
        for (i in off until off + len) {
            writeUnsignedByte(b[i])
        }
    }

    fun writeUnsignedShortArray(b: Array<UShort>, off: Int, len: Int) {
        for (i in off until off + len) {
            writeUnsignedShort(b[i])
        }
    }

    fun writeUnsignedIntArray(b: Array<UInt>, off: Int, len: Int) {
        for (i in off until off + len) {
            writeUnsignedInt(b[i])
        }
    }

    fun writeUnsignedLongArray(b: Array<ULong>, off: Int, len: Int) {
        for (i in off until off + len) {
            writeUnsignedLong(b[i])
        }
    }

    fun writeCharArray(b: Array<Char>, off: Int, len: Int) {
        for (i in off until off + len) {
            writeChar(b[i])
        }
    }

    fun writeBooleanArray(b: Array<Boolean>, off: Int, len: Int) {
        for (i in off until off + len) {
            writeBoolean(b[i])
        }
    }

    fun writeByteArray(b: Array<Byte>, off: Int, len: Int) {
        for (i in off until off + len) {
            writeByte(b[i])
        }
    }

    fun writeShortArray(b: Array<Short>, off: Int, len: Int) {
        for (i in off until off + len) {
            writeShort(b[i])
        }
    }

    fun writeIntArray(b: Array<Int>, off: Int, len: Int) {
        for (i in off until off + len) {
            writeInt(b[i])
        }
    }

    fun writeLongArray(b: Array<Long>, off: Int, len: Int) {
        for (i in off until off + len) {
            writeLong(b[i])
        }
    }

    fun writeFloatArray(b: Array<Float>, off: Int, len: Int) {
        for (i in off until off + len) {
            writeFloat(b[i])
        }
    }

    fun writeDoubleArray(b: Array<Double>, off: Int, len: Int) {
        for (i in off until off + len) {
            writeDouble(b[i])
        }
    }

    fun writeUTF(str: CharSequence) {
        val bytes = str.toBytes()
        writeUnsignedShort(bytes.size.toUShort())
        writeByteArray(bytes)
    }

    fun writeUTF(str: CharSequence, off: Int, len: Int) {
        val bytes = str.toBytes()
        writeUnsignedShort(len.toUShort())
        writeByteArray(bytes, off, len)
    }

    fun writeString(s: CharSequence) {
        writeUTF(s)
    }

    fun writeString(s: CharSequence, off: Int, len: Int) {
        writeUTF(s, off, len)
    }

}