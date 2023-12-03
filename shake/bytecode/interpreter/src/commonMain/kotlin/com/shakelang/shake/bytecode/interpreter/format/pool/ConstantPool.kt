package com.shakelang.shake.bytecode.interpreter.format.pool

import com.shakelang.shake.util.io.streaming.input.DataInputStream
import com.shakelang.shake.util.io.streaming.input.dataStream
import com.shakelang.shake.util.io.streaming.output.ByteArrayOutputStream
import com.shakelang.shake.util.io.streaming.output.DataOutputStream

open class ConstantPool(
    open val entries: List<ConstantPoolEntry>
) : List<ConstantPoolEntry> by entries {

    fun isUtf8(identifier: Int) = entries[identifier] is ConstantPoolEntry.Utf8Constant
    fun isByte(identifier: Int) = entries[identifier] is ConstantPoolEntry.ByteConstant
    fun isShort(identifier: Int) = entries[identifier] is ConstantPoolEntry.ShortConstant
    fun isInt(identifier: Int) = entries[identifier] is ConstantPoolEntry.IntConstant
    fun isLong(identifier: Int) = entries[identifier] is ConstantPoolEntry.LongConstant
    fun isFloat(identifier: Int) = entries[identifier] is ConstantPoolEntry.FloatConstant
    fun isDouble(identifier: Int) = entries[identifier] is ConstantPoolEntry.DoubleConstant
    fun isClass(identifier: Int) = entries[identifier] is ConstantPoolEntry.ClassConstant

    fun getUtf8(identifier: Int) = entries[identifier] as ConstantPoolEntry.Utf8Constant
    fun getByte(identifier: Int) = entries[identifier] as ConstantPoolEntry.ByteConstant
    fun getShort(identifier: Int) = entries[identifier] as ConstantPoolEntry.ShortConstant
    fun getInt(identifier: Int) = entries[identifier] as ConstantPoolEntry.IntConstant
    fun getLong(identifier: Int) = entries[identifier] as ConstantPoolEntry.LongConstant
    fun getFloat(identifier: Int) = entries[identifier] as ConstantPoolEntry.FloatConstant
    fun getDouble(identifier: Int) = entries[identifier] as ConstantPoolEntry.DoubleConstant
    fun getClass(identifier: Int) = entries[identifier] as ConstantPoolEntry.ClassConstant

    fun findUtf8(value: String): Int? {
        for (i in entries.indices) {
            val entry = entries[i]
            if (entry is ConstantPoolEntry.Utf8Constant && entry.value == value) {
                return i
            }
        }
        return null
    }

    fun findByte(value: Byte): Int? {
        for (i in entries.indices) {
            val entry = entries[i]
            if (entry is ConstantPoolEntry.ByteConstant && entry.value == value) {
                return i
            }
        }
        return null
    }

    fun findShort(value: Short): Int? {
        for (i in entries.indices) {
            val entry = entries[i]
            if (entry is ConstantPoolEntry.ShortConstant && entry.value == value) {
                return i
            }
        }
        return null
    }

    fun findInt(value: Int): Int? {
        for (i in entries.indices) {
            val entry = entries[i]
            if (entry is ConstantPoolEntry.IntConstant && entry.value == value) {
                return i
            }
        }
        return null
    }

    fun findLong(value: Long): Int? {
        for (i in entries.indices) {
            val entry = entries[i]
            if (entry is ConstantPoolEntry.LongConstant && entry.value == value) {
                return i
            }
        }
        return null
    }

    fun findFloat(value: Float): Int? {
        for (i in entries.indices) {
            val entry = entries[i]
            if (entry is ConstantPoolEntry.FloatConstant && entry.value == value) {
                return i
            }
        }
        return null
    }

    fun findDouble(value: Double): Int? {
        for (i in entries.indices) {
            val entry = entries[i]
            if (entry is ConstantPoolEntry.DoubleConstant && entry.value == value) {
                return i
            }
        }
        return null
    }

    fun findClass(identifier: Int): Int? {
        for (i in entries.indices) {
            val entry = entries[i]
            if (entry is ConstantPoolEntry.ClassConstant && entry.identifier == identifier) {
                return i
            }
        }
        return null
    }

    fun findClass(name: String): Int? {
        val identifier = findUtf8(name) ?: return null
        return findClass(identifier)
    }

    fun dump(stream: DataOutputStream) {
        stream.writeInt(entries.size)
        for (entry in entries) {
            entry.dump(stream)
        }
    }

    fun dump(): ByteArray {
        val byteStream = ByteArrayOutputStream()
        val stream = DataOutputStream(byteStream)
        dump(stream)
        return byteStream.toByteArray()
    }

    override fun equals(other: Any?): Boolean {
        if (this === other) return true
        if (other !is ConstantPool) return false

        // find matching entries

        // TODO this is not the best way to do this (O(n^2))
        for (entry in entries) {
            if (entry !in other.entries) return false
        }

        for (entry in other.entries) {
            if (entry !in entries) return false
        }

        return true
    }

    companion object {
        fun fromStream(stream: DataInputStream): ConstantPool {
            val entries = mutableListOf<ConstantPoolEntry>()
            val count = stream.readInt()
            for (i in 0 until count) {
                entries.add(ConstantPoolEntry.fromStream(stream))
            }
            return ConstantPool(entries)
        }

        fun fromByteArray(array: ByteArray): ConstantPool {
            return fromStream(array.dataStream())
        }

        fun fromList(list: List<ConstantPoolEntry>): ConstantPool {
            return ConstantPool(list)
        }
    }
}

