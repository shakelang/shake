package io.github.shakelang.shake.processor.program.creation.code.statements

import io.github.shakelang.shake.processor.program.creation.CreationShakeAssignable
import io.github.shakelang.shake.processor.program.creation.CreationShakeDeclaration
import io.github.shakelang.shake.processor.program.creation.CreationShakeType
import io.github.shakelang.shake.processor.program.creation.CreationShakeScope
import io.github.shakelang.shake.processor.program.creation.code.values.CreationShakeValue
import io.github.shakelang.shake.processor.program.creation.code.values.CreationShakeVariableUsage
import io.github.shakelang.shake.processor.program.types.ShakeType
import io.github.shakelang.shake.processor.program.types.code.statements.ShakeVariableDeclaration
import io.github.shakelang.shake.processor.program.types.code.values.ShakeValue

open class CreationShakeVariableDeclaration : CreationShakeDeclaration, CreationShakeAssignable, CreationShakeStatement, ShakeVariableDeclaration {
    override val scope: CreationShakeScope
    final override val name: String
    override val initialValue: CreationShakeValue?
    final override var type: ShakeType
    override var latestValue: CreationShakeValue?
    override var latestType: ShakeType
    override val isFinal: Boolean

    override val qualifiedName: String
        get() = "local $name"

    constructor(scope: CreationShakeScope, name: String, initialValue: CreationShakeValue, isFinal: Boolean) {
        this.scope = scope
        this.name = name
        this.initialValue = initialValue
        this.isFinal = isFinal
        this.type = initialValue.type
        this.latestValue = initialValue
        this.latestType = initialValue.type
    }

    constructor(scope: CreationShakeScope, name: String, type: ShakeType, isFinal: Boolean) {
        this.scope = scope
        this.name = name
        this.initialValue = null
        this.isFinal = isFinal
        this.type = type
        this.latestValue = null
        this.latestType = type
    }

    constructor(scope: CreationShakeScope, name: String, type: ShakeType, initialValue: CreationShakeValue?, isFinal: Boolean) {
        this.scope = scope
        this.name = name
        this.initialValue = initialValue
        this.isFinal = isFinal
        this.type = type
        this.latestValue = initialValue
        this.latestType = type
    }

    override fun valueCompatible(value: ShakeValue): Boolean {
        return type.compatibleTo(value.type)
    }

    override fun use(scope: CreationShakeScope): CreationShakeVariableUsage {
        return CreationShakeVariableUsage(scope, this)
    }

    override val actualValue: CreationShakeValue?
        get() = latestValue ?: initialValue

    override val actualType: ShakeType
        get() = latestType

    override fun assignType(other: ShakeType): ShakeType? {
        val typeAssign = type.assignType(other)
        if (typeAssign != null) {
            latestType = typeAssign
            return typeAssign
        }
        return other
    }

    override fun additionAssignType(other: ShakeType): ShakeType? {
        val typeAssign = type.additionAssignType(other)
        if (typeAssign != null) {
            latestType = typeAssign
            return typeAssign
        }
        return type
    }

    override fun subtractionAssignType(other: ShakeType): ShakeType? {
        val typeAssign = type.subtractionAssignType(other)
        if (typeAssign != null) {
            latestType = typeAssign
            return typeAssign
        }
        return type
    }

    override fun multiplicationAssignType(other: ShakeType): ShakeType? {
        val typeAssign = type.multiplicationAssignType(other)
        if (typeAssign != null) {
            latestType = typeAssign
            return typeAssign
        }
        return type
    }

    override fun divisionAssignType(other: ShakeType): ShakeType? {
        val typeAssign = type.divisionAssignType(other)
        if (typeAssign != null) {
            latestType = typeAssign
            return typeAssign
        }
        return type
    }

    override fun modulusAssignType(other: ShakeType): ShakeType? {
        val typeAssign = type.modulusAssignType(other)
        if (typeAssign != null) {
            latestType = typeAssign
            return typeAssign
        }
        return type
    }

    override fun powerAssignType(other: ShakeType): ShakeType? {
        val typeAssign = type.powerAssignType(other)
        if (typeAssign != null) {
            latestType = typeAssign
            return typeAssign
        }
        return type
    }

    override fun incrementBeforeType(): CreationShakeType? {
        TODO("Not yet implemented")
    }

    override fun incrementAfterType(): CreationShakeType? {
        TODO("Not yet implemented")
    }

    override fun decrementBeforeType(): CreationShakeType? {
        TODO("Not yet implemented")
    }

    override fun decrementAfterType(): CreationShakeType? {
        TODO("Not yet implemented")
    }

    override fun access(scope: CreationShakeScope): CreationShakeValue {
        return CreationShakeVariableUsage(scope, this)
    }

    override fun toJson(): Map<String, Any?> {
        return mapOf(
            "name" to name,
            "type" to type.toJson(),
            "initialValue" to initialValue?.toJson(),
            "latestValue" to latestValue?.toJson(),
            "latestType" to latestType.toJson()
        )
    }

}