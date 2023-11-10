package io.github.shakelang.shake.util.jvmlib.infos.methods

import io.github.shakelang.shake.util.io.streaming.input.dataStream
import io.github.shakelang.shake.util.io.streaming.output.ByteArrayOutputStream
import io.github.shakelang.shake.util.jvmlib.infos.attributes.AttributeMap
import io.github.shakelang.shake.util.jvmlib.infos.constants.Constant
import io.github.shakelang.shake.util.jvmlib.infos.constants.ConstantPool
import kotlin.test.Test
import kotlin.test.assertEquals
import kotlin.test.assertFalse
import kotlin.test.assertTrue

class MethodInfoTests {

    @Test
    fun test() {
        val pool = ConstantPool(
            arrayOf(
                Constant.utf8("method"),
                Constant.utf8("I")
            )
        )
        val info = MethodInfo(0x00u, pool.getUtf8(1), pool.getUtf8(2), AttributeMap(emptyMap()))
        assertEquals(0x0000u, info.accessFlags)
        assertEquals(pool[1], info.name)
        assertEquals(1u, info.nameIndex)
        assertEquals(pool[2], info.descriptor)
        assertEquals(2u, info.descriptorIndex)
    }

    @Test
    fun testFromStream() {
        val stream = byteArrayOf(0x00, 0x00, 0x00, 0x01, 0x00, 0x02, 0x00, 0x00).dataStream()
        val pool = ConstantPool(
            arrayOf(
                Constant.utf8("method"),
                Constant.utf8("I")
            )
        )
        val info = MethodInfo.fromStream(pool, stream)
        assertEquals(0x0000u, info.accessFlags)
        assertEquals(pool[1], info.name)
        assertEquals(1u, info.nameIndex)
        assertEquals(pool[2], info.descriptor)
        assertEquals(2u, info.descriptorIndex)
    }

    @Test
    fun testFromArray() {
        val arr = byteArrayOf(0x00, 0x00, 0x00, 0x01, 0x00, 0x02, 0x00, 0x00)
        val pool = ConstantPool(
            arrayOf(
                Constant.utf8("method"),
                Constant.utf8("I")
            )
        )
        val info = MethodInfo.fromBytes(pool, arr)
        assertEquals(0x0000u, info.accessFlags)
        assertEquals(pool[1], info.name)
        assertEquals(1u, info.nameIndex)
        assertEquals(pool[2], info.descriptor)
        assertEquals(2u, info.descriptorIndex)
    }

    @Test
    fun testIsPublic() {
        val pool = ConstantPool(
            arrayOf(
                Constant.utf8("method"),
                Constant.utf8("I")
            )
        )
        var info = MethodInfo(0x0000u, pool.getUtf8(1), pool.getUtf8(2), AttributeMap(emptyMap()))
        assertFalse(info.isPublic)

        info = MethodInfo(0x0001u, pool.getUtf8(1), pool.getUtf8(2), AttributeMap(emptyMap()))
        assertTrue(info.isPublic)
    }

    @Test
    fun testIsPrivate() {
        val pool = ConstantPool(
            arrayOf(
                Constant.utf8("method"),
                Constant.utf8("I")
            )
        )
        var info = MethodInfo(0x0000u, pool.getUtf8(1), pool.getUtf8(2), AttributeMap(emptyMap()))
        assertFalse(info.isPrivate)

        info = MethodInfo(0x0002u, pool.getUtf8(1), pool.getUtf8(2), AttributeMap(emptyMap()))
        assertTrue(info.isPrivate)
    }

    @Test
    fun testIsProtected() {
        val pool = ConstantPool(
            arrayOf(
                Constant.utf8("method"),
                Constant.utf8("I")
            )
        )
        var info = MethodInfo(0x0000u, pool.getUtf8(1), pool.getUtf8(2), AttributeMap(emptyMap()))
        assertFalse(info.isProtected)

        info = MethodInfo(0x0004u, pool.getUtf8(1), pool.getUtf8(2), AttributeMap(emptyMap()))
        assertTrue(info.isProtected)
    }

    @Test
    fun testIsStatic() {
        val pool = ConstantPool(
            arrayOf(
                Constant.utf8("method"),
                Constant.utf8("I")
            )
        )
        var info = MethodInfo(0x0000u, pool.getUtf8(1), pool.getUtf8(2), AttributeMap(emptyMap()))
        assertFalse(info.isStatic)

        info = MethodInfo(0x0008u, pool.getUtf8(1), pool.getUtf8(2), AttributeMap(emptyMap()))
        assertTrue(info.isStatic)
    }

    @Test
    fun testIsFinal() {
        val pool = ConstantPool(
            arrayOf(
                Constant.utf8("method"),
                Constant.utf8("I")
            )
        )
        var info = MethodInfo(0x0000u, pool.getUtf8(1), pool.getUtf8(2), AttributeMap(emptyMap()))
        assertFalse(info.isFinal)

        info = MethodInfo(0x0010u, pool.getUtf8(1), pool.getUtf8(2), AttributeMap(emptyMap()))
        assertTrue(info.isFinal)
    }

