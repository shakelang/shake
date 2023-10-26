package io.github.shakelang.primitives.bytes

import kotlin.math.abs
import kotlin.math.max
import kotlin.test.Test
import kotlin.test.assertEquals
import kotlin.test.assertFailsWith
import kotlin.test.assertTrue

@OptIn(ExperimentalUnsignedTypes::class)
class ByteArrayTests {

    @Test
    fun testToByte() {
        assertEquals(0x00u.toByte(), byteArrayOf(0x00u).toByte())
        assertEquals(0x01u.toByte(), byteArrayOf(0x01u).toByte())
        assertEquals(0x7Fu.toByte(), byteArrayOf(0x7Fu).toByte())
        assertEquals(0x80u.toByte(), byteArrayOf(0x80u).toByte())
        assertEquals(0xFFu.toByte(), byteArrayOf(0xFFu).toByte())
        assertFailsWith<IllegalArgumentException>("ByteArray must be of size 1, but is 2") {
            byteArrayOf(0x00u, 0x01u).toByte()
        }
        assertFailsWith<IllegalArgumentException>("ByteArray must be of size 1, but is 0") {
            byteArrayOf().toByte()
        }
    }

    @Test
    fun testToShort() {
        assertEquals(0x0000u.toShort(), byteArrayOf(0x00u, 0x00u).toShort())
        assertEquals(0x0001u.toShort(), byteArrayOf(0x00u, 0x01u).toShort())
        assertEquals(0x007Fu.toShort(), byteArrayOf(0x00u, 0x7Fu).toShort())
        assertEquals(0x0080u.toShort(), byteArrayOf(0x00u, 0x80u).toShort())
        assertEquals(0x00FFu.toShort(), byteArrayOf(0x00u, 0xFFu).toShort())
        assertEquals(0x0100u.toShort(), byteArrayOf(0x01u, 0x00u).toShort())
        assertEquals(0x7FFFu.toShort(), byteArrayOf(0x7Fu, 0xFFu).toShort())
        assertEquals(0x8000u.toShort(), byteArrayOf(0x80u, 0x00u).toShort())
        assertEquals(0xFFFFu.toShort(), byteArrayOf(0xFFu, 0xFFu).toShort())
        assertFailsWith<IllegalArgumentException>("ByteArray must be of size 2, but is 1") {
            byteArrayOf(0x00u).toShort()
        }
        assertFailsWith<IllegalArgumentException>("ByteArray must be of size 2, but is 3") {
            byteArrayOf(0x00u, 0x00u, 0x01u).toShort()
        }
    }

    @Test
    fun testToInt() {
        assertEquals(0x00000000u.toInt(), byteArrayOf(0x00u, 0x00u, 0x00u, 0x00u).toInt())
        assertEquals(0x00000001u.toInt(), byteArrayOf(0x00u, 0x00u, 0x00u, 0x01u).toInt())
        assertEquals(0x0000007Fu.toInt(), byteArrayOf(0x00u, 0x00u, 0x00u, 0x7Fu).toInt())
        assertEquals(0x00000080u.toInt(), byteArrayOf(0x00u, 0x00u, 0x00u, 0x80u).toInt())
        assertEquals(0x000000FFu.toInt(), byteArrayOf(0x00u, 0x00u, 0x00u, 0xFFu).toInt())
        assertEquals(0x00000100u.toInt(), byteArrayOf(0x00u, 0x00u, 0x01u, 0x00u).toInt())
        assertEquals(0x00007FFFu.toInt(), byteArrayOf(0x00u, 0x00u, 0x7Fu, 0xFFu).toInt())
        assertEquals(0x00008000u.toInt(), byteArrayOf(0x00u, 0x00u, 0x80u, 0x00u).toInt())
        assertEquals(0x0000FFFFu.toInt(), byteArrayOf(0x00u, 0x00u, 0xFFu, 0xFFu).toInt())
        assertEquals(0x00010000u.toInt(), byteArrayOf(0x00u, 0x01u, 0x00u, 0x00u).toInt())
        assertEquals(0x007FFFFFu.toInt(), byteArrayOf(0x00u, 0x7Fu, 0xFFu, 0xFFu).toInt())
        assertEquals(0x00800000u.toInt(), byteArrayOf(0x00u, 0x80u, 0x00u, 0x00u).toInt())
        assertEquals(0x00FFFFFFu.toInt(), byteArrayOf(0x00u, 0xFFu, 0xFFu, 0xFFu).toInt())
        assertEquals(0x01000000u.toInt(), byteArrayOf(0x01u, 0x00u, 0x00u, 0x00u).toInt())
        assertEquals(0x7FFFFFFFu.toInt(), byteArrayOf(0x7Fu, 0xFFu, 0xFFu, 0xFFu).toInt())
        assertEquals(0x80000000u.toInt(), byteArrayOf(0x80u, 0x00u, 0x00u, 0x00u).toInt())
        assertEquals(
            0xFFFFFFFFu.toInt(),
            byteArrayOf(0xFFu, 0xFFu, 0xFFu, 0xFFu).toInt()
        )
        assertFailsWith<IllegalArgumentException>("ByteArray must be of size 4, but is 1") {
            byteArrayOf(0x00u).toInt()
        }
        assertFailsWith<IllegalArgumentException>("ByteArray must be of size 4, but is 5") {
            byteArrayOf(0x00u, 0x00u, 0x00u, 0x00u, 0x01u).toInt()
        }
    }

