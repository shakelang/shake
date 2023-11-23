package com.shakelang.shake.processor.program.types

import com.shakelang.shake.processor.ShakeSelect
import com.shakelang.shake.processor.program.types.code.ShakeScope

/**
 * A ShakeType is a type of a variable, field, method return type or a literal
 */
interface ShakeType {

    /**
     * The name of the type
     */
    val name: String

    /**
     * The qualified name of the type
     */
    val qualifiedName: String

    /**
     * If I overload the assign operator, what is the return type (null if not overloaded) of the expression `this = other`
     * If the assign operator is not overloaded, this method should return null. When returning null,
     * [ShakeAssignable.assignType] will know that the assign operator is not overloaded and will use the default
     * assign operator and resolve the type itself.
     *
     * @param other The type to assign
     * @param scope The scope in which the assign is done
     * @return The return type of the assign expression
     *
     * @since 0.1.0
     * @version 0.1.0
     */
    fun assignType(other: ShakeType, scope: ShakeScope): ShakeType? = assignOverload(other, scope)?.returnType

    /**
     * If I overload the add-assign operator, what is the return type (null if not overloaded) of the expression `this += other`
     * If the add-assign operator is not overloaded, this method should return null. When returning null,
     * [ShakeAssignable.additionAssignType] will know that the add-assign operator is not overloaded and will use the default
     * add-assign operator and resolve the type itself.
     *
     * @param other The type to add
     * @param scope The scope in which the add-assign is done
     * @return The return type of the add-assign expression
     *
     * @since 0.1.0
     * @version 0.1.0
     */
    fun additionAssignType(other: ShakeType, scope: ShakeScope): ShakeType? = additionAssignOverload(other, scope)?.returnType

    /**
     * If I overload the sub-assign operator, what is the return type (null if not overloaded) of the expression `this -= other`
     * If the sub-assign operator is not overloaded, this method should return null. When returning null,
     * [ShakeAssignable.subtractionAssignType] will know that the sub-assign operator is not overloaded and will use the default
     * sub-assign operator and resolve the type itself.
     *
     * @param other The type to sub
     * @param scope The scope in which the sub-assign is done
     * @return The return type of the sub-assign expression
     *
     * @since 0.1.0
     * @version 0.1.0
     */
    fun subtractionAssignType(other: ShakeType, scope: ShakeScope): ShakeType? = subtractionAssignOverload(other, scope)?.returnType

    /**
     * If I overload the mul-assign operator, what is the return type (null if not overloaded) of the expression `this *= other`
     * If the mul-assign operator is not overloaded, this method should return null. When returning null,
     * [ShakeAssignable.multiplicationAssignType] will know that the mul-assign operator is not overloaded and will use the default
     * mul-assign operator and resolve the type itself.
     *
     * @param other The type to mul
     * @param scope The scope in which the mul-assign is done
     * @return The return type of the mul-assign expression
     *
     * @since 0.1.0
     * @version 0.1.0
     */
    fun multiplicationAssignType(other: ShakeType, scope: ShakeScope): ShakeType? = multiplicationAssignOverload(other, scope)?.returnType

    /**
     * If I overload the div-assign operator, what is the return type (null if not overloaded) of the expression `this /= other`
     * If the div-assign operator is not overloaded, this method should return null. When returning null,
     * [ShakeAssignable.divisionAssignType] will know that the div-assign operator is not overloaded and will use the default
     * div-assign operator and resolve the type itself.
     *
     * @param other The type to div
     * @param scope The scope in which the div-assign is done
     * @return The return type of the div-assign expression
     *
     * @since 0.1.0
     * @version 0.1.0
     */
    fun divisionAssignType(other: ShakeType, scope: ShakeScope): ShakeType? = divisionAssignOverload(other, scope)?.returnType

