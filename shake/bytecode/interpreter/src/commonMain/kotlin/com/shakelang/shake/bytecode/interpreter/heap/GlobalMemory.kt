package com.shakelang.shake.bytecode.interpreter.heap

import com.shakelang.util.primitives.bytes.*

class OutOfRangeException(message: String) : RuntimeException(message)

class GlobalMemory {

    var contents: Array<ByteArray> = arrayOf()
        private set

    var outerSize: Int = 0
        private set

    val innerSize: Int = 1024 * 16

    var size: Long = 0
        private set

    operator fun get(outer: Int, inner: Int): Byte {
        return contents[outer][inner]
    }

    operator fun set(outer: Int, inner: Int, value: Byte) {
        contents[outer][inner] = value
    }

    operator fun get(pointer: Long): Byte {
        if (!contains(pointer)) throw OutOfRangeException("Cannot read byte at $pointer")
        val index = pointerToIndex(pointer)
        return get((index / innerSize).toInt(), (index % innerSize).toInt())
    }

    operator fun set(pointer: Long, value: Byte) {
        if (!contains(pointer)) throw OutOfRangeException("Cannot write byte at ${pointer.toBytes().toHexString()}")
        val index = pointerToIndex(pointer)
        set((index / innerSize).toInt(), (index % innerSize).toInt(), value)
    }

    fun grow(blocks: Int) {
        outerSize += blocks
        size += blocks * innerSize
        contents = Array(contents.size + blocks) {
            if (it < contents.size) {
                contents[it]
            } else {
                ByteArray(innerSize)
            }
        }
    }

    fun getBytes(pointer: Long, length: Long): ByteArray {
        val bytes = ByteArray(length.toInt())
        for (i in 0 until length) bytes[i.toInt()] = this[pointer + i]
        return bytes
    }

    fun setBytes(pointer: Long, bytes: ByteArray) {
        for (i in bytes.indices) this[pointer + i] = bytes[i]
    }

    fun getByte(pointer: Long): Byte = this[pointer]

    fun setByte(pointer: Long, value: Byte) {
        this[pointer] = value
    }

    fun getShort(pointer: Long): Short = getBytes(pointer, 2).toShort()
    fun setShort(pointer: Long, value: Short) = setBytes(pointer, value.toBytes())
    fun getInt(pointer: Long): Int = getBytes(pointer, 4).toInt()
    fun setInt(pointer: Long, value: Int) = setBytes(pointer, value.toBytes())
    fun getLong(pointer: Long): Long = getBytes(pointer, 8).toLong()
    fun setLong(pointer: Long, value: Long) = setBytes(pointer, value.toBytes())

    fun getUByte(pointer: Long): UByte = getByte(pointer).toUByte()
    fun setUByte(pointer: Long, value: UByte) = setByte(pointer, value.toByte())
    fun getUShort(pointer: Long): UShort = getShort(pointer).toUShort()
    fun setUShort(pointer: Long, value: UShort) = setShort(pointer, value.toShort())
    fun getUInt(pointer: Long): UInt = getInt(pointer).toUInt()
    fun setUInt(pointer: Long, value: UInt) = setInt(pointer, value.toInt())
    fun getULong(pointer: Long): ULong = getLong(pointer).toULong()
    fun setULong(pointer: Long, value: ULong) = setLong(pointer, value.toLong())

    fun getBoolean(pointer: Long): Boolean = getByte(pointer) != 0.toByte()
    fun setBoolean(pointer: Long, value: Boolean) = setByte(pointer, if (value) 1 else 0)

    fun getChar(pointer: Long): Char = getShort(pointer).toInt().toChar()
    fun setChar(pointer: Long, value: Char) = setShort(pointer, value.code.toShort())

    fun getFloat(pointer: Long): Float = getInt(pointer).toFloat()
    fun setFloat(pointer: Long, value: Float) = setInt(pointer, value.toInt())

    fun getDouble(pointer: Long): Double = getLong(pointer).toDouble()
    fun setDouble(pointer: Long, value: Double) = setLong(pointer, value.toLong())
    fun isPointer(pointer: Long): Boolean = Companion.isPointer(getLong(pointer))

    fun getPointer(pointer: Long): Long = getLong(pointer)
    fun setPointer(pointer: Long, value: Long) = setLong(pointer, value)

    fun contains(pointer: Long): Boolean {
        val index = pointerToIndex(pointer)
        return index in 0..<size
    }

    companion object {
        // Let's define how our pointers will look like:
        // A pointer should start with 011110001011 (0x78B)
        // Then we have 4 bits (16 values) for storing additional
        // information about the pointer
        // Then we have 56 bits (2^52 values) for storing the actual
        // pointer.
        // This is enough to address 4 PiB of memory, which is a lot,
        // so we should be fine for a while
        const val POINTER_BASE: Long = 0x78B00000_00000000L

        fun pointerToIndex(pointer: Long): Long = pointer and 0x0000_FFFF_FFFF_FFFFL
        fun indexToPointer(index: Long): Long = POINTER_BASE or index
        fun isPointer(pointer: Long): Boolean = pointer == -1L || (pointer and 0xFFF0_0000_0000_0000uL.toLong() == POINTER_BASE)
    }
}
