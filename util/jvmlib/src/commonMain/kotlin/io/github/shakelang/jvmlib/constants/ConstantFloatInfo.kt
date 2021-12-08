package io.github.shakelang.jvmlib.constants

class ConstantFloatInfo(val value: Float) : ConstantInfo() {

    override val tag: Byte get() = ConstantFloatInfo.tag
    override val type: String get() = name

    override fun toJson() = super.toJson().with("value", value)

    companion object {
        const val name = "ConstantFloatInfo"
        const val tag = ConstantTags.CONSTANT_FLOAT
    }

}