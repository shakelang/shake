package io.github.shakelang.shake.util.primitives.bytes

import kotlin.test.Test
import kotlin.test.assertEquals
import kotlin.test.assertFailsWith

class ByteListTests {

    @Test
    fun testToByte() {
        val bytes = listOf<Byte>(1)
        val byte = bytes.toByte()
        assertEquals(1, byte)

        val bytes2 = listOf<Byte>(1, 2)
        assertFailsWith(IllegalArgumentException::class, "ByteArray must be of size 1, but is 2") {
            bytes2.toByte()
        }
    }

    @Test
    fun testToShort() {
        val bytes = listOf<Byte>(1, 2)
        val short = bytes.toShort()
        assertEquals(0x0102, short)

        val bytes2 = listOf<Byte>(1)
        assertFailsWith(IllegalArgumentException::class, "ByteArray must be of size 2, but is 1") {
            bytes2.toShort()
        }
    }

    @Test
    fun testToInt() {
        val bytes = listOf<Byte>(1, 2, 3, 4)
        val int = bytes.toInt()
        assertEquals(16909060, int)

        val bytes2 = listOf<Byte>(1)
        assertFailsWith(IllegalArgumentException::class, "ByteArray must be of size 4, but is 1") {
            bytes2.toInt()
        }
    }

    @Test
    fun testToLong() {
        val bytes = listOf<Byte>(1, 2, 3, 4, 5, 6, 7, 8)
        val long = bytes.toLong()
        assertEquals(0x0102030405060708L, long)

        val bytes2 = listOf<Byte>(1)
        assertFailsWith(IllegalArgumentException::class, "ByteArray must be of size 8, but is 1") {
            bytes2.toLong()
        }
    }

    @Test
    fun testToFloat() {
        val bytes = listOf<Byte>(1, 2, 3, 4)
        val float = bytes.toFloat()
        assertEquals(Float.fromBits(0x01020304), float)

        val bytes2 = listOf<Byte>(1)
        assertFailsWith(IllegalArgumentException::class, "ByteArray must be of size 4, but is 1") {
            bytes2.toFloat()
        }
    }

    @Test
    fun testToDouble() {
        val bytes = listOf<Byte>(1, 2, 3, 4, 5, 6, 7, 8)
        val double = bytes.toDouble()
        assertEquals(Double.fromBits(0x0102030405060708), double)

        val bytes2 = listOf<Byte>(1)
        assertFailsWith(IllegalArgumentException::class, "ByteArray must be of size 8, but is 1") {
            bytes2.toDouble()
        }
    }

    @Test
    fun testToUnsignedByte() {
        val bytes = listOf<Byte>(1)
        val unsignedByte = bytes.toUnsignedByte()
        assertEquals(1u, unsignedByte)

        val bytes2 = listOf<Byte>(1, 2)
        assertFailsWith(IllegalArgumentException::class, "ByteArray must be of size 1, but is 2") {
            bytes2.toUnsignedByte()
        }
    }

    @Test
    fun testToUnsignedShort() {
        val bytes = listOf<Byte>(1, 2)
        val unsignedShort = bytes.toUnsignedShort()
        assertEquals(0x0102u, unsignedShort)

        val bytes2 = listOf<Byte>(1)
        assertFailsWith(IllegalArgumentException::class, "ByteArray must be of size 2, but is 1") {
            bytes2.toUnsignedShort()
        }
    }

    @Test
    fun testToUnsignedInt() {
        val bytes = listOf<Byte>(1, 2, 3, 4)
        val unsignedInt = bytes.toUnsignedInt()
        assertEquals(0x01020304u, unsignedInt)

        val bytes2 = listOf<Byte>(1)
        assertFailsWith(IllegalArgumentException::class, "ByteArray must be of size 4, but is 1") {
            bytes2.toUnsignedInt()
        }
    }

