package com.shakelang.util.primitives.atomic

import com.shakelang.util.primitives.calc.shl
import com.shakelang.util.primitives.calc.shr
import com.shakelang.util.primitives.calc.ushr
import kotlin.experimental.and
import kotlin.experimental.inv
import kotlin.experimental.or
import kotlin.experimental.xor
import kotlin.math.pow

// Short Operations
@Suppress("MemberVisibilityCanBePrivate")
object AtomicShortOperationImplementations {

    // Short Operations
    fun plus(source1: AtomicShort, other: Short): Short = (source1.shortValue + other).toShort()
    fun plus(source1: AtomicShort, source2: AtomicShort): Short = (source1.shortValue + source2.shortValue).toShort()
    fun minus(source1: AtomicShort, other: Short): Short = (source1.shortValue - other).toShort()
    fun minus(source1: AtomicShort, source2: AtomicShort): Short = (source1.shortValue - source2.shortValue).toShort()
    fun times(source1: AtomicShort, other: Short): Short = (source1.shortValue * other).toShort()
    fun times(source1: AtomicShort, source2: AtomicShort): Short = (source1.shortValue * source2.shortValue).toShort()
    fun div(source1: AtomicShort, other: Short): Short = (source1.shortValue / other).toShort()
    fun div(source1: AtomicShort, source2: AtomicShort): Short = (source1.shortValue / source2.shortValue).toShort()
    fun rem(source1: AtomicShort, other: Short): Short = (source1.shortValue % other).toShort()
    fun rem(source1: AtomicShort, source2: AtomicShort): Short = (source1.shortValue % source2.shortValue).toShort()
    fun pow(source1: AtomicShort, other: Short): Short = source1.shortValue.toDouble().pow(other.toDouble()).toInt().toShort()
    fun pow(source1: AtomicShort, source2: AtomicShort): Short = source1.shortValue.toDouble().pow(source2.shortValue.toDouble()).toInt().toShort()
    fun and(source1: AtomicShort, other: Short): Short = source1.shortValue and other
    fun and(source1: AtomicShort, source2: AtomicShort): Short = source1.shortValue and source2.shortValue
    fun or(source1: AtomicShort, other: Short): Short = source1.shortValue or other
    fun or(source1: AtomicShort, source2: AtomicShort): Short = source1.shortValue or source2.shortValue
    fun xor(source1: AtomicShort, other: Short): Short = source1.shortValue xor other
    fun xor(source1: AtomicShort, source2: AtomicShort): Short = source1.shortValue xor source2.shortValue
    fun shl(source1: AtomicShort, other: Short): Short = source1.shortValue shl other
    fun shl(source1: AtomicShort, source2: AtomicShort): Short = source1.shortValue shl source2.shortValue
    fun shr(source1: AtomicShort, other: Short): Short = source1.shortValue shr other
    fun shr(source1: AtomicShort, source2: AtomicShort): Short = source1.shortValue shr source2.shortValue
    fun ushr(source1: AtomicShort, other: Short): Short = source1.shortValue ushr other
    fun ushr(source1: AtomicShort, source2: AtomicShort): Short = source1.shortValue ushr source2.shortValue
    fun inv(source1: AtomicShort): Short = source1.shortValue.inv()
    fun negate(source1: AtomicShort): Short = (-source1.shortValue).toShort()

