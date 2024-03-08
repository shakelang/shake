@file:Suppress("unused_variable")

package com.shakelang.util.jvmlib.infos.attributes

import com.shakelang.util.io.streaming.input.bytes.dataStream
import com.shakelang.util.jvmlib.infos.constants.Constant
import com.shakelang.util.jvmlib.infos.constants.ConstantPool
import kotlin.test.Test
import kotlin.test.assertEquals

class AttributeStackMapTableTests {

    @Test
    fun testVerificationTypeList() {
        val list = AttributeStackMapTableInfo.VerificationTypeList(mutableListOf(0u, 3u))
        assertEquals(2, list.size)
        assertEquals(0u, list[0])
        assertEquals(3u, list[1])
        assertEquals<List<Byte>>(listOf(0, 3), list.bytes.toList())
    }

    @Test
    fun testVerificationTypeListFromStream() {
        val stream = byteArrayOf(0, 3).dataStream()
        val list = AttributeStackMapTableInfo.VerificationTypeList.fromStream(stream, 2)
        assertEquals(2, list.size)
        assertEquals(0u, list[0])
        assertEquals(3u, list[1])
        assertEquals<List<Byte>>(listOf(0, 3), list.bytes.toList())
    }

    @Test
    fun testVerificationTypeListFromBytes() {
        val bytes = byteArrayOf(0, 3)
        val list = AttributeStackMapTableInfo.VerificationTypeList.fromBytes(bytes, 2)
        assertEquals(2, list.size)
        assertEquals(0u, list[0])
        assertEquals(3u, list[1])
        assertEquals<List<Byte>>(listOf(0, 3), list.bytes.toList())
    }

    @Test
    fun testSameFrameInfo() {
        val info = AttributeStackMapTableInfo.StackMapFrameInfo.SameFrameInfo(0u)
        assertEquals(0u, info.frameType)
        assertEquals<List<Byte>>(listOf(0), info.bytes.toList())
    }

    @Test
    fun testSameFrameInfoFromStream() {
        val stream = byteArrayOf().dataStream()
        val info = AttributeStackMapTableInfo.StackMapFrameInfo.SameFrameInfo.fromStream(0u, stream)
        assertEquals(0u, info.frameType)
        assertEquals<List<Byte>>(listOf(0), info.bytes.toList())
    }

    @Test
    fun testSameFrameInfoFromStream2() {
        val stream = byteArrayOf(0).dataStream()
        val info = AttributeStackMapTableInfo.StackMapFrameInfo.SameFrameInfo.fromStream(stream)
        assertEquals(0u, info.frameType)
        assertEquals<List<Byte>>(listOf(0), info.bytes.toList())
    }

    @Test
    fun testSameFrameInfoFromBytes() {
        val bytes = byteArrayOf()
        val info = AttributeStackMapTableInfo.StackMapFrameInfo.SameFrameInfo.fromBytes(0u, bytes)
        assertEquals(0u, info.frameType)
        assertEquals<List<Byte>>(listOf(0), info.bytes.toList())
    }

    @Test
    fun testSameFrameInfoFromBytesOffset() {
        val bytes = byteArrayOf(0)
        val info = AttributeStackMapTableInfo.StackMapFrameInfo.SameFrameInfo.fromBytes(bytes, 0)
        assertEquals(0u, info.frameType)
        assertEquals<List<Byte>>(listOf(0), info.bytes.toList())
    }

    @Test
    fun testSameFrameInfoFromBytes2() {
        val bytes = byteArrayOf(0)
        val info = AttributeStackMapTableInfo.StackMapFrameInfo.SameFrameInfo.fromBytes(bytes)
        assertEquals(0u, info.frameType)
        assertEquals<List<Byte>>(listOf(0), info.bytes.toList())
    }

    @Test
    fun testSameFrameInfoFromBytes2Offset() {
        val bytes = byteArrayOf(0)
        val info = AttributeStackMapTableInfo.StackMapFrameInfo.SameFrameInfo.fromBytes(bytes, 0)
        assertEquals(0u, info.frameType)
        assertEquals<List<Byte>>(listOf(0), info.bytes.toList())
    }

    @Test
    fun testSameLocals1Info() {
        val stack = AttributeStackMapTableInfo.VerificationTypeList(mutableListOf(0u))
        val info = AttributeStackMapTableInfo.StackMapFrameInfo.SameLocals1StackItemFrameInfo(64u, stack)
        assertEquals(64u, info.frameType)
        assertEquals(0u, info.offsetDelta)
        assertEquals<List<Byte>>(listOf(64, 0), info.bytes.toList())
    }

    @Test
    fun testSameLocals1InfoFromStream() {
        val stream = byteArrayOf(0).dataStream()
        val info = AttributeStackMapTableInfo.StackMapFrameInfo.SameLocals1StackItemFrameInfo.fromStream(64u, stream)
        assertEquals(64u, info.frameType)
        assertEquals(0u, info.offsetDelta)
        assertEquals<List<Byte>>(listOf(64, 0), info.bytes.toList())
    }

