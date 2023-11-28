package com.shakelang.shake.bytecode.interpreter

import com.shakelang.shake.util.io.streaming.input.DataInputStream
import com.shakelang.shake.util.io.streaming.input.dataStream
import com.shakelang.shake.util.io.streaming.output.ByteArrayOutputStream
import com.shakelang.shake.util.io.streaming.output.DataOutputStream
import kotlin.experimental.and

open class ConstantPool(
    open val entries: List<ConstantPoolEntry>
) : List<ConstantPoolEntry> by entries {

    fun isUtf8(identifier: Int) = entries[identifier] is ConstantPoolEntry.Utf8Constant
    fun isByte(identifier: Int) = entries[identifier] is ConstantPoolEntry.ByteConstant
    fun isShort(identifier: Int) = entries[identifier] is ConstantPoolEntry.ShortConstant
    fun isInt(identifier: Int) = entries[identifier] is ConstantPoolEntry.IntConstant
    fun isLong(identifier: Int) = entries[identifier] is ConstantPoolEntry.LongConstant
    fun isFloat(identifier: Int) = entries[identifier] is ConstantPoolEntry.FloatConstant
    fun isDouble(identifier: Int) = entries[identifier] is ConstantPoolEntry.DoubleConstant
    fun isClass(identifier: Int) = entries[identifier] is ConstantPoolEntry.ClassConstant

    fun getUtf8(identifier: Int) = entries[identifier] as ConstantPoolEntry.Utf8Constant
    fun getByte(identifier: Int) = entries[identifier] as ConstantPoolEntry.ByteConstant
    fun getShort(identifier: Int) = entries[identifier] as ConstantPoolEntry.ShortConstant
    fun getInt(identifier: Int) = entries[identifier] as ConstantPoolEntry.IntConstant
    fun getLong(identifier: Int) = entries[identifier] as ConstantPoolEntry.LongConstant
    fun getFloat(identifier: Int) = entries[identifier] as ConstantPoolEntry.FloatConstant
    fun getDouble(identifier: Int) = entries[identifier] as ConstantPoolEntry.DoubleConstant
    fun getClass(identifier: Int) = entries[identifier] as ConstantPoolEntry.ClassConstant

    fun findUtf8(value: String): Int? {
        for (i in entries.indices) {
            val entry = entries[i]
            if (entry is ConstantPoolEntry.Utf8Constant && entry.value == value) {
                return i
            }
        }
        return null
    }

    fun findByte(value: Byte): Int? {
        for (i in entries.indices) {
            val entry = entries[i]
            if (entry is ConstantPoolEntry.ByteConstant && entry.value == value) {
                return i
            }
        }
        return null
    }

    fun findShort(value: Short): Int? {
        for (i in entries.indices) {
            val entry = entries[i]
            if (entry is ConstantPoolEntry.ShortConstant && entry.value == value) {
                return i
            }
        }
        return null
    }

    fun findInt(value: Int): Int? {
        for (i in entries.indices) {
            val entry = entries[i]
            if (entry is ConstantPoolEntry.IntConstant && entry.value == value) {
                return i
            }
        }
        return null
    }

    fun findLong(value: Long): Int? {
        for (i in entries.indices) {
            val entry = entries[i]
            if (entry is ConstantPoolEntry.LongConstant && entry.value == value) {
                return i
            }
        }
        return null
    }

    fun findFloat(value: Float): Int? {
        for (i in entries.indices) {
            val entry = entries[i]
            if (entry is ConstantPoolEntry.FloatConstant && entry.value == value) {
                return i
            }
        }
        return null
    }

    fun findDouble(value: Double): Int? {
        for (i in entries.indices) {
            val entry = entries[i]
            if (entry is ConstantPoolEntry.DoubleConstant && entry.value == value) {
                return i
            }
        }
        return null
    }

    fun findClass(identifier: Int): Int? {
        for (i in entries.indices) {
            val entry = entries[i]
            if (entry is ConstantPoolEntry.ClassConstant && entry.identifier == identifier) {
                return i
            }
        }
        return null
    }

    fun findClass(name: String): Int? {
        val identifier = findUtf8(name) ?: return null
        return findClass(identifier)
    }

    fun dump(stream: DataOutputStream) {
        stream.writeInt(entries.size)
        for (entry in entries) {
            entry.dump(stream)
        }
    }

    fun dump(): ByteArray {
        val byteStream = ByteArrayOutputStream()
        val stream = DataOutputStream(byteStream)
        dump(stream)
        return byteStream.toByteArray()
    }

    companion object {
        fun fromStream(stream: DataInputStream): ConstantPool {
            val entries = mutableListOf<ConstantPoolEntry>()
            val count = stream.readInt()
            for (i in 0 until count) {
                entries.add(ConstantPoolEntry.fromStream(stream))
            }
            return ConstantPool(entries)
        }

        fun fromByteArray(array: ByteArray): ConstantPool {
            return fromStream(array.dataStream())
        }

        fun fromList(list: List<ConstantPoolEntry>): ConstantPool {
            return ConstantPool(list)
        }
    }
}


