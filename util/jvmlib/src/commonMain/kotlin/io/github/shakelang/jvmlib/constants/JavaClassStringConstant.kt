package io.github.shakelang.jvmlib.constants

class JavaClassStringConstant(val value: String) : JavaClassConstant() {

    override val tag: Byte get() = 1
    override fun toJson() = super.toJson().with("value", value)
    override val name: String get() = "JavaClassStringConstant"

}