    val shortPlusShort: BinaryOperation<AtomicShort, Short, Short> = ::plus
    val shortPlusAtomicShort: BinaryOperation<AtomicShort, AtomicShort, Short> = ::plus
    val shortMinusShort: BinaryOperation<AtomicShort, Short, Short> = ::minus
    val shortMinusAtomicShort: BinaryOperation<AtomicShort, AtomicShort, Short> = ::minus
    val shortTimesShort: BinaryOperation<AtomicShort, Short, Short> = ::times
    val shortTimesAtomicShort: BinaryOperation<AtomicShort, AtomicShort, Short> = ::times
    val shortDivShort: BinaryOperation<AtomicShort, Short, Short> = ::div
    val shortDivAtomicShort: BinaryOperation<AtomicShort, AtomicShort, Short> = ::div
    val shortRemShort: BinaryOperation<AtomicShort, Short, Short> = ::rem
    val shortRemAtomicShort: BinaryOperation<AtomicShort, AtomicShort, Short> = ::rem
    val shortPowShort: BinaryOperation<AtomicShort, Short, Short> = ::pow
    val shortPowAtomicShort: BinaryOperation<AtomicShort, AtomicShort, Short> = ::pow
    val shortAndShort: BinaryOperation<AtomicShort, Short, Short> = ::and
    val shortAndAtomicShort: BinaryOperation<AtomicShort, AtomicShort, Short> = ::and
    val shortOrShort: BinaryOperation<AtomicShort, Short, Short> = ::or
    val bytOrAtomicShort: BinaryOperation<AtomicShort, AtomicShort, Short> = ::or
    val shortXorShort: BinaryOperation<AtomicShort, Short, Short> = ::xor
    val shortXorAtomicShort: BinaryOperation<AtomicShort, AtomicShort, Short> = ::xor
    val shortShlShort: BinaryOperation<AtomicShort, Short, Short> = ::shl
    val shortShlAtomicShort: BinaryOperation<AtomicShort, AtomicShort, Short> = ::shl
    val shortShrShort: BinaryOperation<AtomicShort, Short, Short> = ::shr
    val shortShrAtomicShort: BinaryOperation<AtomicShort, AtomicShort, Short> = ::shr
    val shortUshrShort: BinaryOperation<AtomicShort, Short, Short> = ::ushr
    val shortUshrAtomicShort: BinaryOperation<AtomicShort, AtomicShort, Short> = ::ushr
    val shortInv: UnaryOperation<AtomicShort, Short> = ::inv
    val shortNegate: UnaryOperation<AtomicShort, Short> = ::negate

