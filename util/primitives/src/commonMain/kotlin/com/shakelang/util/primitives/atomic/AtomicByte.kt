import com.shakelang.util.primitives.atomic.*
import com.shakelang.util.primitives.calc.shl
import com.shakelang.util.primitives.calc.shr
import com.shakelang.util.primitives.calc.ushr
import kotlin.experimental.and
import kotlin.experimental.inv
import kotlin.experimental.or
import kotlin.experimental.xor
import kotlin.math.pow

// Byte Operations
@Suppress("MemberVisibilityCanBePrivate")
object AtomicByteOperationImplementations {

    // Byte Operations
    fun plus(source1: AtomicByte, other: Byte): Byte = (source1.byteValue + other).toByte()
    fun plus(source1: AtomicByte, source2: AtomicByte): Byte = (source1.byteValue + source2.byteValue).toByte()
    fun minus(source1: AtomicByte, other: Byte): Byte = (source1.byteValue - other).toByte()
    fun minus(source1: AtomicByte, source2: AtomicByte): Byte = (source1.byteValue - source2.byteValue).toByte()
    fun times(source1: AtomicByte, other: Byte): Byte = (source1.byteValue * other).toByte()
    fun times(source1: AtomicByte, source2: AtomicByte): Byte = (source1.byteValue * source2.byteValue).toByte()
    fun div(source1: AtomicByte, other: Byte): Byte = (source1.byteValue / other).toByte()
    fun div(source1: AtomicByte, source2: AtomicByte): Byte = (source1.byteValue / source2.byteValue).toByte()
    fun rem(source1: AtomicByte, other: Byte): Byte = (source1.byteValue % other).toByte()
    fun rem(source1: AtomicByte, source2: AtomicByte): Byte = (source1.byteValue % source2.byteValue).toByte()
    fun pow(source1: AtomicByte, other: Byte): Byte = source1.byteValue.toDouble().pow(other.toDouble()).toInt().toByte()
    fun pow(source1: AtomicByte, source2: AtomicByte): Byte = source1.byteValue.toDouble().pow(source2.byteValue.toDouble()).toInt().toByte()
    fun and(source1: AtomicByte, other: Byte): Byte = source1.byteValue and other
    fun and(source1: AtomicByte, source2: AtomicByte): Byte = source1.byteValue and source2.byteValue
    fun or(source1: AtomicByte, other: Byte): Byte = source1.byteValue or other
    fun or(source1: AtomicByte, source2: AtomicByte): Byte = source1.byteValue or source2.byteValue
    fun xor(source1: AtomicByte, other: Byte): Byte = source1.byteValue xor other
    fun xor(source1: AtomicByte, source2: AtomicByte): Byte = source1.byteValue xor source2.byteValue
    fun shl(source1: AtomicByte, other: Byte): Byte = source1.byteValue shl other
    fun shl(source1: AtomicByte, source2: AtomicByte): Byte = source1.byteValue shl source2.byteValue
    fun shr(source1: AtomicByte, other: Byte): Byte = source1.byteValue shr other
    fun shr(source1: AtomicByte, source2: AtomicByte): Byte = source1.byteValue shr source2.byteValue
    fun ushr(source1: AtomicByte, other: Byte): Byte = source1.byteValue ushr other
    fun ushr(source1: AtomicByte, source2: AtomicByte): Byte = source1.byteValue ushr source2.byteValue
    fun inv(source1: AtomicByte): Byte = source1.byteValue.inv()
    fun negate(source1: AtomicByte): Byte = (-source1.byteValue).toByte()

