package io.github.shakelang.jvmlib.infos.fields

import io.github.shakelang.io.streaming.input.dataStream
import io.github.shakelang.io.streaming.output.ByteArrayOutputStream
import io.github.shakelang.jvmlib.infos.attributes.AttributeMap
import io.github.shakelang.jvmlib.infos.constants.Constant
import io.github.shakelang.jvmlib.infos.constants.ConstantPool
import kotlin.test.Test
import kotlin.test.assertEquals
import kotlin.test.assertFalse
import kotlin.test.assertTrue

class FieldInfoTests {

    @Test
    fun test() {

        val pool = ConstantPool(
            arrayOf(
                Constant.utf8("variable"),
                Constant.utf8("I")
            )
        )
        val info = FieldInfo(0x00u, pool.getUtf8(1), pool.getUtf8(2), AttributeMap(emptyMap()))
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
                Constant.utf8("variable"),
                Constant.utf8("I")
            )
        )
        val info = FieldInfo.fromStream(pool, stream)
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
                Constant.utf8("variable"),
                Constant.utf8("I")
            )
        )
        val info = FieldInfo.fromBytes(pool, arr)
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
                Constant.utf8("variable"),
                Constant.utf8("I")
            )
        )
        var info = FieldInfo(0x0000u, pool.getUtf8(1), pool.getUtf8(2), AttributeMap(emptyMap()))
        assertFalse(info.isPublic)

        info = FieldInfo(0x0001u, pool.getUtf8(1), pool.getUtf8(2), AttributeMap(emptyMap()))
        assertTrue(info.isPublic)

    }

    @Test
    fun testIsPrivate() {

        val pool = ConstantPool(
            arrayOf(
                Constant.utf8("variable"),
                Constant.utf8("I")
            )
        )
        var info = FieldInfo(0x0000u, pool.getUtf8(1), pool.getUtf8(2), AttributeMap(emptyMap()))
        assertFalse(info.isPrivate)

        info = FieldInfo(0x0002u, pool.getUtf8(1), pool.getUtf8(2), AttributeMap(emptyMap()))
        assertTrue(info.isPrivate)

    }

    @Test
    fun testIsProtected() {

        val pool = ConstantPool(
            arrayOf(
                Constant.utf8("variable"),
                Constant.utf8("I")
            )
        )
        var info = FieldInfo(0x0000u, pool.getUtf8(1), pool.getUtf8(2), AttributeMap(emptyMap()))
        assertFalse(info.isProtected)

        info = FieldInfo(0x0004u, pool.getUtf8(1), pool.getUtf8(2), AttributeMap(emptyMap()))
        assertTrue(info.isProtected)

    }

    @Test
    fun testIsStatic() {

        val pool = ConstantPool(
            arrayOf(
                Constant.utf8("variable"),
                Constant.utf8("I")
            )
        )
        var info = FieldInfo(0x0000u, pool.getUtf8(1), pool.getUtf8(2), AttributeMap(emptyMap()))
        assertFalse(info.isStatic)

        info = FieldInfo(0x0008u, pool.getUtf8(1), pool.getUtf8(2), AttributeMap(emptyMap()))
        assertTrue(info.isStatic)

    }

    @Test
    fun testIsFinal() {

        val pool = ConstantPool(
            arrayOf(
                Constant.utf8("variable"),
                Constant.utf8("I")
            )
        )
        var info = FieldInfo(0x0000u, pool.getUtf8(1), pool.getUtf8(2), AttributeMap(emptyMap()))
        assertFalse(info.isFinal)

        info = FieldInfo(0x0010u, pool.getUtf8(1), pool.getUtf8(2), AttributeMap(emptyMap()))
        assertTrue(info.isFinal)

    }

    @Test
    fun testIsVolatile() {

        val pool = ConstantPool(
            arrayOf(
                Constant.utf8("variable"),
                Constant.utf8("I")
            )
        )
        var info = FieldInfo(0x0000u, pool.getUtf8(1), pool.getUtf8(2), AttributeMap(emptyMap()))
        assertFalse(info.isVolatile)

        info = FieldInfo(0x0040u, pool.getUtf8(1), pool.getUtf8(2), AttributeMap(emptyMap()))
        assertTrue(info.isVolatile)

    }

    @Test
    fun testIsTransient() {

        val pool = ConstantPool(
            arrayOf(
                Constant.utf8("variable"),
                Constant.utf8("I")
            )
        )
        var info = FieldInfo(0x0000u, pool.getUtf8(1), pool.getUtf8(2), AttributeMap(emptyMap()))
        assertFalse(info.isTransient)

        info = FieldInfo(0x0080u, pool.getUtf8(1), pool.getUtf8(2), AttributeMap(emptyMap()))
        assertTrue(info.isTransient)

    }

    @Test
    fun isSynthetic() {

        val pool = ConstantPool(
            arrayOf(
                Constant.utf8("variable"),
                Constant.utf8("I")
            )
        )
        var info = FieldInfo(0x0000u, pool.getUtf8(1), pool.getUtf8(2), AttributeMap(emptyMap()))
        assertFalse(info.isSynthetic)

        info = FieldInfo(0x1000u, pool.getUtf8(1), pool.getUtf8(2), AttributeMap(emptyMap()))
        assertTrue(info.isSynthetic)

    }

    @Test
    fun isEnum() {

        val pool = ConstantPool(
            arrayOf(
                Constant.utf8("variable"),
                Constant.utf8("I")
            )
        )
        var info = FieldInfo(0x0000u, pool.getUtf8(1), pool.getUtf8(2), AttributeMap(emptyMap()))
        assertFalse(info.isEnum)

        info = FieldInfo(0x4000u, pool.getUtf8(1), pool.getUtf8(2), AttributeMap(emptyMap()))
        assertTrue(info.isEnum)

    }

    @Test
    fun testDump() {
        val pool = ConstantPool(
            arrayOf(
                Constant.utf8("variable"),
                Constant.utf8("I")
            )
        )
        val info = FieldInfo(0x00u, pool.getUtf8(1), pool.getUtf8(2), AttributeMap(emptyMap()))
        val output = ByteArrayOutputStream()
        info.dump(output)
        assertEquals<List<Byte>>(listOf(0x00, 0x00, 0x00, 0x01, 0x00, 0x02, 0x00, 0x00), output.toByteArray().toList())
    }

    @Test
    fun testToBytes() {
        val pool = ConstantPool(
            arrayOf(
                Constant.utf8("variable"),
                Constant.utf8("I")
            )
        )
        val info = FieldInfo(0x00u, pool.getUtf8(1), pool.getUtf8(2), AttributeMap(emptyMap()))
        assertEquals<List<Byte>>(listOf(0x00, 0x00, 0x00, 0x01, 0x00, 0x02, 0x00, 0x00), info.toBytes().toList())
    }

}