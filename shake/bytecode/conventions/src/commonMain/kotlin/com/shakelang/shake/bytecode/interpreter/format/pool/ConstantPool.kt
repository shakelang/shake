package com.shakelang.shake.bytecode.interpreter.format.pool

import com.shakelang.shake.util.io.streaming.input.DataInputStream
import com.shakelang.shake.util.io.streaming.input.dataStream
import com.shakelang.shake.util.io.streaming.output.ByteArrayOutputStream
import com.shakelang.shake.util.io.streaming.output.DataOutputStream

open class ConstantPoolException(message: String? = null, cause: Throwable? = null) : Exception(message, cause)

open class ConstantPoolOverflowException(message: String? = null, cause: Throwable? = null) : ConstantPoolException(message, cause)
open class ConstantPoolTypeException(message: String? = null, cause: Throwable? = null) : ConstantPoolException(message, cause)

/**
 * A ConstantPool is a list of [ConstantPoolEntry]s
 * We store data inside the [ConstantPool]. This has the advantage that we can reference to the data
 * by an index. Double-used data will only be stored once, and we can reference to it by the index.
 * @param entries The entries of the [ConstantPool]
 *
 * @since 0.1.0
 * @version 0.1.0
 */
open class ConstantPool(

    /**
     * The entries of the [ConstantPool]
     *
     * @since 0.1.0
     * @version 0.1.0
     */
    open val entries: List<ConstantPoolEntry>

) : List<ConstantPoolEntry> by entries {

    /**
     * Check if the constant at the given [identifier] is a [ConstantPoolEntry.Utf8Constant]
     * @param identifier The identifier of the constant
     * @return If the constant at the given [identifier] is a [ConstantPoolEntry.Utf8Constant]
     *
     * @since 0.1.0
     * @version 0.1.0
     */
    fun isUtf8(identifier: Int) = get(identifier) is ConstantPoolEntry.Utf8Constant

    /**
     * Check if the constant at the given [identifier] is a [ConstantPoolEntry.ByteConstant]
     * @param identifier The identifier of the constant
     * @return If the constant at the given [identifier] is a [ConstantPoolEntry.ByteConstant]
     *
     * @since 0.1.0
     * @version 0.1.0
     */
    fun isByte(identifier: Int) = get(identifier) is ConstantPoolEntry.ByteConstant

    /**
     * Check if the constant at the given [identifier] is a [ConstantPoolEntry.ShortConstant]
     * @param identifier The identifier of the constant
     * @return If the constant at the given [identifier] is a [ConstantPoolEntry.ShortConstant]
     *
     * @since 0.1.0
     * @version 0.1.0
     */
    fun isShort(identifier: Int) = get(identifier) is ConstantPoolEntry.ShortConstant

    /**
     * Check if the constant at the given [identifier] is a [ConstantPoolEntry.IntConstant]
     * @param identifier The identifier of the constant
     * @return If the constant at the given [identifier] is a [ConstantPoolEntry.IntConstant]
     *
     * @since 0.1.0
     * @version 0.1.0
     */
    fun isInt(identifier: Int) = get(identifier) is ConstantPoolEntry.IntConstant

    /**
     * Check if the constant at the given [identifier] is a [ConstantPoolEntry.LongConstant]
     * @param identifier The identifier of the constant
     * @return If the constant at the given [identifier] is a [ConstantPoolEntry.LongConstant]
     *
     * @since 0.1.0
     * @version 0.1.0
     */
    fun isLong(identifier: Int) = get(identifier) is ConstantPoolEntry.LongConstant

    /**
     * Check if the constant at the given [identifier] is a [ConstantPoolEntry.FloatConstant]
     * @param identifier The identifier of the constant
     * @return If the constant at the given [identifier] is a [ConstantPoolEntry.FloatConstant]
     *
     * @since 0.1.0
     * @version 0.1.0
     */
    fun isFloat(identifier: Int) = get(identifier) is ConstantPoolEntry.FloatConstant

    /**
     * Check if the constant at the given [identifier] is a [ConstantPoolEntry.DoubleConstant]
     * @param identifier The identifier of the constant
     * @return If the constant at the given [identifier] is a [ConstantPoolEntry.DoubleConstant]
     *
     * @since 0.1.0
     * @version 0.1.0
     */
    fun isDouble(identifier: Int) = get(identifier) is ConstantPoolEntry.DoubleConstant

    /**
     * Check if the constant at the given [identifier] is a [ConstantPoolEntry.ClassConstant]
     * @param identifier The identifier of the constant
     * @return If the constant at the given [identifier] is a [ConstantPoolEntry.ClassConstant]
     *
     * @since 0.1.0
     * @version 0.1.0
     */
    fun isClass(identifier: Int) = get(identifier) is ConstantPoolEntry.ClassConstant

    override operator fun get(identifier: Int) : ConstantPoolEntry {
        try {
            return entries[identifier] as ConstantPoolEntry.ByteConstant
        } catch (e: ClassCastException) {
            throw ConstantPoolOverflowException("ConstantPool is of size ${entries.size} but tried to get entry at $identifier")
        }
    }

    /**
     * Get the constant at the given [identifier] as a [ConstantPoolEntry.Utf8Constant]
     * @param identifier The identifier of the constant
     * @return The constant at the given [identifier] as a [ConstantPoolEntry.Utf8Constant]
     *
     * @since 0.1.0
     * @version 0.1.0
     */
    fun getUtf8(identifier: Int) : ConstantPoolEntry.Utf8Constant {
        if(!isUtf8(identifier)) throw ConstantPoolTypeException("Constant at $identifier is not a Utf8Constant")
        return get(identifier) as ConstantPoolEntry.Utf8Constant
    }

    /**
     * Get the constant at the given [identifier] as a [ConstantPoolEntry.ByteConstant]
     * @param identifier The identifier of the constant
     * @return The constant at the given [identifier] as a [ConstantPoolEntry.ByteConstant]
     *
     * @since 0.1.0
     * @version 0.1.0
     */
    fun getByte(identifier: Int): ConstantPoolEntry.ByteConstant {
        if(!isByte(identifier)) throw ConstantPoolTypeException("Constant at $identifier is not a ByteConstant")
        return get(identifier) as ConstantPoolEntry.ByteConstant
    }
    /**
     * Get the constant at the given [identifier] as a [ConstantPoolEntry.ShortConstant]
     * @param identifier The identifier of the constant
     * @return The constant at the given [identifier] as a [ConstantPoolEntry.ShortConstant]
     *
     * @since 0.1.0
     * @version 0.1.0
     */
    fun getShort(identifier: Int): ConstantPoolEntry.ShortConstant {
        if(!isShort(identifier)) throw ConstantPoolTypeException("Constant at $identifier is not a ShortConstant")
        return get(identifier) as ConstantPoolEntry.ShortConstant
    }

    /**
     * Get the constant at the given [identifier] as a [ConstantPoolEntry.IntConstant]
     * @param identifier The identifier of the constant
     * @return The constant at the given [identifier] as a [ConstantPoolEntry.IntConstant]
     *
     * @since 0.1.0
     * @version 0.1.0
     */
    fun getInt(identifier: Int): ConstantPoolEntry.IntConstant {
        if(!isInt(identifier)) throw ConstantPoolTypeException("Constant at $identifier is not a IntConstant")
        return get(identifier) as ConstantPoolEntry.IntConstant
    }

    /**
     * Get the constant at the given [identifier] as a [ConstantPoolEntry.LongConstant]
     * @param identifier The identifier of the constant
     * @return The constant at the given [identifier] as a [ConstantPoolEntry.LongConstant]
     *
     * @since 0.1.0
     * @version 0.1.0
     */
    fun getLong(identifier: Int): ConstantPoolEntry.LongConstant {
        if(!isLong(identifier)) throw ConstantPoolTypeException("Constant at $identifier is not a LongConstant")
        return get(identifier) as ConstantPoolEntry.LongConstant
    }

    /**
     * Get the constant at the given [identifier] as a [ConstantPoolEntry.FloatConstant]
     * @param identifier The identifier of the constant
     * @return The constant at the given [identifier] as a [ConstantPoolEntry.FloatConstant]
     *
     * @since 0.1.0
     * @version 0.1.0
     */
    fun getFloat(identifier: Int): ConstantPoolEntry.FloatConstant {
        if(!isFloat(identifier)) throw ConstantPoolTypeException("Constant at $identifier is not a FloatConstant")
        return get(identifier) as ConstantPoolEntry.FloatConstant
    }

    /**
     * Get the constant at the given [identifier] as a [ConstantPoolEntry.DoubleConstant]
     * @param identifier The identifier of the constant
     * @return The constant at the given [identifier] as a [ConstantPoolEntry.DoubleConstant]
     *
     * @since 0.1.0
     * @version 0.1.0
     */
    fun getDouble(identifier: Int): ConstantPoolEntry.DoubleConstant {
        if(!isDouble(identifier)) throw ConstantPoolTypeException("Constant at $identifier is not a DoubleConstant")
        return get(identifier) as ConstantPoolEntry.DoubleConstant
    }

    /**
     * Get the constant at the given [identifier] as a [ConstantPoolEntry.ClassConstant]
     * @param identifier The identifier of the constant
     * @return The constant at the given [identifier] as a [ConstantPoolEntry.ClassConstant]
     *
     * @since 0.1.0
     * @version 0.1.0
     */
    fun getClass(identifier: Int): ConstantPoolEntry.ClassConstant {
        if(!isClass(identifier)) throw ConstantPoolTypeException("Constant at $identifier is not a ClassConstant")
        return get(identifier) as ConstantPoolEntry.ClassConstant
    }

    /**
     * Find the [ConstantPoolEntry.Utf8Constant] with the given [value]
     * @param value The value of the [ConstantPoolEntry.Utf8Constant]
     * @return The index of the [ConstantPoolEntry.Utf8Constant] with the given [value]
     *
     * @since 0.1.0
     * @version 0.1.0
     */
    fun findUtf8(value: String): Int? {
        for (i in entries.indices) {
            val entry = entries[i]
            if (entry is ConstantPoolEntry.Utf8Constant && entry.value == value) {
                return i
            }
        }
        return null
    }

    /**
     * Find the [ConstantPoolEntry.ByteConstant] with the given [value]
     * @param value The value of the [ConstantPoolEntry.ByteConstant]
     * @return The index of the [ConstantPoolEntry.ByteConstant] with the given [value]
     *
     * @since 0.1.0
     * @version 0.1.0
     */
    fun findByte(value: Byte): Int? {
        for (i in entries.indices) {
            val entry = entries[i]
            if (entry is ConstantPoolEntry.ByteConstant && entry.value == value) {
                return i
            }
        }
        return null
    }

    /**
     * Find the [ConstantPoolEntry.ShortConstant] with the given [value]
     * @param value The value of the [ConstantPoolEntry.ShortConstant]
     * @return The index of the [ConstantPoolEntry.ShortConstant] with the given [value]
     *
     * @since 0.1.0
     * @version 0.1.0
     */
    fun findShort(value: Short): Int? {
        for (i in entries.indices) {
            val entry = entries[i]
            if (entry is ConstantPoolEntry.ShortConstant && entry.value == value) {
                return i
            }
        }
        return null
    }

    /**
     * Find the [ConstantPoolEntry.IntConstant] with the given [value]
     * @param value The value of the [ConstantPoolEntry.IntConstant]
     * @return The index of the [ConstantPoolEntry.IntConstant] with the given [value]
     *
     * @since 0.1.0
     * @version 0.1.0
     */
    fun findInt(value: Int): Int? {
        for (i in entries.indices) {
            val entry = entries[i]
            if (entry is ConstantPoolEntry.IntConstant && entry.value == value) {
                return i
            }
        }
        return null
    }

    /**
     * Find the [ConstantPoolEntry.LongConstant] with the given [value]
     * @param value The value of the [ConstantPoolEntry.LongConstant]
     * @return The index of the [ConstantPoolEntry.LongConstant] with the given [value]
     *
     * @since 0.1.0
     * @version 0.1.0
     */
    fun findLong(value: Long): Int? {
        for (i in entries.indices) {
            val entry = entries[i]
            if (entry is ConstantPoolEntry.LongConstant && entry.value == value) {
                return i
            }
        }
        return null
    }

    /**
     * Find the [ConstantPoolEntry.FloatConstant] with the given [value]
     * @param value The value of the [ConstantPoolEntry.FloatConstant]
     * @return The index of the [ConstantPoolEntry.FloatConstant] with the given [value]
     *
     * @since 0.1.0
     * @version 0.1.0
     */
    fun findFloat(value: Float): Int? {
        for (i in entries.indices) {
            val entry = entries[i]
            if (entry is ConstantPoolEntry.FloatConstant && entry.value == value) {
                return i
            }
        }
        return null
    }

    /**
     * Find the [ConstantPoolEntry.DoubleConstant] with the given [value]
     * @param value The value of the [ConstantPoolEntry.DoubleConstant]
     * @return The index of the [ConstantPoolEntry.DoubleConstant] with the given [value]
     *
     * @since 0.1.0
     * @version 0.1.0
     */
    fun findDouble(value: Double): Int? {
        for (i in entries.indices) {
            val entry = entries[i]
            if (entry is ConstantPoolEntry.DoubleConstant && entry.value == value) {
                return i
            }
        }
        return null
    }

    /**
     * Find the [ConstantPoolEntry.ClassConstant] with the given [identifier]
     * @param identifier The identifier of the [ConstantPoolEntry.ClassConstant]
     * @return The index of the [ConstantPoolEntry.ClassConstant] with the given [identifier]
     *
     * @since 0.1.0
     * @version 0.1.0
     */
    fun findClass(identifier: Int): Int? {
        for (i in entries.indices) {
            val entry = entries[i]
            if (entry is ConstantPoolEntry.ClassConstant && entry.identifier == identifier) {
                return i
            }
        }
        return null
    }

    /**
     * Find the [ConstantPoolEntry.ClassConstant] with the given [name]
     * @param name The name of the [ConstantPoolEntry.ClassConstant]
     * @return The index of the [ConstantPoolEntry.ClassConstant] with the given [name]
     *
     * @since 0.1.0
     * @version 0.1.0
     */
    fun findClass(name: String): Int? {
        val identifier = findUtf8(name) ?: return null
        return findClass(identifier)
    }

    /**
     * Dump the [ConstantPool] to the given [stream]
     * @param stream The [DataOutputStream] to dump the [ConstantPool] to
     *
     * @since 0.1.0
     * @version 0.1.0
     */
    fun dump(stream: DataOutputStream) {
        stream.writeInt(entries.size)
        for (entry in entries) {
            entry.dump(stream)
        }
    }

    /**
     * Dump the [ConstantPool] to a [ByteArray]
     * @return The [ByteArray] of the dumped [ConstantPool]
     *
     * @since 0.1.0
     * @version 0.1.0
     */
    fun dump(): ByteArray {
        val byteStream = ByteArrayOutputStream()
        val stream = DataOutputStream(byteStream)
        dump(stream)
        return byteStream.toByteArray()
    }

    /**
     * Check if the [ConstantPool] is equal to the given [other] object
     * @param other The other object
     * @return If the [ConstantPool] is equal to the given [other] object
     *
     * @since 0.1.0
     * @version 0.1.0
     */
    override fun equals(other: Any?): Boolean {
        if (this === other) return true
        if (other !is ConstantPool) return false

        return entries.mapIndexed { index, entry -> index to entry }.all { (index, entry) ->
            other.entries[index] == entry
        }
    }

    override fun hashCode(): Int {
        return entries.hashCode()
    }

    companion object {

        /**
         * Create a [ConstantPool] from the given [stream]
         * @param stream The [DataInputStream] to read the [ConstantPool] from
         * @return The [ConstantPool] read from the [stream]
         *
         * @since 0.1.0
         * @version 0.1.0
         */
        fun fromStream(stream: DataInputStream): ConstantPool {
            val entries = mutableListOf<ConstantPoolEntry>()
            val count = stream.readInt()
            for (i in 0 until count) {
                entries.add(ConstantPoolEntry.fromStream(stream))
            }
            return ConstantPool(entries)
        }

        /**
         * Create a [ConstantPool] from the given [ByteArray]
         * @param array The [ByteArray] to read the [ConstantPool] from
         * @return The [ConstantPool] read from the [ByteArray]
         *
         * @since 0.1.0
         * @version 0.1.0
         */
        fun fromByteArray(array: ByteArray): ConstantPool {
            return fromStream(array.dataStream())
        }

        /**
         * Create a [ConstantPool] from the given [List]
         * @param list The [List] to create the [ConstantPool] from
         * @return The [ConstantPool] created from the [List]
         */
        fun fromList(list: List<ConstantPoolEntry>): ConstantPool {
            return ConstantPool(list)
        }
    }
}

