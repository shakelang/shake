@file:Suppress(
    "FunctionName",
    "MemberVisibilityCanBePrivate",
    "ktlint:standard:function-naming",
    "ktlint:standard:property-naming",
    "unused",
)

package com.shakelang.shake.shakespeare.builder

import com.shakelang.shake.shakespeare.spec.code.*

interface Builder : BuilderContext
interface BuilderContext : ValueCreationContext

interface ValueCreationContext {
    fun string(value: String) = StringLiteralSpec.builder().value(value).build()

    fun byte(value: Byte) = IntLiteralSpec.builder().value(value.toInt()).build()
    fun byte(value: Short) = IntLiteralSpec.builder().value(value.toInt()).build()
    fun byte(value: Int) = IntLiteralSpec.builder().value(value).build()
    fun byte(value: Long) = IntLiteralSpec.builder().value(value.toInt()).build()
    fun byte(value: Float) = IntLiteralSpec.builder().value(value.toInt()).build()
    fun byte(value: Double) = IntLiteralSpec.builder().value(value.toInt()).build()

    fun short(value: Byte) = IntLiteralSpec.builder().value(value.toInt()).build()
    fun short(value: Short) = IntLiteralSpec.builder().value(value.toInt()).build()
    fun short(value: Int) = IntLiteralSpec.builder().value(value).build()
    fun short(value: Long) = IntLiteralSpec.builder().value(value.toInt()).build()
    fun short(value: Float) = IntLiteralSpec.builder().value(value.toInt()).build()
    fun short(value: Double) = IntLiteralSpec.builder().value(value.toInt()).build()

    fun int(value: Byte) = IntLiteralSpec.builder().value(value.toInt()).build()
    fun int(value: Short) = IntLiteralSpec.builder().value(value.toInt()).build()
    fun int(value: Int) = IntLiteralSpec.builder().value(value).build()
    fun int(value: Long) = IntLiteralSpec.builder().value(value.toInt()).build()
    fun int(value: Float) = IntLiteralSpec.builder().value(value.toInt()).build()
    fun int(value: Double) = IntLiteralSpec.builder().value(value.toInt()).build()

    fun long(value: Byte) = IntLiteralSpec.builder().value(value.toInt()).build()
    fun long(value: Short) = IntLiteralSpec.builder().value(value.toInt()).build()
    fun long(value: Int) = IntLiteralSpec.builder().value(value).build()
    fun long(value: Long) = IntLiteralSpec.builder().value(value.toInt()).build()
    fun long(value: Float) = IntLiteralSpec.builder().value(value.toInt()).build()
    fun long(value: Double) = IntLiteralSpec.builder().value(value.toInt()).build()

    fun float(value: Byte) = FloatLiteralSpec.builder().value(value.toFloat()).build()
    fun float(value: Short) = FloatLiteralSpec.builder().value(value.toFloat()).build()
    fun float(value: Int) = FloatLiteralSpec.builder().value(value.toFloat()).build()
    fun float(value: Long) = FloatLiteralSpec.builder().value(value.toFloat()).build()
    fun float(value: Float) = FloatLiteralSpec.builder().value(value).build()
    fun float(value: Double) = FloatLiteralSpec.builder().value(value.toFloat()).build()

    fun double(value: Byte) = FloatLiteralSpec.builder().value(value.toDouble()).build()
    fun double(value: Short) = FloatLiteralSpec.builder().value(value.toDouble()).build()
    fun double(value: Int) = FloatLiteralSpec.builder().value(value.toDouble()).build()
    fun double(value: Long) = FloatLiteralSpec.builder().value(value.toDouble()).build()
    fun double(value: Float) = FloatLiteralSpec.builder().value(value.toDouble()).build()
    fun double(value: Double) = FloatLiteralSpec.builder().value(value).build()

    fun boolean(value: Boolean) = BooleanLiteralSpec.builder().value(value).build()

    fun nullValue() = NullLiteralSpec.INSTANCE

    fun variable(name: String) = VariableReferenceSpec.builder().name(name).build()
    fun variableReference(name: String) = VariableReferenceSpec.builder().name(name).build()

    fun variableAssignment(init: VariableAssignmentBuilder.() -> Unit) = VariableAssignmentBuilder(init).build()
    fun variableAdditionAssignment(init: VariableAdditionSpecBuilder.() -> Unit) = VariableAdditionSpecBuilder(init).build()
    fun variableSubtractionAssignment(init: VariableSubtractionSpecBuilder.() -> Unit) = VariableSubtractionSpecBuilder(init).build()
    fun variableMultiplicationAssignment(init: VariableMultiplicationSpecBuilder.() -> Unit) = VariableMultiplicationSpecBuilder(init).build()
    fun variableDivisionAssignment(init: VariableDivisionSpecBuilder.() -> Unit) = VariableDivisionSpecBuilder(init).build()
    fun variableModuloAssignment(init: VariableModuloSpecBuilder.() -> Unit) = VariableModuloSpecBuilder(init).build()
    fun variableIncrementBefore(init: VariableIncrementBeforeSpecBuilder.() -> Unit) = VariableIncrementBeforeSpecBuilder(init).build()
    fun variableIncrementAfter(init: VariableIncrementAfterSpecBuilder.() -> Unit) = VariableIncrementAfterSpecBuilder(init).build()
    fun variableDecrementBefore(init: VariableDecrementBeforeSpecBuilder.() -> Unit) = VariableDecrementBeforeSpecBuilder(init).build()
    fun variableDecrementAfter(init: VariableDecrementAfterSpecBuilder.() -> Unit) = VariableDecrementAfterSpecBuilder(init).build()
    fun variableIncrementBefore(variable: String) = VariableIncrementBeforeSpecBuilder { name(variable) }.build()
    fun variableIncrementAfter(variable: String) = VariableIncrementAfterSpecBuilder { name(variable) }.build()
    fun variableDecrementBefore(variable: String) = VariableDecrementBeforeSpecBuilder { name(variable) }.build()
    fun variableDecrementAfter(variable: String) = VariableDecrementAfterSpecBuilder { name(variable) }.build()
    fun functionCall(init: FunctionCallBuilder.() -> Unit) = FunctionCallBuilder(init).build()
}
