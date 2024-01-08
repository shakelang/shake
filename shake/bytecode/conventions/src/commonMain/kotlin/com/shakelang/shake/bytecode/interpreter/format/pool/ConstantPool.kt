package com.shakelang.shake.bytecode.interpreter.format.pool

import com.shakelang.util.io.streaming.input.DataInputStream
import com.shakelang.util.io.streaming.input.dataStream
import com.shakelang.util.io.streaming.output.ByteArrayOutputStream
import com.shakelang.util.io.streaming.output.DataOutputStream

open class ConstantPoolException(message: String? = null, cause: Throwable? = null) : Exception(message, cause)

open class ConstantPoolOverflowException(message: String? = null, cause: Throwable? = null) :
    ConstantPoolException(message, cause)

open class ConstantPoolTypeException(message: String? = null, cause: Throwable? = null) :
    ConstantPoolException(message, cause)

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
    open val entries: List<ConstantPoolEntry>,

    ) : List<ConstantPoolEntry> by entries {

    /**
     * Check if the constant at the given [index] is a [ConstantPoolEntry.Utf8Constant]
     * @param index The identifier of the constant
     * @return If the constant at the given [index] is a [ConstantPoolEntry.Utf8Constant]
     *
     * @since 0.1.0
     * @version 0.1.0
     */
    fun isUtf8(index: Int) = get(index) is ConstantPoolEntry.Utf8Constant

    /**
     * Check if the constant at the given [index] is a [ConstantPoolEntry.ByteConstant]
     * @param index The identifier of the constant
     * @return If the constant at the given [index] is a [ConstantPoolEntry.ByteConstant]
     *
     * @since 0.1.0
     * @version 0.1.0
     */
    fun isByte(index: Int) = get(index) is ConstantPoolEntry.ByteConstant

    /**
     * Check if the constant at the given [index] is a [ConstantPoolEntry.ShortConstant]
     * @param index The identifier of the constant
     * @return If the constant at the given [index] is a [ConstantPoolEntry.ShortConstant]
     *
     * @since 0.1.0
     * @version 0.1.0
     */
    fun isShort(index: Int) = get(index) is ConstantPoolEntry.ShortConstant

    /**
     * Check if the constant at the given [index] is a [ConstantPoolEntry.IntConstant]
     * @param index The identifier of the constant
     * @return If the constant at the given [index] is a [ConstantPoolEntry.IntConstant]
     *
     * @since 0.1.0
     * @version 0.1.0
     */
    fun isInt(index: Int) = get(index) is ConstantPoolEntry.IntConstant

    /**
     * Check if the constant at the given [index] is a [ConstantPoolEntry.LongConstant]
     * @param index The identifier of the constant
     * @return If the constant at the given [index] is a [ConstantPoolEntry.LongConstant]
     *
     * @since 0.1.0
     * @version 0.1.0
     */
    fun isLong(index: Int) = get(index) is ConstantPoolEntry.LongConstant

    /**
     * Check if the constant at the given [index] is a [ConstantPoolEntry.FloatConstant]
     * @param index The identifier of the constant
     * @return If the constant at the given [index] is a [ConstantPoolEntry.FloatConstant]
     *
     * @since 0.1.0
     * @version 0.1.0
     */
    fun isFloat(index: Int) = get(index) is ConstantPoolEntry.FloatConstant

    /**
     * Check if the constant at the given [index] is a [ConstantPoolEntry.DoubleConstant]
     * @param index The identifier of the constant
     * @return If the constant at the given [index] is a [ConstantPoolEntry.DoubleConstant]
     *
     * @since 0.1.0
     * @version 0.1.0
     */
    fun isDouble(index: Int) = get(index) is ConstantPoolEntry.DoubleConstant

    /**
     * Check if the constant at the given [index] is a [ConstantPoolEntry.ClassConstant]
     * @param index The identifier of the constant
     * @return If the constant at the given [index] is a [ConstantPoolEntry.ClassConstant]
     *
     * @since 0.1.0
     * @version 0.1.0
     */
    fun isClass(index: Int) = get(index) is ConstantPoolEntry.ClassConstant

    /**
     * Check if the constant at the given [index] is a [ConstantPoolEntry.StringConstant]
     * @param index The identifier of the constant
     * @return If the constant at the given [index] is a [ConstantPoolEntry.StringConstant]
     *
     * @since 0.1.0
     * @version 0.1.0
     */
    fun isString(index: Int) = get(index) is ConstantPoolEntry.StringConstant

    override operator fun get(index: Int): ConstantPoolEntry {
        try {
            return entries[index]
        } catch (e: IndexOutOfBoundsException) {
            throw ConstantPoolOverflowException("ConstantPool overflow at $index", e)
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
    fun getUtf8(identifier: Int): ConstantPoolEntry.Utf8Constant {
        if (!isUtf8(identifier)) throw ConstantPoolTypeException("Constant at $identifier is not a Utf8Constant")
        return get(identifier) as ConstantPoolEntry.Utf8Constant
    }

    /**
     * Get the constant at the given [index] as a [ConstantPoolEntry.ByteConstant]
     * @param index The identifier of the constant
     * @return The constant at the given [index] as a [ConstantPoolEntry.ByteConstant]
     *
     * @since 0.1.0
     * @version 0.1.0
     */
    fun getByte(index: Int): ConstantPoolEntry.ByteConstant {
        if (!isByte(index)) throw ConstantPoolTypeException("Constant at $index is not a ByteConstant")
        return get(index) as ConstantPoolEntry.ByteConstant
    }

    /**
     * Get the constant at the given [index] as a [ConstantPoolEntry.ShortConstant]
     * @param index The identifier of the constant
     * @return The constant at the given [index] as a [ConstantPoolEntry.ShortConstant]
     *
     * @since 0.1.0
     * @version 0.1.0
     */
    fun getShort(index: Int): ConstantPoolEntry.ShortConstant {
        if (!isShort(index)) throw ConstantPoolTypeException("Constant at $index is not a ShortConstant")
        return get(index) as ConstantPoolEntry.ShortConstant
    }

    /**
     * Get the constant at the given [index] as a [ConstantPoolEntry.IntConstant]
     * @param index The identifier of the constant
     * @return The constant at the given [index] as a [ConstantPoolEntry.IntConstant]
     *
     * @since 0.1.0
     * @version 0.1.0
     */
    fun getInt(index: Int): ConstantPoolEntry.IntConstant {
        if (!isInt(index)) throw ConstantPoolTypeException("Constant at $index is not a IntConstant")
        return get(index) as ConstantPoolEntry.IntConstant
    }

    /**
     * Get the constant at the given [index] as a [ConstantPoolEntry.LongConstant]
     * @param index The identifier of the constant
     * @return The constant at the given [index] as a [ConstantPoolEntry.LongConstant]
     *
     * @since 0.1.0
     * @version 0.1.0
     */
    fun getLong(index: Int): ConstantPoolEntry.LongConstant {
        if (!isLong(index)) throw ConstantPoolTypeException("Constant at $index is not a LongConstant")
        return get(index) as ConstantPoolEntry.LongConstant
    }

    /**
     * Get the constant at the given [index] as a [ConstantPoolEntry.FloatConstant]
     * @param index The identifier of the constant
     * @return The constant at the given [index] as a [ConstantPoolEntry.FloatConstant]
     *
     * @since 0.1.0
     * @version 0.1.0
     */
    fun getFloat(index: Int): ConstantPoolEntry.FloatConstant {
        if (!isFloat(index)) throw ConstantPoolTypeException("Constant at $index is not a FloatConstant")
        return get(index) as ConstantPoolEntry.FloatConstant
    }

    /**
     * Get the constant at the given [index] as a [ConstantPoolEntry.DoubleConstant]
     * @param index The identifier of the constant
     * @return The constant at the given [index] as a [ConstantPoolEntry.DoubleConstant]
     *
     * @since 0.1.0
     * @version 0.1.0
     */
    fun getDouble(index: Int): ConstantPoolEntry.DoubleConstant {
        if (!isDouble(index)) throw ConstantPoolTypeException("Constant at $index is not a DoubleConstant")
        return get(index) as ConstantPoolEntry.DoubleConstant
    }

    /**
     * Get the constant at the given [index] as a [ConstantPoolEntry.ClassConstant]
     * @param index The identifier of the constant
     * @return The constant at the given [index] as a [ConstantPoolEntry.ClassConstant]
     *
     * @since 0.1.0
     * @version 0.1.0
     */
    fun getClass(index: Int): ConstantPoolEntry.ClassConstant {
        if (!isClass(index)) throw ConstantPoolTypeException("Constant at $index is not a ClassConstant")
        return get(index) as ConstantPoolEntry.ClassConstant
    }

    /**
     * Get the constant at the given [index] as a [ConstantPoolEntry.StringConstant]
     * @param index The identifier of the constant
     * @return The constant at the given [index] as a [ConstantPoolEntry.StringConstant]
     *
     * @since 0.1.0
     * @version 0.1.0
     */
    fun getString(index: Int): ConstantPoolEntry.StringConstant {
        if (!isString(index)) throw ConstantPoolTypeException("Constant at $index is not a StringConstant")
        return get(index) as ConstantPoolEntry.StringConstant
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
     * Find the [ConstantPoolEntry.StringConstant] with the given [value]
     * @param value The value of the [ConstantPoolEntry.StringConstant]
     * @return The index of the [ConstantPoolEntry.StringConstant] with the given [value]
     *
     * @since 0.1.0
     * @version 0.1.0
     */
    fun findString(value: Int): Int? {
        for (i in entries.indices) {
            val entry = entries[i]
            if (entry is ConstantPoolEntry.StringConstant && entry.identifier == value) {
                return i
            }
        }
        return null
    }

    /**
     * Find the [ConstantPoolEntry.StringConstant] with the given [value]
     * @param value The value of the [ConstantPoolEntry.StringConstant]
     * @return The index of the [ConstantPoolEntry.StringConstant] with the given [value]
     *
     * @since 0.1.0
     * @version 0.1.0
     */
    fun findString(value: String): Int? {
        val identifier = findUtf8(value) ?: return null
        return findString(identifier)
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

    fun forEach(action: (ConstantPoolEntry) -> Unit) {
        entries.forEach(action)
    }

    fun forEachIndexed(action: (Int, ConstantPoolEntry) -> Unit) {
        entries.forEachIndexed(action)
    }

    fun forEachUtf8(action: (ConstantPoolEntry.Utf8Constant) -> Unit) {
        entries.forEach {
            if (it is ConstantPoolEntry.Utf8Constant) action(it)
        }
    }

    fun forEachByte(action: (ConstantPoolEntry.ByteConstant) -> Unit) {
        entries.forEach {
            if (it is ConstantPoolEntry.ByteConstant) action(it)
        }
    }

    fun forEachShort(action: (ConstantPoolEntry.ShortConstant) -> Unit) {
        entries.forEach {
            if (it is ConstantPoolEntry.ShortConstant) action(it)
        }
    }

    fun forEachInt(action: (ConstantPoolEntry.IntConstant) -> Unit) {
        entries.forEach {
            if (it is ConstantPoolEntry.IntConstant) action(it)
        }
    }

    fun forEachLong(action: (ConstantPoolEntry.LongConstant) -> Unit) {
        entries.forEach {
            if (it is ConstantPoolEntry.LongConstant) action(it)
        }
    }

    fun forEachFloat(action: (ConstantPoolEntry.FloatConstant) -> Unit) {
        entries.forEach {
            if (it is ConstantPoolEntry.FloatConstant) action(it)
        }
    }

    fun forEachDouble(action: (ConstantPoolEntry.DoubleConstant) -> Unit) {
        entries.forEach {
            if (it is ConstantPoolEntry.DoubleConstant) action(it)
        }
    }

    fun forEachClass(action: (ConstantPoolEntry.ClassConstant) -> Unit) {
        entries.forEach {
            if (it is ConstantPoolEntry.ClassConstant) action(it)
        }
    }

    override fun toString(): String {
        return "ConstantPool(entries=$entries)"
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
    override val entries: MutableList<ConstantPoolEntry> = mutableListOf(),

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
     * Create a [ConstantPoolEntry.StringConstant] with the given [value]
     * @param value The identifier of the [ConstantPoolEntry.StringConstant]
     *
     * @since 0.1.0
     * @version 0.1.0
     */
    fun createString(value: Int): Int {
        val entry = ConstantPoolEntry.StringConstant(value)
        val index = entries.size
        add(entry)
        return index
    }

    /**
     * Create a [ConstantPoolEntry.StringConstant] with the given [value]
     * @param value The name of the [ConstantPoolEntry.StringConstant]
     *
     * @since 0.1.0
     * @version 0.1.0
     */
    fun createString(value: String) = createString(resolveUtf8(value))

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

    /**
     * Resolve the [ConstantPoolEntry.StringConstant] with the given [value]
     * If a [ConstantPoolEntry.StringConstant] with the given [value] does not exist, it will be created
     * @param value The identifier of the [ConstantPoolEntry.StringConstant]
     * @return The index of the [ConstantPoolEntry.StringConstant] with the given [value]
     *
     * @since 0.1.0
     * @version 0.1.0
     */
    fun resolveString(value: Int) = findString(value) ?: createString(value)

    /**
     * Resolve the [ConstantPoolEntry.StringConstant] with the given [value]
     * If a [ConstantPoolEntry.StringConstant] with the given [value] does not exist, it will be created
     * @param value The name of the [ConstantPoolEntry.StringConstant]
     * @return The index of the [ConstantPoolEntry.StringConstant] with the given [value]
     *
     * @since 0.1.0
     * @version 0.1.0
     */
    fun resolveString(value: String) = findString(value) ?: createString(value)

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
