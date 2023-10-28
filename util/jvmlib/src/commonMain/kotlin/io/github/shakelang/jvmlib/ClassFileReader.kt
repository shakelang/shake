package io.github.shakelang.jvmlib

import io.github.shakelang.jvmlib.infos.ClassInfo
import io.github.shakelang.io.streaming.input.CountingInputStream
import io.github.shakelang.io.streaming.input.DataInputStream
import io.github.shakelang.io.streaming.input.InputStream

object ClassFileReader
{

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