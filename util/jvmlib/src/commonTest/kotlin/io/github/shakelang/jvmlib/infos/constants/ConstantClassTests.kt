package io.github.shakelang.jvmlib.infos.constants

import io.github.shakelang.parseutils.bytes.stream
import io.github.shakelang.parseutils.bytes.toBytes
import kotlin.test.Test
import kotlin.test.assertEquals

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
        val inputStream = byteArrayOf(0x00, 0x02).stream()
        val constant = ConstantClassInfo.contentsFromStream(inputStream)
        val pool = ConstantPool(mutableListOf(constant, *testConstants))
        assertEquals(pool[2], constant.value)
        assertEquals(2u, constant.valueIndex)
        assertEquals(7, constant.tag)
        assertEquals("constant_class_info", constant.tagName)
    }

    @Test
    fun testFromStream() {
        val inputStream = byteArrayOf(0x07, 0x00, 0x02).stream()
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

}