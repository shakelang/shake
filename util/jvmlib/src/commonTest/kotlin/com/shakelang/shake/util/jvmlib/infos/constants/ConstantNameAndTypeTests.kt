package com.shakelang.shake.util.jvmlib.infos.constants

import com.shakelang.shake.util.io.streaming.input.inputStream
import kotlin.test.Test
import kotlin.test.assertEquals

class ConstantNameAndTypeTests {

    private val testConstants: Array<ConstantInfo> get() = arrayOf(ConstantUtf8Info("foo"), ConstantUtf8Info("I"))

    @Test
    fun test() {
        val constant = ConstantNameAndTypeInfo(3u, 2u)
        val pool = ConstantPool(mutableListOf(constant, *testConstants))
        assertEquals(pool[2], constant.name)
        assertEquals(2u, constant.nameIndex)
        assertEquals(pool[3], constant.type)
        assertEquals(3u, constant.typeIndex)
        assertEquals(12, constant.tag)
        assertEquals("constant_name_and_type_info", constant.tagName)
    }

    @Test
    fun testContentFromStream() {
        val inputStream = byteArrayOf(0x00, 0x03, 0x00, 0x02).inputStream()
        val constant = ConstantNameAndTypeInfo.contentsFromStream(inputStream)
        val pool = ConstantPool(mutableListOf(constant, *testConstants))
        assertEquals(pool[2], constant.name)
        assertEquals(2u, constant.nameIndex)
        assertEquals(pool[3], constant.type)
        assertEquals(3u, constant.typeIndex)
        assertEquals(12, constant.tag)
        assertEquals("constant_name_and_type_info", constant.tagName)
    }

    @Test
    fun testFromStream() {
        val inputStream = byteArrayOf(0x0c, 0x00, 0x03, 0x00, 0x02).inputStream()
        val constant = ConstantNameAndTypeInfo.fromStream(inputStream)
        val pool = ConstantPool(mutableListOf(constant, *testConstants))
        assertEquals(pool[2], constant.name)
        assertEquals(2u, constant.nameIndex)
        assertEquals(pool[3], constant.type)
        assertEquals(3u, constant.typeIndex)
        assertEquals(12, constant.tag)
        assertEquals("constant_name_and_type_info", constant.tagName)
    }

    @Test
    fun testContentsFromBytes() {
        val bytes = byteArrayOf(0x00, 0x03, 0x00, 0x02)
        val constant = ConstantNameAndTypeInfo.contentsFromBytes(bytes)
        val pool = ConstantPool(mutableListOf(constant, *testConstants))
        assertEquals(pool[2], constant.name)
        assertEquals(2u, constant.nameIndex)
        assertEquals(pool[3], constant.type)
        assertEquals(3u, constant.typeIndex)
        assertEquals(12, constant.tag)
        assertEquals("constant_name_and_type_info", constant.tagName)
    }

    @Test
    fun testFromBytes() {
        val bytes = byteArrayOf(0x0c, 0x00, 0x03, 0x00, 0x02)
        val constant = ConstantNameAndTypeInfo.fromBytes(bytes)
        val pool = ConstantPool(mutableListOf(constant, *testConstants))
        assertEquals(pool[2], constant.name)
        assertEquals(2u, constant.nameIndex)
        assertEquals(pool[3], constant.type)
        assertEquals(3u, constant.typeIndex)
        assertEquals(12, constant.tag)
        assertEquals("constant_name_and_type_info", constant.tagName)
    }

    @Test
    fun testToBytes() {
        val constant = ConstantNameAndTypeInfo(3u, 2u)
        ConstantPool(mutableListOf(constant, *testConstants))
        val bytes = byteArrayOf(0x0c, 0x00, 0x03, 0x00, 0x02)
        assertEquals(bytes.toList(), constant.toBytes().toList())
    }

    @Test
    fun testToJson() {
        val constant = ConstantNameAndTypeInfo(3u, 2u)
        ConstantPool(mutableListOf(constant, *testConstants))
        val json = constant.toJson()
        assertEquals(4, json.size)
        assertEquals(3, json["name"])
        assertEquals(2, json["type"])
        assertEquals(12, json["tag"])
        assertEquals("constant_name_and_type_info", json["tag_type"])
    }
}
