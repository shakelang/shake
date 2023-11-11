package com.shakelang.shake.processor.program.types

import io.github.shakelang.shake.processor.program.types.code.ShakeScope
import io.github.shakelang.shake.processor.program.types.code.values.ShakeValue

interface ShakeAssignable {

    val actualValue: ShakeValue?
    val actualType: ShakeType
    val type: ShakeType

    // fun access(scope: ShakeScope): ShakeValue

    fun assignType(other: ShakeType, scope: ShakeScope): ShakeType?
    fun additionAssignType(other: ShakeType, scope: ShakeScope): ShakeType?
    fun subtractionAssignType(other: ShakeType, scope: ShakeScope): ShakeType?
    fun multiplicationAssignType(other: ShakeType, scope: ShakeScope): ShakeType?
    fun divisionAssignType(other: ShakeType, scope: ShakeScope): ShakeType?
    fun modulusAssignType(other: ShakeType, scope: ShakeScope): ShakeType?
    fun powerAssignType(other: ShakeType, scope: ShakeScope): ShakeType?
    fun incrementBeforeType(scope: ShakeScope): ShakeType?
    fun incrementAfterType(scope: ShakeScope): ShakeType?
    fun decrementBeforeType(scope: ShakeScope): ShakeType?
    fun decrementAfterType(scope: ShakeScope): ShakeType?
}
