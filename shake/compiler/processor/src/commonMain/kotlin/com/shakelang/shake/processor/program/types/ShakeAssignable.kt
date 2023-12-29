package com.shakelang.shake.processor.program.types

import com.shakelang.shake.processor.program.types.code.ShakeScope
import com.shakelang.shake.processor.program.types.code.values.ShakeValue

interface ShakeAssignable {

    val actualValue: ShakeValue?
    val actualType: ShakeType
    val type: ShakeType

    // fun access(scope: ShakeScope): ShakeValue

    fun assignType(other: ShakeType, scope: ShakeScope): ShakeType? {
        return type.assignType(other, scope)
    }

    fun additionAssignType(other: ShakeType, scope: ShakeScope): ShakeType? {
        return type.additionAssignType(other, scope)
    }

    fun subtractionAssignType(other: ShakeType, scope: ShakeScope): ShakeType? {
        return type.subtractionAssignType(other, scope)
    }

    fun multiplicationAssignType(other: ShakeType, scope: ShakeScope): ShakeType? {
        return type.multiplicationAssignType(other, scope)
    }

    fun divisionAssignType(other: ShakeType, scope: ShakeScope): ShakeType? {
        return type.divisionAssignType(other, scope)
    }

    fun modulusAssignType(other: ShakeType, scope: ShakeScope): ShakeType? {
        return type.modulusAssignType(other, scope)
    }

    fun powerAssignType(other: ShakeType, scope: ShakeScope): ShakeType? {
        return type.powerAssignType(other, scope)
    }

    fun incrementBeforeType(scope: ShakeScope): ShakeType? {
        return type.incrementBeforeType(scope)
    }

    fun incrementAfterType(scope: ShakeScope): ShakeType? {
        return type.incrementAfterType(scope)
    }

    fun decrementBeforeType(scope: ShakeScope): ShakeType? {
        return type.decrementBeforeType(scope)
    }

    fun decrementAfterType(scope: ShakeScope): ShakeType? {
        return type.decrementAfterType(scope)
    }
}