/**
 * A [MutableConstantPool] is a [ConstantPool] with the ability to add and remove [ConstantPoolEntry]s
 * @param entries The entries of the [MutableConstantPool]
 *
 * @since 0.1.0
 * @version 0.1.0
 */
class MutableConstantPool(

    /**
     * The entries of the [MutableConstantPool]
     *
     * @since 0.1.0
     * @version 0.1.0
     */
    override val entries: MutableList<ConstantPoolEntry> = mutableListOf()

) : ConstantPool(entries), MutableList<ConstantPoolEntry> by entries {

    /**
     * Create a [ConstantPoolEntry.Utf8Constant] with the given [value]
     * @param value The value of the [ConstantPoolEntry.Utf8Constant]
     *
     * @since 0.1.0
     * @version 0.1.0
     */
    fun createUtf8(value: String): Int {
        val entry = ConstantPoolEntry.Utf8Constant(value)
        val index = entries.size
        add(entry)
        return index
    }

    /**
     * Create a [ConstantPoolEntry.ByteConstant] with the given [value]
     * @param value The value of the [ConstantPoolEntry.ByteConstant]
     *
     * @since 0.1.0
     * @version 0.1.0
     */
    fun createByte(value: Byte): Int {
        val entry = ConstantPoolEntry.ByteConstant(value)
        val index = entries.size
        add(entry)
        return index
    }

    /**
     * Create a [ConstantPoolEntry.ShortConstant] with the given [value]
     * @param value The value of the [ConstantPoolEntry.ShortConstant]
     *
     * @since 0.1.0
     * @version 0.1.0
     */
    fun createShort(value: Short): Int {
        val entry = ConstantPoolEntry.ShortConstant(value)
        val index = entries.size
        add(entry)
        return index
    }

    /**
     * Create a [ConstantPoolEntry.IntConstant] with the given [value]
     * @param value The value of the [ConstantPoolEntry.IntConstant]
     *
     * @since 0.1.0
     * @version 0.1.0
     */
    fun createInt(value: Int): Int {
        val entry = ConstantPoolEntry.IntConstant(value)
        val index = entries.size
        add(entry)
        return index
    }

    /**
     * Create a [ConstantPoolEntry.LongConstant] with the given [value]
     * @param value The value of the [ConstantPoolEntry.LongConstant]
     *
     * @since 0.1.0
     * @version 0.1.0
     */
    fun createLong(value: Long): Int {
        val entry = ConstantPoolEntry.LongConstant(value)
        val index = entries.size
        add(entry)
        return index
    }

    /**
     * Create a [ConstantPoolEntry.FloatConstant] with the given [value]
     * @param value The value of the [ConstantPoolEntry.FloatConstant]
     *
     * @since 0.1.0
     * @version 0.1.0
     */
    fun createFloat(value: Float): Int {
        val entry = ConstantPoolEntry.FloatConstant(value)
        val index = entries.size
        add(entry)
        return index
    }

    /**
     * Create a [ConstantPoolEntry.DoubleConstant] with the given [value]
     * @param value The value of the [ConstantPoolEntry.DoubleConstant]
     *
     * @since 0.1.0
     * @version 0.1.0
     */
    fun createDouble(value: Double): Int {
        val entry = ConstantPoolEntry.DoubleConstant(value)
        val index = entries.size
        add(entry)
        return index
    }

    /**
     * Create a [ConstantPoolEntry.ClassConstant] with the given [value]
     * @param value The identifier of the [ConstantPoolEntry.ClassConstant]
     *
     * @since 0.1.0
     * @version 0.1.0
     */
    fun createClass(value: Int): Int {
        val entry = ConstantPoolEntry.ClassConstant(value)
        val index = entries.size
        add(entry)
        return index
    }

    /**
     * Create a [ConstantPoolEntry.ClassConstant] with the given [value]
     * @param value The name of the [ConstantPoolEntry.ClassConstant]
     *
     * @since 0.1.0
     * @version 0.1.0
     */
    fun createClass(value: String) = createClass(resolveUtf8(value))

    /**
     * Resolve the [ConstantPoolEntry.Utf8Constant] with the given [value]
     * If a [ConstantPoolEntry.Utf8Constant] with the given [value] does not exist, it will be created
     * @param value The identifier of the [ConstantPoolEntry.Utf8Constant]
     * @return The index of the [ConstantPoolEntry.Utf8Constant] with the given [value]
     *
     * @since 0.1.0
     * @version 0.1.0
     */
    fun resolveUtf8(value: String) = findUtf8(value) ?: createUtf8(value)

    /**
     * Resolve the [ConstantPoolEntry.ByteConstant] with the given [value]
     * If a [ConstantPoolEntry.ByteConstant] with the given [value] does not exist, it will be created
     * @param value The identifier of the [ConstantPoolEntry.ByteConstant]
     * @return The index of the [ConstantPoolEntry.ByteConstant] with the given [value]
     *
     * @since 0.1.0
     * @version 0.1.0
     */
    fun resolveByte(value: Byte) = findByte(value) ?: createByte(value)

    /**
     * Resolve the [ConstantPoolEntry.ShortConstant] with the given [value]
     * If a [ConstantPoolEntry.ShortConstant] with the given [value] does not exist, it will be created
     * @param value The identifier of the [ConstantPoolEntry.ShortConstant]
     * @return The index of the [ConstantPoolEntry.ShortConstant] with the given [value]
     *
     * @since 0.1.0
     * @version 0.1.0
     */
    fun resolveShort(value: Short) = findShort(value) ?: createShort(value)

    /**
     * Resolve the [ConstantPoolEntry.IntConstant] with the given [value]
     * If a [ConstantPoolEntry.IntConstant] with the given [value] does not exist, it will be created
     * @param value The identifier of the [ConstantPoolEntry.IntConstant]
     * @return The index of the [ConstantPoolEntry.IntConstant] with the given [value]
     *
     * @since 0.1.0
     * @version 0.1.0
     */
    fun resolveInt(value: Int) = findInt(value) ?: createInt(value)

    /**
     * Resolve the [ConstantPoolEntry.LongConstant] with the given [value]
     * If a [ConstantPoolEntry.LongConstant] with the given [value] does not exist, it will be created
     * @param value The identifier of the [ConstantPoolEntry.LongConstant]
     * @return The index of the [ConstantPoolEntry.LongConstant] with the given [value]
     *
     * @since 0.1.0
     * @version 0.1.0
     */
    fun resolveLong(value: Long) = findLong(value) ?: createLong(value)

    /**
     * Resolve the [ConstantPoolEntry.FloatConstant] with the given [value]
     * If a [ConstantPoolEntry.FloatConstant] with the given [value] does not exist, it will be created
     * @param value The identifier of the [ConstantPoolEntry.FloatConstant]
     * @return The index of the [ConstantPoolEntry.FloatConstant] with the given [value]
     *
     * @since 0.1.0
     * @version 0.1.0
     */
    fun resolveFloat(value: Float) = findFloat(value) ?: createFloat(value)

    /**
     * Resolve the [ConstantPoolEntry.DoubleConstant] with the given [value]
     * If a [ConstantPoolEntry.DoubleConstant] with the given [value] does not exist, it will be created
     * @param value The identifier of the [ConstantPoolEntry.DoubleConstant]
     * @return The index of the [ConstantPoolEntry.DoubleConstant] with the given [value]
     *
     * @since 0.1.0
     * @version 0.1.0
     */
    fun resolveDouble(value: Double) = findDouble(value) ?: createDouble(value)

    /**
     * Resolve the [ConstantPoolEntry.ClassConstant] with the given [value]
     * If a [ConstantPoolEntry.ClassConstant] with the given [value] does not exist, it will be created
     * @param value The identifier of the [ConstantPoolEntry.ClassConstant]
     * @return The index of the [ConstantPoolEntry.ClassConstant] with the given [value]
     *
     * @since 0.1.0
     * @version 0.1.0
     */
    fun resolveClass(value: Int) = findClass(value) ?: createClass(value)

    /**
     * Resolve the [ConstantPoolEntry.ClassConstant] with the given [value]
     * If a [ConstantPoolEntry.ClassConstant] with the given [value] does not exist, it will be created
     * @param value The name of the [ConstantPoolEntry.ClassConstant]
     * @return The index of the [ConstantPoolEntry.ClassConstant] with the given [value]
     *
     * @since 0.1.0
     * @version 0.1.0
     */
    fun resolveClass(value: String) = findClass(value) ?: createClass(value)

    companion object {

        /**
         * Create a [MutableConstantPool] from the given [stream]
         * @param stream The [DataInputStream] to read the [MutableConstantPool] from
         * @return The [MutableConstantPool] read from the [stream]
         *
         * @since 0.1.0
         * @version 0.1.0
         */
        fun fromStream(stream: DataInputStream): MutableConstantPool {
            val entries = mutableListOf<ConstantPoolEntry>()
            val count = stream.readInt()
            for (i in 0 until count) {
                entries.add(ConstantPoolEntry.fromStream(stream))
            }
            return MutableConstantPool(entries)
        }

        /**
         * Create a [MutableConstantPool] from the given [ByteArray]
         * @param array The [ByteArray] to read the [MutableConstantPool] from
         * @return The [MutableConstantPool] read from the [ByteArray]
         *
         * @since 0.1.0
         * @version 0.1.0
         */
        fun fromByteArray(array: ByteArray): MutableConstantPool {
            return fromStream(array.dataStream())
        }

        /**
         * Create a [MutableConstantPool] from the given [List]
         * @param list The [List] to create the [MutableConstantPool] from
         * @return The [MutableConstantPool] created from the [List]
         */
        fun fromList(list: List<ConstantPoolEntry>): MutableConstantPool {
            return MutableConstantPool(list.toMutableList())
        }

        /**
         * Create a [MutableConstantPool] from the given [ConstantPool]
         * @param pool The [ConstantPool] to create the [MutableConstantPool] from
         * @return The [MutableConstantPool] created from the [ConstantPool]
         */
        fun fromConstantPool(pool: ConstantPool): MutableConstantPool {
            return MutableConstantPool(pool.entries.toMutableList())
        }
    }
}
