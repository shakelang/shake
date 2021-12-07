package io.github.shakelang.jvmlib.constants

class JavaClassIntegerConstant(val value: Int) : JavaClassConstant() {

    override val tag: Byte get() = 3
    override fun toJson() = super.toJson().with("value", value)
    override val name: String get() = "JavaClassIntegerConstant"

}