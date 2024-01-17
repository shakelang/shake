package com.shakelang.shake.bytecode.interpreter.maloc

import com.shakelang.util.primitives.bytes.getInt

class Maloc(val globalMemory: GlobalMemory) {

    inner class Header(
        val start: Int,
    ) {
        val size: Int
            get() = globalMemory.memory.getInt(start)

        val end: Int
            get() = start + size
    }

    companion object {
        const val HEADER_SIZE = 16
    }
}
