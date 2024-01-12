@file:Suppress("MemberVisibilityCanBePrivate")

package com.shakelang.shake.bytecode.interpreter.format

import com.shakelang.shake.bytecode.interpreter.format.attribute.Attribute
import com.shakelang.shake.bytecode.interpreter.format.attribute.MutableAttribute
import com.shakelang.shake.bytecode.interpreter.format.descriptor.MethodDescriptor
import com.shakelang.shake.bytecode.interpreter.format.pool.ConstantPool
import com.shakelang.shake.bytecode.interpreter.format.pool.MutableConstantPool
import com.shakelang.util.io.streaming.input.DataInputStream
import com.shakelang.util.io.streaming.output.ByteArrayOutputStream
import com.shakelang.util.io.streaming.output.DataOutputStream
import kotlin.experimental.and
import kotlin.experimental.inv
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
     * Returns the name of the method
     *
     * [Specification](https://spec.shakelang.com/bytecode/storage-format/#method-qualified-name-index)
     *
     * @see [qualifiedName]
     * @since 0.1.0
     * @version 0.1.0
     */
    open val name: String
        get() = MethodDescriptor.parse(qualifiedName).name

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
        get() = Flags.isPublic(flags)

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
        get() = Flags.isPrivate(flags)

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
        get() = Flags.isProtected(flags)

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
        get() = Flags.isStatic(flags)

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
        get() = Flags.isFinal(flags)

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
        get() = Flags.isSynchronized(flags)

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
        get() = Flags.isNative(flags)

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
        get() = Flags.isAbstract(flags)

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
        get() = Flags.isStrict(flags)

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

        object Flags {

            /**
             * The flag that represents a public method
             *
             * [Specification](https://spec.shakelang.com/bytecode/storage-format/#method-flags)
             *
             * @since 0.1.0
             * @version 0.1.0
             */
            const val FLAG_IS_PUBLIC: Short = 0b00000000_00000001

            /**
             * Inverted [FLAG_IS_PUBLIC]
             *
             * [Specification](https://spec.shakelang.com/bytecode/storage-format/#method-flags)
             *
             * @since 0.1.0
             * @version 0.1.0
             */
            val FLAG_IS_PUBLIC_INVERTED: Short = FLAG_IS_PUBLIC.inv()

            /**
             * The flag that represents a private method
             *
             * [Specification](https://spec.shakelang.com/bytecode/storage-format/#method-flags)
             *
             * @since 0.1.0
             * @version 0.1.0
             */
            const val FLAG_IS_PRIVATE: Short = 0b00000000_00000010

            /**
             * Inverted [FLAG_IS_PRIVATE]
             *
             * [Specification](https://spec.shakelang.com/bytecode/storage-format/#method-flags)
             *
             * @since 0.1.0
             * @version 0.1.0
             */
            val FLAG_IS_PRIVATE_INVERTED: Short = FLAG_IS_PRIVATE.inv()

            /**
             * The flag that represents a protected method
             *
             * [Specification](https://spec.shakelang.com/bytecode/storage-format/#method-flags)
             *
             * @since 0.1.0
             * @version 0.1.0
             */
            const val FLAG_IS_PROTECTED: Short = 0b00000000_00000100

            /**
             * Inverted [FLAG_IS_PROTECTED]
             *
             * [Specification](https://spec.shakelang.com/bytecode/storage-format/#method-flags)
             *
             * @since 0.1.0
             * @version 0.1.0
             */
            val FLAG_IS_PROTECTED_INVERTED: Short = FLAG_IS_PROTECTED.inv()

            /**
             * The flag that represents a static method
             *
             * [Specification](https://spec.shakelang.com/bytecode/storage-format/#method-flags)
             *
             * @since 0.1.0
             * @version 0.1.0
             */
            const val FLAG_IS_STATIC: Short = 0b00000000_00001000

            /**
             * Inverted [FLAG_IS_STATIC]
             *
             * [Specification](https://spec.shakelang.com/bytecode/storage-format/#method-flags)
             *
             * @since 0.1.0
             * @version 0.1.0
             */
            val FLAG_IS_STATIC_INVERTED: Short = FLAG_IS_STATIC.inv()

            /**
             * The flag that represents a final method
             *
             * [Specification](https://spec.shakelang.com/bytecode/storage-format/#method-flags)
             *
             * @since 0.1.0
             * @version 0.1.0
             */
            const val FLAG_IS_FINAL: Short = 0b00000000_00010000

            /**
             * Inverted [FLAG_IS_FINAL]
             *
             * [Specification](https://spec.shakelang.com/bytecode/storage-format/#method-flags)
             *
             * @since 0.1.0
             * @version 0.1.0
             */
            val FLAG_IS_FINAL_INVERTED: Short = FLAG_IS_FINAL.inv()

            /**
             * The flag that represents a synchronized method
             *
             * [Specification](https://spec.shakelang.com/bytecode/storage-format/#method-flags)
             *
             * @since 0.1.0
             * @version 0.1.0
             */
            const val FLAG_IS_SYNCHRONIZED: Short = 0b00000000_00100000

            /**
             * Inverted [FLAG_IS_SYNCHRONIZED]
             *
             * [Specification](https://spec.shakelang.com/bytecode/storage-format/#method-flags)
             *
             * @since 0.1.0
             * @version 0.1.0
             */
            val FLAG_IS_SYNCHRONIZED_INVERTED: Short = FLAG_IS_SYNCHRONIZED.inv()

            /**
             * The flag that represents a native method
             *
             * [Specification](https://spec.shakelang.com/bytecode/storage-format/#method-flags)
             *
             * @since 0.1.0
             * @version 0.1.0
             */
            const val FLAG_IS_NATIVE: Short = 0b00000000_01000000

            /**
             * Inverted [FLAG_IS_NATIVE]
             *
             * [Specification](https://spec.shakelang.com/bytecode/storage-format/#method-flags)
             *
             * @since 0.1.0
             * @version 0.1.0
             */
            val FLAG_IS_NATIVE_INVERTED: Short = FLAG_IS_NATIVE.inv()

            /**
             * The flag that represents an abstract method
             *
             * [Specification](https://spec.shakelang.com/bytecode/storage-format/#method-flags)
             *
             * @since 0.1.0
             * @version 0.1.0
             */
            const val FLAG_IS_ABSTRACT: Short = 0b00000000_10000000

            /**
             * Inverted [FLAG_IS_ABSTRACT]
             *
             * [Specification](https://spec.shakelang.com/bytecode/storage-format/#method-flags)
             *
             * @since 0.1.0
             * @version 0.1.0
             */
            val FLAG_IS_ABSTRACT_INVERTED: Short = FLAG_IS_ABSTRACT.inv()

            /**
             * The flag that represents a strict method
             *
             * [Specification](https://spec.shakelang.com/bytecode/storage-format/#method-flags)
             *
             * @since 0.1.0
             * @version 0.1.0
             */
            const val FLAG_IS_STRICT: Short = 0b00000001_00000000

            /**
             * Inverted [FLAG_IS_STRICT]
             *
             * [Specification](https://spec.shakelang.com/bytecode/storage-format/#method-flags)
             *
             * @since 0.1.0
             * @version 0.1.0
             */
            val FLAG_IS_STRICT_INVERTED: Short = FLAG_IS_STRICT.inv()

            /**
             * Get isPublic from the given [flags]
             *
             * [Specification](https://spec.shakelang.com/bytecode/storage-format/#method-flags)
             *
             * @param flags The flags to get the isPublic from
             * @return If the flags contain the isPublic flag
             * @since 0.1.0
             * @version 0.1.0
             */
            fun isPublic(flags: Short): Boolean = flags and FLAG_IS_PUBLIC != 0.toShort()

            /**
             * Set isPublic in the given [flags]
             * (Sets the first bit of the [flags])
             *
             * [Specification](https://spec.shakelang.com/bytecode/storage-format/#method-flags)
             *
             * @param flags The flags to set the isPublic in
             * @return The flags with the isPublic flag set
             * @since 0.1.0
             * @version 0.1.0
             */
            fun setPublic(flags: Short, value: Boolean = true): Short =
                if (value) flags or FLAG_IS_PUBLIC else flags and FLAG_IS_PUBLIC_INVERTED

            /**
             * Get isPrivate from the given [flags]
             *
             * [Specification](https://spec.shakelang.com/bytecode/storage-format/#method-flags)
             *
             * @param flags The flags to get the isPrivate from
             * @return If the flags contain the isPrivate flag
             * @since 0.1.0
             * @version 0.1.0
             */
            fun isPrivate(flags: Short): Boolean = flags and FLAG_IS_PRIVATE != 0.toShort()

            /**
             * Set isPrivate in the given [flags]
             * (Sets the second bit of the [flags])
             *
             * [Specification](https://spec.shakelang.com/bytecode/storage-format/#method-flags)
             *
             * @param flags The flags to set the isPrivate in
             * @return The flags with the isPrivate flag set
             * @since 0.1.0
             * @version 0.1.0
             */
            fun setPrivate(flags: Short, value: Boolean = true): Short =
                if (value) flags or FLAG_IS_PRIVATE else flags and FLAG_IS_PRIVATE_INVERTED

            /**
             * Get isProtected from the given [flags]
             *
             * [Specification](https://spec.shakelang.com/bytecode/storage-format/#method-flags)
             *
             * @param flags The flags to get the isProtected from
             * @return If the flags contain the isProtected flag
             * @since 0.1.0
             * @version 0.1.0
             */
            fun isProtected(flags: Short): Boolean = flags and FLAG_IS_PROTECTED != 0.toShort()

            /**
             * Set isProtected in the given [flags]
             * (Sets the third bit of the [flags])
             *
             * [Specification](https://spec.shakelang.com/bytecode/storage-format/#method-flags)
             *
             * @param flags The flags to set the isProtected in
             * @return The flags with the isProtected flag set
             * @since 0.1.0
             * @version 0.1.0
             */
            fun setProtected(flags: Short, value: Boolean = true): Short =
                if (value) flags or FLAG_IS_PROTECTED else flags and FLAG_IS_PROTECTED_INVERTED

            /**
             * Get isStatic from the given [flags]
             *
             * [Specification](https://spec.shakelang.com/bytecode/storage-format/#method-flags)
             *
             * @param flags The flags to get the isStatic from
             * @return If the flags contain the isStatic flag
             * @since 0.1.0
             * @version 0.1.0
             */
            fun isStatic(flags: Short): Boolean = flags and FLAG_IS_STATIC != 0.toShort()

            /**
             * Set isStatic in the given [flags]
             * (Sets the fourth bit of the [flags])
             *
             * [Specification](https://spec.shakelang.com/bytecode/storage-format/#method-flags)
             *
             * @param flags The flags to set the isStatic in
             * @return The flags with the isStatic flag set
             * @since 0.1.0
             * @version 0.1.0
             */
            fun setStatic(flags: Short, value: Boolean = true): Short =
                if (value) flags or FLAG_IS_STATIC else flags and FLAG_IS_STATIC_INVERTED

            /**
             * Get isFinal from the given [flags]
             *
             * [Specification](https://spec.shakelang.com/bytecode/storage-format/#method-flags)
             *
             * @param flags The flags to get the isFinal from
             * @return If the flags contain the isFinal flag
             * @since 0.1.0
             * @version 0.1.0
             */
            fun isFinal(flags: Short): Boolean = flags and FLAG_IS_FINAL != 0.toShort()

            /**
             * Set isFinal in the given [flags]
             * (Sets the fifth bit of the [flags])
             *
             * [Specification](https://spec.shakelang.com/bytecode/storage-format/#method-flags)
             *
             * @param flags The flags to set the isFinal in
             * @return The flags with the isFinal flag set
             * @since 0.1.0
             * @version 0.1.0
             */
            fun setFinal(flags: Short, value: Boolean = true): Short =
                if (value) flags or FLAG_IS_FINAL else flags and FLAG_IS_FINAL_INVERTED

            /**
             * Get isSynchronized from the given [flags]
             *
             * [Specification](https://spec.shakelang.com/bytecode/storage-format/#method-flags)
             *
             * @param flags The flags to get the isSynchronized from
             * @return If the flags contain the isSynchronized flag
             * @since 0.1.0
             * @version 0.1.0
             */
            fun isSynchronized(flags: Short): Boolean = flags and FLAG_IS_SYNCHRONIZED != 0.toShort()

            /**
             * Set isSynchronized in the given [flags]
             * (Sets the sixth bit of the [flags])
             *
             * [Specification](https://spec.shakelang.com/bytecode/storage-format/#method-flags)
             *
             * @param flags The flags to set the isSynchronized in
             * @return The flags with the isSynchronized flag set
             * @since 0.1.0
             * @version 0.1.0
             */
            fun setSynchronized(flags: Short, value: Boolean = true): Short =
                if (value) flags or FLAG_IS_SYNCHRONIZED else flags and FLAG_IS_SYNCHRONIZED_INVERTED

            /**
             * Get isNative from the given [flags]
             *
             * [Specification](https://spec.shakelang.com/bytecode/storage-format/#method-flags)
             *
             * @param flags The flags to get the isNative from
             * @return If the flags contain the isNative flag
             * @since 0.1.0
             * @version 0.1.0
             */
            fun isNative(flags: Short): Boolean = flags and FLAG_IS_NATIVE != 0.toShort()

            /**
             * Set isNative in the given [flags]
             * (Sets the seventh bit of the [flags])
             *
             * [Specification](https://spec.shakelang.com/bytecode/storage-format/#method-flags)
             *
             * @param flags The flags to set the isNative in
             * @return The flags with the isNative flag set
             * @since 0.1.0
             * @version 0.1.0
             */
            fun setNative(flags: Short, value: Boolean = true): Short =
                if (value) flags or FLAG_IS_NATIVE else flags and FLAG_IS_NATIVE_INVERTED

            /**
             * Get isAbstract from the given [flags]
             *
             * [Specification](https://spec.shakelang.com/bytecode/storage-format/#method-flags)
             *
             * @param flags The flags to get the isAbstract from
             * @return If the flags contain the isAbstract flag
             * @since 0.1.0
             * @version 0.1.0
             */
            fun isAbstract(flags: Short): Boolean = flags and FLAG_IS_ABSTRACT != 0.toShort()

            /**
             * Set isAbstract in the given [flags]
             * (Sets the eighth bit of the [flags])
             *
             * [Specification](https://spec.shakelang.com/bytecode/storage-format/#method-flags)
             *
             * @param flags The flags to set the isAbstract in
             * @return The flags with the isAbstract flag set
             * @since 0.1.0
             * @version 0.1.0
             */
            fun setAbstract(flags: Short, value: Boolean = true): Short =
                if (value) flags or FLAG_IS_ABSTRACT else flags and FLAG_IS_ABSTRACT_INVERTED

            /**
             * Get isStrict from the given [flags]
             *
             * [Specification](https://spec.shakelang.com/bytecode/storage-format/#method-flags)
             *
             * @param flags The flags to get the isStrict from
             * @return If the flags contain the isStrict flag
             * @since 0.1.0
             * @version 0.1.0
             */
            fun isStrict(flags: Short): Boolean = flags and FLAG_IS_STRICT != 0.toShort()

            /**
             * Set isStrict in the given [flags]
             * (Sets the ninth bit of the [flags])
             *
             * [Specification](https://spec.shakelang.com/bytecode/storage-format/#method-flags)
             *
             * @param flags The flags to set the isStrict in
             * @return The flags with the isStrict flag set
             * @since 0.1.0
             * @version 0.1.0
             */
            fun setStrict(flags: Short, value: Boolean = true): Short =
                if (value) flags or FLAG_IS_STRICT else flags and FLAG_IS_STRICT_INVERTED
        }

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
        get() = Flags.isPublic(flags)
        set(value) {
            flags = Flags.setPublic(flags, value)
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
        get() = Flags.isPrivate(flags)
        set(value) {
            flags = Flags.setPrivate(flags, value)
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
        get() = Flags.isProtected(flags)
        set(value) {
            flags = Flags.setProtected(flags, value)
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
        get() = Flags.isStatic(flags)
        set(value) {
            flags = Flags.setStatic(flags, value)
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
        get() = Flags.isFinal(flags)
        set(value) {
            flags = Flags.setFinal(flags, value)
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
        get() = Flags.isSynchronized(flags)
        set(value) {
            flags = Flags.setSynchronized(flags, value)
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
        get() = Flags.isNative(flags)
        set(value) {
            flags = Flags.setNative(flags, value)
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
        get() = Flags.isAbstract(flags)
        set(value) {
            flags = Flags.setAbstract(flags, value)
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
        get() = Flags.isStrict(flags)
        set(value) {
            flags = Flags.setStrict(flags, value)
        }

    companion object {

        val Flags = Method.Companion.Flags

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
