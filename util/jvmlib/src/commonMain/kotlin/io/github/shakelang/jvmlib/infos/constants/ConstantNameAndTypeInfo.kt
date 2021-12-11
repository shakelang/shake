package io.github.shakelang.jvmlib.infos.constants

import io.github.shakelang.parseutils.streaming.DataInputStream

class ConstantNameAndTypeInfo(val typeIndex: UShort, val nameIndex: UShort) : ConstantInfo(), ConstantUser {

    override val uses get() = arrayOf(typeIndex, nameIndex)

    override val tag: Byte get() = ConstantNameAndTypeInfo.tag
    override val type: String get() = ConstantNameAndTypeInfo.name

    override fun toJson() = super.toJson().with("name", typeIndex).with("type", nameIndex)
    fun getName(pool: ConstantPool) = pool[nameIndex.toInt()] as ConstantUtf8Info
    fun getValue(pool: ConstantPool) = pool[typeIndex.toInt()] as ConstantUtf8Info

    companion object {
        fun contentsFromStream(stream: DataInputStream): ConstantNameAndTypeInfo {
            val value = stream.readUnsignedShort()
            val name = stream.readUnsignedShort()
            return ConstantNameAndTypeInfo(value, name)
        }

        fun fromStream(stream: DataInputStream): ConstantNameAndTypeInfo {
            if(stream.readByte() != ConstantClassInfo.tag)
                throw IllegalArgumentException("Invalid tag for ConstantNameAndTypeInfo")
            return contentsFromStream(stream)
        }

        const val name = "ConstantNameAndTypeInfo"
        const val tag = ConstantTags.CONSTANT_NAME_AND_TYPE
    }

}