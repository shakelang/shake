package io.github.shakelang.jvmlib

import io.github.shakelang.jvmlib.constants.ConstantPool
import io.github.shakelang.jvmlib.info.AttributeInfo
import io.github.shakelang.jvmlib.info.FieldInfo
import io.github.shakelang.jvmlib.info.MethodInfo
import io.github.shakelang.shason.json

class ClassFile (
    val minorVersion: Int,
    val majorVersion: Int,
    val constantPool: ConstantPool,
    val accessFlags: Int,
    val thisClass: Int,
    val superClass: Int,
    val interfaces: Array<Int>,
    val fields: Array<FieldInfo>,
    val methods: Array<MethodInfo>,
    val attributes: Array<AttributeInfo>
) {

    fun toJson() = mapOf(
        "minorVersion" to "0x${minorVersion.toString(16)}",
        "majorVersion" to "0x${majorVersion.toString(16)}",
        "constantPool" to constantPool.toString(),
        "accessFlags" to "0x${accessFlags.toString(16)}",
        "thisClass" to "0x${thisClass.toString(16)}",
        "superClass" to "0x${superClass.toString(16)}",
        "interfaces" to interfaces.map { "0x${it.toString(16)}" },
        "fields" to fields.map { it.toString() },
        "methods" to methods.map { it.toString() },
        "attributes" to attributes.map { it.toString() }
    )

    override fun toString(): String {
        return json.stringify(toJson())
    }

}

class ParsedMethod {

}
