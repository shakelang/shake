package com.shakelang.util.jvmlib

import com.shakelang.util.io.streaming.input.bytes.CountingInputStream
import com.shakelang.util.io.streaming.input.bytes.DataInputStream
import com.shakelang.util.io.streaming.input.bytes.InputStream

object ClassFileReader {

    fun readClass(input: InputStream): com.shakelang.util.jvmlib.infos.ClassInfo {
        val counter = CountingInputStream(input)
        val stream = DataInputStream(counter)

        try {
            return com.shakelang.util.jvmlib.infos.ClassInfo.fromStream(stream)
        } catch (e: Throwable) {
            throw RuntimeException("Error at position 0x${counter.count.toString(16)} while parsing class", e)
        }
    }
}