    @Test
    fun testSameLocals1InfoFromStream2() {
        val stream = byteArrayOf(64, 0).dataStream()
        val info = AttributeStackMapTableInfo.StackMapFrameInfo.SameLocals1StackItemFrameInfo.fromStream(stream)
        assertEquals(64u, info.frameType)
        assertEquals(0u, info.offsetDelta)
        assertEquals<List<Byte>>(listOf(64, 0), info.bytes.toList())
    }

    @Test
    fun testSameLocals1InfoFromBytes() {
        val bytes = byteArrayOf(0)
        val info = AttributeStackMapTableInfo.StackMapFrameInfo.SameLocals1StackItemFrameInfo.fromBytes(64u, bytes)
        assertEquals(64u, info.frameType)
        assertEquals(0u, info.offsetDelta)
        assertEquals<List<Byte>>(listOf(64, 0), info.bytes.toList())
    }

    @Test
    fun testSameLocals1InfoFromBytesOffset() {
        val bytes = byteArrayOf(64, 0)
        val info = AttributeStackMapTableInfo.StackMapFrameInfo.SameLocals1StackItemFrameInfo.fromBytes(bytes, 0)
        assertEquals(64u, info.frameType)
        assertEquals(0u, info.offsetDelta)
        assertEquals<List<Byte>>(listOf(64, 0), info.bytes.toList())
    }

    @Test
    fun testSameLocals1InfoFromBytes2() {
        val bytes = byteArrayOf(64, 0)
        val info = AttributeStackMapTableInfo.StackMapFrameInfo.SameLocals1StackItemFrameInfo.fromBytes(bytes)
        assertEquals(64u, info.frameType)
        assertEquals(0u, info.offsetDelta)
        assertEquals<List<Byte>>(listOf(64, 0), info.bytes.toList())
    }

    @Test
    fun testSameLocals1InfoFromBytes2Offset() {
        val bytes = byteArrayOf(64, 0)
        val info = AttributeStackMapTableInfo.StackMapFrameInfo.SameLocals1StackItemFrameInfo.fromBytes(bytes, 0)
        assertEquals(64u, info.frameType)
        assertEquals(0u, info.offsetDelta)
        assertEquals<List<Byte>>(listOf(64, 0), info.bytes.toList())
    }

    @Test
    fun testSameLocals1StackItemFrameExtendedInfo() {
        val stack = AttributeStackMapTableInfo.VerificationTypeList(mutableListOf(0u))
        val info = AttributeStackMapTableInfo.StackMapFrameInfo.SameLocals1StackItemFrameExtendedInfo(64u, stack)
        assertEquals(247u, info.frameType)
        assertEquals(64u, info.offsetDelta)
        assertEquals(listOf(247u.toByte(), 0, 64, 0), info.bytes.toList())
    }

    @Test
    fun testSameLocals1StackItemFrameExtendedInfoFromStream() {
        val stream = byteArrayOf(0, 64, 0).dataStream()
        val info =
            AttributeStackMapTableInfo.StackMapFrameInfo.SameLocals1StackItemFrameExtendedInfo.fromStream(247u, stream)
        assertEquals(247u, info.frameType)
        assertEquals(64u, info.offsetDelta)
        assertEquals(listOf(247u.toByte(), 0, 64, 0), info.bytes.toList())
    }

    @Test
    fun testSameLocals1StackItemFrameExtendedInfoFromStream2() {
        val stream = byteArrayOf(247u.toByte(), 0, 64, 0).dataStream()
        val info = AttributeStackMapTableInfo.StackMapFrameInfo.SameLocals1StackItemFrameExtendedInfo.fromStream(stream)
        assertEquals(247u, info.frameType)
        assertEquals(64u, info.offsetDelta)
        assertEquals(listOf(247u.toByte(), 0, 64, 0), info.bytes.toList())
    }

    @Test
    fun testSameLocals1StackItemFrameExtendedInfoFromBytes() {
        val bytes = byteArrayOf(0, 64, 0)
        val info =
            AttributeStackMapTableInfo.StackMapFrameInfo.SameLocals1StackItemFrameExtendedInfo.fromBytes(247u, bytes)
        assertEquals(247u, info.frameType)
        assertEquals(64u, info.offsetDelta)
        assertEquals(listOf(247u.toByte(), 0, 64, 0), info.bytes.toList())
    }

    @Test
    fun testSameLocals1StackItemFrameExtendedInfoFromBytesOffset() {
        val bytes = byteArrayOf(0, 0, 64, 0)
        val info =
            AttributeStackMapTableInfo.StackMapFrameInfo.SameLocals1StackItemFrameExtendedInfo.fromBytes(247u, bytes, 1)
        assertEquals(247u, info.frameType)
        assertEquals(64u, info.offsetDelta)
        assertEquals(listOf(247u.toByte(), 0, 64, 0), info.bytes.toList())
    }

    @Test
    fun testSameLocals1StackItemFrameExtendedInfoFromBytes2() {
        val bytes = byteArrayOf(247u.toByte(), 0, 64, 0)
        val info = AttributeStackMapTableInfo.StackMapFrameInfo.SameLocals1StackItemFrameExtendedInfo.fromBytes(bytes)
        assertEquals(247u, info.frameType)
        assertEquals(64u, info.offsetDelta)
        assertEquals(listOf(247u.toByte(), 0, 64, 0), info.bytes.toList())
    }

