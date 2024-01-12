package com.shakelang.shake.bytecode.interpreter.format

import com.shakelang.shake.bytecode.interpreter.format.attribute.Attribute
import com.shakelang.shake.bytecode.interpreter.format.attribute.MutableAttribute
import com.shakelang.shake.bytecode.interpreter.format.pool.ConstantPool
import com.shakelang.shake.bytecode.interpreter.format.pool.MutableConstantPool
import com.shakelang.util.io.streaming.input.DataInputStream
import com.shakelang.util.io.streaming.output.ByteArrayOutputStream
import com.shakelang.util.io.streaming.output.DataOutputStream
import kotlin.experimental.and
import kotlin.experimental.inv
import kotlin.experimental.or

/**
 * A class representing a field in the shake [StorageFormat]
 *
 * [Specification](https://spec.shakelang.com/bytecode/storage-format/#fields)
 *
 * @constructor Creates a [Field] with the given [pool], [nameConstant], [typeConstant], [flags] and [attributes]
 *
 * @property pool The [ConstantPool]
 * @property nameConstant The constant pool index of the name of the field
 * @property typeConstant The constant pool index of the type of the field
 * @property flags The flags of the field
 * @property attributes The attributes of the field
 *
 * @param pool The [ConstantPool]
 * @param nameConstant The constant pool index of the name of the field
 * @param typeConstant The constant pool index of the type of the field
 * @param flags The flags of the field
 * @param attributes The attributes of the field
 *
 * @see ConstantPool
 * @see MutableField
 *
 * @since 0.1.0
 * @version 0.1.0
 */
