package com.shakelang.shake.util.primitives.bytes

import io.kotest.assertions.throwables.shouldThrow
import io.kotest.core.spec.style.FreeSpec
import io.kotest.matchers.shouldBe

@Suppress("unused")
class ByteListTests : FreeSpec({

    "toByte" {
        val bytes = listOf<Byte>(1)
        val byte = bytes.toByte()
        byte shouldBe 1

        val bytes2 = listOf<Byte>(1, 2)
        shouldThrow<IllegalArgumentException> {
            bytes2.toByte()
        }
    }

    "toShort" {
        val bytes = listOf<Byte>(1, 2)
        val short = bytes.toShort()
        short shouldBe 0x0102

        val bytes2 = listOf<Byte>(1)
        shouldThrow<IllegalArgumentException> {
            bytes2.toShort()
        }
    }

    "toInt" {
        val bytes = listOf<Byte>(1, 2, 3, 4)
        val int = bytes.toInt()
        int shouldBe 16909060

        val bytes2 = listOf<Byte>(1)
        shouldThrow<IllegalArgumentException> {
            bytes2.toInt()
        }
    }

    "toLong" {
        val bytes = listOf<Byte>(1, 2, 3, 4, 5, 6, 7, 8)
        val long = bytes.toLong()
        long shouldBe 0x0102030405060708L

        val bytes2 = listOf<Byte>(1)
        shouldThrow<IllegalArgumentException> {
            bytes2.toLong()
        }
    }

    "toFloat" {
        val bytes = listOf<Byte>(1, 2, 3, 4)
        val float = bytes.toFloat()
        float shouldBe Float.fromBits(0x01020304)

        val bytes2 = listOf<Byte>(1)
        shouldThrow<IllegalArgumentException> {
            bytes2.toFloat()
        }
    }

    "toDouble" {
        val bytes = listOf<Byte>(1, 2, 3, 4, 5, 6, 7, 8)
        val double = bytes.toDouble()
        double shouldBe Double.fromBits(0x0102030405060708)

        val bytes2 = listOf<Byte>(1)
        shouldThrow<IllegalArgumentException> {
            bytes2.toDouble()
        }
    }

    "toUnsignedByte" {
        val bytes = listOf<Byte>(1)
        val unsignedByte = bytes.toUnsignedByte()
        unsignedByte shouldBe 1u

        val bytes2 = listOf<Byte>(1, 2)
        shouldThrow<IllegalArgumentException> {
            bytes2.toUnsignedByte()
        }
    }

    "toUnsignedShort" {
        val bytes = listOf<Byte>(1, 2)
        val unsignedShort = bytes.toUnsignedShort()
        unsignedShort shouldBe 0x0102u

        val bytes2 = listOf<Byte>(1)
        shouldThrow<IllegalArgumentException> {
            bytes2.toUnsignedShort()
        }
    }

    "toUnsignedInt" {
        val bytes = listOf<Byte>(1, 2, 3, 4)
        val unsignedInt = bytes.toUnsignedInt()
        unsignedInt shouldBe 0x01020304u

        val bytes2 = listOf<Byte>(1)
        shouldThrow<IllegalArgumentException> {
            bytes2.toUnsignedInt()
        }
    }

    "toUnsignedLong" {
        val bytes = listOf<Byte>(1, 2, 3, 4, 5, 6, 7, 8)
        val unsignedLong = bytes.toUnsignedLong()
        unsignedLong shouldBe 0x0102030405060708uL

        val bytes2 = listOf<Byte>(1)
        shouldThrow<IllegalArgumentException> {
            bytes2.toUnsignedLong()
        }
    }

    "setBytes" {
        val bytes = mutableListOf<Byte>(1, 2, 3, 4, 5, 6, 7, 8)
        bytes.setBytes(0, listOf(9, 10, 11, 12))
        bytes shouldBe listOf<Byte>(9, 10, 11, 12, 5, 6, 7, 8)

        bytes.setBytes(4, listOf(13, 14, 15, 16))
        bytes shouldBe listOf<Byte>(9, 10, 11, 12, 13, 14, 15, 16)

        bytes.setBytes(4, kotlin.byteArrayOf(13, 14, 15, 16))
        bytes shouldBe listOf<Byte>(9, 10, 11, 12, 13, 14, 15, 16)

        shouldThrow<IndexOutOfBoundsException> {
            bytes.setBytes(0, listOf(9, 10, 11, 12, 13, 14, 15, 16, 17))
        }
    }

    "setByte" {
        val bytes = mutableListOf<Byte>(1, 2, 3, 4, 5, 6, 7, 8)
        bytes.setByte(0, 9)
        bytes shouldBe listOf<Byte>(9, 2, 3, 4, 5, 6, 7, 8)

        bytes.setByte(4, 13)
        bytes shouldBe listOf<Byte>(9, 2, 3, 4, 13, 6, 7, 8)

        shouldThrow<IllegalArgumentException> {
            bytes.setByte(8, 9)
        }

        shouldThrow<IllegalArgumentException> {
            bytes.setByte(-1, 9)
        }
    }

    "setShort" {
        val bytes = mutableListOf<Byte>(1, 2, 3, 4, 5, 6, 7, 8)
        bytes.setShort(0, 0x0304)
        bytes shouldBe listOf<Byte>(3, 4, 3, 4, 5, 6, 7, 8)

        bytes.setShort(4, 0x0304)
        bytes shouldBe listOf<Byte>(3, 4, 3, 4, 3, 4, 7, 8)

        shouldThrow<IllegalArgumentException> {
            bytes.setShort(8, 0x0102)
        }

        shouldThrow<IllegalArgumentException> {
            bytes.setShort(-1, 0x0102)
        }
    }

    "setInt" {
        val bytes = mutableListOf<Byte>(1, 2, 3, 4, 5, 6, 7, 8)
        bytes.setInt(0, 0x04030201)
        bytes shouldBe listOf<Byte>(4, 3, 2, 1, 5, 6, 7, 8)

        bytes.setInt(4, 0x04030201)
        bytes shouldBe listOf<Byte>(4, 3, 2, 1, 4, 3, 2, 1)

        shouldThrow<IllegalArgumentException> {
            bytes.setInt(6, 0x01020304)
        }

        shouldThrow<IllegalArgumentException> {
            bytes.setInt(-1, 0x01020304)
        }
    }

    "setLong" {
        val bytes = mutableListOf<Byte>(1, 2, 3, 4, 5, 6, 7, 8)
        bytes.setLong(0, 0x0807060504030201L)
        bytes shouldBe listOf<Byte>(8, 7, 6, 5, 4, 3, 2, 1)

        shouldThrow<IllegalArgumentException> {
            bytes.setLong(6, 0x0102030405060708L)
        }

        shouldThrow<IllegalArgumentException> {
            bytes.setLong(-1, 0x0102030405060708L)
        }
    }

    "setFloat" {
        val bytes = mutableListOf<Byte>(1, 2, 3, 4, 5, 6, 7, 8)
        bytes.setFloat(0, 1.0f)
        bytes shouldBe listOf<Byte>(63, -128, 0, 0, 5, 6, 7, 8)

        bytes.setFloat(4, 1.0f)
        bytes shouldBe listOf<Byte>(63, -128, 0, 0, 63, -128, 0, 0)

        shouldThrow<IllegalArgumentException> {
            bytes.setFloat(6, Float.fromBits(0x01020304))
        }

        shouldThrow<IllegalArgumentException> {
            bytes.setFloat(-1, Float.fromBits(0x01020304))
        }
    }

    "setDouble" {
        val bytes = mutableListOf<Byte>(1, 2, 3, 4, 5, 6, 7, 8)
        bytes.setDouble(0, 1.0)
        bytes shouldBe listOf<Byte>(63, -16, 0, 0, 0, 0, 0, 0)

        shouldThrow<IllegalArgumentException> {
            bytes.setDouble(4, Double.fromBits(0x0102030405060708L))
        }

        shouldThrow<IllegalArgumentException> {
            bytes.setDouble(-1, Double.fromBits(0x0102030405060708L))
        }
    }

    "setUnsignedByte" {
        val bytes = mutableListOf<Byte>(1, 2, 3, 4, 5, 6, 7, 8)
        bytes.setUnsignedByte(0, 0xFFu)
        bytes shouldBe listOf<Byte>(-1, 2, 3, 4, 5, 6, 7, 8)

        bytes.setUnsignedByte(4, 0xFFu)
        bytes shouldBe listOf<Byte>(-1, 2, 3, 4, -1, 6, 7, 8)

        shouldThrow<IllegalArgumentException> {
            bytes.setUnsignedByte(8, 0xFFu)
        }

        shouldThrow<IllegalArgumentException> {
            bytes.setUnsignedByte(-1, 0xFFu)
        }
    }

    "setUnsignedShort" {
        val bytes = mutableListOf<Byte>(1, 2, 3, 4, 5, 6, 7, 8)
        bytes.setUnsignedShort(0, 0xFFFFu)
        bytes shouldBe listOf<Byte>(-1, -1, 3, 4, 5, 6, 7, 8)

        bytes.setUnsignedShort(4, 0xFFFFu)
        bytes shouldBe listOf<Byte>(-1, -1, 3, 4, -1, -1, 7, 8)

        shouldThrow<IllegalArgumentException> {
            bytes.setUnsignedShort(7, 0xFFFFu)
        }

        shouldThrow<IllegalArgumentException> {
            bytes.setUnsignedShort(-1, 0xFFFFu)
        }
    }

    "setUnsignedInt" {
        val bytes = mutableListOf<Byte>(1, 2, 3, 4, 5, 6, 7, 8)
        bytes.setUnsignedInt(0, 0xFFFFFFFFu)
        bytes shouldBe listOf<Byte>(-1, -1, -1, -1, 5, 6, 7, 8)

        bytes.setUnsignedInt(4, 0xFFFFFFFFu)
        bytes shouldBe listOf<Byte>(-1, -1, -1, -1, -1, -1, -1, -1)

        shouldThrow<IllegalArgumentException> {
            bytes.setUnsignedInt(7, 0xFFFFFFFFu)
        }

        shouldThrow<IllegalArgumentException> {
            bytes.setUnsignedInt(-1, 0xFFFFFFFFu)
        }
    }

    "setUnsignedLong" {
        val bytes = mutableListOf<Byte>(1, 2, 3, 4, 5, 6, 7, 8)
        bytes.setUnsignedLong(0, 0xFFFFFFFFFFFFFFFFuL)
        bytes shouldBe listOf<Byte>(-1, -1, -1, -1, -1, -1, -1, -1)

        shouldThrow<IllegalArgumentException> {
            bytes.setUnsignedLong(7, 0xFFFFFFFFFFFFFFFFuL)
        }

        shouldThrow<IllegalArgumentException> {
            bytes.setUnsignedLong(-1, 0xFFFFFFFFFFFFFFFFuL)
        }
    }

    "getByte" {
        val bytes = listOf<Byte>(1, 2, 3, 4, 5, 6, 7, 8)
        bytes.getByte(0) shouldBe 1
        bytes.getByte(1) shouldBe 2
        bytes.getByte(2) shouldBe 3
        bytes.getByte(3) shouldBe 4
        bytes.getByte(4) shouldBe 5
        bytes.getByte(5) shouldBe 6
        bytes.getByte(6) shouldBe 7
        bytes.getByte(7) shouldBe 8

        shouldThrow<IllegalArgumentException> {
            bytes.getByte(8)
        }

        shouldThrow<IllegalArgumentException> {
            bytes.getByte(-1)
        }
    }

    "getShort" {
        val bytes = listOf<Byte>(1, 2, 3, 4, 5, 6, 7, 8)
        bytes.getShort(0) shouldBe 0x0102
        bytes.getShort(2) shouldBe 0x0304
        bytes.getShort(4) shouldBe 0x0506
        bytes.getShort(6) shouldBe 0x0708
        bytes.getShort(1) shouldBe 0x0203

        shouldThrow<IllegalArgumentException> {
            bytes.getShort(8)
        }

        shouldThrow<IllegalArgumentException> {
            bytes.getShort(-1)
        }
    }

    "getInt" {
        val bytes = listOf<Byte>(1, 2, 3, 4, 5, 6, 7, 8)
        bytes.getInt(0) shouldBe 0x01020304
        bytes.getInt(4) shouldBe 0x05060708
        bytes.getInt(1) shouldBe 0x02030405

        shouldThrow<IllegalArgumentException> {
            bytes.getInt(8)
        }

        shouldThrow<IllegalArgumentException> {
            bytes.getInt(-1)
        }
    }

    "getLong" {
        val bytes = listOf<Byte>(1, 2, 3, 4, 5, 6, 7, 8)
        bytes.getLong(0) shouldBe 0x0102030405060708L

        shouldThrow<IllegalArgumentException> {
            bytes.getLong(1)
        }

        shouldThrow<IllegalArgumentException> {
            bytes.getLong(-1)
        }
    }

    "getFloat" {
        val bytes = listOf<Byte>(1, 2, 3, 4, 5, 6, 7, 8)
        bytes.getFloat(0) shouldBe Float.fromBits(0x01020304)
        bytes.getFloat(4) shouldBe Float.fromBits(0x05060708)
        bytes.getFloat(1) shouldBe Float.fromBits(0x02030405)

        shouldThrow<IllegalArgumentException> {
            bytes.getFloat(5)
        }

        shouldThrow<IllegalArgumentException> {
            bytes.getFloat(-1)
        }
    }

    "getDouble" {
        val bytes = listOf<Byte>(1, 2, 3, 4, 5, 6, 7, 8)
        bytes.getDouble(0) shouldBe Double.fromBits(0x0102030405060708L)

        shouldThrow<IllegalArgumentException> {
            bytes.getDouble(1)
        }

        shouldThrow<IllegalArgumentException> {
            bytes.getDouble(-1)
        }
    }

    "getUnsignedByte" {
        val bytes = listOf(1, 2, 3, 4, 5, 6, 7, 255u.toByte())
        bytes.getUnsignedByte(0) shouldBe 1u
        bytes.getUnsignedByte(1) shouldBe 2u
        bytes.getUnsignedByte(2) shouldBe 3u
        bytes.getUnsignedByte(3) shouldBe 4u
        bytes.getUnsignedByte(4) shouldBe 5u
        bytes.getUnsignedByte(5) shouldBe 6u
        bytes.getUnsignedByte(6) shouldBe 7u
        bytes.getUnsignedByte(7) shouldBe 255u
    }

    "getUnsignedShort" {
        val bytes = listOf<Byte>(1, 2, 3, 4, 5, 6, 7, 8)
        bytes.getUnsignedShort(0) shouldBe 0x0102u
        bytes.getUnsignedShort(2) shouldBe 0x0304u
        bytes.getUnsignedShort(4) shouldBe 0x0506u
        bytes.getUnsignedShort(6) shouldBe 0x0708u
        bytes.getUnsignedShort(1) shouldBe 0x0203u
    }

    "getUnsignedInt" {
        val bytes = listOf<Byte>(1, 2, 3, 4, 5, 6, 7, 8)
        bytes.getUnsignedInt(0) shouldBe 0x01020304u
        bytes.getUnsignedInt(4) shouldBe 0x05060708u
        bytes.getUnsignedInt(1) shouldBe 0x02030405u
    }

    "getUnsignedLong" {
        val bytes = listOf<Byte>(1, 2, 3, 4, 5, 6, 7, 8)
        bytes.getUnsignedLong(0) shouldBe 0x0102030405060708uL
    }

    "removeLastByte" {
        val bytes = mutableListOf<Byte>(1, 2, 3, 4, 5, 6, 7, 8)

        bytes.size shouldBe 8
        bytes.removeLastByte() shouldBe 8
        bytes.size shouldBe 7
        bytes.removeLastByte() shouldBe 7
        bytes.size shouldBe 6
        bytes.removeLastByte() shouldBe 6
        bytes.size shouldBe 5
        bytes.removeLastByte() shouldBe 5
        bytes.size shouldBe 4
        bytes.removeLastByte() shouldBe 4
        bytes.size shouldBe 3
        bytes.removeLastByte() shouldBe 3
        bytes.size shouldBe 2
        bytes.removeLastByte() shouldBe 2
        bytes.size shouldBe 1
        bytes.removeLastByte() shouldBe 1
        bytes.size shouldBe 0

        shouldThrow<IllegalArgumentException> {
            bytes.removeLastByte()
        }
    }

    "removeLastShort" {
        val bytes = mutableListOf<Byte>(1, 2, 3, 4, 5, 6, 7, 8)
        bytes.size shouldBe 8
        bytes.removeLastShort() shouldBe 0x0708
        bytes.size shouldBe 6
        bytes.removeLastShort() shouldBe 0x0506
        bytes.size shouldBe 4
        bytes.removeLastShort() shouldBe 0x0304
        bytes.size shouldBe 2
        bytes.removeLastShort() shouldBe 0x0102
        bytes.size shouldBe 0

        shouldThrow<IllegalArgumentException> {
            bytes.removeLastShort()
        }
    }

    "removeLastInt" {
        val bytes = mutableListOf<Byte>(1, 2, 3, 4, 5, 6, 7, 8)
        bytes.size shouldBe 8
        bytes.removeLastInt() shouldBe 0x05060708
        bytes.size shouldBe 4
        bytes.removeLastInt() shouldBe 0x01020304
        bytes.size shouldBe 0

        shouldThrow<IllegalArgumentException> {
            bytes.removeLastInt()
        }
    }

    "removeLastLong" {
        val bytes = mutableListOf<Byte>(1, 2, 3, 4, 5, 6, 7, 8)
        bytes.size shouldBe 8
        bytes.removeLastLong() shouldBe 0x0102030405060708L
        bytes.size shouldBe 0

        shouldThrow<IllegalArgumentException> {
            bytes.removeLastLong()
        }
    }

    "removeLastFloat" {
        val bytes = mutableListOf<Byte>(1, 2, 3, 4, 5, 6, 7, 8)
        bytes.size shouldBe 8
        bytes.removeLastFloat() shouldBe Float.fromBits(0x05060708)
        bytes.size shouldBe 4
        bytes.removeLastFloat() shouldBe Float.fromBits(0x01020304)
        bytes.size shouldBe 0

        shouldThrow<IllegalArgumentException> {
            bytes.removeLastFloat()
        }
    }

    "removeLastDouble" {
        val bytes = mutableListOf<Byte>(1, 2, 3, 4, 5, 6, 7, 8)
        bytes.size shouldBe 8
        bytes.removeLastDouble() shouldBe Double.fromBits(0x0102030405060708)
        bytes.size shouldBe 0

        shouldThrow<IllegalArgumentException> {
            bytes.removeLastDouble()
        }
    }

    "removeLastUnsignedByte" {
        val bytes = mutableListOf<Byte>(1, 2, 3, 4, 5, 6, 7, 8)
        bytes.size shouldBe 8
        bytes.removeLastUnsignedByte() shouldBe 8u
        bytes.size shouldBe 7
        bytes.removeLastUnsignedByte() shouldBe 7u
        bytes.size shouldBe 6
        bytes.removeLastUnsignedByte() shouldBe 6u
        bytes.size shouldBe 5
        bytes.removeLastUnsignedByte() shouldBe 5u
        bytes.size shouldBe 4
        bytes.removeLastUnsignedByte() shouldBe 4u
        bytes.size shouldBe 3
        bytes.removeLastUnsignedByte() shouldBe 3u
        bytes.size shouldBe 2
        bytes.removeLastUnsignedByte() shouldBe 2u
        bytes.size shouldBe 1
        bytes.removeLastUnsignedByte() shouldBe 1u
        bytes.size shouldBe 0

        shouldThrow<IllegalArgumentException> {
            bytes.removeLastUnsignedByte()
        }
    }

    "removeLastUnsignedShort" {
        val bytes = mutableListOf<Byte>(1, 2, 3, 4, 5, 6, 7, 8)
        bytes.size shouldBe 8
        bytes.removeLastUnsignedShort() shouldBe 0x0708u
        bytes.size shouldBe 6
        bytes.removeLastUnsignedShort() shouldBe 0x0506u
        bytes.size shouldBe 4
        bytes.removeLastUnsignedShort() shouldBe 0x0304u
        bytes.size shouldBe 2
        bytes.removeLastUnsignedShort() shouldBe 0x0102u
        bytes.size shouldBe 0

        shouldThrow<IllegalArgumentException> {
            bytes.removeLastUnsignedShort()
        }
    }

    "removeLastUnsignedInt" {
        val bytes = mutableListOf<Byte>(1, 2, 3, 4, 5, 6, 7, 8)
        bytes.size shouldBe 8
        bytes.removeLastUnsignedInt() shouldBe 0x05060708u
        bytes.size shouldBe 4
        bytes.removeLastUnsignedInt() shouldBe 0x01020304u
        bytes.size shouldBe 0

        shouldThrow<IllegalArgumentException> {
            bytes.removeLastUnsignedInt()
        }
    }

    "removeLastUnsignedLong" {
        val bytes = mutableListOf<Byte>(1, 2, 3, 4, 5, 6, 7, 8)
        bytes.size shouldBe 8
        bytes.removeLastUnsignedLong() shouldBe 0x0102030405060708uL
        bytes.size shouldBe 0

        shouldThrow<IllegalArgumentException> {
            bytes.removeLastUnsignedLong()
        }
    }

    "getBytes" {
        val bytes = listOf<Byte>(1, 2, 3, 4, 5, 6, 7, 8)
        bytes.getBytes(0, 4) shouldBe listOf<Byte>(1, 2, 3, 4)
        bytes.getBytes(4, 4) shouldBe listOf<Byte>(5, 6, 7, 8)

        shouldThrow<IllegalArgumentException> {
            bytes.getBytes(5, 4)
        }

        shouldThrow<IllegalArgumentException> {
            bytes.getBytes(-1, 4)
        }
    }

    "toHexString" {
        val bytes = listOf<Byte>(0, 1, 2, 3, 4, 5, 6, 7, 8, -1)
        bytes.toHexString() shouldBe "000102030405060708ff"
    }

    "toUtf8String" {
        val bytes = listOf<Byte>(1, 2, 3, 4, 5, 6, 7, 8)
        bytes.toUtf8String() shouldBe "\u0001\u0002\u0003\u0004\u0005\u0006\u0007\u0008"
    }

    "appendWithByte" {
        val bytes = mutableListOf<Byte>()
        bytes.append(1.toByte())
        bytes shouldBe listOf<Byte>(1)
        bytes.append(2.toByte())
        bytes shouldBe listOf<Byte>(1, 2)
        bytes.append(3.toByte())
        bytes shouldBe listOf<Byte>(1, 2, 3)
        bytes.append(4.toByte())
        bytes shouldBe listOf<Byte>(1, 2, 3, 4)
        bytes.append(5.toByte())
        bytes shouldBe listOf<Byte>(1, 2, 3, 4, 5)
        bytes.append(6.toByte())
        bytes shouldBe listOf<Byte>(1, 2, 3, 4, 5, 6)
        bytes.append(7.toByte())
        bytes shouldBe listOf<Byte>(1, 2, 3, 4, 5, 6, 7)
        bytes.append(8.toByte())
        bytes shouldBe listOf<Byte>(1, 2, 3, 4, 5, 6, 7, 8)
    }

    "appendWithShort" {
        val bytes = mutableListOf<Byte>()
        bytes.append(0x0102.toShort())
        bytes shouldBe listOf<Byte>(1, 2)
        bytes.append(0x0304.toShort())
        bytes shouldBe listOf<Byte>(1, 2, 3, 4)
        bytes.append(0x0506.toShort())
        bytes shouldBe listOf<Byte>(1, 2, 3, 4, 5, 6)
        bytes.append(0x0708.toShort())
        bytes shouldBe listOf<Byte>(1, 2, 3, 4, 5, 6, 7, 8)
    }

    "appendWithInt" {
        val bytes = mutableListOf<Byte>()
        bytes.append(0x01020304)
        bytes shouldBe listOf<Byte>(1, 2, 3, 4)
        bytes.append(0x05060708)
        bytes shouldBe listOf<Byte>(1, 2, 3, 4, 5, 6, 7, 8)
    }

    "appendWithLong" {
        val bytes = mutableListOf<Byte>()
        bytes.append(0x0102030405060708L)
        bytes shouldBe listOf<Byte>(1, 2, 3, 4, 5, 6, 7, 8)
    }

    "appendWithFloat" {
        val bytes = mutableListOf<Byte>()
        bytes.append(Float.fromBits(0x01020304))
        bytes shouldBe listOf<Byte>(1, 2, 3, 4)
        bytes.append(Float.fromBits(0x05060708))
        bytes shouldBe listOf<Byte>(1, 2, 3, 4, 5, 6, 7, 8)
    }

    "appendWithDouble" {
        val bytes = mutableListOf<Byte>()
        bytes.append(Double.fromBits(0x0102030405060708L))
        bytes shouldBe listOf<Byte>(1, 2, 3, 4, 5, 6, 7, 8)
    }

    "appendWithUnsignedByte"{
        val bytes = mutableListOf<Byte>()
        bytes.append(1u.toUByte())
        bytes shouldBe listOf<Byte>(1)
        bytes.append(2u.toUByte())
        bytes shouldBe listOf<Byte>(1, 2)
        bytes.append(3u.toUByte())
        bytes shouldBe listOf<Byte>(1, 2, 3)
        bytes.append(4u.toUByte())
        bytes shouldBe listOf<Byte>(1, 2, 3, 4)
        bytes.append(5u.toUByte())
        bytes shouldBe listOf<Byte>(1, 2, 3, 4, 5)
        bytes.append(6u.toUByte())
        bytes shouldBe listOf<Byte>(1, 2, 3, 4, 5, 6)
        bytes.append(7u.toUByte())
        bytes shouldBe listOf<Byte>(1, 2, 3, 4, 5, 6, 7)
        bytes.append(8u.toUByte())
        bytes shouldBe listOf<Byte>(1, 2, 3, 4, 5, 6, 7, 8)
    }

    "appendWithUnsignedShort" {
        val bytes = mutableListOf<Byte>()
        bytes.append(0x0102u.toUShort())
        bytes shouldBe listOf<Byte>(1, 2)
        bytes.append(0x0304u.toUShort())
        bytes shouldBe listOf<Byte>(1, 2, 3, 4)
        bytes.append(0x0506u.toUShort())
        bytes shouldBe listOf<Byte>(1, 2, 3, 4, 5, 6)
        bytes.append(0x0708u.toUShort())
        bytes shouldBe listOf<Byte>(1, 2, 3, 4, 5, 6, 7, 8)
    }

    "appendWithUnsignedInt" {
        val bytes = mutableListOf<Byte>()
        bytes.append(0x01020304u)
        bytes shouldBe listOf<Byte>(1, 2, 3, 4)
        bytes.append(0x05060708u)
        bytes shouldBe listOf<Byte>(1, 2, 3, 4, 5, 6, 7, 8)
    }

    "appendWithUnsignedLong" {
        val bytes = mutableListOf<Byte>()
        bytes.append(0x0102030405060708uL)
        bytes shouldBe listOf<Byte>(1, 2, 3, 4, 5, 6, 7, 8)
    }
})
