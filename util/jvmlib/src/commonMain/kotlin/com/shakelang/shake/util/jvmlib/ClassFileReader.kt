package com.shakelang.shake.util.jvmlib

import com.shakelang.util.io.streaming.input.CountingInputStream
import com.shakelang.util.io.streaming.input.DataInputStream
import com.shakelang.util.io.streaming.input.InputStream

object ClassFileReader {

    fun readClass(input: InputStream): com.shakelang.shake.util.jvmlib.infos.ClassInfo {
        val counter = CountingInputStream(input)
        val stream = DataInputStream(counter)

        try {
            return com.shakelang.shake.util.jvmlib.infos.ClassInfo.fromStream(stream)
        } catch (e: Throwable) {
            throw RuntimeException("Error at position 0x${counter.count.toString(16)} while parsing class", e)
        }
    }
}
