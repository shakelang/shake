package io.github.shakelang.jvmlib.infos

import io.github.shakelang.shake.util.io.streaming.input.dataStream
import io.github.shakelang.shake.util.io.streaming.output.ByteArrayOutputStream
import io.github.shakelang.jvmlib.infos.constants.Constant
import io.github.shakelang.jvmlib.infos.constants.ConstantPool
import kotlin.test.Test
import kotlin.test.assertEquals

class InterfaceListTests {

    @Test
    fun testFromStream() {
        val pool = ConstantPool(
            arrayOf(
                Constant.utf8("java/lang/Runnable"),
                Constant.utf8("anotherInterface"),
            )
        )
        val stream = byteArrayOf(
            0x00, 0x02, 0x00, 0x00, 0x00, 0x01
        ).dataStream()

        val list = InterfaceList.fromStream(pool, stream)

        assertEquals(2, list.size)
        assertEquals(pool[1], list[0])
        assertEquals(pool[2], list[1])

    }

    @Test
    fun testFromBytes() {
        val pool = ConstantPool(
            arrayOf(
                Constant.utf8("java/lang/Runnable"),
                Constant.utf8("anotherInterface"),
            )
        )
        val bytes = byteArrayOf(
            0x00, 0x02, 0x00, 0x00, 0x00, 0x01
        )

        val list = InterfaceList.fromBytes(pool, bytes)

        assertEquals(2, list.size)
        assertEquals(pool[1], list[0])
        assertEquals(pool[2], list[1])
    }

    @Test
    fun testDump() {
        val pool = ConstantPool(
            arrayOf(
                Constant.utf8("java/lang/Runnable"),
                Constant.utf8("anotherInterface"),
            )
        )
        val list = InterfaceList(listOf(pool.getUtf8(1), pool.getUtf8(2)))
        val bytes = listOf<Byte>(
            0x00, 0x02, 0x00, 0x00, 0x00, 0x01
        )

        assertEquals(2, list.size)
        assertEquals(pool[1], list[0])
        assertEquals(pool[2], list[1])

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

        val list = InterfaceList(listOf(pool.getUtf8(1), pool.getUtf8(2)))
        val bytes = listOf<Byte>(
            0x00, 0x02, 0x00, 0x00, 0x00, 0x01
        )

        assertEquals(2, list.size)
        assertEquals(pool[1], list[0])
        assertEquals(pool[2], list[1])

        assertEquals(bytes, list.toBytes().toList())
    }
}