    @Test
    fun testToUnsignedLong() {
        val bytes = listOf<Byte>(1, 2, 3, 4, 5, 6, 7, 8)
        val unsignedLong = bytes.toUnsignedLong()
        assertEquals(0x0102030405060708u, unsignedLong)

        val bytes2 = listOf<Byte>(1)
        assertFailsWith(IllegalArgumentException::class, "ByteArray must be of size 8, but is 1") {
            bytes2.toUnsignedLong()
        }
    }

    @Test
    fun testSetBytes() {
        val bytes = mutableListOf<Byte>(1, 2, 3, 4, 5, 6, 7, 8)
        bytes.setBytes(0, listOf(9, 10, 11, 12))
        assertEquals(listOf<Byte>(9, 10, 11, 12, 5, 6, 7, 8), bytes)

        bytes.setBytes(4, listOf(13, 14, 15, 16))
        assertEquals(listOf<Byte>(9, 10, 11, 12, 13, 14, 15, 16), bytes)

        bytes.setBytes(4, kotlin.byteArrayOf(13, 14, 15, 16))
        assertEquals(listOf<Byte>(9, 10, 11, 12, 13, 14, 15, 16), bytes)

        assertFailsWith(IndexOutOfBoundsException::class, "Index 8 out of bounds for length 8") {
            bytes.setBytes(0, listOf(9, 10, 11, 12, 13, 14, 15, 16, 17))
        }
    }

    @Test
    fun testSetByte() {
        val bytes = mutableListOf<Byte>(1, 2, 3, 4, 5, 6, 7, 8)
        bytes.setByte(0, 9)
        assertEquals(listOf<Byte>(9, 2, 3, 4, 5, 6, 7, 8), bytes)

        bytes.setByte(4, 13)
        assertEquals(listOf<Byte>(9, 2, 3, 4, 13, 6, 7, 8), bytes)

        assertFailsWith(IllegalArgumentException::class, "ByteArray must be of size 9, but is 8") {
            bytes.setByte(8, 9)
        }

        assertFailsWith<IllegalArgumentException>("index must be >= 0, but is -1") {
            bytes.setByte(-1, 9)
        }
    }

    @Test
    fun testSetShort() {
        val bytes = mutableListOf<Byte>(1, 2, 3, 4, 5, 6, 7, 8)
        bytes.setShort(0, 0x0304)
        assertEquals(listOf<Byte>(3, 4, 3, 4, 5, 6, 7, 8), bytes)

        bytes.setShort(4, 0x0304)
        assertEquals(listOf<Byte>(3, 4, 3, 4, 3, 4, 7, 8), bytes)

        assertFailsWith(IllegalArgumentException::class, "ByteArray must be of size 9, but is 8") {
            bytes.setShort(8, 0x0102)
        }

        assertFailsWith<IllegalArgumentException>("index must be >= 0, but is -1") {
            bytes.setShort(-1, 0x0102)
        }
    }

    @Test
    fun testSetInt() {
        val bytes = mutableListOf<Byte>(1, 2, 3, 4, 5, 6, 7, 8)
        bytes.setInt(0, 0x04030201)
        assertEquals(listOf<Byte>(4, 3, 2, 1, 5, 6, 7, 8), bytes)

        bytes.setInt(4, 0x04030201)
        assertEquals(listOf<Byte>(4, 3, 2, 1, 4, 3, 2, 1), bytes)

        assertFailsWith(IllegalArgumentException::class, "ByteArray must be of size 9, but is 8") {
            bytes.setInt(6, 0x01020304)
        }

        assertFailsWith<IllegalArgumentException>("index must be >= 0, but is -1") {
            bytes.setInt(-1, 0x01020304)
        }
    }

    @Test
    fun testSetLong() {
        val bytes = mutableListOf<Byte>(1, 2, 3, 4, 5, 6, 7, 8)
        bytes.setLong(0, 0x0807060504030201L)
        assertEquals(listOf<Byte>(8, 7, 6, 5, 4, 3, 2, 1), bytes)

        assertFailsWith(IllegalArgumentException::class, "ByteArray must be of size 9, but is 8") {
            bytes.setLong(6, 0x0102030405060708L)
        }

        assertFailsWith<IllegalArgumentException>("index must be >= 0, but is -1") {
            bytes.setLong(-1, 0x0102030405060708L)
        }
    }

