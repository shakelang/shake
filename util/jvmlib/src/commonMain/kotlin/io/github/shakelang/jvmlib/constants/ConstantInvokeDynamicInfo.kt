package io.github.shakelang.jvmlib.constants

class ConstantInvokeDynamicInfo : CONSTANT {

    override val tag: Byte get() = ConstantInvokeDynamicInfo.tag
    override val type: String get() = name

    val byte0: Byte
    val byte1: Byte
    val byte2: Byte
    val byte3: Byte

    constructor(byte0: Byte, byte1: Byte, byte2: Byte, byte3: Byte) : super() {
        this.byte0 = byte0
        this.byte1 = byte1
        this.byte2 = byte2
        this.byte3 = byte3
    }

    constructor(bytes: ByteArray) : super() {
        if (bytes.size != 4) throw Error("Expecting to get 4 bytes!")
        byte0 = bytes[0]
        byte1 = bytes[1]
        byte2 = bytes[2]
        byte3 = bytes[3]
    }
    override fun toJson() = super.toJson().with("bytes", arrayOf(byte0, byte1, byte2, byte3))

    companion object {
        const val name = "ConstantInvokeDynamicInfo"
        const val tag = ConstantTags.CONSTANT_INVOKE_DYNAMIC
    }

}