    @Test
    fun testToLong() {
        assertEquals(0x0000000000000000UL.toLong(), byteArrayOf(0x00u, 0x00u, 0x00u, 0x00u, 0x00u, 0x00u, 0x00u, 0x00u).toLong())
        assertEquals(0x0000000000000010UL.toLong(), byteArrayOf(0x00u, 0x00u, 0x00u, 0x00u, 0x00u, 0x00u, 0x00u, 0x10u).toLong())
        assertEquals(-1, byteArrayOf(0xFFu, 0xFFu, 0xFFu, 0xFFu, 0xFFu, 0xFFu, 0xFFu, 0xFFu).toLong())
        assertEquals(Long.MIN_VALUE, byteArrayOf(0x80u, 0x00u, 0x00u, 0x00u, 0x00u, 0x00u, 0x00u, 0x00u).toLong())
        assertEquals(Long.MAX_VALUE, byteArrayOf(0x7Fu, 0xFFu, 0xFFu, 0xFFu, 0xFFu, 0xFFu, 0xFFu, 0xFFu).toLong())
        assertFailsWith<IllegalArgumentException>("ByteArray must be of size 8, but is 1") {
            byteArrayOf(0x00u).toLong()
        }
        assertFailsWith<IllegalArgumentException>("ByteArray must be of size 8, but is 9") {
            byteArrayOf(0x00u, 0x00u, 0x00u, 0x00u, 0x00u, 0x00u, 0x00u, 0x00u, 0x01u).toLong()
        }
    }

    @Test
    fun testToFloat() {
        assertCompare(0.0f, byteArrayOf(0x00u, 0x00u, 0x00u, 0x00u).toFloat())
        assertCompare(1.0f, byteArrayOf(0x3Fu, 0x80u, 0x00u, 0x00u).toFloat())
        assertCompare(-1.0f, byteArrayOf(0xBFu, 0x80u, 0x00u, 0x00u).toFloat())
        assertCompare(Float.MIN_VALUE, byteArrayOf(0x00u, 0x00u, 0x00u, 0x01u).toFloat())
        assertCompare(Float.MAX_VALUE, byteArrayOf(0x7Fu, 0x7Fu, 0xFFu, 0xFFu).toFloat())
        assertCompare(Float.NaN, byteArrayOf(0x7Fu, 0xC0u, 0x00u, 0x00u).toFloat())
        assertCompare(Float.NEGATIVE_INFINITY, byteArrayOf(0xFFu, 0x80u, 0x00u, 0x00u).toFloat())
        assertCompare(Float.POSITIVE_INFINITY, byteArrayOf(0x7Fu, 0x80u, 0x00u, 0x00u).toFloat())
        assertFailsWith<IllegalArgumentException>("ByteArray must be of size 4, but is 5") {
            byteArrayOf(0x00u, 0x00u, 0x00u, 0x00u, 0x01u).toFloat()
        }
        assertFailsWith<IllegalArgumentException>("ByteArray must be of size 4, but is 3") {
            byteArrayOf(0x00u, 0x00u, 0x00u).toFloat()
        }
    }

    @Test
    fun testToDouble() {
        assertCompare(0.0, byteArrayOf(0x00u, 0x00u, 0x00u, 0x00u, 0x00u, 0x00u, 0x00u, 0x00u).toDouble())
        assertCompare(1.0, byteArrayOf(0x3Fu, 0xF0u, 0x00u, 0x00u, 0x00u, 0x00u, 0x00u, 0x00u).toDouble())
        assertCompare(-1.0, byteArrayOf(0xBFu, 0xF0u, 0x00u, 0x00u, 0x00u, 0x00u, 0x00u, 0x00u).toDouble())
        assertCompare(Double.MIN_VALUE, byteArrayOf(0x00u, 0x00u, 0x00u, 0x00u, 0x00u, 0x00u, 0x00u, 0x01u).toDouble())
        assertCompare(Double.MAX_VALUE, byteArrayOf(0x7Fu, 0xEFu, 0xFFu, 0xFFu, 0xFFu, 0xFFu, 0xFFu, 0xFFu).toDouble())
        assertCompare(Double.NaN, byteArrayOf(0xFFu, 0xFFu, 0xFFu, 0xFFu, 0xFFu, 0xFFu, 0xFFu, 0xFFu).toDouble())
        assertCompare(Double.NEGATIVE_INFINITY, byteArrayOf(0xffu, 0xf0u, 0x00u, 0x00u, 0x00u, 0x00u, 0x00u, 0x00u).toDouble())
        assertCompare(Double.POSITIVE_INFINITY, byteArrayOf(0x7fu, 0xf0u, 0x00u, 0x00u, 0x00u, 0x00u, 0x00u, 0x00u).toDouble())
        assertFailsWith<IllegalArgumentException>("ByteArray must be of size 8, but is 9") {
            byteArrayOf(0x00u, 0x00u, 0x00u, 0x00u, 0x00u, 0x00u, 0x00u, 0x00u, 0x01u).toDouble()
        }

        assertFailsWith<IllegalArgumentException>("ByteArray must be of size 8, but is 7") {
            byteArrayOf(0x00u, 0x00u, 0x00u, 0x00u, 0x00u, 0x00u, 0x00u).toDouble()
        }
    }

