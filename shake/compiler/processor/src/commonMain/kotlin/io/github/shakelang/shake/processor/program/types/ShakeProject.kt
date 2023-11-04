package io.github.shakelang.shake.processor.program.types

import io.github.shakelang.shake.processor.program.types.code.ShakeScope
import io.github.shakelang.shake.util.shason.json

interface ShakeProject {
    val subpackages: List<ShakePackage>
    val classes: List<ShakeClass>
    val functions: List<ShakeMethod>
    val fields: List<ShakeField>

    val projectScope: ShakeScope

    fun getPackage(name: String): ShakePackage? {
        return subpackages.find { it.name == name }
    }

    fun getPackage(name: Array<String>): ShakePackage? {
        return if (name.isEmpty()) null else getPackage(name.first())?.getPackage(name.drop(1))
    }

    fun getPackage(name: List<String>): ShakePackage? {
        return if (name.isEmpty()) null else getPackage(name.first())?.getPackage(name.drop(1))
    }

    fun getClass(pkg: Array<String>, name: String): ShakeClass? {
        return this.getPackage(pkg)?.classes?.find { it.name == name }
    }

    fun getClass(clz: String): ShakeClass? {
        val parts = clz.split(".")
        val name = parts.last()
        val pkg = parts.dropLast(1).toTypedArray()
        return if (pkg.isEmpty()) this.classes.find { it.name == name }
        else this.getPackage(pkg)?.classes?.find { it.name == name }
    }

    fun toJson(): Map<String, Any?> {
        return mapOf(
            "classes" to classes.map { it.toJson() },
            "functions" to functions.map { it.toJson() },
            "fields" to fields.map { it.toJson() },
            "subpackages" to subpackages.map { it.toJson() }
        )
    }

    fun toJsonString(format: Boolean = false): String {
        return json.stringify(toJson(), format)
    }
}