package com.shakelang.shake.processor.program.types

import com.shakelang.shake.processor.ShakeSelect
import com.shakelang.shake.processor.program.types.code.ShakeScope

interface ShakeType {

    val name: String
    val qualifiedName: String

    fun assignType(other: ShakeType, scope: ShakeScope): ShakeType? = assignOverload(other, scope)?.returnType
    fun additionAssignType(other: ShakeType, scope: ShakeScope): ShakeType? = assignOverload(other, scope)?.returnType
    fun subtractionAssignType(other: ShakeType, scope: ShakeScope): ShakeType? =
        assignOverload(other, scope)?.returnType

    fun multiplicationAssignType(other: ShakeType, scope: ShakeScope): ShakeType? =
        assignOverload(other, scope)?.returnType

    fun divisionAssignType(other: ShakeType, scope: ShakeScope): ShakeType? = assignOverload(other, scope)?.returnType
    fun modulusAssignType(other: ShakeType, scope: ShakeScope): ShakeType? = assignOverload(other, scope)?.returnType
    fun powerAssignType(other: ShakeType, scope: ShakeScope): ShakeType? = assignOverload(other, scope)?.returnType
    fun incrementBeforeType(scope: ShakeScope): ShakeType? = incrementBeforeOverload(scope)?.returnType
    fun incrementAfterType(scope: ShakeScope): ShakeType? = incrementAfterOverload(scope)?.returnType
    fun decrementBeforeType(scope: ShakeScope): ShakeType? = decrementBeforeOverload(scope)?.returnType
    fun decrementAfterType(scope: ShakeScope): ShakeType? = decrementAfterOverload(scope)?.returnType
    fun additionType(other: ShakeType, scope: ShakeScope): ShakeType? = additionOverload(other, scope)?.returnType
    fun subtractionType(other: ShakeType, scope: ShakeScope): ShakeType? = subtractionOverload(other, scope)?.returnType
    fun multiplicationType(other: ShakeType, scope: ShakeScope): ShakeType? =
        multiplicationOverload(other, scope)?.returnType

    fun divisionType(other: ShakeType, scope: ShakeScope): ShakeType? = divisionOverload(other, scope)?.returnType
    fun modulusType(other: ShakeType, scope: ShakeScope): ShakeType? = modulusOverload(other, scope)?.returnType
    fun powerType(other: ShakeType, scope: ShakeScope): ShakeType? = powerOverload(other, scope)?.returnType
    fun equalsType(other: ShakeType, scope: ShakeScope): ShakeType? = equalsOverload(other, scope)?.returnType
    fun notEqualsType(other: ShakeType, scope: ShakeScope): ShakeType? = notEqualsOverload(other, scope)?.returnType
    fun greaterThanType(other: ShakeType, scope: ShakeScope): ShakeType? = greaterThanOverload(other, scope)?.returnType
    fun greaterThanOrEqualType(other: ShakeType, scope: ShakeScope): ShakeType? =
        greaterThanOrEqualOverload(other, scope)?.returnType

    fun lessThanType(other: ShakeType, scope: ShakeScope): ShakeType? = lessThanOverload(other, scope)?.returnType
    fun lessThanOrEqualType(other: ShakeType, scope: ShakeScope): ShakeType? =
        lessThanOrEqualOverload(other, scope)?.returnType

    fun andType(other: ShakeType, scope: ShakeScope): ShakeType? = andOverload(other, scope)?.returnType
    fun orType(other: ShakeType, scope: ShakeScope): ShakeType? = orOverload(other, scope)?.returnType
    fun notType(scope: ShakeScope): ShakeType? = notOverload(scope)?.returnType
    fun childType(name: String, scope: ShakeScope): ShakeType? = null
    fun childFunctions(name: String, scope: ShakeScope): List<ShakeMethod>? =
        scope.getFunctions(name).filter { it.expanding == this }

    fun childInvokable(name: String, scope: ShakeScope): List<ShakeMethod>? = childFunctions(name, scope)

    fun assignOverloads(scope: ShakeScope): List<ShakeMethod> =
        scope.getFunctions("assign").filter { it.expanding == this && it.isOperator }

    fun additionAssignOverloads(scope: ShakeScope): List<ShakeMethod> =
        scope.getFunctions("additionAssign").filter { it.expanding == this && it.isOperator }

