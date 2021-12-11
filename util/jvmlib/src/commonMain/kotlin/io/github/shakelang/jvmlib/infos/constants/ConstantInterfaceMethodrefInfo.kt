package io.github.shakelang.jvmlib.infos.constants

import io.github.shakelang.parseutils.streaming.DataInputStream

class ConstantInterfaceMethodrefInfo(val classRefIndex: UShort, val nameTypeRefIndex: UShort) : ConstantInfo(), ConstantUser {

    override val uses get() = arrayOf(classRefIndex, nameTypeRefIndex)

    override val tag: Byte get() = ConstantInterfaceMethodrefInfo.tag
    override val type: String get() = name

    override fun toJson() = super.toJson().with("classRef", classRefIndex).with("nameTypeRef", nameTypeRefIndex)

    fun getClassRef(pool: ConstantPool) : ConstantClassInfo = pool[classRefIndex.toInt()] as ConstantClassInfo
    fun getNameTypeRef(pool: ConstantPool) : ConstantNameAndTypeInfo = pool[nameTypeRefIndex.toInt()] as ConstantNameAndTypeInfo

    companion object {
        fun contentsFromStream(stream: DataInputStream): ConstantInterfaceMethodrefInfo {
            val classRef = stream.readUnsignedShort()
            val nameTypeRef = stream.readUnsignedShort()
            return ConstantInterfaceMethodrefInfo(classRef, nameTypeRef)
        }

        fun fromStream(stream: DataInputStream): ConstantInterfaceMethodrefInfo {
            if(stream.readByte() != ConstantClassInfo.tag)
                throw IllegalArgumentException("Invalid tag for ConstantInterfaceMethodrefInfo")
            return contentsFromStream(stream)
        }

        const val name: String = "ConstantInterfaceMethodrefInfo"
        const val tag: Byte = ConstantTags.CONSTANT_INTERFACE_METHOD_REF
    }

}