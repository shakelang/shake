package com.shakelang.shake.bytecode.interpreter.format

import com.shakelang.shake.bytecode.interpreter.format.pool.ConstantPool
import com.shakelang.shake.bytecode.interpreter.format.pool.MutableConstantPool
import com.shakelang.util.io.DumpAble
import com.shakelang.util.io.streaming.input.bytes.DataInputStream
import com.shakelang.util.io.streaming.output.bytes.DataOutputStream

/**
 * The magic number of the map format is used to identify the map format. It is always the same.
 * See the [map format specification](https://spec.shakelang.com/bytecode/map-format#magic)
 * for more information
 *
 * The magic number is `0x4a16a478`.
 *
 * @since 0.1.0
 * @version 0.1.0
 */
const val MAGIC = 0x4a16a478 // SHAKE MAGIC

/**
 * Implementation of the map format.
 * See the [map format specification](https://spec.shakelang.com/bytecode/file-format) for more information
 *
 * @param major The major version of the map format
 * @param minor The minor version of the map format
 * @param packageNameConstant The constant of the package name
 * @param constantPool The constant pool
 * @param classes The classes
 * @param fields The fields
 * @param methods The methods
 *
 * @since 0.1.0
 * @version 0.1.0
 */
open class StorageFormat(

    /**
     * The major version of the map format
     * See the [map format specification](https://spec.shakelang.com/bytecode/map-format#major-and-minor)
     * for more information
     * @since 0.1.0
     * @version 0.1.0
     */
    open val major: Short,

    /**
     * The minor version of the map format
     * See the [map format specification](https://spec.shakelang.com/bytecode/map-format#major-and-minor)
     * for more information
     * @since 0.1.0
     * @version 0.1.0
     */
    open val minor: Short,

    /**
     * The constant of the package name
     * See the [map format specification](https://spec.shakelang.com/bytecode/map-format#package-name)
     * @since 0.1.0
     * @version 0.1.0
     */
    open val packageNameConstant: Int,

    /**
     * The constant pool
     * See the [map format specification](https://spec.shakelang.com/bytecode/map-format#constant-pool-count--constant-pool)
     * for more information
     * @since 0.1.0
     * @version 0.1.0
     */
    open val constantPool: ConstantPool,

    /**
     * The classes
     * See the [map format specification](https://spec.shakelang.com/bytecode/map-format#class-count--classes)
     * for more information
     * @since 0.1.0
     * @version 0.1.0
     */
    open val classes: List<Class>,

    /**
     * The methods
     * See the [map format specification](https://spec.shakelang.com/bytecode/map-format#method-count--methods)
     * for more information
     * @since 0.1.0
     * @version 0.1.0
     */
    open val methods: List<Method>,

    /**
     * The fields
     * See the [map format specification](https://spec.shakelang.com/bytecode/map-format#field-count--fields)
     * for more information
     * @since 0.1.0
     * @version 0.1.0
     */
    open val fields: List<Field>,
) : DumpAble {

    /**
     * The magic number of the map format is used to identify the map format. It is always the same.
     * See the [map format specification](https://spec.shakelang.com/bytecode/map-format#magic)
     * for more information
     * @since 0.1.0
     * @version 0.1.0
     */
    open val magic: Int = MAGIC

    /**
     * The package name
     * This is a shortcut for `constantPool.getUtf8(packageNameConstant).value`
     * See the [map format specification](https://spec.shakelang.com/bytecode/map-format#package-name)
     * for more information
     * @since 0.1.0
     * @version 0.1.0
     */
    open val packageName: String
        get() = constantPool.getUtf8(packageNameConstant).value

    /**
     * Dump the map format to a [DataOutputStream]
     * @param stream The stream to dump the map format to
     * @since 0.1.0
     * @version 0.1.0
     */
    override fun dump(stream: DataOutputStream) {
        stream.writeInt(magic)
        stream.writeShort(major)
        stream.writeShort(minor)
        stream.writeInt(packageNameConstant)
        constantPool.dump(stream)
        stream.writeShort(classes.size.toShort())
        for (clazz in classes) {
            clazz.dump(stream)
        }
        stream.writeShort(methods.size.toShort())
        for (method in methods) {
            method.dump(stream)
        }
        stream.writeShort(fields.size.toShort())
        for (field in fields) {
            field.dump(stream)
        }
    }

    /**
     * Check if this map format is equal to another object
     * @param other The other object
     * @return If the map formats are equal
     * @since 0.1.0
     * @version 0.1.0
     */
    override fun equals(other: Any?): Boolean {
        if (this === other) return true
        if (other !is StorageFormat) return false

        if (major != other.major) return false
        if (minor != other.minor) return false
        if (packageName != other.packageName) return false
        if (constantPool != other.constantPool) return false

        // TODO this is not the best way to do this (O(n^2))
        // This should be improved in the future
        // It is not that important because this is only used for testing
        // and not for the actual bytecode

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

        return true
    }

    /**
     * Get the hash code of this map format
     * @return The hash code
     * @since 0.1.0
     * @version 0.1.0
     */
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

        /**
         * Create a [StorageFormat] from a [DataInputStream]
         * @param stream The stream to read the map format from
         * @return The map format
         * @since 0.1.0
         * @version 0.1.0
         */
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
            val methodsCount = stream.readShort().toInt()
            val methods = mutableListOf<Method>()
            for (i in 0 until methodsCount) {
                methods.add(Method.fromStream(constantPool, stream))
            }
            val fieldsCount = stream.readShort().toInt()
            val fields = mutableListOf<Field>()
            for (i in 0 until fieldsCount) {
                fields.add(Field.fromStream(constantPool, stream))
            }
            return StorageFormat(major, minor, packageNameConstant, constantPool, classes, methods, fields)
        }
    }
}

