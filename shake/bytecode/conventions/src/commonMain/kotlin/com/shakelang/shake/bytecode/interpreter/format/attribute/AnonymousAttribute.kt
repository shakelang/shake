package com.shakelang.shake.bytecode.interpreter.format.attribute

import com.shakelang.shake.bytecode.interpreter.format.pool.ConstantPool
import com.shakelang.shake.bytecode.interpreter.format.pool.MutableConstantPool
import com.shakelang.util.io.streaming.input.bytes.DataInputStream
import com.shakelang.util.io.streaming.output.bytes.ByteArrayOutputStream
import com.shakelang.util.io.streaming.output.bytes.DataOutputStream

/**
 * An abstract [Attribute] implementation without any custom properties
 * This implementation can wrap any attribute.
 *
 * [Specification](https://spec.shakelang.com/bytecode/map-format/#attributes)
 *
 * @param pool the [ConstantPool] of the attribute
 * @param nameConstant the name of the attribute
 * @param value the value of the attribute
 * @see Attribute
 *
 * @since 0.1.0
 * @version 0.1.0
 */
open class AnonymousAttributeImpl(

    /**
     * The [ConstantPool] of the attribute
     *
     * [Specification](https://spec.shakelang.com/bytecode/map-format/#constant-pool)
     *
     * @see ConstantPool
     * @see MutableConstantPool
     * @since 0.1.0
     * @version 0.1.0
     */
    override val pool: ConstantPool,

    /**
     * The name of the attribute
     *
     * [Specification](https://spec.shakelang.com/bytecode/map-format/#attribute-name-index)
     *
     * @see Attribute
     * @since 0.1.0
     * @version 0.1.0
     */
    override val nameConstant: Int,

    /**
     * The value of the attribute
     *
     * [Specification](https://spec.shakelang.com/bytecode/map-format/#attribute-info)
     *
     * @see Attribute
     * @since 0.1.0
     * @version 0.1.0
     */
    override val value: ByteArray,
) : Attribute {

    /**
     * The name of the attribute
     *
     * [Specification](https://spec.shakelang.com/bytecode/map-format/#attribute-name-index)
     *
     * @see Attribute
     * @since 0.1.0
     * @version 0.1.0
     */
    override val name: String get() = pool.getUtf8(nameConstant).value

    /**
     * Get the size of the attribute
     *
     * [Specification](https://spec.shakelang.com/bytecode/map-format/#attribute-info)
     *
     * @see Attribute
     * @since 0.1.0
     * @version 0.1.0
     */
    override fun dump(stream: DataOutputStream) {
        stream.writeInt(nameConstant)
        stream.writeInt(value.size)
        stream.write(value)
    }

    /**
     * Get the size of the attribute
     *
     * [Specification](https://spec.shakelang.com/bytecode/map-format/#attribute-info)
     *
     * @see Attribute
     * @since 0.1.0
     * @version 0.1.0
     */
    override fun dump(): ByteArray {
        val stream = ByteArrayOutputStream()
        dump(DataOutputStream(stream))
        return stream.toByteArray()
    }

    /**
     * Does the attribute equal the [other] object
     * @since 0.1.0
     * @version 0.1.0
     */
    override fun equals(other: Any?): Boolean {
        if (this === other) return true
        if (other !is Attribute) return false

        if (pool != other.pool) return false
        if (nameConstant != other.nameConstant) return false
        if (!value.contentEquals(other.value)) return false

        return true
    }

    /**
     * Get the hash code of the attribute
     * @since 0.1.0
     * @version 0.1.0
     */
    override fun hashCode(): Int {
        var result = pool.hashCode()
        result = 31 * result + nameConstant
        result = 31 * result + value.contentHashCode()
        return result
    }

    companion object {

        /**
         * Create an [AnonymousAttributeImpl] from an [Attribute]
         * @since 0.1.0
         * @version 0.1.0
         */
        fun fromStream(pool: ConstantPool, stream: DataInputStream): Attribute {
            val name = stream.readInt()
            val size = stream.readInt()
            val value = ByteArray(size) {
                stream.readByte()
            }
            return AnonymousAttributeImpl(pool, name, value)
        }

        /**
         * Create an [AnonymousAttributeImpl] from an [Attribute]
         * @since 0.1.0
         * @version 0.1.0
         */
        fun fromStream(pool: ConstantPool, stream: DataInputStream, nameConstant: Int): Attribute {
            val size = stream.readInt()
            val value = ByteArray(size) {
                stream.readByte()
            }
            return AnonymousAttributeImpl(pool, nameConstant, value)
        }
    }
}

