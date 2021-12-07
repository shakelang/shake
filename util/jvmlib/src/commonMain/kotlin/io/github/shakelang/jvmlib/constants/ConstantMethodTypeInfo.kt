package io.github.shakelang.jvmlib.constants

class ConstantMethodTypeInfo(val index: Int) : CONSTANT() {
    
    override val tag: Byte get() = ConstantMethodrefInfo.tag
    override val type: String get() = ConstantMethodrefInfo.name
    override fun toJson() = super.toJson().with("index", index)

    companion object {
        const val name = "ConstantMethodTypeInfo"
        const val tag = ConstantTags.CONSTANT_METHODTYPE
    }

}