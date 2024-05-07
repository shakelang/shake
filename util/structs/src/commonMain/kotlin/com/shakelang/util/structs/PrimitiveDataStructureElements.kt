package com.shakelang.util.structs

import com.shakelang.util.io.streaming.output.bytes.OutputStream
import com.shakelang.util.primitives.bytes.*

object PrimitiveDataStructureElements {
    object Int8 : DataStructureNumberElement<kotlin.Byte> {
        override val byteLength: Int = 1
        override fun parse(bytes: ByteArray): kotlin.Byte = bytes.toByte()
        override fun parse(bytes: ByteArray, offset: Int): kotlin.Byte = bytes[offset]
        override fun toBytes(element: kotlin.Byte): ByteArray = byteArrayOf(element)

        override fun dump(element: kotlin.Byte, outputStream: OutputStream) =
            outputStream.write(toBytes(element))

        override fun convertToBytes(element: kotlin.Byte): ByteArray = toBytes(element)
        override fun convertToBytes(element: Short): ByteArray = toBytes(element.toByte())
        override fun convertToBytes(element: Int): ByteArray = toBytes(element.toByte())
        override fun convertToBytes(element: Long): ByteArray = toBytes(element.toByte())
        override fun convertToBytes(element: Float): ByteArray = toBytes(element.toInt().toByte())
        override fun convertToBytes(element: Double): ByteArray = toBytes(element.toInt().toByte())
        override fun convertToBytes(element: kotlin.UByte): ByteArray = toBytes(element.toByte())
        override fun convertToBytes(element: UShort): ByteArray = toBytes(element.toByte())
        override fun convertToBytes(element: UInt): ByteArray = toBytes(element.toByte())
        override fun convertToBytes(element: ULong): ByteArray = toBytes(element.toByte())

        override fun dumpBytes(element: kotlin.Byte, outputStream: OutputStream) =
            outputStream.write(convertToBytes(element))

        override fun dumpBytes(element: Short, outputStream: OutputStream) =
            outputStream.write(convertToBytes(element.toByte()))

        override fun dumpBytes(element: Int, outputStream: OutputStream) =
            outputStream.write(convertToBytes(element.toByte()))

        override fun dumpBytes(element: Long, outputStream: OutputStream) =
            outputStream.write(convertToBytes(element.toByte()))

        override fun dumpBytes(element: Float, outputStream: OutputStream) =
            outputStream.write(convertToBytes(element.toInt().toByte()))

        override fun dumpBytes(element: Double, outputStream: OutputStream) =
            outputStream.write(convertToBytes(element.toInt().toByte()))

        override fun dumpBytes(element: kotlin.UByte, outputStream: OutputStream) =
            outputStream.write(convertToBytes(element.toByte()))

        override fun dumpBytes(element: UShort, outputStream: OutputStream) =
            outputStream.write(convertToBytes(element.toByte()))

        override fun dumpBytes(element: UInt, outputStream: OutputStream) =
            outputStream.write(convertToBytes(element.toByte()))

        override fun dumpBytes(element: ULong, outputStream: OutputStream) =
            outputStream.write(convertToBytes(element.toByte()))
    }

    object Int16LE : DataStructureNumberElement<Short> {
        override val byteLength: Int = 2
        override fun parse(bytes: ByteArray): Short = bytes.toShortLE()
        override fun parse(bytes: ByteArray, offset: Int): Short = bytes.getShortLE(offset)
        override fun toBytes(element: Short): ByteArray = element.toBytesLE()

        override fun convertToBytes(element: kotlin.Byte): ByteArray = toBytes(element.toShort())
        override fun convertToBytes(element: Short): ByteArray = toBytes(element)
        override fun convertToBytes(element: Int): ByteArray = toBytes(element.toShort())
        override fun convertToBytes(element: Long): ByteArray = toBytes(element.toShort())
        override fun convertToBytes(element: Float): ByteArray = toBytes(element.toInt().toShort())
        override fun convertToBytes(element: Double): ByteArray = toBytes(element.toInt().toShort())
        override fun convertToBytes(element: kotlin.UByte): ByteArray = toBytes(element.toShort())
        override fun convertToBytes(element: UShort): ByteArray = toBytes(element.toShort())
        override fun convertToBytes(element: UInt): ByteArray = toBytes(element.toShort())
        override fun convertToBytes(element: ULong): ByteArray = toBytes(element.toShort())

