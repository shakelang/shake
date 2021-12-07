package io.github.shakelang.jvmlib.constants

class ConstantFieldrefInfo(val classRef: Int, val nameTypeRef: Int) : CONSTANT() {

    override val tag: Byte = 9
    override fun toJson() = super.toJson().with("classRef", classRef).with("nameTypeRef", nameTypeRef)
    override val type: String get() = "CONSTANT_Fieldref"

}