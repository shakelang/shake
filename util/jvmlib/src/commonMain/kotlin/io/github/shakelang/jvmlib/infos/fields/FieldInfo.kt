package io.github.shakelang.jvmlib.infos.fields

import io.github.shakelang.jvmlib.infos.ClassInfo
import io.github.shakelang.jvmlib.infos.attributes.AttributeMap
import io.github.shakelang.jvmlib.infos.constants.ConstantPool
import io.github.shakelang.jvmlib.infos.constants.ConstantUser
import io.github.shakelang.jvmlib.infos.constants.ConstantUtf8Info
import io.github.shakelang.parseutils.streaming.DataInputStream
import io.github.shakelang.shason.json

class FieldInfo(
    val access_flags: UShort,
    val name: ConstantUtf8Info,
    val descriptor: ConstantUtf8Info,
    val attributes: AttributeMap,
) : ConstantUser {

    val nameIndex get() = name.index
    val descriptorIndex get() = descriptor.index

    override val uses get() = arrayOf(nameIndex, descriptorIndex, *attributes.uses)
    val users: Array<ConstantUser> get() = arrayOf(this, *attributes.users)

    private lateinit var clazz: ClassInfo
    val classInfo: ClassInfo get() = clazz

    override fun toString() = json.stringify(toJson())

    fun toJson() = mapOf(
        "access_flags" to access_flags,
        "name_index" to name,
        "descriptor_index" to descriptor,
        "attributes" to attributes.toJson()
    )

    fun init(clazz: ClassInfo) {
        this.clazz = clazz
        this.attributes.init(clazz)
    }

    companion object {
        fun fromStream(pool: ConstantPool, stream: DataInputStream): FieldInfo {
            val access_flags = stream.readUnsignedShort()
            val nameIndex = stream.readUnsignedShort()
            val name = pool.getUtf8(nameIndex)
            val descriptorIndex = stream.readUnsignedShort()
            val descriptor = pool.getUtf8(descriptorIndex)
            val attributes = AttributeMap.fromStream(pool, stream)
            return FieldInfo(access_flags, name, descriptor, attributes)
        }
    }
}