package io.github.shakelang.jvmlib.infos.constants

import io.github.shakelang.io.streaming.input.DataInputStream
import io.github.shakelang.io.streaming.input.InputStream
import io.github.shakelang.io.streaming.input.dataStream
import io.github.shakelang.io.streaming.output.DataOutputStream

class ConstantMethodHandleInfo(val referenceKind: Byte, private val ri: UShort) : ConstantInfo(), ConstantUser {

    lateinit var reference: ConstantInfo
    val referenceIndex: UShort get() = reference.index

    override val uses get() = arrayOf(reference)

    override val tag: Byte get() = ConstantMethodHandleInfo.tag
    override val tagName: String get() = name
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

        fun contentsFromStream(stream: InputStream)
                = contentsFromStream(stream.dataStream)

        fun fromStream(stream: DataInputStream) =
            if(stream.readByte() != tag)
                throw IllegalArgumentException("Invalid tag for ConstantMethodHandleInfo")
            else contentsFromStream(stream)

        fun fromStream(stream: InputStream) = fromStream(stream.dataStream)

        fun contentsFromBytes(bytes: ByteArray) = contentsFromStream(bytes.dataStream())

        fun fromBytes(bytes: ByteArray) = fromStream(bytes.dataStream())

        const val name = "constant_method_handle_info"
        const val tag = ConstantTags.CONSTANT_METHOD_HANDLE
    }

}