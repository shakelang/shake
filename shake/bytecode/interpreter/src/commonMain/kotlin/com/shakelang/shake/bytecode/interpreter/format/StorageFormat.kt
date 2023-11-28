package com.shakelang.shake.bytecode.interpreter.format

import com.shakelang.shake.bytecode.interpreter.format.pool.ConstantPool
import com.shakelang.shake.bytecode.interpreter.format.pool.MutableConstantPool
import com.shakelang.shake.util.io.streaming.input.DataInputStream
import com.shakelang.shake.util.io.streaming.output.ByteArrayOutputStream
import com.shakelang.shake.util.io.streaming.output.DataOutputStream

open class StorageFormat(
    open val magic: Int,
    open val major: Short,
    open val minor: Short,
    open val constantPool: ConstantPool,
    open val classes: List<Class>,
    open val fields: List<Field>,
    open val methods: List<Method>
) {
    fun dump(stream: DataOutputStream) {
        stream.writeInt(magic)
        stream.writeShort(major)
        stream.writeShort(minor)
        constantPool.dump(stream)
        stream.writeInt(classes.size)
        for (class_ in classes) {
            class_.dump(stream)
        }
        stream.writeInt(fields.size)
        for (field in fields) {
            field.dump(stream)
        }
        stream.writeInt(methods.size)
        for (method in methods) {
            method.dump(stream)
        }
    }

    fun dump(): ByteArray {
        val byteStream = ByteArrayOutputStream()
        val stream = DataOutputStream(byteStream)
        dump(stream)
        return byteStream.toByteArray()
    }

    companion object {
        fun fromStream(stream: DataInputStream): StorageFormat {
            val magic = stream.readInt()
            val major = stream.readShort()
            val minor = stream.readShort()
            val constantPool = ConstantPool.fromStream(stream)
            val classesCount = stream.readInt()
            val classes = mutableListOf<Class>()
            for (i in 0 until classesCount) {
                classes.add(Class.fromStream(stream))
            }
            val fieldsCount = stream.readInt()
            val fields = mutableListOf<Field>()
            for (i in 0 until fieldsCount) {
                fields.add(Field.fromStream(constantPool, stream))
            }
            val methodsCount = stream.readInt()
            val methods = mutableListOf<Method>()
            for (i in 0 until methodsCount) {
                methods.add(Method.fromStream(constantPool, stream))
            }
            return StorageFormat(magic, major, minor, constantPool, classes, fields, methods)
        }
    }
}

class MutableStorageFormat(
    override var magic: Int,
    override var major: Short,
    override var minor: Short,
    override var constantPool: MutableConstantPool,
    override var classes: MutableList<Class>,
    override var fields: MutableList<Field>,
    override var methods: MutableList<Method>
) : StorageFormat(
    magic,
    major,
    minor,
    constantPool,
    classes,
    fields,
    methods
) {
    fun createClass(name: String, superName: String, interfaces: List<String>): Int {
        val nameConstant = constantPool.resolveUtf8(name)
        val superNameConstant = constantPool.resolveUtf8(superName)
        val interfacesConstants = interfaces.map { constantPool.resolveUtf8(it) }
        val class_ = Class(constantPool, nameConstant, superNameConstant, interfacesConstants, emptyList(), emptyList(), emptyList())
        classes.add(class_)
        return classes.indexOf(class_)
    }

    fun createField(name: String, attributes: Short): Int {
        val nameConstant = constantPool.resolveUtf8(name)
        val field = Field(constantPool, nameConstant, attributes)
        fields.add(field)
        return fields.indexOf(field)
    }

    fun createMethod(name: String, qualifiedName: String, attributes: Short): Int {
        val nameConstant = constantPool.resolveUtf8(name)
        val qualifiedNameConstant = constantPool.resolveUtf8(qualifiedName)
        val method = Method(constantPool, nameConstant, qualifiedNameConstant, attributes)
        methods.add(method)
        return methods.indexOf(method)
    }

    companion object {
        fun fromStream(stream: DataInputStream): MutableStorageFormat {
            val magic = stream.readInt()
            val major = stream.readShort()
            val minor = stream.readShort()
            val constantPool = MutableConstantPool.fromStream(stream)
            val classesCount = stream.readInt()
            val classes = mutableListOf<Class>()
            for (i in 0 until classesCount) {
                classes.add(Class.fromStream(stream))
            }
            val fieldsCount = stream.readInt()
            val fields = mutableListOf<Field>()
            for (i in 0 until fieldsCount) {
                fields.add(Field.fromStream(constantPool, stream))
            }
            val methodsCount = stream.readInt()
            val methods = mutableListOf<Method>()
            for (i in 0 until methodsCount) {
                methods.add(Method.fromStream(constantPool, stream))
            }
            return MutableStorageFormat(magic, major, minor, constantPool, classes, fields, methods)
        }
    }
}
