package com.shakelang.util.primitives.atomic

import com.shakelang.util.primitives.calc.shl
import com.shakelang.util.primitives.calc.shr
import com.shakelang.util.primitives.calc.ushr
import kotlin.experimental.and
import kotlin.experimental.inv
import kotlin.experimental.or
import kotlin.experimental.xor
import kotlin.math.pow

// Long Operations
@Suppress("MemberVisibilityCanBePrivate")
object AtomicLongOperationImplementations {

    // Long Operations
    fun plus(source1: AtomicLong, other: Long): Long = (source1.longValue + other).toLong()
    fun plus(source1: AtomicLong, source2: AtomicLong): Long = (source1.longValue + source2.longValue).toLong()
    fun minus(source1: AtomicLong, other: Long): Long = (source1.longValue - other).toLong()
    fun minus(source1: AtomicLong, source2: AtomicLong): Long = (source1.longValue - source2.longValue).toLong()
    fun times(source1: AtomicLong, other: Long): Long = (source1.longValue * other).toLong()
    fun times(source1: AtomicLong, source2: AtomicLong): Long = (source1.longValue * source2.longValue).toLong()
    fun div(source1: AtomicLong, other: Long): Long = (source1.longValue / other).toLong()
    fun div(source1: AtomicLong, source2: AtomicLong): Long = (source1.longValue / source2.longValue).toLong()
    fun rem(source1: AtomicLong, other: Long): Long = (source1.longValue % other).toLong()
    fun rem(source1: AtomicLong, source2: AtomicLong): Long = (source1.longValue % source2.longValue).toLong()
    fun pow(source1: AtomicLong, other: Long): Long = source1.longValue.toDouble().pow(other.toDouble()).toLong().toLong()
    fun pow(source1: AtomicLong, source2: AtomicLong): Long = source1.longValue.toDouble().pow(source2.longValue.toDouble()).toLong().toLong()
    fun and(source1: AtomicLong, other: Long): Long = source1.longValue and other
    fun and(source1: AtomicLong, source2: AtomicLong): Long = source1.longValue and source2.longValue
    fun or(source1: AtomicLong, other: Long): Long = source1.longValue or other
    fun or(source1: AtomicLong, source2: AtomicLong): Long = source1.longValue or source2.longValue
    fun xor(source1: AtomicLong, other: Long): Long = source1.longValue xor other
    fun xor(source1: AtomicLong, source2: AtomicLong): Long = source1.longValue xor source2.longValue
    fun shl(source1: AtomicLong, other: Long): Long = source1.longValue shl other
    fun shl(source1: AtomicLong, source2: AtomicLong): Long = source1.longValue shl source2.longValue
    fun shr(source1: AtomicLong, other: Long): Long = source1.longValue shr other
    fun shr(source1: AtomicLong, source2: AtomicLong): Long = source1.longValue shr source2.longValue
    fun ushr(source1: AtomicLong, other: Long): Long = source1.longValue ushr other
    fun ushr(source1: AtomicLong, source2: AtomicLong): Long = source1.longValue ushr source2.longValue
    fun inv(source1: AtomicLong): Long = source1.longValue.inv()
    fun negate(source1: AtomicLong): Long = (-source1.longValue).toLong()

