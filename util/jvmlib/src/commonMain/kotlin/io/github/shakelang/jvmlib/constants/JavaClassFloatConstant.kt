package io.github.shakelang.jvmlib.constants

class JavaClassFloatConstant(val value: Float) : JavaClassConstant() {

    override val tag: Byte = 4
    override fun toJson() = super.toJson().with("value", value)
    override val name: String get() = "JavaClassFloatConstant"

}