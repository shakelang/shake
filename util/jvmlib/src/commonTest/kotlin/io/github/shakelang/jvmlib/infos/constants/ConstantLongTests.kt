package io.github.shakelang.jvmlib.infos.constants

import io.github.shakelang.parseutils.bytes.stream
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
        val inputStream = byteArrayOf(0x00, 0x00, 0x00, 0x00, 0x00, 0x00, 0x00, 0x01).stream()
        val constant = ConstantLongInfo.contentsFromStream(inputStream)
        assertEquals(1, constant.value)
        assertEquals(5, constant.tag)
        assertEquals("constant_long_info", constant.tagName)
    }

    @Test
    fun testFromStream() {
        val inputStream = byteArrayOf(0x05, 0x00, 0x00, 0x00, 0x00, 0x00, 0x00, 0x00, 0x01).stream()
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
}