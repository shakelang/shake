package io.github.shakelang.jvmlib.infos.attributes

import io.github.shakelang.jvmlib.infos.ClassInfo
import io.github.shakelang.jvmlib.infos.constants.ConstantPool
import io.github.shakelang.jvmlib.infos.constants.ConstantUser
import io.github.shakelang.parseutils.bytes.*
import io.github.shakelang.parseutils.streaming.ByteArrayInputStream
import io.github.shakelang.parseutils.streaming.DataInputStream

abstract class AttributeInfo (val nameIndex: UShort, val name: String) : ConstantUser {

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
            val name = pool.getUtf8(nameIndex).value
            val length = stream.readInt()
            return when (name) {
                "ConstantValue" -> AttributeConstantValue.contentsFromStream(pool, stream, nameIndex)
                "Code" -> AttributeCode.contentsFromStream(pool, stream, nameIndex)
                else -> UnknownAttribute.contentsFromStream(pool, stream, nameIndex, length)
            }
        }
    }
}