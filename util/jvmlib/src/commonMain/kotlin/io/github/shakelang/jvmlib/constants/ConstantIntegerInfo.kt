package io.github.shakelang.jvmlib.constants

class ConstantIntegerInfo(val value: Int) : ConstantInfo() {

    override val tag: Byte get() = ConstantIntegerInfo.tag
    override val type: String get() = name

    override fun toJson() = super.toJson().with("value", value)

    companion object {
        const val name: String = "ConstantIntegerInfo"
        const val tag: Byte = ConstantTags.CONSTANT_INTEGER
    }

}