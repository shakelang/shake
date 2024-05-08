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
    fun plus(source1: AtomicByte, other: Short): Short = (source1.byteValue + other).toShort()
    fun plus(source1: AtomicByte, other: AtomicShort): Short = (source1.byteValue + other.shortValue).toShort()
    fun plus(source1: AtomicByte, other: Int): Int = (source1.byteValue + other)
    fun plus(source1: AtomicByte, other: AtomicInt): Int = (source1.byteValue + other.intValue)
    fun plus(source1: AtomicByte, other: Long): Long = source1.byteValue + other
    fun plus(source1: AtomicByte, other: AtomicLong): Long = (source1.byteValue + other.longValue)

    fun minus(source1: AtomicByte, other: Byte): Byte = (source1.byteValue - other).toByte()
    fun minus(source1: AtomicByte, source2: AtomicByte): Byte = (source1.byteValue - source2.byteValue).toByte()
    fun minus(source1: AtomicByte, other: Short): Short = (source1.byteValue - other).toShort()
    fun minus(source1: AtomicByte, other: AtomicShort): Short = (source1.byteValue - other.shortValue).toShort()
    fun minus(source1: AtomicByte, other: Int): Int = (source1.byteValue - other)
    fun minus(source1: AtomicByte, other: AtomicInt): Int = (source1.byteValue - other.intValue)
    fun minus(source1: AtomicByte, other: Long): Long = source1.byteValue - other
    fun minus(source1: AtomicByte, other: AtomicLong): Long = (source1.byteValue - other.longValue)

    fun times(source1: AtomicByte, other: Byte): Byte = (source1.byteValue * other).toByte()
    fun times(source1: AtomicByte, source2: AtomicByte): Byte = (source1.byteValue * source2.byteValue).toByte()
    fun times(source1: AtomicByte, other: Short): Short = (source1.byteValue * other).toShort()
    fun times(source1: AtomicByte, other: AtomicShort): Short = (source1.byteValue * other.shortValue).toShort()
    fun times(source1: AtomicByte, other: Int): Int = (source1.byteValue * other)
    fun times(source1: AtomicByte, other: AtomicInt): Int = (source1.byteValue * other.intValue)
    fun times(source1: AtomicByte, other: Long): Long = source1.byteValue * other
    fun times(source1: AtomicByte, other: AtomicLong): Long = (source1.byteValue * other.longValue)

    fun div(source1: AtomicByte, other: Byte): Byte = (source1.byteValue / other).toByte()
    fun div(source1: AtomicByte, source2: AtomicByte): Byte = (source1.byteValue / source2.byteValue).toByte()
    fun div(source1: AtomicByte, other: Short): Short = (source1.byteValue / other).toShort()
    fun div(source1: AtomicByte, other: AtomicShort): Short = (source1.byteValue / other.shortValue).toShort()
    fun div(source1: AtomicByte, other: Int): Int = (source1.byteValue / other)
    fun div(source1: AtomicByte, other: AtomicInt): Int = (source1.byteValue / other.intValue)
    fun div(source1: AtomicByte, other: Long): Long = source1.byteValue / other
    fun div(source1: AtomicByte, other: AtomicLong): Long = (source1.byteValue / other.longValue)

    fun rem(source1: AtomicByte, other: Byte): Byte = (source1.byteValue % other).toByte()
    fun rem(source1: AtomicByte, source2: AtomicByte): Byte = (source1.byteValue % source2.byteValue).toByte()
    fun rem(source1: AtomicByte, other: Short): Short = (source1.byteValue % other).toShort()
    fun rem(source1: AtomicByte, other: AtomicShort): Short = (source1.byteValue % other.shortValue).toShort()
    fun rem(source1: AtomicByte, other: Int): Int = (source1.byteValue % other)
    fun rem(source1: AtomicByte, other: AtomicInt): Int = (source1.byteValue % other.intValue)
    fun rem(source1: AtomicByte, other: Long): Long = source1.byteValue % other
    fun rem(source1: AtomicByte, other: AtomicLong): Long = (source1.byteValue % other.longValue)

    fun pow(source1: AtomicByte, other: Byte): Byte = source1.byteValue.toDouble().pow(other.toDouble()).toInt().toByte()
    fun pow(source1: AtomicByte, source2: AtomicByte): Byte = source1.byteValue.toDouble().pow(source2.byteValue.toDouble()).toInt().toByte()
    fun pow(source1: AtomicByte, other: Short): Byte = source1.byteValue.toDouble().pow(other.toDouble()).toInt().toByte()
    fun pow(source1: AtomicByte, other: AtomicShort): Byte = source1.byteValue.toDouble().pow(other.shortValue.toDouble()).toInt().toByte()
    fun pow(source1: AtomicByte, other: Int): Byte = source1.byteValue.toDouble().pow(other.toDouble()).toInt().toByte()
    fun pow(source1: AtomicByte, other: AtomicInt): Byte = source1.byteValue.toDouble().pow(other.intValue.toDouble()).toInt().toByte()
    fun pow(source1: AtomicByte, other: Long): Byte = source1.byteValue.toDouble().pow(other.toDouble()).toInt().toByte()
    fun pow(source1: AtomicByte, other: AtomicLong): Byte = source1.byteValue.toDouble().pow(other.longValue.toDouble()).toInt().toByte()

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
    val bytePlusShort: BinaryOperation<AtomicByte, Short, Short> = ::plus
    val bytePlusAtomicShort: BinaryOperation<AtomicByte, AtomicShort, Short> = ::plus
    val bytePlusInt: BinaryOperation<AtomicByte, Int, Int> = ::plus
    val bytePlusAtomicInt: BinaryOperation<AtomicByte, AtomicInt, Int> = ::plus
    val bytePlusLong: BinaryOperation<AtomicByte, Long, Long> = ::plus
    val bytePlusAtomicLong: BinaryOperation<AtomicByte, AtomicLong, Long> = ::plus

    val byteMinusByte: BinaryOperation<AtomicByte, Byte, Byte> = ::minus
    val byteMinusAtomicByte: BinaryOperation<AtomicByte, AtomicByte, Byte> = ::minus
    val byteMinusShort: BinaryOperation<AtomicByte, Short, Short> = ::minus
    val byteMinusAtomicShort: BinaryOperation<AtomicByte, AtomicShort, Short> = ::minus
    val byteMinusInt: BinaryOperation<AtomicByte, Int, Int> = ::minus
    val byteMinusAtomicInt: BinaryOperation<AtomicByte, AtomicInt, Int> = ::minus
    val byteMinusLong: BinaryOperation<AtomicByte, Long, Long> = ::minus
    val byteMinusAtomicLong: BinaryOperation<AtomicByte, AtomicLong, Long> = ::minus

    val byteTimesByte: BinaryOperation<AtomicByte, Byte, Byte> = ::times
    val byteTimesAtomicByte: BinaryOperation<AtomicByte, AtomicByte, Byte> = ::times
    val byteTimesShort: BinaryOperation<AtomicByte, Short, Short> = ::times
    val byteTimesAtomicShort: BinaryOperation<AtomicByte, AtomicShort, Short> = ::times
    val byteTimesInt: BinaryOperation<AtomicByte, Int, Int> = ::times
    val byteTimesAtomicInt: BinaryOperation<AtomicByte, AtomicInt, Int> = ::times
    val byteTimesLong: BinaryOperation<AtomicByte, Long, Long> = ::times
    val byteTimesAtomicLong: BinaryOperation<AtomicByte, AtomicLong, Long> = ::times

    val byteDivByte: BinaryOperation<AtomicByte, Byte, Byte> = ::div
    val byteDivAtomicByte: BinaryOperation<AtomicByte, AtomicByte, Byte> = ::div
    val byteDivShort: BinaryOperation<AtomicByte, Short, Short> = ::div
    val byteDivAtomicShort: BinaryOperation<AtomicByte, AtomicShort, Short> = ::div
    val byteDivInt: BinaryOperation<AtomicByte, Int, Int> = ::div
    val byteDivAtomicInt: BinaryOperation<AtomicByte, AtomicInt, Int> = ::div
    val byteDivLong: BinaryOperation<AtomicByte, Long, Long> = ::div
    val byteDivAtomicLong: BinaryOperation<AtomicByte, AtomicLong, Long> = ::div

    val byteRemByte: BinaryOperation<AtomicByte, Byte, Byte> = ::rem
    val byteRemAtomicByte: BinaryOperation<AtomicByte, AtomicByte, Byte> = ::rem
    val byteRemShort: BinaryOperation<AtomicByte, Short, Short> = ::rem
    val byteRemAtomicShort: BinaryOperation<AtomicByte, AtomicShort, Short> = ::rem
    val byteRemInt: BinaryOperation<AtomicByte, Int, Int> = ::rem
    val byteRemAtomicInt: BinaryOperation<AtomicByte, AtomicInt, Int> = ::rem
    val byteRemLong: BinaryOperation<AtomicByte, Long, Long> = ::rem
    val byteRemAtomicLong: BinaryOperation<AtomicByte, AtomicLong, Long> = ::rem

    val bytePowByte: BinaryOperation<AtomicByte, Byte, Byte> = ::pow
    val bytePowAtomicByte: BinaryOperation<AtomicByte, AtomicByte, Byte> = ::pow
    val bytePowShort: BinaryOperation<AtomicByte, Short, Byte> = ::pow
    val bytePowAtomicShort: BinaryOperation<AtomicByte, AtomicShort, Byte> = ::pow
    val bytePowInt: BinaryOperation<AtomicByte, Int, Byte> = ::pow
    val bytePowAtomicInt: BinaryOperation<AtomicByte, AtomicInt, Byte> = ::pow
    val bytePowLong: BinaryOperation<AtomicByte, Long, Byte> = ::pow
    val bytePowAtomicLong: BinaryOperation<AtomicByte, AtomicLong, Byte> = ::pow

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

    fun transformPlus(source1: AtomicByte, other: Byte): AtomicByte = transform1(source1, other, bytePlusByte, AutoUpdateAbleAtomicByte.creator)
    fun transformPlus(source1: AtomicByte, other: AtomicByte): AtomicByte = transform2(source1, other, bytePlusAtomicByte, AutoUpdateAbleAtomicByte.creator)
    fun transformPlus(source1: AtomicByte, other: Short): AtomicShort = transform1(source1, other, bytePlusShort, AutoUpdateAbleAtomicShort.creator)
    fun transformPlus(source1: AtomicByte, other: AtomicShort): AtomicShort = transform2(source1, other, bytePlusAtomicShort, AutoUpdateAbleAtomicShort.creator)
    fun transformPlus(source1: AtomicByte, other: Int): AtomicInt = transform1(source1, other, bytePlusInt, AutoUpdateAbleAtomicInt.creator)
    fun transformPlus(source1: AtomicByte, other: AtomicInt): AtomicInt = transform2(source1, other, bytePlusAtomicInt, AutoUpdateAbleAtomicInt.creator)
    fun transformPlus(source1: AtomicByte, other: Long): AtomicLong = transform1(source1, other, bytePlusLong, AutoUpdateAbleAtomicLong.creator)
    fun transformPlus(source1: AtomicByte, other: AtomicLong): AtomicLong = transform2(source1, other, bytePlusAtomicLong, AutoUpdateAbleAtomicLong.creator)

    fun transformMinus(source1: AtomicByte, other: Byte): AtomicByte = transform1(source1, other, byteMinusByte, AutoUpdateAbleAtomicByte.creator)
    fun transformMinus(source1: AtomicByte, other: AtomicByte): AtomicByte = transform2(source1, other, byteMinusAtomicByte, AutoUpdateAbleAtomicByte.creator)
    fun transformMinus(source1: AtomicByte, other: Short): AtomicShort = transform1(source1, other, byteMinusShort, AutoUpdateAbleAtomicShort.creator)
    fun transformMinus(source1: AtomicByte, other: AtomicShort): AtomicShort = transform2(source1, other, byteMinusAtomicShort, AutoUpdateAbleAtomicShort.creator)
    fun transformMinus(source1: AtomicByte, other: Int): AtomicInt = transform1(source1, other, byteMinusInt, AutoUpdateAbleAtomicInt.creator)
    fun transformMinus(source1: AtomicByte, other: AtomicInt): AtomicInt = transform2(source1, other, byteMinusAtomicInt, AutoUpdateAbleAtomicInt.creator)
    fun transformMinus(source1: AtomicByte, other: Long): AtomicLong = transform1(source1, other, byteMinusLong, AutoUpdateAbleAtomicLong.creator)
    fun transformMinus(source1: AtomicByte, other: AtomicLong): AtomicLong = transform2(source1, other, byteMinusAtomicLong, AutoUpdateAbleAtomicLong.creator)

    fun transformTimes(source1: AtomicByte, other: Byte): AtomicByte = transform1(source1, other, byteTimesByte, AutoUpdateAbleAtomicByte.creator)
    fun transformTimes(source1: AtomicByte, other: AtomicByte): AtomicByte = transform2(source1, other, byteTimesAtomicByte, AutoUpdateAbleAtomicByte.creator)
    fun transformTimes(source1: AtomicByte, other: Short): AtomicShort = transform1(source1, other, byteTimesShort, AutoUpdateAbleAtomicShort.creator)
    fun transformTimes(source1: AtomicByte, other: AtomicShort): AtomicShort = transform2(source1, other, byteTimesAtomicShort, AutoUpdateAbleAtomicShort.creator)
    fun transformTimes(source1: AtomicByte, other: Int): AtomicInt = transform1(source1, other, byteTimesInt, AutoUpdateAbleAtomicInt.creator)
    fun transformTimes(source1: AtomicByte, other: AtomicInt): AtomicInt = transform2(source1, other, byteTimesAtomicInt, AutoUpdateAbleAtomicInt.creator)
    fun transformTimes(source1: AtomicByte, other: Long): AtomicLong = transform1(source1, other, byteTimesLong, AutoUpdateAbleAtomicLong.creator)
    fun transformTimes(source1: AtomicByte, other: AtomicLong): AtomicLong = transform2(source1, other, byteTimesAtomicLong, AutoUpdateAbleAtomicLong.creator)

    fun transformDiv(source1: AtomicByte, other: Byte): AtomicByte = transform1(source1, other, byteDivByte, AutoUpdateAbleAtomicByte.creator)
    fun transformDiv(source1: AtomicByte, other: AtomicByte): AtomicByte = transform2(source1, other, byteDivAtomicByte, AutoUpdateAbleAtomicByte.creator)
    fun transformDiv(source1: AtomicByte, other: Short): AtomicShort = transform1(source1, other, byteDivShort, AutoUpdateAbleAtomicShort.creator)
    fun transformDiv(source1: AtomicByte, other: AtomicShort): AtomicShort = transform2(source1, other, byteDivAtomicShort, AutoUpdateAbleAtomicShort.creator)
    fun transformDiv(source1: AtomicByte, other: Int): AtomicInt = transform1(source1, other, byteDivInt, AutoUpdateAbleAtomicInt.creator)
    fun transformDiv(source1: AtomicByte, other: AtomicInt): AtomicInt = transform2(source1, other, byteDivAtomicInt, AutoUpdateAbleAtomicInt.creator)
    fun transformDiv(source1: AtomicByte, other: Long): AtomicLong = transform1(source1, other, byteDivLong, AutoUpdateAbleAtomicLong.creator)
    fun transformDiv(source1: AtomicByte, other: AtomicLong): AtomicLong = transform2(source1, other, byteDivAtomicLong, AutoUpdateAbleAtomicLong.creator)

    fun transformRem(source1: AtomicByte, other: Byte): AtomicByte = transform1(source1, other, byteRemByte, AutoUpdateAbleAtomicByte.creator)
    fun transformRem(source1: AtomicByte, other: AtomicByte): AtomicByte = transform2(source1, other, byteRemAtomicByte, AutoUpdateAbleAtomicByte.creator)
    fun transformRem(source1: AtomicByte, other: Short): AtomicShort = transform1(source1, other, byteRemShort, AutoUpdateAbleAtomicShort.creator)
    fun transformRem(source1: AtomicByte, other: AtomicShort): AtomicShort = transform2(source1, other, byteRemAtomicShort, AutoUpdateAbleAtomicShort.creator)
    fun transformRem(source1: AtomicByte, other: Int): AtomicInt = transform1(source1, other, byteRemInt, AutoUpdateAbleAtomicInt.creator)
    fun transformRem(source1: AtomicByte, other: AtomicInt): AtomicInt = transform2(source1, other, byteRemAtomicInt, AutoUpdateAbleAtomicInt.creator)
    fun transformRem(source1: AtomicByte, other: Long): AtomicLong = transform1(source1, other, byteRemLong, AutoUpdateAbleAtomicLong.creator)
    fun transformRem(source1: AtomicByte, other: AtomicLong): AtomicLong = transform2(source1, other, byteRemAtomicLong, AutoUpdateAbleAtomicLong.creator)

    fun transformPow(source1: AtomicByte, other: Byte): AtomicByte = transform1(source1, other, bytePowByte, AutoUpdateAbleAtomicByte.creator)
    fun transformPow(source1: AtomicByte, other: AtomicByte): AtomicByte = transform2(source1, other, bytePowAtomicByte, AutoUpdateAbleAtomicByte.creator)
    fun transformPow(source1: AtomicByte, other: Short): AtomicByte = transform1(source1, other, bytePowShort, AutoUpdateAbleAtomicByte.creator)
    fun transformPow(source1: AtomicByte, other: AtomicShort): AtomicByte = transform2(source1, other, bytePowAtomicShort, AutoUpdateAbleAtomicByte.creator)
    fun transformPow(source1: AtomicByte, other: Int): AtomicByte = transform1(source1, other, bytePowInt, AutoUpdateAbleAtomicByte.creator)
    fun transformPow(source1: AtomicByte, other: AtomicInt): AtomicByte = transform2(source1, other, bytePowAtomicInt, AutoUpdateAbleAtomicByte.creator)
    fun transformPow(source1: AtomicByte, other: Long): AtomicByte = transform1(source1, other, bytePowLong, AutoUpdateAbleAtomicByte.creator)
    fun transformPow(source1: AtomicByte, other: AtomicLong): AtomicByte = transform2(source1, other, bytePowAtomicLong, AutoUpdateAbleAtomicByte.creator)

    fun transformAnd(source1: AtomicByte, other: Byte): AtomicByte = transform1(source1, other, byteAndByte, AutoUpdateAbleAtomicByte.creator)
    fun transformAnd(source1: AtomicByte, other: AtomicByte): AtomicByte = transform2(source1, other, byteAndAtomicByte, AutoUpdateAbleAtomicByte.creator)

    fun transformOr(source1: AtomicByte, other: Byte): AtomicByte = transform1(source1, other, byteOrByte, AutoUpdateAbleAtomicByte.creator)
    fun transformOr(source1: AtomicByte, other: AtomicByte): AtomicByte = transform2(source1, other, bytOrAtomicByte, AutoUpdateAbleAtomicByte.creator)

    fun transformXor(source1: AtomicByte, other: Byte): AtomicByte = transform1(source1, other, byteXorByte, AutoUpdateAbleAtomicByte.creator)
    fun transformXor(source1: AtomicByte, other: AtomicByte): AtomicByte = transform2(source1, other, byteXorAtomicByte, AutoUpdateAbleAtomicByte.creator)

    fun transformShl(source1: AtomicByte, other: Byte): AtomicByte = transform1(source1, other, byteShlByte, AutoUpdateAbleAtomicByte.creator)
    fun transformShl(source1: AtomicByte, other: AtomicByte): AtomicByte = transform2(source1, other, byteShlAtomicByte, AutoUpdateAbleAtomicByte.creator)

    fun transformShr(source1: AtomicByte, other: Byte): AtomicByte = transform1(source1, other, byteShrByte, AutoUpdateAbleAtomicByte.creator)
    fun transformShr(source1: AtomicByte, other: AtomicByte): AtomicByte = transform2(source1, other, byteShrAtomicByte, AutoUpdateAbleAtomicByte.creator)

    fun transformUshr(source1: AtomicByte, other: Byte): AtomicByte = transform1(source1, other, byteUshrByte, AutoUpdateAbleAtomicByte.creator)
    fun transformUshr(source1: AtomicByte, other: AtomicByte): AtomicByte = transform2(source1, other, byteUshrAtomicByte, AutoUpdateAbleAtomicByte.creator)

    fun transformInv(source1: AtomicByte): AtomicByte = transform1(source1, byteInv, AutoUpdateAbleAtomicByte.creator)
    fun transformNegate(source1: AtomicByte): AtomicByte = transform1(source1, byteNegate, AutoUpdateAbleAtomicByte.creator)
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
     * Add another short to this byte (returns a new [AtomicShort] that automatically updates)
     * @param other The other short to add
     * @return The new [AtomicShort] that automatically updates
     */
    operator fun plus(other: Short) = AtomicByteOperationImplementations.transformPlus(this, other)

    /**
     * Add another [AtomicShort] to this byte (returns a new [AtomicShort] that automatically updates)
     * @param other The other [AtomicShort] to add
     * @return The new [AtomicShort] that automatically updates
     */
    operator fun plus(other: AtomicShort) = AtomicByteOperationImplementations.transformPlus(this, other)

    /**
     * Add another int to this byte (returns a new [AtomicInt] that automatically updates)
     * @param other The other int to add
     * @return The new [AtomicInt] that automatically updates
     */
    operator fun plus(other: Int) = AtomicByteOperationImplementations.transformPlus(this, other)

    /**
     * Add another [AtomicInt] to this byte (returns a new [AtomicInt] that automatically updates)
     * @param other The other [AtomicInt] to add
     * @return The new [AtomicInt] that automatically updates
     */
    operator fun plus(other: AtomicInt) = AtomicByteOperationImplementations.transformPlus(this, other)

    /**
     * Add another long to this byte (returns a new [AtomicLong] that automatically updates)
     * @param other The other long to add
     * @return The new [AtomicLong] that automatically updates
     */
    operator fun plus(other: Long) = AtomicByteOperationImplementations.transformPlus(this, other)

    /**
     * Add another [AtomicLong] to this byte (returns a new [AtomicLong] that automatically updates)
     * @param other The other [AtomicLong] to add
     * @return The new [AtomicLong] that automatically updates
     */
    operator fun plus(other: AtomicLong) = AtomicByteOperationImplementations.transformPlus(this, other)

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
     * Subtract another short from this byte (returns a new [AtomicShort] that automatically updates)
     * @param other The other short to subtract
     * @return The new [AtomicShort] that automatically updates
     */
    operator fun minus(other: Short) = AtomicByteOperationImplementations.transformMinus(this, other)

    /**
     * Subtract another [AtomicShort] from this byte (returns a new [AtomicShort] that automatically updates)
     * @param other The other [AtomicShort] to subtract
     * @return The new [AtomicShort] that automatically updates
     */
    operator fun minus(other: AtomicShort) = AtomicByteOperationImplementations.transformMinus(this, other)

    /**
     * Subtract another int from this byte (returns a new [AtomicInt] that automatically updates)
     * @param other The other int to subtract
     * @return The new [AtomicInt] that automatically updates
     */
    operator fun minus(other: Int) = AtomicByteOperationImplementations.transformMinus(this, other)

    /**
     * Subtract another [AtomicInt] from this byte (returns a new [AtomicInt] that automatically updates)
     * @param other The other [AtomicInt] to subtract
     * @return The new [AtomicInt] that automatically updates
     */
    operator fun minus(other: AtomicInt) = AtomicByteOperationImplementations.transformMinus(this, other)

    /**
     * Subtract another long from this byte (returns a new [AtomicLong] that automatically updates)
     * @param other The other long to subtract
     * @return The new [AtomicLong] that automatically updates
     */
    operator fun minus(other: Long) = AtomicByteOperationImplementations.transformMinus(this, other)

    /**
     * Subtract another [AtomicLong] from this byte (returns a new [AtomicLong] that automatically updates)
     * @param other The other [AtomicLong] to subtract
     * @return The new [AtomicLong] that automatically updates
     */
    operator fun minus(other: AtomicLong) = AtomicByteOperationImplementations.transformMinus(this, other)

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
     * Multiply this byte with another short (returns a new [AtomicShort] that automatically updates)
     * @param other The other short to multiply with
     * @return The new [AtomicShort] that automatically updates
     */
    operator fun times(other: Short) = AtomicByteOperationImplementations.transformTimes(this, other)

    /**
     * Multiply this byte with another [AtomicShort] (returns a new [AtomicShort] that automatically updates)
     * @param other The other [AtomicShort] to multiply with
     * @return The new [AtomicShort] that automatically updates
     */
    operator fun times(other: AtomicShort) = AtomicByteOperationImplementations.transformTimes(this, other)

    /**
     * Multiply this byte with another int (returns a new [AtomicInt] that automatically updates)
     * @param other The other int to multiply with
     * @return The new [AtomicInt] that automatically updates
     */
    operator fun times(other: Int) = AtomicByteOperationImplementations.transformTimes(this, other)

    /**
     * Multiply this byte with another [AtomicInt] (returns a new [AtomicInt] that automatically updates)
     * @param other The other [AtomicInt] to multiply with
     * @return The new [AtomicInt] that automatically updates
     */
    operator fun times(other: AtomicInt) = AtomicByteOperationImplementations.transformTimes(this, other)

    /**
     * Multiply this byte with another long (returns a new [AtomicLong] that automatically updates)
     * @param other The other long to multiply with
     * @return The new [AtomicLong] that automatically updates
     */
    operator fun times(other: Long) = AtomicByteOperationImplementations.transformTimes(this, other)

    /**
     * Multiply this byte with another [AtomicLong] (returns a new [AtomicLong] that automatically updates)
     * @param other The other [AtomicLong] to multiply with
     * @return The new [AtomicLong] that automatically updates
     */
    operator fun times(other: AtomicLong) = AtomicByteOperationImplementations.transformTimes(this, other)

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
     * Divide this byte by another short (returns a new [AtomicShort] that automatically updates)
     * @param other The other short to divide by
     * @return The new [AtomicShort] that automatically updates
     */
    operator fun div(other: Short) = AtomicByteOperationImplementations.transformDiv(this, other)

    /**
     * Divide this byte by another [AtomicShort] (returns a new [AtomicShort] that automatically updates)
     * @param other The other [AtomicShort] to divide by
     * @return The new [AtomicShort] that automatically updates
     */
    operator fun div(other: AtomicShort) = AtomicByteOperationImplementations.transformDiv(this, other)

    /**
     * Divide this byte by another int (returns a new [AtomicInt] that automatically updates)
     * @param other The other int to divide by
     * @return The new [AtomicInt] that automatically updates
     */
    operator fun div(other: Int) = AtomicByteOperationImplementations.transformDiv(this, other)

    /**
     * Divide this byte by another [AtomicInt] (returns a new [AtomicInt] that automatically updates)
     * @param other The other [AtomicInt] to divide by
     * @return The new [AtomicInt] that automatically updates
     */
    operator fun div(other: AtomicInt) = AtomicByteOperationImplementations.transformDiv(this, other)

    /**
     * Divide this byte by another long (returns a new [AtomicLong] that automatically updates)
     * @param other The other long to divide by
     * @return The new [AtomicLong] that automatically updates
     */
    operator fun div(other: Long) = AtomicByteOperationImplementations.transformDiv(this, other)

    /**
     * Divide this byte by another [AtomicLong] (returns a new [AtomicLong] that automatically updates)
     * @param other The other [AtomicLong] to divide by
     * @return The new [AtomicLong] that automatically updates
     */
    operator fun div(other: AtomicLong) = AtomicByteOperationImplementations.transformDiv(this, other)

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
     * Modulo this byte by another short (returns a new [AtomicShort] that automatically updates)
     * @param other The other short to modulo by
     * @return The new [AtomicShort] that automatically updates
     */
    operator fun rem(other: Short) = AtomicByteOperationImplementations.transformRem(this, other)

    /**
     * Modulo this byte by another [AtomicShort] (returns a new [AtomicShort] that automatically updates)
     * @param other The other [AtomicShort] to modulo by
     * @return The new [AtomicShort] that automatically updates
     */
    operator fun rem(other: AtomicShort) = AtomicByteOperationImplementations.transformRem(this, other)

    /**
     * Modulo this byte by another int (returns a new [AtomicInt] that automatically updates)
     * @param other The other int to modulo by
     * @return The new [AtomicInt] that automatically updates
     */
    operator fun rem(other: Int) = AtomicByteOperationImplementations.transformRem(this, other)

    /**
     * Modulo this byte by another [AtomicInt] (returns a new [AtomicInt] that automatically updates)
     * @param other The other [AtomicInt] to modulo by
     * @return The new [AtomicInt] that automatically updates
     */
    operator fun rem(other: AtomicInt) = AtomicByteOperationImplementations.transformRem(this, other)

    /**
     * Modulo this byte by another long (returns a new [AtomicLong] that automatically updates)
     * @param other The other long to modulo by
     * @return The new [AtomicLong] that automatically updates
     */
    operator fun rem(other: Long) = AtomicByteOperationImplementations.transformRem(this, other)

    /**
     * Modulo this byte by another [AtomicLong] (returns a new [AtomicLong] that automatically updates)
     * @param other The other [AtomicLong] to modulo by
     * @return The new [AtomicLong] that automatically updates
     */
    operator fun rem(other: AtomicLong) = AtomicByteOperationImplementations.transformRem(this, other)

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
     * Power this byte by another short (returns a new [AtomicByte] that automatically updates)
     * @param other The other short to power by
     * @return The new [AtomicByte] that automatically updates
     */
    infix fun pow(other: Short) = AtomicByteOperationImplementations.transformPow(this, other)

    /**
     * Power this byte by another [AtomicShort] (returns a new [AtomicByte] that automatically updates)
     * @param other The other [AtomicShort] to power by
     * @return The new [AtomicByte] that automatically updates
     */
    infix fun pow(other: AtomicShort) = AtomicByteOperationImplementations.transformPow(this, other)

    /**
     * Power this byte by another int (returns a new [AtomicByte] that automatically updates)
     * @param other The other int to power by
     * @return The new [AtomicByte] that automatically updates
     */
    infix fun pow(other: Int) = AtomicByteOperationImplementations.transformPow(this, other)

    /**
     * Power this byte by another [AtomicInt] (returns a new [AtomicByte] that automatically updates)
     * @param other The other [AtomicInt] to power by
     * @return The new [AtomicByte] that automatically updates
     */
    infix fun pow(other: AtomicInt) = AtomicByteOperationImplementations.transformPow(this, other)

    /**
     * Power this byte by another long (returns a new [AtomicByte] that automatically updates)
     * @param other The other long to power by
     * @return The new [AtomicByte] that automatically updates
     */
    infix fun pow(other: Long) = AtomicByteOperationImplementations.transformPow(this, other)

    /**
     * Power this byte by another [AtomicLong] (returns a new [AtomicByte] that automatically updates)
     * @param other The other [AtomicLong] to power by
     * @return The new [AtomicByte] that automatically updates
     */
    infix fun pow(other: AtomicLong) = AtomicByteOperationImplementations.transformPow(this, other)

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
     * Destroy this [AtomicByte]
     */
    fun destroy() {}
}

abstract class AtomicByteBase(
    initialValue: Byte,
) : ListenableItemImpl(), AtomicByte {

    val destroyListeners: MutableList<() -> Unit> = mutableListOf()

    override fun addDestroyListener(listener: () -> Unit): () -> Unit {
        destroyListeners.add(listener)
        return { destroyListeners.remove(listener) }
    }

    override fun removeDestroyListener(listener: () -> Unit) {
        destroyListeners.remove(listener)
    }

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

class AutoUpdateAbleAtomicByte(
    initialValue: Byte,
) : AtomicByteBase(initialValue), AutoUpdatableAtomicValue<Byte> {

    init {
        update(initialValue)
    }

    override fun update(value: Byte) {
        super.update(value)
    }

    override fun destroy() {
        super.destroy()
        destroyListeners.forEach { it() }
    }

    companion object {
        fun create(
            initialValue: Byte,
        ): AutoUpdateAbleAtomicByte = AutoUpdateAbleAtomicByte(initialValue)

        fun create() = create(0)

        val creator: () -> AutoUpdateAbleAtomicByte = ::create
    }
}