    @Test
    fun testSameLocals1StackItemFrameExtendedInfoFromBytes2Offset() {
        val bytes = byteArrayOf(0, 247u.toByte(), 0, 64, 0)
        val info =
            AttributeStackMapTableInfo.StackMapFrameInfo.SameLocals1StackItemFrameExtendedInfo.fromBytes(bytes, 1)
        assertEquals(247u, info.frameType)
        assertEquals(64u, info.offsetDelta)
        assertEquals(listOf(247u.toByte(), 0, 64, 0), info.bytes.toList())
    }

    @Test
    fun testChopFrameInfo() {
        val info = AttributeStackMapTableInfo.StackMapFrameInfo.ChopFrameInfo(248u, 0x0f0au)
        assertEquals(248u, info.frameType)
        assertEquals(0x0F0au, info.offsetDelta)
        assertEquals(listOf(248u.toByte(), 0x0F, 0x0a), info.bytes.toList())
    }

    @Test
    fun testChopFrameInfoFromStream() {
        val stream = byteArrayOf(0x0F, 0x0a).dataStream()
        val info = AttributeStackMapTableInfo.StackMapFrameInfo.ChopFrameInfo.fromStream(248u, stream)
        assertEquals(248u, info.frameType)
        assertEquals(0x0F0au, info.offsetDelta)
        assertEquals(listOf(248u.toByte(), 0x0F, 0x0a), info.bytes.toList())
    }

    @Test
    fun testChopFrameInfoFromStream2() {
        val stream = byteArrayOf(248u.toByte(), 0x0F, 0x0a).dataStream()
        val info = AttributeStackMapTableInfo.StackMapFrameInfo.ChopFrameInfo.fromStream(stream)
        assertEquals(248u, info.frameType)
        assertEquals(0x0F0au, info.offsetDelta)
        assertEquals(listOf(248u.toByte(), 0x0F, 0x0a), info.bytes.toList())
    }

    @Test
    fun testChopFrameInfoFromBytes() {
        val bytes = byteArrayOf(0x0F, 0x0a)
        val info = AttributeStackMapTableInfo.StackMapFrameInfo.ChopFrameInfo.fromBytes(248u, bytes)
        assertEquals(248u, info.frameType)
        assertEquals(0x0F0au, info.offsetDelta)
        assertEquals(listOf(248u.toByte(), 0x0F, 0x0a), info.bytes.toList())
    }

    @Test
    fun testChopFrameInfoFromBytesOffset() {
        val bytes = byteArrayOf(0, 0x0F, 0x0a)
        val info = AttributeStackMapTableInfo.StackMapFrameInfo.ChopFrameInfo.fromBytes(248u, bytes, 1)
        assertEquals(248u, info.frameType)
        assertEquals(0x0F0au, info.offsetDelta)
        assertEquals(listOf(248u.toByte(), 0x0F, 0x0a), info.bytes.toList())
    }

    @Test
    fun testChopFrameInfoFromBytes2() {
        val bytes = byteArrayOf(248u.toByte(), 0x0F, 0x0a)
        val info = AttributeStackMapTableInfo.StackMapFrameInfo.ChopFrameInfo.fromBytes(bytes)
        assertEquals(248u, info.frameType)
        assertEquals(0x0F0au, info.offsetDelta)
        assertEquals(listOf(248u.toByte(), 0x0F, 0x0a), info.bytes.toList())
    }

    @Test
    fun testChopFrameInfoFromBytes2Offset() {
        val bytes = byteArrayOf(0, 248u.toByte(), 0x0F, 0x0a)
        val info = AttributeStackMapTableInfo.StackMapFrameInfo.ChopFrameInfo.fromBytes(bytes, 1)
        assertEquals(248u, info.frameType)
        assertEquals(0x0F0au, info.offsetDelta)
        assertEquals(listOf(248u.toByte(), 0x0F, 0x0a), info.bytes.toList())
    }

    @Test
    fun testSameFrameExtendedInfo() {
        val info = AttributeStackMapTableInfo.StackMapFrameInfo.SameFrameExtendedInfo(0x0f0au)
        assertEquals(251u, info.frameType)
        assertEquals(0x0f0au, info.offsetDelta)
        assertEquals(listOf(251u.toByte(), 0x0f, 0x0a), info.bytes.toList())
    }

    @Test
    fun testAppendFrameInfo() {
        val list = AttributeStackMapTableInfo.VerificationTypeList(mutableListOf(0u))
        val info = AttributeStackMapTableInfo.StackMapFrameInfo.AppendFrameInfo(252u, 0x0f0au, list)
        assertEquals(252u, info.frameType)
        assertEquals(0x0f0au, info.offsetDelta)
        assertEquals(list, info.locals)
        assertEquals(listOf(252u.toByte(), 0x0f, 0x0a, 0), info.bytes.toList())
    }

