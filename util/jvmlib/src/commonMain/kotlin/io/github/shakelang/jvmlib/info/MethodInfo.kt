package io.github.shakelang.jvmlib.info

import io.github.shakelang.jvmlib.constants.ConstantInfo
import io.github.shakelang.shason.json

class MethodInfo(
    val name: Int,
    val descriptor: Int,
    val accessFlags: Int,
    val attributes: Array<AttributeInfo>
) {

    fun getNameValue(constants: Array<ConstantInfo>): String {
        //(constants[name] as ).value as String
        TODO()
    }
    override fun toString(): String = json.stringify(toJson())

    fun toJson() = mapOf(
        "name" to name,
        "descriptor" to descriptor,
        "accessFlags" to accessFlags,
        "attributes" to attributes.contentToString()
    )
}