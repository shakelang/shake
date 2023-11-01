package io.github.shakelang.jvmlib.infos.constants

import io.github.shakelang.shake.util.io.streaming.input.inputStream
import io.github.shakelang.primitives.bytes.toBytes
import kotlin.math.abs
import kotlin.math.max
import kotlin.test.Test
import kotlin.test.assertEquals
import kotlin.test.assertTrue

class ConstantDoubleTests {

    @Test
    fun test() {
        val constant = ConstantDoubleInfo(1.0)
        assertCompare(1.0, constant.value)
        assertEquals(6, constant.tag)
        assertEquals("constant_double_info", constant.tagName)
    }

    @Test
    fun testContentFromStream() {
        val inputStream = 1.0.toBytes().inputStream()
        val constant = ConstantDoubleInfo.contentsFromStream(inputStream)
        assertCompare(1.0, constant.value)
        assertEquals(6, constant.tag)
        assertEquals("constant_double_info", constant.tagName)
    }

    @Test
    fun testFromStream() {
        val inputStream = byteArrayOf(0x06, *1.0.toBytes()).inputStream()
        val constant = ConstantDoubleInfo.fromStream(inputStream)
        assertCompare(1.0, constant.value)
        assertEquals(6, constant.tag)
        assertEquals("constant_double_info", constant.tagName)
    }

    @Test
    fun testContentsFromBytes() {
        val bytes = 1.0.toBytes()
        val constant = ConstantDoubleInfo.contentsFromBytes(bytes)
        assertCompare(1.0, constant.value)
        assertEquals(6, constant.tag)
        assertEquals("constant_double_info", constant.tagName)
    }

    @Test
    fun testFromBytes() {
        val bytes = byteArrayOf(0x06, *1.0.toBytes())
        val constant = ConstantDoubleInfo.fromBytes(bytes)
        assertCompare(1.0, constant.value)
        assertEquals(6, constant.tag)
        assertEquals("constant_double_info", constant.tagName)
    }

    @Test
    fun testToBytes() {
        val constant = ConstantDoubleInfo(1.0)
        assertEquals(byteArrayOf(0x06, *1.0.toBytes()).toList(), constant.toBytes().toList())
    }

    @Test
    fun testToJson() {
        val constant = ConstantDoubleInfo(1.0)
        val json = constant.toJson()
        assertEquals(json.size, 3)
        assertTrue(json.containsKey("tag"))
        assertTrue(json.containsKey("tag_type"))
        assertTrue(json.containsKey("value"))
        assertEquals(6, json["tag"])
        assertEquals("constant_double_info", json["tag_type"])
        assertEquals(1.0, json["value"])
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