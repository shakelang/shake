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
    open val flags: Short,
    open val attributes: List<Attribute>
) {
    val isPublic: Boolean
        get() = flags and 0b00000000_00000001.toShort() != 0.toShort()
    val isPrivate: Boolean
        get() = flags and 0b00000000_00000010.toShort() != 0.toShort()
    val isProtected: Boolean
        get() = flags and 0b00000000_00000100.toShort() != 0.toShort()
    val isStatic: Boolean
        get() = flags and 0b00000000_00001000.toShort() != 0.toShort()
    val isFinal: Boolean
        get() = flags and 0b00000000_00010000.toShort() != 0.toShort()

    val name: String get() = pool.getUtf8(nameConstant).value

    fun dump(stream: DataOutputStream) {
        stream.writeInt(nameConstant)
        stream.writeShort(flags)
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
            val flags = stream.readShort()
            val attributeCount = stream.readInt()
            val attributes = (0 until attributeCount).map { Attribute.fromStream(pool, stream) }
            return Field(pool, name, flags, attributes)
        }
    }
}

class MutableField(
    override val pool: MutableConstantPool,
    override var nameConstant: Int,
    override var flags: Short,
    attributes: MutableList<Attribute>
) : Field(pool, nameConstant, flags, attributes) {

    override val attributes: MutableList<Attribute>
        get() = super.attributes as MutableList<Attribute>

    fun setName(name: String) {
        nameConstant = pool.resolveUtf8(name)
    }

    companion object {
        fun fromField(pool: MutableConstantPool, field: Field): MutableField {
            return MutableField(
                pool,
                field.nameConstant,
                field.flags,
                field.attributes.toMutableList()
            )
        }
    }
}
