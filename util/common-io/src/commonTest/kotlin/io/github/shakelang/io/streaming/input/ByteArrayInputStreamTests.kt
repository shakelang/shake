package io.github.shakelang.io.streaming.input

import io.github.shakelang.primitives.bytes.toBytes
import kotlin.test.Test
import kotlin.test.assertContentEquals
import kotlin.test.assertEquals

class ByteArrayInputStreamTests {

    @Test
    fun testConstructor() {
        val stream = "Hello".toBytes().stream()
        assertEquals(5, stream.available())
        assertEquals('H'.code, stream.read())

        // Test with offset and length
        val stream2 = ByteArrayInputStream("Hello".toBytes(), 1, 3)
        assertEquals(3, stream2.available())
        assertEquals('e'.code, stream2.read())
        assertEquals('l'.code, stream2.read())
        assertEquals('l'.code, stream2.read())
        assertEquals(-1, stream2.read())
    }

    @Test
    fun testRead() {
        val stream = "Hello".toBytes().stream()

        assertEquals('H'.code, stream.read())
        assertEquals('e'.code, stream.read())
        assertEquals('l'.code, stream.read())
        assertEquals('l'.code, stream.read())
        assertEquals('o'.code, stream.read())
        assertEquals(-1, stream.read())
    }

    @Test
    fun testReadWithByteArray() {
        val stream = "Hello".toBytes().stream()
        val buffer = ByteArray(2)
        assertEquals(2, stream.read(buffer))
        assertEquals('H'.code.toByte(), buffer[0])
        assertEquals('e'.code.toByte(), buffer[1])
        assertEquals(2, stream.read(buffer))
        assertEquals('l'.code.toByte(), buffer[0])
        assertEquals('l'.code.toByte(), buffer[1])
        assertEquals(1, stream.read(buffer))
        assertEquals('o'.code.toByte(), buffer[0])
        assertEquals(108, buffer[1])
    }

    @Test
    fun testReadWithByteArrayAndOffset() {
        val stream = "Hello".toBytes().stream()
        val buffer = ByteArray(4)
        assertEquals(2, stream.read(buffer, 1, 2))
        assertEquals(0, buffer[0])
        assertEquals('H'.code.toByte(), buffer[1])
        assertEquals('e'.code.toByte(), buffer[2])
        assertEquals(0, buffer[3])
        assertEquals(2, stream.read(buffer, 1, 2))
        assertEquals(0, buffer[0])
        assertEquals('l'.code.toByte(), buffer[1])
        assertEquals('l'.code.toByte(), buffer[2])
        assertEquals(0, buffer[3])
        assertEquals(1, stream.read(buffer, 1, 2))
        assertEquals(0, buffer[0])
        assertEquals('o'.code.toByte(), buffer[1])
        assertEquals(108, buffer[2])
        assertEquals(0, buffer[3])
    }

    @Test
    fun testSkip() {
        val stream = "Hello".toBytes().stream()
        assertEquals(2, stream.skip(2))
        assertEquals('l'.code, stream.read())
        assertEquals(2, stream.skip(2))
        assertEquals(-1, stream.read())

        // Test with to many bytes to skip
        val stream2 = "Hello".toBytes().stream()
        assertEquals(5, stream2.skip(10))
        assertEquals(-1, stream2.read())

        // Test with negative amount of bytes to skip
        val stream3 = "Hello".toBytes().stream()
        assertEquals(0, stream3.skip(-10))
        assertEquals('H'.code, stream3.read())
    }

    @Test
    fun testReadNBytes() {
        val stream = "Hello".toBytes().stream()
        assertContentEquals("He".toBytes(), stream.readNBytes(2))
        assertContentEquals("ll".toBytes(), stream.readNBytes(2))
        assertContentEquals(byteArrayOf(111), stream.readNBytes(1))
    }

    @Test
    fun testReadNBytesWithOffset() {
        val stream = "Hello".toBytes().stream()
        val buffer = ByteArray(4)
        assertEquals(2, stream.readNBytes(buffer, 1, 2))
        assertEquals(0, buffer[0])
        assertEquals('H'.code.toByte(), buffer[1])
        assertEquals('e'.code.toByte(), buffer[2])
        assertEquals(0, buffer[3])
        assertEquals(2, stream.readNBytes(buffer, 1, 2))
        assertEquals(0, buffer[0])
        assertEquals('l'.code.toByte(), buffer[1])
        assertEquals('l'.code.toByte(), buffer[2])
        assertEquals(0, buffer[3])
        assertEquals(1, stream.readNBytes(buffer, 1, 2))
        assertEquals(0, buffer[0])
        assertEquals('o'.code.toByte(), buffer[1])
        assertEquals(108, buffer[2])
        assertEquals(0, buffer[3])
    }

    @Test
    fun testMarkSupported() {
        val stream = "Hello".toBytes().stream()
        assertEquals(true, stream.markSupported())
    }

    @Test
    fun testMark() {
        val stream = "Hello".toBytes().stream()
        stream.mark(2)
        assertEquals('H'.code, stream.read())
        assertEquals('e'.code, stream.read())
        stream.reset()
        assertEquals('H'.code, stream.read())
        assertEquals('e'.code, stream.read())
    }

    @Test
    fun testAvailable() {
        val stream = "Hello".toBytes().stream()
        assertEquals(5, stream.available())
        stream.read()
        assertEquals(4, stream.available())
        stream.readNBytes(2)
        assertEquals(2, stream.available())
        stream.skip(2)
        assertEquals(0, stream.available())
    }

    @Test
    fun testReset() {
        val stream = "Hello".toBytes().stream()
        stream.read()
        stream.read()
        stream.read()
        stream.read()
        stream.read()
        stream.reset()
        stream.read()
        stream.read()
        stream.read()
        stream.read()
        stream.read()
    }

    @Test
    fun testClose() {
        val stream = "Hello".toBytes().stream()
        stream.read()
        stream.read()
        stream.read()
        stream.read()
        stream.read()
        stream.close()
    }

    @Test
    fun testToString() {
        val stream = "Hello".toBytes().stream()

        assertEquals(
            "ByteArrayInputStream(pos=0, mark=0, count=5)",
            stream.toString()
        )

        stream.read()
        stream.read()
        stream.read()
        stream.read()
        stream.read()

        assertEquals(
            "ByteArrayInputStream(pos=5, mark=0, count=5)",
            stream.toString()
        )
    }

}