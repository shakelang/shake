package io.github.shakelang.jvmlib.infos.constants

import io.github.shakelang.parseutils.streaming.DataInputStream

class ConstantMethodrefInfo(val classRefIndex: UShort, val nameTypeRefIndex: UShort) : ConstantInfo(), ConstantUser {

    override val uses get() = arrayOf(classRefIndex, nameTypeRefIndex)

    override val tag: Byte get() = ConstantMethodrefInfo.tag
    override val type: String get() = name
    
    override fun toJson() = super.toJson().with("classRef", classRefIndex).with("nameTypeRef", nameTypeRefIndex)

    fun getClassRef(pool: ConstantPool) : ConstantClassInfo = pool[classRefIndex.toInt()] as ConstantClassInfo
    fun getNameTypeRef(pool: ConstantPool) : ConstantNameAndTypeInfo = pool[nameTypeRefIndex.toInt()] as ConstantNameAndTypeInfo

    companion object {
        fun contentsFromStream(stream: DataInputStream): ConstantMethodrefInfo {
            val classRef = stream.readUnsignedShort()
            val nameTypeRef = stream.readUnsignedShort()
            return ConstantMethodrefInfo(classRef, nameTypeRef)
        }

        fun fromStream(stream: DataInputStream): ConstantMethodrefInfo {
            if(stream.readByte() != ConstantClassInfo.tag)
                throw IllegalArgumentException("Invalid tag for ConstantMethodrefInfo")
            return contentsFromStream(stream)
        }

        const val name = "ConstantMethodrefInfo"
        const val tag = ConstantTags.CONSTANT_METHOD_REF
    }

}