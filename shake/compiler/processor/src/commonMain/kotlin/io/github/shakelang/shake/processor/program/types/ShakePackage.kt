package io.github.shakelang.shake.processor.program.types

import io.github.shakelang.shake.processor.program.types.code.ShakeScope

interface ShakePackage {
    val baseProject: ShakeProject
    val name: String
    val parent: ShakePackage?
    val subpackages: MutableList<ShakePackage>
    val classes: MutableList<ShakeClass>
    val functions: MutableList<ShakeFunction>
    val fields: MutableList<ShakeField>

    val qualifiedName: String
    val scope: ShakeScope

    fun getPackage(name: String): ShakePackage
    fun getPackage(name: Array<String>): ShakePackage

    fun toJson(): Map<String, Any?>
}