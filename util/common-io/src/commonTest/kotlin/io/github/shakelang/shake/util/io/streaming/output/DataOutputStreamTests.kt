package io.github.shakelang.shake.util.io.streaming.output

import kotlin.test.Test
import kotlin.test.assertEquals

@OptIn(ExperimentalUnsignedTypes::class)
class DataOutputStreamTests {

    @Test
    fun testWrite() {
        val baseStream = ByteArrayOutputStream()
        val stream = DataOutputStream(baseStream)
        stream.write(0x01)
        stream.write(0x02)
        stream.write(0x03)
        assertEquals(byteArrayOf(0x01, 0x02, 0x03).toList(), baseStream.toByteArray().toList())
    }

    @Test
    fun testWriteArray() {
        val baseStream = ByteArrayOutputStream()
        val stream = DataOutputStream(baseStream)
        stream.write(byteArrayOf(0x01, 0x02, 0x03))
        assertEquals(byteArrayOf(0x01, 0x02, 0x03).toList(), baseStream.toByteArray().toList())
    }

    @Test
    fun testWriteBoolean() {
        val baseStream = ByteArrayOutputStream()
        val stream = DataOutputStream(baseStream)
        stream.writeBoolean(true)
        stream.writeBoolean(false)
        assertEquals(byteArrayOf(0x01, 0x00).toList(), baseStream.toByteArray().toList())
    }

    @Test
    fun testWriteByte() {
        val baseStream = ByteArrayOutputStream()
        val stream = DataOutputStream(baseStream)
        stream.writeByte(0x01)
        stream.writeByte(0x02)
        stream.writeByte(0x03)
        assertEquals(byteArrayOf(0x01, 0x02, 0x03).toList(), baseStream.toByteArray().toList())
    }

    @Test
    fun testWriteShort() {
        val baseStream = ByteArrayOutputStream()
        val stream = DataOutputStream(baseStream)
        stream.writeShort(0x0102)
        stream.writeShort(0x0304)
        stream.writeShort(0x0506)
        assertEquals(byteArrayOf(0x01, 0x02, 0x03, 0x04, 0x05, 0x06).toList(), baseStream.toByteArray().toList())
    }

    @Test
    fun testWriteInt() {
        val baseStream = ByteArrayOutputStream()
        val stream = DataOutputStream(baseStream)
        stream.writeInt(0x01020304)
        stream.writeInt(0x05060708)
        stream.writeInt(0x090A0B0C)
        assertEquals(
            byteArrayOf(0x01, 0x02, 0x03, 0x04, 0x05, 0x06, 0x07, 0x08, 0x09, 0x0A, 0x0B, 0x0C)
                .toList(),
            baseStream.toByteArray().toList()
        )
    }

    @Test
    fun testWriteLong() {
        val baseStream = ByteArrayOutputStream()
        val stream = DataOutputStream(baseStream)
        stream.writeLong(0x0102030405060708L)
        stream.writeLong(0x090A0B0C0D0E0F10L)
        stream.writeLong(0x1112131415161718L)
        assertEquals(
            byteArrayOf(
                0x01, 0x02, 0x03, 0x04, 0x05, 0x06, 0x07, 0x08, 0x09, 0x0A, 0x0B, 0x0C, 0x0D, 0x0E,
                0x0F, 0x10, 0x11, 0x12, 0x13, 0x14, 0x15, 0x16, 0x17, 0x18
            ).toList(),
            baseStream.toByteArray().toList()
        )
    }

    @Test
    fun testWriteFloat() {
        val baseStream = ByteArrayOutputStream()
        val stream = DataOutputStream(baseStream)
        stream.writeFloat(1.0f)
        stream.writeFloat(2.0f)
        stream.writeFloat(3.0f)
        assertEquals(
            byteArrayOf(0x3F, 0x80u.toByte(), 0x00, 0x00, 0x40, 0x00, 0x00, 0x00, 0x40, 0x40, 0x00, 0x00)
                .toList(),
            baseStream.toByteArray().toList()
        )
    }

    @Test
    fun testWriteDouble() {
        val baseStream = ByteArrayOutputStream()
        val stream = DataOutputStream(baseStream)
        stream.writeDouble(1.0)
        stream.writeDouble(2.0)
        stream.writeDouble(3.0)
        assertEquals(
            byteArrayOf(
                0x3F, 0xF0u.toByte(), 0x00, 0x00, 0x00, 0x00, 0x00, 0x00, 0x40, 0x00, 0x00, 0x00, 0x00,
                0x00, 0x00, 0x00, 0x40, 0x08, 0x00, 0x00, 0x00, 0x00, 0x00, 0x00
            ).toList(),
            baseStream.toByteArray().toList()
        )
    }

