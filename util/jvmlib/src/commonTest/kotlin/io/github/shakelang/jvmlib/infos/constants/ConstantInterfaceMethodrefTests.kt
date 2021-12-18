package io.github.shakelang.jvmlib.infos.constants

import io.github.shakelang.parseutils.bytes.stream
import kotlin.test.Test
import kotlin.test.assertEquals

class ConstantInterfaceMethodrefTests {

    private val testConstants: Array<ConstantInfo> get() = arrayOf(
        ConstantClassInfo(3u),
        ConstantUtf8Info("java/lang/Object"),
        ConstantNameAndTypeInfo(6u, 5u),
        ConstantUtf8Info("foo"),
        ConstantUtf8Info("I")
    )

    @Test
    fun test() {
        val constant = ConstantInterfaceMethodrefInfo(2u, 4u)
        val pool = ConstantPool(mutableListOf(constant, *testConstants))
        assertEquals(pool[2], constant.classRef)
        assertEquals(2u, constant.classRefIndex)
        assertEquals(pool[4], constant.nameTypeRef)
        assertEquals(4u, constant.nameTypeRefIndex)
        assertEquals(11, constant.tag)
        assertEquals("constant_interface_methodref_info", constant.tagName)
    }

    @Test
    fun testContentFromStream() {
        val inputStream = byteArrayOf(0x00, 0x02, 0x00, 0x04).stream()
        val constant = ConstantInterfaceMethodrefInfo.contentsFromStream(inputStream)
        val pool = ConstantPool(mutableListOf(constant, *testConstants))
        assertEquals(pool[2], constant.classRef)
        assertEquals(2u, constant.classRefIndex)
        assertEquals(pool[4], constant.nameTypeRef)
        assertEquals(4u, constant.nameTypeRefIndex)
        assertEquals(11, constant.tag)
        assertEquals("constant_interface_methodref_info", constant.tagName)
    }

    @Test
    fun testFromStream() {
        val inputStream = byteArrayOf(0x0b, 0x00, 0x02, 0x00, 0x04).stream()
        val constant = ConstantInterfaceMethodrefInfo.fromStream(inputStream)
        val pool = ConstantPool(mutableListOf(constant, *testConstants))
        assertEquals(pool[2], constant.classRef)
        assertEquals(2u, constant.classRefIndex)
        assertEquals(pool[4], constant.nameTypeRef)
        assertEquals(4u, constant.nameTypeRefIndex)
        assertEquals(11, constant.tag)
        assertEquals("constant_interface_methodref_info", constant.tagName)
    }

    @Test
    fun testContentsFromBytes() {
        val bytes = byteArrayOf(0x00, 0x02, 0x00, 0x04)
        val constant = ConstantInterfaceMethodrefInfo.contentsFromBytes(bytes)
        val pool = ConstantPool(mutableListOf(constant, *testConstants))
        assertEquals(pool[2], constant.classRef)
        assertEquals(2u, constant.classRefIndex)
        assertEquals(pool[4], constant.nameTypeRef)
        assertEquals(4u, constant.nameTypeRefIndex)
        assertEquals(11, constant.tag)
        assertEquals("constant_interface_methodref_info", constant.tagName)
    }

    @Test
    fun testFromBytes() {
        val bytes = byteArrayOf(0x0b, 0x00, 0x02, 0x00, 0x04)
        val constant = ConstantInterfaceMethodrefInfo.fromBytes(bytes)
        val pool = ConstantPool(mutableListOf(constant, *testConstants))
        assertEquals(pool[2], constant.classRef)
        assertEquals(2u, constant.classRefIndex)
        assertEquals(pool[4], constant.nameTypeRef)
        assertEquals(4u, constant.nameTypeRefIndex)
        assertEquals(11, constant.tag)
        assertEquals("constant_interface_methodref_info", constant.tagName)
    }

    @Test
    fun testToBytes() {
        val constant = ConstantInterfaceMethodrefInfo(2u, 4u)
        ConstantPool(mutableListOf(constant, *testConstants))
        assertEquals(byteArrayOf(0x0b, 0x00, 0x02, 0x00, 0x04).toList(), constant.toBytes().toList())
    }

    @Test
    fun testToJson() {
        val constant = ConstantInterfaceMethodrefInfo(2u, 4u)
        ConstantPool(mutableListOf(constant, *testConstants))
        val json = constant.toJson()
        assertEquals(4, json.size)
        assertEquals(2, json["class_ref"])
        assertEquals(4, json["name_type_ref"])
        assertEquals(11, json["tag"])
        assertEquals("constant_interface_methodref_info", json["tag_type"])
    }
}