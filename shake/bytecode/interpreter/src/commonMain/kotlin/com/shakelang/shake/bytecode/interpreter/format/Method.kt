package com.shakelang.shake.bytecode.interpreter.format

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
    open val attributes: Short
) {
    open val isPublic: Boolean
        get() = attributes and 0b00000000_00000001.toShort() != 0.toShort()
    open val isPrivate: Boolean
        get() = attributes and 0b00000000_00000010.toShort() != 0.toShort()
    open val isProtected: Boolean
        get() = attributes and 0b00000000_00000100.toShort() != 0.toShort()
    open val isStatic: Boolean
        get() = attributes and 0b00000000_00001000.toShort() != 0.toShort()
    open val isFinal: Boolean
        get() = attributes and 0b00000000_00010000.toShort() != 0.toShort()

    open val name: String get() = pool.getUtf8(nameConstant).value
    open val qualifiedName: String get() = pool.getUtf8(qualifiedNameConstant).value

    fun dump(stream: DataOutputStream) {
        stream.writeInt(nameConstant)
        stream.writeInt(qualifiedNameConstant)
        stream.writeShort(attributes)
    }

    fun dump(): ByteArray {
        val byteStream = ByteArrayOutputStream()
        val stream = DataOutputStream(byteStream)
        dump(stream)
        return byteStream.toByteArray()
    }

    companion object {
        fun fromStream(pool: ConstantPool, stream: DataInputStream): Method {
            val name = stream.readInt()
            val qualifiedName = stream.readInt()
            val attributes = stream.readShort()
            return Method(pool, name, qualifiedName, attributes)
        }
    }
}

class MutableMethod(
    override val pool: MutableConstantPool,
    override var nameConstant: Int,
    override var qualifiedNameConstant: Int,
    override var attributes: Short
) : Method(pool, nameConstant, qualifiedNameConstant, attributes) {

    override var isPublic: Boolean
        get() = attributes and 0b00000000_00000001.toShort() != 0.toShort()
        set(value) {
            attributes = if (value) attributes or 0b00000000_00000001.toShort()
            else attributes and 0b11111111_11111110.toShort()
        }

    override var isPrivate: Boolean
        get() = attributes and 0b00000000_00000010.toShort() != 0.toShort()
        set(value) {
            attributes = if (value) attributes or 0b00000000_00000010.toShort()
            else attributes and 0b11111111_11111101.toShort()
        }

    override var isProtected: Boolean
        get() = attributes and 0b00000000_00000100.toShort() != 0.toShort()
        set(value) {
            attributes = if (value) attributes or 0b00000000_00000100.toShort()
            else attributes and 0b11111111_11111011.toShort()
        }

    override var isStatic: Boolean
        get() = attributes and 0b00000000_00001000.toShort() != 0.toShort()
        set(value) {
            attributes = if (value) attributes or 0b00000000_00001000.toShort()
            else attributes and 0b11111111_11110111.toShort()
        }

    override var isFinal: Boolean
        get() = attributes and 0b00000000_00010000.toShort() != 0.toShort()
        set(value) {
            attributes = if (value) attributes or 0b00000000_00010000.toShort()
            else attributes and 0b11111111_11101111.toShort()
        }

    fun setName(name: String) {
        nameConstant = pool.resolveUtf8(name)
    }

    fun setQualifiedName(qualifiedName: String) {
        qualifiedNameConstant = pool.resolveUtf8(qualifiedName)
    }

    companion object {
        fun fromMethod(pool: MutableConstantPool, method: Method): MutableMethod {
            return MutableMethod(
                pool,
                method.nameConstant,
                method.qualifiedNameConstant,
                method.attributes
            )
        }
    }
}