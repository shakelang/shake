@file:Suppress("unused")
package com.shakelang.shake.util.primitives.bits

import kotlin.experimental.and
import kotlin.experimental.or

val Byte.bit0: Boolean get() = (this and 0x01) != 0.toByte()
val Byte.bit1: Boolean get() = (this and 0x02) != 0.toByte()
val Byte.bit2: Boolean get() = (this and 0x04) != 0.toByte()
val Byte.bit3: Boolean get() = (this and 0x08) != 0.toByte()
val Byte.bit4: Boolean get() = (this and 0x10) != 0.toByte()
val Byte.bit5: Boolean get() = (this and 0x20) != 0.toByte()
val Byte.bit6: Boolean get() = (this and 0x40) != 0.toByte()
val Byte.bit7: Boolean get() = (this and -0x80) != 0.toByte()

val Short.bit0: Boolean get() = (this and 0x01) != 0.toShort()
val Short.bit1: Boolean get() = (this and 0x02) != 0.toShort()
val Short.bit2: Boolean get() = (this and 0x04) != 0.toShort()
val Short.bit3: Boolean get() = (this and 0x08) != 0.toShort()
val Short.bit4: Boolean get() = (this and 0x10) != 0.toShort()
val Short.bit5: Boolean get() = (this and 0x20) != 0.toShort()
val Short.bit6: Boolean get() = (this and 0x40) != 0.toShort()
val Short.bit7: Boolean get() = (this and 0x80) != 0.toShort()
val Short.bit8: Boolean get() = (this and 0x100) != 0.toShort()
val Short.bit9: Boolean get() = (this and 0x200) != 0.toShort()
val Short.bit10: Boolean get() = (this and 0x400) != 0.toShort()
val Short.bit11: Boolean get() = (this and 0x800) != 0.toShort()
val Short.bit12: Boolean get() = (this and 0x1000) != 0.toShort()
val Short.bit13: Boolean get() = (this and 0x2000) != 0.toShort()
val Short.bit14: Boolean get() = (this and 0x4000) != 0.toShort()
val Short.bit15: Boolean get() = (this and -0x8000) != 0.toShort()

val Int.bit0: Boolean get() = (this and 0x01) != 0
val Int.bit1: Boolean get() = (this and 0x02) != 0
val Int.bit2: Boolean get() = (this and 0x04) != 0
val Int.bit3: Boolean get() = (this and 0x08) != 0
val Int.bit4: Boolean get() = (this and 0x10) != 0
val Int.bit5: Boolean get() = (this and 0x20) != 0
val Int.bit6: Boolean get() = (this and 0x40) != 0
val Int.bit7: Boolean get() = (this and 0x80) != 0
val Int.bit8: Boolean get() = (this and 0x100) != 0
val Int.bit9: Boolean get() = (this and 0x200) != 0
val Int.bit10: Boolean get() = (this and 0x400) != 0
val Int.bit11: Boolean get() = (this and 0x800) != 0
val Int.bit12: Boolean get() = (this and 0x1000) != 0
val Int.bit13: Boolean get() = (this and 0x2000) != 0
val Int.bit14: Boolean get() = (this and 0x4000) != 0
val Int.bit15: Boolean get() = (this and 0x8000) != 0
val Int.bit16: Boolean get() = (this and 0x10000) != 0
val Int.bit17: Boolean get() = (this and 0x20000) != 0
val Int.bit18: Boolean get() = (this and 0x40000) != 0
val Int.bit19: Boolean get() = (this and 0x80000) != 0
val Int.bit20: Boolean get() = (this and 0x100000) != 0
val Int.bit21: Boolean get() = (this and 0x200000) != 0
val Int.bit22: Boolean get() = (this and 0x400000) != 0
val Int.bit23: Boolean get() = (this and 0x800000) != 0
val Int.bit24: Boolean get() = (this and 0x1000000) != 0
val Int.bit25: Boolean get() = (this and 0x2000000) != 0
val Int.bit26: Boolean get() = (this and 0x4000000) != 0
val Int.bit27: Boolean get() = (this and 0x8000000) != 0
val Int.bit28: Boolean get() = (this and 0x10000000) != 0
val Int.bit29: Boolean get() = (this and 0x20000000) != 0
val Int.bit30: Boolean get() = (this and 0x40000000) != 0
val Int.bit31: Boolean get() = (this and -0x80000000) != 0

