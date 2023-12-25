@file:OptIn(ExperimentalUnsignedTypes::class)

package com.shakelang.util.testlib

import io.kotest.matchers.collections.shouldContainExactly
import io.kotest.matchers.collections.shouldNotContainExactly

/**
 * Check if the [ByteArray] contains exactly the same elements as the [other] [ByteArray]
 * (Same order, same elements)
 * @since 0.2.2
 * @version 0.2.2
 */
infix fun ByteArray.shouldContainExactly(other: ByteArray) =
    this.toTypedArray() shouldContainExactly other.toTypedArray()

/**
 * Check if the [ByteArray] contains exactly the same elements as the [other] [Collection]
 * (Same order, same elements)
 * @since 0.2.2
 * @version 0.2.2
 */
infix fun ByteArray.shouldContainExactly(other: Collection<Byte>) =
    this.toTypedArray() shouldContainExactly other.toTypedArray()

/**
 * Check if the [ByteArray] contains exactly the same elements as the [other] [Sequence]
 * (Same order, same elements)
 * @since 0.2.2
 * @version 0.2.2
 */
infix fun ByteArray.shouldContainExactly(other: Sequence<Byte>) =
    this.toTypedArray() shouldContainExactly other.toList().toTypedArray()

/**
 * Check if the [ShortArray] contains exactly the same elements as the [other] [ShortArray]
 * (Same order, same elements)
 * @since 0.2.2
 * @version 0.2.2
 */
infix fun ShortArray.shouldContainExactly(other: ShortArray) =
    this.toTypedArray() shouldContainExactly other.toTypedArray()

/**
 * Check if the [ShortArray] contains exactly the same elements as the [other] [Collection]
 * (Same order, same elements)
 * @since 0.2.2
 * @version 0.2.2
 */
infix fun ShortArray.shouldContainExactly(other: Collection<Short>) =
    this.toTypedArray() shouldContainExactly other.toTypedArray()

/**
 * Check if the [ShortArray] contains exactly the same elements as the [other] [Sequence]
 * (Same order, same elements)
 * @since 0.2.2
 * @version 0.2.2
 */
infix fun ShortArray.shouldContainExactly(other: Sequence<Short>) =
    this.toTypedArray() shouldContainExactly other.toList().toTypedArray()

/**
 * Check if the [IntArray] contains exactly the same elements as the [other] [IntArray]
 * (Same order, same elements)
 * @since 0.2.2
 * @version 0.2.2
 */
infix fun IntArray.shouldContainExactly(other: IntArray) =
    this.toTypedArray() shouldContainExactly other.toTypedArray()

/**
 * Check if the [IntArray] contains exactly the same elements as the [other] [Collection]
 * (Same order, same elements)
 * @since 0.2.2
 * @version 0.2.2
 */
infix fun IntArray.shouldContainExactly(other: Collection<Int>) =
    this.toTypedArray() shouldContainExactly other.toTypedArray()

/**
 * Check if the [IntArray] contains exactly the same elements as the [other] [Sequence]
 * (Same order, same elements)
 * @since 0.2.2
 * @version 0.2.2
 */
infix fun IntArray.shouldContainExactly(other: Sequence<Int>) =
    this.toTypedArray() shouldContainExactly other.toList().toTypedArray()

/**
 * Check if the [LongArray] contains exactly the same elements as the [other] [LongArray]
 * (Same order, same elements)
 * @since 0.2.2
 * @version 0.2.2
 */
infix fun LongArray.shouldContainExactly(other: LongArray) =
    this.toTypedArray() shouldContainExactly other.toTypedArray()

/**
 * Check if the [LongArray] contains exactly the same elements as the [other] [Collection]
 * (Same order, same elements)
 * @since 0.2.2
 * @version 0.2.2
 */
infix fun LongArray.shouldContainExactly(other: Collection<Long>) =
    this.toTypedArray() shouldContainExactly other.toTypedArray()

/**
 * Check if the [LongArray] contains exactly the same elements as the [other] [Sequence]
 * (Same order, same elements)
 * @since 0.2.2
 * @version 0.2.2
 */
infix fun LongArray.shouldContainExactly(other: Sequence<Long>) =
    this.toTypedArray() shouldContainExactly other.toList().toTypedArray()

