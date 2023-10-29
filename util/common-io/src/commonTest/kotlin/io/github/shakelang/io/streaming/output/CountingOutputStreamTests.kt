package io.github.shakelang.io.streaming.output

import kotlin.test.Test
import kotlin.test.assertContentEquals
import kotlin.test.assertEquals

class CountingOutputStreamTests {

    @Test
    fun testWriteByte() {
        val baseStream = ByteArrayOutputStream()
        val stream = CountingOutputStream(baseStream)
        stream.write(0x01)
        assertEquals(byteArrayOf(0x01).toList(), baseStream.toByteArray().toList())
        assertEquals(1, stream.count)
        assertEquals(1, stream.byteCount)
        assertEquals(1, stream.operationCount)
    }

    @Test
    fun testWriteBytes() {
        val baseStream = ByteArrayOutputStream()
        val stream = CountingOutputStream(baseStream)
        stream.write(0x01)
        stream.write(0x02)
        stream.write(0x03)
        assertEquals(byteArrayOf(0x01, 0x02, 0x03).toList(), baseStream.toByteArray().toList())
        assertEquals(3, stream.count)
        assertEquals(3, stream.byteCount)
        assertEquals(3, stream.operationCount)
    }

    @Test
    fun testWriteByteArray() {
        val baseStream = ByteArrayOutputStream()
        val stream = CountingOutputStream(baseStream)
        stream.write(byteArrayOf(0x01, 0x02, 0x03))
        assertEquals(byteArrayOf(0x01, 0x02, 0x03).toList(), baseStream.toByteArray().toList())
        assertEquals(3, stream.count)
        assertEquals(3, stream.byteCount)
        assertEquals(1, stream.operationCount)
    }

    @Test
    fun testWriteByteArrayPartially() {
        val baseStream = ByteArrayOutputStream()
        val stream = CountingOutputStream(baseStream)
        stream.write(byteArrayOf(0x01, 0x02, 0x03, 0x04, 0x05), 1, 3)
        assertEquals(byteArrayOf(0x02, 0x03, 0x04).toList(), baseStream.toByteArray().toList())
        assertEquals(3, stream.count)
        assertEquals(3, stream.byteCount)
        assertEquals(1, stream.operationCount)
    }

    @Test
    fun testWriteBigAmountOfBytes() {
        val baseStream = ByteArrayOutputStream()
        val stream = CountingOutputStream(baseStream)

        val bytes = ByteArray(32768) {
            stream.write(it)
            it.toByte()
        }

        assertContentEquals(bytes, baseStream.toByteArray())
        assertEquals(32768, stream.count)
        assertEquals(32768, stream.byteCount)
        assertEquals(32768, stream.operationCount)
    }

    @Test
    fun testSize() {
        val baseStream = ByteArrayOutputStream()
        val stream = CountingOutputStream(baseStream)
        stream.write(0x01)
        stream.write(0x02)
        stream.write(0x03)
        assertEquals(3, baseStream.size())
        assertEquals(3, stream.count)
        assertEquals(3, stream.byteCount)
        assertEquals(3, stream.operationCount)
    }

    @Test
    fun testFlush() {
        val baseStream = ByteArrayOutputStream()
        val stream = CountingOutputStream(baseStream)
        stream.write(0x01)
        stream.write(0x02)
        stream.write(0x03)
        stream.flush()
        assertEquals(3, baseStream.size())
        assertEquals(3, stream.count)
        assertEquals(3, stream.byteCount)
        assertEquals(3, stream.operationCount)
    }

    @Test
    fun testClose() {
        val baseStream = ByteArrayOutputStream()
        val stream = CountingOutputStream(baseStream)
        stream.write(0x01)
        stream.write(0x02)
        stream.write(0x03)
        stream.close()
        assertEquals(3, baseStream.size())
        assertEquals(3, stream.count)
        assertEquals(3, stream.byteCount)
        assertEquals(3, stream.operationCount)
    }
}