    @Test
    fun testIsSynchronized() {
        val pool = ConstantPool(
            arrayOf(
                Constant.utf8("method"),
                Constant.utf8("I")
            )
        )
        var info = MethodInfo(0x0000u, pool.getUtf8(1), pool.getUtf8(2), AttributeMap(emptyMap()))
        assertFalse(info.isSynchronized)

        info = MethodInfo(0x0020u, pool.getUtf8(1), pool.getUtf8(2), AttributeMap(emptyMap()))
        assertTrue(info.isSynchronized)
    }

    @Test
    fun testIsBridge() {
        val pool = ConstantPool(
            arrayOf(
                Constant.utf8("method"),
                Constant.utf8("I")
            )
        )
        var info = MethodInfo(0x0000u, pool.getUtf8(1), pool.getUtf8(2), AttributeMap(emptyMap()))
        assertFalse(info.isBridge)

        info = MethodInfo(0x0040u, pool.getUtf8(1), pool.getUtf8(2), AttributeMap(emptyMap()))
        assertTrue(info.isBridge)
    }

    @Test
    fun testIsVarargs() {
        val pool = ConstantPool(
            arrayOf(
                Constant.utf8("method"),
                Constant.utf8("I")
            )
        )
        var info = MethodInfo(0x0000u, pool.getUtf8(1), pool.getUtf8(2), AttributeMap(emptyMap()))
        assertFalse(info.isVarargs)

        info = MethodInfo(0x0080u, pool.getUtf8(1), pool.getUtf8(2), AttributeMap(emptyMap()))
        assertTrue(info.isVarargs)
    }

    @Test
    fun testIsNative() {
        val pool = ConstantPool(
            arrayOf(
                Constant.utf8("method"),
                Constant.utf8("I")
            )
        )
        var info = MethodInfo(0x0000u, pool.getUtf8(1), pool.getUtf8(2), AttributeMap(emptyMap()))
        assertFalse(info.isNative)

        info = MethodInfo(0x0100u, pool.getUtf8(1), pool.getUtf8(2), AttributeMap(emptyMap()))
        assertTrue(info.isNative)
    }

    @Test
    fun testIsAbstract() {
        val pool = ConstantPool(
            arrayOf(
                Constant.utf8("method"),
                Constant.utf8("I")
            )
        )
        var info = MethodInfo(0x0000u, pool.getUtf8(1), pool.getUtf8(2), AttributeMap(emptyMap()))
        assertFalse(info.isAbstract)

        info = MethodInfo(0x0400u, pool.getUtf8(1), pool.getUtf8(2), AttributeMap(emptyMap()))
        assertTrue(info.isAbstract)
    }

    @Test
    fun testIsStrict() {
        val pool = ConstantPool(
            arrayOf(
                Constant.utf8("method"),
                Constant.utf8("I")
            )
        )
        var info = MethodInfo(0x0000u, pool.getUtf8(1), pool.getUtf8(2), AttributeMap(emptyMap()))
        assertFalse(info.isStrict)

        info = MethodInfo(0x0800u, pool.getUtf8(1), pool.getUtf8(2), AttributeMap(emptyMap()))
        assertTrue(info.isStrict)
    }

    @Test
    fun isSynthetic() {
        val pool = ConstantPool(
            arrayOf(
                Constant.utf8("method"),
                Constant.utf8("I")
            )
        )
        var info = MethodInfo(0x0000u, pool.getUtf8(1), pool.getUtf8(2), AttributeMap(emptyMap()))
        assertFalse(info.isSynthetic)

        info = MethodInfo(0x1000u, pool.getUtf8(1), pool.getUtf8(2), AttributeMap(emptyMap()))
        assertTrue(info.isSynthetic)
    }

    @Test
    fun testDump() {
        val pool = ConstantPool(
            arrayOf(
                Constant.utf8("method"),
                Constant.utf8("I")
            )
        )
        val info = MethodInfo(0x00u, pool.getUtf8(1), pool.getUtf8(2), AttributeMap(emptyMap()))
        val output = ByteArrayOutputStream()
        info.dump(output)
        assertEquals<List<Byte>>(listOf(0x00, 0x00, 0x00, 0x01, 0x00, 0x02, 0x00, 0x00), output.toByteArray().toList())
    }

    @Test
    fun testToBytes() {
        val pool = ConstantPool(
            arrayOf(
                Constant.utf8("method"),
                Constant.utf8("I")
            )
        )
        val info = MethodInfo(0x00u, pool.getUtf8(1), pool.getUtf8(2), AttributeMap(emptyMap()))
        assertEquals<List<Byte>>(listOf(0x00, 0x00, 0x00, 0x01, 0x00, 0x02, 0x00, 0x00), info.toBytes().toList())
    }
}
