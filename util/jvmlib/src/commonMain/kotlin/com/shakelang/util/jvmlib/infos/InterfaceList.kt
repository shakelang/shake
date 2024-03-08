package com.shakelang.util.jvmlib.infos

import com.shakelang.util.io.streaming.input.bytes.DataInputStream
import com.shakelang.util.io.streaming.input.bytes.InputStream
import com.shakelang.util.io.streaming.input.bytes.dataStream
import com.shakelang.util.io.streaming.output.bytes.ByteArrayOutputStream
import com.shakelang.util.io.streaming.output.bytes.DataOutputStream
import com.shakelang.util.io.streaming.output.bytes.OutputStream
import com.shakelang.util.jvmlib.infos.constants.ConstantInfo
import com.shakelang.util.jvmlib.infos.constants.ConstantPool
import com.shakelang.util.jvmlib.infos.constants.ConstantUser
import com.shakelang.util.jvmlib.infos.constants.ConstantUtf8Info
import com.shakelang.util.shason.json

class InterfaceList(val interfaces: List<ConstantUtf8Info>) : List<ConstantUtf8Info> by interfaces, ConstantUser {

    override val uses: Array<ConstantInfo> get() = interfaces.toTypedArray()

    private lateinit var clazz: ClassInfo
    val classInfo: ClassInfo get() = clazz

    constructor(interfaces: Array<ConstantUtf8Info>) : this(interfaces.toList())

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

    fun toJson() = interfaces.map { it.toJson() }
    override fun toString() = json.stringify(toJson())

    fun dump(out: DataOutputStream) {
        out.writeUnsignedShort(interfaces.size.toUShort())
        interfaces.forEach {
            out.writeUnsignedShort((it.index - 1u).toUShort())
        }
    }

    fun dump(out: OutputStream) {
        dump(DataOutputStream(out))
    }

    fun toBytes(): ByteArray {
        val stream = ByteArrayOutputStream()
        dump(stream)
        return stream.toByteArray()
    }

    companion object {
        fun fromStream(pool: ConstantPool, stream: DataInputStream): InterfaceList {
            val count = stream.readUnsignedShort()
            val interfaces = Array(count.toInt()) {
                val pos = stream.readUnsignedShort()
                pool.getUtf8((pos + 1u).toInt())
            }
            return InterfaceList(interfaces)
        }

        fun fromStream(pool: ConstantPool, stream: InputStream): InterfaceList {
            return fromStream(pool, stream)
        }

        fun fromBytes(pool: ConstantPool, bytes: ByteArray): InterfaceList {
            return fromStream(pool, bytes.dataStream())
        }
    }
}
