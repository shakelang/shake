package io.github.shakelang.jvmlib.constants

class CONSTANT_String(private val value: Int) : CONSTANT() {

    override val tag: Byte = 8
    override fun toJson() = super.toJson().with("value", value)
    override val type: String get() = "CONSTANT_String"

}