package io.github.shakelang.jvmlib.infos.constants

import io.github.shakelang.parseutils.streaming.input.DataInputStream
import io.github.shakelang.parseutils.streaming.output.DataOutputStream

class ConstantMethodTypeInfo(val di: UShort) : ConstantInfo(), ConstantUser {

    lateinit var descriptor: ConstantUtf8Info
    val descriptorIndex get() = descriptor.index

    override val uses: Array<ConstantInfo> get() = arrayOf(descriptor)

    override val tag: Byte get() = ConstantMethodrefInfo.tag
    override val tagName: String get() = ConstantMethodrefInfo.name
    override fun toJson() = super.toJson().with("index", descriptorIndex)

    fun getIndex(pool: ConstantPool) = pool[descriptorIndex.toInt()] as ConstantMethodTypeInfo
    fun getValue(pool: ConstantPool) = pool[descriptorIndex.toInt()] as ConstantMethodTypeInfo
    fun getType(pool: ConstantPool) = pool[descriptorIndex.toInt()] as ConstantMethodTypeInfo

    override fun init(pool: ConstantPool) {
        super.init(pool)
        descriptor = pool.getUtf8(di.toInt())
    }

    override fun dump(out: DataOutputStream) {
        out.writeByte(tag)
        out.writeUnsignedShort(descriptorIndex)
    }

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

        const val name = "constant_methodtype_info"
        const val tag = ConstantTags.CONSTANT_METHODTYPE
    }

}