    @Test
    fun testToUnsignedByte() {
        assertEquals(0u, byteArrayOf(0x00u).toUnsignedByte())
        assertEquals(1u, byteArrayOf(0x01u).toUnsignedByte())
        assertEquals(255u, byteArrayOf(0xFFu).toUnsignedByte())
        assertFailsWith<IllegalArgumentException>("ByteArray must be of size 1, but is 2") {
            byteArrayOf(0x00u, 0x01u).toUnsignedByte()
        }
        assertFailsWith<IllegalArgumentException>("ByteArray must be of size 1, but is 0") {
            byteArrayOf().toUnsignedByte()
        }
    }

    @Test
    fun testToUnsignedShort() {
        assertEquals(0u, byteArrayOf(0x00u, 0x00u).toUnsignedShort())
        assertEquals(1u, byteArrayOf(0x00u, 0x01u).toUnsignedShort())
        assertEquals(65535u, byteArrayOf(0xFFu, 0xFFu).toUnsignedShort())
        assertFailsWith<IllegalArgumentException>("ByteArray must be of size 2, but is 3") {
            byteArrayOf(0x00u, 0x00u, 0x01u).toUnsignedShort()
        }
        assertFailsWith<IllegalArgumentException>("ByteArray must be of size 2, but is 0") {
            byteArrayOf().toUnsignedShort()
        }
    }

    @Test
    fun testToUnsignedInt() {
        assertEquals(0u, byteArrayOf(0x00u, 0x00u, 0x00u, 0x00u).toUnsignedInt())
        assertEquals(1u, byteArrayOf(0x00u, 0x00u, 0x00u, 0x01u).toUnsignedInt())
        assertEquals(4294967295u, byteArrayOf(0xFFu, 0xFFu, 0xFFu, 0xFFu).toUnsignedInt())
        assertFailsWith<IllegalArgumentException>("ByteArray must be of size 4, but is 5") {
            byteArrayOf(0x00u, 0x00u, 0x00u, 0x00u, 0x01u).toUnsignedInt()
        }
        assertFailsWith<IllegalArgumentException>("ByteArray must be of size 4, but is 0") {
            byteArrayOf().toUnsignedInt()
        }
    }

    @Test
    fun testToUnsignedLong() {
        assertEquals(0uL, byteArrayOf(0x00u, 0x00u, 0x00u, 0x00u, 0x00u, 0x00u, 0x00u, 0x00u).toUnsignedLong())
        assertEquals(1uL, byteArrayOf(0x00u, 0x00u, 0x00u, 0x00u, 0x00u, 0x00u, 0x00u, 0x01u).toUnsignedLong())
        assertEquals(18446744073709551615uL, byteArrayOf(0xFFu, 0xFFu, 0xFFu, 0xFFu, 0xFFu, 0xFFu, 0xFFu, 0xFFu).toUnsignedLong())
        assertFailsWith<IllegalArgumentException>("ByteArray must be of size 8, but is 9") {
            byteArrayOf(0x00u, 0x00u, 0x00u, 0x00u, 0x00u, 0x00u, 0x00u, 0x00u, 0x01u).toUnsignedLong()
        }
        assertFailsWith<IllegalArgumentException>("ByteArray must be of size 8, but is 0") {
            byteArrayOf().toUnsignedLong()
        }
    }

    @Test
    fun testSetBytes() {
        var bytes = ByteArray(8)
        bytes.setBytes(0, byteArrayOf(0x00u, 0x00u, 0x00u, 0x00u, 0x00u, 0x00u, 0x00u, 0x00u))
        assertEquals(0x00u.toByte(), bytes[0])
        assertEquals(0x00u.toByte(), bytes[1])
        assertEquals(0x00u.toByte(), bytes[2])
        assertEquals(0x00u.toByte(), bytes[3])
        assertEquals(0x00u.toByte(), bytes[4])
        assertEquals(0x00u.toByte(), bytes[5])
        assertEquals(0x00u.toByte(), bytes[6])
        assertEquals(0x00u.toByte(), bytes[7])
        bytes = ByteArray(12)
        bytes.setBytes(2, byteArrayOf(0xFFu, 0xFFu, 0xFFu, 0xFFu, 0xFFu, 0xFFu, 0xFFu, 0xFFu))
        assertEquals(0x00u.toByte(), bytes[0])
        assertEquals(0x00u.toByte(), bytes[1])
        assertEquals(0xFFu.toByte(), bytes[2])
        assertEquals(0xFFu.toByte(), bytes[3])
        assertEquals(0xFFu.toByte(), bytes[4])
        assertEquals(0xFFu.toByte(), bytes[5])
        assertEquals(0xFFu.toByte(), bytes[6])
        assertEquals(0xFFu.toByte(), bytes[7])
        assertEquals(0xFFu.toByte(), bytes[8])
        assertEquals(0xFFu.toByte(), bytes[9])
        assertEquals(0x00u.toByte(), bytes[10])
        assertEquals(0x00u.toByte(), bytes[11])
    }

