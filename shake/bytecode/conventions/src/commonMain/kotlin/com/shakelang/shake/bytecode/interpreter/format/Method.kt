package com.shakelang.shake.bytecode.interpreter.format

import com.shakelang.shake.bytecode.interpreter.format.attribute.Attribute
import com.shakelang.shake.bytecode.interpreter.format.attribute.MutableAttribute
import com.shakelang.shake.bytecode.interpreter.format.pool.ConstantPool
import com.shakelang.shake.bytecode.interpreter.format.pool.MutableConstantPool
import com.shakelang.util.io.streaming.input.DataInputStream
import com.shakelang.util.io.streaming.output.ByteArrayOutputStream
import com.shakelang.util.io.streaming.output.DataOutputStream
import kotlin.experimental.and
import kotlin.experimental.or

/**
 * A class representing a method in the bytecode
 *
 * [Method Specification](https://spec.shakelang.com/bytecode/storage-format/#methods)
 *
 * @constructor Creates a [Method] with the given [pool], [qualifiedNameConstant], [flags] and [attributes]
 *
 * @property pool The [ConstantPool] of the method
 * @property qualifiedNameConstant The constant of the qualified name of the method
 * @property flags The flags of the method
 * @property attributes The attributes of the method
 *
 * @param pool The [ConstantPool] of the method
 * @param qualifiedNameConstant The constant of the qualified name of the method
 * @param flags The flags of the method
 * @param attributes The attributes of the method
 *
 * @since 0.1.0
 * @version 0.1.0
 */
