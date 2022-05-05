package io.github.shakelang.shake.processor.program.immutable

import io.github.shakelang.shake.parser.node.ShakeDeclaration
import io.github.shakelang.shake.processor.program.types.ShakeAssignable
import io.github.shakelang.shake.processor.program.types.ShakeField
import io.github.shakelang.shake.processor.program.types.ShakeType
import io.github.shakelang.shake.processor.program.types.code.values.ShakeValue

open class ImmutableShakeField (
    override val project: ImmutableShakeProject,
    override val pkg: ImmutableShakePackage?,
    override val parentScope: ImmutableShakeScope,
    override val name: String,
    override val isStatic: Boolean,
    override val isFinal: Boolean,
    override val isAbstract: Boolean,
    override val isPrivate: Boolean,
    override val isProtected: Boolean,
    override val isPublic: Boolean,
    override val initialValue: ShakeValue? = null,
): ShakeField, ShakeDeclaration, ShakeAssignable {

    override val qualifiedName: String
        get() = (pkg?.qualifiedName?.plus(".") ?: "") + name

    override val actualValue: ShakeValue?
        get() = TODO("Not yet implemented")

    override val actualType: ShakeType
        get() = TODO("Not yet implemented")

    final override lateinit var type: ShakeType
        private set

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