        override fun dumpBytes(element: kotlin.Byte, outputStream: OutputStream) =
            outputStream.write(convertToBytes(element.toShort()))

        override fun dumpBytes(element: Short, outputStream: OutputStream) =
            outputStream.write(convertToBytes(element))

        override fun dumpBytes(element: Int, outputStream: OutputStream) =
            outputStream.write(convertToBytes(element.toShort()))

        override fun dumpBytes(element: Long, outputStream: OutputStream) =
            outputStream.write(convertToBytes(element.toShort()))

        override fun dumpBytes(element: Float, outputStream: OutputStream) =
            outputStream.write(convertToBytes(element.toInt().toShort()))

        override fun dumpBytes(element: Double, outputStream: OutputStream) =
            outputStream.write(convertToBytes(element.toInt().toShort()))

        override fun dumpBytes(element: kotlin.UByte, outputStream: OutputStream) =
            outputStream.write(convertToBytes(element.toShort()))

        override fun dumpBytes(element: UShort, outputStream: OutputStream) =
            outputStream.write(convertToBytes(element.toShort()))

        override fun dumpBytes(element: UInt, outputStream: OutputStream) =
            outputStream.write(convertToBytes(element.toShort()))

        override fun dumpBytes(element: ULong, outputStream: OutputStream) =
            outputStream.write(convertToBytes(element.toShort()))
    }

    object Int16BE : DataStructureNumberElement<Short> {
        override val byteLength: Int = 2
        override fun parse(bytes: ByteArray): Short = bytes.toShortBE()
        override fun parse(bytes: ByteArray, offset: Int): Short = bytes.getShortBE(offset)
        override fun toBytes(element: Short): ByteArray = element.toBytesBE()

        override fun convertToBytes(element: kotlin.Byte): ByteArray = toBytes(element.toShort())
        override fun convertToBytes(element: Short): ByteArray = toBytes(element)
        override fun convertToBytes(element: Int): ByteArray = toBytes(element.toShort())
        override fun convertToBytes(element: Long): ByteArray = toBytes(element.toShort())
        override fun convertToBytes(element: Float): ByteArray = toBytes(element.toInt().toShort())
        override fun convertToBytes(element: Double): ByteArray = toBytes(element.toInt().toShort())
        override fun convertToBytes(element: kotlin.UByte): ByteArray = toBytes(element.toShort())
        override fun convertToBytes(element: UShort): ByteArray = toBytes(element.toShort())
        override fun convertToBytes(element: UInt): ByteArray = toBytes(element.toShort())
        override fun convertToBytes(element: ULong): ByteArray = toBytes(element.toShort())

        override fun dumpBytes(element: kotlin.Byte, outputStream: OutputStream) =
            outputStream.write(convertToBytes(element.toShort()))

        override fun dumpBytes(element: Short, outputStream: OutputStream) =
            outputStream.write(convertToBytes(element))

        override fun dumpBytes(element: Int, outputStream: OutputStream) =
            outputStream.write(convertToBytes(element.toShort()))

        override fun dumpBytes(element: Long, outputStream: OutputStream) =
            outputStream.write(convertToBytes(element.toShort()))

        override fun dumpBytes(element: Float, outputStream: OutputStream) =
            outputStream.write(convertToBytes(element.toInt().toShort()))

        override fun dumpBytes(element: Double, outputStream: OutputStream) =
            outputStream.write(convertToBytes(element.toInt().toShort()))

        override fun dumpBytes(element: kotlin.UByte, outputStream: OutputStream) =
            outputStream.write(convertToBytes(element.toShort()))

        override fun dumpBytes(element: UShort, outputStream: OutputStream) =
            outputStream.write(convertToBytes(element.toShort()))

        override fun dumpBytes(element: UInt, outputStream: OutputStream) =
            outputStream.write(convertToBytes(element.toShort()))

        override fun dumpBytes(element: ULong, outputStream: OutputStream) =
            outputStream.write(convertToBytes(element.toShort()))
    }