    @Test
    fun testAppendFrameInfoFromStream() {
        val stream = byteArrayOf(0x0f, 0x0a, 0).dataStream()
        val info = AttributeStackMapTableInfo.StackMapFrameInfo.AppendFrameInfo.fromStream(252u, stream)
        assertEquals(252u, info.frameType)
        assertEquals(0x0f0au, info.offsetDelta)
        assertEquals(1, info.locals.size)
        assertEquals(0u, info.locals[0])
        assertEquals(listOf(252u.toByte(), 0x0f, 0x0a, 0), info.bytes.toList())
    }

    @Test
    fun testAppendFrameInfoFromStream2() {
        val stream = byteArrayOf(252u.toByte(), 0x0f, 0x0a, 0).dataStream()
        val info = AttributeStackMapTableInfo.StackMapFrameInfo.AppendFrameInfo.fromStream(stream)
        assertEquals(252u, info.frameType)
        assertEquals(0x0f0au, info.offsetDelta)
        assertEquals(1, info.locals.size)
        assertEquals(0u, info.locals[0])
        assertEquals(listOf(252u.toByte(), 0x0f, 0x0a, 0), info.bytes.toList())
    }

    @Test
    fun testAppendFrameInfoFromBytes() {
        val bytes = byteArrayOf(0x0f, 0x0a, 0)
        val info = AttributeStackMapTableInfo.StackMapFrameInfo.AppendFrameInfo.fromBytes(252u, bytes)
        assertEquals(252u, info.frameType)
        assertEquals(0x0f0au, info.offsetDelta)
        assertEquals(1, info.locals.size)
        assertEquals(0u, info.locals[0])
        assertEquals(listOf(252u.toByte(), 0x0f, 0x0a, 0), info.bytes.toList())
    }

    @Test
    fun testAppendFrameInfoFromBytesOffset() {
        val bytes = byteArrayOf(0, 0x0f, 0x0a, 0)
        val info = AttributeStackMapTableInfo.StackMapFrameInfo.AppendFrameInfo.fromBytes(252u, bytes, 1)
        assertEquals(252u, info.frameType)
        assertEquals(0x0f0au, info.offsetDelta)
        assertEquals(1, info.locals.size)
        assertEquals(0u, info.locals[0])
        assertEquals(listOf(252u.toByte(), 0x0f, 0x0a, 0), info.bytes.toList())
    }

    @Test
    fun testAppendFrameInfoFromBytes2() {
        val bytes = byteArrayOf(252u.toByte(), 0x0f, 0x0a, 0)
        val info = AttributeStackMapTableInfo.StackMapFrameInfo.AppendFrameInfo.fromBytes(bytes)
        assertEquals(252u, info.frameType)
        assertEquals(0x0f0au, info.offsetDelta)
        assertEquals(1, info.locals.size)
        assertEquals(0u, info.locals[0])
        assertEquals(listOf(252u.toByte(), 0x0f, 0x0a, 0), info.bytes.toList())
    }

    @Test
    fun testAppendFrameInfoFromBytes2Offset() {
        val bytes = byteArrayOf(0, 252u.toByte(), 0x0f, 0x0a, 0)
        val info = AttributeStackMapTableInfo.StackMapFrameInfo.AppendFrameInfo.fromBytes(bytes, 1)
        assertEquals(252u, info.frameType)
        assertEquals(0x0f0au, info.offsetDelta)
        assertEquals(1, info.locals.size)
        assertEquals(0u, info.locals[0])
        assertEquals(listOf(252u.toByte(), 0x0f, 0x0a, 0), info.bytes.toList())
    }

    @Test
    fun testFullFrameInfo() {
        val list = AttributeStackMapTableInfo.VerificationTypeList(mutableListOf(0u))
        val list2 = AttributeStackMapTableInfo.VerificationTypeList(mutableListOf(2u))
        val info = AttributeStackMapTableInfo.StackMapFrameInfo.FullFrameInfo(0x0f0au, 1u, 1u, list, list2)
        assertEquals(255u, info.frameType)
        assertEquals(0x0f0au, info.offsetDelta)
        assertEquals(1u, info.numberOfLocals)
        assertEquals(1u, info.numberOfStackItems)
        assertEquals(list, info.locals)
        assertEquals(list2, info.stack)
        assertEquals(listOf(255u.toByte(), 0x0f, 0x0a, 0, 1, 0, 0, 1, 2), info.bytes.toList())
    }

    @Test
    fun testFullFrameInfoFromStream() {
        val stream = byteArrayOf(0x0f, 0x0a, 0, 1, 0, 0, 1, 2).dataStream()
        val info = AttributeStackMapTableInfo.StackMapFrameInfo.FullFrameInfo.fromStream(255u, stream)
        assertEquals(255u, info.frameType)
        assertEquals(0x0f0au, info.offsetDelta)
        assertEquals(1u, info.numberOfLocals)
        assertEquals(1u, info.numberOfStackItems)
        assertEquals(listOf(0.toByte()), info.locals.bytes.toList())
        assertEquals(listOf(2.toByte()), info.stack.bytes.toList())
        assertEquals(listOf(255u.toByte(), 0x0f, 0x0a, 0, 1, 0, 0, 1, 2), info.bytes.toList())
    }

