package io.github.shakelang.parseutils.streaming.input

import io.github.shakelang.parseutils.bytes.stream
import io.github.shakelang.parseutils.bytes.toBytes
import kotlin.test.Test
import kotlin.test.assertContentEquals
import kotlin.test.assertEquals

class BufferedInputStreamTests {

    @Test
    fun testRead() {
        val counter = "Hello".toBytes().stream().countingStream
        val stream = counter.bufferedStream

        assertEquals(0, counter.byteCount)
        assertEquals(0, counter.operationCount)

        assertEquals('H'.code, stream.read())

        assertEquals(5, counter.byteCount)
        assertEquals(1, counter.operationCount)

        assertEquals('e'.code, stream.read())

        assertEquals(5, counter.byteCount)
        assertEquals(1, counter.operationCount)

        assertEquals('l'.code, stream.read())

        assertEquals(5, counter.byteCount)
        assertEquals(1, counter.operationCount)

        assertEquals('l'.code, stream.read())

        assertEquals(5, counter.byteCount)
        assertEquals(1, counter.operationCount)

        assertEquals('o'.code, stream.read())

        assertEquals(5, counter.byteCount)
        assertEquals(1, counter.operationCount)

        assertEquals(-1, stream.read())
    }

    @Test
    fun testReadWithByteArray() {
        val counter = "Hello".toBytes().stream().countingStream
        val stream = counter.bufferedStream

        assertEquals(0, counter.byteCount)
        assertEquals(0, counter.operationCount)

        val buffer = ByteArray(2)

        assertEquals(2, stream.read(buffer))

        assertEquals(5, counter.byteCount)
        assertEquals(1, counter.operationCount)

        assertEquals('H'.code.toByte(), buffer[0])
        assertEquals('e'.code.toByte(), buffer[1])

        assertEquals(2, stream.read(buffer))

        assertEquals(5, counter.byteCount)
        assertEquals(1, counter.operationCount)

        assertEquals('l'.code.toByte(), buffer[0])
        assertEquals('l'.code.toByte(), buffer[1])

        assertEquals(1, stream.read(buffer))

        assertEquals(5, counter.byteCount)
        assertEquals(2, counter.operationCount)

        assertEquals('o'.code.toByte(), buffer[0])
        assertEquals(108, buffer[1])
    }

    @Test
    fun testReadWithByteArrayAndOffset() {
        val counter = "Hello".toBytes().stream().countingStream
        val stream = counter.bufferedStream

        assertEquals(0, counter.byteCount)
        assertEquals(0, counter.operationCount)

        val buffer = ByteArray(4)

        assertEquals(2, stream.read(buffer, 1, 2))

        assertEquals(5, counter.byteCount)
        assertEquals(1, counter.operationCount)

        assertEquals(0, buffer[0])
        assertEquals('H'.code.toByte(), buffer[1])
        assertEquals('e'.code.toByte(), buffer[2])
        assertEquals(0, buffer[3])

        assertEquals(2, stream.read(buffer, 1, 2))

        assertEquals(5, counter.byteCount)
        assertEquals(1, counter.operationCount)

        assertEquals(0, buffer[0])
        assertEquals('l'.code.toByte(), buffer[1])
        assertEquals('l'.code.toByte(), buffer[2])
        assertEquals(0, buffer[3])

        assertEquals(1, stream.read(buffer, 1, 2))

        assertEquals(5, counter.byteCount)
        assertEquals(2, counter.operationCount)

        assertEquals(0, buffer[0])
        assertEquals('o'.code.toByte(), buffer[1])
        assertEquals(108, buffer[2])
        assertEquals(0, buffer[3])
    }

    @Test
    fun testSkip() {
        val counter = "Hello".toBytes().stream().countingStream
        val stream = counter.bufferedStream

        assertEquals(0, counter.byteCount)
        assertEquals(0, counter.operationCount)

        assertEquals(2, stream.skip(2))

        assertEquals(5, counter.byteCount)
        assertEquals(1, counter.operationCount)

        assertEquals('l'.code, stream.read())

        assertEquals(5, counter.byteCount)
        assertEquals(1, counter.operationCount)

        assertEquals(2, stream.skip(2))

        assertEquals(5, counter.byteCount)
        assertEquals(1, counter.operationCount)

        assertEquals(-1, stream.read())
    }

    @Test
    fun testReadNBytes() {
        val counter = "Hello".toBytes().stream().countingStream
        val stream = counter.bufferedStream

        assertEquals(0, counter.byteCount)
        assertEquals(0, counter.operationCount)

        assertContentEquals("He".toBytes(), stream.readNBytes(2))

        assertEquals(5, counter.byteCount)
        assertEquals(1, counter.operationCount)

        assertContentEquals("ll".toBytes(), stream.readNBytes(2))

        assertEquals(5, counter.byteCount)
        assertEquals(1, counter.operationCount)

        assertContentEquals(byteArrayOf(111, 0), stream.readNBytes(2))

        assertEquals(5, counter.byteCount)
        assertEquals(2, counter.operationCount)
    }