val Long.bit0: Boolean get() = (this and 0x01) != 0L
val Long.bit1: Boolean get() = (this and 0x02) != 0L
val Long.bit2: Boolean get() = (this and 0x04) != 0L
val Long.bit3: Boolean get() = (this and 0x08) != 0L
val Long.bit4: Boolean get() = (this and 0x10) != 0L
val Long.bit5: Boolean get() = (this and 0x20) != 0L
val Long.bit6: Boolean get() = (this and 0x40) != 0L
val Long.bit7: Boolean get() = (this and 0x80) != 0L
val Long.bit8: Boolean get() = (this and 0x100) != 0L
val Long.bit9: Boolean get() = (this and 0x200) != 0L
val Long.bit10: Boolean get() = (this and 0x400) != 0L
val Long.bit11: Boolean get() = (this and 0x800) != 0L
val Long.bit12: Boolean get() = (this and 0x1000) != 0L
val Long.bit13: Boolean get() = (this and 0x2000) != 0L
val Long.bit14: Boolean get() = (this and 0x4000) != 0L
val Long.bit15: Boolean get() = (this and 0x8000) != 0L
val Long.bit16: Boolean get() = (this and 0x10000) != 0L
val Long.bit17: Boolean get() = (this and 0x20000) != 0L
val Long.bit18: Boolean get() = (this and 0x40000) != 0L
val Long.bit19: Boolean get() = (this and 0x80000) != 0L
val Long.bit20: Boolean get() = (this and 0x100000) != 0L
val Long.bit21: Boolean get() = (this and 0x200000) != 0L
val Long.bit22: Boolean get() = (this and 0x400000) != 0L
val Long.bit23: Boolean get() = (this and 0x800000) != 0L
val Long.bit24: Boolean get() = (this and 0x1000000) != 0L
val Long.bit25: Boolean get() = (this and 0x2000000) != 0L
val Long.bit26: Boolean get() = (this and 0x4000000) != 0L
val Long.bit27: Boolean get() = (this and 0x8000000) != 0L
val Long.bit28: Boolean get() = (this and 0x10000000) != 0L
val Long.bit29: Boolean get() = (this and 0x20000000) != 0L
val Long.bit30: Boolean get() = (this and 0x40000000) != 0L
val Long.bit31: Boolean get() = (this and 0x80000000L) != 0L
val Long.bit32: Boolean get() = (this and 0x100000000L) != 0L
val Long.bit33: Boolean get() = (this and 0x200000000L) != 0L
val Long.bit34: Boolean get() = (this and 0x400000000L) != 0L
val Long.bit35: Boolean get() = (this and 0x800000000L) != 0L
val Long.bit36: Boolean get() = (this and 0x1000000000L) != 0L
val Long.bit37: Boolean get() = (this and 0x2000000000L) != 0L
val Long.bit38: Boolean get() = (this and 0x4000000000L) != 0L
val Long.bit39: Boolean get() = (this and 0x8000000000L) != 0L
val Long.bit40: Boolean get() = (this and 0x10000000000L) != 0L
val Long.bit41: Boolean get() = (this and 0x20000000000L) != 0L
val Long.bit42: Boolean get() = (this and 0x40000000000L) != 0L
val Long.bit43: Boolean get() = (this and 0x80000000000L) != 0L
val Long.bit44: Boolean get() = (this and 0x100000000000L) != 0L
val Long.bit45: Boolean get() = (this and 0x200000000000L) != 0L
val Long.bit46: Boolean get() = (this and 0x400000000000L) != 0L
val Long.bit47: Boolean get() = (this and 0x800000000000L) != 0L
val Long.bit48: Boolean get() = (this and 0x1000000000000L) != 0L
val Long.bit49: Boolean get() = (this and 0x2000000000000L) != 0L
val Long.bit50: Boolean get() = (this and 0x4000000000000L) != 0L
val Long.bit51: Boolean get() = (this and 0x8000000000000L) != 0L
val Long.bit52: Boolean get() = (this and 0x10000000000000L) != 0L
val Long.bit53: Boolean get() = (this and 0x20000000000000L) != 0L
val Long.bit54: Boolean get() = (this and 0x40000000000000L) != 0L
val Long.bit55: Boolean get() = (this and 0x80000000000000L) != 0L
val Long.bit56: Boolean get() = (this and 0x100000000000000L) != 0L
val Long.bit57: Boolean get() = (this and 0x200000000000000L) != 0L
val Long.bit58: Boolean get() = (this and 0x400000000000000L) != 0L
val Long.bit59: Boolean get() = (this and 0x800000000000000L) != 0L
val Long.bit60: Boolean get() = (this and 0x1000000000000000L) != 0L
val Long.bit61: Boolean get() = (this and 0x2000000000000000L) != 0L
val Long.bit62: Boolean get() = (this and 0x4000000000000000L) != 0L
val Long.bit63: Boolean get() = (this and 0x8000000000000000uL.toLong()) != 0L