    fun subtractionAssignOverloads(scope: ShakeScope): List<ShakeMethod> =
        scope.getFunctions("subtractionAssign").filter { it.expanding == this && it.isOperator }

    fun multiplicationAssignOverloads(scope: ShakeScope): List<ShakeMethod> =
        scope.getFunctions("multiplicationAssign").filter { it.expanding == this && it.isOperator }

    fun divisionAssignOverloads(scope: ShakeScope): List<ShakeMethod> =
        scope.getFunctions("divisionAssign").filter { it.expanding == this && it.isOperator }

    fun modulusAssignOverloads(scope: ShakeScope): List<ShakeMethod> =
        scope.getFunctions("modulusAssign").filter { it.expanding == this && it.isOperator }

    fun powerAssignOverloads(scope: ShakeScope): List<ShakeMethod> =
        scope.getFunctions("powerAssign").filter { it.expanding == this && it.isOperator }

    fun incrementBeforeOverloads(scope: ShakeScope): List<ShakeMethod> =
        scope.getFunctions("incrementBefore").filter { it.expanding == this && it.isOperator }

    fun incrementAfterOverloads(scope: ShakeScope): List<ShakeMethod> =
        scope.getFunctions("incrementAfter").filter { it.expanding == this && it.isOperator }

    fun decrementBeforeOverloads(scope: ShakeScope): List<ShakeMethod> =
        scope.getFunctions("decrementBefore").filter { it.expanding == this && it.isOperator }

    fun decrementAfterOverloads(scope: ShakeScope): List<ShakeMethod> =
        scope.getFunctions("decrementAfter").filter { it.expanding == this && it.isOperator }

    fun additionOverloads(scope: ShakeScope): List<ShakeMethod> =
        scope.getFunctions("plus").filter { it.expanding == this && it.isOperator }

    fun subtractionOverloads(scope: ShakeScope): List<ShakeMethod> =
        scope.getFunctions("minus").filter { it.expanding == this && it.isOperator }

    fun multiplicationOverloads(scope: ShakeScope): List<ShakeMethod> =
        scope.getFunctions("times").filter { it.expanding == this && it.isOperator }

    fun divisionOverloads(scope: ShakeScope): List<ShakeMethod> =
        scope.getFunctions("div").filter { it.expanding == this && it.isOperator }

    fun modulusOverloads(scope: ShakeScope): List<ShakeMethod> =
        scope.getFunctions("mod").filter { it.expanding == this && it.isOperator }

    fun powerOverloads(scope: ShakeScope): List<ShakeMethod> =
        scope.getFunctions("pow").filter { it.expanding == this && it.isOperator }

    fun equalsOverloads(scope: ShakeScope): List<ShakeMethod> =
        scope.getFunctions("equals").filter { it.expanding == this && it.isOperator }

    fun notEqualsOverloads(scope: ShakeScope): List<ShakeMethod> =
        scope.getFunctions("notEquals").filter { it.expanding == this && it.isOperator }

    fun greaterThanOverloads(scope: ShakeScope): List<ShakeMethod> =
        scope.getFunctions("greaterThan").filter { it.expanding == this && it.isOperator }

    fun greaterThanOrEqualOverloads(scope: ShakeScope): List<ShakeMethod> =
        scope.getFunctions("greaterThanOrEqual").filter { it.expanding == this && it.isOperator }

    fun lessThanOverloads(scope: ShakeScope): List<ShakeMethod> =
        scope.getFunctions("lessThan").filter { it.expanding == this && it.isOperator }

    fun lessThanOrEqualOverloads(scope: ShakeScope): List<ShakeMethod> =
        scope.getFunctions("lessThanOrEqual").filter { it.expanding == this && it.isOperator }

    fun andOverloads(scope: ShakeScope): List<ShakeMethod> =
        scope.getFunctions("and").filter { it.expanding == this && it.isOperator }

    fun orOverloads(scope: ShakeScope): List<ShakeMethod> =
        scope.getFunctions("or").filter { it.expanding == this && it.isOperator }

    fun notOverloads(scope: ShakeScope): List<ShakeMethod> =
        scope.getFunctions("not").filter { it.expanding == this && it.isOperator }

    fun assignOverload(other: ShakeType, scope: ShakeScope): ShakeMethod? =
        ShakeSelect.selectFunction(assignOverloads(scope), listOf(other))

    fun additionAssignOverload(other: ShakeType, scope: ShakeScope): ShakeMethod? =
        ShakeSelect.selectFunction(additionAssignOverloads(scope), listOf(other))

