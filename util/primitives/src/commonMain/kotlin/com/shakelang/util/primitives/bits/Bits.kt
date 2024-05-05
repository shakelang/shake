@file:Suppress("unused")

package com.shakelang.util.primitives.bits

import kotlin.experimental.and
import kotlin.experimental.or

/**
 * Get bit with index 0 of a byte
 */
val Byte.bit0: Boolean get() = (this and 0x01) != 0.toByte()

/**
 * Get bit with index 1 of a byte
 */
val Byte.bit1: Boolean get() = (this and 0x02) != 0.toByte()

/**
 * Get bit with index 2 of a byte
 */
val Byte.bit2: Boolean get() = (this and 0x04) != 0.toByte()

/**
 * Get bit with index 3 of a byte
 */
val Byte.bit3: Boolean get() = (this and 0x08) != 0.toByte()

/**
 * Get bit with index 4 of a byte
 */
val Byte.bit4: Boolean get() = (this and 0x10) != 0.toByte()

/**
 * Get bit with index 5 of a byte
 */
val Byte.bit5: Boolean get() = (this and 0x20) != 0.toByte()

/**
 * Get bit with index 6 of a byte
 */
val Byte.bit6: Boolean get() = (this and 0x40) != 0.toByte()

/**
 * Get bit with index 7 of a byte
 */
val Byte.bit7: Boolean get() = (this and -0x80) != 0.toByte()

/**
 * Get n-th bit of a byte
 * @param n index of the bit to get
 * @return true if the bit is set, false otherwise
 */
fun Byte.bit(n: Int): Boolean = (this and (1 shl n).toByte()) != 0.toByte()

/**
 * Get bit with index 0 of a short
 */
val Short.bit0: Boolean get() = (this and 0x01) != 0.toShort()

/**
 * Get bit with index 1 of a short
 */
val Short.bit1: Boolean get() = (this and 0x02) != 0.toShort()

/**
 * Get bit with index 2 of a short
 */
val Short.bit2: Boolean get() = (this and 0x04) != 0.toShort()

/**
 * Get bit with index 3 of a short
 */
val Short.bit3: Boolean get() = (this and 0x08) != 0.toShort()

/**
 * Get bit with index 4 of a short
 */
val Short.bit4: Boolean get() = (this and 0x10) != 0.toShort()

/**
 * Get bit with index 5 of a short
 */
val Short.bit5: Boolean get() = (this and 0x20) != 0.toShort()

/**
 * Get bit with index 6 of a short
 */
val Short.bit6: Boolean get() = (this and 0x40) != 0.toShort()

/**
 * Get bit with index 7 of a short
 */
val Short.bit7: Boolean get() = (this and 0x80) != 0.toShort()

/**
 * Get bit with index 8 of a short
 */
val Short.bit8: Boolean get() = (this and 0x100) != 0.toShort()

/**
 * Get bit with index 9 of a short
 */
val Short.bit9: Boolean get() = (this and 0x200) != 0.toShort()

/**
 * Get bit with index 10 of a short
 */
val Short.bit10: Boolean get() = (this and 0x400) != 0.toShort()

/**
 * Get bit with index 11 of a short
 */
val Short.bit11: Boolean get() = (this and 0x800) != 0.toShort()

/**
 * Get bit with index 12 of a short
 */
val Short.bit12: Boolean get() = (this and 0x1000) != 0.toShort()

/**
 * Get bit with index 13 of a short
 */
val Short.bit13: Boolean get() = (this and 0x2000) != 0.toShort()

/**
 * Get bit with index 14 of a short
 */
val Short.bit14: Boolean get() = (this and 0x4000) != 0.toShort()

/**
 * Get bit with index 15 of a short
 */
val Short.bit15: Boolean get() = (this and -0x8000) != 0.toShort()

/**
 * Get n-th bit of a short
 * @param n index of the bit to get
 * @return true if the bit is set, false otherwise
 */
fun Short.bit(n: Int): Boolean = (this and (1 shl n).toShort()) != 0.toShort()

/**
 * Get bit with index 0 of an int
 */
val Int.bit0: Boolean get() = (this and 0x01) != 0

/**
 * Get bit with index 1 of an int
 */
val Int.bit1: Boolean get() = (this and 0x02) != 0

/**
 * Get bit with index 2 of an int
 */
val Int.bit2: Boolean get() = (this and 0x04) != 0

/**
 * Get bit with index 3 of an int
 */
val Int.bit3: Boolean get() = (this and 0x08) != 0

/**
 * Get bit with index 4 of an int
 */
val Int.bit4: Boolean get() = (this and 0x10) != 0

/**
 * Get bit with index 5 of an int
 */
val Int.bit5: Boolean get() = (this and 0x20) != 0

/**
 * Get bit with index 6 of an int
 */
val Int.bit6: Boolean get() = (this and 0x40) != 0

/**
 * Get bit with index 7 of an int
 */
val Int.bit7: Boolean get() = (this and 0x80) != 0

/**
 * Get bit with index 8 of an int
 */
val Int.bit8: Boolean get() = (this and 0x100) != 0

/**
 * Get bit with index 9 of an int
 */
val Int.bit9: Boolean get() = (this and 0x200) != 0

/**
 * Get bit with index 10 of an int
 */
val Int.bit10: Boolean get() = (this and 0x400) != 0

/**
 * Get bit with index 11 of an int
 */
val Int.bit11: Boolean get() = (this and 0x800) != 0

/**
 * Get bit with index 12 of an int
 */
val Int.bit12: Boolean get() = (this and 0x1000) != 0

/**
 * Get bit with index 13 of an int
 */
val Int.bit13: Boolean get() = (this and 0x2000) != 0

/**
 * Get bit with index 14 of an int
 */
val Int.bit14: Boolean get() = (this and 0x4000) != 0

/**
 * Get bit with index 15 of an int
 */
val Int.bit15: Boolean get() = (this and 0x8000) != 0

/**
 * Get bit with index 16 of an int
 */
val Int.bit16: Boolean get() = (this and 0x10000) != 0

/**
 * Get bit with index 17 of an int
 */
val Int.bit17: Boolean get() = (this and 0x20000) != 0

/**
 * Get bit with index 18 of an int
 */
val Int.bit18: Boolean get() = (this and 0x40000) != 0

/**
 * Get bit with index 19 of an int
 */
val Int.bit19: Boolean get() = (this and 0x80000) != 0

/**
 * Get bit with index 20 of an int
 */
val Int.bit20: Boolean get() = (this and 0x100000) != 0

/**
 * Get bit with index 21 of an int
 */
val Int.bit21: Boolean get() = (this and 0x200000) != 0

/**
 * Get bit with index 22 of an int
 */
val Int.bit22: Boolean get() = (this and 0x400000) != 0

/**
 * Get bit with index 23 of an int
 */
val Int.bit23: Boolean get() = (this and 0x800000) != 0

/**
 * Get bit with index 24 of an int
 */
val Int.bit24: Boolean get() = (this and 0x1000000) != 0

/**
 * Get bit with index 25 of an int
 */
val Int.bit25: Boolean get() = (this and 0x2000000) != 0

/**
 * Get bit with index 26 of an int
 */
val Int.bit26: Boolean get() = (this and 0x4000000) != 0

/**
 * Get bit with index 27 of an int
 */
val Int.bit27: Boolean get() = (this and 0x8000000) != 0

/**
 * Get bit with index 28 of an int
 */
val Int.bit28: Boolean get() = (this and 0x10000000) != 0

/**
 * Get bit with index 29 of an int
 */
val Int.bit29: Boolean get() = (this and 0x20000000) != 0

/**
 * Get bit with index 30 of an int
 */
val Int.bit30: Boolean get() = (this and 0x40000000) != 0

/**
 * Get bit with index 31 of an int
 */
val Int.bit31: Boolean get() = (this and -0x80000000) != 0

/**
 * Get n-th bit of an int
 * @param n index of the bit to get
 * @return true if the bit is set, false otherwise
 */
fun Int.bit(n: Int): Boolean = (this and (1 shl n)) != 0

/**
 * Get bit with index 0 of a long
 */
val Long.bit0: Boolean get() = (this and 0x01) != 0L

/**
 * Get bit with index 1 of a long
 */
val Long.bit1: Boolean get() = (this and 0x02) != 0L

/**
 * Get bit with index 2 of a long
 */
val Long.bit2: Boolean get() = (this and 0x04) != 0L

/**
 * Get bit with index 3 of a long
 */
val Long.bit3: Boolean get() = (this and 0x08) != 0L

/**
 * Get bit with index 4 of a long
 */
val Long.bit4: Boolean get() = (this and 0x10) != 0L

/**
 * Get bit with index 5 of a long
 */
val Long.bit5: Boolean get() = (this and 0x20) != 0L

/**
 * Get bit with index 6 of a long
 */
val Long.bit6: Boolean get() = (this and 0x40) != 0L

/**
 * Get bit with index 7 of a long
 */
val Long.bit7: Boolean get() = (this and 0x80) != 0L

/**
 * Get bit with index 8 of a long
 */
val Long.bit8: Boolean get() = (this and 0x100) != 0L

/**
 * Get bit with index 9 of a long
 */
val Long.bit9: Boolean get() = (this and 0x200) != 0L

/**
 * Get bit with index 10 of a long
 */
val Long.bit10: Boolean get() = (this and 0x400) != 0L

/**
 * Get bit with index 11 of a long
 */
val Long.bit11: Boolean get() = (this and 0x800) != 0L

/**
 * Get bit with index 12 of a long
 */
val Long.bit12: Boolean get() = (this and 0x1000) != 0L

/**
 * Get bit with index 13 of a long
 */
val Long.bit13: Boolean get() = (this and 0x2000) != 0L

/**
 * Get bit with index 14 of a long
 */
val Long.bit14: Boolean get() = (this and 0x4000) != 0L

/**
 * Get bit with index 15 of a long
 */
val Long.bit15: Boolean get() = (this and 0x8000) != 0L

/**
 * Get bit with index 16 of a long
 */
val Long.bit16: Boolean get() = (this and 0x10000) != 0L

/**
 * Get bit with index 17 of a long
 */
val Long.bit17: Boolean get() = (this and 0x20000) != 0L

/**
 * Get bit with index 18 of a long
 */
val Long.bit18: Boolean get() = (this and 0x40000) != 0L

/**
 * Get bit with index 19 of a long
 */
val Long.bit19: Boolean get() = (this and 0x80000) != 0L

/**
 * Get bit with index 20 of a long
 */
val Long.bit20: Boolean get() = (this and 0x100000) != 0L

/**
 * Get bit with index 21 of a long
 */
val Long.bit21: Boolean get() = (this and 0x200000) != 0L

/**
 * Get bit with index 22 of a long
 */
val Long.bit22: Boolean get() = (this and 0x400000) != 0L

/**
 * Get bit with index 23 of a long
 */
val Long.bit23: Boolean get() = (this and 0x800000) != 0L

/**
 * Get bit with index 24 of a long
 */
val Long.bit24: Boolean get() = (this and 0x1000000) != 0L

/**
 * Get bit with index 25 of a long
 */
val Long.bit25: Boolean get() = (this and 0x2000000) != 0L

/**
 * Get bit with index 26 of a long
 */
val Long.bit26: Boolean get() = (this and 0x4000000) != 0L

/**
 * Get bit with index 27 of a long
 */
val Long.bit27: Boolean get() = (this and 0x8000000) != 0L

/**
 * Get bit with index 28 of a long
 */
val Long.bit28: Boolean get() = (this and 0x10000000) != 0L

/**
 * Get bit with index 29 of a long
 */
val Long.bit29: Boolean get() = (this and 0x20000000) != 0L

/**
 * Get bit with index 30 of a long
 */
val Long.bit30: Boolean get() = (this and 0x40000000) != 0L

/**
 * Get bit with index 31 of a long
 */
val Long.bit31: Boolean get() = (this and 0x80000000L) != 0L

/**
 * Get bit with index 32 of a long
 */
val Long.bit32: Boolean get() = (this and 0x100000000L) != 0L

/**
 * Get bit with index 33 of a long
 */
val Long.bit33: Boolean get() = (this and 0x200000000L) != 0L

/**
 * Get bit with index 34 of a long
 */
val Long.bit34: Boolean get() = (this and 0x400000000L) != 0L

/**
 * Get bit with index 35 of a long
 */
val Long.bit35: Boolean get() = (this and 0x800000000L) != 0L

/**
 * Get bit with index 36 of a long
 */
val Long.bit36: Boolean get() = (this and 0x1000000000L) != 0L

/**
 * Get bit with index 37 of a long
 */
val Long.bit37: Boolean get() = (this and 0x2000000000L) != 0L

/**
 * Get bit with index 38 of a long
 */
val Long.bit38: Boolean get() = (this and 0x4000000000L) != 0L

/**
 * Get bit with index 39 of a long
 */
val Long.bit39: Boolean get() = (this and 0x8000000000L) != 0L

/**
 * Get bit with index 40 of a long
 */
val Long.bit40: Boolean get() = (this and 0x10000000000L) != 0L

/**
 * Get bit with index 41 of a long
 */
val Long.bit41: Boolean get() = (this and 0x20000000000L) != 0L

/**
 * Get bit with index 42 of a long
 */
val Long.bit42: Boolean get() = (this and 0x40000000000L) != 0L

/**
 * Get bit with index 43 of a long
 */
val Long.bit43: Boolean get() = (this and 0x80000000000L) != 0L

/**
 * Get bit with index 44 of a long
 */
val Long.bit44: Boolean get() = (this and 0x100000000000L) != 0L

/**
 * Get bit with index 45 of a long
 */
val Long.bit45: Boolean get() = (this and 0x200000000000L) != 0L

/**
 * Get bit with index 46 of a long
 */
val Long.bit46: Boolean get() = (this and 0x400000000000L) != 0L

/**
 * Get bit with index 47 of a long
 */
val Long.bit47: Boolean get() = (this and 0x800000000000L) != 0L

/**
 * Get bit with index 48 of a long
 */
val Long.bit48: Boolean get() = (this and 0x1000000000000L) != 0L

/**
 * Get bit with index 49 of a long
 */
val Long.bit49: Boolean get() = (this and 0x2000000000000L) != 0L

/**
 * Get bit with index 50 of a long
 */
val Long.bit50: Boolean get() = (this and 0x4000000000000L) != 0L

/**
 * Get bit with index 51 of a long
 */
val Long.bit51: Boolean get() = (this and 0x8000000000000L) != 0L

/**
 * Get bit with index 52 of a long
 */
val Long.bit52: Boolean get() = (this and 0x10000000000000L) != 0L

/**
 * Get bit with index 53 of a long
 */
val Long.bit53: Boolean get() = (this and 0x20000000000000L) != 0L

/**
 * Get bit with index 54 of a long
 */
val Long.bit54: Boolean get() = (this and 0x40000000000000L) != 0L

/**
 * Get bit with index 55 of a long
 */
val Long.bit55: Boolean get() = (this and 0x80000000000000L) != 0L

/**
 * Get bit with index 56 of a long
 */
val Long.bit56: Boolean get() = (this and 0x100000000000000L) != 0L

/**
 * Get bit with index 57 of a long
 */
val Long.bit57: Boolean get() = (this and 0x200000000000000L) != 0L

/**
 * Get bit with index 58 of a long
 */
val Long.bit58: Boolean get() = (this and 0x400000000000000L) != 0L

/**
 * Get bit with index 59 of a long
 */
val Long.bit59: Boolean get() = (this and 0x800000000000000L) != 0L

/**
 * Get bit with index 60 of a long
 */
val Long.bit60: Boolean get() = (this and 0x1000000000000000L) != 0L

/**
 * Get bit with index 61 of a long
 */
val Long.bit61: Boolean get() = (this and 0x2000000000000000L) != 0L

/**
 * Get bit with index 62 of a long
 */
val Long.bit62: Boolean get() = (this and 0x4000000000000000L) != 0L

/**
 * Get bit with index 63 of a long
 */
val Long.bit63: Boolean get() = (this and 0x8000000000000000uL.toLong()) != 0L

/**
 * Get n-th bit of a long
 * @param n index of the bit to get
 * @return true if the bit is set, false otherwise
 */
fun Long.bit(n: Int): Boolean = (this and (1L shl n)) != 0L

/**
 * Get bit with index 0 of a float
 */
val Float.bit0: Boolean get() = this.toRawBits().bit0

/**
 * Get bit with index 1 of a float
 */
val Float.bit1: Boolean get() = this.toRawBits().bit1

/**
 * Get bit with index 2 of a float
 */
val Float.bit2: Boolean get() = this.toRawBits().bit2

/**
 * Get bit with index 3 of a float
 */
val Float.bit3: Boolean get() = this.toRawBits().bit3

/**
 * Get bit with index 4 of a float
 */
val Float.bit4: Boolean get() = this.toRawBits().bit4

/**
 * Get bit with index 5 of a float
 */
val Float.bit5: Boolean get() = this.toRawBits().bit5

/**
 * Get bit with index 6 of a float
 */
val Float.bit6: Boolean get() = this.toRawBits().bit6

/**
 * Get bit with index 7 of a float
 */
val Float.bit7: Boolean get() = this.toRawBits().bit7

/**
 * Get bit with index 8 of a float
 */
val Float.bit8: Boolean get() = this.toRawBits().bit8

/**
 * Get bit with index 9 of a float
 */
val Float.bit9: Boolean get() = this.toRawBits().bit9

/**
 * Get bit with index 10 of a float
 */
val Float.bit10: Boolean get() = this.toRawBits().bit10

/**
 * Get bit with index 11 of a float
 */
val Float.bit11: Boolean get() = this.toRawBits().bit11

/**
 * Get bit with index 12 of a float
 */
val Float.bit12: Boolean get() = this.toRawBits().bit12

/**
 * Get bit with index 13 of a float
 */
val Float.bit13: Boolean get() = this.toRawBits().bit13

/**
 * Get bit with index 14 of a float
 */
val Float.bit14: Boolean get() = this.toRawBits().bit14

/**
 * Get bit with index 15 of a float
 */
val Float.bit15: Boolean get() = this.toRawBits().bit15

/**
 * Get bit with index 16 of a float
 */