class ByteBits(private val byte: Byte) : List<Boolean> {
    override val size: Int get() = 8

    override operator fun get(index: Int) = byte and (1 shl index).toByte() != 0.toByte()

    override fun containsAll(elements: Collection<Boolean>): Boolean {

        for (element in elements) {
            if (!contains(element)) return false
        }

        return true
    }

    override fun contains(element: Boolean): Boolean {
        for (i in 0 until size) {
            if (get(i) == element) return true
        }
        return false
    }

    override fun isEmpty(): Boolean = false

    override fun iterator(): Iterator<Boolean> = toList().iterator()

    override fun listIterator() = toList().listIterator()

    override fun listIterator(index: Int) = toList().listIterator(index)

    override fun subList(fromIndex: Int, toIndex: Int) = toList().subList(fromIndex, toIndex)

    override fun lastIndexOf(element: Boolean) = toList().lastIndexOf(element)

    override fun indexOf(element: Boolean) = toList().indexOf(element)

    fun toList(): List<Boolean> = BooleanArray(8) { get(it) }.toList()
}

val Byte.bits: ByteBits get() = ByteBits(this)

class ShortBits(private val short: Short) : List<Boolean> {
    override operator fun get(index: Int) = short and (1 shl index).toShort() != 0.toShort()

    fun toList(): List<Boolean> = BooleanArray(16) { get(it) }.toList()

    override val size: Int get() = 16

    override fun contains(element: Boolean): Boolean {
        for (i in 0 until size) {
            if (get(i) == element) return true
        }
        return false
    }

    override fun containsAll(elements: Collection<Boolean>): Boolean {
        for (element in elements) {
            if (!contains(element)) return false
        }
        return true
    }

    override fun isEmpty(): Boolean = false

    override fun iterator(): Iterator<Boolean> = toList().iterator()

    override fun listIterator(): ListIterator<Boolean> = toList().listIterator()

    override fun listIterator(index: Int): ListIterator<Boolean> = toList().listIterator(index)

    override fun subList(fromIndex: Int, toIndex: Int): List<Boolean> = toList().subList(fromIndex, toIndex)

    override fun lastIndexOf(element: Boolean): Int = toList().lastIndexOf(element)

    override fun indexOf(element: Boolean): Int = toList().indexOf(element)

}

val Short.bits: ShortBits get() = ShortBits(this)

