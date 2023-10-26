package io.github.shakelang.jvmlib.infos.constants

import io.github.shakelang.io.streaming.input.DataInputStream
import io.github.shakelang.io.streaming.input.InputStream
import io.github.shakelang.io.streaming.input.dataStream
import io.github.shakelang.io.streaming.output.DataOutputStream

class ConstantNameAndTypeInfo(private val ti: UShort, private val ni: UShort) : ConstantInfo(), ConstantUser {

    lateinit var type: ConstantUtf8Info
    lateinit var name: ConstantUtf8Info

    val typeIndex: UShort get() = type.index
    val nameIndex: UShort get() = name.index


    override val uses: Array<ConstantInfo> get() = arrayOf(type, name)

    override val tag: Byte get() = ConstantNameAndTypeInfo.tag
    override val tagName: String get() = ConstantNameAndTypeInfo.name

    override fun toJson() = super.toJson()
        .with("name", typeIndex.toInt())
        .with("type", nameIndex.toInt())

    override fun init(pool: ConstantPool) {
        super.init(pool)
        type = pool[ti] as ConstantUtf8Info
        name = pool[ni] as ConstantUtf8Info
    }

    override fun dump(out: DataOutputStream) {
        out.writeByte(tag)
        out.writeUnsignedShort(typeIndex)
        out.writeUnsignedShort(nameIndex)
    }

    companion object {
        fun contentsFromStream(stream: DataInputStream): ConstantNameAndTypeInfo {
            val value = stream.readUnsignedShort()
            val name = stream.readUnsignedShort()
            return ConstantNameAndTypeInfo(value, name)
        }

        fun contentsFromStream(stream: InputStream)
                = contentsFromStream(stream.dataStream)

        fun fromStream(stream: DataInputStream) =
            if(stream.readByte() != tag)
                throw IllegalArgumentException("Invalid tag for ConstantNameAndTypeInfo")
            else contentsFromStream(stream)

        fun fromStream(stream: InputStream) = fromStream(stream.dataStream)

        fun contentsFromBytes(bytes: ByteArray) = contentsFromStream(bytes.dataStream())

        fun fromBytes(bytes: ByteArray) = fromStream(bytes.dataStream())

        const val name = "constant_name_and_type_info"
        const val tag = ConstantTags.CONSTANT_NAME_AND_TYPE
    }

}