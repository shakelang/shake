package com.shakelang.shake.bytecode.interpreter.format

import com.shakelang.shake.bytecode.interpreter.format.pool.ConstantPool
import com.shakelang.shake.bytecode.interpreter.format.pool.MutableConstantPool
import com.shakelang.shake.util.io.streaming.input.DataInputStream
import com.shakelang.shake.util.io.streaming.output.DataOutputStream

open class Attribute(
    open val pool: ConstantPool,
    open val nameConstant: Int,
    open val value: ByteArray
) {

    val name: String get() = pool.getUtf8(nameConstant).value

    open fun bump(stream: DataOutputStream) {
        stream.writeInt(nameConstant)
        stream.writeInt(value.size)
        stream.write(value)
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
            return Attribute(pool, name, value)
        }
    }
}

class MutableAttribute(
    pool: MutableConstantPool,
    override var nameConstant: Int,
    override var value: ByteArray
) : Attribute(pool, nameConstant, value) {

    override val pool: ConstantPool
        get() = super.pool as MutableConstantPool

    companion object {
        fun fromAttribute(attribute: Attribute): MutableAttribute {
            return MutableAttribute(
                attribute.pool as MutableConstantPool,
                attribute.nameConstant,
                attribute.value
            )
        }
    }
}
