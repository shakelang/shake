package com.shakelang.shake.bytecode.interpreter.maloc

import com.shakelang.util.primitives.bytes.toBytes
import com.shakelang.util.primitives.bytes.toInt
import com.shakelang.util.primitives.bytes.toLong
import com.shakelang.util.primitives.bytes.toShort

class GlobalMemory {

    var contents: Array<ByteArray> = arrayOf()
        private set

    var outerSize: Long = 0
        private set

    val innerSize: Long = 1024 * 16

    var size: Long = 0
        private set

    operator fun get(outer: Int, inner: Int): Byte {
        return contents[outer][inner]
    }

    operator fun set(outer: Int, inner: Int, value: Byte) {
        contents[outer][inner] = value
    }

    operator fun get(index: Long): Byte {
        return contents[(index / innerSize).toInt()][(index % innerSize).toInt()]
    }

    operator fun set(index: Long, value: Byte) {
        contents[(index / innerSize).toInt()][(index % innerSize).toInt()] = value
    }

    fun grow(blocks: Int) {
        contents = Array(contents.size + blocks) {
            if (it < contents.size) {
                contents[it]
            } else {
                ByteArray(innerSize.toInt())
            }
        }
    }

    fun getBytes(index: Long, length: Long): ByteArray {
        val bytes = ByteArray(length.toInt())
        for (i in 0 until length) bytes[i.toInt()] = this[index + i]
        return bytes
    }

    fun setBytes(index: Long, bytes: ByteArray) {
    }

    fun getByte(index: Long): Byte = this[index]

    fun setByte(index: Long, value: Byte) {
        this[index] = value
    }

    fun getShort(index: Long): Short = getBytes(index, 2).toShort()
    fun setShort(index: Long, value: Short) = setBytes(index, value.toBytes())
    fun getInt(index: Long): Int = getBytes(index, 4).toInt()
    fun setInt(index: Long, value: Int) = setBytes(index, value.toBytes())
    fun getLong(index: Long): Long = getBytes(index, 8).toLong()
    fun setLong(index: Long, value: Long) = setBytes(index, value.toBytes())

    fun getUByte(index: Long): UByte = getByte(index).toUByte()
    fun setUByte(index: Long, value: UByte) = setByte(index, value.toByte())
    fun getUShort(index: Long): UShort = getShort(index).toUShort()
    fun setUShort(index: Long, value: UShort) = setShort(index, value.toShort())
    fun getUInt(index: Long): UInt = getInt(index).toUInt()
    fun setUInt(index: Long, value: UInt) = setInt(index, value.toInt())
    fun getULong(index: Long): ULong = getLong(index).toULong()
    fun setULong(index: Long, value: ULong) = setLong(index, value.toLong())

    fun getBoolean(index: Long): Boolean = getByte(index) != 0.toByte()
    fun setBoolean(index: Long, value: Boolean) = setByte(index, if (value) 1 else 0)

    fun getChar(index: Long): Char = getShort(index).toInt().toChar()
    fun setChar(index: Long, value: Char) = setShort(index, value.code.toShort())

    fun getFloat(index: Long): Float = getInt(index).toFloat()
    fun setFloat(index: Long, value: Float) = setInt(index, value.toInt())

    fun getDouble(index: Long): Double = getLong(index).toDouble()
    fun setDouble(index: Long, value: Double) = setLong(index, value.toLong())
}