    object Int32LE : DataStructureNumberElement<Int> {
        override val byteLength: Int = 4
        override fun parse(bytes: ByteArray): Int = bytes.toIntLE()
        override fun parse(bytes: ByteArray, offset: Int): Int = bytes.getIntLE(offset)
        override fun toBytes(element: Int): ByteArray = element.toBytesLE()

        override fun convertToBytes(element: kotlin.Byte): ByteArray = toBytes(element.toInt())
        override fun convertToBytes(element: Short): ByteArray = toBytes(element.toInt())
        override fun convertToBytes(element: Int): ByteArray = toBytes(element)
        override fun convertToBytes(element: Long): ByteArray = toBytes(element.toInt())
        override fun convertToBytes(element: Float): ByteArray = toBytes(element.toInt())
        override fun convertToBytes(element: Double): ByteArray = toBytes(element.toInt())
        override fun convertToBytes(element: kotlin.UByte): ByteArray = toBytes(element.toInt())
        override fun convertToBytes(element: UShort): ByteArray = toBytes(element.toInt())
        override fun convertToBytes(element: UInt): ByteArray = toBytes(element.toInt())
        override fun convertToBytes(element: ULong): ByteArray = toBytes(element.toInt())

        override fun dumpBytes(element: kotlin.Byte, outputStream: OutputStream) =
            outputStream.write(convertToBytes(element.toInt()))

        override fun dumpBytes(element: Short, outputStream: OutputStream) =
            outputStream.write(convertToBytes(element.toInt()))

        override fun dumpBytes(element: Int, outputStream: OutputStream) =
            outputStream.write(convertToBytes(element))

        override fun dumpBytes(element: Long, outputStream: OutputStream) =
            outputStream.write(convertToBytes(element.toInt()))

        override fun dumpBytes(element: Float, outputStream: OutputStream) =
            outputStream.write(convertToBytes(element.toInt()))

        override fun dumpBytes(element: Double, outputStream: OutputStream) =
            outputStream.write(convertToBytes(element.toInt()))

        override fun dumpBytes(element: kotlin.UByte, outputStream: OutputStream) =
            outputStream.write(convertToBytes(element.toInt()))

        override fun dumpBytes(element: UShort, outputStream: OutputStream) =
            outputStream.write(convertToBytes(element.toInt()))

        override fun dumpBytes(element: UInt, outputStream: OutputStream) =
            outputStream.write(convertToBytes(element.toInt()))

        override fun dumpBytes(element: ULong, outputStream: OutputStream) =
            outputStream.write(convertToBytes(element.toInt()))
    }

    object Int32BE : DataStructureNumberElement<Int> {
        override val byteLength: Int = 4
        override fun parse(bytes: ByteArray): Int = bytes.toIntBE()
        override fun parse(bytes: ByteArray, offset: Int): Int = bytes.getIntBE(offset)
        override fun toBytes(element: Int): ByteArray = element.toBytesBE()

        override fun convertToBytes(element: kotlin.Byte): ByteArray = toBytes(element.toInt())
        override fun convertToBytes(element: Short): ByteArray = toBytes(element.toInt())
        override fun convertToBytes(element: Int): ByteArray = toBytes(element)
        override fun convertToBytes(element: Long): ByteArray = toBytes(element.toInt())
        override fun convertToBytes(element: Float): ByteArray = toBytes(element.toInt())
        override fun convertToBytes(element: Double): ByteArray = toBytes(element.toInt())
        override fun convertToBytes(element: kotlin.UByte): ByteArray = toBytes(element.toInt())
        override fun convertToBytes(element: UShort): ByteArray = toBytes(element.toInt())
        override fun convertToBytes(element: UInt): ByteArray = toBytes(element.toInt())
        override fun convertToBytes(element: ULong): ByteArray = toBytes(element.toInt())

        override fun dumpBytes(element: kotlin.Byte, outputStream: OutputStream) =
            outputStream.write(convertToBytes(element.toInt()))

        override fun dumpBytes(element: Short, outputStream: OutputStream) =
            outputStream.write(convertToBytes(element.toInt()))

        override fun dumpBytes(element: Int, outputStream: OutputStream) =
            outputStream.write(convertToBytes(element))

        override fun dumpBytes(element: Long, outputStream: OutputStream) =
            outputStream.write(convertToBytes(element.toInt()))

