package io.github.shakelang.shake.processor.program.creation

import io.github.shakelang.shake.processor.program.creation.code.*
import io.github.shakelang.shake.processor.program.creation.code.values.CreationShakeValue

interface CreationShakeAssignable {

    val actualValue: CreationShakeValue?
    val actualType: CreationShakeType
    val type: CreationShakeType

    fun access(scope: CreationShakeScope): CreationShakeValue

    fun assignType(other: CreationShakeType): CreationShakeType?
    fun additionAssignType(other: CreationShakeType): CreationShakeType?
    fun subtractionAssignType(other: CreationShakeType): CreationShakeType?
    fun multiplicationAssignType(other: CreationShakeType): CreationShakeType?
    fun divisionAssignType(other: CreationShakeType): CreationShakeType?
    fun modulusAssignType(other: CreationShakeType): CreationShakeType?
    fun powerAssignType(other: CreationShakeType): CreationShakeType?
    fun incrementBeforeType(): CreationShakeType?
    fun incrementAfterType(): CreationShakeType?
    fun decrementBeforeType(): CreationShakeType?
    fun decrementAfterType(): CreationShakeType?

    fun createAssignment(value: CreationShakeValue): ShakeAssignment {
        val type = assignType(value.type) ?: throw Exception("Cannot assign ${value.type} to ${this.type}")
        return ShakeAssignment(this, value, type)
    }

    fun createAddAssignment(value: CreationShakeValue): ShakeAddAssignment {
        val type = additionAssignType(value.type) ?: throw Exception("Cannot add ${value.type} to ${this.type}")
        return ShakeAddAssignment(this, value, type)
    }

    fun createSubtractAssignment(value: CreationShakeValue): ShakeSubAssignment {
        val type = subtractionAssignType(value.type) ?: throw Exception("Cannot subtract ${value.type} from ${this.type}")
        return ShakeSubAssignment(this, value, type)
    }

    fun createMultiplyAssignment(value: CreationShakeValue): ShakeMulAssignment {
        val type = multiplicationAssignType(value.type) ?: throw Exception("Cannot multiply ${value.type} with ${this.type}")
        return ShakeMulAssignment(this, value, type)
    }

    fun createDivideAssignment(value: CreationShakeValue): ShakeDivAssignment {
        val type = divisionAssignType(value.type) ?: throw Exception("Cannot divide ${value.type} by ${this.type}")
        return ShakeDivAssignment(this, value, type)
    }

    fun createModulusAssignment(value: CreationShakeValue): ShakeModAssignment {
        val type = modulusAssignType(value.type) ?: throw Exception("Cannot modulus ${value.type} by ${this.type}")
        return ShakeModAssignment(this, value, type)
    }

    fun createPowerAssignment(value: CreationShakeValue): ShakePowerAssignment {
        val type = powerAssignType(value.type) ?: throw Exception("Cannot power ${value.type} by ${this.type}")
        return ShakePowerAssignment(this, value, type)
    }

    fun createIncrementBeforeAssignment(): ShakeIncrementBefore {
        val type = incrementBeforeType() ?: throw Exception("Cannot increase ${this.type} before")
        return ShakeIncrementBefore(this, type)
    }

    fun createIncrementAfterAssignment(): ShakeIncrementAfter {
        val type = incrementAfterType() ?: throw Exception("Cannot increase ${this.type} after")
        return ShakeIncrementAfter(this, type)
    }

    fun createDecrementBeforeAssignment(): ShakeDecrementBefore {
        val type = decrementBeforeType() ?: throw Exception("Cannot decrease ${this.type} before")
        return ShakeDecrementBefore(this, type)
    }

    fun createDecrementAfterAssignment(): ShakeDecrementAfter {
        val type = decrementAfterType() ?: throw Exception("Cannot decrease ${this.type} after")
        return ShakeDecrementAfter(this, type)
    }

    fun toJson(): Map<String, Any?>

    companion object {
        fun wrap(v: CreationShakeValue): CreationShakeAssignable {
            return object : CreationShakeAssignable {

                override fun access(scope: CreationShakeScope): CreationShakeValue {
                    return v
                }

                override fun assignType(other: CreationShakeType): CreationShakeType? {
                    return v.type.assignType(other)
                }

                override fun additionAssignType(other: CreationShakeType): CreationShakeType? {
                    return v.type.additionAssignType(other)
                }

                override fun subtractionAssignType(other: CreationShakeType): CreationShakeType? {
                    return v.type.subtractionAssignType(other)
                }

                override fun multiplicationAssignType(other: CreationShakeType): CreationShakeType? {
                    return v.type.multiplicationAssignType(other)
                }

                override fun divisionAssignType(other: CreationShakeType): CreationShakeType? {
                    return v.type.divisionAssignType(other)
                }

                override fun modulusAssignType(other: CreationShakeType): CreationShakeType? {
                    return v.type.modulusAssignType(other)
                }

                override fun powerAssignType(other: CreationShakeType): CreationShakeType? {
                    return v.type.powerAssignType(other)
                }

                override fun incrementBeforeType(): CreationShakeType? {
                    return v.type.incrementBeforeType()
                }

                override fun incrementAfterType(): CreationShakeType? {
                    return v.type.incrementAfterType()
                }

                override fun decrementBeforeType(): CreationShakeType? {
                    return v.type.decrementBeforeType()
                }

                override fun decrementAfterType(): CreationShakeType? {
                    return v.type.decrementAfterType()
                }

                override val actualType: CreationShakeType
                    get() = v.type

                override val actualValue: CreationShakeValue
                    get() = v

                override val type: CreationShakeType
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