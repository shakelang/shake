package io.github.shakelang.jvmlib.infos.fields

import io.github.shakelang.jvmlib.infos.ClassInfo
import io.github.shakelang.jvmlib.infos.attributes.AttributeMap
import io.github.shakelang.jvmlib.infos.constants.ConstantPool
import io.github.shakelang.jvmlib.infos.constants.ConstantUser
import io.github.shakelang.parseutils.streaming.DataInputStream
import io.github.shakelang.shason.json

class FieldInfo(
    val access_flags: UShort,
    val name_index: UShort,
    val descriptor_index: UShort,
    val attributes: AttributeMap,
) : ConstantUser {

    override val uses get() = arrayOf(name_index, descriptor_index, *attributes.uses)
    val users: Array<ConstantUser> get() = arrayOf(this, *attributes.users)

    private lateinit var clazz: ClassInfo

    override fun toString() = json.stringify(toJson())

    fun toJson() = mapOf(
        "access_flags" to access_flags,
        "name_index" to name_index,
        "descriptor_index" to descriptor_index,
        "attributes" to attributes.toJson()
    )

    fun init(clazz: ClassInfo) {
        this.clazz = clazz
        this.attributes.init(clazz)
    }

    companion object {
        fun fromStream(pool: ConstantPool, stream: DataInputStream): FieldInfo {
            val access_flags = stream.readUnsignedShort()
            val name_index = stream.readUnsignedShort()
            val descriptor_index = stream.readUnsignedShort()
            val attributes = AttributeMap.fromStream(pool, stream)
            return FieldInfo(access_flags, name_index, descriptor_index, attributes)
        }
    }
}