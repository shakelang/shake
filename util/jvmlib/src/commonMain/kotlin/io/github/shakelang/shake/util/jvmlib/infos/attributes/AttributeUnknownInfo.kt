package io.github.shakelang.shake.util.jvmlib.infos.attributes

import io.github.shakelang.shake.util.io.streaming.input.DataInputStream
import io.github.shakelang.shake.util.jvmlib.infos.constants.ConstantInfo
import io.github.shakelang.shake.util.jvmlib.infos.constants.ConstantPool
import io.github.shakelang.shake.util.jvmlib.infos.constants.ConstantUtf8Info
import io.github.shakelang.shake.util.primitives.bytes.getBytes
import io.github.shakelang.shake.util.primitives.bytes.getInt
import io.github.shakelang.shake.util.primitives.bytes.getUnsignedShort
import io.github.shakelang.shake.util.primitives.bytes.toHexString

class AttributeUnknownInfo(name: ConstantUtf8Info, override val bytes: ByteArray) : AttributeInfo(name) {

    override val uses: Array<ConstantInfo> get() = arrayOf(name)


    override fun toJson(): Map<String, Any> = mapOf(
        "name" to name,
        "bytes" to bytes.toHexString()
    )

    companion object {
        fun fromBytes(pool: ConstantPool, bytes: ByteArray): AttributeUnknownInfo {
            val nameIndex = bytes.getUnsignedShort(0)
            val name = pool.getUtf8(nameIndex)
            val length = bytes.getInt(2)
            return contentsFromBytes(pool, bytes, name, length)
        }

        fun contentsFromBytes(
            pool: ConstantPool,
            bytes: ByteArray,
            name: ConstantUtf8Info,
            length: Int
        ): AttributeUnknownInfo {
            return AttributeUnknownInfo(name, bytes.getBytes(0, length))
        }

        fun fromStream(pool: ConstantPool, stream: DataInputStream): AttributeUnknownInfo {
            val nameIndex = stream.readUnsignedShort()
            val name = pool.getUtf8(nameIndex)
            val length = stream.readInt()
            return contentsFromStream(pool, stream, name, length)
        }

        fun contentsFromStream(
            pool: ConstantPool,
            stream: DataInputStream,
            name: ConstantUtf8Info,
            length: Int
        ): AttributeUnknownInfo {
            return AttributeUnknownInfo(name, stream.readNBytes(length))
        }
    }
}