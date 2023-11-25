@file:OptIn(ExperimentalUnsignedTypes::class)

package com.shakelang.shake.util.primitives.bytes

import io.kotest.assertions.throwables.shouldThrowWithMessage
import io.kotest.core.spec.style.FreeSpec
import io.kotest.matchers.shouldBe
import kotlin.math.abs
import kotlin.math.max

@Suppress("unused")
@OptIn(ExperimentalUnsignedTypes::class)
class ByteArrayTests : FreeSpec({

    "toByte" {
        byteArrayOf(0x00u).toByte() shouldBe 0x00u.toByte()
        byteArrayOf(0x01u).toByte() shouldBe 0x01u.toByte()
        byteArrayOf(0x7Fu).toByte() shouldBe 0x7Fu.toByte()
        byteArrayOf(0x80u).toByte() shouldBe 0x80u.toByte()
        byteArrayOf(0xFFu).toByte() shouldBe 0xFFu.toByte()
        shouldThrowWithMessage<IllegalArgumentException>("ByteArray must be of size 1, but is 2") {
            byteArrayOf(0x00u, 0x01u).toByte()
        }
        shouldThrowWithMessage<IllegalArgumentException>("ByteArray must be of size 1, but is 0") {
            byteArrayOf().toByte()
        }
    }

    "toShort" {
        byteArrayOf(0x00u, 0x00u).toShort() shouldBe 0x0000u.toShort()
        byteArrayOf(0x00u, 0x01u).toShort() shouldBe 0x0001u.toShort()
        byteArrayOf(0x00u, 0x7Fu).toShort() shouldBe 0x007Fu.toShort()
        byteArrayOf(0x00u, 0x80u).toShort() shouldBe 0x0080u.toShort()
        byteArrayOf(0x00u, 0xFFu).toShort() shouldBe 0x00FFu.toShort()
        byteArrayOf(0x01u, 0x00u).toShort() shouldBe 0x0100u.toShort()
        byteArrayOf(0x7Fu, 0xFFu).toShort() shouldBe 0x7FFFu.toShort()
        byteArrayOf(0x80u, 0x00u).toShort() shouldBe 0x8000u.toShort()
        byteArrayOf(0xFFu, 0xFFu).toShort() shouldBe 0xFFFFu.toShort()
        shouldThrowWithMessage<IllegalArgumentException>("ByteArray must be of size 2, but is 1") {
            byteArrayOf(0x00u).toShort()
        }
        shouldThrowWithMessage<IllegalArgumentException>("ByteArray must be of size 2, but is 3") {
            byteArrayOf(0x00u, 0x00u, 0x01u).toShort()
        }
    }

    "toInt" {
        byteArrayOf(0x00u, 0x00u, 0x00u, 0x00u).toInt() shouldBe 0x00000000u.toInt()
        byteArrayOf(0x00u, 0x00u, 0x00u, 0x01u).toInt() shouldBe 0x00000001u.toInt()
        byteArrayOf(0x00u, 0x00u, 0x00u, 0x7Fu).toInt() shouldBe 0x0000007Fu.toInt()
        byteArrayOf(0x00u, 0x00u, 0x00u, 0x80u).toInt() shouldBe 0x00000080u.toInt()
        byteArrayOf(0x00u, 0x00u, 0x00u, 0xFFu).toInt() shouldBe 0x000000FFu.toInt()
        byteArrayOf(0x00u, 0x00u, 0x01u, 0x00u).toInt() shouldBe 0x00000100u.toInt()
        byteArrayOf(0x00u, 0x00u, 0x7Fu, 0xFFu).toInt() shouldBe 0x00007FFFu.toInt()
        byteArrayOf(0x00u, 0x00u, 0x80u, 0x00u).toInt() shouldBe 0x00008000u.toInt()
        byteArrayOf(0x00u, 0x00u, 0xFFu, 0xFFu).toInt() shouldBe 0x0000FFFFu.toInt()
        byteArrayOf(0x00u, 0x01u, 0x00u, 0x00u).toInt() shouldBe 0x00010000u.toInt()
        byteArrayOf(0x00u, 0x7Fu, 0xFFu, 0xFFu).toInt() shouldBe 0x007FFFFFu.toInt()
        byteArrayOf(0x00u, 0x80u, 0x00u, 0x00u).toInt() shouldBe 0x00800000u.toInt()
        byteArrayOf(0x00u, 0xFFu, 0xFFu, 0xFFu).toInt() shouldBe 0x00FFFFFFu.toInt()
        byteArrayOf(0x01u, 0x00u, 0x00u, 0x00u).toInt() shouldBe 0x01000000u.toInt()
        byteArrayOf(0x7Fu, 0xFFu, 0xFFu, 0xFFu).toInt() shouldBe 0x7FFFFFFFu.toInt()
        byteArrayOf(0x80u, 0x00u, 0x00u, 0x00u).toInt() shouldBe 0x80000000u.toInt()
        byteArrayOf(0xFFu, 0xFFu, 0xFFu, 0xFFu).toInt() shouldBe 0xFFFFFFFFu.toInt()
        shouldThrowWithMessage<IllegalArgumentException>("ByteArray must be of size 4, but is 1") {
            byteArrayOf(0x00u).toInt()
        }
        shouldThrowWithMessage<IllegalArgumentException>("ByteArray must be of size 4, but is 5") {
            byteArrayOf(0x00u, 0x00u, 0x00u, 0x00u, 0x01u).toInt()
        }
    }

    "toLong" {
        byteArrayOf(0x00u, 0x00u, 0x00u, 0x00u, 0x00u, 0x00u, 0x00u, 0x00u).toLong() shouldBe 0x0000000000000000uL.toLong()
        byteArrayOf(0x00u, 0x00u, 0x00u, 0x00u, 0x00u, 0x00u, 0x00u, 0x10u).toLong() shouldBe 0x0000000000000010uL.toLong()
        byteArrayOf(0xFFu, 0xFFu, 0xFFu, 0xFFu, 0xFFu, 0xFFu, 0xFFu, 0xFFu).toLong() shouldBe -1L
        byteArrayOf(0x80u, 0x00u, 0x00u, 0x00u, 0x00u, 0x00u, 0x00u, 0x00u).toLong() shouldBe Long.MIN_VALUE
        byteArrayOf(0x7Fu, 0xFFu, 0xFFu, 0xFFu, 0xFFu, 0xFFu, 0xFFu, 0xFFu).toLong() shouldBe Long.MAX_VALUE
        shouldThrowWithMessage<IllegalArgumentException>("ByteArray must be of size 8, but is 1") {
            byteArrayOf(0x00u).toLong()
        }
        shouldThrowWithMessage<IllegalArgumentException>("ByteArray must be of size 8, but is 9") {
            byteArrayOf(0x00u, 0x00u, 0x00u, 0x00u, 0x00u, 0x00u, 0x00u, 0x00u, 0x01u).toLong()
        }
    }

    "toFloat" {
        assertCompare(0.0f, byteArrayOf(0x00u, 0x00u, 0x00u, 0x00u).toFloat())
        assertCompare(1.0f, byteArrayOf(0x3Fu, 0x80u, 0x00u, 0x00u).toFloat())
        assertCompare(-1.0f, byteArrayOf(0xBFu, 0x80u, 0x00u, 0x00u).toFloat())
        assertCompare(Float.MIN_VALUE, byteArrayOf(0x00u, 0x00u, 0x00u, 0x01u).toFloat())
        assertCompare(Float.MAX_VALUE, byteArrayOf(0x7Fu, 0x7Fu, 0xFFu, 0xFFu).toFloat())
        assertCompare(Float.NaN, byteArrayOf(0x7Fu, 0xC0u, 0x00u, 0x00u).toFloat())
        assertCompare(Float.NEGATIVE_INFINITY, byteArrayOf(0xFFu, 0x80u, 0x00u, 0x00u).toFloat())
        assertCompare(Float.POSITIVE_INFINITY, byteArrayOf(0x7Fu, 0x80u, 0x00u, 0x00u).toFloat())
        shouldThrowWithMessage<IllegalArgumentException>("ByteArray must be of size 4, but is 5") {
            byteArrayOf(0x00u, 0x00u, 0x00u, 0x00u, 0x01u).toFloat()
        }
        shouldThrowWithMessage<IllegalArgumentException>("ByteArray must be of size 4, but is 3") {
            byteArrayOf(0x00u, 0x00u, 0x00u).toFloat()
        }
    }

    "toDouble" {
        assertCompare(0.0, byteArrayOf(0x00u, 0x00u, 0x00u, 0x00u, 0x00u, 0x00u, 0x00u, 0x00u).toDouble())
        assertCompare(1.0, byteArrayOf(0x3Fu, 0xF0u, 0x00u, 0x00u, 0x00u, 0x00u, 0x00u, 0x00u).toDouble())
        assertCompare(-1.0, byteArrayOf(0xBFu, 0xF0u, 0x00u, 0x00u, 0x00u, 0x00u, 0x00u, 0x00u).toDouble())
        assertCompare(Double.MIN_VALUE, byteArrayOf(0x00u, 0x00u, 0x00u, 0x00u, 0x00u, 0x00u, 0x00u, 0x01u).toDouble())
        assertCompare(Double.MAX_VALUE, byteArrayOf(0x7Fu, 0xEFu, 0xFFu, 0xFFu, 0xFFu, 0xFFu, 0xFFu, 0xFFu).toDouble())
        assertCompare(Double.NaN, byteArrayOf(0xFFu, 0xFFu, 0xFFu, 0xFFu, 0xFFu, 0xFFu, 0xFFu, 0xFFu).toDouble())
        assertCompare(
            Double.NEGATIVE_INFINITY,
            byteArrayOf(0xffu, 0xf0u, 0x00u, 0x00u, 0x00u, 0x00u, 0x00u, 0x00u).toDouble()
        )
        assertCompare(
            Double.POSITIVE_INFINITY,
            byteArrayOf(0x7fu, 0xf0u, 0x00u, 0x00u, 0x00u, 0x00u, 0x00u, 0x00u).toDouble()
        )
        shouldThrowWithMessage<IllegalArgumentException>("ByteArray must be of size 8, but is 9") {
            byteArrayOf(0x00u, 0x00u, 0x00u, 0x00u, 0x00u, 0x00u, 0x00u, 0x00u, 0x01u).toDouble()
        }

        shouldThrowWithMessage<IllegalArgumentException>("ByteArray must be of size 8, but is 7") {
            byteArrayOf(0x00u, 0x00u, 0x00u, 0x00u, 0x00u, 0x00u, 0x00u).toDouble()
        }
    }

    "toUnsignedByte" {
        byteArrayOf(0x00u).toUnsignedByte() shouldBe 0x00u
        byteArrayOf(0x01u).toUnsignedByte() shouldBe 0x01u
        byteArrayOf(0xFFu).toUnsignedByte() shouldBe 0xFFu
        shouldThrowWithMessage<IllegalArgumentException>("ByteArray must be of size 1, but is 2") {
            byteArrayOf(0x00u, 0x01u).toUnsignedByte()
        }
        shouldThrowWithMessage<IllegalArgumentException>("ByteArray must be of size 1, but is 0") {
            byteArrayOf().toUnsignedByte()
        }
    }

    "toUnsignedShort" {
        byteArrayOf(0x00u, 0x00u).toUnsignedShort() shouldBe 0x0000u
        byteArrayOf(0x00u, 0x01u).toUnsignedShort() shouldBe 0x0001u
        byteArrayOf(0xFFu, 0xFFu).toUnsignedShort() shouldBe 0xFFFFu
        shouldThrowWithMessage<IllegalArgumentException>("ByteArray must be of size 2, but is 3") {
            byteArrayOf(0x00u, 0x00u, 0x01u).toUnsignedShort()
        }
        shouldThrowWithMessage<IllegalArgumentException>("ByteArray must be of size 2, but is 0") {
            byteArrayOf().toUnsignedShort()
        }
    }

    "toUnsignedInt" {
        byteArrayOf(0x00u, 0x00u, 0x00u, 0x00u).toUnsignedInt() shouldBe 0x00000000u
        byteArrayOf(0x00u, 0x00u, 0x00u, 0x01u).toUnsignedInt() shouldBe 0x00000001u
        byteArrayOf(0xFFu, 0xFFu, 0xFFu, 0xFFu).toUnsignedInt() shouldBe 0xFFFFFFFFu
        shouldThrowWithMessage<IllegalArgumentException>("ByteArray must be of size 4, but is 5") {
            byteArrayOf(0x00u, 0x00u, 0x00u, 0x00u, 0x01u).toUnsignedInt()
        }
        shouldThrowWithMessage<IllegalArgumentException>("ByteArray must be of size 4, but is 0") {
            byteArrayOf().toUnsignedInt()
        }
    }

    "toUnsignedLong" {
        byteArrayOf(0x00u, 0x00u, 0x00u, 0x00u, 0x00u, 0x00u, 0x00u, 0x00u).toUnsignedLong() shouldBe 0x0000000000000000uL
        byteArrayOf(0x00u, 0x00u, 0x00u, 0x00u, 0x00u, 0x00u, 0x00u, 0x01u).toUnsignedLong() shouldBe 0x0000000000000001uL
        byteArrayOf(0xFFu, 0xFFu, 0xFFu, 0xFFu, 0xFFu, 0xFFu, 0xFFu, 0xFFu).toUnsignedLong() shouldBe 0xFFFFFFFFFFFFFFFFuL
        shouldThrowWithMessage<IllegalArgumentException>("ByteArray must be of size 8, but is 9") {
            byteArrayOf(0x00u, 0x00u, 0x00u, 0x00u, 0x00u, 0x00u, 0x00u, 0x00u, 0x01u).toUnsignedLong()
        }
        shouldThrowWithMessage<IllegalArgumentException>("ByteArray must be of size 8, but is 0") {
            byteArrayOf().toUnsignedLong()
        }
    }

    "setBytes" {
        var bytes = ByteArray(8)
        bytes.setBytes(0, byteArrayOf(0x00u, 0x00u, 0x00u, 0x00u, 0x00u, 0x00u, 0x00u, 0x00u))
        (0..7).forEach { bytes[it] shouldBe 0x00u.toByte() }
        bytes = ByteArray(12)
        bytes.setBytes(2, byteArrayOf(0xFFu, 0xFFu, 0xFFu, 0xFFu, 0xFFu, 0xFFu, 0xFFu, 0xFFu))
        bytes[0] shouldBe 0x00u.toByte()
        bytes[1] shouldBe 0x00u.toByte()
        (2..11).forEach { bytes[it] shouldBe 0xFFu.toByte() }
    }

    "setBytes errors" {
        val bytes = ByteArray(8)
        shouldThrowWithMessage<IllegalArgumentException>("startIndex must be >= 0, but is -1") {
            bytes.setBytes(-1, byteArrayOf(0xFFu, 0xFFu, 0xFFu, 0xFFu, 0xFFu, 0xFFu, 0xFFu, 0xFFu))
        }

        shouldThrowWithMessage<IllegalArgumentException>("ByteArray must be of size 9, but is 8") {
            bytes.setBytes(1, ByteArray(8) { 0xFF.toByte() })
        }
    }

    "setByte" {
        var bytes = ByteArray(8)
        bytes.setByte(0, 0xFFu.toByte())
        bytes[0] shouldBe 0xFFu.toByte()
        (1..7).forEach { bytes[it] shouldBe 0x00u.toByte() }
        bytes = ByteArray(12)
        bytes.setByte(2, 0xFFu.toByte())
        bytes[0] shouldBe 0x00u.toByte()
        bytes[1] shouldBe 0x00u.toByte()
        bytes[2] shouldBe 0xFFu.toByte()
        (3..11).forEach { bytes[it] shouldBe 0x00u.toByte() }
    }

    "setByte errors" {
        val bytes = ByteArray(8)
        shouldThrowWithMessage<IllegalArgumentException>("startIndex must be >= 0, but is -1") {
            bytes.setByte(-1, 0xFFu.toByte())
        }
        shouldThrowWithMessage<IllegalArgumentException>("ByteArray must be of size 9, but is 8") {
            bytes.setByte(8, 0xFFu.toByte())
        }
    }

    "setShort" {
        var bytes = ByteArray(8)
        bytes.setShort(0, 0xFFFFu.toShort())
        bytes[0] shouldBe 0xFFu.toByte()
        bytes[1] shouldBe 0xFFu.toByte()
        (2..7).forEach { bytes[it] shouldBe 0x00u.toByte() }
        bytes = ByteArray(12)
        bytes.setShort(2, 0xFFFFu.toShort())
        bytes[0] shouldBe 0x00u.toByte()
        bytes[1] shouldBe 0x00u.toByte()
        bytes[2] shouldBe 0xFFu.toByte()
        bytes[3] shouldBe 0xFFu.toByte()
        (4..11).forEach { bytes[it] shouldBe 0x00u.toByte() }
    }

    "setShort errors" {
        val bytes = ByteArray(8)
        shouldThrowWithMessage<IllegalArgumentException>("startIndex must be >= 0, but is -1") {
            bytes.setShort(-1, 0xFFFFu.toShort())
        }
        shouldThrowWithMessage<IllegalArgumentException>("ByteArray must be of size 10, but is 8") {
            bytes.setShort(8, 0xFFFFu.toShort())
        }
    }

    "setInt" {
        var bytes = ByteArray(8)
        bytes.setInt(0, 0xFFFFFFFFu.toInt())
        bytes[0] shouldBe 0xFFu.toByte()
        bytes[1] shouldBe 0xFFu.toByte()
        bytes[2] shouldBe 0xFFu.toByte()
        bytes[3] shouldBe 0xFFu.toByte()
        (4..7).forEach { bytes[it] shouldBe 0x00u.toByte() }
        bytes = ByteArray(12)
        bytes.setInt(2, 0xFFFFFFFFu.toInt())
        bytes[0] shouldBe 0x00u.toByte()
        bytes[1] shouldBe 0x00u.toByte()
        (2..5).forEach { bytes[it] shouldBe 0xFFu.toByte() }
        (6..11).forEach { bytes[it] shouldBe 0x00u.toByte() }
    }

    "setInt errors" {
        val bytes = ByteArray(8)
        shouldThrowWithMessage<IllegalArgumentException>("startIndex must be >= 0, but is -1") {
            bytes.setInt(-1, 0xFFFFFFFFu.toInt())
        }
        shouldThrowWithMessage<IllegalArgumentException>("ByteArray must be of size 12, but is 8") {
            bytes.setInt(8, 0xFFFFFFFFu.toInt())
        }
    }

    "setLong" {
        var bytes = ByteArray(8)
        bytes.setLong(0, 0xFFFFFFFFFFFFFFFFuL.toLong())
        (0..7).forEach { bytes[it] shouldBe 0xFFu.toByte() }
        bytes = ByteArray(12)
        bytes.setLong(2, 0xFFFFFFFFFFFFFFFFuL.toLong())
        (0..1).forEach { bytes[it] shouldBe 0x00u.toByte() }
        (2..9).forEach { bytes[it] shouldBe 0xFFu.toByte() }
        (10..11).forEach { bytes[it] shouldBe 0x00u.toByte() }
    }

    "setLong errors" {
        val bytes = ByteArray(8)
        shouldThrowWithMessage<IllegalArgumentException>("startIndex must be >= 0, but is -1") {
            bytes.setLong(-1, 0xFFFFFFFFFFFFFFFFuL.toLong())
        }
        shouldThrowWithMessage<IllegalArgumentException>("ByteArray must be of size 16, but is 8") {
            bytes.setLong(8, 0xFFFFFFFFFFFFFFFFuL.toLong())
        }
    }

    "setFloat" {
        var bytes = ByteArray(8)
        bytes.setFloat(0, Float.fromBits(0x3f99999au.toInt()))
        (0..3).forEach { bytes[it] shouldBe 0x3Fu.toByte() }
        (4..7).forEach { bytes[it] shouldBe 0x99u.toByte() }
        bytes = ByteArray(12)
        bytes.setFloat(2, Float.fromBits(0x3f99999au.toInt()))
        (0..1).forEach { bytes[it] shouldBe 0x00u.toByte() }
        bytes[2] shouldBe 0x3Fu.toByte()
        (3..7).forEach { bytes[it] shouldBe 0x99u.toByte() }
        (8..11).forEach { bytes[it] shouldBe 0x00u.toByte() }
    }

    "setFloat errors" {
        val bytes = ByteArray(8)
        shouldThrowWithMessage<IllegalArgumentException>("startIndex must be >= 0, but is -1") {
            bytes.setFloat(-1, Float.fromBits(0x3f99999au.toInt()))
        }
        shouldThrowWithMessage<IllegalArgumentException>("ByteArray must be of size 8, but is 4") {
            bytes.setFloat(5, Float.fromBits(0x3f99999au.toInt()))
        }
    }

    "setDouble" {
        var bytes = ByteArray(8)
        bytes.setDouble(0, Double.fromBits(0x3ff3333333333333uL.toLong()))
        (0..7).forEach { bytes[it] shouldBe 0x3Fu.toByte() }
        bytes = ByteArray(12)
        bytes.setDouble(2, Double.fromBits(0x3ff3333333333333uL.toLong()))
        (0..1).forEach { bytes[it] shouldBe 0x00u.toByte() }
        bytes[2] shouldBe 0x3Fu.toByte()
        (3..10).forEach { bytes[it] shouldBe 0xf3u.toByte() }
        (11..11).forEach { bytes[it] shouldBe 0x33u.toByte() }
    }

    @Test
    fun testSetDoubleErrors() {
        val bytes = ByteArray(8)
        shouldThrowWithMessage<IllegalArgumentException>("startIndex must be >= 0, but is -1") {
            bytes.setDouble(-1, Double.fromBits(0x3ff3333333333333uL.toLong()))
        }
        shouldThrowWithMessage<IllegalArgumentException>("ByteArray must be of size 16, but is 8") {
            bytes.setDouble(8, Double.fromBits(0x3ff3333333333333uL.toLong()))
        }
    }

    "setUnsignedByte" {
        var bytes = ByteArray(8)
        bytes.setUnsignedByte(0, 0xFFu)
        bytes[0] shouldBe 0xFFu.toByte()
        (1..7).forEach { bytes[it] shouldBe 0x00u.toByte() }
        bytes = ByteArray(12)
        bytes.setUnsignedByte(2, 0xFFu)
        (0..1).forEach { bytes[it] shouldBe 0x00u.toByte() }
        bytes[2] shouldBe 0xFFu.toByte()
        (3..11).forEach { bytes[it] shouldBe 0x00u.toByte() }
    }

    "setUnsignedByte errors" {
        val bytes = ByteArray(8)
        shouldThrowWithMessage<IllegalArgumentException>("startIndex must be >= 0, but is -1") {
            bytes.setUnsignedByte(-1, 0xFFu)
        }
        shouldThrowWithMessage<IllegalArgumentException>("ByteArray must be of size 9, but is 8") {
            bytes.setUnsignedByte(8, 0xFFu)
        }
    }

    "setUnsignedShort" {
        var bytes = ByteArray(8)
        bytes.setUnsignedShort(0, 0xFFFFu)
        (0..1).forEach { bytes[it] shouldBe 0xFFu.toByte() }
        (2..7).forEach { bytes[it] shouldBe 0x00u.toByte() }
        bytes = ByteArray(12)
        bytes.setUnsignedShort(2, 0xFFFFu)
        (0..1).forEach { bytes[it] shouldBe 0x00u.toByte() }
        (2..3).forEach { bytes[it] shouldBe 0xFFu.toByte() }
        (4..11).forEach { bytes[it] shouldBe 0x00u.toByte() }
    }

    "setUnsignedShort errors" {
        val bytes = ByteArray(8)
        shouldThrowWithMessage<IllegalArgumentException>("startIndex must be >= 0, but is -1") {
            bytes.setUnsignedShort(-1, 0xFFFFu)
        }
        shouldThrowWithMessage<IllegalArgumentException>("ByteArray must be of size 11, but is 8") {
            bytes.setUnsignedShort(8, 0xFFFFu)
        }
    }

    "setUnsignedInt" {
        var bytes = ByteArray(8)
        bytes.setUnsignedInt(0, 0xFFFFFFFFu)
        (0..3).forEach { bytes[it] shouldBe 0xFFu.toByte() }
        (4..7).forEach { bytes[it] shouldBe 0x00u.toByte() }
        bytes = ByteArray(12)
        bytes.setUnsignedInt(2, 0xFFFFFFFFu)
        (0..1).forEach { bytes[it] shouldBe 0x00u.toByte() }
        (2..5).forEach { bytes[it] shouldBe 0xFFu.toByte() }
        (6..11).forEach { bytes[it] shouldBe 0x00u.toByte() }
    }

    "setUnsignedInt errors" {
        val bytes = ByteArray(8)
        shouldThrowWithMessage<IllegalArgumentException>("startIndex must be >= 0, but is -1") {
            bytes.setUnsignedInt(-1, 0xFFFFFFFFu)
        }
        shouldThrowWithMessage<IllegalArgumentException>("ByteArray must be of size 13, but is 8") {
            bytes.setUnsignedInt(8, 0xFFFFFFFFu)
        }
    }

    "setUnsignedLong" {
        var bytes = ByteArray(8)
        bytes.setUnsignedLong(0, 0xFFFFFFFFFFFFFFFFu)
        (0..7).forEach { bytes[it] shouldBe 0xFFu.toByte() }
        bytes = ByteArray(12)
        bytes.setUnsignedLong(2, 0xFFFFFFFFFFFFFFFFu)
        (0..1).forEach { bytes[it] shouldBe 0x00u.toByte() }
        (2..9).forEach { bytes[it] shouldBe 0xFFu.toByte() }
        (10..11).forEach { bytes[it] shouldBe 0x00u.toByte() }
    }

    "setUnsignedLong errors" {
        val bytes = ByteArray(8)
        shouldThrowWithMessage<IllegalArgumentException>("startIndex must be >= 0, but is -1") {
            bytes.setUnsignedLong(-1, 0xFFFFFFFFFFFFFFFFu)
        }
        shouldThrowWithMessage<IllegalArgumentException>("ByteArray must be of size 17, but is 8") {
            bytes.setUnsignedLong(8, 0xFFFFFFFFFFFFFFFFu)
        }
    }

    "getByte" {
        val bytes = ByteArray(8)
        bytes[0] = 0xFFu.toByte()
        bytes[1] = 0xFFu.toByte()
        bytes[2] = 0xFFu.toByte()
        bytes[3] = 0xFFu.toByte()
        bytes[4] = 0x00u.toByte()
        bytes[5] = 0x00u.toByte()
        bytes[6] = 0x00u.toByte()
        bytes[7] = 0x00u.toByte()
        (0..3).forEach { bytes[it] shouldBe 0xFFu.toByte() }
        (4..7).forEach { bytes[it] shouldBe 0x00u.toByte() }
    }

    "getByte errors" {
        val bytes = ByteArray(8)
        shouldThrowWithMessage<IllegalArgumentException>("startIndex must be >= 0, but is -1") {
            bytes.getByte(-1)
        }
        shouldThrowWithMessage<IllegalArgumentException>("ByteArray must be of size 9, but is 8") {
            bytes.getByte(8)
        }
    }

    "getUnsignedByte" {
        val bytes = ByteArray(8)
        bytes[0] = 0xFFu.toByte()
        bytes[1] = 0xFFu.toByte()
        bytes[2] = 0xFFu.toByte()
        bytes[3] = 0xFFu.toByte()
        bytes[4] = 0x00u.toByte()
        bytes[5] = 0x00u.toByte()
        bytes[6] = 0x00u.toByte()
        bytes[7] = 0x00u.toByte()
        (0..3).forEach { bytes[it] shouldBe 0xFFu.toByte() }
        (4..7).forEach { bytes[it] shouldBe 0x00u.toByte() }
    }

    "getUnsignedByte errors" {
        val bytes = ByteArray(8)
        shouldThrowWithMessage<IllegalArgumentException>("startIndex must be >= 0, but is -1") {
            bytes.getUnsignedByte(-1)
        }
        shouldThrowWithMessage<IllegalArgumentException>("ByteArray must be of size 9, but is 8") {
            bytes.getUnsignedByte(8)
        }
    }

    "getShort" {
        val bytes = ByteArray(8)
        bytes[0] = 0x00u.toByte()
        bytes[1] = 0x11u.toByte()
        bytes[2] = 0x22u.toByte()
        bytes[3] = 0x33u.toByte()
        bytes[4] = 0x44u.toByte()
        bytes[5] = 0x55u.toByte()
        bytes[6] = 0x66u.toByte()
        bytes[7] = 0x77u.toByte()
        bytes.getShort(0) shouldBe 0x0011u.toShort()
        bytes.getShort(2) shouldBe 0x2233u.toShort()
        bytes.getShort(4) shouldBe 0x4455u.toShort()
        bytes.getShort(6) shouldBe 0x6677u.toShort()
    }

    "getShort errors" {
        val bytes = ByteArray(8)
        shouldThrowWithMessage<IllegalArgumentException>("startIndex must be >= 0, but is -1") {
            bytes.getShort(-1)
        }
        shouldThrowWithMessage<IllegalArgumentException>("ByteArray must be of size 10, but is 8") {
            bytes.getShort(8)
        }
    }

    "getUnsignedShort" {
        val bytes = ByteArray(8)
        bytes[0] = 0x00u.toByte()
        bytes[1] = 0x11u.toByte()
        bytes[2] = 0x22u.toByte()
        bytes[3] = 0x33u.toByte()
        bytes[4] = 0x44u.toByte()
        bytes[5] = 0x55u.toByte()
        bytes[6] = 0x66u.toByte()
        bytes[7] = 0x77u.toByte()
        bytes.getUnsignedShort(0) shouldBe 0x0011u
        bytes.getUnsignedShort(2) shouldBe 0x2233u
        bytes.getUnsignedShort(4) shouldBe 0x4455u
        bytes.getUnsignedShort(6) shouldBe 0x6677u
    }

    "getUnsignedShort errors" {
        val bytes = ByteArray(8)
        shouldThrowWithMessage<IllegalArgumentException>("startIndex must be >= 0, but is -1") {
            bytes.getUnsignedShort(-1)
        }
        shouldThrowWithMessage<IllegalArgumentException>("ByteArray must be of size 10, but is 8") {
            bytes.getUnsignedShort(8)
        }
    }

    "getInt" {
        val bytes = ByteArray(8)
        bytes[0] = 0x00u.toByte()
        bytes[1] = 0x11u.toByte()
        bytes[2] = 0x22u.toByte()
        bytes[3] = 0x33u.toByte()
        bytes[4] = 0x44u.toByte()
        bytes[5] = 0x55u.toByte()
        bytes[6] = 0x66u.toByte()
        bytes[7] = 0x77u.toByte()
        bytes.getInt(0) shouldBe 0x00112233u.toInt()
        bytes.getInt(4) shouldBe 0x44556677u.toInt()
    }

    "getInt errors" {
        val bytes = ByteArray(8)
        shouldThrowWithMessage<IllegalArgumentException>("startIndex must be >= 0, but is -1") {
            bytes.getInt(-1)
        }
        shouldThrowWithMessage<IllegalArgumentException>("ByteArray must be of size 12, but is 8") {
            bytes.getInt(8)
        }
    }

    "getUnsignedInt" {
        val bytes = ByteArray(8)
        bytes[0] = 0x00u.toByte()
        bytes[1] = 0x11u.toByte()
        bytes[2] = 0x22u.toByte()
        bytes[3] = 0x33u.toByte()
        bytes[4] = 0x44u.toByte()
        bytes[5] = 0x55u.toByte()
        bytes[6] = 0x66u.toByte()
        bytes[7] = 0x77u.toByte()
        bytes.getUnsignedInt(0) shouldBe 0x00112233u
        bytes.getUnsignedInt(4) shouldBe 0x44556677u
    }

    "getUnsignedInt errors" {
        val bytes = ByteArray(8)
        shouldThrowWithMessage<IllegalArgumentException>("startIndex must be >= 0, but is -1") {
            bytes.getUnsignedInt(-1)
        }
        shouldThrowWithMessage<IllegalArgumentException>("ByteArray must be of size 12, but is 8") {
            bytes.getUnsignedInt(8)
        }
    }

    "getLong" {
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
        bytes.getLong(0) shouldBe 0x0011223344556677uL.toLong()
        bytes.getLong(8) shouldBe 0x8899AABBCCDDEEFFuL.toLong()
    }

    "getLong errors" {
        val bytes = ByteArray(16)
        shouldThrowWithMessage<IllegalArgumentException>("startIndex must be >= 0, but is -1") {
            bytes.getLong(-1)
        }
        shouldThrowWithMessage<IllegalArgumentException>("ByteArray must be of size 20, but is 16") {
            bytes.getLong(16)
        }
    }

    "getUnsignedLong" {
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
        bytes.getUnsignedLong(0) shouldBe 0x0011223344556677uL
        bytes.getUnsignedLong(8) shouldBe 0x8899AABBCCDDEEFFuL
    }

    "getUnsignedLong errors" {
        val bytes = ByteArray(16)
        shouldThrowWithMessage<IllegalArgumentException>("startIndex must be >= 0, but is -1") {
            bytes.getUnsignedLong(-1)
        }
        shouldThrowWithMessage<IllegalArgumentException>("ByteArray must be of size 20, but is 16") {
            bytes.getUnsignedLong(16)
        }
    }

    "getFloat" {
        val bytes = ByteArray(8)
        bytes[0] = 0x00u.toByte()
        bytes[1] = 0x11u.toByte()
        bytes[2] = 0x22u.toByte()
        bytes[3] = 0x33u.toByte()
        bytes[4] = 0x44u.toByte()
        bytes[5] = 0x55u.toByte()
        bytes[6] = 0x66u.toByte()
        bytes[7] = 0x77u.toByte()
        bytes.getFloat(0) shouldBe Float.fromBits(0x00112233u.toInt())
        bytes.getFloat(4) shouldBe Float.fromBits(0x44556677u.toInt())
    }

    "getFloat errors" {
        val bytes = ByteArray(8)
        shouldThrowWithMessage<IllegalArgumentException>("startIndex must be >= 0, but is -1") {
            bytes.getFloat(-1)
        }
        shouldThrowWithMessage<IllegalArgumentException>("ByteArray must be of size 12, but is 8") {
            bytes.getFloat(8)
        }
    }

    "getDouble" {
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
        bytes.getDouble(0) shouldBe Double.fromBits(0x0011223344556677uL.toLong())
        bytes.getDouble(8) shouldBe Double.fromBits(0x8899AABBCCDDEEFFuL.toLong())
    }

    "getDouble errors" {
        val bytes = ByteArray(16)
        shouldThrowWithMessage<IllegalArgumentException>("startIndex must be >= 0, but is -1") {
            bytes.getDouble(-1)
        }
        shouldThrowWithMessage<IllegalArgumentException>("ByteArray must be of size 20, but is 16") {
            bytes.getDouble(16)
        }
    }

    "getBytes" {
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
        bytes.getBytes(0, 16).toList() shouldBe byteArrayOf(
            0x00u, 0x11u, 0x22u, 0x33u, 0x44u, 0x55u, 0x66u, 0x77u, 0x88u, 0x99u,
            0xAAu, 0xBBu, 0xCCu, 0xDDu, 0xEEu, 0xFFu
        ).toList()
        bytes.getBytes(1, 2).toList() shouldBe byteArrayOf(0x11u, 0x22u).toList()
    }

    "getBytes errors" {
        val bytes = ByteArray(16)
        shouldThrowWithMessage<IndexOutOfBoundsException>("arraycopy: source index -1 out of bounds for byte[16]") {
            bytes.getBytes(-1, 16)
        }
        shouldThrowWithMessage<IllegalArgumentException>("toIndex (20) is greater than size (16)") {
            bytes.getBytes(0, -1)
        }
        shouldThrowWithMessage<IndexOutOfBoundsException>("ByteArray must be of size 20, but is 16") {
            bytes.getBytes(16, 4)
        }
        shouldThrowWithMessage<IndexOutOfBoundsException>("ByteArray must be of size 20, but is 16") {
            bytes.getBytes(0, 20)
        }
    }

    "getUnsignedBytes" {
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
        bytes.toHexString() shouldBe "00112233445566778899aabbccddeeff"
    }

    "getUnsignedBytes errors" {
        val bytes = "hello world".toCharArray().map { it.code.toUByte().toByte() }.toByteArray()
        bytes.toUtf8String() shouldBe "hello world"
    }

    "toHexString" {
        val byteArray = byteArrayOf(0x00u, 0xFFu)
        byteArray[0] shouldBe 0x00u.toByte()
        byteArray[1] shouldBe 0xFFu.toByte()
    }
})

fun assertCompare(expected: Float, actual: Float) {
    val delta = max(abs(expected / 1000), 0.001f)
    compare(expected, actual, delta) shouldBe true
}

fun assertCompare(expected: Double, actual: Double) {
    val delta = max(abs(expected / 1000000), 0.000001)
    compare(expected, actual, delta) shouldBe true
}

fun compare(f0: Float, f1: Float, delta: Float): Boolean = (f0.isNaN() && f1.isNaN()) ||
    (f0.isInfinite() && f1.isInfinite() && ((f0 > 0 && f1 > 0) || (f0 < 0 && f1 < 0))) ||
    abs(f0 - f1) <= delta

fun compare(d0: Double, d1: Double, delta: Double): Boolean = (d0.isNaN() && d1.isNaN()) ||
    (d0.isInfinite() && d1.isInfinite() && ((d0 > 0 && d1 > 0) || (d0 < 0 && d1 < 0))) ||
    abs(d0 - d1) <= delta