    @Test
    fun testWriteUnsignedByte() {
        val baseStream = ByteArrayOutputStream()
        val stream = DataOutputStream(baseStream)
        stream.writeUnsignedByte(0x01u)
        stream.writeUnsignedByte(0x02u)
        stream.writeUnsignedByte(0x03u)
        assertEquals(byteArrayOf(0x01, 0x02, 0x03).toList(), baseStream.toByteArray().toList())
    }

    @Test
    fun testWriteUnsignedShort() {
        val baseStream = ByteArrayOutputStream()
        val stream = DataOutputStream(baseStream)
        stream.writeUnsignedShort(0x0102u)
        stream.writeUnsignedShort(0x0304u)
        stream.writeUnsignedShort(0x0506u)
        assertEquals(byteArrayOf(0x01, 0x02, 0x03, 0x04, 0x05, 0x06).toList(), baseStream.toByteArray().toList())
    }

    @Test
    fun testWriteUnsignedInt() {
        val baseStream = ByteArrayOutputStream()
        val stream = DataOutputStream(baseStream)
        stream.writeUnsignedInt(0x01020304u)
        stream.writeUnsignedInt(0x05060708u)
        stream.writeUnsignedInt(0x090A0B0Cu)
        assertEquals(
            byteArrayOf(0x01, 0x02, 0x03, 0x04, 0x05, 0x06, 0x07, 0x08, 0x09, 0x0A, 0x0B, 0x0C)
                .toList(),
            baseStream.toByteArray().toList()
        )
    }

    @Test
    fun testWriteUnsignedLong() {
        val baseStream = ByteArrayOutputStream()
        val stream = DataOutputStream(baseStream)
        stream.writeUnsignedLong(0x0102030405060708uL)
        stream.writeUnsignedLong(0x090A0B0C0D0E0F10uL)
        stream.writeUnsignedLong(0x1112131415161718uL)
        assertEquals(
            byteArrayOf(
                0x01, 0x02, 0x03, 0x04, 0x05, 0x06, 0x07, 0x08, 0x09, 0x0A, 0x0B, 0x0C, 0x0D, 0x0E,
                0x0F, 0x10, 0x11, 0x12, 0x13, 0x14, 0x15, 0x16, 0x17, 0x18
            ).toList(),
            baseStream.toByteArray().toList()
        )
    }

    @Test
    fun testWriteChar() {
        val baseStream = ByteArrayOutputStream()
        val stream = DataOutputStream(baseStream)
        stream.writeCharUTF8('a')
        stream.writeCharUTF8('b')
        stream.writeCharUTF8('c')
        assertEquals(byteArrayOf(0x61, 0x62, 0x63).toList(), baseStream.toByteArray().toList())
    }

    @Test
    fun testWriteBooleanArray() {
        val baseStream = ByteArrayOutputStream()
        val stream = DataOutputStream(baseStream)
        stream.writeBooleanArray(booleanArrayOf(true, false, true, true))
        assertEquals(byteArrayOf(0x01, 0x00, 0x01, 0x01).toList(), baseStream.toByteArray().toList())
    }

    @Test
    fun testWriteOffsetBooleanArray() {
        val baseStream = ByteArrayOutputStream()
        val stream = DataOutputStream(baseStream)
        stream.writeBooleanArray(booleanArrayOf(true, false, true, true), 1, 2)
        assertEquals(byteArrayOf(0x00, 0x01).toList(), baseStream.toByteArray().toList())
    }

    @Test
    fun testWriteBooleanArray2() {
        val baseStream = ByteArrayOutputStream()
        val stream = DataOutputStream(baseStream)
        stream.writeBooleanArray(arrayOf(true, false, true, true))
        assertEquals(byteArrayOf(0x01, 0x00, 0x01, 0x01).toList(), baseStream.toByteArray().toList())
    }

    @Test
    fun testWriteOffsetBooleanArray2() {
        val baseStream = ByteArrayOutputStream()
        val stream = DataOutputStream(baseStream)
        stream.writeBooleanArray(arrayOf(true, false, true, true), 1, 2)
        assertEquals(byteArrayOf(0x00, 0x01).toList(), baseStream.toByteArray().toList())
    }