val Float.bit16: Boolean get() = this.toRawBits().bit16

/**
 * Get bit with index 17 of a float
 */
val Float.bit17: Boolean get() = this.toRawBits().bit17

/**
 * Get bit with index 18 of a float
 */
val Float.bit18: Boolean get() = this.toRawBits().bit18

/**
 * Get bit with index 19 of a float
 */
val Float.bit19: Boolean get() = this.toRawBits().bit19

/**
 * Get bit with index 20 of a float
 */
val Float.bit20: Boolean get() = this.toRawBits().bit20

/**
 * Get bit with index 21 of a float
 */
val Float.bit21: Boolean get() = this.toRawBits().bit21

/**
 * Get bit with index 22 of a float
 */
val Float.bit22: Boolean get() = this.toRawBits().bit22

/**
 * Get bit with index 23 of a float
 */
val Float.bit23: Boolean get() = this.toRawBits().bit23

/**
 * Get bit with index 24 of a float
 */
val Float.bit24: Boolean get() = this.toRawBits().bit24

/**
 * Get bit with index 25 of a float
 */
val Float.bit25: Boolean get() = this.toRawBits().bit25

/**
 * Get bit with index 26 of a float
 */
val Float.bit26: Boolean get() = this.toRawBits().bit26

/**
 * Get bit with index 27 of a float
 */
val Float.bit27: Boolean get() = this.toRawBits().bit27

/**
 * Get bit with index 28 of a float
 */
val Float.bit28: Boolean get() = this.toRawBits().bit28

/**
 * Get bit with index 29 of a float
 */
val Float.bit29: Boolean get() = this.toRawBits().bit29

/**
 * Get bit with index 30 of a float
 */
val Float.bit30: Boolean get() = this.toRawBits().bit30

/**
 * Get bit with index 31 of a float
 */
val Float.bit31: Boolean get() = this.toRawBits().bit31

/**
 * Get n-th bit of a float
 * @param n index of the bit to get
 * @return true if the bit is set, false otherwise
 */
fun Float.bit(n: Int): Boolean = this.toRawBits().bit(n)

/**
 * Get bit with index 0 of a double
 */
val Double.bit0: Boolean get() = this.toRawBits().bit0

/**
 * Get bit with index 1 of a double
 */
val Double.bit1: Boolean get() = this.toRawBits().bit1

/**
 * Get bit with index 2 of a double
 */
val Double.bit2: Boolean get() = this.toRawBits().bit2

/**
 * Get bit with index 3 of a double
 */
val Double.bit3: Boolean get() = this.toRawBits().bit3

/**
 * Get bit with index 4 of a double
 */
val Double.bit4: Boolean get() = this.toRawBits().bit4

/**
 * Get bit with index 5 of a double
 */
val Double.bit5: Boolean get() = this.toRawBits().bit5

/**
 * Get bit with index 6 of a double
 */
val Double.bit6: Boolean get() = this.toRawBits().bit6

/**
 * Get bit with index 7 of a double
 */
val Double.bit7: Boolean get() = this.toRawBits().bit7

/**
 * Get bit with index 8 of a double
 */
val Double.bit8: Boolean get() = this.toRawBits().bit8

/**
 * Get bit with index 9 of a double
 */
val Double.bit9: Boolean get() = this.toRawBits().bit9

/**
 * Get bit with index 10 of a double
 */
val Double.bit10: Boolean get() = this.toRawBits().bit10

/**
 * Get bit with index 11 of a double
 */
val Double.bit11: Boolean get() = this.toRawBits().bit11

/**
 * Get bit with index 12 of a double
 */
val Double.bit12: Boolean get() = this.toRawBits().bit12

/**
 * Get bit with index 13 of a double
 */
val Double.bit13: Boolean get() = this.toRawBits().bit13

/**
 * Get bit with index 14 of a double
 */
val Double.bit14: Boolean get() = this.toRawBits().bit14

/**
 * Get bit with index 15 of a double
 */
val Double.bit15: Boolean get() = this.toRawBits().bit15

/**
 * Get bit with index 16 of a double
 */
val Double.bit16: Boolean get() = this.toRawBits().bit16

/**
 * Get bit with index 17 of a double
 */
val Double.bit17: Boolean get() = this.toRawBits().bit17

/**
 * Get bit with index 18 of a double
 */
val Double.bit18: Boolean get() = this.toRawBits().bit18

/**
 * Get bit with index 19 of a double
 */
val Double.bit19: Boolean get() = this.toRawBits().bit19

/**
 * Get bit with index 20 of a double
 */
val Double.bit20: Boolean get() = this.toRawBits().bit20

/**
 * Get bit with index 21 of a double
 */
val Double.bit21: Boolean get() = this.toRawBits().bit21

/**
 * Get bit with index 22 of a double
 */
val Double.bit22: Boolean get() = this.toRawBits().bit22

/**
 * Get bit with index 23 of a double
 */
val Double.bit23: Boolean get() = this.toRawBits().bit23

/**
 * Get bit with index 24 of a double
 */
val Double.bit24: Boolean get() = this.toRawBits().bit24

/**
 * Get bit with index 25 of a double
 */
val Double.bit25: Boolean get() = this.toRawBits().bit25

/**
 * Get bit with index 26 of a double
 */
val Double.bit26: Boolean get() = this.toRawBits().bit26

/**
 * Get bit with index 27 of a double
 */
val Double.bit27: Boolean get() = this.toRawBits().bit27

/**
 * Get bit with index 28 of a double
 */
val Double.bit28: Boolean get() = this.toRawBits().bit28

/**
 * Get bit with index 29 of a double
 */

val Double.bit29: Boolean get() = this.toRawBits().bit29

/**
 * Get bit with index 30 of a double
 */
val Double.bit30: Boolean get() = this.toRawBits().bit30

/**
 * Get bit with index 31 of a double
 */
val Double.bit31: Boolean get() = this.toRawBits().bit31

/**
 * Get bit with index 32 of a double
 */
val Double.bit32: Boolean get() = this.toRawBits().bit32

/**
 * Get bit with index 33 of a double
 */
val Double.bit33: Boolean get() = this.toRawBits().bit33

/**
 * Get bit with index 34 of a double
 */
val Double.bit34: Boolean get() = this.toRawBits().bit34

/**
 * Get bit with index 35 of a double
 */
val Double.bit35: Boolean get() = this.toRawBits().bit35

/**
 * Get bit with index 36 of a double
 */
val Double.bit36: Boolean get() = this.toRawBits().bit36

/**
 * Get bit with index 37 of a double
 */
val Double.bit37: Boolean get() = this.toRawBits().bit37

/**
 * Get bit with index 38 of a double
 */
val Double.bit38: Boolean get() = this.toRawBits().bit38

/**
 * Get bit with index 39 of a double
 */
val Double.bit39: Boolean get() = this.toRawBits().bit39

/**
 * Get bit with index 40 of a double
 */
val Double.bit40: Boolean get() = this.toRawBits().bit40

/**
 * Get bit with index 41 of a double
 */
val Double.bit41: Boolean get() = this.toRawBits().bit41

/**
 * Get bit with index 42 of a double
 */
val Double.bit42: Boolean get() = this.toRawBits().bit42

/**
 * Get bit with index 43 of a double
 */
val Double.bit43: Boolean get() = this.toRawBits().bit43

/**
 * Get bit with index 44 of a double
 */
val Double.bit44: Boolean get() = this.toRawBits().bit44

/**
 * Get bit with index 45 of a double
 */
val Double.bit45: Boolean get() = this.toRawBits().bit45

/**
 * Get bit with index 46 of a double
 */
val Double.bit46: Boolean get() = this.toRawBits().bit46

/**
 * Get bit with index 47 of a double
 */
val Double.bit47: Boolean get() = this.toRawBits().bit47

/**
 * Get bit with index 48 of a double
 */
val Double.bit48: Boolean get() = this.toRawBits().bit48

/**
 * Get bit with index 49 of a double
 */
val Double.bit49: Boolean get() = this.toRawBits().bit49

/**
 * Get bit with index 50 of a double
 */
val Double.bit50: Boolean get() = this.toRawBits().bit50

/**
 * Get bit with index 51 of a double
 */
val Double.bit51: Boolean get() = this.toRawBits().bit51

/**
 * Get bit with index 52 of a double
 */
val Double.bit52: Boolean get() = this.toRawBits().bit52

/**
 * Get bit with index 53 of a double
 */
val Double.bit53: Boolean get() = this.toRawBits().bit53

/**
 * Get bit with index 54 of a double
 */
val Double.bit54: Boolean get() = this.toRawBits().bit54

/**
 * Get bit with index 55 of a double
 */
val Double.bit55: Boolean get() = this.toRawBits().bit55

/**
 * Get bit with index 56 of a double
 */
val Double.bit56: Boolean get() = this.toRawBits().bit56

/**
 * Get bit with index 57 of a double
 */
val Double.bit57: Boolean get() = this.toRawBits().bit57

/**
 * Get bit with index 58 of a double
 */
val Double.bit58: Boolean get() = this.toRawBits().bit58

/**
 * Get bit with index 59 of a double
 */
val Double.bit59: Boolean get() = this.toRawBits().bit59

/**
 * Get bit with index 60 of a double
 */
val Double.bit60: Boolean get() = this.toRawBits().bit60

/**
 * Get bit with index 61 of a double
 */
val Double.bit61: Boolean get() = this.toRawBits().bit61

/**
 * Get bit with index 62 of a double
 */
val Double.bit62: Boolean get() = this.toRawBits().bit62

/**
 * Get bit with index 63 of a double
 */
val Double.bit63: Boolean get() = this.toRawBits().bit63

/**
 * Get n-th bit of a double
 * @param n index of the bit to get
 * @return true if the bit is set, false otherwise
 */
fun Double.bit(n: Int): Boolean = this.toRawBits().bit(n)

/**
 * Get bit with index 0 of an unsigned byte
 */
val UByte.bit0: Boolean get() = this.toByte().bit0

/**
 * Get bit with index 1 of an unsigned byte
 */
val UByte.bit1: Boolean get() = this.toByte().bit1

/**
 * Get bit with index 2 of an unsigned byte
 */
val UByte.bit2: Boolean get() = this.toByte().bit2

/**
 * Get bit with index 3 of an unsigned byte
 */
val UByte.bit3: Boolean get() = this.toByte().bit3

/**
 * Get bit with index 4 of an unsigned byte
 */
val UByte.bit4: Boolean get() = this.toByte().bit4

/**
 * Get bit with index 5 of an unsigned byte
 */
val UByte.bit5: Boolean get() = this.toByte().bit5

/**
 * Get bit with index 6 of an unsigned byte
 */
val UByte.bit6: Boolean get() = this.toByte().bit6

/**
 * Get bit with index 7 of an unsigned byte
 */
val UByte.bit7: Boolean get() = this.toByte().bit7

/**
 * Get n-th bit of an unsigned byte
 * @param n index of the bit to get
 * @return true if the bit is set, false otherwise
 */
fun UByte.bit(n: Int): Boolean = this.toByte().bit(n)

/**
 * Get bit with index 0 of an unsigned short
 */
val UShort.bit0: Boolean get() = this.toShort().bit0

/**
 * Get bit with index 1 of an unsigned short
 */
val UShort.bit1: Boolean get() = this.toShort().bit1

/**
 * Get bit with index 2 of an unsigned short
 */
val UShort.bit2: Boolean get() = this.toShort().bit2

/**
 * Get bit with index 3 of an unsigned short
 */
val UShort.bit3: Boolean get() = this.toShort().bit3

/**
 * Get bit with index 4 of an unsigned short
 */
val UShort.bit4: Boolean get() = this.toShort().bit4

/**
 * Get bit with index 5 of an unsigned short
 */
val UShort.bit5: Boolean get() = this.toShort().bit5

/**
 * Get bit with index 6 of an unsigned short
 */
val UShort.bit6: Boolean get() = this.toShort().bit6

/**
 * Get bit with index 7 of an unsigned short
 */
val UShort.bit7: Boolean get() = this.toShort().bit7

/**
 * Get bit with index 8 of an unsigned short
 */
val UShort.bit8: Boolean get() = this.toShort().bit8

/**
 * Get bit with index 9 of an unsigned short
 */
val UShort.bit9: Boolean get() = this.toShort().bit9

/**
 * Get bit with index 10 of an unsigned short
 */
val UShort.bit10: Boolean get() = this.toShort().bit10

/**
 * Get bit with index 11 of an unsigned short
 */
val UShort.bit11: Boolean get() = this.toShort().bit11

/**
 * Get bit with index 12 of an unsigned short
 */
val UShort.bit12: Boolean get() = this.toShort().bit12

/**
 * Get bit with index 13 of an unsigned short
 */
val UShort.bit13: Boolean get() = this.toShort().bit13

/**
 * Get bit with index 14 of an unsigned short
 */
val UShort.bit14: Boolean get() = this.toShort().bit14

/**
 * Get bit with index 15 of an unsigned short
 */
val UShort.bit15: Boolean get() = this.toShort().bit15

/**
 * Get n-th bit of an unsigned short
 * @param n index of the bit to get
 * @return true if the bit is set, false otherwise
 */
fun UShort.bit(n: Int): Boolean = this.toShort().bit(n)

/**
 * Get bit with index 0 of an unsigned int
 */
val UInt.bit0: Boolean get() = this.toInt().bit0

/**
 * Get bit with index 1 of an unsigned int
 */
val UInt.bit1: Boolean get() = this.toInt().bit1

/**
 * Get bit with index 2 of an unsigned int
 */
val UInt.bit2: Boolean get() = this.toInt().bit2

/**
 * Get bit with index 3 of an unsigned int
 */
val UInt.bit3: Boolean get() = this.toInt().bit3

/**
 * Get bit with index 4 of an unsigned int
 */
val UInt.bit4: Boolean get() = this.toInt().bit4

/**
 * Get bit with index 5 of an unsigned int
 */
val UInt.bit5: Boolean get() = this.toInt().bit5

/**
 * Get bit with index 6 of an unsigned int
 */
val UInt.bit6: Boolean get() = this.toInt().bit6

/**
 * Get bit with index 7 of an unsigned int
 */
val UInt.bit7: Boolean get() = this.toInt().bit7

/**
 * Get bit with index 8 of an unsigned int
 */
val UInt.bit8: Boolean get() = this.toInt().bit8

/**
 * Get bit with index 9 of an unsigned int
 */
val UInt.bit9: Boolean get() = this.toInt().bit9

/**
 * Get bit with index 10 of an unsigned int
 */
val UInt.bit10: Boolean get() = this.toInt().bit10

/**
 * Get bit with index 11 of an unsigned int
 */
val UInt.bit11: Boolean get() = this.toInt().bit11

/**
 * Get bit with index 12 of an unsigned int
 */
val UInt.bit12: Boolean get() = this.toInt().bit12

/**
 * Get bit with index 13 of an unsigned int
 */
val UInt.bit13: Boolean get() = this.toInt().bit13

/**
 * Get bit with index 14 of an unsigned int
 */
val UInt.bit14: Boolean get() = this.toInt().bit14

/**
 * Get bit with index 15 of an unsigned int
 */
val UInt.bit15: Boolean get() = this.toInt().bit15

/**
 * Get bit with index 16 of an unsigned int
 */
val UInt.bit16: Boolean get() = this.toInt().bit16

/**
 * Get bit with index 17 of an unsigned int
 */
val UInt.bit17: Boolean get() = this.toInt().bit17

/**
 * Get bit with index 18 of an unsigned int
 */
val UInt.bit18: Boolean get() = this.toInt().bit18

/**
 * Get bit with index 19 of an unsigned int
 */
val UInt.bit19: Boolean get() = this.toInt().bit19

/**
 * Get bit with index 20 of an unsigned int
 */
val UInt.bit20: Boolean get() = this.toInt().bit20

/**
 * Get bit with index 21 of an unsigned int
 */
val UInt.bit21: Boolean get() = this.toInt().bit21

/**
 * Get bit with index 22 of an unsigned int
 */
val UInt.bit22: Boolean get() = this.toInt().bit22

/**
 * Get bit with index 23 of an unsigned int
 */
val UInt.bit23: Boolean get() = this.toInt().bit23

/**
 * Get bit with index 24 of an unsigned int
 */
val UInt.bit24: Boolean get() = this.toInt().bit24

/**
 * Get bit with index 25 of an unsigned int
 */
val UInt.bit25: Boolean get() = this.toInt().bit25

/**
 * Get bit with index 26 of an unsigned int
 */
val UInt.bit26: Boolean get() = this.toInt().bit26

/**
 * Get bit with index 27 of an unsigned int
 */
val UInt.bit27: Boolean get() = this.toInt().bit27

/**
 * Get bit with index 28 of an unsigned int
 */
val UInt.bit28: Boolean get() = this.toInt().bit28

/**
 * Get bit with index 29 of an unsigned int
 */
val UInt.bit29: Boolean get() = this.toInt().bit29

/**
 * Get bit with index 30 of an unsigned int
 */
val UInt.bit30: Boolean get() = this.toInt().bit30

/**
 * Get bit with index 31 of an unsigned int
 */
val UInt.bit31: Boolean get() = this.toInt().bit31

/**
 * Get n-th bit of an unsigned int
 * @param n index of the bit to get
 * @return true if the bit is set, false otherwise
 */
fun UInt.bit(n: Int): Boolean = this.toInt().bit(n)

/**
 * Get bit with index 0 of an unsigned long
 */
val ULong.bit0: Boolean get() = this.toLong().bit0

/**
 * Get bit with index 1 of an unsigned long
 */
val ULong.bit1: Boolean get() = this.toLong().bit1

/**
 * Get bit with index 2 of an unsigned long
 */
val ULong.bit2: Boolean get() = this.toLong().bit2

/**
 * Get bit with index 3 of an unsigned long
 */
val ULong.bit3: Boolean get() = this.toLong().bit3

/**
 * Get bit with index 4 of an unsigned long
 */
val ULong.bit4: Boolean get() = this.toLong().bit4

/**
 * Get bit with index 5 of an unsigned long
 */
val ULong.bit5: Boolean get() = this.toLong().bit5

/**
 * Get bit with index 6 of an unsigned long
 */