@Suppress("MemberVisibilityCanBePrivate")
open class Field(

    /**
     * The [ConstantPool]
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
     * The constant pool index of the name of the field
     *
     * [Specification](https://spec.shakelang.com/bytecode/storage-format/#field-name-index)
     *
     * @since 0.1.0
     * @version 0.1.0
     */
    open val nameConstant: Int,

    /**
     * The constant pool index of the type of the field
     *
     * [Specification](https://spec.shakelang.com/bytecode/storage-format/#field-type-index)
     *
     * @since 0.1.0
     * @version 0.1.0
     */
    open val typeConstant: Int,

    /**
     * The flags of the field
     *
     * [Specification](https://spec.shakelang.com/bytecode/storage-format/#field-flags)
     *
     * @since 0.1.0
     * @version 0.1.0
     */
    open val flags: Short,

    /**
     * The attributes of the field
     *
     * [Specification](https://spec.shakelang.com/bytecode/storage-format/#field-attributes)
     *
     * @since 0.1.0
     * @version 0.1.0
     */
    open val attributes: List<Attribute>,
) {

    /**
     * Returns if the field is public
     *
     * [Specification](https://spec.shakelang.com/bytecode/storage-format/#field-flags)
     *
     * @see [flags]
     * @since 0.1.0
     * @version 0.1.0
     */
    open val isPublic: Boolean
        get() = Flags.isPublic(flags)

    /**
     * Returns if the field is private
     *
     * [Specification](https://spec.shakelang.com/bytecode/storage-format/#field-flags)
     *
     * @see [flags]
     * @since 0.1.0
     * @version 0.1.0
     */
    open val isPrivate: Boolean
        get() = Flags.isPrivate(flags)

    /**
     * Returns if the field is protected
     *
     * [Specification](https://spec.shakelang.com/bytecode/storage-format/#field-flags)
     *
     * @see [flags]
     * @since 0.1.0
     * @version 0.1.0
     */
    open val isProtected: Boolean
        get() = Flags.isProtected(flags)

    /**
     * Returns if the field is static
     *
     * [Specification](https://spec.shakelang.com/bytecode/storage-format/#field-flags)
     *
     * @see [flags]
     * @since 0.1.0
     * @version 0.1.0
     */
    open val isStatic: Boolean
        get() = Flags.isStatic(flags)

    /**
     * Returns if the field is final
     *
     * [Specification](https://spec.shakelang.com/bytecode/storage-format/#field-flags)
     *
     * @see [flags]
     * @since 0.1.0
     * @version 0.1.0
     */
    open val isFinal: Boolean
        get() = Flags.isFinal(flags)

    /**
     * Returns if the field is abstract
     *
     * [Specification](https://spec.shakelang.com/bytecode/storage-format/#field-flags)
     *
     * @see [flags]
     * @since 0.1.0
     * @version 0.1.0
     */
    open val isAbstract: Boolean
        get() = Flags.isAbstract(flags)

    /**
     * Returns the name of the field
     *
     * [Specification](https://spec.shakelang.com/bytecode/storage-format/#field-name-index)
     *
     * @see [nameConstant]
     * @see [pool]
     * @since 0.1.0
     * @version 0.1.0
     */
    open val name: String get() = pool.getUtf8(nameConstant).value

    /**
     * Returns the type of the field
     *
     * [Specification](https://spec.shakelang.com/bytecode/storage-format/#field-type-index)
     *
     * @see [typeConstant]
     * @see [pool]
     * @since 0.1.0
     * @version 0.1.0
     */
    open val type: String get() = pool.getUtf8(typeConstant).value

    /**
     * Dumps the [Field] to the given [stream]
     *
     * [Specification](https://spec.shakelang.com/bytecode/storage-format/#fields)
     *
     * @param stream The [DataOutputStream] to dump the [Field] to
     * @see [dump]
     * @since 0.1.0
     * @version 0.1.0
     */
    fun dump(stream: DataOutputStream) {
        stream.writeInt(nameConstant)
        stream.writeInt(typeConstant)
        stream.writeShort(flags)
        stream.writeShort(attributes.size.toShort())
        for (attribute in attributes) attribute.dump(stream)
    }

    /**
     * Dumps the [Field] to a [ByteArray]
     *
     * [Specification](https://spec.shakelang.com/bytecode/storage-format/#fields)
     *
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

    override fun equals(other: Any?): Boolean {
        if (this === other) return true
        if (other !is Field) return false

        if (pool != other.pool) return false
        if (nameConstant != other.nameConstant) return false
        if (typeConstant != other.typeConstant) return false
        if (flags != other.flags) return false

        attributes.zip(other.attributes).forEach { (a, b) ->
            if (a != b) return false
        }

        return true
    }

    override fun hashCode(): Int {
        var result = pool.hashCode()
        result = 31 * result + nameConstant
        result = 31 * result + typeConstant
        result = 31 * result + flags
        result = 31 * result + attributes.hashCode()
        return result
    }

    companion object {

        object Flags {
            /**
             * The flag that represents a public field
             *
             * [Specification](https://spec.shakelang.com/bytecode/storage-format/#field-flags)
             *
             * @since 0.1.0
             * @version 0.1.0
             */
            const val FLAG_IS_PUBLIC: Short = 0b00000000_00000001

            /**
             * Inverted [FLAG_IS_PUBLIC]
             *
             * [Specification](https://spec.shakelang.com/bytecode/storage-format/#field-flags)
             *
             * @since 0.1.0
             * @version 0.1.0
             */
            val FLAG_IS_PUBLIC_INVERTED: Short = FLAG_IS_PUBLIC.inv()

            /**
             * The flag that represents a private field
             *
             * [Specification](https://spec.shakelang.com/bytecode/storage-format/#field-flags)
             *
             * @since 0.1.0
             * @version 0.1.0
             */
            const val FLAG_IS_PRIVATE: Short = 0b00000000_00000010

            /**
             * Inverted [FLAG_IS_PRIVATE]
             *
             * [Specification](https://spec.shakelang.com/bytecode/storage-format/#field-flags)
             *
             * @since 0.1.0
             * @version 0.1.0
             */
            val FLAG_IS_PRIVATE_INVERTED: Short = FLAG_IS_PRIVATE.inv()

            /**
             * The flag that represents a protected field
             *
             * [Specification](https://spec.shakelang.com/bytecode/storage-format/#field-flags)
             *
             * @since 0.1.0
             * @version 0.1.0
             */
            const val FLAG_IS_PROTECTED: Short = 0b00000000_00000100

            /**
             * Inverted [FLAG_IS_PROTECTED]
             *
             * [Specification](https://spec.shakelang.com/bytecode/storage-format/#field-flags)
             *
             * @since 0.1.0
             * @version 0.1.0
             */
            val FLAG_IS_PROTECTED_INVERTED: Short = FLAG_IS_PROTECTED.inv()

            /**
             * The flag that represents a static field
             *
             * [Specification](https://spec.shakelang.com/bytecode/storage-format/#field-flags)
             *
             * @since 0.1.0
             * @version 0.1.0
             */
            const val FLAG_IS_STATIC: Short = 0b00000000_00001000

            /**
             * Inverted [FLAG_IS_STATIC]
             *
             * [Specification](https://spec.shakelang.com/bytecode/storage-format/#field-flags)
             *
             * @since 0.1.0
             * @version 0.1.0
             */
            val FLAG_IS_STATIC_INVERTED: Short = FLAG_IS_STATIC.inv()

            /**
             * The flag that represents a final field
             *
             * [Specification](https://spec.shakelang.com/bytecode/storage-format/#field-flags)
             *
             * @since 0.1.0
             * @version 0.1.0
             */
            const val FLAG_IS_FINAL: Short = 0b00000000_00010000

            /**
             * Inverted [FLAG_IS_FINAL]
             *
             * [Specification](https://spec.shakelang.com/bytecode/storage-format/#field-flags)
             *
             * @since 0.1.0
             * @version 0.1.0
             */
            val FLAG_IS_FINAL_INVERTED: Short = FLAG_IS_FINAL.inv()

            /**
             * The flag that represents an abstract field
             *
             * [Specification](https://spec.shakelang.com/bytecode/storage-format/#field-flags)
             *
             * @since 0.1.0
             * @version 0.1.0
             */
            const val FLAG_IS_ABSTRACT: Short = 0b00000000_01000000

            /**
             * Inverted [FLAG_IS_ABSTRACT]
             *
             * [Specification](https://spec.shakelang.com/bytecode/storage-format/#field-flags)
             *
             * @since 0.1.0
             * @version 0.1.0
             */
            val FLAG_IS_ABSTRACT_INVERTED: Short = FLAG_IS_ABSTRACT.inv()

            /**
             * Get isPublic from the given [flags]
             *
             * [Specification](https://spec.shakelang.com/bytecode/storage-format/#field-flags)
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
             * [Specification](https://spec.shakelang.com/bytecode/storage-format/#field-flags)
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
             * [Specification](https://spec.shakelang.com/bytecode/storage-format/#field-flags)
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
             * [Specification](https://spec.shakelang.com/bytecode/storage-format/#field-flags)
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
             * [Specification](https://spec.shakelang.com/bytecode/storage-format/#field-flags)
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
             * [Specification](https://spec.shakelang.com/bytecode/storage-format/#field-flags)
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
             * [Specification](https://spec.shakelang.com/bytecode/storage-format/#field-flags)
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
             * [Specification](https://spec.shakelang.com/bytecode/storage-format/#field-flags)
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
             * [Specification](https://spec.shakelang.com/bytecode/storage-format/#field-flags)
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
             * [Specification](https://spec.shakelang.com/bytecode/storage-format/#field-flags)
             *
             * @param flags The flags to set the isFinal in
             * @return The flags with the isFinal flag set
             * @since 0.1.0
             * @version 0.1.0
             */
            fun setFinal(flags: Short, value: Boolean = true): Short =
                if (value) flags or FLAG_IS_FINAL else flags and FLAG_IS_FINAL_INVERTED

            /**
             * Get isAbstract from the given [flags]
             *
             * [Specification](https://spec.shakelang.com/bytecode/storage-format/#field-flags)
             *
             * @param flags The flags to get the isAbstract from
             * @return If the flags contain the isAbstract flag
             * @since 0.1.0
             * @version 0.1.0
             */
            fun isAbstract(flags: Short): Boolean = flags and FLAG_IS_ABSTRACT != 0.toShort()

            /**
             * Set isAbstract in the given [flags]
             * (Sets the sixth bit of the [flags])
             *
             * [Specification](https://spec.shakelang.com/bytecode/storage-format/#field-flags)
             *
             * @param flags The flags to set the isAbstract in
             * @return The flags with the isAbstract flag set
             * @since 0.1.0
             * @version 0.1.0
             */
            fun setAbstract(flags: Short, value: Boolean = true): Short =
                if (value) flags or FLAG_IS_ABSTRACT else flags and FLAG_IS_ABSTRACT_INVERTED
        }

        /**
         * Reads a [Field] from the given [pool] and [stream]
         *
         * [Specification](https://spec.shakelang.com/bytecode/storage-format/#fields)
         *
         * @param pool The [ConstantPool]
         * @param stream The [DataInputStream] to read the [Field] from
         * @see [fromStream]
         * @since 0.1.0
         * @version 0.1.0
         */
        fun fromStream(pool: ConstantPool, stream: DataInputStream): Field {
            val name = stream.readInt()
            val type = stream.readInt()
            val flags = stream.readShort()
            val attributeCount = stream.readShort()
            val attributes = (0 until attributeCount).map { Attribute.fromStream(pool, stream) }
            return Field(pool, name, type, flags, attributes)
        }
    }
}

