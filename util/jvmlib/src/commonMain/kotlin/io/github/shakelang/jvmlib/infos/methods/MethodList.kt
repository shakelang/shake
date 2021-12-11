package io.github.shakelang.jvmlib.infos.methods

import io.github.shakelang.jvmlib.infos.ClassInfo
import io.github.shakelang.jvmlib.infos.constants.ConstantInfo
import io.github.shakelang.jvmlib.infos.constants.ConstantPool
import io.github.shakelang.jvmlib.infos.constants.ConstantUser
import io.github.shakelang.parseutils.streaming.DataInputStream
import io.github.shakelang.shason.json

class MethodList(methods: Array<MethodInfo>) : List<MethodInfo>, ConstantUser {

    override val uses : Array<ConstantInfo> get() = methods.map { it.uses.toList() }.flatten().toTypedArray()
    override val users = methods.map { it.users.toList() }.flatten().toTypedArray()

    private lateinit var clazz: ClassInfo

    val methods: List<MethodInfo> = methods.toList()

    override val size: Int
        get() = methods.size

    override fun contains(element: MethodInfo): Boolean = methods.contains(element)
    override fun containsAll(elements: Collection<MethodInfo>): Boolean = methods.containsAll(elements)
    override fun get(index: Int): MethodInfo = methods[index]
    override fun indexOf(element: MethodInfo): Int = methods.indexOf(element)
    override fun isEmpty(): Boolean = methods.isEmpty()
    override fun iterator(): Iterator<MethodInfo> = methods.iterator()
    override fun lastIndexOf(element: MethodInfo): Int = methods.lastIndexOf(element)
    override fun listIterator(): ListIterator<MethodInfo> = methods.listIterator()
    override fun listIterator(index: Int): ListIterator<MethodInfo> = methods.listIterator(index)
    override fun subList(fromIndex: Int, toIndex: Int): List<MethodInfo> = methods.subList(fromIndex, toIndex)

    fun init(clazz: ClassInfo) {
        this.clazz = clazz
        this.methods.forEach { it.init(clazz) }
    }

    fun toJson() = methods.map { it.toJson() }
    override fun toString() = json.stringify(toJson())

    companion object {
        fun fromStream(pool: ConstantPool, stream: DataInputStream): MethodList {
            val count = stream.readUnsignedShort()
            val fields = Array(count.toInt()) { MethodInfo.fromStream(pool, stream) }
            return MethodList(fields)
        }
    }

}