val ULong.bit6: Boolean get() = this.toLong().bit6

/**
 * Get bit with index 7 of an unsigned long
 */
val ULong.bit7: Boolean get() = this.toLong().bit7

/**
 * Get bit with index 8 of an unsigned long
 */
val ULong.bit8: Boolean get() = this.toLong().bit8

/**
 * Get bit with index 9 of an unsigned long
 */
val ULong.bit9: Boolean get() = this.toLong().bit9

/**
 * Get bit with index 10 of an unsigned long
 */
val ULong.bit10: Boolean get() = this.toLong().bit10

/**
 * Get bit with index 11 of an unsigned long
 */
val ULong.bit11: Boolean get() = this.toLong().bit11

/**
 * Get bit with index 12 of an unsigned long
 */
val ULong.bit12: Boolean get() = this.toLong().bit12

/**
 * Get bit with index 13 of an unsigned long
 */
val ULong.bit13: Boolean get() = this.toLong().bit13

/**
 * Get bit with index 14 of an unsigned long
 */
val ULong.bit14: Boolean get() = this.toLong().bit14

/**
 * Get bit with index 15 of an unsigned long
 */
val ULong.bit15: Boolean get() = this.toLong().bit15

/**
 * Get bit with index 16 of an unsigned long
 */
val ULong.bit16: Boolean get() = this.toLong().bit16

/**
 * Get bit with index 17 of an unsigned long
 */
val ULong.bit17: Boolean get() = this.toLong().bit17

/**
 * Get bit with index 18 of an unsigned long
 */
val ULong.bit18: Boolean get() = this.toLong().bit18

/**
 * Get bit with index 19 of an unsigned long
 */
val ULong.bit19: Boolean get() = this.toLong().bit19

/**
 * Get bit with index 20 of an unsigned long
 */
val ULong.bit20: Boolean get() = this.toLong().bit20

/**
 * Get bit with index 21 of an unsigned long
 */
val ULong.bit21: Boolean get() = this.toLong().bit21

/**
 * Get bit with index 22 of an unsigned long
 */
val ULong.bit22: Boolean get() = this.toLong().bit22

/**
 * Get bit with index 23 of an unsigned long
 */
val ULong.bit23: Boolean get() = this.toLong().bit23

/**
 * Get bit with index 24 of an unsigned long
 */
val ULong.bit24: Boolean get() = this.toLong().bit24

/**
 * Get bit with index 25 of an unsigned long
 */
val ULong.bit25: Boolean get() = this.toLong().bit25

/**
 * Get bit with index 26 of an unsigned long
 */
val ULong.bit26: Boolean get() = this.toLong().bit26

/**
 * Get bit with index 27 of an unsigned long
 */
val ULong.bit27: Boolean get() = this.toLong().bit27

/**
 * Get bit with index 28 of an unsigned long
 */
val ULong.bit28: Boolean get() = this.toLong().bit28

/**
 * Get bit with index 29 of an unsigned long
 */
val ULong.bit29: Boolean get() = this.toLong().bit29

/**
 * Get bit with index 30 of an unsigned long
 */
val ULong.bit30: Boolean get() = this.toLong().bit30

/**
 * Get bit with index 31 of an unsigned long
 */
val ULong.bit31: Boolean get() = this.toLong().bit31

/**
 * Get bit with index 32 of an unsigned long
 */
val ULong.bit32: Boolean get() = this.toLong().bit32

/**
 * Get bit with index 33 of an unsigned long
 */
val ULong.bit33: Boolean get() = this.toLong().bit33

/**
 * Get bit with index 34 of an unsigned long
 */
val ULong.bit34: Boolean get() = this.toLong().bit34

/**
 * Get bit with index 35 of an unsigned long
 */
val ULong.bit35: Boolean get() = this.toLong().bit35

/**
 * Get bit with index 36 of an unsigned long
 */
val ULong.bit36: Boolean get() = this.toLong().bit36

/**
 * Get bit with index 37 of an unsigned long
 */
val ULong.bit37: Boolean get() = this.toLong().bit37

/**
 * Get bit with index 38 of an unsigned long
 */
val ULong.bit38: Boolean get() = this.toLong().bit38

/**
 * Get bit with index 39 of an unsigned long
 */
val ULong.bit39: Boolean get() = this.toLong().bit39

/**
 * Get bit with index 40 of an unsigned long
 */
val ULong.bit40: Boolean get() = this.toLong().bit40

/**
 * Get bit with index 41 of an unsigned long
 */
val ULong.bit41: Boolean get() = this.toLong().bit41

/**
 * Get bit with index 42 of an unsigned long
 */
val ULong.bit42: Boolean get() = this.toLong().bit42

/**
 * Get bit with index 43 of an unsigned long
 */
val ULong.bit43: Boolean get() = this.toLong().bit43

/**
 * Get bit with index 44 of an unsigned long
 */
val ULong.bit44: Boolean get() = this.toLong().bit44

/**
 * Get bit with index 45 of an unsigned long
 */
val ULong.bit45: Boolean get() = this.toLong().bit45

/**
 * Get bit with index 46 of an unsigned long
 */
val ULong.bit46: Boolean get() = this.toLong().bit46

/**
 * Get bit with index 47 of an unsigned long
 */
val ULong.bit47: Boolean get() = this.toLong().bit47

/**
 * Get bit with index 48 of an unsigned long
 */
val ULong.bit48: Boolean get() = this.toLong().bit48

/**
 * Get bit with index 49 of an unsigned long
 */
val ULong.bit49: Boolean get() = this.toLong().bit49

/**
 * Get bit with index 50 of an unsigned long
 */
val ULong.bit50: Boolean get() = this.toLong().bit50

/**
 * Get bit with index 51 of an unsigned long
 */
val ULong.bit51: Boolean get() = this.toLong().bit51

/**
 * Get bit with index 52 of an unsigned long
 */
val ULong.bit52: Boolean get() = this.toLong().bit52

/**
 * Get bit with index 53 of an unsigned long
 */
val ULong.bit53: Boolean get() = this.toLong().bit53

/**
 * Get bit with index 54 of an unsigned long
 */
val ULong.bit54: Boolean get() = this.toLong().bit54

/**
 * Get bit with index 55 of an unsigned long
 */
val ULong.bit55: Boolean get() = this.toLong().bit55

/**
 * Get bit with index 56 of an unsigned long
 */
val ULong.bit56: Boolean get() = this.toLong().bit56

/**
 * Get bit with index 57 of an unsigned long
 */
val ULong.bit57: Boolean get() = this.toLong().bit57

/**
 * Get bit with index 58 of an unsigned long
 */
val ULong.bit58: Boolean get() = this.toLong().bit58

/**
 * Get bit with index 59 of an unsigned long
 */
val ULong.bit59: Boolean get() = this.toLong().bit59

/**
 * Get bit with index 60 of an unsigned long
 */
val ULong.bit60: Boolean get() = this.toLong().bit60

/**
 * Get bit with index 61 of an unsigned long
 */
val ULong.bit61: Boolean get() = this.toLong().bit61

/**
 * Get bit with index 62 of an unsigned long
 */
val ULong.bit62: Boolean get() = this.toLong().bit62

/**
 * Get bit with index 63 of an unsigned long
 */
val ULong.bit63: Boolean get() = this.toLong().bit63

/**
 * Get n-th bit of an unsigned long
 * @param n The index of the bit to get
 * @return The value of the bit
 */
fun ULong.bit(n: Int): Boolean = this.toLong().bit(n)

/**
 * Get bit with index 0 of a char
 */
val Char.bit0: Boolean get() = this.code.bit0

/**
 * Get bit with index 1 of a char
 */
val Char.bit1: Boolean get() = this.code.bit1

/**
 * Get bit with index 2 of a char
 */
val Char.bit2: Boolean get() = this.code.bit2

/**
 * Get bit with index 3 of a char
 */
val Char.bit3: Boolean get() = this.code.bit3

/**
 * Get bit with index 4 of a char
 */
val Char.bit4: Boolean get() = this.code.bit4

/**
 * Get bit with index 5 of a char
 */
val Char.bit5: Boolean get() = this.code.bit5

/**
 * Get bit with index 6 of a char
 */
val Char.bit6: Boolean get() = this.code.bit6

/**
 * Get bit with index 7 of a char
 */
val Char.bit7: Boolean get() = this.code.bit7

/**
 * Get bit with index 8 of a char
 */
val Char.bit8: Boolean get() = this.code.bit8

/**
 * Get bit with index 9 of a char
 */
val Char.bit9: Boolean get() = this.code.bit9

/**
 * Get bit with index 10 of a char
 */
val Char.bit10: Boolean get() = this.code.bit10

/**
 * Get bit with index 11 of a char
 */
val Char.bit11: Boolean get() = this.code.bit11

/**
 * Get bit with index 12 of a char
 */
val Char.bit12: Boolean get() = this.code.bit12

/**
 * Get bit with index 13 of a char
 */
val Char.bit13: Boolean get() = this.code.bit13

/**
 * Get bit with index 14 of a char
 */
val Char.bit14: Boolean get() = this.code.bit14

/**
 * Get bit with index 15 of a char
 */
val Char.bit15: Boolean get() = this.code.bit15

/**
 * Get n-th bit of a char
 * @param n The index of the bit to get
 * @return The value of the bit
 */
fun Char.bit(n: Int): Boolean = this.code.bit(n)

/**
 * Change the bit with index 0 of a byte
 * @param value The value to set the bit to
 * @return The byte with the bit changed
 */
fun Byte.withBit0(value: Boolean): Byte = if (value) (this or 0x01) else (this and 0xFE.toByte())

/**
 * Change the bit with index 1 of a byte
 * @param value The value to set the bit to
 * @return The byte with the bit changed
 */
fun Byte.withBit1(value: Boolean): Byte = if (value) (this or 0x02) else (this and 0xFD.toByte())

/**
 * Change the bit with index 2 of a byte
 * @param value The value to set the bit to
 * @return The byte with the bit changed
 */
fun Byte.withBit2(value: Boolean): Byte = if (value) (this or 0x04) else (this and 0xFB.toByte())

/**
 * Change the bit with index 3 of a byte
 * @param value The value to set the bit to
 * @return The byte with the bit changed
 */
fun Byte.withBit3(value: Boolean): Byte = if (value) (this or 0x08) else (this and 0xF7.toByte())

/**
 * Change the bit with index 4 of a byte
 * @param value The value to set the bit to
 * @return The byte with the bit changed
 */
fun Byte.withBit4(value: Boolean): Byte = if (value) (this or 0x10) else (this and 0xEF.toByte())

/**
 * Change the bit with index 5 of a byte
 * @param value The value to set the bit to
 * @return The byte with the bit changed
 */
fun Byte.withBit5(value: Boolean): Byte = if (value) (this or 0x20) else (this and 0xDF.toByte())

/**
 * Change the bit with index 6 of a byte
 * @param value The value to set the bit to
 * @return The byte with the bit changed
 */
fun Byte.withBit6(value: Boolean): Byte = if (value) (this or 0x40) else (this and 0xBF.toByte())

/**
 * Change the bit with index 7 of a byte
 * @param value The value to set the bit to
 * @return The byte with the bit changed
 */
fun Byte.withBit7(value: Boolean): Byte = if (value) (this or -0x80) else (this and 0x7F)

/**
 * Change the n-th bit of a byte
 * @param n The index of the bit to change
 * @param value The value to set the bit to
 * @return The byte with the bit changed
 */
fun Byte.withBit(n: Int, value: Boolean): Byte = if (value) (this or (1 shl n).toByte()) else (this and (1 shl n).inv().toByte())

/**
 * Change the bit with index 0 of a short
 * @param value The value to set the bit to
 * @return The short with the bit changed
 */
fun Short.withBit0(value: Boolean): Short = if (value) (this or 0x0001) else (this and 0xFFFEu.toShort())

/**
 * Change the bit with index 1 of a short
 * @param value The value to set the bit to
 * @return The short with the bit changed
 */
fun Short.withBit1(value: Boolean): Short = if (value) (this or 0x0002) else (this and 0xFFFDu.toShort())

/**
 * Change the bit with index 2 of a short
 * @param value The value to set the bit to
 * @return The short with the bit changed
 */
fun Short.withBit2(value: Boolean): Short = if (value) (this or 0x0004) else (this and 0xFFFBu.toShort())

/**
 * Change the bit with index 3 of a short
 * @param value The value to set the bit to
 * @return The short with the bit changed
 */
fun Short.withBit3(value: Boolean): Short = if (value) (this or 0x0008) else (this and 0xFFF7u.toShort())

/**
 * Change the bit with index 4 of a short
 * @param value The value to set the bit to
 * @return The short with the bit changed
 */
fun Short.withBit4(value: Boolean): Short = if (value) (this or 0x0010) else (this and 0xFFEFu.toShort())

/**
 * Change the bit with index 5 of a short
 * @param value The value to set the bit to
 * @return The short with the bit changed
 */
fun Short.withBit5(value: Boolean): Short = if (value) (this or 0x0020) else (this and 0xFFDFu.toShort())

/**
 * Change the bit with index 6 of a short
 * @param value The value to set the bit to
 * @return The short with the bit changed
 */
fun Short.withBit6(value: Boolean): Short = if (value) (this or 0x0040) else (this and 0xFFBFu.toShort())

/**
 * Change the bit with index 7 of a short
 * @param value The value to set the bit to
 * @return The short with the bit changed
 */
fun Short.withBit7(value: Boolean): Short = if (value) (this or 0x0080) else (this and 0xFF7Fu.toShort())

/**
 * Change the bit with index 8 of a short
 * @param value The value to set the bit to
 * @return The short with the bit changed
 */
fun Short.withBit8(value: Boolean): Short = if (value) (this or 0x0100) else (this and 0xFEFF.toShort())

/**
 * Change the bit with index 9 of a short
 * @param value The value to set the bit to
 * @return The short with the bit changed
 */
fun Short.withBit9(value: Boolean): Short = if (value) (this or 0x200) else (this and 0xFDFF.toShort())

/**
 * Change the bit with index 10 of a short
 * @param value The value to set the bit to
 * @return The short with the bit changed
 */
fun Short.withBit10(value: Boolean): Short = if (value) (this or 0x0400) else (this and 0xFBFF.toShort())

/**
 * Change the bit with index 11 of a short
 * @param value The value to set the bit to
 * @return The short with the bit changed
 */
fun Short.withBit11(value: Boolean): Short = if (value) (this or 0x0800) else (this and 0xF7FF.toShort())

/**
 * Change the bit with index 12 of a short
 * @param value The value to set the bit to
 * @return The short with the bit changed
 */
fun Short.withBit12(value: Boolean): Short = if (value) (this or 0x1000) else (this and 0xEFFF.toShort())

/**
 * Change the bit with index 13 of a short
 * @param value The value to set the bit to
 * @return The short with the bit changed
 */
fun Short.withBit13(value: Boolean): Short = if (value) (this or 0x2000) else (this and 0xDFFF.toShort())

/**
 * Change the bit with index 14 of a short
 * @param value The value to set the bit to
 * @return The short with the bit changed
 */
fun Short.withBit14(value: Boolean): Short = if (value) (this or 0x4000) else (this and 0xBFFF.toShort())

/**
 * Change the bit with index 15 of a short
 * @param value The value to set the bit to
 * @return The short with the bit changed
 */
fun Short.withBit15(value: Boolean): Short = if (value) (this or -0x8000) else (this and 0x7FFF)

/**
 * Change the n-th bit of a short
 * @param n The index of the bit to change
 * @param value The value to set the bit to
 * @return The short with the bit changed
 */
fun Short.withBit(n: Int, value: Boolean): Short = if (value) (this or (1 shl n).toShort()) else (this and (1 shl n).inv().toShort())

/**
 * Change the bit with index 0 of an int
 * @param value The value to set the bit to
 * @return The int with the bit changed
 */
fun Int.withBit0(value: Boolean): Int = if (value) (this or 0x01) else (this and 0xFFFFFFFEu.toInt())

/**
 * Change the bit with index 1 of an int
 * @param value The value to set the bit to
 * @return The int with the bit changed
 */
fun Int.withBit1(value: Boolean): Int = if (value) (this or 0x02) else (this and 0xFFFFFFFDu.toInt())

/**
 * Change the bit with index 2 of an int
 * @param value The value to set the bit to
 * @return The int with the bit changed
 */
fun Int.withBit2(value: Boolean): Int = if (value) (this or 0x04) else (this and 0xFFFFFFFBu.toInt())

/**
 * Change the bit with index 3 of an int
 * @param value The value to set the bit to
 * @return The int with the bit changed
 */
fun Int.withBit3(value: Boolean): Int = if (value) (this or 0x08) else (this and 0xFFFFFFF7u.toInt())

/**
 * Change the bit with index 4 of an int
 * @param value The value to set the bit to
 * @return The int with the bit changed
 */
fun Int.withBit4(value: Boolean): Int = if (value) (this or 0x10) else (this and 0xFFFFFFEFu.toInt())

/**
 * Change the bit with index 5 of an int
 * @param value The value to set the bit to
 * @return The int with the bit changed
 */
fun Int.withBit5(value: Boolean): Int = if (value) (this or 0x20) else (this and 0xFFFFFFDFu.toInt())

/**
 * Change the bit with index 6 of an int
 * @param value The value to set the bit to
 * @return The int with the bit changed
 */
fun Int.withBit6(value: Boolean): Int = if (value) (this or 0x40) else (this and 0xFFFFFFBFu.toInt())

/**
 * Change the bit with index 7 of an int
 * @param value The value to set the bit to
 * @return The int with the bit changed
 */
fun Int.withBit7(value: Boolean): Int = if (value) (this or 0x80) else (this and 0xFFFFFF7Fu.toInt())

/**
 * Change the bit with index 8 of an int
 * @param value The value to set the bit to
 * @return The int with the bit changed
 */
fun Int.withBit8(value: Boolean): Int = if (value) (this or 0x100) else (this and 0xFFFFFEFFu.toInt())

/**
 * Change the bit with index 9 of an int
 * @param value The value to set the bit to
 * @return The int with the bit changed
 */
fun Int.withBit9(value: Boolean): Int = if (value) (this or 0x200) else (this and 0xFFFFFDFFu.toInt())