    /**
     * If I overload the mod-assign operator, what is the return type (null if not overloaded) of the expression `this %= other`
     * If the mod-assign operator is not overloaded, this method should return null. When returning null,
     * [ShakeAssignable.modulusAssignType] will know that the mod-assign operator is not overloaded and will use the default
     * mod-assign operator and resolve the type itself.
     *
     * @param other The type to mod
     * @param scope The scope in which the mod-assign is done
     * @return The return type of the mod-assign expression
     *
     * @since 0.1.0
     * @version 0.1.0
     */
    fun modulusAssignType(other: ShakeType, scope: ShakeScope): ShakeType? = modulusAssignOverload(other, scope)?.returnType

    /**
     * If I overload the pow-assign operator, what is the return type (null if not overloaded) of the expression `this **= other`
     * If the pow-assign operator is not overloaded, this method should return null. When returning null,
     * [ShakeAssignable.powerAssignType] will know that the pow-assign operator is not overloaded and will use the default
     * pow-assign operator and resolve the type itself.
     *
     * @param other The type to pow
     * @param scope The scope in which the pow-assign is done
     * @return The return type of the pow-assign expression
     *
     * @since 0.1.0
     * @version 0.1.0
     */
    fun powerAssignType(other: ShakeType, scope: ShakeScope): ShakeType? = powerAssignOverload(other, scope)?.returnType

    /**
     * If I overload the increment-before operator, what is the return type (null if not overloaded) of the expression `++this`
     * If the increment-before operator is not overloaded, this method should return null. When returning null,
     * [ShakeAssignable.incrementBeforeType] will know that the increment-before operator is not overloaded and will use the default
     * increment-before operator and resolve the type itself.
     *
     * @param scope The scope in which the increment-before is done
     * @return The return type of the increment-before expression
     *
     * @since 0.1.0
     * @version 0.1.0
     */
    fun incrementBeforeType(scope: ShakeScope): ShakeType? = incrementBeforeOverload(scope)?.returnType

    /**
     * If I overload the increment-after operator, what is the return type (null if not overloaded) of the expression `this++`
     * If the increment-after operator is not overloaded, this method should return null. When returning null,
     * [ShakeAssignable.incrementAfterType] will know that the increment-after operator is not overloaded and will use the default
     * increment-after operator and resolve the type itself.
     *
     * @param scope The scope in which the increment-after is done
     * @return The return type of the increment-after expression
     *
     * @since 0.1.0
     * @version 0.1.0
     */
    fun incrementAfterType(scope: ShakeScope): ShakeType? = incrementAfterOverload(scope)?.returnType

    /**
     * If I overload the decrement-before operator, what is the return type (null if not overloaded) of the expression `--this`
     * If the decrement-before operator is not overloaded, this method should return null. When returning null,
     * [ShakeAssignable.decrementBeforeType] will know that the decrement-before operator is not overloaded and will use the default
     * decrement-before operator and resolve the type itself.
     *
     * @param scope The scope in which the decrement-before is done
     * @return The return type of the decrement-before expression
     *
     * @since 0.1.0
     * @version 0.1.0
     */
    fun decrementBeforeType(scope: ShakeScope): ShakeType? = decrementBeforeOverload(scope)?.returnType

    /**
     * If I overload the decrement-after operator, what is the return type (null if not overloaded) of the expression `this--`
     * If the decrement-after operator is not overloaded, this method should return null. When returning null,
     * [ShakeAssignable.decrementAfterType] will know that the decrement-after operator is not overloaded and will use the default
     * decrement-after operator and resolve the type itself.
     *
     * @param scope The scope in which the decrement-after is done
     * @return The return type of the decrement-after expression
     *
     * @since 0.1.0
     * @version 0.1.0
     */
    fun decrementAfterType(scope: ShakeScope): ShakeType? = decrementAfterOverload(scope)?.returnType

