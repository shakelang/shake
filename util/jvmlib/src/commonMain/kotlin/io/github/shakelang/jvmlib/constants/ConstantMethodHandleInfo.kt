package io.github.shakelang.jvmlib.constants

class ConstantMethodHandleInfo(val name: Byte, val index: Int) : CONSTANT() {

    override val tag: Byte = 15
    override fun toJson() = super.toJson().with("type", name).with("index", index)
    override val type: String get() = "CONSTANT_MethodHandle"

}