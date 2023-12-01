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

    open fun bump(stream: DataOutputStream) {
        stream.writeInt(nameConstant)
        stream.writeInt(value.size)
        stream.write(value)
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