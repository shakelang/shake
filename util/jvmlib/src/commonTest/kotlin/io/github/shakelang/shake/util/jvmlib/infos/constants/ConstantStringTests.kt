package io.github.shakelang.shake.util.jvmlib.infos.constants

import io.github.shakelang.shake.util.io.streaming.input.inputStream
import kotlin.test.Test
import kotlin.test.assertEquals

class ConstantStringTests {

    private val testConstants: Array<ConstantInfo> get() = arrayOf(ConstantUtf8Info("hello world"))

    @Test
    fun test() {
        val constant = ConstantClassInfo(2u)
        val pool = ConstantPool(mutableListOf(constant, *testConstants))
        assertEquals(pool[2], constant.value)
        assertEquals(2u, constant.valueIndex)
        assertEquals(7, constant.tag)
        assertEquals("constant_class_info", constant.tagName)
    }

    @Test
    fun testContentFromStream() {
        val inputStream = byteArrayOf(0x00, 0x02).inputStream()
        val constant = ConstantClassInfo.contentsFromStream(inputStream)
        val pool = ConstantPool(mutableListOf(constant, *testConstants))
        assertEquals(pool[2], constant.value)
        assertEquals(2u, constant.valueIndex)
        assertEquals(7, constant.tag)
        assertEquals("constant_class_info", constant.tagName)
    }

    @Test
    fun testFromStream() {
        val inputStream = byteArrayOf(0x07, 0x00, 0x02).inputStream()
        val constant = ConstantClassInfo.fromStream(inputStream)
        val pool = ConstantPool(mutableListOf(constant, *testConstants))
        assertEquals(pool[2], constant.value)
        assertEquals(2u, constant.valueIndex)
        assertEquals(7, constant.tag)
        assertEquals("constant_class_info", constant.tagName)
    }

    @Test
    fun testContentsFromBytes() {
        val bytes = byteArrayOf(0x00, 0x02)
        val constant = ConstantClassInfo.contentsFromBytes(bytes)
        val pool = ConstantPool(mutableListOf(constant, *testConstants))
        assertEquals(pool[2], constant.value)
        assertEquals(2u, constant.valueIndex)
        assertEquals(7, constant.tag)
        assertEquals("constant_class_info", constant.tagName)
    }

    @Test
    fun testFromBytes() {
        val bytes = byteArrayOf(0x07, 0x00, 0x02)
        val constant = ConstantClassInfo.fromBytes(bytes)
        val pool = ConstantPool(mutableListOf(constant, *testConstants))
        assertEquals(pool[2], constant.value)
        assertEquals(2u, constant.valueIndex)
        assertEquals(7, constant.tag)
        assertEquals("constant_class_info", constant.tagName)
    }

    @Test
    fun testToBytes() {
        val constant = ConstantClassInfo(2u)
        val pool = ConstantPool(mutableListOf(constant, *testConstants))
        assertEquals(byteArrayOf(0x07, 0x00, 0x02).toList(), constant.toBytes().toList())
    }

    @Test
    fun testToJson() {
        val constant = ConstantStringInfo(2u)
        ConstantPool(mutableListOf(constant, *testConstants))
        val json = constant.toJson()
        assertEquals(3, json.size)
        assertEquals(8, json["tag"])
        assertEquals("constant_string_info", json["tag_type"])
        assertEquals(2, json["value"])
    }
}