    @Test
    fun testSetByte() {
        var bytes = ByteArray(8)
        bytes.setByte(0, 0xFFu.toByte())
        assertEquals(0xFFu.toByte(), bytes[0])
        assertEquals(0x00u.toByte(), bytes[1])
        assertEquals(0x00u.toByte(), bytes[2])
        assertEquals(0x00u.toByte(), bytes[3])
        assertEquals(0x00u.toByte(), bytes[4])
        assertEquals(0x00u.toByte(), bytes[5])
        assertEquals(0x00u.toByte(), bytes[6])
        assertEquals(0x00u.toByte(), bytes[7])
        bytes = ByteArray(12)
        bytes.setByte(2, 0xFFu.toByte())
        assertEquals(0x00u.toByte(), bytes[0])
        assertEquals(0x00u.toByte(), bytes[1])
        assertEquals(0xFFu.toByte(), bytes[2])
        assertEquals(0x00u.toByte(), bytes[3])
        assertEquals(0x00u.toByte(), bytes[4])
        assertEquals(0x00u.toByte(), bytes[5])
        assertEquals(0x00u.toByte(), bytes[6])
        assertEquals(0x00u.toByte(), bytes[7])
        assertEquals(0x00u.toByte(), bytes[8])
        assertEquals(0x00u.toByte(), bytes[9])
        assertEquals(0x00u.toByte(), bytes[10])
        assertEquals(0x00u.toByte(), bytes[11])
    }

    @Test
    fun testSetShort() {
        var bytes = ByteArray(8)
        bytes.setShort(0, 0xFFFFu.toShort())
        assertEquals(0xFFu.toByte(), bytes[0])
        assertEquals(0xFFu.toByte(), bytes[1])
        assertEquals(0x00u.toByte(), bytes[2])
        assertEquals(0x00u.toByte(), bytes[3])
        assertEquals(0x00u.toByte(), bytes[4])
        assertEquals(0x00u.toByte(), bytes[5])
        assertEquals(0x00u.toByte(), bytes[6])
        assertEquals(0x00u.toByte(), bytes[7])
        bytes = ByteArray(12)
        bytes.setShort(2, 0xFFFFu.toShort())
        assertEquals(0x00u.toByte(), bytes[0])
        assertEquals(0x00u.toByte(), bytes[1])
        assertEquals(0xFFu.toByte(), bytes[2])
        assertEquals(0xFFu.toByte(), bytes[3])
        assertEquals(0x00u.toByte(), bytes[4])
        assertEquals(0x00u.toByte(), bytes[5])
        assertEquals(0x00u.toByte(), bytes[6])
        assertEquals(0x00u.toByte(), bytes[7])
        assertEquals(0x00u.toByte(), bytes[8])
        assertEquals(0x00u.toByte(), bytes[9])
        assertEquals(0x00u.toByte(), bytes[10])
        assertEquals(0x00u.toByte(), bytes[11])
    }

    @Test
    fun testSetInt() {
        var bytes = ByteArray(8)
        bytes.setInt(0, 0xFFFFFFFFu.toInt())
        assertEquals(0xFFu.toByte(), bytes[0])
        assertEquals(0xFFu.toByte(), bytes[1])
        assertEquals(0xFFu.toByte(), bytes[2])
        assertEquals(0xFFu.toByte(), bytes[3])
        assertEquals(0x00u.toByte(), bytes[4])
        assertEquals(0x00u.toByte(), bytes[5])
        assertEquals(0x00u.toByte(), bytes[6])
        assertEquals(0x00u.toByte(), bytes[7])
        bytes = ByteArray(12)
        bytes.setInt(2, 0xFFFFFFFFu.toInt())
        assertEquals(0x00u.toByte(), bytes[0])
        assertEquals(0x00u.toByte(), bytes[1])
        assertEquals(0xFFu.toByte(), bytes[2])
        assertEquals(0xFFu.toByte(), bytes[3])
        assertEquals(0xFFu.toByte(), bytes[4])
        assertEquals(0xFFu.toByte(), bytes[5])
        assertEquals(0x00u.toByte(), bytes[6])
        assertEquals(0x00u.toByte(), bytes[7])
        assertEquals(0x00u.toByte(), bytes[8])
        assertEquals(0x00u.toByte(), bytes[9])
        assertEquals(0x00u.toByte(), bytes[10])
        assertEquals(0x00u.toByte(), bytes[11])
    }

    @Test
    fun testSetLong() {
        var bytes = ByteArray(8)
        bytes.setLong(0, 0xFFFFFFFFFFFFFFFFuL.toLong())
        assertEquals(0xFFu.toByte(), bytes[0])
        assertEquals(0xFFu.toByte(), bytes[1])
        assertEquals(0xFFu.toByte(), bytes[2])
        assertEquals(0xFFu.toByte(), bytes[3])
        assertEquals(0xFFu.toByte(), bytes[4])
        assertEquals(0xFFu.toByte(), bytes[5])
        assertEquals(0xFFu.toByte(), bytes[6])
        assertEquals(0xFFu.toByte(), bytes[7])
        bytes = ByteArray(12)
        bytes.setLong(2, 0xFFFFFFFFFFFFFFFFuL.toLong())
        assertEquals(0x00u.toByte(), bytes[0])
        assertEquals(0x00u.toByte(), bytes[1])
        assertEquals(0xFFu.toByte(), bytes[2])
        assertEquals(0xFFu.toByte(), bytes[3])
        assertEquals(0xFFu.toByte(), bytes[4])
        assertEquals(0xFFu.toByte(), bytes[5])
        assertEquals(0xFFu.toByte(), bytes[6])
        assertEquals(0xFFu.toByte(), bytes[7])
        assertEquals(0xFFu.toByte(), bytes[8])
        assertEquals(0xFFu.toByte(), bytes[9])
        assertEquals(0x00u.toByte(), bytes[10])
        assertEquals(0x00u.toByte(), bytes[11])
    }

