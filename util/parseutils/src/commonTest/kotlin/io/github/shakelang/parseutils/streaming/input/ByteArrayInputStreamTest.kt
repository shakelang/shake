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

}