    /**
     * Returns the type of the expression `this + other`
     * If the add operator is not overloaded, this method should look for a default add operator and resolve the type
     * itself. This isi the default implementation of [additionType], we don't know anything about the type here and so
     * we just try to search for an overloaded operator.
     *
     * In implementation of [additionType] should look something like this:
     * ```kotlin
     * override fun additionType(other: ShakeType, scope: ShakeScope): ShakeType? {
     *   return super.additionType(other, scope) ?: ... // default operator stuff
     * }
     * ```
     *
     * @param other The type to add
     * @param scope The scope in which the add is done
     * @return The return type of the add expression
     *
     * @since 0.1.0
     * @version 0.1.0
     */
    fun additionType(other: ShakeType, scope: ShakeScope): ShakeType? = additionOverload(other, scope)?.returnType

    /**
     * Returns the type of the expression `this - other`
     * If the sub operator is not overloaded, this method should look for a default sub operator and resolve the type
     * itself. This isi the default implementation of [subtractionType], we don't know anything about the type here and so
     * we just try to search for an overloaded operator.
     *
     * In implementation of [subtractionType] should look something like this:
     * ```kotlin
     * override fun subtractionType(other: ShakeType, scope: ShakeScope): ShakeType? {
     *   return super.subtractionType(other, scope) ?: ... // default operator stuff
     * }
     * ```
     *
     * @param other The type to sub
     * @param scope The scope in which the sub is done
     * @return The return type of the sub expression
     *
     * @since 0.1.0
     * @version 0.1.0
     */
    fun subtractionType(other: ShakeType, scope: ShakeScope): ShakeType? =
        subtractionOverload(other, scope)?.returnType

    /**
     * Returns the type of the expression `this * other`
     * If the mul operator is not overloaded, this method should look for a default mul operator and resolve the type
     * itself. This isi the default implementation of [multiplicationType], we don't know anything about the type here and so
     * we just try to search for an overloaded operator.
     *
     * In implementation of [multiplicationType] should look something like this:
     * ```kotlin
     * override fun multiplicationType(other: ShakeType, scope: ShakeScope): ShakeType? {
     *   return super.multiplicationType(other, scope) ?: ... // default operator stuff
     * }
     * ```
     *
     * @param other The type to mul
     * @param scope The scope in which the mul is done
     * @return The return type of the mul expression
     *
     * @since 0.1.0
     * @version 0.1.0
     */
    fun multiplicationType(other: ShakeType, scope: ShakeScope): ShakeType? =
        multiplicationOverload(other, scope)?.returnType

    /**
     * Returns the type of the expression `this / other`
     * If the div operator is not overloaded, this method should look for a default div operator and resolve the type
     * itself. This isi the default implementation of [divisionType], we don't know anything about the type here and so
     * we just try to search for an overloaded operator.
     *
     * In implementation of [divisionType] should look something like this:
     * ```kotlin
     * override fun divisionType(other: ShakeType, scope: ShakeScope): ShakeType? {
     *   return super.divisionType(other, scope) ?: ... // default operator stuff
     * }
     * ```
     *
     * @param other The type to div
     * @param scope The scope in which the div is done
     * @return The return type of the div expression
     *
     * @since 0.1.0
     * @version 0.1.0
     */
    fun divisionType(other: ShakeType, scope: ShakeScope): ShakeType? = divisionOverload(other, scope)?.returnType

    /**
     * Returns the type of the expression `this % other`
     * If the mod operator is not overloaded, this method should look for a default mod operator and resolve the type
     * itself. This isi the default implementation of [modulusType], we don't know anything about the type here and so
     * we just try to search for an overloaded operator.
     *
     * In implementation of [modulusType] should look something like this:
     * ```kotlin
     * override fun modulusType(other: ShakeType, scope: ShakeScope): ShakeType? {
     *   return super.modulusType(other, scope) ?: ... // default operator stuff
     * }
     * ```
     *
     * @param other The type to mod
     * @param scope The scope in which the mod is done
     * @return The return type of the mod expression
     *
     * @since 0.1.0
     * @version 0.1.0
     */
    fun modulusType(other: ShakeType, scope: ShakeScope): ShakeType? = modulusOverload(other, scope)?.returnType

