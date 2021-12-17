package io.github.shakelang.jvmlib.infos.constants

import io.github.shakelang.parseutils.bytes.dataStream
import io.github.shakelang.parseutils.streaming.input.DataInputStream
import io.github.shakelang.parseutils.streaming.input.InputStream
import io.github.shakelang.parseutils.streaming.input.dataStream
import io.github.shakelang.parseutils.streaming.output.DataOutputStream

class ConstantInvokeDynamicInfo(
    val bootstrapMethodAttributeIndex: UShort,
    val nati: UShort
) : ConstantInfo() {

    lateinit var nameAndType: ConstantNameAndTypeInfo
    val nameAndTypeIndex: UShort get() = nameAndType.index

    override val tag: Byte get() = ConstantInvokeDynamicInfo.tag
    override val tagName: String get() = name
    override fun toJson() = super.toJson()
        .with("bootstrapMethodAttributeIndex", bootstrapMethodAttributeIndex)
        .with("nameAndTypeIndex", nameAndTypeIndex)

    override fun dump(out: DataOutputStream) {
        out.writeByte(tag)
        out.writeUnsignedShort(bootstrapMethodAttributeIndex)
        out.writeUnsignedShort(nameAndTypeIndex)
    }

    override fun init(pool: ConstantPool) {
        super.init(pool)
        nameAndType = pool[nati] as ConstantNameAndTypeInfo
    }

    companion object {
        fun contentsFromStream(stream: DataInputStream): ConstantInvokeDynamicInfo {
            return ConstantInvokeDynamicInfo(
                stream.readUnsignedShort(),
                stream.readUnsignedShort(),
            )
        }

        fun contentsFromStream(stream: InputStream)
                = contentsFromStream(stream.dataStream)

        fun fromStream(stream: DataInputStream) =
            if(stream.readByte() != tag)
                throw IllegalArgumentException("Invalid tag for ConstantInvokeDynamicInfo")
            else contentsFromStream(stream)

        fun fromStream(stream: InputStream) = fromStream(stream.dataStream)

        fun contentsFromBytes(bytes: ByteArray) = contentsFromStream(bytes.dataStream())

        fun fromBytes(bytes: ByteArray) = fromStream(bytes.dataStream())

        const val name = "constant_invoke_dynamic"
        const val tag = ConstantTags.CONSTANT_INVOKE_DYNAMIC
    }

}