/**
 * Change the bit with index 10 of an int
 * @param value The value to set the bit to
 * @return The int with the bit changed
 */
fun Int.withBit10(value: Boolean): Int = if (value) (this or 0x400) else (this and 0xFFFFFBFFu.toInt())

/**
 * Change the bit with index 11 of an int
 * @param value The value to set the bit to
 * @return The int with the bit changed
 */
fun Int.withBit11(value: Boolean): Int = if (value) (this or 0x800) else (this and 0xFFFFF7FFu.toInt())

/**
 * Change the bit with index 12 of an int
 * @param value The value to set the bit to
 * @return The int with the bit changed
 */
fun Int.withBit12(value: Boolean): Int = if (value) (this or 0x1000) else (this and 0xFFFFEFFFu.toInt())

/**
 * Change the bit with index 13 of an int
 * @param value The value to set the bit to
 * @return The int with the bit changed
 */
fun Int.withBit13(value: Boolean): Int = if (value) (this or 0x2000) else (this and 0xFFFFDFFFu.toInt())

/**
 * Change the bit with index 14 of an int
 * @param value The value to set the bit to
 * @return The int with the bit changed
 */
fun Int.withBit14(value: Boolean): Int = if (value) (this or 0x4000) else (this and 0xFFFFBFFFu.toInt())

/**
 * Change the bit with index 15 of an int
 * @param value The value to set the bit to
 * @return The int with the bit changed
 */
fun Int.withBit15(value: Boolean): Int = if (value) (this or 0x8000) else (this and 0xFFFF7FFFu.toInt())

/**
 * Change the bit with index 16 of an int
 * @param value The value to set the bit to
 * @return The int with the bit changed
 */
fun Int.withBit16(value: Boolean): Int = if (value) (this or 0x10000) else (this and 0xFFFEFFFFu.toInt())

/**
 * Change the bit with index 17 of an int
 * @param value The value to set the bit to
 * @return The int with the bit changed
 */
fun Int.withBit17(value: Boolean): Int = if (value) (this or 0x20000) else (this and 0xFFFDFFFFu.toInt())

/**
 * Change the bit with index 18 of an int
 * @param value The value to set the bit to
 * @return The int with the bit changed
 */
fun Int.withBit18(value: Boolean): Int = if (value) (this or 0x40000) else (this and 0xFFFBFFFFu.toInt())

/**
 * Change the bit with index 19 of an int
 * @param value The value to set the bit to
 * @return The int with the bit changed
 */
fun Int.withBit19(value: Boolean): Int = if (value) (this or 0x80000) else (this and 0xFFF7FFFFu.toInt())

/**
 * Change the bit with index 20 of an int
 * @param value The value to set the bit to
 * @return The int with the bit changed
 */
fun Int.withBit20(value: Boolean): Int = if (value) (this or 0x100000) else (this and 0xFFEFFFFFu.toInt())

/**
 * Change the bit with index 21 of an int
 * @param value The value to set the bit to
 * @return The int with the bit changed
 */
fun Int.withBit21(value: Boolean): Int = if (value) (this or 0x200000) else (this and 0xFFDFFFFFu.toInt())

/**
 * Change the bit with index 22 of an int
 * @param value The value to set the bit to
 * @return The int with the bit changed
 */
fun Int.withBit22(value: Boolean): Int = if (value) (this or 0x400000) else (this and 0xFFBFFFFFu.toInt())

/**
 * Change the bit with index 23 of an int
 * @param value The value to set the bit to
 * @return The int with the bit changed
 */
fun Int.withBit23(value: Boolean): Int = if (value) (this or 0x800000) else (this and 0xFF7FFFFFu.toInt())

/**
 * Change the bit with index 24 of an int
 * @param value The value to set the bit to
 * @return The int with the bit changed
 */
fun Int.withBit24(value: Boolean): Int = if (value) (this or 0x1000000) else (this and 0xFEFFFFFFu.toInt())

/**
 * Change the bit with index 25 of an int
 * @param value The value to set the bit to
 * @return The int with the bit changed
 */
fun Int.withBit25(value: Boolean): Int = if (value) (this or 0x2000000) else (this and 0xFDFFFFFFu.toInt())

/**
 * Change the bit with index 26 of an int
 * @param value The value to set the bit to
 * @return The int with the bit changed
 */
fun Int.withBit26(value: Boolean): Int = if (value) (this or 0x4000000) else (this and 0xFBFFFFFFu.toInt())

/**
 * Change the bit with index 27 of an int
 * @param value The value to set the bit to
 * @return The int with the bit changed
 */
fun Int.withBit27(value: Boolean): Int = if (value) (this or 0x8000000) else (this and 0xF7FFFFFFu.toInt())

/**
 * Change the bit with index 28 of an int
 * @param value The value to set the bit to
 * @return The int with the bit changed
 */
fun Int.withBit28(value: Boolean): Int = if (value) (this or 0x10000000) else (this and 0xEFFFFFFFu.toInt())

/**
 * Change the bit with index 29 of an int
 * @param value The value to set the bit to
 * @return The int with the bit changed
 */
fun Int.withBit29(value: Boolean): Int = if (value) (this or 0x20000000) else (this and 0xDFFFFFFFu.toInt())

/**
 * Change the bit with index 30 of an int
 * @param value The value to set the bit to
 * @return The int with the bit changed
 */
fun Int.withBit30(value: Boolean): Int = if (value) (this or 0x40000000) else (this and 0xBFFFFFFFu.toInt())

/**
 * Change the bit with index 31 of an int
 * @param value The value to set the bit to
 * @return The int with the bit changed
 */
fun Int.withBit31(value: Boolean): Int = if (value) (this or -0x80000000) else (this and 0x7FFFFFFF)

/**
 * Change the n-th bit of an int
 * @param n The index of the bit to change
 * @param value The value to set the bit to
 * @return The int with the bit changed
 */
fun Int.withBit(n: Int, value: Boolean): Int = if (value) (this or (1 shl n)) else (this and (1 shl n).inv())

/**
 * Change the bit with index 0 of a long
 * @param value The value to set the bit to
 * @return The long with the bit changed
 */
fun Long.withBit0(value: Boolean): Long = if (value) (this or 0x01L) else (this and 0xFFFFFFFFFFFFFFFEuL.toLong())

/**
 * Change the bit with index 1 of a long
 * @param value The value to set the bit to
 * @return The long with the bit changed
 */
fun Long.withBit1(value: Boolean): Long = if (value) (this or 0x02L) else (this and 0xFFFFFFFFFFFFFFFDuL.toLong())

/**
 * Change the bit with index 2 of a long
 * @param value The value to set the bit to
 * @return The long with the bit changed
 */
fun Long.withBit2(value: Boolean): Long = if (value) (this or 0x04L) else (this and 0xFFFFFFFFFFFFFFFBuL.toLong())

/**
 * Change the bit with index 3 of a long
 * @param value The value to set the bit to
 * @return The long with the bit changed
 */
fun Long.withBit3(value: Boolean): Long = if (value) (this or 0x08L) else (this and 0xFFFFFFFFFFFFFFF7uL.toLong())

/**
 * Change the bit with index 4 of a long
 * @param value The value to set the bit to
 * @return The long with the bit changed
 */
fun Long.withBit4(value: Boolean): Long = if (value) (this or 0x10L) else (this and 0xFFFFFFFFFFFFFFEFuL.toLong())

/**
 * Change the bit with index 5 of a long
 * @param value The value to set the bit to
 * @return The long with the bit changed
 */
fun Long.withBit5(value: Boolean): Long = if (value) (this or 0x20L) else (this and 0xFFFFFFFFFFFFFFDFuL.toLong())

/**
 * Change the bit with index 6 of a long
 * @param value The value to set the bit to
 * @return The long with the bit changed
 */
fun Long.withBit6(value: Boolean): Long = if (value) (this or 0x40L) else (this and 0xFFFFFFFFFFFFFFBFuL.toLong())

/**
 * Change the bit with index 7 of a long
 * @param value The value to set the bit to
 * @return The long with the bit changed
 */
fun Long.withBit7(value: Boolean): Long = if (value) (this or 0x80L) else (this and 0xFFFFFFFFFFFFFF7FuL.toLong())

/**
 * Change the bit with index 8 of a long
 * @param value The value to set the bit to
 * @return The long with the bit changed
 */
fun Long.withBit8(value: Boolean): Long = if (value) (this or 0x100L) else (this and 0xFFFFFFFFFFFFFEFFuL.toLong())

/**
 * Change the bit with index 9 of a long
 * @param value The value to set the bit to
 * @return The long with the bit changed
 */
fun Long.withBit9(value: Boolean): Long = if (value) (this or 0x200L) else (this and 0xFFFFFFFFFFFFFDFFuL.toLong())

/**
 * Change the bit with index 10 of a long
 * @param value The value to set the bit to
 * @return The long with the bit changed
 */
fun Long.withBit10(value: Boolean): Long = if (value) (this or 0x400L) else (this and 0xFFFFFFFFFFFFFBFFuL.toLong())

/**
 * Change the bit with index 11 of a long
 * @param value The value to set the bit to
 * @return The long with the bit changed
 */
fun Long.withBit11(value: Boolean): Long = if (value) (this or 0x800L) else (this and 0xFFFFFFFFFFFFF7FFuL.toLong())

/**
 * Change the bit with index 12 of a long
 * @param value The value to set the bit to
 * @return The long with the bit changed
 */
fun Long.withBit12(value: Boolean): Long = if (value) (this or 0x1000L) else (this and 0xFFFFFFFFFFFFEFFFuL.toLong())

/**
 * Change the bit with index 13 of a long
 * @param value The value to set the bit to
 * @return The long with the bit changed
 */
fun Long.withBit13(value: Boolean): Long = if (value) (this or 0x2000L) else (this and 0xFFFFFFFFFFFFDFFFuL.toLong())

/**
 * Change the bit with index 14 of a long
 * @param value The value to set the bit to
 * @return The long with the bit changed
 */
fun Long.withBit14(value: Boolean): Long = if (value) (this or 0x4000L) else (this and 0xFFFFFFFFFFFFBFFFuL.toLong())

/**
 * Change the bit with index 15 of a long
 * @param value The value to set the bit to
 * @return The long with the bit changed
 */
fun Long.withBit15(value: Boolean): Long = if (value) (this or 0x8000L) else (this and 0xFFFFFFFFFFFF7FFFuL.toLong())

/**
 * Change the bit with index 16 of a long
 * @param value The value to set the bit to
 * @return The long with the bit changed
 */
fun Long.withBit16(value: Boolean): Long = if (value) (this or 0x10000L) else (this and 0xFFFFFFFFFFFEFFFFuL.toLong())

/**
 * Change the bit with index 17 of a long
 * @param value The value to set the bit to
 * @return The long with the bit changed
 */
fun Long.withBit17(value: Boolean): Long = if (value) (this or 0x20000L) else (this and 0xFFFFFFFFFFFDFFFFuL.toLong())

/**
 * Change the bit with index 18 of a long
 * @param value The value to set the bit to
 * @return The long with the bit changed
 */
fun Long.withBit18(value: Boolean): Long = if (value) (this or 0x40000L) else (this and 0xFFFFFFFFFFFBFFFFuL.toLong())

/**
 * Change the bit with index 19 of a long
 * @param value The value to set the bit to
 * @return The long with the bit changed
 */
fun Long.withBit19(value: Boolean): Long = if (value) (this or 0x80000L) else (this and 0xFFFFFFFFFFF7FFFFuL.toLong())

/**
 * Change the bit with index 20 of a long
 * @param value The value to set the bit to
 * @return The long with the bit changed
 */
fun Long.withBit20(value: Boolean): Long = if (value) (this or 0x100000L) else (this and 0xFFFFFFFFFFEFFFFFuL.toLong())

/**
 * Change the bit with index 21 of a long
 * @param value The value to set the bit to
 * @return The long with the bit changed
 */
fun Long.withBit21(value: Boolean): Long = if (value) (this or 0x200000L) else (this and 0xFFFFFFFFFFDFFFFFuL.toLong())

/**
 * Change the bit with index 22 of a long
 * @param value The value to set the bit to
 * @return The long with the bit changed
 */
fun Long.withBit22(value: Boolean): Long = if (value) (this or 0x400000L) else (this and 0xFFFFFFFFFFBFFFFFuL.toLong())

/**
 * Change the bit with index 23 of a long
 * @param value The value to set the bit to
 * @return The long with the bit changed
 */
fun Long.withBit23(value: Boolean): Long = if (value) (this or 0x800000L) else (this and 0xFFFFFFFFFF7FFFFFuL.toLong())

/**
 * Change the bit with index 24 of a long
 * @param value The value to set the bit to
 * @return The long with the bit changed
 */
fun Long.withBit24(value: Boolean): Long = if (value) (this or 0x1000000L) else (this and 0xFFFFFFFFFEFFFFFFuL.toLong())

/**
 * Change the bit with index 25 of a long
 * @param value The value to set the bit to
 * @return The long with the bit changed
 */
fun Long.withBit25(value: Boolean): Long = if (value) (this or 0x2000000L) else (this and 0xFFFFFFFFFDFFFFFFuL.toLong())

/**
 * Change the bit with index 26 of a long
 * @param value The value to set the bit to
 * @return The long with the bit changed
 */
fun Long.withBit26(value: Boolean): Long = if (value) (this or 0x4000000L) else (this and 0xFFFFFFFFFBFFFFFFuL.toLong())

/**
 * Change the bit with index 27 of a long
 * @param value The value to set the bit to
 * @return The long with the bit changed
 */
fun Long.withBit27(value: Boolean): Long = if (value) (this or 0x8000000L) else (this and 0xFFFFFFFFF7FFFFFFuL.toLong())

/**
 * Change the bit with index 28 of a long
 * @param value The value to set the bit to
 * @return The long with the bit changed
 */
fun Long.withBit28(value: Boolean): Long = if (value) (this or 0x10000000L) else (this and 0xFFFFFFFFEFFFFFFFuL.toLong())

/**
 * Change the bit with index 29 of a long
 * @param value The value to set the bit to
 * @return The long with the bit changed
 */
fun Long.withBit29(value: Boolean): Long = if (value) (this or 0x20000000L) else (this and 0xFFFFFFFFDFFFFFFFuL.toLong())

/**
 * Change the bit with index 30 of a long
 * @param value The value to set the bit to
 * @return The long with the bit changed
 */
fun Long.withBit30(value: Boolean): Long = if (value) (this or 0x40000000L) else (this and 0xFFFFFFFFBFFFFFFFuL.toLong())

/**
 * Change the bit with index 31 of a long
 * @param value The value to set the bit to
 * @return The long with the bit changed
 */
fun Long.withBit31(value: Boolean): Long = if (value) (this or 0x80000000L) else (this and 0xFFFFFFFF7FFFFFFFuL.toLong())

/**
 * Change the bit with index 32 of a long
 * @param value The value to set the bit to
 * @return The long with the bit changed
 */
fun Long.withBit32(value: Boolean): Long = if (value) (this or 0x100000000L) else (this and 0xFFFFFFFEFFFFFFFFuL.toLong())

/**
 * Change the bit with index 33 of a long
 * @param value The value to set the bit to
 * @return The long with the bit changed
 */
fun Long.withBit33(value: Boolean): Long = if (value) (this or 0x200000000L) else (this and 0xFFFFFFFDFFFFFFFFuL.toLong())

/**
 * Change the bit with index 34 of a long
 * @param value The value to set the bit to
 * @return The long with the bit changed
 */
fun Long.withBit34(value: Boolean): Long = if (value) (this or 0x400000000L) else (this and 0xFFFFFFFBFFFFFFFFuL.toLong())

/**
 * Change the bit with index 35 of a long
 * @param value The value to set the bit to
 * @return The long with the bit changed
 */
fun Long.withBit35(value: Boolean): Long = if (value) (this or 0x800000000L) else (this and 0xFFFFFFF7FFFFFFFFuL.toLong())

/**
 * Change the bit with index 36 of a long
 * @param value The value to set the bit to
 * @return The long with the bit changed
 */
fun Long.withBit36(value: Boolean): Long = if (value) (this or 0x1000000000L) else (this and 0xFFFFFFEFFFFFFFFFuL.toLong())

/**
 * Change the bit with index 37 of a long
 * @param value The value to set the bit to
 * @return The long with the bit changed
 */
fun Long.withBit37(value: Boolean): Long = if (value) (this or 0x2000000000L) else (this and 0xFFFFFFDFFFFFFFFFuL.toLong())

/**
 * Change the bit with index 38 of a long
 * @param value The value to set the bit to
 * @return The long with the bit changed
 */
fun Long.withBit38(value: Boolean): Long = if (value) (this or 0x4000000000L) else (this and 0xFFFFFFBFFFFFFFFFuL.toLong())

/**
 * Change the bit with index 39 of a long
 * @param value The value to set the bit to
 * @return The long with the bit changed
 */
fun Long.withBit39(value: Boolean): Long = if (value) (this or 0x8000000000L) else (this and 0xFFFFFF7FFFFFFFFFuL.toLong())

/**
 * Change the bit with index 40 of a long
 * @param value The value to set the bit to
 * @return The long with the bit changed
 */
fun Long.withBit40(value: Boolean): Long = if (value) (this or 0x10000000000L) else (this and 0xFFFFFEFFFFFFFFFFuL.toLong())

/**
 * Change the bit with index 41 of a long
 * @param value The value to set the bit to
 * @return The long with the bit changed
 */
fun Long.withBit41(value: Boolean): Long = if (value) (this or 0x20000000000L) else (this and 0xFFFFFDFFFFFFFFFFuL.toLong())

/**
 * Change the bit with index 42 of a long
 * @param value The value to set the bit to
 * @return The long with the bit changed
 */
fun Long.withBit42(value: Boolean): Long = if (value) (this or 0x40000000000L) else (this and 0xFFFFFBFFFFFFFFFFuL.toLong())

/**
 * Change the bit with index 43 of a long
 * @param value The value to set the bit to
 * @return The long with the bit changed
 */
fun Long.withBit43(value: Boolean): Long = if (value) (this or 0x80000000000L) else (this and 0xFFFFF7FFFFFFFFFFuL.toLong())

