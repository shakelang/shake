package io.github.shakelang.shake.processor.program.creation.code.values

import io.github.shakelang.shake.processor.program.creation.CreationShakeAssignable
import io.github.shakelang.shake.processor.program.creation.CreationShakeDeclaration
import io.github.shakelang.shake.processor.program.creation.CreationShakeType
import io.github.shakelang.shake.processor.program.creation.CreationShakeScope
import io.github.shakelang.shake.processor.program.types.ShakeType
import io.github.shakelang.shake.processor.program.types.code.values.ShakeChild
import io.github.shakelang.shake.processor.program.types.code.values.ShakeChildUsage

class CreationShakeChild (

    override val scope: CreationShakeScope,
    override val parent : CreationShakeValue,
    override val name: String,

) : CreationShakeAssignable, ShakeChild {

    override val type : ShakeType = parent.type.childType(name)!!
    override val actualValue: CreationShakeChildUsage get() = CreationShakeChildUsage(this)
    override val actualType: ShakeType
        get() = actualValue.type
    override val access : CreationShakeValue get() = CreationShakeChildUsage(this)

    override fun assignType(other: ShakeType): ShakeType = type.additionAssignType(other) ?: other
    override fun additionAssignType(other: ShakeType): ShakeType = type.additionAssignType(other) ?: type
    override fun subtractionAssignType(other: ShakeType): ShakeType = type.subtractionAssignType(other) ?: type
    override fun multiplicationAssignType(other: ShakeType): ShakeType = type.multiplicationAssignType(other) ?: type
    override fun divisionAssignType(other: ShakeType): ShakeType = type.divisionAssignType(other) ?: type
    override fun modulusAssignType(other: ShakeType): ShakeType = type.modulusAssignType(other) ?: type
    override fun powerAssignType(other: ShakeType): ShakeType = type.powerAssignType(other) ?: type
    override fun incrementBeforeType(): ShakeType = type.incrementBeforeType() ?: type
    override fun incrementAfterType(): ShakeType = type.incrementAfterType() ?: type
    override fun decrementBeforeType(): ShakeType = type.decrementBeforeType() ?: type
    override fun decrementAfterType(): ShakeType = type.decrementAfterType() ?: type

    override fun access(scope: CreationShakeScope): CreationShakeValue {
        return access
    }

    override fun toJson(): Map<String, Any?> {
        return mapOf(
            "type" to "child",
            "name" to name,
            "parent" to parent.toJson(),
        )
    }

}

class CreationShakeChildUsage (

    override val used: CreationShakeChild

) : CreationShakeUsage(), ShakeChildUsage {

    override val scope: CreationShakeScope
        get() = used.scope

    override val type: ShakeType
        get() = used.type

    override val declaration: CreationShakeDeclaration
        get() {
            val parentType = used.parent.type
            if(parentType is CreationShakeType.Object) {
                return parentType.clazz.fields.find { it.name == used.name }!! // TODO: check if this is correct
            }
            throw IllegalStateException("Child usage is not in an object")
        }

    override fun toJson(): Map<String, Any?> {
        return mapOf(
            "type" to "child",
            "parent" to used.parent.toJson(),
            "name" to used.name
        )
    }



}