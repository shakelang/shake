package io.github.shakelang.shake.processor.program.types

import io.github.shakelang.shake.processor.program.types.code.ShakeScope

interface ShakeProject {
    val subpackages: List<ShakePackage>
    val classes: List<ShakeClass>
    val functions: List<ShakeFunction>
    val fields: List<ShakeField>

    val projectScope: ShakeScope

    fun getPackage(name: String): ShakePackage
    fun getPackage(name: Array<String>): ShakePackage
    fun getClass(pkg: Array<String>, name: String): ShakeClass?
    fun getClass(clz: String): ShakeClass?
    fun toJson(): Map<String, Any?>
    fun toJsonString(format: Boolean = false): String
}