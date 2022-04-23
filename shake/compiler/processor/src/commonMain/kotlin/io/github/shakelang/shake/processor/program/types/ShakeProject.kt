package io.github.shakelang.shake.processor.program.types

import io.github.shakelang.shake.parser.node.ShakeFile
import io.github.shakelang.shake.processor.program.types.code.ShakeScope

interface ShakeProject {
    val subpackages: MutableList<ShakePackage>
    val classes: MutableList<ShakeClass>
    val functions: MutableList<ShakeFunction>
    val fields: MutableList<ShakeField>

    val projectScope: ShakeScope

    fun getPackage(name: String): ShakePackage
    fun getPackage(name: Array<String>): ShakePackage
    fun getPackage(name: String, then: (ShakePackage) -> Unit)
    fun putFile(name: String, contents: ShakeFile)
    fun putFile(name: Array<String>, contents: ShakeFile)
    fun getClass(pkg: Array<String>, name: String): ShakeClass?
    fun getClass(clz: String): ShakeClass?
    fun finish()
    fun toJson(): Map<String, Any?>
    fun toJsonString(format: Boolean = false): String
}