    @Test
    fun testWriteByteArray() {
        val baseStream = ByteArrayOutputStream()
        val stream = DataOutputStream(baseStream)
        stream.writeByteArray(
            byteArrayOf(
                0x01, 0x02, 0x03, 0x04, 0x05, 0x06, 0x07, 0x08, 0x09, 0x0A, 0x0B, 0x0C, 0x0D, 0x0E,
                0x0F, 0x10, 0x11, 0x12, 0x13, 0x14, 0x15, 0x16, 0x17, 0x18
            )
        )
        assertEquals(
            byteArrayOf(
                0x01, 0x02, 0x03, 0x04, 0x05, 0x06, 0x07, 0x08, 0x09, 0x0A, 0x0B, 0x0C, 0x0D, 0x0E,
                0x0F, 0x10, 0x11, 0x12, 0x13, 0x14, 0x15, 0x16, 0x17, 0x18
            ).toList(),
            baseStream.toByteArray().toList()
        )
    }

    @Test
    fun testWriteOffsetByteArray() {
        val baseStream = ByteArrayOutputStream()
        val stream = DataOutputStream(baseStream)
        stream.writeByteArray(
            byteArrayOf(
                0x01, 0x02, 0x03, 0x04, 0x05, 0x06, 0x07, 0x08, 0x09, 0x0A, 0x0B, 0x0C, 0x0D, 0x0E,
                0x0F, 0x10, 0x11, 0x12, 0x13, 0x14, 0x15, 0x16, 0x17, 0x18
            ),
            1,
            2
        )
        assertEquals(byteArrayOf(0x02, 0x03).toList(), baseStream.toByteArray().toList())
    }

    @Test
    fun testWriteByteArray2() {
        val baseStream = ByteArrayOutputStream()
        val stream = DataOutputStream(baseStream)
        stream.writeByteArray(
            arrayOf(
                0x01, 0x02, 0x03, 0x04, 0x05, 0x06, 0x07, 0x08, 0x09, 0x0A, 0x0B, 0x0C, 0x0D, 0x0E,
                0x0F, 0x10, 0x11, 0x12, 0x13, 0x14, 0x15, 0x16, 0x17, 0x18
            )
        )
        assertEquals(
            byteArrayOf(
                0x01, 0x02, 0x03, 0x04, 0x05, 0x06, 0x07, 0x08, 0x09, 0x0A, 0x0B, 0x0C, 0x0D, 0x0E,
                0x0F, 0x10, 0x11, 0x12, 0x13, 0x14, 0x15, 0x16, 0x17, 0x18
            ).toList(),
            baseStream.toByteArray().toList()
        )
    }

    @Test
    fun testWriteOffsetByteArray2() {
        val baseStream = ByteArrayOutputStream()
        val stream = DataOutputStream(baseStream)
        stream.writeByteArray(
            arrayOf(
                0x01, 0x02, 0x03, 0x04, 0x05, 0x06, 0x07, 0x08, 0x09, 0x0A, 0x0B, 0x0C, 0x0D, 0x0E,
                0x0F, 0x10, 0x11, 0x12, 0x13, 0x14, 0x15, 0x16, 0x17, 0x18
            ),
            1,
            2
        )
        assertEquals(byteArrayOf(0x02, 0x03).toList(), baseStream.toByteArray().toList())
    }

    @Test
    fun testWriteShortArray() {
        val baseStream = ByteArrayOutputStream()
        val stream = DataOutputStream(baseStream)
        stream.writeShortArray(shortArrayOf(0x0102, 0x0304, 0x0506, 0x0708))
        assertEquals(
            byteArrayOf(0x01, 0x02, 0x03, 0x04, 0x05, 0x06, 0x07, 0x08).toList(),
            baseStream.toByteArray().toList()
        )
    }

    @Test
    fun testWriteOffsetShortArray() {
        val baseStream = ByteArrayOutputStream()
        val stream = DataOutputStream(baseStream)
        stream.writeShortArray(shortArrayOf(0x0102, 0x0304, 0x0506, 0x0708), 1, 2)
        assertEquals(byteArrayOf(0x03, 0x04, 0x05, 0x06).toList(), baseStream.toByteArray().toList())
    }

    @Test
    fun testWriteShortArray2() {
        val baseStream = ByteArrayOutputStream()
        val stream = DataOutputStream(baseStream)
        stream.writeShortArray(arrayOf(0x0102, 0x0304, 0x0506, 0x0708))
        assertEquals(
            byteArrayOf(0x01, 0x02, 0x03, 0x04, 0x05, 0x06, 0x07, 0x08).toList(),
            baseStream.toByteArray().toList()
        )
    }

    @Test
    fun testWriteOffsetShortArray2() {
        val baseStream = ByteArrayOutputStream()
        val stream = DataOutputStream(baseStream)
        stream.writeShortArray(arrayOf(0x0102, 0x0304, 0x0506, 0x0708), 1, 2)
        assertEquals(byteArrayOf(0x03, 0x04, 0x05, 0x06).toList(), baseStream.toByteArray().toList())
    }

