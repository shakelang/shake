package com.shakelang.util.primitives.atomic

import com.shakelang.util.primitives.calc.shl
import com.shakelang.util.primitives.calc.shr
import com.shakelang.util.primitives.calc.ushr
import kotlin.experimental.and
import kotlin.experimental.inv
import kotlin.experimental.or
import kotlin.experimental.xor
import kotlin.math.pow

// Int Operations
@Suppress("MemberVisibilityCanBePrivate")
object AtomicIntOperationImplementations {

    // Int Operations
    fun plus(source1: AtomicInt, other: Int): Int = (source1.intValue + other)
    fun plus(source1: AtomicInt, source2: AtomicInt): Int = (source1.intValue + source2.intValue)
    fun minus(source1: AtomicInt, other: Int): Int = (source1.intValue - other)
    fun minus(source1: AtomicInt, source2: AtomicInt): Int = (source1.intValue - source2.intValue)
    fun times(source1: AtomicInt, other: Int): Int = (source1.intValue * other)
    fun times(source1: AtomicInt, source2: AtomicInt): Int = source1.intValue * source2.intValue
    fun div(source1: AtomicInt, other: Int): Int = (source1.intValue / other)
    fun div(source1: AtomicInt, source2: AtomicInt): Int = (source1.intValue / source2.intValue)
    fun rem(source1: AtomicInt, other: Int): Int = (source1.intValue % other)
    fun rem(source1: AtomicInt, source2: AtomicInt): Int = (source1.intValue % source2.intValue)
    fun pow(source1: AtomicInt, other: Int): Int = source1.intValue.toDouble().pow(other.toDouble()).toInt()
    fun pow(source1: AtomicInt, source2: AtomicInt): Int = source1.intValue.toDouble().pow(source2.intValue.toDouble()).toInt()
    fun and(source1: AtomicInt, other: Int): Int = source1.intValue and other
    fun and(source1: AtomicInt, source2: AtomicInt): Int = source1.intValue and source2.intValue
    fun or(source1: AtomicInt, other: Int): Int = source1.intValue or other
    fun or(source1: AtomicInt, source2: AtomicInt): Int = source1.intValue or source2.intValue
    fun xor(source1: AtomicInt, other: Int): Int = source1.intValue xor other
    fun xor(source1: AtomicInt, source2: AtomicInt): Int = source1.intValue xor source2.intValue
    fun shl(source1: AtomicInt, other: Int): Int = source1.intValue shl other
    fun shl(source1: AtomicInt, source2: AtomicInt): Int = source1.intValue shl source2.intValue
    fun shr(source1: AtomicInt, other: Int): Int = source1.intValue shr other
    fun shr(source1: AtomicInt, source2: AtomicInt): Int = source1.intValue shr source2.intValue
    fun ushr(source1: AtomicInt, other: Int): Int = source1.intValue ushr other
    fun ushr(source1: AtomicInt, source2: AtomicInt): Int = source1.intValue ushr source2.intValue
    fun inv(source1: AtomicInt): Int = source1.intValue.inv()
    fun negate(source1: AtomicInt): Int = (-source1.intValue)

