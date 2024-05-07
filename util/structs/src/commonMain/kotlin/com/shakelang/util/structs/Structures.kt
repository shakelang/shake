package com.shakelang.util.structs

import com.shakelang.util.io.streaming.input.bytes.InputStream
import com.shakelang.util.io.streaming.output.bytes.OutputStream

object Structures {
    open class FixedLengthArray<E, T : DataStructureElement<E>>(
        val type: T,
        val size: Int,
    ) : DataStructureElement<List<E>> {
        override val byteLength: Int = if (type.byteLength < 0) -1 else type.byteLength * size
        override fun parse(bytes: ByteArray): List<E> {
            var i = 0
            val arr = mutableListOf<E>()
            while (i < size) {
                arr.add(type.parse(bytes, type.byteLength * i++))
            }
            return arr.toList()
        }

        override fun parse(bytes: ByteArray, offset: Int): List<E> {
            var i = 0
            val arr = mutableListOf<E>()
            while (i < size) {
                arr.add(type.parse(bytes, type.byteLength * i++ + offset))
            }
            return arr.toList()
        }

        override fun toBytes(element: List<E>): ByteArray {
            var i = 0
            val elements = element.map { type.toBytes(it) }

            val byteArray = ByteArray(elements.sumOf { it.size })
            for (e in elements) {
                for (b in e) {
                    byteArray[i++] = b
                }
            }

            if (i != byteLength) throw IllegalStateException("The byte length of the array is not correct")
            return byteArray
        }

        override fun dump(element: List<E>, outputStream: OutputStream) {
            for (e in element) {
                type.dump(e, outputStream)
            }
        }

        override fun load(inputStream: InputStream): List<E> {
            val arr = mutableListOf<E>()
            for (i in 0 until size) {
                arr.add(type.load(inputStream))
            }
            return arr.toList()
        }
    }

    class FixedLengthUTF8String(size: Int) : FixedLengthArray<Char, PrimitiveDataStructureElements.UTF8>(
        PrimitiveDataStructureElements.UTF8,
        size,
    )

    class FixedLengthUTF16BEString(size: Int) : FixedLengthArray<Char, PrimitiveDataStructureElements.UTF16BE>(
        PrimitiveDataStructureElements.UTF16BE,
        size,
    )

    class FixedLengthUTF16LEString(size: Int) : FixedLengthArray<Char, PrimitiveDataStructureElements.UTF16LE>(
        PrimitiveDataStructureElements.UTF16LE,
        size,
    )

    open class Struct(val elements: List<Pair<String, DataStructureElement<*>>>) : DataStructureElement<Struct.ParseResult> {

        val map: Map<String, DataStructureElement<*>> = elements.toMap()
        open class ParseResult(val map: Map<String, *>)

        override val byteLength: Int = if (elements.any { it.second.byteLength < 0 }) -1 else elements.sumOf { it.second.byteLength }

        override fun parse(bytes: ByteArray) = parse(bytes, 0)

        override fun parse(bytes: ByteArray, offset: Int): ParseResult {
            var off = offset
            val map = mutableMapOf<String, Any?>()
            for ((name, element) in elements) {
                val value = element.parse(bytes, off)
                map[name] = value
                off += element.byteLength
            }
            return ParseResult(map)
        }

        override fun toBytes(element: ParseResult): ByteArray {
            // Check element
            for ((name, e) in elements)
                if (!element.map.containsKey(name)) throw IllegalStateException("Element $name is missing in the struct")

            for ((name, e) in element.map)
                if (!map.containsKey(name)) throw IllegalStateException("Element $name is not in the struct")

            val byteArray = ByteArray(byteLength) { 0 }
            var i = 0

            for ((name, e) in elements) {
                val value = element.map[name]

                // TODO not null assertion
                @Suppress("UNCHECKED_CAST")
                val bytes = (e as DataStructureElement<Any>).toBytes(value!!)
                for (b in bytes) {
                    byteArray[i++] = b
                }
            }

            return byteArray
        }
    }

