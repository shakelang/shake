@file:OptIn(ExperimentalUnsignedTypes::class)

package com.shakelang.util.io.streaming.input

import com.shakelang.util.primitives.bytes.byteArrayOf
import kotlin.test.Test
import kotlin.test.assertEquals

@Test
fun testByteArrayStream() {
    val byteArray = byteArrayOf(0x00u, 0xFFu)
    val stream = byteArray.inputStream()
    assertEquals(0x00, stream.read())
    assertEquals(0xFF, stream.read())
}

@Test
fun testByteArrayDataInputStream() {
    val byteArray = byteArrayOf(0x00u, 0xFFu)
    val stream = byteArray.dataStream()
    assertEquals(0x00, stream.read())
    assertEquals(0xFF, stream.read())
}

@Test
fun testByteArrayCountingInputStream() {
    val byteArray = byteArrayOf(0x00, -1)
    val stream = byteArray.countingStream()
    assertEquals(0x00, stream.read())
    assertEquals(0xFF, stream.read())
    assertEquals(2, stream.count)
}

@Test
fun testInputStream() {
    val bytes = listOf<Byte>(1, 2, 3, 4, 5, 6, 7, 8)
    val inputStream = bytes.inputStream()
    assertEquals(1, inputStream.read())
    assertEquals(2, inputStream.read())
    assertEquals(3, inputStream.read())
    assertEquals(4, inputStream.read())
    assertEquals(5, inputStream.read())
    assertEquals(6, inputStream.read())
    assertEquals(7, inputStream.read())
    assertEquals(8, inputStream.read())
}

@Test
fun testCountingInputStream() {
    val bytes = listOf<Byte>(1, 2, 3, 4, 5, 6, 7, 8)
    val countingInputStream = bytes.countingStream()
    assertEquals(0, countingInputStream.count)
    assertEquals(1, countingInputStream.read())
    assertEquals(1, countingInputStream.count)
    assertEquals(2, countingInputStream.read())
    assertEquals(2, countingInputStream.count)
    assertEquals(3, countingInputStream.read())
    assertEquals(3, countingInputStream.count)
    assertEquals(4, countingInputStream.read())
    assertEquals(4, countingInputStream.count)
    assertEquals(5, countingInputStream.read())
    assertEquals(5, countingInputStream.count)
    assertEquals(6, countingInputStream.read())
    assertEquals(6, countingInputStream.count)
    assertEquals(7, countingInputStream.read())
    assertEquals(7, countingInputStream.count)
    assertEquals(8, countingInputStream.read())
    assertEquals(8, countingInputStream.count)
}

@Test
fun testDataInputStream() {
    val bytes = listOf<Byte>(1, 2, 3, 4, 5, 6, 7, 8)
    val dataInputStream = bytes.dataStream()
    assertEquals(1, dataInputStream.readByte())
    assertEquals(2, dataInputStream.readByte())
    assertEquals(3, dataInputStream.readByte())
    assertEquals(4, dataInputStream.readByte())
    assertEquals(5, dataInputStream.readByte())
    assertEquals(6, dataInputStream.readByte())
    assertEquals(7, dataInputStream.readByte())
    assertEquals(8, dataInputStream.readByte())
}

@Test
fun testByteInputStream() {
    val str = "Hello, world!"
    val stream = str.byteInputStream()
    assertEquals('H'.code, stream.read())
    assertEquals('e'.code, stream.read())
    assertEquals('l'.code, stream.read())
    assertEquals('l'.code, stream.read())
    assertEquals('o'.code, stream.read())
    assertEquals(','.code, stream.read())
    assertEquals(' '.code, stream.read())
    assertEquals('w'.code, stream.read())
    assertEquals('o'.code, stream.read())
    assertEquals('r'.code, stream.read())
    assertEquals('l'.code, stream.read())
    assertEquals('d'.code, stream.read())
    assertEquals('!'.code, stream.read())
}

@Test
fun testCountingByteInputStream() {
    val str = "Hello, world!"
    val stream = str.byteCountingStream()
    assertEquals(0, stream.count)
    assertEquals('H'.code, stream.read())
    assertEquals(1, stream.count)
    assertEquals('e'.code, stream.read())
    assertEquals(2, stream.count)
    assertEquals('l'.code, stream.read())
    assertEquals(3, stream.count)
    assertEquals('l'.code, stream.read())
    assertEquals(4, stream.count)
    assertEquals('o'.code, stream.read())
    assertEquals(5, stream.count)
    assertEquals(','.code, stream.read())
    assertEquals(6, stream.count)
    assertEquals(' '.code, stream.read())
    assertEquals(7, stream.count)
    assertEquals('w'.code, stream.read())
    assertEquals(8, stream.count)
    assertEquals('o'.code, stream.read())
    assertEquals(9, stream.count)
    assertEquals('r'.code, stream.read())
    assertEquals(10, stream.count)
    assertEquals('l'.code, stream.read())
    assertEquals(11, stream.count)
    assertEquals('d'.code, stream.read())
    assertEquals(12, stream.count)
    assertEquals('!'.code, stream.read())
    assertEquals(13, stream.count)
}

@Test
fun testDataByteInputStream() {
    val str = "Hello, world!"
    val stream = str.byteDataStream()
    assertEquals('H'.code.toByte(), stream.readByte())
    assertEquals('e'.code.toByte(), stream.readByte())
    assertEquals('l'.code.toByte(), stream.readByte())
    assertEquals('l'.code.toByte(), stream.readByte())
    assertEquals('o'.code.toByte(), stream.readByte())
    assertEquals(','.code.toByte(), stream.readByte())
    assertEquals(' '.code.toByte(), stream.readByte())
    assertEquals('w'.code.toByte(), stream.readByte())
    assertEquals('o'.code.toByte(), stream.readByte())
    assertEquals('r'.code.toByte(), stream.readByte())
    assertEquals('l'.code.toByte(), stream.readByte())
    assertEquals('d'.code.toByte(), stream.readByte())
    assertEquals('!'.code.toByte(), stream.readByte())
}
