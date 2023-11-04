package io.github.shakelang.shake.util.jvmlib.infos.constants

import io.github.shakelang.shake.util.io.streaming.input.inputStream
import io.github.shakelang.shake.util.primitives.bytes.toBytes
import kotlin.test.Test
import kotlin.test.assertEquals

class ConstantUtf8Tests {

    @Test
    fun test() {
        val constant = ConstantUtf8Info("Hello, World!")
        assertEquals("Hello, World!", constant.value)
        assertEquals(1, constant.tag)
        assertEquals("constant_utf8_info", constant.tagName)
    }

    @Test
    fun testContentFromStream() {
        val inputStream = byteArrayOf(0x00, 0x0d, *"Hello, World!".toBytes()).inputStream()
        val constant = ConstantUtf8Info.contentsFromStream(inputStream)
        assertEquals("Hello, World!", constant.value)
        assertEquals(1, constant.tag)
        assertEquals("constant_utf8_info", constant.tagName)
    }

    @Test
    fun testFromStream() {
        val inputStream = byteArrayOf(0x01, 0x00, 0x0d, *"Hello, World!".toBytes()).inputStream()
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

    @Test
    fun testToJson() {
        val constant = ConstantUtf8Info("Hello, World!")
        val json = constant.toJson()
        assertEquals(3, json.size)
        assertEquals(1, json["tag"])
        assertEquals("constant_utf8_info", json["tag_type"])
        assertEquals("Hello, World!", json["value"])
    }

}