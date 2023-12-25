@file:Suppress("unused_variable")

package com.shakelang.util.jvmlib.infos.constants

import com.shakelang.util.io.streaming.input.inputStream
import com.shakelang.util.jvmlib.infos.InterfaceList
import com.shakelang.util.jvmlib.infos.attributes.AttributeMap
import com.shakelang.util.jvmlib.infos.fields.FieldList
import com.shakelang.util.jvmlib.infos.methods.MethodList
import kotlin.test.Test
import kotlin.test.assertFalse
import kotlin.test.assertTrue

class ConstantInfoTests {

    @Test
    fun testConstantClassInfoFromStream() {
        val inputStream = byteArrayOf(0x07, 0x00, 0x02).inputStream()
        val constant = ConstantInfo.fromStream(inputStream).toClass()
    }

    @Test
    fun testConstantDoubleFromStream() {
        val inputStream = byteArrayOf(0x06, 0x00, 0x00, 0x00, 0x00, 0x00, 0x00, 0x00, 0x00).inputStream()
        val constant = ConstantInfo.fromStream(inputStream)
    }

    @Test
    fun testConstantFieldRefFromStream() {
        val inputStream = byteArrayOf(0x09, 0x00, 0x00, 0x00, 0x00, 0x00, 0x00, 0x00, 0x00, 0x00).inputStream()
        val constant = ConstantInfo.fromStream(inputStream).toFieldRef()
    }

    @Test
    fun testConstantFloatFromStream() {
        val inputStream = byteArrayOf(0x04, 0x00, 0x00, 0x00, 0x00).inputStream()
        val constant = ConstantInfo.fromStream(inputStream)
    }

    @Test
    fun testConstantIntegerFromStream() {
        val inputStream = byteArrayOf(0x03, 0x00, 0x00, 0x00, 0x00).inputStream()
        val constant = ConstantInfo.fromStream(inputStream)
    }

    @Test
    fun testConstantInterfaceMethodRefFromStream() {
        val inputStream =
            byteArrayOf(0x0b, 0x00, 0x00, 0x00, 0x00, 0x00, 0x00, 0x00, 0x00, 0x00, 0x00, 0x00).inputStream()
        val constant = ConstantInfo.fromStream(inputStream).toInterfaceMethodRef()
    }

    @Test
    fun testConstantLongFromStream() {
        val inputStream = byteArrayOf(0x05, 0x00, 0x00, 0x00, 0x00, 0x00, 0x00, 0x00, 0x00).inputStream()
        val constant = ConstantInfo.fromStream(inputStream)
    }

    @Test
    fun testConstantMethodRefFromStream() {
        val inputStream = byteArrayOf(0x0a, 0x00, 0x00, 0x00, 0x00, 0x00, 0x00, 0x00, 0x00, 0x00, 0x00).inputStream()
        val constant = ConstantInfo.fromStream(inputStream).toMethodRef()
    }

    @Test
    fun testConstantNameAndTypeFromStream() {
        val inputStream =
            byteArrayOf(0x0c, 0x00, 0x00, 0x00, 0x00, 0x00, 0x00, 0x00, 0x00, 0x00, 0x00, 0x00, 0x00).inputStream()
        val constant = ConstantInfo.fromStream(inputStream).toNameAndType()
    }

    @Test
    fun testConstantStringFromStream() {
        val inputStream = byteArrayOf(0x08, 0x00, 0x00, 0x00, 0x00, 0x00, 0x00, 0x00, 0x00, 0x00).inputStream()
        val constant = ConstantInfo.fromStream(inputStream).toStringRef()
    }

    @Test
    fun testConstantUtf8FromStream() {
        val inputStream = byteArrayOf(0x01, 0x04, 0x00, 0x01, 0x06, 0x06).inputStream()
        val constant = ConstantInfo.fromStream(inputStream).toUtf8()
    }

    @Test
    fun testConstantClassInfoFromBytes() {
        val bytes = byteArrayOf(0x07, 0x00, 0x02)
        val constant = ConstantInfo.fromBytes(bytes).toClass()
    }