    fun subtractionAssignOverload(other: ShakeType, scope: ShakeScope): ShakeMethod? =
        ShakeSelect.selectFunction(subtractionAssignOverloads(scope), listOf(other))

    fun multiplicationAssignOverload(other: ShakeType, scope: ShakeScope): ShakeMethod? =
        ShakeSelect.selectFunction(multiplicationAssignOverloads(scope), listOf(other))

    fun divisionAssignOverload(other: ShakeType, scope: ShakeScope): ShakeMethod? =
        ShakeSelect.selectFunction(divisionAssignOverloads(scope), listOf(other))

    fun modulusAssignOverload(other: ShakeType, scope: ShakeScope): ShakeMethod? =
        ShakeSelect.selectFunction(modulusAssignOverloads(scope), listOf(other))

    fun powerAssignOverload(other: ShakeType, scope: ShakeScope): ShakeMethod? =
        ShakeSelect.selectFunction(powerAssignOverloads(scope), listOf(other))

    fun incrementBeforeOverload(scope: ShakeScope): ShakeMethod? =
        ShakeSelect.selectFunction(incrementBeforeOverloads(scope), emptyList())

    fun incrementAfterOverload(scope: ShakeScope): ShakeMethod? =
        ShakeSelect.selectFunction(incrementAfterOverloads(scope), emptyList())

    fun decrementBeforeOverload(scope: ShakeScope): ShakeMethod? =
        ShakeSelect.selectFunction(decrementBeforeOverloads(scope), emptyList())

    fun decrementAfterOverload(scope: ShakeScope): ShakeMethod? =
        ShakeSelect.selectFunction(decrementAfterOverloads(scope), emptyList())

    fun additionOverload(other: ShakeType, scope: ShakeScope): ShakeMethod? =
        ShakeSelect.selectFunction(additionOverloads(scope), listOf(other))

    fun subtractionOverload(other: ShakeType, scope: ShakeScope): ShakeMethod? =
        ShakeSelect.selectFunction(subtractionOverloads(scope), listOf(other))

    fun multiplicationOverload(other: ShakeType, scope: ShakeScope): ShakeMethod? =
        ShakeSelect.selectFunction(multiplicationOverloads(scope), listOf(other))

    fun divisionOverload(other: ShakeType, scope: ShakeScope): ShakeMethod? =
        ShakeSelect.selectFunction(divisionOverloads(scope), listOf(other))

    fun modulusOverload(other: ShakeType, scope: ShakeScope): ShakeMethod? =
        ShakeSelect.selectFunction(modulusOverloads(scope), listOf(other))

    fun powerOverload(other: ShakeType, scope: ShakeScope): ShakeMethod? =
        ShakeSelect.selectFunction(powerOverloads(scope), listOf(other))

    fun equalsOverload(other: ShakeType, scope: ShakeScope): ShakeMethod? =
        ShakeSelect.selectFunction(equalsOverloads(scope), listOf(other))

    fun notEqualsOverload(other: ShakeType, scope: ShakeScope): ShakeMethod? =
        ShakeSelect.selectFunction(notEqualsOverloads(scope), listOf(other))

    fun greaterThanOverload(other: ShakeType, scope: ShakeScope): ShakeMethod? =
        ShakeSelect.selectFunction(greaterThanOverloads(scope), listOf(other))

    fun greaterThanOrEqualOverload(other: ShakeType, scope: ShakeScope): ShakeMethod? =
        ShakeSelect.selectFunction(greaterThanOrEqualOverloads(scope), listOf(other))

    fun lessThanOverload(other: ShakeType, scope: ShakeScope): ShakeMethod? =
        ShakeSelect.selectFunction(lessThanOverloads(scope), listOf(other))

    fun lessThanOrEqualOverload(other: ShakeType, scope: ShakeScope): ShakeMethod? =
        ShakeSelect.selectFunction(lessThanOrEqualOverloads(scope), listOf(other))

    fun andOverload(other: ShakeType, scope: ShakeScope): ShakeMethod? =
        ShakeSelect.selectFunction(andOverloads(scope), listOf(other))

    fun orOverload(other: ShakeType, scope: ShakeScope): ShakeMethod? =
        ShakeSelect.selectFunction(orOverloads(scope), listOf(other))

    fun notOverload(scope: ShakeScope): ShakeMethod? = ShakeSelect.selectFunction(notOverloads(scope), emptyList())

