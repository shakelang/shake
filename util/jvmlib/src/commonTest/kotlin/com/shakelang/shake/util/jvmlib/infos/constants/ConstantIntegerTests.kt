package com.shakelang.shake.util.jvmlib.infos.constants

import com.shakelang.util.io.streaming.input.inputStream
import kotlin.test.Test
import kotlin.test.assertEquals

class ConstantIntegerTests {

    @Test
    fun test() {
        val constant = ConstantIntegerInfo(1)
        assertEquals(1, constant.value)
        assertEquals(3, constant.tag)
        assertEquals("constant_integer_info", constant.tagName)
    }

    @Test
    fun testContentFromStream() {
        val inputStream = byteArrayOf(0x00, 0x00, 0x00, 0x01).inputStream()
        val constant = ConstantIntegerInfo.contentsFromStream(inputStream)
        assertEquals(1, constant.value)
        assertEquals(3, constant.tag)
        assertEquals("constant_integer_info", constant.tagName)
    }

    @Test
    fun testFromStream() {
        val inputStream = byteArrayOf(0x03, 0x00, 0x00, 0x00, 0x01).inputStream()
        val constant = ConstantIntegerInfo.fromStream(inputStream)
        assertEquals(1, constant.value)
        assertEquals(3, constant.tag)
        assertEquals("constant_integer_info", constant.tagName)
    }

    @Test
    fun testContentsFromBytes() {
        val bytes = byteArrayOf(0x00, 0x00, 0x00, 0x01)
        val constant = ConstantIntegerInfo.contentsFromBytes(bytes)
        assertEquals(1, constant.value)
        assertEquals(3, constant.tag)
        assertEquals("constant_integer_info", constant.tagName)
    }

    @Test
    fun testFromBytes() {
        val bytes = byteArrayOf(0x03, 0x00, 0x00, 0x00, 0x01)
        val constant = ConstantIntegerInfo.fromBytes(bytes)
        assertEquals(1, constant.value)
        assertEquals(3, constant.tag)
        assertEquals("constant_integer_info", constant.tagName)
    }

    @Test
    fun testToBytes() {
        val constant = ConstantIntegerInfo(1)
        assertEquals(byteArrayOf(0x03, 0x00, 0x00, 0x00, 0x01).toList(), constant.toBytes().toList())
    }

    @Test
    fun testToJson() {
        val constant = ConstantIntegerInfo(1)
        val json = constant.toJson()
        assertEquals(3, json.size)
        assertEquals(1, json["value"])
        assertEquals(3, json["tag"])
        assertEquals("constant_integer_info", json["tag_type"])
    }
}
