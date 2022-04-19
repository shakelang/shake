package io.github.shakelang.shake.processor.program.code.statements

import io.github.shakelang.shake.processor.program.ShakeAssignable
import io.github.shakelang.shake.processor.program.ShakeDeclaration
import io.github.shakelang.shake.processor.program.ShakeType
import io.github.shakelang.shake.processor.program.code.ShakeScope
import io.github.shakelang.shake.processor.program.code.ShakeStatement
import io.github.shakelang.shake.processor.program.code.ShakeValue
import io.github.shakelang.shake.processor.program.code.values.ShakeVariableUsage

open class ShakeVariableDeclaration : ShakeDeclaration, ShakeAssignable, ShakeStatement {
    val scope: ShakeScope
    final override val name: String
    val initialValue: ShakeValue?
    final override var type: ShakeType
    var latestValue: ShakeValue?
    var latestType: ShakeType

    constructor(scope: ShakeScope, name: String, initialValue: ShakeValue) {
        this.scope = scope
        this.name = name
        this.initialValue = initialValue
        this.type = initialValue.type
        this.latestValue = initialValue
        this.latestType = initialValue.type
    }

    constructor(scope: ShakeScope, name: String, type: ShakeType) {
        this.scope = scope
        this.name = name
        this.initialValue = null
        this.type = type
        this.latestValue = null
        this.latestType = type
    }

    constructor(scope: ShakeScope, name: String, type: ShakeType, initialValue: ShakeValue?) {
        this.scope = scope
        this.name = name
        this.initialValue = initialValue
        this.type = type
        this.latestValue = initialValue
        this.latestType = type
    }

    fun valueCompatible(value: ShakeValue): Boolean {
        return type.compatibleTo(value.type)
    }

    override fun use(scope: ShakeScope): ShakeVariableUsage {
        return ShakeVariableUsage(scope, this)
    }

    override val actualValue: ShakeValue?
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

    override fun incrementBeforeType(): ShakeType? {
        TODO("Not yet implemented")
    }

    override fun incrementAfterType(): ShakeType? {
        TODO("Not yet implemented")
    }

    override fun decrementBeforeType(): ShakeType? {
        TODO("Not yet implemented")
    }

    override fun decrementAfterType(): ShakeType? {
        TODO("Not yet implemented")
    }

}