/**
 * Check if the [FloatArray] contains exactly the same elements as the [other] [FloatArray]
 * (Same order, same elements)
 * @since 0.2.2
 * @version 0.2.2
 */
infix fun FloatArray.shouldContainExactly(other: FloatArray) =
    this.toTypedArray() shouldContainExactly other.toTypedArray()

/**
 * Check if the [FloatArray] contains exactly the same elements as the [other] [Collection]
 * (Same order, same elements)
 * @since 0.2.2
 * @version 0.2.2
 */
infix fun FloatArray.shouldContainExactly(other: Collection<Float>) =
    this.toTypedArray() shouldContainExactly other.toTypedArray()

/**
 * Check if the [FloatArray] contains exactly the same elements as the [other] [Sequence]
 * (Same order, same elements)
 * @since 0.2.2
 * @version 0.2.2
 */
infix fun FloatArray.shouldContainExactly(other: Sequence<Float>) =
    this.toTypedArray() shouldContainExactly other.toList().toTypedArray()

/**
 * Check if the [DoubleArray] contains exactly the same elements as the [other] [DoubleArray]
 * (Same order, same elements)
 * @since 0.2.2
 * @version 0.2.2
 */
infix fun DoubleArray.shouldContainExactly(other: DoubleArray) =
    this.toTypedArray() shouldContainExactly other.toTypedArray()

/**
 * Check if the [DoubleArray] contains exactly the same elements as the [other] [Collection]
 * (Same order, same elements)
 * @since 0.2.2
 * @version 0.2.2
 */
infix fun DoubleArray.shouldContainExactly(other: Collection<Double>) =
    this.toTypedArray() shouldContainExactly other.toTypedArray()

/**
 * Check if the [DoubleArray] contains exactly the same elements as the [other] [Sequence]
 * (Same order, same elements)
 * @since 0.2.2
 * @version 0.2.2
 */
infix fun DoubleArray.shouldContainExactly(other: Sequence<Double>) =
    this.toTypedArray() shouldContainExactly other.toList().toTypedArray()

/**
 * Check if the [BooleanArray] contains exactly the same elements as the [other] [BooleanArray]
 * (Same order, same elements)
 * @since 0.2.2
 * @version 0.2.2
 */
infix fun BooleanArray.shouldContainExactly(other: BooleanArray) =
    this.toTypedArray() shouldContainExactly other.toTypedArray()

/**
 * Check if the [BooleanArray] contains exactly the same elements as the [other] [Collection]
 * (Same order, same elements)
 * @since 0.2.2
 * @version 0.2.2
 */
infix fun BooleanArray.shouldContainExactly(other: Collection<Boolean>) =
    this.toTypedArray() shouldContainExactly other.toTypedArray()

/**
 * Check if the [BooleanArray] contains exactly the same elements as the [other] [Sequence]
 * (Same order, same elements)
 * @since 0.2.2
 * @version 0.2.2
 */
infix fun BooleanArray.shouldContainExactly(other: Sequence<Boolean>) =
    this.toTypedArray() shouldContainExactly other.toList().toTypedArray()

/**
 * Check if the [CharArray] contains exactly the same elements as the [other] [CharArray]
 * (Same order, same elements)
 * @since 0.2.2
 * @version 0.2.2
 */
infix fun CharArray.shouldContainExactly(other: CharArray) =
    this.toTypedArray() shouldContainExactly other.toTypedArray()

/**
 * Check if the [CharArray] contains exactly the same elements as the [other] [Collection]
 * (Same order, same elements)
 * @since 0.2.2
 * @version 0.2.2
 */
infix fun CharArray.shouldContainExactly(other: Collection<Char>) =
    this.toTypedArray() shouldContainExactly other.toTypedArray()

/**
 * Check if the [CharArray] contains exactly the same elements as the [other] [Sequence]
 * (Same order, same elements)
 * @since 0.2.2
 * @version 0.2.2
 */
infix fun CharArray.shouldContainExactly(other: Sequence<Char>) =
    this.toTypedArray() shouldContainExactly other.toList().toTypedArray()

