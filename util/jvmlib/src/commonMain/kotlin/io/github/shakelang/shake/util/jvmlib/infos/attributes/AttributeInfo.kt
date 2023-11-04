package io.github.shakelang.shake.util.jvmlib.infos.attributes

import io.github.shakelang.shake.util.io.streaming.input.ByteArrayInputStream
import io.github.shakelang.shake.util.io.streaming.input.DataInputStream
import io.github.shakelang.shake.util.io.streaming.output.DataOutputStream
import io.github.shakelang.shake.util.jvmlib.infos.ClassInfo
import io.github.shakelang.shake.util.jvmlib.infos.constants.ConstantPool
import io.github.shakelang.shake.util.jvmlib.infos.constants.ConstantUser
import io.github.shakelang.shake.util.jvmlib.infos.constants.ConstantUtf8Info
import io.github.shakelang.shake.util.primitives.bytes.setBytes
import io.github.shakelang.shake.util.primitives.bytes.setInt
import io.github.shakelang.shake.util.primitives.bytes.setUnsignedShort

abstract class AttributeInfo(val name: ConstantUtf8Info) : ConstantUser {

    val nameIndex get() = name.index

    private lateinit var clazz: ClassInfo

    abstract val bytes: ByteArray
    fun toBytes(): ByteArray {
        val bb = this.bytes
        val bytes = ByteArray(6 + bb.size)
        bytes.setUnsignedShort(0, nameIndex)
        bytes.setInt(2, bb.size)
        bytes.setBytes(6, bb)
        return bytes
    }

    abstract fun toJson(): Map<String, Any>
    fun init(clazz: ClassInfo) {
        this.clazz = clazz
    }

    fun dump(out: DataOutputStream) = out.write(toBytes())

    companion object {
        fun fromBytes(pool: ConstantPool, bytes: ByteArray): AttributeInfo =
            fromStream(pool, DataInputStream(ByteArrayInputStream(bytes)))

        fun fromStream(pool: ConstantPool, stream: DataInputStream): AttributeInfo {
            val nameIndex = stream.readUnsignedShort()
            val name = pool.getUtf8(nameIndex)
            val length = stream.readInt()
            val bytes = stream.readNBytes(length)
//            println("AttributeInfo: $name")
//            println("AttributeBytes: ${bytes.map{it.toUByte().toString(16)}}")

            val attrStream = DataInputStream(ByteArrayInputStream(bytes))
            try {
                return when (name.value) {
                    "ConstantValue" -> AttributeConstantValueInfo.contentsFromStream(pool, attrStream, name)
                    "Code" -> AttributeCodeInfo.contentsFromStream(pool, attrStream, name)
                    "StackMapTable" -> AttributeStackMapTableInfo.contentsFromStream(attrStream, name)
                    else -> AttributeUnknownInfo.contentsFromStream(pool, attrStream, name, length)
                }.let {
                    if (attrStream.available() > 0) {
                        println("WARNING: ${attrStream.available()} bytes left in attribute $name")
                    }
                    it
                }
            } catch (e: Throwable) {
//                println("AttributeInfo: $name, $length bytes")
                throw e
            }
        }
    }
}