/**
 * Change the bit with index 44 of a long
 * @param value The value to set the bit to
 * @return The long with the bit changed
 */
fun Long.withBit44(value: Boolean): Long = if (value) (this or 0x100000000000L) else (this and 0xFFFFEFFFFFFFFFFFuL.toLong())

/**
 * Change the bit with index 45 of a long
 * @param value The value to set the bit to
 * @return The long with the bit changed
 */
fun Long.withBit45(value: Boolean): Long = if (value) (this or 0x200000000000L) else (this and 0xFFFFDFFFFFFFFFFFuL.toLong())

/**
 * Change the bit with index 46 of a long
 * @param value The value to set the bit to
 * @return The long with the bit changed
 */
fun Long.withBit46(value: Boolean): Long = if (value) (this or 0x400000000000L) else (this and 0xFFFFBFFFFFFFFFFFuL.toLong())

/**
 * Change the bit with index 47 of a long
 * @param value The value to set the bit to
 * @return The long with the bit changed
 */
fun Long.withBit47(value: Boolean): Long = if (value) (this or 0x800000000000L) else (this and 0xFFFF7FFFFFFFFFFFuL.toLong())

/**
 * Change the bit with index 48 of a long
 * @param value The value to set the bit to
 * @return The long with the bit changed
 */
fun Long.withBit48(value: Boolean): Long = if (value) (this or 0x1000000000000L) else (this and 0xFFFEFFFFFFFFFFFFuL.toLong())

/**
 * Change the bit with index 49 of a long
 * @param value The value to set the bit to
 * @return The long with the bit changed
 */
fun Long.withBit49(value: Boolean): Long = if (value) (this or 0x2000000000000L) else (this and 0xFFFDFFFFFFFFFFFFuL.toLong())

/**
 * Change the bit with index 50 of a long
 * @param value The value to set the bit to
 * @return The long with the bit changed
 */
fun Long.withBit50(value: Boolean): Long = if (value) (this or 0x4000000000000L) else (this and 0xFFFBFFFFFFFFFFFFuL.toLong())

/**
 * Change the bit with index 51 of a long
 * @param value The value to set the bit to
 * @return The long with the bit changed
 */
fun Long.withBit51(value: Boolean): Long = if (value) (this or 0x8000000000000L) else (this and 0xFFF7FFFFFFFFFFFFuL.toLong())

/**
 * Change the bit with index 52 of a long
 * @param value The value to set the bit to
 * @return The long with the bit changed
 */
fun Long.withBit52(value: Boolean): Long = if (value) (this or 0x10000000000000L) else (this and 0xFFEFFFFFFFFFFFFFuL.toLong())

/**
 * Change the bit with index 53 of a long
 * @param value The value to set the bit to
 * @return The long with the bit changed
 */
fun Long.withBit53(value: Boolean): Long = if (value) (this or 0x20000000000000L) else (this and 0xFFDFFFFFFFFFFFFFuL.toLong())

/**
 * Change the bit with index 54 of a long
 * @param value The value to set the bit to
 * @return The long with the bit changed
 */
fun Long.withBit54(value: Boolean): Long = if (value) (this or 0x40000000000000L) else (this and 0xFFBFFFFFFFFFFFFFuL.toLong())

/**
 * Change the bit with index 55 of a long
 * @param value The value to set the bit to
 * @return The long with the bit changed
 */
fun Long.withBit55(value: Boolean): Long = if (value) (this or 0x80000000000000L) else (this and 0xFF7FFFFFFFFFFFFFuL.toLong())

/**
 * Change the bit with index 56 of a long
 * @param value The value to set the bit to
 * @return The long with the bit changed
 */
fun Long.withBit56(value: Boolean): Long = if (value) (this or 0x100000000000000L) else (this and 0xFEFFFFFFFFFFFFFFuL.toLong())

/**
 * Change the bit with index 57 of a long
 * @param value The value to set the bit to
 * @return The long with the bit changed
 */
fun Long.withBit57(value: Boolean): Long = if (value) (this or 0x200000000000000L) else (this and 0xFDFFFFFFFFFFFFFFuL.toLong())

/**
 * Change the bit with index 58 of a long
 * @param value The value to set the bit to
 * @return The long with the bit changed
 */
fun Long.withBit58(value: Boolean): Long = if (value) (this or 0x400000000000000L) else (this and 0xFBFFFFFFFFFFFFFFuL.toLong())

/**
 * Change the bit with index 59 of a long
 * @param value The value to set the bit to
 * @return The long with the bit changed
 */
fun Long.withBit59(value: Boolean): Long = if (value) (this or 0x800000000000000L) else (this and 0xF7FFFFFFFFFFFFFFuL.toLong())

/**
 * Change the bit with index 60 of a long
 * @param value The value to set the bit to
 * @return The long with the bit changed
 */
fun Long.withBit60(value: Boolean): Long = if (value) (this or 0x1000000000000000L) else (this and 0xEFFFFFFFFFFFFFFFuL.toLong())

/**
 * Change the bit with index 61 of a long
 * @param value The value to set the bit to
 * @return The long with the bit changed
 */
fun Long.withBit61(value: Boolean): Long = if (value) (this or 0x2000000000000000L) else (this and 0xDFFFFFFFFFFFFFFFuL.toLong())

/**
 * Change the bit with index 62 of a long
 * @param value The value to set the bit to
 * @return The long with the bit changed
 */
fun Long.withBit62(value: Boolean): Long = if (value) (this or 0x4000000000000000L) else (this and 0xBFFFFFFFFFFFFFFFuL.toLong())

/**
 * Change the bit with index 63 of a long
 * @param value The value to set the bit to
 * @return The long with the bit changed
 */
fun Long.withBit63(value: Boolean): Long = if (value) (this or 0x8000000000000000uL.toLong()) else (this and 0x7FFFFFFFFFFFFFFFuL.toLong())

/**
 * Change the n-th bit of a long
 * @param n The index of the bit to change
 * @param value The value to set the bit to
 * @return The long with the bit changed
 */
fun Long.withBit(n: Int, value: Boolean): Long = if (value) (this or (1L shl n)) else (this and (1L shl n).inv())

/**
 * Change the bit with index 0 of a Float
 * @param value The value to set the bit to
 * @return The Float with the bit changed
 */
fun Float.withBit0(value: Boolean): Float = Float.fromBits(this.toRawBits().withBit0(value))

/**
 * Change the bit with index 1 of a Float
 * @param value The value to set the bit to
 * @return The Float with the bit changed
 */
fun Float.withBit1(value: Boolean): Float = Float.fromBits(this.toRawBits().withBit1(value))

/**
 * Change the bit with index 2 of a Float
 * @param value The value to set the bit to
 * @return The Float with the bit changed
 */
fun Float.withBit2(value: Boolean): Float = Float.fromBits(this.toRawBits().withBit2(value))

/**
 * Change the bit with index 3 of a Float
 * @param value The value to set the bit to
 * @return The Float with the bit changed
 */
fun Float.withBit3(value: Boolean): Float = Float.fromBits(this.toRawBits().withBit3(value))

/**
 * Change the bit with index 4 of a Float
 * @param value The value to set the bit to
 * @return The Float with the bit changed
 */
fun Float.withBit4(value: Boolean): Float = Float.fromBits(this.toRawBits().withBit4(value))

/**
 * Change the bit with index 5 of a Float
 * @param value The value to set the bit to
 * @return The Float with the bit changed
 */
fun Float.withBit5(value: Boolean): Float = Float.fromBits(this.toRawBits().withBit5(value))

/**
 * Change the bit with index 6 of a Float
 * @param value The value to set the bit to
 * @return The Float with the bit changed
 */
fun Float.withBit6(value: Boolean): Float = Float.fromBits(this.toRawBits().withBit6(value))

/**
 * Change the bit with index 7 of a Float
 * @param value The value to set the bit to
 * @return The Float with the bit changed
 */
fun Float.withBit7(value: Boolean): Float = Float.fromBits(this.toRawBits().withBit7(value))

/**
 * Change the bit with index 8 of a Float
 * @param value The value to set the bit to
 * @return The Float with the bit changed
 */
fun Float.withBit8(value: Boolean): Float = Float.fromBits(this.toRawBits().withBit8(value))

/**
 * Change the bit with index 9 of a Float
 * @param value The value to set the bit to
 * @return The Float with the bit changed
 */
fun Float.withBit9(value: Boolean): Float = Float.fromBits(this.toRawBits().withBit9(value))

/**
 * Change the bit with index 10 of a Float
 * @param value The value to set the bit to
 * @return The Float with the bit changed
 */
fun Float.withBit10(value: Boolean): Float = Float.fromBits(this.toRawBits().withBit10(value))

/**
 * Change the bit with index 11 of a Float
 * @param value The value to set the bit to
 * @return The Float with the bit changed
 */
fun Float.withBit11(value: Boolean): Float = Float.fromBits(this.toRawBits().withBit11(value))

/**
 * Change the bit with index 12 of a Float
 * @param value The value to set the bit to
 * @return The Float with the bit changed
 */
fun Float.withBit12(value: Boolean): Float = Float.fromBits(this.toRawBits().withBit12(value))

/**
 * Change the bit with index 13 of a Float
 * @param value The value to set the bit to
 * @return The Float with the bit changed
 */
fun Float.withBit13(value: Boolean): Float = Float.fromBits(this.toRawBits().withBit13(value))

/**
 * Change the bit with index 14 of a Float
 * @param value The value to set the bit to
 * @return The Float with the bit changed
 */
fun Float.withBit14(value: Boolean): Float = Float.fromBits(this.toRawBits().withBit14(value))

/**
 * Change the bit with index 15 of a Float
 * @param value The value to set the bit to
 * @return The Float with the bit changed
 */
fun Float.withBit15(value: Boolean): Float = Float.fromBits(this.toRawBits().withBit15(value))

/**
 * Change the bit with index 16 of a Float
 * @param value The value to set the bit to
 * @return The Float with the bit changed
 */
fun Float.withBit16(value: Boolean): Float = Float.fromBits(this.toRawBits().withBit16(value))

/**
 * Change the bit with index 17 of a Float
 * @param value The value to set the bit to
 * @return The Float with the bit changed
 */
fun Float.withBit17(value: Boolean): Float = Float.fromBits(this.toRawBits().withBit17(value))

/**
 * Change the bit with index 18 of a Float
 * @param value The value to set the bit to
 * @return The Float with the bit changed
 */
fun Float.withBit18(value: Boolean): Float = Float.fromBits(this.toRawBits().withBit18(value))

/**
 * Change the bit with index 19 of a Float
 * @param value The value to set the bit to
 * @return The Float with the bit changed
 */
fun Float.withBit19(value: Boolean): Float = Float.fromBits(this.toRawBits().withBit19(value))

/**
 * Change the bit with index 20 of a Float
 * @param value The value to set the bit to
 * @return The Float with the bit changed
 */
fun Float.withBit20(value: Boolean): Float = Float.fromBits(this.toRawBits().withBit20(value))

/**
 * Change the bit with index 21 of a Float
 * @param value The value to set the bit to
 * @return The Float with the bit changed
 */
fun Float.withBit21(value: Boolean): Float = Float.fromBits(this.toRawBits().withBit21(value))

/**
 * Change the bit with index 22 of a Float
 * @param value The value to set the bit to
 * @return The Float with the bit changed
 */
fun Float.withBit22(value: Boolean): Float = Float.fromBits(this.toRawBits().withBit22(value))

/**
 * Change the bit with index 23 of a Float
 * @param value The value to set the bit to
 * @return The Float with the bit changed
 */
fun Float.withBit23(value: Boolean): Float = Float.fromBits(this.toRawBits().withBit23(value))

/**
 * Change the bit with index 24 of a Float
 * @param value The value to set the bit to
 * @return The Float with the bit changed
 */
fun Float.withBit24(value: Boolean): Float = Float.fromBits(this.toRawBits().withBit24(value))

/**
 * Change the bit with index 25 of a Float
 * @param value The value to set the bit to
 * @return The Float with the bit changed
 */
fun Float.withBit25(value: Boolean): Float = Float.fromBits(this.toRawBits().withBit25(value))

/**
 * Change the bit with index 26 of a Float
 * @param value The value to set the bit to
 * @return The Float with the bit changed
 */
fun Float.withBit26(value: Boolean): Float = Float.fromBits(this.toRawBits().withBit26(value))

/**
 * Change the bit with index 27 of a Float
 * @param value The value to set the bit to
 * @return The Float with the bit changed
 */
fun Float.withBit27(value: Boolean): Float = Float.fromBits(this.toRawBits().withBit27(value))

/**
 * Change the bit with index 28 of a Float
 * @param value The value to set the bit to
 * @return The Float with the bit changed
 */
fun Float.withBit28(value: Boolean): Float = Float.fromBits(this.toRawBits().withBit28(value))

/**
 * Change the bit with index 29 of a Float
 * @param value The value to set the bit to
 * @return The Float with the bit changed
 */
fun Float.withBit29(value: Boolean): Float = Float.fromBits(this.toRawBits().withBit29(value))

/**
 * Change the bit with index 30 of a Float
 * @param value The value to set the bit to
 * @return The Float with the bit changed
 */
fun Float.withBit30(value: Boolean): Float = Float.fromBits(this.toRawBits().withBit30(value))

/**
 * Change the bit with index 31 of a Float
 * @param value The value to set the bit to
 * @return The Float with the bit changed
 */
fun Float.withBit31(value: Boolean): Float = Float.fromBits(this.toRawBits().withBit31(value))

/**
 * Change the n-th bit of a Float
 * @param n The index of the bit to change
 * @param value The value to set the bit to
 * @return The Float with the bit changed
 */
fun Float.withBit(n: Int, value: Boolean): Float = Float.fromBits(this.toRawBits().withBit(n, value))

/**
 * Change the bit with index 0 of a Double
 * @param value The value to set the bit to
 * @return The Double with the bit changed
 */
fun Double.withBit0(value: Boolean): Double = Double.fromBits(this.toRawBits().withBit0(value))

/**
 * Change the bit with index 1 of a Double
 * @param value The value to set the bit to
 * @return The Double with the bit changed
 */
fun Double.withBit1(value: Boolean): Double = Double.fromBits(this.toRawBits().withBit1(value))

/**
 * Change the bit with index 2 of a Double
 * @param value The value to set the bit to
 * @return The Double with the bit changed
 */
fun Double.withBit2(value: Boolean): Double = Double.fromBits(this.toRawBits().withBit2(value))

/**
 * Change the bit with index 3 of a Double
 * @param value The value to set the bit to
 * @return The Double with the bit changed
 */
fun Double.withBit3(value: Boolean): Double = Double.fromBits(this.toRawBits().withBit3(value))

/**
 * Change the bit with index 4 of a Double
 * @param value The value to set the bit to
 * @return The Double with the bit changed
 */
fun Double.withBit4(value: Boolean): Double = Double.fromBits(this.toRawBits().withBit4(value))

/**
 * Change the bit with index 5 of a Double
 * @param value The value to set the bit to
 * @return The Double with the bit changed
 */
fun Double.withBit5(value: Boolean): Double = Double.fromBits(this.toRawBits().withBit5(value))

/**
 * Change the bit with index 6 of a Double
 * @param value The value to set the bit to
 * @return The Double with the bit changed
 */
fun Double.withBit6(value: Boolean): Double = Double.fromBits(this.toRawBits().withBit6(value))

/**
 * Change the bit with index 7 of a Double
 * @param value The value to set the bit to
 * @return The Double with the bit changed
 */
fun Double.withBit7(value: Boolean): Double = Double.fromBits(this.toRawBits().withBit7(value))

/**
 * Change the bit with index 8 of a Double
 * @param value The value to set the bit to
 * @return The Double with the bit changed
 */
fun Double.withBit8(value: Boolean): Double = Double.fromBits(this.toRawBits().withBit8(value))

/**
 * Change the bit with index 9 of a Double
 * @param value The value to set the bit to
 * @return The Double with the bit changed
 */
fun Double.withBit9(value: Boolean): Double = Double.fromBits(this.toRawBits().withBit9(value))

/**
 * Change the bit with index 10 of a Double
 * @param value The value to set the bit to
 * @return The Double with the bit changed
 */
fun Double.withBit10(value: Boolean): Double = Double.fromBits(this.toRawBits().withBit10(value))

/**
 * Change the bit with index 11 of a Double
 * @param value The value to set the bit to
 * @return The Double with the bit changed
 */
fun Double.withBit11(value: Boolean): Double = Double.fromBits(this.toRawBits().withBit11(value))

/**
 * Change the bit with index 12 of a Double
 * @param value The value to set the bit to
 * @return The Double with the bit changed
 */
fun Double.withBit12(value: Boolean): Double = Double.fromBits(this.toRawBits().withBit12(value))

/**
 * Change the bit with index 13 of a Double
 * @param value The value to set the bit to
 * @return The Double with the bit changed
 */
fun Double.withBit13(value: Boolean): Double = Double.fromBits(this.toRawBits().withBit13(value))

/**
 * Change the bit with index 14 of a Double
 * @param value The value to set the bit to
 * @return The Double with the bit changed
 */
fun Double.withBit14(value: Boolean): Double = Double.fromBits(this.toRawBits().withBit14(value))

/**
 * Change the bit with index 15 of a Double
 * @param value The value to set the bit to
 * @return The Double with the bit changed
 */
fun Double.withBit15(value: Boolean): Double = Double.fromBits(this.toRawBits().withBit15(value))

/**
 * Change the bit with index 16 of a Double
 * @param value The value to set the bit to
 * @return The Double with the bit changed
 */
fun Double.withBit16(value: Boolean): Double = Double.fromBits(this.toRawBits().withBit16(value))

/**
 * Change the bit with index 17 of a Double
 * @param value The value to set the bit to
 * @return The Double with the bit changed
 */
fun Double.withBit17(value: Boolean): Double = Double.fromBits(this.toRawBits().withBit17(value))

/**
 * Change the bit with index 18 of a Double
 * @param value The value to set the bit to
 * @return The Double with the bit changed
 */
fun Double.withBit18(value: Boolean): Double = Double.fromBits(this.toRawBits().withBit18(value))

