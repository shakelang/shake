package io.github.shakelang.io.streaming.input

import io.github.shakelang.primitives.bytes.toBytes
import kotlin.test.Test
import kotlin.test.assertContentEquals
import kotlin.test.assertEquals

class CountingInputStreamTests {

    @Test
    fun testRead() {
        val stream = "Hello".toBytes().stream().countingStream

        assertEquals(0, stream.byteCount)
        assertEquals(0, stream.count)
        assertEquals(0, stream.operationCount)

        assertEquals('H'.code, stream.read())

        assertEquals(1, stream.byteCount)
        assertEquals(1, stream.count)
        assertEquals(1, stream.operationCount)

        assertEquals('e'.code, stream.read())

        assertEquals(2, stream.byteCount)
        assertEquals(2, stream.count)
        assertEquals(2, stream.operationCount)

        assertEquals('l'.code, stream.read())
        assertEquals('l'.code, stream.read())

        assertEquals(4, stream.byteCount)

        assertEquals('o'.code, stream.read())

        assertEquals(5, stream.byteCount)
        assertEquals(5, stream.count)
        assertEquals(5, stream.operationCount)

        assertEquals(-1, stream.read())
    }

    @Test
    fun testReadWithByteArray() {
        val stream = "Hello".toBytes().stream().countingStream

        assertEquals(0, stream.byteCount)
        assertEquals(0, stream.count)
        assertEquals(0, stream.operationCount)

        val buffer = ByteArray(2)

        assertEquals(2, stream.read(buffer))

        assertEquals(2, stream.byteCount)
        assertEquals(2, stream.count)
        assertEquals(1, stream.operationCount)

        assertEquals('H'.code.toByte(), buffer[0])
        assertEquals('e'.code.toByte(), buffer[1])

        assertEquals(2, stream.read(buffer))

        assertEquals(4, stream.byteCount)
        assertEquals(4, stream.count)
        assertEquals(2, stream.operationCount)

        assertEquals('l'.code.toByte(), buffer[0])
        assertEquals('l'.code.toByte(), buffer[1])

        assertEquals(1, stream.read(buffer))

        assertEquals(5, stream.byteCount)
        assertEquals(5, stream.count)
        assertEquals(3, stream.operationCount)

        assertEquals('o'.code.toByte(), buffer[0])
        assertEquals(108, buffer[1])
    }

    @Test
    fun testReadWithByteArrayAndOffset() {
        val stream = "Hello".toBytes().stream().countingStream

        assertEquals(0, stream.byteCount)
        assertEquals(0, stream.count)
        assertEquals(0, stream.operationCount)

        val buffer = ByteArray(4)

        assertEquals(2, stream.read(buffer, 1, 2))

        assertEquals(2, stream.byteCount)
        assertEquals(2, stream.count)
        assertEquals(1, stream.operationCount)

        assertEquals(0, buffer[0])
        assertEquals('H'.code.toByte(), buffer[1])
        assertEquals('e'.code.toByte(), buffer[2])
        assertEquals(0, buffer[3])

        assertEquals(2, stream.read(buffer, 1, 2))

        assertEquals(4, stream.byteCount)
        assertEquals(4, stream.count)
        assertEquals(2, stream.operationCount)

        assertEquals(0, buffer[0])
        assertEquals('l'.code.toByte(), buffer[1])
        assertEquals('l'.code.toByte(), buffer[2])
        assertEquals(0, buffer[3])

        assertEquals(1, stream.read(buffer, 1, 2))

        assertEquals(5, stream.byteCount)
        assertEquals(5, stream.count)
        assertEquals(3, stream.operationCount)

        assertEquals(0, buffer[0])
        assertEquals('o'.code.toByte(), buffer[1])
        assertEquals(108, buffer[2])
        assertEquals(0, buffer[3])
    }

    @Test
    fun testSkip() {
        val stream = "Hello".toBytes().stream().countingStream

        assertEquals(0, stream.byteCount)
        assertEquals(0, stream.count)
        assertEquals(0, stream.operationCount)

        assertEquals(2, stream.skip(2))

        assertEquals(2, stream.byteCount)
        assertEquals(2, stream.count)
        assertEquals(1, stream.operationCount)

        assertEquals('l'.code, stream.read())

        assertEquals(3, stream.byteCount)
        assertEquals(3, stream.count)
        assertEquals(2, stream.operationCount)

        assertEquals(2, stream.skip(2))

        assertEquals(5, stream.byteCount)
        assertEquals(5, stream.count)
        assertEquals(3, stream.operationCount)

        assertEquals(-1, stream.read())
    }

    @Test
    fun testReadNBytes() {
        val stream = "Hello".toBytes().stream().countingStream

        assertEquals(0, stream.byteCount)
        assertEquals(0, stream.count)
        assertEquals(0, stream.operationCount)

        assertContentEquals("He".toBytes(), stream.readNBytes(2))

        assertEquals(2, stream.byteCount)
        assertEquals(2, stream.count)
        assertEquals(1, stream.operationCount)

        assertContentEquals("ll".toBytes(), stream.readNBytes(2))

        assertEquals(4, stream.byteCount)
        assertEquals(4, stream.count)
        assertEquals(2, stream.operationCount)

        assertContentEquals(byteArrayOf(111), stream.readNBytes(1))

        assertEquals(5, stream.byteCount)
        assertEquals(5, stream.count)
        assertEquals(3, stream.operationCount)
    }