class IntBits(private val int: Int) : List<Boolean> {
    override operator fun get(index: Int) = int and (1 shl index) != 0

    fun toList(): List<Boolean> = BooleanArray(32) { get(it) }.toList()

    override val size: Int get() = 32

    override fun contains(element: Boolean): Boolean {
        for (i in 0 until size) {
            if (get(i) == element) return true
        }
        return false
    }

    override fun containsAll(elements: Collection<Boolean>): Boolean {
        for (element in elements) {
            if (!contains(element)) return false
        }
        return true
    }

    override fun isEmpty(): Boolean = false
    override fun iterator(): Iterator<Boolean> = toList().iterator()
    override fun listIterator(): ListIterator<Boolean> = toList().listIterator()
    override fun listIterator(index: Int): ListIterator<Boolean> = toList().listIterator(index)
    override fun subList(fromIndex: Int, toIndex: Int): List<Boolean> = toList().subList(fromIndex, toIndex)
    override fun lastIndexOf(element: Boolean): Int = toList().lastIndexOf(element)
    override fun indexOf(element: Boolean): Int = toList().indexOf(element)
}

val Int.bits: IntBits get() = IntBits(this)

class LongBits(private val long: Long) : List<Boolean> {
    override operator fun get(index: Int) = long and (1 shl index).toLong() != 0L

    fun toList(): List<Boolean> = BooleanArray(64) { get(it) }.toList()

    override val size: Int get() = 64

    override fun contains(element: Boolean): Boolean {
        for (i in 0 until size) {
            if (get(i) == element) return true
        }
        return false
    }

    override fun containsAll(elements: Collection<Boolean>): Boolean {
        for (element in elements) {
            if (!contains(element)) return false
        }
        return true
    }

    override fun isEmpty(): Boolean = false

    override fun iterator(): Iterator<Boolean> = toList().iterator()

    override fun listIterator(): ListIterator<Boolean> = toList().listIterator()

    override fun listIterator(index: Int): ListIterator<Boolean> = toList().listIterator(index)

    override fun subList(fromIndex: Int, toIndex: Int): List<Boolean> = toList().subList(fromIndex, toIndex)

    override fun lastIndexOf(element: Boolean): Int = toList().lastIndexOf(element)

    override fun indexOf(element: Boolean): Int = toList().indexOf(element)

}

val Long.bits: LongBits get() = LongBits(this)

fun Byte.setBit0(value: Boolean): Byte = if (value) (this or 0x01) else (this and 0xFE.toByte())
fun Byte.setBit1(value: Boolean): Byte = if (value) (this or 0x02) else (this and 0xFD.toByte())
fun Byte.setBit2(value: Boolean): Byte = if (value) (this or 0x04) else (this and 0xFB.toByte())
fun Byte.setBit3(value: Boolean): Byte = if (value) (this or 0x08) else (this and 0xF7.toByte())
fun Byte.setBit4(value: Boolean): Byte = if (value) (this or 0x10) else (this and 0xEF.toByte())
fun Byte.setBit5(value: Boolean): Byte = if (value) (this or 0x20) else (this and 0xDF.toByte())
fun Byte.setBit6(value: Boolean): Byte = if (value) (this or 0x40) else (this and 0xBF.toByte())
fun Byte.setBit7(value: Boolean): Byte = if (value) (this or -0x80) else (this and 0x7F)

