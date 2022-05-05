package io.github.shakelang.shake.processor.program.creation.code.values

import io.github.shakelang.shake.processor.program.creation.CreationShakeAssignable
import io.github.shakelang.shake.processor.program.creation.CreationShakeDeclaration
import io.github.shakelang.shake.processor.program.creation.CreationShakeType
import io.github.shakelang.shake.processor.program.creation.code.CreationShakeScope

class CreationShakeChild (

    val scope: CreationShakeScope,
    val parent : CreationShakeValue,
    val name: String,

    ) : CreationShakeAssignable {

    override val type : CreationShakeType = parent.type.childType(name)!!
    override val actualValue: CreationShakeChildUsage get() = CreationShakeChildUsage(this)
    override val actualType: CreationShakeType
        get() = actualValue.type
    val access : CreationShakeValue get() = CreationShakeChildUsage(this)

    override fun assignType(other: CreationShakeType): CreationShakeType = type.additionAssignType(other) ?: other
    override fun additionAssignType(other: CreationShakeType): CreationShakeType = type.additionAssignType(other) ?: type
    override fun subtractionAssignType(other: CreationShakeType): CreationShakeType = type.subtractionAssignType(other) ?: type
    override fun multiplicationAssignType(other: CreationShakeType): CreationShakeType = type.multiplicationAssignType(other) ?: type
    override fun divisionAssignType(other: CreationShakeType): CreationShakeType = type.divisionAssignType(other) ?: type
    override fun modulusAssignType(other: CreationShakeType): CreationShakeType = type.modulusAssignType(other) ?: type
    override fun powerAssignType(other: CreationShakeType): CreationShakeType = type.powerAssignType(other) ?: type
    override fun incrementBeforeType(): CreationShakeType = type.incrementBeforeType() ?: type
    override fun incrementAfterType(): CreationShakeType = type.incrementAfterType() ?: type
    override fun decrementBeforeType(): CreationShakeType = type.decrementBeforeType() ?: type
    override fun decrementAfterType(): CreationShakeType = type.decrementAfterType() ?: type

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

    val used: CreationShakeChild

) : CreationShakeUsage() {

    override val scope: CreationShakeScope
        get() = used.scope

    override val type: CreationShakeType
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