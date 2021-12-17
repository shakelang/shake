package io.github.shakelang.jvmlib.infos.constants

import io.github.shakelang.parseutils.bytes.stream
import kotlin.test.Test
import kotlin.test.assertEquals

class ConstantMethodrefTests {

    private val testConstants: Array<ConstantInfo> get() = arrayOf(
        ConstantClassInfo(3u),
        ConstantUtf8Info("java/lang/Object"),
        ConstantNameAndTypeInfo(6u, 5u),
        ConstantUtf8Info("foo"),
        ConstantUtf8Info("I")
    )

    @Test
    fun test() {
        val constant = ConstantMethodrefInfo(2u, 4u)
        val pool = ConstantPool(mutableListOf(constant, *testConstants))
        assertEquals(pool[2], constant.classRef)
        assertEquals(2u, constant.classRefIndex)
        assertEquals(pool[4], constant.nameTypeRef)
        assertEquals(4u, constant.nameTypeRefIndex)
        assertEquals(10, constant.tag)
        assertEquals("constant_methodref_info", constant.tagName)
    }

    @Test
    fun testContentFromStream() {
        val inputStream = byteArrayOf(0x00, 0x02, 0x00, 0x04).stream()
        val constant = ConstantMethodrefInfo.contentsFromStream(inputStream)
        val pool = ConstantPool(mutableListOf(constant, *testConstants))
        assertEquals(pool[2], constant.classRef)
        assertEquals(2u, constant.classRefIndex)
        assertEquals(pool[4], constant.nameTypeRef)
        assertEquals(4u, constant.nameTypeRefIndex)
        assertEquals(10, constant.tag)
        assertEquals("constant_methodref_info", constant.tagName)
    }

    @Test
    fun testFromStream() {
        val inputStream = byteArrayOf(0x0a, 0x00, 0x02, 0x00, 0x04).stream()
        val constant = ConstantMethodrefInfo.fromStream(inputStream)
        val pool = ConstantPool(mutableListOf(constant, *testConstants))
        assertEquals(pool[2], constant.classRef)
        assertEquals(2u, constant.classRefIndex)
        assertEquals(pool[4], constant.nameTypeRef)
        assertEquals(4u, constant.nameTypeRefIndex)
        assertEquals(10, constant.tag)
        assertEquals("constant_methodref_info", constant.tagName)
    }

    @Test
    fun testContentsFromBytes() {
        val bytes = byteArrayOf(0x00, 0x02, 0x00, 0x04)
        val constant = ConstantMethodrefInfo.contentsFromBytes(bytes)
        val pool = ConstantPool(mutableListOf(constant, *testConstants))
        assertEquals(pool[2], constant.classRef)
        assertEquals(2u, constant.classRefIndex)
        assertEquals(pool[4], constant.nameTypeRef)
        assertEquals(4u, constant.nameTypeRefIndex)
        assertEquals(10, constant.tag)
        assertEquals("constant_methodref_info", constant.tagName)
    }

    @Test
    fun testFromBytes() {
        val bytes = byteArrayOf(0x0a, 0x00, 0x02, 0x00, 0x04)
        val constant = ConstantMethodrefInfo.fromBytes(bytes)
        val pool = ConstantPool(mutableListOf(constant, *testConstants))
        assertEquals(pool[2], constant.classRef)
        assertEquals(2u, constant.classRefIndex)
        assertEquals(pool[4], constant.nameTypeRef)
        assertEquals(4u, constant.nameTypeRefIndex)
        assertEquals(10, constant.tag)
        assertEquals("constant_methodref_info", constant.tagName)
    }
}