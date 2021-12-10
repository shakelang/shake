package io.github.shakelang.jvmlib.infos.constants

import io.github.shakelang.parseutils.streaming.DataInputStream

class ConstantMethodHandleInfo(val name: Byte, val index: UShort) : ConstantInfo(), ConstantUser {

    override val uses get() = arrayOf(index)

    override val tag: Byte get() = ConstantMethodHandleInfo.tag
    override val type: String get() = ConstantMethodHandleInfo.name
    override fun toJson() = super.toJson().with("type", name).with("index", index)

    fun getIndex(pool: ConstantPool) = pool[index.toInt()]
    fun getValue(pool: ConstantPool) = pool[index.toInt()]


    companion object {
        fun contentsFromStream(stream: DataInputStream): ConstantMethodHandleInfo {
            val name = stream.readByte()
            val index = stream.readUnsignedShort()
            return ConstantMethodHandleInfo(name, index)
        }

        fun contentsFromStream(stream: DataInputStream, pool: ConstantPool): ConstantMethodHandleInfo {
            if(stream.readByte() != ConstantClassInfo.tag)
                throw IllegalArgumentException("Expected ConstantClassInfo")
            return contentsFromStream(stream)
        }

        const val name = "ConstantMethodHandleInfo"
        const val tag = ConstantTags.CONSTANT_METHOD_HANDLE
    }

}