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
 * A class representing a field in a class file
 *
 * [Specification](https://spec.shakelang.com/bytecode/storage-format/#fields)
 *
 * @constructor Creates a [Field] with the given [pool], [nameConstant], [typeConstant], [flags] and [attributes]
 *
 * @property pool The [ConstantPool] of the class file
 * @property nameConstant The constant pool index of the name of the field
 * @property typeConstant The constant pool index of the type of the field
 * @property flags The flags of the field
 * @property attributes The attributes of the field
 *
 * @param pool The [ConstantPool] of the class file
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
open class Field(

    /**
     * The [ConstantPool] of the class file
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
        get() = flags and 0b00000000_00000001.toShort() != 0.toShort()

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
        get() = flags and 0b00000000_00000010.toShort() != 0.toShort()

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
        get() = flags and 0b00000000_00000100.toShort() != 0.toShort()

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
        get() = flags and 0b00000000_00001000.toShort() != 0.toShort()

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
        get() = flags and 0b00000000_00010000.toShort() != 0.toShort()

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
        get() = flags and 0b00000000_01000000.toShort() != 0.toShort()

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

    companion object {

        /**
         * Reads a [Field] from the given [pool] and [stream]
         *
         * [Specification](https://spec.shakelang.com/bytecode/storage-format/#fields)
         *
         * @param pool The [ConstantPool] of the class file
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
 * @param pool The [MutableConstantPool] of the class file
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
     * The [MutableConstantPool] of the class file
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
        get() = flags and 0b00000000_00000001.toShort() != 0.toShort()
        set(value) {
            flags = if (value) flags or 0b00000000_00000001.toShort() else flags and 0b11111111_11111110.toShort()
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
        get() = flags and 0b00000000_00000010.toShort() != 0.toShort()
        set(value) {
            flags = if (value) flags or 0b00000000_00000010.toShort() else flags and 0b11111111_11111101.toShort()
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
        get() = flags and 0b00000000_00000100.toShort() != 0.toShort()
        set(value) {
            flags = if (value) flags or 0b00000000_00000100.toShort() else flags and 0b11111111_11111011.toShort()
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
        get() = flags and 0b00000000_00001000.toShort() != 0.toShort()
        set(value) {
            flags = if (value) flags or 0b00000000_00001000.toShort() else flags and 0b11111111_11110111.toShort()
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
        get() = flags and 0b00000000_00010000.toShort() != 0.toShort()
        set(value) {
            flags = if (value) flags or 0b00000000_00010000.toShort() else flags and 0b11111111_11101111.toShort()
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
        get() = flags and 0b00000000_01000000.toShort() != 0.toShort()
        set(value) {
            flags = if (value) flags or 0b00000000_01000000.toShort() else flags and 0b11111111_10111111.toShort()
        }

    companion object {

        /**
         * Creates a [MutableField] from the given [pool] and [field]
         *
         * @param pool The [MutableConstantPool] of the class file
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
         * @param pool The [MutableConstantPool] of the class file
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
