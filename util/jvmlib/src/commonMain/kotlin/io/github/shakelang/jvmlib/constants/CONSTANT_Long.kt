package io.github.shakelang.jvmlib.constants

class CONSTANT_Long(val value: Long) : CONSTANT() {

    override val tag: Byte = 5
    override fun toJson() = super.toJson().with("value", value)
    override val type: String get() = "CONSTANT_Long"

}