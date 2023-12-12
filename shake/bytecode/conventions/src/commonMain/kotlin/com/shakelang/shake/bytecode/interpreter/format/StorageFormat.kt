package com.shakelang.shake.bytecode.interpreter.format

import com.shakelang.shake.bytecode.interpreter.format.pool.ConstantPool
import com.shakelang.shake.bytecode.interpreter.format.pool.MutableConstantPool
import com.shakelang.shake.util.io.streaming.input.DataInputStream
import com.shakelang.shake.util.io.streaming.output.ByteArrayOutputStream
import com.shakelang.shake.util.io.streaming.output.DataOutputStream

const val MAGIC = 0x4a16a478 // SHAKE MAGIC

open class StorageFormat(
    open val major: Short,
    open val minor: Short,
    open val packageNameConstant: Int,
    open val constantPool: ConstantPool,
    open val classes: List<Class>,
    open val fields: List<Field>,
    open val methods: List<Method>
) {

    open val magic: Int = MAGIC
    open val packageName: String
        get() = constantPool.getUtf8(packageNameConstant).value

    fun dump(stream: DataOutputStream) {
        stream.writeInt(magic)
        stream.writeShort(major)
        stream.writeShort(minor)
        stream.writeInt(packageNameConstant)
        constantPool.dump(stream)
        stream.writeShort(classes.size.toShort())
        for (clazz in classes) {
            clazz.dump(stream)
        }
        stream.writeShort(fields.size.toShort())
        for (field in fields) {
            field.dump(stream)
        }
        stream.writeShort(methods.size.toShort())
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
        if (packageName != other.packageName) return false
        if (constantPool != other.constantPool) return false

        // TODO this is not the best way to do this (O(n^2))

        // find matching classes

        for (field in fields) {
            if (field !in other.fields) return false
        }

        for (method in methods) {
            if (method !in other.methods) return false
        }

        for (clazz in classes) {
            if (clazz !in other.classes) return false
        }

        println("done")

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
            val packageNameConstant = stream.readInt()
            val constantPool = ConstantPool.fromStream(stream)
            val classesCount = stream.readShort().toInt()
            val classes = mutableListOf<Class>()
            for (i in 0 until classesCount) {
                classes.add(Class.fromStream(constantPool, stream))
            }
            val fieldsCount = stream.readShort().toInt()
            val fields = mutableListOf<Field>()
            for (i in 0 until fieldsCount) {
                fields.add(Field.fromStream(constantPool, stream))
            }
            val methodsCount = stream.readShort().toInt()
            val methods = mutableListOf<Method>()
            for (i in 0 until methodsCount) {
                methods.add(Method.fromStream(constantPool, stream))
            }
            return StorageFormat(major, minor, packageNameConstant, constantPool, classes, fields, methods)
        }
    }
}

class MutableStorageFormat(
    override var major: Short,
    override var minor: Short,
    override var packageNameConstant: Int,
    override var constantPool: MutableConstantPool,
    override var classes: MutableList<MutableClass>,
    override var fields: MutableList<MutableField>,
    override var methods: MutableList<MutableMethod>
) : StorageFormat(
    major,
    minor,
    packageNameConstant,
    constantPool,
    classes,
    fields,
    methods
) {
    override var packageName: String
        get() = constantPool.getUtf8(packageNameConstant).value
        set(value) {
            packageNameConstant = constantPool.resolveUtf8(value)
        }

    companion object {
        fun fromStorageFormat(storageFormat: StorageFormat): MutableStorageFormat {
            val pool = MutableConstantPool.fromConstantPool(storageFormat.constantPool)
            return MutableStorageFormat(
                storageFormat.major,
                storageFormat.minor,
                storageFormat.packageNameConstant,
                pool,
                storageFormat.classes.map { MutableClass.fromClass(pool, it) }.toMutableList(),
                storageFormat.fields.map { MutableField.fromField(pool, it) }.toMutableList(),
                storageFormat.methods.map { MutableMethod.fromMethod(pool, it) }.toMutableList()
            )
        }

        fun fromStream(stream: DataInputStream): MutableStorageFormat {
            val magic = stream.readInt()
            if (magic != MAGIC) throw IllegalArgumentException("Magic number is not correct")
            val major = stream.readShort()
            val minor = stream.readShort()
            val packageNameConstant = stream.readInt()
            val pool = MutableConstantPool.fromStream(stream)
            val classesCount = stream.readShort().toInt()
            val classes = mutableListOf<MutableClass>()
            for (i in 0 until classesCount) {
                classes.add(MutableClass.fromStream(pool, stream))
            }
            val fieldsCount = stream.readShort().toInt()
            val fields = mutableListOf<MutableField>()
            for (i in 0 until fieldsCount) {
                fields.add(MutableField.fromStream(pool, stream))
            }
            val methodsCount = stream.readShort().toInt()
            val methods = mutableListOf<MutableMethod>()
            for (i in 0 until methodsCount) {
                methods.add(MutableMethod.fromStream(pool, stream))
            }
            return MutableStorageFormat(
                major,
                minor,
                packageNameConstant,
                pool,
                classes,
                fields,
                methods
            )
        }
    }
}