    @Test
    fun testWriteIntArray() {
        val baseStream = ByteArrayOutputStream()
        val stream = DataOutputStream(baseStream)
        stream.writeIntArray(intArrayOf(0x01020304, 0x05060708, 0x090A0B0C, 0x0D0E0F10))
        assertEquals(
            byteArrayOf(
                0x01, 0x02, 0x03, 0x04, 0x05, 0x06, 0x07, 0x08, 0x09, 0x0A, 0x0B, 0x0C, 0x0D, 0x0E,
                0x0F, 0x10
            ).toList(),
            baseStream.toByteArray().toList()
        )
    }

    @Test
    fun testWriteOffsetIntArray() {
        val baseStream = ByteArrayOutputStream()
        val stream = DataOutputStream(baseStream)
        stream.writeIntArray(intArrayOf(0x01020304, 0x05060708, 0x090A0B0C, 0x0D0E0F10), 1, 2)
        assertEquals(
            byteArrayOf(0x05, 0x06, 0x07, 0x08, 0x09, 0x0a, 0x0b, 0x0c).toList(),
            baseStream.toByteArray().toList()
        )
    }

    @Test
    fun testWriteIntArray2() {
        val baseStream = ByteArrayOutputStream()
        val stream = DataOutputStream(baseStream)
        stream.writeIntArray(arrayOf(0x01020304, 0x05060708, 0x090A0B0C, 0x0D0E0F10))
        assertEquals(
            byteArrayOf(
                0x01, 0x02, 0x03, 0x04, 0x05, 0x06, 0x07, 0x08, 0x09, 0x0A, 0x0B, 0x0C, 0x0D, 0x0E,
                0x0F, 0x10
            ).toList(),
            baseStream.toByteArray().toList()
        )
    }

    @Test
    fun testWriteOffsetIntArray2() {
        val baseStream = ByteArrayOutputStream()
        val stream = DataOutputStream(baseStream)
        stream.writeIntArray(arrayOf(0x01020304, 0x05060708, 0x090A0B0C, 0x0D0E0F10), 1, 2)
        assertEquals(
            byteArrayOf(0x05, 0x06, 0x07, 0x08, 0x09, 0x0a, 0x0b, 0x0c).toList(),
            baseStream.toByteArray().toList()
        )
    }

    @Test
    fun testWriteLongArray() {
        val baseStream = ByteArrayOutputStream()
        val stream = DataOutputStream(baseStream)
        stream.writeLongArray(longArrayOf(0x0102030405060708L, 0x090A0B0C0D0E0F10L, 0x1112131415161718L))
        assertEquals(
            byteArrayOf(
                0x01, 0x02, 0x03, 0x04, 0x05, 0x06, 0x07, 0x08, 0x09, 0x0A, 0x0B, 0x0C, 0x0D, 0x0E,
                0x0F, 0x10, 0x11, 0x12, 0x13, 0x14, 0x15, 0x16, 0x17, 0x18
            ).toList(),
            baseStream.toByteArray().toList()
        )
    }

    @Test
    fun testWriteOffsetLongArray() {
        val baseStream = ByteArrayOutputStream()
        val stream = DataOutputStream(baseStream)
        stream.writeLongArray(longArrayOf(0x0102030405060708L, 0x090A0B0C0D0E0F10L, 0x1112131415161718L), 1, 2)
        assertEquals(
            byteArrayOf(
                0x09, 0x0a, 0x0b, 0x0c, 0x0d, 0x0e, 0x0f, 0x10, 0x11, 0x12, 0x13, 0x14, 0x15, 0x16,
                0x17, 0x18
            ).toList(),
            baseStream.toByteArray().toList()
        )
    }

    @Test
    fun testWriteLongArray2() {
        val baseStream = ByteArrayOutputStream()
        val stream = DataOutputStream(baseStream)
        stream.writeLongArray(arrayOf(0x0102030405060708L, 0x090A0B0C0D0E0F10L, 0x1112131415161718L))
        assertEquals(
            byteArrayOf(
                0x01, 0x02, 0x03, 0x04, 0x05, 0x06, 0x07, 0x08, 0x09, 0x0A, 0x0B, 0x0C, 0x0D, 0x0E,
                0x0F, 0x10, 0x11, 0x12, 0x13, 0x14, 0x15, 0x16, 0x17, 0x18
            ).toList(),
            baseStream.toByteArray().toList()
        )
    }

