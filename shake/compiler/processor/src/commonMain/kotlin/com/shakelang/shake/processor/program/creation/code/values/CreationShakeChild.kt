package com.shakelang.shake.processor.program.creation.code.values

import io.github.shakelang.shake.processor.program.creation.*
import io.github.shakelang.shake.processor.program.types.ShakeType
import io.github.shakelang.shake.processor.program.types.code.ShakeScope
import io.github.shakelang.shake.processor.program.types.code.values.ShakeChild
import io.github.shakelang.shake.processor.program.types.code.values.ShakeChildUsage

class CreationShakeChild(

    override val project: CreationShakeProject,
    override val scope: CreationShakeScope,
    override val parent: CreationShakeValue,
    override val name: String

) : CreationShakeAssignable, ShakeChild {

    override val type: ShakeType = parent.type.childType(name, scope)!!
    override val actualValue: CreationShakeChildUsage get() = CreationShakeChildUsage(project, this)
    override val actualType: ShakeType
        get() = actualValue.type
    override val access: CreationShakeValue get() = CreationShakeChildUsage(project, this)

    override fun assignType(other: ShakeType, scope: ShakeScope): ShakeType =
        type.additionAssignType(other, scope) ?: other

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
    override fun decrementBeforeType(scope: ShakeScope): ShakeType = type.decrementBeforeType(scope) ?: type
    override fun decrementAfterType(scope: ShakeScope): ShakeType = type.decrementAfterType(scope) ?: type

    override fun access(scope: CreationShakeScope): CreationShakeValue {
        return access
    }

    override fun toJson(): Map<String, Any?> {
        return mapOf(
            "type" to "child",
            "name" to name,
            "parent" to parent.toJson()
        )
    }
}

class CreationShakeChildUsage(

    override val project: CreationShakeProject,
    override val used: CreationShakeChild

) : CreationShakeUsage(), ShakeChildUsage {

    override val scope: CreationShakeScope
        get() = used.scope

    override val type: ShakeType
        get() = used.type

    override val declaration: CreationShakeDeclaration
        get() {
            val parentType = used.parent.type
            if (parentType is CreationShakeType.Object) {
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
