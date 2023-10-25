package io.github.shakelang.parseutils.streaming.input

import io.github.shakelang.parseutils.bytes.toBytes
import kotlin.test.Test
import kotlin.test.assertEquals

class CountingInputStreamTests {

    @Test
    fun test() {
        val input = "Hello, world!"
        val stream = CountingInputStream(ByteArrayInputStream(input.toBytes()))
        assertEquals('H'.code.toUByte().toInt(), stream.read())
        assertEquals(1, stream.count)
        assertEquals('e'.code.toUByte().toInt(), stream.read())
        assertEquals(2, stream.count)
        assertEquals('l'.code.toUByte().toInt(), stream.read())
        assertEquals(3, stream.count)
        assertEquals('l'.code.toUByte().toInt(), stream.read())
        assertEquals(4, stream.count)
        assertEquals('o'.code.toUByte().toInt(), stream.read())
        assertEquals(5, stream.count)
        assertEquals(','.code.toUByte().toInt(), stream.read())
        assertEquals(6, stream.count)
        assertEquals(" world!".toBytes().toList(), stream.readNBytes(7).toList())
        assertEquals(13, stream.count)
    }

}