    @Test
    fun testWriteOffsetLongArray2() {
        val baseStream = ByteArrayOutputStream()
        val stream = DataOutputStream(baseStream)
        stream.writeLongArray(arrayOf(0x0102030405060708L, 0x090A0B0C0D0E0F10L, 0x1112131415161718L), 1, 2)
        assertEquals(
            byteArrayOf(
                0x09, 0x0a, 0x0b, 0x0c, 0x0d, 0x0e, 0x0f, 0x10, 0x11, 0x12, 0x13, 0x14, 0x15, 0x16,
                0x17, 0x18
            ).toList(),
            baseStream.toByteArray().toList()
        )
    }

    @Test
    fun testWriteFloatArray() {
        val baseStream = ByteArrayOutputStream()
        val stream = DataOutputStream(baseStream)
        stream.writeFloatArray(floatArrayOf(1.0f, 2.0f, 3.0f))
        assertEquals(
            byteArrayOf(0x3F, 0x80u.toByte(), 0x00, 0x00, 0x40, 0x00, 0x00, 0x00, 0x40, 0x40, 0x00, 0x00)
                .toList(),
            baseStream.toByteArray().toList()
        )
    }

    @Test
    fun testWriteOffsetFloatArray() {
        val baseStream = ByteArrayOutputStream()
        val stream = DataOutputStream(baseStream)
        stream.writeFloatArray(floatArrayOf(1.0f, 2.0f, 3.0f), 1, 2)
        assertEquals(
            byteArrayOf(0x40, 0x00, 0x00, 0x00, 0x40, 0x40, 0x00, 0x00).toList(),
            baseStream.toByteArray().toList()
        )
    }

    @Test
    fun testWriteFloatArray2() {
        val baseStream = ByteArrayOutputStream()
        val stream = DataOutputStream(baseStream)
        stream.writeFloatArray(arrayOf(1.0f, 2.0f, 3.0f))
        assertEquals(
            byteArrayOf(0x3F, 0x80u.toByte(), 0x00, 0x00, 0x40, 0x00, 0x00, 0x00, 0x40, 0x40, 0x00, 0x00)
                .toList(),
            baseStream.toByteArray().toList()
        )
    }

    @Test
    fun testWriteOffsetFloatArray2() {
        val baseStream = ByteArrayOutputStream()
        val stream = DataOutputStream(baseStream)
        stream.writeFloatArray(arrayOf(1.0f, 2.0f, 3.0f), 1, 2)
        assertEquals(
            byteArrayOf(0x40, 0x00, 0x00, 0x00, 0x40, 0x40, 0x00, 0x00).toList(),
            baseStream.toByteArray().toList()
        )
    }

    @Test
    fun testWriteDoubleArray() {
        val baseStream = ByteArrayOutputStream()
        val stream = DataOutputStream(baseStream)
        stream.writeDoubleArray(doubleArrayOf(1.0, 2.0, 3.0))
        assertEquals(
            byteArrayOf(
                0x3F, 0xF0u.toByte(), 0x00, 0x00, 0x00, 0x00, 0x00, 0x00, 0x40, 0x00, 0x00, 0x00, 0x00,
                0x00, 0x00, 0x00, 0x40, 0x08, 0x00, 0x00, 0x00, 0x00, 0x00, 0x00
            ).toList(),
            baseStream.toByteArray().toList()
        )
    }

    @Test
    fun testWriteOffsetDoubleArray() {
        val baseStream = ByteArrayOutputStream()
        val stream = DataOutputStream(baseStream)
        stream.writeDoubleArray(doubleArrayOf(1.0, 2.0, 3.0), 1, 2)
        assertEquals(
            byteArrayOf(
                0x40,
                0x00,
                0x00,
                0x00,
                0x00,
                0x00,
                0x00,
                0x00,
                0x40,
                0x08,
                0x00,
                0x00,
                0x00,
                0x00,
                0x00,
                0x00
            ).toList(),
            baseStream.toByteArray().toList()
        )
    }

    @Test
    fun testWriteDoubleArray2() {
        val baseStream = ByteArrayOutputStream()
        val stream = DataOutputStream(baseStream)
        stream.writeDoubleArray(arrayOf(1.0, 2.0, 3.0))
        assertEquals(
            byteArrayOf(
                0x3F, 0xF0u.toByte(), 0x00, 0x00, 0x00, 0x00, 0x00, 0x00, 0x40, 0x00, 0x00, 0x00, 0x00,
                0x00, 0x00, 0x00, 0x40, 0x08, 0x00, 0x00, 0x00, 0x00, 0x00, 0x00
            ).toList(),
            baseStream.toByteArray().toList()
        )
    }