    val intPlusInt: BinaryOperation<AtomicInt, Int, Int> = ::plus
    val intPlusAtomicInt: BinaryOperation<AtomicInt, AtomicInt, Int> = ::plus
    val intMinusInt: BinaryOperation<AtomicInt, Int, Int> = ::minus
    val intMinusAtomicInt: BinaryOperation<AtomicInt, AtomicInt, Int> = ::minus
    val intTimesInt: BinaryOperation<AtomicInt, Int, Int> = ::times
    val intTimesAtomicInt: BinaryOperation<AtomicInt, AtomicInt, Int> = ::times
    val intDivInt: BinaryOperation<AtomicInt, Int, Int> = ::div
    val intDivAtomicInt: BinaryOperation<AtomicInt, AtomicInt, Int> = ::div
    val intRemInt: BinaryOperation<AtomicInt, Int, Int> = ::rem
    val intRemAtomicInt: BinaryOperation<AtomicInt, AtomicInt, Int> = ::rem
    val intPowInt: BinaryOperation<AtomicInt, Int, Int> = ::pow
    val intPowAtomicInt: BinaryOperation<AtomicInt, AtomicInt, Int> = ::pow
    val intAndInt: BinaryOperation<AtomicInt, Int, Int> = ::and
    val intAndAtomicInt: BinaryOperation<AtomicInt, AtomicInt, Int> = ::and
    val intOrInt: BinaryOperation<AtomicInt, Int, Int> = ::or
    val bytOrAtomicInt: BinaryOperation<AtomicInt, AtomicInt, Int> = ::or
    val intXorInt: BinaryOperation<AtomicInt, Int, Int> = ::xor
    val intXorAtomicInt: BinaryOperation<AtomicInt, AtomicInt, Int> = ::xor
    val intShlInt: BinaryOperation<AtomicInt, Int, Int> = ::shl
    val intShlAtomicInt: BinaryOperation<AtomicInt, AtomicInt, Int> = ::shl
    val intShrInt: BinaryOperation<AtomicInt, Int, Int> = ::shr
    val intShrAtomicInt: BinaryOperation<AtomicInt, AtomicInt, Int> = ::shr
    val intUshrInt: BinaryOperation<AtomicInt, Int, Int> = ::ushr
    val intUshrAtomicInt: BinaryOperation<AtomicInt, AtomicInt, Int> = ::ushr
    val intInv: UnaryOperation<AtomicInt, Int> = ::inv
    val intNegate: UnaryOperation<AtomicInt, Int> = ::negate

