package com.shakelang.util.io.streaming.output

import kotlin.test.Test
import kotlin.test.assertContentEquals
import kotlin.test.assertEquals

class ByteArrayOutputStreamTests {

    @Test
    fun testWriteByte() {
        val stream = ByteArrayOutputStream()
        stream.write(0x01)
        assertEquals(byteArrayOf(0x01).toList(), stream.toByteArray().toList())
    }

    @Test
    fun testWriteBytes() {
        val stream = ByteArrayOutputStream()
        stream.write(0x01)
        stream.write(0x02)
        stream.write(0x03)
        assertEquals(byteArrayOf(0x01, 0x02, 0x03).toList(), stream.toByteArray().toList())
    }

    @Test
    fun testWriteByteArray() {
        val stream = ByteArrayOutputStream()
        stream.write(byteArrayOf(0x01, 0x02, 0x03))
        assertEquals(byteArrayOf(0x01, 0x02, 0x03).toList(), stream.toByteArray().toList())
    }

    @Test
    fun testWriteByteArrayPartially() {
        val stream = ByteArrayOutputStream()
        stream.write(byteArrayOf(0x01, 0x02, 0x03, 0x04, 0x05), 1, 3)
        assertEquals(byteArrayOf(0x02, 0x03, 0x04).toList(), stream.toByteArray().toList())
    }

    @Test
    fun testWriteBigAmountOfBytes() {
        val stream = ByteArrayOutputStream()

        val bytes = ByteArray(32768) {
            stream.write(it.toByte())
            it.toByte()
        }

        assertContentEquals(bytes, stream.toByteArray())
    }

    @Test
    fun testReset() {
        val stream = ByteArrayOutputStream()
        stream.write(0x01)
        stream.write(0x02)
        stream.write(0x03)
        stream.reset()
        assertEquals(0, stream.size())
        assertContentEquals(byteArrayOf(), stream.toByteArray())
    }

    @Test
    fun testSize() {
        val stream = ByteArrayOutputStream()
        stream.write(0x01)
        stream.write(0x02)
        stream.write(0x03)
        assertEquals(3, stream.size())
    }
}