        override fun dumpBytes(element: Float, outputStream: OutputStream) =
            outputStream.write(convertToBytes(element.toInt()))

        override fun dumpBytes(element: Double, outputStream: OutputStream) =
            outputStream.write(convertToBytes(element.toInt()))

        override fun dumpBytes(element: kotlin.UByte, outputStream: OutputStream) =
            outputStream.write(convertToBytes(element.toInt()))

        override fun dumpBytes(element: UShort, outputStream: OutputStream) =
            outputStream.write(convertToBytes(element.toInt()))

        override fun dumpBytes(element: UInt, outputStream: OutputStream) =
            outputStream.write(convertToBytes(element.toInt()))

        override fun dumpBytes(element: ULong, outputStream: OutputStream) =
            outputStream.write(convertToBytes(element.toInt()))
    }

    object Int64LE : DataStructureNumberElement<Long> {
        override val byteLength: Int = 8
        override fun parse(bytes: ByteArray): Long = bytes.toLongLE()
        override fun parse(bytes: ByteArray, offset: Int): Long = bytes.getLongLE(offset)
        override fun toBytes(element: Long): ByteArray = element.toBytesLE()

        override fun convertToBytes(element: kotlin.Byte): ByteArray = toBytes(element.toLong())
        override fun convertToBytes(element: Short): ByteArray = toBytes(element.toLong())
        override fun convertToBytes(element: Int): ByteArray = toBytes(element.toLong())
        override fun convertToBytes(element: Long): ByteArray = toBytes(element)
        override fun convertToBytes(element: Float): ByteArray = toBytes(element.toLong())
        override fun convertToBytes(element: Double): ByteArray = toBytes(element.toLong())
        override fun convertToBytes(element: kotlin.UByte): ByteArray = toBytes(element.toLong())
        override fun convertToBytes(element: UShort): ByteArray = toBytes(element.toLong())
        override fun convertToBytes(element: UInt): ByteArray = toBytes(element.toLong())
        override fun convertToBytes(element: ULong): ByteArray = toBytes(element.toLong())

        override fun dumpBytes(element: kotlin.Byte, outputStream: OutputStream) =
            outputStream.write(convertToBytes(element.toLong()))

        override fun dumpBytes(element: Short, outputStream: OutputStream) =
            outputStream.write(convertToBytes(element.toLong()))

        override fun dumpBytes(element: Int, outputStream: OutputStream) =
            outputStream.write(convertToBytes(element.toLong()))

        override fun dumpBytes(element: Long, outputStream: OutputStream) =
            outputStream.write(convertToBytes(element))

        override fun dumpBytes(element: Float, outputStream: OutputStream) =
            outputStream.write(convertToBytes(element.toLong()))

        override fun dumpBytes(element: Double, outputStream: OutputStream) =
            outputStream.write(convertToBytes(element.toLong()))

        override fun dumpBytes(element: kotlin.UByte, outputStream: OutputStream) =
            outputStream.write(convertToBytes(element.toLong()))

        override fun dumpBytes(element: UShort, outputStream: OutputStream) =
            outputStream.write(convertToBytes(element.toLong()))

        override fun dumpBytes(element: UInt, outputStream: OutputStream) =
            outputStream.write(convertToBytes(element.toLong()))

        override fun dumpBytes(element: ULong, outputStream: OutputStream) =
            outputStream.write(convertToBytes(element.toLong()))
    }

    object Int64BE : DataStructureNumberElement<Long> {
        override val byteLength: Int = 8
        override fun parse(bytes: ByteArray): Long = bytes.toLongBE()
        override fun parse(bytes: ByteArray, offset: Int): Long = bytes.getLongBE(offset)
        override fun toBytes(element: Long): ByteArray = element.toBytesBE()

        override fun convertToBytes(element: kotlin.Byte): ByteArray = toBytes(element.toLong())
        override fun convertToBytes(element: Short): ByteArray = toBytes(element.toLong())
        override fun convertToBytes(element: Int): ByteArray = toBytes(element.toLong())
        override fun convertToBytes(element: Long): ByteArray = toBytes(element)
        override fun convertToBytes(element: Float): ByteArray = toBytes(element.toLong())
        override fun convertToBytes(element: Double): ByteArray = toBytes(element.toLong())
        override fun convertToBytes(element: kotlin.UByte): ByteArray = toBytes(element.toLong())
        override fun convertToBytes(element: UShort): ByteArray = toBytes(element.toLong())
        override fun convertToBytes(element: UInt): ByteArray = toBytes(element.toLong())
        override fun convertToBytes(element: ULong): ByteArray = toBytes(element.toLong())

