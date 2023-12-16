package com.shakelang.shake.bytecode.interpreter.format.attribute

import com.shakelang.shake.bytecode.interpreter.format.pool.ConstantPool
import com.shakelang.shake.bytecode.interpreter.format.pool.MutableConstantPool
import com.shakelang.shake.util.io.streaming.input.DataInputStream
import com.shakelang.shake.util.io.streaming.output.ByteArrayOutputStream
import com.shakelang.shake.util.io.streaming.output.DataOutputStream

open class AnonymousAttributeImpl(
    override val pool: ConstantPool,
    override val nameConstant: Int,
    override val value: ByteArray
) : Attribute {

    override val name: String get() = pool.getUtf8(nameConstant).value

    override fun dump(stream: DataOutputStream) {
        stream.writeInt(nameConstant)
        stream.writeInt(value.size)
        stream.write(value)
    }

    override fun dump(): ByteArray {
        val stream = ByteArrayOutputStream()
        dump(DataOutputStream(stream))
        return stream.toByteArray()
    }

    override fun equals(other: Any?): Boolean {
        if (this === other) return true
        if (other !is Attribute) return false

        if (pool != other.pool) return false
        if (nameConstant != other.nameConstant) return false
        if (!value.contentEquals(other.value)) return false

        return true
    }

    override fun hashCode(): Int {
        var result = pool.hashCode()
        result = 31 * result + nameConstant
        result = 31 * result + value.contentHashCode()
        return result
    }

    companion object {

        fun fromStream(pool: ConstantPool, stream: DataInputStream): Attribute {
            val name = stream.readInt()
            val size = stream.readInt()
            val value = ByteArray(size) {
                stream.readByte()
            }
            return AnonymousAttributeImpl(pool, name, value)
        }

        fun fromStream(pool: ConstantPool, stream: DataInputStream, nameConstant: Int): Attribute {
            val size = stream.readInt()
            val value = ByteArray(size) {
                stream.readByte()
            }
            return AnonymousAttributeImpl(pool, nameConstant, value)
        }
    }
}

class MutableAnonymousAttributeImpl(
    pool: MutableConstantPool,
    override var nameConstant: Int,
    override var value: ByteArray
) : AnonymousAttributeImpl(pool, nameConstant, value), MutableAttribute {

    override val pool: MutableConstantPool
        get() = super.pool as MutableConstantPool

    override var name: String
        get() = pool.getUtf8(nameConstant).value
        set(value) {
            nameConstant = pool.resolveUtf8(value)
        }

    companion object {
        fun fromAttribute(attribute: Attribute): MutableAnonymousAttributeImpl {
            return MutableAnonymousAttributeImpl(
                attribute.pool as MutableConstantPool,
                attribute.nameConstant,
                attribute.value
            )
        }

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
            nameConstant: Int
        ): MutableAnonymousAttributeImpl {
            val size = stream.readInt()
            val value = ByteArray(size) {
                stream.readByte()
            }
            return MutableAnonymousAttributeImpl(pool, nameConstant, value)
        }
    }
}