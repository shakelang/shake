package com.shakelang.util.jvmlib.infos.constants

import com.shakelang.util.io.streaming.input.bytes.DataInputStream
import com.shakelang.util.io.streaming.input.bytes.InputStream
import com.shakelang.util.io.streaming.input.bytes.dataStream
import com.shakelang.util.io.streaming.output.bytes.DataOutputStream

class ConstantMethodTypeInfo(val di: UShort) : ConstantInfo(), ConstantUser {

    lateinit var descriptor: ConstantUtf8Info
    val descriptorIndex get() = descriptor.index

    override val uses: Array<ConstantInfo> get() = arrayOf(descriptor)

    override val tag: Byte get() = ConstantMethodTypeInfo.TAG
    override val tagName: String get() = NAME
    override fun toJson() = super.toJson()
        .with("index", descriptorIndex.toInt())

    override fun init(pool: ConstantPool) {
        super.init(pool)
        descriptor = pool.getUtf8(di.toInt())
    }

    override fun dump(out: DataOutputStream) {
        out.writeByte(tag)
        out.writeUnsignedShort(descriptorIndex)
    }

    companion object {
        fun contentsFromStream(stream: DataInputStream): ConstantMethodTypeInfo {
            val index = stream.readUnsignedShort()
            return ConstantMethodTypeInfo(index)
        }

        fun contentsFromStream(stream: InputStream) = contentsFromStream(stream.dataStream)

        fun fromStream(stream: DataInputStream) =
            if (stream.readByte() != TAG) {
                throw IllegalArgumentException("Invalid tag for ConstantMethodTypeInfo")
            } else {
                contentsFromStream(stream)
            }

        fun fromStream(stream: InputStream) = fromStream(stream.dataStream)

        fun contentsFromBytes(bytes: ByteArray) = contentsFromStream(bytes.dataStream())

        fun fromBytes(bytes: ByteArray) = fromStream(bytes.dataStream())

        const val NAME = "constant_methodtype_info"
        const val TAG = ConstantTags.CONSTANT_METHODTYPE
    }
}
