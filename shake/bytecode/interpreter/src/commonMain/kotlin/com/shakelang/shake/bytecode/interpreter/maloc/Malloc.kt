package com.shakelang.shake.bytecode.interpreter.maloc

import com.shakelang.util.primitives.bytes.toBytes
import com.shakelang.util.primitives.bytes.toLong

class Malloc(
    val globalMemory: GlobalMemory,
) {
    var startPointer: Long = -1
    var tailPointer: Long = -1

    // Let's define some stuff
    // All our malloc objects have the following header:
    // struct malloc_header {
    //     uint64_t size;
    //     struct malloc_header* next;
    // };
    // As we are in a 64bit environment, our next pointer is 8 bytes long,
    // so overall our header is 16 bytes long
    val headerSize = 16

    // We are filling memory from the bottom to the top, so we need to know
    // what we used already. We prefer to recycle memory over increasing the
    // memory size, so we need to know what we used already
    var size: Long = 0
        private set

    fun readHeader(pointer: Long): MallocHeader {
        return MallocHeader(
            globalMemory.getBytes(pointer, 8).toLong(),
            globalMemory.getBytes(pointer + 8, 8).toLong(),
        )
    }

    fun writeHeader(pointer: Long, header: MallocHeader) {
        globalMemory.setBytes(pointer, header.size.toBytes())
        globalMemory.setBytes(pointer + 8, header.next.toBytes())
    }

    private fun searchForFreeSpace(size: Long): FreeHeaderSearchResult? {
        var pointer = startPointer
        var resultPreviousPointer = -1L
        var resultPointer = -1L
        var resultHeader = MallocHeader(0, 0)
        var previousPointer = -1L
        while (pointer != -1L) {
            val header = readHeader(pointer)
            if (header.size >= size) {
                // Free chunk is big enough.

                if (header.size == size) {
                    // This chunk is exactly the size we need.
                    // No need to search further.
                    return FreeHeaderSearchResult(pointer, previousPointer, header)
                }

                if (header.size < resultHeader.size || resultPointer == -1L) {
                    // This chunk is smaller than the previous best fit.
                    // But it is still big enough.
                    resultPointer = pointer
                    resultPreviousPointer = previousPointer
                    resultHeader = header
                }
            }

            previousPointer = pointer
            pointer = header.next
        }

        if (resultPointer == -1L) {
            // No free chunk was found.
            return null
        }

        return FreeHeaderSearchResult(resultPointer, resultPreviousPointer, resultHeader)
    }

    fun malloc(size: Long): Long {
        // We need to allocate a new chunk of memory.
        // We need to find a free chunk that is big enough.
        val result = searchForFreeSpace(size)
        if (result == null) {
            // No free chunk was found.
            // We need to allocate a new chunk.
            val newPointer = this.size
            this.size += size + headerSize

            return newPointer
        }

        // We found a free chunk.
        // TODO: Split the chunk if it is too big.

        // Update the previous chunk to point to the next chunk.
        val beforeHeader = readHeader(result.previousPointer)
        writeHeader(result.previousPointer, MallocHeader(beforeHeader.size, result.header.next))

        // Update the header of the chunk to be allocated.
        writeHeader(result.pointer, MallocHeader(size, -1))

        // If this is the first chunk, update the start pointer.
        if (result.pointer == startPointer) {
            startPointer = result.header.next
        }

        // Return the pointer to the chunk.
        return result.pointer + headerSize
    }

    fun free(pointer: Long) {
        // Append the chunk to the end of free chunks.
        val header = readHeader(tailPointer)
        writeHeader(tailPointer, MallocHeader(header.size, pointer - headerSize))

        // Update the tail pointer.
        tailPointer = pointer - headerSize

        // If the start pointer is not set, set it.
        if (startPointer == -1L) {
            startPointer = pointer - headerSize
        }
    }
}

data class MallocHeader(
    val size: Long,
    val next: Long,
)

private data class FreeHeaderSearchResult(
    val pointer: Long,
    val previousPointer: Long,
    val header: MallocHeader,
)
