package io.github.shakelang.shake.util.primitives.bytes

import kotlin.test.Test
import kotlin.test.assertEquals

class StringTests {

    @Test
    fun testStringToBytes() {
        val str = "Hello, world!"
        val bytes = str.toBytes()
        assertEquals(str.length, bytes.size)
        assertEquals('H'.code.toByte(), bytes[0])
        assertEquals('e'.code.toByte(), bytes[1])
        assertEquals('l'.code.toByte(), bytes[2])
        assertEquals('l'.code.toByte(), bytes[3])
        assertEquals('o'.code.toByte(), bytes[4])
        assertEquals(','.code.toByte(), bytes[5])
        assertEquals(' '.code.toByte(), bytes[6])
        assertEquals('w'.code.toByte(), bytes[7])
        assertEquals('o'.code.toByte(), bytes[8])
        assertEquals('r'.code.toByte(), bytes[9])
        assertEquals('l'.code.toByte(), bytes[10])
        assertEquals('d'.code.toByte(), bytes[11])
        assertEquals('!'.code.toByte(), bytes[12])
    }

}