/**
 * Check if the [UByteArray] contains exactly the same elements as the [other] [UByteArray]
 * (Same order, same elements)
 * @since 0.2.2
 * @version 0.2.2
 */
infix fun UByteArray.shouldContainExactly(other: UByteArray) =
    this.toTypedArray() shouldContainExactly other.toTypedArray()

/**
 * Check if the [UByteArray] contains exactly the same elements as the [other] [Collection]
 * (Same order, same elements)
 * @since 0.2.2
 * @version 0.2.2
 */
infix fun UByteArray.shouldContainExactly(other: Collection<UByte>) =
    this.toTypedArray() shouldContainExactly other.toTypedArray()

/**
 * Check if the [UByteArray] contains exactly the same elements as the [other] [Sequence]
 * (Same order, same elements)
 * @since 0.2.2
 * @version 0.2.2
 */
infix fun UByteArray.shouldContainExactly(other: Sequence<UByte>) =
    this.toTypedArray() shouldContainExactly other.toList().toTypedArray()

/**
 * Check if the [UShortArray] contains exactly the same elements as the [other] [UShortArray]
 * (Same order, same elements)
 * @since 0.2.2
 * @version 0.2.2
 */
infix fun UShortArray.shouldContainExactly(other: UShortArray) =
    this.toTypedArray() shouldContainExactly other.toTypedArray()

/**
 * Check if the [UShortArray] contains exactly the same elements as the [other] [Collection]
 * (Same order, same elements)
 * @since 0.2.2
 * @version 0.2.2
 */
infix fun UShortArray.shouldContainExactly(other: Collection<UShort>) =
    this.toTypedArray() shouldContainExactly other.toTypedArray()

/**
 * Check if the [UShortArray] contains exactly the same elements as the [other] [Sequence]
 * (Same order, same elements)
 * @since 0.2.2
 * @version 0.2.2
 */
infix fun UShortArray.shouldContainExactly(other: Sequence<UShort>) =
    this.toTypedArray() shouldContainExactly other.toList().toTypedArray()

/**
 * Check if the [UIntArray] contains exactly the same elements as the [other] [UIntArray]
 * (Same order, same elements)
 * @since 0.2.2
 * @version 0.2.2
 */
infix fun UIntArray.shouldContainExactly(other: UIntArray) =
    this.toTypedArray() shouldContainExactly other.toTypedArray()

/**
 * Check if the [UIntArray] contains exactly the same elements as the [other] [Collection]
 * (Same order, same elements)
 * @since 0.2.2
 * @version 0.2.2
 */
infix fun UIntArray.shouldContainExactly(other: Collection<UInt>) =
    this.toTypedArray() shouldContainExactly other.toTypedArray()

/**
 * Check if the [UIntArray] contains exactly the same elements as the [other] [Sequence]
 * (Same order, same elements)
 * @since 0.2.2
 * @version 0.2.2
 */
infix fun UIntArray.shouldContainExactly(other: Sequence<UInt>) =
    this.toTypedArray() shouldContainExactly other.toList().toTypedArray()

/**
 * Check if the [ULongArray] contains exactly the same elements as the [other] [ULongArray]
 * (Same order, same elements)
 * @since 0.2.2
 * @version 0.2.2
 */
infix fun ULongArray.shouldContainExactly(other: ULongArray) =
    this.toTypedArray() shouldContainExactly other.toTypedArray()

/**
 * Check if the [ULongArray] contains exactly the same elements as the [other] [Collection]
 * (Same order, same elements)
 * @since 0.2.2
 * @version 0.2.2
 */
infix fun ULongArray.shouldContainExactly(other: Collection<ULong>) =
    this.toTypedArray() shouldContainExactly other.toTypedArray()

/**
 * Check if the [ULongArray] contains exactly the same elements as the [other] [Sequence]
 * (Same order, same elements)
 * @since 0.2.2
 * @version 0.2.2
 */
infix fun ULongArray.shouldContainExactly(other: Sequence<ULong>) =
    this.toTypedArray() shouldContainExactly other.toList().toTypedArray()

/**
 * Check if the [ByteArray] does not contain exactly the same elements as the [other] [ByteArray]
 * (Same order, same elements)
 * @since 0.2.2
 * @version 0.2.2
 */
