package io.github.shakelang.shake.util.jvmlib.infos.fields

import io.github.shakelang.shake.util.io.streaming.input.dataStream
import io.github.shakelang.shake.util.io.streaming.output.ByteArrayOutputStream
import io.github.shakelang.shake.util.jvmlib.infos.attributes.AttributeMap
import io.github.shakelang.shake.util.jvmlib.infos.constants.Constant
import io.github.shakelang.shake.util.jvmlib.infos.constants.ConstantPool
import kotlin.test.Test
import kotlin.test.assertEquals
import kotlin.test.assertFalse
import kotlin.test.assertTrue

class FieldListTests {

    @Test
    fun testFromStream() {
        val pool = ConstantPool(
            arrayOf(
                Constant.utf8("hello"),
                Constant.utf8("world"),
                Constant.utf8("I"),
                Constant.utf8("D"),
            )
        )
        val stream = byteArrayOf(
            0x00, 0x02,
            0x00, 0x01, 0x00, 0x01, 0x00, 0x03, 0x00, 0x00,
            0x00, 0x00, 0x00, 0x02, 0x00, 0x04, 0x00, 0x00,
        ).dataStream()

        val list = FieldList.fromStream(pool, stream)

        assertEquals(2, list.size)
        assertTrue(list[0].isPublic)
        assertEquals(pool[1], list[0].name)
        assertEquals(pool[3], list[0].descriptor)

        assertFalse(list[1].isPublic)
        assertEquals(pool[2], list[1].name)
        assertEquals(pool[4], list[1].descriptor)
    }

    @Test
    fun testFromBytes() {
        val pool = ConstantPool(
            arrayOf(
                Constant.utf8("hello"),
                Constant.utf8("world"),
                Constant.utf8("I"),
                Constant.utf8("D"),
            )
        )
        val bytes = byteArrayOf(
            0x00, 0x02,
            0x00, 0x01, 0x00, 0x01, 0x00, 0x03, 0x00, 0x00,
            0x00, 0x00, 0x00, 0x02, 0x00, 0x04, 0x00, 0x00,
        )

        val list = FieldList.fromBytes(pool, bytes)

        assertEquals(2, list.size)
        assertTrue(list[0].isPublic)
        assertEquals(pool[1], list[0].name)
        assertEquals(pool[3], list[0].descriptor)

        assertFalse(list[1].isPublic)
        assertEquals(pool[2], list[1].name)
        assertEquals(pool[4], list[1].descriptor)
    }

    @Test
    fun testDump() {
        val pool = ConstantPool(
            arrayOf(
                Constant.utf8("hello"),
                Constant.utf8("world"),
                Constant.utf8("I"),
                Constant.utf8("D"),
            )
        )
        val list = FieldList(listOf(
            FieldInfo(0x0001u, pool.getUtf8(1), pool.getUtf8(3), AttributeMap(emptyMap())),
            FieldInfo(0x0000u, pool.getUtf8(2), pool.getUtf8(4), AttributeMap(emptyMap())),
        ))
        val bytes = listOf<Byte>(
            0x00, 0x02,
            0x00, 0x01, 0x00, 0x01, 0x00, 0x03, 0x00, 0x00,
            0x00, 0x00, 0x00, 0x02, 0x00, 0x04, 0x00, 0x00,
        )

        assertEquals(2, list.size)
        assertTrue(list[0].isPublic)
        assertEquals(pool[1], list[0].name)
        assertEquals(pool[3], list[0].descriptor)

        assertFalse(list[1].isPublic)
        assertEquals(pool[2], list[1].name)
        assertEquals(pool[4], list[1].descriptor)

        val out = ByteArrayOutputStream()
        list.dump(out)
        assertEquals(bytes, out.toByteArray().toList())
    }

    @Test
    fun testToBytes() {
        val pool = ConstantPool(
            arrayOf(
                Constant.utf8("hello"),
                Constant.utf8("world"),
                Constant.utf8("I"),
                Constant.utf8("D"),
            )
        )
        val list = FieldList(listOf(
            FieldInfo(0x0001u, pool.getUtf8(1), pool.getUtf8(3), AttributeMap(emptyMap())),
            FieldInfo(0x0000u, pool.getUtf8(2), pool.getUtf8(4), AttributeMap(emptyMap())),
        ))
        val bytes = listOf<Byte>(
            0x00, 0x02,
            0x00, 0x01, 0x00, 0x01, 0x00, 0x03, 0x00, 0x00,
            0x00, 0x00, 0x00, 0x02, 0x00, 0x04, 0x00, 0x00,
        )

        assertEquals(2, list.size)
        assertTrue(list[0].isPublic)
        assertEquals(pool[1], list[0].name)
        assertEquals(pool[3], list[0].descriptor)

        assertFalse(list[1].isPublic)
        assertEquals(pool[2], list[1].name)
        assertEquals(pool[4], list[1].descriptor)

        assertEquals(bytes, list.toBytes().toList())
    }
}