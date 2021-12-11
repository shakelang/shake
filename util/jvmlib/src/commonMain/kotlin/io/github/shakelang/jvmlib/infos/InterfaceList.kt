package io.github.shakelang.jvmlib.infos

import io.github.shakelang.jvmlib.infos.constants.ConstantInfo
import io.github.shakelang.jvmlib.infos.constants.ConstantPool
import io.github.shakelang.jvmlib.infos.constants.ConstantUser
import io.github.shakelang.jvmlib.infos.constants.ConstantUtf8Info
import io.github.shakelang.parseutils.streaming.DataInputStream
import io.github.shakelang.shason.json


class InterfaceList(interfaces: Array<ConstantUtf8Info>) : List<ConstantUtf8Info>, ConstantUser {

    override val uses: Array<ConstantInfo> get() = interfaces.toTypedArray()

    private lateinit var clazz: ClassInfo
    val classInfo : ClassInfo get() = clazz

    val interfaces: List<ConstantUtf8Info> = interfaces.toList()

    override val size: Int
        get() = interfaces.size

    override fun contains(element: ConstantUtf8Info): Boolean = interfaces.contains(element)
    override fun containsAll(elements: Collection<ConstantUtf8Info>): Boolean = interfaces.containsAll(elements)
    override fun get(index: Int): ConstantUtf8Info = interfaces[index]
    override fun indexOf(element: ConstantUtf8Info): Int = interfaces.indexOf(element)
    override fun isEmpty(): Boolean = interfaces.isEmpty()
    override fun iterator(): Iterator<ConstantUtf8Info> = interfaces.iterator()
    override fun lastIndexOf(element: ConstantUtf8Info): Int = interfaces.lastIndexOf(element)
    override fun listIterator(): ListIterator<ConstantUtf8Info> = interfaces.listIterator()
    override fun listIterator(index: Int): ListIterator<ConstantUtf8Info> = interfaces.listIterator(index)
    override fun subList(fromIndex: Int, toIndex: Int): List<ConstantUtf8Info> = interfaces.subList(fromIndex, toIndex)

    fun init(clazz: ClassInfo) {
        this.clazz = clazz
    }

    fun toJson() = interfaces.toList()
    override fun toString() = json.stringify(toJson())

    companion object {
        fun fromStream(pool: ConstantPool, stream: DataInputStream): InterfaceList {
            val count = stream.readUnsignedShort()
            val interfaces = Array(count.toInt()) {
                val pos = stream.readUnsignedShort()
                pool.getUtf8((pos+ 1u).toInt())
            }
            return InterfaceList(interfaces)
        }
    }

}