    val bytePlusByte: BinaryOperation<AtomicByte, Byte, Byte> = ::plus
    val bytePlusAtomicByte: BinaryOperation<AtomicByte, AtomicByte, Byte> = ::plus
    val byteMinusByte: BinaryOperation<AtomicByte, Byte, Byte> = ::minus
    val byteMinusAtomicByte: BinaryOperation<AtomicByte, AtomicByte, Byte> = ::minus
    val byteTimesByte: BinaryOperation<AtomicByte, Byte, Byte> = ::times
    val byteTimesAtomicByte: BinaryOperation<AtomicByte, AtomicByte, Byte> = ::times
    val byteDivByte: BinaryOperation<AtomicByte, Byte, Byte> = ::div
    val byteDivAtomicByte: BinaryOperation<AtomicByte, AtomicByte, Byte> = ::div
    val byteRemByte: BinaryOperation<AtomicByte, Byte, Byte> = ::rem
    val byteRemAtomicByte: BinaryOperation<AtomicByte, AtomicByte, Byte> = ::rem
    val bytePowByte: BinaryOperation<AtomicByte, Byte, Byte> = ::pow
    val bytePowAtomicByte: BinaryOperation<AtomicByte, AtomicByte, Byte> = ::pow
    val byteAndByte: BinaryOperation<AtomicByte, Byte, Byte> = ::and
    val byteAndAtomicByte: BinaryOperation<AtomicByte, AtomicByte, Byte> = ::and
    val byteOrByte: BinaryOperation<AtomicByte, Byte, Byte> = ::or
    val bytOrAtomicByte: BinaryOperation<AtomicByte, AtomicByte, Byte> = ::or
    val byteXorByte: BinaryOperation<AtomicByte, Byte, Byte> = ::xor
    val byteXorAtomicByte: BinaryOperation<AtomicByte, AtomicByte, Byte> = ::xor
    val byteShlByte: BinaryOperation<AtomicByte, Byte, Byte> = ::shl
    val byteShlAtomicByte: BinaryOperation<AtomicByte, AtomicByte, Byte> = ::shl
    val byteShrByte: BinaryOperation<AtomicByte, Byte, Byte> = ::shr
    val byteShrAtomicByte: BinaryOperation<AtomicByte, AtomicByte, Byte> = ::shr
    val byteUshrByte: BinaryOperation<AtomicByte, Byte, Byte> = ::ushr
    val byteUshrAtomicByte: BinaryOperation<AtomicByte, AtomicByte, Byte> = ::ushr
    val byteInv: UnaryOperation<AtomicByte, Byte> = ::inv
    val byteNegate: UnaryOperation<AtomicByte, Byte> = ::negate

    private class AutoUpdateAbleAtomicByte(
        initialValue: Byte,
    ) : AtomicByteBase(initialValue) {
        init {
            update(initialValue)
        }

        val destroyListeners: MutableList<() -> Unit> = mutableListOf()

        fun addDestroyListener(listener: () -> Unit): () -> Unit {
            destroyListeners.add(listener)
            return { destroyListeners.remove(listener) }
        }

        fun removeDestroyListener(listener: () -> Unit) {
            destroyListeners.remove(listener)
        }

        public override fun update(newValue: Byte) {
            super.update(newValue)
        }

        override fun destroy() {
            super.destroy()
            destroyListeners.forEach { it() }
        }
    }