    val longPlusLong: BinaryOperation<AtomicLong, Long, Long> = ::plus
    val longPlusAtomicLong: BinaryOperation<AtomicLong, AtomicLong, Long> = ::plus
    val longMinusLong: BinaryOperation<AtomicLong, Long, Long> = ::minus
    val longMinusAtomicLong: BinaryOperation<AtomicLong, AtomicLong, Long> = ::minus
    val longTimesLong: BinaryOperation<AtomicLong, Long, Long> = ::times
    val longTimesAtomicLong: BinaryOperation<AtomicLong, AtomicLong, Long> = ::times
    val longDivLong: BinaryOperation<AtomicLong, Long, Long> = ::div
    val longDivAtomicLong: BinaryOperation<AtomicLong, AtomicLong, Long> = ::div
    val longRemLong: BinaryOperation<AtomicLong, Long, Long> = ::rem
    val longRemAtomicLong: BinaryOperation<AtomicLong, AtomicLong, Long> = ::rem
    val longPowLong: BinaryOperation<AtomicLong, Long, Long> = ::pow
    val longPowAtomicLong: BinaryOperation<AtomicLong, AtomicLong, Long> = ::pow
    val longAndLong: BinaryOperation<AtomicLong, Long, Long> = ::and
    val longAndAtomicLong: BinaryOperation<AtomicLong, AtomicLong, Long> = ::and
    val longOrLong: BinaryOperation<AtomicLong, Long, Long> = ::or
    val bytOrAtomicLong: BinaryOperation<AtomicLong, AtomicLong, Long> = ::or
    val longXorLong: BinaryOperation<AtomicLong, Long, Long> = ::xor
    val longXorAtomicLong: BinaryOperation<AtomicLong, AtomicLong, Long> = ::xor
    val longShlLong: BinaryOperation<AtomicLong, Long, Long> = ::shl
    val longShlAtomicLong: BinaryOperation<AtomicLong, AtomicLong, Long> = ::shl
    val longShrLong: BinaryOperation<AtomicLong, Long, Long> = ::shr
    val longShrAtomicLong: BinaryOperation<AtomicLong, AtomicLong, Long> = ::shr
    val longUshrLong: BinaryOperation<AtomicLong, Long, Long> = ::ushr
    val longUshrAtomicLong: BinaryOperation<AtomicLong, AtomicLong, Long> = ::ushr
    val longInv: UnaryOperation<AtomicLong, Long> = ::inv
    val longNegate: UnaryOperation<AtomicLong, Long> = ::negate

    fun transform1(
        source1: AtomicLong,
        operation: UnaryOperation<AtomicLong, Long>,
    ): AtomicLong {
        val target = AutoUpdateAbleAtomicLong(
            0,
        )
        val connection = connection1(source1, target) {
            target.update(operation.invoke(it.source1))
        }
        target.update(operation.invoke(connection.source1))
        target.destroyListeners.add(connection::destroy)
        return target
    }

    fun <T> transform1(
        source1: AtomicLong,
        constant: T,
        operation: BinaryOperation<AtomicLong, T, Long>,
    ): AtomicLong {
        val target = AutoUpdateAbleAtomicLong(
            0,
        )
        val connection = connection1(source1, target) {
            target.update(operation.invoke(it.source1, constant))
        }
        target.update(operation.invoke(connection.source1, constant))
        target.destroyListeners.add(connection::destroy)
        return target
    }

    fun transform2(
        source1: AtomicLong,
        source2: AtomicLong,
        concat: BinaryOperation<AtomicLong, AtomicLong, Long>,
    ): AtomicLong {
        val target = AutoUpdateAbleAtomicLong(
            0,
        )
        val connection = connection2(source1, source2, target) {
            target.update(concat.invoke(it.source1, it.source2))
        }
        target.update(concat(source1, source2))
        target.destroyListeners.add(connection::destroy)
        return target
    }

