package io.github.shakelang.shake.processor.program.creation

import io.github.shakelang.shake.processor.program.creation.code.*
import io.github.shakelang.shake.processor.program.creation.code.values.CreationShakeValue
import io.github.shakelang.shake.processor.program.types.ShakeAssignable
import io.github.shakelang.shake.processor.program.types.ShakeType
import io.github.shakelang.shake.processor.program.types.code.ShakeScope

interface CreationShakeAssignable : ShakeAssignable {

    val project: CreationShakeProject
    override val actualValue: CreationShakeValue?
    override val actualType: ShakeType
    override val type: ShakeType

    fun access(scope: CreationShakeScope): CreationShakeValue

    override fun assignType(other: ShakeType, scope: ShakeScope): ShakeType?
    override fun additionAssignType(other: ShakeType, scope: ShakeScope): ShakeType?
    override fun subtractionAssignType(other: ShakeType, scope: ShakeScope): ShakeType?
    override fun multiplicationAssignType(other: ShakeType, scope: ShakeScope): ShakeType?
    override fun divisionAssignType(other: ShakeType, scope: ShakeScope): ShakeType?
    override fun modulusAssignType(other: ShakeType, scope: ShakeScope): ShakeType?
    override fun powerAssignType(other: ShakeType, scope: ShakeScope): ShakeType?
    override fun incrementBeforeType(scope: ShakeScope): ShakeType?
    override fun incrementAfterType(scope: ShakeScope): ShakeType?
    override fun decrementBeforeType(scope: ShakeScope): ShakeType?
    override fun decrementAfterType(scope: ShakeScope): ShakeType?

    fun createAssignment(value: CreationShakeValue, scope: ShakeScope): CreationShakeAssignment {
        val type = assignType(value.type, scope) ?: throw Exception("Cannot assign ${value.type} to ${this.type}")
        return CreationShakeAssignment(project, this, value, type)
    }

    fun createAddAssignment(value: CreationShakeValue, scope: ShakeScope): CreationShakeAddAssignment {
        val type = additionAssignType(value.type, scope)?: throw Exception("Cannot add ${value.type} to ${this.type}")
        return CreationShakeAddAssignment(project, this, value, type)
    }

    fun createSubtractAssignment(value: CreationShakeValue, scope: ShakeScope): CreationShakeSubAssignment {
        val type = subtractionAssignType(value.type, scope) ?: throw Exception("Cannot subtract ${value.type} from ${this.type}")
        return CreationShakeSubAssignment(project, this, value, type)
    }

    fun createMultiplyAssignment(value: CreationShakeValue, scope: ShakeScope): CreationShakeMulAssignment {
        val type = multiplicationAssignType(value.type, scope) ?: throw Exception("Cannot multiply ${value.type} with ${this.type}")
        return CreationShakeMulAssignment(project, this, value, type)
    }

    fun createDivideAssignment(value: CreationShakeValue, scope: ShakeScope): CreationShakeDivAssignment {
        val type = divisionAssignType(value.type, scope) ?: throw Exception("Cannot divide ${value.type} by ${this.type}")
        return CreationShakeDivAssignment(project, this, value, type)
    }

    fun createModulusAssignment(value: CreationShakeValue, scope: ShakeScope): CreationShakeModAssignment {
        val type = modulusAssignType(value.type, scope) ?: throw Exception("Cannot modulus ${value.type} by ${this.type}")
        return CreationShakeModAssignment(project, this, value, type)
    }

    fun createPowerAssignment(value: CreationShakeValue, scope: ShakeScope): CreationShakePowerAssignment {
        val type = powerAssignType(value.type, scope) ?: throw Exception("Cannot power ${value.type} by ${this.type}")
        return CreationShakePowerAssignment(project, this, value, type)
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
        fun wrap(project: CreationShakeProject, v: CreationShakeValue): CreationShakeAssignable {
            return object : CreationShakeAssignable {

                override val project: CreationShakeProject = project

                override fun access(scope: CreationShakeScope): CreationShakeValue {
                    return v
                }

                override fun assignType(other: ShakeType, scope: ShakeScope): ShakeType? {
                    return v.type.assignType(other, scope)
                }

                override fun additionAssignType(other: ShakeType, scope: ShakeScope): ShakeType? {
                    return v.type.additionAssignType(other, scope)
                }

                override fun subtractionAssignType(other: ShakeType, scope: ShakeScope): ShakeType? {
                    return v.type.subtractionAssignType(other, scope)
                }

                override fun multiplicationAssignType(other: ShakeType, scope: ShakeScope): ShakeType? {
                    return v.type.multiplicationAssignType(other, scope)
                }

                override fun divisionAssignType(other: ShakeType, scope: ShakeScope): ShakeType? {
                    return v.type.divisionAssignType(other, scope)
                }

                override fun modulusAssignType(other: ShakeType, scope: ShakeScope): ShakeType? {
                    return v.type.modulusAssignType(other, scope)
                }

                override fun powerAssignType(other: ShakeType, scope: ShakeScope): ShakeType? {
                    return v.type.powerAssignType(other, scope)
                }

                override fun incrementBeforeType(scope: ShakeScope): ShakeType? {
                    return v.type.incrementBeforeType(scope)
                }

                override fun incrementAfterType(scope: ShakeScope): ShakeType? {
                    return v.type.incrementAfterType(scope)
                }

                override fun decrementBeforeType(scope: ShakeScope): ShakeType? {
                    return v.type.decrementBeforeType(scope)
                }

                override fun decrementAfterType(scope: ShakeScope): ShakeType? {
                    return v.type.decrementAfterType(scope)
                }

                override val actualType: ShakeType
                    get() = v.type

                override val actualValue: CreationShakeValue
                    get() = v

                override val type: ShakeType
                    get() = v.type

                override fun toJson(): Map<String, Any?> {
                    return mapOf(
                        "type" to "anonymous-assignable",
                        "value" to v.toJson()
                    )
                }
            }
        }
    }
}