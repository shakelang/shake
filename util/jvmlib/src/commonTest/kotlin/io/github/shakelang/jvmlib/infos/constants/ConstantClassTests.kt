package io.github.shakelang.jvmlib.infos.constants

import io.github.shakelang.shake.util.io.streaming.input.inputStream
import kotlin.test.Test
import kotlin.test.assertEquals
import kotlin.test.assertTrue

class ConstantClassTests {

    private val testConstants: Array<ConstantInfo> get() = arrayOf(ConstantUtf8Info("java/lang/Object"))

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
        val constant = ConstantClassInfo(2u)
        ConstantPool(mutableListOf(constant, *testConstants))
        val json = constant.toJson()
        assertEquals(json.size, 3)
        assertTrue(json.containsKey("tag"))
        assertTrue(json.containsKey("tag_type"))
        assertTrue(json.containsKey("value"))
        assertEquals(7, json["tag"])
        assertEquals("constant_class_info", json["tag_type"])
        assertEquals(2, json["value"])
    }

}