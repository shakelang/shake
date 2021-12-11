package io.github.shakelang.jvmlib.infos.constants

import io.github.shakelang.parseutils.streaming.DataInputStream

class ConstantClassInfo(val valueIndex: UShort) : ConstantInfo(), ConstantUser {

    override val uses get() = arrayOf(valueIndex)

    override val tag: Byte get() = ConstantClassInfo.tag
    override val type: String get() = name
    override fun toJson() = super.toJson().with("value", valueIndex)

    fun getValue(pool: ConstantPool) = pool[valueIndex.toInt()] as ConstantUtf8Info

    companion object {
        fun contentsFromStream(stream: DataInputStream): ConstantClassInfo {
            val value = stream.readUnsignedShort()
            return ConstantClassInfo(value)
        }
        fun fromStream(stream: DataInputStream): ConstantClassInfo {
            if(stream.readByte() != tag) throw IllegalArgumentException("Invalid tag for ConstantClassInfo")
            return contentsFromStream(stream)
        }

        const val name = "ConstantClassInfo"
        const val tag = ConstantTags.CONSTANT_CLASS
    }

}