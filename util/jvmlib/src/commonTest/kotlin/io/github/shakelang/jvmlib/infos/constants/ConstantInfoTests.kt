@file:Suppress("unused_variable")
package io.github.shakelang.jvmlib.infos.constants

import io.github.shakelang.parseutils.bytes.stream
import kotlin.test.Test

class ConstantInfoTests {

    @Test
    fun testConstantClassInfoFromStream() {
        val inputStream = byteArrayOf(0x07, 0x00, 0x02).stream()
        val constant = ConstantInfo.fromStream(inputStream).toClass()
    }

    @Test
    fun testConstantDoubleFromStream() {
        val inputStream = byteArrayOf(0x06, 0x00, 0x00, 0x00, 0x00, 0x00, 0x00, 0x00, 0x00).stream()
        val constant = ConstantInfo.fromStream(inputStream)
    }

    @Test
    fun testConstantFieldRefFromStream() {
        val inputStream = byteArrayOf(0x09, 0x00, 0x00, 0x00, 0x00, 0x00, 0x00, 0x00, 0x00, 0x00).stream()
        val constant = ConstantInfo.fromStream(inputStream).toFieldRef()
    }

    @Test
    fun testConstantFloatFromStream() {
        val inputStream = byteArrayOf(0x04, 0x00, 0x00, 0x00, 0x00).stream()
        val constant = ConstantInfo.fromStream(inputStream)
    }

    @Test
    fun testConstantIntegerFromStream() {
        val inputStream = byteArrayOf(0x03, 0x00, 0x00, 0x00, 0x00).stream()
        val constant = ConstantInfo.fromStream(inputStream)
    }

    @Test
    fun testConstantInterfaceMethodRefFromStream() {
        val inputStream = byteArrayOf(0x0b, 0x00, 0x00, 0x00, 0x00, 0x00, 0x00, 0x00, 0x00, 0x00, 0x00, 0x00).stream()
        val constant = ConstantInfo.fromStream(inputStream).toInterfaceMethodRef()
    }

    @Test
    fun testConstantLongFromStream() {
        val inputStream = byteArrayOf(0x05, 0x00, 0x00, 0x00, 0x00, 0x00, 0x00, 0x00, 0x00).stream()
        val constant = ConstantInfo.fromStream(inputStream)
    }

    @Test
    fun testConstantMethodRefFromStream() {
        val inputStream = byteArrayOf(0x0a, 0x00, 0x00, 0x00, 0x00, 0x00, 0x00, 0x00, 0x00, 0x00, 0x00).stream()
        val constant = ConstantInfo.fromStream(inputStream).toMethodRef()
    }

    @Test
    fun testConstantNameAndTypeFromStream() {
        val inputStream = byteArrayOf(0x0c, 0x00, 0x00, 0x00, 0x00, 0x00, 0x00, 0x00, 0x00, 0x00, 0x00, 0x00, 0x00).stream()
        val constant = ConstantInfo.fromStream(inputStream).toNameAndType()
    }

    @Test
    fun testConstantStringFromStream() {
        val inputStream = byteArrayOf(0x08, 0x00, 0x00, 0x00, 0x00, 0x00, 0x00, 0x00, 0x00, 0x00).stream()
        val constant = ConstantInfo.fromStream(inputStream).toStringRef()
    }

    @Test
    fun testConstantUtf8FromStream() {
        val inputStream = byteArrayOf(0x01, 0x04, 0x00, 0x01, 0x06, 0x06).stream()
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

}