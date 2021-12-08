package io.github.shakelang.jvmlib.constants

class ConstantUtf8Info(val value: String) : ConstantInfo() {

    override val tag: Byte get() = ConstantUtf8Info.tag
    override val type: String get() = name

    override fun toJson() = super.toJson().with("value", value)

    companion object {
        const val name = "ConstantUtf8Info"
        const val tag = ConstantTags.CONSTANT_STRING
    }

}