class MutableConstantPool(
    override val entries: MutableList<ConstantPoolEntry> = mutableListOf()
) : ConstantPool(entries), MutableList<ConstantPoolEntry> by entries {
    fun createUtf8(value: String): Int {
        val entry = ConstantPoolEntry.Utf8Constant(value)
        add(entry)
        return entries.indexOf(entry)
    }

    fun createByte(value: Byte): Int {
        val entry = ConstantPoolEntry.ByteConstant(value)
        add(entry)
        return entries.indexOf(entry)
    }

    fun createShort(value: Short): Int {
        val entry = ConstantPoolEntry.ShortConstant(value)
        add(entry)
        return entries.indexOf(entry)
    }

    fun createInt(value: Int): Int {
        val entry = ConstantPoolEntry.IntConstant(value)
        add(entry)
        return entries.indexOf(entry)
    }

    fun createLong(value: Long): Int {
        val entry = ConstantPoolEntry.LongConstant(value)
        add(entry)
        return entries.indexOf(entry)
    }

    fun createFloat(value: Float): Int {
        val entry = ConstantPoolEntry.FloatConstant(value)
        add(entry)
        return entries.indexOf(entry)
    }

    fun createDouble(value: Double): Int {
        val entry = ConstantPoolEntry.DoubleConstant(value)
        add(entry)
        return entries.indexOf(entry)
    }

    fun createClass(identifier: Int): Int {
        val entry = ConstantPoolEntry.ClassConstant(identifier)
        add(entry)
        return entries.indexOf(entry)
    }

    fun createClass(name: String) = createClass(createUtf8(name))

    fun resolveUtf8(identifier: String) = findUtf8(identifier) ?: createUtf8(identifier)
    fun resolveByte(identifier: Byte) = findByte(identifier) ?: createByte(identifier)
    fun resolveShort(identifier: Short) = findShort(identifier) ?: createShort(identifier)
    fun resolveInt(identifier: Int) = findInt(identifier) ?: createInt(identifier)
    fun resolveLong(identifier: Long) = findLong(identifier) ?: createLong(identifier)
    fun resolveFloat(identifier: Float) = findFloat(identifier) ?: createFloat(identifier)
    fun resolveDouble(identifier: Double) = findDouble(identifier) ?: createDouble(identifier)
    fun resolveClass(identifier: Int) = findClass(identifier) ?: createClass(identifier)
    fun resolveClass(identifier: String) = findClass(identifier) ?: createClass(identifier)

    companion object {
        fun fromStream(stream: DataInputStream): MutableConstantPool {
            val entries = mutableListOf<ConstantPoolEntry>()
            val count = stream.readInt()
            for (i in 0 until count) {
                entries.add(ConstantPoolEntry.fromStream(stream))
            }
            return MutableConstantPool(entries)
        }

        fun fromByteArray(array: ByteArray): MutableConstantPool {
            return fromStream(array.dataStream())
        }

        fun fromList(list: List<ConstantPoolEntry>): MutableConstantPool {
            return MutableConstantPool(list.toMutableList())
        }
    }
}

sealed class ConstantPoolEntry {

    abstract override fun toString(): String
    abstract fun dump(stream: DataOutputStream)

    fun dump(): ByteArray {
        val byteStream = ByteArrayOutputStream()
        val stream = DataOutputStream(byteStream)
        dump(stream)
        return byteStream.toByteArray()
    }

