package io.github.shakelang.jvmlib.infos.constants

import io.github.shakelang.io.streaming.input.DataInputStream
import io.github.shakelang.io.streaming.input.InputStream
import io.github.shakelang.io.streaming.input.dataStream
import io.github.shakelang.io.streaming.output.DataOutputStream

class ConstantMethodTypeInfo(val di: UShort) : ConstantInfo(), ConstantUser {

    lateinit var descriptor: ConstantUtf8Info
    val descriptorIndex get() = descriptor.index

    override val uses: Array<ConstantInfo> get() = arrayOf(descriptor)

    override val tag: Byte get() = ConstantMethodTypeInfo.tag
    override val tagName: String get() = name
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

        fun contentsFromStream(stream: InputStream)
                = contentsFromStream(stream.dataStream)

        fun fromStream(stream: DataInputStream) =
            if(stream.readByte() != tag)
                throw IllegalArgumentException("Invalid tag for ConstantMethodTypeInfo")
            else contentsFromStream(stream)

        fun fromStream(stream: InputStream) = fromStream(stream.dataStream)

        fun contentsFromBytes(bytes: ByteArray) = contentsFromStream(bytes.dataStream())

        fun fromBytes(bytes: ByteArray) = fromStream(bytes.dataStream())

        const val name = "constant_methodtype_info"
        const val tag = ConstantTags.CONSTANT_METHODTYPE
    }

}