    /**
     * Returns the type of the expression `this ** other`
     * If the pow operator is not overloaded, this method should look for a default pow operator and resolve the type
     * itself. This isi the default implementation of [powerType], we don't know anything about the type here and so
     * we just try to search for an overloaded operator.
     *
     * In implementation of [powerType] should look something like this:
     * ```kotlin
     * override fun powerType(other: ShakeType, scope: ShakeScope): ShakeType? {
     *   return super.powerType(other, scope) ?: ... // default operator stuff
     * }
     * ```
     *
     * @param other The type to pow
     * @param scope The scope in which the pow is done
     * @return The return type of the pow expression
     *
     * @since 0.1.0
     * @version 0.1.0
     */
    fun powerType(other: ShakeType, scope: ShakeScope): ShakeType? = powerOverload(other, scope)?.returnType

    /**
     * Returns the type of the expression `this == other`
     * If the equals operator is not overloaded, this method should look for a default equals operator and resolve the type
     * itself. This isi the default implementation of [equalsType], we don't know anything about the type here and so
     * we just try to search for an overloaded operator.
     *
     * In implementation of [equalsType] should look something like this:
     * ```kotlin
     * override fun equalsType(other: ShakeType, scope: ShakeScope): ShakeType? {
     *   return super.equalsType(other, scope) ?: ... // default operator stuff
     * }
     * ```
     *
     * @param other The type to compare
     * @param scope The scope in which the compare is done
     * @return The return type of the compare expression
     *
     * @since 0.1.0
     * @version 0.1.0
     */
    fun equalsType(other: ShakeType, scope: ShakeScope): ShakeType? = equalsOverload(other, scope)?.returnType

    /**
     * Returns the type of the expression `this != other`
     * If the not-equals operator is not overloaded, this method should look for a default not-equals operator and resolve the type
     * itself. This isi the default implementation of [notEqualsType], we don't know anything about the type here and so
     * we just try to search for an overloaded operator.
     *
     * In implementation of [notEqualsType] should look something like this:
     * ```kotlin
     * override fun notEqualsType(other: ShakeType, scope: ShakeScope): ShakeType? {
     *   return super.notEqualsType(other, scope) ?: ... // default operator stuff
     * }
     * ```
     *
     * @param other The type to compare
     * @param scope The scope in which the compare is done
     * @return The return type of the compare expression
     *
     * @since 0.1.0
     * @version 0.1.0
     */
    fun notEqualsType(other: ShakeType, scope: ShakeScope): ShakeType? = notEqualsOverload(other, scope)?.returnType

    /**
     * Returns the type of the expression `this > other`
     * If the greater-than operator is not overloaded, this method should look for a default greater-than operator and resolve the type
     * itself. This isi the default implementation of [greaterThanType], we don't know anything about the type here and so
     * we just try to search for an overloaded operator.
     *
     * In implementation of [greaterThanType] should look something like this:
     * ```kotlin
     * override fun greaterThanType(other: ShakeType, scope: ShakeScope): ShakeType? {
     *   return super.greaterThanType(other, scope) ?: ... // default operator stuff
     * }
     * ```
     *
     * @param other The type to compare
     * @param scope The scope in which the compare is done
     * @return The return type of the compare expression
     *
     * @since 0.1.0
     * @version 0.1.0
     */
    fun greaterThanType(other: ShakeType, scope: ShakeScope): ShakeType? = greaterThanOverload(other, scope)?.returnType

    /**
     * Returns the type of the expression `this >= other`
     * If the greater-than-or-equals operator is not overloaded, this method should look for a default greater-than-or-equals operator and resolve the type
     * itself. This isi the default implementation of [greaterThanOrEqualType], we don't know anything about the type here and so
     * we just try to search for an overloaded operator.
     *
     * In implementation of [greaterThanOrEqualType] should look something like this:
     * ```kotlin
     * override fun greaterThanOrEqualType(other: ShakeType, scope: ShakeScope): ShakeType? {
     *   return super.greaterThanOrEqualType(other, scope) ?: ... // default operator stuff
     * }
     * ```
     *
     * @param other The type to compare
     * @param scope The scope in which the compare is done
     * @return The return type of the compare expression
     *
     * @since 0.1.0
     * @version 0.1.0
     */
    fun greaterThanOrEqualType(other: ShakeType, scope: ShakeScope): ShakeType? =
        greaterThanOrEqualOverload(other, scope)?.returnType