    @Test
    fun testSetFloat() {
        var bytes = ByteArray(8)
        bytes.setFloat(0, Float.fromBits(0x3f99999au.toInt()))
        assertEquals(0x3Fu.toByte(), bytes[0])
        assertEquals(0x99u.toByte(), bytes[1])
        assertEquals(0x99u.toByte(), bytes[2])
        assertEquals(0x9au.toByte(), bytes[3])
        assertEquals(0x00u.toByte(), bytes[4])
        assertEquals(0x00u.toByte(), bytes[5])
        assertEquals(0x00u.toByte(), bytes[6])
        assertEquals(0x00u.toByte(), bytes[7])
        bytes = ByteArray(12)
        bytes.setFloat(2, Float.fromBits(0x3f99999au.toInt()))
        assertEquals(0x00u.toByte(), bytes[0])
        assertEquals(0x00u.toByte(), bytes[1])
        assertEquals(0x3Fu.toByte(), bytes[2])
        assertEquals(0x99u.toByte(), bytes[3])
        assertEquals(0x99u.toByte(), bytes[4])
        assertEquals(0x9au.toByte(), bytes[5])
        assertEquals(0x00u.toByte(), bytes[6])
        assertEquals(0x00u.toByte(), bytes[7])
        assertEquals(0x00u.toByte(), bytes[8])
        assertEquals(0x00u.toByte(), bytes[9])
        assertEquals(0x00u.toByte(), bytes[10])
        assertEquals(0x00u.toByte(), bytes[11])
    }

    @Test
    fun testSetDouble() {
        var bytes = ByteArray(8)
        bytes.setDouble(0, Double.fromBits(0x3ff3333333333333uL.toLong()))
        assertEquals(0x3Fu.toByte(), bytes[0])
        assertEquals(0xF3u.toByte(), bytes[1])
        assertEquals(0x33u.toByte(), bytes[2])
        assertEquals(0x33u.toByte(), bytes[3])
        assertEquals(0x33u.toByte(), bytes[4])
        assertEquals(0x33u.toByte(), bytes[5])
        assertEquals(0x33u.toByte(), bytes[6])
        assertEquals(0x33u.toByte(), bytes[7])
        bytes = ByteArray(12)
        bytes.setDouble(2, Double.fromBits(0x3ff3333333333333uL.toLong()))
        assertEquals(0x00u.toByte(), bytes[0])
        assertEquals(0x00u.toByte(), bytes[1])
        assertEquals(0x3Fu.toByte(), bytes[2])
        assertEquals(0xF3u.toByte(), bytes[3])
        assertEquals(0x33u.toByte(), bytes[4])
        assertEquals(0x33u.toByte(), bytes[5])
        assertEquals(0x33u.toByte(), bytes[6])
        assertEquals(0x33u.toByte(), bytes[7])
        assertEquals(0x33u.toByte(), bytes[8])
        assertEquals(0x33u.toByte(), bytes[9])
        assertEquals(0x00u.toByte(), bytes[10])
        assertEquals(0x00u.toByte(), bytes[11])
    }

    @Test
    fun testSetUnsignedByte() {
        var bytes = ByteArray(8)
        bytes.setUnsignedByte(0, 0xFFu)
        assertEquals(0xFFu.toByte(), bytes[0])
        assertEquals(0x00u.toByte(), bytes[1])
        assertEquals(0x00u.toByte(), bytes[2])
        assertEquals(0x00u.toByte(), bytes[3])
        assertEquals(0x00u.toByte(), bytes[4])
        assertEquals(0x00u.toByte(), bytes[5])
        assertEquals(0x00u.toByte(), bytes[6])
        assertEquals(0x00u.toByte(), bytes[7])
        bytes = ByteArray(12)
        bytes.setUnsignedByte(2, 0xFFu)
        assertEquals(0x00u.toByte(), bytes[0])
        assertEquals(0x00u.toByte(), bytes[1])
        assertEquals(0xFFu.toByte(), bytes[2])
        assertEquals(0x00u.toByte(), bytes[3])
        assertEquals(0x00u.toByte(), bytes[4])
        assertEquals(0x00u.toByte(), bytes[5])
        assertEquals(0x00u.toByte(), bytes[6])
        assertEquals(0x00u.toByte(), bytes[7])
        assertEquals(0x00u.toByte(), bytes[8])
        assertEquals(0x00u.toByte(), bytes[9])
        assertEquals(0x00u.toByte(), bytes[10])
        assertEquals(0x00u.toByte(), bytes[11])
    }

    @Test
    fun testSetUnsignedShort() {
        var bytes = ByteArray(8)
        bytes.setUnsignedShort(0, 0xFFFFu)
        assertEquals(0xFFu.toByte(), bytes[0])
        assertEquals(0xFFu.toByte(), bytes[1])
        assertEquals(0x00u.toByte(), bytes[2])
        assertEquals(0x00u.toByte(), bytes[3])
        assertEquals(0x00u.toByte(), bytes[4])
        assertEquals(0x00u.toByte(), bytes[5])
        assertEquals(0x00u.toByte(), bytes[6])
        assertEquals(0x00u.toByte(), bytes[7])
        bytes = ByteArray(12)
        bytes.setUnsignedShort(2, 0xFFFFu)
        assertEquals(0x00u.toByte(), bytes[0])
        assertEquals(0x00u.toByte(), bytes[1])
        assertEquals(0xFFu.toByte(), bytes[2])
        assertEquals(0xFFu.toByte(), bytes[3])
        assertEquals(0x00u.toByte(), bytes[4])
        assertEquals(0x00u.toByte(), bytes[5])
        assertEquals(0x00u.toByte(), bytes[6])
        assertEquals(0x00u.toByte(), bytes[7])
        assertEquals(0x00u.toByte(), bytes[8])
        assertEquals(0x00u.toByte(), bytes[9])
        assertEquals(0x00u.toByte(), bytes[10])
        assertEquals(0x00u.toByte(), bytes[11])
    }