        override fun dumpBytes(element: kotlin.Byte, outputStream: OutputStream) =
            outputStream.write(convertToBytes(element.toLong()))

        override fun dumpBytes(element: Short, outputStream: OutputStream) =
            outputStream.write(convertToBytes(element.toLong()))

        override fun dumpBytes(element: Int, outputStream: OutputStream) =
            outputStream.write(convertToBytes(element.toLong()))

        override fun dumpBytes(element: Long, outputStream: OutputStream) =
            outputStream.write(convertToBytes(element))

        override fun dumpBytes(element: Float, outputStream: OutputStream) =
            outputStream.write(convertToBytes(element.toLong()))

        override fun dumpBytes(element: Double, outputStream: OutputStream) =
            outputStream.write(convertToBytes(element.toLong()))

        override fun dumpBytes(element: kotlin.UByte, outputStream: OutputStream) =
            outputStream.write(convertToBytes(element.toLong()))

        override fun dumpBytes(element: UShort, outputStream: OutputStream) =
            outputStream.write(convertToBytes(element.toLong()))

        override fun dumpBytes(element: UInt, outputStream: OutputStream) =
            outputStream.write(convertToBytes(element.toLong()))

        override fun dumpBytes(element: ULong, outputStream: OutputStream) =
            outputStream.write(convertToBytes(element.toLong()))
    }

    val Byte = Int8
    val ShortBE = Int16BE
    val ShortLE = Int16LE
    val Short = Int16BE
    val IntBE = Int32BE
    val IntLE = Int32LE
    val Int = Int32BE
    val LongBE = Int64BE
    val LongLE = Int64LE
    val Long = Int64BE

    object UInt8 : DataStructureElement<kotlin.UByte> {
        override val byteLength: Int = 1
        override fun parse(bytes: ByteArray): kotlin.UByte = bytes[0].toUByte()
        override fun parse(bytes: ByteArray, offset: Int): kotlin.UByte = bytes[offset].toUByte()
        override fun toBytes(element: kotlin.UByte): ByteArray = byteArrayOf(element.toByte())
    }

    object UInt16LE : DataStructureElement<UShort> {
        override val byteLength: Int = 2
        override fun parse(bytes: ByteArray): kotlin.UShort = bytes.toUnsignedShortLE()
        override fun parse(bytes: ByteArray, offset: Int): kotlin.UShort = bytes.getUShortLE(offset)
        override fun toBytes(element: kotlin.UShort): ByteArray = element.toBytesLE()
    }

    object UInt16BE : DataStructureElement<UShort> {
        override val byteLength: Int = 2
        override fun parse(bytes: ByteArray): kotlin.UShort = bytes.toUShortBE()
        override fun parse(bytes: ByteArray, offset: Int): kotlin.UShort = bytes.getUShortBE(offset)
        override fun toBytes(element: kotlin.UShort): ByteArray = element.toBytesBE()
    }

    object UInt32LE : DataStructureElement<UInt> {
        override val byteLength: Int = 4
        override fun parse(bytes: ByteArray): kotlin.UInt = bytes.toUIntLE()
        override fun parse(bytes: ByteArray, offset: Int): kotlin.UInt = bytes.getUIntLE(offset)
        override fun toBytes(element: kotlin.UInt): ByteArray = element.toBytesLE()
    }

    object UInt32BE : DataStructureElement<UInt> {
        override val byteLength: Int = 4
        override fun parse(bytes: ByteArray): kotlin.UInt = bytes.toUIntBE()
        override fun parse(bytes: ByteArray, offset: Int): kotlin.UInt = bytes.getUIntBE(offset)
        override fun toBytes(element: kotlin.UInt): ByteArray = element.toBytesBE()
    }