    @Test
    fun testSetFloat() {
        val bytes = mutableListOf<Byte>(1, 2, 3, 4, 5, 6, 7, 8)
        bytes.setFloat(0, 1.0f)
        assertEquals(listOf<Byte>(63, -128, 0, 0, 5, 6, 7, 8), bytes)

        bytes.setFloat(4, 1.0f)
        assertEquals(listOf<Byte>(63, -128, 0, 0, 63, -128, 0, 0), bytes)

        assertFailsWith(IllegalArgumentException::class, "ByteArray must be of size 9, but is 8") {
            bytes.setFloat(6, Float.fromBits(0x01020304))
        }

        assertFailsWith<IllegalArgumentException>("index must be >= 0, but is -1") {
            bytes.setFloat(-1, Float.fromBits(0x01020304))
        }
    }

    @Test
    fun testSetDouble() {
        val bytes = mutableListOf<Byte>(1, 2, 3, 4, 5, 6, 7, 8)
        bytes.setDouble(0, 1.0)
        assertEquals(listOf<Byte>(63, -16, 0, 0, 0, 0, 0, 0), bytes)

        assertFailsWith(IllegalArgumentException::class, "ByteArray must be of size 9, but is 8") {
            bytes.setDouble(4, Double.fromBits(0x0102030405060708L))
        }

        assertFailsWith<IllegalArgumentException>("index must be >= 0, but is -1") {
            bytes.setDouble(-1, Double.fromBits(0x0102030405060708L))
        }
    }

    @Test
    fun testSetUnsignedByte() {
        val bytes = mutableListOf<Byte>(1, 2, 3, 4, 5, 6, 7, 8)
        bytes.setUnsignedByte(0, 0xFFu)
        assertEquals(listOf<Byte>(-1, 2, 3, 4, 5, 6, 7, 8), bytes)

        bytes.setUnsignedByte(4, 0xFFu)
        assertEquals(listOf<Byte>(-1, 2, 3, 4, -1, 6, 7, 8), bytes)

        assertFailsWith(IllegalArgumentException::class, "ByteArray must be of size 9, but is 8") {
            bytes.setUnsignedByte(8, 0xFFu)
        }

        assertFailsWith<IllegalArgumentException>("index must be >= 0, but is -1") {
            bytes.setUnsignedByte(-1, 0xFFu)
        }
    }

    @Test
    fun testSetUnsignedShort() {
        val bytes = mutableListOf<Byte>(1, 2, 3, 4, 5, 6, 7, 8)
        bytes.setUnsignedShort(0, 0xFFFFu)
        assertEquals(listOf<Byte>(-1, -1, 3, 4, 5, 6, 7, 8), bytes)

        bytes.setUnsignedShort(4, 0xFFFFu)
        assertEquals(listOf<Byte>(-1, -1, 3, 4, -1, -1, 7, 8), bytes)

        assertFailsWith(IllegalArgumentException::class, "ByteArray must be of size 9, but is 8") {
            bytes.setUnsignedShort(7, 0xFFFFu)
        }

        assertFailsWith<IllegalArgumentException>("index must be >= 0, but is -1") {
            bytes.setUnsignedShort(-1, 0xFFFFu)
        }
    }

    @Test
    fun testSetUnsignedInt() {
        val bytes = mutableListOf<Byte>(1, 2, 3, 4, 5, 6, 7, 8)
        bytes.setUnsignedInt(0, 0xFFFFFFFFu)
        assertEquals(listOf<Byte>(-1, -1, -1, -1, 5, 6, 7, 8), bytes)

        bytes.setUnsignedInt(4, 0xFFFFFFFFu)
        assertEquals(listOf<Byte>(-1, -1, -1, -1, -1, -1, -1, -1), bytes)

        assertFailsWith(IllegalArgumentException::class, "ByteArray must be of size 9, but is 8") {
            bytes.setUnsignedInt(7, 0xFFFFFFFFu)
        }

        assertFailsWith<IllegalArgumentException>("index must be >= 0, but is -1") {
            bytes.setUnsignedInt(-1, 0xFFFFFFFFu)
        }
    }

