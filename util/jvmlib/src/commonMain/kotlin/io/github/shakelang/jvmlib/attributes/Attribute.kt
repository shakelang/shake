package io.github.shakelang.jvmlib.attributes

import io.github.shakelang.jvmlib.infos.constants.ConstantPool
import io.github.shakelang.parseutils.bytes.*
import io.github.shakelang.parseutils.streaming.ByteArrayInputStream
import io.github.shakelang.parseutils.streaming.DataInputStream

abstract class Attribute (val nameIndex: UShort, val name: String) {

    abstract val bytes: ByteArray
    fun toBytes(): ByteArray {
        val bb = this.bytes
        val bytes = ByteArray(6 + bb.size)
        bytes.setUnsignedShort(0, nameIndex)
        bytes.setInt(2, bb.size)
        bytes.setBytes(6, bb)
        return bytes
    }

    companion object {
        fun fromBytes(pool: ConstantPool, bytes: ByteArray): Attribute
            = fromStream(pool, DataInputStream(ByteArrayInputStream(bytes)))

        fun fromStream(pool: ConstantPool, stream: DataInputStream): Attribute {
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