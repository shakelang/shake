package com.shakelang.util.jvmlib.infos.attributes

import com.shakelang.util.io.streaming.input.DataInputStream
import com.shakelang.util.io.streaming.input.InputStream
import com.shakelang.util.io.streaming.input.dataStream
import com.shakelang.util.jvmlib.infos.constants.ConstantInfo
import com.shakelang.util.jvmlib.infos.constants.ConstantPool
import com.shakelang.util.jvmlib.infos.constants.ConstantUtf8Info
import com.shakelang.util.primitives.bytes.*

class AttributeStackMapTableInfo(
    name: ConstantUtf8Info,
    val entries: StackMapFrameList
) : AttributeInfo(name) {

    override val bytes: ByteArray
        get() = entries.bytes

    override val uses: Array<ConstantInfo>
        get() = arrayOf(this.name)

    override fun toJson(): Map<String, Any> {
        return mapOf(
            "tag" to ATTRIBUTE_STACK_MAP_TABLE_TAG,
            "entries" to entries.toJson()
        )
    }

    companion object {
        const val ATTRIBUTE_STACK_MAP_TABLE_TAG: String = "StackMapTable"
        fun contentsFromStream(stream: DataInputStream, name: ConstantUtf8Info): AttributeStackMapTableInfo {
            return AttributeStackMapTableInfo(name, StackMapFrameList.fromStream(stream))
        }

        fun contentsFromStream(stream: InputStream, name: ConstantUtf8Info): AttributeStackMapTableInfo {
            return AttributeStackMapTableInfo(name, StackMapFrameList.fromStream(stream.dataStream))
        }

        fun contentsFromBytes(bytes: ByteArray, name: ConstantUtf8Info): AttributeStackMapTableInfo {
            return AttributeStackMapTableInfo(name, StackMapFrameList.fromBytes(bytes))
        }

        fun contentsFromBytes(bytes: ByteArray, name: ConstantUtf8Info, offset: Int): AttributeStackMapTableInfo {
            return AttributeStackMapTableInfo(name, StackMapFrameList.fromBytes(bytes, offset))
        }

        fun fromStream(pool: ConstantPool, stream: DataInputStream): AttributeStackMapTableInfo {
            val name = pool.getUtf8(stream.readUnsignedShort())
            val length = stream.readUnsignedInt()
            return contentsFromStream(stream, name)
        }

        fun fromStream(pool: ConstantPool, stream: InputStream): AttributeStackMapTableInfo {
            return fromStream(pool, stream.dataStream)
        }

        fun fromBytes(pool: ConstantPool, bytes: ByteArray): AttributeStackMapTableInfo {
            val name = pool.getUtf8(bytes.getUnsignedShort(0))
            return contentsFromBytes(bytes, name, 6)
        }

        fun fromBytes(pool: ConstantPool, bytes: ByteArray, offset: Int): AttributeStackMapTableInfo {
            val name = pool.getUtf8(bytes.getUnsignedShort(offset))
            return contentsFromBytes(bytes, name, offset + 6)
        }
    }

    class StackMapFrameList(
        val frames: MutableList<StackMapFrameInfo>
    ) : MutableList<StackMapFrameInfo> by frames {

        val bytes: ByteArray
            get() {
                val cbytes = frames.map { it.bytes }
                val bytes = ByteArray(2 + cbytes.sumOf { it.size })
                bytes.setUnsignedShort(0, cbytes.size.toUShort())
                var offset = 2
                for (c in cbytes) {
                    c.copyInto(bytes, offset)
                    offset += c.size
                }
                return bytes
            }

        fun toJson() = frames.map { it.toJson() }

        companion object {
            fun fromStream(stream: DataInputStream): StackMapFrameList {
                val numberOfEntries = stream.readUnsignedShort()
                val frames = Array(numberOfEntries.toInt()) {
                    StackMapFrameInfo.fromStream(stream)
                }
                return StackMapFrameList(frames.toMutableList())
            }

            fun fromStream(stream: InputStream): StackMapFrameList {
                return fromStream(stream.dataStream)
            }

            fun fromBytes(bytes: ByteArray, offset: Int): StackMapFrameList {
                val stream = bytes.dataStream()
                stream.skip(offset.toLong())
                return fromStream(stream)
            }

            fun fromBytes(bytes: ByteArray): StackMapFrameList {
                val stream = bytes.dataStream()
                return fromStream(stream)
            }
        }
    }

    abstract class StackMapFrameInfo {
        abstract fun toJson(): Map<String, Any>

        abstract val bytes: ByteArray
        abstract val frameType: UByte
        abstract val offsetDelta: UShort

        companion object {
            fun fromStream(stream: DataInputStream): StackMapFrameInfo {
                return when (val frameType = stream.readUnsignedByte()) {
                    in 0u..63u -> SameFrameInfo.fromStream(frameType, stream)
                    in 64u..127u -> SameLocals1StackItemFrameInfo.fromStream(frameType, stream)
                    247u.toUByte() -> SameLocals1StackItemFrameExtendedInfo.fromStream(frameType, stream)
                    in 248u..250u -> ChopFrameInfo.fromStream(frameType, stream)
                    251u.toUByte() -> SameFrameExtendedInfo.fromStream(frameType, stream)
                    in 252u..254u -> AppendFrameInfo.fromStream(frameType, stream)
                    255u.toUByte() -> FullFrameInfo.fromStream(frameType, stream)
                    else -> throw IllegalArgumentException("Unknown frame type: $frameType")
                }
            }

            fun fromStream(stream: InputStream): StackMapFrameInfo {
                return fromStream(stream.dataStream)
            }

            fun fromBytes(bytes: ByteArray, offset: Int): StackMapFrameInfo {
                return when (val frameType = bytes.getUnsignedByte(offset)) {
                    in 0u..63u -> SameFrameInfo.fromBytes(bytes, offset)
                    in 64u..127u -> SameLocals1StackItemFrameInfo.fromBytes(bytes, offset)
                    247u.toUByte() -> SameLocals1StackItemFrameExtendedInfo.fromBytes(bytes, offset)
                    in 248u..250u -> ChopFrameInfo.fromBytes(bytes, offset)
                    251u.toUByte() -> SameFrameExtendedInfo.fromBytes(bytes, offset)
                    in 252u..254u -> AppendFrameInfo.fromBytes(bytes, offset)
                    255u.toUByte() -> FullFrameInfo.fromBytes(bytes, offset)
                    else -> throw IllegalArgumentException("Unknown frame type: $frameType")
                }
            }

            fun fromBytes(bytes: ByteArray): StackMapFrameInfo {
                return fromBytes(bytes, 0)
            }
        }

        class SameFrameInfo(
            override val frameType: UByte
        ) : StackMapFrameInfo() {
            override val offsetDelta: UShort get() = frameType.toUShort()
            override val bytes: ByteArray get() = byteArrayOf(frameType.toByte())

            override fun toJson() = mapOf(
                "frameType" to frameType.toInt()
            )

            companion object {
                fun fromStream(frameType: UByte, stream: DataInputStream): SameFrameInfo {
                    if (frameType !in 0u..63u) throw IllegalArgumentException("Invalid frame type: $frameType (expected 0..63)")
                    return SameFrameInfo(frameType)
                }

                fun fromStream(frameType: UByte, stream: InputStream): SameFrameInfo {
                    if (frameType !in 0u..63u) throw IllegalArgumentException("Invalid frame type: $frameType (expected 0..63)")
                    return SameFrameInfo(frameType)
                }

                fun fromBytes(frameType: UByte, bytes: ByteArray): SameFrameInfo {
                    if (frameType !in 0u..63u) throw IllegalArgumentException("Invalid frame type: $frameType (expected 0..63)")
                    return SameFrameInfo(frameType)
                }

                fun fromBytes(frameType: UByte, bytes: ByteArray, offset: Int): SameFrameInfo {
                    if (frameType !in 0u..63u) throw IllegalArgumentException("Invalid frame type: $frameType (expected 0..63)")
                    return SameFrameInfo(frameType)
                }

                fun fromStream(stream: DataInputStream): SameFrameInfo {
                    val frameType = stream.readUnsignedByte()
                    if (frameType !in 0u..63u) throw IllegalArgumentException("Invalid frame type: $frameType (expected 0..63)")
                    return SameFrameInfo(frameType)
                }

                fun fromStream(stream: InputStream): SameFrameInfo {
                    return fromStream(DataInputStream(stream))
                }

                fun fromBytes(bytes: ByteArray, offset: Int): SameFrameInfo {
                    return fromBytes(bytes[offset].toUByte(), bytes, offset + 1)
                }

                fun fromBytes(bytes: ByteArray): SameFrameInfo {
                    return fromBytes(bytes[0].toUByte(), bytes, 1)
                }
            }
        }

        class SameLocals1StackItemFrameInfo(
            override val frameType: UByte,
            val stack: VerificationTypeList
        ) : StackMapFrameInfo() {

            override val offsetDelta: UShort get() = (frameType - 64u).toUShort()
            override val bytes: ByteArray get() = byteArrayOf(frameType.toByte()) + stack.bytes

            override fun toJson(): Map<String, Any> {
                return mapOf(
                    "frameType" to frameType.toInt(),
                    "stack" to stack.toJson()
                )
            }

            companion object {
                fun fromStream(frameType: UByte, stream: DataInputStream): SameLocals1StackItemFrameInfo {
                    if (frameType !in 64u..127u) throw IllegalArgumentException("Invalid frame type: $frameType (expected 64-127)")
                    val stack = VerificationTypeList.fromStream(stream, 1)
                    return SameLocals1StackItemFrameInfo(frameType, stack)
                }

                fun fromStream(frameType: UByte, stream: InputStream): SameLocals1StackItemFrameInfo {
                    return fromStream(frameType, stream.dataStream)
                }

                fun fromBytes(frameType: UByte, bytes: ByteArray): SameLocals1StackItemFrameInfo {
                    if (frameType !in 64u..127u) throw IllegalArgumentException("Invalid frame type: $frameType (expected 64-127)")
                    val stack = VerificationTypeList.fromBytes(bytes, 1)
                    return SameLocals1StackItemFrameInfo(frameType, stack)
                }

                fun fromBytes(frameType: UByte, bytes: ByteArray, offset: Int): SameLocals1StackItemFrameInfo {
                    if (frameType !in 64u..127u) throw IllegalArgumentException("Invalid frame type: $frameType (expected 64-127)")
                    val stack = VerificationTypeList.fromBytes(bytes, offset, 1)
                    return SameLocals1StackItemFrameInfo(frameType, stack)
                }

                fun fromStream(stream: DataInputStream): SameLocals1StackItemFrameInfo {
                    val frameType = stream.readUnsignedByte()
                    return fromStream(frameType, stream)
                }

                fun fromStream(stream: InputStream): SameLocals1StackItemFrameInfo {
                    return fromStream(stream.dataStream)
                }

                fun fromBytes(bytes: ByteArray, offset: Int): SameLocals1StackItemFrameInfo {
                    val frameType = bytes.getUnsignedByte(offset)
                    return fromBytes(frameType, bytes, offset + 1)
                }

                fun fromBytes(bytes: ByteArray): SameLocals1StackItemFrameInfo {
                    return fromBytes(bytes, 0)
                }
            }
        }

        class SameLocals1StackItemFrameExtendedInfo(
            override val offsetDelta: UShort,
            val stack: VerificationTypeList
        ) : StackMapFrameInfo() {
            override val frameType: UByte get() = 247u
            override val bytes: ByteArray get() = byteArrayOf(frameType.toByte(), *offsetDelta.toBytes(), *stack.bytes)

            override fun toJson(): Map<String, Any> = mapOf(
                "offsetDelta" to offsetDelta.toInt(),
                "stack" to stack.toJson()
            )

            companion object {
                fun fromStream(frameType: UByte, stream: DataInputStream): SameLocals1StackItemFrameExtendedInfo {
                    if (frameType != 247u.toUByte()) throw IllegalArgumentException("frameType must be 247")
                    return SameLocals1StackItemFrameExtendedInfo(
                        stream.readUnsignedShort(),
                        VerificationTypeList.fromStream(stream, 1)
                    )
                }

                fun fromStream(frameType: UByte, stream: InputStream): SameLocals1StackItemFrameExtendedInfo {
                    return fromStream(frameType, stream.dataStream)
                }

                fun fromBytes(frameType: UByte, bytes: ByteArray, offset: Int): SameLocals1StackItemFrameExtendedInfo {
                    if (frameType != 247u.toUByte()) throw IllegalArgumentException("frameType must be 247")
                    val offsetDelta = bytes.getUnsignedShort(offset)
                    val stack = VerificationTypeList.fromBytes(bytes, offset + 2, 1)
                    return SameLocals1StackItemFrameExtendedInfo(offsetDelta, stack)
                }

                fun fromBytes(frameType: UByte, bytes: ByteArray): SameLocals1StackItemFrameExtendedInfo {
                    return fromBytes(frameType, bytes, 0)
                }

                fun fromStream(stream: DataInputStream): SameLocals1StackItemFrameExtendedInfo {
                    val frameType = stream.readUnsignedByte()
                    return fromStream(frameType, stream)
                }

                fun fromStream(stream: InputStream): SameLocals1StackItemFrameExtendedInfo {
                    return fromStream(stream.dataStream)
                }

                fun fromBytes(bytes: ByteArray, offset: Int): SameLocals1StackItemFrameExtendedInfo {
                    val frameType = bytes.getUnsignedByte(offset)
                    return fromBytes(frameType, bytes, offset + 1)
                }

                fun fromBytes(bytes: ByteArray): SameLocals1StackItemFrameExtendedInfo {
                    return fromBytes(bytes, 0)
                }
            }
        }

        class ChopFrameInfo(
            override val frameType: UByte,
            override val offsetDelta: UShort
        ) : StackMapFrameInfo() {
            val k get() = 251 - frameType.toInt()
            override val bytes: ByteArray get() = byteArrayOf(frameType.toByte(), *offsetDelta.toBytes())

            override fun toJson(): Map<String, Any> = mapOf(
                "frameType" to frameType.toInt(),
                "offsetDelta" to offsetDelta.toInt()
            )

            companion object {
                fun fromStream(frameType: UByte, stream: DataInputStream): ChopFrameInfo {
                    if (frameType !in 248u..250u) throw IllegalArgumentException("frameType must be 248-250")
                    return ChopFrameInfo(frameType, stream.readUnsignedShort())
                }

                fun fromStream(frameType: UByte, stream: InputStream): ChopFrameInfo {
                    return fromStream(frameType, stream.dataStream)
                }

                fun fromBytes(frameType: UByte, bytes: ByteArray, offset: Int): ChopFrameInfo {
                    if (frameType !in 248u..250u) throw IllegalArgumentException("frameType must be 248-250")
                    val offsetDelta = bytes.getUnsignedShort(offset)
                    return ChopFrameInfo(frameType, offsetDelta)
                }

                fun fromBytes(frameType: UByte, bytes: ByteArray): ChopFrameInfo {
                    return fromBytes(frameType, bytes, 0)
                }

                fun fromStream(stream: DataInputStream): ChopFrameInfo {
                    val frameType = stream.readUnsignedByte()
                    return fromStream(frameType, stream)
                }

                fun fromStream(stream: InputStream): ChopFrameInfo {
                    return fromStream(stream.dataStream)
                }

                fun fromBytes(bytes: ByteArray, offset: Int): ChopFrameInfo {
                    val frameType = bytes.getUnsignedByte(offset)
                    return fromBytes(frameType, bytes, offset + 1)
                }

                fun fromBytes(bytes: ByteArray): ChopFrameInfo {
                    return fromBytes(bytes, 0)
                }
            }
        }

        class SameFrameExtendedInfo(
            override val offsetDelta: UShort
        ) : StackMapFrameInfo() {
            override val frameType: UByte get() = 251u
            override val bytes: ByteArray get() = byteArrayOf(frameType.toByte(), *offsetDelta.toBytes())

            override fun toJson(): Map<String, Any> = mapOf(
                "offsetDelta" to offsetDelta.toInt()
            )

            companion object {
                fun fromStream(frameType: UByte, stream: DataInputStream): SameFrameExtendedInfo {
                    if (frameType != 251u.toUByte()) throw IllegalArgumentException("frameType must be 251")
                    return SameFrameExtendedInfo(stream.readUnsignedShort())
                }

                fun fromStream(frameType: UByte, stream: InputStream): SameFrameExtendedInfo {
                    return fromStream(frameType, stream.dataStream)
                }

                fun fromBytes(frameType: UByte, bytes: ByteArray, offset: Int): SameFrameExtendedInfo {
                    if (frameType != 251u.toUByte()) throw IllegalArgumentException("frameType must be 251")
                    return SameFrameExtendedInfo(bytes.getUnsignedShort(offset))
                }

                fun fromBytes(frameType: UByte, bytes: ByteArray): SameFrameExtendedInfo {
                    return fromBytes(frameType, bytes, 0)
                }

                fun fromStream(stream: DataInputStream): SameFrameExtendedInfo {
                    val frameType = stream.readUnsignedByte()
                    return fromStream(frameType, stream)
                }

                fun fromStream(stream: InputStream): SameFrameExtendedInfo {
                    return fromStream(stream.dataStream)
                }

                fun fromBytes(bytes: ByteArray, offset: Int): SameFrameExtendedInfo {
                    val frameType = bytes.getUnsignedByte(offset)
                    return fromBytes(frameType, bytes, offset + 1)
                }

                fun fromBytes(bytes: ByteArray): SameFrameExtendedInfo {
                    return fromBytes(bytes, 0)
                }
            }
        }

        class AppendFrameInfo(
            override val frameType: UByte,
            override val offsetDelta: UShort,
            val locals: VerificationTypeList
        ) : StackMapFrameInfo() {
            override val bytes: ByteArray get() = byteArrayOf(frameType.toByte(), *offsetDelta.toBytes()) + locals.bytes

            override fun toJson(): Map<String, Any> = mapOf(
                "frameType" to frameType.toInt(),
                "offsetDelta" to offsetDelta.toInt(),
                "locals" to locals.toJson()
            )

            companion object {
                fun fromStream(frameType: UByte, stream: DataInputStream): AppendFrameInfo {
                    if (frameType !in 252u..254u) throw IllegalArgumentException("frameType must be 252..254")
                    val offsetDelta = stream.readUnsignedShort()
                    val locals = VerificationTypeList.fromStream(stream, (frameType - 251u).toInt())
                    return AppendFrameInfo(frameType, offsetDelta, locals)
                }

                fun fromStream(frameType: UByte, stream: InputStream): AppendFrameInfo {
                    return fromStream(frameType, stream.dataStream)
                }

                fun fromBytes(frameType: UByte, bytes: ByteArray, offset: Int): AppendFrameInfo {
                    if (frameType !in 252u..254u) throw IllegalArgumentException("frameType must be 252..254")
                    val locals = VerificationTypeList.fromBytes(bytes, offset + 2, (frameType - 251u).toInt())
                    return AppendFrameInfo(frameType, bytes.getUnsignedShort(offset), locals)
                }

                fun fromBytes(frameType: UByte, bytes: ByteArray): AppendFrameInfo {
                    return fromBytes(frameType, bytes, 0)
                }

                fun fromStream(stream: DataInputStream): AppendFrameInfo {
                    val frameType = stream.readUnsignedByte()
                    return fromStream(frameType, stream)
                }

                fun fromStream(stream: InputStream): AppendFrameInfo {
                    return fromStream(stream.dataStream)
                }

                fun fromBytes(bytes: ByteArray, offset: Int): AppendFrameInfo {
                    val frameType = bytes.getUnsignedByte(offset)
                    return fromBytes(frameType, bytes, offset + 1)
                }

                fun fromBytes(bytes: ByteArray): AppendFrameInfo {
                    return fromBytes(bytes, 0)
                }
            }
        }

        class FullFrameInfo(
            override val offsetDelta: UShort,
            val numberOfLocals: UShort,
            val numberOfStackItems: UShort,
            val locals: VerificationTypeList,
            val stack: VerificationTypeList
        ) : StackMapFrameInfo() {
            override val frameType: UByte get() = 255u
            override val bytes: ByteArray
                get() = byteArrayOf(
                    frameType.toByte(),
                    *offsetDelta.toBytes(),
                    *numberOfLocals.toBytes(),
                    *locals.bytes,
                    *numberOfStackItems.toBytes(),
                    *stack.bytes
                )

            override fun toJson(): Map<String, Any> = mapOf(
                "offsetDelta" to offsetDelta.toInt(),
                "numberOfLocals" to numberOfLocals.toInt(),
                "numberOfStackItems" to numberOfStackItems.toInt(),
                "locals" to locals.toJson(),
                "stack" to stack.toJson()
            )

            companion object {
                fun fromStream(frameType: UByte, stream: DataInputStream): FullFrameInfo {
                    if (frameType != 255u.toUByte()) throw IllegalArgumentException("Frame type must be 255")
                    val offsetDelta = stream.readUnsignedShort()
                    val numberOfLocals = stream.readUnsignedShort()
                    val locals = VerificationTypeList.fromStream(stream, numberOfLocals.toInt())
                    val numberOfStackItems = stream.readUnsignedShort()
                    val stack = VerificationTypeList.fromStream(stream, numberOfStackItems.toInt())
                    return FullFrameInfo(offsetDelta, numberOfLocals, numberOfStackItems, locals, stack)
                }

                fun fromStream(frameType: UByte, stream: InputStream): FullFrameInfo {
                    return fromStream(frameType, stream.dataStream)
                }

                fun fromBytes(frameType: UByte, bytes: ByteArray, offset: Int): FullFrameInfo {
                    if (frameType != 255u.toUByte()) throw IllegalArgumentException("Frame type must be 255")
                    val offsetDelta = bytes.getUnsignedShort(offset)
                    val numberOfLocals = bytes.getUnsignedShort(offset + 2)
                    val locals = VerificationTypeList.fromBytes(bytes, offset + 4, numberOfLocals.toInt())
                    val numberOfStackItems = bytes.getUnsignedShort(offset + 4 + locals.bytes.size)
                    val stack = VerificationTypeList.fromBytes(
                        bytes,
                        offset + 4 + locals.bytes.size + 2,
                        numberOfStackItems.toInt()
                    )
                    return FullFrameInfo(offsetDelta, numberOfLocals, numberOfStackItems, locals, stack)
                }

                fun fromBytes(frameType: UByte, bytes: ByteArray): FullFrameInfo {
                    return fromBytes(frameType, bytes, 0)
                }

                fun fromStream(stream: DataInputStream): FullFrameInfo {
                    val frameType = stream.readUnsignedByte()
                    return fromStream(frameType, stream)
                }

                fun fromStream(stream: InputStream): FullFrameInfo {
                    return fromStream(stream.dataStream)
                }

                fun fromBytes(bytes: ByteArray, offset: Int): FullFrameInfo {
                    val frameType = bytes.getUnsignedByte(offset)
                    return fromBytes(frameType, bytes, offset + 1)
                }

                fun fromBytes(bytes: ByteArray): FullFrameInfo {
                    return fromBytes(bytes, 0)
                }
            }
        }
    }

    class VerificationTypeList(
        val entries: MutableList<UByte>
    ) : MutableList<UByte> by entries {

        val bytes: ByteArray get() = entries.map { it.toByte() }.toByteArray()

        fun toJson(): String = entries.map { it.toByte() }.toByteArray().toHexString()

        companion object {
            const val ITEM_TOP: UByte = 0u
            const val ITEM_INTEGER: UByte = 1u
            const val ITEM_FLOAT: UByte = 2u
            const val ITEM_DOUBLE: UByte = 3u
            const val ITEM_LONG: UByte = 4u
            const val ITEM_NULL: UByte = 5u
            const val ITEM_UNINITIALIZED_THIS: UByte = 6u
            const val ITEM_OBJECT: UByte = 7u
            const val ITEM_UNINITIALIZED: UByte = 8u

            fun fromStream(stream: DataInputStream, count: Int): VerificationTypeList {
                return VerificationTypeList(MutableList(count) { stream.readUnsignedByte() })
            }

            fun fromStream(stream: InputStream, count: Int): VerificationTypeList {
                return fromStream(stream.dataStream, count)
            }

            fun fromBytes(bytes: ByteArray, count: Int): VerificationTypeList {
                return VerificationTypeList(MutableList(count) { bytes[it].toUByte() })
            }

            fun fromBytes(bytes: ByteArray, offset: Int, count: Int): VerificationTypeList {
                return VerificationTypeList(MutableList(count) { bytes[it + offset].toUByte() })
            }
        }
    }
}
