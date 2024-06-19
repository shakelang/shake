package com.shakelang.shake.processor.program.creation

import com.shakelang.shake.processor.program.creation.code.*
import com.shakelang.shake.processor.program.creation.code.values.CreationShakeValue
import com.shakelang.shake.processor.program.types.ShakeAssignable
import com.shakelang.shake.processor.program.types.ShakeType
import com.shakelang.shake.processor.program.types.code.ShakeScope

interface CreationShakeAssignable : ShakeAssignable {

    val project: CreationShakeProject
    override val actualValue: CreationShakeValue?
    override val actualType: ShakeType
    override val type: ShakeType

    fun access(scope: CreationShakeScope): CreationShakeValue

    override fun assignType(other: ShakeType, scope: ShakeScope): ShakeType? {
        val overload = type.assignType(other, scope)
        if (overload != null) return overload

        // Check compatibility
        if (!other.compatibleTo(type)) return null

        // TODO: Return type or other type? Other may be more specific, but there may be automatic conversions happening
        return type
    }

    override fun additionAssignType(other: ShakeType, scope: ShakeScope): ShakeType? {
        val overload = type.additionAssignType(other, scope)
        if (overload != null) return overload

        val indirect = type.additionType(other, scope) ?: return null
        return assignType(indirect, scope)
    }

    override fun subtractionAssignType(other: ShakeType, scope: ShakeScope): ShakeType? {
        val overload = type.subtractionAssignType(other, scope)
        if (overload != null) return overload

        val indirect = type.subtractionType(other, scope) ?: return null
        return assignType(indirect, scope)
    }

    override fun multiplicationAssignType(other: ShakeType, scope: ShakeScope): ShakeType? {
        val overload = type.multiplicationAssignType(other, scope)
        if (overload != null) return overload

        val indirect = type.multiplicationType(other, scope) ?: return null
        return assignType(indirect, scope)
    }

    override fun divisionAssignType(other: ShakeType, scope: ShakeScope): ShakeType? {
        val overload = type.divisionAssignType(other, scope)
        if (overload != null) return overload

        val indirect = type.divisionType(other, scope) ?: return null
        return assignType(indirect, scope)
    }

    override fun modulusAssignType(other: ShakeType, scope: ShakeScope): ShakeType? {
        val overload = type.modulusAssignType(other, scope)
        if (overload != null) return overload

        val indirect = type.modulusType(other, scope) ?: return null
        return assignType(indirect, scope)
    }

    override fun powerAssignType(other: ShakeType, scope: ShakeScope): ShakeType? {
        val overload = type.powerAssignType(other, scope)
        if (overload != null) return overload

        val indirect = type.powerType(other, scope) ?: return null
        return assignType(indirect, scope)
    }

    override fun incrementBeforeType(scope: ShakeScope): ShakeType? = type.incrementBeforeType(scope)

    override fun incrementAfterType(scope: ShakeScope): ShakeType? = type.incrementAfterType(scope)

    override fun decrementBeforeType(scope: ShakeScope): ShakeType? = type.decrementBeforeType(scope)

    override fun decrementAfterType(scope: ShakeScope): ShakeType? = type.decrementAfterType(scope)

    fun createAssignment(value: CreationShakeValue, scope: ShakeScope): CreationShakeAssignment {
        val type = assignType(value.type, scope) ?: throw Exception("Cannot assign ${value.type} to ${this.type}")
        return CreationShakeAssignment(project, this, value, type)
    }

    fun createAddAssignment(value: CreationShakeValue, scope: CreationShakeScope): CreationShakeValuedStatement {
        val overloadType = type.additionAssignType(value.type, scope)
        if (overloadType != null) return CreationShakeAddAssignment(project, this, value, overloadType)
        // TODO Handle overloads

        val aw = type.additionOperator(value.type, scope) ?: throw Exception("Cannot add-assign ${value.type} to ${this.type}")
        val newValue = CreationShakeInvocation.create(project, aw.overload, listOf(value), this.access(scope))
        return CreationShakeAssignment(project, this, newValue, type)
    }

    fun createSubtractAssignment(value: CreationShakeValue, scope: CreationShakeScope): CreationShakeValuedStatement {
        val overloadType = type.subtractionAssignType(value.type, scope)
        if (overloadType != null) return CreationShakeSubAssignment(project, this, value, overloadType)
        // TODO Handle overloads

        val aw = type.subtractionOperator(value.type, scope) ?: throw Exception("Cannot subtract-assign ${value.type} from ${this.type}")
        val newValue = CreationShakeInvocation.create(project, aw.overload, listOf(value), this.access(scope))
        return CreationShakeAssignment(project, this, newValue, type)
    }

    fun createMultiplyAssignment(value: CreationShakeValue, scope: CreationShakeScope): CreationShakeValuedStatement {
        val overloadType = type.multiplicationAssignType(value.type, scope)
        if (overloadType != null) return CreationShakeMulAssignment(project, this, value, overloadType)
        // TODO Handle overloads

        val aw = type.multiplicationOperator(value.type, scope) ?: throw Exception("Cannot multiply-assign ${value.type} to ${this.type}")
        val newValue = CreationShakeInvocation.create(project, aw.overload, listOf(value), this.access(scope))
        return CreationShakeAssignment(project, this, newValue, type)
    }