/**
 * A mutable version of [Field]
 *
 * [Specification](https://spec.shakelang.com/bytecode/storage-format/#fields)
 *
 * @constructor Creates a [MutableField] with the given [pool], [nameConstant], [typeConstant], [flags] and [attributes]
 *
 * @property nameConstant The constant pool index of the name of the field
 * @property typeConstant The constant pool index of the type of the field
 * @property flags The flags of the field
 *
 * @param pool The [MutableConstantPool]
 * @param nameConstant The constant pool index of the name of the field
 * @param typeConstant The constant pool index of the type of the field
 * @param flags The flags of the field
 * @param attributes The attributes of the field
 *
 * @see ConstantPool
 * @see MutableConstantPool
 * @see Field
 *
 * @since 0.1.0
 * @version 0.1.0
 */
class MutableField(

    pool: MutableConstantPool,

    /**
     * The constant pool index of the name of the field
     *
     * [Specification](https://spec.shakelang.com/bytecode/storage-format/#field-name-index)
     *
     * @since 0.1.0
     * @version 0.1.0
     */
    override var nameConstant: Int,

    /**
     * The constant pool index of the type of the field
     *
     * [Specification](https://spec.shakelang.com/bytecode/storage-format/#field-type-index)
     *
     * @since 0.1.0
     * @version 0.1.0
     */
    override var typeConstant: Int,

    /**
     * The flags of the field
     *
     * [Specification](https://spec.shakelang.com/bytecode/storage-format/#field-flags)
     *
     * @since 0.1.0
     * @version 0.1.0
     */
    override var flags: Short,

    attributes: MutableList<Attribute>,

) : Field(pool, nameConstant, typeConstant, flags, attributes) {

    /**
     * The [MutableConstantPool]
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
    override val pool: MutableConstantPool get() = super.pool as MutableConstantPool

    /**
     * The name of the field
     * This is just a getter and setter for the [nameConstant] property
     *
     * [Specification](https://spec.shakelang.com/bytecode/storage-format/#field-name-index)
     *
     * @see nameConstant
     * @see pool
     * @since 0.1.0
     * @version 0.1.0
     */
    override var name: String
        get() = pool.getUtf8(nameConstant).value
        set(value) {
            nameConstant = pool.resolveUtf8(value)
        }

    /**
     * The type of the field
     * This is just a getter and setter for the [typeConstant] property
     *
     * [Specification](https://spec.shakelang.com/bytecode/storage-format/#field-type-index)
     *
     * @see typeConstant
     * @see pool
     * @since 0.1.0
     * @version 0.1.0
     */
    override var type: String
        get() = pool.getUtf8(typeConstant).value
        set(value) {
            typeConstant = pool.resolveUtf8(value)
        }

    /**
     * The attributes of the field
     * This is just a getter and setter for the [attributes] property
     *
     * [Specification](https://spec.shakelang.com/bytecode/storage-format/#field-attributes)
     *
     * This function is just a cast of the [attributes] property
     * This is a safe cast, because the [attributes] property is always a [MutableList]
     * (As the only constructor of this class requires a [MutableList] of [MutableAttribute]s)
     *
     * @see attributes
     * @since 0.1.0
     * @version 0.1.0
     */
    @Suppress("UNCHECKED_CAST")
    override val attributes: MutableList<MutableAttribute>
        get() = super.attributes as MutableList<MutableAttribute>

    /**
     * Is the field public?
     * This is just a getter and setter for the [flags] property
     *
     * [Specification](https://spec.shakelang.com/bytecode/storage-format/#field-flags)
     *
     * @see flags
     * @since 0.1.0
     * @version 0.1.0
     */
    override var isPublic: Boolean
        get() = Flags.isPublic(flags)
        set(value) {
            flags = Flags.setPublic(flags, value)
        }

    /**
     * Is the field private?
     * This is just a getter and setter for the [flags] property
     *
     * [Specification](https://spec.shakelang.com/bytecode/storage-format/#field-flags)
     *
     * @see flags
     * @since 0.1.0
     * @version 0.1.0
     */
    override var isPrivate: Boolean
        get() = Flags.isPrivate(flags)
        set(value) {
            flags = Flags.setPrivate(flags, value)
        }

    /**
     * Is the field protected?
     * This is just a getter and setter for the [flags] property
     *
     * [Specification](https://spec.shakelang.com/bytecode/storage-format/#field-flags)
     *
     * @see flags
     * @since 0.1.0
     * @version 0.1.0
     */
    override var isProtected: Boolean
        get() = Flags.isProtected(flags)
        set(value) {
            flags = Flags.setProtected(flags, value)
        }

    /**
     * Is the field static?
     * This is just a getter and setter for the [flags] property
     *
     * [Specification](https://spec.shakelang.com/bytecode/storage-format/#field-flags)
     *
     * @see flags
     * @since 0.1.0
     * @version 0.1.0
     */
    override var isStatic: Boolean
        get() = Flags.isStatic(flags)
        set(value) {
            flags = Flags.setStatic(flags, value)
        }

    /**
     * Is the field final?
     * This is just a getter and setter for the [flags] property
     *
     * [Specification](https://spec.shakelang.com/bytecode/storage-format/#field-flags)
     *
     * @see flags
     * @since 0.1.0
     * @version 0.1.0
     */
    override var isFinal: Boolean
        get() = Flags.isFinal(flags)
        set(value) {
            flags = Flags.setFinal(flags, value)
        }

    /**
     * Is the field abstract?
     * This is just a getter and setter for the [flags] property
     *
     * [Specification](https://spec.shakelang.com/bytecode/storage-format/#field-flags)
     *
     * @see flags
     * @since 0.1.0
     * @version 0.1.0
     */
    override var isAbstract: Boolean
        get() = Flags.isAbstract(flags)
        set(value) {
            flags = Flags.setAbstract(flags, value)
        }

    companion object {

        val Flags = Field.Companion.Flags

        /**
         * Creates a [MutableField] from the given [pool] and [field]
         *
         * @param pool The [MutableConstantPool]
         * @param field The [Field] to copy
         * @return The [MutableField] created from the given [pool] and [field]
         *
         * @since 0.1.0
         * @version 0.1.0
         */
        fun fromField(pool: MutableConstantPool, field: Field): MutableField {
            return MutableField(
                pool,
                field.nameConstant,
                field.typeConstant,
                field.flags,
                field.attributes.toMutableList(),
            )
        }

        /**
         * Reads a [MutableField] from the given [pool] and [stream]
         *
         * [Specification](https://spec.shakelang.com/bytecode/storage-format/#fields)
         *
         * @param pool The [MutableConstantPool]
         * @param stream The [DataInputStream] to read the [MutableField] from
         * @see [fromStream]
         * @since 0.1.0
         * @version 0.1.0
         */
        fun fromStream(pool: MutableConstantPool, stream: DataInputStream): MutableField {
            val name = stream.readInt()
            val type = stream.readInt()
            val flags = stream.readShort()
            val attributeCount = stream.readShort()
            val attributes = (0 until attributeCount).map { Attribute.fromStream(pool, stream) }.toMutableList()
            return MutableField(pool, name, type, flags, attributes)
        }
    }
}
