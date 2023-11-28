package com.shakelang.shake.bytecode.interpreter.format

import com.shakelang.shake.bytecode.interpreter.format.pool.ConstantPool
import com.shakelang.shake.bytecode.interpreter.format.pool.MutableConstantPool
import com.shakelang.shake.util.io.streaming.input.DataInputStream
import com.shakelang.shake.util.io.streaming.output.ByteArrayOutputStream
import com.shakelang.shake.util.io.streaming.output.DataOutputStream

open class Class(
    open val pool: ConstantPool,
    open val nameConstant: Int,
    open val superNameConstant: Int,
    open val interfacesConstants: List<Int>,
    open val fields: List<Int>,
    open val methods: List<Int>,
    open val subClasses: List<Int>
) {

    open val name: String get() = pool.getUtf8(nameConstant).value
    open val superName: String get() = pool.getUtf8(superNameConstant).value
    open val interfaces: List<String> get() = interfacesConstants.map { pool.getUtf8(it).value }

    fun dump(stream: DataOutputStream) {
        stream.writeInt(nameConstant)
        stream.writeInt(superNameConstant)
        stream.writeInt(interfacesConstants.size)
        for (interfaceConstant in interfacesConstants) {
            stream.writeInt(interfaceConstant)
        }
        stream.writeInt(fields.size)
        for (field in fields) {
            stream.writeInt(field)
        }
        stream.writeInt(methods.size)
        for (method in methods) {
            stream.writeInt(method)
        }
        stream.writeInt(subClasses.size)
        for (subClass in subClasses) {
            stream.writeInt(subClass)
        }
    }

    fun dump(): ByteArray {
        val byteStream = ByteArrayOutputStream()
        val stream = DataOutputStream(byteStream)
        dump(stream)
        return byteStream.toByteArray()
    }

    companion object {
        fun fromStream(stream: DataInputStream): Class {
            val name = stream.readInt()
            val superName = stream.readInt()
            val interfacesCount = stream.readInt()
            val interfaces = mutableListOf<Int>()
            for (i in 0 until interfacesCount) {
                interfaces.add(stream.readInt())
            }
            val fieldsCount = stream.readInt()
            val fields = mutableListOf<Int>()
            for (i in 0 until fieldsCount) {
                fields.add(stream.readInt())
            }
            val methodsCount = stream.readInt()
            val methods = mutableListOf<Int>()
            for (i in 0 until methodsCount) {
                methods.add(stream.readInt())
            }
            val subClassesCount = stream.readInt()
            val subClasses = mutableListOf<Int>()
            for (i in 0 until subClassesCount) {
                subClasses.add(stream.readInt())
            }
            return Class(
                ConstantPool.fromStream(stream),
                name,
                superName,
                interfaces,
                fields,
                methods,
                subClasses
            )
        }
    }
}

class MutableClass(
    override val pool: MutableConstantPool,
    override var nameConstant: Int,
    override var superNameConstant: Int,
    override var interfacesConstants: MutableList<Int>,
    override var fields: MutableList<Int>,
    override var methods: MutableList<Int>,
    override var subClasses: MutableList<Int>
) : Class(pool, nameConstant, superNameConstant, interfacesConstants, fields, methods, subClasses) {

    override var name: String
        get() = pool.getUtf8(nameConstant).value
        set(value) { nameConstant = pool.resolveUtf8(value) }

    override var superName: String
        get() = pool.getUtf8(superNameConstant).value
        set(value) { superNameConstant = pool.resolveUtf8(value) }

    override var interfaces: List<String>
        get() = interfacesConstants.map { pool.getUtf8(it).value }
        set(value) { interfacesConstants = value.map { pool.resolveUtf8(it) }.toMutableList() }

    fun addField(field: Int) = fields.add(field)
    fun addMethod(method: Int) = methods.add(method)
    fun addSubClass(subClass: Int) = subClasses.add(subClass)

    companion object {
        fun fromStream(stream: DataInputStream): MutableClass {
            val name = stream.readInt()
            val superName = stream.readInt()
            val interfacesCount = stream.readInt()
            val interfaces = mutableListOf<Int>()
            for (i in 0 until interfacesCount) {
                interfaces.add(stream.readInt())
            }
            val fieldsCount = stream.readInt()
            val fields = mutableListOf<Int>()
            for (i in 0 until fieldsCount) {
                fields.add(stream.readInt())
            }
            val methodsCount = stream.readInt()
            val methods = mutableListOf<Int>()
            for (i in 0 until methodsCount) {
                methods.add(stream.readInt())
            }
            val subClassesCount = stream.readInt()
            val subClasses = mutableListOf<Int>()
            for (i in 0 until subClassesCount) {
                subClasses.add(stream.readInt())
            }
            return MutableClass(
                MutableConstantPool.fromStream(stream),
                name,
                superName,
                interfaces,
                fields,
                methods,
                subClasses
            )
        }
    }
}