    @Test
    fun testConstantDoubleFromBytes() {
        val bytes = byteArrayOf(0x06, 0x00, 0x00, 0x00, 0x00, 0x00, 0x00, 0x00, 0x00)
        val constant = ConstantInfo.fromBytes(bytes)
    }

    @Test
    fun testConstantFieldRefFromBytes() {
        val bytes = byteArrayOf(0x09, 0x00, 0x00, 0x00, 0x00, 0x00, 0x00, 0x00, 0x00, 0x00)
        val constant = ConstantInfo.fromBytes(bytes).toFieldRef()
    }

    @Test
    fun testConstantFloatFromBytes() {
        val bytes = byteArrayOf(0x04, 0x00, 0x00, 0x00, 0x00)
        val constant = ConstantInfo.fromBytes(bytes)
    }

    @Test
    fun testConstantIntegerFromBytes() {
        val bytes = byteArrayOf(0x03, 0x00, 0x00, 0x00, 0x00)
        val constant = ConstantInfo.fromBytes(bytes)
    }

    @Test
    fun testConstantInterfaceMethodRefFromBytes() {
        val bytes = byteArrayOf(0x0b, 0x00, 0x00, 0x00, 0x00, 0x00, 0x00, 0x00, 0x00, 0x00, 0x00, 0x00)
        val constant = ConstantInfo.fromBytes(bytes).toInterfaceMethodRef()
    }

    @Test
    fun testConstantLongFromBytes() {
        val bytes = byteArrayOf(0x05, 0x00, 0x00, 0x00, 0x00, 0x00, 0x00, 0x00, 0x00)
        val constant = ConstantInfo.fromBytes(bytes)
    }

    @Test
    fun testConstantMethodRefFromBytes() {
        val bytes = byteArrayOf(0x0a, 0x00, 0x00, 0x00, 0x00, 0x00, 0x00, 0x00, 0x00, 0x00, 0x00)
        val constant = ConstantInfo.fromBytes(bytes).toMethodRef()
    }

    @Test
    fun testConstantNameAndTypeFromBytes() {
        val bytes = byteArrayOf(0x0c, 0x00, 0x00, 0x00, 0x00, 0x00, 0x00, 0x00, 0x00, 0x00, 0x00, 0x00, 0x00)
        val constant = ConstantInfo.fromBytes(bytes).toNameAndType()
    }

    @Test
    fun testConstantStringFromBytes() {
        val bytes = byteArrayOf(0x08, 0x00, 0x00, 0x00, 0x00, 0x00, 0x00, 0x00, 0x00, 0x00)
        val constant = ConstantInfo.fromBytes(bytes).toStringRef()
    }

    @Test
    fun testConstantUtf8FromBytes() {
        val bytes = byteArrayOf(0x01, 0x04, 0x00, 0x01, 0x06, 0x06)
        val constant = ConstantInfo.fromBytes(bytes).toUtf8()
    }

    @Test
    fun testIsUsed() {
        val testConstants: Array<ConstantInfo> = arrayOf(
            ConstantFieldrefInfo(2u, 4u),
            ConstantClassInfo(3u),
            ConstantUtf8Info("java/lang/Object"),
            ConstantNameAndTypeInfo(6u, 5u),
            ConstantUtf8Info("foo"),
            ConstantUtf8Info("I")
        )
        val pool = ConstantPool(testConstants)
        val clz = com.shakelang.util.jvmlib.infos.ClassInfo(
            0u,
            0u,
            pool,
            0u,
            pool.getClass(2),
            pool.getClass(2),
            InterfaceList(emptyArray()),
            FieldList(listOf()),
            MethodList(listOf()),
            AttributeMap(mapOf())
        )
        val constant = ConstantFieldrefInfo(2u, 4u)
        val constant2 = pool[4]
        constant.init(pool)
        assertFalse(constant.isUsed)
        assertTrue(constant2.isUsed)
    }
}