    fun assignOperator(other: ShakeType, scope: ShakeScope): ShakeOperatorRequestResult {
        val method = assignOverload(other, scope)
        if (method != null) return ShakeOperatorRequestResult(method.returnType, method)
        return ShakeOperatorRequestResult(null, null)
    }

    fun additionAssignOperator(other: ShakeType, scope: ShakeScope): ShakeOperatorRequestResult {
        val method = additionAssignOverload(other, scope)
        if (method != null) return ShakeOperatorRequestResult(method.returnType, method)
        return ShakeOperatorRequestResult(null, null)
    }

    fun subtractionAssignOperator(other: ShakeType, scope: ShakeScope): ShakeOperatorRequestResult {
        val method = subtractionAssignOverload(other, scope)
        if (method != null) return ShakeOperatorRequestResult(method.returnType, method)
        return ShakeOperatorRequestResult(null, null)
    }

    fun multiplicationAssignOperator(other: ShakeType, scope: ShakeScope): ShakeOperatorRequestResult {
        val method = multiplicationAssignOverload(other, scope)
        if (method != null) return ShakeOperatorRequestResult(method.returnType, method)
        return ShakeOperatorRequestResult(null, null)
    }

    fun divisionAssignOperator(other: ShakeType, scope: ShakeScope): ShakeOperatorRequestResult {
        val method = divisionAssignOverload(other, scope)
        if (method != null) return ShakeOperatorRequestResult(method.returnType, method)
        return ShakeOperatorRequestResult(null, null)
    }

    fun modulusAssignOperator(other: ShakeType, scope: ShakeScope): ShakeOperatorRequestResult {
        val method = modulusAssignOverload(other, scope)
        if (method != null) return ShakeOperatorRequestResult(method.returnType, method)
        return ShakeOperatorRequestResult(null, null)
    }

    fun powerAssignOperator(other: ShakeType, scope: ShakeScope): ShakeOperatorRequestResult {
        val method = powerAssignOverload(other, scope)
        if (method != null) return ShakeOperatorRequestResult(method.returnType, method)
        return ShakeOperatorRequestResult(null, null)
    }

    fun incrementBeforeOperator(scope: ShakeScope): ShakeOperatorRequestResult {
        val method = incrementBeforeOverload(scope)
        if (method != null) return ShakeOperatorRequestResult(method.returnType, method)
        return ShakeOperatorRequestResult(null, null)
    }

    fun incrementAfterOperator(scope: ShakeScope): ShakeOperatorRequestResult {
        val method = incrementAfterOverload(scope)
        if (method != null) return ShakeOperatorRequestResult(method.returnType, method)
        return ShakeOperatorRequestResult(null, null)
    }

    fun decrementBeforeOperator(scope: ShakeScope): ShakeOperatorRequestResult {
        val method = decrementBeforeOverload(scope)
        if (method != null) return ShakeOperatorRequestResult(method.returnType, method)
        return ShakeOperatorRequestResult(null, null)
    }

    fun decrementAfterOperator(scope: ShakeScope): ShakeOperatorRequestResult {
        val method = decrementAfterOverload(scope)
        if (method != null) return ShakeOperatorRequestResult(method.returnType, method)
        return ShakeOperatorRequestResult(null, null)
    }

    fun additionOperator(other: ShakeType, scope: ShakeScope): ShakeOperatorRequestResult {
        val method = additionOverload(other, scope)
        if (method != null) return ShakeOperatorRequestResult(method.returnType, method)
        return ShakeOperatorRequestResult(null, null)
    }

    fun subtractionOperator(other: ShakeType, scope: ShakeScope): ShakeOperatorRequestResult {
        val method = subtractionOverload(other, scope)
        if (method != null) return ShakeOperatorRequestResult(method.returnType, method)
        return ShakeOperatorRequestResult(null, null)
    }

    fun multiplicationOperator(other: ShakeType, scope: ShakeScope): ShakeOperatorRequestResult {
        val method = multiplicationOverload(other, scope)
        if (method != null) return ShakeOperatorRequestResult(method.returnType, method)
        return ShakeOperatorRequestResult(null, null)
    }

    fun divisionOperator(other: ShakeType, scope: ShakeScope): ShakeOperatorRequestResult {
        val method = divisionOverload(other, scope)
        if (method != null) return ShakeOperatorRequestResult(method.returnType, method)
        return ShakeOperatorRequestResult(null, null)
    }

