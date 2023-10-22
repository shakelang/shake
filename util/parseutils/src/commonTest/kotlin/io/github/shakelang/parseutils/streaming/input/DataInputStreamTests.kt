package io.github.shakelang.parseutils.streaming.input

import io.github.shakelang.parseutils.bytes.byteArrayOf
import kotlin.test.Test
import kotlin.test.assertEquals

class DataInputStreamTests {

    @Test
    fun test() {
        val bytes = ByteArrayInputStream(byteArrayOf(0x00u, 0x01u, 0x02u, 0x03u, 0x04u, 0x05u, 0x06u, 0x07u, 0x08u, 0x09u, 0x0Au, 0x0Bu, 0x0Cu, 0x0Du, 0x0Eu, 0x0Fu))
        val stream = DataInputStream(bytes)
        assertEquals(0x00, stream.read())
        assertEquals(0x01, stream.read())
        assertEquals(0x02, stream.read())
        assertEquals(0x03, stream.read())
        assertEquals(0x04, stream.read())
        assertEquals(0x05, stream.read())
        assertEquals(0x06, stream.read())
        assertEquals(0x07, stream.read())
        assertEquals(0x08, stream.read())
        assertEquals(0x09, stream.read())
        assertEquals(0x0A, stream.read())
        assertEquals(0x0B, stream.read())
        assertEquals(0x0C, stream.read())
        assertEquals(0x0D, stream.read())
        assertEquals(0x0E, stream.read())
        assertEquals(0x0F, stream.read())
    }

    @Test
    fun testReadBulk() {
        val bytes = ByteArrayInputStream(byteArrayOf(0x00u, 0x01u, 0x02u, 0x03u, 0x04u, 0x05u, 0x06u, 0x07u, 0x08u, 0x09u, 0x0Au, 0x0Bu, 0x0Cu, 0x0Du, 0x0Eu, 0x0Fu))
        val stream = DataInputStream(bytes)
        val buffer = ByteArray(16)
        assertEquals(16, stream.read(buffer))
        assertEquals(0x00, buffer[0])
        assertEquals(0x01, buffer[1])
        assertEquals(0x02, buffer[2])
        assertEquals(0x03, buffer[3])
        assertEquals(0x04, buffer[4])
        assertEquals(0x05, buffer[5])
        assertEquals(0x06, buffer[6])
        assertEquals(0x07, buffer[7])
        assertEquals(0x08, buffer[8])
        assertEquals(0x09, buffer[9])
        assertEquals(0x0A, buffer[10])
        assertEquals(0x0B, buffer[11])
        assertEquals(0x0C, buffer[12])
        assertEquals(0x0D, buffer[13])
        assertEquals(0x0E, buffer[14])
        assertEquals(0x0F, buffer[15])
    }

    @Test
    fun testSkip() {
        val bytes = ByteArrayInputStream(byteArrayOf(0x00u, 0x01u, 0x02u, 0x03u, 0x04u, 0x05u, 0x06u, 0x07u, 0x08u, 0x09u, 0x0Au, 0x0Bu, 0x0Cu, 0x0Du, 0x0Eu, 0x0Fu))
        val stream = DataInputStream(bytes)
        assertEquals(0x00, stream.read())
        stream.skip(3)
        assertEquals(0x04, stream.read())
        stream.skip(4)
        assertEquals(0x09, stream.read())
        stream.skip(5)
        assertEquals(0x0F, stream.read())
    }

    @Test
    fun testReadByte() {
        val bytes = ByteArrayInputStream(byteArrayOf(0x00, 0x7F, -0x00, -0x7F))
        val stream = DataInputStream(bytes)
        assertEquals(0x00, stream.readByte())
        assertEquals(0x7F, stream.readByte())
        assertEquals(-0x00, stream.readByte())
        assertEquals(-0x7F, stream.readByte())
    }

    @Test
    fun testReadShort() {
        val bytes = ByteArrayInputStream(byteArrayOf(0x00u, 0x01u, 0x7Fu, 0xFFu, 0x00u, 0x7Fu, 0xFFu, 0x00u, 0x7Fu, 0xFFu,
            0x00u, 0x7Fu, 0xFFu, 0x00u, 0x7Fu, 0xFFu))
        val stream = DataInputStream(bytes)
        assertEquals(0x0001u.toShort(), stream.readShort())
        assertEquals(0x7FFFu.toShort(), stream.readShort())
        assertEquals(0x007Fu.toShort(), stream.readShort())
        assertEquals(0xFF00u.toShort(), stream.readShort())
        assertEquals(0x7FFFu.toShort(), stream.readShort())
        assertEquals(0x007Fu.toShort(), stream.readShort())
        assertEquals(0xFF00u.toShort(), stream.readShort())
        assertEquals(0x7FFFu.toShort(), stream.readShort())
    }

    @Test
    fun testReadInt() {
        val bytes = ByteArrayInputStream(byteArrayOf(0x00u, 0x01u, 0x7Fu, 0xFFu, 0x00u, 0x7Fu, 0xFFu, 0x00u, 0x7Fu, 0xFFu,
            0x00u, 0x7Fu, 0xFFu, 0x00u, 0x7Fu, 0xFFu, 0x00u, 0x7Fu, 0xFFu))
        val stream = DataInputStream(bytes)
        assertEquals(0x00017FFFu.toInt(), stream.readInt())
        assertEquals(0x007FFF00u.toInt(), stream.readInt())
        assertEquals(0x7FFF007Fu.toInt(), stream.readInt())
        assertEquals(0xFF007FFFu.toInt(), stream.readInt())
    }

