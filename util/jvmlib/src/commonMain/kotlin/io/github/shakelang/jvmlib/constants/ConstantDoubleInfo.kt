package io.github.shakelang.jvmlib.constants

class ConstantDoubleInfo(val value: Double) : ConstantInfo() {

    override val tag: Byte get() = ConstantDoubleInfo.tag
    override val type: String get() = name
    override fun toJson() = super.toJson().with("value", value)

    companion object {
        const val name = "ConstantDoubleInfo"
        const val tag = ConstantTags.CONSTANT_DOUBLE
    }

}