    fun transformPlus(source1: AtomicShort, other: Short): AtomicShort = transform1(source1, other, shortPlusShort, AutoUpdateAbleAtomicShort.creator)
    fun transformPlus(source1: AtomicShort, other: AtomicShort): AtomicShort = transform2(source1, other, shortPlusAtomicShort, AutoUpdateAbleAtomicShort.creator)
    fun transformMinus(source1: AtomicShort, other: Short): AtomicShort = transform1(source1, other, shortMinusShort, AutoUpdateAbleAtomicShort.creator)
    fun transformMinus(source1: AtomicShort, other: AtomicShort): AtomicShort = transform2(source1, other, shortMinusAtomicShort, AutoUpdateAbleAtomicShort.creator)
    fun transformTimes(source1: AtomicShort, other: Short): AtomicShort = transform1(source1, other, shortTimesShort, AutoUpdateAbleAtomicShort.creator)
    fun transformTimes(source1: AtomicShort, other: AtomicShort): AtomicShort = transform2(source1, other, shortTimesAtomicShort, AutoUpdateAbleAtomicShort.creator)
    fun transformDiv(source1: AtomicShort, other: Short): AtomicShort = transform1(source1, other, shortDivShort, AutoUpdateAbleAtomicShort.creator)
    fun transformDiv(source1: AtomicShort, other: AtomicShort): AtomicShort = transform2(source1, other, shortDivAtomicShort, AutoUpdateAbleAtomicShort.creator)
    fun transformRem(source1: AtomicShort, other: Short): AtomicShort = transform1(source1, other, shortRemShort, AutoUpdateAbleAtomicShort.creator)
    fun transformRem(source1: AtomicShort, other: AtomicShort): AtomicShort = transform2(source1, other, shortRemAtomicShort, AutoUpdateAbleAtomicShort.creator)
    fun transformPow(source1: AtomicShort, other: Short): AtomicShort = transform1(source1, other, shortPowShort, AutoUpdateAbleAtomicShort.creator)
    fun transformPow(source1: AtomicShort, other: AtomicShort): AtomicShort = transform2(source1, other, shortPowAtomicShort, AutoUpdateAbleAtomicShort.creator)
    fun transformAnd(source1: AtomicShort, other: Short): AtomicShort = transform1(source1, other, shortAndShort, AutoUpdateAbleAtomicShort.creator)
    fun transformAnd(source1: AtomicShort, other: AtomicShort): AtomicShort = transform2(source1, other, shortAndAtomicShort, AutoUpdateAbleAtomicShort.creator)
    fun transformOr(source1: AtomicShort, other: Short): AtomicShort = transform1(source1, other, shortOrShort, AutoUpdateAbleAtomicShort.creator)
    fun transformOr(source1: AtomicShort, other: AtomicShort): AtomicShort = transform2(source1, other, bytOrAtomicShort, AutoUpdateAbleAtomicShort.creator)
    fun transformXor(source1: AtomicShort, other: Short): AtomicShort = transform1(source1, other, shortXorShort, AutoUpdateAbleAtomicShort.creator)
    fun transformXor(source1: AtomicShort, other: AtomicShort): AtomicShort = transform2(source1, other, shortXorAtomicShort, AutoUpdateAbleAtomicShort.creator)
    fun transformShl(source1: AtomicShort, other: Short): AtomicShort = transform1(source1, other, shortShlShort, AutoUpdateAbleAtomicShort.creator)
    fun transformShl(source1: AtomicShort, other: AtomicShort): AtomicShort = transform2(source1, other, shortShlAtomicShort, AutoUpdateAbleAtomicShort.creator)
    fun transformShr(source1: AtomicShort, other: Short): AtomicShort = transform1(source1, other, shortShrShort, AutoUpdateAbleAtomicShort.creator)
    fun transformShr(source1: AtomicShort, other: AtomicShort): AtomicShort = transform2(source1, other, shortShrAtomicShort, AutoUpdateAbleAtomicShort.creator)
    fun transformUshr(source1: AtomicShort, other: Short): AtomicShort = transform1(source1, other, shortUshrShort, AutoUpdateAbleAtomicShort.creator)
    fun transformUshr(source1: AtomicShort, other: AtomicShort): AtomicShort = transform2(source1, other, shortUshrAtomicShort, AutoUpdateAbleAtomicShort.creator)
    fun transformInv(source1: AtomicShort): AtomicShort = transform1(source1, shortInv, AutoUpdateAbleAtomicShort.creator)
    fun transformNegate(source1: AtomicShort): AtomicShort = transform1(source1, shortNegate, AutoUpdateAbleAtomicShort.creator)
}

/**
 * An atomic short value
 */
interface AtomicShort : AtomicValue {

    /**
     * The value of the atomic short
     */
    val shortValue: Short

    /**
     * Get the value of the atomic short
     */
    fun get(): Short

    /**
     * Add another short to this short (returns a new [AtomicShort] that automatically updates)
     * @param other The other short to add
     * @return The new [AtomicShort] that automatically updates
     */
    operator fun plus(other: Short) = AtomicShortOperationImplementations.transformPlus(this, other)

    /**
     * Add another [AtomicShort] to this short (returns a new [AtomicShort] that automatically updates)
     * @param other The other [AtomicShort] to add
     * @return The new [AtomicShort] that automatically updates
     */
    operator fun plus(other: AtomicShort) = AtomicShortOperationImplementations.transformPlus(this, other)

    /**
     * Subtract another short from this short (returns a new [AtomicShort] that automatically updates)
     * @param other The other short to subtract
     * @return The new [AtomicShort] that automatically updates
     */
    operator fun minus(other: Short) = AtomicShortOperationImplementations.transformMinus(this, other)

    /**
     * Subtract another [AtomicShort] from this short (returns a new [AtomicShort] that automatically updates)
     * @param other The other [AtomicShort] to subtract
     * @return The new [AtomicShort] that automatically updates
     */
    operator fun minus(other: AtomicShort) = AtomicShortOperationImplementations.transformMinus(this, other)

    /**
     * Multiply this short with another short (returns a new [AtomicShort] that automatically updates)
     * @param other The other short to multiply with
     * @return The new [AtomicShort] that automatically updates
     */
    operator fun times(other: Short) = AtomicShortOperationImplementations.transformTimes(this, other)