/**
 * A mutable [AnonymousAttributeImpl] implementation without any custom properties
 * This implementation can wrap any attribute.
 *
 * [Specification](https://spec.shakelang.com/bytecode/map-format/#attributes)
 *
 * @param pool the [MutableConstantPool] of the attribute
 * @param nameConstant the name of the attribute
 * @param value the value of the attribute
 * @see Attribute
 *
 * @since 0.1.0
 * @version 0.1.0
 */
class MutableAnonymousAttributeImpl(

    /**
     * The [MutableConstantPool] of the attribute
     *
     * [Specification](https://spec.shakelang.com/bytecode/map-format/#constant-pool)
     *
     * @see ConstantPool
     * @see MutableConstantPool
     * @since 0.1.0
     * @version 0.1.0
     */
    pool: MutableConstantPool,

    /**
     * The name of the attribute
     *
     * [Specification](https://spec.shakelang.com/bytecode/map-format/#attribute-name-index)
     *
     * @see Attribute
     * @since 0.1.0
     * @version 0.1.0
     */
    override var nameConstant: Int,

    /**
     * The value of the attribute
     *
     * [Specification](https://spec.shakelang.com/bytecode/map-format/#attribute-info)
     *
     * @see Attribute
     * @since 0.1.0
     * @version 0.1.0
     */
    override var value: ByteArray,
) : AnonymousAttributeImpl(pool, nameConstant, value),
    MutableAttribute {

    /**
     * The [MutableConstantPool] of the attribute
     *
     * [Specification](https://spec.shakelang.com/bytecode/map-format/#constant-pool)
     *
     * @see ConstantPool
     * @see MutableConstantPool
     * @since 0.1.0
     * @version 0.1.0
     */
    override val pool: MutableConstantPool
        get() = super.pool as MutableConstantPool

    /**
     * The name of the attribute
     *
     * [Specification](https://spec.shakelang.com/bytecode/map-format/#attribute-name-index)
     *
     * @see Attribute
     * @since 0.1.0
     * @version 0.1.0
     */
    override var name: String
        get() = pool.getUtf8(nameConstant).value
        set(value) {
            nameConstant = pool.resolveUtf8(value)
        }

    companion object {

        /**
         * Create an [MutableAnonymousAttributeImpl] from an [Attribute]
         *
         * [Specification](https://spec.shakelang.com/bytecode/map-format/#attributes)
         *
         * @since 0.1.0
         * @version 0.1.0
         */
        fun fromAttribute(attribute: Attribute): MutableAnonymousAttributeImpl = MutableAnonymousAttributeImpl(
            attribute.pool as MutableConstantPool,
            attribute.nameConstant,
            attribute.value,
        )

        /**
         * Create an [MutableAnonymousAttributeImpl] from an [Attribute]
         *
         * [Specification](https://spec.shakelang.com/bytecode/map-format/#attributes)
         *
         * @since 0.1.0
         * @version 0.1.0
         */
        fun fromStream(pool: MutableConstantPool, stream: DataInputStream): MutableAnonymousAttributeImpl {
            val name = stream.readInt()
            val size = stream.readInt()
            val value = ByteArray(size) {
                stream.readByte()
            }
            return MutableAnonymousAttributeImpl(pool, name, value)
        }

        fun fromStream(
            pool: MutableConstantPool,
            stream: DataInputStream,
            nameConstant: Int,
        ): MutableAnonymousAttributeImpl {
            val size = stream.readInt()
            val value = ByteArray(size) {
                stream.readByte()
            }
            return MutableAnonymousAttributeImpl(pool, nameConstant, value)
        }
    }
}