    /**
     * Returns the type of the expression `this < other`
     * If the less-than operator is not overloaded, this method should look for a default less-than operator and resolve the type
     * itself. This isi the default implementation of [lessThanType], we don't know anything about the type here and so
     * we just try to search for an overloaded operator.
     *
     * In implementation of [lessThanType] should look something like this:
     * ```kotlin
     * override fun lessThanType(other: ShakeType, scope: ShakeScope): ShakeType? {
     *   return super.lessThanType(other, scope) ?: ... // default operator stuff
     * }
     * ```
     *
     * @param other The type to compare
     * @param scope The scope in which the compare is done
     * @return The return type of the compare expression
     *
     * @since 0.1.0
     * @version 0.1.0
     */
    fun lessThanType(other: ShakeType, scope: ShakeScope): ShakeType? = lessThanOverload(other, scope)?.returnType

    /**
     * Returns the type of the expression `this <= other`
     * If the less-than-or-equals operator is not overloaded, this method should look for a default less-than-or-equals operator and resolve the type
     * itself. This isi the default implementation of [lessThanOrEqualType], we don't know anything about the type here and so
     * we just try to search for an overloaded operator.
     *
     * In implementation of [lessThanOrEqualType] should look something like this:
     * ```kotlin
     * override fun lessThanOrEqualType(other: ShakeType, scope: ShakeScope): ShakeType? {
     *   return super.lessThanOrEqualType(other, scope) ?: ... // default operator stuff
     * }
     * ```
     *
     * @param other The type to compare
     * @param scope The scope in which the compare is done
     * @return The return type of the compare expression
     *
     * @since 0.1.0
     * @version 0.1.0
     */
    fun  lessThanOrEqualType(other: ShakeType, scope: ShakeScope): ShakeType? =
        lessThanOrEqualOverload(other, scope)?.returnType

    /**
     * Returns the type of the expression `this && other`
     * If the and operator is not overloaded, this method should look for a default and operator and resolve the type
     * itself. This isi the default implementation of [logicalAndType], we don't know anything about the type here and so
     * we just try to search for an overloaded operator.
     *
     * In implementation of [logicalAndType] should look something like this:
     * ```kotlin
     * override fun logicalAndType(other: ShakeType, scope: ShakeScope): ShakeType? {
     *   return super.logicalAndType(other, scope) ?: ... // default operator stuff
     * }
     * ```
     *
     * @param other The type to and
     * @param scope The scope in which the and is done
     * @return The return type of the and expression
     *
     * @since 0.1.0
     * @version 0.1.0
     */
    fun logicalAndType(other: ShakeType, scope: ShakeScope): ShakeType? = logicalAndOverload(other, scope)?.returnType

    /**
     * Returns the type of the expression `this || other`
     * If the or operator is not overloaded, this method should look for a default or operator and resolve the type
     * itself. This isi the default implementation of [logicalOrType], we don't know anything about the type here and so
     * we just try to search for an overloaded operator.
     *
     * In implementation of [logicalOrType] should look something like this:
     * ```kotlin
     * override fun logicalOrType(other: ShakeType, scope: ShakeScope): ShakeType? {
     *   return super.logicalOrType(other, scope) ?: ... // default operator stuff
     * }
     * ```
     *
     * @param other The type to or
     * @param scope The scope in which the or is done
     * @return The return type of the or expression
     *
     * @since 0.1.0
     * @version 0.1.0
     */
    fun logicalOrType(other: ShakeType, scope: ShakeScope): ShakeType? = logicalOrOverload(other, scope)?.returnType