    @Test
    fun testFullFrameInfoFromStream2() {
        val stream = byteArrayOf(255u.toByte(), 0x0f, 0x0a, 0, 1, 0, 0, 1, 2).dataStream()
        val info = AttributeStackMapTableInfo.StackMapFrameInfo.FullFrameInfo.fromStream(stream)
        assertEquals(255u, info.frameType)
        assertEquals(0x0f0au, info.offsetDelta)
        assertEquals(1u, info.numberOfLocals)
        assertEquals(1u, info.numberOfStackItems)
        assertEquals(listOf(0.toByte()), info.locals.bytes.toList())
        assertEquals(listOf(2.toByte()), info.stack.bytes.toList())
        assertEquals(listOf(255u.toByte(), 0x0f, 0x0a, 0, 1, 0, 0, 1, 2), info.bytes.toList())
    }

    @Test
    fun testFullFrameInfoFromBytes() {
        val bytes = byteArrayOf(255u.toByte(), 0x0f, 0x0a, 0, 1, 0, 0, 1, 2)
        val info = AttributeStackMapTableInfo.StackMapFrameInfo.FullFrameInfo.fromBytes(bytes)
        assertEquals(255u, info.frameType)
        assertEquals(0x0f0au, info.offsetDelta)
        assertEquals(1u, info.numberOfLocals)
        assertEquals(1u, info.numberOfStackItems)
        assertEquals(listOf(0.toByte()), info.locals.bytes.toList())
        assertEquals(listOf(2.toByte()), info.stack.bytes.toList())
        assertEquals(listOf(255u.toByte(), 0x0f, 0x0a, 0, 1, 0, 0, 1, 2), info.bytes.toList())
    }

    @Test
    fun testFullFrameInfoFromBytesWithOffset() {
        val bytes = byteArrayOf(0, 255u.toByte(), 0x0f, 0x0a, 0, 1, 0, 0, 1, 2)
        val info = AttributeStackMapTableInfo.StackMapFrameInfo.FullFrameInfo.fromBytes(bytes, 1)
        assertEquals(255u, info.frameType)
        assertEquals(0x0f0au, info.offsetDelta)
        assertEquals(1u, info.numberOfLocals)
        assertEquals(1u, info.numberOfStackItems)
        assertEquals(listOf(0.toByte()), info.locals.bytes.toList())
        assertEquals(listOf(2.toByte()), info.stack.bytes.toList())
        assertEquals(listOf(255u.toByte(), 0x0f, 0x0a, 0, 1, 0, 0, 1, 2), info.bytes.toList())
    }

    @Test
    fun testFullFrameInfoFromBytes2() {
        val bytes = byteArrayOf(255u.toByte(), 0x0f, 0x0a, 0, 1, 0, 0, 1, 2)
        val info = AttributeStackMapTableInfo.StackMapFrameInfo.FullFrameInfo.fromBytes(bytes)
        assertEquals(255u, info.frameType)
        assertEquals(0x0f0au, info.offsetDelta)
        assertEquals(1u, info.numberOfLocals)
        assertEquals(1u, info.numberOfStackItems)
        assertEquals(listOf(0.toByte()), info.locals.bytes.toList())
        assertEquals(listOf(2.toByte()), info.stack.bytes.toList())
        assertEquals(listOf(255u.toByte(), 0x0f, 0x0a, 0, 1, 0, 0, 1, 2), info.bytes.toList())
    }

    @Test
    fun testFullFrameInfoFromBytesWithOffset2() {
        val bytes = byteArrayOf(0, 255u.toByte(), 0x0f, 0x0a, 0, 1, 0, 0, 1, 2)
        val info = AttributeStackMapTableInfo.StackMapFrameInfo.FullFrameInfo.fromBytes(bytes, 1)
        assertEquals(255u, info.frameType)
        assertEquals(0x0f0au, info.offsetDelta)
        assertEquals(1u, info.numberOfLocals)
        assertEquals(1u, info.numberOfStackItems)
        assertEquals(listOf(0.toByte()), info.locals.bytes.toList())
        assertEquals(listOf(2.toByte()), info.stack.bytes.toList())
        assertEquals(listOf(255u.toByte(), 0x0f, 0x0a, 0, 1, 0, 0, 1, 2), info.bytes.toList())
    }

    @Test
    fun testStackMapFrameInfoFromStreamCreatingSameFrameInfo() {
        val stream = byteArrayOf(0).dataStream()
        val info = AttributeStackMapTableInfo.StackMapFrameInfo.fromStream(stream)
        assertEquals(0u, info.frameType)
        assertEquals<List<Byte>>(listOf(0), info.bytes.toList())
    }

    @Test
    fun testStackMapFrameInfoFromStreamCreatingSameLocals1Info() {
        val stream = byteArrayOf(64, 0).dataStream()
        val info = AttributeStackMapTableInfo.StackMapFrameInfo.fromStream(stream)
        assertEquals(64u, info.frameType)
        assertEquals(0u, info.offsetDelta)
        assertEquals<List<Byte>>(listOf(64, 0), info.bytes.toList())
    }

