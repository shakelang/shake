package com.shakelang.shake.bytecode.interpreter.maloc

import com.shakelang.util.primitives.bytes.*

class GlobalMemory {

    val innerSize = 256 * 256

    var memory = arrayOfNulls<ByteArray>(1024)
        private set

    var size: Long = 0
        private set

    fun increaseSize(size: Long) {
        val newSize = this.size + size

        // Space available in the current array

        val spaceAvailable = memory.size.toLong() * innerSize - this.size
        this.size = newSize

        if (spaceAvailable >= size) return
    }

    operator fun set(index: Int, value: ByteArray) {
        value.copyInto(memory, index)
    }

    operator fun get(index: Int): Byte = memory[index]

    operator fun get(index: Int, size: Int): ByteArray {
        val array = ByteArray(size)
        memory.copyInto(array, 0, index, index + size)
        return array
    }

    fun setByte(index: Int, value: Byte) = memory.setByte(index, value)
    fun getByte(index: Int): Byte = memory.getByte(index)
    fun setUnsignedByte(index: Int, value: UByte) = memory.setUnsignedByte(index, value)
    fun getUnsignedByte(index: Int): UByte = memory.getUnsignedByte(index)
    fun setShort(index: Int, value: Short) = memory.setShort(index, value)
    fun setUnsignedShort(index: Int, value: UShort) = memory.setUnsignedShort(index, value)
    fun getUnsignedShort(index: Int): UShort = memory.getUnsignedShort(index)
    fun getShort(index: Int): Short = memory.getShort(index)
    fun setUShort(index: Int, value: UShort) = memory.setUnsignedShort(index, value)
    fun getUShort(index: Int): UShort = memory.getUnsignedShort(index)
    fun setInt(index: Int, value: Int) = memory.setInt(index, value)
    fun getInt(index: Int): Int = memory.getInt(index)
    fun setUnsignedInt(index: Int, value: UInt) = memory.setUnsignedInt(index, value)
    fun getUnsignedInt(index: Int): UInt = memory.getUnsignedInt(index)
    fun setUInt(index: Int, value: UInt) = memory.setUnsignedInt(index, value)
    fun getUInt(index: Int): UInt = memory.getUnsignedInt(index)
    fun setLong(index: Int, value: Long) = memory.setLong(index, value)
    fun getLong(index: Int): Long = memory.getLong(index)
    fun setUnsignedLong(index: Int, value: ULong) = memory.setUnsignedLong(index, value)
    fun getUnsignedLong(index: Int): ULong = memory.getUnsignedLong(index)
    fun setULong(index: Int, value: ULong) = memory.setUnsignedLong(index, value)
    fun getULong(index: Int): ULong = memory.getUnsignedLong(index)
    fun setFloat(index: Int, value: Float) = memory.setFloat(index, value)
    fun getFloat(index: Int): Float = memory.getFloat(index)
    fun setDouble(index: Int, value: Double) = memory.setDouble(index, value)
    fun getDouble(index: Int): Double = memory.getDouble(index)
}
