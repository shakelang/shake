package io.github.shakelang.jvmlib.infos.attributes

import io.github.shakelang.jvmlib.infos.ClassInfo
import io.github.shakelang.jvmlib.infos.constants.ConstantPool
import io.github.shakelang.jvmlib.infos.constants.ConstantUser
import io.github.shakelang.jvmlib.infos.constants.ConstantUtf8Info
import io.github.shakelang.parseutils.bytes.*
import io.github.shakelang.parseutils.streaming.ByteArrayInputStream
import io.github.shakelang.parseutils.streaming.DataInputStream

abstract class AttributeInfo (val name: ConstantUtf8Info) : ConstantUser {

    val nameIndex get() = name.index

    private lateinit var clazz: ClassInfo

    abstract val bytes: ByteArray
    fun toBytes(): ByteArray {
        val bb = this.bytes
        val bytes = ByteArray(6 + bb.size)
        bytes.setUnsignedShort(0, nameIndex)
        bytes.setInt(2, bb.size)
        bytes.setBytes(6, bb)
        return bytes
    }

    abstract fun toJson(): Map<String, Any>
    fun init(clazz: ClassInfo) {
        this.clazz = clazz
    }

    companion object {
        fun fromBytes(pool: ConstantPool, bytes: ByteArray): AttributeInfo
            = fromStream(pool, DataInputStream(ByteArrayInputStream(bytes)))

        fun fromStream(pool: ConstantPool, stream: DataInputStream): AttributeInfo {
            val nameIndex = stream.readUnsignedShort()
            val name = pool.getUtf8(nameIndex)
            val length = stream.readInt()
            return when (name.value) {
                "ConstantValue" -> AttributeConstantValue.contentsFromStream(pool, stream, name)
                "Code" -> AttributeCode.contentsFromStream(pool, stream, name)
                else -> UnknownAttribute.contentsFromStream(pool, stream, name, length)
            }
        }
    }
}