    /**
     * Returns the type of the expression `!this`
     * If the not operator is not overloaded, this method should look for a default not operator and resolve the type
     * itself. This isi the default implementation of [logicalNotType], we don't know anything about the type here and so
     * we just try to search for an overloaded operator.
     *
     * In implementation of [logicalNotType] should look something like this:
     * ```kotlin
     * override fun logicalNotType(scope: ShakeScope): ShakeType? {
     *   return super.logicalNotType(scope) ?: ... // default operator stuff
     * }
     * ```
     *
     * @param scope The scope in which the not is done
     * @return The return type of the not expression
     *
     * @since 0.1.0
     * @version 0.1.0
     */
    fun logicalNotType(scope: ShakeScope): ShakeType? = logicalNotOverload(scope)?.returnType

    /**
     * Returns the type of the expression `this & other`
     * If the binary-and operator is not overloaded, this method should look for a default binary-and operator and resolve the type
     * itself. This isi the default implementation of [binaryAndType], we don't know anything about the type here and so
     * we just try to search for an overloaded operator.
     *
     * In implementation of [binaryAndType] should look something like this:
     * ```kotlin
     * override fun binaryAndType(other: ShakeType, scope: ShakeScope): ShakeType? {
     *   return super.binaryAndType(other, scope) ?: ... // default operator stuff
     * }
     * ```
     *
     * @param other The type to binary-and
     * @param scope The scope in which the binary-and is done
     * @return The return type of the binary-and expression
     *
     * @since 0.1.0
     * @version 0.1.0
     */
    fun binaryAndType(other: ShakeType, scope: ShakeScope): ShakeType? = binaryAndOverload(other, scope)?.returnType

    /**
     * Returns the type of the expression `this | other`
     * If the binary-or operator is not overloaded, this method should look for a default binary-or operator and resolve the type
     * itself. This isi the default implementation of [binaryOrType], we don't know anything about the type here and so
     * we just try to search for an overloaded operator.
     *
     * In implementation of [binaryOrType] should look something like this:
     * ```kotlin
     * override fun binaryOrType(other: ShakeType, scope: ShakeScope): ShakeType? {
     *   return super.binaryOrType(other, scope) ?: ... // default operator stuff
     * }
     * ```
     *
     * @param other The type to binary-or
     * @param scope The scope in which the binary-or is done
     * @return The return type of the binary-or expression
     *
     * @since 0.1.0
     * @version 0.1.0
     */
    fun binaryOrType(other: ShakeType, scope: ShakeScope): ShakeType? = binaryOrOverload(other, scope)?.returnType

    /**
     * Returns the type of the expression `this ^ other`
     * If the binary-xor operator is not overloaded, this method should look for a default binary-xor operator and resolve the type
     * itself. This isi the default implementation of [binaryXorType], we don't know anything about the type here and so
     * we just try to search for an overloaded operator.
     *
     * In implementation of [binaryXorType] should look something like this:
     * ```kotlin
     * override fun binaryXorType(other: ShakeType, scope: ShakeScope): ShakeType? {
     *   return super.binaryXorType(other, scope) ?: ... // default operator stuff
     * }
     * ```
     *
     * @param other The type to binary-xor
     * @param scope The scope in which the binary-xor is done
     * @return The return type of the binary-xor expression
     *
     * @since 0.1.0
     * @version 0.1.0
     */
    fun binaryXorType(other: ShakeType, scope: ShakeScope): ShakeType? = binaryXorOverload(other, scope)?.returnType

    /**
     * Returns the type of the expression `this << other`
     * If the binary-left-shift operator is not overloaded, this method should look for a default binary-left-shift operator and resolve the type
     * itself. This isi the default implementation of [binaryShiftLeftType], we don't know anything about the type here and so
     * we just try to search for an overloaded operator.
     *
     * In implementation of [binaryLeftShiftType] should look something like this:
     * ```kotlin
     * override fun binaryLeftShiftType(other: ShakeType, scope: ShakeScope): ShakeType? {
     *   return super.binaryLeftShiftType(other, scope) ?: ... // default operator stuff
     * }
     * ```
     *
     * @param other The type to binary-left-shift
     * @param scope The scope in which the binary-left-shift is done
     * @return The return type of the binary-left-shift expression
     *
     * @since 0.1.0
     * @version 0.1.0
     */
    fun binaryShiftLeftType(other: ShakeType, scope: ShakeScope): ShakeType? = binaryShiftLeftOverload(other, scope)?.returnType