    @Test
    fun testReadNBytesWithOffset() {
        val counter = "Hello".toBytes().stream().countingStream
        val stream = counter.bufferedStream

        assertEquals(0, counter.byteCount)
        assertEquals(0, counter.operationCount)

        val buffer = ByteArray(4)

        assertEquals(2, stream.readNBytes(buffer, 1, 2))

        assertEquals(5, counter.byteCount)
        assertEquals(1, counter.operationCount)

        assertEquals(0, buffer[0])
        assertEquals('H'.code.toByte(), buffer[1])
        assertEquals('e'.code.toByte(), buffer[2])
        assertEquals(0, buffer[3])

        assertEquals(2, stream.readNBytes(buffer, 1, 2))

        assertEquals(5, counter.byteCount)
        assertEquals(1, counter.operationCount)

        assertEquals(0, buffer[0])
        assertEquals('l'.code.toByte(), buffer[1])
        assertEquals('l'.code.toByte(), buffer[2])
        assertEquals(0, buffer[3])

        assertEquals(1, stream.readNBytes(buffer, 1, 2))

        assertEquals(5, counter.byteCount)
        assertEquals(2, counter.operationCount)

        assertEquals(0, buffer[0])
        assertEquals('o'.code.toByte(), buffer[1])
        assertEquals(108, buffer[2])
        assertEquals(0, buffer[3])
    }

    @Test
    fun testMarkSupported() {
        val counter = "Hello".toBytes().stream().countingStream
        val stream = counter.bufferedStream

        assertEquals(0, counter.byteCount)
        assertEquals(0, counter.operationCount)

        assertEquals(true, stream.markSupported())

        assertEquals(0, counter.byteCount)
        assertEquals(0, counter.operationCount)
    }

    @Test
    fun testMark() {
        val counter = "Hello".toBytes().stream().countingStream
        val stream = counter.bufferedStream

        assertEquals(0, counter.byteCount)
        assertEquals(0, counter.operationCount)

        stream.mark(2)

        assertEquals(0, counter.byteCount)
        assertEquals(0, counter.operationCount)

        assertEquals('H'.code, stream.read())

        assertEquals(5, counter.byteCount)
        assertEquals(1, counter.operationCount)

        assertEquals('e'.code, stream.read())

        assertEquals(5, counter.byteCount)
        assertEquals(1, counter.operationCount)

        stream.reset()

        assertEquals(0, counter.byteCount)
        assertEquals(0, counter.operationCount)

        assertEquals('H'.code, stream.read())

        assertEquals(5, counter.byteCount)
        assertEquals(1, counter.operationCount)

        assertEquals('e'.code, stream.read())

        assertEquals(5, counter.byteCount)
        assertEquals(1, counter.operationCount)
    }

   @Test
    fun testAvailable() {
        val counter = "Hello".toBytes().stream().countingStream
        val stream = counter.bufferedStream

        assertEquals(0, counter.byteCount)
        assertEquals(0, counter.operationCount)

        assertEquals(5, stream.available())

        assertEquals(0, counter.byteCount)
        assertEquals(0, counter.operationCount)

        stream.read()

        assertEquals(4, stream.available())

        assertEquals(1, counter.byteCount)
        assertEquals(1, counter.operationCount)

        stream.readNBytes(2)

        assertEquals(1, stream.available())

        assertEquals(3, counter.byteCount)
        assertEquals(2, counter.operationCount)

        stream.skip(2)

        assertEquals(0, stream.available())

        assertEquals(5, counter.byteCount)
        assertEquals(3, counter.operationCount)
    }

    @Test
    fun testReset() {
        val counter = "Hello".toBytes().stream().countingStream
        val stream = counter.bufferedStream

        assertEquals(0, counter.byteCount)
        assertEquals(0, counter.operationCount)

        stream.read()
        stream.read()
        stream.read()
        stream.read()
        stream.read()

        assertEquals(5, counter.byteCount)
        assertEquals(1, counter.operationCount)

        stream.reset()

        assertEquals(0, counter.byteCount)
        assertEquals(0, counter.operationCount)

        stream.read()
        stream.read()
        stream.read()
        stream.read()
        stream.read()

        assertEquals(5, counter.byteCount)
        assertEquals(1, counter.operationCount)
    }

    @Test
    fun testClose() {
        val counter = "Hello".toBytes().stream().countingStream
        val stream = counter.bufferedStream

        assertEquals(0, counter.byteCount)
        assertEquals(0, counter.operationCount)

        stream.read()
        stream.read()
        stream.read()
        stream.read()
        stream.read()

        assertEquals(5, counter.byteCount)
        assertEquals(1, counter.operationCount)

        stream.close()
    }

    @Test
    fun testToString() {
        val counter = "Hello".toBytes().stream().countingStream
        val stream = counter.bufferedStream

        assertEquals(
            "BufferedInputStream(input=CountingInputStream(data=ByteArrayInputStream[pos=0, " +
                    "mark=0, count=5], byteCount=0, operationCount=0), bufferSize=0, bufferPos=0)",
            stream.toString()
        )

        stream.read()
        stream.read()
        stream.read()
        stream.read()
        stream.read()

        assertEquals(
            "BufferedInputStream(input=CountingInputStream(data=ByteArrayInputStream[pos=5, " +
                    "mark=0, count=5], byteCount=5, operationCount=1), bufferSize=5, bufferPos=5)",
            stream.toString()
        )
    }
}