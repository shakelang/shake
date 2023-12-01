package com.shakelang.shake.bytecode.interpreter.format

import com.shakelang.shake.bytecode.interpreter.format.pool.ConstantPool
import com.shakelang.shake.bytecode.interpreter.format.pool.MutableConstantPool
import com.shakelang.shake.util.io.streaming.input.DataInputStream
import com.shakelang.shake.util.io.streaming.output.ByteArrayOutputStream
import com.shakelang.shake.util.io.streaming.output.DataOutputStream

val MAGIC = 0x5348414B // SHAK

open class StorageFormat(
    open val major: Short,
    open val minor: Short,
    open val constantPool: ConstantPool,
    open val classes: List<Class>,
    open val fields: List<Field>,
    open val methods: List<Method>
) {

    open val magic: Int = MAGIC

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

            if (magic != MAGIC) throw IllegalArgumentException("Magic number is not correct")

            val major = stream.readShort()
            val minor = stream.readShort()
            val constantPool = ConstantPool.fromStream(stream)
            val classesCount = stream.readInt()
            val classes = mutableListOf<Class>()
            for (i in 0 until classesCount) {
                classes.add(Class.fromStream(constantPool, stream))
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
            return StorageFormat(major, minor, constantPool, classes, fields, methods)
        }
    }
}

class MutableStorageFormat(
    override var major: Short,
    override var minor: Short,
    override var constantPool: MutableConstantPool,
    override var classes: MutableList<Class>,
    override var fields: MutableList<Field>,
    override var methods: MutableList<Method>
) : StorageFormat(
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
        fun fromStorageFormat(storageFormat: StorageFormat): MutableStorageFormat {
            val pool = MutableConstantPool.fromConstantPool(storageFormat.constantPool)
            return MutableStorageFormat(
                storageFormat.major,
                storageFormat.minor,
                pool,
                storageFormat.classes.map { MutableClass.fromClass(pool, it) }.toMutableList(),
                storageFormat.fields.map { MutableField.fromField(pool, it) }.toMutableList(),
                storageFormat.methods.map { MutableMethod.fromMethod(pool, it) }.toMutableList()
            )
        }
    }
}