    open class AbstractArray<E, T : DataStructureElement<E>>(
        val sizeType: DataStructureNumberElement<*>,
        val elementType: T,
    ) : DataStructureElement<List<E>> {
        override val byteLength: Int = -1

        override fun parse(bytes: ByteArray): List<E> {
            val size = sizeType.parse(bytes)
            val arr = mutableListOf<E>()
            var off = sizeType.byteLength
            for (i in 0 until size.toInt()) {
                val element = elementType.parse(bytes, off)
                arr.add(element)
                off += elementType.byteLength
            }
            return arr.toList()
        }

        override fun parse(bytes: ByteArray, offset: Int): List<E> {
            val size = sizeType.parse(bytes, offset)
            val arr = mutableListOf<E>()
            var off = offset + sizeType.byteLength
            for (i in 0 until size.toInt()) {
                val element = elementType.parse(bytes, off)
                arr.add(element)
                off += elementType.byteLength
            }
            return arr.toList()
        }

        override fun toBytes(element: List<E>): ByteArray {
            val size = element.size
            val sizeBytes = sizeType.convertToBytes(size)
            val elements = element.map { elementType.toBytes(it) }

            val byteArray = ByteArray(sizeBytes.size + elements.sumOf { it.size })
            var i = 0

            for (b in sizeBytes) {
                byteArray[i++] = b
            }

            for (e in elements) {
                for (b in e) {
                    byteArray[i++] = b
                }
            }

            return byteArray
        }
    }

    open class ClassicArray<E, T : DataStructureElement<E>>(
        elementType: T,
    ) : AbstractArray<E, T>(PrimitiveDataStructureElements.Int32BE, elementType)

    object ClassicUTF8String : DataStructureElement<String> {
        override val byteLength: Int = -1

        val UTF8Array = ClassicArray(PrimitiveDataStructureElements.UTF8)

        override fun toBytes(element: String): ByteArray {
            return UTF8Array.toBytes(element.toList())
        }

        override fun parse(bytes: ByteArray): String {
            return UTF8Array.parse(bytes).joinToString("")
        }

        override fun parse(bytes: ByteArray, offset: Int): String {
            return UTF8Array.parse(bytes, offset).joinToString("")
        }

        override fun dump(element: String, outputStream: OutputStream) {
            UTF8Array.dump(element.toList(), outputStream)
        }

        override fun load(inputStream: InputStream): String {
            return UTF8Array.load(inputStream).joinToString("")
        }
    }

    object ClassicUTF16BEString : DataStructureElement<String> {
        override val byteLength: Int = -1

        val UTF16BEArray = ClassicArray(PrimitiveDataStructureElements.UTF16BE)

        override fun toBytes(element: String): ByteArray {
            return UTF16BEArray.toBytes(element.toList())
        }

        override fun parse(bytes: ByteArray): String {
            return UTF16BEArray.parse(bytes).joinToString("")
        }

        override fun parse(bytes: ByteArray, offset: Int): String {
            return UTF16BEArray.parse(bytes, offset).joinToString("")
        }

        override fun dump(element: String, outputStream: OutputStream) {
            UTF16BEArray.dump(element.toList(), outputStream)
        }

        override fun load(inputStream: InputStream): String {
            return UTF16BEArray.load(inputStream).joinToString("")
        }
    }

    object ClassicUTF16LEString : DataStructureElement<String> {
        override val byteLength: Int = -1

        val UTF16LEArray = ClassicArray(PrimitiveDataStructureElements.UTF16LE)

        override fun toBytes(element: String): ByteArray {
            return UTF16LEArray.toBytes(element.toList())
        }

        override fun parse(bytes: ByteArray): String {
            return UTF16LEArray.parse(bytes).joinToString("")
        }

        override fun parse(bytes: ByteArray, offset: Int): String {
            return UTF16LEArray.parse(bytes, offset).joinToString("")
        }

        override fun dump(element: String, outputStream: OutputStream) {
            UTF16LEArray.dump(element.toList(), outputStream)
        }

        override fun load(inputStream: InputStream): String {
            return UTF16LEArray.load(inputStream).joinToString("")
        }
    }

    val ClassicUTF16String = ClassicUTF16BEString
}
