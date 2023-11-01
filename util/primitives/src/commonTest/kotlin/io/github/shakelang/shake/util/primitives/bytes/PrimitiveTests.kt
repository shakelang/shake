package io.github.shakelang.shake.util.primitives.bytes

import kotlin.test.Test
import kotlin.test.assertEquals

class PrimitiveTests {

    @Test
    fun testByteToBytes() {
        val b = 0xFFu.toByte()
        val bytes = b.toBytes()
        assertEquals(1, bytes.size)
        assertEquals(b, bytes[0])
    }

    @Test
    fun testShortToBytes() {
        val s = 0xFFFFu.toShort()
        val bytes = s.toBytes()
        assertEquals(2, bytes.size)
        assertEquals(0xFFu.toByte(), bytes[0])
        assertEquals(0xFFu.toByte(), bytes[1])
    }

    @Test
    fun testIntToBytes() {
        val i = 0xFFFFFFFFu
        val bytes = i.toBytes()
        assertEquals(4, bytes.size)
        assertEquals(0xFFu.toByte(), bytes[0])
        assertEquals(0xFFu.toByte(), bytes[1])
        assertEquals(0xFFu.toByte(), bytes[2])
        assertEquals(0xFFu.toByte(), bytes[3])
    }

    @Test
    fun testLongToBytes() {
        val l = 0xFFFFFFFFFFFFFFFFuL
        val bytes = l.toBytes()
        assertEquals(8, bytes.size)
        assertEquals(0xFFu.toByte(), bytes[0])
        assertEquals(0xFFu.toByte(), bytes[1])
        assertEquals(0xFFu.toByte(), bytes[2])
        assertEquals(0xFFu.toByte(), bytes[3])
        assertEquals(0xFFu.toByte(), bytes[4])
        assertEquals(0xFFu.toByte(), bytes[5])
        assertEquals(0xFFu.toByte(), bytes[6])
        assertEquals(0xFFu.toByte(), bytes[7])
    }

    @Test
    fun testFloatToBytes() {
        val f = Float.fromBits(0x3f99999a)
        val bytes = f.toBytes()
        assertEquals(4, bytes.size)
        assertEquals(0x3fu.toByte(), bytes[0])
        assertEquals(0x99u.toByte(), bytes[1])
        assertEquals(0x99u.toByte(), bytes[2])
        assertEquals(0x9au.toByte(), bytes[3])
    }

    @Test
    fun testDoubleToBytes() {
        val d = Double.fromBits(0x3ff3333333333333)
        val bytes = d.toBytes()
        assertEquals(8, bytes.size)
        assertEquals(0x3fu.toByte(), bytes[0])
        assertEquals(0xf3u.toByte(), bytes[1])
        assertEquals(0x33u.toByte(), bytes[2])
        assertEquals(0x33u.toByte(), bytes[3])
        assertEquals(0x33u.toByte(), bytes[4])
        assertEquals(0x33u.toByte(), bytes[5])
        assertEquals(0x33u.toByte(), bytes[6])
        assertEquals(0x33u.toByte(), bytes[7])
    }

    @Test
    fun testUByteToBytes() {
        val b: UByte = 0xFFu
        val bytes = b.toBytes()
        assertEquals(1, bytes.size)
        assertEquals(b.toByte(), bytes[0])
    }

    @Test
    fun testUShortToBytes() {
        val s: UShort = 0xFFAAu
        val bytes = s.toBytes()
        assertEquals(2, bytes.size)
        assertEquals(0xFFu.toByte(), bytes[0])
        assertEquals(0xAAu.toByte(), bytes[1])
    }

    @Test
    fun testUIntToBytes() {
        val i: UInt = 0xFFAABBCCu
        val bytes = i.toBytes()
        assertEquals(4, bytes.size)
        assertEquals(0xFFu.toByte(), bytes[0])
        assertEquals(0xAAu.toByte(), bytes[1])
        assertEquals(0xBBu.toByte(), bytes[2])
        assertEquals(0xCCu.toByte(), bytes[3])
    }

    @Test
    fun testULongToBytes() {
        val l: ULong = 0xFFAABBCCDDEE0011uL
        val bytes = l.toBytes()
        assertEquals(8, bytes.size)
        assertEquals(0xFFu.toByte(), bytes[0])
        assertEquals(0xAAu.toByte(), bytes[1])
        assertEquals(0xBBu.toByte(), bytes[2])
        assertEquals(0xCCu.toByte(), bytes[3])
        assertEquals(0xDDu.toByte(), bytes[4])
        assertEquals(0xEEu.toByte(), bytes[5])
        assertEquals(0x00u.toByte(), bytes[6])
        assertEquals(0x11u.toByte(), bytes[7])
    }

    @Test
    fun testByteOf() {
        val b = byteOf(1)
        assertEquals(1, b.toInt())
    }

    @Test
    fun testShortOf() {
        val s = shortOf(1, 2)
        assertEquals(0x0102, s)
    }

    @Test
    fun testIntOf() {
        val i = intOf(1, 2, 3, 4)
        assertEquals(0x01020304, i)
    }

    @Test
    fun testLongOf() {
        val l = longOf(1, 2, 3, 4, 5, 6, 7, 8)
        assertEquals(0x0102030405060708, l)
    }

    @Test
    fun testFloatOf() {
        val f = floatOf(1, 2, 3, 4)
        assertEquals(0x01020304, f.toBits())
    }

    @Test
    fun testDoubleOf() {
        val d = doubleOf(1, 2, 3, 4, 5, 6, 7, 8)
        assertEquals(0x0102030405060708, d.toBits())
    }

    @Test
    fun testUByteOf() {
        val b = ubyteOf(1)
        assertEquals(1u, b)
    }

    @Test
    fun testUShortOf() {
        val s = ushortOf(1, 2)
        assertEquals(0x0102u, s)
    }

    @Test
    fun testUIntOf() {
        val i = uintOf(1, 2, 3, 4)
        assertEquals(0x01020304u, i)
    }

    @Test
    fun testULongOf() {
        val l = ulongOf(1, 2, 3, 4, 5, 6, 7, 8)
        assertEquals(0x0102030405060708uL, l)
    }

    @Test
    fun testUnsignedByteOf() {
        val b = unsignedByteOf(1)
        assertEquals(1u, b)
    }

    @Test
    fun testUnsignedShortOf() {
        val s = unsignedShortOf(1, 2)
        assertEquals(0x0102u, s)
    }

    @Test
    fun testUnsignedIntOf() {
        val i = unsignedIntOf(1, 2, 3, 4)
        assertEquals(0x01020304u, i)
    }

    @Test
    fun testUnsignedLongOf() {
        val l = unsignedLongOf(1, 2, 3, 4, 5, 6, 7, 8)
        assertEquals(0x0102030405060708uL, l)
    }
}