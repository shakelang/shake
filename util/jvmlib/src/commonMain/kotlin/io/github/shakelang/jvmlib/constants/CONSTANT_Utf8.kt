package io.github.shakelang.jvmlib.constants

class CONSTANT_Utf8(val value: String) : CONSTANT() {

    override val tag: Byte get() = 1
    override fun toJson() = super.toJson().with("value", value)
    override val type: String get() = "CONSTANT_Utf8"

}