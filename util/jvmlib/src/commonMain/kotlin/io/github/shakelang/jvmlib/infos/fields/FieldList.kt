package io.github.shakelang.jvmlib.infos.fields

import io.github.shakelang.jvmlib.infos.ClassInfo
import io.github.shakelang.jvmlib.infos.constants.ConstantInfo
import io.github.shakelang.jvmlib.infos.constants.ConstantPool
import io.github.shakelang.jvmlib.infos.constants.ConstantUser
import io.github.shakelang.parseutils.streaming.input.DataInputStream
import io.github.shakelang.shason.json

class FieldList(fields: Array<FieldInfo>) : List<FieldInfo>, ConstantUser {

    override val uses : Array<ConstantInfo> get() = fields.map { it.uses.toList() }.flatten().toTypedArray()
    override val users = fields.map { it.users.toList() }.flatten().toTypedArray()

    private lateinit var clazz: ClassInfo

    val fields: List<FieldInfo> = fields.toList()

    override val size: Int
        get() = fields.size

    override fun contains(element: FieldInfo): Boolean = fields.contains(element)
    override fun containsAll(elements: Collection<FieldInfo>): Boolean = fields.containsAll(elements)
    override fun get(index: Int): FieldInfo = fields[index]
    override fun indexOf(element: FieldInfo): Int = fields.indexOf(element)
    override fun isEmpty(): Boolean = fields.isEmpty()
    override fun iterator(): Iterator<FieldInfo> = fields.iterator()
    override fun lastIndexOf(element: FieldInfo): Int = fields.lastIndexOf(element)
    override fun listIterator(): ListIterator<FieldInfo> = fields.listIterator()
    override fun listIterator(index: Int): ListIterator<FieldInfo> = fields.listIterator(index)
    override fun subList(fromIndex: Int, toIndex: Int): List<FieldInfo> = fields.subList(fromIndex, toIndex)
    
    fun init(clazz: ClassInfo) {
        this.clazz = clazz
        this.fields.forEach { it.init(clazz) }
    }

    fun toJson() = fields.map { it.toJson() }
    override fun toString() = json.stringify(toJson())

    companion object {
        fun fromStream(pool: ConstantPool, stream: DataInputStream): FieldList {
            val count = stream.readUnsignedShort()
            val fields = Array(count.toInt()) { FieldInfo.fromStream(pool, stream) }
            return FieldList(fields)
        }
    }

}