class MutableConstantPool(
    override val entries: MutableList<ConstantPoolEntry> = mutableListOf()
) : ConstantPool(entries), MutableList<ConstantPoolEntry> by entries {
    fun createUtf8(value: String): Int {
        val entry = ConstantPoolEntry.Utf8Constant(value)
        add(entry)
        return entries.indexOf(entry)
    }

    fun createByte(value: Byte): Int {
        val entry = ConstantPoolEntry.ByteConstant(value)
        add(entry)
        return entries.indexOf(entry)
    }

    fun createShort(value: Short): Int {
        val entry = ConstantPoolEntry.ShortConstant(value)
        add(entry)
        return entries.indexOf(entry)
    }

    fun createInt(value: Int): Int {
        val entry = ConstantPoolEntry.IntConstant(value)
        add(entry)
        return entries.indexOf(entry)
    }

    fun createLong(value: Long): Int {
        val entry = ConstantPoolEntry.LongConstant(value)
        add(entry)
        return entries.indexOf(entry)
    }

    fun createFloat(value: Float): Int {
        val entry = ConstantPoolEntry.FloatConstant(value)
        add(entry)
        return entries.indexOf(entry)
    }

    fun createDouble(value: Double): Int {
        val entry = ConstantPoolEntry.DoubleConstant(value)
        add(entry)
        return entries.indexOf(entry)
    }

    fun createClass(identifier: Int): Int {
        val entry = ConstantPoolEntry.ClassConstant(identifier)
        add(entry)
        return entries.indexOf(entry)
    }

    fun createClass(name: String) = createClass(createUtf8(name))

    fun resolveUtf8(identifier: String) = findUtf8(identifier) ?: createUtf8(identifier)
    fun resolveByte(identifier: Byte) = findByte(identifier) ?: createByte(identifier)
    fun resolveShort(identifier: Short) = findShort(identifier) ?: createShort(identifier)
    fun resolveInt(identifier: Int) = findInt(identifier) ?: createInt(identifier)
    fun resolveLong(identifier: Long) = findLong(identifier) ?: createLong(identifier)
    fun resolveFloat(identifier: Float) = findFloat(identifier) ?: createFloat(identifier)
    fun resolveDouble(identifier: Double) = findDouble(identifier) ?: createDouble(identifier)
    fun resolveClass(identifier: Int) = findClass(identifier) ?: createClass(identifier)
    fun resolveClass(identifier: String) = findClass(identifier) ?: createClass(identifier)

    companion object {
        fun fromStream(stream: DataInputStream): MutableConstantPool {
            val entries = mutableListOf<ConstantPoolEntry>()
            val count = stream.readInt()
            for (i in 0 until count) {
                entries.add(ConstantPoolEntry.fromStream(stream))
            }
            return MutableConstantPool(entries)
        }

        fun fromByteArray(array: ByteArray): MutableConstantPool {
            return fromStream(array.dataStream())
        }

        fun fromList(list: List<ConstantPoolEntry>): MutableConstantPool {
            return MutableConstantPool(list.toMutableList())
        }

        fun fromConstantPool(pool: ConstantPool): MutableConstantPool {
            return MutableConstantPool(pool.entries.toMutableList())
        }
    }

    override fun contains(element: ConstantPoolEntry) = entries.contains(element)
    override fun containsAll(elements: Collection<ConstantPoolEntry>) = entries.containsAll(elements)
    override fun get(index: Int) = entries[index]
    override fun indexOf(element: ConstantPoolEntry) = entries.indexOf(element)
    override fun isEmpty() = entries.isEmpty()
    override fun iterator() = entries.iterator()
    override fun lastIndexOf(element: ConstantPoolEntry) = entries.lastIndexOf(element)
    override fun listIterator() = entries.listIterator()
    override fun listIterator(index: Int) = entries.listIterator(index)
    override val size get() = entries.size

    override fun subList(fromIndex: Int, toIndex: Int) = entries.subList(fromIndex, toIndex)
}
