package io.github.shakelang.shake.util.jvmlib.infos.constants

import io.github.shakelang.shake.util.io.streaming.input.DataInputStream
import io.github.shakelang.shake.util.io.streaming.input.InputStream
import io.github.shakelang.shake.util.io.streaming.input.dataStream
import io.github.shakelang.shake.util.io.streaming.output.DataOutputStream

class ConstantInterfaceMethodrefInfo(private val cri: UShort, val ntri: UShort) : ConstantInfo(), ConstantUser {

    lateinit var classRef: ConstantClassInfo
    lateinit var nameTypeRef: ConstantNameAndTypeInfo

    val classRefIndex: UShort get() = classRef.index
    val nameTypeRefIndex: UShort get() = nameTypeRef.index

    override val uses: Array<ConstantInfo> get() = arrayOf(classRef, nameTypeRef)

    override val tag: Byte get() = ConstantInterfaceMethodrefInfo.tag
    override val tagName: String get() = name

    override fun toJson() = super.toJson()
        .with("class_ref", classRefIndex.toInt())
        .with("name_type_ref", nameTypeRefIndex.toInt())

    override fun init(pool: ConstantPool) {
        super.init(pool)
        classRef = pool.getClass(cri)
        nameTypeRef = pool.getNameAndType(ntri)
    }

    override fun dump(out: DataOutputStream) {
        out.writeByte(tag)
        out.writeUnsignedShort(classRefIndex)
        out.writeUnsignedShort(nameTypeRefIndex)
    }

    companion object {
        fun contentsFromStream(stream: DataInputStream): ConstantInterfaceMethodrefInfo {
            val classRef = stream.readUnsignedShort()
            val nameTypeRef = stream.readUnsignedShort()
            return ConstantInterfaceMethodrefInfo(classRef, nameTypeRef)
        }

        fun contentsFromStream(stream: InputStream) = contentsFromStream(stream.dataStream)

        fun fromStream(stream: DataInputStream) =
            if (stream.readByte() != tag)
                throw IllegalArgumentException("Invalid tag for ConstantInterfaceMethodrefInfo")
            else contentsFromStream(stream)

        fun fromStream(stream: InputStream) = fromStream(stream.dataStream)

        fun contentsFromBytes(bytes: ByteArray) = contentsFromStream(bytes.dataStream())

        fun fromBytes(bytes: ByteArray) = fromStream(bytes.dataStream())

        const val name: String = "constant_interface_methodref_info"
        const val tag: Byte = ConstantTags.CONSTANT_INTERFACE_METHOD_REF
    }

}