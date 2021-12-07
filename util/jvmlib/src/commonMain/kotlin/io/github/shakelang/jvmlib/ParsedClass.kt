package io.github.shakelang.jvmlib

import io.github.shakelang.shason.json

class ClassFile (
    val minorVersion: Int,
    val majorVersion: Int,
    val accessFlags: Int,
    val name: String,
    val superClass: String,
    val interfaces: Array<String>,
    val fields: Array<String>,
    val methods: Array<ParsedMethod>,
    val attributes: Array<Int>
) {

    override fun toString(): String {
        return json.stringify(
            mapOf(
                "minorVersion" to "0x${minorVersion.toString(16)}",
                "majorVersion" to "0x${majorVersion.toString(16)}",
                "name" to name,
                "superClass" to superClass,
                "accessFlags" to "0x${accessFlags.toString(16)}",
                "interfaces" to interfaces,
                "fields" to fields,
                "methods" to methods,
                "attributes" to attributes
            )
        )
    }

}

class ParsedMethod {

}
