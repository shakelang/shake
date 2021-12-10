package io.github.shakelang.jvmlib.infos.constants

import io.github.shakelang.parseutils.streaming.DataInputStream

class ConstantPool(val constants: List<ConstantInfo>) : List<ConstantInfo> {

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

    fun get(index: UShort): ConstantInfo {
        return this[index.toInt()]
    }

    override fun indexOf(element: ConstantInfo): Int {
        return constants.indexOf(element)
    }

    override fun isEmpty(): Boolean {
        return constants.isEmpty()
    }

    override fun iterator(): Iterator<ConstantInfo> {
        return constants.iterator()
    }

    override fun lastIndexOf(element: ConstantInfo): Int {
        return constants.lastIndexOf(element)
    }

    override fun listIterator(): ListIterator<ConstantInfo> {
        return constants.listIterator()
    }

    override fun listIterator(index: Int): ListIterator<ConstantInfo> {
        return constants.listIterator(index)
    }

    override fun subList(fromIndex: Int, toIndex: Int): List<ConstantInfo> {
        return constants.subList(fromIndex, toIndex)
    }

    fun toJson(): List<Map<String, Any>> {
        return this.constants.map { it.toJson() }
    }

    override fun toString(): String {
        return constants.toString()
    }

    fun getUtf8(nameIndex: UShort): ConstantUtf8Info = this.constants[nameIndex.toInt()].toUtf8()
    fun getClass(nameIndex: UShort): ConstantClassInfo = this.constants[nameIndex.toInt()].toClass()
    fun getFieldRef(nameIndex: UShort) = this.constants[nameIndex.toInt()].toFieldRef()
    fun getMethodRef(nameIndex: UShort) = this.constants[nameIndex.toInt()].toMethodRef()
    fun getInterfaceMethodRef(nameIndex: UShort) = this.constants[nameIndex.toInt()].toInterfaceMethodRef()
    fun getString(nameIndex: UShort) = this.constants[nameIndex.toInt()].toString()
    fun getInteger(nameIndex: UShort) = this.constants[nameIndex.toInt()].toInteger()
    fun getFloat(nameIndex: UShort) = this.constants[nameIndex.toInt()].toFloat()
    fun getLong(nameIndex: UShort) = this.constants[nameIndex.toInt()].toLong()
    fun getDouble(nameIndex: UShort) = this.constants[nameIndex.toInt()].toDouble()
    fun getNameAndType(nameIndex: UShort) = this.constants[nameIndex.toInt()].toNameAndType()
    fun getMethodHandle(nameIndex: UShort) = this.constants[nameIndex.toInt()].toMethodHandle()
    fun getMethodType(nameIndex: UShort) = this.constants[nameIndex.toInt()].toMethodType()
    fun getInvokeDynamic(nameIndex: UShort) = this.constants[nameIndex.toInt()].toInvokeDynamic()

    companion object {
        fun fromStream(stream: DataInputStream): ConstantPool {
            val constantPoolCount = stream.readUnsignedShort()
            val constants = Array(constantPoolCount.toInt()) { ConstantInfo.fromStream(stream) }
            return ConstantPool(constants)
        }
    }

}