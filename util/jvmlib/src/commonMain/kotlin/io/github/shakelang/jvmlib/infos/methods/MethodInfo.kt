package io.github.shakelang.jvmlib.infos.methods

import io.github.shakelang.jvmlib.infos.ClassInfo
import io.github.shakelang.jvmlib.infos.attributes.AttributeMap
import io.github.shakelang.jvmlib.infos.constants.ConstantPool
import io.github.shakelang.jvmlib.infos.constants.ConstantUser
import io.github.shakelang.jvmlib.infos.constants.ConstantUtf8Info
import io.github.shakelang.parseutils.streaming.DataInputStream
import io.github.shakelang.shason.json

class MethodInfo(
    val accessFlags: UShort,
    val name: ConstantUtf8Info,
    val descriptor: ConstantUtf8Info,
    val attributes: AttributeMap
) : ConstantUser {

    val nameIndex: UShort get() = name.index
    val descriptorIndex: UShort get() = descriptor.index

    override val uses get() = arrayOf(nameIndex, descriptorIndex, *attributes.uses)
    val users: Array<ConstantUser> get() = arrayOf(this, *attributes.users)

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

    companion object {

        fun fromStream(pool: ConstantPool, stream: DataInputStream): MethodInfo {

            val accessFlags = stream.readUnsignedShort()
            val nameIndex = stream.readUnsignedShort()
            val name: ConstantUtf8Info = pool.getUtf8(nameIndex)
            val descriptorIndex = stream.readUnsignedShort()
            val descriptor: ConstantUtf8Info = pool.getUtf8(descriptorIndex)
            val attributes = AttributeMap.fromStream(pool, stream)
            return MethodInfo(accessFlags, descriptor, name, attributes)

        }

    }
}