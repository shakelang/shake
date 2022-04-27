package io.github.shakelang.shake.processor.program.mutable.code.values

import io.github.shakelang.shake.processor.program.mutable.ShakeAssignable
import io.github.shakelang.shake.processor.program.mutable.ShakeDeclaration
import io.github.shakelang.shake.processor.program.mutable.ShakeType
import io.github.shakelang.shake.processor.program.mutable.code.ShakeScope

class ShakeChild (

    val scope: ShakeScope,
    val parent : ShakeValue,
    val name: String,

    ) : ShakeAssignable {

    override val type : ShakeType = parent.type.childType(name)!!
    override val actualValue: ShakeChildUsage get() = ShakeChildUsage(this)
    override val actualType: ShakeType
        get() = actualValue.type
    val access : ShakeValue get() = ShakeChildUsage(this)

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

    override fun access(scope: ShakeScope): ShakeValue {
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

class ShakeChildUsage (

    val used: ShakeChild

) : ShakeUsage() {

    override val scope: ShakeScope
        get() = used.scope

    override val type: ShakeType
        get() = used.type

    override val declaration: ShakeDeclaration
        get() {
            val parentType = used.parent.type
            if(parentType is ShakeType.Object) {
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