infix fun ByteArray.shouldNotContainExactly(other: ByteArray) =
    this.toTypedArray() shouldNotContainExactly other.toTypedArray()

/**
 * Check if the [ByteArray] does not contain exactly the same elements as the [other] [Collection]
 * (Same order, same elements)
 * @since 0.2.2
 * @version 0.2.2
 */
infix fun ByteArray.shouldNotContainExactly(other: Collection<Byte>) =
    this.toTypedArray() shouldNotContainExactly other.toTypedArray()

/**
 * Check if the [ByteArray] does not contain exactly the same elements as the [other] [Sequence]
 * (Same order, same elements)
 * @since 0.2.2
 * @version 0.2.2
 */
infix fun ByteArray.shouldNotContainExactly(other: Sequence<Byte>) =
    this.toTypedArray() shouldNotContainExactly other.toList().toTypedArray()

/**
 * Check if the [ShortArray] does not contain exactly the same elements as the [other] [ShortArray]
 * (Same order, same elements)
 * @since 0.2.2
 * @version 0.2.2
 */
infix fun ShortArray.shouldNotContainExactly(other: ShortArray) =
    this.toTypedArray() shouldNotContainExactly other.toTypedArray()

/**
 * Check if the [ShortArray] does not contain exactly the same elements as the [other] [Collection]
 * (Same order, same elements)
 * @since 0.2.2
 * @version 0.2.2
 */
infix fun ShortArray.shouldNotContainExactly(other: Collection<Short>) =
    this.toTypedArray() shouldNotContainExactly other.toTypedArray()

/**
 * Check if the [ShortArray] does not contain exactly the same elements as the [other] [Sequence]
 * (Same order, same elements)
 * @since 0.2.2
 * @version 0.2.2
 */
infix fun ShortArray.shouldNotContainExactly(other: Sequence<Short>) =
    this.toTypedArray() shouldNotContainExactly other.toList().toTypedArray()

/**
 * Check if the [IntArray] does not contain exactly the same elements as the [other] [IntArray]
 * (Same order, same elements)
 * @since 0.2.2
 * @version 0.2.2
 */
infix fun IntArray.shouldNotContainExactly(other: IntArray) =
    this.toTypedArray() shouldNotContainExactly other.toTypedArray()

/**
 * Check if the [IntArray] does not contain exactly the same elements as the [other] [Collection]
 * (Same order, same elements)
 * @since 0.2.2
 * @version 0.2.2
 */
infix fun IntArray.shouldNotContainExactly(other: Collection<Int>) =
    this.toTypedArray() shouldNotContainExactly other.toTypedArray()

/**
 * Check if the [IntArray] does not contain exactly the same elements as the [other] [Sequence]
 * (Same order, same elements)
 * @since 0.2.2
 * @version 0.2.2
 */
infix fun IntArray.shouldNotContainExactly(other: Sequence<Int>) =
    this.toTypedArray() shouldNotContainExactly other.toList().toTypedArray()

/**
 * Check if the [LongArray] does not contain exactly the same elements as the [other] [LongArray]
 * (Same order, same elements)
 * @since 0.2.2
 * @version 0.2.2
 */
infix fun LongArray.shouldNotContainExactly(other: LongArray) =
    this.toTypedArray() shouldNotContainExactly other.toTypedArray()

/**
 * Check if the [LongArray] does not contain exactly the same elements as the [other] [Collection]
 * (Same order, same elements)
 * @since 0.2.2
 * @version 0.2.2
 */
infix fun LongArray.shouldNotContainExactly(other: Collection<Long>) =
    this.toTypedArray() shouldNotContainExactly other.toTypedArray()

/**
 * Check if the [LongArray] does not contain exactly the same elements as the [other] [Sequence]
 * (Same order, same elements)
 * @since 0.2.2
 * @version 0.2.2
 */
infix fun LongArray.shouldNotContainExactly(other: Sequence<Long>) =
    this.toTypedArray() shouldNotContainExactly other.toList().toTypedArray()

/**
 * Check if the [FloatArray] does not contain exactly the same elements as the [other] [FloatArray]
 * (Same order, same elements)
 * @since 0.2.2
 * @version 0.2.2
 */