    @Test
    fun testReadNBytesWithOffset() {
        val stream = "Hello".toBytes().stream().countingStream

        assertEquals(0, stream.byteCount)
        assertEquals(0, stream.count)
        assertEquals(0, stream.operationCount)

        val buffer = ByteArray(4)

        assertEquals(2, stream.readNBytes(buffer, 1, 2))

        assertEquals(2, stream.byteCount)
        assertEquals(2, stream.count)
        assertEquals(1, stream.operationCount)

        assertEquals(0, buffer[0])
        assertEquals('H'.code.toByte(), buffer[1])
        assertEquals('e'.code.toByte(), buffer[2])
        assertEquals(0, buffer[3])

        assertEquals(2, stream.readNBytes(buffer, 1, 2))

        assertEquals(4, stream.byteCount)
        assertEquals(4, stream.count)
        assertEquals(2, stream.operationCount)

        assertEquals(0, buffer[0])
        assertEquals('l'.code.toByte(), buffer[1])
        assertEquals('l'.code.toByte(), buffer[2])
        assertEquals(0, buffer[3])

        assertEquals(1, stream.readNBytes(buffer, 1, 2))

        assertEquals(5, stream.byteCount)
        assertEquals(5, stream.count)
        assertEquals(3, stream.operationCount)

        assertEquals(0, buffer[0])
        assertEquals('o'.code.toByte(), buffer[1])
        assertEquals(108, buffer[2])
        assertEquals(0, buffer[3])
    }

    @Test
    fun testMarkSupported() {
        val stream = "Hello".toBytes().stream().countingStream

        assertEquals(0, stream.byteCount)
        assertEquals(0, stream.count)
        assertEquals(0, stream.operationCount)

        assertEquals(true, stream.markSupported())

        assertEquals(0, stream.byteCount)
        assertEquals(0, stream.count)
        assertEquals(0, stream.operationCount)
    }

    @Test
    fun testMark() {
        val stream = "Hello".toBytes().stream().countingStream

        assertEquals(0, stream.byteCount)
        assertEquals(0, stream.count)
        assertEquals(0, stream.operationCount)

        stream.mark(2)

        assertEquals(0, stream.byteCount)
        assertEquals(0, stream.count)
        assertEquals(0, stream.operationCount)

        assertEquals('H'.code, stream.read())

        assertEquals(1, stream.byteCount)
        assertEquals(1, stream.count)
        assertEquals(1, stream.operationCount)

        assertEquals('e'.code, stream.read())

        assertEquals(2, stream.byteCount)
        assertEquals(2, stream.count)
        assertEquals(2, stream.operationCount)

        stream.reset()

        assertEquals(0, stream.byteCount)
        assertEquals(0, stream.count)
        assertEquals(0, stream.operationCount)

        assertEquals('H'.code, stream.read())

        assertEquals(1, stream.byteCount)
        assertEquals(1, stream.count)
        assertEquals(1, stream.operationCount)

        assertEquals('e'.code, stream.read())

        assertEquals(2, stream.byteCount)
        assertEquals(2, stream.count)
        assertEquals(2, stream.operationCount)
    }

    @Test
    fun testAvailable() {
        val stream = "Hello".toBytes().stream().countingStream

        assertEquals(0, stream.byteCount)
        assertEquals(0, stream.count)
        assertEquals(0, stream.operationCount)

        assertEquals(5, stream.available())

        assertEquals(0, stream.byteCount)
        assertEquals(0, stream.count)
        assertEquals(0, stream.operationCount)

        stream.read()

        assertEquals(4, stream.available())

        assertEquals(1, stream.byteCount)
        assertEquals(1, stream.count)
        assertEquals(1, stream.operationCount)

        stream.readNBytes(2)

        assertEquals(2, stream.available())

        assertEquals(3, stream.byteCount)
        assertEquals(3, stream.count)
        assertEquals(2, stream.operationCount)

        stream.skip(2)

        assertEquals(0, stream.available())

        assertEquals(5, stream.byteCount)
        assertEquals(5, stream.count)
        assertEquals(3, stream.operationCount)
    }

    @Test
    fun testReset() {
        val stream = "Hello".toBytes().stream().countingStream

        assertEquals(0, stream.byteCount)
        assertEquals(0, stream.count)
        assertEquals(0, stream.operationCount)

        stream.read()
        stream.read()
        stream.read()
        stream.read()
        stream.read()

        assertEquals(5, stream.byteCount)
        assertEquals(5, stream.count)
        assertEquals(5, stream.operationCount)

        stream.reset()

        assertEquals(0, stream.byteCount)
        assertEquals(0, stream.count)
        assertEquals(0, stream.operationCount)

        stream.read()
        stream.read()
        stream.read()
        stream.read()
        stream.read()

        assertEquals(5, stream.byteCount)
        assertEquals(5, stream.count)
        assertEquals(5, stream.operationCount)
    }

    @Test
    fun testClose() {
        val stream = "Hello".toBytes().stream().countingStream

        assertEquals(0, stream.byteCount)
        assertEquals(0, stream.count)
        assertEquals(0, stream.operationCount)

        stream.read()
        stream.read()
        stream.read()
        stream.read()
        stream.read()

        assertEquals(5, stream.byteCount)
        assertEquals(5, stream.count)
        assertEquals(5, stream.operationCount)

        stream.close()
    }

    @Test
    fun testToString() {
        val stream = "Hello".toBytes().stream().countingStream

        assertEquals(
            "CountingInputStream(data=ByteArrayInputStream(pos=0, mark=0, count=5), byteCount=0," +
                    " operationCount=0)",
            stream.toString()
        )

        stream.read()
        stream.read()
        stream.read()
        stream.read()
        stream.read()

        assertEquals(
            "CountingInputStream(data=ByteArrayInputStream(pos=5, mark=0, count=5), byteCount=5, " +
                    "operationCount=5)",
            stream.toString()
        )
    }

}