    fun modulusOperator(other: ShakeType, scope: ShakeScope): ShakeOperatorRequestResult {
        val method = modulusOverload(other, scope)
        if (method != null) return ShakeOperatorRequestResult(method.returnType, method)
        return ShakeOperatorRequestResult(null, null)
    }

    fun powerOperator(other: ShakeType, scope: ShakeScope): ShakeOperatorRequestResult {
        val method = powerOverload(other, scope)
        if (method != null) return ShakeOperatorRequestResult(method.returnType, method)
        return ShakeOperatorRequestResult(null, null)
    }

    fun greaterThanOperator(other: ShakeType, scope: ShakeScope): ShakeOperatorRequestResult {
        val method = greaterThanOverload(other, scope)
        if (method != null) return ShakeOperatorRequestResult(method.returnType, method)
        return ShakeOperatorRequestResult(null, null)
    }

    fun greaterThanOrEqualOperator(other: ShakeType, scope: ShakeScope): ShakeOperatorRequestResult {
        val method = greaterThanOrEqualOverload(other, scope)
        if (method != null) return ShakeOperatorRequestResult(method.returnType, method)
        return ShakeOperatorRequestResult(null, null)
    }

    fun lessThanOperator(other: ShakeType, scope: ShakeScope): ShakeOperatorRequestResult {
        val method = lessThanOverload(other, scope)
        if (method != null) return ShakeOperatorRequestResult(method.returnType, method)
        return ShakeOperatorRequestResult(null, null)
    }

    fun lessThanOrEqualOperator(other: ShakeType, scope: ShakeScope): ShakeOperatorRequestResult {
        val method = lessThanOrEqualOverload(other, scope)
        if (method != null) return ShakeOperatorRequestResult(method.returnType, method)
        return ShakeOperatorRequestResult(null, null)
    }

    fun andOperator(other: ShakeType, scope: ShakeScope): ShakeOperatorRequestResult {
        val method = andOverload(other, scope)
        if (method != null) return ShakeOperatorRequestResult(method.returnType, method)
        return ShakeOperatorRequestResult(null, null)
    }

    fun orOperator(other: ShakeType, scope: ShakeScope): ShakeOperatorRequestResult {
        val method = orOverload(other, scope)
        if (method != null) return ShakeOperatorRequestResult(method.returnType, method)
        return ShakeOperatorRequestResult(null, null)
    }

    fun notOperator(scope: ShakeScope): ShakeOperatorRequestResult {
        val method = notOverload(scope)
        if (method != null) return ShakeOperatorRequestResult(method.returnType, method)
        return ShakeOperatorRequestResult(null, null)
    }

    val kind: Kind

    fun castableTo(other: ShakeType): Boolean
    fun compatibleTo(other: ShakeType): Boolean
    fun compatibilityDistance(other: ShakeType): Int

    fun toJson(): Map<String, Any?>

    enum class Kind {
        PRIMITIVE,
        OBJECT,
        ARRAY,
        LAMBDA
    }

    enum class PrimitiveType {
        BOOLEAN,
        BYTE,
        CHAR,
        SHORT,
        INT,
        LONG,
        FLOAT,
        DOUBLE,
        UNSIGNED_BYTE,
        UNSIGNED_SHORT,
        UNSIGNED_INT,
        UNSIGNED_LONG,
        VOID,
        DYNAMIC
    }

    interface Primitive : ShakeType {

        val type: PrimitiveType
        override val kind: Kind get() = Kind.PRIMITIVE

        override fun castableTo(other: ShakeType): Boolean =
            other is Primitive &&
                (
                    other.type == PrimitiveType.BYTE ||
                        other.type == PrimitiveType.SHORT ||
                        other.type == PrimitiveType.INT ||
                        other.type == PrimitiveType.LONG ||
                        other.type == PrimitiveType.FLOAT ||
                        other.type == PrimitiveType.DOUBLE ||
                        other.type == PrimitiveType.UNSIGNED_BYTE ||
                        other.type == PrimitiveType.UNSIGNED_SHORT ||
                        other.type == PrimitiveType.UNSIGNED_INT ||
                        other.type == PrimitiveType.UNSIGNED_LONG ||
                        other.type == PrimitiveType.CHAR
                    )
    }

    interface Object : ShakeType {
        val clazz: ShakeClass
        override val kind: Kind get() = Kind.OBJECT

