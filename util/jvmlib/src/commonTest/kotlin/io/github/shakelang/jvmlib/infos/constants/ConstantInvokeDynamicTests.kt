package io.github.shakelang.jvmlib.infos.constants

import io.github.shakelang.parseutils.bytes.stream
import io.github.shakelang.parseutils.bytes.toBytes
import kotlin.test.Test
import kotlin.test.assertEquals

class ConstantInvokeDynamicTests {

    private val testConstants: Array<ConstantInfo> get() = arrayOf(
        ConstantNameAndTypeInfo(3u, 4u),
        ConstantUtf8Info("foo"),
        ConstantUtf8Info("I"),
    )

    @Test
    fun test() {
        val constant = ConstantInvokeDynamicInfo(0u, 2u)
        val pool = ConstantPool(mutableListOf(constant, *testConstants))
        assertEquals(pool[2], constant.nameAndType)
        assertEquals(2u, constant.nameAndTypeIndex)
        assertEquals(0u, constant.bootstrapMethodAttributeIndex)
        assertEquals(18, constant.tag)
        assertEquals("constant_invoke_dynamic", constant.tagName)
    }

    @Test
    fun testContentFromStream() {
        val inputStream = byteArrayOf(0x00, 0x00, 0x00, 0x02).stream()
        val constant = ConstantInvokeDynamicInfo.contentsFromStream(inputStream)
        val pool = ConstantPool(mutableListOf(constant, *testConstants))
        assertEquals(pool[2], constant.nameAndType)
        assertEquals(2u, constant.nameAndTypeIndex)
        assertEquals(0u, constant.bootstrapMethodAttributeIndex)
        assertEquals(18, constant.tag)
        assertEquals("constant_invoke_dynamic", constant.tagName)
    }

    @Test
    fun testFromStream() {
        val inputStream = byteArrayOf(0x12, 0x00, 0x00, 0x00, 0x02).stream()
        val constant = ConstantInvokeDynamicInfo.fromStream(inputStream)
        val pool = ConstantPool(mutableListOf(constant, *testConstants))
        assertEquals(pool[2], constant.nameAndType)
        assertEquals(2u, constant.nameAndTypeIndex)
        assertEquals(0u, constant.bootstrapMethodAttributeIndex)
        assertEquals(18, constant.tag)
        assertEquals("constant_invoke_dynamic", constant.tagName)
    }

    @Test
    fun testContentsFromBytes() {
        val bytes = byteArrayOf(0x00, 0x00, 0x00, 0x02)
        val constant = ConstantInvokeDynamicInfo.contentsFromBytes(bytes)
        val pool = ConstantPool(mutableListOf(constant, *testConstants))
        assertEquals(pool[2], constant.nameAndType)
        assertEquals(2u, constant.nameAndTypeIndex)
        assertEquals(0u, constant.bootstrapMethodAttributeIndex)
        assertEquals(18, constant.tag)
        assertEquals("constant_invoke_dynamic", constant.tagName)
    }

    @Test
    fun testFromBytes() {
        val bytes = byteArrayOf(0x12, 0x00, 0x00, 0x00, 0x02)
        val constant = ConstantInvokeDynamicInfo.fromBytes(bytes)
        val pool = ConstantPool(mutableListOf(constant, *testConstants))
        assertEquals(pool[2], constant.nameAndType)
        assertEquals(2u, constant.nameAndTypeIndex)
        assertEquals(0u, constant.bootstrapMethodAttributeIndex)
        assertEquals(18, constant.tag)
        assertEquals("constant_invoke_dynamic", constant.tagName)
    }

}