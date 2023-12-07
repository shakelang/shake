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

    override fun equals(other: Any?): Boolean {
        if (this === other) return true
        if (other !is StorageFormat) return false

        if (major != other.major) return false
        if (minor != other.minor) return false
        if (constantPool != other.constantPool) return false

        // TODO this is not the best way to do this (O(n^2))

        // find matching classes

        for (class_ in classes) {
            if (!other.classes.contains(class_)) return false
        }

        // find matching fields

        for (field in fields) {
            if (!other.fields.contains(field)) return false
        }

        // find matching methods

        for (method in methods) {
            if (!other.methods.contains(method)) return false
        }

        return true
    }

    override fun hashCode(): Int {
        var result = major.toInt()
        result = 31 * result + minor.toInt()
        result = 31 * result + constantPool.hashCode()
        result = 31 * result + classes.hashCode()
        result = 31 * result + fields.hashCode()
        result = 31 * result + methods.hashCode()
        return result
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