        override fun assignOverloads(scope: ShakeScope): List<ShakeMethod> =
            clazz.methods.filter { it.name == "plus" && it.isOperator } + super.assignOverloads(scope)

        override fun additionAssignOverloads(scope: ShakeScope): List<ShakeMethod> =
            clazz.methods.filter { it.name == "plus" && it.isOperator } + super.additionAssignOverloads(scope)

        override fun subtractionAssignOverloads(scope: ShakeScope): List<ShakeMethod> =
            clazz.methods.filter { it.name == "minus" && it.isOperator } + super.subtractionAssignOverloads(scope)

        override fun multiplicationAssignOverloads(scope: ShakeScope): List<ShakeMethod> =
            clazz.methods.filter { it.name == "times" && it.isOperator } + super.multiplicationAssignOverloads(scope)

        override fun divisionAssignOverloads(scope: ShakeScope): List<ShakeMethod> =
            clazz.methods.filter { it.name == "div" && it.isOperator } + super.divisionAssignOverloads(scope)

        override fun modulusAssignOverloads(scope: ShakeScope): List<ShakeMethod> =
            clazz.methods.filter { it.name == "mod" && it.isOperator } + super.modulusAssignOverloads(scope)

        override fun powerAssignOverloads(scope: ShakeScope): List<ShakeMethod> =
            clazz.methods.filter { it.name == "pow" && it.isOperator } + super.powerAssignOverloads(scope)

        override fun incrementBeforeOverloads(scope: ShakeScope): List<ShakeMethod> =
            clazz.methods.filter { it.name == "inc" && it.isOperator } + super.incrementBeforeOverloads(scope)

        override fun incrementAfterOverloads(scope: ShakeScope): List<ShakeMethod> =
            clazz.methods.filter { it.name == "inc" && it.isOperator } + super.incrementAfterOverloads(scope)

        override fun decrementBeforeOverloads(scope: ShakeScope): List<ShakeMethod> =
            clazz.methods.filter { it.name == "dec" && it.isOperator } + super.decrementBeforeOverloads(scope)

        override fun decrementAfterOverloads(scope: ShakeScope): List<ShakeMethod> =
            clazz.methods.filter { it.name == "dec" && it.isOperator } + super.decrementAfterOverloads(scope)

        override fun additionOverloads(scope: ShakeScope): List<ShakeMethod> =
            clazz.methods.filter { it.name == "plus" && it.isOperator } + super.additionOverloads(scope)

        override fun subtractionOverloads(scope: ShakeScope): List<ShakeMethod> =
            clazz.methods.filter { it.name == "minus" && it.isOperator } + super.subtractionOverloads(scope)

        override fun multiplicationOverloads(scope: ShakeScope): List<ShakeMethod> =
            clazz.methods.filter { it.name == "times" && it.isOperator } + super.multiplicationOverloads(scope)

        override fun divisionOverloads(scope: ShakeScope): List<ShakeMethod> =
            clazz.methods.filter { it.name == "div" && it.isOperator } + super.divisionOverloads(scope)

        override fun modulusOverloads(scope: ShakeScope): List<ShakeMethod> =
            clazz.methods.filter { it.name == "mod" && it.isOperator } + super.modulusOverloads(scope)

        override fun powerOverloads(scope: ShakeScope): List<ShakeMethod> =
            clazz.methods.filter { it.name == "pow" && it.isOperator } + super.powerOverloads(scope)

        override fun equalsOverloads(scope: ShakeScope): List<ShakeMethod> =
            clazz.methods.filter { it.name == "equals" && it.isOperator } + super.equalsOverloads(scope)

        override fun notEqualsOverloads(scope: ShakeScope): List<ShakeMethod> =
            clazz.methods.filter { it.name == "notEquals" && it.isOperator } + super.notEqualsOverloads(scope)

        override fun greaterThanOverloads(scope: ShakeScope): List<ShakeMethod> =
            clazz.methods.filter { it.name == "greaterThan" && it.isOperator } + super.greaterThanOverloads(scope)

        override fun greaterThanOrEqualOverloads(scope: ShakeScope): List<ShakeMethod> =
            clazz.methods.filter { it.name == "greaterThanOrEqual" && it.isOperator } + super.greaterThanOrEqualOverloads(
                scope
            )

        override fun lessThanOverloads(scope: ShakeScope): List<ShakeMethod> =
            clazz.methods.filter { it.name == "lessThan" && it.isOperator } + super.lessThanOverloads(scope)