fun Short.setBit0(value: Boolean): Short = if (value) (this or 0x01) else (this and 0xFE)
fun Short.setBit1(value: Boolean): Short = if (value) (this or 0x02) else (this and 0xFD)
fun Short.setBit2(value: Boolean): Short = if (value) (this or 0x04) else (this and 0xFB)
fun Short.setBit3(value: Boolean): Short = if (value) (this or 0x08) else (this and 0xF7)
fun Short.setBit4(value: Boolean): Short = if (value) (this or 0x10) else (this and 0xEF)
fun Short.setBit5(value: Boolean): Short = if (value) (this or 0x20) else (this and 0xDF)
fun Short.setBit6(value: Boolean): Short = if (value) (this or 0x40) else (this and 0xBF)
fun Short.setBit7(value: Boolean): Short = if (value) (this or 0x80) else (this and 0x7F)
fun Short.setBit8(value: Boolean): Short = if (value) (this or 0x100) else (this and 0xFEFF.toShort())
fun Short.setBit9(value: Boolean): Short = if (value) (this or 0x200) else (this and 0xFDFF.toShort())
fun Short.setBit10(value: Boolean): Short = if (value) (this or 0x400) else (this and 0xFBFF.toShort())
fun Short.setBit11(value: Boolean): Short = if (value) (this or 0x800) else (this and 0xF7FF.toShort())
fun Short.setBit12(value: Boolean): Short = if (value) (this or 0x1000) else (this and 0xEFFF.toShort())
fun Short.setBit13(value: Boolean): Short = if (value) (this or 0x2000) else (this and 0xDFFF.toShort())
fun Short.setBit14(value: Boolean): Short = if (value) (this or 0x4000) else (this and 0xBFFF.toShort())
fun Short.setBit15(value: Boolean): Short = if (value) (this or -0x8000) else (this and 0x7FFF)

fun Int.setBit0(value: Boolean): Int = if (value) this or 0x01 else this and 0xFFFFFFFEu.toInt()
fun Int.setBit1(value: Boolean): Int = if (value) this or 0x02 else this and 0xFFFFFFFDu.toInt()
fun Int.setBit2(value: Boolean): Int = if (value) this or 0x04 else this and 0xFFFFFFFBu.toInt()
fun Int.setBit3(value: Boolean): Int = if (value) this or 0x08 else this and 0xFFFFFFF7u.toInt()
fun Int.setBit4(value: Boolean): Int = if (value) this or 0x10 else this and 0xFFFFFFEFu.toInt()
fun Int.setBit5(value: Boolean): Int = if (value) this or 0x20 else this and 0xFFFFFFDFu.toInt()
fun Int.setBit6(value: Boolean): Int = if (value) this or 0x40 else this and 0xFFFFFFBFu.toInt()
fun Int.setBit7(value: Boolean): Int = if (value) this or 0x80 else this and 0xFFFFFF7Fu.toInt()
fun Int.setBit8(value: Boolean): Int = if (value) this or 0x100 else this and 0xFFFFFEFFu.toInt()
fun Int.setBit9(value: Boolean): Int = if (value) this or 0x200 else this and 0xFFFFFDFFu.toInt()
fun Int.setBit10(value: Boolean): Int = if (value) this or 0x400 else this and 0xFFFFFBFFu.toInt()
fun Int.setBit11(value: Boolean): Int = if (value) this or 0x800 else this and 0xFFFFF7FFu.toInt()
fun Int.setBit12(value: Boolean): Int = if (value) this or 0x1000 else this and 0xFFFFEFFFu.toInt()
fun Int.setBit13(value: Boolean): Int = if (value) this or 0x2000 else this and 0xFFFFFBFFu.toInt()
fun Int.setBit14(value: Boolean): Int = if (value) this or 0x4000 else this and 0xFFFFF7FFu.toInt()
fun Int.setBit15(value: Boolean): Int = if (value) this or 0x8000 else this and 0xFFFF7FFFu.toInt()
fun Int.setBit16(value: Boolean): Int = if (value) this or 0x10000 else this and 0xFFFEFFFFu.toInt()
fun Int.setBit17(value: Boolean): Int = if (value) this or 0x20000 else this and 0xFFFDFFFFu.toInt()
fun Int.setBit18(value: Boolean): Int = if (value) this or 0x40000 else this and 0xFFFBFFFFu.toInt()
fun Int.setBit19(value: Boolean): Int = if (value) this or 0x80000 else this and 0xFFF7FFFFu.toInt()
fun Int.setBit20(value: Boolean): Int = if (value) this or 0x100000 else this and 0xFFEFFFFFu.toInt()
fun Int.setBit21(value: Boolean): Int = if (value) this or 0x200000 else this and 0xFFDFFFFFu.toInt()
fun Int.setBit22(value: Boolean): Int = if (value) this or 0x400000 else this and 0xFFBFFFFFu.toInt()
fun Int.setBit23(value: Boolean): Int = if (value) this or 0x800000 else this and 0xFF7FFFFFu.toInt()
fun Int.setBit24(value: Boolean): Int = if (value) this or 0x1000000 else this and 0xFEFFFFFFu.toInt()
fun Int.setBit25(value: Boolean): Int = if (value) this or 0x2000000 else this and 0xFDFFFFFFu.toInt()
fun Int.setBit26(value: Boolean): Int = if (value) this or 0x4000000 else this and 0xFBFFFFFFu.toInt()
fun Int.setBit27(value: Boolean): Int = if (value) this or 0x8000000 else this and 0xF7FFFFFFu.toInt()
fun Int.setBit28(value: Boolean): Int = if (value) this or 0x10000000 else this and 0xEFFFFFFFu.toInt()
fun Int.setBit29(value: Boolean): Int = if (value) this or 0x20000000 else this and 0xDFFFFFFFu.toInt()
fun Int.setBit30(value: Boolean): Int = if (value) this or 0x40000000 else this and 0xBFFFFFFFu.toInt()
fun Int.setBit31(value: Boolean): Int = if (value) this or -0x80000000 else this and 0x7FFFFFFFu.toInt()

