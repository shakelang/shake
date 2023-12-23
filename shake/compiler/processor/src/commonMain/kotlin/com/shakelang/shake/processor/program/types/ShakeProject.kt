package com.shakelang.shake.processor.program.types

import com.shakelang.shake.processor.program.types.code.ShakeScope
import com.shakelang.shake.util.shason.json

interface ShakeProject {
    val subpackages: List<ShakePackage>

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

    fun getClass(name: List<String>): ShakeClass? = getPackage(name.first())?.getClass(name.drop(1))

    fun getClass(name: Array<String>): ShakeClass? = getPackage(name.first())?.getClass(name.drop(1))
    fun getClass(clz: String): ShakeClass? = getClass(clz.split("."))

    fun getFunctions(name: List<String>): List<ShakeMethod> =
        getPackage(name.first())?.getFunctions(name.drop(1)) ?: emptyList()

    fun getFunctions(name: Array<String>): List<ShakeMethod> =
        getPackage(name.first())?.getFunctions(name.drop(1)) ?: emptyList()

    fun getFunctions(name: String): List<ShakeMethod> = getFunctions(name.split("."))

    fun getField(name: List<String>): ShakeField? =
        getPackage(name.first())?.getField(name.drop(1))

    fun getField(name: Array<String>): ShakeField? =
        getPackage(name.first())?.getField(name.drop(1))

    fun getField(name: String): ShakeField? = getField(name.split("."))


    fun toJson(): Map<String, Any?> {
        return mapOf(
            "subpackages" to subpackages.map { it.toJson() }
        )
    }

    fun toJsonString(format: Boolean = false): String {
        return json.stringify(toJson(), format)
    }

    fun phase1()
    fun phase2()
    fun phase3()
    fun phase4()
}
