package io.github.shakelang.shake.processor.program.creation

import io.github.shakelang.shake.processor.program.creation.code.*
import io.github.shakelang.shake.processor.program.creation.code.values.CreationShakeValue
import io.github.shakelang.shake.processor.program.types.ShakeAssignable
import io.github.shakelang.shake.processor.program.types.ShakeType

interface CreationShakeAssignable : ShakeAssignable {

    val project: CreationShakeProject
    override val actualValue: CreationShakeValue?
    override val actualType: ShakeType
    override val type: ShakeType

    fun access(scope: CreationShakeScope): CreationShakeValue

    override fun assignType(other: ShakeType): ShakeType?
    override fun additionAssignType(other: ShakeType): ShakeType?
    override fun subtractionAssignType(other: ShakeType): ShakeType?
    override fun multiplicationAssignType(other: ShakeType): ShakeType?
    override fun divisionAssignType(other: ShakeType): ShakeType?
    override fun modulusAssignType(other: ShakeType): ShakeType?
    override fun powerAssignType(other: ShakeType): ShakeType?
    override fun incrementBeforeType(): ShakeType?
    override fun incrementAfterType(): ShakeType?
    override fun decrementBeforeType(): ShakeType?
    override fun decrementAfterType(): ShakeType?

    fun createAssignment(value: CreationShakeValue): CreationShakeAssignment {
        val type = assignType(value.type) ?: throw Exception("Cannot assign ${value.type} to ${this.type}")
        return CreationShakeAssignment(project, this, value, type)
    }

    fun createAddAssignment(value: CreationShakeValue): CreationShakeAddAssignment {
        val type = additionAssignType(value.type) ?: throw Exception("Cannot add ${value.type} to ${this.type}")
        return CreationShakeAddAssignment(project, this, value, type)
    }

    fun createSubtractAssignment(value: CreationShakeValue): CreationShakeSubAssignment {
        val type = subtractionAssignType(value.type) ?: throw Exception("Cannot subtract ${value.type} from ${this.type}")
        return CreationShakeSubAssignment(project, this, value, type)
    }

    fun createMultiplyAssignment(value: CreationShakeValue): CreationShakeMulAssignment {
        val type = multiplicationAssignType(value.type) ?: throw Exception("Cannot multiply ${value.type} with ${this.type}")
        return CreationShakeMulAssignment(project, this, value, type)
    }

    fun createDivideAssignment(value: CreationShakeValue): CreationShakeDivAssignment {
        val type = divisionAssignType(value.type) ?: throw Exception("Cannot divide ${value.type} by ${this.type}")
        return CreationShakeDivAssignment(project, this, value, type)
    }

    fun createModulusAssignment(value: CreationShakeValue): CreationShakeModAssignment {
        val type = modulusAssignType(value.type) ?: throw Exception("Cannot modulus ${value.type} by ${this.type}")
        return CreationShakeModAssignment(project, this, value, type)
    }

    fun createPowerAssignment(value: CreationShakeValue): CreationShakePowerAssignment {
        val type = powerAssignType(value.type) ?: throw Exception("Cannot power ${value.type} by ${this.type}")
        return CreationShakePowerAssignment(project, this, value, type)
    }

    fun createIncrementBeforeAssignment(): CreationShakeIncrementBefore {
        val type = incrementBeforeType() ?: throw Exception("Cannot increase ${this.type} before")
        return CreationShakeIncrementBefore(project, this, type)
    }

    fun createIncrementAfterAssignment(): CreationShakeIncrementAfter {
        val type = incrementAfterType() ?: throw Exception("Cannot increase ${this.type} after")
        return CreationShakeIncrementAfter(project, this, type)
    }

    fun createDecrementBeforeAssignment(): CreationShakeDecrementBefore {
        val type = decrementBeforeType() ?: throw Exception("Cannot decrease ${this.type} before")
        return CreationShakeDecrementBefore(project, this, type)
    }

    fun createDecrementAfterAssignment(): CreationShakeDecrementAfter {
        val type = decrementAfterType() ?: throw Exception("Cannot decrease ${this.type} after")
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

                override fun assignType(other: ShakeType): ShakeType? {
                    return v.type.assignType(other)
                }

                override fun additionAssignType(other: ShakeType): ShakeType? {
                    return v.type.additionAssignType(other)
                }

                override fun subtractionAssignType(other: ShakeType): ShakeType? {
                    return v.type.subtractionAssignType(other)
                }

                override fun multiplicationAssignType(other: ShakeType): ShakeType? {
                    return v.type.multiplicationAssignType(other)
                }

                override fun divisionAssignType(other: ShakeType): ShakeType? {
                    return v.type.divisionAssignType(other)
                }

                override fun modulusAssignType(other: ShakeType): ShakeType? {
                    return v.type.modulusAssignType(other)
                }

                override fun powerAssignType(other: ShakeType): ShakeType? {
                    return v.type.powerAssignType(other)
                }

                override fun incrementBeforeType(): ShakeType? {
                    return v.type.incrementBeforeType()
                }

                override fun incrementAfterType(): ShakeType? {
                    return v.type.incrementAfterType()
                }

                override fun decrementBeforeType(): ShakeType? {
                    return v.type.decrementBeforeType()
                }

                override fun decrementAfterType(): ShakeType? {
                    return v.type.decrementAfterType()
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