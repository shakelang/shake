package io.github.shakelang.jvmlib.info

import io.github.shakelang.jvmlib.constants.JavaClassConstant
import io.github.shakelang.shason.json

class MethodInfo(
    val name: Int,
    val descriptor: Int,
    val accessFlags: Int,
    val attributes: Array<Int>
) {

    fun getNameValue(constants: Array<JavaClassConstant>): String {
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