package com.shakelang.shake.util.jvmlib.infos.constants

import com.shakelang.util.io.streaming.input.inputStream
import com.shakelang.shake.util.primitives.bytes.toBytes
import kotlin.test.Test
import kotlin.test.assertEquals

class ConstantFloatTests {

    @Test
    fun test() {
        val constant = ConstantFloatInfo(1.0f)
        assertCompare(1.0f, constant.value)
        assertEquals(4, constant.tag)
        assertEquals("constant_float_info", constant.tagName)
    }

    @Test
    fun testContentFromStream() {
        val inputStream = 1.0f.toBytes().inputStream()
        val constant = ConstantFloatInfo.contentsFromStream(inputStream)
        assertCompare(1.0f, constant.value)
        assertEquals(4, constant.tag)
        assertEquals("constant_float_info", constant.tagName)
    }

    @Test
    fun testFromStream() {
        val inputStream = byteArrayOf(0x04, *1.0f.toBytes()).inputStream()
        val constant = ConstantFloatInfo.fromStream(inputStream)
        assertCompare(1.0f, constant.value)
        assertEquals(4, constant.tag)
        assertEquals("constant_float_info", constant.tagName)
    }

    @Test
    fun testContentsFromBytes() {
        val bytes = 1.0f.toBytes()
        val constant = ConstantFloatInfo.contentsFromBytes(bytes)
        assertCompare(1.0f, constant.value)
        assertEquals(4, constant.tag)
        assertEquals("constant_float_info", constant.tagName)
    }

    @Test
    fun testFromBytes() {
        val bytes = byteArrayOf(0x04, *1.0f.toBytes())
        val constant = ConstantFloatInfo.fromBytes(bytes)
        assertCompare(1.0f, constant.value)
        assertEquals(4, constant.tag)
        assertEquals("constant_float_info", constant.tagName)
    }

    @Test
    fun testToBytes() {
        val constant = ConstantFloatInfo(1.0f)
        assertEquals(byteArrayOf(0x04, *1.0f.toBytes()).toList(), constant.toBytes().toList())
    }

    @Test
    fun testToJson() {
        val constant = ConstantFloatInfo(1.0f)
        val json = constant.toJson()
        assertEquals(3, json.size)
        assertEquals(1.0, json["value"] as Double, 0.01)
        assertEquals(4, json["tag"])
        assertEquals("constant_float_info", json["tag_type"])
    }
}
