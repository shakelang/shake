package io.github.shakelang.shake.util.jvmlib.infos.attributes

import io.github.shakelang.shake.util.io.streaming.input.dataStream
import io.github.shakelang.shake.util.jvmlib.infos.constants.Constant
import io.github.shakelang.shake.util.jvmlib.infos.constants.ConstantPool
import kotlin.test.Test
import kotlin.test.assertEquals

class AttributeConstantValueTests {

    private val pool
        get() = ConstantPool(
            mutableListOf(
                Constant.utf8("ConstantValue"),
                Constant.utf8("Hello, World!")
            )
        )

    @Test
    fun test() {
        val pool = pool
        val attribute = AttributeConstantValueInfo(pool.getUtf8(1), pool.getUtf8(2))

        assertEquals(pool.getUtf8(1), attribute.name)
        assertEquals(1u, attribute.nameIndex)
        assertEquals(pool.getUtf8(2), attribute.value)
        assertEquals(2u, attribute.valueIndex)
    }

    @Test
    fun testContentsFromStream() {
        val pool = pool
        val stream = byteArrayOf(0x00, 0x02).dataStream()

        val attribute = AttributeConstantValueInfo.contentsFromStream(pool, stream, pool.getUtf8(1))

        assertEquals(pool.getUtf8(1), attribute.name)
        assertEquals(1u, attribute.nameIndex)
        assertEquals(pool.getUtf8(2), attribute.value)
        assertEquals(2u, attribute.valueIndex)
    }

    @Test
    fun testFromStream() {
        val pool = pool
        val stream = byteArrayOf(0x00, 0x01, 0x00, 0x02).dataStream()

        val attribute = AttributeConstantValueInfo.fromStream(pool, stream)

        assertEquals(pool.getUtf8(1), attribute.name)
        assertEquals(1u, attribute.nameIndex)
        assertEquals(pool.getUtf8(2), attribute.value)
        assertEquals(2u, attribute.valueIndex)
    }

    @Test
    fun testContentsFromBytes() {
        val pool = pool
        val bytes = byteArrayOf(0x00, 0x02)

        val attribute = AttributeConstantValueInfo.contentsFromBytes(pool, bytes, pool.getUtf8(1))

        assertEquals(pool.getUtf8(1), attribute.name)
        assertEquals(1u, attribute.nameIndex)
        assertEquals(pool.getUtf8(2), attribute.value)
        assertEquals(2u, attribute.valueIndex)
    }

    @Test
    fun testContentsFromBytesWithOffset() {
        val pool = pool
        val bytes = byteArrayOf(0x00, 0x01, 0x00, 0x02)

        val attribute = AttributeConstantValueInfo.contentsFromBytes(pool, bytes, pool.getUtf8(1), 2)

        assertEquals(pool.getUtf8(1), attribute.name)
        assertEquals(1u, attribute.nameIndex)
        assertEquals(pool.getUtf8(2), attribute.value)
        assertEquals(2u, attribute.valueIndex)
    }

    @Test
    fun testFromBytes() {
        val pool = pool
        val bytes = byteArrayOf(0x00, 0x01, 0x00, 0x02)

        val attribute = AttributeConstantValueInfo.fromBytes(pool, bytes)

        assertEquals(pool.getUtf8(1), attribute.name)
        assertEquals(1u, attribute.nameIndex)
        assertEquals(pool.getUtf8(2), attribute.value)
        assertEquals(2u, attribute.valueIndex)
    }

    @Test
    fun testFromBytesWithOffset() {
        val pool = pool
        val bytes = byteArrayOf(0x00, 0x00, 0x00, 0x01, 0x00, 0x02)

        val attribute = AttributeConstantValueInfo.fromBytes(pool, bytes, 2)

        assertEquals(pool.getUtf8(1), attribute.name)
        assertEquals(1u, attribute.nameIndex)
        assertEquals(pool.getUtf8(2), attribute.value)
        assertEquals(2u, attribute.valueIndex)
    }
}