    fun transformPlus(source1: AtomicLong, other: Long): AtomicLong = transform1(source1, other, longPlusLong)
    fun transformPlus(source1: AtomicLong, other: AtomicLong): AtomicLong = transform2(source1, other, longPlusAtomicLong)
    fun transformMinus(source1: AtomicLong, other: Long): AtomicLong = transform1(source1, other, longMinusLong)
    fun transformMinus(source1: AtomicLong, other: AtomicLong): AtomicLong = transform2(source1, other, longMinusAtomicLong)
    fun transformTimes(source1: AtomicLong, other: Long): AtomicLong = transform1(source1, other, longTimesLong)
    fun transformTimes(source1: AtomicLong, other: AtomicLong): AtomicLong = transform2(source1, other, longTimesAtomicLong)
    fun transformDiv(source1: AtomicLong, other: Long): AtomicLong = transform1(source1, other, longDivLong)
    fun transformDiv(source1: AtomicLong, other: AtomicLong): AtomicLong = transform2(source1, other, longDivAtomicLong)
    fun transformRem(source1: AtomicLong, other: Long): AtomicLong = transform1(source1, other, longRemLong)
    fun transformRem(source1: AtomicLong, other: AtomicLong): AtomicLong = transform2(source1, other, longRemAtomicLong)
    fun transformPow(source1: AtomicLong, other: Long): AtomicLong = transform1(source1, other, longPowLong)
    fun transformPow(source1: AtomicLong, other: AtomicLong): AtomicLong = transform2(source1, other, longPowAtomicLong)
    fun transformAnd(source1: AtomicLong, other: Long): AtomicLong = transform1(source1, other, longAndLong)
    fun transformAnd(source1: AtomicLong, other: AtomicLong): AtomicLong = transform2(source1, other, longAndAtomicLong)
    fun transformOr(source1: AtomicLong, other: Long): AtomicLong = transform1(source1, other, longOrLong)
    fun transformOr(source1: AtomicLong, other: AtomicLong): AtomicLong = transform2(source1, other, bytOrAtomicLong)
    fun transformXor(source1: AtomicLong, other: Long): AtomicLong = transform1(source1, other, longXorLong)
    fun transformXor(source1: AtomicLong, other: AtomicLong): AtomicLong = transform2(source1, other, longXorAtomicLong)
    fun transformShl(source1: AtomicLong, other: Long): AtomicLong = transform1(source1, other, longShlLong)
    fun transformShl(source1: AtomicLong, other: AtomicLong): AtomicLong = transform2(source1, other, longShlAtomicLong)
    fun transformShr(source1: AtomicLong, other: Long): AtomicLong = transform1(source1, other, longShrLong)
    fun transformShr(source1: AtomicLong, other: AtomicLong): AtomicLong = transform2(source1, other, longShrAtomicLong)
    fun transformUshr(source1: AtomicLong, other: Long): AtomicLong = transform1(source1, other, longUshrLong)
    fun transformUshr(source1: AtomicLong, other: AtomicLong): AtomicLong = transform2(source1, other, longUshrAtomicLong)
    fun transformInv(source1: AtomicLong): AtomicLong = transform1(source1, longInv)
    fun transformNegate(source1: AtomicLong): AtomicLong = transform1(source1, longNegate)
}

/**
 * An atomic long value
 */
interface AtomicLong : AtomicValue {

    /**
     * The value of the atomic long
     */
    val longValue: Long

    /**
     * Get the value of the atomic long
     */
    fun get(): Long

    /**
     * Add another long to this long (returns a new [AtomicLong] that automatically updates)
     * @param other The other long to add
     * @return The new [AtomicLong] that automatically updates
     */
    operator fun plus(other: Long) = AtomicLongOperationImplementations.transformPlus(this, other)

    /**
     * Add another [AtomicLong] to this long (returns a new [AtomicLong] that automatically updates)
     * @param other The other [AtomicLong] to add
     * @return The new [AtomicLong] that automatically updates
     */
    operator fun plus(other: AtomicLong) = AtomicLongOperationImplementations.transformPlus(this, other)

    /**
     * Subtract another long from this long (returns a new [AtomicLong] that automatically updates)
     * @param other The other long to subtract
     * @return The new [AtomicLong] that automatically updates
     */
    operator fun minus(other: Long) = AtomicLongOperationImplementations.transformMinus(this, other)

    /**
     * Subtract another [AtomicLong] from this long (returns a new [AtomicLong] that automatically updates)
     * @param other The other [AtomicLong] to subtract
     * @return The new [AtomicLong] that automatically updates
     */
    operator fun minus(other: AtomicLong) = AtomicLongOperationImplementations.transformMinus(this, other)

