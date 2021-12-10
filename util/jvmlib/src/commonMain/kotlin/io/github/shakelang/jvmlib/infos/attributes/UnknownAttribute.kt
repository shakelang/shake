package io.github.shakelang.jvmlib.infos.attributes

import io.github.shakelang.jvmlib.infos.constants.ConstantPool
import io.github.shakelang.parseutils.bytes.getBytes
import io.github.shakelang.parseutils.bytes.getInt
import io.github.shakelang.parseutils.bytes.getUnsignedShort
import io.github.shakelang.parseutils.bytes.toHexString
import io.github.shakelang.parseutils.streaming.DataInputStream

class UnknownAttribute(nameIndex: UShort, name: String, override val bytes: ByteArray) : AttributeInfo(nameIndex, name) {

    override fun toJson(): Map<String, Any> = mapOf(
        "name" to name,
        "bytes" to bytes.toHexString()
    )

    companion object {
        fun fromBytes(pool: ConstantPool, bytes: ByteArray): UnknownAttribute {
            val nameIndex = bytes.getUnsignedShort(0)
            val length = bytes.getInt(2)
            return contentsFromBytes(pool, bytes, nameIndex, length)
        }

        fun contentsFromBytes(pool: ConstantPool, bytes: ByteArray, nameIndex: UShort, length: Int): UnknownAttribute {
            val name = pool.getUtf8(nameIndex).value
            return UnknownAttribute(nameIndex, name, bytes.getBytes(6, length))
        }

        fun fromStream(pool: ConstantPool, stream: DataInputStream): UnknownAttribute {
            val nameIndex = stream.readUnsignedShort()
            val length = stream.readInt()
            return contentsFromStream(pool, stream, nameIndex, length)
        }

        fun contentsFromStream(pool: ConstantPool, stream: DataInputStream, nameIndex: UShort, length: Int): UnknownAttribute {
            val name = pool.getUtf8(nameIndex).value
            return UnknownAttribute(nameIndex, name, stream.readNBytes(length))
        }
    }
}