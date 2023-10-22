package io.github.shakelang.jvmlib.infos.constants

import io.github.shakelang.parseutils.bytes.stream
import kotlin.test.Test
import kotlin.test.assertEquals

class ConstantMethodHandleTests {

    private val testConstants: Array<ConstantInfo> get() = arrayOf(ConstantUtf8Info("java/lang/Object"))

    @Test
    fun test() {
        val constant = ConstantMethodHandleInfo(2, 2u)
        val pool = ConstantPool(mutableListOf(constant, *testConstants))
        assertEquals(pool[2], constant.reference)
        assertEquals(2u, constant.referenceIndex)
        assertEquals(2, constant.referenceKind)
        assertEquals(15, constant.tag)
        assertEquals("constant_method_handle_info", constant.tagName)
    }

    @Test
    fun testContentFromStream() {
        val inputStream = byteArrayOf(0x02, 0x00, 0x02).stream()
        val constant = ConstantMethodHandleInfo.contentsFromStream(inputStream)
        val pool = ConstantPool(mutableListOf(constant, *testConstants))
        assertEquals(pool[2], constant.reference)
        assertEquals(2u, constant.referenceIndex)
        assertEquals(2, constant.referenceKind)
        assertEquals(15, constant.tag)
        assertEquals("constant_method_handle_info", constant.tagName)
    }

    @Test
    fun testFromStream() {
        val inputStream = byteArrayOf(0x0f, 0x02, 0x00, 0x02).stream()
        val constant = ConstantMethodHandleInfo.fromStream(inputStream)
        val pool = ConstantPool(mutableListOf(constant, *testConstants))
        assertEquals(pool[2], constant.reference)
        assertEquals(2u, constant.referenceIndex)
        assertEquals(2, constant.referenceKind)
        assertEquals(15, constant.tag)
        assertEquals("constant_method_handle_info", constant.tagName)
    }

    @Test
    fun testContentsFromBytes() {
        val bytes = byteArrayOf(0x02, 0x00, 0x02)
        val constant = ConstantMethodHandleInfo.contentsFromBytes(bytes)
        val pool = ConstantPool(mutableListOf(constant, *testConstants))
        assertEquals(pool[2], constant.reference)
        assertEquals(2u, constant.referenceIndex)
        assertEquals(2, constant.referenceKind)
        assertEquals(15, constant.tag)
        assertEquals("constant_method_handle_info", constant.tagName)
    }

    @Test
    fun testFromBytes() {
        val bytes = byteArrayOf(0x0f, 0x02, 0x00, 0x02)
        val constant = ConstantMethodHandleInfo.fromBytes(bytes)
        val pool = ConstantPool(mutableListOf(constant, *testConstants))
        assertEquals(pool[2], constant.reference)
        assertEquals(2u, constant.referenceIndex)
        assertEquals(2, constant.referenceKind)
        assertEquals(15, constant.tag)
        assertEquals("constant_method_handle_info", constant.tagName)
    }

    fun testToBytes() {
        val constant = ConstantMethodHandleInfo(2, 2u)
        val bytes = byteArrayOf(0x0f, 0x02, 0x00, 0x02)
        assertEquals(bytes.toList(), constant.toBytes().toList())
    }

    @Test
    fun testToJson() {
        val constant = ConstantMethodHandleInfo(2, 2u)
        ConstantPool(mutableListOf(constant, *testConstants))
        val json = constant.toJson()
        assertEquals(4, json.size)
        assertEquals(15, json["tag"])
        assertEquals("constant_method_handle_info", json["tag_type"])
        assertEquals(2, json["index"])
        assertEquals(2, json["type"])
    }
}