package com.shakelang.util.jvmlib.infos.methods

import com.shakelang.util.io.streaming.input.DataInputStream
import com.shakelang.util.io.streaming.input.InputStream
import com.shakelang.util.io.streaming.input.dataStream
import com.shakelang.util.io.streaming.output.ByteArrayOutputStream
import com.shakelang.util.io.streaming.output.DataOutputStream
import com.shakelang.util.io.streaming.output.OutputStream
import com.shakelang.util.jvmlib.infos.attributes.AttributeMap
import com.shakelang.util.jvmlib.infos.constants.ConstantPool
import com.shakelang.util.jvmlib.infos.constants.ConstantUser
import com.shakelang.util.jvmlib.infos.constants.ConstantUtf8Info
import com.shakelang.util.shason.json

class MethodInfo(
    val accessFlags: UShort,
    val name: ConstantUtf8Info,
    val descriptor: ConstantUtf8Info,
    val attributes: AttributeMap,
) : ConstantUser {

    val isPublic: Boolean
        get() = accessFlags.toInt() and 0x0001 != 0

    val isPrivate: Boolean
        get() = accessFlags.toInt() and 0x0002 != 0

    val isProtected: Boolean
        get() = accessFlags.toInt() and 0x0004 != 0

    val isStatic: Boolean
        get() = accessFlags.toInt() and 0x0008 != 0

    val isFinal: Boolean
        get() = accessFlags.toInt() and 0x0010 != 0

    val isSynchronized: Boolean
        get() = accessFlags.toInt() and 0x0020 != 0

    val isBridge: Boolean
        get() = accessFlags.toInt() and 0x0040 != 0

    val isVarargs: Boolean
        get() = accessFlags.toInt() and 0x0080 != 0

    val isNative: Boolean
        get() = accessFlags.toInt() and 0x0100 != 0

    val isAbstract: Boolean
        get() = accessFlags.toInt() and 0x0400 != 0

    val isStrict: Boolean
        get() = accessFlags.toInt() and 0x0800 != 0

    val isSynthetic: Boolean
        get() = accessFlags.toInt() and 0x1000 != 0

    val nameIndex: UShort get() = name.index
    val descriptorIndex: UShort get() = descriptor.index

    override val uses get() = arrayOf(name, descriptor, *attributes.uses)
    override val users: Array<ConstantUser> get() = arrayOf(this, *attributes.users)

    private lateinit var clazz: com.shakelang.util.jvmlib.infos.ClassInfo

    override fun toString(): String = json.stringify(toJson())

    fun toJson() = mapOf(
        "access_flags" to accessFlags,
        "name" to name.toJson(),
        "name_index" to nameIndex,
        "descriptor" to descriptor.toJson(),
        "descriptor_index" to descriptorIndex,
        "attributes" to attributes.toJson(),
    )

    fun init(clazz: com.shakelang.util.jvmlib.infos.ClassInfo) {
        this.clazz = clazz
        this.attributes.init(clazz)
    }

    fun dump(out: DataOutputStream) {
        out.writeUnsignedShort(accessFlags)
        out.writeUnsignedShort(nameIndex)
        out.writeUnsignedShort(descriptorIndex)
        attributes.dump(out)
    }

    fun dump(out: OutputStream) {
        dump(DataOutputStream(out))
    }

    fun toBytes(): ByteArray {
        val stream = ByteArrayOutputStream()
        dump(stream)
        return stream.toByteArray()
    }

    companion object {

        fun fromStream(pool: ConstantPool, stream: DataInputStream): MethodInfo {
            val accessFlags = stream.readUnsignedShort()
            val nameIndex = stream.readUnsignedShort()
            val name: ConstantUtf8Info = pool.getUtf8(nameIndex)
            val descriptorIndex = stream.readUnsignedShort()
            val descriptor: ConstantUtf8Info = pool.getUtf8(descriptorIndex)
            val attributes = AttributeMap.fromStream(pool, stream)
            return MethodInfo(accessFlags, name, descriptor, attributes)
        }

        fun fromStream(pool: ConstantPool, stream: InputStream): MethodInfo {
            return fromStream(pool, stream.dataStream)
        }

        fun fromBytes(pool: ConstantPool, bytes: ByteArray): MethodInfo {
            return fromStream(pool, bytes.dataStream())
        }
    }
}
