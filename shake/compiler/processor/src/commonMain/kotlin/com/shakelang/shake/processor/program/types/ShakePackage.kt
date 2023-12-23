package com.shakelang.shake.processor.program.types

import com.shakelang.shake.processor.program.types.code.ShakeScope

interface ShakePackage {
    val baseProject: ShakeProject
    val name: String
    val parent: ShakePackage?
    val subpackages: List<ShakePackage>
    val classes: List<ShakeClass>
    val functions: List<ShakeMethod>
    val fields: List<ShakeField>

    val qualifierPrefix: String get() = (parent?.qualifierPrefix ?: "") + name + "/"
    val qualifiedName: String get() = (parent?.qualifierPrefix ?: "") + name

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

    fun getClass(name: List<String>): ShakeClass? {

        println("Searching for class: ${name.joinToString(".")} in package: $qualifiedName")

        if (name.isEmpty()) return null
        if (name.size == 1) return getClass(name.first())

        val subClass = getClass(name.first())
        val searched0 = subClass?.getClass(name.drop(1))
        if (searched0 != null) return searched0

        val searched1 = getPackage(name.first())
        if (searched1 != null) return searched1.getClass(name.drop(1))

        return null
    }

    fun getClass(name: Array<String>) = getClass(name.toList())

    fun getClass(name: String): ShakeClass? {
        return classes.find { it.name == name }
    }

    fun getFunctions(name: List<String>): List<ShakeMethod>? {
        if (name.isEmpty()) return emptyList()
        if (name.size == 1) return getFunctions(name.first())
        val subClass = getClass(name.first())
        val subClassResults = subClass?.getMethods(name.drop(1)) ?: emptyList()
        val subPackageResults = getPackage(name.first())?.getFunctions(name.drop(1)) ?: emptyList()
        return subClassResults + subPackageResults
    }

    fun getFunctions(name: Array<String>) = getFunctions(name.toList())

    fun getFunctions(name: String): List<ShakeMethod> {
        return functions.filter { it.name == name }
    }

    fun getField(name: List<String>): ShakeField? {
        if (name.isEmpty()) return null
        if (name.size == 1) return getField(name.first())
        val subClass = getClass(name.first())
        val searched0 = subClass?.getField(name.drop(1))
        if (searched0 != null) return searched0
        val searched1 = getPackage(name.first())
        if (searched1 != null) return searched1.getField(name.drop(1))
        return null
    }

    fun getField(name: Array<String>) = getField(name.toList())

    fun getField(name: String): ShakeField? {
        return fields.find { it.name == name }
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

    fun phase1()
    fun phase2()
    fun phase3()
    fun phase4()
}
