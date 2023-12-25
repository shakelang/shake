package com.shakelang.util.io.streaming.input

import com.shakelang.util.primitives.bytes.toBytes
import kotlin.test.Test
import kotlin.test.assertContentEquals
import kotlin.test.assertIs

class ExpansionTests {

    @Test
    fun testStreamAsDataInputStream() {
        val stream = byteArrayOf(0x01, 0x02, 0x03, 0x04, 0x05).inputStream()
        val dataStream = stream.asDataInputStream()
        assertIs<DataInputStream>(dataStream)

        val dataStream2 = stream.dataStream
        assertIs<DataInputStream>(dataStream2)
    }

    @Test
    fun testStreamAsCountingInputStream() {
        val stream = byteArrayOf(0x01, 0x02, 0x03, 0x04, 0x05).countingStream()
        val countingStream = stream.asCountingInputStream()
        assertIs<CountingInputStream>(countingStream)

        val countingStream2 = stream.countingStream
        assertIs<CountingInputStream>(countingStream2)
    }

    @Test
    fun testStreamAsBufferedInputStream() {
        val stream = byteArrayOf(0x01, 0x02, 0x03, 0x04, 0x05).inputStream()
        val bufferedStream = stream.asBufferedInputStream()
        assertIs<BufferedInputStream>(bufferedStream)

        val bufferedStream2 = stream.bufferedStream
        assertIs<BufferedInputStream>(bufferedStream2)
    }

    @Test
    fun testFullyRead() {
        val stream = byteArrayOf(0x01, 0x02, 0x03, 0x04, 0x05).inputStream()
        assertContentEquals(byteArrayOf(0x01, 0x02, 0x03, 0x04, 0x05), stream.readFully)
    }

    @Test
    fun testByteArrayStream() {
        val stream = byteArrayOf(0x01, 0x02, 0x03, 0x04, 0x05).inputStream()
        assertContentEquals(byteArrayOf(0x01, 0x02, 0x03, 0x04, 0x05), stream.readFully)

        val stream2 = byteArrayOf(0x01, 0x02, 0x03, 0x04, 0x05).inputStream()
        assertContentEquals(byteArrayOf(0x01, 0x02, 0x03, 0x04, 0x05), stream2.readFully)
    }

    @Test
    fun testByteArrayDataStream() {
        val stream = byteArrayOf(0x01, 0x02, 0x03, 0x04, 0x05).dataStream()
        assertIs<DataInputStream>(stream)
        assertContentEquals(byteArrayOf(0x01, 0x02, 0x03, 0x04, 0x05), stream.readFully)
    }

    @Test
    fun testByteArrayCountingStream() {
        val stream = byteArrayOf(0x01, 0x02, 0x03, 0x04, 0x05).countingStream()
        assertIs<CountingInputStream>(stream)
        assertContentEquals(byteArrayOf(0x01, 0x02, 0x03, 0x04, 0x05), stream.readFully)
    }

    @Test
    fun testByteArrayBufferedStream() {
        val stream = byteArrayOf(0x01, 0x02, 0x03, 0x04, 0x05).bufferedStream()
        assertIs<BufferedInputStream>(stream)
        assertContentEquals(byteArrayOf(0x01, 0x02, 0x03, 0x04, 0x05), stream.readFully)
    }

    @Test
    fun testByteListStream() {
        val stream = listOf<Byte>(0x01, 0x02, 0x03, 0x04, 0x05).inputStream()
        assertContentEquals(byteArrayOf(0x01, 0x02, 0x03, 0x04, 0x05), stream.readFully)
    }

    @Test
    fun testByteListDataStream() {
        val stream = listOf<Byte>(0x01, 0x02, 0x03, 0x04, 0x05).dataStream()
        assertIs<DataInputStream>(stream)
        assertContentEquals(byteArrayOf(0x01, 0x02, 0x03, 0x04, 0x05), stream.readFully)
    }

    @Test
    fun testByteListCountingStream() {
        val stream = listOf<Byte>(0x01, 0x02, 0x03, 0x04, 0x05).countingStream()
        assertIs<CountingInputStream>(stream)
        assertContentEquals(byteArrayOf(0x01, 0x02, 0x03, 0x04, 0x05), stream.readFully)
    }

    @Test
    fun testByteListBufferedStream() {
        val stream = listOf<Byte>(0x01, 0x02, 0x03, 0x04, 0x05).bufferedStream()
        assertIs<BufferedInputStream>(stream)
        assertContentEquals(byteArrayOf(0x01, 0x02, 0x03, 0x04, 0x05), stream.readFully)
    }

    @Test
    fun testCharSequenceByteStream() {
        val stream = "Hello World!".byteStream()
        assertContentEquals("Hello World!".toBytes(), stream.readFully)

        val stream2 = "Hello World!".byteInputStream()
        assertContentEquals("Hello World!".toBytes(), stream2.readFully)
    }

    @Test
    fun testCharSequenceByteDataStream() {
        val stream = "Hello World!".byteDataStream()
        assertIs<DataInputStream>(stream)
        assertContentEquals("Hello World!".toBytes(), stream.readFully)
    }

    @Test
    fun testCharSequenceByteCountingStream() {
        val stream = "Hello World!".byteCountingStream()
        assertIs<CountingInputStream>(stream)
        assertContentEquals("Hello World!".toBytes(), stream.readFully)
    }

    @Test
    fun testCharSequenceByteBufferedStream() {
        val stream = "Hello World!".byteBufferedStream()
        assertIs<BufferedInputStream>(stream)
        assertContentEquals("Hello World!".toBytes(), stream.readFully)
    }
}
