package io.github.shakelang.jvmlib.infos.constants

import io.github.shakelang.parseutils.streaming.input.DataInputStream
import io.github.shakelang.parseutils.streaming.output.DataOutputStream

class ConstantInvokeDynamicInfo(
    val bootstrapMethodAttributeIndex: UShort,
    val nati: UShort
) : ConstantInfo() {

    lateinit var nameAndType: ConstantNameAndTypeInfo
    val nameAndTypeIndex: UShort get() = constantPool.indexOf(nameAndType).toUShort()

    override val tag: Byte get() = ConstantInvokeDynamicInfo.tag
    override val tagName: String get() = name
    override fun toJson() = super.toJson()
        .with("bootstrapMethodAttributeIndex", bootstrapMethodAttributeIndex)
        .with("nameAndTypeIndex", nameAndTypeIndex)

    override fun dumpTo(out: DataOutputStream) {
        out.writeByte(tag)
        out.writeUnsignedShort(bootstrapMethodAttributeIndex)
        out.writeUnsignedShort(nameAndTypeIndex)
    }

    companion object {
        fun contentsFromStream(stream: DataInputStream): ConstantInvokeDynamicInfo {
            return ConstantInvokeDynamicInfo(
                stream.readUnsignedShort(),
                stream.readUnsignedShort(),
            )
        }

        fun fromStream(stream: DataInputStream): ConstantInvokeDynamicInfo {
            if(stream.readByte() != ConstantClassInfo.tag)
                throw Error("Expecting to get a ConstantClassInfo!")
            return contentsFromStream(stream)
        }

        const val name = "ConstantInvokeDynamicInfo"
        const val tag = ConstantTags.CONSTANT_INVOKE_DYNAMIC
    }

}