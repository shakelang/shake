package io.github.shakelang.parseutils.streaming.input

import kotlin.test.Test
import kotlin.test.assertEquals

class ByteArrayInputStreamTest {

    @Test
    fun test() {
        val input = ByteArrayInputStream(byteArrayOf(1, 2, 3, 4, 5, -1, -2, -3, -4, -5))
        assertEquals(1, input.read().toUByte().toByte())
        assertEquals(2, input.read().toUByte().toByte())
        assertEquals(3, input.read().toUByte().toByte())
        assertEquals(4, input.read().toUByte().toByte())
        assertEquals(5, input.read().toUByte().toByte())
        assertEquals(-1, input.read().toUByte().toByte())
        assertEquals(-2, input.read().toUByte().toByte())
        assertEquals(-3, input.read().toUByte().toByte())
        assertEquals(-4, input.read().toUByte().toByte())
        assertEquals(-5, input.read().toUByte().toByte())
    }

    @Test
    fun bulkReadTest() {
        val input = ByteArrayInputStream(byteArrayOf(1, 2, 3, 4, 5, -1, -2, -3, -4, -5))
        val buffer = ByteArray(5)
        assertEquals(5, input.read(buffer))
        assertEquals(1, buffer[0])
        assertEquals(2, buffer[1])
        assertEquals(3, buffer[2])
        assertEquals(4, buffer[3])
        assertEquals(5, buffer[4])
        assertEquals(5, input.read(buffer))
        assertEquals(-1, buffer[0])
        assertEquals(-2, buffer[1])
        assertEquals(-3, buffer[2])
        assertEquals(-4, buffer[3])
        assertEquals(-5, buffer[4])
    }

    @Test
    fun testSkip() {
        val stream = ByteArrayInputStream(byteArrayOf(0x00, 0x01, 0x02, 0x03, 0x04, 0x05, 0x06, 0x07, 0x08, 0x09, 0x0A, 0x0B, 0x0C, 0x0D, 0x0E, 0x0F))
        assertEquals(0x00, stream.read())
        stream.skip(3)
        assertEquals(0x04, stream.read())
        stream.skip(4)
        assertEquals(0x09, stream.read())
        stream.skip(5)
        assertEquals(0x0F, stream.read())
    }

}