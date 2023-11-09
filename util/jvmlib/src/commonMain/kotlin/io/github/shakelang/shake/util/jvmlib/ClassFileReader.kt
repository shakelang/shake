package io.github.shakelang.shake.util.jvmlib

import io.github.shakelang.shake.util.io.streaming.input.CountingInputStream
import io.github.shakelang.shake.util.io.streaming.input.DataInputStream
import io.github.shakelang.shake.util.io.streaming.input.InputStream
import io.github.shakelang.shake.util.jvmlib.infos.ClassInfo

object ClassFileReader {

    fun readClass(input: InputStream): ClassInfo {

        val counter = CountingInputStream(input)
        val stream = DataInputStream(counter)

        try {
            return ClassInfo.fromStream(stream)
        } catch (e: Throwable) {
            throw RuntimeException("Error at position 0x${counter.count.toString(16)} while parsing class", e)
        }

    }

}