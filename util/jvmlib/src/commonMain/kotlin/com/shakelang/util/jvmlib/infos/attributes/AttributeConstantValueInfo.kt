package com.shakelang.util.jvmlib.infos.attributes

import com.shakelang.util.io.streaming.input.bytes.DataInputStream
import com.shakelang.util.io.streaming.input.bytes.InputStream
import com.shakelang.util.io.streaming.input.bytes.dataStream
import com.shakelang.util.jvmlib.infos.constants.ConstantInfo
import com.shakelang.util.jvmlib.infos.constants.ConstantPool
import com.shakelang.util.jvmlib.infos.constants.ConstantUtf8Info
import com.shakelang.util.primitives.bytes.getUnsignedShort
import com.shakelang.util.primitives.bytes.toBytes
import com.shakelang.util.primitives.bytes.toUnsignedShort

class AttributeConstantValueInfo(

    attributeName: ConstantUtf8Info,
    val value: ConstantInfo,

) : AttributeInfo(attributeName) {

    override val uses: Array<ConstantInfo> get() = arrayOf(name, value)
    val valueIndex: UShort get() = value.index

    override val bytes: ByteArray
        get() = valueIndex.toBytes()

    override fun toJson() = mapOf(
        "tag" to TAG,
        "attribute_name_index" to nameIndex,
        "constant_value_index" to valueIndex,
    )

    companion object {
        const val TAG = "ConstantValue"
        fun contentsFromBytes(
            pool: ConstantPool,
            bytes: ByteArray,
            name: ConstantUtf8Info,
        ): AttributeConstantValueInfo {
            val valueIndex = bytes.toUnsignedShort()
            val value = pool[valueIndex]
            return AttributeConstantValueInfo(name, value)
        }

        fun contentsFromBytes(
            pool: ConstantPool,
            bytes: ByteArray,
            name: ConstantUtf8Info,
            offset: Int,
        ): AttributeConstantValueInfo {
            val valueIndex = bytes.getUnsignedShort(offset)
            val value = pool[valueIndex]
            return AttributeConstantValueInfo(name, value)
        }

        fun fromBytes(pool: ConstantPool, bytes: ByteArray): AttributeConstantValueInfo {
            val name = pool.getUtf8(bytes.getUnsignedShort(0))
            return contentsFromBytes(pool, bytes, name, 2)
        }

        fun fromBytes(pool: ConstantPool, bytes: ByteArray, offset: Int): AttributeConstantValueInfo {
            val name = pool.getUtf8(bytes.getUnsignedShort(offset))
            return contentsFromBytes(pool, bytes, name, offset + 2)
        }

        fun contentsFromStream(
            pool: ConstantPool,
            stream: DataInputStream,
            name: ConstantUtf8Info,
        ): AttributeConstantValueInfo {
            val constantValueIndex = stream.readUnsignedShort()
            val constantValue = pool[constantValueIndex]
            return AttributeConstantValueInfo(name, constantValue)
        }

        fun contentsFromStream(
            pool: ConstantPool,
            stream: InputStream,
            name: ConstantUtf8Info,
        ): AttributeConstantValueInfo {
            return contentsFromStream(pool, stream.dataStream, name)
        }

        fun fromStream(pool: ConstantPool, stream: DataInputStream): AttributeConstantValueInfo {
            val name = pool.getUtf8(stream.readUnsignedShort())
            return contentsFromStream(pool, stream, name)
        }

        fun fromStream(pool: ConstantPool, stream: InputStream): AttributeConstantValueInfo {
            return fromStream(pool, stream.dataStream)
        }
    }
}
