package io.github.shakelang.jvmlib.infos.methods

import io.github.shakelang.jvmlib.infos.ClassInfo
import io.github.shakelang.jvmlib.infos.attributes.AttributeMap
import io.github.shakelang.jvmlib.infos.constants.ConstantPool
import io.github.shakelang.jvmlib.infos.constants.ConstantUser
import io.github.shakelang.jvmlib.infos.constants.ConstantUtf8Info
import io.github.shakelang.parseutils.streaming.input.DataInputStream
import io.github.shakelang.parseutils.streaming.output.DataOutputStream
import io.github.shakelang.shason.json

class MethodInfo(
    val accessFlags: UShort,
    val name: ConstantUtf8Info,
    val descriptor: ConstantUtf8Info,
    val attributes: AttributeMap
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

    private lateinit var clazz: ClassInfo

    override fun toString(): String = json.stringify(toJson())

    fun toJson() = mapOf(
        "name" to name,
        "descriptor" to descriptor,
        "accessFlags" to accessFlags,
        "attributes" to attributes.toJson()
    )

    fun init(clazz: ClassInfo) {
        this.clazz = clazz
        this.attributes.init(clazz)
    }

    fun dump(out: DataOutputStream) {
        out.writeUnsignedShort(accessFlags)
        out.writeUnsignedShort(nameIndex)
        out.writeUnsignedShort(descriptorIndex)
        attributes.dump(out)
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

    }
}