package com.shakelang.shake.util.jvmlib.infos.constants

import com.shakelang.shake.util.io.streaming.output.DataOutputStream

class ConstantNullPointer private constructor() : ConstantInfo() {
    override val tag: Byte get() = 0x00
    override val tagName: String get() = "null"
    override fun dump(out: DataOutputStream) {}
    override val index: UShort get() = 0u

    companion object {
        val INSTANCE = ConstantNullPointer()
    }
}
