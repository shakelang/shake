package io.github.shakelang.jvmlib.constants

class Dynamic(val value: Int) : ConstantInfo() {

    override val tag: Byte = 16
    override fun toJson() = super.toJson().with("value", value)
    override val type: String get() = "Dynamic"

}