/**
 * Change the bit with index 19 of a Double
 * @param value The value to set the bit to
 * @return The Double with the bit changed
 */
fun Double.withBit19(value: Boolean): Double = Double.fromBits(this.toRawBits().withBit19(value))

/**
 * Change the bit with index 20 of a Double
 * @param value The value to set the bit to
 * @return The Double with the bit changed
 */
fun Double.withBit20(value: Boolean): Double = Double.fromBits(this.toRawBits().withBit20(value))

/**
 * Change the bit with index 21 of a Double
 * @param value The value to set the bit to
 * @return The Double with the bit changed
 */
fun Double.withBit21(value: Boolean): Double = Double.fromBits(this.toRawBits().withBit21(value))

/**
 * Change the bit with index 22 of a Double
 * @param value The value to set the bit to
 * @return The Double with the bit changed
 */
fun Double.withBit22(value: Boolean): Double = Double.fromBits(this.toRawBits().withBit22(value))

/**
 * Change the bit with index 23 of a Double
 * @param value The value to set the bit to
 * @return The Double with the bit changed
 */
fun Double.withBit23(value: Boolean): Double = Double.fromBits(this.toRawBits().withBit23(value))

/**
 * Change the bit with index 24 of a Double
 * @param value The value to set the bit to
 * @return The Double with the bit changed
 */
fun Double.withBit24(value: Boolean): Double = Double.fromBits(this.toRawBits().withBit24(value))

/**
 * Change the bit with index 25 of a Double
 * @param value The value to set the bit to
 * @return The Double with the bit changed
 */
fun Double.withBit25(value: Boolean): Double = Double.fromBits(this.toRawBits().withBit25(value))

/**
 * Change the bit with index 26 of a Double
 * @param value The value to set the bit to
 * @return The Double with the bit changed
 */
fun Double.withBit26(value: Boolean): Double = Double.fromBits(this.toRawBits().withBit26(value))

/**
 * Change the bit with index 27 of a Double
 * @param value The value to set the bit to
 * @return The Double with the bit changed
 */
fun Double.withBit27(value: Boolean): Double = Double.fromBits(this.toRawBits().withBit27(value))

/**
 * Change the bit with index 28 of a Double
 * @param value The value to set the bit to
 * @return The Double with the bit changed
 */
fun Double.withBit28(value: Boolean): Double = Double.fromBits(this.toRawBits().withBit28(value))

/**
 * Change the bit with index 29 of a Double
 * @param value The value to set the bit to
 * @return The Double with the bit changed
 */
fun Double.withBit29(value: Boolean): Double = Double.fromBits(this.toRawBits().withBit29(value))

/**
 * Change the bit with index 30 of a Double
 * @param value The value to set the bit to
 * @return The Double with the bit changed
 */
fun Double.withBit30(value: Boolean): Double = Double.fromBits(this.toRawBits().withBit30(value))

/**
 * Change the bit with index 31 of a Double
 * @param value The value to set the bit to
 * @return The Double with the bit changed
 */
fun Double.withBit31(value: Boolean): Double = Double.fromBits(this.toRawBits().withBit31(value))

/**
 * Change the bit with index 32 of a Double
 * @param value The value to set the bit to
 * @return The Double with the bit changed
 */
fun Double.withBit32(value: Boolean): Double = Double.fromBits(this.toRawBits().withBit32(value))

/**
 * Change the bit with index 33 of a Double
 * @param value The value to set the bit to
 * @return The Double with the bit changed
 */
fun Double.withBit33(value: Boolean): Double = Double.fromBits(this.toRawBits().withBit33(value))

/**
 * Change the bit with index 34 of a Double
 * @param value The value to set the bit to
 * @return The Double with the bit changed
 */
fun Double.withBit34(value: Boolean): Double = Double.fromBits(this.toRawBits().withBit34(value))

/**
 * Change the bit with index 35 of a Double
 * @param value The value to set the bit to
 * @return The Double with the bit changed
 */
fun Double.withBit35(value: Boolean): Double = Double.fromBits(this.toRawBits().withBit35(value))

/**
 * Change the bit with index 36 of a Double
 * @param value The value to set the bit to
 * @return The Double with the bit changed
 */
fun Double.withBit36(value: Boolean): Double = Double.fromBits(this.toRawBits().withBit36(value))

/**
 * Change the bit with index 37 of a Double
 * @param value The value to set the bit to
 * @return The Double with the bit changed
 */
fun Double.withBit37(value: Boolean): Double = Double.fromBits(this.toRawBits().withBit37(value))

/**
 * Change the bit with index 38 of a Double
 * @param value The value to set the bit to
 * @return The Double with the bit changed
 */
fun Double.withBit38(value: Boolean): Double = Double.fromBits(this.toRawBits().withBit38(value))

/**
 * Change the bit with index 39 of a Double
 * @param value The value to set the bit to
 * @return The Double with the bit changed
 */
fun Double.withBit39(value: Boolean): Double = Double.fromBits(this.toRawBits().withBit39(value))

/**
 * Change the bit with index 40 of a Double
 * @param value The value to set the bit to
 * @return The Double with the bit changed
 */
fun Double.withBit40(value: Boolean): Double = Double.fromBits(this.toRawBits().withBit40(value))

/**
 * Change the bit with index 41 of a Double
 * @param value The value to set the bit to
 * @return The Double with the bit changed
 */
fun Double.withBit41(value: Boolean): Double = Double.fromBits(this.toRawBits().withBit41(value))

/**
 * Change the bit with index 42 of a Double
 * @param value The value to set the bit to
 * @return The Double with the bit changed
 */
fun Double.withBit42(value: Boolean): Double = Double.fromBits(this.toRawBits().withBit42(value))

/**
 * Change the bit with index 43 of a Double
 * @param value The value to set the bit to
 * @return The Double with the bit changed
 */
fun Double.withBit43(value: Boolean): Double = Double.fromBits(this.toRawBits().withBit43(value))

/**
 * Change the bit with index 44 of a Double
 * @param value The value to set the bit to
 * @return The Double with the bit changed
 */
fun Double.withBit44(value: Boolean): Double = Double.fromBits(this.toRawBits().withBit44(value))

/**
 * Change the bit with index 45 of a Double
 * @param value The value to set the bit to
 * @return The Double with the bit changed
 */
fun Double.withBit45(value: Boolean): Double = Double.fromBits(this.toRawBits().withBit45(value))

/**
 * Change the bit with index 46 of a Double
 * @param value The value to set the bit to
 * @return The Double with the bit changed
 */
fun Double.withBit46(value: Boolean): Double = Double.fromBits(this.toRawBits().withBit46(value))

/**
 * Change the bit with index 47 of a Double
 * @param value The value to set the bit to
 * @return The Double with the bit changed
 */
fun Double.withBit47(value: Boolean): Double = Double.fromBits(this.toRawBits().withBit47(value))

/**
 * Change the bit with index 48 of a Double
 * @param value The value to set the bit to
 * @return The Double with the bit changed
 */
fun Double.withBit48(value: Boolean): Double = Double.fromBits(this.toRawBits().withBit48(value))

/**
 * Change the bit with index 49 of a Double
 * @param value The value to set the bit to
 * @return The Double with the bit changed
 */
fun Double.withBit49(value: Boolean): Double = Double.fromBits(this.toRawBits().withBit49(value))

/**
 * Change the bit with index 50 of a Double
 * @param value The value to set the bit to
 * @return The Double with the bit changed
 */
fun Double.withBit50(value: Boolean): Double = Double.fromBits(this.toRawBits().withBit50(value))

/**
 * Change the bit with index 51 of a Double
 * @param value The value to set the bit to
 * @return The Double with the bit changed
 */
fun Double.withBit51(value: Boolean): Double = Double.fromBits(this.toRawBits().withBit51(value))

/**
 * Change the bit with index 52 of a Double
 * @param value The value to set the bit to
 * @return The Double with the bit changed
 */
fun Double.withBit52(value: Boolean): Double = Double.fromBits(this.toRawBits().withBit52(value))

/**
 * Change the bit with index 53 of a Double
 * @param value The value to set the bit to
 * @return The Double with the bit changed
 */
fun Double.withBit53(value: Boolean): Double = Double.fromBits(this.toRawBits().withBit53(value))

/**
 * Change the bit with index 54 of a Double
 * @param value The value to set the bit to
 * @return The Double with the bit changed
 */
fun Double.withBit54(value: Boolean): Double = Double.fromBits(this.toRawBits().withBit54(value))

/**
 * Change the bit with index 55 of a Double
 * @param value The value to set the bit to
 * @return The Double with the bit changed
 */
fun Double.withBit55(value: Boolean): Double = Double.fromBits(this.toRawBits().withBit55(value))

/**
 * Change the bit with index 56 of a Double
 * @param value The value to set the bit to
 * @return The Double with the bit changed
 */
fun Double.withBit56(value: Boolean): Double = Double.fromBits(this.toRawBits().withBit56(value))

/**
 * Change the bit with index 57 of a Double
 * @param value The value to set the bit to
 * @return The Double with the bit changed
 */
fun Double.withBit57(value: Boolean): Double = Double.fromBits(this.toRawBits().withBit57(value))

/**
 * Change the bit with index 58 of a Double
 * @param value The value to set the bit to
 * @return The Double with the bit changed
 */
fun Double.withBit58(value: Boolean): Double = Double.fromBits(this.toRawBits().withBit58(value))

/**
 * Change the bit with index 59 of a Double
 * @param value The value to set the bit to
 * @return The Double with the bit changed
 */
fun Double.withBit59(value: Boolean): Double = Double.fromBits(this.toRawBits().withBit59(value))

/**
 * Change the bit with index 60 of a Double
 * @param value The value to set the bit to
 * @return The Double with the bit changed
 */
fun Double.withBit60(value: Boolean): Double = Double.fromBits(this.toRawBits().withBit60(value))

/**
 * Change the bit with index 61 of a Double
 * @param value The value to set the bit to
 * @return The Double with the bit changed
 */
fun Double.withBit61(value: Boolean): Double = Double.fromBits(this.toRawBits().withBit61(value))

/**
 * Change the bit with index 62 of a Double
 * @param value The value to set the bit to
 * @return The Double with the bit changed
 */
fun Double.withBit62(value: Boolean): Double = Double.fromBits(this.toRawBits().withBit62(value))

/**
 * Change the bit with index 63 of a Double
 * @param value The value to set the bit to
 * @return The Double with the bit changed
 */
fun Double.withBit63(value: Boolean): Double = Double.fromBits(this.toRawBits().withBit63(value))

/**
 * Change the n-th bit of a Double
 * @param n The index of the bit to change
 * @param value The value to set the bit to
 * @return The Double with the bit changed
 */
fun Double.withBit(n: Int, value: Boolean): Double = Double.fromBits(this.toRawBits().withBit(n, value))

/**
 * Change the bit with index 0 of an unsigned byte
 * @param value The value to set the bit to
 * @return The unsigned byte with the bit changed
 */
fun UByte.withBit0(value: Boolean): UByte = if (value) (this or 0x1u) else (this and 0xFEu)

/**
 * Change the bit with index 1 of an unsigned byte
 * @param value The value to set the bit to
 * @return The unsigned byte with the bit changed
 */
fun UByte.withBit1(value: Boolean): UByte = if (value) (this or 0x2u) else (this and 0xFDu)

/**
 * Change the bit with index 2 of an unsigned byte
 * @param value The value to set the bit to
 * @return The unsigned byte with the bit changed
 */
fun UByte.withBit2(value: Boolean): UByte = if (value) (this or 0x4u) else (this and 0xFBu)

/**
 * Change the bit with index 3 of an unsigned byte
 * @param value The value to set the bit to
 * @return The unsigned byte with the bit changed
 */
fun UByte.withBit3(value: Boolean): UByte = if (value) (this or 0x8u) else (this and 0xF7u)

/**
 * Change the bit with index 4 of an unsigned byte
 * @param value The value to set the bit to
 * @return The unsigned byte with the bit changed
 */
fun UByte.withBit4(value: Boolean): UByte = if (value) (this or 0x10u) else (this and 0xEFu)

/**
 * Change the bit with index 5 of an unsigned byte
 * @param value The value to set the bit to
 * @return The unsigned byte with the bit changed
 */
fun UByte.withBit5(value: Boolean): UByte = if (value) (this or 0x20u) else (this and 0xDFu)

/**
 * Change the bit with index 6 of an unsigned byte
 * @param value The value to set the bit to
 * @return The unsigned byte with the bit changed
 */
fun UByte.withBit6(value: Boolean): UByte = if (value) (this or 0x40u) else (this and 0xBFu)

/**
 * Change the bit with index 7 of an unsigned byte
 * @param value The value to set the bit to
 * @return The unsigned byte with the bit changed
 */
fun UByte.withBit7(value: Boolean): UByte = if (value) (this or 0x80u) else (this and 0x7Fu)

/**
 * Change the n-th bit of an unsigned byte
 * @param n The index of the bit to change
 * @param value The value to set the bit to
 * @return The unsigned byte with the bit changed
 */
fun UByte.withBit(n: Int, value: Boolean): UByte = this.toByte().withBit(n, value).toUByte()

/**
 * Change the bit with index 0 of an unsigned short
 * @param value The value to set the bit to
 * @return The unsigned short with the bit changed
 */
fun UShort.withBit0(value: Boolean): UShort = if (value) (this or 0x1u) else (this and 0xFFFEu)

/**
 * Change the bit with index 1 of an unsigned short
 * @param value The value to set the bit to
 * @return The unsigned short with the bit changed
 */
fun UShort.withBit1(value: Boolean): UShort = if (value) (this or 0x2u) else (this and 0xFFFDu)

/**
 * Change the bit with index 2 of an unsigned short
 * @param value The value to set the bit to
 * @return The unsigned short with the bit changed
 */
fun UShort.withBit2(value: Boolean): UShort = if (value) (this or 0x4u) else (this and 0xFFFBu)

/**
 * Change the bit with index 3 of an unsigned short
 * @param value The value to set the bit to
 * @return The unsigned short with the bit changed
 */
fun UShort.withBit3(value: Boolean): UShort = if (value) (this or 0x8u) else (this and 0xFFF7u)

/**
 * Change the bit with index 4 of an unsigned short
 * @param value The value to set the bit to
 * @return The unsigned short with the bit changed
 */
fun UShort.withBit4(value: Boolean): UShort = if (value) (this or 0x10u) else (this and 0xFFEFu)

/**
 * Change the bit with index 5 of an unsigned short
 * @param value The value to set the bit to
 * @return The unsigned short with the bit changed
 */
fun UShort.withBit5(value: Boolean): UShort = if (value) (this or 0x20u) else (this and 0xFFDFu)

/**
 * Change the bit with index 6 of an unsigned short
 * @param value The value to set the bit to
 * @return The unsigned short with the bit changed
 */
fun UShort.withBit6(value: Boolean): UShort = if (value) (this or 0x40u) else (this and 0xFFBFu)

/**
 * Change the bit with index 7 of an unsigned short
 * @param value The value to set the bit to
 * @return The unsigned short with the bit changed
 */
fun UShort.withBit7(value: Boolean): UShort = if (value) (this or 0x80u) else (this and 0xFF7Fu)

/**
 * Change the bit with index 8 of an unsigned short
 * @param value The value to set the bit to
 * @return The unsigned short with the bit changed
 */
fun UShort.withBit8(value: Boolean): UShort = if (value) (this or 0x100u) else (this and 0xFEFFu)

/**
 * Change the bit with index 9 of an unsigned short
 * @param value The value to set the bit to
 * @return The unsigned short with the bit changed
 */
fun UShort.withBit9(value: Boolean): UShort = if (value) (this or 0x200u) else (this and 0xFDFFu)

/**
 * Change the bit with index 10 of an unsigned short
 * @param value The value to set the bit to
 * @return The unsigned short with the bit changed
 */
fun UShort.withBit10(value: Boolean): UShort = if (value) (this or 0x400u) else (this and 0xFBFFu)

/**
 * Change the bit with index 11 of an unsigned short
 * @param value The value to set the bit to
 * @return The unsigned short with the bit changed
 */
fun UShort.withBit11(value: Boolean): UShort = if (value) (this or 0x800u) else (this and 0xF7FFu)

/**
 * Change the bit with index 12 of an unsigned short
 * @param value The value to set the bit to
 * @return The unsigned short with the bit changed
 */
fun UShort.withBit12(value: Boolean): UShort = if (value) (this or 0x1000u) else (this and 0xEFFFu)

/**
 * Change the bit with index 13 of an unsigned short
 * @param value The value to set the bit to
 * @return The unsigned short with the bit changed
 */
fun UShort.withBit13(value: Boolean): UShort = if (value) (this or 0x2000u) else (this and 0xDFFFu)

/**
 * Change the bit with index 14 of an unsigned short
 * @param value The value to set the bit to
 * @return The unsigned short with the bit changed
 */
fun UShort.withBit14(value: Boolean): UShort = if (value) (this or 0x4000u) else (this and 0xBFFFu)

/**
 * Change the bit with index 15 of an unsigned short
 * @param value The value to set the bit to
 * @return The unsigned short with the bit changed
 */
fun UShort.withBit15(value: Boolean): UShort = if (value) (this or 0x8000u) else (this and 0x7FFFu)

/**
 * Change the n-th bit of an unsigned short
 * @param n The index of the bit to change
 * @param value The value to set the bit to
 * @return The unsigned short with the bit changed
 */
fun UShort.withBit(n: Int, value: Boolean): UShort = this.toShort().withBit(n, value).toUShort()

/**
 * Change the bit with index 0 of an unsigned int
 * @param value The value to set the bit to
 * @return The unsigned int with the bit changed
 */
fun UInt.withBit0(value: Boolean): UInt = if (value) (this or 0x1u) else (this and 0xFFFFFFFEu)

/**
 * Change the bit with index 1 of an unsigned int
 * @param value The value to set the bit to
 * @return The unsigned int with the bit changed
 */
fun UInt.withBit1(value: Boolean): UInt = if (value) (this or 0x2u) else (this and 0xFFFFFFFDu)

/**
 * Change the bit with index 2 of an unsigned int
 * @param value The value to set the bit to
 * @return The unsigned int with the bit changed
 */
