package io.github.shakelang.jvmlib.constants

import io.github.shakelang.parseutils.streaming.DataInputStream

class ConstantFieldrefInfo(val classRef: UShort, val nameTypeRef: UShort) : ConstantInfo() {

    override val tag: Byte get() = ConstantFieldrefInfo.tag
    override val type: String get() = name
    override fun toJson() = super.toJson().with("classRef", classRef).with("nameTypeRef", nameTypeRef)

    fun getClassRef(pool: ConstantPool) : ConstantClassInfo = pool[classRef.toInt()] as ConstantClassInfo
    fun getNameTypeRef(pool: ConstantPool) : ConstantNameAndTypeInfo = pool[nameTypeRef.toInt()] as ConstantNameAndTypeInfo

    companion object {
        fun contentsFromStream(stream: DataInputStream): ConstantFieldrefInfo {
            val classRef = stream.readUnsignedShort()
            val nameTypeRef = stream.readUnsignedShort()
            return ConstantFieldrefInfo(classRef, nameTypeRef)
        }

        fun fromStream(stream: DataInputStream): ConstantFieldrefInfo {
            if(stream.readByte() != ConstantClassInfo.tag)
                throw IllegalArgumentException("Invalid tag for ConstantFieldrefInfo")
            return contentsFromStream(stream)
        }

        const val name = "ConstantFieldrefInfo"
        const val tag = ConstantTags.CONSTANT_FIELD_REF
    }

}