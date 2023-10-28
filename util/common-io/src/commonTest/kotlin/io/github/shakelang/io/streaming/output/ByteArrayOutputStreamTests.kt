package io.github.shakelang.io.streaming.output

import kotlin.test.Test
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


}