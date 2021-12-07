package io.github.shakelang.jvmlib.constants

class ConstantInterfaceMethodrefInfo(val classRef: Int, val nameTypeRef: Int) : CONSTANT() {

    override val tag: Byte get() = ConstantInterfaceMethodrefInfo.tag
    override val type: String get() = name

    override fun toJson() = super.toJson().with("classRef", classRef).with("nameTypeRef", nameTypeRef)

    companion object {
        const val name: String = "ConstantInterfaceMethodrefInfo"
        const val tag: Byte = ConstantTags.CONSTANT_INTERFACE_METHOD_REF
    }

}