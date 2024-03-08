package com.shakelang.util.jvmlib.infos.constants

import com.shakelang.util.io.streaming.input.bytes.DataInputStream
import com.shakelang.util.io.streaming.input.bytes.InputStream
import com.shakelang.util.io.streaming.input.bytes.dataStream
import com.shakelang.util.io.streaming.output.bytes.DataOutputStream

class ConstantMethodHandleInfo(val referenceKind: Byte, private val ri: UShort) : ConstantInfo(), ConstantUser {

    lateinit var reference: ConstantInfo
    val referenceIndex: UShort get() = reference.index

    override val uses get() = arrayOf(reference)

    override val tag: Byte get() = ConstantMethodHandleInfo.TAG
    override val tagName: String get() = NAME
    override fun toJson() = super.toJson()
        .with("type", referenceKind.toInt())
        .with("index", referenceIndex.toInt())

    override fun init(pool: ConstantPool) {
        super.init(pool)
        reference = pool[ri]
    }

    override fun dump(out: DataOutputStream) {
        out.writeByte(tag)
        out.writeByte(referenceKind)
        out.writeUnsignedShort(referenceIndex)
    }

    companion object {
        fun contentsFromStream(stream: DataInputStream): ConstantMethodHandleInfo {
            val name = stream.readByte()
            val index = stream.readUnsignedShort()
            return ConstantMethodHandleInfo(name, index)
        }

        fun contentsFromStream(stream: InputStream) = contentsFromStream(stream.dataStream)

        fun fromStream(stream: DataInputStream) =
            if (stream.readByte() != TAG) {
                throw IllegalArgumentException("Invalid tag for ConstantMethodHandleInfo")
            } else {
                contentsFromStream(stream)
            }

        fun fromStream(stream: InputStream) = fromStream(stream.dataStream)

        fun contentsFromBytes(bytes: ByteArray) = contentsFromStream(bytes.dataStream())

        fun fromBytes(bytes: ByteArray) = fromStream(bytes.dataStream())

        const val NAME = "constant_method_handle_info"
        const val TAG = ConstantTags.CONSTANT_METHOD_HANDLE
    }
}
