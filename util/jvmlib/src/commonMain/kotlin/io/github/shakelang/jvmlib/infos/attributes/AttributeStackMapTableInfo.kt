package io.github.shakelang.jvmlib.infos.attributes

import io.github.shakelang.jvmlib.infos.constants.ConstantInfo
import io.github.shakelang.jvmlib.infos.constants.ConstantPool
import io.github.shakelang.jvmlib.infos.constants.ConstantUtf8Info
import io.github.shakelang.parseutils.bytes.setUnsignedShort
import io.github.shakelang.parseutils.bytes.toBytes
import io.github.shakelang.parseutils.streaming.input.DataInputStream

class AttributeStackMapTableInfo (
    name: ConstantUtf8Info,
    val entries: StackMapFrameList
) : AttributeInfo(name) {

    override val bytes: ByteArray
        get() = entries.bytes

    override val uses: Array<ConstantInfo>
        get() = TODO("Not yet implemented")

    override fun toJson(): Map<String, Any> {
        TODO("Not yet implemented")
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

        companion object {
            fun fromStream(stream: DataInputStream): StackMapFrameList {
                val numberOfEntries = stream.readUnsignedShort()
                val frames = Array(numberOfEntries.toInt()) { StackMapFrameInfo.fromStream(stream) }
                return StackMapFrameList(frames.toMutableList())
            }
        }
    }

    abstract class StackMapFrameInfo {
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

            companion object {
                fun fromStream(frameType: UByte, stream: DataInputStream): SameLocals1StackItemFrameInfo {
                    val stack = VerificationTypeList.fromStream(stream)
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

            companion object {
                fun fromStream(stream: DataInputStream): SameLocals1StackItemFrameExtendedInfo {
                    return SameLocals1StackItemFrameExtendedInfo(
                        stream.readUnsignedShort(),
                        VerificationTypeList.fromStream(stream)
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
            val k get() = frameType.toInt() - 251
            override val bytes: ByteArray get() = byteArrayOf(frameType.toByte(), *offsetDelta.toBytes()) + locals.bytes

            companion object {
                fun fromStream(frameType: UByte, stream: DataInputStream): AppendFrameInfo {
                    val locals = VerificationTypeList.fromStream(stream)
                    return AppendFrameInfo(frameType, stream.readUnsignedShort(), locals)
                }
            }
        }

        class FullFrameInfo(
            override val offsetDelta: UShort,
            val numberOfLocals: UShort,
            val numberOfStackItems: UShort,
            val locals: Array<VerificationTypeList>,
            val stack: Array<VerificationTypeList>
        ) : StackMapFrameInfo() {
            override val frameType: UByte get() = 255u
            override val bytes: ByteArray get() =
                byteArrayOf(frameType.toByte(), *offsetDelta.toBytes(), *numberOfLocals.toBytes(), *numberOfStackItems.toBytes()) +
                        locals.flatMap { it.bytes.toList() }.toByteArray() +
                        stack.flatMap { it.bytes.toList() }.toByteArray()

            companion object {
                fun fromStream(stream: DataInputStream): FullFrameInfo {
                    val offsetDelta = stream.readUnsignedShort()
                    val numberOfLocals = stream.readUnsignedShort()
                    val locals = Array(numberOfLocals.toInt()) { VerificationTypeList.fromStream(stream) }
                    val numberOfStackItems = stream.readUnsignedShort()
                    val stack = Array(numberOfStackItems.toInt()) { VerificationTypeList.fromStream(stream) }
                    return FullFrameInfo(offsetDelta, numberOfLocals, numberOfStackItems, locals, stack)
                }
            }
        }
    }

    class VerificationTypeList (
        val entries: MutableList<UByte>
    ) : MutableList<UByte> by entries {

        val bytes: ByteArray get() = entries.map { it.toByte() }.toByteArray()

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

            fun fromStream(stream: DataInputStream): VerificationTypeList {
                val count = stream.readUnsignedShort()
                val entries = Array(count.toInt()) { stream.readUnsignedByte() }
                return VerificationTypeList(entries.toMutableList())
            }
        }
    }

    companion object {
        fun contentsFromStream(stream: DataInputStream, name: ConstantUtf8Info): AttributeStackMapTableInfo {
            return AttributeStackMapTableInfo(name, StackMapFrameList.fromStream(stream))
        }
    }
}