        override fun lessThanOrEqualOverloads(scope: ShakeScope): List<ShakeMethod> =
            clazz.methods.filter { it.name == "lessThanOrEqual" && it.isOperator } + super.lessThanOrEqualOverloads(
                scope
            )

        override fun andOverloads(scope: ShakeScope): List<ShakeMethod> =
            clazz.methods.filter { it.name == "and" && it.isOperator } + super.andOverloads(scope)

        override fun orOverloads(scope: ShakeScope): List<ShakeMethod> =
            clazz.methods.filter { it.name == "or" && it.isOperator } + super.orOverloads(scope)

        override fun notOverloads(scope: ShakeScope): List<ShakeMethod> =
            clazz.methods.filter { it.name == "not" && it.isOperator } + super.notOverloads(scope)

        override fun childType(name: String, scope: ShakeScope): ShakeType? =
            clazz.fields.find { it.name == name }?.type

        override fun childFunctions(name: String, scope: ShakeScope): List<ShakeMethod> {
            return clazz.methods.filter { it.name == name } + scope.getFunctions(name).filter { it.expanding == this }
        }

        override fun castableTo(other: ShakeType): Boolean {
            return other is Object && other.clazz.compatibleTo(clazz)
        }

        override fun compatibleTo(other: ShakeType): Boolean {
            return other is Object && clazz.compatibleTo(other.clazz)
        }

        override fun compatibilityDistance(other: ShakeType): Int {
            return if (other is Object) clazz.compatibilityDistance(other.clazz) else -1
        }

        override fun toJson(): Map<String, Any?> {
            return mapOf("type" to "object", "class" to clazz.qualifiedName)
        }

        override val qualifiedName: String
            get() = "L${clazz.qualifiedName}"
    }

    interface Array : ShakeType {
        val elementType: ShakeType
        override fun additionType(other: ShakeType, scope: ShakeScope): ShakeType? = null
        override fun subtractionType(other: ShakeType, scope: ShakeScope): ShakeType? = null
        override fun multiplicationType(other: ShakeType, scope: ShakeScope): ShakeType? = null
        override fun divisionType(other: ShakeType, scope: ShakeScope): ShakeType? = null
        override fun modulusType(other: ShakeType, scope: ShakeScope): ShakeType? = null
        override fun powerType(other: ShakeType, scope: ShakeScope): ShakeType? = null
        override fun equalsType(other: ShakeType, scope: ShakeScope): ShakeType? = null
        override fun notEqualsType(other: ShakeType, scope: ShakeScope): ShakeType? = null
        override fun greaterThanType(other: ShakeType, scope: ShakeScope): ShakeType? = null
        override fun greaterThanOrEqualType(other: ShakeType, scope: ShakeScope): ShakeType? = null
        override fun lessThanType(other: ShakeType, scope: ShakeScope): ShakeType? = null
        override fun lessThanOrEqualType(other: ShakeType, scope: ShakeScope): ShakeType? = null
        override fun andType(other: ShakeType, scope: ShakeScope): ShakeType? = null
        override fun orType(other: ShakeType, scope: ShakeScope): ShakeType? = null
        override fun notType(scope: ShakeScope): ShakeType? = null

        override val kind: Kind
            get() = Kind.ARRAY

        override fun castableTo(other: ShakeType): Boolean {
            return other is Array && other.elementType.castableTo(elementType)
        }

        override fun compatibleTo(other: ShakeType): Boolean {
            return other is Array && elementType.compatibleTo(other.elementType)
        }

        override fun compatibilityDistance(other: ShakeType): Int {
            return if (other is Array) elementType.compatibilityDistance(other.elementType) else -1
        }

        override fun toJson(): Map<String, Any?> {
            return mapOf("type" to "array", "elementType" to elementType.toJson())
        }

        override val qualifiedName: String
            get() = "[${elementType.qualifiedName}"
    }

    interface Lambda : ShakeType {
        val parameters: List<ShakeParameter>
        val returnType: ShakeType

        override fun toJson(): Map<String, Any?> {
            return mapOf(
                "type" to "lambda",
                "parameters" to parameters.map { it.toJson() },
                "returnType" to returnType.toJson()
            )
        }
    }
}

class ShakeOperatorRequestResult(
    val returnType: ShakeType?,
    val overload: ShakeMethod?
)
