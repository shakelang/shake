package com.shakelang.util.jvmlib.infos.attributes

import com.shakelang.util.io.streaming.input.ByteArrayInputStream
import com.shakelang.util.io.streaming.input.DataInputStream
import com.shakelang.util.io.streaming.output.DataOutputStream
import com.shakelang.util.jvmlib.infos.constants.ConstantPool
import com.shakelang.util.jvmlib.infos.constants.ConstantUser
import com.shakelang.util.jvmlib.infos.constants.ConstantUtf8Info
import com.shakelang.util.primitives.bytes.setBytes
import com.shakelang.util.primitives.bytes.setInt
import com.shakelang.util.primitives.bytes.setUnsignedShort

abstract class AttributeInfo(val name: ConstantUtf8Info) : ConstantUser {

    val nameIndex get() = name.index

    private lateinit var clazz: com.shakelang.util.jvmlib.infos.ClassInfo

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
    fun init(clazz: com.shakelang.util.jvmlib.infos.ClassInfo) {
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
