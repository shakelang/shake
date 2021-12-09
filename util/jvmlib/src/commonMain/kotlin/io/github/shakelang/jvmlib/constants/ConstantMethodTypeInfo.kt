package io.github.shakelang.jvmlib.constants

import io.github.shakelang.parseutils.streaming.DataInputStream

class ConstantMethodTypeInfo(val index: UShort) : ConstantInfo() {

    override val tag: Byte get() = ConstantMethodrefInfo.tag
    override val type: String get() = ConstantMethodrefInfo.name
    override fun toJson() = super.toJson().with("index", index)

    fun getIndex(pool: ConstantPool) = pool[index.toInt()] as ConstantMethodTypeInfo
    fun getValue(pool: ConstantPool) = pool[index.toInt()] as ConstantMethodTypeInfo
    fun getType(pool: ConstantPool) = pool[index.toInt()] as ConstantMethodTypeInfo

    companion object {
        fun contentsFromStream(stream: DataInputStream): ConstantMethodTypeInfo {
            val index = stream.readUnsignedShort()
            return ConstantMethodTypeInfo(index)
        }

        fun fromStream(stream: DataInputStream): ConstantMethodTypeInfo {
            if(stream.readByte() != ConstantClassInfo.tag)
                throw IllegalArgumentException("Invalid tag for ConstantMethodTypeInfo")
            return contentsFromStream(stream)
        }

        const val name = "ConstantMethodTypeInfo"
        const val tag = ConstantTags.CONSTANT_METHODTYPE
    }

}