    object UInt64LE : DataStructureElement<ULong> {
        override val byteLength: Int = 8
        override fun parse(bytes: ByteArray): kotlin.ULong = bytes.toULongLE()
        override fun parse(bytes: ByteArray, offset: Int): kotlin.ULong = bytes.getULongLE(offset)
        override fun toBytes(element: kotlin.ULong): ByteArray = element.toBytesLE()
    }

    object UInt64BE : DataStructureElement<ULong> {
        override val byteLength: Int = 8
        override fun parse(bytes: ByteArray): kotlin.ULong = bytes.toULongBE()
        override fun parse(bytes: ByteArray, offset: Int): kotlin.ULong = bytes.getULongBE(offset)
        override fun toBytes(element: kotlin.ULong): ByteArray = element.toBytesBE()
    }

    val UByte = UInt8
    val UShortBE = UInt16BE
    val UShortLE = UInt16LE
    val UShort = UInt16BE
    val UIntBE = UInt32BE
    val UIntLE = UInt32LE
    val UInt = UInt32BE
    val ULongBE = UInt64BE
    val ULongLE = UInt64LE
    val ULong = UInt64BE

    object Float32LE : DataStructureElement<Float> {
        override val byteLength: Int = 4
        override fun parse(bytes: ByteArray): Float = bytes.toFloatLE()
        override fun parse(bytes: ByteArray, offset: Int): Float = bytes.getFloatLE(offset)
        override fun toBytes(element: Float): ByteArray = element.toBytesLE()
    }

    object Float32BE : DataStructureElement<Float> {
        override val byteLength: Int = 4
        override fun parse(bytes: ByteArray): Float = bytes.toFloatBE()
        override fun parse(bytes: ByteArray, offset: Int): Float = bytes.getFloatBE(offset)
        override fun toBytes(element: Float): ByteArray = element.toBytesBE()
    }

    object Float64LE : DataStructureElement<Double> {
        override val byteLength: Int = 8
        override fun parse(bytes: ByteArray): Double = bytes.toDoubleLE()
        override fun parse(bytes: ByteArray, offset: Int): Double = bytes.getDoubleLE(offset)
        override fun toBytes(element: Double): ByteArray = element.toBytesLE()
    }

    object Float64BE : DataStructureElement<Double> {
        override val byteLength: Int = 8
        override fun parse(bytes: ByteArray): Double = bytes.toDoubleBE()
        override fun parse(bytes: ByteArray, offset: Int): Double = bytes.getDoubleBE(offset)
        override fun toBytes(element: Double): ByteArray = element.toBytesBE()
    }

    val Float32 = Float32BE
    val Float64 = Float64BE
    val Float = Float32
    val Double = Float64

    object Boolean : DataStructureElement<kotlin.Boolean> {
        override val byteLength: Int = 1
        override fun parse(bytes: ByteArray) = bytes[0] != 0.toByte()
        override fun parse(bytes: ByteArray, offset: Int) = bytes[offset] != 0.toByte()
        override fun toBytes(element: kotlin.Boolean): ByteArray = byteArrayOf(if (element) 1 else 0)
    }

    object UTF8 : DataStructureElement<Char> {
        override val byteLength: Int = 1
        override fun parse(bytes: ByteArray): Char = bytes.toUnsignedByte().toInt().toChar()
        override fun parse(bytes: ByteArray, offset: Int): Char = bytes[offset].toUByte().toInt().toChar()
        override fun toBytes(element: Char): ByteArray = byteArrayOf(element.code.toByte())
    }

    object UTF16LE : DataStructureElement<Char> {
        override val byteLength: Int = 2
        override fun parse(bytes: ByteArray) = bytes.toUnsignedShortBE().toInt().toChar()
        override fun parse(bytes: ByteArray, offset: Int) = bytes.getShortBE(offset).toInt().toChar()
        override fun toBytes(element: Char): ByteArray = element.code.toShort().toBytesBE()
    }

    object UTF16BE : DataStructureElement<Char> {
        override val byteLength: Int = 2
        override fun parse(bytes: ByteArray) = bytes.toUnsignedShortLE().toInt().toChar()
        override fun parse(bytes: ByteArray, offset: Int) = bytes.getShortLE(offset).toInt().toChar()
        override fun toBytes(element: Char): ByteArray = element.code.toShort().toBytesLE()
    }

    val UTF16 = UTF16BE
}