    class Utf8Constant(val value: String) : ConstantPoolEntry() {
        override fun toString(): String {
            return "Utf8Constant(value='$value')"
        }

        override fun dump(stream: DataOutputStream) {
            stream.writeByte(1)
            stream.writeUTF8(value)
        }

        companion object {
            fun fromStream(stream: DataInputStream): Utf8Constant {
                return Utf8Constant(stream.readUTF())
            }
        }

    }

    class ByteConstant(val value: Byte) : ConstantPoolEntry() {
        override fun toString(): String {
            return "ByteConstant(value=$value)"
        }

        override fun dump(stream: DataOutputStream) {
            stream.writeByte(2)
            stream.writeByte(value)
        }

        companion object {
            fun fromStream(stream: DataInputStream): ByteConstant {
                return ByteConstant(stream.readByte())
            }
        }
    }

    class ShortConstant(val value: Short) : ConstantPoolEntry() {
        override fun toString(): String {
            return "ShortConstant(value=$value)"
        }

        override fun dump(stream: DataOutputStream) {
            stream.writeByte(3)
            stream.writeShort(value)
        }

        companion object {
            fun fromStream(stream: DataInputStream): ShortConstant {
                return ShortConstant(stream.readShort())
            }
        }
    }

    class IntConstant(val value: Int) : ConstantPoolEntry() {
        override fun toString(): String {
            return "IntConstant(value=$value)"
        }

        override fun dump(stream: DataOutputStream) {
            stream.writeByte(4)
            stream.writeInt(value)
        }

        companion object {
            fun fromStream(stream: DataInputStream): IntConstant {
                return IntConstant(stream.readInt())
            }
        }
    }

    class LongConstant(val value: Long) : ConstantPoolEntry() {
        override fun toString(): String {
            return "LongConstant(value=$value)"
        }

        override fun dump(stream: DataOutputStream) {
            stream.writeByte(5)
            stream.writeLong(value)
        }

        companion object {
            fun fromStream(stream: DataInputStream): LongConstant {
                return LongConstant(stream.readLong())
            }
        }
    }

    class FloatConstant(val value: Float) : ConstantPoolEntry() {
        override fun toString(): String {
            return "FloatConstant(value=$value)"
        }

        override fun dump(stream: DataOutputStream) {
            stream.writeByte(6)
            stream.writeFloat(value)
        }

        companion object {
            fun fromStream(stream: DataInputStream): FloatConstant {
                return FloatConstant(stream.readFloat())
            }
        }
    }

    class DoubleConstant(val value: Double) : ConstantPoolEntry() {
        override fun toString(): String {
            return "DoubleConstant(value=$value)"
        }

        override fun dump(stream: DataOutputStream) {
            stream.writeByte(7)
            stream.writeDouble(value)
        }

        companion object {
            fun fromStream(stream: DataInputStream): DoubleConstant {
                return DoubleConstant(stream.readDouble())
            }
        }
    }

    class ClassConstant(val identifier: Int) : ConstantPoolEntry() {
        override fun toString(): String {
            return "ClassConstant(identifier=$identifier)"
        }

        override fun dump(stream: DataOutputStream) {
            stream.writeByte(8)
            stream.writeInt(identifier)
        }

        companion object {
            fun fromStream(stream: DataInputStream): ClassConstant {
                return ClassConstant(stream.readInt())
            }
        }
    }

    companion object {
        fun fromStream(stream: DataInputStream): ConstantPoolEntry {
            val type = stream.readByte()
            return when (type) {
                1.toByte() -> Utf8Constant.fromStream(stream)
                2.toByte() -> ByteConstant.fromStream(stream)
                3.toByte() -> ShortConstant.fromStream(stream)
                4.toByte() -> IntConstant.fromStream(stream)
                5.toByte() -> LongConstant.fromStream(stream)
                6.toByte() -> FloatConstant.fromStream(stream)
                7.toByte() -> DoubleConstant.fromStream(stream)
                8.toByte() -> ClassConstant.fromStream(stream)
                else -> throw Exception("Unknown constant pool entry type: $type")
            }
        }
    }
}