    /**
     * Multiply this long with another long (returns a new [AtomicLong] that automatically updates)
     * @param other The other long to multiply with
     * @return The new [AtomicLong] that automatically updates
     */
    operator fun times(other: Long) = AtomicLongOperationImplementations.transformTimes(this, other)

    /**
     * Multiply this long with another [AtomicLong] (returns a new [AtomicLong] that automatically updates)
     * @param other The other [AtomicLong] to multiply with
     * @return The new [AtomicLong] that automatically updates
     */
    operator fun times(other: AtomicLong) = AtomicLongOperationImplementations.transformTimes(this, other)

    /**
     * Divide this long by another long (returns a new [AtomicLong] that automatically updates)
     * @param other The other long to divide by
     * @return The new [AtomicLong] that automatically updates
     */
    operator fun div(other: Long) = AtomicLongOperationImplementations.transformDiv(this, other)

    /**
     * Divide this long by another [AtomicLong] (returns a new [AtomicLong] that automatically updates)
     * @param other The other [AtomicLong] to divide by
     * @return The new [AtomicLong] that automatically updates
     */
    operator fun div(other: AtomicLong) = AtomicLongOperationImplementations.transformDiv(this, other)

    /**
     * Modulo this long by another long (returns a new [AtomicLong] that automatically updates)
     * @param other The other long to modulo by
     * @return The new [AtomicLong] that automatically updates
     */
    operator fun rem(other: Long) = AtomicLongOperationImplementations.transformRem(this, other)

    /**
     * Modulo this long by another [AtomicLong] (returns a new [AtomicLong] that automatically updates)
     * @param other The other [AtomicLong] to modulo by
     * @return The new [AtomicLong] that automatically updates
     */
    operator fun rem(other: AtomicLong) = AtomicLongOperationImplementations.transformRem(this, other)

    /**
     * Power this long by another long (returns a new [AtomicLong] that automatically updates)
     * @param other The other long to power by
     * @return The new [AtomicLong] that automatically updates
     */
    infix fun pow(other: Long) = AtomicLongOperationImplementations.transformPow(this, other)

    /**
     * Power this long by another [AtomicLong] (returns a new [AtomicLong] that automatically updates)
     * @param other The other [AtomicLong] to power by
     * @return The new [AtomicLong] that automatically updates
     */
    infix fun pow(other: AtomicLong) = AtomicLongOperationImplementations.transformPow(this, other)

    /**
     * And this long with another long (returns a new [AtomicLong] that automatically updates)
     * @param other The other long to and with
     * @return The new [AtomicLong] that automatically updates
     */
    infix fun and(other: Long) = AtomicLongOperationImplementations.transformAnd(this, other)

    /**
     * And this long with another [AtomicLong] (returns a new [AtomicLong] that automatically updates)
     * @param other The other [AtomicLong] to and with
     * @return The new [AtomicLong] that automatically updates
     */
    infix fun and(other: AtomicLong) = AtomicLongOperationImplementations.transformAnd(this, other)

    /**
     * Or this long with another long (returns a new [AtomicLong] that automatically updates)
     * @param other The other long to or with
     * @return The new [AtomicLong] that automatically updates
     */
    infix fun or(other: Long) = AtomicLongOperationImplementations.transformOr(this, other)

    /**
     * Or this long with another [AtomicLong] (returns a new [AtomicLong] that automatically updates)
     * @param other The other [AtomicLong] to or with
     * @return The new [AtomicLong] that automatically updates
     */
    infix fun or(other: AtomicLong) = AtomicLongOperationImplementations.transformOr(this, other)

    /**
     * Xor this long with another long (returns a new [AtomicLong] that automatically updates)
     * @param other The other long to xor with
     * @return The new [AtomicLong] that automatically updates
     */
    infix fun xor(other: Long) = AtomicLongOperationImplementations.transformXor(this, other)

