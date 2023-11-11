package io.github.shakelang.shake.util.io.streaming.output

import kotlin.test.Test
import kotlin.test.assertContentEquals
import kotlin.test.assertEquals

class BufferedOutputStreamTests {

    @Test
    fun testWriteByte() {
        val baseStream = ByteArrayOutputStream()
        val stream = BufferedOutputStream(baseStream)
        stream.write(0x01)
        assertEquals(byteArrayOf().toList(), baseStream.toByteArray().toList())
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
        assertEquals(byteArrayOf().toList(), baseStream.toByteArray().toList())
        stream.flush()
        assertEquals(byteArrayOf(0x01, 0x02, 0x03).toList(), baseStream.toByteArray().toList())
    }

    @Test
    fun testWriteByteArray() {
        val baseStream = ByteArrayOutputStream()
        val stream = BufferedOutputStream(baseStream)
        stream.write(byteArrayOf(0x01, 0x02, 0x03))
        assertEquals(byteArrayOf().toList(), baseStream.toByteArray().toList())
        stream.flush()
        assertEquals(byteArrayOf(0x01, 0x02, 0x03).toList(), baseStream.toByteArray().toList())
    }

    @Test
    fun testWriteByteArrayPartially() {
        val baseStream = ByteArrayOutputStream()
        val stream = BufferedOutputStream(baseStream)
        stream.write(byteArrayOf(0x01, 0x02, 0x03, 0x04, 0x05), 1, 3)
        assertEquals(byteArrayOf().toList(), baseStream.toByteArray().toList())
        stream.flush()
        assertEquals(byteArrayOf(0x02, 0x03, 0x04).toList(), baseStream.toByteArray().toList())
    }

    @Test
    fun testWriteBigAmountOfBytes() {
        val baseStream = ByteArrayOutputStream()
        val stream = BufferedOutputStream(baseStream)

        val bytes = ByteArray(32768) {
            stream.write(it)
            it.toByte()
        }

        stream.flush()

        assertContentEquals(bytes, baseStream.toByteArray())
    }

    @Test
    fun testDirectWriteBigAmountOfBytes() {
        val baseStream = ByteArrayOutputStream()
        val stream = BufferedOutputStream(baseStream)

        val bytes = ByteArray(32768) {
            it.toByte()
        }

        stream.write(bytes)

        assertContentEquals(bytes, baseStream.toByteArray())
    }

    @Test
    fun testSize() {
        val baseStream = ByteArrayOutputStream()
        val stream = BufferedOutputStream(baseStream)
        stream.write(0x01)
        stream.write(0x02)
        stream.write(0x03)
        assertEquals(0, baseStream.size())
        stream.flush()
        assertEquals(3, baseStream.size())
    }

    @Test
    fun testFlush() {
        val baseStream = ByteArrayOutputStream()
        val stream = BufferedOutputStream(baseStream)
        stream.write(0x01)
        stream.write(0x02)
        stream.write(0x03)
        stream.flush()
        assertEquals(3, baseStream.size())
    }

    @Test
    fun testClose() {
        val baseStream = ByteArrayOutputStream()
        val stream = BufferedOutputStream(baseStream)
        stream.write(0x01)
        stream.write(0x02)
        stream.write(0x03)
        stream.close()
        assertEquals(3, baseStream.size())
    }
}
