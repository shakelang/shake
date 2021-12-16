package io.github.shakelang.parseutils.streaming.output

import kotlin.test.Test
import kotlin.test.assertEquals

class CountingOutputStreamTests {

    @Test
    fun testWriteByte() {
        val baseStream = ByteArrayOutputStream()
        val stream = CountingOutputStream(baseStream)
        assertEquals(0, stream.getCount())
        stream.write(0x01)
        assertEquals(1, stream.getCount())
        assertEquals(byteArrayOf(0x01).toList(), baseStream.toByteArray().toList())
        assertEquals(1, stream.getCount())
    }

    @Test
    fun testWriteBytes() {
        val baseStream = ByteArrayOutputStream()
        val stream = CountingOutputStream(baseStream)
        assertEquals(0, stream.getCount())
        stream.write(0x01)
        assertEquals(1, stream.getCount())
        stream.write(0x02)
        assertEquals(2, stream.getCount())
        stream.write(0x03)
        assertEquals(3, stream.getCount())
        assertEquals(byteArrayOf(0x01, 0x02, 0x03).toList(), baseStream.toByteArray().toList())
        assertEquals(3, stream.getCount())
    }

    @Test
    fun testWriteByteArray() {
        val baseStream = ByteArrayOutputStream()
        val stream = CountingOutputStream(baseStream)
        assertEquals(0, stream.getCount())
        stream.write(byteArrayOf(0x01, 0x02, 0x03))
        assertEquals(3, stream.getCount())
        assertEquals(byteArrayOf(0x01, 0x02, 0x03).toList(), baseStream.toByteArray().toList())
        assertEquals(3, stream.getCount())
    }

}