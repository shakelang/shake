package io.github.shakelang.jvmlib.constants

class ConstantFloatInfo(val value: Float) : CONSTANT() {

    override val tag: Byte = 4
    override fun toJson() = super.toJson().with("value", value)
    override val type: String get() = "CONSTANT_Float"

}