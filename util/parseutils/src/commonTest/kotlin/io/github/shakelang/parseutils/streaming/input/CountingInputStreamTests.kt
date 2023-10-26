package io.github.shakelang.parseutils.streaming.input

import io.github.shakelang.parseutils.bytes.toBytes
import kotlin.test.Test
import kotlin.test.assertEquals

class CountingInputStreamTests {

    @Test
    fun testCount() {
        val input = "Hello, world!"
        val stream = ByteArrayInputStream(input.toBytes()).countingStream
        assertEquals('H'.code.toUByte().toInt(), stream.read())
        assertEquals(1, stream.count)
        stream.skip(1)
        assertEquals(2, stream.count)
        assertEquals('l'.code.toUByte().toInt(), stream.read())
        assertEquals(3, stream.count)
        stream.skip(1)
        assertEquals(4, stream.count)
        assertEquals('o'.code.toUByte().toInt(), stream.read())
        assertEquals(5, stream.count)
        assertEquals(','.code.toUByte().toInt(), stream.read())
        assertEquals(6, stream.count)
        assertEquals(" world!".toBytes().toList(), stream.readNBytes(7).toList())
        assertEquals(13, stream.count)
    }

    @Test
    fun testByteCount() {
        val input = "Hello, world!"
        val stream = ByteArrayInputStream(input.toBytes()).countingStream
        assertEquals('H'.code.toUByte().toInt(), stream.read())
        assertEquals(1, stream.byteCount)
        assertEquals('e'.code.toUByte().toInt(), stream.read())
        assertEquals(2, stream.byteCount)
        stream.skip(1)
        assertEquals(3, stream.byteCount)
        assertEquals('l'.code.toUByte().toInt(), stream.read())
        assertEquals(4, stream.byteCount)
        stream.skip(1)
        assertEquals(5, stream.byteCount)
        assertEquals(','.code.toUByte().toInt(), stream.read())
        assertEquals(6, stream.byteCount)
        assertEquals(" world!".toBytes().toList(), stream.readNBytes(7).toList())
        assertEquals(13, stream.byteCount)
    }

    @Test
fun testOperationCount() {
        val input = "Hello, world!"
        val stream = ByteArrayInputStream(input.toBytes()).countingStream
        assertEquals('H'.code.toUByte().toInt(), stream.read())
        assertEquals(1, stream.operationCount)
        stream.skip(3)
        assertEquals(2, stream.operationCount)
        assertEquals('o'.code.toUByte().toInt(), stream.read())
        assertEquals(3, stream.operationCount)
        assertEquals(','.code.toUByte().toInt(), stream.read())
        assertEquals(4, stream.operationCount)
        assertEquals(" world!".toBytes().toList(), stream.readNBytes(7).toList())
        assertEquals(5, stream.operationCount)
    }

}