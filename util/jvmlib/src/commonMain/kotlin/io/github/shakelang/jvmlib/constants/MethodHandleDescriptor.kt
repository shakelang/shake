package io.github.shakelang.jvmlib.constants

class MethodHandleDescriptor(val type: Byte, val index: Int) : JavaClassConstant() {

    override val tag: Byte = 15
    override fun toJson() = super.toJson().with("type", type).with("index", index)
    override val name: String get() = "MethodHandleDescriptor"

}