    @Test
    fun testSetUnsignedInt() {
        var bytes = ByteArray(8)
        bytes.setUnsignedInt(0, 0xFFFFFFFFu)
        assertEquals(0xFFu.toByte(), bytes[0])
        assertEquals(0xFFu.toByte(), bytes[1])
        assertEquals(0xFFu.toByte(), bytes[2])
        assertEquals(0xFFu.toByte(), bytes[3])
        assertEquals(0x00u.toByte(), bytes[4])
        assertEquals(0x00u.toByte(), bytes[5])
        assertEquals(0x00u.toByte(), bytes[6])
        assertEquals(0x00u.toByte(), bytes[7])
        bytes = ByteArray(12)
        bytes.setUnsignedInt(2, 0xFFFFFFFFu)
        assertEquals(0x00u.toByte(), bytes[0])
        assertEquals(0x00u.toByte(), bytes[1])
        assertEquals(0xFFu.toByte(), bytes[2])
        assertEquals(0xFFu.toByte(), bytes[3])
        assertEquals(0xFFu.toByte(), bytes[4])
        assertEquals(0xFFu.toByte(), bytes[5])
        assertEquals(0x00u.toByte(), bytes[6])
        assertEquals(0x00u.toByte(), bytes[7])
        assertEquals(0x00u.toByte(), bytes[8])
        assertEquals(0x00u.toByte(), bytes[9])
        assertEquals(0x00u.toByte(), bytes[10])
        assertEquals(0x00u.toByte(), bytes[11])
    }

    @Test
    fun testSetUnsignedLong() {
        var bytes = ByteArray(8)
        bytes.setUnsignedLong(0, 0xFFFFFFFFFFFFFFFFu)
        assertEquals(0xFFu.toByte(), bytes[0])
        assertEquals(0xFFu.toByte(), bytes[1])
        assertEquals(0xFFu.toByte(), bytes[2])
        assertEquals(0xFFu.toByte(), bytes[3])
        assertEquals(0xFFu.toByte(), bytes[4])
        assertEquals(0xFFu.toByte(), bytes[5])
        assertEquals(0xFFu.toByte(), bytes[6])
        assertEquals(0xFFu.toByte(), bytes[7])
        bytes = ByteArray(12)
        bytes.setUnsignedLong(2, 0xFFFFFFFFFFFFFFFFu)
        assertEquals(0x00u.toByte(), bytes[0])
        assertEquals(0x00u.toByte(), bytes[1])
        assertEquals(0xFFu.toByte(), bytes[2])
        assertEquals(0xFFu.toByte(), bytes[3])
        assertEquals(0xFFu.toByte(), bytes[4])
        assertEquals(0xFFu.toByte(), bytes[5])
        assertEquals(0xFFu.toByte(), bytes[6])
        assertEquals(0xFFu.toByte(), bytes[7])
        assertEquals(0xFFu.toByte(), bytes[8])
        assertEquals(0xFFu.toByte(), bytes[9])
        assertEquals(0x00u.toByte(), bytes[10])
        assertEquals(0x00u.toByte(), bytes[11])
    }

    @Test
    fun testGetByte() {
        val bytes = ByteArray(8)
        bytes[0] = 0xFFu.toByte()
        bytes[1] = 0xFFu.toByte()
        bytes[2] = 0xFFu.toByte()
        bytes[3] = 0xFFu.toByte()
        bytes[4] = 0x00u.toByte()
        bytes[5] = 0x00u.toByte()
        bytes[6] = 0x00u.toByte()
        bytes[7] = 0x00u.toByte()
        assertEquals(0xFFu.toByte(), bytes.getByte(0))
        assertEquals(0xFFu.toByte(), bytes.getByte(1))
        assertEquals(0xFFu.toByte(), bytes.getByte(2))
        assertEquals(0xFFu.toByte(), bytes.getByte(3))
        assertEquals(0x00u.toByte(), bytes.getByte(4))
        assertEquals(0x00u.toByte(), bytes.getByte(5))
        assertEquals(0x00u.toByte(), bytes.getByte(6))
        assertEquals(0x00u.toByte(), bytes.getByte(7))
    }

    @Test
    fun testGetUnsignedByte() {
        val bytes = ByteArray(8)
        bytes[0] = 0xFFu.toByte()
        bytes[1] = 0xFFu.toByte()
        bytes[2] = 0xFFu.toByte()
        bytes[3] = 0xFFu.toByte()
        bytes[4] = 0x00u.toByte()
        bytes[5] = 0x00u.toByte()
        bytes[6] = 0x00u.toByte()
        bytes[7] = 0x00u.toByte()
        assertEquals(0xFFu, bytes.getUnsignedByte(0))
        assertEquals(0xFFu, bytes.getUnsignedByte(1))
        assertEquals(0xFFu, bytes.getUnsignedByte(2))
        assertEquals(0xFFu, bytes.getUnsignedByte(3))
        assertEquals(0x00u, bytes.getUnsignedByte(4))
        assertEquals(0x00u, bytes.getUnsignedByte(5))
        assertEquals(0x00u, bytes.getUnsignedByte(6))
        assertEquals(0x00u, bytes.getUnsignedByte(7))
    }

