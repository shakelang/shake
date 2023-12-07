package com.shakelang.shake.bytecode.interpreter.format

import com.shakelang.shake.bytecode.interpreter.format.pool.ConstantPool
import com.shakelang.shake.bytecode.interpreter.format.pool.MutableConstantPool
import com.shakelang.shake.util.io.streaming.input.DataInputStream
import com.shakelang.shake.util.io.streaming.output.ByteArrayOutputStream
import com.shakelang.shake.util.io.streaming.output.DataOutputStream
import kotlin.experimental.and
import kotlin.experimental.or

open class Class(
    open val pool: ConstantPool,
    open val nameConstant: Int,
    open val superNameConstant: Int,
    open val flags: Short,
    open val interfacesConstants: List<Int>,
    open val fields: List<Field>,
    open val methods: List<Method>,
    open val subClasses: List<Class>,
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
    open val superName: String get() = pool.getUtf8(superNameConstant).value
    open val interfaces: List<String> get() = interfacesConstants.map { pool.getUtf8(it).value }

    fun dump(stream: DataOutputStream) {
        stream.writeInt(nameConstant)
        stream.writeInt(superNameConstant)
        stream.writeShort(flags)
        stream.writeInt(interfacesConstants.size)
        for (interfaceConstant in interfacesConstants) {
            stream.writeInt(interfaceConstant)
        }
        stream.writeInt(fields.size)
        for (field in fields) {
            field.dump(stream)
        }
        stream.writeInt(methods.size)
        for (method in methods) {
            method.dump(stream)
        }
        stream.writeInt(subClasses.size)
        for (subClass in subClasses) {
            subClass.dump(stream)
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
        if (other !is Class) return false

        if (pool != other.pool) return false
        if (nameConstant != other.nameConstant) return false
        if (superNameConstant != other.superNameConstant) return false
        if (flags != other.flags) return false

        // TODO this is not the best way to do this (O(n^2))

        // find matching interfaces

        for (interfaceConstant in interfacesConstants) {
            if (interfaceConstant !in other.interfacesConstants) return false
        }

        for (interfaceConstant in other.interfacesConstants) {
            if (interfaceConstant !in interfacesConstants) return false
        }

        // find matching fields

        for (field in fields) {
            if (field !in other.fields) return false
        }

        for (field in other.fields) {
            if (field !in fields) return false
        }

        // find matching methods

        for (method in methods) {
            if (method !in other.methods) return false
        }

        for (method in other.methods) {
            if (method !in methods) return false
        }

        // find matching subclasses

        for (subClass in subClasses) {
            if (subClass !in other.subClasses) return false
        }

        for (subClass in other.subClasses) {
            if (subClass !in subClasses) return false
        }

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
        result = 31 * result + superNameConstant
        result = 31 * result + flags
        result = 31 * result + interfacesConstants.hashCode()
        result = 31 * result + fields.hashCode()
        result = 31 * result + methods.hashCode()
        result = 31 * result + subClasses.hashCode()
        result = 31 * result + attributes.hashCode()
        return result
    }

    companion object {
        fun fromStream(pool: ConstantPool, stream: DataInputStream): Class {
            val name = stream.readInt()
            val superName = stream.readInt()
            val flags = stream.readShort()
            val interfacesCount = stream.readInt()
            val interfaces = mutableListOf<Int>()
            for (i in 0 until interfacesCount) {
                interfaces.add(stream.readInt())
            }
            val fieldsCount = stream.readInt()
            val fields = mutableListOf<Field>()
            for (i in 0 until fieldsCount) {
                fields.add(Field.fromStream(pool, stream))
            }
            val methodsCount = stream.readInt()
            val methods = mutableListOf<Method>()
            for (i in 0 until methodsCount) {
                methods.add(Method.fromStream(pool, stream))
            }
            val subClassesCount = stream.readInt()
            val subClasses = mutableListOf<Class>()
            for (i in 0 until subClassesCount) {
                subClasses.add(fromStream(pool, stream))
            }
            val attributesCount = stream.readInt()
            val attributes = (0 until attributesCount).map { Attribute.fromStream(pool, stream) }
            return Class(
                ConstantPool.fromStream(stream),
                name,
                superName,
                flags,
                interfaces,
                fields,
                methods,
                subClasses,
                attributes
            )
        }
    }
}

class MutableClass(
    override val pool: MutableConstantPool,
    override var nameConstant: Int,
    override var superNameConstant: Int,
    override var flags: Short,
    override var interfacesConstants: MutableList<Int>,
    fields: MutableList<MutableField>,
    methods: MutableList<MutableMethod>,
    subClasses: MutableList<MutableClass>,
    attributes: MutableList<MutableAttribute>
) : Class(pool, nameConstant, superNameConstant, flags, interfacesConstants, fields, methods, subClasses, attributes) {

    @Suppress("UNCHECKED_CAST")
    override val fields: MutableList<MutableField>
        get() = super.fields as MutableList<MutableField>

    @Suppress("UNCHECKED_CAST")
    override val methods: MutableList<MutableMethod>
        get() = super.methods as MutableList<MutableMethod>

    @Suppress("UNCHECKED_CAST")
    override val subClasses: MutableList<MutableClass>
        get() = super.subClasses as MutableList<MutableClass>

    override var isPublic: Boolean
        get() = super.isPublic
        set(value) { flags = if (value) flags or 0b00000000_00000001.toShort() else flags and 0b11111111_11111110.toShort() }

    override var isPrivate: Boolean
        get() = super.isPrivate
        set(value) { flags = if (value) flags or 0b00000000_00000010.toShort() else flags and 0b11111111_11111101.toShort() }

    override var isProtected: Boolean
        get() = super.isProtected
        set(value) { flags = if (value) flags or 0b00000000_00000100.toShort() else flags and 0b11111111_11111011.toShort() }

    override var isStatic: Boolean
        get() = super.isStatic
        set(value) { flags = if (value) flags or 0b00000000_00001000.toShort() else flags and 0b11111111_11110111.toShort() }

    override var isFinal: Boolean
        get() = super.isFinal
        set(value) { flags = if (value) flags or 0b00000000_00010000.toShort() else flags and 0b11111111_11101111.toShort() }

    override var name: String
        get() = pool.getUtf8(nameConstant).value
        set(value) { nameConstant = pool.resolveUtf8(value) }

    override var superName: String
        get() = pool.getUtf8(superNameConstant).value
        set(value) { superNameConstant = pool.resolveUtf8(value) }

    override var interfaces: List<String>
        get() = interfacesConstants.map { pool.getUtf8(it).value }
        set(value) { interfacesConstants = value.map { pool.resolveUtf8(it) }.toMutableList() }

    fun addField(field: MutableField) = fields.add(field)
    fun addMethod(method: MutableMethod) = methods.add(method)
    fun addSubClass(subClass: MutableClass) = subClasses.add(subClass)

    companion object {
        fun fromClass(pool: MutableConstantPool, class_: Class): MutableClass {
            return MutableClass(
                pool,
                class_.nameConstant,
                class_.superNameConstant,
                class_.flags,
                class_.interfacesConstants.toMutableList(),
                class_.fields.map { MutableField.fromField(pool, it) }.toMutableList(),
                class_.methods.map { MutableMethod.fromMethod(pool, it) }.toMutableList(),
                class_.subClasses.map { fromClass(pool, it) }.toMutableList(),
                class_.attributes.map { MutableAttribute.fromAttribute(it) }.toMutableList()
            )
        }
    }
}