open class Method(

    /**
     * The [ConstantPool] of the bytecode
     *
     * [Specification](https://spec.shakelang.com/bytecode/storage-format/#constant-pool)
     *
     * @see ConstantPool
     * @see MutableConstantPool
     *
     * @since 0.1.0
     * @version 0.1.0
     */
    open val pool: ConstantPool,

    /**
     * The index of an utf8 constant in the [ConstantPool] that represents the qualified name of the method
     *
     * [Specification](https://spec.shakelang.com/bytecode/storage-format/#method-qualified-name-index)
     *
     * @since 0.1.0
     * @version 0.1.0
     */
    open val qualifiedNameConstant: Int,

    /**
     * The flags of the method
     *
     * [Specification](https://spec.shakelang.com/bytecode/storage-format/#method-flags)
     *
     * @since 0.1.0
     * @version 0.1.0
     */
    open val flags: Short,

    /**
     * The attributes of the method
     *
     * [Specification](https://spec.shakelang.com/bytecode/storage-format/#method-attributes)
     *
     * @since 0.1.0
     * @version 0.1.0
     */
    open val attributes: List<Attribute>,
) {

    /**
     * Returns if the method is public
     *
     * [Specification](https://spec.shakelang.com/bytecode/storage-format/#method-flags)
     *
     * @see [flags]
     * @since 0.1.0
     * @version 0.1.0
     */
    open val isPublic: Boolean
        get() = flags and 0b00000000_00000001.toShort() != 0.toShort()

    /**
     * Returns if the method is private
     *
     * [Specification](https://spec.shakelang.com/bytecode/storage-format/#method-flags)
     *
     * @see [flags]
     * @since 0.1.0
     * @version 0.1.0
     */
    open val isPrivate: Boolean
        get() = flags and 0b00000000_00000010.toShort() != 0.toShort()

    /**
     * Returns if the method is protected
     *
     * [Specification](https://spec.shakelang.com/bytecode/storage-format/#method-flags)
     *
     * @see [flags]
     * @since 0.1.0
     * @version 0.1.0
     */
    open val isProtected: Boolean
        get() = flags and 0b00000000_00000100.toShort() != 0.toShort()

    /**
     * Returns if the method is static
     *
     * [Specification](https://spec.shakelang.com/bytecode/storage-format/#method-flags)
     *
     * @see [flags]
     * @since 0.1.0
     * @version 0.1.0
     */
    open val isStatic: Boolean
        get() = flags and 0b00000000_00001000.toShort() != 0.toShort()

    /**
     * Returns if the method is final
     *
     * [Specification](https://spec.shakelang.com/bytecode/storage-format/#method-flags)
     *
     * @see [flags]
     * @since 0.1.0
     * @version 0.1.0
     */
    open val isFinal: Boolean
        get() = flags and 0b00000000_00010000.toShort() != 0.toShort()

    /**
     * Returns if the method is synchronized
     *
     * [Specification](https://spec.shakelang.com/bytecode/storage-format/#method-flags)
     *
     * @see [flags]
     * @since 0.1.0
     * @version 0.1.0
     */
    open val isSynchronized: Boolean
        get() = flags and 0b00000000_00100000.toShort() != 0.toShort()

    /**
     * Returns if the method is native
     *
     * [Specification](https://spec.shakelang.com/bytecode/storage-format/#method-flags)
     *
     * @see [flags]
     * @since 0.1.0
     * @version 0.1.0
     */
    open val isNative: Boolean
        get() = flags and 0b00000000_01000000.toShort() != 0.toShort()

    /**
     * Returns if the method is abstract
     *
     * [Specification](https://spec.shakelang.com/bytecode/storage-format/#method-flags)
     *
     * @see [flags]
     * @since 0.1.0
     * @version 0.1.0
     */
    open val isAbstract: Boolean
        get() = flags and 0b00000000_10000000.toShort() != 0.toShort()

    /**
     * Returns if the method is strict
     *
     * [Specification](https://spec.shakelang.com/bytecode/storage-format/#method-flags)
     *
     * @see [flags]
     * @since 0.1.0
     * @version 0.1.0
     */
    open val isStrict: Boolean
        get() = flags and 0b00000001_00000000.toShort() != 0.toShort()

    /**
     * Returns the qualified name of the method
     *
     * [Specification](https://spec.shakelang.com/bytecode/storage-format/#method-qualified-name-index)
     *
     * @see [qualifiedNameConstant]
     * @see [pool]
     * @since 0.1.0
     * @version 0.1.0
     */
    open val qualifiedName: String get() = pool.getUtf8(qualifiedNameConstant).value

    /**
     * Dumps the [Method] to the given [stream]
     *
     * [Specification](https://spec.shakelang.com/bytecode/storage-format/#methods)
     *
     * @param stream The [DataOutputStream] to write to
     *
     * @see [dump]
     * @see [dump]
     * @since 0.1.0
     * @version 0.1.0
     */
    fun dump(stream: DataOutputStream) {
        stream.writeInt(qualifiedNameConstant)
        stream.writeShort(flags)
        stream.writeShort(attributes.size.toShort())
        for (attribute in attributes) {
            attribute.dump(stream)
        }
    }

    /**
     * Dumps the [Method] to a [ByteArray]
     *
     * [Specification](https://spec.shakelang.com/bytecode/storage-format/#methods)
     *
     * @see [dump]
     * @see [dump]
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
     * Returns if the given [other] object is equal to this [Method]
     *
     * @param other The other object
     *
     * @see [equals]
     * @see [equals]
     * @since 0.1.0
     * @version 0.1.0
     */
    override fun equals(other: Any?): Boolean {
        if (this === other) return true
        if (other !is Method) return false

        if (pool != other.pool) return false
        if (qualifiedNameConstant != other.qualifiedNameConstant) return false
        if (flags != other.flags) return false

        // TODO this is not the best way to do this (O(n^2))

        // find matching attributes

        for (attribute in attributes) {
            if (attribute !in other.attributes) return false
        }

        for (attribute in other.attributes) {
            if (attribute !in attributes) return false
        }

        return true
    }

    /**
     * Returns the hash code of this [Method]
     *
     * @see [hashCode]
     * @see [hashCode]
     * @since 0.1.0
     * @version 0.1.0
     */
    override fun hashCode(): Int {
        var result = pool.hashCode()
        result = 31 * result + qualifiedNameConstant
        result = 31 * result + flags
        result = 31 * result + attributes.hashCode()
        return result
    }

    companion object {

        /**
         * Reads a [Method] from the given [stream]
         *
         * [Specification](https://spec.shakelang.com/bytecode/storage-format/#methods)
         *
         * @param pool The [ConstantPool] of the method
         * @param stream The [DataInputStream] to read from
         *
         * @since 0.1.0
         * @version 0.1.0
         */
        fun fromStream(pool: ConstantPool, stream: DataInputStream): Method {
            val qualifiedName = stream.readInt()
            val flags = stream.readShort()
            val attributeCount = stream.readShort().toInt()
            val attributes = (0 until attributeCount).map { Attribute.fromStream(pool, stream) }
            return Method(pool, qualifiedName, flags, attributes)
        }
    }
}

