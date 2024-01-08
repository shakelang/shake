package com.shakelang.util.jvmlib.infos.constants

import com.shakelang.util.io.streaming.input.inputStream
import kotlin.test.Test
import kotlin.test.assertEquals

class ConstantLongTests {

    @Test
    fun test() {
        val constant = ConstantLongInfo(1)
        assertEquals(1, constant.value)
        assertEquals(5, constant.tag)
        assertEquals("constant_long_info", constant.tagName)
    }

    @Test
    fun testContentFromStream() {
        val inputStream = byteArrayOf(0x00, 0x00, 0x00, 0x00, 0x00, 0x00, 0x00, 0x01).inputStream()
        val constant = ConstantLongInfo.contentsFromStream(inputStream)
        assertEquals(1, constant.value)
        assertEquals(5, constant.tag)
        assertEquals("constant_long_info", constant.tagName)
    }

    @Test
    fun testFromStream() {
        val inputStream = byteArrayOf(0x05, 0x00, 0x00, 0x00, 0x00, 0x00, 0x00, 0x00, 0x01).inputStream()
        val constant = ConstantLongInfo.fromStream(inputStream)
        assertEquals(1, constant.value)
        assertEquals(5, constant.tag)
        assertEquals("constant_long_info", constant.tagName)
    }

    @Test
    fun testContentsFromBytes() {
        val bytes = byteArrayOf(0x00, 0x00, 0x00, 0x00, 0x00, 0x00, 0x00, 0x01)
        val constant = ConstantLongInfo.contentsFromBytes(bytes)
        assertEquals(1, constant.value)
        assertEquals(5, constant.tag)
        assertEquals("constant_long_info", constant.tagName)
    }

    @Test
    fun testFromBytes() {
        val bytes = byteArrayOf(0x05, 0x00, 0x00, 0x00, 0x00, 0x00, 0x00, 0x00, 0x01)
        val constant = ConstantLongInfo.fromBytes(bytes)
        assertEquals(1, constant.value)
        assertEquals(5, constant.tag)
        assertEquals("constant_long_info", constant.tagName)
    }

    @Test
    fun testToBytes() {
        val constant = ConstantLongInfo(1)
        assertEquals(
            byteArrayOf(0x05, 0x00, 0x00, 0x00, 0x00, 0x00, 0x00, 0x00, 0x01).toList(),
            constant.toBytes().toList(),
        )
    }

    @Test
    fun testToJson() {
        val constant = ConstantLongInfo(1)
        val json = constant.toJson()
        assertEquals(3, json.size)
        assertEquals(1L, json["value"])
        assertEquals(5, json["tag"])
        assertEquals("constant_long_info", json["tag_type"])
    }
}