    @Test
    fun testReadLong() {
        val bytes = ByteArrayInputStream(byteArrayOf(0x00u, 0x01u, 0x7Fu, 0xFFu, 0x00u, 0x7Fu, 0xFFu, 0x00u, 0x7Fu, 0xFFu,
            0x00u, 0x7Fu, 0xFFu, 0x00u, 0x7Fu, 0xFFu, 0x00u, 0x7Fu, 0xFFu, 0x00u, 0x7Fu, 0xFFu, 0x00u, 0x7Fu))
        val stream = DataInputStream(bytes)
        assertEquals(0x00017FFF007FFF00uL.toLong(), stream.readLong())
        assertEquals(0x7FFF007FFF007FFFuL.toLong(), stream.readLong())
        assertEquals(0x007FFF007FFF007FuL.toLong(), stream.readLong())
    }

    @Test
    fun testReadFloat() {
        val bytes = ByteArrayInputStream(byteArrayOf(0x00u, 0x01u, 0x7Fu, 0xFFu, 0x00u, 0x7Fu, 0xFFu, 0x00u))
        val stream = DataInputStream(bytes)
        assertEquals(Float.fromBits(0x00017FFFu.toInt()), stream.readFloat())
        assertEquals(Float.fromBits(0x007FFF00u.toInt()), stream.readFloat())
    }

    @Test
    fun testReadDouble() {
        val bytes = ByteArrayInputStream(byteArrayOf(0x00u, 0x01u, 0x7Fu, 0xFFu, 0x00u, 0x7Fu, 0xFFu, 0x00u, 0x7Fu, 0xFFu,
            0x00u, 0x7Fu, 0xFFu, 0x00u, 0x7Fu, 0xFFu))
        val stream = DataInputStream(bytes)
        assertEquals(Double.fromBits(0x00017FFF007FFF00uL.toLong()), stream.readDouble())
        assertEquals(Double.fromBits(0x7FFF007FFF007FFFuL.toLong()), stream.readDouble())
    }

    @Test
    fun testReadUnsignedByte() {
        val bytes = ByteArrayInputStream(byteArrayOf(0x00u, 0x7Fu, 0x00u, 0xFFu))
        val stream = DataInputStream(bytes)
        assertEquals(0x00u, stream.readUnsignedByte())
        assertEquals(0x7Fu, stream.readUnsignedByte())
        assertEquals(0x00u, stream.readUnsignedByte())
        assertEquals(0xFFu, stream.readUnsignedByte())
    }

    @Test
    fun testReadUnsignedShort() {
        val bytes = ByteArrayInputStream(byteArrayOf(0x00u, 0x01u, 0x7Fu, 0xFFu, 0x00u, 0x7Fu, 0xFFu, 0x00u, 0x7Fu, 0xFFu,
            0x00u, 0x7Fu, 0xFFu, 0x00u, 0x7Fu, 0xFFu))
        val stream = DataInputStream(bytes)
        assertEquals(0x0001u, stream.readUnsignedShort())
        assertEquals(0x7FFFu, stream.readUnsignedShort())
        assertEquals(0x007Fu, stream.readUnsignedShort())
        assertEquals(0xFF00u, stream.readUnsignedShort())
        assertEquals(0x7FFFu, stream.readUnsignedShort())
        assertEquals(0x007Fu, stream.readUnsignedShort())
        assertEquals(0xFF00u, stream.readUnsignedShort())
        assertEquals(0x7FFFu, stream.readUnsignedShort())
    }

    @Test
    fun testReadUnsignedInt() {
        val bytes = ByteArrayInputStream(byteArrayOf(0x00u, 0x01u, 0x7Fu, 0xFFu, 0x00u, 0x7Fu, 0xFFu, 0x00u, 0x7Fu, 0xFFu,
            0x00u, 0x7Fu, 0xFFu, 0x00u, 0x7Fu, 0xFFu, 0x00u, 0x7Fu, 0xFFu))
        val stream = DataInputStream(bytes)
        assertEquals(0x00017FFFu, stream.readUnsignedInt())
        assertEquals(0x007FFF00u, stream.readUnsignedInt())
        assertEquals(0x7FFF007Fu, stream.readUnsignedInt())
        assertEquals(0xFF007FFFu, stream.readUnsignedInt())
    }

    @Test
    fun testReadUnsignedLong() {
        val bytes = ByteArrayInputStream(byteArrayOf(0x00u, 0x01u, 0x7Fu, 0xFFu, 0x00u, 0x7Fu, 0xFFu, 0x00u, 0x7Fu, 0xFFu,
            0x00u, 0x7Fu, 0xFFu, 0x00u, 0x7Fu, 0xFFu, 0x00u, 0x7Fu, 0xFFu, 0x00u, 0x7Fu, 0xFFu, 0x00u, 0x7Fu))
        val stream = DataInputStream(bytes)
        assertEquals(0x00017FFF007FFF00uL, stream.readUnsignedLong())
        assertEquals(0x7FFF007FFF007FFFuL, stream.readUnsignedLong())
        assertEquals(0x007FFF007FFF007FuL, stream.readUnsignedLong())
    }

    @Test
    fun testReadUTF() {
        val bytes = ByteArrayInputStream(listOf(
            listOf<Byte>(0,0,0,11),
            "hello world".toCharArray().map{it.code.toByte()},
            "hello 2".toCharArray().map{it.code.toByte()}
        ).flatten().toByteArray())
        val stream = DataInputStream(bytes)
        assertEquals("hello world", stream.readUTF())
        assertEquals("hello 2", stream.readUTF(7))

    }

}