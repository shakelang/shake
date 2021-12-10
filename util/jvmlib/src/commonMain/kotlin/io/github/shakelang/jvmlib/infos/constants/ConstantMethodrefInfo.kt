package io.github.shakelang.jvmlib.infos.constants

import io.github.shakelang.parseutils.streaming.DataInputStream

class ConstantMethodrefInfo(val classRef: UShort, val nameTypeRef: UShort) : ConstantInfo() {

    override val tag: Byte get() = ConstantMethodrefInfo.tag
    override val type: String get() = name
    
    override fun toJson() = super.toJson().with("classRef", classRef).with("nameTypeRef", nameTypeRef)

    fun getClassRef(pool: ConstantPool) : ConstantClassInfo = pool[classRef.toInt()] as ConstantClassInfo
    fun getNameTypeRef(pool: ConstantPool) : ConstantNameAndTypeInfo = pool[nameTypeRef.toInt()] as ConstantNameAndTypeInfo

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