    fun transformPlus(source1: AtomicInt, other: Int): AtomicInt = transform1(source1, other, intPlusInt, AutoUpdateAbleAtomicInt.creator)
    fun transformPlus(source1: AtomicInt, other: AtomicInt): AtomicInt = transform2(source1, other, intPlusAtomicInt, AutoUpdateAbleAtomicInt.creator)
    fun transformMinus(source1: AtomicInt, other: Int): AtomicInt = transform1(source1, other, intMinusInt, AutoUpdateAbleAtomicInt.creator)
    fun transformMinus(source1: AtomicInt, other: AtomicInt): AtomicInt = transform2(source1, other, intMinusAtomicInt, AutoUpdateAbleAtomicInt.creator)
    fun transformTimes(source1: AtomicInt, other: Int): AtomicInt = transform1(source1, other, intTimesInt, AutoUpdateAbleAtomicInt.creator)
    fun transformTimes(source1: AtomicInt, other: AtomicInt): AtomicInt = transform2(source1, other, intTimesAtomicInt, AutoUpdateAbleAtomicInt.creator)
    fun transformDiv(source1: AtomicInt, other: Int): AtomicInt = transform1(source1, other, intDivInt, AutoUpdateAbleAtomicInt.creator)
    fun transformDiv(source1: AtomicInt, other: AtomicInt): AtomicInt = transform2(source1, other, intDivAtomicInt, AutoUpdateAbleAtomicInt.creator)
    fun transformRem(source1: AtomicInt, other: Int): AtomicInt = transform1(source1, other, intRemInt, AutoUpdateAbleAtomicInt.creator)
    fun transformRem(source1: AtomicInt, other: AtomicInt): AtomicInt = transform2(source1, other, intRemAtomicInt, AutoUpdateAbleAtomicInt.creator)
    fun transformPow(source1: AtomicInt, other: Int): AtomicInt = transform1(source1, other, intPowInt, AutoUpdateAbleAtomicInt.creator)
    fun transformPow(source1: AtomicInt, other: AtomicInt): AtomicInt = transform2(source1, other, intPowAtomicInt, AutoUpdateAbleAtomicInt.creator)
    fun transformAnd(source1: AtomicInt, other: Int): AtomicInt = transform1(source1, other, intAndInt, AutoUpdateAbleAtomicInt.creator)
    fun transformAnd(source1: AtomicInt, other: AtomicInt): AtomicInt = transform2(source1, other, intAndAtomicInt, AutoUpdateAbleAtomicInt.creator)
    fun transformOr(source1: AtomicInt, other: Int): AtomicInt = transform1(source1, other, intOrInt, AutoUpdateAbleAtomicInt.creator)
    fun transformOr(source1: AtomicInt, other: AtomicInt): AtomicInt = transform2(source1, other, bytOrAtomicInt, AutoUpdateAbleAtomicInt.creator)
    fun transformXor(source1: AtomicInt, other: Int): AtomicInt = transform1(source1, other, intXorInt, AutoUpdateAbleAtomicInt.creator)
    fun transformXor(source1: AtomicInt, other: AtomicInt): AtomicInt = transform2(source1, other, intXorAtomicInt, AutoUpdateAbleAtomicInt.creator)
    fun transformShl(source1: AtomicInt, other: Int): AtomicInt = transform1(source1, other, intShlInt, AutoUpdateAbleAtomicInt.creator)
    fun transformShl(source1: AtomicInt, other: AtomicInt): AtomicInt = transform2(source1, other, intShlAtomicInt, AutoUpdateAbleAtomicInt.creator)
    fun transformShr(source1: AtomicInt, other: Int): AtomicInt = transform1(source1, other, intShrInt, AutoUpdateAbleAtomicInt.creator)
    fun transformShr(source1: AtomicInt, other: AtomicInt): AtomicInt = transform2(source1, other, intShrAtomicInt, AutoUpdateAbleAtomicInt.creator)
    fun transformUshr(source1: AtomicInt, other: Int): AtomicInt = transform1(source1, other, intUshrInt, AutoUpdateAbleAtomicInt.creator)
    fun transformUshr(source1: AtomicInt, other: AtomicInt): AtomicInt = transform2(source1, other, intUshrAtomicInt, AutoUpdateAbleAtomicInt.creator)
    fun transformInv(source1: AtomicInt): AtomicInt = transform1(source1, intInv, AutoUpdateAbleAtomicInt.creator)
    fun transformNegate(source1: AtomicInt): AtomicInt = transform1(source1, intNegate, AutoUpdateAbleAtomicInt.creator)
}

/**
 * An atomic int value
 */
interface AtomicInt : AtomicValue {

    /**
     * The value of the atomic int
     */
    val intValue: Int

    /**
     * Get the value of the atomic int
     */
    fun get(): Int

    /**
     * Add another int to this int (returns a new [AtomicInt] that automatically updates)
     * @param other The other int to add
     * @return The new [AtomicInt] that automatically updates
     */
    operator fun plus(other: Int) = AtomicIntOperationImplementations.transformPlus(this, other)

    /**
     * Add another [AtomicInt] to this int (returns a new [AtomicInt] that automatically updates)
     * @param other The other [AtomicInt] to add
     * @return The new [AtomicInt] that automatically updates
     */
    operator fun plus(other: AtomicInt) = AtomicIntOperationImplementations.transformPlus(this, other)

    /**
     * Subtract another int from this int (returns a new [AtomicInt] that automatically updates)
     * @param other The other int to subtract
     * @return The new [AtomicInt] that automatically updates
     */
    operator fun minus(other: Int) = AtomicIntOperationImplementations.transformMinus(this, other)

    /**
     * Subtract another [AtomicInt] from this int (returns a new [AtomicInt] that automatically updates)
     * @param other The other [AtomicInt] to subtract
     * @return The new [AtomicInt] that automatically updates
     */
    operator fun minus(other: AtomicInt) = AtomicIntOperationImplementations.transformMinus(this, other)