fun Long.setBit0(value: Boolean): Long = if (value) this or 0x01 else this and 0xFFFFFFFFFFFFFFFEu.toLong()
fun Long.setBit1(value: Boolean): Long = if (value) this or 0x02 else this and 0xFFFFFFFFFFFFFFFDu.toLong()
fun Long.setBit2(value: Boolean): Long = if (value) this or 0x04 else this and 0xFFFFFFFFFFFFFFFBu.toLong()
fun Long.setBit3(value: Boolean): Long = if (value) this or 0x08 else this and 0xFFFFFFFFFFFFFFF7u.toLong()
fun Long.setBit4(value: Boolean): Long = if (value) this or 0x10 else this and 0xFFFFFFFFFFFFFFEFu.toLong()
fun Long.setBit5(value: Boolean): Long = if (value) this or 0x20 else this and 0xFFFFFFFFFFFFFFDFu.toLong()
fun Long.setBit6(value: Boolean): Long = if (value) this or 0x40 else this and 0xFFFFFFFFFFFFFFBFu.toLong()
fun Long.setBit7(value: Boolean): Long = if (value) this or 0x80 else this and 0xFFFFFFFFFFFFFF7Fu.toLong()
fun Long.setBit8(value: Boolean): Long = if (value) this or 0x100 else this and 0xFFFFFFFFFFFFFEFFu.toLong()
fun Long.setBit9(value: Boolean): Long = if (value) this or 0x200 else this and 0xFFFFFFFFFFFFFDFFu.toLong()
fun Long.setBit10(value: Boolean): Long = if (value) this or 0x400 else this and 0xFFFFFFFFFFFFFBFFu.toLong()
fun Long.setBit11(value: Boolean): Long = if (value) this or 0x800 else this and 0xFFFFFFFFFFFFF7FFu.toLong()
fun Long.setBit12(value: Boolean): Long = if (value) this or 0x1000 else this and 0xFFFFFFFFFFFFEFFFu.toLong()
fun Long.setBit13(value: Boolean): Long = if (value) this or 0x2000 else this and 0xFFFFFFFFFFFFDFFFu.toLong()
fun Long.setBit14(value: Boolean): Long = if (value) this or 0x4000 else this and 0xFFFFFFFFFFFFBFFFu.toLong()
fun Long.setBit15(value: Boolean): Long = if (value) this or 0x8000 else this and 0xFFFFFFFFFFFF7FFFu.toLong()
fun Long.setBit16(value: Boolean): Long = if (value) this or 0x10000 else this and 0xFFFFFFFFFFFEFFFFu.toLong()
fun Long.setBit17(value: Boolean): Long = if (value) this or 0x20000 else this and 0xFFFFFFFFFFFDFFFFu.toLong()
fun Long.setBit18(value: Boolean): Long = if (value) this or 0x40000 else this and 0xFFFFFFFFFFFBFFFFu.toLong()
fun Long.setBit19(value: Boolean): Long = if (value) this or 0x80000 else this and 0xFFFFFFFFFFF7FFFFu.toLong()
fun Long.setBit20(value: Boolean): Long = if (value) this or 0x100000 else this and 0xFFFFFFFFFFEFFFFFu.toLong()
fun Long.setBit21(value: Boolean): Long = if (value) this or 0x200000 else this and 0xFFFFFFFFFFDFFFFFu.toLong()
fun Long.setBit22(value: Boolean): Long = if (value) this or 0x400000 else this and 0xFFFFFFFFFFBFFFFFu.toLong()
fun Long.setBit23(value: Boolean): Long = if (value) this or 0x800000 else this and 0xFFFFFFFFFF7FFFFFu.toLong()
fun Long.setBit24(value: Boolean): Long = if (value) this or 0x1000000 else this and 0xFFFFFFFFFEFFFFFFu.toLong()
fun Long.setBit25(value: Boolean): Long = if (value) this or 0x2000000 else this and 0xFFFFFFFFFDFFFFFFu.toLong()
fun Long.setBit26(value: Boolean): Long = if (value) this or 0x4000000 else this and 0xFFFFFFFFFBFFFFFFu.toLong()
fun Long.setBit27(value: Boolean): Long = if (value) this or 0x8000000 else this and 0xFFFFFFFFF7FFFFFFu.toLong()
fun Long.setBit28(value: Boolean): Long = if (value) this or 0x10000000 else this and 0xFFFFFFFFEFFFFFFFu.toLong()
fun Long.setBit29(value: Boolean): Long = if (value) this or 0x20000000 else this and 0xFFFFFFFFDFFFFFFFu.toLong()
fun Long.setBit30(value: Boolean): Long = if (value) this or 0x40000000 else this and 0xFFFFFFFFBFFFFFFFu.toLong()
fun Long.setBit31(value: Boolean): Long = if (value) this or 0x80000000 else this and 0xFFFFFFFF7FFFFFFFu.toLong()
fun Long.setBit32(value: Boolean): Long = if (value) this or 0x100000000 else this and 0xFFFFFFFEFFFFFFFFu.toLong()
fun Long.setBit33(value: Boolean): Long = if (value) this or 0x200000000 else this and 0xFFFFFFFDFFFFFFFFu.toLong()
fun Long.setBit34(value: Boolean): Long = if (value) this or 0x400000000 else this and 0xFFFFFFFBFFFFFFFFu.toLong()
fun Long.setBit35(value: Boolean): Long = if (value) this or 0x800000000 else this and 0xFFFFFFF7FFFFFFFFu.toLong()
fun Long.setBit36(value: Boolean): Long = if (value) this or 0x1000000000 else this and 0xFFFFFFEFFFFFFFFFu.toLong()
fun Long.setBit37(value: Boolean): Long = if (value) this or 0x2000000000 else this and 0xFFFFFFDFFFFFFFFFu.toLong()
fun Long.setBit38(value: Boolean): Long = if (value) this or 0x4000000000 else this and 0xFFFFFFBFFFFFFFFFu.toLong()
fun Long.setBit39(value: Boolean): Long = if (value) this or 0x8000000000 else this and 0xFFFFFF7FFFFFFFFFu.toLong()
fun Long.setBit40(value: Boolean): Long = if (value) this or 0x10000000000 else this and 0xFFFFFEFFFFFFFFFFu.toLong()
fun Long.setBit41(value: Boolean): Long = if (value) this or 0x20000000000 else this and 0xFFFFFDFFFFFFFFFFu.toLong()
fun Long.setBit42(value: Boolean): Long = if (value) this or 0x40000000000 else this and 0xFFFFFBFFFFFFFFFFu.toLong()
fun Long.setBit43(value: Boolean): Long = if (value) this or 0x80000000000 else this and 0xFFFFF7FFFFFFFFFFu.toLong()
fun Long.setBit44(value: Boolean): Long = if (value) this or 0x100000000000 else this and 0xFFFFEFFFFFFFFFFFu.toLong()
fun Long.setBit45(value: Boolean): Long = if (value) this or 0x200000000000 else this and 0xFFFFDFFFFFFFFFFFu.toLong()
fun Long.setBit46(value: Boolean): Long = if (value) this or 0x400000000000 else this and 0xFFFFBFFFFFFFFFFFu.toLong()
fun Long.setBit47(value: Boolean): Long = if (value) this or 0x800000000000 else this and 0xFFFF7FFFFFFFFFFFu.toLong()
fun Long.setBit48(value: Boolean): Long = if (value) this or 0x1000000000000 else this and 0xFFFEFFFFFFFFFFFfu.toLong()
fun Long.setBit49(value: Boolean): Long = if (value) this or 0x2000000000000 else this and 0xFFFDFFFFFFFFFFFfu.toLong()
fun Long.setBit50(value: Boolean): Long = if (value) this or 0x4000000000000 else this and 0xFFFBFFFFFFFFFFFfu.toLong()
fun Long.setBit51(value: Boolean): Long = if (value) this or 0x8000000000000 else this and 0xFFF7FFFFFFFFFFFfu.toLong()
fun Long.setBit52(value: Boolean): Long = if (value) this or 0x10000000000000 else this and 0xFFEFFFFFFFFFFFFFu.toLong()
fun Long.setBit53(value: Boolean): Long = if (value) this or 0x20000000000000 else this and 0xFFDFFFFFFFFFFFFFu.toLong()
fun Long.setBit54(value: Boolean): Long = if (value) this or 0x40000000000000 else this and 0xFFBFFFFFFFFFFFFFu.toLong()
fun Long.setBit55(value: Boolean): Long = if (value) this or 0x80000000000000 else this and 0xFF7FFFFFFFFFFFFFu.toLong()
fun Long.setBit56(value: Boolean): Long = if (value) this or 0x100000000000000 else this and 0xFEFFFFFFFFFFFFFFu.toLong()
fun Long.setBit57(value: Boolean): Long = if (value) this or 0x200000000000000 else this and 0xFDFFFFFFFFFFFFFFu.toLong()
fun Long.setBit58(value: Boolean): Long = if (value) this or 0x400000000000000 else this and 0xFBFFFFFFFFFFFFFFu.toLong()
fun Long.setBit59(value: Boolean): Long = if (value) this or 0x800000000000000 else this and 0xF7FFFFFFFFFFFFFFu.toLong()
fun Long.setBit60(value: Boolean): Long = if (value) this or 0x1000000000000000 else this and 0xEFFFFFFFFFFFFFFFu.toLong()
fun Long.setBit61(value: Boolean): Long = if (value) this or 0x2000000000000000 else this and 0xDFFFFFFFFFFFFFFFu.toLong()
fun Long.setBit62(value: Boolean): Long = if (value) this or 0x4000000000000000 else this and 0xBFFFFFFFFFFFFFFFu.toLong()
fun Long.setBit63(value: Boolean): Long = if (value) this or 0x8000000000000000uL.toLong() else this and 0x7FFFFFFFFFFFFFFFu.toLong()