    @Test
    fun testSetUnsignedLong() {
        val bytes = mutableListOf<Byte>(1, 2, 3, 4, 5, 6, 7, 8)
        bytes.setUnsignedLong(0, 0xFFFFFFFFFFFFFFFFuL)
        assertEquals(listOf<Byte>(-1, -1, -1, -1, -1, -1, -1, -1), bytes)

        assertFailsWith(IllegalArgumentException::class, "ByteArray must be of size 9, but is 8") {
            bytes.setUnsignedLong(7, 0xFFFFFFFFFFFFFFFFuL)
        }

        assertFailsWith<IllegalArgumentException>("index must be >= 0, but is -1") {
            bytes.setUnsignedLong(-1, 0xFFFFFFFFFFFFFFFFuL)
        }
    }

    @Test
    fun testGetByte() {
        val bytes = listOf<Byte>(1, 2, 3, 4, 5, 6, 7, 8)
        assertEquals(1, bytes.getByte(0))
        assertEquals(2, bytes.getByte(1))
        assertEquals(3, bytes.getByte(2))
        assertEquals(4, bytes.getByte(3))
        assertEquals(5, bytes.getByte(4))
        assertEquals(6, bytes.getByte(5))
        assertEquals(7, bytes.getByte(6))
        assertEquals(8, bytes.getByte(7))

        assertFailsWith(IllegalArgumentException::class, "ByteArray must be of size 10, but is 8") {
            bytes.getByte(8)
        }

        assertFailsWith<IllegalArgumentException>("index must be >= 0, but is -1") {
            bytes.getByte(-1)
        }
    }

    @Test
    fun testGetShort() {
        val bytes = listOf<Byte>(1, 2, 3, 4, 5, 6, 7, 8)
        assertEquals(0x0102, bytes.getShort(0))
        assertEquals(0x0304, bytes.getShort(2))
        assertEquals(0x0506, bytes.getShort(4))
        assertEquals(0x0708, bytes.getShort(6))
        assertEquals(0x0203, bytes.getShort(1))

        assertFailsWith(IllegalArgumentException::class, "ByteArray must be of size 10, but is 8") {
            bytes.getShort(8)
        }

        assertFailsWith<IllegalArgumentException>("index must be >= 0, but is -1") {
            bytes.getShort(-1)
        }
    }

    @Test
    fun testGetInt() {
        val bytes = listOf<Byte>(1, 2, 3, 4, 5, 6, 7, 8)
        assertEquals(0x01020304, bytes.getInt(0))
        assertEquals(0x05060708, bytes.getInt(4))
        assertEquals(0x02030405, bytes.getInt(1))

        assertFailsWith(IllegalArgumentException::class, "ByteArray must be of size 10, but is 8") {
            bytes.getInt(8)
        }

        assertFailsWith<IllegalArgumentException>("index must be >= 0, but is -1") {
            bytes.getInt(-1)
        }
    }

    @Test
    fun testGetLong() {
        val bytes = listOf<Byte>(1, 2, 3, 4, 5, 6, 7, 8)
        assertEquals(0x0102030405060708L, bytes.getLong(0))

        assertFailsWith(IllegalArgumentException::class, "ByteArray must be of size 10, but is 8") {
            bytes.getLong(1)
        }

        assertFailsWith<IllegalArgumentException>("index must be >= 0, but is -1") {
            bytes.getLong(-1)
        }
    }

    @Test
    fun testGetFloat() {
        val bytes = listOf<Byte>(1, 2, 3, 4, 5, 6, 7, 8)
        assertEquals(Float.fromBits(0x01020304), bytes.getFloat(0))
        assertEquals(Float.fromBits(0x05060708), bytes.getFloat(4))
        assertEquals(Float.fromBits(0x02030405), bytes.getFloat(1))

        assertFailsWith(IllegalArgumentException::class, "ByteArray must be of size 10, but is 8") {
            bytes.getFloat(5)
        }

        assertFailsWith<IllegalArgumentException>("index must be >= 0, but is -1") {
            bytes.getFloat(-1)
        }
    }

