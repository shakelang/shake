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
        assertEquals(1, stream.getCount())
        assertEquals('e'.code.toUByte().toInt(), stream.read())
        assertEquals(2, stream.getCount())
        assertEquals('l'.code.toUByte().toInt(), stream.read())
        assertEquals(3, stream.getCount())
        assertEquals('l'.code.toUByte().toInt(), stream.read())
        assertEquals(4, stream.getCount())
        assertEquals('o'.code.toUByte().toInt(), stream.read())
        assertEquals(5, stream.getCount())
        assertEquals(','.code.toUByte().toInt(), stream.read())
        assertEquals(6, stream.getCount())
        assertEquals(" world!".toBytes().toList(), stream.readNBytes(7).toList())
        assertEquals(13, stream.getCount())
    }

}