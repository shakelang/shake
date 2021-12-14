package io.github.shakelang.jvmlib.infos.attributes

import io.github.shakelang.jvmlib.infos.constants.ConstantInfo
import io.github.shakelang.jvmlib.infos.constants.ConstantUtf8Info
import io.github.shakelang.parseutils.bytes.setUnsignedShort
import io.github.shakelang.parseutils.bytes.toBytes
import io.github.shakelang.parseutils.bytes.toHexString
import io.github.shakelang.parseutils.streaming.input.DataInputStream

class AttributeStackMapTableInfo (
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
                    bytes.copyInto(c, offset)
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
                    247u.toUByte() -> SameLocals1StackItemFrameExtendedInfo.fromStream(stream)
                    in 248u..250u -> ChopFrameInfo.fromStream(frameType, stream)
                    251u.toUByte() -> SameFrameExtendedInfo.fromStream(frameType, stream)
                    in 252u..254u -> AppendFrameInfo.fromStream(frameType, stream)
                    255u.toUByte() -> FullFrameInfo.fromStream(stream)
                    else -> throw IllegalArgumentException("Unknown frame type: $frameType")
                }
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
                    return SameFrameInfo(frameType)
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
                    val stack = VerificationTypeList.fromStream(stream, 1)
                    return SameLocals1StackItemFrameInfo(frameType, stack)
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
                fun fromStream(stream: DataInputStream): SameLocals1StackItemFrameExtendedInfo {
                    return SameLocals1StackItemFrameExtendedInfo(
                        stream.readUnsignedShort(),
                        VerificationTypeList.fromStream(stream, 1)
                    )
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
                    return ChopFrameInfo(frameType, stream.readUnsignedShort())
                }
            }
        }

        class SameFrameExtendedInfo(
            override val offsetDelta: UShort
        ) : StackMapFrameInfo() {
            override val frameType: UByte get() = 251u
            override val bytes: ByteArray get() = offsetDelta.toBytes()

            override fun toJson(): Map<String, Any> = mapOf(
                "offsetDelta" to offsetDelta.toInt()
            )

            companion object {
                fun fromStream(frameType: UByte, stream: DataInputStream): SameFrameExtendedInfo {
                    return SameFrameExtendedInfo(stream.readUnsignedShort())
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
                    val locals = VerificationTypeList.fromStream(stream, (frameType - 251u).toInt())
                    return AppendFrameInfo(frameType, stream.readUnsignedShort(), locals)
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
            override val bytes: ByteArray get() = byteArrayOf(frameType.toByte()) + offsetDelta.toBytes() +
                numberOfLocals.toBytes() + numberOfStackItems.toBytes() + locals.bytes + stack.bytes

            override fun toJson(): Map<String, Any> = mapOf(
                "offsetDelta" to offsetDelta.toInt(),
                "numberOfLocals" to numberOfLocals.toInt(),
                "numberOfStackItems" to numberOfStackItems.toInt(),
                "locals" to locals.toJson(),
                "stack" to stack.toJson()
            )

            companion object {
                fun fromStream(stream: DataInputStream): FullFrameInfo {
                    val offsetDelta = stream.readUnsignedShort()
                    val numberOfLocals = stream.readUnsignedShort()
                    val locals = VerificationTypeList.fromStream(stream, numberOfLocals.toInt())
                    val numberOfStackItems = stream.readUnsignedShort()
                    val stack = VerificationTypeList.fromStream(stream, numberOfStackItems.toInt())
                    return FullFrameInfo(offsetDelta, numberOfLocals, numberOfStackItems, locals, stack)
                }
            }
        }
    }

    class VerificationTypeList (
        val entries: MutableList<UByte>
    ) : MutableList<UByte> by entries {

        val bytes: ByteArray get() = entries.map { it.toByte() }.toByteArray()

        fun toJson(): String = entries.map { it.toByte() }.toByteArray().toHexString()

        companion object {
            const val ITEM_TOP : UByte = 0u
            const val ITEM_INTEGER : UByte = 1u
            const val ITEM_FLOAT : UByte = 2u
            const val ITEM_DOUBLE : UByte = 3u
            const val ITEM_LONG : UByte = 4u
            const val ITEM_NULL : UByte = 5u
            const val ITEM_UNINITIALIZED_THIS : UByte = 6u
            const val ITEM_OBJECT : UByte = 7u
            const val ITEM_UNINITIALIZED : UByte = 8u

            fun fromStream(stream: DataInputStream, count: Int): VerificationTypeList {
                val entries = Array(count) {
                    stream.readUnsignedByte()
                }
                return VerificationTypeList(entries.toMutableList())
            }
        }
    }
}