    @Test
    fun testGetDouble() {
        val bytes = listOf<Byte>(1, 2, 3, 4, 5, 6, 7, 8)
        assertEquals(Double.fromBits(0x0102030405060708L), bytes.getDouble(0))

        assertFailsWith(IllegalArgumentException::class, "ByteArray must be of size 10, but is 8") {
            bytes.getDouble(1)
        }

        assertFailsWith<IllegalArgumentException>("index must be >= 0, but is -1") {
            bytes.getDouble(-1)
        }
    }

    @Test
    fun testGetUnsignedByte() {
        val bytes = listOf(1, 2, 3, 4, 5, 6, 7, 255u.toByte())
        assertEquals(1u, bytes.getUnsignedByte(0))
        assertEquals(2u, bytes.getUnsignedByte(1))
        assertEquals(3u, bytes.getUnsignedByte(2))
        assertEquals(4u, bytes.getUnsignedByte(3))
        assertEquals(5u, bytes.getUnsignedByte(4))
        assertEquals(6u, bytes.getUnsignedByte(5))
        assertEquals(7u, bytes.getUnsignedByte(6))
        assertEquals(255u, bytes.getUnsignedByte(7))
    }

    @Test
    fun testGetUnsignedShort() {
        val bytes = listOf<Byte>(1, 2, 3, 4, 5, 6, 7, 8)
        assertEquals(0x0102u, bytes.getUnsignedShort(0))
        assertEquals(0x0304u, bytes.getUnsignedShort(2))
        assertEquals(0x0506u, bytes.getUnsignedShort(4))
        assertEquals(0x0708u, bytes.getUnsignedShort(6))
        assertEquals(0x0203u, bytes.getUnsignedShort(1))
    }

    @Test
    fun testGetUnsignedInt() {
        val bytes = listOf<Byte>(1, 2, 3, 4, 5, 6, 7, 8)
        assertEquals(0x01020304u, bytes.getUnsignedInt(0))
        assertEquals(0x05060708u, bytes.getUnsignedInt(4))
        assertEquals(0x02030405u, bytes.getUnsignedInt(1))
    }

    @Test
    fun testGetUnsignedLong() {
        val bytes = listOf<Byte>(1, 2, 3, 4, 5, 6, 7, 8)
        assertEquals(0x0102030405060708uL, bytes.getUnsignedLong(0))
    }

    @Test
    fun testRemoveLastByte() {
        val bytes = mutableListOf<Byte>(1, 2, 3, 4, 5, 6, 7, 8)
        assertEquals(8, bytes.size)
        assertEquals(8, bytes.removeLastByte())
        assertEquals(7, bytes.size)
        assertEquals(7, bytes.removeLastByte())
        assertEquals(6, bytes.size)
        assertEquals(6, bytes.removeLastByte())
        assertEquals(5, bytes.size)
        assertEquals(5, bytes.removeLastByte())
        assertEquals(4, bytes.size)
        assertEquals(4, bytes.removeLastByte())
        assertEquals(3, bytes.size)
        assertEquals(3, bytes.removeLastByte())
        assertEquals(2, bytes.size)
        assertEquals(2, bytes.removeLastByte())
        assertEquals(1, bytes.size)
        assertEquals(1, bytes.removeLastByte())
        assertEquals(0, bytes.size)

        assertFailsWith(IllegalArgumentException::class, "ByteArray must be of size 10, but is 8") {
            bytes.removeLastByte()
        }
    }

    @Test
    fun testRemoveLastShort() {
        val bytes = mutableListOf<Byte>(1, 2, 3, 4, 5, 6, 7, 8)
        assertEquals(8, bytes.size)
        assertEquals(0x0708, bytes.removeLastShort())
        assertEquals(6, bytes.size)
        assertEquals(0x0506, bytes.removeLastShort())
        assertEquals(4, bytes.size)
        assertEquals(0x0304, bytes.removeLastShort())
        assertEquals(2, bytes.size)
        assertEquals(0x0102, bytes.removeLastShort())
        assertEquals(0, bytes.size)

        assertFailsWith(IllegalArgumentException::class, "ByteArray must be of size 2, but is 0") {
            bytes.removeLastShort()
        }
    }