/**
 * Mutable implementation of the [StorageFormat]
 * See the [map format specification](https://spec.shakelang.com/bytecode/map-format) for more information
 * @see StorageFormat
 * @since 0.1.0
 * @version 0.1.0
 */
class MutableStorageFormat(

    /**
     * The major version of the map format
     * See the [map format specification](https://spec.shakelang.com/bytecode/map-format#major-and-minor)
     * for more information
     * @since 0.1.0
     * @version 0.1.0
     */
    override var major: Short,

    /**
     * The minor version of the map format
     * See the [map format specification](https://spec.shakelang.com/bytecode/map-format#major-and-minor)
     * for more information
     * @since 0.1.0
     * @version 0.1.0
     */
    override var minor: Short,

    /**
     * The constant of the package name
     * See the [map format specification](https://spec.shakelang.com/bytecode/map-format#package-name)
     * @since 0.1.0
     * @version 0.1.0
     */
    override var packageNameConstant: Int,

    /**
     * The constant pool
     * See the [map format specification](https://spec.shakelang.com/bytecode/map-format#constant-pool-count--constant-pool)
     * for more information
     * @since 0.1.0
     * @version 0.1.0
     */
    override var constantPool: MutableConstantPool,

    /**
     * The classes
     * See the [map format specification](https://spec.shakelang.com/bytecode/map-format#class-count--classes)
     * for more information
     * @since 0.1.0
     * @version 0.1.0
     */
    override var classes: MutableList<MutableClass>,

    /**
     * The methods
     * See the [map format specification](https://spec.shakelang.com/bytecode/map-format#method-count--methods)
     * for more information
     * @since 0.1.0
     * @version 0.1.0
     */
    override var methods: MutableList<MutableMethod>,

    /**
     * The fields
     * See the [map format specification](https://spec.shakelang.com/bytecode/map-format#field-count--fields)
     * for more information
     * @since 0.1.0
     * @version 0.1.0
     */
    override var fields: MutableList<MutableField>,
) : StorageFormat(
    major,
    minor,
    packageNameConstant,
    constantPool,
    classes,
    methods,
    fields,
) {

    /**
     * The package name
     * This is a shortcut for `constantPool.getUtf8(packageNameConstant).value`
     * See the [map format specification](https://spec.shakelang.com/bytecode/map-format#package-name)
     * for more information
     * @since 0.1.0
     * @version 0.1.0
     */
    override var packageName: String
        get() = constantPool.getUtf8(packageNameConstant).value
        set(value) {
            packageNameConstant = constantPool.resolveUtf8(value)
        }

    companion object {

        /**
         * Create a [MutableStorageFormat] from a [StorageFormat]
         * @param storageFormat The map format to create the mutable map format from
         * @return The mutable map format
         * @since 0.1.0
         * @version 0.1.0
         */
        fun fromStorageFormat(storageFormat: StorageFormat): MutableStorageFormat {
            val pool = MutableConstantPool.fromConstantPool(storageFormat.constantPool)
            return MutableStorageFormat(
                storageFormat.major,
                storageFormat.minor,
                storageFormat.packageNameConstant,
                pool,
                storageFormat.classes.map { MutableClass.fromClass(pool, it) }.toMutableList(),
                storageFormat.methods.map { MutableMethod.fromMethod(pool, it) }.toMutableList(),
                storageFormat.fields.map { MutableField.fromField(pool, it) }.toMutableList(),
            )
        }

        /**
         * Create a [MutableStorageFormat] from a [DataInputStream]
         * @param stream The stream to read the map format from
         * @return The map format
         * @since 0.1.0
         * @version 0.1.0
         */
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
                methods,
                fields,
            )
        }
    }
}