    /**
     * Returns the type of the expression `this >> other`
     * If the binary-right-shift operator is not overloaded, this method should look for a default binary-right-shift operator and resolve the type
     * itself. This isi the default implementation of [binaryShiftRightType], we don't know anything about the type here and so
     * we just try to search for an overloaded operator.
     *
     * In implementation of [binaryShiftRightType] should look something like this:
     * ```kotlin
     * override fun binaryRightShiftType(other: ShakeType, scope: ShakeScope): ShakeType? {
     *   return super.binaryRightShiftType(other, scope) ?: ... // default operator stuff
     * }
     * ```
     *
     * @param other The type to binary-right-shift
     * @param scope The scope in which the binary-right-shift is done
     * @return The return type of the binary-right-shift expression
     *
     * @since 0.1.0
     * @version 0.1.0
     */
    fun binaryShiftRightType(other: ShakeType, scope: ShakeScope): ShakeType? = binaryShiftRightOverload(other, scope)?.returnType

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

    fun logicalAndOverloads(scope: ShakeScope): List<ShakeMethod> =
        scope.getFunctions("and").filter { it.expanding == this && it.isOperator }

    fun logicalOrOverloads(scope: ShakeScope): List<ShakeMethod> =
        scope.getFunctions("or").filter { it.expanding == this && it.isOperator }

    fun logicalXorOverloads(scope: ShakeScope): List<ShakeMethod> =
        scope.getFunctions("xor").filter { it.expanding == this && it.isOperator }

    fun notOverloads(scope: ShakeScope): List<ShakeMethod> =
        scope.getFunctions("not").filter { it.expanding == this && it.isOperator }

    fun binaryAndOverloads(scope: ShakeScope): List<ShakeMethod> =
        scope.getFunctions("binaryAnd").filter { it.expanding == this && it.isOperator }

    fun binaryOrOverloads(scope: ShakeScope): List<ShakeMethod> =
        scope.getFunctions("binaryOr").filter { it.expanding == this && it.isOperator }

    fun binaryXorOverloads(scope: ShakeScope): List<ShakeMethod> =
        scope.getFunctions("binaryXor").filter { it.expanding == this && it.isOperator }

    fun binaryNotOverloads(scope: ShakeScope): List<ShakeMethod> =
        scope.getFunctions("binaryNot").filter { it.expanding == this && it.isOperator }

    fun binaryShiftLeftOverloads(scope: ShakeScope): List<ShakeMethod> =
        scope.getFunctions("binaryShitLeft").filter { it.expanding == this && it.isOperator }

    fun binaryShiftRightOverloads(scope: ShakeScope): List<ShakeMethod> =
        scope.getFunctions("binaryShitRight").filter { it.expanding == this && it.isOperator }

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

    fun logicalAndOverload(other: ShakeType, scope: ShakeScope): ShakeMethod? =
        ShakeSelect.selectFunction(logicalAndOverloads(scope), listOf(other))

    fun logicalOrOverload(other: ShakeType, scope: ShakeScope): ShakeMethod? =
        ShakeSelect.selectFunction(logicalOrOverloads(scope), listOf(other))

    fun logicalXorOverload(other: ShakeType, scope: ShakeScope): ShakeMethod? =
        ShakeSelect.selectFunction(logicalXorOverloads(scope), listOf(other))

    fun logicalNotOverload(scope: ShakeScope): ShakeMethod? = ShakeSelect.selectFunction(notOverloads(scope), emptyList())

    fun binaryAndOverload(other: ShakeType, scope: ShakeScope): ShakeMethod? =
        ShakeSelect.selectFunction(binaryAndOverloads(scope), listOf(other))

