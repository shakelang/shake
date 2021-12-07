package io.github.shakelang.jvmlib.constants

class CONSTANT_Double(val value: Double) : CONSTANT() {

    override val tag: Byte = 6
    override fun toJson() = super.toJson().with("value", value)
    override val type: String get() = "CONSTANT_Double"

}