    @Test
    fun testStackMapFrameInfoFromStreamCreatingSameLocals1StackItemFrameExtendedInfo() {
        val stream = byteArrayOf(247u.toByte(), 0x0f, 0x0a, 0).dataStream()
        val info = AttributeStackMapTableInfo.StackMapFrameInfo.fromStream(stream)
        assertEquals(247u, info.frameType)
        assertEquals(0x0f0au, info.offsetDelta)
        assertEquals(listOf(247u.toByte(), 0x0f, 0x0a, 0), info.bytes.toList())
    }

    @Test
    fun testStackMapFrameInfoFromStreamCreatingChopFrameInfo() {
        val stream = byteArrayOf(248.toByte(), 0x0f, 0x0a).dataStream()
        val info = AttributeStackMapTableInfo.StackMapFrameInfo.fromStream(stream)
        assertEquals(248u, info.frameType)
        assertEquals(0x0f0au, info.offsetDelta)
        assertEquals(listOf(248u.toByte(), 0x0f, 0x0a), info.bytes.toList())
    }

    @Test
    fun testStackMapFrameInfoFromStreamCreatingSameFrameExtendedInfo() {
        val stream = byteArrayOf(251.toByte(), 0x0f, 0x0a).dataStream()
        val info = AttributeStackMapTableInfo.StackMapFrameInfo.fromStream(stream)
        assertEquals(251u, info.frameType)
        assertEquals(0x0f0au, info.offsetDelta)
        assertEquals(listOf(251u.toByte(), 0x0f, 0x0a), info.bytes.toList())
    }

    @Test
    fun testStackMapFrameInfoFromStreamCreatingAppendFrameInfo() {
        val stream = byteArrayOf(252u.toByte(), 0x0f, 0x0a, 0).dataStream()
        val info = AttributeStackMapTableInfo.StackMapFrameInfo.fromStream(stream)
        assertEquals(252u, info.frameType)
        assertEquals(0x0f0au, info.offsetDelta)
        assertEquals(listOf(252u.toByte(), 0x0f, 0x0a, 0), info.bytes.toList())
    }

    @Test
    fun testStackMapFrameInfoFromStreamCreatingFullFrameInfo() {
        val stream = byteArrayOf(255u.toByte(), 0x0f, 0x0a, 0, 1, 0, 0, 1, 2).dataStream()
        val info = AttributeStackMapTableInfo.StackMapFrameInfo.fromStream(stream)
        assertEquals(255u, info.frameType)
        assertEquals(0x0f0au, info.offsetDelta)
        assertEquals(listOf(255u.toByte(), 0x0f, 0x0a, 0, 1, 0, 0, 1, 2), info.bytes.toList())
    }

    @Test
    fun testStackMapFrameInfoFromBytesCreatingSameFrameInfo() {
        val bytes = byteArrayOf(0)
        val info = AttributeStackMapTableInfo.StackMapFrameInfo.fromBytes(bytes)
        assertEquals(0u, info.frameType)
        assertEquals<List<Byte>>(listOf(0), info.bytes.toList())
    }

    @Test
    fun testStackMapFrameInfoFromBytesCreatingSameLocals1Info() {
        val bytes = byteArrayOf(64, 0)
        val info = AttributeStackMapTableInfo.StackMapFrameInfo.fromBytes(bytes)
        assertEquals(64u, info.frameType)
        assertEquals(0u, info.offsetDelta)
        assertEquals<List<Byte>>(listOf(64, 0), info.bytes.toList())
    }

    @Test
    fun testStackMapFrameInfoFromBytesCreatingSameLocals1StackItemFrameExtendedInfo() {
        val bytes = byteArrayOf(247u.toByte(), 0x0f, 0x0a, 0)
        val info = AttributeStackMapTableInfo.StackMapFrameInfo.fromBytes(bytes)
        assertEquals(247u, info.frameType)
        assertEquals(0x0f0au, info.offsetDelta)
        assertEquals(listOf(247u.toByte(), 0x0f, 0x0a, 0), info.bytes.toList())
    }

    @Test
    fun testStackMapFrameInfoFromBytesCreatingChopFrameInfo() {
        val bytes = byteArrayOf(248.toByte(), 0x0f, 0x0a)
        val info = AttributeStackMapTableInfo.StackMapFrameInfo.fromBytes(bytes)
        assertEquals(248u, info.frameType)
        assertEquals(0x0f0au, info.offsetDelta)
        assertEquals(listOf(248u.toByte(), 0x0f, 0x0a), info.bytes.toList())
    }

    @Test
    fun testStackMapFrameInfoFromBytesCreatingSameFrameExtendedInfo() {
        val bytes = byteArrayOf(251.toByte(), 0x0f, 0x0a)
        val info = AttributeStackMapTableInfo.StackMapFrameInfo.fromBytes(bytes)
        assertEquals(251u, info.frameType)
        assertEquals(0x0f0au, info.offsetDelta)
        assertEquals(listOf(251u.toByte(), 0x0f, 0x0a), info.bytes.toList())
    }

