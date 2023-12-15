package com.shakelang.shake.bytecode.interpreter.format.attribute

import com.shakelang.shake.bytecode.interpreter.format.pool.ConstantPool
import com.shakelang.shake.bytecode.interpreter.format.pool.MutableConstantPool
import com.shakelang.shake.util.io.streaming.input.DataInputStream
import com.shakelang.shake.util.io.streaming.output.ByteArrayOutputStream
import com.shakelang.shake.util.io.streaming.output.DataOutputStream

interface Attribute {
    val pool: ConstantPool
    val nameConstant: Int
    val value: ByteArray
    val name: String get() = pool.getUtf8(nameConstant).value
    fun dump(stream: DataOutputStream) {
        stream.writeUnsignedShort(nameConstant.toUShort())
        stream.writeInt(value.size)
        stream.write(value)
    }
    fun dump(): ByteArray {
        val stream = ByteArrayOutputStream()
        dump(DataOutputStream(stream))
        return stream.toByteArray()
    }
    override fun equals(other: Any?): Boolean
    override fun hashCode(): Int

    fun compareHelper(other: Any?): Boolean {
        if (this === other) return true
        if (other !is Attribute) return false
        if (pool != other.pool) return false
        if (nameConstant != other.nameConstant) return false
        if (!value.contentEquals(other.value)) return false
        return true
    }

    fun hashCodeHelper(): Int {
        var result = pool.hashCode()
        result = 31 * result + nameConstant
        result = 31 * result + value.contentHashCode()
        return result
    }

    companion object {
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

interface MutableAttribute : Attribute {
    override var nameConstant: Int
    override var value: ByteArray
    override val pool: MutableConstantPool
    override var name: String
        get() = super.name
        set(value) {
            nameConstant = pool.resolveUtf8(value)
        }

    companion object {
        fun fromStream(pool: MutableConstantPool, stream: DataInputStream): MutableAttribute {
            val nameConstant = stream.readInt()
            val name = pool.getUtf8(nameConstant).value

            return when (name) {
                "Code" -> MutableCodeAttribute.fromStream(pool, stream, nameConstant)
                else -> MutableAnonymousAttributeImpl.fromStream(pool, stream, nameConstant)
            }
        }
    }
}

