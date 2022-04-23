package io.github.shakelang.shake.processor.program.mutable

import io.github.shakelang.shake.processor.program.mutable.code.*
import io.github.shakelang.shake.processor.program.mutable.code.values.ShakeValue

interface ShakeAssignable {

    val actualValue: ShakeValue?
    val actualType: ShakeType
    val type: ShakeType

    fun access(scope: ShakeScope): ShakeValue

    fun assignType(other: ShakeType): ShakeType?
    fun additionAssignType(other: ShakeType): ShakeType?
    fun subtractionAssignType(other: ShakeType): ShakeType?
    fun multiplicationAssignType(other: ShakeType): ShakeType?
    fun divisionAssignType(other: ShakeType): ShakeType?
    fun modulusAssignType(other: ShakeType): ShakeType?
    fun powerAssignType(other: ShakeType): ShakeType?
    fun incrementBeforeType(): ShakeType?
    fun incrementAfterType(): ShakeType?
    fun decrementBeforeType(): ShakeType?
    fun decrementAfterType(): ShakeType?

    fun createAssignment(value: ShakeValue): ShakeAssignment {
        val type = assignType(value.type) ?: throw Exception("Cannot assign ${value.type} to ${this.type}")
        return ShakeAssignment(this, value, type)
    }

    fun createAddAssignment(value: ShakeValue): ShakeAddAssignment {
        val type = additionAssignType(value.type) ?: throw Exception("Cannot add ${value.type} to ${this.type}")
        return ShakeAddAssignment(this, value, type)
    }

    fun createSubtractAssignment(value: ShakeValue): ShakeSubAssignment {
        val type = subtractionAssignType(value.type) ?: throw Exception("Cannot subtract ${value.type} from ${this.type}")
        return ShakeSubAssignment(this, value, type)
    }

    fun createMultiplyAssignment(value: ShakeValue): ShakeMulAssignment {
        val type = multiplicationAssignType(value.type) ?: throw Exception("Cannot multiply ${value.type} with ${this.type}")
        return ShakeMulAssignment(this, value, type)
    }

    fun createDivideAssignment(value: ShakeValue): ShakeDivAssignment {
        val type = divisionAssignType(value.type) ?: throw Exception("Cannot divide ${value.type} by ${this.type}")
        return ShakeDivAssignment(this, value, type)
    }

    fun createModulusAssignment(value: ShakeValue): ShakeModAssignment {
        val type = modulusAssignType(value.type) ?: throw Exception("Cannot modulus ${value.type} by ${this.type}")
        return ShakeModAssignment(this, value, type)
    }

    fun createPowerAssignment(value: ShakeValue): ShakePowerAssignment {
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
        fun wrap(v: ShakeValue): ShakeAssignable {
            return object : ShakeAssignable {

                override fun access(scope: ShakeScope): ShakeValue {
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

                override val actualValue: ShakeValue
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