    @Test
    fun testGetShort() {
        val bytes = ByteArray(8)
        bytes[0] = 0x00u.toByte()
        bytes[1] = 0x11u.toByte()
        bytes[2] = 0x22u.toByte()
        bytes[3] = 0x33u.toByte()
        bytes[4] = 0x44u.toByte()
        bytes[5] = 0x55u.toByte()
        bytes[6] = 0x66u.toByte()
        bytes[7] = 0x77u.toByte()
        assertEquals(0x0011u.toShort(), bytes.getShort(0))
        assertEquals(0x2233u.toShort(), bytes.getShort(2))
        assertEquals(0x4455u.toShort(), bytes.getShort(4))
        assertEquals(0x6677u.toShort(), bytes.getShort(6))
    }

    @Test
    fun testGetUnsignedShort() {
        val bytes = ByteArray(8)
        bytes[0] = 0x00u.toByte()
        bytes[1] = 0x11u.toByte()
        bytes[2] = 0x22u.toByte()
        bytes[3] = 0x33u.toByte()
        bytes[4] = 0x44u.toByte()
        bytes[5] = 0x55u.toByte()
        bytes[6] = 0x66u.toByte()
        bytes[7] = 0x77u.toByte()
        assertEquals(0x0011u, bytes.getUnsignedShort(0))
        assertEquals(0x2233u, bytes.getUnsignedShort(2))
        assertEquals(0x4455u, bytes.getUnsignedShort(4))
        assertEquals(0x6677u, bytes.getUnsignedShort(6))
    }

    @Test
    fun testGetInt() {
        val bytes = ByteArray(8)
        bytes[0] = 0x00u.toByte()
        bytes[1] = 0x11u.toByte()
        bytes[2] = 0x22u.toByte()
        bytes[3] = 0x33u.toByte()
        bytes[4] = 0x44u.toByte()
        bytes[5] = 0x55u.toByte()
        bytes[6] = 0x66u.toByte()
        bytes[7] = 0x77u.toByte()
        assertEquals(0x00112233u.toInt(), bytes.getInt(0))
        assertEquals(0x44556677u.toInt(), bytes.getInt(4))
    }

    @Test
    fun testGetUnsignedInt() {
        val bytes = ByteArray(8)
        bytes[0] = 0x00u.toByte()
        bytes[1] = 0x11u.toByte()
        bytes[2] = 0x22u.toByte()
        bytes[3] = 0x33u.toByte()
        bytes[4] = 0x44u.toByte()
        bytes[5] = 0x55u.toByte()
        bytes[6] = 0x66u.toByte()
        bytes[7] = 0x77u.toByte()
        assertEquals(0x00112233u, bytes.getUnsignedInt(0))
        assertEquals(0x44556677u, bytes.getUnsignedInt(4))
    }

    @Test
    fun testGetLong() {
        val bytes = ByteArray(16)
        bytes[0] = 0x00u.toByte()
        bytes[1] = 0x11u.toByte()
        bytes[2] = 0x22u.toByte()
        bytes[3] = 0x33u.toByte()
        bytes[4] = 0x44u.toByte()
        bytes[5] = 0x55u.toByte()
        bytes[6] = 0x66u.toByte()
        bytes[7] = 0x77u.toByte()
        bytes[8] = 0x88u.toByte()
        bytes[9] = 0x99u.toByte()
        bytes[10] = 0xAAu.toByte()
        bytes[11] = 0xBBu.toByte()
        bytes[12] = 0xCCu.toByte()
        bytes[13] = 0xDDu.toByte()
        bytes[14] = 0xEEu.toByte()
        bytes[15] = 0xFFu.toByte()
        assertEquals(0x0011223344556677uL.toLong(), bytes.getLong(0))
        assertEquals(0x8899AABBCCDDEEFFuL.toLong(), bytes.getLong(8))
    }

    @Test
    fun testGetUnsignedLong() {
        val bytes = ByteArray(16)
        bytes[0] = 0x00u.toByte()
        bytes[1] = 0x11u.toByte()
        bytes[2] = 0x22u.toByte()
        bytes[3] = 0x33u.toByte()
        bytes[4] = 0x44u.toByte()
        bytes[5] = 0x55u.toByte()
        bytes[6] = 0x66u.toByte()
        bytes[7] = 0x77u.toByte()
        bytes[8] = 0x88u.toByte()
        bytes[9] = 0x99u.toByte()
        bytes[10] = 0xAAu.toByte()
        bytes[11] = 0xBBu.toByte()
        bytes[12] = 0xCCu.toByte()
        bytes[13] = 0xDDu.toByte()
        bytes[14] = 0xEEu.toByte()
        bytes[15] = 0xFFu.toByte()
        assertEquals(0x0011223344556677uL, bytes.getUnsignedLong(0))
        assertEquals(0x8899AABBCCDDEEFFuL, bytes.getUnsignedLong(8))
    }