    @Test
    fun testWriteOffsetDoubleArray2() {
        val baseStream = ByteArrayOutputStream()
        val stream = DataOutputStream(baseStream)
        stream.writeDoubleArray(arrayOf(1.0, 2.0, 3.0), 1, 2)
        assertEquals(
            byteArrayOf(
                0x40,
                0x00,
                0x00,
                0x00,
                0x00,
                0x00,
                0x00,
                0x00,
                0x40,
                0x08,
                0x00,
                0x00,
                0x00,
                0x00,
                0x00,
                0x00
            ).toList(),
            baseStream.toByteArray().toList()
        )
    }

    @Test
    fun writeUnsignedByteArray() {
        val baseStream = ByteArrayOutputStream()
        val stream = DataOutputStream(baseStream)
        stream.writeUnsignedByteArray(ubyteArrayOf(0x01u, 0x02u, 0x03u).toUByteArray())
        assertEquals(byteArrayOf(0x01, 0x02, 0x03).toList(), baseStream.toByteArray().toList())
    }

    @Test
    fun writeOffsetUnsignedByteArray() {
        val baseStream = ByteArrayOutputStream()
        val stream = DataOutputStream(baseStream)
        stream.writeUnsignedByteArray(ubyteArrayOf(0x01u, 0x02u, 0x03u, 0x04u).toUByteArray(), 1, 2)
        assertEquals(byteArrayOf(0x02, 0x03).toList(), baseStream.toByteArray().toList())
    }

    @Test
    fun writeUnsignedByteArray2() {
        val baseStream = ByteArrayOutputStream()
        val stream = DataOutputStream(baseStream)
        stream.writeUnsignedByteArray(arrayOf(0x01u, 0x02u, 0x03u))
        assertEquals(byteArrayOf(0x01, 0x02, 0x03).toList(), baseStream.toByteArray().toList())
    }

    @Test
    fun writeOffsetUnsignedByteArray2() {
        val baseStream = ByteArrayOutputStream()
        val stream = DataOutputStream(baseStream)
        stream.writeUnsignedByteArray(arrayOf(0x01u, 0x02u, 0x03u, 0x04u), 1, 2)
        assertEquals(byteArrayOf(0x02, 0x03).toList(), baseStream.toByteArray().toList())
    }

    @Test
    fun writeUnsignedShortArray() {
        val baseStream = ByteArrayOutputStream()
        val stream = DataOutputStream(baseStream)
        stream.writeUnsignedShortArray(ushortArrayOf(0x01u, 0x02u, 0x03u).toUShortArray())
        assertEquals(byteArrayOf(0x00, 0x01, 0x00, 0x02, 0x00, 0x03).toList(), baseStream.toByteArray().toList())
    }

    @Test
    fun writeOffsetUnsignedShortArray() {
        val baseStream = ByteArrayOutputStream()
        val stream = DataOutputStream(baseStream)
        stream.writeUnsignedShortArray(ushortArrayOf(0x01u, 0x02u, 0x03u, 0x04u).toUShortArray(), 1, 2)
        assertEquals(byteArrayOf(0x00, 0x02, 0x00, 0x03).toList(), baseStream.toByteArray().toList())
    }

    @Test
    fun writeUnsignedShortArray2() {
        val baseStream = ByteArrayOutputStream()
        val stream = DataOutputStream(baseStream)
        stream.writeUnsignedShortArray(arrayOf(0x01u, 0x02u, 0x03u))
        assertEquals(byteArrayOf(0x00, 0x01, 0x00, 0x02, 0x00, 0x03).toList(), baseStream.toByteArray().toList())
    }

    @Test
    fun writeOffsetUnsignedShortArray2() {
        val baseStream = ByteArrayOutputStream()
        val stream = DataOutputStream(baseStream)
        stream.writeUnsignedShortArray(arrayOf(0x01u, 0x02u, 0x03u, 0x04u), 1, 2)
        assertEquals(byteArrayOf(0x00, 0x02, 0x00, 0x03).toList(), baseStream.toByteArray().toList())
    }

    @Test
    fun writeUnsignedIntArray() {
        val baseStream = ByteArrayOutputStream()
        val stream = DataOutputStream(baseStream)
        stream.writeUnsignedIntArray(uintArrayOf(0x01u, 0x02u, 0x03u).toUIntArray())
        assertEquals(
            byteArrayOf(0x00, 0x00, 0x00, 0x01, 0x00, 0x00, 0x00, 0x02, 0x00, 0x00, 0x00, 0x03).toList(),
            baseStream.toByteArray().toList()
        )
    }