    /**
     * Multiply this short with another [AtomicShort] (returns a new [AtomicShort] that automatically updates)
     * @param other The other [AtomicShort] to multiply with
     * @return The new [AtomicShort] that automatically updates
     */
    operator fun times(other: AtomicShort) = AtomicShortOperationImplementations.transformTimes(this, other)

    /**
     * Divide this short by another short (returns a new [AtomicShort] that automatically updates)
     * @param other The other short to divide by
     * @return The new [AtomicShort] that automatically updates
     */
    operator fun div(other: Short) = AtomicShortOperationImplementations.transformDiv(this, other)

    /**
     * Divide this short by another [AtomicShort] (returns a new [AtomicShort] that automatically updates)
     * @param other The other [AtomicShort] to divide by
     * @return The new [AtomicShort] that automatically updates
     */
    operator fun div(other: AtomicShort) = AtomicShortOperationImplementations.transformDiv(this, other)

    /**
     * Modulo this short by another short (returns a new [AtomicShort] that automatically updates)
     * @param other The other short to modulo by
     * @return The new [AtomicShort] that automatically updates
     */
    operator fun rem(other: Short) = AtomicShortOperationImplementations.transformRem(this, other)

    /**
     * Modulo this short by another [AtomicShort] (returns a new [AtomicShort] that automatically updates)
     * @param other The other [AtomicShort] to modulo by
     * @return The new [AtomicShort] that automatically updates
     */
    operator fun rem(other: AtomicShort) = AtomicShortOperationImplementations.transformRem(this, other)

    /**
     * Power this short by another short (returns a new [AtomicShort] that automatically updates)
     * @param other The other short to power by
     * @return The new [AtomicShort] that automatically updates
     */
    infix fun pow(other: Short) = AtomicShortOperationImplementations.transformPow(this, other)

    /**
     * Power this short by another [AtomicShort] (returns a new [AtomicShort] that automatically updates)
     * @param other The other [AtomicShort] to power by
     * @return The new [AtomicShort] that automatically updates
     */
    infix fun pow(other: AtomicShort) = AtomicShortOperationImplementations.transformPow(this, other)

    /**
     * And this short with another short (returns a new [AtomicShort] that automatically updates)
     * @param other The other short to and with
     * @return The new [AtomicShort] that automatically updates
     */
    infix fun and(other: Short) = AtomicShortOperationImplementations.transformAnd(this, other)

    /**
     * And this short with another [AtomicShort] (returns a new [AtomicShort] that automatically updates)
     * @param other The other [AtomicShort] to and with
     * @return The new [AtomicShort] that automatically updates
     */
    infix fun and(other: AtomicShort) = AtomicShortOperationImplementations.transformAnd(this, other)

    /**
     * Or this short with another short (returns a new [AtomicShort] that automatically updates)
     * @param other The other short to or with
     * @return The new [AtomicShort] that automatically updates
     */
    infix fun or(other: Short) = AtomicShortOperationImplementations.transformOr(this, other)

    /**
     * Or this short with another [AtomicShort] (returns a new [AtomicShort] that automatically updates)
     * @param other The other [AtomicShort] to or with
     * @return The new [AtomicShort] that automatically updates
     */
    infix fun or(other: AtomicShort) = AtomicShortOperationImplementations.transformOr(this, other)

    /**
     * Xor this short with another short (returns a new [AtomicShort] that automatically updates)
     * @param other The other short to xor with
     * @return The new [AtomicShort] that automatically updates
     */
    infix fun xor(other: Short) = AtomicShortOperationImplementations.transformXor(this, other)

    /**
     * Xor this short with another [AtomicShort] (returns a new [AtomicShort] that automatically updates)
     * @param other The other [AtomicShort] to xor with
     * @return The new [AtomicShort] that automatically updates
     */
    infix fun xor(other: AtomicShort) = AtomicShortOperationImplementations.transformXor(this, other)