    fun transform1(
        source1: AtomicByte,
        operation: UnaryOperation<AtomicByte, Byte>,
    ): AtomicByte {
        val target = AutoUpdateAbleAtomicByte(
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
        source1: AtomicByte,
        constant: T,
        operation: BinaryOperation<AtomicByte, T, Byte>,
    ): AtomicByte {
        val target = AutoUpdateAbleAtomicByte(
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
        source1: AtomicByte,
        source2: AtomicByte,
        concat: BinaryOperation<AtomicByte, AtomicByte, Byte>,
    ): AtomicByte {
        val target = AutoUpdateAbleAtomicByte(
            0,
        )
        val connection = connection2(source1, source2, target) {
            target.update(concat.invoke(it.source1, it.source2))
        }
        target.update(concat(source1, source2))
        target.destroyListeners.add(connection::destroy)
        return target
    }

    fun transformPlus(source1: AtomicByte, other: Byte): AtomicByte = transform1(source1, other, bytePlusByte)
    fun transformPlus(source1: AtomicByte, other: AtomicByte): AtomicByte = transform2(source1, other, bytePlusAtomicByte)
    fun transformMinus(source1: AtomicByte, other: Byte): AtomicByte = transform1(source1, other, byteMinusByte)
    fun transformMinus(source1: AtomicByte, other: AtomicByte): AtomicByte = transform2(source1, other, byteMinusAtomicByte)
    fun transformTimes(source1: AtomicByte, other: Byte): AtomicByte = transform1(source1, other, byteTimesByte)
    fun transformTimes(source1: AtomicByte, other: AtomicByte): AtomicByte = transform2(source1, other, byteTimesAtomicByte)
    fun transformDiv(source1: AtomicByte, other: Byte): AtomicByte = transform1(source1, other, byteDivByte)
    fun transformDiv(source1: AtomicByte, other: AtomicByte): AtomicByte = transform2(source1, other, byteDivAtomicByte)
    fun transformRem(source1: AtomicByte, other: Byte): AtomicByte = transform1(source1, other, byteRemByte)
    fun transformRem(source1: AtomicByte, other: AtomicByte): AtomicByte = transform2(source1, other, byteRemAtomicByte)
    fun transformPow(source1: AtomicByte, other: Byte): AtomicByte = transform1(source1, other, bytePowByte)
    fun transformPow(source1: AtomicByte, other: AtomicByte): AtomicByte = transform2(source1, other, bytePowAtomicByte)
    fun transformAnd(source1: AtomicByte, other: Byte): AtomicByte = transform1(source1, other, byteAndByte)
    fun transformAnd(source1: AtomicByte, other: AtomicByte): AtomicByte = transform2(source1, other, byteAndAtomicByte)
    fun transformOr(source1: AtomicByte, other: Byte): AtomicByte = transform1(source1, other, byteOrByte)
    fun transformOr(source1: AtomicByte, other: AtomicByte): AtomicByte = transform2(source1, other, bytOrAtomicByte)
    fun transformXor(source1: AtomicByte, other: Byte): AtomicByte = transform1(source1, other, byteXorByte)
    fun transformXor(source1: AtomicByte, other: AtomicByte): AtomicByte = transform2(source1, other, byteXorAtomicByte)
    fun transformShl(source1: AtomicByte, other: Byte): AtomicByte = transform1(source1, other, byteShlByte)
    fun transformShl(source1: AtomicByte, other: AtomicByte): AtomicByte = transform2(source1, other, byteShlAtomicByte)
    fun transformShr(source1: AtomicByte, other: Byte): AtomicByte = transform1(source1, other, byteShrByte)
    fun transformShr(source1: AtomicByte, other: AtomicByte): AtomicByte = transform2(source1, other, byteShrAtomicByte)
    fun transformUshr(source1: AtomicByte, other: Byte): AtomicByte = transform1(source1, other, byteUshrByte)
    fun transformUshr(source1: AtomicByte, other: AtomicByte): AtomicByte = transform2(source1, other, byteUshrAtomicByte)
    fun transformInv(source1: AtomicByte): AtomicByte = transform1(source1, byteInv)
    fun transformNegate(source1: AtomicByte): AtomicByte = transform1(source1, byteNegate)

    // Short Operations
}

/**
 * An atomic byte value
 */
interface AtomicByte : AtomicValue {

    /**
     * The value of the atomic byte
     */
    val byteValue: Byte

    /**
     * Get the value of the atomic byte
     */
    fun get(): Byte

    /**
     * Add another byte to this byte (returns a new [AtomicByte] that automatically updates)
     * @param other The other byte to add
     * @return The new [AtomicByte] that automatically updates
     */
    operator fun plus(other: Byte) = AtomicByteOperationImplementations.transformPlus(this, other)

    /**
     * Add another [AtomicByte] to this byte (returns a new [AtomicByte] that automatically updates)
     * @param other The other [AtomicByte] to add
     * @return The new [AtomicByte] that automatically updates
     */
    operator fun plus(other: AtomicByte) = AtomicByteOperationImplementations.transformPlus(this, other)

    /**
     * Subtract another byte from this byte (returns a new [AtomicByte] that automatically updates)
     * @param other The other byte to subtract
     * @return The new [AtomicByte] that automatically updates
     */
    operator fun minus(other: Byte) = AtomicByteOperationImplementations.transformMinus(this, other)

    /**
     * Subtract another [AtomicByte] from this byte (returns a new [AtomicByte] that automatically updates)
     * @param other The other [AtomicByte] to subtract
     * @return The new [AtomicByte] that automatically updates
     */
    operator fun minus(other: AtomicByte) = AtomicByteOperationImplementations.transformMinus(this, other)

    /**
     * Multiply this byte with another byte (returns a new [AtomicByte] that automatically updates)
     * @param other The other byte to multiply with
     * @return The new [AtomicByte] that automatically updates
     */
    operator fun times(other: Byte) = AtomicByteOperationImplementations.transformTimes(this, other)

    /**
     * Multiply this byte with another [AtomicByte] (returns a new [AtomicByte] that automatically updates)
     * @param other The other [AtomicByte] to multiply with
     * @return The new [AtomicByte] that automatically updates
     */
    operator fun times(other: AtomicByte) = AtomicByteOperationImplementations.transformTimes(this, other)

    /**
     * Divide this byte by another byte (returns a new [AtomicByte] that automatically updates)
     * @param other The other byte to divide by
     * @return The new [AtomicByte] that automatically updates
     */
    operator fun div(other: Byte) = AtomicByteOperationImplementations.transformDiv(this, other)

    /**
     * Divide this byte by another [AtomicByte] (returns a new [AtomicByte] that automatically updates)
     * @param other The other [AtomicByte] to divide by
     * @return The new [AtomicByte] that automatically updates
     */
    operator fun div(other: AtomicByte) = AtomicByteOperationImplementations.transformDiv(this, other)

    /**
     * Modulo this byte by another byte (returns a new [AtomicByte] that automatically updates)
     * @param other The other byte to modulo by
     * @return The new [AtomicByte] that automatically updates
     */
    operator fun rem(other: Byte) = AtomicByteOperationImplementations.transformRem(this, other)

    /**
     * Modulo this byte by another [AtomicByte] (returns a new [AtomicByte] that automatically updates)
     * @param other The other [AtomicByte] to modulo by
     * @return The new [AtomicByte] that automatically updates
     */
    operator fun rem(other: AtomicByte) = AtomicByteOperationImplementations.transformRem(this, other)

    /**
     * Power this byte by another byte (returns a new [AtomicByte] that automatically updates)
     * @param other The other byte to power by
     * @return The new [AtomicByte] that automatically updates
     */
    infix fun pow(other: Byte) = AtomicByteOperationImplementations.transformPow(this, other)

    /**
     * Power this byte by another [AtomicByte] (returns a new [AtomicByte] that automatically updates)
     * @param other The other [AtomicByte] to power by
     * @return The new [AtomicByte] that automatically updates
     */
    infix fun pow(other: AtomicByte) = AtomicByteOperationImplementations.transformPow(this, other)

    /**
     * And this byte with another byte (returns a new [AtomicByte] that automatically updates)
     * @param other The other byte to and with
     * @return The new [AtomicByte] that automatically updates
     */
    infix fun and(other: Byte) = AtomicByteOperationImplementations.transformAnd(this, other)

    /**
     * And this byte with another [AtomicByte] (returns a new [AtomicByte] that automatically updates)
     * @param other The other [AtomicByte] to and with
     * @return The new [AtomicByte] that automatically updates
     */
    infix fun and(other: AtomicByte) = AtomicByteOperationImplementations.transformAnd(this, other)

    /**
     * Or this byte with another byte (returns a new [AtomicByte] that automatically updates)
     * @param other The other byte to or with
     * @return The new [AtomicByte] that automatically updates
     */
    infix fun or(other: Byte) = AtomicByteOperationImplementations.transformOr(this, other)

    /**
     * Or this byte with another [AtomicByte] (returns a new [AtomicByte] that automatically updates)
     * @param other The other [AtomicByte] to or with
     * @return The new [AtomicByte] that automatically updates
     */
    infix fun or(other: AtomicByte) = AtomicByteOperationImplementations.transformOr(this, other)

    /**
     * Xor this byte with another byte (returns a new [AtomicByte] that automatically updates)
     * @param other The other byte to xor with
     * @return The new [AtomicByte] that automatically updates
     */
    infix fun xor(other: Byte) = AtomicByteOperationImplementations.transformXor(this, other)

    /**
     * Xor this byte with another [AtomicByte] (returns a new [AtomicByte] that automatically updates)
     * @param other The other [AtomicByte] to xor with
     * @return The new [AtomicByte] that automatically updates
     */
    infix fun xor(other: AtomicByte) = AtomicByteOperationImplementations.transformXor(this, other)

    /**
     * Shift this byte left by another byte (returns a new [AtomicByte] that automatically updates)
     * @param other The other byte to shift left by
     * @return The new [AtomicByte] that automatically updates
     */
    infix fun shl(other: Byte) = AtomicByteOperationImplementations.transformShl(this, other)

    /**
     * Shift this byte left by another [AtomicByte] (returns a new [AtomicByte] that automatically updates)
     * @param other The other [AtomicByte] to shift left by
     * @return The new [AtomicByte] that automatically updates
     */
    infix fun shl(other: AtomicByte) = AtomicByteOperationImplementations.transformShl(this, other)

    /**
     * Shift this byte right by another byte (returns a new [AtomicByte] that automatically updates)
     * @param other The other byte to shift right by
     * @return The new [AtomicByte] that automatically updates
     */
    infix fun shr(other: Byte) = AtomicByteOperationImplementations.transformShr(this, other)

    /**
     * Shift this byte right by another [AtomicByte] (returns a new [AtomicByte] that automatically updates)
     * @param other The other [AtomicByte] to shift right by
     * @return The new [AtomicByte] that automatically updates
     */
    infix fun shr(other: AtomicByte) = AtomicByteOperationImplementations.transformShr(this, other)

    /**
     * Shift this byte right by another byte (returns a new [AtomicByte] that automatically updates)
     * @param other The other byte to shift right by
     * @return The new [AtomicByte] that automatically updates
     */
    infix fun ushr(other: Byte) = AtomicByteOperationImplementations.transformUshr(this, other)

    /**
     * Shift this byte right by another [AtomicByte] (returns a new [AtomicByte] that automatically updates)
     * @param other The other [AtomicByte] to shift right by
     * @return The new [AtomicByte] that automatically updates
     */
    infix fun ushr(other: AtomicByte) = AtomicByteOperationImplementations.transformUshr(this, other)

    /**
     * Invert this byte (returns a new [AtomicByte] that automatically updates)
     * @return The new [AtomicByte] that automatically updates
     */
    fun inv() = AtomicByteOperationImplementations.transformInv(this)

    /**
     * Negate this byte (returns a new [AtomicByte] that automatically updates)
     * @return The new [AtomicByte] that automatically updates
     */
    fun negate() = AtomicByteOperationImplementations.transformNegate(this)

    /**
     * Add a listener to this [AtomicByte]
     * @param listener The listener to add
     * @return A function to remove the listener
     */
    fun destroy() {}
}

abstract class AtomicByteBase(
    initialValue: Byte,
) : ListenableItemImpl(), AtomicByte {

    final override var byteValue: Byte = initialValue
        get() = get()
        private set(value) {
            field = value
            notifyListeners()
        }

    protected open fun update(newValue: Byte) {
        byteValue = newValue
    }

    override fun get(): Byte = byteValue
}

class EditableAtomicByte(
    initialValue: Byte,
) : AtomicByteBase(initialValue) {

    public override fun update(newValue: Byte) {
        super.update(newValue)
    }
}

fun atomicByteOf(
    initialValue: Byte,
): AtomicByte = EditableAtomicByte(initialValue)

fun Byte.atomic(): AtomicByte = atomicByteOf(this)
