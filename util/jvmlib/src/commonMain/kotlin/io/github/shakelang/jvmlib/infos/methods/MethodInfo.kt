package io.github.shakelang.jvmlib.infos.methods

import io.github.shakelang.jvmlib.infos.ClassInfo
import io.github.shakelang.jvmlib.infos.attributes.AttributeMap
import io.github.shakelang.jvmlib.infos.constants.ConstantPool
import io.github.shakelang.parseutils.streaming.DataInputStream
import io.github.shakelang.shason.json

class MethodInfo(
    val name: UShort,
    val descriptor: UShort,
    val accessFlags: UShort,
    val attributes: AttributeMap
) {

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
            val name = stream.readUnsignedShort()
            val descriptor = stream.readUnsignedShort()
            val attributes = AttributeMap.fromStream(pool, stream)
            return MethodInfo(name, descriptor, accessFlags, attributes)

        }

    }
}