package com.shakelang.util.jvmlib.infos.attributes

import com.shakelang.util.io.streaming.input.dataStream
import com.shakelang.util.jvmlib.infos.constants.Constant
import com.shakelang.util.jvmlib.infos.constants.ConstantPool
import kotlin.test.Test
import kotlin.test.assertEquals

class AttributeUnknownTests {

    private val pool
        get() = ConstantPool(
            mutableListOf(
                Constant.utf8("some_name"),
            ),
        )

    @Test
    fun test() {
        val pool = pool
        val info = AttributeUnknownInfo(pool.getUtf8(1), byteArrayOf())

        assertEquals(1u, info.nameIndex)
        assertEquals(pool[1], info.name)
        assertEquals(0, info.bytes.size)
    }

    @Test
    fun testContentsFromStream() {
        val pool = pool
        val stream = byteArrayOf().dataStream()
        val info = AttributeUnknownInfo.contentsFromStream(pool, stream, pool.getUtf8(1), 0)

        assertEquals(1u, info.nameIndex)
        assertEquals(pool[1], info.name)
        assertEquals(0, info.bytes.size)
    }

    @Test
    fun testContentsFromStream2() {
        val pool = pool
        val stream = byteArrayOf(0x00, 0x01, 0x21, 0x13).dataStream()
        val info = AttributeUnknownInfo.contentsFromStream(pool, stream, pool.getUtf8(1), 4)

        assertEquals(1u, info.nameIndex)
        assertEquals(pool[1], info.name)
        assertEquals(4, info.bytes.size)
    }

    @Test
    fun testFromStream() {
        val pool = pool
        val stream = byteArrayOf(0x00, 0x01, 0x00, 0x00, 0x00, 0x00).dataStream()
        val info = AttributeUnknownInfo.fromStream(pool, stream)

        assertEquals(1u, info.nameIndex)
        assertEquals(pool[1], info.name)
        assertEquals(0, info.bytes.size)
    }

    @Test
    fun testFromStream2() {
        val pool = pool
        val stream = byteArrayOf(0x00, 0x01, 0x00, 0x00, 0x00, 0x04, 0x00, 0x01, 0x21, 0x13).dataStream()
        val info = AttributeUnknownInfo.fromStream(pool, stream)

        assertEquals(1u, info.nameIndex)
        assertEquals(pool[1], info.name)
        assertEquals(4, info.bytes.size)
    }

    @Test
    fun testContentsFromBytes() {
        val pool = pool
        val info = AttributeUnknownInfo.contentsFromBytes(pool, byteArrayOf(), pool.getUtf8(1), 0)

        assertEquals(1u, info.nameIndex)
        assertEquals(pool[1], info.name)
        assertEquals(0, info.bytes.size)
    }

    @Test
    fun testContentsFromBytes2() {
        val pool = pool
        val info = AttributeUnknownInfo.contentsFromBytes(pool, byteArrayOf(0x00, 0x01, 0x21, 0x13), pool.getUtf8(1), 4)

        assertEquals(1u, info.nameIndex)
        assertEquals(pool[1], info.name)
        assertEquals(4, info.bytes.size)
    }

    @Test
    fun testFromBytes() {
        val pool = pool
        val info = AttributeUnknownInfo.fromBytes(pool, byteArrayOf(0x00, 0x01, 0x00, 0x00, 0x00, 0x00))

        assertEquals(1u, info.nameIndex)
        assertEquals(pool[1], info.name)
        assertEquals(0, info.bytes.size)
    }

    @Test
    fun testFromBytes2() {
        val pool = pool
        val info = AttributeUnknownInfo.fromBytes(
            pool,
            byteArrayOf(0x00, 0x01, 0x00, 0x00, 0x00, 0x04, 0x00, 0x01, 0x21, 0x13),
        )

        assertEquals(1u, info.nameIndex)
        assertEquals(pool[1], info.name)
        assertEquals(4, info.bytes.size)
    }
}
