package io.github.shakelang.jvmlib.infos.constants

import io.github.shakelang.parseutils.streaming.input.DataInputStream

class ConstantFieldrefInfo(private val cri: UShort, val ntri: UShort) : ConstantInfo(), ConstantUser {


    lateinit var classRef: ConstantClassInfo
    lateinit var nameTypeRef: ConstantNameAndTypeInfo

    val classRefIndex: UShort get() = constantPool.indexOf(classRef).toUShort()
    val nameTypeRefIndex: UShort get() = constantPool.indexOf(nameTypeRef).toUShort()

    override val uses: Array<ConstantInfo> get() = arrayOf(classRef, nameTypeRef)

    override val tag: Byte get() = ConstantFieldrefInfo.tag
    override val tagName: String get() = name
    override fun toJson() = super.toJson().with("classRef", classRefIndex).with("nameTypeRef", nameTypeRefIndex)

    override fun init(pool: ConstantPool) {
        super.init(pool)
        classRef = pool.getClass(cri)
        nameTypeRef = pool.getNameAndType(ntri)
    }

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