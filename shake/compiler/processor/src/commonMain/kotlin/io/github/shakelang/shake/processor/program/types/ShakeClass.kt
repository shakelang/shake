package io.github.shakelang.shake.processor.program.types

import io.github.shakelang.shake.processor.program.types.code.ShakeScope
import kotlin.math.min

interface ShakeClass {
    val staticScope: ShakeScope
    val instanceScope: ShakeScope
    val prj: ShakeProject
    val pkg: ShakePackage?
    val parentScope: ShakeScope
    val name: String

    val methods: List<ShakeMethod>
    val fields: List<ShakeField>
    val classes: List<ShakeClass>

    val isAbstract: Boolean
    val isFinal: Boolean
    val isStatic: Boolean
    val isPublic: Boolean
    val isPrivate: Boolean
    val isProtected: Boolean
    val isNative: Boolean

    val instanceMethods: List<ShakeMethod> get() = methods.filter { !it.isStatic }
    val instanceFields: List<ShakeField> get() = fields.filter { !it.isStatic }
    val instanceClasses: List<ShakeClass> get() = classes.filter { !it.isStatic }

    val staticMethods: List<ShakeMethod> get() = methods.filter { it.isStatic }
    val staticFields: List<ShakeField> get() = fields.filter { it.isStatic }
    val staticClasses: List<ShakeClass> get() = classes.filter { it.isStatic }

    val constructors: List<ShakeConstructor>

    val qualifiedName: String get() = (pkg?.qualifiedName?.plus(".") ?: "") + name
    val signature: String get() = qualifiedName
    val superClass: ShakeClass

    val interfaces: List<ShakeClass>

    private val isObject get() = qualifiedName == "shake.lang.Object"

    fun compatibleTo(other: ShakeClass): Boolean {
        if (this == other) return true
        if (isObject) return false
        if (this.superClass.compatibleTo(other)) return true
        return this.interfaces.any { it.compatibleTo(other) }
    }

    fun compatibilityDistance(other: ShakeClass): Int {
        if (this == other) return 0
        if (isObject) return -1
        val scd = this.superClass.compatibilityDistance(other) + 1
        val intDistance = (this.interfaces.minOfOrNull { it.compatibilityDistance(other) } ?: -2) + 1
        if (scd < 0) return intDistance
        if (intDistance < 0) return scd
        return min(scd, intDistance)
    }

    fun toJson(): Map<String, Any?> {
        return mapOf(
            "name" to this.name,
            "super" to this.superClass?.name,
            "interfaces" to this.interfaces.map { it.name },
            "methods" to this.methods.map { it.toJson() },
            "staticMethods" to this.staticMethods.map { it.toJson() },
            "fields" to this.fields.map { it.toJson() },
            "staticFields" to this.staticFields.map { it.toJson() },
            "constructors" to this.constructors.map { it.toJson() },
            "classes" to this.classes.map { it.toJson() },
            "staticClasses" to this.staticClasses.map { it.toJson() }
        )
    }
}
