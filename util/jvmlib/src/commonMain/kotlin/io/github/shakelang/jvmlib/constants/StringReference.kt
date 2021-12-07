package io.github.shakelang.jvmlib.constants

class StringReference(private val value: Int) : JavaClassConstant() {

    override val tag: Byte = 8
    override fun toJson() = super.toJson().with("value", value)
    override val name: String get() = "StringReference"

}