    @Test
    fun testStackMapFrameInfoFromBytesCreatingAppendFrameInfo() {
        val bytes = byteArrayOf(252u.toByte(), 0x0f, 0x0a, 0)
        val info = AttributeStackMapTableInfo.StackMapFrameInfo.fromBytes(bytes)
        assertEquals(252u, info.frameType)
        assertEquals(0x0f0au, info.offsetDelta)
        assertEquals(listOf(252u.toByte(), 0x0f, 0x0a, 0), info.bytes.toList())
    }

    @Test
    fun testStackMapFrameInfoFromBytesCreatingFullFrameInfo() {
        val bytes = byteArrayOf(255u.toByte(), 0x0f, 0x0a, 0, 1, 0, 0, 1, 2)
        val info = AttributeStackMapTableInfo.StackMapFrameInfo.fromBytes(bytes)
        assertEquals(255u, info.frameType)
        assertEquals(0x0f0au, info.offsetDelta)
        assertEquals(listOf(255u.toByte(), 0x0f, 0x0a, 0, 1, 0, 0, 1, 2), info.bytes.toList())
    }

    @Test
    fun testStackMapFrameInfoFromBytesWithOffsetCreatingSameFrameInfo() {
        val bytes = byteArrayOf(0, 0)
        val info = AttributeStackMapTableInfo.StackMapFrameInfo.fromBytes(bytes, 1)
        assertEquals(0u, info.frameType)
        assertEquals<List<Byte>>(listOf(0), info.bytes.toList())
    }

    @Test
    fun testStackMapFrameInfoFromBytesWithOffsetCreatingSameLocals1Info() {
        val bytes = byteArrayOf(0, 64, 0)
        val info = AttributeStackMapTableInfo.StackMapFrameInfo.fromBytes(bytes, 1)
        assertEquals(64u, info.frameType)
        assertEquals(0u, info.offsetDelta)
        assertEquals<List<Byte>>(listOf(64, 0), info.bytes.toList())
    }

    @Test
    fun testStackMapFrameInfoFromBytesWithOffsetCreatingSameLocals1StackItemFrameExtendedInfo() {
        val bytes = byteArrayOf(0, 247u.toByte(), 0x0f, 0x0a, 0)
        val info = AttributeStackMapTableInfo.StackMapFrameInfo.fromBytes(bytes, 1)
        assertEquals(247u, info.frameType)
        assertEquals(0x0f0au, info.offsetDelta)
        assertEquals(listOf(247u.toByte(), 0x0f, 0x0a, 0), info.bytes.toList())
    }

    @Test
    fun testStackMapFrameInfoFromBytesWithOffsetCreatingChopFrameInfo() {
        val bytes = byteArrayOf(0, 248.toByte(), 0x0f, 0x0a)
        val info = AttributeStackMapTableInfo.StackMapFrameInfo.fromBytes(bytes, 1)
        assertEquals(248u, info.frameType)
        assertEquals(0x0f0au, info.offsetDelta)
        assertEquals(listOf(248u.toByte(), 0x0f, 0x0a), info.bytes.toList())
    }

    @Test
    fun testStackMapFrameInfoFromBytesWithOffsetCreatingSameFrameExtendedInfo() {
        val bytes = byteArrayOf(0, 251.toByte(), 0x0f, 0x0a)
        val info = AttributeStackMapTableInfo.StackMapFrameInfo.fromBytes(bytes, 1)
        assertEquals(251u, info.frameType)
        assertEquals(0x0f0au, info.offsetDelta)
        assertEquals(listOf(251u.toByte(), 0x0f, 0x0a), info.bytes.toList())
    }

    @Test
    fun testStackMapFrameInfoFromBytesWithOffsetCreatingAppendFrameInfo() {
        val bytes = byteArrayOf(0, 252u.toByte(), 0x0f, 0x0a, 0)
        val info = AttributeStackMapTableInfo.StackMapFrameInfo.fromBytes(bytes, 1)
        assertEquals(252u, info.frameType)
        assertEquals(0x0f0au, info.offsetDelta)
        assertEquals(listOf(252u.toByte(), 0x0f, 0x0a, 0), info.bytes.toList())
    }

    @Test
    fun testStackMapFrameInfoFromBytesWithOffsetCreatingFullFrameInfo() {
        val bytes = byteArrayOf(0, 255u.toByte(), 0x0f, 0x0a, 0, 1, 0, 0, 1, 2)
        val info = AttributeStackMapTableInfo.StackMapFrameInfo.fromBytes(bytes, 1)
        assertEquals(255u, info.frameType)
        assertEquals(0x0f0au, info.offsetDelta)
        assertEquals(listOf(255u.toByte(), 0x0f, 0x0a, 0, 1, 0, 0, 1, 2), info.bytes.toList())
    }

    @Test
    fun testStackMapFrameListFromStream() {
        val bytes = byteArrayOf(
            0, 2,
            255u.toByte(), 0x0f, 0x0a, 0, 1, 0, 0, 1, 2,
            0,
        )
        val stream = bytes.dataStream()
        val list = AttributeStackMapTableInfo.StackMapFrameList.fromStream(stream)
        assertEquals(2, list.size)
        assertEquals(bytes.toList(), list.bytes.toList())
    }