    @Test
    fun testRemoveLastInt() {
        val bytes = mutableListOf<Byte>(1, 2, 3, 4, 5, 6, 7, 8)
        assertEquals(8, bytes.size)
        assertEquals(0x05060708, bytes.removeLastInt())
        assertEquals(4, bytes.size)
        assertEquals(0x01020304, bytes.removeLastInt())
        assertEquals(0, bytes.size)

        assertFailsWith(IllegalArgumentException::class, "ByteArray must be of size 4, but is 0") {
            bytes.removeLastInt()
        }
    }

    @Test
    fun testRemoveLastLong() {
        val bytes = mutableListOf<Byte>(1, 2, 3, 4, 5, 6, 7, 8)
        assertEquals(8, bytes.size)
        assertEquals(0x0102030405060708L, bytes.removeLastLong())
        assertEquals(0, bytes.size)

        assertFailsWith(IllegalArgumentException::class, "ByteArray must be of size 8, but is 0") {
            bytes.removeLastLong()
        }
    }

    @Test
    fun testRemoveLastFloat() {
        val bytes = mutableListOf<Byte>(1, 2, 3, 4, 5, 6, 7, 8)
        assertEquals(8, bytes.size)
        assertEquals(Float.fromBits(0x05060708), bytes.removeLastFloat())
        assertEquals(4, bytes.size)
        assertEquals(Float.fromBits(0x01020304), bytes.removeLastFloat())
        assertEquals(0, bytes.size)

        assertFailsWith(IllegalArgumentException::class, "ByteArray must be of size 4, but is 0") {
            bytes.removeLastFloat()
        }
    }

    @Test
    fun testRemoveLastDouble() {
        val bytes = mutableListOf<Byte>(1, 2, 3, 4, 5, 6, 7, 8)
        assertEquals(8, bytes.size)
        assertEquals(Double.fromBits(0x0102030405060708L), bytes.removeLastDouble())
        assertEquals(0, bytes.size)

        assertFailsWith(IllegalArgumentException::class, "ByteArray must be of size 8, but is 0") {
            bytes.removeLastDouble()
        }
    }

    @Test
    fun testRemoveLastUnsignedByte() {
        val bytes = mutableListOf<Byte>(1, 2, 3, 4, 5, 6, 7, 8)
        assertEquals(8, bytes.size)
        assertEquals(8u, bytes.removeLastUnsignedByte())
        assertEquals(7, bytes.size)
        assertEquals(7u, bytes.removeLastUnsignedByte())
        assertEquals(6, bytes.size)
        assertEquals(6u, bytes.removeLastUnsignedByte())
        assertEquals(5, bytes.size)
        assertEquals(5u, bytes.removeLastUnsignedByte())
        assertEquals(4, bytes.size)
        assertEquals(4u, bytes.removeLastUnsignedByte())
        assertEquals(3, bytes.size)
        assertEquals(3u, bytes.removeLastUnsignedByte())
        assertEquals(2, bytes.size)
        assertEquals(2u, bytes.removeLastUnsignedByte())
        assertEquals(1, bytes.size)
        assertEquals(1u, bytes.removeLastUnsignedByte())
        assertEquals(0, bytes.size)

        assertFailsWith(IllegalArgumentException::class, "ByteArray must be of size 10, but is 8") {
            bytes.removeLastUnsignedByte()
        }
    }

