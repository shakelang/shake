package io.github.shakelang.parseutils.streaming.output

import kotlin.test.Test
import kotlin.test.assertEquals

class BufferedOutputStreamTests {

    @Test
    fun testWriteByte() {
        val baseStream = ByteArrayOutputStream()
        val stream = BufferedOutputStream(baseStream)
        stream.write(0x01)
        stream.flush()
        assertEquals(byteArrayOf(0x01).toList(), baseStream.toByteArray().toList())
    }

    @Test
    fun testWriteBytes() {
        val baseStream = ByteArrayOutputStream()
        val stream = BufferedOutputStream(baseStream)
        stream.write(0x01)
        stream.write(0x02)
        stream.write(0x03)
        stream.flush()
        assertEquals(byteArrayOf(0x01, 0x02, 0x03).toList(), baseStream.toByteArray().toList())
    }

    @Test
    fun testWriteByteArray() {
        val baseStream = ByteArrayOutputStream()
        val stream = BufferedOutputStream(baseStream)
        stream.write(byteArrayOf(0x01, 0x02, 0x03))
        stream.flush()
        assertEquals(byteArrayOf(0x01, 0x02, 0x03).toList(), baseStream.toByteArray().toList())
    }


}