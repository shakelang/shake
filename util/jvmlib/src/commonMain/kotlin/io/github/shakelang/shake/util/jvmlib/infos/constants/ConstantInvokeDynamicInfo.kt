package io.github.shakelang.shake.util.jvmlib.infos.constants

import io.github.shakelang.shake.util.io.streaming.input.DataInputStream
import io.github.shakelang.shake.util.io.streaming.input.InputStream
import io.github.shakelang.shake.util.io.streaming.input.dataStream
import io.github.shakelang.shake.util.io.streaming.output.DataOutputStream

class ConstantInvokeDynamicInfo(
    val bootstrapMethodAttributeIndex: UShort,
    val nati: UShort
) : ConstantInfo() {

    lateinit var nameAndType: ConstantNameAndTypeInfo
    val nameAndTypeIndex: UShort get() = nameAndType.index

    override val tag: Byte get() = ConstantInvokeDynamicInfo.tag
    override val tagName: String get() = name
    override fun toJson() = super.toJson()
        .with("bootstrap_method_attribute", bootstrapMethodAttributeIndex.toInt())
        .with("name_type_ref", nameAndTypeIndex.toInt())

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

        fun contentsFromStream(stream: InputStream) = contentsFromStream(stream.dataStream)

        fun fromStream(stream: DataInputStream) =
            if (stream.readByte() != tag)
                throw IllegalArgumentException("Invalid tag for ConstantInvokeDynamicInfo")
            else contentsFromStream(stream)

        fun fromStream(stream: InputStream) = fromStream(stream.dataStream)

        fun contentsFromBytes(bytes: ByteArray) = contentsFromStream(bytes.dataStream())

        fun fromBytes(bytes: ByteArray) = fromStream(bytes.dataStream())

        const val name = "constant_invoke_dynamic"
        const val tag = ConstantTags.CONSTANT_INVOKE_DYNAMIC
    }

}