    @Test
    fun writeOffsetUnsignedIntArray() {
        val baseStream = ByteArrayOutputStream()
        val stream = DataOutputStream(baseStream)
        stream.writeUnsignedIntArray(uintArrayOf(0x01u, 0x02u, 0x03u, 0x04u).toUIntArray(), 1, 2)
        assertEquals(
            byteArrayOf(0x00, 0x00, 0x00, 0x02, 0x00, 0x00, 0x00, 0x03).toList(),
            baseStream.toByteArray().toList()
        )
    }

    @Test
    fun writeUnsignedIntArray2() {
        val baseStream = ByteArrayOutputStream()
        val stream = DataOutputStream(baseStream)
        stream.writeUnsignedIntArray(arrayOf(0x01u, 0x02u, 0x03u))
        assertEquals(
            byteArrayOf(0x00, 0x00, 0x00, 0x01, 0x00, 0x00, 0x00, 0x02, 0x00, 0x00, 0x00, 0x03).toList(),
            baseStream.toByteArray().toList()
        )
    }

    @Test
    fun writeOffsetUnsignedIntArray2() {
        val baseStream = ByteArrayOutputStream()
        val stream = DataOutputStream(baseStream)
        stream.writeUnsignedIntArray(arrayOf(0x01u, 0x02u, 0x03u, 0x04u), 1, 2)
        assertEquals(
            byteArrayOf(0x00, 0x00, 0x00, 0x02, 0x00, 0x00, 0x00, 0x03).toList(),
            baseStream.toByteArray().toList()
        )
    }

    @Test
    fun writeUnsignedLongArray() {
        val baseStream = ByteArrayOutputStream()
        val stream = DataOutputStream(baseStream)
        stream.writeUnsignedLongArray(ulongArrayOf(0x01u, 0x02u, 0x03u).toULongArray())
        assertEquals(
            byteArrayOf(
                0x00, 0x00, 0x00, 0x00, 0x00, 0x00, 0x00, 0x01, 0x00, 0x00, 0x00, 0x00, 0x00, 0x00,
                0x00, 0x02, 0x00, 0x00, 0x00, 0x00, 0x00, 0x00, 0x00, 0x03
            ).toList(),
            baseStream.toByteArray().toList()
        )
    }

    @Test
    fun writeOffsetUnsignedLongArray() {
        val baseStream = ByteArrayOutputStream()
        val stream = DataOutputStream(baseStream)
        stream.writeUnsignedLongArray(ulongArrayOf(0x01u, 0x02u, 0x03u, 0x04u).toULongArray(), 1, 2)
        assertEquals(
            byteArrayOf(
                0x00, 0x00, 0x00, 0x00, 0x00, 0x00, 0x00, 0x02, 0x00, 0x00, 0x00, 0x00, 0x00, 0x00,
                0x00, 0x03
            ).toList(),
            baseStream.toByteArray().toList()
        )
    }

    @Test
    fun writeUnsignedLongArray2() {
        val baseStream = ByteArrayOutputStream()
        val stream = DataOutputStream(baseStream)
        stream.writeUnsignedLongArray(arrayOf(0x01u, 0x02u, 0x03u))
        assertEquals(
            byteArrayOf(
                0x00, 0x00, 0x00, 0x00, 0x00, 0x00, 0x00, 0x01, 0x00, 0x00, 0x00, 0x00, 0x00, 0x00,
                0x00, 0x02, 0x00, 0x00, 0x00, 0x00, 0x00, 0x00, 0x00, 0x03
            ).toList(),
            baseStream.toByteArray().toList()
        )
    }

    @Test
    fun writeOffsetUnsignedLongArray2() {
        val baseStream = ByteArrayOutputStream()
        val stream = DataOutputStream(baseStream)
        stream.writeUnsignedLongArray(arrayOf(0x01u, 0x02u, 0x03u, 0x04u), 1, 2)
        assertEquals(
            byteArrayOf(
                0x00, 0x00, 0x00, 0x00, 0x00, 0x00, 0x00, 0x02, 0x00, 0x00, 0x00, 0x00, 0x00, 0x00,
                0x00, 0x03
            ).toList(),
            baseStream.toByteArray().toList()
        )
    }

    @Test
    fun testWriteCharArrayUTF8() {
        val baseStream = ByteArrayOutputStream()
        val stream = DataOutputStream(baseStream)
        stream.writeCharArrayUTF8(arrayOf('a', 'b', 'c'))
        assertEquals(byteArrayOf(0x61, 0x62, 0x63).toList(), baseStream.toByteArray().toList())
    }