    /**
     * Shift this short left by another short (returns a new [AtomicShort] that automatically updates)
     * @param other The other short to shift left by
     * @return The new [AtomicShort] that automatically updates
     */
    infix fun shl(other: Short) = AtomicShortOperationImplementations.transformShl(this, other)

    /**
     * Shift this short left by another [AtomicShort] (returns a new [AtomicShort] that automatically updates)
     * @param other The other [AtomicShort] to shift left by
     * @return The new [AtomicShort] that automatically updates
     */
    infix fun shl(other: AtomicShort) = AtomicShortOperationImplementations.transformShl(this, other)

    /**
     * Shift this short right by another short (returns a new [AtomicShort] that automatically updates)
     * @param other The other short to shift right by
     * @return The new [AtomicShort] that automatically updates
     */
    infix fun shr(other: Short) = AtomicShortOperationImplementations.transformShr(this, other)

    /**
     * Shift this short right by another [AtomicShort] (returns a new [AtomicShort] that automatically updates)
     * @param other The other [AtomicShort] to shift right by
     * @return The new [AtomicShort] that automatically updates
     */
    infix fun shr(other: AtomicShort) = AtomicShortOperationImplementations.transformShr(this, other)

    /**
     * Shift this short right by another short (returns a new [AtomicShort] that automatically updates)
     * @param other The other short to shift right by
     * @return The new [AtomicShort] that automatically updates
     */
    infix fun ushr(other: Short) = AtomicShortOperationImplementations.transformUshr(this, other)

    /**
     * Shift this short right by another [AtomicShort] (returns a new [AtomicShort] that automatically updates)
     * @param other The other [AtomicShort] to shift right by
     * @return The new [AtomicShort] that automatically updates
     */
    infix fun ushr(other: AtomicShort) = AtomicShortOperationImplementations.transformUshr(this, other)

    /**
     * Invert this short (returns a new [AtomicShort] that automatically updates)
     * @return The new [AtomicShort] that automatically updates
     */
    fun inv() = AtomicShortOperationImplementations.transformInv(this)

    /**
     * Negate this short (returns a new [AtomicShort] that automatically updates)
     * @return The new [AtomicShort] that automatically updates
     */
    fun negate() = AtomicShortOperationImplementations.transformNegate(this)

    /**
     * Destroy the [AtomicShort]
     */
    fun destroy() {}
}

abstract class AtomicShortBase(
    initialValue: Short,
) : ListenableItemImpl(), AtomicShort {

    final override var shortValue: Short = initialValue
        get() = get()
        private set(value) {
            field = value
            notifyListeners()
        }

    val destroyListeners: MutableList<() -> Unit> = mutableListOf()

    override fun addDestroyListener(listener: () -> Unit): () -> Unit {
        destroyListeners.add(listener)
        return { destroyListeners.remove(listener) }
    }

    override fun removeDestroyListener(listener: () -> Unit) {
        destroyListeners.remove(listener)
    }

    protected open fun update(newValue: Short) {
        shortValue = newValue
    }

    override fun get(): Short = shortValue
}

class EditableAtomicShort(
    initialValue: Short,
) : AtomicShortBase(initialValue) {

    public override fun update(newValue: Short) {
        super.update(newValue)
    }
}

fun atomicShortOf(
    initialValue: Short,
): AtomicShort = EditableAtomicShort(initialValue)

fun Short.atomic(): AtomicShort = atomicShortOf(this)

class AutoUpdateAbleAtomicShort(
    initialValue: Short,
) : AtomicShortBase(initialValue), AutoUpdatableAtomicValue<Short> {
    init {
        update(initialValue)
    }

    override fun update(newValue: Short) {
        super.update(newValue)
    }

    override fun destroy() {
        super.destroy()
        destroyListeners.forEach { it() }
    }

    companion object {
        fun create(initialValue: Short): AutoUpdateAbleAtomicShort = AutoUpdateAbleAtomicShort(initialValue)
        fun create(): AutoUpdateAbleAtomicShort = create(0)
        val creator: () -> AutoUpdateAbleAtomicShort = ::create
    }
}
