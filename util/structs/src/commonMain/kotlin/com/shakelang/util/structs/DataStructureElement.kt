package com.shakelang.util.structs

import com.shakelang.util.io.streaming.input.bytes.InputStream
import com.shakelang.util.io.streaming.output.bytes.OutputStream

interface DataStructureElement<E> {
    val byteLength: Int
    fun parse(bytes: ByteArray): E
    fun parse(bytes: ByteArray, offset: Int): E
    fun toBytes(element: E): ByteArray
    fun dump(element: E, outputStream: OutputStream) = outputStream.write(toBytes(element))
    fun load(inputStream: InputStream): E = parse(inputStream.readNBytes(byteLength))
}

interface DataStructureNumberElement<E : Number> : DataStructureElement<E> {

    fun convertToBytes(element: Byte): ByteArray
    fun convertToBytes(element: Short): ByteArray
    fun convertToBytes(element: Int): ByteArray
    fun convertToBytes(element: Long): ByteArray
    fun convertToBytes(element: Float): ByteArray
    fun convertToBytes(element: Double): ByteArray
    fun convertToBytes(element: UByte): ByteArray
    fun convertToBytes(element: UShort): ByteArray
    fun convertToBytes(element: UInt): ByteArray
    fun convertToBytes(element: ULong): ByteArray

    fun dumpBytes(element: Byte, outputStream: OutputStream) = outputStream.write(convertToBytes(element))
    fun dumpBytes(element: Short, outputStream: OutputStream) = outputStream.write(convertToBytes(element))
    fun dumpBytes(element: Int, outputStream: OutputStream) = outputStream.write(convertToBytes(element))
    fun dumpBytes(element: Long, outputStream: OutputStream) = outputStream.write(convertToBytes(element))
    fun dumpBytes(element: Float, outputStream: OutputStream) = outputStream.write(convertToBytes(element))
    fun dumpBytes(element: Double, outputStream: OutputStream) = outputStream.write(convertToBytes(element))
    fun dumpBytes(element: UByte, outputStream: OutputStream) = outputStream.write(convertToBytes(element))
    fun dumpBytes(element: UShort, outputStream: OutputStream) = outputStream.write(convertToBytes(element))
    fun dumpBytes(element: UInt, outputStream: OutputStream) = outputStream.write(convertToBytes(element))
    fun dumpBytes(element: ULong, outputStream: OutputStream) = outputStream.write(convertToBytes(element))
}
