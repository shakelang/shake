package io.github.shakelang.shake.processor.program.types

import io.github.shakelang.shake.processor.program.types.code.ShakeScope

interface ShakePackage {
    val baseProject: ShakeProject
    val name: String
    val parent: ShakePackage?
    val subpackages: List<ShakePackage>
    val classes: List<ShakeClass>
    val functions: List<ShakeMethod>
    val fields: List<ShakeField>

    val qualifiedName: String get() = (parent?.qualifiedName?.plus(".") ?: "") + name
    val scope: ShakeScope

    fun getPackage(name: String): ShakePackage? {
        return subpackages.find { it.name == name }
    }

    fun getPackage(name: Array<String>): ShakePackage? {
        return if (name.isEmpty()) this else getPackage(name.first())?.getPackage(name.drop(1))
    }

    fun getPackage(name: List<String>): ShakePackage? {
        return if (name.isEmpty()) this else getPackage(name.first())?.getPackage(name.drop(1))
    }

    fun toJson(): Map<String, Any?> {
        return mapOf(
            "name" to name,
            "parent" to parent?.name,
            "subpackages" to subpackages.map { it.toJson() },
            "classes" to classes.map { it.toJson() },
            "functions" to functions.map { it.toJson() },
            "fields" to fields.map { it.toJson() }
        )
    }
}