    /**
     * Multiply this int with another int (returns a new [AtomicInt] that automatically updates)
     * @param other The other int to multiply with
     * @return The new [AtomicInt] that automatically updates
     */
    operator fun times(other: Int) = AtomicIntOperationImplementations.transformTimes(this, other)

    /**
     * Multiply this int with another [AtomicInt] (returns a new [AtomicInt] that automatically updates)
     * @param other The other [AtomicInt] to multiply with
     * @return The new [AtomicInt] that automatically updates
     */
    operator fun times(other: AtomicInt) = AtomicIntOperationImplementations.transformTimes(this, other)

    /**
     * Divide this int by another int (returns a new [AtomicInt] that automatically updates)
     * @param other The other int to divide by
     * @return The new [AtomicInt] that automatically updates
     */
    operator fun div(other: Int) = AtomicIntOperationImplementations.transformDiv(this, other)

    /**
     * Divide this int by another [AtomicInt] (returns a new [AtomicInt] that automatically updates)
     * @param other The other [AtomicInt] to divide by
     * @return The new [AtomicInt] that automatically updates
     */
    operator fun div(other: AtomicInt) = AtomicIntOperationImplementations.transformDiv(this, other)

    /**
     * Modulo this int by another int (returns a new [AtomicInt] that automatically updates)
     * @param other The other int to modulo by
     * @return The new [AtomicInt] that automatically updates
     */
    operator fun rem(other: Int) = AtomicIntOperationImplementations.transformRem(this, other)

    /**
     * Modulo this int by another [AtomicInt] (returns a new [AtomicInt] that automatically updates)
     * @param other The other [AtomicInt] to modulo by
     * @return The new [AtomicInt] that automatically updates
     */
    operator fun rem(other: AtomicInt) = AtomicIntOperationImplementations.transformRem(this, other)

    /**
     * Power this int by another int (returns a new [AtomicInt] that automatically updates)
     * @param other The other int to power by
     * @return The new [AtomicInt] that automatically updates
     */
    infix fun pow(other: Int) = AtomicIntOperationImplementations.transformPow(this, other)

    /**
     * Power this int by another [AtomicInt] (returns a new [AtomicInt] that automatically updates)
     * @param other The other [AtomicInt] to power by
     * @return The new [AtomicInt] that automatically updates
     */
    infix fun pow(other: AtomicInt) = AtomicIntOperationImplementations.transformPow(this, other)

    /**
     * And this int with another int (returns a new [AtomicInt] that automatically updates)
     * @param other The other int to and with
     * @return The new [AtomicInt] that automatically updates
     */
    infix fun and(other: Int) = AtomicIntOperationImplementations.transformAnd(this, other)

    /**
     * And this int with another [AtomicInt] (returns a new [AtomicInt] that automatically updates)
     * @param other The other [AtomicInt] to and with
     * @return The new [AtomicInt] that automatically updates
     */
    infix fun and(other: AtomicInt) = AtomicIntOperationImplementations.transformAnd(this, other)

    /**
     * Or this int with another int (returns a new [AtomicInt] that automatically updates)
     * @param other The other int to or with
     * @return The new [AtomicInt] that automatically updates
     */
    infix fun or(other: Int) = AtomicIntOperationImplementations.transformOr(this, other)

    /**
     * Or this int with another [AtomicInt] (returns a new [AtomicInt] that automatically updates)
     * @param other The other [AtomicInt] to or with
     * @return The new [AtomicInt] that automatically updates
     */
    infix fun or(other: AtomicInt) = AtomicIntOperationImplementations.transformOr(this, other)

    /**
     * Xor this int with another int (returns a new [AtomicInt] that automatically updates)
     * @param other The other int to xor with
     * @return The new [AtomicInt] that automatically updates
     */
    infix fun xor(other: Int) = AtomicIntOperationImplementations.transformXor(this, other)

