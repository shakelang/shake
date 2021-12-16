package io.github.shakelang.parseutils.streaming.input

import kotlin.test.Test
import kotlin.test.assertEquals

class BufferedInputStreamTests {

    @Test
    fun testBufferedInputStream() {
        val inputStream = BufferedInputStream(ByteArrayInputStream(byteArrayOf(0, 1, 2, 3, 4, 5, 6, 7, 8, 9)))
        assertEquals(0, inputStream.read())
        assertEquals(1, inputStream.read())
        assertEquals(2, inputStream.read())
        assertEquals(3, inputStream.read())
        assertEquals(4, inputStream.read())
        assertEquals(5, inputStream.read())
        assertEquals(6, inputStream.read())
        assertEquals(7, inputStream.read())
        assertEquals(8, inputStream.read())
        assertEquals(9, inputStream.read())
        assertEquals(-1, inputStream.read())
    }

}