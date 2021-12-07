package io.github.shakelang.jvmlib.constants

class ConstantLongInfo(val value: Long) : CONSTANT() {

    override val tag: Byte get() = ConstantLongInfo.tag
    override val type: String get() = name

    override fun toJson() = super.toJson().with("value", value)

    companion object {
        const val name = "ConstantLongInfo"
        const val tag = ConstantTags.CONSTANT_LONG
    }

}