    @Test
    fun testRemoveLastUnsignedShort() {
        val bytes = mutableListOf<Byte>(1, 2, 3, 4, 5, 6, 7, 8)
        assertEquals(8, bytes.size)
        assertEquals(0x0708u, bytes.removeLastUnsignedShort())
        assertEquals(6, bytes.size)
        assertEquals(0x0506u, bytes.removeLastUnsignedShort())
        assertEquals(4, bytes.size)
        assertEquals(0x0304u, bytes.removeLastUnsignedShort())
        assertEquals(2, bytes.size)
        assertEquals(0x0102u, bytes.removeLastUnsignedShort())
        assertEquals(0, bytes.size)

        assertFailsWith(IllegalArgumentException::class, "ByteArray must be of size 2, but is 0") {
            bytes.removeLastUnsignedShort()
        }
    }

    @Test
    fun testRemoveLastUnsignedInt() {
        val bytes = mutableListOf<Byte>(1, 2, 3, 4, 5, 6, 7, 8)
        assertEquals(8, bytes.size)
        assertEquals(0x05060708u, bytes.removeLastUnsignedInt())
        assertEquals(4, bytes.size)
        assertEquals(0x01020304u, bytes.removeLastUnsignedInt())
        assertEquals(0, bytes.size)

        assertFailsWith(IllegalArgumentException::class, "ByteArray must be of size 4, but is 0") {
            bytes.removeLastUnsignedInt()
        }
    }

    @Test
    fun testRemoveLastUnsignedLong() {
        val bytes = mutableListOf<Byte>(1, 2, 3, 4, 5, 6, 7, 8)
        assertEquals(8, bytes.size)
        assertEquals(0x0102030405060708uL, bytes.removeLastUnsignedLong())
        assertEquals(0, bytes.size)

        assertFailsWith(IllegalArgumentException::class, "ByteArray must be of size 8, but is 0") {
            bytes.removeLastUnsignedLong()
        }
    }

    @Test
    fun testGetBytes() {
        val bytes = listOf<Byte>(1, 2, 3, 4, 5, 6, 7, 8)
        assertEquals(0x01020304, bytes.getBytes(0, 4).toInt())
        assertEquals(0x05060708, bytes.getBytes(4, 4).toInt())

        assertFailsWith(IllegalArgumentException::class, "ByteArray must be of size 9, but is 8") {
            bytes.getBytes(5, 4)
        }

        assertFailsWith<IllegalArgumentException>("index must be >= 0, but is -1") {
            bytes.getBytes(-1, 4)
        }
    }

    @Test
    fun testToHexString() {
        val bytes = listOf<Byte>(0, 1, 2, 3, 4, 5, 6, 7, 8, -1)
        assertEquals("000102030405060708ff", bytes.toHexString())
    }

    @Test
    fun testToUtf8String() {
        val bytes = listOf<Byte>(1, 2, 3, 4, 5, 6, 7, 8)
        assertEquals("\u0001\u0002\u0003\u0004\u0005\u0006\u0007\u0008", bytes.toUtf8String())
    }

    @Test
    fun testAppendWithByte() {
        val bytes = mutableListOf<Byte>()
        bytes.append(1.toByte())
        assertEquals(listOf<Byte>(1), bytes)
        bytes.append(2.toByte())
        assertEquals(listOf<Byte>(1, 2), bytes)
        bytes.append(3.toByte())
        assertEquals(listOf<Byte>(1, 2, 3), bytes)
        bytes.append(4.toByte())
        assertEquals(listOf<Byte>(1, 2, 3, 4), bytes)
        bytes.append(5.toByte())
        assertEquals(listOf<Byte>(1, 2, 3, 4, 5), bytes)
        bytes.append(6.toByte())
        assertEquals(listOf<Byte>(1, 2, 3, 4, 5, 6), bytes)
        bytes.append(7.toByte())
        assertEquals(listOf<Byte>(1, 2, 3, 4, 5, 6, 7), bytes)
        bytes.append(8.toByte())
        assertEquals(listOf<Byte>(1, 2, 3, 4, 5, 6, 7, 8), bytes)
    }

    @Test
    fun testAppendWithShort() {
        val bytes = mutableListOf<Byte>()
        bytes.append(0x0102.toShort())
        assertEquals(listOf<Byte>(1, 2), bytes)
        bytes.append(0x0304.toShort())
        assertEquals(listOf<Byte>(1, 2, 3, 4), bytes)
        bytes.append(0x0506.toShort())
        assertEquals(listOf<Byte>(1, 2, 3, 4, 5, 6), bytes)
        bytes.append(0x0708.toShort())
        assertEquals(listOf<Byte>(1, 2, 3, 4, 5, 6, 7, 8), bytes)
    }

