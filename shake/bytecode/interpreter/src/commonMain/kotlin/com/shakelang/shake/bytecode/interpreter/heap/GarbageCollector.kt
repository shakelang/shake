package com.shakelang.shake.bytecode.interpreter.heap

import com.shakelang.shake.bytecode.interpreter.ByteStack
import com.shakelang.shake.bytecode.interpreter.ShakeInterpreter
import com.shakelang.util.primitives.bytes.getLong

class GarbageCollector(
    val malloc: Malloc,
    val interpreter: ShakeInterpreter,
) {
    val globalMemory = malloc.globalMemory

    fun scan(localMemory: ByteArray) {
        var sp = 0
        while (sp < localMemory.size - 7) {
            val pointer = localMemory.getLong(sp) - 16
            sp++
            if (!GlobalMemory.isPointer(pointer)) continue

            val header = try {
                malloc.readHeader(pointer)
            } catch (e: OutOfRangeException) {
                continue
            }

            // Invalid header
            if (!header.check()) continue

            // Already marked
            if (header.isMarked) continue

            header.mark()
            malloc.writeHeader(pointer, header)
        }
    }

    fun scan(stack: ByteStack) = scan(stack.toByteArray())

    fun scanLocalReferences() {
        this.interpreter.callStack.forEach {
            scan(it.locals)
            scan(it.stack)
        }
    }

    fun scanRegion(start: Long, end: Long): Boolean {
        var changed = false
        var sp = start / 8 * 8
        while (sp < end) {
            val p = sp
            sp += 8
            if (globalMemory.isPointer(p)) {
                val pointer = globalMemory.getPointer(p) - malloc.headerSize

                val header = try {
                    malloc.readHeader(pointer)
                } catch (e: OutOfRangeException) {
                    continue
                }

                // Invalid header
                if (!header.check()) continue

                // Already marked
                if (header.isMarked) continue

                header.mark()
                malloc.writeHeader(pointer, header)

                changed = true
            }
        }

        return changed
    }

    fun scanBlock(header: MallocHeader, blockPointer: Long): Boolean {
        val start = blockPointer + malloc.headerSize
        val end = start + header.size
        header.isScanned = true
        malloc.writeHeader(blockPointer, header)
        return scanRegion(start, end)
    }

    fun scanBlock(blockPointer: Long): Boolean {
        val header = malloc.readHeader(blockPointer)
        return scanBlock(header, blockPointer)
    }

    fun scanBlocks(startPointer: Long): Boolean {
        var changed = false
        var pointer = startPointer
        while (pointer != -1L) {
            // For performance reasons, we give the header directly to the scanBlock function
            // instead of reading it again

            val header = malloc.readHeader(pointer)

            // skip if block is already scanned or not marked
            if (header.isScanned || !header.isMarked) {
                pointer = header.next
                continue
            }

            if (scanBlock(header, pointer) && !changed) changed = true
            pointer = header.next
        }
        return changed
    }

    fun mark(startPointer: Long) {
        var changed = true
        while (changed) {
            changed = scanBlocks(startPointer)
        }
    }

    fun unmark(pointer: Long, header: MallocHeader) {
        header.unmark()
        header.isScanned = false
        malloc.writeHeader(pointer, header)
    }

    fun unmark(pointer: Long) {
        unmark(pointer, malloc.readHeader(pointer))
    }

    fun unmarkBlocks(startPointer: Long) {
        var pointer = startPointer
        while (pointer != -1L) {
            // For performance reasons, we give the header directly to the unmark function
            // instead of reading it again
            val header = malloc.readHeader(pointer)
            unmark(pointer, header)
            pointer = header.next
        }
    }

    fun freeUnmarkedBlocks(startPointer: Long) {
        var pointer = startPointer
        var beforePointer = -1L
        var beforeHeader: MallocHeader? = null

        while (pointer != -1L) {
            val header = malloc.readHeader(pointer)
            if (!header.isMarked) {
                println("Freeing $pointer")
                malloc.free(pointer)
            } else {
                header.survived++
                malloc.writeHeader(pointer, header)

                // TODO: Move block to new level if it survived enough times

                beforePointer = pointer
                beforeHeader = header
            }
            pointer = header.next
        }
    }

    fun collect(startPointer: Long) {
        unmarkBlocks(startPointer)
        scanLocalReferences()
        mark(startPointer)
        freeUnmarkedBlocks(startPointer)
    }

    fun collect() = collect(malloc.usedStartPointer)
}