fun UInt.withBit2(value: Boolean): UInt = if (value) (this or 0x4u) else (this and 0xFFFFFFFBu)

/**
 * Change the bit with index 3 of an unsigned int
 * @param value The value to set the bit to
 * @return The unsigned int with the bit changed
 */
fun UInt.withBit3(value: Boolean): UInt = if (value) (this or 0x8u) else (this and 0xFFFFFFF7u)

/**
 * Change the bit with index 4 of an unsigned int
 * @param value The value to set the bit to
 * @return The unsigned int with the bit changed
 */
fun UInt.withBit4(value: Boolean): UInt = if (value) (this or 0x10u) else (this and 0xFFFFFFEFu)

/**
 * Change the bit with index 5 of an unsigned int
 * @param value The value to set the bit to
 * @return The unsigned int with the bit changed
 */
fun UInt.withBit5(value: Boolean): UInt = if (value) (this or 0x20u) else (this and 0xFFFFFFDFu)

/**
 * Change the bit with index 6 of an unsigned int
 * @param value The value to set the bit to
 * @return The unsigned int with the bit changed
 */
fun UInt.withBit6(value: Boolean): UInt = if (value) (this or 0x40u) else (this and 0xFFFFFFBFu)

/**
 * Change the bit with index 7 of an unsigned int
 * @param value The value to set the bit to
 * @return The unsigned int with the bit changed
 */
fun UInt.withBit7(value: Boolean): UInt = if (value) (this or 0x80u) else (this and 0xFFFFFF7Fu)

/**
 * Change the bit with index 8 of an unsigned int
 * @param value The value to set the bit to
 * @return The unsigned int with the bit changed
 */
fun UInt.withBit8(value: Boolean): UInt = if (value) (this or 0x100u) else (this and 0xFFFFFEFFu)

/**
 * Change the bit with index 9 of an unsigned int
 * @param value The value to set the bit to
 * @return The unsigned int with the bit changed
 */
fun UInt.withBit9(value: Boolean): UInt = if (value) (this or 0x200u) else (this and 0xFFFFFDFFu)

/**
 * Change the bit with index 10 of an unsigned int
 * @param value The value to set the bit to
 * @return The unsigned int with the bit changed
 */
fun UInt.withBit10(value: Boolean): UInt = if (value) (this or 0x400u) else (this and 0xFFFFFBFFu)

/**
 * Change the bit with index 11 of an unsigned int
 * @param value The value to set the bit to
 * @return The unsigned int with the bit changed
 */
fun UInt.withBit11(value: Boolean): UInt = if (value) (this or 0x800u) else (this and 0xFFFFF7FFu)

/**
 * Change the bit with index 12 of an unsigned int
 * @param value The value to set the bit to
 * @return The unsigned int with the bit changed
 */
fun UInt.withBit12(value: Boolean): UInt = if (value) (this or 0x1000u) else (this and 0xFFFFEFFFu)

/**
 * Change the bit with index 13 of an unsigned int
 * @param value The value to set the bit to
 * @return The unsigned int with the bit changed
 */
fun UInt.withBit13(value: Boolean): UInt = if (value) (this or 0x2000u) else (this and 0xFFFFDFFFu)

/**
 * Change the bit with index 14 of an unsigned int
 * @param value The value to set the bit to
 * @return The unsigned int with the bit changed
 */
fun UInt.withBit14(value: Boolean): UInt = if (value) (this or 0x4000u) else (this and 0xFFFFBFFFu)

/**
 * Change the bit with index 15 of an unsigned int
 * @param value The value to set the bit to
 * @return The unsigned int with the bit changed
 */
fun UInt.withBit15(value: Boolean): UInt = if (value) (this or 0x8000u) else (this and 0xFFFF7FFFu)

/**
 * Change the bit with index 16 of an unsigned int
 * @param value The value to set the bit to
 * @return The unsigned int with the bit changed
 */
fun UInt.withBit16(value: Boolean): UInt = if (value) (this or 0x10000u) else (this and 0xFFFEFFFFu)

/**
 * Change the bit with index 17 of an unsigned int
 * @param value The value to set the bit to
 * @return The unsigned int with the bit changed
 */
fun UInt.withBit17(value: Boolean): UInt = if (value) (this or 0x20000u) else (this and 0xFFFDFFFFu)

/**
 * Change the bit with index 18 of an unsigned int
 * @param value The value to set the bit to
 * @return The unsigned int with the bit changed
 */
fun UInt.withBit18(value: Boolean): UInt = if (value) (this or 0x40000u) else (this and 0xFFFBFFFFu)

/**
 * Change the bit with index 19 of an unsigned int
 * @param value The value to set the bit to
 * @return The unsigned int with the bit changed
 */
fun UInt.withBit19(value: Boolean): UInt = if (value) (this or 0x80000u) else (this and 0xFFF7FFFFu)

/**
 * Change the bit with index 20 of an unsigned int
 * @param value The value to set the bit to
 * @return The unsigned int with the bit changed
 */
fun UInt.withBit20(value: Boolean): UInt = if (value) (this or 0x100000u) else (this and 0xFFEFFFFFu)

/**
 * Change the bit with index 21 of an unsigned int
 * @param value The value to set the bit to
 * @return The unsigned int with the bit changed
 */
fun UInt.withBit21(value: Boolean): UInt = if (value) (this or 0x200000u) else (this and 0xFFDFFFFFu)

/**
 * Change the bit with index 22 of an unsigned int
 * @param value The value to set the bit to
 * @return The unsigned int with the bit changed
 */
fun UInt.withBit22(value: Boolean): UInt = if (value) (this or 0x400000u) else (this and 0xFFBFFFFFu)

/**
 * Change the bit with index 23 of an unsigned int
 * @param value The value to set the bit to
 * @return The unsigned int with the bit changed
 */
fun UInt.withBit23(value: Boolean): UInt = if (value) (this or 0x800000u) else (this and 0xFF7FFFFFu)

/**
 * Change the bit with index 24 of an unsigned int
 * @param value The value to set the bit to
 * @return The unsigned int with the bit changed
 */
fun UInt.withBit24(value: Boolean): UInt = if (value) (this or 0x1000000u) else (this and 0xFEFFFFFFu)

/**
 * Change the bit with index 25 of an unsigned int
 * @param value The value to set the bit to
 * @return The unsigned int with the bit changed
 */
fun UInt.withBit25(value: Boolean): UInt = if (value) (this or 0x2000000u) else (this and 0xFDFFFFFFu)

/**
 * Change the bit with index 26 of an unsigned int
 * @param value The value to set the bit to
 * @return The unsigned int with the bit changed
 */
fun UInt.withBit26(value: Boolean): UInt = if (value) (this or 0x4000000u) else (this and 0xFBFFFFFFu)

/**
 * Change the bit with index 27 of an unsigned int
 * @param value The value to set the bit to
 * @return The unsigned int with the bit changed
 */
fun UInt.withBit27(value: Boolean): UInt = if (value) (this or 0x8000000u) else (this and 0xF7FFFFFFu)

/**
 * Change the bit with index 28 of an unsigned int
 * @param value The value to set the bit to
 * @return The unsigned int with the bit changed
 */
fun UInt.withBit28(value: Boolean): UInt = if (value) (this or 0x10000000u) else (this and 0xEFFFFFFFu)

/**
 * Change the bit with index 29 of an unsigned int
 * @param value The value to set the bit to
 * @return The unsigned int with the bit changed
 */
fun UInt.withBit29(value: Boolean): UInt = if (value) (this or 0x20000000u) else (this and 0xDFFFFFFFu)

/**
 * Change the bit with index 30 of an unsigned int
 * @param value The value to set the bit to
 * @return The unsigned int with the bit changed
 */
fun UInt.withBit30(value: Boolean): UInt = if (value) (this or 0x40000000u) else (this and 0xBFFFFFFFu)

/**
 * Change the bit with index 31 of an unsigned int
 * @param value The value to set the bit to
 * @return The unsigned int with the bit changed
 */
fun UInt.withBit31(value: Boolean): UInt = if (value) (this or 0x80000000u) else (this and 0x7FFFFFFFu)

/**
 * Change the n-th bit of an unsigned int
 * @param n The index of the bit to change
 * @param value The value to set the bit to
 * @return The unsigned int with the bit changed
 */
fun UInt.withBit(n: Int, value: Boolean): UInt = this.toInt().withBit(n, value).toUInt()

/**
 * Change the bit with index 0 of an unsigned long
 * @param value The value to set the bit to
 * @return The unsigned long with the bit changed
 */
fun ULong.withBit0(value: Boolean): ULong = if (value) (this or 0x1u) else (this and 0xFFFFFFFFFFFFFFFEu)

/**
 * Change the bit with index 1 of an unsigned long
 * @param value The value to set the bit to
 * @return The unsigned long with the bit changed
 */
fun ULong.withBit1(value: Boolean): ULong = if (value) (this or 0x2u) else (this and 0xFFFFFFFFFFFFFFFDu)

/**
 * Change the bit with index 2 of an unsigned long
 * @param value The value to set the bit to
 * @return The unsigned long with the bit changed
 */
fun ULong.withBit2(value: Boolean): ULong = if (value) (this or 0x4u) else (this and 0xFFFFFFFFFFFFFFFBu)

/**
 * Change the bit with index 3 of an unsigned long
 * @param value The value to set the bit to
 * @return The unsigned long with the bit changed
 */
fun ULong.withBit3(value: Boolean): ULong = if (value) (this or 0x8u) else (this and 0xFFFFFFFFFFFFFFF7u)

/**
 * Change the bit with index 4 of an unsigned long
 * @param value The value to set the bit to
 * @return The unsigned long with the bit changed
 */
fun ULong.withBit4(value: Boolean): ULong = if (value) (this or 0x10u) else (this and 0xFFFFFFFFFFFFFFEFu)

/**
 * Change the bit with index 5 of an unsigned long
 * @param value The value to set the bit to
 * @return The unsigned long with the bit changed
 */
fun ULong.withBit5(value: Boolean): ULong = if (value) (this or 0x20u) else (this and 0xFFFFFFFFFFFFFFDFu)

/**
 * Change the bit with index 6 of an unsigned long
 * @param value The value to set the bit to
 * @return The unsigned long with the bit changed
 */
fun ULong.withBit6(value: Boolean): ULong = if (value) (this or 0x40u) else (this and 0xFFFFFFFFFFFFFFBFu)

/**
 * Change the bit with index 7 of an unsigned long
 * @param value The value to set the bit to
 * @return The unsigned long with the bit changed
 */
fun ULong.withBit7(value: Boolean): ULong = if (value) (this or 0x80u) else (this and 0xFFFFFFFFFFFFFF7Fu)

/**
 * Change the bit with index 8 of an unsigned long
 * @param value The value to set the bit to
 * @return The unsigned long with the bit changed
 */
fun ULong.withBit8(value: Boolean): ULong = if (value) (this or 0x100u) else (this and 0xFFFFFFFFFFFFFEFFu)

/**
 * Change the bit with index 9 of an unsigned long
 * @param value The value to set the bit to
 * @return The unsigned long with the bit changed
 */
fun ULong.withBit9(value: Boolean): ULong = if (value) (this or 0x200u) else (this and 0xFFFFFFFFFFFFFDFFu)

/**
 * Change the bit with index 10 of an unsigned long
 * @param value The value to set the bit to
 * @return The unsigned long with the bit changed
 */
fun ULong.withBit10(value: Boolean): ULong = if (value) (this or 0x400u) else (this and 0xFFFFFFFFFFFFFBFFu)

/**
 * Change the bit with index 11 of an unsigned long
 * @param value The value to set the bit to
 * @return The unsigned long with the bit changed
 */
fun ULong.withBit11(value: Boolean): ULong = if (value) (this or 0x800u) else (this and 0xFFFFFFFFFFFFF7FFu)

/**
 * Change the bit with index 12 of an unsigned long
 * @param value The value to set the bit to
 * @return The unsigned long with the bit changed
 */
fun ULong.withBit12(value: Boolean): ULong = if (value) (this or 0x1000u) else (this and 0xFFFFFFFFFFFFEFFFu)

/**
 * Change the bit with index 13 of an unsigned long
 * @param value The value to set the bit to
 * @return The unsigned long with the bit changed
 */
fun ULong.withBit13(value: Boolean): ULong = if (value) (this or 0x2000u) else (this and 0xFFFFFFFFFFFFDFFFu)

/**
 * Change the bit with index 14 of an unsigned long
 * @param value The value to set the bit to
 * @return The unsigned long with the bit changed
 */
fun ULong.withBit14(value: Boolean): ULong = if (value) (this or 0x4000u) else (this and 0xFFFFFFFFFFFFBFFFu)

/**
 * Change the bit with index 15 of an unsigned long
 * @param value The value to set the bit to
 * @return The unsigned long with the bit changed
 */
fun ULong.withBit15(value: Boolean): ULong = if (value) (this or 0x8000u) else (this and 0xFFFFFFFFFFFF7FFFu)

/**
 * Change the bit with index 16 of an unsigned long
 * @param value The value to set the bit to
 * @return The unsigned long with the bit changed
 */
fun ULong.withBit16(value: Boolean): ULong = if (value) (this or 0x10000u) else (this and 0xFFFFFFFFFFFEFFFFu)

/**
 * Change the bit with index 17 of an unsigned long
 * @param value The value to set the bit to
 * @return The unsigned long with the bit changed
 */
fun ULong.withBit17(value: Boolean): ULong = if (value) (this or 0x20000u) else (this and 0xFFFFFFFFFFFDFFFFu)

/**
 * Change the bit with index 18 of an unsigned long
 * @param value The value to set the bit to
 * @return The unsigned long with the bit changed
 */
fun ULong.withBit18(value: Boolean): ULong = if (value) (this or 0x40000u) else (this and 0xFFFFFFFFFFFBFFFFu)

/**
 * Change the bit with index 19 of an unsigned long
 * @param value The value to set the bit to
 * @return The unsigned long with the bit changed
 */
fun ULong.withBit19(value: Boolean): ULong = if (value) (this or 0x80000u) else (this and 0xFFFFFFFFFFF7FFFFu)

/**
 * Change the bit with index 20 of an unsigned long
 * @param value The value to set the bit to
 * @return The unsigned long with the bit changed
 */
fun ULong.withBit20(value: Boolean): ULong = if (value) (this or 0x100000u) else (this and 0xFFFFFFFFFFEFFFFFu)

/**
 * Change the bit with index 21 of an unsigned long
 * @param value The value to set the bit to
 * @return The unsigned long with the bit changed
 */
fun ULong.withBit21(value: Boolean): ULong = if (value) (this or 0x200000u) else (this and 0xFFFFFFFFFFDFFFFFu)

/**
 * Change the bit with index 22 of an unsigned long
 * @param value The value to set the bit to
 * @return The unsigned long with the bit changed
 */
fun ULong.withBit22(value: Boolean): ULong = if (value) (this or 0x400000u) else (this and 0xFFFFFFFFFFBFFFFFu)

/**
 * Change the bit with index 23 of an unsigned long
 * @param value The value to set the bit to
 * @return The unsigned long with the bit changed
 */
fun ULong.withBit23(value: Boolean): ULong = if (value) (this or 0x800000u) else (this and 0xFFFFFFFFFF7FFFFFu)

/**
 * Change the bit with index 24 of an unsigned long
 * @param value The value to set the bit to
 * @return The unsigned long with the bit changed
 */
fun ULong.withBit24(value: Boolean): ULong = if (value) (this or 0x1000000u) else (this and 0xFFFFFFFFFEFFFFFFu)

/**
 * Change the bit with index 25 of an unsigned long
 * @param value The value to set the bit to
 * @return The unsigned long with the bit changed
 */
fun ULong.withBit25(value: Boolean): ULong = if (value) (this or 0x2000000u) else (this and 0xFFFFFFFFFDFFFFFFu)

/**
 * Change the bit with index 26 of an unsigned long
 * @param value The value to set the bit to
 * @return The unsigned long with the bit changed
 */
fun ULong.withBit26(value: Boolean): ULong = if (value) (this or 0x4000000u) else (this and 0xFFFFFFFFFBFFFFFFu)

/**
 * Change the bit with index 27 of an unsigned long
 * @param value The value to set the bit to
 * @return The unsigned long with the bit changed
 */
fun ULong.withBit27(value: Boolean): ULong = if (value) (this or 0x8000000u) else (this and 0xFFFFFFFFF7FFFFFFu)

/**
 * Change the bit with index 28 of an unsigned long
 * @param value The value to set the bit to
 * @return The unsigned long with the bit changed
 */
fun ULong.withBit28(value: Boolean): ULong = if (value) (this or 0x10000000u) else (this and 0xFFFFFFFFEFFFFFFFu)

/**
 * Change the bit with index 29 of an unsigned long
 * @param value The value to set the bit to
 * @return The unsigned long with the bit changed
 */
fun ULong.withBit29(value: Boolean): ULong = if (value) (this or 0x20000000u) else (this and 0xFFFFFFFFDFFFFFFFu)

/**
 * Change the bit with index 30 of an unsigned long
 * @param value The value to set the bit to
 * @return The unsigned long with the bit changed
 */
fun ULong.withBit30(value: Boolean): ULong = if (value) (this or 0x40000000u) else (this and 0xFFFFFFFFBFFFFFFFu)

/**
 * Change the bit with index 31 of an unsigned long
 * @param value The value to set the bit to
 * @return The unsigned long with the bit changed
 */
fun ULong.withBit31(value: Boolean): ULong = if (value) (this or 0x80000000u) else (this and 0xFFFFFFFF7FFFFFFFu)

/**
 * Change the bit with index 32 of an unsigned long
 * @param value The value to set the bit to
 * @return The unsigned long with the bit changed
 */
fun ULong.withBit32(value: Boolean): ULong = if (value) (this or 0x100000000u) else (this and 0xFFFFFFFEFFFFFFFFu)

/**
 * Change the bit with index 33 of an unsigned long
 * @param value The value to set the bit to
 * @return The unsigned long with the bit changed
 */
fun ULong.withBit33(value: Boolean): ULong = if (value) (this or 0x200000000u) else (this and 0xFFFFFFFDFFFFFFFFu)

