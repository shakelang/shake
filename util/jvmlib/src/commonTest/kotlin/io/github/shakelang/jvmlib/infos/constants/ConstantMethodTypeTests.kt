package io.github.shakelang.jvmlib.infos.constants

import io.github.shakelang.io.streaming.input.stream
import kotlin.test.Test
import kotlin.test.assertEquals

class ConstantMethodTypeTests {

    private val testConstants: Array<ConstantInfo> get() = arrayOf(ConstantUtf8Info("testMethod"))

    @Test
    fun test() {
        val constant = ConstantMethodTypeInfo(2u)
        val pool = ConstantPool(mutableListOf(constant, *testConstants))
        assertEquals(pool[2], constant.descriptor)
        assertEquals(2u, constant.descriptorIndex)
        assertEquals(16, constant.tag)
        assertEquals("constant_methodtype_info", constant.tagName)
    }

    @Test
    fun testContentFromStream() {
        val inputStream = byteArrayOf(0x00, 0x02).stream()
        val constant = ConstantMethodTypeInfo.contentsFromStream(inputStream)
        val pool = ConstantPool(mutableListOf(constant, *testConstants))
        assertEquals(pool[2], constant.descriptor)
        assertEquals(2u, constant.descriptorIndex)
        assertEquals(16, constant.tag)
        assertEquals("constant_methodtype_info", constant.tagName)
    }

    @Test
    fun testFromStream() {
        val inputStream = byteArrayOf(0x10, 0x00, 0x02).stream()
        val constant = ConstantMethodTypeInfo.fromStream(inputStream)
        val pool = ConstantPool(mutableListOf(constant, *testConstants))
        assertEquals(pool[2], constant.descriptor)
        assertEquals(2u, constant.descriptorIndex)
        assertEquals(16, constant.tag)
        assertEquals("constant_methodtype_info", constant.tagName)
    }

    @Test
    fun testContentsFromBytes() {
        val bytes = byteArrayOf(0x00, 0x02)
        val constant = ConstantMethodTypeInfo.contentsFromBytes(bytes)
        val pool = ConstantPool(mutableListOf(constant, *testConstants))
        assertEquals(pool[2], constant.descriptor)
        assertEquals(2u, constant.descriptorIndex)
        assertEquals(16, constant.tag)
        assertEquals("constant_methodtype_info", constant.tagName)
    }

    @Test
    fun testFromBytes() {
        val bytes = byteArrayOf(0x10, 0x00, 0x02)
        val constant = ConstantMethodTypeInfo.fromBytes(bytes)
        val pool = ConstantPool(mutableListOf(constant, *testConstants))
        assertEquals(pool[2], constant.descriptor)
        assertEquals(2u, constant.descriptorIndex)
        assertEquals(16, constant.tag)
        assertEquals("constant_methodtype_info", constant.tagName)
    }

    @Test
    fun testToBytes() {
        val constant = ConstantMethodTypeInfo(2u)
        ConstantPool(mutableListOf(constant, *testConstants))
        val bytes = constant.toBytes()
        assertEquals(byteArrayOf(0x10, 0x00, 0x02).toList(), bytes.toList())
    }

    @Test
    fun testToJson() {
        val constant = ConstantMethodTypeInfo(2u)
        ConstantPool(mutableListOf(constant, *testConstants))
        val json = constant.toJson()
        assertEquals(3, json.size)
        assertEquals(16, json["tag"])
        assertEquals("constant_methodtype_info", json["tag_type"])
        assertEquals(2, json["index"])
    }

}