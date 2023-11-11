package io.github.shakelang.shake.processor.program.types

import io.github.shakelang.shake.processor.program.types.code.ShakeScope
import io.github.shakelang.shake.processor.program.types.code.values.ShakeValue

interface ShakeField : ShakeDeclaration, ShakeAssignable {
    val project: ShakeProject
    val pkg: ShakePackage?
    val clazz: ShakeClass?
    val parentScope: ShakeScope
    val isStatic: Boolean
    val isFinal: Boolean
    val isAbstract: Boolean
    val isPrivate: Boolean
    val isProtected: Boolean
    val isPublic: Boolean
    val isNative: Boolean
    val initialValue: ShakeValue?
    val expanding: ShakeType?

    override val qualifiedName: String
        get() = "${(clazz?.qualifiedName ?: pkg?.qualifiedName)?.plus("$")}${expanding?.qualifiedName?.plus("$") ?: ""}$name"

    val signature: String get() = name

    override fun assignType(other: ShakeType, scope: ShakeScope): ShakeType = type.assignType(other, scope) ?: other
    override fun additionAssignType(other: ShakeType, scope: ShakeScope): ShakeType =
        type.additionAssignType(other, scope) ?: type

    override fun subtractionAssignType(other: ShakeType, scope: ShakeScope): ShakeType =
        type.subtractionAssignType(other, scope) ?: type

    override fun multiplicationAssignType(other: ShakeType, scope: ShakeScope): ShakeType =
        type.multiplicationAssignType(other, scope) ?: type

    override fun divisionAssignType(other: ShakeType, scope: ShakeScope): ShakeType =
        type.divisionAssignType(other, scope) ?: type

    override fun modulusAssignType(other: ShakeType, scope: ShakeScope): ShakeType =
        type.modulusAssignType(other, scope) ?: type

    override fun powerAssignType(other: ShakeType, scope: ShakeScope): ShakeType =
        type.powerAssignType(other, scope) ?: type

    override fun incrementBeforeType(scope: ShakeScope): ShakeType = type.incrementBeforeType(scope) ?: type
    override fun incrementAfterType(scope: ShakeScope): ShakeType = type.incrementAfterType(scope) ?: type
    override fun decrementBeforeType(scope: ShakeScope): ShakeType? = type.decrementBeforeType(scope) ?: type
    override fun decrementAfterType(scope: ShakeScope): ShakeType? = type.decrementAfterType(scope) ?: type

    override fun toJson(): Map<String, Any?> {
        return mapOf(
            "name" to name,
            "isStatic" to isStatic,
            "isFinal" to isFinal,
            "isAbstract" to isAbstract,
            "isPrivate" to isPrivate,
            "isProtected" to isProtected,
            "isPublic" to isPublic,
            "type" to type.toJson()
        )
    }
}
