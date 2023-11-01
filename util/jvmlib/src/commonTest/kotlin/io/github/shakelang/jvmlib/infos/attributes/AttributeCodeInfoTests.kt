package io.github.shakelang.jvmlib.infos.attributes

import io.github.shakelang.shake.util.io.streaming.input.dataStream
import io.github.shakelang.jvmlib.infos.constants.Constant
import io.github.shakelang.jvmlib.infos.constants.ConstantPool
import kotlin.test.Test
import kotlin.test.assertEquals
import kotlin.test.assertSame

class AttributeCodeInfoTests {

    private val pool get() = ConstantPool(mutableListOf(
        Constant.utf8("Code")
    ))

    @Test
    fun test() {
        val pool = pool
        val attribute = AttributeCodeInfo(
            pool.getUtf8(1),
            0u,
            0u,
            byteArrayOf(),
            arrayOf(),
            arrayOf(),
        )
        assertSame(pool[1], attribute.name)
        assertEquals(0u, attribute.max_stack)
        assertEquals(0u, attribute.max_locals)
        assertEquals(0, attribute.code.size)
        assertEquals(0, attribute.exception_table.size)
        assertEquals(0, attribute.attributes.size)
    }

    @Test
    fun testContentsFromStream() {
        val pool = pool
        val stream = byteArrayOf(
            0x00, 0x00, // max_stack
            0x00, 0x00, // max_locals
            0x00, 0x00, 0x00, 0x00, // code length
            0x00, 0x00, // exception table length
            0x00, 0x00, // attributes length
        ).dataStream()
        val attribute = AttributeCodeInfo.contentsFromStream(pool, stream, pool.getUtf8(1))
        assertSame(pool[1], attribute.name)
        assertEquals(0u, attribute.max_stack)
        assertEquals(0u, attribute.max_locals)
        assertEquals(0, attribute.code.size)
        assertEquals(0, attribute.exception_table.size)
        assertEquals(0, attribute.attributes.size)
    }

    @Test
    fun testContentsFromStream2() {
        val pool = pool
        val stream = byteArrayOf(
            0x00, 0x00, // max_stack
            0x00, 0x00, // max_locals
            0x00, 0x00, 0x01, 0x00, // code length
            *((0 until 0x0100).map { (it % 20).toByte() }.toByteArray()), // code
            0x00, 0x02, // exception table length
            0x00, 0x00, 0x00, 0x00, 0x00, 0x00, 0x00, 0x00, // exception table entry
            0x00, 0x00, 0x00, 0x00, 0x00, 0x00, 0x00, 0x00, // exception table entry
            0x00, 0x00, // attributes length
        ).dataStream()
        val attribute = AttributeCodeInfo.contentsFromStream(pool, stream, pool.getUtf8(1))
        assertSame(pool[1], attribute.name)
        assertEquals(0u, attribute.max_stack)
        assertEquals(0u, attribute.max_locals)
        assertEquals(0x100, attribute.code.size)
        assertEquals(2, attribute.exception_table.size)
        assertEquals(0, attribute.attributes.size)
    }

    @Test
    fun testFromStream() {
        val pool = pool
        val stream = byteArrayOf(
            0x00, 0x01, // code constant pool index
            0x00, 0x00, // max_stack
            0x00, 0x00, // max_locals
            0x00, 0x00, 0x00, 0x00, // code length
            0x00, 0x00, // exception table length
            0x00, 0x00, // attributes length
        ).dataStream()
        val attribute = AttributeCodeInfo.fromStream(pool, stream)
        assertSame(pool[1], attribute.name)
        assertEquals(0u, attribute.max_stack)
        assertEquals(0u, attribute.max_locals)
        assertEquals(0, attribute.code.size)
        assertEquals(0, attribute.exception_table.size)
        assertEquals(0, attribute.attributes.size)
    }