infix fun FloatArray.shouldNotContainExactly(other: FloatArray) =
    this.toTypedArray() shouldNotContainExactly other.toTypedArray()

/**
 * Check if the [FloatArray] does not contain exactly the same elements as the [other] [Collection]
 * (Same order, same elements)
 * @since 0.2.2
 * @version 0.2.2
 */
infix fun FloatArray.shouldNotContainExactly(other: Collection<Float>) =
    this.toTypedArray() shouldNotContainExactly other.toTypedArray()

/**
 * Check if the [FloatArray] does not contain exactly the same elements as the [other] [Sequence]
 * (Same order, same elements)
 * @since 0.2.2
 * @version 0.2.2
 */
infix fun FloatArray.shouldNotContainExactly(other: Sequence<Float>) =
    this.toTypedArray() shouldNotContainExactly other.toList().toTypedArray()

/**
 * Check if the [DoubleArray] does not contain exactly the same elements as the [other] [DoubleArray]
 * (Same order, same elements)
 * @since 0.2.2
 * @version 0.2.2
 */
infix fun DoubleArray.shouldNotContainExactly(other: DoubleArray) =
    this.toTypedArray() shouldNotContainExactly other.toTypedArray()

/**
 * Check if the [DoubleArray] does not contain exactly the same elements as the [other] [Collection]
 * (Same order, same elements)
 * @since 0.2.2
 * @version 0.2.2
 */
infix fun DoubleArray.shouldNotContainExactly(other: Collection<Double>) =
    this.toTypedArray() shouldNotContainExactly other.toTypedArray()

/**
 * Check if the [DoubleArray] does not contain exactly the same elements as the [other] [Sequence]
 * (Same order, same elements)
 * @since 0.2.2
 * @version 0.2.2
 */
infix fun DoubleArray.shouldNotContainExactly(other: Sequence<Double>) =
    this.toTypedArray() shouldNotContainExactly other.toList().toTypedArray()

/**
 * Check if the [BooleanArray] does not contain exactly the same elements as the [other] [BooleanArray]
 * (Same order, same elements)
 * @since 0.2.2
 * @version 0.2.2
 */
infix fun BooleanArray.shouldNotContainExactly(other: BooleanArray) =
    this.toTypedArray() shouldNotContainExactly other.toTypedArray()

/**
 * Check if the [BooleanArray] does not contain exactly the same elements as the [other] [Collection]
 * (Same order, same elements)
 * @since 0.2.2
 * @version 0.2.2
 */
infix fun BooleanArray.shouldNotContainExactly(other: Collection<Boolean>) =
    this.toTypedArray() shouldNotContainExactly other.toTypedArray()

/**
 * Check if the [BooleanArray] does not contain exactly the same elements as the [other] [Sequence]
 * (Same order, same elements)
 * @since 0.2.2
 * @version 0.2.2
 */
infix fun BooleanArray.shouldNotContainExactly(other: Sequence<Boolean>) =
    this.toTypedArray() shouldNotContainExactly other.toList().toTypedArray()

/**
 * Check if the [CharArray] does not contain exactly the same elements as the [other] [CharArray]
 * (Same order, same elements)
 * @since 0.2.2
 * @version 0.2.2
 */
infix fun CharArray.shouldNotContainExactly(other: CharArray) =
    this.toTypedArray() shouldNotContainExactly other.toTypedArray()

/**
 * Check if the [CharArray] does not contain exactly the same elements as the [other] [Collection]
 * (Same order, same elements)
 * @since 0.2.2
 * @version 0.2.2
 */
infix fun CharArray.shouldNotContainExactly(other: Collection<Char>) =
    this.toTypedArray() shouldNotContainExactly other.toTypedArray()

/**
 * Check if the [CharArray] does not contain exactly the same elements as the [other] [Sequence]
 * (Same order, same elements)
 * @since 0.2.2
 * @version 0.2.2
 */
infix fun CharArray.shouldNotContainExactly(other: Sequence<Char>) =
    this.toTypedArray() shouldNotContainExactly other.toList().toTypedArray()