    @Test
    fun testStackMapFrameListFromBytes() {
        val bytes = byteArrayOf(
            0, 2,
            255u.toByte(), 0x0f, 0x0a, 0, 1, 0, 0, 1, 2,
            0,
        )
        val list = AttributeStackMapTableInfo.StackMapFrameList.fromBytes(bytes)
        assertEquals(2, list.size)
        assertEquals(bytes.toList(), list.bytes.toList())
    }

    @Test
    fun testStackMapFrameListFromBytesWithOffset() {
        val bytes = byteArrayOf(
            *(1..10).map { (it % 255).toUByte().toByte() }.toByteArray(),
            0, 2,
            255u.toByte(), 0x0f, 0x0a, 0, 1, 0, 0, 1, 2,
            0,
        )
        val list = AttributeStackMapTableInfo.StackMapFrameList.fromBytes(bytes, 10)
        assertEquals(2, list.size)
        assertEquals(bytes.toList().subList(10, bytes.size), list.bytes.toList())
    }

    @Test
    fun testContentsFromStream() {
        val const = Constant.utf8("StackMapTable")
        val pool = ConstantPool(arrayOf(const))

        val stream = byteArrayOf(0, 2, 255u.toByte(), 0x0f, 0x0a, 0, 1, 0, 0, 1, 2, 0).dataStream()
        val info = AttributeStackMapTableInfo.contentsFromStream(stream, const)
        assertEquals(2, info.entries.size)
        assertEquals(const, info.name)
        assertEquals(byteArrayOf(0, 2, 255u.toByte(), 0x0f, 0x0a, 0, 1, 0, 0, 1, 2, 0).toList(), info.bytes.toList())
    }

    @Test
    fun testContentsFromBytes() {
        val const = Constant.utf8("StackMapTable")
        val pool = ConstantPool(arrayOf(const))

        val bytes = byteArrayOf(0, 2, 255u.toByte(), 0x0f, 0x0a, 0, 1, 0, 0, 1, 2, 0)
        val info = AttributeStackMapTableInfo.contentsFromBytes(bytes, const)
        assertEquals(2, info.entries.size)
        assertEquals(const, info.name)
        assertEquals(bytes.toList(), info.bytes.toList())
    }

    @Test
    fun testContentsFromBytesWithOffset() {
        val const = Constant.utf8("StackMapTable")
        val pool = ConstantPool(arrayOf(const))

        val bytes = byteArrayOf(
            *(1..10).map { (it % 255).toUByte().toByte() }.toByteArray(),
            0, 2, 255u.toByte(), 0x0f, 0x0a, 0, 1, 0, 0, 1, 2, 0,
        )
        val info = AttributeStackMapTableInfo.contentsFromBytes(bytes, const, 10)
        assertEquals(2, info.entries.size)
        assertEquals(const, info.name)
        assertEquals(bytes.toList().subList(10, bytes.size), info.bytes.toList())
    }

    @Test
    fun testFromStream() {
        val const = Constant.utf8("StackMapTable")
        val pool = ConstantPool(arrayOf(const))
        val bytes = byteArrayOf(0, 1, 0, 0, 0, 12, 0, 2, 255u.toByte(), 0x0f, 0x0a, 0, 1, 0, 0, 1, 2, 0)
        val stream = bytes.dataStream()
        val info = AttributeStackMapTableInfo.fromStream(pool, stream)
        assertEquals(2, info.entries.size)
        assertEquals(const, info.name)
        assertEquals(bytes.toList().subList(6, bytes.size), info.bytes.toList())
        assertEquals(bytes.toList(), info.toBytes().toList())
    }

    @Test
    fun testFromBytes() {
        val const = Constant.utf8("StackMapTable")
        val pool = ConstantPool(arrayOf(const))
        val bytes = byteArrayOf(0, 1, 0, 0, 0, 12, 0, 2, 255u.toByte(), 0x0f, 0x0a, 0, 1, 0, 0, 1, 2, 0)
        val info = AttributeStackMapTableInfo.fromBytes(pool, bytes)
        assertEquals(2, info.entries.size)
        assertEquals(const, info.name)
        assertEquals(bytes.toList().subList(6, bytes.size), info.bytes.toList())
        assertEquals(bytes.toList(), info.toBytes().toList())
    }

    @Test
    fun testFromBytesWithOffset() {
        val const = Constant.utf8("StackMapTable")
        val pool = ConstantPool(arrayOf(const))
        val bytes = byteArrayOf(
            *(1..10).map { (it % 255).toUByte().toByte() }.toByteArray(),
            0, 1, 0, 0, 0, 12, 0, 2, 255u.toByte(), 0x0f, 0x0a, 0, 1, 0, 0, 1, 2, 0,
        )
        val info = AttributeStackMapTableInfo.fromBytes(pool, bytes, 10)
        assertEquals(2, info.entries.size)
        assertEquals(const, info.name)
        assertEquals(bytes.toList().subList(16, bytes.size), info.bytes.toList())
        assertEquals(bytes.toList().subList(10, bytes.size), info.toBytes().toList())
    }
}