open class Field(
    open val pool: ConstantPool,
    open val nameConstant: Int,
    open val attributes: Short,
) {
    val isPublic: Boolean
        get() = attributes and 0b00000000_00000001.toShort() != 0.toShort()
    val isPrivate: Boolean
        get() = attributes and 0b00000000_00000010.toShort() != 0.toShort()
    val isProtected: Boolean
        get() = attributes and 0b00000000_00000100.toShort() != 0.toShort()
    val isStatic: Boolean
        get() = attributes and 0b00000000_00001000.toShort() != 0.toShort()
    val isFinal: Boolean
        get() = attributes and 0b00000000_00010000.toShort() != 0.toShort()

    val name: String get() = pool.getUtf8(nameConstant).value

    fun dump(stream: DataOutputStream) {
        stream.writeInt(nameConstant)
        stream.writeShort(attributes)
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
            val attributes = stream.readShort()
            return Field(pool, name, attributes)
        }
    }
}

class MutableField(
    override val pool: MutableConstantPool,
    override var nameConstant: Int,
    override var attributes: Short,
) : Field(pool, nameConstant, attributes) {
    fun setName(name: String) {
        nameConstant = pool.resolveUtf8(name)
    }

    companion object {
        fun fromStream(pool: MutableConstantPool, stream: DataInputStream): MutableField {
            val name = stream.readInt()
            val attributes = stream.readShort()
            return MutableField(pool, name, attributes)
        }
    }
}

open class Method(
    open val pool: ConstantPool,
    open val nameConstant: Int,
    open val qualifiedNameConstant: Int,
    open val attributes: Short,
) {
    val isPublic: Boolean
        get() = attributes and 0b00000000_00000001.toShort() != 0.toShort()
    val isPrivate: Boolean
        get() = attributes and 0b00000000_00000010.toShort() != 0.toShort()
    val isProtected: Boolean
        get() = attributes and 0b00000000_00000100.toShort() != 0.toShort()
    val isStatic: Boolean
        get() = attributes and 0b00000000_00001000.toShort() != 0.toShort()
    val isFinal: Boolean
        get() = attributes and 0b00000000_00010000.toShort() != 0.toShort()

    val name: String get() = pool.getUtf8(nameConstant).value
    val qualifiedName: String get() = pool.getUtf8(qualifiedNameConstant).value

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
    override var attributes: Short,
) : Method(pool, nameConstant, qualifiedNameConstant, attributes) {
    fun setName(name: String) {
        nameConstant = pool.resolveUtf8(name)
    }

    fun setQualifiedName(qualifiedName: String) {
        qualifiedNameConstant = pool.resolveUtf8(qualifiedName)
    }

    companion object {
        fun fromStream(pool: MutableConstantPool, stream: DataInputStream): MutableMethod {
            val name = stream.readInt()
            val qualifiedName = stream.readInt()
            val attributes = stream.readShort()
            return MutableMethod(pool, name, qualifiedName, attributes)
        }
    }
}

open class Class(
    open val pool: ConstantPool,
    open val nameConstant: Int,
    open val superNameConstant: Int,
    open val interfacesConstants: List<Int>,
    open val fields: List<Int>,
    open val methods: List<Int>,
    open val subClasses: List<Int>,
) {

    val name: String get() = pool.getUtf8(nameConstant).value
    val superName: String get() = pool.getUtf8(superNameConstant).value
    val interfaces: List<String> get() = interfacesConstants.map { pool.getUtf8(it).value }

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
    override var subClasses: MutableList<Int>,
) : Class(pool, nameConstant, superNameConstant, interfacesConstants, fields, methods, subClasses) {
    fun setName(name: String) {
        nameConstant = pool.resolveUtf8(name)
    }

    fun setSuperName(superName: String) {
        superNameConstant = pool.resolveUtf8(superName)
    }

    fun addInterface(interfaceName: String) {
        interfacesConstants.add(pool.resolveUtf8(interfaceName))
    }

    fun addField(field: Int) {
        fields.add(field)
    }

    fun addMethod(method: Int) {
        methods.add(method)
    }

    fun addSubClass(subClass: Int) {
        subClasses.add(subClass)
    }

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

open class StorageFormat(
    open val magic: Int,
    open val major: Short,
    open val minor: Short,
    open val constantPool: ConstantPool,
    open val classes: List<Class>,
    open val fields: List<Field>,
    open val methods: List<Method>,
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
    override var methods: MutableList<Method>,
) : StorageFormat (
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