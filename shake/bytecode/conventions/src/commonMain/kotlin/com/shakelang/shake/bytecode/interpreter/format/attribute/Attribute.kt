package com.shakelang.shake.bytecode.interpreter.format.attribute

import com.shakelang.shake.bytecode.interpreter.format.pool.ConstantPool
import com.shakelang.shake.bytecode.interpreter.format.pool.MutableConstantPool
import com.shakelang.util.io.streaming.input.DataInputStream
import com.shakelang.util.io.streaming.output.ByteArrayOutputStream
import com.shakelang.util.io.streaming.output.DataOutputStream

/**
 * An attribute of a class, method or field
 *
 * [Specification](https://spec.shakelang.com/bytecode/storage-format/#attributes)
 *
 * @since 0.1.0
 * @version 0.1.0
 */
interface Attribute {

    /**
     * The constant pool of the attribute
     *
     * [Specification](https://spec.shakelang.com/bytecode/storage-format/#constant-pool)
     *
     * @see ConstantPool
     * @see MutableConstantPool
     * @since 0.1.0
     * @version 0.1.0
     */
    val pool: ConstantPool

    /**
     * The constant pool index of the name of the attribute
     *
     * [Specification](https://spec.shakelang.com/bytecode/storage-format/#attribute-name-index)
     *
     * @see name
     * @see pool
     * @since 0.1.0
     * @version 0.1.0
     */
    val nameConstant: Int

    /**
     * The value of the attribute
     *
     * [Specification](https://spec.shakelang.com/bytecode/storage-format/#attribute-name)
     *
     * @see nameConstant
     * @see pool
     * @since 0.1.0
     * @version 0.1.0
     */
    val value: ByteArray

    /**
     * The name of the attribute
     *
     * [Specification](https://spec.shakelang.com/bytecode/storage-format/#attribute-name)
     *
     * @see nameConstant
     * @see pool
     * @since 0.1.0
     * @version 0.1.0
     */
    val name: String get() = pool.getUtf8(nameConstant).value

    /**
     * Dump the attribute to a [DataOutputStream]
     *
     * [Specification](https://spec.shakelang.com/bytecode/storage-format/#attributes)
     *
     * @see DataOutputStream
     * @since 0.1.0
     * @version 0.1.0
     */
    fun dump(stream: DataOutputStream) {
        stream.writeUnsignedShort(nameConstant.toUShort())
        stream.writeInt(value.size)
        stream.write(value)
    }

    /**
     * Dump the attribute to a [ByteArray]
     *
     * [Specification](https://spec.shakelang.com/bytecode/storage-format/#attributes)
     *
     * @see DataOutputStream
     * @since 0.1.0
     * @version 0.1.0
     */
    fun dump(): ByteArray {
        val stream = ByteArrayOutputStream()
        dump(DataOutputStream(stream))
        return stream.toByteArray()
    }

    /**
     * Compare this [Attribute] to another [Any] object
     *
     * @see compareHelper
     * @since 0.1.0
     * @version 0.1.0
     */
    override fun equals(other: Any?): Boolean

    /**
     * Get the hash code of this [Attribute]
     *
     * @see hashCodeHelper
     * @since 0.1.0
     * @version 0.1.0
     */
    override fun hashCode(): Int

    /**
     * Compare this [Attribute] to another [Attribute]
     *
     * @see compareHelper
     * @since 0.1.0
     * @version 0.1.0
     */
    fun compareHelper(other: Any?): Boolean {
        if (this === other) return true
        if (other !is Attribute) return false
        if (pool != other.pool) return false
        if (nameConstant != other.nameConstant) return false
        if (!value.contentEquals(other.value)) return false
        return true
    }

    /**
     * Get the hash code of this [Attribute]
     *
     * @see compareHelper
     * @since 0.1.0
     * @version 0.1.0
     */
    fun hashCodeHelper(): Int {
        var result = pool.hashCode()
        result = 31 * result + nameConstant
        result = 31 * result + value.contentHashCode()
        return result
    }

    companion object {

        /**
         * Create an [Attribute] from a [DataInputStream]
         *
         * [Specification](https://spec.shakelang.com/bytecode/storage-format/#attributes)
         *
         * @see DataInputStream
         * @since 0.1.0
         * @version 0.1.0
         */
        fun fromStream(pool: ConstantPool, stream: DataInputStream): Attribute {
            val nameConstant = stream.readInt()
            val name = pool.getUtf8(nameConstant).value

            return when (name) {
                "Code" -> CodeAttribute.fromStream(pool, stream, nameConstant)
                else -> AnonymousAttributeImpl.fromStream(pool, stream, nameConstant)
            }
        }
    }
}

/**
 * A mutable [Attribute]
 *
 * @see Attribute
 * @since 0.1.0
 * @version 0.1.0
 */
interface MutableAttribute : Attribute {

    /**
     * The constant pool of the attribute
     *
     * [Specification](https://spec.shakelang.com/bytecode/storage-format/#constant-pool)
     *
     * @see ConstantPool
     * @see MutableConstantPool
     * @since 0.1.0
     * @version 0.1.0
     */
    override var nameConstant: Int

    /**
     * The value of the attribute
     *
     * [Specification](https://spec.shakelang.com/bytecode/storage-format/#attribute-name)
     *
     * @see nameConstant
     * @see pool
     * @since 0.1.0
     * @version 0.1.0
     */
    override var value: ByteArray

    /**
     * The name of the attribute
     *
     * [Specification](https://spec.shakelang.com/bytecode/storage-format/#attribute-name)
     *
     * @see nameConstant
     * @see pool
     * @since 0.1.0
     * @version 0.1.0
     */
    override val pool: MutableConstantPool

    /**
     * The name of the attribute
     *
     * [Specification](https://spec.shakelang.com/bytecode/storage-format/#attribute-name)
     *
     * @see nameConstant
     * @see pool
     * @since 0.1.0
     * @version 0.1.0
     */
    override var name: String
        get() = super.name
        set(value) {
            nameConstant = pool.resolveUtf8(value)
        }

    companion object {

        /**
         * Read an [MutableAttribute] from a [DataInputStream]
         *
         * [Specification](https://spec.shakelang.com/bytecode/storage-format/#attributes)
         *
         * @see DataInputStream
         * @since 0.1.0
         * @version 0.1.0
         */
        fun fromStream(pool: MutableConstantPool, stream: DataInputStream): MutableAttribute {
            val nameConstant = stream.readInt()
            val name = pool.getUtf8(nameConstant).value

            return when (name) {
                "Code" -> MutableCodeAttribute.fromStream(pool, stream, nameConstant)
                else -> MutableAnonymousAttributeImpl.fromStream(pool, stream, nameConstant)
            }
        }

        /**
         * Create an [Attribute] from an [Attribute]
         *
         * [Specification](https://spec.shakelang.com/bytecode/storage-format/#attributes)
         *
         * @see Attribute
         * @since 0.1.0
         * @version 0.1.0
         */
        fun fromAttribute(attribute: Attribute): MutableAttribute {
            return when (attribute) {
                is CodeAttribute -> MutableCodeAttribute.fromCodeAttribute(attribute)
                else -> MutableAnonymousAttributeImpl.fromAttribute(attribute)
            }
        }
    }
}