    fun createDivideAssignment(value: CreationShakeValue, scope: CreationShakeScope): CreationShakeValuedStatement {
        val overloadType = type.divisionAssignType(value.type, scope)
        if (overloadType != null) return CreationShakeDivAssignment(project, this, value, overloadType)
        // TODO Handle overloads

        val aw = type.divisionOperator(value.type, scope) ?: throw Exception("Cannot divide-assign ${value.type} by ${this.type}")
        val newValue = CreationShakeInvocation.create(project, aw.overload, listOf(value), this.access(scope))
        return CreationShakeAssignment(project, this, newValue, type)
    }

    fun createModulusAssignment(value: CreationShakeValue, scope: CreationShakeScope): CreationShakeValuedStatement {
        val overloadType = type.modulusAssignType(value.type, scope)
        if (overloadType != null) return CreationShakeModAssignment(project, this, value, overloadType)
        // TODO Handle overloads

        val aw = type.modulusOperator(value.type, scope) ?: throw Exception("Cannot modulus-assign ${value.type} by ${this.type}")
        val newValue = CreationShakeInvocation.create(project, aw.overload, listOf(value), this.access(scope))
        return CreationShakeAssignment(project, this, newValue, type)
    }

    fun createPowerAssignment(value: CreationShakeValue, scope: CreationShakeScope): CreationShakeValuedStatement {
        val overloadType = type.powerAssignType(value.type, scope)
        if (overloadType != null) return CreationShakePowerAssignment(project, this, value, overloadType)
        // TODO Handle overloads

        val aw = type.powerOperator(value.type, scope) ?: throw Exception("Cannot power-assign ${value.type} to ${this.type}")
        val newValue = CreationShakeInvocation.create(project, aw.overload, listOf(value), this.access(scope))
        return CreationShakePowerAssignment(project, this, newValue, type)
    }

    fun createIncrementBeforeAssignment(scope: ShakeScope): CreationShakeIncrementBefore {
        val type = incrementBeforeType(scope) ?: throw Exception("Cannot increase ${this.type} before")
        return CreationShakeIncrementBefore(project, this, type)
    }

    fun createIncrementAfterAssignment(scope: ShakeScope): CreationShakeIncrementAfter {
        val type = incrementAfterType(scope) ?: throw Exception("Cannot increase ${this.type} after")
        return CreationShakeIncrementAfter(project, this, type)
    }

    fun createDecrementBeforeAssignment(scope: ShakeScope): CreationShakeDecrementBefore {
        val type = decrementBeforeType(scope) ?: throw Exception("Cannot decrease ${this.type} before")
        return CreationShakeDecrementBefore(project, this, type)
    }

    fun createDecrementAfterAssignment(scope: ShakeScope): CreationShakeDecrementAfter {
        val type = decrementAfterType(scope) ?: throw Exception("Cannot decrease ${this.type} after")
        return CreationShakeDecrementAfter(project, this, type)
    }

    fun toJson(): Map<String, Any?>

    companion object {
        fun wrap(project: CreationShakeProject, v: CreationShakeValue): CreationShakeAssignable = object : CreationShakeAssignable {

            override val project: CreationShakeProject = project

            override fun access(scope: CreationShakeScope): CreationShakeValue = v

            override fun assignType(other: ShakeType, scope: ShakeScope): ShakeType? = v.type.assignType(other, scope)

            override fun additionAssignType(other: ShakeType, scope: ShakeScope): ShakeType? = v.type.additionAssignType(other, scope)

            override fun subtractionAssignType(other: ShakeType, scope: ShakeScope): ShakeType? = v.type.subtractionAssignType(other, scope)

            override fun multiplicationAssignType(other: ShakeType, scope: ShakeScope): ShakeType? = v.type.multiplicationAssignType(other, scope)

            override fun divisionAssignType(other: ShakeType, scope: ShakeScope): ShakeType? = v.type.divisionAssignType(other, scope)

            override fun modulusAssignType(other: ShakeType, scope: ShakeScope): ShakeType? = v.type.modulusAssignType(other, scope)

            override fun powerAssignType(other: ShakeType, scope: ShakeScope): ShakeType? = v.type.powerAssignType(other, scope)

            override fun incrementBeforeType(scope: ShakeScope): ShakeType? = v.type.incrementBeforeType(scope)

            override fun incrementAfterType(scope: ShakeScope): ShakeType? = v.type.incrementAfterType(scope)

            override fun decrementBeforeType(scope: ShakeScope): ShakeType? = v.type.decrementBeforeType(scope)

            override fun decrementAfterType(scope: ShakeScope): ShakeType? = v.type.decrementAfterType(scope)

            override val actualType: ShakeType
                get() = v.type

            override val actualValue: CreationShakeValue
                get() = v

            override val type: ShakeType
                get() = v.type

            override fun toJson(): Map<String, Any?> = mapOf(
                "type" to "anonymous-assignable",
                "value" to v.toJson(),
            )
        }
    }
}