/**
 * A mutable version of [Method]
 *
 * @see [Method]
 *
 * @constructor Creates a [MutableMethod] with the given [pool], [qualifiedNameConstant], [flags] and [attributes]
 *
 * @property pool The [MutableConstantPool] of the method
 * @property qualifiedNameConstant The constant of the qualified name of the method
 * @property flags The flags of the method
 * @property attributes The attributes of the method
 *
 * @param pool The [MutableConstantPool] of the method
 * @param qualifiedNameConstant The constant of the qualified name of the method
 * @param flags The flags of the method
 * @param attributes The attributes of the method
 *
 * @since 0.1.0
 * @version 0.1.0
 */
class MutableMethod(
    pool: MutableConstantPool,

    /**
     * The index of an utf8 constant in the [ConstantPool] that represents the qualified name of the method
     *
     * [Specification](https://spec.shakelang.com/bytecode/storage-format/#method-qualified-name-index)
     *
     * @since 0.1.0
     * @version 0.1.0
     */
    override var qualifiedNameConstant: Int,

    /**
     * The flags of the method
     *
     * [Specification](https://spec.shakelang.com/bytecode/storage-format/#method-flags)
     *
     * @since 0.1.0
     * @version 0.1.0
     */
    override var flags: Short,
    attributes: MutableList<Attribute>,
) : Method(pool, qualifiedNameConstant, flags, attributes) {

    /**
     * The [MutableConstantPool] of the bytecode
     *
     * [Specification](https://spec.shakelang.com/bytecode/storage-format/#constant-pool)
     *
     * This function is just a cast of the [pool] property
     * This is a safe cast, because the [pool] property is always a [MutableConstantPool]
     * (As the only constructor of this class requires a [MutableConstantPool])
     *
     * @see ConstantPool
     * @see MutableConstantPool
     *
     * @since 0.1.0
     * @version 0.1.0
     */
    override val pool: MutableConstantPool
        get() = super.pool as MutableConstantPool

    /**
     * The qualified name of the method
     *
     * [Specification](https://spec.shakelang.com/bytecode/storage-format/#method-qualified-name-index)
     *
     * @see [qualifiedNameConstant]
     * @see [pool]
     * @since 0.1.0
     * @version 0.1.0
     */
    override var qualifiedName: String
        get() = pool.getUtf8(qualifiedNameConstant).value
        set(value) {
            qualifiedNameConstant = pool.resolveUtf8(value)
        }

    /**
     * The attributes of the method
     *
     * [Specification](https://spec.shakelang.com/bytecode/storage-format/#method-attributes)
     *
     * This function is just a cast of the [attributes] property
     * This is a safe cast, because the [attributes] property is always a [MutableList]
     * (As the only constructor of this class requires a [MutableList] of [MutableAttribute])
     *
     * @since 0.1.0
     * @version 0.1.0
     */
    @Suppress("UNCHECKED_CAST")
    override val attributes: MutableList<MutableAttribute>
        get() = super.attributes as MutableList<MutableAttribute>

    /**
     * Is this method public?
     *
     * [Specification](https://spec.shakelang.com/bytecode/storage-format/#method-flags)
     *
     * @see [flags]
     * @since 0.1.0
     * @version 0.1.0
     */
    override var isPublic: Boolean
        get() = flags and 0b00000000_00000001.toShort() != 0.toShort()
        set(value) {
            flags = if (value) {
                flags or 0b00000000_00000001.toShort()
            } else {
                flags and 0b11111111_11111110.toShort()
            }
        }

    /**
     * Is this method private?
     *
     * [Specification](https://spec.shakelang.com/bytecode/storage-format/#method-flags)
     *
     * @see [flags]
     * @since 0.1.0
     * @version 0.1.0
     */
    override var isPrivate: Boolean
        get() = flags and 0b00000000_00000010.toShort() != 0.toShort()
        set(value) {
            flags = if (value) {
                flags or 0b00000000_00000010.toShort()
            } else {
                flags and 0b11111111_11111101.toShort()
            }
        }

    /**
     * Is this method protected?
     *
     * [Specification](https://spec.shakelang.com/bytecode/storage-format/#method-flags)
     *
     * @see [flags]
     * @since 0.1.0
     * @version 0.1.0
     */
    override var isProtected: Boolean
        get() = flags and 0b00000000_00000100.toShort() != 0.toShort()
        set(value) {
            flags = if (value) {
                flags or 0b00000000_00000100.toShort()
            } else {
                flags and 0b11111111_11111011.toShort()
            }
        }

    /**
     * Is this method static?
     *
     * [Specification](https://spec.shakelang.com/bytecode/storage-format/#method-flags)
     *
     * @see [flags]
     * @since 0.1.0
     * @version 0.1.0
     */
    override var isStatic: Boolean
        get() = flags and 0b00000000_00001000.toShort() != 0.toShort()
        set(value) {
            flags = if (value) {
                flags or 0b00000000_00001000.toShort()
            } else {
                flags and 0b11111111_11110111.toShort()
            }
        }

    /**
     * Is this method final?
     *
     * [Specification](https://spec.shakelang.com/bytecode/storage-format/#method-flags)
     *
     * @see [flags]
     * @since 0.1.0
     * @version 0.1.0
     */
    override var isFinal: Boolean
        get() = flags and 0b00000000_00010000.toShort() != 0.toShort()
        set(value) {
            flags = if (value) {
                flags or 0b00000000_00010000.toShort()
            } else {
                flags and 0b11111111_11101111.toShort()
            }
        }

    /**
     * Is this method synchronized?
     *
     * [Specification](https://spec.shakelang.com/bytecode/storage-format/#method-flags)
     *
     * @see [flags]
     * @since 0.1.0
     * @version 0.1.0
     */
    override var isSynchronized: Boolean
        get() = flags and 0b00000000_00100000.toShort() != 0.toShort()
        set(value) {
            flags = if (value) {
                flags or 0b00000000_00100000.toShort()
            } else {
                flags and 0b11111111_11011111.toShort()
            }
        }

    /**
     * Is this method native?
     *
     * [Specification](https://spec.shakelang.com/bytecode/storage-format/#method-flags)
     *
     * @see [flags]
     * @since 0.1.0
     * @version 0.1.0
     */
    override var isNative: Boolean
        get() = flags and 0b00000000_01000000.toShort() != 0.toShort()
        set(value) {
            flags = if (value) {
                flags or 0b00000000_01000000.toShort()
            } else {
                flags and 0b11111111_10111111.toShort()
            }
        }

    /**
     * Is this method abstract?
     *
     * [Specification](https://spec.shakelang.com/bytecode/storage-format/#method-flags)
     *
     * @see [flags]
     * @see [isAbstract]
     * @since 0.1.0
     * @version 0.1.0
     */
    override var isAbstract: Boolean
        get() = flags and 0b00000000_10000000.toShort() != 0.toShort()
        set(value) {
            flags = if (value) {
                flags or 0b00000000_10000000.toShort()
            } else {
                flags and 0b11111111_01111111.toShort()
            }
        }

    /**
     * Is this method strict?
     *
     * [Specification](https://spec.shakelang.com/bytecode/storage-format/#method-flags)
     *
     * @see [flags]
     * @see [isStrict]
     * @since 0.1.0
     * @version 0.1.0
     */
    override var isStrict: Boolean
        get() = flags and 0b00000001_00000000.toShort() != 0.toShort()
        set(value) {
            flags = if (value) {
                flags or 0b00000001_00000000.toShort()
            } else {
                flags and 0b11111110_11111111.toShort()
            }
        }

    companion object {

        /**
         * Creates a [MutableMethod] from the given [pool] and [method]
         *
         * @param pool The [MutableConstantPool] of the method
         * @param method The [Method] to copy
         *
         * @see [fromMethod]
         * @see [fromStream]
         * @since 0.1.0
         * @version 0.1.0
         */
        fun fromMethod(pool: MutableConstantPool, method: Method): MutableMethod {
            return MutableMethod(
                pool,
                method.qualifiedNameConstant,
                method.flags,
                method.attributes.map { MutableAttribute.fromAttribute(it) }.toMutableList(),
            )
        }

        /**
         * Reads a [MutableMethod] from the given [stream]
         *
         * [Specification](https://spec.shakelang.com/bytecode/storage-format/#methods)
         *
         * @param pool The [MutableConstantPool] of the method
         * @param stream The [DataInputStream] to read from
         *
         * @see [fromMethod]
         * @see [fromStream]
         * @since 0.1.0
         * @version 0.1.0
         */
        fun fromStream(pool: MutableConstantPool, stream: DataInputStream): MutableMethod {
            val qualifiedName = stream.readInt()
            val flags = stream.readShort()
            val attributeCount = stream.readShort().toInt()
            val attributes = (0 until attributeCount).map { MutableAttribute.fromStream(pool, stream) }
            return MutableMethod(pool, qualifiedName, flags, attributes.toMutableList())
        }
    }
}
