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
        get() = "${(clazz?.qualifiedName ?: pkg?.qualifiedName)?.plus(".")}$name"

    val signature: String get() = name

    override fun assignType(other: ShakeType): ShakeType = type.assignType(other) ?: other
    override fun additionAssignType(other: ShakeType): ShakeType = type.additionAssignType(other) ?: type
    override fun subtractionAssignType(other: ShakeType): ShakeType = type.subtractionAssignType(other) ?: type
    override fun multiplicationAssignType(other: ShakeType): ShakeType = type.multiplicationAssignType(other) ?: type
    override fun divisionAssignType(other: ShakeType): ShakeType = type.divisionAssignType(other) ?: type
    override fun modulusAssignType(other: ShakeType): ShakeType = type.modulusAssignType(other) ?: type
    override fun powerAssignType(other: ShakeType): ShakeType = type.powerAssignType(other) ?: type
    override fun incrementBeforeType(): ShakeType = type.incrementBeforeType() ?: type
    override fun incrementAfterType(): ShakeType = type.incrementAfterType() ?: type
    override fun decrementBeforeType(): ShakeType? = type.decrementBeforeType() ?: type
    override fun decrementAfterType(): ShakeType? = type.decrementAfterType() ?: type

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