    /**
     * Xor this long with another [AtomicLong] (returns a new [AtomicLong] that automatically updates)
     * @param other The other [AtomicLong] to xor with
     * @return The new [AtomicLong] that automatically updates
     */
    infix fun xor(other: AtomicLong) = AtomicLongOperationImplementations.transformXor(this, other)

    /**
     * Shift this long left by another long (returns a new [AtomicLong] that automatically updates)
     * @param other The other long to shift left by
     * @return The new [AtomicLong] that automatically updates
     */
    infix fun shl(other: Long) = AtomicLongOperationImplementations.transformShl(this, other)

    /**
     * Shift this long left by another [AtomicLong] (returns a new [AtomicLong] that automatically updates)
     * @param other The other [AtomicLong] to shift left by
     * @return The new [AtomicLong] that automatically updates
     */
    infix fun shl(other: AtomicLong) = AtomicLongOperationImplementations.transformShl(this, other)

    /**
     * Shift this long right by another long (returns a new [AtomicLong] that automatically updates)
     * @param other The other long to shift right by
     * @return The new [AtomicLong] that automatically updates
     */
    infix fun shr(other: Long) = AtomicLongOperationImplementations.transformShr(this, other)

    /**
     * Shift this long right by another [AtomicLong] (returns a new [AtomicLong] that automatically updates)
     * @param other The other [AtomicLong] to shift right by
     * @return The new [AtomicLong] that automatically updates
     */
    infix fun shr(other: AtomicLong) = AtomicLongOperationImplementations.transformShr(this, other)

    /**
     * Shift this long right by another long (returns a new [AtomicLong] that automatically updates)
     * @param other The other long to shift right by
     * @return The new [AtomicLong] that automatically updates
     */
    infix fun ushr(other: Long) = AtomicLongOperationImplementations.transformUshr(this, other)

    /**
     * Shift this long right by another [AtomicLong] (returns a new [AtomicLong] that automatically updates)
     * @param other The other [AtomicLong] to shift right by
     * @return The new [AtomicLong] that automatically updates
     */
    infix fun ushr(other: AtomicLong) = AtomicLongOperationImplementations.transformUshr(this, other)

    /**
     * Invert this long (returns a new [AtomicLong] that automatically updates)
     * @return The new [AtomicLong] that automatically updates
     */
    fun inv() = AtomicLongOperationImplementations.transformInv(this)

    /**
     * Negate this long (returns a new [AtomicLong] that automatically updates)
     * @return The new [AtomicLong] that automatically updates
     */
    fun negate() = AtomicLongOperationImplementations.transformNegate(this)

    /**
     * Destroy the [AtomicLong]
     */
    fun destroy() {}
}

abstract class AtomicLongBase(
    initialValue: Long,
) : ListenableItemImpl(), AtomicLong {

    final override var longValue: Long = initialValue
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

    protected open fun update(newValue: Long) {
        longValue = newValue
    }

    override fun get(): Long = longValue
}

class EditableAtomicLong(
    initialValue: Long,
) : AtomicLongBase(initialValue) {

    public override fun update(newValue: Long) {
        super.update(newValue)
    }
}

fun atomicLongOf(
    initialValue: Long,
): AtomicLong = EditableAtomicLong(initialValue)

fun Long.atomic(): AtomicLong = atomicLongOf(this)

class AutoUpdateAbleAtomicLong(
    initialValue: Long,
) : AtomicLongBase(initialValue), AutoUpdatableAtomicValue<Long> {
    init {
        update(initialValue)
    }

    override fun update(newValue: Long) {
        super.update(newValue)
    }

    override fun destroy() {
        super.destroy()
        destroyListeners.forEach { it() }
    }

    companion object {
        fun create(
            initialValue: Long,
        ): AutoUpdateAbleAtomicLong = AutoUpdateAbleAtomicLong(initialValue)

        fun create(): AutoUpdateAbleAtomicLong = create(0)

        val creator: () -> AutoUpdateAbleAtomicLong = ::create
    }
}