    @Test
    fun testAppendWithInt() {
        val bytes = mutableListOf<Byte>()
        bytes.append(0x01020304)
        assertEquals(listOf<Byte>(1, 2, 3, 4), bytes)
        bytes.append(0x05060708)
        assertEquals(listOf<Byte>(1, 2, 3, 4, 5, 6, 7, 8), bytes)
    }

    @Test
    fun testAppendWithLong() {
        val bytes = mutableListOf<Byte>()
        bytes.append(0x0102030405060708L)
        assertEquals(listOf<Byte>(1, 2, 3, 4, 5, 6, 7, 8), bytes)
    }

    @Test
    fun testAppendWithFloat() {
        val bytes = mutableListOf<Byte>()
        bytes.append(Float.fromBits(0x01020304))
        assertEquals(listOf<Byte>(1, 2, 3, 4), bytes)
        bytes.append(Float.fromBits(0x05060708))
        assertEquals(listOf<Byte>(1, 2, 3, 4, 5, 6, 7, 8), bytes)
    }

    @Test
    fun testAppendWithDouble() {
        val bytes = mutableListOf<Byte>()
        bytes.append(Double.fromBits(0x0102030405060708L))
        assertEquals(listOf<Byte>(1, 2, 3, 4, 5, 6, 7, 8), bytes)
    }

    @Test
    fun testAppendWithUnsignedByte() {
        val bytes = mutableListOf<Byte>()
        bytes.append(1u.toUByte())
        assertEquals(listOf<Byte>(1), bytes)
        bytes.append(2u.toUByte())
        assertEquals(listOf<Byte>(1, 2), bytes)
        bytes.append(3u.toUByte())
        assertEquals(listOf<Byte>(1, 2, 3), bytes)
        bytes.append(4u.toUByte())
        assertEquals(listOf<Byte>(1, 2, 3, 4), bytes)
        bytes.append(5u.toUByte())
        assertEquals(listOf<Byte>(1, 2, 3, 4, 5), bytes)
        bytes.append(6u.toUByte())
        assertEquals(listOf<Byte>(1, 2, 3, 4, 5, 6), bytes)
        bytes.append(7u.toUByte())
        assertEquals(listOf<Byte>(1, 2, 3, 4, 5, 6, 7), bytes)
        bytes.append(8u.toUByte())
        assertEquals(listOf<Byte>(1, 2, 3, 4, 5, 6, 7, 8), bytes)
    }

    @Test
    fun testAppendWithUnsignedShort() {
        val bytes = mutableListOf<Byte>()
        bytes.append(0x0102u.toUShort())
        assertEquals(listOf<Byte>(1, 2), bytes)
        bytes.append(0x0304u.toUShort())
        assertEquals(listOf<Byte>(1, 2, 3, 4), bytes)
        bytes.append(0x0506u.toUShort())
        assertEquals(listOf<Byte>(1, 2, 3, 4, 5, 6), bytes)
        bytes.append(0x0708u.toUShort())
        assertEquals(listOf<Byte>(1, 2, 3, 4, 5, 6, 7, 8), bytes)
    }

    @Test
    fun testAppendWithUnsignedInt() {
        val bytes = mutableListOf<Byte>()
        bytes.append(0x01020304u)
        assertEquals(listOf<Byte>(1, 2, 3, 4), bytes)
        bytes.append(0x05060708u)
        assertEquals(listOf<Byte>(1, 2, 3, 4, 5, 6, 7, 8), bytes)
    }

    @Test
    fun testAppendWithUnsignedLong() {
        val bytes = mutableListOf<Byte>()
        bytes.append(0x0102030405060708uL)
        assertEquals(listOf<Byte>(1, 2, 3, 4, 5, 6, 7, 8), bytes)
    }
}
