package com.shakelang.shake.util.io

import com.shakelang.shake.util.io.streaming.output.ByteArrayOutputStream
import com.shakelang.shake.util.io.streaming.output.DataOutputStream
import com.shakelang.shake.util.io.streaming.output.OutputStream

/**
 * An interface for objects that can be dumped to a OutputStreams
 */
interface DumpAble {

    /**
     * Dump the object to a [DataOutputStream]
     *
     * @param stream The [DataOutputStream] to dump the object to
     */
    fun dump(stream: DataOutputStream)

    /**
     * Dump the object to an [OutputStream]
     * @param stream The [OutputStream] to dump the object to
     */
    fun dump(stream: OutputStream) {
        dump(DataOutputStream(stream))
    }

    /**
     * Dump the object to an [ByteArray]
     *
     * @return The [ByteArray] with the dumped object
     */
    fun dump(): ByteArray {
        val stream = ByteArrayOutputStream()
        dump(stream)
        return stream.toByteArray()
    }
}
