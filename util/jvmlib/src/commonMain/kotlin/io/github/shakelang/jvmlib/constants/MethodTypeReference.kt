package io.github.shakelang.jvmlib.constants

class MethodTypeReference(val index: Int) : JavaClassConstant() {

    override val tag: Byte = 16
    override fun toJson() = super.toJson().with("index", index)
    override val name: String get() = "MethodTypeReference"

}