    fun binaryOrOverload(other: ShakeType, scope: ShakeScope): ShakeMethod? =
        ShakeSelect.selectFunction(binaryOrOverloads(scope), listOf(other))

    fun binaryXorOverload(other: ShakeType, scope: ShakeScope): ShakeMethod? =
        ShakeSelect.selectFunction(binaryXorOverloads(scope), listOf(other))

    fun binaryNotOverload(scope: ShakeScope): ShakeMethod? =
        ShakeSelect.selectFunction(binaryNotOverloads(scope), emptyList())

    fun binaryShiftLeftOverload(other: ShakeType, scope: ShakeScope): ShakeMethod? =
        ShakeSelect.selectFunction(binaryShiftLeftOverloads(scope), listOf(other))

    fun binaryShiftRightOverload(other: ShakeType, scope: ShakeScope): ShakeMethod? =
        ShakeSelect.selectFunction(binaryShiftRightOverloads(scope), listOf(other))


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
        val method = logicalAndOverload(other, scope)
        if (method != null) return ShakeOperatorRequestResult(method.returnType, method)
        return ShakeOperatorRequestResult(null, null)
    }

    fun orOperator(other: ShakeType, scope: ShakeScope): ShakeOperatorRequestResult {
        val method = logicalOrOverload(other, scope)
        if (method != null) return ShakeOperatorRequestResult(method.returnType, method)
        return ShakeOperatorRequestResult(null, null)
    }

    fun notOperator(scope: ShakeScope): ShakeOperatorRequestResult {
        val method = logicalNotOverload(scope)
        if (method != null) return ShakeOperatorRequestResult(method.returnType, method)
        return ShakeOperatorRequestResult(null, null)
    }

    fun binaryAndOperator(other: ShakeType, scope: ShakeScope): ShakeOperatorRequestResult {
        val method = binaryAndOverload(other, scope)
        if (method != null) return ShakeOperatorRequestResult(method.returnType, method)
        return ShakeOperatorRequestResult(null, null)
    }

    fun binaryOrOperator(other: ShakeType, scope: ShakeScope): ShakeOperatorRequestResult {
        val method = binaryOrOverload(other, scope)
        if (method != null) return ShakeOperatorRequestResult(method.returnType, method)
        return ShakeOperatorRequestResult(null, null)
    }

    fun binaryXorOperator(other: ShakeType, scope: ShakeScope): ShakeOperatorRequestResult {
        val method = binaryXorOverload(other, scope)
        if (method != null) return ShakeOperatorRequestResult(method.returnType, method)
        return ShakeOperatorRequestResult(null, null)
    }

    fun binaryNotOperator(scope: ShakeScope): ShakeOperatorRequestResult {
        val method = binaryNotOverload(scope)
        if (method != null) return ShakeOperatorRequestResult(method.returnType, method)
        return ShakeOperatorRequestResult(null, null)
    }

    fun binaryShiftLeftOperator(other: ShakeType, scope: ShakeScope): ShakeOperatorRequestResult {
        val method = binaryShiftLeftOverload(other, scope)
        if (method != null) return ShakeOperatorRequestResult(method.returnType, method)
        return ShakeOperatorRequestResult(null, null)
    }

    fun binaryShiftRightOperator(other: ShakeType, scope: ShakeScope): ShakeOperatorRequestResult {
        val method = binaryShiftRightOverload(other, scope)
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
        NULL,
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

        override fun logicalAndOverloads(scope: ShakeScope): List<ShakeMethod> =
            clazz.methods.filter { it.name == "and" && it.isOperator } + super.logicalAndOverloads(scope)

        override fun logicalOrOverloads(scope: ShakeScope): List<ShakeMethod> =
            clazz.methods.filter { it.name == "or" && it.isOperator } + super.logicalOrOverloads(scope)

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
        override fun logicalAndType(other: ShakeType, scope: ShakeScope): ShakeType? = null
        override fun logicalOrType(other: ShakeType, scope: ShakeScope): ShakeType? = null
        override fun logicalNotType(scope: ShakeScope): ShakeType? = null

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
