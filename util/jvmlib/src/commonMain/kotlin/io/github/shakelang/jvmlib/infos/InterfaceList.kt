package io.github.shakelang.jvmlib.infos

import io.github.shakelang.jvmlib.infos.constants.ConstantPool
import io.github.shakelang.jvmlib.infos.constants.ConstantUser
import io.github.shakelang.parseutils.streaming.DataInputStream
import io.github.shakelang.shason.json


class InterfaceList(interfaces: Array<UShort>) : List<UShort>, ConstantUser {

    override val uses get() = interfaces.toTypedArray()
    val users = interfaces.map { this }.flatten().toTypedArray()

    private lateinit var clazz: ClassInfo

    val interfaces: List<UShort> = interfaces.toList()

    override val size: Int
        get() = interfaces.size

    override fun contains(element: UShort): Boolean = interfaces.contains(element)
    override fun containsAll(elements: Collection<UShort>): Boolean = interfaces.containsAll(elements)
    override fun get(index: Int): UShort = interfaces[index]
    override fun indexOf(element: UShort): Int = interfaces.indexOf(element)
    override fun isEmpty(): Boolean = interfaces.isEmpty()
    override fun iterator(): Iterator<UShort> = interfaces.iterator()
    override fun lastIndexOf(element: UShort): Int = interfaces.lastIndexOf(element)
    override fun listIterator(): ListIterator<UShort> = interfaces.listIterator()
    override fun listIterator(index: Int): ListIterator<UShort> = interfaces.listIterator(index)
    override fun subList(fromIndex: Int, toIndex: Int): List<UShort> = interfaces.subList(fromIndex, toIndex)

    fun init(clazz: ClassInfo) {
        this.clazz = clazz
    }

    fun toJson() = interfaces.toList()
    override fun toString() = json.stringify(toJson())

    companion object {
        fun fromStream(stream: DataInputStream): InterfaceList {
            val count = stream.readUnsignedShort()
            val interfaces = Array(count.toInt()) { stream.readUnsignedShort() }
            return InterfaceList(interfaces)
        }
    }

}
