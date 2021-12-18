package io.github.shakelang.jvmlib.infos.constants

import io.github.shakelang.parseutils.bytes.stream
import io.github.shakelang.parseutils.bytes.toBytes
import kotlin.test.Test
import kotlin.test.assertEquals

class ConstantUtf8Tests {

    @Test
    fun test() {
        val constant = ConstantUtf8Info("Hello, World!")
        assertEquals("Hello, World!", constant.value )
        assertEquals(1, constant.tag)
        assertEquals("constant_utf8_info", constant.tagName)
    }

    @Test
    fun testContentFromStream() {
        val inputStream = byteArrayOf(0x00, 0x0d, *"Hello, World!".toBytes()).stream()
        val constant = ConstantUtf8Info.contentsFromStream(inputStream)
        assertEquals("Hello, World!", constant.value)
        assertEquals(1, constant.tag)
        assertEquals("constant_utf8_info", constant.tagName)
    }

    @Test
    fun testFromStream() {
        val inputStream = byteArrayOf(0x01, 0x00, 0x0d, *"Hello, World!".toBytes()).stream()
        val constant = ConstantUtf8Info.fromStream(inputStream)
        assertEquals("Hello, World!", constant.value)
        assertEquals(1, constant.tag)
        assertEquals("constant_utf8_info", constant.tagName)
    }

    @Test
    fun testContentsFromBytes() {
        val bytes = byteArrayOf(0x00, 0x0d, *"Hello, World!".toBytes())
        val constant = ConstantUtf8Info.contentsFromBytes(bytes)
        assertEquals("Hello, World!", constant.value)
        assertEquals(1, constant.tag)
        assertEquals("constant_utf8_info", constant.tagName)
    }

    @Test
    fun testFromBytes() {
        val bytes = byteArrayOf(0x01, 0x00, 0x0d, *"Hello, World!".toBytes())
        val constant = ConstantUtf8Info.fromBytes(bytes)
        assertEquals("Hello, World!", constant.value)
        assertEquals(1, constant.tag)
        assertEquals("constant_utf8_info", constant.tagName)
    }

    @Test
    fun testToBytes() {
        val constant = ConstantUtf8Info("Hello, World!")
        assertEquals(byteArrayOf(0x01, 0x00, 0x0d, *"Hello, World!".toBytes()).toList(), constant.toBytes().toList())
    }

}