package io.github.shakelang.shake.processor.program.types

import io.github.shakelang.shake.processor.program.types.code.ShakeScope

interface ShakeClass {
    val staticScope: ShakeScope
    val instanceScope: ShakeScope
    val prj: ShakeProject
    val pkg: ShakePackage?
    val parentScope: ShakeScope
    val name: String
    val methods: List<ShakeMethod>
    val fields: List<ShakeClassField>
    val classes: List<ShakeClass>
    val staticMethods: List<ShakeMethod>
    val staticFields: List<ShakeClassField>
    val staticClasses: List<ShakeClass>
    val constructors: List<ShakeConstructor>

    val qualifiedName: String
    val superClass: ShakeClass?

    val interfaces: List<ShakeClass>

    fun compatibleTo(other: ShakeClass): Boolean
    fun compatibilityDistance(other: ShakeClass): Int

    fun toJson(): Map<String, Any?>
}