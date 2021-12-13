package io.github.shakelang.jvmlib.infos.attributes

import io.github.shakelang.jvmlib.infos.ClassInfo
import io.github.shakelang.jvmlib.infos.constants.ConstantPool
import io.github.shakelang.jvmlib.infos.constants.ConstantUser
import io.github.shakelang.jvmlib.infos.constants.ConstantUtf8Info
import io.github.shakelang.parseutils.bytes.setBytes
import io.github.shakelang.parseutils.bytes.setInt
import io.github.shakelang.parseutils.bytes.setUnsignedShort
import io.github.shakelang.parseutils.streaming.input.ByteArrayInputStream
import io.github.shakelang.parseutils.streaming.input.DataInputStream
import io.github.shakelang.parseutils.streaming.output.DataOutputStream

abstract class AttributeInfo (val name: ConstantUtf8Info) : ConstantUser {

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
        fun fromBytes(pool: ConstantPool, bytes: ByteArray): AttributeInfo
            = fromStream(pool, DataInputStream(ByteArrayInputStream(bytes)))

        fun fromStream(pool: ConstantPool, stream: DataInputStream): AttributeInfo {
            val nameIndex = stream.readUnsignedShort()
            val name = pool.getUtf8(nameIndex)
            val length = stream.readInt()
            return when (name.value) {
                "ConstantValue" -> AttributeConstantValueInfo.contentsFromStream(pool, stream, name)
                "Code" -> AttributeCodeInfo.contentsFromStream(pool, stream, name)
                "StackMapTable" -> AttributeStackMapTableInfo.contentsFromStream(stream, name)
                else -> AttributeUnknownInfo.contentsFromStream(pool, stream, name, length)
            }
        }
    }
}