package io.github.shakelang.jvmlib.constants

class ConstantFieldrefInfo(val classRef: Int, val nameTypeRef: Int) : CONSTANT() {

    override val tag: Byte get() = ConstantFieldrefInfo.tag
    override val type: String get() = name
    override fun toJson() = super.toJson().with("classRef", classRef).with("nameTypeRef", nameTypeRef)

    companion object {
        const val name = "ConstantFieldrefInfo"
        const val tag = ConstantTags.CONSTANT_FIELD_REF
    }

}