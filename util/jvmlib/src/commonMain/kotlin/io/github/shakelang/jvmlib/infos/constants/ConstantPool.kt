package io.github.shakelang.jvmlib.infos.constants

import io.github.shakelang.jvmlib.infos.ClassInfo
import io.github.shakelang.parseutils.streaming.DataInputStream

class ConstantPool(val constants: MutableList<ConstantInfo>) : MutableList<ConstantInfo>, ConstantUser {

    constructor() : this(mutableListOf())
    constructor(constants: List<ConstantInfo>) : this(constants.toMutableList())

    init {
        constants.forEach { it.init(this) }
    }

    private lateinit var clazz: ClassInfo
    val classInfo : ClassInfo get() = clazz
    override val uses : Array<ConstantInfo> = users.map { it.uses.toList() }.flatten().toTypedArray()
    override val users: Array<ConstantUser> get() = constants.filter { it is ConstantUser }.map { it as ConstantUser }.toTypedArray()

    constructor(constants: Array<ConstantInfo>) : this(constants.toList())

    override val size: Int
        get() = constants.size

    override fun contains(element: ConstantInfo): Boolean {
        return constants.contains(element)
    }

    override fun containsAll(elements: Collection<ConstantInfo>): Boolean {
        return constants.containsAll(elements)
    }

    override fun get(index: Int): ConstantInfo {
        return constants[index-1]
    }

    operator fun get(index: UShort): ConstantInfo {
        return this[index.toInt()]
    }

    override fun indexOf(element: ConstantInfo): Int {
        return constants.indexOf(element)
    }

    override fun isEmpty(): Boolean {
        return constants.isEmpty()
    }

    override fun iterator(): MutableIterator<ConstantInfo> {
        return constants.iterator()
    }

    override fun lastIndexOf(element: ConstantInfo): Int {
        return constants.lastIndexOf(element)
    }

    override fun listIterator(): MutableListIterator<ConstantInfo> {
        return constants.listIterator()
    }

    override fun listIterator(index: Int): MutableListIterator<ConstantInfo> {
        return constants.listIterator(index)
    }

    override fun subList(fromIndex: Int, toIndex: Int): MutableList<ConstantInfo> {
        return constants.subList(fromIndex, toIndex)
    }

    fun toJson(): List<Map<String, Any>> {
        return this.constants.map { it.toJson() }
    }

    fun init(clazz: ClassInfo) {
        this.clazz = clazz
    }

    override fun toString(): String {
        return constants.toString()
    }

    fun getUtf8(nameIndex: Int): ConstantUtf8Info = this[nameIndex].toUtf8()
    fun getClass(nameIndex: Int): ConstantClassInfo = this[nameIndex].toClass()
    fun getFieldRef(nameIndex: Int) = this[nameIndex].toFieldRef()
    fun getMethodRef(nameIndex: Int) = this[nameIndex].toMethodRef()
    fun getInterfaceMethodRef(nameIndex: Int) = this[nameIndex].toInterfaceMethodRef()
    fun getString(nameIndex: Int) = this[nameIndex].toStringRef()
    fun getInteger(nameIndex: Int) = this[nameIndex].toInteger()
    fun getFloat(nameIndex: Int) = this[nameIndex].toFloat()
    fun getLong(nameIndex: Int) = this[nameIndex].toLong()
    fun getDouble(nameIndex: Int) = this[nameIndex].toDouble()
    fun getNameAndType(nameIndex: Int) = this[nameIndex].toNameAndType()
    fun getMethodHandle(nameIndex: Int) = this[nameIndex].toMethodHandle()
    fun getMethodType(nameIndex: Int) = this[nameIndex].toMethodType()
    fun getInvokeDynamic(nameIndex: Int) = this[nameIndex].toInvokeDynamic()

    fun getUtf8(nameIndex: UShort): ConstantUtf8Info = this.getUtf8(nameIndex.toInt())
    fun getClass(nameIndex: UShort): ConstantClassInfo = this.getClass(nameIndex.toInt())
    fun getFieldRef(nameIndex: UShort) = this.getFieldRef(nameIndex.toInt())
    fun getMethodRef(nameIndex: UShort) = this.getMethodRef(nameIndex.toInt())
    fun getInterfaceMethodRef(nameIndex: UShort) = this.getInterfaceMethodRef(nameIndex.toInt())
    fun getString(nameIndex: UShort) = this.getString(nameIndex.toInt())
    fun getInteger(nameIndex: UShort) = this.getInteger(nameIndex.toInt())
    fun getFloat(nameIndex: UShort) = this.getFloat(nameIndex.toInt())
    fun getLong(nameIndex: UShort) = this.getLong(nameIndex.toInt())
    fun getDouble(nameIndex: UShort) = this.getDouble(nameIndex.toInt())
    fun getNameAndType(nameIndex: UShort) = this.getNameAndType(nameIndex.toInt())
    fun getMethodHandle(nameIndex: UShort) = this.getMethodHandle(nameIndex.toInt())
    fun getMethodType(nameIndex: UShort) = this.getMethodType(nameIndex.toInt())
    fun getInvokeDynamic(nameIndex: UShort) = this.getInvokeDynamic(nameIndex.toInt())

    override fun add(element: ConstantInfo): Boolean {
        return constants.add(element)
    }

    override fun add(index: Int, element: ConstantInfo) {
        constants.add(index, element)
    }

    override fun addAll(index: Int, elements: Collection<ConstantInfo>): Boolean {
        return constants.addAll(index, elements)
    }

    override fun addAll(elements: Collection<ConstantInfo>): Boolean {
        return constants.addAll(elements)
    }

    override fun clear() {
        constants.clear()
    }

    override fun remove(element: ConstantInfo): Boolean {
        return constants.remove(element)
    }

    override fun removeAll(elements: Collection<ConstantInfo>): Boolean {
        return constants.removeAll(elements)
    }

    override fun removeAt(index: Int): ConstantInfo {
        return constants.removeAt(index)
    }

    override fun retainAll(elements: Collection<ConstantInfo>): Boolean {
        return constants.retainAll(elements)
    }

    override fun set(index: Int, element: ConstantInfo): ConstantInfo {
        return constants.set(index, element)
    }

    fun clean() {
        lateinit var unused: List<ConstantInfo>
        do {
            val used = classInfo.uses
            unused = filter { !used.contains(it) }
            removeAll(unused)
        } while (unused.isNotEmpty())
    }

    companion object {
        fun fromStream(stream: DataInputStream): ConstantPool {
            val constantPoolCount = stream.readUnsignedShort()
            val constants = Array(constantPoolCount.toInt()-1) { ConstantInfo.fromStream(stream) }
            return ConstantPool(constants)
        }
    }

}