    @Test
    fun testFromStream2() {
        val pool = pool
        val stream = byteArrayOf(
            0x00, 0x01, // code constant pool index
            0x00, 0x00, // max_stack
            0x00, 0x00, // max_locals
            0x00, 0x00, 0x01, 0x00, // code length
            *((0 until 0x0100).map { (it % 20).toByte() }.toByteArray()), // code
            0x00, 0x02, // exception table length
            0x00, 0x00, 0x00, 0x00, 0x00, 0x00, 0x00, 0x00, // exception table entry
            0x00, 0x00, 0x00, 0x00, 0x00, 0x00, 0x00, 0x00, // exception table entry
            0x00, 0x00, // attributes length
        ).dataStream()
        val attribute = AttributeCodeInfo.fromStream(pool, stream)
        assertSame(pool[1], attribute.name)
        assertEquals(0u, attribute.max_stack)
        assertEquals(0u, attribute.max_locals)
        assertEquals(0x100, attribute.code.size)
        assertEquals(2, attribute.exception_table.size)
        assertEquals(0, attribute.attributes.size)
    }

    @Test
    fun testContentsFromBytes() {
        val pool = pool
        val bytes = byteArrayOf(
            0x00, 0x00, // max_stack
            0x00, 0x00, // max_locals
            0x00, 0x00, 0x00, 0x00, // code length
            0x00, 0x00, // exception table length
            0x00, 0x00, // attributes length
        )
        val attribute = AttributeCodeInfo.contentsFromBytes(pool, bytes, pool.getUtf8(1))
        assertSame(pool[1], attribute.name)
        assertEquals(0u, attribute.max_stack)
        assertEquals(0u, attribute.max_locals)
        assertEquals(0, attribute.code.size)
        assertEquals(0, attribute.exception_table.size)
        assertEquals(0, attribute.attributes.size)
    }

    @Test
    fun testContentsFromBytes2() {
        val pool = pool
        val bytes = byteArrayOf(
            0x00, 0x00, // max_stack
            0x00, 0x00, // max_locals
            0x00, 0x00, 0x01, 0x00, // code length
            *((0 until 0x0100).map { (it % 20).toByte() }.toByteArray()), // code
            0x00, 0x02, // exception table length
            0x00, 0x00, 0x00, 0x00, 0x00, 0x00, 0x00, 0x00, // exception table entry
            0x00, 0x00, 0x00, 0x00, 0x00, 0x00, 0x00, 0x00, // exception table entry
            0x00, 0x00, // attributes length
        )
        val attribute = AttributeCodeInfo.contentsFromBytes(pool, bytes, pool.getUtf8(1))
        assertSame(pool[1], attribute.name)
        assertEquals(0u, attribute.max_stack)
        assertEquals(0u, attribute.max_locals)
        assertEquals(0x100, attribute.code.size)
        assertEquals(2, attribute.exception_table.size)
        assertEquals(0, attribute.attributes.size)
    }

    @Test
    fun testFromBytes() {
        val pool = pool
        val bytes = byteArrayOf(
            0x00, 0x01, // code constant pool index
            0x00, 0x00, // max_stack
            0x00, 0x00, // max_locals
            0x00, 0x00, 0x00, 0x00, // code length
            0x00, 0x00, // exception table length
            0x00, 0x00, // attributes length
        )
        val attribute = AttributeCodeInfo.fromBytes(pool, bytes)
        assertSame(pool[1], attribute.name)
        assertEquals(0u, attribute.max_stack)
        assertEquals(0u, attribute.max_locals)
        assertEquals(0, attribute.code.size)
        assertEquals(0, attribute.exception_table.size)
        assertEquals(0, attribute.attributes.size)
    }

    @Test
    fun testFromBytes2() {
        val pool = pool
        val bytes = byteArrayOf(
            0x00, 0x01, // code constant pool index
            0x00, 0x00, // max_stack
            0x00, 0x00, // max_locals
            0x00, 0x00, 0x01, 0x00, // code length
            *((0 until 0x0100).map { (it % 20).toByte() }.toByteArray()), // code
            0x00, 0x02, // exception table length
            0x00, 0x00, 0x00, 0x00, 0x00, 0x00, 0x00, 0x00, // exception table entry
            0x00, 0x00, 0x00, 0x00, 0x00, 0x00, 0x00, 0x00, // exception table entry
            0x00, 0x00, // attributes length
        )
        val attribute = AttributeCodeInfo.fromBytes(pool, bytes)
        assertSame(pool[1], attribute.name)
        assertEquals(0u, attribute.max_stack)
        assertEquals(0u, attribute.max_locals)
        assertEquals(0x100, attribute.code.size)
        assertEquals(2, attribute.exception_table.size)
        assertEquals(0, attribute.attributes.size)
    }

}