/**
 * Change the bit with index 34 of an unsigned long
 * @param value The value to set the bit to
 * @return The unsigned long with the bit changed
 */
fun ULong.withBit34(value: Boolean): ULong = if (value) (this or 0x400000000u) else (this and 0xFFFFFFFBFFFFFFFFu)

/**
 * Change the bit with index 35 of an unsigned long
 * @param value The value to set the bit to
 * @return The unsigned long with the bit changed
 */
fun ULong.withBit35(value: Boolean): ULong = if (value) (this or 0x800000000u) else (this and 0xFFFFFFF7FFFFFFFFu)

/**
 * Change the bit with index 36 of an unsigned long
 * @param value The value to set the bit to
 * @return The unsigned long with the bit changed
 */
fun ULong.withBit36(value: Boolean): ULong = if (value) (this or 0x1000000000u) else (this and 0xFFFFFFEFFFFFFFFFu)

/**
 * Change the bit with index 37 of an unsigned long
 * @param value The value to set the bit to
 * @return The unsigned long with the bit changed
 */
fun ULong.withBit37(value: Boolean): ULong = if (value) (this or 0x2000000000u) else (this and 0xFFFFFFDFFFFFFFFFu)

/**
 * Change the bit with index 38 of an unsigned long
 * @param value The value to set the bit to
 * @return The unsigned long with the bit changed
 */
fun ULong.withBit38(value: Boolean): ULong = if (value) (this or 0x4000000000u) else (this and 0xFFFFFFBFFFFFFFFFu)

/**
 * Change the bit with index 39 of an unsigned long
 * @param value The value to set the bit to
 * @return The unsigned long with the bit changed
 */
fun ULong.withBit39(value: Boolean): ULong = if (value) (this or 0x8000000000u) else (this and 0xFFFFFF7FFFFFFFFFu)

/**
 * Change the bit with index 40 of an unsigned long
 * @param value The value to set the bit to
 * @return The unsigned long with the bit changed
 */
fun ULong.withBit40(value: Boolean): ULong = if (value) (this or 0x10000000000u) else (this and 0xFFFFFEFFFFFFFFFFu)

/**
 * Change the bit with index 41 of an unsigned long
 * @param value The value to set the bit to
 * @return The unsigned long with the bit changed
 */
fun ULong.withBit41(value: Boolean): ULong = if (value) (this or 0x20000000000u) else (this and 0xFFFFFDFFFFFFFFFFu)

/**
 * Change the bit with index 42 of an unsigned long
 * @param value The value to set the bit to
 * @return The unsigned long with the bit changed
 */
fun ULong.withBit42(value: Boolean): ULong = if (value) (this or 0x40000000000u) else (this and 0xFFFFFBFFFFFFFFFFu)

/**
 * Change the bit with index 43 of an unsigned long
 * @param value The value to set the bit to
 * @return The unsigned long with the bit changed
 */
fun ULong.withBit43(value: Boolean): ULong = if (value) (this or 0x80000000000u) else (this and 0xFFFFF7FFFFFFFFFFu)

/**
 * Change the bit with index 44 of an unsigned long
 * @param value The value to set the bit to
 * @return The unsigned long with the bit changed
 */
fun ULong.withBit44(value: Boolean): ULong = if (value) (this or 0x100000000000u) else (this and 0xFFFFEFFFFFFFFFFFu)

/**
 * Change the bit with index 45 of an unsigned long
 * @param value The value to set the bit to
 * @return The unsigned long with the bit changed
 */
fun ULong.withBit45(value: Boolean): ULong = if (value) (this or 0x200000000000u) else (this and 0xFFFFDFFFFFFFFFFFu)

/**
 * Change the bit with index 46 of an unsigned long
 * @param value The value to set the bit to
 * @return The unsigned long with the bit changed
 */
fun ULong.withBit46(value: Boolean): ULong = if (value) (this or 0x400000000000u) else (this and 0xFFFFBFFFFFFFFFFFu)

/**
 * Change the bit with index 47 of an unsigned long
 * @param value The value to set the bit to
 * @return The unsigned long with the bit changed
 */
fun ULong.withBit47(value: Boolean): ULong = if (value) (this or 0x800000000000u) else (this and 0xFFFF7FFFFFFFFFFFu)

/**
 * Change the bit with index 48 of an unsigned long
 * @param value The value to set the bit to
 * @return The unsigned long with the bit changed
 */
fun ULong.withBit48(value: Boolean): ULong = if (value) (this or 0x1000000000000u) else (this and 0xFFFEFFFFFFFFFFFFu)

/**
 * Change the bit with index 49 of an unsigned long
 * @param value The value to set the bit to
 * @return The unsigned long with the bit changed
 */
fun ULong.withBit49(value: Boolean): ULong = if (value) (this or 0x2000000000000u) else (this and 0xFFFDFFFFFFFFFFFFu)

/**
 * Change the bit with index 50 of an unsigned long
 * @param value The value to set the bit to
 * @return The unsigned long with the bit changed
 */
fun ULong.withBit50(value: Boolean): ULong = if (value) (this or 0x4000000000000u) else (this and 0xFFFBFFFFFFFFFFFFu)

/**
 * Change the bit with index 51 of an unsigned long
 * @param value The value to set the bit to
 * @return The unsigned long with the bit changed
 */
fun ULong.withBit51(value: Boolean): ULong = if (value) (this or 0x8000000000000u) else (this and 0xFFF7FFFFFFFFFFFFu)

/**
 * Change the bit with index 52 of an unsigned long
 * @param value The value to set the bit to
 * @return The unsigned long with the bit changed
 */
fun ULong.withBit52(value: Boolean): ULong = if (value) (this or 0x10000000000000u) else (this and 0xFFEFFFFFFFFFFFFFu)

/**
 * Change the bit with index 53 of an unsigned long
 * @param value The value to set the bit to
 * @return The unsigned long with the bit changed
 */
fun ULong.withBit53(value: Boolean): ULong = if (value) (this or 0x20000000000000u) else (this and 0xFFDFFFFFFFFFFFFFu)

/**
 * Change the bit with index 54 of an unsigned long
 * @param value The value to set the bit to
 * @return The unsigned long with the bit changed
 */
fun ULong.withBit54(value: Boolean): ULong = if (value) (this or 0x40000000000000u) else (this and 0xFFBFFFFFFFFFFFFFu)

/**
 * Change the bit with index 55 of an unsigned long
 * @param value The value to set the bit to
 * @return The unsigned long with the bit changed
 */
fun ULong.withBit55(value: Boolean): ULong = if (value) (this or 0x80000000000000u) else (this and 0xFF7FFFFFFFFFFFFFu)

/**
 * Change the bit with index 56 of an unsigned long
 * @param value The value to set the bit to
 * @return The unsigned long with the bit changed
 */
fun ULong.withBit56(value: Boolean): ULong = if (value) (this or 0x100000000000000u) else (this and 0xFEFFFFFFFFFFFFFFu)

/**
 * Change the bit with index 57 of an unsigned long
 * @param value The value to set the bit to
 * @return The unsigned long with the bit changed
 */
fun ULong.withBit57(value: Boolean): ULong = if (value) (this or 0x200000000000000u) else (this and 0xFDFFFFFFFFFFFFFFu)

/**
 * Change the bit with index 58 of an unsigned long
 * @param value The value to set the bit to
 * @return The unsigned long with the bit changed
 */
fun ULong.withBit58(value: Boolean): ULong = if (value) (this or 0x400000000000000u) else (this and 0xFBFFFFFFFFFFFFFFu)

/**
 * Change the bit with index 59 of an unsigned long
 * @param value The value to set the bit to
 * @return The unsigned long with the bit changed
 */
fun ULong.withBit59(value: Boolean): ULong = if (value) (this or 0x800000000000000u) else (this and 0xF7FFFFFFFFFFFFFFu)

/**
 * Change the bit with index 60 of an unsigned long
 * @param value The value to set the bit to
 * @return The unsigned long with the bit changed
 */
fun ULong.withBit60(value: Boolean): ULong = if (value) (this or 0x1000000000000000u) else (this and 0xEFFFFFFFFFFFFFFFu)

/**
 * Change the bit with index 61 of an unsigned long
 * @param value The value to set the bit to
 * @return The unsigned long with the bit changed
 */
fun ULong.withBit61(value: Boolean): ULong = if (value) (this or 0x2000000000000000u) else (this and 0xDFFFFFFFFFFFFFFFu)

/**
 * Change the bit with index 62 of an unsigned long
 * @param value The value to set the bit to
 * @return The unsigned long with the bit changed
 */
fun ULong.withBit62(value: Boolean): ULong = if (value) (this or 0x4000000000000000u) else (this and 0xBFFFFFFFFFFFFFFFu)

/**
 * Change the bit with index 63 of an unsigned long
 * @param value The value to set the bit to
 * @return The unsigned long with the bit changed
 */
fun ULong.withBit63(value: Boolean): ULong = if (value) (this or 0x8000000000000000u) else (this and 0x7FFFFFFFFFFFFFFFu)

/**
 * Change the n-th bit of an unsigned long
 * @param n The index of the bit to change
 * @param value The value to set the bit to
 * @return The unsigned long with the bit changed
 */
fun ULong.withBit(n: Int, value: Boolean): ULong = this.toLong().withBit(n, value).toULong()

/**
 * Change the bit with index 0 of a char
 * @param value The value to set the bit to
 * @return The char with the bit changed
 */
fun Char.withBit0(value: Boolean): Char = this.code.withBit0(value).toChar()

/**
 * Change the bit with index 1 of a char
 * @param value The value to set the bit to
 * @return The char with the bit changed
 */
fun Char.withBit1(value: Boolean): Char = this.code.withBit1(value).toChar()

/**
 * Change the bit with index 2 of a char
 * @param value The value to set the bit to
 * @return The char with the bit changed
 */
fun Char.withBit2(value: Boolean): Char = this.code.withBit2(value).toChar()

/**
 * Change the bit with index 3 of a char
 * @param value The value to set the bit to
 * @return The char with the bit changed
 */
fun Char.withBit3(value: Boolean): Char = this.code.withBit3(value).toChar()

/**
 * Change the bit with index 4 of a char
 * @param value The value to set the bit to
 * @return The char with the bit changed
 */
fun Char.withBit4(value: Boolean): Char = this.code.withBit4(value).toChar()

/**
 * Change the bit with index 5 of a char
 * @param value The value to set the bit to
 * @return The char with the bit changed
 */
fun Char.withBit5(value: Boolean): Char = this.code.withBit5(value).toChar()

/**
 * Change the bit with index 6 of a char
 * @param value The value to set the bit to
 * @return The char with the bit changed
 */
fun Char.withBit6(value: Boolean): Char = this.code.withBit6(value).toChar()

/**
 * Change the bit with index 7 of a char
 * @param value The value to set the bit to
 * @return The char with the bit changed
 */
fun Char.withBit7(value: Boolean): Char = this.code.withBit7(value).toChar()

/**
 * Change the bit with index 8 of a char
 * @param value The value to set the bit to
 * @return The char with the bit changed
 */
fun Char.withBit8(value: Boolean): Char = this.code.withBit8(value).toChar()

/**
 * Change the bit with index 9 of a char
 * @param value The value to set the bit to
 * @return The char with the bit changed
 */
fun Char.withBit9(value: Boolean): Char = this.code.withBit9(value).toChar()

/**
 * Change the bit with index 10 of a char
 * @param value The value to set the bit to
 * @return The char with the bit changed
 */
fun Char.withBit10(value: Boolean): Char = this.code.withBit10(value).toChar()

/**
 * Change the bit with index 11 of a char
 * @param value The value to set the bit to
 * @return The char with the bit changed
 */
fun Char.withBit11(value: Boolean): Char = this.code.withBit11(value).toChar()

/**
 * Change the bit with index 12 of a char
 * @param value The value to set the bit to
 * @return The char with the bit changed
 */
fun Char.withBit12(value: Boolean): Char = this.code.withBit12(value).toChar()

/**
 * Change the bit with index 13 of a char
 * @param value The value to set the bit to
 * @return The char with the bit changed
 */
fun Char.withBit13(value: Boolean): Char = this.code.withBit13(value).toChar()

/**
 * Change the bit with index 14 of a char
 * @param value The value to set the bit to
 * @return The char with the bit changed
 */
fun Char.withBit14(value: Boolean): Char = this.code.withBit14(value).toChar()

/**
 * Change the bit with index 15 of a char
 * @param value The value to set the bit to
 * @return The char with the bit changed
 */
fun Char.withBit15(value: Boolean): Char = this.code.withBit15(value).toChar()

/**
 * Change the n-th bit of a char
 * @param n The index of the bit to change
 * @param value The value to set the bit to
 * @return The char with the bit changed
 */
fun Char.withBit(n: Int, value: Boolean): Char = this.code.withBit(n, value).toChar()

/**
 * A bit list of a byte
 */
class ByteBits(

    /**
     * The byte to get the bits from
     */
    val byte: Byte,

) : List<Boolean> {
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

/**
 * A bit list of a short
 */
class ShortBits(

    /**
     * The short to get the bits from
     */
    val short: Short,

) : List<Boolean> {
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

/**
 * A bit list of an int
 */
class IntBits(

    /**
     * The int to get the bits from
     */
    val int: Int,

) : List<Boolean> {
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

/**
 * A bit list of a long
 */
class LongBits(

    /**
     * The long to get the bits from
     */
    val long: Long,

) : List<Boolean> {
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

/**
 * A bit list of a float
 */
class FloatBits(

    /**
     * The float to get the bits from
     */
    val float: Float,

) : List<Boolean> {

    override operator fun get(index: Int) = float.toRawBits().bit(index)

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

val Float.bits: FloatBits get() = FloatBits(this)

/**
 * A bit list of a double
 */
class DoubleBits(

    /**
     * The double to get the bits from
     */
    val double: Double,

) : List<Boolean> {

    override operator fun get(index: Int) = double.toRawBits().bit(index)

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

val Double.bits: DoubleBits get() = DoubleBits(this)

/**
 * A bit list of an unsigned byte
 */
class UByteBits(

    /**
     * The unsigned byte to get the bits from
     */
    val byte: UByte,

) : List<Boolean> {

    override operator fun get(index: Int) = byte and (1u shl index).toUByte() != 0u.toUByte()

    fun toList(): List<Boolean> = BooleanArray(8) { get(it) }.toList()

    override val size: Int get() = 8

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

val UByte.bits: UByteBits get() = UByteBits(this)

/**
 * A bit list of an unsigned short
 */
class UShortBits(

    /**
     * The unsigned short to get the bits from
     */
    val short: UShort,

) : List<Boolean> {

    override operator fun get(index: Int) = short and (1u shl index).toUShort() != 0u.toUShort()

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

val UShort.bits: UShortBits get() = UShortBits(this)

/**
 * A bit list of an unsigned int
 */
class UIntBits(

    /**
     * The unsigned int to get the bits from
     */
    val int: UInt,

) : List<Boolean> {

    override operator fun get(index: Int) = int and (1u shl index) != 0u

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

val UInt.bits: UIntBits get() = UIntBits(this)

/**
 * A bit list of an unsigned long
 */
class ULongBits(

    /**
     * The unsigned long to get the bits from
     */
    val long: ULong,

) : List<Boolean> {

    override operator fun get(index: Int) = long.bit(index)

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

val ULong.bits: ULongBits get() = ULongBits(this)

fun byteFromBits(bits: List<Boolean>): Byte {
    if (bits.size != 8) throw IllegalArgumentException("The list must have 8 elements")
    var byte = 0.toByte()
    for (i in 0 until 8) {
        byte = byte.withBit(i, bits[i])
    }
    return byte
}

fun shortFromBits(bits: List<Boolean>): Short {
    if (bits.size != 16) throw IllegalArgumentException("The list must have 16 elements")
    var short = 0.toShort()
    for (i in 0 until 16) {
        short = short.withBit(i, bits[i])
    }
    return short
}

fun intFromBits(bits: List<Boolean>): Int {
    if (bits.size != 32) throw IllegalArgumentException("The list must have 32 elements")
    var int = 0
    for (i in 0 until 32) {
        int = int.withBit(i, bits[i])
    }
    return int
}

fun longFromBits(bits: List<Boolean>): Long {
    if (bits.size != 64) throw IllegalArgumentException("The list must have 64 elements")
    var long = 0L
    for (i in 0 until 64) {
        long = long.withBit(i, bits[i])
    }
    return long
}

fun floatFromBits(bits: List<Boolean>): Float {
    if (bits.size != 32) throw IllegalArgumentException("The list must have 32 elements")
    var float = 0f
    for (i in 0 until 32) {
        float = float.withBit(i, bits[i])
    }
    return float
}

fun doubleFromBits(bits: List<Boolean>): Double {
    if (bits.size != 64) throw IllegalArgumentException("The list must have 64 elements")
    var double = 0.0
    for (i in 0 until 64) {
        double = double.withBit(i, bits[i])
    }
    return double
}

fun ubyteFromBits(bits: List<Boolean>): UByte {
    if (bits.size != 8) throw IllegalArgumentException("The list must have 8 elements")
    var byte = 0.toUByte()
    for (i in 0 until 8) {
        byte = byte.withBit(i, bits[i])
    }
    return byte
}

fun ushortFromBits(bits: List<Boolean>): UShort {
    if (bits.size != 16) throw IllegalArgumentException("The list must have 16 elements")
    var short = 0.toUShort()
    for (i in 0 until 16) {
        short = short.withBit(i, bits[i])
    }
    return short
}

fun uintFromBits(bits: List<Boolean>): UInt {
    if (bits.size != 32) throw IllegalArgumentException("The list must have 32 elements")
    var int = 0u
    for (i in 0 until 32) {
        int = int.withBit(i, bits[i])
    }
    return int
}

fun ulongFromBits(bits: List<Boolean>): ULong {
    if (bits.size != 64) throw IllegalArgumentException("The list must have 64 elements")
    var long = 0uL
    for (i in 0 until 64) {
        long = long.withBit(i, bits[i])
    }
    return long
}

fun charFromBits(bits: List<Boolean>): Char {
    return ushortFromBits(bits).toInt().toChar()
}