    @Test
    fun testGetFloat() {
        val bytes = ByteArray(8)
        bytes[0] = 0x00u.toByte()
        bytes[1] = 0x11u.toByte()
        bytes[2] = 0x22u.toByte()
        bytes[3] = 0x33u.toByte()
        bytes[4] = 0x44u.toByte()
        bytes[5] = 0x55u.toByte()
        bytes[6] = 0x66u.toByte()
        bytes[7] = 0x77u.toByte()
        assertEquals(Float.fromBits(0x00112233u.toInt()), bytes.getFloat(0))
        assertEquals(Float.fromBits(0x44556677u.toInt()), bytes.getFloat(4))
    }

    @Test
    fun testGetDouble() {
        val bytes = ByteArray(16)
        bytes[0] = 0x00u.toByte()
        bytes[1] = 0x11u.toByte()
        bytes[2] = 0x22u.toByte()
        bytes[3] = 0x33u.toByte()
        bytes[4] = 0x44u.toByte()
        bytes[5] = 0x55u.toByte()
        bytes[6] = 0x66u.toByte()
        bytes[7] = 0x77u.toByte()
        bytes[8] = 0x88u.toByte()
        bytes[9] = 0x99u.toByte()
        bytes[10] = 0xAAu.toByte()
        bytes[11] = 0xBBu.toByte()
        bytes[12] = 0xCCu.toByte()
        bytes[13] = 0xDDu.toByte()
        bytes[14] = 0xEEu.toByte()
        bytes[15] = 0xFFu.toByte()
        assertEquals(Double.fromBits(0x0011223344556677uL.toLong()), bytes.getDouble(0))
        assertEquals(Double.fromBits(0x8899AABBCCDDEEFFuL.toLong()), bytes.getDouble(8))
    }

    @Test
    fun testGetBytes() {
        val bytes = ByteArray(16)
        bytes[0] = 0x00u.toByte()
        bytes[1] = 0x11u.toByte()
        bytes[2] = 0x22u.toByte()
        bytes[3] = 0x33u.toByte()
        bytes[4] = 0x44u.toByte()
        bytes[5] = 0x55u.toByte()
        bytes[6] = 0x66u.toByte()
        bytes[7] = 0x77u.toByte()
        bytes[8] = 0x88u.toByte()
        bytes[9] = 0x99u.toByte()
        bytes[10] = 0xAAu.toByte()
        bytes[11] = 0xBBu.toByte()
        bytes[12] = 0xCCu.toByte()
        bytes[13] = 0xDDu.toByte()
        bytes[14] = 0xEEu.toByte()
        bytes[15] = 0xFFu.toByte()
        assertEquals(byteArrayOf(0x00u, 0x11u, 0x22u, 0x33u, 0x44u, 0x55u, 0x66u, 0x77u, 0x88u, 0x99u,
            0xAAu, 0xBBu, 0xCCu, 0xDDu, 0xEEu, 0xFFu).toList(), bytes.getBytes(0, 16).toList())
        assertEquals(byteArrayOf(0x11u, 0x22u).toList(), bytes.getBytes(1, 2).toList())
    }

    @Test
    fun testToHexString() {
        val bytes = ByteArray(16)
        bytes[0] = 0x00u.toByte()
        bytes[1] = 0x11u.toByte()
        bytes[2] = 0x22u.toByte()
        bytes[3] = 0x33u.toByte()
        bytes[4] = 0x44u.toByte()
        bytes[5] = 0x55u.toByte()
        bytes[6] = 0x66u.toByte()
        bytes[7] = 0x77u.toByte()
        bytes[8] = 0x88u.toByte()
        bytes[9] = 0x99u.toByte()
        bytes[10] = 0xAAu.toByte()
        bytes[11] = 0xBBu.toByte()
        bytes[12] = 0xCCu.toByte()
        bytes[13] = 0xDDu.toByte()
        bytes[14] = 0xEEu.toByte()
        bytes[15] = 0xFFu.toByte()
        assertEquals("00112233445566778899aabbccddeeff", bytes.toHexString())
    }

    @Test
    fun testToUtf8String() {
        val bytes = "hello world".toCharArray().map { it.code.toUByte().toByte() }.toByteArray()
        assertEquals("hello world", bytes.toUtf8String())
    }

    @Test
    fun testByteArrayOf() {
        val byteArray = byteArrayOf(0x00u, 0xFFu)
        assertEquals(0x00, byteArray[0])
        assertEquals(-1, byteArray[1])
    }
}

fun assertCompare(expected: Float, actual: Float) {
    val delta = max(abs(expected / 1000), 0.001f)
    assertTrue(compare(expected, actual, delta), "Comparison failed, expected $expected, but got $actual (comparison delta: $delta)")
}
fun assertCompare(expected: Double, actual: Double) {
    val delta = max(abs(expected / 1000000), 0.000001)
    assertTrue(compare(expected, actual, delta), "Comparison failed, expected $expected, but got $actual (comparison delta: $delta)")
}

fun compare(f0: Float, f1: Float, delta: Float): Boolean
    = (f0.isNaN() && f1.isNaN())
        || (f0.isInfinite() && f1.isInfinite() && ((f0 > 0 && f1 > 0) || (f0 < 0 && f1 < 0)))
        || abs(f0 - f1) <= delta
fun compare(d0: Double, d1: Double, delta: Double): Boolean = (d0.isNaN() && d1.isNaN())
        || (d0.isInfinite() && d1.isInfinite() && ((d0 > 0 && d1 > 0) || (d0 < 0 && d1 < 0)))
        || abs(d0 - d1) <= delta