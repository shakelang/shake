package com.shakelang.shake.bytecode.interpreter.format

import com.shakelang.shake.bytecode.interpreter.format.pool.ConstantPool
import com.shakelang.shake.bytecode.interpreter.format.pool.MutableConstantPool
import com.shakelang.shake.util.io.streaming.input.DataInputStream
import com.shakelang.shake.util.io.streaming.output.ByteArrayOutputStream
import com.shakelang.shake.util.io.streaming.output.DataOutputStream
import kotlin.experimental.and

open class Field(
    open val pool: ConstantPool,
    open val nameConstant: Int,
    open val attributes: Short
) {
    val isPublic: Boolean
        get() = attributes and 0b00000000_00000001.toShort() != 0.toShort()
    val isPrivate: Boolean
        get() = attributes and 0b00000000_00000010.toShort() != 0.toShort()
    val isProtected: Boolean
        get() = attributes and 0b00000000_00000100.toShort() != 0.toShort()
    val isStatic: Boolean
        get() = attributes and 0b00000000_00001000.toShort() != 0.toShort()
    val isFinal: Boolean
        get() = attributes and 0b00000000_00010000.toShort() != 0.toShort()

    val name: String get() = pool.getUtf8(nameConstant).value

    fun dump(stream: DataOutputStream) {
        stream.writeInt(nameConstant)
        stream.writeShort(attributes)
    }

    fun dump(): ByteArray {
        val byteStream = ByteArrayOutputStream()
        val stream = DataOutputStream(byteStream)
        dump(stream)
        return byteStream.toByteArray()
    }

    companion object {
        fun fromStream(pool: ConstantPool, stream: DataInputStream): Field {
            val name = stream.readInt()
            val attributes = stream.readShort()
            return Field(pool, name, attributes)
        }
    }
}

class MutableField(
    override val pool: MutableConstantPool,
    override var nameConstant: Int,
    override var attributes: Short
) : Field(pool, nameConstant, attributes) {
    fun setName(name: String) {
        nameConstant = pool.resolveUtf8(name)
    }

    companion object {
        fun fromField(pool: MutableConstantPool, field: Field): MutableField {
            return MutableField(
                pool,
                field.nameConstant,
                field.attributes
            )
        }
    }
}
