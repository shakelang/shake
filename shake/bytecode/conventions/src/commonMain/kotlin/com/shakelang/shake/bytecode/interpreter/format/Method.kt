package com.shakelang.shake.bytecode.interpreter.format

import com.shakelang.shake.bytecode.interpreter.format.attribute.Attribute
import com.shakelang.shake.bytecode.interpreter.format.attribute.MutableAttribute
import com.shakelang.shake.bytecode.interpreter.format.pool.ConstantPool
import com.shakelang.shake.bytecode.interpreter.format.pool.MutableConstantPool
import com.shakelang.shake.util.io.streaming.input.DataInputStream
import com.shakelang.shake.util.io.streaming.output.ByteArrayOutputStream
import com.shakelang.shake.util.io.streaming.output.DataOutputStream
import kotlin.experimental.and
import kotlin.experimental.or

open class Method(
    open val pool: ConstantPool,
    open val nameConstant: Int,
    open val qualifiedNameConstant: Int,
    open val flags: Short,
    open val attributes: List<Attribute>
) {
    open val isPublic: Boolean
        get() = flags and 0b00000000_00000001.toShort() != 0.toShort()
    open val isPrivate: Boolean
        get() = flags and 0b00000000_00000010.toShort() != 0.toShort()
    open val isProtected: Boolean
        get() = flags and 0b00000000_00000100.toShort() != 0.toShort()
    open val isStatic: Boolean
        get() = flags and 0b00000000_00001000.toShort() != 0.toShort()
    open val isFinal: Boolean
        get() = flags and 0b00000000_00010000.toShort() != 0.toShort()

    open val name: String get() = pool.getUtf8(nameConstant).value
    open val qualifiedName: String get() = pool.getUtf8(qualifiedNameConstant).value

    fun dump(stream: DataOutputStream) {
        stream.writeInt(nameConstant)
        stream.writeInt(qualifiedNameConstant)
        stream.writeShort(flags)
        stream.writeShort(attributes.size.toShort())
        for (attribute in attributes) {
            attribute.dump(stream)
        }
    }

    fun dump(): ByteArray {
        val byteStream = ByteArrayOutputStream()
        val stream = DataOutputStream(byteStream)
        dump(stream)
        return byteStream.toByteArray()
    }

    override fun equals(other: Any?): Boolean {
        if (this === other) return true
        if (other !is Method) return false

        if (pool != other.pool) return false
        if (nameConstant != other.nameConstant) return false
        if (qualifiedNameConstant != other.qualifiedNameConstant) return false
        if (flags != other.flags) return false

        // TODO this is not the best way to do this (O(n^2))

        // find matching attributes

        for (attribute in attributes) {
            if (attribute !in other.attributes) return false
        }

        for (attribute in other.attributes) {
            if (attribute !in attributes) return false
        }

        return true
    }

    override fun hashCode(): Int {
        var result = pool.hashCode()
        result = 31 * result + nameConstant
        result = 31 * result + qualifiedNameConstant
        result = 31 * result + flags
        result = 31 * result + attributes.hashCode()
        return result
    }

    companion object {
        fun fromStream(pool: ConstantPool, stream: DataInputStream): Method {
            val name = stream.readInt()
            val qualifiedName = stream.readInt()
            val flags = stream.readShort()
            val attributeCount = stream.readShort().toInt()
            val attributes = (0 until attributeCount).map { Attribute.fromStream(pool, stream) }
            return Method(pool, name, qualifiedName, flags, attributes)
        }
    }
}

class MutableMethod(
    override val pool: MutableConstantPool,
    override var nameConstant: Int,
    override var qualifiedNameConstant: Int,
    override var flags: Short,
    attributes: MutableList<Attribute>
) : Method(pool, nameConstant, qualifiedNameConstant, flags, attributes) {

    override var name: String
        get() = pool.getUtf8(nameConstant).value
        set(value) {
            nameConstant = pool.resolveUtf8(value)
        }

    override var qualifiedName: String
        get() = pool.getUtf8(qualifiedNameConstant).value
        set(value) {
            qualifiedNameConstant = pool.resolveUtf8(value)
        }

    override val attributes: MutableList<Attribute>
        get() = super.attributes as MutableList<Attribute>

    override var isPublic: Boolean
        get() = flags and 0b00000000_00000001.toShort() != 0.toShort()
        set(value) {
            flags = if (value) {
                flags or 0b00000000_00000001.toShort()
            } else {
                flags and 0b11111111_11111110.toShort()
            }
        }

    override var isPrivate: Boolean
        get() = flags and 0b00000000_00000010.toShort() != 0.toShort()
        set(value) {
            flags = if (value) {
                flags or 0b00000000_00000010.toShort()
            } else {
                flags and 0b11111111_11111101.toShort()
            }
        }

    override var isProtected: Boolean
        get() = flags and 0b00000000_00000100.toShort() != 0.toShort()
        set(value) {
            flags = if (value) {
                flags or 0b00000000_00000100.toShort()
            } else {
                flags and 0b11111111_11111011.toShort()
            }
        }

    override var isStatic: Boolean
        get() = flags and 0b00000000_00001000.toShort() != 0.toShort()
        set(value) {
            flags = if (value) {
                flags or 0b00000000_00001000.toShort()
            } else {
                flags and 0b11111111_11110111.toShort()
            }
        }

    override var isFinal: Boolean
        get() = flags and 0b00000000_00010000.toShort() != 0.toShort()
        set(value) {
            flags = if (value) {
                flags or 0b00000000_00010000.toShort()
            } else {
                flags and 0b11111111_11101111.toShort()
            }
        }

    companion object {
        fun fromMethod(pool: MutableConstantPool, method: Method): MutableMethod {
            return MutableMethod(
                pool,
                method.nameConstant,
                method.qualifiedNameConstant,
                method.flags,
                method.attributes.map { MutableAttribute.fromAttribute(it) }.toMutableList()
            )
        }

        fun fromStream(pool: MutableConstantPool, stream: DataInputStream): MutableMethod {
            val name = stream.readInt()
            val qualifiedName = stream.readInt()
            val flags = stream.readShort()
            val attributeCount = stream.readShort().toInt()
            val attributes = (0 until attributeCount).map { MutableAttribute.fromStream(pool, stream) }
            return MutableMethod(pool, name, qualifiedName, flags, attributes.toMutableList())
        }
    }
}