    /**
     * Xor this int with another [AtomicInt] (returns a new [AtomicInt] that automatically updates)
     * @param other The other [AtomicInt] to xor with
     * @return The new [AtomicInt] that automatically updates
     */
    infix fun xor(other: AtomicInt) = AtomicIntOperationImplementations.transformXor(this, other)

    /**
     * Shift this int left by another int (returns a new [AtomicInt] that automatically updates)
     * @param other The other int to shift left by
     * @return The new [AtomicInt] that automatically updates
     */
    infix fun shl(other: Int) = AtomicIntOperationImplementations.transformShl(this, other)

    /**
     * Shift this int left by another [AtomicInt] (returns a new [AtomicInt] that automatically updates)
     * @param other The other [AtomicInt] to shift left by
     * @return The new [AtomicInt] that automatically updates
     */
    infix fun shl(other: AtomicInt) = AtomicIntOperationImplementations.transformShl(this, other)

    /**
     * Shift this int right by another int (returns a new [AtomicInt] that automatically updates)
     * @param other The other int to shift right by
     * @return The new [AtomicInt] that automatically updates
     */
    infix fun shr(other: Int) = AtomicIntOperationImplementations.transformShr(this, other)

    /**
     * Shift this int right by another [AtomicInt] (returns a new [AtomicInt] that automatically updates)
     * @param other The other [AtomicInt] to shift right by
     * @return The new [AtomicInt] that automatically updates
     */
    infix fun shr(other: AtomicInt) = AtomicIntOperationImplementations.transformShr(this, other)

    /**
     * Shift this int right by another int (returns a new [AtomicInt] that automatically updates)
     * @param other The other int to shift right by
     * @return The new [AtomicInt] that automatically updates
     */
    infix fun ushr(other: Int) = AtomicIntOperationImplementations.transformUshr(this, other)

    /**
     * Shift this int right by another [AtomicInt] (returns a new [AtomicInt] that automatically updates)
     * @param other The other [AtomicInt] to shift right by
     * @return The new [AtomicInt] that automatically updates
     */
    infix fun ushr(other: AtomicInt) = AtomicIntOperationImplementations.transformUshr(this, other)

    /**
     * Invert this int (returns a new [AtomicInt] that automatically updates)
     * @return The new [AtomicInt] that automatically updates
     */
    fun inv() = AtomicIntOperationImplementations.transformInv(this)

    /**
     * Negate this int (returns a new [AtomicInt] that automatically updates)
     * @return The new [AtomicInt] that automatically updates
     */
    fun negate() = AtomicIntOperationImplementations.transformNegate(this)

    /**
     * Destroy the [AtomicInt]
     */
    fun destroy() {}
}

abstract class AtomicIntBase(
    initialValue: Int,
) : ListenableItemImpl(), AtomicInt {

    final override var intValue: Int = initialValue
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

    protected open fun update(newValue: Int) {
        intValue = newValue
    }

    override fun get(): Int = intValue
}

class EditableAtomicInt(
    initialValue: Int,
) : AtomicIntBase(initialValue) {

    public override fun update(newValue: Int) {
        super.update(newValue)
    }
}

fun atomicIntOf(
    initialValue: Int,
): AtomicInt = EditableAtomicInt(initialValue)

fun Int.atomic(): AtomicInt = atomicIntOf(this)

class AutoUpdateAbleAtomicInt(
    initialValue: Int,
) : AtomicIntBase(initialValue), AutoUpdatableAtomicValue<Int> {
    init {
        update(initialValue)
    }

    override fun update(newValue: Int) {
        super.update(newValue)
    }

    override fun destroy() {
        super.destroy()
        destroyListeners.forEach { it() }
    }

    companion object {
        fun create(initialValue: Int): AutoUpdateAbleAtomicInt = AutoUpdateAbleAtomicInt(initialValue)
        fun create(): AutoUpdateAbleAtomicInt = create(0)
        val creator: () -> AutoUpdateAbleAtomicInt = ::create
    }
}