/**
 * Check if the [UByteArray] does not contain exactly the same elements as the [other] [UByteArray]
 * (Same order, same elements)
 * @since 0.2.2
 * @version 0.2.2
 */
infix fun UByteArray.shouldNotContainExactly(other: UByteArray) =
    this.toTypedArray() shouldNotContainExactly other.toTypedArray()

/**
 * Check if the [UByteArray] does not contain exactly the same elements as the [other] [Collection]
 * (Same order, same elements)
 * @since 0.2.2
 * @version 0.2.2
 */
infix fun UByteArray.shouldNotContainExactly(other: Collection<UByte>) =
    this.toTypedArray() shouldNotContainExactly other.toTypedArray()

/**
 * Check if the [UByteArray] does not contain exactly the same elements as the [other] [Sequence]
 * (Same order, same elements)
 * @since 0.2.2
 * @version 0.2.2
 */
infix fun UByteArray.shouldNotContainExactly(other: Sequence<UByte>) =
    this.toTypedArray() shouldNotContainExactly other.toList().toTypedArray()

/**
 * Check if the [UShortArray] does not contain exactly the same elements as the [other] [UShortArray]
 * (Same order, same elements)
 * @since 0.2.2
 * @version 0.2.2
 */
infix fun UShortArray.shouldNotContainExactly(other: UShortArray) =
    this.toTypedArray() shouldNotContainExactly other.toTypedArray()

/**
 * Check if the [UShortArray] does not contain exactly the same elements as the [other] [Collection]
 * (Same order, same elements)
 * @since 0.2.2
 * @version 0.2.2
 */
infix fun UShortArray.shouldNotContainExactly(other: Collection<UShort>) =
    this.toTypedArray() shouldNotContainExactly other.toTypedArray()

/**
 * Check if the [UShortArray] does not contain exactly the same elements as the [other] [Sequence]
 * (Same order, same elements)
 * @since 0.2.2
 * @version 0.2.2
 */
infix fun UShortArray.shouldNotContainExactly(other: Sequence<UShort>) =
    this.toTypedArray() shouldNotContainExactly other.toList().toTypedArray()

/**
 * Check if the [UIntArray] does not contain exactly the same elements as the [other] [UIntArray]
 * (Same order, same elements)
 * @since 0.2.2
 * @version 0.2.2
 */
infix fun UIntArray.shouldNotContainExactly(other: UIntArray) =
    this.toTypedArray() shouldNotContainExactly other.toTypedArray()

/**
 * Check if the [UIntArray] does not contain exactly the same elements as the [other] [Collection]
 * (Same order, same elements)
 * @since 0.2.2
 * @version 0.2.2
 */
infix fun UIntArray.shouldNotContainExactly(other: Collection<UInt>) =
    this.toTypedArray() shouldNotContainExactly other.toTypedArray()

/**
 * Check if the [UIntArray] does not contain exactly the same elements as the [other] [Sequence]
 * (Same order, same elements)
 * @since 0.2.2
 * @version 0.2.2
 */
infix fun UIntArray.shouldNotContainExactly(other: Sequence<UInt>) =
    this.toTypedArray() shouldNotContainExactly other.toList().toTypedArray()

/**
 * Check if the [ULongArray] does not contain exactly the same elements as the [other] [ULongArray]
 * (Same order, same elements)
 * @since 0.2.2
 * @version 0.2.2
 */
infix fun ULongArray.shouldNotContainExactly(other: ULongArray) =
    this.toTypedArray() shouldNotContainExactly other.toTypedArray()

/**
 * Check if the [ULongArray] does not contain exactly the same elements as the [other] [Collection]
 * (Same order, same elements)
 * @since 0.2.2
 * @version 0.2.2
 */
infix fun ULongArray.shouldNotContainExactly(other: Collection<ULong>) =
    this.toTypedArray() shouldNotContainExactly other.toTypedArray()

/**
 * Check if the [ULongArray] does not contain exactly the same elements as the [other] [Sequence]
 * (Same order, same elements)
 * @since 0.2.2
 * @version 0.2.2
 */
infix fun ULongArray.shouldNotContainExactly(other: Sequence<ULong>) =
    this.toTypedArray() shouldNotContainExactly other.toList().toTypedArray()
