package io.github.shakelang.parseutils.bytes

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

}