    @Test
    fun testWriteOffsetCharArrayUTF8() {
        val baseStream = ByteArrayOutputStream()
        val stream = DataOutputStream(baseStream)
        stream.writeCharArrayUTF8(arrayOf('a', 'b', 'c', 'd'), 1, 2)
        assertEquals(byteArrayOf(0x62, 0x63).toList(), baseStream.toByteArray().toList())
    }

    @Test
    fun testWriteCharArrayUTF82() {
        val baseStream = ByteArrayOutputStream()
        val stream = DataOutputStream(baseStream)
        stream.writeCharArrayUTF8(arrayOf('a', 'b', 'c').toCharArray())
        assertEquals(byteArrayOf(0x61, 0x62, 0x63).toList(), baseStream.toByteArray().toList())
    }

    @Test
    fun testWriteOffsetCharArrayUTF82() {
        val baseStream = ByteArrayOutputStream()
        val stream = DataOutputStream(baseStream)
        stream.writeCharArrayUTF8(arrayOf('a', 'b', 'c', 'd').toCharArray(), 1, 2)
        assertEquals(byteArrayOf(0x62, 0x63).toList(), baseStream.toByteArray().toList())
    }

    @Test
    fun testWriteCharArray() {
        val baseStream = ByteArrayOutputStream()
        val stream = DataOutputStream(baseStream)
        stream.writeCharArray(arrayOf('a', 'b', 'c'))
        assertEquals(byteArrayOf(0x00, 0x61, 0x00, 0x62, 0x00, 0x63).toList(), baseStream.toByteArray().toList())
    }

    @Test
    fun testWriteOffsetCharArray() {
        val baseStream = ByteArrayOutputStream()
        val stream = DataOutputStream(baseStream)
        stream.writeCharArray(arrayOf('a', 'b', 'c', 'd'), 1, 2)
        assertEquals(byteArrayOf(0x00, 0x62, 0x00, 0x63).toList(), baseStream.toByteArray().toList())
    }

    @Test
    fun testWriteCharArray2() {
        val baseStream = ByteArrayOutputStream()
        val stream = DataOutputStream(baseStream)
        stream.writeCharArray(arrayOf('a', 'b', 'c').toCharArray())
        assertEquals(byteArrayOf(0x00, 0x61, 0x00, 0x62, 0x00, 0x63).toList(), baseStream.toByteArray().toList())
    }

    @Test
    fun testWriteOffsetCharArray2() {
        val baseStream = ByteArrayOutputStream()
        val stream = DataOutputStream(baseStream)
        stream.writeCharArray(arrayOf('a', 'b', 'c', 'd').toCharArray(), 1, 2)
        assertEquals(byteArrayOf(0x00, 0x62, 0x00, 0x63).toList(), baseStream.toByteArray().toList())
    }

    @Test
    fun testWriteUTF8() {
        val baseStream = ByteArrayOutputStream()
        val stream = DataOutputStream(baseStream)
        stream.writeUTF8("abc")
        assertEquals(byteArrayOf(0x00, 0x03, 0x61, 0x62, 0x63).toList(), baseStream.toByteArray().toList())
    }

    @Test
    fun testWriteOffsetUTF8() {
        val baseStream = ByteArrayOutputStream()
        val stream = DataOutputStream(baseStream)
        stream.writeUTF8("abcd", 1, 2)
        assertEquals(byteArrayOf(0x00, 0x02, 0x62, 0x63).toList(), baseStream.toByteArray().toList())
    }

    @Test
    fun testWriteStringUTF8() {
        val baseStream = ByteArrayOutputStream()
        val stream = DataOutputStream(baseStream)
        stream.writeStringUTF8("abc")
        assertEquals(byteArrayOf(0x00, 0x03, 0x61, 0x62, 0x63).toList(), baseStream.toByteArray().toList())
    }

    @Test
    fun testWriteOffsetStringUTF8() {
        val baseStream = ByteArrayOutputStream()
        val stream = DataOutputStream(baseStream)
        stream.writeStringUTF8("abcd", 1, 2)
        assertEquals(byteArrayOf(0x00, 0x02, 0x62, 0x63).toList(), baseStream.toByteArray().toList())
    }

    @Test
    fun testFlush() {
        val baseStream = ByteArrayOutputStream()
        val stream = DataOutputStream(baseStream)
        stream.writeBoolean(true)
        stream.flush()
        assertEquals(byteArrayOf(0x01).toList(), baseStream.toByteArray().toList())
    }

    @Test
    fun testClose() {
        val baseStream = ByteArrayOutputStream()
        val stream = DataOutputStream(baseStream)
        stream.writeBoolean(true)
        stream.close()
        assertEquals(byteArrayOf(0x01).toList(), baseStream.toByteArray().toList())
    }
}
